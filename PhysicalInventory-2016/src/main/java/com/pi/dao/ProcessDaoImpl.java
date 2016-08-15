package com.pi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.pi.model.StoreProcess;

@Repository("processDao")
public class ProcessDaoImpl implements ProcessDao {

	private String sql = null;
	ResultSet rst = null;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void updateStore(StoreProcess process) {
		final List<String> stNoList = process.getStoreNoList();
		final List<String> unselctedList = process.getUncheckList();
		final String processDate = process.getProcessDate();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(processDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		final Timestamp invRunDate = new java.sql.Timestamp(
				parsedDate.getTime());
		final Timestamp timeStamp = new java.sql.Timestamp(new Date().getTime());

		if (null != stNoList && stNoList.size() > 0) {
			sql = "UPDATE PHY_INV_STORES_PO SET PARTICIPATING_STORE='Y',UPDATED_DATE=?, INV_RUN_DATE = ? WHERE STORE_NUMBER=?";
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				public void setValues(PreparedStatement ps, int i)
						throws SQLException {
					String storeNo = stNoList.get(i);
					ps.setTimestamp(1, timeStamp);
					ps.setTimestamp(2, invRunDate);
					ps.setString(3, storeNo);
				}

				public int getBatchSize() {
					return stNoList.size();
				}
			});
		}

		if (null != unselctedList && unselctedList.size() > 0) {
			System.out.println("inside unselected method");
			sql = "UPDATE PHY_INV_STORES_PO SET PARTICIPATING_STORE='N',UPDATED_DATE=?, INV_RUN_DATE = ? WHERE STORE_NUMBER=?";
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				public void setValues(PreparedStatement ps, int i)
						throws SQLException {

					String storeNo = unselctedList.get(i);
					ps.setTimestamp(1, timeStamp);
					ps.setString(2, null);
					ps.setString(3, storeNo);

				}

				public int getBatchSize() {
					return unselctedList.size();
				}
			});
		}
		// update to the log table
		int task_id = getTaskId();
		String sql = "INSERT INTO PHY_INV_TASKS_LOG(ID, CREATED_DATE,TASK_ID) VALUES(TASKS_LOG_SEQ.NEXTVAL,?,?)";
		jdbcTemplate.update(sql, timeStamp, task_id);
		System.out.println("updated in PHY_INV_TASKS_LOG table ");
	}

	@Override
	public List<StoreProcess> getAllStorePo() {
		sql = "SELECT STORE_NUMBER,PARTICIPATING_STORE FROM PHY_INV_STORES_PO";
		return jdbcTemplate.query(sql,
				new ResultSetExtractor<List<StoreProcess>>() {

					@Override
					public List<StoreProcess> extractData(ResultSet rst)
							throws SQLException, DataAccessException {
						List<StoreProcess> sList = new ArrayList<StoreProcess>();
						while (rst.next()) {

							StoreProcess store = new StoreProcess();

							store.setStoreNo(rst.getString("STORE_NUMBER"));
							String status = rst
									.getString("PARTICIPATING_STORE");
							if (status.equals("Y")) {
								store.setStatus(true);
							} else {
								store.setStatus(false);
							}
							sList.add(store);
						}
						return sList;
					}

				});

	}

	@Override
	public int storeTaskId(String requestId) {
		int task_id = getTaskId();
		String taskPOQuery = "update PHY_INV_TASKS_PO set STATUS=? WHERE TASKS = ?";
		jdbcTemplate.update(taskPOQuery, "APPROVED", "Stores Taking Data");
		sql = "INSERT INTO PHY_INV_TASKS_LOG(ID, REQUEST_ID,CREATED_DATE,TASK_ID) VALUES('1',?,?,?)";
		return jdbcTemplate.update(sql, requestId, new Date(), task_id);
	}

	public int getTaskId() {
		String query = "SELECT TASK_ID FROM PHY_INV_TASKS_PO WHERE TASKS = 'Stores Taking Data'";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(query);
		int task_id = 0;
		while (rs.next()) {
			task_id = rs.getInt("task_id");
		}
		return task_id;
	}

	@Override
	public List<StoreProcess> getAllReport() {
		sql = "SELECT STORE_NUMBER,PARTICIPATING_STORE FROM PHY_INV_STORES_PO where participating_store='Y'";
		return jdbcTemplate.query(sql,
				new ResultSetExtractor<List<StoreProcess>>() {
					@Override
					public List<StoreProcess> extractData(ResultSet rst)
							throws SQLException, DataAccessException {
						List<StoreProcess> sList = new ArrayList<StoreProcess>();
						while (rst.next()) {
							StoreProcess store = new StoreProcess();
							store.setStoreNo(rst.getString("STORE_NUMBER"));
							String status = rst
									.getString("PARTICIPATING_STORE");
							if (status.equals("Y")) {
								store.setStatus(true);
							} else {
								store.setStatus(false);
							}
							sList.add(store);
						}
						return sList;
					}
				});
	}

}

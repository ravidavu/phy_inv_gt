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

	private final static String SQL_UPDATE_STORES_PO_Y = "UPDATE PHY_INV_STORES_PO SET PARTICIPATING_STORE='Y',UPDATED_DATE=?,INV_RUN_DATE = ? WHERE STORE_NUMBER=?";
	private final static String SQL_UPDATE_STORES_PO_N = "UPDATE PHY_INV_STORES_PO SET PARTICIPATING_STORE='N',UPDATED_DATE=?, INV_RUN_DATE = ? WHERE STORE_NUMBER=?";
	private final static String SQL_TASKS_PO = "UPDATE PHY_INV_TASKS_PO SET STATUS=? WHERE TASKS = ?";

	private final static String SQL_TASKS_LOG = "INSERT INTO PHY_INV_TASKS_LOG(ID, CREATED_DATE,TASK_ID) VALUES(TASKS_LOG_SEQ.NEXTVAL,?,?)";
	private final static String SQL_STORE_REQUEST_ID = "INSERT INTO PHY_INV_TASKS_LOG(ID, REQUEST_ID,CREATED_DATE,TASK_ID) VALUES(TASKS_LOG_SEQ.NEXTVAL,?,?,?)";

	private final static String SQL_ALL_STORES_PO = "SELECT STORE_NUMBER,PARTICIPATING_STORE FROM PHY_INV_STORES_PO";
	private final static String SQL_GET_ALL_SELECTED_STORES = "SELECT STORE_NUMBER,PARTICIPATING_STORE FROM PHY_INV_STORES_PO where participating_store='Y'";
	private final static String SQL_GET_TASK_ID_WITH_STR_TAKING_DATA = "SELECT TASK_ID FROM PHY_INV_TASKS_PO WHERE TASKS = 'Stores Taking Data'";

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
			jdbcTemplate.batchUpdate(SQL_UPDATE_STORES_PO_Y,
					new BatchPreparedStatementSetter() {

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
			jdbcTemplate.batchUpdate(SQL_UPDATE_STORES_PO_N,
					new BatchPreparedStatementSetter() {

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
		jdbcTemplate.update(SQL_TASKS_LOG, timeStamp, task_id);
		// update the tasks_po_table
		jdbcTemplate.update(SQL_TASKS_PO, "SAVED", "Stores Taking Data");
		System.out.println("updated in PHY_INV_TASKS_LOG table ");
	}

	@Override
	public List<StoreProcess> getAllStorePo() {

		return jdbcTemplate.query(SQL_ALL_STORES_PO,
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
		jdbcTemplate.update(SQL_TASKS_PO, "APPROVED", "Stores Taking Data");
		return jdbcTemplate.update(SQL_STORE_REQUEST_ID, requestId, new Date(),
				task_id);
	}

	private int getTaskId() {

		SqlRowSet rs = jdbcTemplate
				.queryForRowSet(SQL_GET_TASK_ID_WITH_STR_TAKING_DATA);
		int task_id = 0;
		while (rs.next()) {
			task_id = rs.getInt("task_id");
		}
		return task_id;
	}

	@Override
	public List<StoreProcess> getAllReport() {
		return jdbcTemplate.query(SQL_GET_ALL_SELECTED_STORES,
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

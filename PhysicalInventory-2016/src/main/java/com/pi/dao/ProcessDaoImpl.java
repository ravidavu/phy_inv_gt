package com.pi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

		if (null != stNoList && stNoList.size() > 0) {
			sql = "UPDATE PHY_INV_STORES_PO SET STATUS_FLAG='1',UPDATED_ID=? WHERE STORE_NUMBER=? AND STATUS_FLAG='0'";
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				public void setValues(PreparedStatement ps, int i)
						throws SQLException {
					String storeNo = stNoList.get(i);
					ps.setString(1, "SYSTEM");
					ps.setString(2, storeNo);
				}

				public int getBatchSize() {
					return stNoList.size();
				}
			});
		}

		if (null != unselctedList && unselctedList.size() > 0) {

			sql = "UPDATE PHY_INV_STORES_PO SET STATUS_FLAG='0',UPDATED_ID=? WHERE STORE_NUMBER =? AND STATUS_FLAG='1'";
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				public void setValues(PreparedStatement ps, int i)
						throws SQLException {

					String storeNo = unselctedList.get(i);
					ps.setString(1, "SYSTEM");
					ps.setString(2, storeNo);

				}

				public int getBatchSize() {
					return unselctedList.size();
				}
			});
		}

	}

	@Override
	public List<StoreProcess> getAllStorePo() {
		sql = "SELECT STORE_NUMBER,STATUS_FLAG FROM PHY_INV_STORES_PO";
		return jdbcTemplate.query(sql,
				new ResultSetExtractor<List<StoreProcess>>() {

					@Override
					public List<StoreProcess> extractData(ResultSet rst)
							throws SQLException, DataAccessException {
						List<StoreProcess> sList = new ArrayList<StoreProcess>();
						while (rst.next()) {

							StoreProcess store = new StoreProcess();

							store.setStoreNo(rst.getString("STORE_NUMBER"));
							String status = rst.getString("STATUS_FLAG");
							if (status.equals("1")) {
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
	public int storeTaskId(String taskId) {
		String query = "SELECT TASK_ID FROM PHY_INV_TASKS_PO WHERE TASKS = ?";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(query, "Approval");
		int task_id = 0;
		if (rs.next()) {
			task_id = rs.getInt("task_id");
		}
		sql = "INSERT INTO PHY_INV_TASKS_LOG(ID, REQUEST_ID,CREATED_DATE,TASK_ID) VALUES(TASKS_LOG_SEQ.NEXTVAL,?,?,?)";
		return jdbcTemplate.update(sql, taskId, new Date(), task_id);
	}

}

package com.pi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.pi.model.Obsolescence;

@Repository("obsolDao")
public class ObsolescenceDaoImpl implements ObsolescenceDao {
	private final static String SQL_TASK_ID_FORCED_OBSOLES = "SELECT TASK_ID FROM PHY_INV_TASKS_PO WHERE TASKS = 'Forced Obsolescence'";
	private final static String SQL_GET_ALL_OBSOLES = "SELECT * FROM PHY_INV_OBSOLESCENCE";

	private final static String SQL_INSERT_OBSOLES = "INSERT INTO PHY_INV_OBSOLESCENCE(STORE_NUMBER,STORE_SKU) VALUES (?,?)";
	private final static String SQL_TASKS_LOG_OBSOLES = "INSERT INTO PHY_INV_TASKS_LOG(ID, CREATED_DATE,TASK_ID,ACTION_PERFORMED,UPDATED_VALUES) VALUES(TASKS_LOG_SEQ.NEXTVAL,?,?,?,?)";

	private final static String SQL_DELETE_OBSOLES = "DELETE from PHY_INV_OBSOLESCENCE WHERE STORE_SKU = ?";

	private final static String SQL_TASKS_PO_OBSOLES = "update PHY_INV_TASKS_PO set STATUS=? WHERE TASKS = ?";

	final Timestamp timeStamp = new java.sql.Timestamp(new Date().getTime());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Boolean createObsolence(final Obsolescence obs) {
		List<String> createObsolenceList = new ArrayList<String>();
		createObsolenceList.add(obs.getSkuNo());
		Boolean result = jdbcTemplate.execute(SQL_INSERT_OBSOLES,
		new PreparedStatementCallback<Boolean>() {
		public Boolean doInPreparedStatement(PreparedStatement ps)
		throws SQLException, DataAccessException {
		ps.setInt(1, obs.getoId());
		ps.setString(2, obs.getSkuNo());
		return ps.execute();
		}
		});

		logData("Insert",createObsolenceList);
		return result;
		}
	@Override
	public List<Obsolescence> getAllObsolence() {
		return jdbcTemplate.query(SQL_GET_ALL_OBSOLES,
				new ResultSetExtractor<List<Obsolescence>>() {

					@Override
					public List<Obsolescence> extractData(ResultSet rst)
							throws SQLException, DataAccessException {
						List<Obsolescence> oList = new ArrayList<Obsolescence>();
						int i = 0;
						StringBuffer skuNo = new StringBuffer("");
						while (rst.next()) {
							i = i + 1;
							skuNo.append(" " + rst.getString("STORE_SKU"));
							Obsolescence obsolen = new Obsolescence();
							obsolen.setSkuNo(rst.getString("STORE_SKU"));
							oList.add(obsolen);

						}
						return oList;
					}
				});
	}

	@Override
	public void deleteObsolence(Obsolescence obsolence) {
		final List<String> delObsoList = obsolence.getSkuNoList();
		try {
			jdbcTemplate.batchUpdate(SQL_DELETE_OBSOLES,
					new BatchPreparedStatementSetter() {
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							String skuNo = delObsoList.get(i);
							ps.setString(1, skuNo);
						}

						public int getBatchSize() {
							return delObsoList.size();
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}

		logData("DELETE",delObsoList);
	}


	private void logData(String action, List<String> data) {
		int task_id = getTaskIdForObso();
		jdbcTemplate.update(SQL_TASKS_LOG_OBSOLES, timeStamp, task_id, action,StringUtils.join(data,','));
		jdbcTemplate.update(SQL_TASKS_PO_OBSOLES, "SAVED","Forced Obsolescence");
	}

	private int getTaskIdForObso() {
		SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_TASK_ID_FORCED_OBSOLES);
		int task_id = 0;
		while (rs.next()) {
			task_id = rs.getInt("task_id");
		}
		return task_id;
	}

}

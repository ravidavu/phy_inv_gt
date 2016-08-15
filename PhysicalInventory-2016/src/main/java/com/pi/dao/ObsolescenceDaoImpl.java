package com.pi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.pi.model.Obsolescence;

@Repository("obsolDao")
public class ObsolescenceDaoImpl implements ObsolescenceDao {
	private String sql = null;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void createObsolence(Obsolescence obs) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Obsolescence> getAllObsolence() {
		sql = "SELECT * FROM PHY_INV_OBSOLESCENCE";
		return jdbcTemplate.query(sql,
				new ResultSetExtractor<List<Obsolescence>>() {

					@Override
					public List<Obsolescence> extractData(ResultSet rst)
							throws SQLException, DataAccessException {
						List<Obsolescence> oList = new ArrayList<Obsolescence>();
						int i=0;
						StringBuffer skuNo= new StringBuffer("");
						while (rst.next()) {
							i=i+1;
							skuNo.append(" "+rst.getString("STORE_SKU"));
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
		try{
		String deleteSql = "DELETE from PHY_INV_OBSOLESCENCE WHERE STORE_SKU = ?";
		final List<String> delObsoList = obsolence.getSkuNoList();
		jdbcTemplate.batchUpdate(deleteSql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)	throws SQLException {
				String skuNo = delObsoList.get(i);
				ps.setString(1, skuNo);
			}
			public int getBatchSize() {
				return delObsoList.size();
			}
		});
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

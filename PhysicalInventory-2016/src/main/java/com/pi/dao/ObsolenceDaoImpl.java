package com.pi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.pi.model.Obsolescence;

@Repository("obsolDao")
public class ObsolenceDaoImpl implements ObsolescenceDao {
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
						int oid = 0;
						while (rst.next()) {
							i=i+1;
							skuNo.append(" "+rst.getString("STORE_SKU"));
							
								
							Obsolescence obsolen = new Obsolescence();
							//obsolen.setoId(rst.getInt("oid"));
							obsolen.setSkuNo(rst.getString("STORE_SKU"));
							oList.add(obsolen);
							
						}
						return oList;
					}
				});
	}

}

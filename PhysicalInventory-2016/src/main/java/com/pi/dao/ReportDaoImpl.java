package com.pi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.pi.model.Report;
import com.pi.model.StoreProcess;

public class ReportDaoImpl implements ReportDao{

	private String sql = null;
	ResultSet rst = null;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Object> getReport(int storeId) {
		String floorSql = "select a.TY_SALES,a.LY_SALES,a.TY_GM,a.LY_GM from phy_inv_sales_totals a,phy_inv_product_desc b " +
				"where b.floor_covering=a.id and b.store_id=?";
		List<Object> rptList = new ArrayList<Object>();
		SqlRowSet floorRs = jdbcTemplate.queryForRowSet(floorSql, storeId);
		List<String> floorList = new ArrayList<String>();
		int task_id=0;
		if(floorRs.next()){
			floorList.add(floorRs.getString("TY_SALES"));
			floorList.add(floorRs.getString("LY_SALES"));
			floorList.add(floorRs.getString("TY_GM"));
			floorList.add(floorRs.getString("LY_GM"));
		}
		
		String sparySql = "select a.TY_SALES,a.LY_SALES,a.TY_GM,a.LY_GM from phy_inv_sales_totals a,phy_inv_product_desc b " +
		"where b.floor_covering=a.id and b.store_id=?";

		SqlRowSet rs = jdbcTemplate.queryForRowSet(sparySql, storeId);
		List<String> sparyList = new ArrayList<String>();
		if(rs.next()){
			sparyList.add(floorRs.getString("TY_SALES"));
			sparyList.add(floorRs.getString("LY_SALES"));
			sparyList.add(floorRs.getString("TY_GM"));
			sparyList.add(floorRs.getString("LY_GM"));
		}
		rptList.set(0, floorList);
		rptList.set(1, sparyList);
		
		return rptList;
	}

}

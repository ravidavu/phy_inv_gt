package com.pi.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository("reportDao")
public class ReportDaoImpl implements ReportDao {

	ResultSet rst = null;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	ResultSet floorRs = null;
	ResultSet rs = null;
	public DecimalFormat decimalFormat = new DecimalFormat("##.00");

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Map<String, List<String>> getReport(int storeId) {
		final List<String> columns = new ArrayList<String>();
		jdbcTemplate.query("select * from phy_inv_sales_totals",
				new ResultSetExtractor<Integer>() {

					@Override
					public Integer extractData(final ResultSet rs)
							throws SQLException, DataAccessException {
						final ResultSetMetaData rsmd = rs.getMetaData();
						final int columnCount = rsmd.getColumnCount();
						for (int i = 1; i <= columnCount; i++) {
							columns.add(rsmd.getColumnName(i));
						}
						return columnCount;
					}
				});
		System.out.println("columns " + columns.get(0));
		String floorSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b "
				+ "where b.floor_covering=a.id and b.store_id=1001";
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		SqlRowSet floorRs = jdbcTemplate.queryForRowSet(floorSql);
		List<String> floorList = new ArrayList<String>();
		List<String> ty_sales_totals = new ArrayList<String>();
		boolean floorFlag = true;
		while (floorRs.next()) {
			floorFlag = false;
			ty_sales_totals.add(floorRs.getString("TY_SALES"));

			for (int i = 1; i < columns.size(); i++) {
				floorList.add(formatNumber(floorRs.getString(columns.get(i))));
			}
		}
		if (floorFlag) {
			for (int i = 1; i < columns.size(); i++) {
				floorList.add(" ");
			}
		}

		System.out.println("===>floorList..." + floorList.size());
		String sparySql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b "
				+ "where b.SPRAY_EQUIPMENT=a.id and b.ID=1001";
		SqlRowSet sparyRS = jdbcTemplate.queryForRowSet(sparySql);
		List<String> sparyList = new ArrayList<String>();

		boolean sparyFlag = true;
		while (sparyRS.next()) {
			sparyFlag = false;
			ty_sales_totals.add(sparyRS.getString("TY_SALES"));
			for (int i = 1; i < columns.size(); i++) {
				sparyList.add(formatNumber(sparyRS.getString(columns.get(i))));
			}
		}
		if (sparyFlag) {
			for (int i = 1; i < columns.size(); i++) {
				sparyList.add(" ");
			}
		}
		String paintSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b "
				+ "where b.PAINT=a.id and b.ID=1001";
		SqlRowSet paintRS = jdbcTemplate.queryForRowSet(paintSql);
		List<String> paintList = new ArrayList<String>();
		boolean paintFlag = true;
		while (paintRS.next()) {
			paintFlag = false;
			ty_sales_totals.add(paintRS.getString("TY_SALES"));

			for (int i = 1; i < columns.size(); i++) {
				paintList.add(formatNumber(paintRS.getString(columns.get(i))));
			}
		}
		if (paintFlag) {
			for (int i = 1; i < columns.size(); i++) {
				paintList.add(" ");
			}
		}

		String brushRollerSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b "
				+ "where b.brushes_rollers=a.id and b.ID=1001";
		SqlRowSet brushRollerRS = jdbcTemplate.queryForRowSet(brushRollerSql);
		List<String> brushRollerList = new ArrayList<String>();
		boolean brushRollerFlag = true;
		while (brushRollerRS.next()) {
			ty_sales_totals.add(brushRollerRS.getString("TY_SALES"));
			brushRollerFlag = false;
			for (int i = 1; i < columns.size(); i++) {
				brushRollerList.add(formatNumber(brushRollerRS
						.getString(columns.get(i))));
			}
		}
		if (brushRollerFlag) {
			for (int i = 1; i < columns.size(); i++) {
				brushRollerList.add(" ");
			}
		}
		String assocProdcutsSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b "
				+ "where b.ASSOC_PRODUCTS=a.id and b.ID=1001";
		SqlRowSet assocProdcutsRS = jdbcTemplate
				.queryForRowSet(assocProdcutsSql);
		List<String> assocProdcutsList = new ArrayList<String>();
		boolean assocProdcutsFlag = true;
		while (assocProdcutsRS.next()) {
			assocProdcutsFlag = false;
			ty_sales_totals.add(assocProdcutsRS.getString("TY_SALES"));

			for (int i = 1; i < columns.size(); i++) {
				assocProdcutsList.add(formatNumber(assocProdcutsRS
						.getString(columns.get(i))));
			}
		}
		if (assocProdcutsFlag) {
			for (int i = 1; i < columns.size(); i++) {
				assocProdcutsList.add(" ");
			}
		}
		String wallCoveringSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b "
				+ "where b.WALL_COVERING=a.id and b.ID=1001";
		SqlRowSet wallCoveringRS = jdbcTemplate.queryForRowSet(wallCoveringSql);
		List<String> wallCoveringList = new ArrayList<String>();
		boolean wallCoveringFlag = true;
		while (wallCoveringRS.next()) {
			ty_sales_totals.add(wallCoveringRS.getString("TY_SALES"));
			wallCoveringFlag = false;
			for (int i = 1; i < columns.size(); i++) {
				wallCoveringList.add(formatNumber(wallCoveringRS
						.getString(columns.get(i))));
			}
		}
		if (wallCoveringFlag) {
			for (int i = 1; i < columns.size(); i++) {
				wallCoveringList.add(" ");
			}
		}
		String windowTreatSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b "
				+ "where b.WINDOW_TRATMENT=a.id and b.ID=1001";
		SqlRowSet windowTreatRS = jdbcTemplate.queryForRowSet(windowTreatSql);
		List<String> windowTreatList = new ArrayList<String>();
		boolean windowTreatFlag = true;
		while (windowTreatRS.next()) {
			ty_sales_totals.add(windowTreatRS.getString("TY_SALES"));
			windowTreatFlag = false;
			for (int i = 1; i < columns.size(); i++) {
				windowTreatList.add(formatNumber(windowTreatRS
						.getString(columns.get(i))));
			}
		}
		if (windowTreatFlag) {
			for (int i = 1; i < columns.size(); i++) {
				windowTreatList.add(" ");
			}
		}

		String desStr = "TY SALES $ ( 11/09 - 10/10 ),LY SALES $ ( 11/08 - 10/09 ) ,TY GM % ( 11/09 - 10/10 ) ,  LY GM % ( 11/08 - 10/09 ) , NET BOOK INVENTORY ,TINT USE ADJUSTMENT ,( + )  CLOSING REPORTS, \"A\"   CHRGD TO STORE-NOT RECVD( INT ) ,\"B\"   RECVD-NOT CHRGD TO STORE( EXT ) ,"
				+ "\"C\"   RECVD-NOT CHRGD TO STORE( INT ) ,   \"D\"   OPEN IBARS/ISTS/EXPENSED MDSE TRAN , \"E\"   OPEN CHARGE-BACKS(EXT) ,\"F\"   CHRGD TO STORE-NOT RECVD( EXT ) , \"R\"   OPEN DOSCREPANCY REPORTS( EXT ) ,\"Z\"   OPEN MOUTH SALES REPORTS ,"
				+ " =   ADJUSTED BOOK INVENTORY ,GROSS PHYSICAL INVENTORY ( AS COUNTED ) , (+)   PHYSICAL INV ADJUSTMENTS ,=  ADJUSTED GROSS PHYSICAL INVENTORY , =  TY INVENTORY SHRINK/( PICKUP )%( 3710 A ,TY INVENTORY SHRINK/( PICKUP )% TO SLS ,CURRENT YEAR OBSOLESCENCE ,(+) CURRENT YEAR OBS ADJUSTMENTS ,"
				+ " = ADJUSTED CURRENT YEAR OBS ( 3700 ACCT ) , = ADJUSTED CURRENT YEAR OBS % TO SLS ,TOTAL P&L ADJUSTMENT $ , TOTAL P&L ADJUSTMENT % TO SALES,  LY INVENTORY SHRINK/ (PICKUP) , LY ADJUSTED CURRENT YEAR OBS ,"
				+ "LY TOTAL P&L ADJUSTMENT  $,LY P&L ADJUSTMENT % TO SALES,TOTAL P&L ADJUSTMENT $ CHANGE TY VS LY ";

		List<String> descList = new ArrayList<String>(Arrays.asList(desStr
				.split(",")));

		System.out.println("descList size " + descList.size() + " paintList "
				+ paintList.size() + " brushRollerList "
				+ brushRollerList.size());
		System.out.println("assocProdcutsList size " + assocProdcutsList.size()
				+ " floorList " + floorList.size() + " sparyList "
				+ sparyList.size());
		System.out.println("wallCoveringList size " + wallCoveringList.size()
				+ " windowTreatList " + windowTreatList.size());

		List<String> totalList = new ArrayList<String>();
		for (int i = 0; i < descList.size(); i++) {
			double result = reverseformatNumber(paintList.get(i))
					+ reverseformatNumber(brushRollerList.get(i))
					+ reverseformatNumber(assocProdcutsList.get(i))
					+ reverseformatNumber(floorList.get(i))
					+ reverseformatNumber(sparyList.get(i))
					+ reverseformatNumber(wallCoveringList.get(i))
					+ reverseformatNumber(windowTreatList.get(i));
			totalList.add(formatNumber(decimalFormat.format(result) + ""));

			map.put("description", descList);
			map.put("paint", paintList);
			map.put("brush", brushRollerList);
			map.put("assocprod", assocProdcutsList);
			map.put("floor", floorList);
			map.put("spray", sparyList);
			map.put("wall", wallCoveringList);
			map.put("windowtreat", windowTreatList);
			map.put("totalList", totalList);
		}
		return map;
	}

	private String formatNumber(String string) {
		String formateedString = " ";
		if (null != string && string.length() > 0) {
			float number = Float.parseFloat(string);
			if (number < 0) {
				number = -number;
				formateedString = NumberFormat.getNumberInstance(Locale.US)
						.format(number) + "-";
			} else {
				formateedString = NumberFormat.getNumberInstance(Locale.US)
						.format(number);
			}
		}
		return formateedString;
	}

	// again remove comma and append - to the front(reverse operation)
	private static double reverseformatNumber(String string) {
		float result = 0;
		if (null != string && string.length() > 0) {
			if (string.contains(",")) {
				string = string.replaceAll(",", "");
			}
			if (string.indexOf("-") > 0) {
				string = "-" + string.substring(0, string.lastIndexOf("-"));
			}
			try {
				result = Float.parseFloat(string);
			} catch (Exception e) {
				result = 0;
			}
		}
		return result;
	}
}

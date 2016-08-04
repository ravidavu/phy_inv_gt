package com.pi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository("reportDao")
public class ReportDaoImpl implements ReportDao{

	private String sql = null;
	ResultSet rst = null;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	ResultSet floorRs = null;
	ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pst = null;
	private String query = null;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	@Override
	public Map<String,List<String>> getReport(int storeId) {
		String floorSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b " +
				"where b.floor_covering=a.id and b.store_id=1001";
		Map<String,List<String>> map= new HashMap<String,List<String>>();
		System.out.println("insdie report dao "+jdbcTemplate);
		List<Object> rptList = new ArrayList<Object>();
		SqlRowSet floorRs = jdbcTemplate.queryForRowSet(floorSql);
		List<String> floorList = new ArrayList<String>();
		/*conn = ConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(floorSql);
		
		floorRs = pst.executeQuery();
		int task_id=0;*/
		while(floorRs.next()){
			floorList.add(floorRs.getString("TY_SALES"));
			floorList.add(floorRs.getString("LY_SALES"));
			floorList.add(floorRs.getString("TY_GM"));
			floorList.add(floorRs.getString("LY_GM"));
			
			floorList.add(floorRs.getString("NET_BOOK_INV"));
			floorList.add(floorRs.getString("TINT_USE_ADJ"));
			floorList.add(floorRs.getString("CLOSING_REPORT"));
			floorList.add(floorRs.getString("A_CHRGD_TO_STR_NOT_RECVD_INT"));
			floorList.add(floorRs.getString("B_RECVD_NOT_CHRGD_TO_STR_EXT"));
			floorList.add(floorRs.getString("C_RECVD_NOT_CHRGD_TO_STR_INT"));
			floorList.add(floorRs.getString("D_OPEN_IBARS"));
			floorList.add(floorRs.getString("E_OPEN_CHARGE_BACKS"));
			floorList.add(floorRs.getString("F_CHRGD_STR_NOT_RECVD_EXT"));
			floorList.add(floorRs.getString("R_OPEN_DISCREPANCY_RPT_EXT"));
			floorList.add(floorRs.getString("Z_OPEN_MON_SALES_RPT"));
			floorList.add(floorRs.getString("ADJUSTED_BOOK_INV"));
			floorList.add(floorRs.getString("GROSS_PHY_INV"));
			floorList.add(floorRs.getString("PHY_INV_ADJ"));
			floorList.add(floorRs.getString("ADJ_GROSS_PHY_INV"));
			floorList.add(floorRs.getString("TY_INV_SHRINK_3710_A"));
			floorList.add(floorRs.getString("TY_INV_SHRINK_TO_SLS"));
			floorList.add(floorRs.getString("CURRENT_YEAR_OBS_ADJ"));
			floorList.add(floorRs.getString("ADJ_CURRENT_YEAR_OBS"));
			floorList.add(floorRs.getString("ADJ_CURRENT_YEAR_OBS_PERCENT"));
			floorList.add(floorRs.getString("TOTAL_PL_ADJ"));
			floorList.add(floorRs.getString("TOTAL_PL_ADJ_TO_SALES"));
			floorList.add(floorRs.getString("LY_INV_SHRINK"));
			floorList.add(floorRs.getString("LY_ADJ_CURRENT_YEAR_OBS"));
			floorList.add(floorRs.getString("LY_TOTAL_PL_ADJ"));
			floorList.add(floorRs.getString("LY_PL_ADJ_PERCENT_TO_SALES"));
			floorList.add(floorRs.getString("TOTAL_PL_ADJ_CHANGE_TY_VS_LY"));
			
		}
		
		System.out.println("===>floorList..."+floorList.size());
		String sparySql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b " +
		"where b.SPRAY_EQUIPMENT=a.id and b.ID=1001";
		SqlRowSet sparyRS = jdbcTemplate.queryForRowSet(sparySql);
		List<String> sparyList = new ArrayList<String>();
		while(sparyRS.next()){
			sparyList.add(sparyRS.getString("TY_SALES"));
			sparyList.add(sparyRS.getString("LY_SALES"));
			sparyList.add(sparyRS.getString("TY_GM"));
			sparyList.add(sparyRS.getString("LY_GM"));

			sparyList.add(sparyRS.getString("NET_BOOK_INV"));
			sparyList.add(sparyRS.getString("TINT_USE_ADJ"));
			sparyList.add(sparyRS.getString("CLOSING_REPORT"));
			sparyList.add(sparyRS.getString("A_CHRGD_TO_STR_NOT_RECVD_INT"));
			sparyList.add(sparyRS.getString("B_RECVD_NOT_CHRGD_TO_STR_EXT"));
			sparyList.add(sparyRS.getString("C_RECVD_NOT_CHRGD_TO_STR_INT"));
			sparyList.add(sparyRS.getString("D_OPEN_IBARS"));
			sparyList.add(sparyRS.getString("E_OPEN_CHARGE_BACKS"));
			sparyList.add(sparyRS.getString("F_CHRGD_STR_NOT_RECVD_EXT"));
			sparyList.add(sparyRS.getString("R_OPEN_DISCREPANCY_RPT_EXT"));
			sparyList.add(sparyRS.getString("Z_OPEN_MON_SALES_RPT"));
			sparyList.add(sparyRS.getString("ADJUSTED_BOOK_INV"));
			sparyList.add(sparyRS.getString("GROSS_PHY_INV"));
			sparyList.add(sparyRS.getString("PHY_INV_ADJ"));
			sparyList.add(sparyRS.getString("ADJ_GROSS_PHY_INV"));
			sparyList.add(sparyRS.getString("TY_INV_SHRINK_3710_A"));
			sparyList.add(sparyRS.getString("TY_INV_SHRINK_TO_SLS"));
			sparyList.add(sparyRS.getString("CURRENT_YEAR_OBS_ADJ"));
			sparyList.add(sparyRS.getString("ADJ_CURRENT_YEAR_OBS"));
			sparyList.add(sparyRS.getString("ADJ_CURRENT_YEAR_OBS_PERCENT"));
			sparyList.add(sparyRS.getString("TOTAL_PL_ADJ"));
			sparyList.add(sparyRS.getString("TOTAL_PL_ADJ_TO_SALES"));
			sparyList.add(sparyRS.getString("LY_INV_SHRINK"));
			sparyList.add(sparyRS.getString("LY_ADJ_CURRENT_YEAR_OBS"));
			sparyList.add(sparyRS.getString("LY_TOTAL_PL_ADJ"));
			sparyList.add(sparyRS.getString("LY_PL_ADJ_PERCENT_TO_SALES"));
			sparyList.add(sparyRS.getString("TOTAL_PL_ADJ_CHANGE_TY_VS_LY"));
		}
		
		String paintSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b " +
							"where b.PAINT=a.id and b.ID=1001";
		SqlRowSet paintRS = jdbcTemplate.queryForRowSet(paintSql);
		List<String> paintList = new ArrayList<String>();
		while(paintRS.next()){
			paintList.add(paintRS.getString("TY_SALES"));
			paintList.add(paintRS.getString("LY_SALES"));
			paintList.add(paintRS.getString("TY_GM"));
			paintList.add(paintRS.getString("LY_GM"));

			paintList.add(paintRS.getString("NET_BOOK_INV"));
			paintList.add(paintRS.getString("TINT_USE_ADJ"));
			paintList.add(paintRS.getString("CLOSING_REPORT"));
			paintList.add(paintRS.getString("A_CHRGD_TO_STR_NOT_RECVD_INT"));
			paintList.add(paintRS.getString("B_RECVD_NOT_CHRGD_TO_STR_EXT"));
			paintList.add(paintRS.getString("C_RECVD_NOT_CHRGD_TO_STR_INT"));
			paintList.add(paintRS.getString("D_OPEN_IBARS"));
			paintList.add(paintRS.getString("E_OPEN_CHARGE_BACKS"));
			paintList.add(paintRS.getString("F_CHRGD_STR_NOT_RECVD_EXT"));
			paintList.add(paintRS.getString("R_OPEN_DISCREPANCY_RPT_EXT"));
			paintList.add(paintRS.getString("Z_OPEN_MON_SALES_RPT"));
			paintList.add(paintRS.getString("ADJUSTED_BOOK_INV"));
			paintList.add(paintRS.getString("GROSS_PHY_INV"));
			paintList.add(paintRS.getString("PHY_INV_ADJ"));
			paintList.add(paintRS.getString("ADJ_GROSS_PHY_INV"));
			paintList.add(paintRS.getString("TY_INV_SHRINK_3710_A"));
			paintList.add(paintRS.getString("TY_INV_SHRINK_TO_SLS"));
			paintList.add(paintRS.getString("CURRENT_YEAR_OBS_ADJ"));
			paintList.add(paintRS.getString("ADJ_CURRENT_YEAR_OBS"));
			paintList.add(paintRS.getString("ADJ_CURRENT_YEAR_OBS_PERCENT"));
			paintList.add(paintRS.getString("TOTAL_PL_ADJ"));
			paintList.add(paintRS.getString("TOTAL_PL_ADJ_TO_SALES"));
			paintList.add(paintRS.getString("LY_INV_SHRINK"));
			paintList.add(paintRS.getString("LY_ADJ_CURRENT_YEAR_OBS"));
			paintList.add(paintRS.getString("LY_TOTAL_PL_ADJ"));
			paintList.add(paintRS.getString("LY_PL_ADJ_PERCENT_TO_SALES"));
			paintList.add(paintRS.getString("TOTAL_PL_ADJ_CHANGE_TY_VS_LY"));
		}
		
		
		String brushRollerSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b " +
								"where b.brushes_rollers=a.id and b.ID=1001";
			SqlRowSet brushRollerRS = jdbcTemplate.queryForRowSet(brushRollerSql);
			List<String> brushRollerList = new ArrayList<String>();
			while(brushRollerRS.next()){
				brushRollerList.add(brushRollerRS.getString("TY_SALES"));
				brushRollerList.add(brushRollerRS.getString("LY_SALES"));
				brushRollerList.add(brushRollerRS.getString("TY_GM"));
				brushRollerList.add(brushRollerRS.getString("LY_GM"));
				
				brushRollerList.add(brushRollerRS.getString("NET_BOOK_INV"));
				brushRollerList.add(brushRollerRS.getString("TINT_USE_ADJ"));
				brushRollerList.add(brushRollerRS.getString("CLOSING_REPORT"));
				brushRollerList.add(brushRollerRS.getString("A_CHRGD_TO_STR_NOT_RECVD_INT"));
				brushRollerList.add(brushRollerRS.getString("B_RECVD_NOT_CHRGD_TO_STR_EXT"));
				brushRollerList.add(brushRollerRS.getString("C_RECVD_NOT_CHRGD_TO_STR_INT"));
				brushRollerList.add(brushRollerRS.getString("D_OPEN_IBARS"));
				brushRollerList.add(brushRollerRS.getString("E_OPEN_CHARGE_BACKS"));
				brushRollerList.add(brushRollerRS.getString("F_CHRGD_STR_NOT_RECVD_EXT"));
				brushRollerList.add(brushRollerRS.getString("R_OPEN_DISCREPANCY_RPT_EXT"));
				brushRollerList.add(brushRollerRS.getString("Z_OPEN_MON_SALES_RPT"));
				brushRollerList.add(brushRollerRS.getString("ADJUSTED_BOOK_INV"));
				brushRollerList.add(brushRollerRS.getString("GROSS_PHY_INV"));
				brushRollerList.add(brushRollerRS.getString("PHY_INV_ADJ"));
				brushRollerList.add(brushRollerRS.getString("ADJ_GROSS_PHY_INV"));
				brushRollerList.add(brushRollerRS.getString("TY_INV_SHRINK_3710_A"));
				brushRollerList.add(brushRollerRS.getString("TY_INV_SHRINK_TO_SLS"));
				brushRollerList.add(brushRollerRS.getString("CURRENT_YEAR_OBS_ADJ"));
				brushRollerList.add(brushRollerRS.getString("ADJ_CURRENT_YEAR_OBS"));
				brushRollerList.add(brushRollerRS.getString("ADJ_CURRENT_YEAR_OBS_PERCENT"));
				brushRollerList.add(brushRollerRS.getString("TOTAL_PL_ADJ"));
				brushRollerList.add(brushRollerRS.getString("TOTAL_PL_ADJ_TO_SALES"));
				brushRollerList.add(brushRollerRS.getString("LY_INV_SHRINK"));
				brushRollerList.add(brushRollerRS.getString("LY_ADJ_CURRENT_YEAR_OBS"));
				brushRollerList.add(brushRollerRS.getString("LY_TOTAL_PL_ADJ"));
				brushRollerList.add(brushRollerRS.getString("LY_PL_ADJ_PERCENT_TO_SALES"));
				brushRollerList.add(brushRollerRS.getString("TOTAL_PL_ADJ_CHANGE_TY_VS_LY"));
		}
			String assocProdcutsSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b " +
										"where b.ASSOC_PRODUCTS=a.id and b.ID=1001";
				SqlRowSet assocProdcutsRS = jdbcTemplate.queryForRowSet(assocProdcutsSql);
				List<String> assocProdcutsList = new ArrayList<String>();
				while(assocProdcutsRS.next()){
					assocProdcutsList.add(assocProdcutsRS.getString("TY_SALES"));
					assocProdcutsList.add(assocProdcutsRS.getString("LY_SALES"));
					assocProdcutsList.add(assocProdcutsRS.getString("TY_GM"));
					assocProdcutsList.add(assocProdcutsRS.getString("LY_GM"));
					
					assocProdcutsList.add(assocProdcutsRS.getString("NET_BOOK_INV"));
					assocProdcutsList.add(assocProdcutsRS.getString("TINT_USE_ADJ"));
					assocProdcutsList.add(assocProdcutsRS.getString("CLOSING_REPORT"));
					assocProdcutsList.add(assocProdcutsRS.getString("A_CHRGD_TO_STR_NOT_RECVD_INT"));
					assocProdcutsList.add(assocProdcutsRS.getString("B_RECVD_NOT_CHRGD_TO_STR_EXT"));
					assocProdcutsList.add(assocProdcutsRS.getString("C_RECVD_NOT_CHRGD_TO_STR_INT"));
					assocProdcutsList.add(assocProdcutsRS.getString("D_OPEN_IBARS"));
					assocProdcutsList.add(assocProdcutsRS.getString("E_OPEN_CHARGE_BACKS"));
					assocProdcutsList.add(assocProdcutsRS.getString("F_CHRGD_STR_NOT_RECVD_EXT"));
					assocProdcutsList.add(assocProdcutsRS.getString("R_OPEN_DISCREPANCY_RPT_EXT"));
					assocProdcutsList.add(assocProdcutsRS.getString("Z_OPEN_MON_SALES_RPT"));
					assocProdcutsList.add(assocProdcutsRS.getString("ADJUSTED_BOOK_INV"));
					assocProdcutsList.add(assocProdcutsRS.getString("GROSS_PHY_INV"));
					assocProdcutsList.add(assocProdcutsRS.getString("PHY_INV_ADJ"));
					assocProdcutsList.add(assocProdcutsRS.getString("ADJ_GROSS_PHY_INV"));
					assocProdcutsList.add(assocProdcutsRS.getString("TY_INV_SHRINK_3710_A"));
					assocProdcutsList.add(assocProdcutsRS.getString("TY_INV_SHRINK_TO_SLS"));
					assocProdcutsList.add(assocProdcutsRS.getString("CURRENT_YEAR_OBS_ADJ"));
					assocProdcutsList.add(assocProdcutsRS.getString("ADJ_CURRENT_YEAR_OBS"));
					assocProdcutsList.add(assocProdcutsRS.getString("ADJ_CURRENT_YEAR_OBS_PERCENT"));
					assocProdcutsList.add(assocProdcutsRS.getString("TOTAL_PL_ADJ"));
					assocProdcutsList.add(assocProdcutsRS.getString("TOTAL_PL_ADJ_TO_SALES"));
					assocProdcutsList.add(assocProdcutsRS.getString("LY_INV_SHRINK"));
					assocProdcutsList.add(assocProdcutsRS.getString("LY_ADJ_CURRENT_YEAR_OBS"));
					assocProdcutsList.add(assocProdcutsRS.getString("LY_TOTAL_PL_ADJ"));
					assocProdcutsList.add(assocProdcutsRS.getString("LY_PL_ADJ_PERCENT_TO_SALES"));
					assocProdcutsList.add(assocProdcutsRS.getString("TOTAL_PL_ADJ_CHANGE_TY_VS_LY"));
				}
				
				String wallCoveringSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b " +
											"where b.WALL_COVERING=a.id and b.ID=1001";
					SqlRowSet wallCoveringRS = jdbcTemplate.queryForRowSet(wallCoveringSql);
					List<String> wallCoveringList = new ArrayList<String>();
					while(wallCoveringRS.next()){
					wallCoveringList.add(wallCoveringRS.getString("TY_SALES"));
					wallCoveringList.add(wallCoveringRS.getString("LY_SALES"));
					wallCoveringList.add(wallCoveringRS.getString("TY_GM"));
					wallCoveringList.add(wallCoveringRS.getString("LY_GM"));
					
					wallCoveringList.add(wallCoveringRS.getString("NET_BOOK_INV"));
					wallCoveringList.add(wallCoveringRS.getString("TINT_USE_ADJ"));
					wallCoveringList.add(wallCoveringRS.getString("CLOSING_REPORT"));
					wallCoveringList.add(wallCoveringRS.getString("A_CHRGD_TO_STR_NOT_RECVD_INT"));
					wallCoveringList.add(wallCoveringRS.getString("B_RECVD_NOT_CHRGD_TO_STR_EXT"));
					wallCoveringList.add(wallCoveringRS.getString("C_RECVD_NOT_CHRGD_TO_STR_INT"));
					wallCoveringList.add(wallCoveringRS.getString("D_OPEN_IBARS"));
					wallCoveringList.add(wallCoveringRS.getString("E_OPEN_CHARGE_BACKS"));
					wallCoveringList.add(wallCoveringRS.getString("F_CHRGD_STR_NOT_RECVD_EXT"));
					wallCoveringList.add(wallCoveringRS.getString("R_OPEN_DISCREPANCY_RPT_EXT"));
					wallCoveringList.add(wallCoveringRS.getString("Z_OPEN_MON_SALES_RPT"));
					wallCoveringList.add(wallCoveringRS.getString("ADJUSTED_BOOK_INV"));
					wallCoveringList.add(wallCoveringRS.getString("GROSS_PHY_INV"));
					wallCoveringList.add(wallCoveringRS.getString("PHY_INV_ADJ"));
					wallCoveringList.add(wallCoveringRS.getString("ADJ_GROSS_PHY_INV"));
					wallCoveringList.add(wallCoveringRS.getString("TY_INV_SHRINK_3710_A"));
					wallCoveringList.add(wallCoveringRS.getString("TY_INV_SHRINK_TO_SLS"));
					wallCoveringList.add(wallCoveringRS.getString("CURRENT_YEAR_OBS_ADJ"));
					wallCoveringList.add(wallCoveringRS.getString("ADJ_CURRENT_YEAR_OBS"));
					wallCoveringList.add(wallCoveringRS.getString("ADJ_CURRENT_YEAR_OBS_PERCENT"));
					wallCoveringList.add(wallCoveringRS.getString("TOTAL_PL_ADJ"));
					wallCoveringList.add(wallCoveringRS.getString("TOTAL_PL_ADJ_TO_SALES"));
					wallCoveringList.add(wallCoveringRS.getString("LY_INV_SHRINK"));
					wallCoveringList.add(wallCoveringRS.getString("LY_ADJ_CURRENT_YEAR_OBS"));
					wallCoveringList.add(wallCoveringRS.getString("LY_TOTAL_PL_ADJ"));
					wallCoveringList.add(wallCoveringRS.getString("LY_PL_ADJ_PERCENT_TO_SALES"));
					wallCoveringList.add(wallCoveringRS.getString("TOTAL_PL_ADJ_CHANGE_TY_VS_LY"));
				}
					
					String windowTreatSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b " +
					"where b.WINDOW_TRATMENT=a.id and b.ID=1001";
						SqlRowSet windowTreatRS = jdbcTemplate.queryForRowSet(windowTreatSql);
						List<String> windowTreatList = new ArrayList<String>();
						while(windowTreatRS.next()){
							windowTreatList.add(windowTreatRS.getString("TY_SALES"));
							windowTreatList.add(windowTreatRS.getString("LY_SALES"));
							windowTreatList.add(windowTreatRS.getString("TY_GM"));
							windowTreatList.add(windowTreatRS.getString("LY_GM"));
							
							windowTreatList.add(windowTreatRS.getString("NET_BOOK_INV"));
							windowTreatList.add(windowTreatRS.getString("TINT_USE_ADJ"));
							windowTreatList.add(windowTreatRS.getString("CLOSING_REPORT"));
							windowTreatList.add(windowTreatRS.getString("A_CHRGD_TO_STR_NOT_RECVD_INT"));
							windowTreatList.add(windowTreatRS.getString("B_RECVD_NOT_CHRGD_TO_STR_EXT"));
							windowTreatList.add(windowTreatRS.getString("C_RECVD_NOT_CHRGD_TO_STR_INT"));
							windowTreatList.add(windowTreatRS.getString("D_OPEN_IBARS"));
							windowTreatList.add(windowTreatRS.getString("E_OPEN_CHARGE_BACKS"));
							windowTreatList.add(windowTreatRS.getString("F_CHRGD_STR_NOT_RECVD_EXT"));
							windowTreatList.add(windowTreatRS.getString("R_OPEN_DISCREPANCY_RPT_EXT"));
							windowTreatList.add(windowTreatRS.getString("Z_OPEN_MON_SALES_RPT"));
							windowTreatList.add(windowTreatRS.getString("ADJUSTED_BOOK_INV"));
							windowTreatList.add(windowTreatRS.getString("GROSS_PHY_INV"));
							windowTreatList.add(windowTreatRS.getString("PHY_INV_ADJ"));
							windowTreatList.add(windowTreatRS.getString("ADJ_GROSS_PHY_INV"));
							windowTreatList.add(windowTreatRS.getString("TY_INV_SHRINK_3710_A"));
							windowTreatList.add(windowTreatRS.getString("TY_INV_SHRINK_TO_SLS"));
							windowTreatList.add(windowTreatRS.getString("CURRENT_YEAR_OBS_ADJ"));
							windowTreatList.add(windowTreatRS.getString("ADJ_CURRENT_YEAR_OBS"));
							windowTreatList.add(windowTreatRS.getString("ADJ_CURRENT_YEAR_OBS_PERCENT"));
							windowTreatList.add(windowTreatRS.getString("TOTAL_PL_ADJ"));
							windowTreatList.add(windowTreatRS.getString("TOTAL_PL_ADJ_TO_SALES"));
							windowTreatList.add(windowTreatRS.getString("LY_INV_SHRINK"));
							windowTreatList.add(windowTreatRS.getString("LY_ADJ_CURRENT_YEAR_OBS"));
							windowTreatList.add(windowTreatRS.getString("LY_TOTAL_PL_ADJ"));
							windowTreatList.add(windowTreatRS.getString("LY_PL_ADJ_PERCENT_TO_SALES"));
							windowTreatList.add(windowTreatRS.getString("TOTAL_PL_ADJ_CHANGE_TY_VS_LY"));
						}
		String desStr = "TY_SALES ,LY_SALES ,TY_GM ,LY_GM ,NET_BOOK_INV ,TINT_USE_ADJ ,CLOSING_REPORT ,A_CHRGD_TO_STR_NOT_RECVD_INT ,B_RECVD_NOT_CHRGD_TO_STR_EXT ," + 
						"C_RECVD_NOT_CHRGD_TO_STR_INT ,D_OPEN_IBARS ,E_OPEN_CHARGE_BACKS ,F_CHRGD_STR_NOT_RECVD_EXT ,R_OPEN_DISCREPANCY_RPT_EXT ,Z_OPEN_MON_SALES_RPT ,"+ 
						"ADJUSTED_BOOK_INV ,GROSS_PHY_INV ,PHY_INV_ADJ ,ADJ_GROSS_PHY_INV ,TY_INV_SHRINK_3710_A ,TY_INV_SHRINK_TO_SLS ,CURRENT_YEAR_OBS ,CURRENT_YEAR_OBS_ADJ ,"+ 
						"ADJ_CURRENT_YEAR_OBS ,ADJ_CURRENT_YEAR_OBS_PERCENT ,TOTAL_PL_ADJ ,TOTAL_PL_ADJ_TO_SALES ,LY_INV_SHRINK ,LY_ADJ_CURRENT_YEAR_OBS ,LY_TOTAL_PL_ADJ ,"+
						"LY_PL_ADJ_PERCENT_TO_SALES";
		List<String> descList = new ArrayList<String>(Arrays.asList(desStr.split(",")));
		map.put("description", descList);
		map.put("paint", paintList);
		map.put("brush", brushRollerList);
		map.put("assocprod", assocProdcutsList);
		map.put("floor", floorList);
		map.put("spray", sparyList);
		map.put("wall", wallCoveringList);
		map.put("windowtreat", windowTreatList);
		System.out.println("map in report dao is "+map.size());
		System.out.println("descList size "+descList.size()+" paintList "+paintList.size()+" brushRollerList "+brushRollerList.size());
		System.out.println("assocProdcutsList size "+assocProdcutsList.size()+" floorList "+floorList.size()+" sparyList "+sparyList.size());
		System.out.println("wallCoveringList size "+wallCoveringList.size()+" windowTreatList "+windowTreatList.size());
		/*} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return map;
	}

}

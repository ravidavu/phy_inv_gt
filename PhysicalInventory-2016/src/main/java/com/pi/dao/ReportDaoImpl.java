package com.pi.dao;

import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository("reportDao")
public class ReportDaoImpl implements ReportDao {

	ResultSet rst = null;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	ResultSet floorRs = null;
	ResultSet rs = null;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Map<String, List<String>> getReport(int storeId) {
		String floorSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b "
				+ "where b.floor_covering=a.id and b.store_id=1001";
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		System.out.println("insdie report dao " + jdbcTemplate);
		SqlRowSet floorRs = jdbcTemplate.queryForRowSet(floorSql);
		List<String> floorList = new ArrayList<String>();
		List<String> ty_sales_totals = new ArrayList<String>();
		while (floorRs.next()) {
			ty_sales_totals.add(floorRs.getString("TY_SALES"));
			
			floorList.add(formatNumber(floorRs.getString("TY_SALES")));
			floorList.add(formatNumber(floorRs.getString("LY_SALES")));
			floorList.add(formatNumber(floorRs.getString("TY_GM")));
			floorList.add(formatNumber(floorRs.getString("LY_GM")));

			floorList.add(formatNumber(floorRs.getString("NET_BOOK_INV")));
			floorList.add(formatNumber(floorRs.getString("TINT_USE_ADJ")));
			floorList.add(formatNumber(floorRs.getString("CLOSING_REPORT")));
			floorList.add(formatNumber(floorRs
					.getString("A_CHRGD_TO_STR_NOT_RECVD_INT")));
			floorList.add(formatNumber(floorRs
					.getString("B_RECVD_NOT_CHRGD_TO_STR_EXT")));
			floorList.add(formatNumber(floorRs
					.getString("C_RECVD_NOT_CHRGD_TO_STR_INT")));
			floorList.add(formatNumber(floorRs.getString("D_OPEN_IBARS")));
			floorList
					.add(formatNumber(floorRs.getString("E_OPEN_CHARGE_BACKS")));
			floorList.add(formatNumber(floorRs
					.getString("F_CHRGD_STR_NOT_RECVD_EXT")));
			floorList.add(formatNumber(floorRs
					.getString("R_OPEN_DISCREPANCY_RPT_EXT")));
			floorList.add(formatNumber(floorRs
					.getString("Z_OPEN_MON_SALES_RPT")));
			floorList.add(formatNumber(floorRs.getString("ADJUSTED_BOOK_INV")));
			floorList.add(formatNumber(floorRs.getString("GROSS_PHY_INV")));
			floorList.add(formatNumber(floorRs.getString("PHY_INV_ADJ")));
			floorList.add(formatNumber(floorRs.getString("ADJ_GROSS_PHY_INV")));
			floorList.add(formatNumber(floorRs
					.getString("TY_INV_SHRINK_3710_A")));
			floorList.add(formatNumber(floorRs
					.getString("TY_INV_SHRINK_TO_SLS")));
			floorList.add(formatNumber(floorRs
					.getString("CURRENT_YEAR_OBS")));
			floorList.add(formatNumber(floorRs
					.getString("CURRENT_YEAR_OBS_ADJ")));
			floorList.add(formatNumber(floorRs
					.getString("ADJ_CURRENT_YEAR_OBS")));
			floorList.add(formatNumber(floorRs
					.getString("ADJ_CURRENT_YEAR_OBS_PERCENT")));
			floorList.add(formatNumber(floorRs.getString("TOTAL_PL_ADJ")));
			floorList.add(formatNumber(floorRs
					.getString("TOTAL_PL_ADJ_TO_SALES")));
			floorList.add(formatNumber(floorRs.getString("LY_INV_SHRINK")));
			floorList.add(formatNumber(floorRs
					.getString("LY_ADJ_CURRENT_YEAR_OBS")));
			floorList.add(formatNumber(floorRs.getString("LY_TOTAL_PL_ADJ")));
			floorList.add(formatNumber(floorRs
					.getString("LY_PL_ADJ_PERCENT_TO_SALES")));
			floorList.add(formatNumber(floorRs
					.getString("TOTAL_PL_ADJ_CHANGE_TY_VS_LY")));

		}

		System.out.println("===>floorList..." + floorList.size());
		String sparySql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b "
				+ "where b.SPRAY_EQUIPMENT=a.id and b.ID=1001";
		SqlRowSet sparyRS = jdbcTemplate.queryForRowSet(sparySql);
		List<String> sparyList = new ArrayList<String>();
		while (sparyRS.next()) {
			ty_sales_totals.add(sparyRS.getString("TY_SALES"));
			sparyList.add(formatNumber(sparyRS.getString("TY_SALES")));
			sparyList.add(formatNumber(sparyRS.getString("LY_SALES")));
			sparyList.add(formatNumber(sparyRS.getString("TY_GM")));
			sparyList.add(formatNumber(sparyRS.getString("LY_GM")));

			sparyList.add(formatNumber(sparyRS.getString("NET_BOOK_INV")));
			sparyList.add(formatNumber(sparyRS.getString("TINT_USE_ADJ")));
			sparyList.add(formatNumber(sparyRS.getString("CLOSING_REPORT")));
			sparyList.add(formatNumber(sparyRS
					.getString("A_CHRGD_TO_STR_NOT_RECVD_INT")));
			sparyList.add(formatNumber(sparyRS
					.getString("B_RECVD_NOT_CHRGD_TO_STR_EXT")));
			sparyList.add(formatNumber(sparyRS
					.getString("C_RECVD_NOT_CHRGD_TO_STR_INT")));
			sparyList.add(formatNumber(sparyRS.getString("D_OPEN_IBARS")));
			sparyList
					.add(formatNumber(sparyRS.getString("E_OPEN_CHARGE_BACKS")));
			sparyList.add(formatNumber(sparyRS
					.getString("F_CHRGD_STR_NOT_RECVD_EXT")));
			sparyList.add(formatNumber(sparyRS
					.getString("R_OPEN_DISCREPANCY_RPT_EXT")));
			sparyList.add(formatNumber(sparyRS
					.getString("Z_OPEN_MON_SALES_RPT")));
			sparyList.add(formatNumber(sparyRS.getString("ADJUSTED_BOOK_INV")));
			sparyList.add(formatNumber(sparyRS.getString("GROSS_PHY_INV")));
			sparyList.add(formatNumber(sparyRS.getString("PHY_INV_ADJ")));
			sparyList.add(formatNumber(sparyRS.getString("ADJ_GROSS_PHY_INV")));
			sparyList.add(formatNumber(sparyRS
					.getString("TY_INV_SHRINK_3710_A")));
			sparyList.add(formatNumber(sparyRS
					.getString("TY_INV_SHRINK_TO_SLS")));
			sparyList.add(formatNumber(sparyRS
					.getString("CURRENT_YEAR_OBS")));
			sparyList.add(formatNumber(sparyRS
					.getString("CURRENT_YEAR_OBS_ADJ")));
			sparyList.add(formatNumber(sparyRS
					.getString("ADJ_CURRENT_YEAR_OBS")));
			sparyList.add(formatNumber(sparyRS
					.getString("ADJ_CURRENT_YEAR_OBS_PERCENT")));
			sparyList.add(formatNumber(sparyRS.getString("TOTAL_PL_ADJ")));
			sparyList.add(formatNumber(sparyRS
					.getString("TOTAL_PL_ADJ_TO_SALES")));
			sparyList.add(formatNumber(sparyRS.getString("LY_INV_SHRINK")));
			sparyList.add(formatNumber(sparyRS
					.getString("LY_ADJ_CURRENT_YEAR_OBS")));
			sparyList.add(formatNumber(sparyRS.getString("LY_TOTAL_PL_ADJ")));
			sparyList.add(formatNumber(sparyRS
					.getString("LY_PL_ADJ_PERCENT_TO_SALES")));
			sparyList.add(formatNumber(sparyRS
					.getString("TOTAL_PL_ADJ_CHANGE_TY_VS_LY")));
		}

		String paintSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b "
				+ "where b.PAINT=a.id and b.ID=1001";
		SqlRowSet paintRS = jdbcTemplate.queryForRowSet(paintSql);
		List<String> paintList = new ArrayList<String>();
		while (paintRS.next()) {
			ty_sales_totals.add(paintRS.getString("TY_SALES"));
			paintList.add(formatNumber(paintRS.getString("TY_SALES")));
			paintList.add(formatNumber(paintRS.getString("LY_SALES")));
			paintList.add(formatNumber(paintRS.getString("TY_GM")));
			paintList.add(formatNumber(paintRS.getString("LY_GM")));

			paintList.add(formatNumber(paintRS.getString("NET_BOOK_INV")));
			paintList.add(formatNumber(paintRS.getString("TINT_USE_ADJ")));
			paintList.add(formatNumber(paintRS.getString("CLOSING_REPORT")));
			paintList.add(formatNumber(paintRS
					.getString("A_CHRGD_TO_STR_NOT_RECVD_INT")));
			paintList.add(formatNumber(paintRS
					.getString("B_RECVD_NOT_CHRGD_TO_STR_EXT")));
			paintList.add(formatNumber(paintRS
					.getString("C_RECVD_NOT_CHRGD_TO_STR_INT")));
			paintList.add(formatNumber(paintRS.getString("D_OPEN_IBARS")));
			paintList
					.add(formatNumber(paintRS.getString("E_OPEN_CHARGE_BACKS")));
			paintList.add(formatNumber(paintRS
					.getString("F_CHRGD_STR_NOT_RECVD_EXT")));
			paintList.add(formatNumber(paintRS
					.getString("R_OPEN_DISCREPANCY_RPT_EXT")));
			paintList.add(formatNumber(paintRS
					.getString("Z_OPEN_MON_SALES_RPT")));
			paintList.add(formatNumber(paintRS.getString("ADJUSTED_BOOK_INV")));
			paintList.add(formatNumber(paintRS.getString("GROSS_PHY_INV")));
			paintList.add(formatNumber(paintRS.getString("PHY_INV_ADJ")));
			paintList.add(formatNumber(paintRS.getString("ADJ_GROSS_PHY_INV")));
			paintList.add(formatNumber(paintRS
					.getString("TY_INV_SHRINK_3710_A")));
			paintList.add(formatNumber(paintRS
					.getString("TY_INV_SHRINK_TO_SLS")));
			paintList.add(formatNumber(paintRS
					.getString("CURRENT_YEAR_OBS")));
			paintList.add(formatNumber(paintRS
					.getString("CURRENT_YEAR_OBS_ADJ")));
			paintList.add(formatNumber(paintRS
					.getString("ADJ_CURRENT_YEAR_OBS")));
			paintList.add(formatNumber(paintRS
					.getString("ADJ_CURRENT_YEAR_OBS_PERCENT")));
			paintList.add(formatNumber(paintRS.getString("TOTAL_PL_ADJ")));
			paintList.add(formatNumber(paintRS
					.getString("TOTAL_PL_ADJ_TO_SALES")));
			paintList.add(formatNumber(paintRS.getString("LY_INV_SHRINK")));
			paintList.add(formatNumber(paintRS
					.getString("LY_ADJ_CURRENT_YEAR_OBS")));
			paintList.add(formatNumber(paintRS.getString("LY_TOTAL_PL_ADJ")));
			paintList.add(formatNumber(paintRS
					.getString("LY_PL_ADJ_PERCENT_TO_SALES")));
			paintList.add(formatNumber(paintRS
					.getString("TOTAL_PL_ADJ_CHANGE_TY_VS_LY")));
		}

		String brushRollerSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b "
				+ "where b.brushes_rollers=a.id and b.ID=1001";
		SqlRowSet brushRollerRS = jdbcTemplate.queryForRowSet(brushRollerSql);
		List<String> brushRollerList = new ArrayList<String>();
		while (brushRollerRS.next()) {
			ty_sales_totals.add(brushRollerRS.getString("TY_SALES"));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("TY_SALES")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("LY_SALES")));
			brushRollerList.add(formatNumber(brushRollerRS.getString("TY_GM")));
			brushRollerList.add(formatNumber(brushRollerRS.getString("LY_GM")));

			brushRollerList.add(formatNumber(brushRollerRS
					.getString("NET_BOOK_INV")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("TINT_USE_ADJ")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("CLOSING_REPORT")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("A_CHRGD_TO_STR_NOT_RECVD_INT")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("B_RECVD_NOT_CHRGD_TO_STR_EXT")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("C_RECVD_NOT_CHRGD_TO_STR_INT")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("D_OPEN_IBARS")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("E_OPEN_CHARGE_BACKS")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("F_CHRGD_STR_NOT_RECVD_EXT")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("R_OPEN_DISCREPANCY_RPT_EXT")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("Z_OPEN_MON_SALES_RPT")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("ADJUSTED_BOOK_INV")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("GROSS_PHY_INV")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("PHY_INV_ADJ")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("ADJ_GROSS_PHY_INV")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("TY_INV_SHRINK_3710_A")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("TY_INV_SHRINK_TO_SLS")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("CURRENT_YEAR_OBS")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("CURRENT_YEAR_OBS_ADJ")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("ADJ_CURRENT_YEAR_OBS")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("ADJ_CURRENT_YEAR_OBS_PERCENT")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("TOTAL_PL_ADJ")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("TOTAL_PL_ADJ_TO_SALES")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("LY_INV_SHRINK")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("LY_ADJ_CURRENT_YEAR_OBS")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("LY_TOTAL_PL_ADJ")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("LY_PL_ADJ_PERCENT_TO_SALES")));
			brushRollerList.add(formatNumber(brushRollerRS
					.getString("TOTAL_PL_ADJ_CHANGE_TY_VS_LY")));
		}
		String assocProdcutsSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b "
				+ "where b.ASSOC_PRODUCTS=a.id and b.ID=1001";
		SqlRowSet assocProdcutsRS = jdbcTemplate
				.queryForRowSet(assocProdcutsSql);
		List<String> assocProdcutsList = new ArrayList<String>();
		while (assocProdcutsRS.next()) {
			ty_sales_totals.add(assocProdcutsRS.getString("TY_SALES"));
			
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("TY_SALES")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("LY_SALES")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("TY_GM")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("LY_GM")));

			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("NET_BOOK_INV")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("TINT_USE_ADJ")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("CLOSING_REPORT")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("A_CHRGD_TO_STR_NOT_RECVD_INT")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("B_RECVD_NOT_CHRGD_TO_STR_EXT")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("C_RECVD_NOT_CHRGD_TO_STR_INT")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("D_OPEN_IBARS")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("E_OPEN_CHARGE_BACKS")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("F_CHRGD_STR_NOT_RECVD_EXT")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("R_OPEN_DISCREPANCY_RPT_EXT")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("Z_OPEN_MON_SALES_RPT")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("ADJUSTED_BOOK_INV")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("GROSS_PHY_INV")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("PHY_INV_ADJ")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("ADJ_GROSS_PHY_INV")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("TY_INV_SHRINK_3710_A")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("TY_INV_SHRINK_TO_SLS")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("CURRENT_YEAR_OBS")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("CURRENT_YEAR_OBS_ADJ")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("ADJ_CURRENT_YEAR_OBS")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("ADJ_CURRENT_YEAR_OBS_PERCENT")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("TOTAL_PL_ADJ")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("TOTAL_PL_ADJ_TO_SALES")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("LY_INV_SHRINK")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("LY_ADJ_CURRENT_YEAR_OBS")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("LY_TOTAL_PL_ADJ")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("LY_PL_ADJ_PERCENT_TO_SALES")));
			assocProdcutsList.add(formatNumber(assocProdcutsRS
					.getString("TOTAL_PL_ADJ_CHANGE_TY_VS_LY")));
		}

		String wallCoveringSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b "
				+ "where b.WALL_COVERING=a.id and b.ID=1001";
		SqlRowSet wallCoveringRS = jdbcTemplate.queryForRowSet(wallCoveringSql);
		List<String> wallCoveringList = new ArrayList<String>();
		while (wallCoveringRS.next()) {
			ty_sales_totals.add(wallCoveringRS.getString("TY_SALES"));
			
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("TY_SALES")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("LY_SALES")));
			wallCoveringList
					.add(formatNumber(wallCoveringRS.getString("TY_GM")));
			wallCoveringList
					.add(formatNumber(wallCoveringRS.getString("LY_GM")));

			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("NET_BOOK_INV")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("TINT_USE_ADJ")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("CLOSING_REPORT")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("A_CHRGD_TO_STR_NOT_RECVD_INT")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("B_RECVD_NOT_CHRGD_TO_STR_EXT")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("C_RECVD_NOT_CHRGD_TO_STR_INT")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("D_OPEN_IBARS")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("E_OPEN_CHARGE_BACKS")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("F_CHRGD_STR_NOT_RECVD_EXT")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("R_OPEN_DISCREPANCY_RPT_EXT")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("Z_OPEN_MON_SALES_RPT")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("ADJUSTED_BOOK_INV")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("GROSS_PHY_INV")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("PHY_INV_ADJ")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("ADJ_GROSS_PHY_INV")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("TY_INV_SHRINK_3710_A")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("TY_INV_SHRINK_TO_SLS")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("CURRENT_YEAR_OBS")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("CURRENT_YEAR_OBS_ADJ")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("ADJ_CURRENT_YEAR_OBS")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("ADJ_CURRENT_YEAR_OBS_PERCENT")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("TOTAL_PL_ADJ")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("TOTAL_PL_ADJ_TO_SALES")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("LY_INV_SHRINK")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("LY_ADJ_CURRENT_YEAR_OBS")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("LY_TOTAL_PL_ADJ")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("LY_PL_ADJ_PERCENT_TO_SALES")));
			wallCoveringList.add(formatNumber(wallCoveringRS
					.getString("TOTAL_PL_ADJ_CHANGE_TY_VS_LY")));
		}

		String windowTreatSql = "select a.* from phy_inv_sales_totals a,phy_inv_product_desc b "
				+ "where b.WINDOW_TRATMENT=a.id and b.ID=1001";
		SqlRowSet windowTreatRS = jdbcTemplate.queryForRowSet(windowTreatSql);
		List<String> windowTreatList = new ArrayList<String>();
		while (windowTreatRS.next()) {
			ty_sales_totals.add(windowTreatRS.getString("TY_SALES"));
			
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("TY_SALES")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("LY_SALES")));
			windowTreatList.add(formatNumber(windowTreatRS.getString("TY_GM")));
			windowTreatList.add(formatNumber(windowTreatRS.getString("LY_GM")));

			windowTreatList.add(formatNumber(windowTreatRS
					.getString("NET_BOOK_INV")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("TINT_USE_ADJ")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("CLOSING_REPORT")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("A_CHRGD_TO_STR_NOT_RECVD_INT")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("B_RECVD_NOT_CHRGD_TO_STR_EXT")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("C_RECVD_NOT_CHRGD_TO_STR_INT")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("D_OPEN_IBARS")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("E_OPEN_CHARGE_BACKS")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("F_CHRGD_STR_NOT_RECVD_EXT")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("R_OPEN_DISCREPANCY_RPT_EXT")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("Z_OPEN_MON_SALES_RPT")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("ADJUSTED_BOOK_INV")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("GROSS_PHY_INV")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("PHY_INV_ADJ")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("ADJ_GROSS_PHY_INV")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("TY_INV_SHRINK_3710_A")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("TY_INV_SHRINK_TO_SLS")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("CURRENT_YEAR_OBS")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("CURRENT_YEAR_OBS_ADJ")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("ADJ_CURRENT_YEAR_OBS")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("ADJ_CURRENT_YEAR_OBS_PERCENT")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("TOTAL_PL_ADJ")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("TOTAL_PL_ADJ_TO_SALES")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("LY_INV_SHRINK")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("LY_ADJ_CURRENT_YEAR_OBS")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("LY_TOTAL_PL_ADJ")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("LY_PL_ADJ_PERCENT_TO_SALES")));
			windowTreatList.add(formatNumber(windowTreatRS
					.getString("TOTAL_PL_ADJ_CHANGE_TY_VS_LY")));
		}
		double total_ty = setArrayListElement(ty_sales_totals);
		
		String desStr = "TY SALES $ ( 11/09 - 10/10 ),LY SALES $ ( 11/08 - 10/09 ) ,TY GM % ( 11/09 - 10/10 ) ,  LY GM % ( 11/08 - 10/09 ) , NET BOOK INVENTORY ,TINT USE ADJUSTMENT ,( + )  CLOSING REPORTS, \"A\"   CHRGD TO STORE-NOT RECVD( INT ) ,\"B\"   RECVD-NOT CHRGD TO STORE( EXT ) ,"
				+ "\"C\"   RECVD-NOT CHRGD TO STORE( INT ) ,   \"D\"   OPEN IBARS/ISTS/EXPENSED MDSE TRAN , \"E\"   OPEN CHARGE-BACKS(EXT) ,\"F\"   CHRGD TO STORE-NOT RECVD( EXT ) , \"R\"   OPEN DOSCREPANCY REPORTS( EXT ) ,\"Z\"   OPEN MOUTH SALES REPORTS ,"
				+ " =   ADJUSTED BOOK INVENTORY ,GROSS PHYSICAL INVENTORY ( AS COUNTED ) , (+)   PHYSICAL INV ADJUSTMENTS ,=  ADJUSTED GROSS PHYSICAL INVENTORY , =  TY INVENTORY SHRINK/( PICKUP )%( 3710 A ,TY INVENTORY SHRINK/( PICKUP )% TO SLS ,CURRENT YEAR OBSOLESCENCE ,(+) CURRENT YEAR OBS ADJUSTMENTS ,"
				+ " = ADJUSTED CURRENT YEAR OBS ( 3700 ACCT ) , = ADJUSTED CURRENT YEAR OBS % TO SLS ,TOTAL P&L ADJUSTMENT $ , TOTAL P&L ADJUSTMENT % TO SALES,  LY INVENTORY SHRINK/ (PICKUP) , LY ADJUSTED CURRENT YEAR OBS ,"
				+ "LY TOTAL P&L ADJUSTMENT  $,LY P&L ADJUSTMENT % TO SALES,TOTAL P&L ADJUSTMENT $ CHANGE TY VS LY ";

		List<String> descList = new ArrayList<String>(Arrays.asList(desStr
				.split(",")));
		map.put("description", descList);
		map.put("paint", paintList);
		map.put("brush", brushRollerList);
		map.put("assocprod", assocProdcutsList);
		map.put("floor", floorList);
		map.put("spray", sparyList);
		map.put("wall", wallCoveringList);
		map.put("windowtreat", windowTreatList);
		System.out.println("map in report dao is " + map.size());
		System.out.println("descList size " + descList.size() + " paintList "
				+ paintList.size() + " brushRollerList "
				+ brushRollerList.size());
		System.out.println("assocProdcutsList size " + assocProdcutsList.size()
				+ " floorList " + floorList.size() + " sparyList "
				+ sparyList.size());
		System.out.println("wallCoveringList size " + wallCoveringList.size()
				+ " windowTreatList " + windowTreatList.size());
		return map;
	}

	 private Double setArrayListElement(List<String> ty_sales_totals) throws NumberFormatException
	 {   
	     Double amount=(double) 0;    
	     for (int i = 0 ; i < ty_sales_totals.size() ; i++)
	     {
	        amount= amount+Double.valueOf((String) ty_sales_totals.get(i));
	     }
	     return amount;
	 }
	 
	private String formatNumber(String string) {
		String formateedString = " ";
		if (null != string && string.length() > 0) {
			double number = Double.parseDouble(string);
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
}

package com.pi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HttpServletBean;

import com.pi.model.StoreProcess;
import com.pi.service.ReportService;
@RestController
public class ReportController {
	
	@Autowired
	private ReportService rptService;
	@RequestMapping(value = "/listOfRpt", method = RequestMethod.GET, headers = "Accept=application/json")
	public Map<String, List<String>> getAllReport(@RequestParam(value="storeNo") String storeNo) {
		System.out.println("-----****-- store number is  "+storeNo);
		Map<String, List<String>> rptList = rptService.getReports(9953);
		return rptList;
	}
	
	@RequestMapping(value = "/listOfReportForDashboard", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<StoreProcess> getAllReport() {
		System.out.println("ProcessCOntroller...listOfReport..");
		List<StoreProcess> storeList = rptService.getAllReport();
		System.out.println("i am here for the report page...");
		return storeList;
	}
}

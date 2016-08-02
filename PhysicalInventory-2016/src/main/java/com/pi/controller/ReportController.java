package com.pi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pi.model.Report;
import com.pi.service.ReportService;

public class ReportController {
	
	@Autowired
	private ReportService rptService;
	
	@RequestMapping(value = "/listOfRpt", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Object> getAllReport(@RequestBody Report rpt) {
		List<Object> rptList = rptService.getReports(rpt.getStoreId());
		return rptList;

	}
	

}

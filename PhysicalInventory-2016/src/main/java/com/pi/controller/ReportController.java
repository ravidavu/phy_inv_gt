package com.pi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pi.service.ReportService;
import com.pi.service.ReportServiceImpl;
@RestController
public class ReportController {
	
	@Autowired
	private ReportService rptService;
	
	@RequestMapping(value = "/listOfRpt", method = RequestMethod.GET, headers = "Accept=application/json")
	public Map<String, List<String>> getAllReport() {
		System.out.println("inside rpt controller "+rptService);
		Map<String, List<String>> rptList = rptService.getReports(9953);
		System.out.println("result is "+rptList.size());
		return rptList;
	}
}

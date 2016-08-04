package com.pi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;









import org.springframework.web.util.UriComponentsBuilder;

import com.pi.model.Costing;
import com.pi.service.CostingService;
import com.pi.service.ReportService;
import com.pi.service.ReportServiceImpl;

@RestController
public class CostingController {
	@Autowired
	private CostingService costingService;
	/*@Autowired
	private ReportService rptService;*/
	@RequestMapping(value = "/getAllStores", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Costing> getAllStroes() {
		
		List<Costing> storeList = costingService.getAllStores();
		return storeList;

	}
	
	/*@RequestMapping(value = "/listOfRpt", method = RequestMethod.GET, headers = "Accept=application/json")
	public Map<String, List<String>> getAllReport() {
		System.out.println("inside rpt controller");
		Map<String, List<String>> rptList = new ReportServiceImpl().getReports(9953);
		return rptList;
	}*/
	
	
}

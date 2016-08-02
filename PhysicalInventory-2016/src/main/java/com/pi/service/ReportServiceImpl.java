package com.pi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pi.dao.ProcessDao;
import com.pi.dao.ReportDao;
import com.pi.model.Report;

public class ReportServiceImpl implements ReportService{

	@Autowired
	private ReportDao reportDao;
	
	@Override
	public List<Object> getReports(int storeId) {
		System.out.println("reportDao is "+reportDao);
		return reportDao.getReport(storeId);
	}

}

package com.pi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dao.ReportDao;
import com.pi.dao.ReportDaoImpl;
@Service("rptService")
public class ReportServiceImpl implements ReportService{

	@Autowired
	private ReportDao reportDao;

	@Override
	public Map<String, List<String>> getReports(int storeId) {
		// TODO Auto-generated method stub
		return reportDao.getReport(storeId);
	}
	

}

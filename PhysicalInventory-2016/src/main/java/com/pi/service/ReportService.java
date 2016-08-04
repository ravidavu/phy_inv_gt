package com.pi.service;

import java.util.List;
import java.util.Map;

public interface ReportService {
	
	public Map<String,List<String>> getReports(int storeId);

}

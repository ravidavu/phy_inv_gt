package com.pi.dao;

import java.util.List;
import java.util.Map;

public interface ReportDao {
	public Map<String,List<String>> getReport(int storeId);
}

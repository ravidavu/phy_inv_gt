package com.pi.dao;

import java.util.List;
import com.pi.model.Report;

public interface ReportDao {
	public List<Object> getReport(int storeId);
}

package com.pi.service;

import java.util.List;

import com.pi.model.StoreProcess;

public interface ProcessService {
	public void updateStore(StoreProcess process);

	public int storeTaskId(String taskId);

	public List<StoreProcess> getAllStorePo();
	
}

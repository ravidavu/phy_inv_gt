package com.pi.dao;

import java.util.List;

import com.pi.model.StoreProcess;

public interface ProcessDao {
	public void updateStore(StoreProcess process);

	public List<StoreProcess> getAllStorePo();

	public int storeTaskId(String taskId);

	public List<StoreProcess> getAllReport();

}

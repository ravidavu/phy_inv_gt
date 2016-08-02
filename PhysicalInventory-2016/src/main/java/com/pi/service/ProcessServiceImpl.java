package com.pi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dao.ProcessDao;
import com.pi.model.StoreProcess;

@Service("processService")
public class ProcessServiceImpl implements ProcessService {
	@Autowired
	private ProcessDao processDao;

	@Override
	public void updateStore(StoreProcess process) {
		processDao.updateStore(process);

	}

	@Override
	public List<StoreProcess> getAllStorePo() {

		return processDao.getAllStorePo();
	}

	@Override
	public int storeTaskId(String taskId) {
			return processDao.storeTaskId(taskId);
		}
}

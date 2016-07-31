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
		System.out.println("*****updateStore"+processDao);
		processDao.updateStore(process);

	}

	@Override
	public List<StoreProcess> getAllStorePo() {

		return processDao.getAllStorePo();
	}

	@Override
	public int storeTaskId(String taskId) {
		System.out.println("*****storeTaskId"+processDao);
		if(processDao!=null){
		return processDao.storeTaskId(taskId);
		}else{
			return 1;
		}
	}
}

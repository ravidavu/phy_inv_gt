package com.pi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dao.CostingDao;
import com.pi.model.Costing;
@Service("costingService")
public class CostingServiceImpl implements CostingService {
	@Autowired
	private CostingDao costingDao;
	@Override
	public void createStore(Costing store) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Costing> getAllStores() {
		// TODO Auto-generated method stub
		return costingDao.getAllStores();
	}

	

	

}

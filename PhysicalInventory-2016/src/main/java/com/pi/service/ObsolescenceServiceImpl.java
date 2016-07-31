package com.pi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dao.ObsolescenceDao;
import com.pi.model.Obsolescence;
@Service("obsolService")
public class ObsolescenceServiceImpl implements ObsolescenceService {
	@Autowired
	private ObsolescenceDao obsolDao;
	@Override
	public void createObsolence(Obsolescence obs) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Obsolescence> getAllObsolence() {
		// TODO Auto-generated method stub
		return obsolDao.getAllObsolence();
	}

}

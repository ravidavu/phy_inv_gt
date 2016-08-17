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
	public Boolean createObsolence(Obsolescence obs) {
		return obsolDao.createObsolence(obs);
	}

	@Override
	public List<Obsolescence> getAllObsolence() {
		return obsolDao.getAllObsolence();
	}

	@Override
	public void deleteObsolence(Obsolescence obsolence) {
		obsolDao.deleteObsolence(obsolence);
	}

}

package com.pi.service;

import java.util.List;

import com.pi.model.Obsolescence;

public interface ObsolescenceService {
	public Boolean createObsolence(Obsolescence obs);

	public List<Obsolescence> getAllObsolence();

	public void deleteObsolence(Obsolescence obsolence);

}

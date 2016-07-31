package com.pi.service;

import java.util.List;

import com.pi.model.Obsolescence;

public interface ObsolescenceService {
	public void createObsolence(Obsolescence obs);

	public List<Obsolescence> getAllObsolence();

}

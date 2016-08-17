package com.pi.dao;

import java.util.List;

import com.pi.model.Obsolescence;

public interface ObsolescenceDao {
	public Boolean createObsolence(Obsolescence obs);

	public List<Obsolescence> getAllObsolence();

	public void deleteObsolence(Obsolescence obsolence);

}

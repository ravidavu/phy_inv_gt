package com.pi.model;

import java.util.ArrayList;
import java.util.List;

public class StoreProcess {
	private String storeNo;
	private boolean status;
	private List<String> storeNoList = new ArrayList<String>();
	private List<String> uncheckList = new ArrayList<String>();
	private String processDate;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<String> getStoreNoList() {
		return storeNoList;
	}
	public void setStoreNoList(List<String> storeNoList) {
		this.storeNoList = storeNoList;
	}
	public List<String> getUncheckList() {
		return uncheckList;
	}
	public void setUncheckList(List<String> uncheckList) {
		this.uncheckList = uncheckList;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}
	public String getProcessDate() {
		return processDate;
	}
	

}

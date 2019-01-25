package com.citi.singleportal.model;

public class HistoryRequest {
private String soeId;
private String fromDate;
private String toDate;

public String getSoeId() {
	return soeId;
}

public String getFromDate() {
	return fromDate;
}

public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}

public String getToDate() {
	return toDate;
}

public void setToDate(String toDate) {
	this.toDate = toDate;
}

public void setSoeId(String soeId) {
	this.soeId = soeId;
}


}

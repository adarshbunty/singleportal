package com.citi.singleportal.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "SOEID", "error" })
public class LoginDetails {

	@JsonProperty("SOEID")
	private String soeId;
	
	
	@JsonProperty("error")
	private String error;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public LoginDetails() {
	}

	/**
	 * 
	 * @param SOEID
	 * 
	 */
	public LoginDetails(String soeId) {
		super();
		this.soeId = soeId;
	}

	@JsonProperty("SOEID")
	public String getSoeId() {
		return soeId;
	}

	@JsonProperty("SOEID")
	public void setSoeId(String soeId) {
		this.soeId = soeId;
	}

	@JsonProperty("error")
	public String getError() {
		return error;
	}

	@JsonProperty("error")
	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "LoginDetails [soeId=" + soeId + ", error=" + error + "]";
	}

}
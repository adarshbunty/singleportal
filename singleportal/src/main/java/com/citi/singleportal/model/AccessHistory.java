package com.citi.singleportal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accesshistory")
public class AccessHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "access_history_id")
	private Integer accessHistoryId;

	@Column(name = "access_time", nullable = false)
	private Date accessTime;

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "selected_option_id", nullable = false)
	private Option option;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prompt_id", nullable = false)
	private Prompt prompt;

	@Column(name = "soe_id", nullable = false)
	private String userId;

	public AccessHistory() {
		super();
	}

	public Integer getAccessHistoryId() {
		return accessHistoryId;
	}

	public void setAccessHistoryId(Integer accessHistoryId) {
		this.accessHistoryId = accessHistoryId;
	}

	

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public Prompt getPrompt() {
		return prompt;
	}

	public void setPrompt(Prompt prompt) {
		this.prompt = prompt;
	}

	public String getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "AccessHistory [accessHistoryId=" + accessHistoryId + ", accessTime=" + accessTime + ", option=" + option
				+ ", prompt=" + prompt + ", userId=" + userId + "]";
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/*
	 * public String getSoeId() { return soeId; }
	 * 
	 * public void setSoeId(String soeId) { this.soeId = soeId; }
	 * 
	 * @Override public String toString() { return "AccessHistory [accessHistoryId="
	 * + accessHistoryId + ", accessTime=" + accessTime + ", option=" + option +
	 * ", prompt=" + prompt + ", soeId=" + soeId + "]"; }
	 * 
	 */}

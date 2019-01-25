package com.citi.singleportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[option]")
public class Option {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "[option_id]")
	private Integer optionId;

	@Column(name = "option_desc", nullable = false)
	private String optionDesc;

	@Column(name = "option_type", nullable = false)
	private String optionType;

	@Column(name = "is_visible", nullable = false)
	private boolean isVisible;

	@Column(name = "is_active", nullable = false)
	private boolean isActive;

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public String getOptionDesc() {
		return optionDesc;
	}

	public void setOptionDesc(String optionDesc) {
		this.optionDesc = optionDesc;
	}
	
	

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Option [optionId=" + optionId + ", optionDesc=" + optionDesc + ", optionType=" + optionType
				+ ", isVisible=" + isVisible + ", isActive=" + isActive + "]";
	}
}
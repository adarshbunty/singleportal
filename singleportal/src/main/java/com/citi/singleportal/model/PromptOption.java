package com.citi.singleportal.model;

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
@Table(name = "promptoption")
public class PromptOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "promptoption_id")
	private Integer promptOptionId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prompt_id", nullable = false)
	private Prompt prompt;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "option_id", nullable = false)
	private Option option;
	
	@Column(name = "next_prompt_id", nullable = false)
	private Integer nextPromptId;

	public PromptOption() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPromptOptionId() {
		return promptOptionId;
	}

	public void setPromptOptionId(Integer promptOptionId) {
		this.promptOptionId = promptOptionId;
	}

	public Prompt getPrompt() {
		return prompt;
	}

	public void setPrompt(Prompt prompt) {
		this.prompt = prompt;
	}

	public Integer getNextPromptId() {
		return nextPromptId;
	}

	public void setNextPromptId(Integer nextPromptId) {
		this.nextPromptId = nextPromptId;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}
	

	/*@Override
	public String toString() {
		return "PromptOption [promptOptionId=" + promptOptionId + ", option=" + option + ", prompt=" + prompt
				+ ", nextPromptId=" + nextPromptId + "]";
	}*/

}

package com.citi.singleportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prompt")
public class Prompt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prompt_id")
	private Integer promptId;

	@Column(name = "prompt_question", nullable = false)
	private String promptQuestion;

	@Column(name = "prompt_level", nullable = false)
	private Integer promptLevel;

	@Column(name = "is_first_prompt", nullable = false)
	private boolean isFirstPrompt;

	public Prompt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPromptId() {
		return promptId;
	}

	public void setPromptId(Integer promptId) {
		this.promptId = promptId;
	}

	public String getPromptQuestion() {
		return promptQuestion;
	}

	public void setPromptQuestion(String promptQuestion) {
		this.promptQuestion = promptQuestion;
	}

	public Integer getPromptLevel() {
		return promptLevel;
	}

	public void setPromptLevel(Integer promptLevel) {
		this.promptLevel = promptLevel;
	}

	public boolean isFirstPrompt() {
		return isFirstPrompt;
	}

	public void setFirstPrompt(boolean isFirstPrompt) {
		this.isFirstPrompt = isFirstPrompt;
	}

	@Override
	public String toString() {
		return "Prompt [promptId=" + promptId + ", promptQuestion=" + promptQuestion + ", promptLevel=" + promptLevel
				+ ", isFirstPrompt=" + isFirstPrompt + "]";
	}

}

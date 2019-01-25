package com.citi.singleportal.service;

import java.text.ParseException;
import java.util.List;

import com.citi.singleportal.model.AccessHistory;
import com.citi.singleportal.model.Option;
import com.citi.singleportal.model.Prompt;
import com.citi.singleportal.model.PromptOption;

public interface SinglePortalService {
	public List<Prompt> getAllPrompts();

	public List<PromptOption> getPromptOptions();

	public List<Option> getAllOptions();

	public Boolean setAccessHistory(AccessHistory accessHistory);
	public  List<AccessHistory> listHistorybySoeId(String soeId);

	public List<AccessHistory> listHistorybyDate(String soeId, String fromDate, String toDate) throws ParseException;

	public List<String> listSoeIds();
}

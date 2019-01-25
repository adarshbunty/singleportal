package com.citi.singleportal.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.singleportal.dao.SinglePortalDao;
import com.citi.singleportal.model.AccessHistory;
import com.citi.singleportal.model.Option;
import com.citi.singleportal.model.Prompt;
import com.citi.singleportal.model.PromptOption;
import com.citi.singleportal.service.SinglePortalService;

@Service
public class PromptServiceImpl implements SinglePortalService{

	@Autowired
	SinglePortalDao singlePortalDao;
	
	@Override
	public List<Prompt> getAllPrompts() {
		return singlePortalDao.getAllPrompts();
	}

	@Override
	public List<PromptOption> getPromptOptions() {
		return singlePortalDao.getPromptOptions();
	}

	public List<Option> getAllOptions() {
		return singlePortalDao.getAllOptions();
	}

	@Override
	public Boolean setAccessHistory(AccessHistory accessHistory) {
		return singlePortalDao.setAccessHistory(accessHistory);
	}

	@Override
	public List<AccessHistory> listHistorybySoeId(String soeId) {
		// TODO Auto-generated method stub
		return singlePortalDao.listHistorybySoeId(soeId);
	}

	@Override
	public List<AccessHistory> listHistorybyDate(String soeId, String fromDate, String toDate) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
		
		
		 List<AccessHistory> listHistorybyDate=singlePortalDao.getHistoryData(soeId, fromDate, toDate);
		 
	
		return listHistorybyDate;
	}

	@Override
	public List<String> listSoeIds() {
		// TODO Auto-generated method stub
		return singlePortalDao.listSoeIds();
	}

}
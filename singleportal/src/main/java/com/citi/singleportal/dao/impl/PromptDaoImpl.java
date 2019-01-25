package com.citi.singleportal.dao.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.citi.singleportal.dao.SinglePortalDao;
import com.citi.singleportal.model.AccessHistory;
import com.citi.singleportal.model.Option;
import com.citi.singleportal.model.Prompt;
import com.citi.singleportal.model.PromptOption;

@Repository
@Transactional
public class PromptDaoImpl implements SinglePortalDao {
	private static final Logger log = LogManager.getLogger(PromptDaoImpl.class.getName());

	private static final String toDate = null;

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * @Autowired private DataBaseConfig baseConfig;
	 */
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prompt> getAllPrompts() {
		List<Prompt> list = null;
		try {
			list = getSession().createQuery("from Prompt").list();

		} catch (HibernateException e) {
			log.error("Exception occured while getting allprompts from DAO: " + e.getMessage(), e);
		}
		return list;
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<PromptOption> getPromptOptions() { List<PromptOption>
	 * list = null; try { Option option = getSession().get(Option.class,1);
	 * log.info(option); List<Option> lists =
	 * getSession().createQuery("from [Option]").list(); list =
	 * getSession().createQuery("from PromptOption").list();
	 * 
	 * } catch (HibernateException e) { e.printStackTrace(); } return list; }
	 */

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * public List<Option> AllOptions() { List<Option> list = null; try { Option
	 * option = getSession().get(Option.class,1); System.out.println(option);
	 * List<Option> lists = getSession().createCriteria(Option.class).list();
	 * log.info("test:"+lists);
	 * 
	 * //list = getSession().createQuery("from PromptOption").list();
	 * 
	 * } catch (HibernateException e) { e.printStackTrace(); } return list; }
	 */

	@Override
	public Boolean setAccessHistory(AccessHistory accessHistory) {
		try {
			int accessHistoryId = (int) getSession().save(accessHistory);
			if (accessHistoryId > 0) {
				return true;
			} else {
				return false;
			}
		} catch (HibernateException e) {
			log.error("Exception occured while storing accesshistory from DAO : " + e.getMessage(), e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PromptOption> getPromptOptions() {
		List<PromptOption> list = null;
		try {

			list = getSession().createQuery("from PromptOption").list();

		} catch (HibernateException e) {
			log.error("Exception occured while getting allPromptOptions from DAO: " + e.getMessage(), e);
		}
		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Option> getAllOptions() {
		List<Option> list = null;
		try {
			list = getSession().createQuery("from Option").list();
			log.info("Fetched result for all options : {}", list);
		} catch (HibernateException e) {
			log.error("Exception occured while getting result for all options in Prompt DAO: " + e.getMessage(), e);
		}
		return list;
	}
	@Override
	public List<AccessHistory> listHistorybySoeId(String userId) {
		List<AccessHistory> list = null;
		try {
			System.out.println("userId"+userId);
			list = getSession().createQuery("from AccessHistory where userId=:userId").setParameter("userId",userId).list();
			log.info("Fetched result for all AccessHistory : {}", list);
		} catch (HibernateException e) {
			log.error("Exception occured while getting result for all AccessHistory in Prompt DAO: " + e.getMessage(), e);
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<AccessHistory> getHistoryData(String userId,String fromDate,String toDate) {
		
		List<AccessHistory> list = null;
		 Timestamp timestamp = null;
		 Timestamp endtimestamp = null;
				try {
					
					if( fromDate != null  && toDate != null ) {
						
						fromDate= fromDate.replaceAll("[^\\x00-\\x7F]","") ; 
						toDate= toDate.replaceAll("[^\\x00-\\x7F]","") ;
					DateFormat expectedFormat = new SimpleDateFormat("MM/dd/yyyy");
					Date frmDate =expectedFormat.parse(fromDate);
					Date enDate = expectedFormat.parse(toDate);
					fromDate = new SimpleDateFormat("yyyy-MM-dd").format(frmDate);
					toDate = new SimpleDateFormat("yyyy-MM-dd").format(enDate);
					frmDate = updateStartTime(frmDate);
					enDate = updateEndTime(enDate);
					  timestamp = new Timestamp( frmDate.getTime() );
					  endtimestamp = new Timestamp( enDate.getTime() );
					}
			String query = "";
			Query hiQuery = null;
			System.out.println("userId"+userId);
			
			if( !StringUtils.isEmpty( userId ) && !StringUtils.isEmpty(fromDate) && !StringUtils.isEmpty( toDate)) {
				fromDate = fromDate +
						"T00:00:00.000";
				toDate = toDate +"T23:59:59.999";
				System.out.println("userId"+fromDate);
				System.out.println("userId"+toDate);
				query = "from AccessHistory where userId=:userId and accessTime between :fromDate and :toDate";
			
				hiQuery = getSession().createQuery(query).
				setParameter("userId",userId).setParameter("fromDate", timestamp)
				.setParameter("toDate",endtimestamp);
			}else if( !StringUtils.isEmpty( userId ) && StringUtils.isEmpty(fromDate) && StringUtils.isEmpty( toDate) ){
				query = "from AccessHistory where userId=:userId";
				hiQuery = getSession().createQuery(query).
						setParameter("userId",userId);
			}else if( StringUtils.isEmpty( userId ) && !StringUtils.isEmpty(fromDate) && !StringUtils.isEmpty( toDate) ){
			
				query = "from AccessHistory where  accessTime between :fromDate and :toDate";
				
				hiQuery = getSession().createQuery(query)
						.setParameter("fromDate", timestamp)
						.setParameter("toDate",endtimestamp);
			}
			
			list = hiQuery.list();
					
			log.info("Fetched result for all AccessHistory : {}", list);
		} catch (HibernateException e) {
			log.error("Exception occured while getting result for all AccessHistory in Prompt DAO: " + e.getMessage(), e);
		} catch (Exception e) {
			log.error("Exception occured while parsing dates for all AccessHistory in Prompt DAO: " + e.getMessage(), e);
		}
		return list;
	}

private Date updateStartTime(Date date) { 	final Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	return cal.getTime();
	}
private Date updateEndTime(Date date) { 	final Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.set(Calendar.HOUR_OF_DAY, 23);
	cal.set(Calendar.MINUTE, 59);
	cal.set(Calendar.SECOND, 59);
	cal.set(Calendar.MILLISECOND, 999);
	return cal.getTime();}

@Override
public List<String> listSoeIds() {
	 List<String> list = null;
	try {
		list = getSession().createQuery("select distinct userId from AccessHistory").list();
		log.info("Fetched result for all AccessHistory : {}", list);
	} catch (HibernateException e) {
		log.error("Exception occured while getting result for all AccessHistory in Prompt DAO: " + e.getMessage(), e);
	}
	return list;
}
}


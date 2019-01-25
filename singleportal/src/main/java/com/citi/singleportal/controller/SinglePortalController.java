package com.citi.singleportal.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citi.singleportal.model.AccessHistory;
import com.citi.singleportal.model.HistoryRequest;
import com.citi.singleportal.model.UserRequest;
import com.citi.singleportal.service.SinglePortalService;

//import https.testingtools_citicorpna_citigroup_net.wsi.atsgsecurity._1.ArrayOfString;
//import https.testingtools_citicorpna_citigroup_net.wsi.atsgsecurity._1.AuthenticationService;
@RestController
@CrossOrigin()
@RequestMapping(value = "singleportal") 
public class SinglePortalController {
	private static final Logger log = LogManager.getLogger(SinglePortalController.class.getName());
	@Autowired
	SinglePortalService singlePortalService;

	//@RequestMapping(value = "/promptOptions", method = RequestMethod.GET)
	@GetMapping("/promptOptions")
	public ResponseEntity<Object> getPromptOptions() {
		try {
			log.info("Fetching Prompt Options..");
			return new ResponseEntity<>(singlePortalService.getPromptOptions(), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error occured while getting prompt options : " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getHistory", method = RequestMethod.POST)
	public ResponseEntity<Object> getHistory(@RequestBody HistoryRequest req) {
		try {
			log.info("Fetching accessHistory Options..");
			return new ResponseEntity<>(singlePortalService.listHistorybySoeId(req.getSoeId()), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error occured while getting accessHistory  : " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value = "/getHistoryData", method = RequestMethod.POST)
	public ResponseEntity<Object> getHistoryData(@RequestBody HistoryRequest req) {
		try {
			log.info("Fetching accessHistory Options..");
			return new ResponseEntity<>(singlePortalService.listHistorybyDate(req.getSoeId(),req.getFromDate(),req.getToDate()), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error occured while getting accessHistory  : " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/authenticate", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public boolean authenticateUser(@RequestBody UserRequest userRequest) {
 		boolean isUserCredentialsValid = false;
 		/**
 		 * 
 		 */
		if (validateUserRequest(userRequest)) {
			isUserCredentialsValid = new AuthenticationService().getAuthenticationServiceSoap().
					isValidUserCredentials(
					userRequest.getDomain(), userRequest.getUserId(), userRequest.getPassword());
			log.info("Authentication Validation for user: {} is success: {} ", userRequest.getUserId(),
					isUserCredentialsValid);
		}

		return isUserCredentialsValid;
	}
	@RequestMapping(value = "/activedomains",  produces = "application/json", method = RequestMethod.GET)
	public ArrayOfString getActiveDomains(){
		ArrayOfString domains=new AuthenticationService().getAuthenticationServiceSoap().getActiveDirectoryDomainCollection();
		
		return domains;
	}

	private boolean validateUserRequest(UserRequest userRequest) {

		if (userRequest == null) {
			log.warn("Authentication Failed. User request is null");
			return false;
		}

		if (userRequest.getDomain() == null || userRequest.getDomain().trim().isEmpty()) {
			log.warn("Authentication Failed. Domain is null or empty");
			return false;
		}

		if (userRequest.getUserId() == null || userRequest.getUserId().trim().isEmpty()) {
			log.warn("Authentication Failed. User Id is null or empty");
			return false;
		}

		if (userRequest.getPassword() == null || userRequest.getPassword().trim().isEmpty()) {
			log.warn("Authentication Failed. User password is null or empty for user: {}", userRequest.getUserId());
			return false;
		}

		return true;
	}

	@RequestMapping(value = "/prompts", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllPrompts() {
		try {
			return new ResponseEntity<>(singlePortalService.getAllPrompts(), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error occured while getting all prompts: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/options", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllOptions() {
		try {
			return new ResponseEntity<>(singlePortalService.getAllOptions(), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception occured while getting all options: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/accessHistory", method = RequestMethod.POST)
	public ResponseEntity<Object> setAccessHistory(@RequestBody AccessHistory accessHistory) {
		try {
			return new ResponseEntity<>(singlePortalService.setAccessHistory(accessHistory), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception occured while storing history: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@RequestMapping(value = "/soeIds", method = RequestMethod.POST)
	public ResponseEntity<Object> getSoeids() {
		try {
			log.info("Fetching accessHistory Options..");
			return new ResponseEntity<>(singlePortalService.listSoeIds(), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error occured while getting accessHistory  : " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

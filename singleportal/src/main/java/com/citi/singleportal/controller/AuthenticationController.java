/*package com.citi.singleportal.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin
@RequestMapping(value = "login") 
public class AuthenticationController {

	
	@RequestMapping//(value = "/login", method = RequestMethod.GET)
	public String  test(Authentication auth) {
		System.out.println(String.format("You are logged in as: %s", auth.getPrincipal()));
		return String.format("You are logged in as: %s", auth.getPrincipal());
	}
}
*/
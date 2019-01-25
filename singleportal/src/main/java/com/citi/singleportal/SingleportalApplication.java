package com.citi.singleportal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }) 
public class SingleportalApplication extends SpringBootServletInitializer {
	private static final Logger log = LogManager.getLogger(SingleportalApplication.class.getName());

	public static void main(String[] args) {
		try {
			log.info("Starting main for single portal application..");
			SpringApplication.run(SingleportalApplication.class, args);
		} catch (Exception e) {
			log.error("Exception occured while starting singleportal application" + e.getMessage(), e);
		}
	}
}


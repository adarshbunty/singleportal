/*package com.citi.singleportal.db;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableConfigurationProperties
public class DataBaseConfig {
	private final ApplicationContext context;
	@Value("${entitymanager.packagesToScan}")
	private String entityManagerPackagesToScan;

	public DataBaseConfig(ApplicationContext context) {
		this.context = context;
	}

	@Bean(name = "dataSource")
	@Primary
	public DataSource dataSource() {
		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		DataSource dataSource = dsLookup.getDataSource("java:comp/env/jdbc/spDS");
		return dataSource;
	}

	@Bean
	@Primary
	public JdbcTemplate wissJdbcTemplate(DataSource wissDataSource) {
		return new JdbcTemplate(wissDataSource);
	}
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		try {
			sessionFactoryBean.setDataSource(dataSource());
			sessionFactoryBean.setPackagesToScan(entityManagerPackagesToScan);
		}
		catch(Exception e){
				}
		return sessionFactoryBean;
	}

	  @Bean
	  public HibernateTransactionManager transactionManager() {
	    HibernateTransactionManager transactionManager = 
	        new HibernateTransactionManager();
	    transactionManager.setSessionFactory(sessionFactory().getObject());
	    return transactionManager; 
	 }
	  @Bean
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	        LocalContainerEntityManagerFactoryBean em = new          LocalContainerEntityManagerFactoryBean();
	        em.setDataSource(dataSource());
	        em.setPackagesToScan(new String[] { "com.citi.singleportal.model" });     
	        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	        em.setJpaVendorAdapter(vendorAdapter);
	        return em;
	     }
}
*/

package com.citi.singleportal.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@PropertySource({"classpath:dbconfig.properties"})
@PropertySource({ "classpath:application.properties" })

public class DataBaseConfig {

	static Logger log = LogManager.getLogger(DataBaseConfig.class.getName());

	@Value("${db.driver}")
	private String dbDriver;

	@Value("${db.password}")
	private String dbPassword;

	@Value("${db.url}")
	private String dbUrl;

	@Value("${db.username}")
	private String dbUserName;

	@Value("${hibernate.dialect}")
	private String hibernateDialect;

	@Value("${hibernate.show_sql}")
	private boolean hibernateShowSql;

	@Value("${hibernate.hbm2ddl.auto}")
	private String hibernateHbm2ddlAuto;

	@Value("${entitymanager.packagesToScan}")
	private String entityManagerPackagesToScan;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		try {
			dataSource.setDriverClassName(dbDriver);
			dataSource.setUrl(dbUrl);
			dataSource.setUsername(dbUserName);
			dataSource.setPassword(dbPassword);
		} catch (Exception e) {
			log.error(e);
		}

		return dataSource;
	}

	/*
	 * @Bean public DataSource dataSource() { DriverManagerDataSource dataSource =
	 * new DriverManagerDataSource(); try {
	 * dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
	 * ; dataSource.setUrl("jdbc:sqlserver://sd-db2a-b2db.GATS:2431/SinglePortal");
	 * dataSource.setUsername("GATSFUNCID_DEV"); dataSource.setPassword("yAdGa28h");
	 * 
	 * } catch(Exception e){ log.error(e); }
	 * 
	 * return dataSource; }
	 */
	
	/*@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		try {
			sessionFactoryBean.setDataSource(dataSource());
			sessionFactoryBean.setPackagesToScan(entityManagerPackagesToScan);
		} catch (Exception e) {
		}
		return sessionFactoryBean;
	}*/
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		try {
			sessionFactoryBean.setDataSource(dataSource());
			sessionFactoryBean.setPackagesToScan(entityManagerPackagesToScan);
			Properties hibernateProperties = new Properties();
			hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
			hibernateProperties.put("hibernate.show_sql", hibernateShowSql);
			hibernateProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
			sessionFactoryBean.setHibernateProperties(hibernateProperties);
		}
		catch(Exception e){
			log.error(e);
				}
		return sessionFactoryBean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.citi.singleportal.model" });
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		return em;
	}
}

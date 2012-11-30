package org.metabuild.grobot.server.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile("default")
@ComponentScan(basePackages = { "org.metabuild.grobot.server.service" })
@EnableTransactionManagement
public class HibernateConfig {

	@Bean(name="sessionFactory")
	public LocalSessionFactoryBean getSessionFactory(DataSource dataSource, Properties hibernateProperties) {
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setHibernateProperties(hibernateProperties);
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("org.metabuild.grobot.domain");
		return sessionFactory;
	}

	@Autowired
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(
			@Qualifier(value="dataSource") DataSource dataSource, 
			@Qualifier(value="hibernateProperties") Properties jpaProperties) {
		final LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setPackagesToScan("org.metabuild.grobot.common.domain");
		entityManagerFactory.setJpaProperties(jpaProperties);
		return entityManagerFactory;
	}
	
	@Autowired(required=true)
	@Bean(name="transactionManager")
	public JpaTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		return new JpaTransactionManager();
	}
}

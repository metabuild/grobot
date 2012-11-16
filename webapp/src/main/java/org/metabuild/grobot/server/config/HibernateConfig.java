package org.metabuild.grobot.server.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile("default")
@ComponentScan(basePackages = { "org.metabuild.grobot.server.dao" })
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

	@Autowired(required=true)
	@Bean(name="transactionManager")
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}
}

package org.metabuild.grobot.server.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.init.JacksonRepositoryPopulatorFactoryBean;
import org.springframework.data.repository.init.RepositoryPopulator;
import org.springframework.data.repository.init.ResourceReader;
import org.springframework.data.repository.init.ResourceReaderRepositoryPopulator;
import org.springframework.data.repository.support.Repositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile("default")
@ComponentScan(basePackages = { "org.metabuild.grobot.server.service" })
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="org.metabuild.grobot.server.repository")
public class JpaConfig {

//	@Bean(name="repositoryPopulator")
//	public RepositoryPopulator getRespositoryPopulator() throws Exception {
//		final JacksonRepositoryPopulatorFactoryBean factory =  new JacksonRepositoryPopulatorFactoryBean();
//		factory.getObject().setResourceLocation("classpath:test-data.json");
//		factory.afterPropertiesSet();
//		return factory.getObject();
//	}

	@Bean(name="entityManagerFactory")
	public EntityManagerFactory getEntityManagerFactory(
			@Qualifier(value="dataSource") DataSource dataSource, 
			@Qualifier(value="hibernateProperties") Properties jpaProperties) {
		
		final HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setGenerateDdl(false); // let hibernate create the database
		
		final LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
		entityManagerFactory.setPackagesToScan("org.metabuild.grobot.common.domain");
		entityManagerFactory.setJpaProperties(jpaProperties);
		entityManagerFactory.afterPropertiesSet();
		
		return entityManagerFactory.getObject();
	}
	
	@Bean(name="transactionManager")
	public PlatformTransactionManager getTransactionManager(EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}
	
	@Bean(name="persistenceExceptionTranslator")
	public HibernateExceptionTranslator getPersistExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}
}

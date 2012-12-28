/*
 * Copyright 2012 Metabuild Software, LLC. (http://www.metabuild.org)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.metabuild.grobot.server.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.metabuild.grobot.common.domain.FakeTargetHostCacheImpl;
import org.metabuild.grobot.common.domain.TargetHostCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Java annotated Spring configuration for unit tests
 * 
 * @author jburbridge
 * @since 11/23/2012
 */
@Configuration
@Profile("test")
@ComponentScan(basePackages = { "org.metabuild.grobot.common.domain"})
@PropertySource("classpath:grobot-server.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="org.metabuild.grobot.server.repository")
public class TestAppConfig {

	@Bean(name="targetCache")
	public TargetHostCache getTargetCache() {
		return new FakeTargetHostCacheImpl(15);
	}
	
	@Bean(name="hibernateProperties")
	public Properties getHibernateProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		hibernateProperties.put("hibernate.max_fetch_depth", 3);
		hibernateProperties.put("hibernate.jdbc.fetch_size", 50);
		hibernateProperties.put("hibernate.jdbc.batch_size", 10);
		hibernateProperties.put("hibernate.show_sql", true);
		return hibernateProperties;
	}
	
	@Bean(name="dataSource")
	public DataSource getDataSource() {
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.addScript("classpath:schema.sql")
			.addScript("classpath:test-data.sql")
			.setName("grobot")
			.build();
	}
	

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

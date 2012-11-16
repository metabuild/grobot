package org.metabuild.grobot.server.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.metabuild.grobot.domain.TargetHostCache;
import org.metabuild.grobot.domain.TargetHostCacheImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Production specific spring configuration
 * 
 * @author jburbridge
 * @since 11/11/2012
 */
@Configuration
@Profile("production")
public class ProductionAppConfig {

	
	@Bean(name="targetCache")
	public TargetHostCache getTargetCache() {
		return new TargetHostCacheImpl();
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
			.build();
	}
}

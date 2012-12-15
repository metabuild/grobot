package org.metabuild.grobot.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;

/**
 * Spring Web JavaConfig style configuration
 * 
 * Typically this would be the configuration class for the webapp only, but because the webapp also happens to be
 * the entry point for the grobot server, all other configuration elements (embedded activemq broker, jms elements, 
 * spring schedulers and jpa/hibernate configurations are imported from here.
 * 
 * @author jburbridge
 * @since 9/30/2012
 */
@Configuration
@EnableWebMvc
@ImportResource(value="classpath:activeMqBrokerConfig.xml")
@Import({ 
	DefaultAppConfig.class, 
	ServerJmsConfig.class
})
@ComponentScan(basePackages = { "org.metabuild.grobot.webapp.controllers" })
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * Add the "resources" directory at the root of the webapp to the registry. This is where we add
	 * our static resources - images, js, css, etc.
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/**
	 * We need to enable the default servlet handler since the dispatcher servlet is mapped to receive 
	 * all requests... otherwise we wouldn't be able to serve static content from the /resources folder
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean(name="tilesViewResolver")
	public ViewResolver getUrlBasedViewResolver() {
		final UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setViewClass(org.springframework.web.servlet.view.tiles2.TilesView.class);
		return resolver;
	}
	
	@Bean(name="tilesConfigurer")
	public TilesConfigurer getTilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		final String[] definitions = { "/WEB-INF/layouts/layouts.xml", "/WEB-INF/views/**/views.xml" };
		configurer.setDefinitions(definitions);
		configurer.setValidateDefinitions(true);
		return configurer;
	}
}
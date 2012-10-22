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
 * Spring WebMVC configuration
 * 
 * @author jburbridge
 * @since 9/30/2012
 */
@Configuration
@EnableWebMvc
@ImportResource(value="classpath:activeMqBrokerConfig.xml")
@Import({AppConfig.class, ServerJmsConfig.class})
@ComponentScan(basePackages = "org.metabuild.grobot.webapp.controllers")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean(name="tilesViewResolver")
	public ViewResolver getUrlBasedViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setViewClass(org.springframework.web.servlet.view.tiles2.TilesView.class);
		return resolver;
	}
	
	@Bean(name="tilesConfigurer")
	public TilesConfigurer getTilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		String[] definitions = { "/WEB-INF/layouts/layouts.xml", "/WEB-INF/views/**/views.xml" };
		configurer.setDefinitions(definitions);
		configurer.setValidateDefinitions(true);
		return configurer;
	}
}
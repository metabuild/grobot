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

import static org.springframework.security.config.annotation.authentication.AuthenticationSecurityBuilders.*;
import static org.springframework.security.config.annotation.web.util.RequestMatchers.*;
import static org.springframework.security.config.annotation.web.WebSecurityConfigurators.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.provisioning.InMemoryUserDetailsManagerSecurityBuilder;
import org.springframework.security.config.annotation.web.DefaultSecurityFilterConfigurator;
import org.springframework.security.config.annotation.web.EnableWebSecurity;
import org.springframework.security.config.annotation.web.ExpressionUrlAuthorizationBuilder;
import org.springframework.security.config.annotation.web.FilterChainProxySecurityBuilder;
import org.springframework.security.config.annotation.web.FormLoginSecurityFilterConfigurator;
import org.springframework.security.config.annotation.web.SecurityFilterChainSecurityBuilder;

/**
 * Spring Security configuration
 * 
 * @author jburbridge
 * @since 3/10/2013
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationMgr() throws Exception {
        return authenticationManager(userDetailsManager()).build();
    }

    @Bean
    public InMemoryUserDetailsManagerSecurityBuilder userDetailsManager() throws Exception {
        return inMemoryAuthentication(
          user("user").password("password").roles("USER"),
          user("admin").password("password").roles("USER", "ADMIN"));
    }

    @Bean
    public FilterChainProxySecurityBuilder builder() throws Exception {
        ExpressionUrlAuthorizationBuilder fiSourceBldr = interceptUrls()
            .antMatchers("/users**","/sessions/**").hasRole("ADMIN")
            .antMatchers("/ping","/resources/**","/signup").permitAll()
            .antMatchers("/**").hasRole("USER");

        return new FilterChainProxySecurityBuilder()
            .ignoring(antMatchers("/resources/**"))
            .securityFilterChains(
                new SecurityFilterChainSecurityBuilder(authenticationMgr())
                    .apply(new DefaultSecurityFilterConfigurator(fiSourceBldr).permitAll())
                    .apply(new FormLoginSecurityFilterConfigurator().permitAll()));
    }
}

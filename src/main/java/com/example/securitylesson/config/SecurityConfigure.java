package com.example.securitylesson.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().
                antMatchers("/security/test")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/security/login")
                .authenticated()
                .and()
                .authorizeRequests()
                .antMatchers("/security/users").hasAnyRole("USER","ADMIN")
                .antMatchers("/security/admin").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/security/post").hasAnyRole("ADMIN");
        super.configure(http);
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
        builder.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER").and()
                .withUser("admin").password("{noop} admin").roles("ADMIN");
    }
}

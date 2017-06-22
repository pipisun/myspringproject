package edu.mum.coffee.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomSuccessHandler csHandler;
	
	@Autowired
	DataSource dataSource;
	
    @Bean
    public CustomFilter customFilter()
            throws Exception {
    	CustomFilter customFilter = new CustomFilter();
    	customFilter
                .setAuthenticationManager(authenticationManagerBean());
        return customFilter;
    } 
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/user/**").access("hasRole('USER')").antMatchers("/admin/**")
		.access("hasRole('ADMIN')").antMatchers("/", "/home", "/index").permitAll().anyRequest().authenticated()
		.and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
		.successHandler(csHandler).failureUrl("/login?error=true")
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/login?logout"))
		.logoutSuccessUrl("/login").permitAll().and().addFilterAfter(new CustomFilter(), UsernamePasswordAuthenticationFilter.class);
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("super").password("pw").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("f.l@abc.com").password("123").roles("USER");
		auth.jdbcAuthentication().dataSource(dataSource)
		  .usersByUsernameQuery(
		   "select email,password, enabled, role from users where email=?")
		  .authoritiesByUsernameQuery(
		   "select email, role from users where email=?");
		
	}
	
}
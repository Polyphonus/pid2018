/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import security.CustomUserDetailsService;

/**
 *
 * @author francoise
 */
@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
 @Override
    protected void configure(HttpSecurity http) throws Exception {
        //uniquement pour test junit à décommenter 
        http.csrf().disable();
        /*
        HttpSecurity It allows configuring web based security for specific http requests.
        By default it will be applied to all requests, 
        but can be restricted using requestMatcher(RequestMatcher) or other similar methods. 
        */
        http
      .authorizeRequests()
                .antMatchers("/user/**").hasRole("USER")	
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/**","/images/**","/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
            
                
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .permitAll();
    }

   /* @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("password").roles("ADMIN");
    }  */
@Autowired 
 private UserDetailsService userDetailsService;
 
/*
UserDetailsService interface is used in order to lookup the username, 
password and GrantedAuthorities for any given user. 
This interface provide only one method which implementing class need to implement.
UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
AuthenticationProvider authenticates the user simply by
comparing the password submitted in a UsernamePasswordAuthenticationToken against the one loaded by
the UserDetailsService


SecurityBuilder used to create an AuthenticationManager. 
Allows for easily building in memory authentication, LDAP authentication, 
JDBC based authentication, adding UserDetailsService, and adding AuthenticationProvider's.
*/
 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {    
	 auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
 } 
 /*
 Indicates that a method produces a bean to be managed by the Spring container. 
 
 */
   @Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
     return new BCryptPasswordEncoder();
    }
}

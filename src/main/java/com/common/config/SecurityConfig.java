package com.common.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT USERNAME, PASSWORD, ENABLED FROM CONTACT_PERSON WHERE USERNAME=?")
                .authoritiesByUsernameQuery("SELECT U.ID_ROLE, A.AUTHORITY\n" +
                        "        \t FROM AUTHORITIES A, CONTACT_PERSON U WHERE U.ID_ROLE = A.ID_ROLE AND U.USERNAME = ?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
               // .antMatchers("/index/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/index/**","/index/","/emit*").access("hasRole('ROLE_SUPERUSER')")
        //.antMatchers("/index/**").access("hasRole('ADMIN')")
            //    .antMatchers("/emit/**").access("hasRole('ROLE_SUPERUSER')")
//              .anyRequest().authenticated() //all requests will checked
                .and().formLogin().defaultSuccessUrl("/index", false)
                .and()
                .formLogin().loginPage("/log").permitAll().usernameParameter("j_username")
                .passwordParameter("j_password").loginProcessingUrl("/j_spring_security_check").failureUrl("/log")
                .and()
                .httpBasic()
                .and().
                authorizeRequests()
                // .antMatchers("/index/**").access("hasRole('ROLE_ADMIN')")
                .and()
//                .authorizeRequests().antMatchers("/user*//**").hasRole("ADMIN")
//                .antMatchers("/user*//**").hasRole("USER")
//                .and()

           //     .authorizeRequests().antMatchers("/index/**").hasRole("SUPERUSER")
              //  .and()
              //  .authorizeRequests().antMatchers("/emit/**").hasRole("SUPERUSER").antMatchers("/index/**").hasRole("SUPERUSER").antMatchers("/index/**").hasRole("ADMIN")
               // .and().authorizeRequests().antMatchers("/index/**").hasRole("ADMIN")
                //.and()
                .logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/log")
                .and()
                .rememberMe().key("myKey").tokenValiditySeconds(300)
                .and()
                .csrf().disable().authorizeRequests();


        http.authorizeRequests().antMatchers("/index/**").hasAnyRole("ADMIN","SUPERUSER")/*.hasRole("ADMIN")*/.and()
                .logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/log")
                .and()
                .rememberMe().key("myKey").tokenValiditySeconds(300)
                .and()
                .csrf().disable().authorizeRequests();


    }
}

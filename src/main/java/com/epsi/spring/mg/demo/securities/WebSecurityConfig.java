package com.epsi.spring.mg.demo.securities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // https://blog.ouidou.fr/spring-security-pour-les-nuls-1930fce60089

        http.authorizeRequests()
//                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
//                .antMatchers("/account/**").hasAnyAuthority("USER")
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/*", "/static/**").permitAll()
                .anyRequest().authenticated()
            .and().formLogin().permitAll()
            .and().logout().logoutSuccessUrl("/").permitAll()
            .and().exceptionHandling().accessDeniedPage("/access-denied");

        // H2-console disable feature security for test
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}

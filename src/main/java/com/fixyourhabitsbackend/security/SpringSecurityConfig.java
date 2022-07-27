package com.fixyourhabitsbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/userprofiles/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/userprofiles/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/userprofiles/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/userprofiles/**").hasRole("USER")

                .antMatchers(HttpMethod.GET, "/userhabits/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/userhabits/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/userhabits/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/userhabits/**").hasRole("USER")

                .antMatchers(HttpMethod.GET, "/adminprofiles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/adminprofiles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/adminprofiles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/adminprofiles/**").hasRole("ADMIN")

                .antMatchers(HttpMethod.PUT, "/adminrewards/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/adminrewards/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/adminrewards/**").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/userrewards/**").hasRole("USER")

                .antMatchers("/authenticated").authenticated()
                .antMatchers("/authenticate/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
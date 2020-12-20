package com.a1tSign.techBoom.configuration;

import com.a1tSign.techBoom.data.entity.Role;
import com.a1tSign.techBoom.data.repository.RoleRepository;
import com.a1tSign.techBoom.security.JwtFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    //TODO: Don't forget to update security mappings for my backend
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/branches/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/v1/items/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/v1/search/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .antMatchers("/api/v1/statistic/**").hasRole("ADMIN")
                .antMatchers("api/v1/current/**").authenticated()
                .antMatchers("/api/v1/security/*").anonymous()
                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

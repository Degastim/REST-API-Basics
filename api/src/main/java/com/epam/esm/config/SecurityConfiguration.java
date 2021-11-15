package com.epam.esm.config;

import com.epam.esm.entity.user.Permission;
import com.epam.esm.filter.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfiguration(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/register").permitAll()
                .antMatchers("/auth/logout").authenticated()
                .antMatchers("/auth/login").not().authenticated()
                .antMatchers(HttpMethod.GET, "/certificates/*").permitAll()
                .antMatchers(HttpMethod.POST, "/certificates/*").hasAuthority(Permission.CERTIFICATES_CREATE.getPermission())
                .antMatchers(HttpMethod.PATCH, "/certificates/*").hasAuthority(Permission.CERTIFICATES_UPDATE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/certificates/*").hasAuthority(Permission.CERTIFICATES_DELETE.getPermission())
                .antMatchers(HttpMethod.GET, "/tags/*").hasAuthority(Permission.TAGS_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/tags/*").hasAuthority(Permission.TAGS_CREATE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/tags/*").hasAuthority(Permission.TAGS_DELETE.getPermission())
               .antMatchers(HttpMethod.GET, "/users").hasAuthority(Permission.USERS_READ.getPermission())
                .antMatchers(HttpMethod.GET, "/users/{id}").hasAuthority(Permission.USERS_READ.getPermission())
                .antMatchers(HttpMethod.GET, "/users/{id}/orders").hasAuthority(Permission.ORDERS_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/users/{id}/orders").hasAuthority(Permission.ORDERS_CREATE.getPermission())
                .antMatchers(HttpMethod.GET, "/orders/*").hasAuthority(Permission.ORDERS_READ.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

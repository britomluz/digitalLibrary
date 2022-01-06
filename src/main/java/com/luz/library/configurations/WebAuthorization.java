package com.luz.library.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers(HttpMethod.GET, "/api/users").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users", "/rest/users").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/users", "/api/users").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/books").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/books").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/books").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/books").hasAnyAuthority("ADMIN")
                .antMatchers( "/index.html", "/login.html", "/api/login.html", "/web/booking.html", "/web/checkout.html", "/scripts/**", "/assets/**","/styles/**").permitAll()
                .antMatchers("/api/**","/web/myaccount.html", "/web/order-details.html").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/web/admin.html").hasAuthority("ADMIN")
                .antMatchers("/rest/**", "/web/admin.html").hasAuthority("ADMIN")
                .antMatchers( "/api/users", "/rest/users","/api/book", "/rest/books", "/h2-console/**" ).permitAll()
                //.antMatchers("/rest/**", "/web/admin.html").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login")
                .successHandler((req, res, auth) -> clearAuthenticationAttributes(req))
                .failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .and()
                .sessionManagement()
                .invalidSessionUrl("/login.html")
                .maximumSessions(2)
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry());

        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }
}

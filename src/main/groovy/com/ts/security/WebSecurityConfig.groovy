package com.ts.security

import com.ts.serviceimpl.UserDetailServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().cors().configurationSource(corsConfigurationSource()).and().csrf().disable()
                .authorizeRequests().antMatchers("/v2/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/webjars/**", "/authenticate", "/master/addRole", "/master/addTechType", "/submission/test", "/candidate/needHelp").permitAll()
                .antMatchers("/authenticate", "/master/addRole", "/submission/test", "/candidate/needHelp", "/master/addTechType").permitAll()
                .antMatchers("/user/addSuperAdmin").permitAll().
                antMatchers("/master/**").hasRole("SuperAdmin").antMatchers("/interviewer/addInterviewer").hasAnyRole("OrgAdmin", "SuperAdmin")
                .antMatchers("/user/addSuperAdmin", "/master/addRole").permitAll()
                .antMatchers("/master/**").hasRole("SuperAdmin")
                .antMatchers("/test/**").hasAnyRole("Interviewer", "OrgAdmin", "SuperAdmin")
                .anyRequest().authenticated().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth_token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }
}

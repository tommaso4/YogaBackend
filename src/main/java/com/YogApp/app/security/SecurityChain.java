package com.YogApp.app.security;

import com.YogApp.app.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityChain {
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
//        httpSecurity.cors(AbstractHttpConfigurer::disable);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeHttpRequests(req-> req.requestMatchers("/auth/**").permitAll());
        httpSecurity.authorizeHttpRequests(req-> req.requestMatchers("/asana/create").hasAuthority(Role.ADMIN.name()));
        httpSecurity.authorizeHttpRequests(req-> req.requestMatchers("/asana/delete/**").hasAuthority(Role.ADMIN.name()));
        httpSecurity.authorizeHttpRequests(req-> req.requestMatchers("/user/addAsana/**").hasAuthority(Role.CLIENT.name()));
        httpSecurity.authorizeHttpRequests(req-> req.requestMatchers("/user/removeAsana/**").hasAuthority(Role.CLIENT.name()));
        httpSecurity.authorizeHttpRequests(req-> req.requestMatchers("/user/{id}").permitAll());
        httpSecurity.authorizeHttpRequests(req-> req.requestMatchers("/user/delete/{id}").permitAll());
        httpSecurity.authorizeHttpRequests(req-> req.requestMatchers("asana/getAll").permitAll());
        httpSecurity.authorizeHttpRequests(req-> req.requestMatchers("asana/getByType/{type}").permitAll());
        return httpSecurity.build();
    }

}

package com.FMNNotificationServer.FMNNotificationServer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .cors(httpSecurityCorsConfigurer -> //this is effectively disabling cors so that my Angular requests dont get blocked
                        httpSecurityCorsConfigurer.configurationSource(request ->
                                new CorsConfiguration().applyPermitDefaultValues()))
                .authorizeHttpRequests(authorizeConfig ->{
                    authorizeConfig.requestMatchers("/").permitAll();
                    authorizeConfig.requestMatchers("/home").permitAll();
                    authorizeConfig.requestMatchers("/signUpForm").permitAll();
                    authorizeConfig.requestMatchers("/login").permitAll();
                    authorizeConfig.requestMatchers("/ex/foos").permitAll();
                    authorizeConfig.requestMatchers("/api/v1/publish").permitAll();
                    authorizeConfig.requestMatchers("/emailing/sendEmail").permitAll();
                    authorizeConfig.requestMatchers("/events").permitAll();
                    authorizeConfig.requestMatchers("/eventForm").permitAll();
                    authorizeConfig.requestMatchers("/getUserList").permitAll();
                    authorizeConfig.anyRequest().authenticated();
                })
                //.formLogin().loginPage("/login") //disabled formlogin since angular requests were getting caught in them
                //.and()
                .csrf(csrf -> {
                    csrf.ignoringRequestMatchers("/emailing/sendEmail");
                    csrf.ignoringRequestMatchers("/events"); //having csrf ignore requests from "/events" since this is where front end/Angular sends
                    csrf.ignoringRequestMatchers("/eventForm");
                })
                //.csrf().ignoringRequestMatchers("/emailing/sendEmail")
                //.and()
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("user")
                        .password("{noop}Pass123")
                        .authorities("ROLE_user")
                        .build()
        );
    }
}

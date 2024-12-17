package com.example.mylibrary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import com.example.mylibrary.security.CustomUserDetailsService;
import com.example.mylibrary.security.SocialLoginSuccessHandler;
import com.example.mylibrary.service.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, SocialLoginSuccessHandler handler)
            throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(a -> {
                    a.requestMatchers("/login").permitAll();
                    a.requestMatchers(HttpMethod.POST, "/users").permitAll();
                    a.anyRequest().authenticated(); // nao oclocar nenhum request antes dessa linha
                })
                .oauth2Login(a -> {
                    a.successHandler(handler);
                })
                .oauth2ResourceServer(o -> o.jwt(Customizer.withDefaults()))
                .build();

    }


    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    public UserDetailsService userDetailsService(UserService service) {
        // var user1 =
        // User.builder().username("user").password(encoder.encode("123123123")).roles("USER").build();
        // var user2 =
        // User.builder().username("admin").password(encoder.encode("123123123")).roles("ADMIN").build();

        // return new InMemoryUserDetailsManager(user1, user2);
        return new CustomUserDetailsService(service);
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }
}

package com.example.mylibrary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                // .formLogin(Customizer.withDefaults())
                .formLogin(configurer -> configurer.loginPage("/login").permitAll())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(a -> {
                    a.requestMatchers("/login").permitAll();
                    a.requestMatchers(HttpMethod.POST, "/users").permitAll();
                    // a.requestMatchers(HttpMethod.POST, "/autores").hasAnyAuthority("CADASTRAR_AUTOR");
                    // a.requestMatchers(HttpMethod.POST, "/autores").hasRole("ADMIN");
                    // a.requestMatchers("/autores/**").hasRole("ADMIN");
                    // a.requestMatchers("/livros/**").hasAnyRole("ADMIN", "USER");
                    a.anyRequest().authenticated(); // nao oclocar nenhum request antes dessa linha
                })
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        var user1 = User.builder().username("user").password(encoder.encode("123123123")).roles("USER").build();
        var user2 = User.builder().username("admin").password(encoder.encode("123123123")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}

package com.example.mylibrary.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.mylibrary.model.User;
import com.example.mylibrary.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SocialLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final String DEFAULT_PASSWORD = "123123123";
    private final UserService service;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken oauth = (OAuth2AuthenticationToken) authentication;

        var principal = oauth.getPrincipal();
        String email = principal.getAttribute("email");
        var user = service.findByEmail(email);

        if (user == null) {
            user = new User();
            user.setEmail(email);
            var login = email.substring(0, email.indexOf('@'));
            user.setLogin(login);
            user.setPassword(DEFAULT_PASSWORD);
            user.setRoles(List.of("USER"));
            service.save(user);
        }

        var customAuth = new CustomAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(customAuth);
        super.onAuthenticationSuccess(request, response, customAuth);
    }

}

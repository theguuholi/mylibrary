package com.example.mylibrary.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.mylibrary.model.User;
import com.example.mylibrary.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserService service;

    public User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // UserDetails userDetails = (UserDetails) auth.getPrincipal();
        // String login = userDetails.getUsername();
        // return service.findByLogin(login);
        if (auth instanceof CustomAuthentication customAuth) {
            return customAuth.getUser();
        }
        return null;
    }
}

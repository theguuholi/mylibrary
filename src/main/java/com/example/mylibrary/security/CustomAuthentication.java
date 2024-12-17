package com.example.mylibrary.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.mylibrary.model.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CustomAuthentication implements Authentication {

    private final User user;

    @Override
    public String getName() {
       return this.user.getLogin();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                // .map(r -> new SimpleGrantedAuthority(r))
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCredentials'");
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'setAuthenticated'");
    }

}

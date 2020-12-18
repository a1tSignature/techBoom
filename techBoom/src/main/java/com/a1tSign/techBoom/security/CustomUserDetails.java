package com.a1tSign.techBoom.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private String username;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static CustomUserDetails fromClaimsToCustomUserDetails(Claims claims) {
        var userDetails = new CustomUserDetails();
        userDetails.username = claims.getSubject();

        var roles = claims.get("roles", String.class).split(",");
        userDetails.grantedAuthorities = Arrays.stream(roles)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

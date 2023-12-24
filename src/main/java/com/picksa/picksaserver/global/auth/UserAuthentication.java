package com.picksa.picksaserver.global.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {

    private UserAuthentication(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(principal, null, authorities);
    }

    public static UserAuthentication from(UserDetails userDetails) {
        return new UserAuthentication(userDetails, userDetails.getAuthorities());
    }

}

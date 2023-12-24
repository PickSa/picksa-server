package com.picksa.picksaserver.global.auth;

import com.picksa.picksaserver.user.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {

    private final String ROLE_PREFIX = "ROLE_";
    private UserEntity userEntity;

    private CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public static CustomUserDetails of(UserEntity userEntity) {
        return new CustomUserDetails(userEntity);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.getUserRole()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return String.valueOf(userEntity.getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private String getUserRole() {
        String name = this.userEntity.getPosition().name();
        return ROLE_PREFIX.concat(name);
    }

}

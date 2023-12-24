package com.picksa.picksaserver.global.auth;

import com.picksa.picksaserver.auth.exception.AuthErrorCode;
import com.picksa.picksaserver.user.UserEntity;
import com.picksa.picksaserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findById(Long.valueOf(username))
                .orElseThrow(() -> new UsernameNotFoundException(AuthErrorCode.USER_NOT_REGISTERED.message()));
        return CustomUserDetails.of(userEntity);
    }

}

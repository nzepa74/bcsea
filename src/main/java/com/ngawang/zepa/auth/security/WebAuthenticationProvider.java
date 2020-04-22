package com.ngawang.zepa.auth.security;

import com.ngawang.zepa.auth.dto.UserDTO;
import com.ngawang.zepa.auth.service.UserLoginService;
import com.ngawang.zepa.enumeration.LoginErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class WebAuthenticationProvider implements AuthenticationProvider {
    //region private variable
    @Autowired
    private PasswordEncoder passwordEncoder;
    //region private service
    @Autowired
    private UserLoginService userLoginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;

        String username = String.valueOf(auth.getPrincipal());
        username = username.trim().toUpperCase();
        String password = String.valueOf(auth.getCredentials());

        UserDTO userLogin = userLoginService.login(username);
        if (userLogin == null) {
            throw new UsernameNotFoundException(LoginErrorCode.FAILED.getCode());
        } else if (!userLogin.getUserStatus().equals('A')) {
            throw new LockedException(LoginErrorCode.LOCKED.getCode());
        } else if (password.equals(userLogin.getPassword())) {
//        } else if (passwordEncoder.matches(password, userLogin.getPassword())) {
            Set<GrantedAuthority> authorities = getAccessRight(userLogin);
            return new UsernamePasswordAuthenticationToken(userLogin, password, authorities);
        } else {
            throw new BadCredentialsException(LoginErrorCode.FAILED.getCode());
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UsernamePasswordAuthenticationToken.class);
    }

    private Set<GrantedAuthority> getAccessRight(UserDTO userLogin) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

        if (userLogin.getUserGroupId() == 1) {//operator
            authorities.add(new SimpleGrantedAuthority("0101-01-VIEW"));
        } else if (userLogin.getUserGroupId() == 2) {//Finance Manager
            authorities.add(new SimpleGrantedAuthority("0101-02-VIEW"));
        } else if (userLogin.getUserGroupId() == 3) {//Administrator
            authorities.add(new SimpleGrantedAuthority("0101-03-VIEW"));
        }

        return authorities;
    }
}

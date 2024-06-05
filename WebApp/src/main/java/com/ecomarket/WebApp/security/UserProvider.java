package com.ecomarket.WebApp.security;

import com.ecomarket.WebApp.Service.UserDetailsService;
import com.ecomarket.WebApp.details.UserDetails;
import com.ecomarket.WebApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        Object credentials = authentication.getCredentials();
        if (credentials == null) throw new BadCredentialsException("Не удалось войти в аккаунт");
        String password = credentials.toString();

        UserDetails userDetails = (UserDetails) userDetailsService.loadUserByUsername(username);
        User user = userDetails.getUser();

        if (user == null) throw new BadCredentialsException("Пользователя с таким ником не существует");
        if (!user.getPassword().equals(password)) throw new BadCredentialsException("Пароль введён неверно");

        return new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}

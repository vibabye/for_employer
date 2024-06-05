package com.ecomarket.WebApp.details;

import com.ecomarket.WebApp.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.List;

/**
* getAuthorities(): возвращает коллекцию объектов GrantedAuthority, представляющих разрешения, предоставленные пользователю.
* getPassword(): возвращает пароль пользователя.
* getUsername(): возвращает имя пользователя.
* isAccountNonExpired(): указывает, не истек ли срок действия учетной записи пользователя.
* isAccountNonLocked(): указывает, заблокирована ли учетная запись пользователя.
* isCredentialsNonExpired(): указывает, не истек ли срок действия учетных данных пользователя.
* isEnabled(): указывает, включен ли пользователь.
*/
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private User user;

    public UserDetails(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return getUser().getPassword();
    }

    @Override
    public String getUsername() {
        return getUser().getLogin();
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

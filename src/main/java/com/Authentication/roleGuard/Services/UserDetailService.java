package com.Authentication.roleGuard.Services;

import com.Authentication.roleGuard.DAO.RoleDaoImpl;
import com.Authentication.roleGuard.DAO.UserDaoImpl;
import com.Authentication.roleGuard.DTO.userDto;
import com.Authentication.roleGuard.Models.RoleEnum;
import com.Authentication.roleGuard.Models.Users;
import com.Authentication.roleGuard.Models.role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetails {
//    @Autowired
//    private userDto userDTO;

    @Autowired
    private final Users users;

    @Autowired
    private RoleDaoImpl roleDao;

    @Autowired
    private UserDaoImpl userDao;

    public UserDetailService(Users users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return users.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
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
}

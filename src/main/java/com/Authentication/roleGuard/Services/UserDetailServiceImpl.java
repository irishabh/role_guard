package com.Authentication.roleGuard.Services;

import com.Authentication.roleGuard.Config.jwtUtil;
import com.Authentication.roleGuard.DAO.RoleDaoImpl;
import com.Authentication.roleGuard.DAO.UserDaoImpl;
import com.Authentication.roleGuard.DTO.userDto;
import com.Authentication.roleGuard.Mapper.userMapper;
import com.Authentication.roleGuard.Models.Users;
import com.Authentication.roleGuard.Models.role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private userMapper mapper;
    @Autowired
    private RoleDaoImpl roleDao;
    @Autowired
    private jwtUtil JwtUtil;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = userDao.getByUserName(username);


        if (users == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Set<role> list = userDao.getRoleByUsername(username);
        users.setRoles(list);
UserDetailService userDetailService = new UserDetailService(users);


        return userDetailService;
    }

    public Set<GrantedAuthority> getAuthorities(Set<role> roles){
        return roles.stream().map(role->new SimpleGrantedAuthority("ROLE_"+role.getRoleName())).collect(Collectors.toSet());
    }
}

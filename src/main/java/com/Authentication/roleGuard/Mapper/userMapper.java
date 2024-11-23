package com.Authentication.roleGuard.Mapper;

import com.Authentication.roleGuard.DAO.RoleDaoImpl;
import com.Authentication.roleGuard.DTO.userDto;
import com.Authentication.roleGuard.Models.RoleEnum;
import com.Authentication.roleGuard.Models.Users;
import com.Authentication.roleGuard.Models.role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class userMapper {
    /*@Autowired
    private BCryptPasswordEncoder encoder;*/
    @Autowired
    private RoleDaoImpl roleDao;
    public Users toEntity(userDto userDTO){
        Users user = new Users();
        Set<role> roles= new HashSet<>();
        for(String roleName:userDTO.getRoles()){
            role role = roleDao.getRole(roleName);
            roles.add(role);
        }
        user.setRoles(roles);

        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        return user;
    }

    public userDto toUserDto(Users users){
        userDto userDTO = new userDto();
        userDTO.setEmail(users.getEmail());
        userDTO.setUsername(users.getUsername());
        /*userDTO.setRoles(users.getRoles().stream().map(role::getRoleName).collect(Collectors.toSet()));*/
        Set<role> roles = new HashSet<>();
        for(role roleName:users.getRoles()){
            role getrole= roleDao.getRole(roleName.toString());
            roles.add(getrole);
        }
        userDTO.setRoles(Collections.singleton(roles.toString()));
        userDTO.setName(users.getName());
        userDTO.setPassword(users.getPassword());
        return userDTO;
    }

}

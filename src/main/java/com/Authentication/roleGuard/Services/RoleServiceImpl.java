package com.Authentication.roleGuard.Services;

import com.Authentication.roleGuard.DAO.RoleDaoImpl;
import com.Authentication.roleGuard.DAO.UserDaoImpl;
import com.Authentication.roleGuard.Models.Users;
import com.Authentication.roleGuard.Models.role;
import com.Authentication.roleGuard.Services.Interfaces.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private RoleDaoImpl roleDao;
    @Transactional
    public void assignRoletoUser(String username, String roleName){
        Users users = userDao.getByUserName(username);
        role role = roleDao.getRole(roleName);
        if(users!=null && role != null){
            users.getRoles().add(role);
            userDao.updateUser(users);
        }else {
            throw new RuntimeException("User and Role are not found");
        }
    }

    @Transactional
    public role createRole(role role){
        roleDao.createNewRole(role);
        return role;
    }

    @Transactional
    public String updateRole(String username, Set<String> newRoleName){
        Users users = userDao.getByUserName(username);
        if(users == null)
        {
            throw new RuntimeException("User not found");
        }
        Set <role> newRole= newRoleName.stream().map(roleName->roleDao.getRole(roleName)).collect(Collectors.toSet());
        users.setRoles(newRole);
        userDao.createNewUser(users);

        return("Role Updated");
    }


}

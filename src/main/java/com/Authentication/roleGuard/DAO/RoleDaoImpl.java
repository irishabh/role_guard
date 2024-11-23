package com.Authentication.roleGuard.DAO;

import com.Authentication.roleGuard.DAO.Interfaces.RoleDao;
import com.Authentication.roleGuard.Models.role;
import com.Authentication.roleGuard.Repositories.RoleRepository;
import com.Authentication.roleGuard.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class RoleDaoImpl implements RoleDao {
    @Autowired
private RoleRepository roleRepo;
    @Autowired
    private UserRepository userRepository;
    public List<role> getAllRole(){return roleRepo.findAll();}

@Transactional
        public role createNewRole(role role){
    return roleRepo.save(role);
    }
    @Transactional
    public role updateRole(role roleDao){
    role roleObj=roleRepo.fetchRoleName(roleDao.getRoleName());
    if(roleObj == null){
        throw new RuntimeException("Role not Found");
    }
    roleObj.setRoleName(roleDao.getRoleName());

    return roleRepo.save(roleObj);
    }

    @Transactional
    public boolean deleteRole(Long id){
        if(roleRepo.existsById(id)){
            roleRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public role getRole(String role){
        return roleRepo.fetchRoleName(role);
    }

   /* @Transactional
    public Set<role> getRoleByUsername(String username){
        Set<role> roles= userRepository.getRoleByUsername(username);
        if (roles != null & roles.contains("ADMIN")) {
            throw new RuntimeException("User not found with username: " + username);
        }

        return roles; }*/

   /*   @Transactional
    @Override
    public List<role> getRolesListByUsername(String username){
//        Users users=userRepository.fetchUserName(username);
        List<role> roles = roleRepo.getRoleListByUsername(username);
//        users.setRoleID(roles);
        return roles;
    }*/

    /*@Transactional
    public role getRoleName(String role){
        return roleRepo.findByName(role);
    }*/

    public String getRoles(String role){
        return String.valueOf(roleRepo.fetchRoleName(role));
    }


}

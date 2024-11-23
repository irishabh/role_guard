package com.Authentication.roleGuard.DAO;

import com.Authentication.roleGuard.DAO.Interfaces.UserDao;
import com.Authentication.roleGuard.Models.Users;
import com.Authentication.roleGuard.Models.role;
import com.Authentication.roleGuard.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class
UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<Users> getUserById(Long id){
        return userRepository.findById(id);
    }
    @Transactional
    public Users createNewUser(Users users){
        return userRepository.save(users);
    }
    @Transactional
    public Users updateUser(Users users){
        Users usersEntity= userRepository.fetchUserName(users.getUsername());
        if(usersEntity == null){
            throw new RuntimeException("user not found");
        }
        usersEntity.setName(users.getName());
       // usersEntity.setRole(String.valueOf(RoleEnum.USER));
        usersEntity.setUsername(users.getUsername());
        usersEntity.setPassword(users.getPassword());

        return userRepository.save(usersEntity);
    }
    @Transactional
    public Boolean deleteUser(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Users getByUserName(String username){
        return userRepository.fetchUserName(username);
    }

//    public Set<role> findRolesByUserId(Long id){
////        Users users = userRepository.fetchUserName(id);
//        Set<role> roles= userRepository.findRolesByUserId(users.getId());
////        users.setRoles(roles);
//        return roles;
//    }
   /* public Set<role> getRolesByUserId(String username){
        Users users=userRepository.fetchUserName(username);
        Set<role> roles = userRepository.getRolesByUserId(username);
        users.setRoles(userRepository.getRolesByUserId(username));
        return roles;
    }*/

    public List<String> getRoleNamesForUser(String username){
        if(username==null){
            throw new IllegalArgumentException("username can not be null");
        }
        return userRepository.findRoleNamesByUsername(username);
    }
public Set<role> getRoleByUsername(String username){
        if(username==null){
            throw new IllegalArgumentException("username can not be null");
        }
        return userRepository.findRoleByUsername(username);
}


}

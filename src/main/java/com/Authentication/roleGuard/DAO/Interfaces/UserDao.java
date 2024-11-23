package com.Authentication.roleGuard.DAO.Interfaces;

import com.Authentication.roleGuard.Models.Users;
import com.Authentication.roleGuard.Models.role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserDao {
    List<Users> getAllUsers();
    Optional<Users> getUserById(Long id);
    Users createNewUser(Users users);
    Users updateUser(Users users);
    Boolean deleteUser(Long id);
    Users getByUserName(String username);

//    public Set<role> findRolesByUserId(Long id);

//    public Set<role> getRolesByUserId(String username);

    List<String> getRoleNamesForUser(String username);

    Set<role> getRoleByUsername(String username);



}

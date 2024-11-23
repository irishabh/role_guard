package com.Authentication.roleGuard.DAO.Interfaces;

import com.Authentication.roleGuard.Models.role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    List<role> getAllRole();
    role createNewRole(role role1);
    role updateRole(role role1);
    role getRole(String role);
    boolean deleteRole(Long id);
  /*public Set<role> getRoleByUsername(String username);*/

    String getRoles(String role);

    /*public role getRoleName(String role);*/

  /*public List<role> getRolesListByUsername(String username);*/


}

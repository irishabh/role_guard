package com.Authentication.roleGuard.Services.Interfaces;

import com.Authentication.roleGuard.Models.role;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface RoleService {
    role createRole(role roleName);
    void assignRoletoUser(String username, String roleName);
    String updateRole(String username, Set<String> newRole);


}

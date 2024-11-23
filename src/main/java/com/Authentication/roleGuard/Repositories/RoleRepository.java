package com.Authentication.roleGuard.Repositories;

import com.Authentication.roleGuard.Models.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository <role, Long> {
    @Query(value = "SELECT r FROM role r WHERE r.roleName = ?1")
    role fetchRoleName(@Param("roleName") String roleName);

    /*@Query(value = "select r.roleName from users u join role r where u.roleID=r.id and u.username= :username ")
    List<role> getRoleListByUsername(@Param("username") String username);*/



}

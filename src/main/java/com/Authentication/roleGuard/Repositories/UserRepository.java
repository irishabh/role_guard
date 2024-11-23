package com.Authentication.roleGuard.Repositories;

import com.Authentication.roleGuard.Models.Users;
import com.Authentication.roleGuard.Models.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    @Query(value = "SELECT s FROM Users s WHERE s.username = :username")
    Users fetchUserName(@Param("username") String username);

    /*@Query("SELECT u.username ,r.roleName FROM Users u JOIN u.roles r WHERE u.username = :username")
    Set<role> getRoleByUsername(@Param("username") String username);*/

//    @Query("SELECT u.roles FROM User u WHERE u.id = :userId")
//    Set<role> findRolesByUserId(@Param("userId") Long userId);

     /*@Query(
      value = "SELECT r.roleName FROM users u JOIN user_roles ur ON u.id=ur.user_ID, join role r on ur.role_id=r.id " +
              "WHERE  u.username= :username ",nativeQuery = true)
    Set<role> getRolesByUserId(@Param("username") String username);*/
@Query("Select r.roleName from Users u JOIN u.roles r where u.username = :username")
    List<String> findRoleNamesByUsername(@Param("username") String username);

@Query(value = "Select r.role_name from User u"+ " JOIN user_roles ur ON u.id = ur.user_id"+
        " JOIN role r ON ur.role_id=r.id"+" WHERE u.username= :username", nativeQuery = true)
    Set<role> findRoleByUsername(@Param("username") String username);





}

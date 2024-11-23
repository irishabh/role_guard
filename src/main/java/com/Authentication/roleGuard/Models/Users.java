package com.Authentication.roleGuard.Models;

import com.Authentication.roleGuard.Config.RoleDeserilization;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

   /* @Enumerated(EnumType.STRING)
    @Column
    private role roleID;*/

     // Bidirectional many-to-many relationship

//    @OneToMany(targetEntity = role.class, fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonDeserialize(using = RoleDeserilization.class)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_roles",  // Join table
        joinColumns = @JoinColumn(name = "user_id"),  // FK to user
        inverseJoinColumns = @JoinColumn(name = "role_id")  // FK to role
    )
    private Set<role> roles = new HashSet<>();

  /*  @Column(name = "roleId")
    private List<role> roleID;
*/

}

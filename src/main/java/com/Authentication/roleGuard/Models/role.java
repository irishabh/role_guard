package com.Authentication.roleGuard.Models;

import com.Authentication.roleGuard.Config.RoleDeserilization;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
@Data
@NoArgsConstructor
//@AllArgsConstructor
@JsonDeserialize(using = RoleDeserilization.class)
@Entity
@Table(name = "role")
public class role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "roleName")
    private String roleName;

    // Bidirectional many-to-many relationship
    @ManyToMany(mappedBy = "roles")
    private Set<Users> users = new HashSet<>();

    public role(long id, String roleName, Set<Users> users) {
        this.id = id;
        this.roleName = roleName;
        this.users = users;
    }
@JsonCreator
    public role(String roleName) {
        this.roleName=roleName;
    }
}

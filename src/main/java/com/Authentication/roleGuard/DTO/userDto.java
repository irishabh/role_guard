package com.Authentication.roleGuard.DTO;

import com.Authentication.roleGuard.Models.role;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Data
@Component
public class userDto {
    private long id;
    private String email;
    private Set<String> roles;
    private String username;

    private String password;

    private  String name;

    private List<role> roleId;

}

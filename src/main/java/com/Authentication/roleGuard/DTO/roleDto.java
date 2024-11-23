package com.Authentication.roleGuard.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class roleDto {
    private long id;
    private String roleName;
}

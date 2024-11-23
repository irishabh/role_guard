package com.Authentication.roleGuard.Config;

import com.Authentication.roleGuard.Models.RoleEnum;
import com.Authentication.roleGuard.Models.role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToRoleConverter implements Converter<String, RoleEnum> {
    @Override
    public RoleEnum convert(String source) {
       try {
           return RoleEnum.valueOf(source);
       }catch (IllegalArgumentException ex){
             throw new IllegalArgumentException("Invalid role: " + source);
       }
    }
}

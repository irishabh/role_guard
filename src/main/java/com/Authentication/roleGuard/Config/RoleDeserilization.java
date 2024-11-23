package com.Authentication.roleGuard.Config;

import com.Authentication.roleGuard.Models.role;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class RoleDeserilization extends JsonDeserializer<role> {
    @Override
    public role deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String roleName = jsonParser.getText();
        return new role(roleName);
    }
}

package com.Authentication.roleGuard.Services.Interfaces;

import com.Authentication.roleGuard.DTO.AuthResponse;
import com.Authentication.roleGuard.DTO.userDto;
import com.Authentication.roleGuard.Models.Users;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface UserService {
String createUser(userDto users);

    Optional<Users> getUserById(Long id);

    List<Users> getAllUsers();

    Users updateUser(Long userId, userDto userDTO);

    /*List<Users> searchUsers(String keyword);*/

   void validateUserDTO(@Valid userDto userDTO);

   String createAdmin(@Valid Users users);
}

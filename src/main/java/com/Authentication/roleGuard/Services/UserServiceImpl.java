package com.Authentication.roleGuard.Services;

import com.Authentication.roleGuard.Config.jwtUtil;
import com.Authentication.roleGuard.DAO.RoleDaoImpl;
import com.Authentication.roleGuard.DAO.UserDaoImpl;
import com.Authentication.roleGuard.DTO.userDto;
import com.Authentication.roleGuard.Mapper.userMapper;
import com.Authentication.roleGuard.Models.Users;
import com.Authentication.roleGuard.Services.Interfaces.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private userMapper mapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private jwtUtil jwtUTIL;

    @Autowired
    private RoleDaoImpl roleDao;

    public void validateUserDTO(@Valid userDto userDTO) {
        if (userDTO.getUsername().isBlank() || null == userDTO.getUsername()) {
            throw new RuntimeException("Username is required");
        }
        if (userDTO.getName().isBlank() || null == userDTO.getName()) {
            throw new RuntimeException("Name is required");
        }
        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }
        if (userDTO.getRoles() == null || userDTO.getRoles().isEmpty()) {
            throw new IllegalArgumentException("user role is not blank");
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("password is not blank");
        }
        // Additional validation logic can be added as needed
    }

    @Transactional
    public Users updateUser(Long userId, @Valid userDto userDTO) {
        // Validate user details
        validateUserDTO(userDTO);
        /*Set<role> roles = roleDao.getRoleByUsername(userDTO.getUsername());*/
        Users users = mapper.toEntity(userDTO);
        // Save updated user
        return userDao.updateUser(users);
    }

    public List<Users> getAllUsers() {
        return userDao.getAllUsers();
    }

    /*public List<Users> searchUsers(String keyword) {
        List<Users> allUsers = userRepository.findAll();
        return allUsers.stream()
                .filter(user -> user.getUsername().contains(keyword) || user.getEmail().contains(keyword))
                .collect(Collectors.toList());
    }*/

    public Optional<Users> getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public String createUser(@Valid userDto users) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {


            Users user = mapper.toEntity(users);
            user.setPassword(passwordEncoder.encode(users.getPassword()));

// Perform validation
            validateUserDTO(users);


            // Save user
            userDao.createNewUser(user);

        }
        return ("SuccessFully User Created");


    }

    @Transactional
    public String createAdmin(@Valid Users users) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Perform validation
        userDto userDTO = mapper.toUserDto(users);
        userDTO.setPassword(passwordEncoder.encode(users.getPassword()));
        /*userDTO.setRoles(users.getRoles().stream().map(role::getRoleName).collect(Collectors.toSet()));*/
        /*Set<role> roles = roleDao.getRoleByUsername(userDTO.getUsername());*/
        validateUserDTO(userDTO);

        // Convert DTO to entity
        Users user = mapper.toEntity(userDTO);
        //jwt token
        //String token =jwtUTIL.generateToken(userDTO);
        // Save user
        userDao.createNewUser(user);


        return ("User Created User Created Successfully");


    }


}

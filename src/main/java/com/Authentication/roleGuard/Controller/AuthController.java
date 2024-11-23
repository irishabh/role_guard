package com.Authentication.roleGuard.Controller;

import com.Authentication.roleGuard.Config.jwtUtil;
import com.Authentication.roleGuard.DAO.RoleDaoImpl;
import com.Authentication.roleGuard.DAO.UserDaoImpl;
import com.Authentication.roleGuard.DTO.AuthResponse;
import com.Authentication.roleGuard.DTO.roleDto;
import com.Authentication.roleGuard.DTO.userDto;
import com.Authentication.roleGuard.Mapper.userMapper;
import com.Authentication.roleGuard.Models.RoleEnum;
import com.Authentication.roleGuard.Models.Users;
import com.Authentication.roleGuard.Models.role;
import com.Authentication.roleGuard.Services.AuthServiceImpl;
import com.Authentication.roleGuard.Services.RoleServiceImpl;
import com.Authentication.roleGuard.Services.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private jwtUtil utilJwt;
    @Autowired
    private AuthServiceImpl authService;

     @Autowired
    private UserServiceImpl userService;
    @Autowired
    private userMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleDaoImpl roleDao;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private roleDto roleDTO;

    @Autowired
    private UserDaoImpl userDao;

    @PostMapping("/login")
    public String login(@RequestBody userDto users, HttpSession session){
      try{
          final Authentication authentication = authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(
                          users.getUsername(),
                          users.getPassword()
                  )
          );
           session.setAttribute("username", authentication.getName());
           session.setAttribute("loginTime", System.currentTimeMillis());
            session.setAttribute("role", authentication.getAuthorities());

            String token = utilJwt.generateToken(users);



            return "{\"message\": \"Login successful\", \"username\": \"" + authentication.getName() + "\", \"token\": \""+token+"\", \"Role\": \""+authentication.getAuthorities()+"\"}";
      }
      catch (AuthenticationException e){
          return "{\"message\": \"Login failed\"}";
      }

//       return ResponseEntity.ok(authService.authenticate(users));
    }
    /*@PreAuthorize("hasRole('USER')")*/
    @PostMapping("/register")
    public String register(@RequestBody userDto users){
        //role role = roleDao.getRole("user");
        //users.getRoles().add()
        if(users!=null)
        ResponseEntity.ok(userService.createUser(users));
        else
            return "{\"message\": \"invalid user\"}";

    return "{\"message\": \"User Registration Success\"}";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/registerAdmin")
    public String registerAdmin(@RequestBody Users users) {
        role role = roleDao.getRole("admin");
        if(users!=null)
        ResponseEntity.ok(userService.createAdmin(users));
        else
            return "{\"message\": \"invalid user\"}";

        return "{\"message\": \"Admin Registration Success\"}";

    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllUser")
    public ResponseEntity<List<Users>> getAllUser(){
        List<Users> userList= userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(false);
        if (session != null){
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return "Successful Logout";
    }

     @GetMapping("/user-info")
    public String getUserInfo(HttpSession session) {
        String username = (String) session.getAttribute("username");
        long loginTime = (long)session.getAttribute("loginTime");
        Date date = new Date(loginTime);

        if (username != null) {
            return "{\"username\": \"" + username + "\", \"loginTime\": \"" + date + "\"}";
        } else {
            return "{\"message\": \"User not logged in\"}";
        }
    }
    @PostMapping("/assignRole")
    public String assignRole(@RequestBody userDto userDTO){

        if(userDTO.getUsername()!=null && userDTO.getRoles() != null) {
            roleService.assignRoletoUser(userDTO.getUsername(), userDTO.getRoles().toString().replaceAll("[\\[\\]\"]", ""));
        }
        return "{\"message\": \"New Role Assigned to User\"}";
    }
}

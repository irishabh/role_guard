package com.Authentication.roleGuard.Services;

import com.Authentication.roleGuard.Config.jwtUtil;
import com.Authentication.roleGuard.DAO.UserDaoImpl;
import com.Authentication.roleGuard.DTO.AuthResponse;
import com.Authentication.roleGuard.DTO.userDto;
import com.Authentication.roleGuard.Mapper.userMapper;
import com.Authentication.roleGuard.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {
@Autowired
    private AuthenticationManager manager;
@Autowired
private UserDaoImpl userDao;

@Autowired
private jwtUtil jwtUTIL;

@Autowired
private userMapper mapper;

public AuthResponse authenticate(userDto users){
   /*Authentication authentication =*/ manager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));
Users users1 = userDao.getByUserName(users.getUsername());
    /*SecurityContextHolder.getContext().setAuthentication(users);*/
    userDto userDTO = mapper.toUserDto(users1);
String token = jwtUTIL.generateToken(userDTO);
return new AuthResponse(token,"User Login Successfully");
}
}

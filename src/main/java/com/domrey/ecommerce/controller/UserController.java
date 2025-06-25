package com.domrey.ecommerce.controller;


import com.domrey.ecommerce.entity.MyUser;
import com.domrey.ecommerce.entity.dto.LoginResponse;
import com.domrey.ecommerce.repository.MyUserRepository;
import com.domrey.ecommerce.service.CustomUserDetailsService;
import com.domrey.ecommerce.service.JwtService;
import com.domrey.ecommerce.service.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    MyUserRepository myUserRepository;

    //@CrossOrigin(origins = "http://127.0.0.1:8000")
    @GetMapping("/user/findAllUser")
    public List<MyUser> findAllUser() {
        return myUserRepository.findAll();
    }

    //@CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping("/user/authenticate")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody LoginForm loginForm) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginForm.username(),
                    loginForm.password()
            ));
            if (authentication.isAuthenticated()) {
                String jwt = jwtService.generateToken(customUserDetailsService.loadUserByUsername(loginForm.username()));
                return ResponseEntity.ok(new LoginResponse(jwt, loginForm.username()));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (BadCredentialsException ex) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Invalid username or password");
            error.put("status", 401);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }


}

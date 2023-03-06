package com.example.app.Controller;


import com.example.app.Entity.appUser;
import com.example.app.Security.JWTUtils;
import com.example.app.service.impl.AccountServiceImpl;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
public class AccountController {


    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    /*@PostMapping(path = "/register")
    public ResponseEntity<appUser> Register(@RequestBody appUser user){
        appUser newUser = accountService.addNewUser(user);
        return ResponseEntity.ok(newUser);
    }*/



}

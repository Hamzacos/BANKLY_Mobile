package com.example.app.service.impl;


import com.example.app.Entity.appRole;
import com.example.app.Entity.appUser;
import com.example.app.Reposetry.RoleRepository;
import com.example.app.Reposetry.UserRepository;
import com.example.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public appUser addNewUser(appUser user) {
        String pw = user.getPassword();
        user.setPassword(passwordEncoder.encode(pw));
        appRole clientRole = roleRepository.findByRoleName("Client");
        user.getUserRoles().add(clientRole);
        return userRepository.save(user);
    }

    @Override
    public appRole addNewRole(appRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        appUser user = userRepository.findByUsername(username);
        appRole role = roleRepository.findByRoleName(roleName);
        user.getUserRoles().add(role);
    }

    @Override
    public appUser loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<appUser> listUser() {
        return userRepository.findAll();
    }
}

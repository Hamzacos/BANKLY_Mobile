package com.example.app.service;



import com.example.app.Entity.appRole;
import com.example.app.Entity.appUser;

import java.util.List;

public interface AccountService{
    appUser addNewUser(appUser user);
    appRole addNewRole(appRole role);
    void addRoleToUser(String username,String RoelName);
    appUser loadUserByUsername(String username);
    List<appUser> listUser();
}

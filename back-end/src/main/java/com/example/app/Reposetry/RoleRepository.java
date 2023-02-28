package com.example.app.Reposetry;


import com.example.app.Entity.appRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<appRole,Integer> {

    appRole findByRoleName(String roleName);
}

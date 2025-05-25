package com.br.securityApp.repository;


import com.br.securityApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{

    User findByUserName(String username);
}

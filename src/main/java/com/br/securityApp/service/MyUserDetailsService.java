package com.br.securityApp.service;

import com.br.securityApp.model.User;
import com.br.securityApp.model.UserDetailsCustom;
import com.br.securityApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);

        if(user == null){

            System.out.println("Usuario não encontrado");

            throw new UsernameNotFoundException("Usuario não encontrado");

        }

        return new UserDetailsCustom(user);
    }
}

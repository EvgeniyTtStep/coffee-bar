package com.example.coffeebar.service;

import com.example.coffeebar.entity.User;
import com.example.coffeebar.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//            return userRepository.findUserByUsername(username)
//                    .orElseThrow(() -> new UsernameNotFoundException("User with username = " + username + " not found"));
//    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("email = " + email);
        Optional<User> userByEmail = userRepository.findUserByEmail(email);
        System.out.println("user username = " + userByEmail.get().getUsername());
        System.out.println("user Email = " + userByEmail.get().getEmail());
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with username = " + email + " not found"));
    }
}

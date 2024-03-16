package com.example.coffeebar.service;

import com.example.coffeebar.entity.ERole;
import com.example.coffeebar.entity.Role;
import com.example.coffeebar.entity.User;
import com.example.coffeebar.repository.RoleRepository;
import com.example.coffeebar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User save(User user) throws RoleNotFoundException {
        if (user != null) {
            User saveUser = user;
            saveUser.setPassword(passwordEncoder.encode(user.getPassword()));
            Role role = roleRepository.findRoleByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RoleNotFoundException("Role not found"));
            saveUser.setRoles(new HashSet<>(List.of(role)));
            userRepository.save(saveUser);
            return saveUser;
        }
        return user;
    }


}

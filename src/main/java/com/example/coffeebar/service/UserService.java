package com.example.coffeebar.service;

import com.example.coffeebar.entity.ERole;
import com.example.coffeebar.entity.Role;
import com.example.coffeebar.entity.User;
import com.example.coffeebar.entity.VerificationToken;
import com.example.coffeebar.repository.RoleRepository;
import com.example.coffeebar.repository.TokenRepository;
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


    private final TokenRepository tokenRepository;


    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, VerificationToken verificationToken, TokenRepository tokenRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User adminSave(User user) throws RoleNotFoundException {
        if (user != null) {
            User saveUser = user;
            saveUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(saveUser);
            return saveUser;
        }
        return user;
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


    public User saveRegisteredUser(User user) {
        if (user != null) {
            return userRepository.save(user);
        }
        return null;
    }


    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
        //.orElseThrow(() -> new UsernameNotFoundException("User vs email " + email + " not found"));
    }


    public User findUserByUsername(String principalName) {
        return userRepository.findUserByUsername(principalName).orElse(null);
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user.getId());
        tokenRepository.save(verificationToken);
    }


    public User getUserByToken(String token) {
        return userRepository.findUserById(tokenRepository.findVerificationTokenByToken(token).getUserId()).get();
    }


    public VerificationToken getVerificationToken(String token) {
        return tokenRepository.findVerificationTokenByToken(token);
    }


    public VerificationToken getTokenByUserId(Long userId) {
        return tokenRepository.findVerificationTokenByUserId(userId);
    }

    public User findUserById(Long userId) {
        return userRepository.findUserById(userId).get();
    }

    public User saveUserPassword(User user) {
        if (user != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        return null;
    }
}

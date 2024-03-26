package com.example.coffeebar.repository;

import com.example.coffeebar.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findVerificationTokenByUserId(Long id);

    VerificationToken findVerificationTokenByToken(String token);
}

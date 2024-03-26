package com.example.coffeebar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "verification_token")
@Component
@Entity
public class VerificationToken {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "user_id")
    private Long userId;

    public VerificationToken(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }
}

package com.example.coffeebar.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Component
@Table(name = "positions")
public class Position {
    @Id
    @Column(name = "id_position")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idPosition;

    String name;

    @OneToMany(mappedBy = "position")
    Set<Personal> personalSet;


}

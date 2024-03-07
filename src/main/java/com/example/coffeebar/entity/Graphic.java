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
@Table(name = "graphiks")
@Component
public class Graphic {

    @Id
    @Column(name = "id_graphiks")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idGraphic;

    @Column(name = "day_of_work")
    Integer workDay;

    @ManyToMany(mappedBy = "graphicSet")
    Set<Personal> personalSet;

}

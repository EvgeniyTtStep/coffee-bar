package com.example.coffeebar.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Component
@Table(name = "drinks")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_drinks", nullable = false)
    private Integer idDrink;

    @Basic
    @Column(name = "name_ua", nullable = true, length = 255)
    private String nameUa;

    @Basic
    @Column(name = "name_en", nullable = true, length = 255)
    private String nameEn;

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    private BigDecimal price;

    @ManyToMany(mappedBy = "drinkSet")
    Set<Order> orderSet;

}
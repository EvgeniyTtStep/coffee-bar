package com.example.coffeebar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @Column(name = "name_ua")
    @NotEmpty(message = "Поле nameUa не може бути порожнім")
    private String nameUa;

    @Basic
    @Column(name = "name_en")
    @NotEmpty(message = "Поле nameEn не може бути порожнім")
    private String nameEn;

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    private BigDecimal price;

    @ManyToMany(mappedBy = "drinkSet", fetch = FetchType.LAZY)
    private Set<Order> orderSet;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

}
package com.example.coffeebar.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "order")
@Component
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Long idOrder;


    @Basic
    @Column(name = "order_date", nullable = false)
    private Timestamp orderDate;

    @ManyToOne
    @JoinColumn(name = "personal_id", nullable = false)
    private Personal personal;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;


    @ManyToMany
    @JoinTable(
            name = "order_deserts",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "deset_id"))
    private Set<Desert> desertSet;


    @ManyToMany
    @JoinTable(
            name = "order_drinks",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id"))
    private Set<Drink> drinkSet;


}

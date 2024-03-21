package com.example.coffeebar.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Component
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Long id_image;

    @Column(name = "name")
    private String name;

    @Column(name = "content_type")
    private String contentType;

    @Lob
    @Column(name = "content")
    private byte[] content;

    @OneToMany(mappedBy = "image")
    private Set<Drink> drinkSet;


}

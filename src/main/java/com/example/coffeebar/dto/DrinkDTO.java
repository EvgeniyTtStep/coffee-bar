package com.example.coffeebar.dto;

import com.example.coffeebar.entity.Image;
import com.example.coffeebar.entity.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class DrinkDTO {

    private Integer idDrink;
    private String nameUa;
    private String nameEn;
    private BigDecimal price;
    private Image image;

    public DrinkDTO() {
    }

    public DrinkDTO(String nameUa, String nameEn, BigDecimal price, Image image) {
        this.nameUa = nameUa;
        this.nameEn = nameEn;
        this.price = price;
        this.image = image;
    }

    public DrinkDTO(String nameUa, String nameEn, BigDecimal price) {
        this.nameUa = nameUa;
        this.nameEn = nameEn;
        this.price = price;
    }
}

package com.example.coffeebar.dto;

import com.example.coffeebar.entity.Graphic;
import com.example.coffeebar.entity.Order;
import com.example.coffeebar.entity.Position;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
public class PersonalDTO {

    Long idPersonal;

    String name;

    String phone;

    String email;

    String address;


    Position position;

    public PersonalDTO() {
    }

    public PersonalDTO(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public PersonalDTO(String name, String phone, String email, String address, Position position) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.position = position;
    }
}

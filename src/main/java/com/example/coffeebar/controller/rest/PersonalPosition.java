package com.example.coffeebar.controller.rest;

import com.example.coffeebar.entity.Personal;
import com.example.coffeebar.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class PersonalPosition {

    List<Personal> personalList;
    List<Position> positionList;

}

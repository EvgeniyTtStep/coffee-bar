package com.example.coffeebar.facade;


import com.example.coffeebar.dto.DrinkDTO;
import com.example.coffeebar.entity.Drink;
import org.springframework.stereotype.Component;

@Component
public class DrinkFacade {

    public DrinkDTO drinkToDrinkDTO(Drink drink) {
        DrinkDTO drinkDTO = new DrinkDTO();
        drinkDTO.setNameUa(drink.getNameUa());
        drinkDTO.setNameEn(drink.getNameEn());
        drinkDTO.setPrice(drink.getPrice());
        drinkDTO.setImage(drink.getImage());
        return drinkDTO;
    }

}

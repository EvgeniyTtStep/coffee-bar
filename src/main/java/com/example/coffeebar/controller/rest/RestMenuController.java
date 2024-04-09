package com.example.coffeebar.controller.rest;

import com.example.coffeebar.dto.DrinkDTO;
import com.example.coffeebar.dto.PersonalDTO;
import com.example.coffeebar.entity.Drink;
import com.example.coffeebar.entity.Personal;
import com.example.coffeebar.entity.Position;
import com.example.coffeebar.facade.DrinkFacade;
import com.example.coffeebar.facade.PersonalFacade;
import com.example.coffeebar.service.MenuService;
import com.example.coffeebar.service.PersonalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin()
public class RestMenuController {

    private final MenuService menuService;

    private final PersonalService personalService;

    private final DrinkFacade drinkFacade;

    private final PersonalFacade personalFacade;

    public RestMenuController(MenuService menuService, PersonalService personalService, DrinkFacade drinkFacade, PersonalFacade personalFacade) {
        this.menuService = menuService;
        this.personalService = personalService;
        this.drinkFacade = drinkFacade;
        this.personalFacade = personalFacade;
    }


    @GetMapping("/drinks/all")
    public ResponseEntity<List<DrinkDTO>> getAllDrinks() {
        List<Drink> allDrinks = menuService.getAllDrinks();
        List<DrinkDTO> drinkDTOList = new ArrayList<>();
        for (Drink drink : allDrinks) {
            drinkDTOList.add(drinkFacade.drinkToDrinkDTO(drink));
        }
        return new ResponseEntity<>(drinkDTOList, HttpStatus.OK);
    }

    @PostMapping("/drinks/add")
    public ResponseEntity<Drink> addDrink(@RequestBody Drink drink) {
        Drink restDrink = menuService.saveRestDrink(drink);
        return new ResponseEntity<>(restDrink, HttpStatus.OK);
    }


    @GetMapping("/personals/all")
    public ResponseEntity<Object> getAllPersonal() {
        List<Personal> personalList = personalService.getAllPersonal();
        List<Position> allPositions = personalService.getAllPositions();
        PersonalPosition personalPosition = new PersonalPosition(personalList, allPositions);
        return new ResponseEntity<>(personalPosition, HttpStatus.OK);
    }

    @GetMapping("/personal/dto/all")
    public ResponseEntity<Object> getAllPersonalDTO() {
        List<Personal> personalList = personalService.getAllPersonal();
        List<PersonalDTO> personalDTOList = new ArrayList<>();
        for (Personal personal : personalList) {
            personalDTOList.add(personalFacade.personalToPersonalDTO(personal));
        }
        return new ResponseEntity<>(personalDTOList, HttpStatus.OK);
    }
}

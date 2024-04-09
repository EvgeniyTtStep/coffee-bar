package com.example.coffeebar.service;

import com.example.coffeebar.entity.Desert;
import com.example.coffeebar.entity.Drink;
import com.example.coffeebar.repository.DesertRepository;
import com.example.coffeebar.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final DrinkRepository drinkRepository;

    private final DesertRepository desertRepository;

    @Autowired
    public MenuService(DrinkRepository drinkRepository, DesertRepository desertRepository) {
        this.drinkRepository = drinkRepository;
        this.desertRepository = desertRepository;
    }


    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public List<Desert> getAllDeserts() {
        return desertRepository.findAll();
    }

    public void addDrink(Drink drink) {
        drinkRepository.save(drink);
    }

    public void addDessert(Desert dessert) {
        desertRepository.save(dessert);
    }

    public void saveDrink(Drink drink) {
        if (drink != null) {
            drinkRepository.save(drink);
        }
    }

    public Drink saveRestDrink(Drink drink) {
        if (drink != null) {
            return drinkRepository.save(drink);
        }
        return null;
    }

    public void saveDesert(Desert desert) {
        if (desert != null) {
            desertRepository.save(desert);
        }
    }

    public Drink findByIdDrink(Long idDrink) {
        return drinkRepository.findDrinkByIdDrink(idDrink).orElse(new Drink());
    }

    public Desert findByIdDesert(Long idDesert) {
        return desertRepository.findById(idDesert).orElse(new Desert());
    }

    public void deleteByIdDrink(Long idDrink) {
        drinkRepository.deleteById(idDrink);
    }

    public void deleteByIdDesert(Long idDesert) {
        desertRepository.deleteById(idDesert);
    }

    public Drink getDrinkById(Long idDrink) {
        return drinkRepository.findById(idDrink).get();
    }
}

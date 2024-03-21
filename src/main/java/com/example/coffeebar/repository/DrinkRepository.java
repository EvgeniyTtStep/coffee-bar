package com.example.coffeebar.repository;

import com.example.coffeebar.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {

    Optional<Drink> findDrinkByIdDrink(Long idDrink);

}

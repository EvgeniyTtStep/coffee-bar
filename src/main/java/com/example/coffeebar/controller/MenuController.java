package com.example.coffeebar.controller;

import com.example.coffeebar.entity.Desert;
import com.example.coffeebar.entity.Drink;
import com.example.coffeebar.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class MenuController {

    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menu")
    public String getMenu(Model model) {
        model.addAttribute("drinks", menuService.getAllDrinks());
        model.addAttribute("deserts", menuService.getAllDeserts());
        return "menu";
    }

    @GetMapping("/drink/add")
    public String addDrink(Model model) {
        model.addAttribute("drink", new Drink());
        return "add-drink";
    }

    @PostMapping("/drink/add")
    public String addDrink(@ModelAttribute Drink drink) {
        menuService.saveDrink(drink);
        return "redirect:/menu";
    }

    @GetMapping("/desert/add")
    public String addDesert(Model model) {
        model.addAttribute("desert", new Desert());
        return "add-desert";
    }

    @PostMapping("/desert/add")
    public String addDesert(@ModelAttribute Desert desert) {
        menuService.saveDesert(desert);
        return "redirect:/menu";
    }

    @GetMapping("/drink/updateDrink/{id}")
    public String updateDrink(@PathVariable(name = "id") Long idDrink, Model model) {
        Drink drinkById = menuService.findByIdDrink(idDrink);
        model.addAttribute("drink", drinkById);
        return "add-drink";
    }

    @GetMapping("/desert/updateDesert/{id}")
    public String updateDesert(@PathVariable(name = "id") Long idDesert, Model model) {
        Desert desertById = menuService.findByIdDesert(idDesert);
        model.addAttribute("desert", desertById);
        return "add-desert";
    }

    @GetMapping("/drink/delete/{idDrink}")
    public String deleteDrink(@PathVariable Long idDrink) {
        menuService.deleteByIdDrink(idDrink);
        return "redirect:/menu";
    }

    @GetMapping("/desert/delete/{idDesert}")
    public String deleteDesert(@PathVariable Long idDesert) {
        menuService.deleteByIdDesert(idDesert);
        return "redirect:/menu";
    }

    @GetMapping("/drink/updatePrice/{idDrink}")
    public String updatePriceDrink(@PathVariable Long idDrink, Model model) {
        Drink drinkById = menuService.findByIdDrink(idDrink);
        model.addAttribute("drink", drinkById);
        return "updateDrink-price";
    }

    @PostMapping("/drink/updatePrice/{id}")
    public String updatePriceDrink(@PathVariable Long id, @RequestParam(name = "price") BigDecimal price) {
        Drink byIdDrink = menuService.findByIdDrink(id);
        byIdDrink.setPrice(price);
        menuService.saveDrink(byIdDrink);
        return "redirect:/menu";
    }


    @GetMapping("/desert/updatePrice/{idDesert}")
    public String updatePriceDesert(@PathVariable Long idDesert, Model model) {
        Desert desertById = menuService.findByIdDesert(idDesert);
        model.addAttribute("desert", desertById);
        return "updateDesert-price";
    }

}

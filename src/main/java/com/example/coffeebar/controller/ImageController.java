package com.example.coffeebar.controller;

import com.example.coffeebar.entity.Image;
import com.example.coffeebar.service.ImageService;
import com.example.coffeebar.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {


    private final ImageService imageService;

    private final MenuService menuService;


    @Autowired
    public ImageController(ImageService imageService, MenuService menuService) {
        this.imageService = imageService;
        this.menuService = menuService;
    }

    @GetMapping("/image/drink/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getDrinkImage(@PathVariable Long id) {
        Image image = menuService.findByIdDrink(id).getImage();
        if (image == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(image.getContent());
    }






}

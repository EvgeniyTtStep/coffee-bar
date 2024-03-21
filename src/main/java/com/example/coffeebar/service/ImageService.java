package com.example.coffeebar.service;

import com.example.coffeebar.entity.Drink;
import com.example.coffeebar.entity.Image;
import com.example.coffeebar.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class ImageService {

    private final ImageRepository imageRepository;


    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void save(Image image) {
        if (image != null) {
            imageRepository.save(image);
        }
    }


    public Image getImageToDrink(Drink byIdDrink) {
        return null;
    }
}

package com.example.coffeebar.facade;

import com.example.coffeebar.dto.PersonalDTO;
import com.example.coffeebar.entity.Personal;
import org.springframework.stereotype.Component;

@Component
public class PersonalFacade {

    public PersonalDTO personalToPersonalDTO(Personal personal) {
        PersonalDTO personalDTO = new PersonalDTO();
        personalDTO.setName(personal.getName());
        personalDTO.setPhone(personal.getPhone());
        personalDTO.setAddress(personal.getAddress());
        personalDTO.setEmail(personal.getEmail());
        personalDTO.setPosition(personal.getPosition());
        return personalDTO;
    }

}

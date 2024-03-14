package com.example.coffeebar.service;


import com.example.coffeebar.entity.Graphic;
import com.example.coffeebar.entity.Personal;
import com.example.coffeebar.entity.Position;
import com.example.coffeebar.repository.GraphicRepository;
import com.example.coffeebar.repository.PersonalRepository;
import com.example.coffeebar.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PersonalService {

    private PersonalRepository personalRepository;


    private PositionRepository positionRepository;


    private GraphicRepository graphicRepository;

    @Autowired
    public void setGraphicRepository(GraphicRepository graphicRepository) {
        this.graphicRepository = graphicRepository;
    }

    @Autowired
    public void setPersonalRepository(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    @Autowired
    public void setPositionRepository(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }


    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    public Position getPositionById(Long idPosition) {
        return positionRepository.findById(idPosition).get();
    }


    public void save(Personal personal, Long idPosition) {
        personal.setPosition(getPositionById(idPosition));
        personalRepository.save(personal);
    }


    public Personal getPersonalByPhone(String phone) {
        return personalRepository.getPersonalByPhone(phone);
    }

    public List<Personal> getAllPersonal() {
        return personalRepository.findAll();
    }

    public List<Graphic> getAllGraphics() {
        return graphicRepository.findAll();
    }

    public List<Personal> getPersonalByPositions(List<String> positionList) {

        List<Personal> personalList = new ArrayList<>();

        for (String position : positionList) {
            personalList.addAll(personalRepository.findPersonalByPosition(position));
        }
        personalList.sort(Comparator.comparing(Personal::getIdPersonal));

        return personalList;
    }

    public Personal findById(Long idPersonal) {
        Optional<Personal> byId = personalRepository.findById(idPersonal);
        return byId.get();
    }
}

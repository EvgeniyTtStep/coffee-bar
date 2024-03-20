package com.example.coffeebar.controller;


import com.example.coffeebar.entity.*;
import com.example.coffeebar.repository.RoleRepository;
import com.example.coffeebar.service.PersonalService;
import com.example.coffeebar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.RoleNotFoundException;
import java.util.*;

@Controller
public class PersonalController {


    private PersonalService personalService;

    private RoleRepository roleRepository;

    private UserService userService;


    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPersonalService(PersonalService personalService) {
        this.personalService = personalService;
    }


    @GetMapping("/personal/all")
    public String getAllPersonal(Model model) {
        model.addAttribute("personals", personalService.getAllPersonal());
        model.addAttribute("positions", personalService.getAllPositions());
        return "personals";
    }

    @GetMapping("/personal/add")
    public String add(Model model) {
        model.addAttribute("personal", new Personal());
        model.addAttribute("positions", personalService.getAllPositions());
        return "admin/add-personal";
    }

//    @PostMapping("/personal/add")
//    public String add(@RequestParam String name,
//                      @RequestParam String phone,
//                      @RequestParam String address,
//                      @RequestParam(name = "id_position") Long idPosition) {
//
//        Personal personal = new Personal(name, phone, address);
//
//        System.out.println("personal Name = " + personal.getName());
//        System.out.println("personal.getPhone() = " + personal.getPhone());
//        Position position = personalService.getPositionById(idPosition);
//        System.out.println("position Name = " + position.getName());
//        personalService.save(personal, idPosition);
//
//        return "index";
//    }

    @PostMapping("/personal/add")
    public String add(@ModelAttribute Personal personal,
                      @RequestParam(name = "username") String username,
                      @RequestParam(name = "email") String email,
                      @RequestParam(name = "password") String password,
                      @RequestParam(name = "id_position") Long idPosition,
                      Model model) throws RoleNotFoundException {

            User userByEmail = userService.findUserByEmail(email);
        if (userByEmail != null) {
            model.addAttribute("err", "User vs email " + email + " exist in database");
            model.addAttribute("personal", personal);
            model.addAttribute("positions", personalService.getAllPositions());
            return "admin/add-personal";
        } else {
            Personal personalByPhone = personalService.getPersonalByPhone(personal.getPhone());
            if (personalByPhone != null) {
                model.addAttribute("err", "Користувач з таким номером вже існує");
                model.addAttribute("personal", personal);
                model.addAttribute("positions", personalService.getAllPositions());
                return "admin/add-personal";
            } else {
                User newUser = new User(personal.getName(), username, email, password);
                Role role = roleRepository.findRoleByName(ERole.ROLE_PERSONAL)
                        .orElseThrow(() -> new RoleNotFoundException("Role not found"));
                newUser.setRoles(new HashSet<>(List.of(role)));
                personalService.save(personal, idPosition);
                userService.adminSave(newUser);
            }
        }
        return "index";
    }


    @GetMapping("/personal/graphic/add")
    public String addGraphics(Model model) {
        model.addAttribute("personals", personalService.getAllPersonal());
        model.addAttribute("personals", personalService.getAllGraphics());
        return "personal_graphic";
    }


    @PostMapping("/personal/positions")
    public String getPersonalByPositions(@RequestParam(name = "selectedPosition", required = false) List<String> positionList,
                                         @RequestParam(name = "sort", required = false) Integer num, Model model) {
        if (positionList != null) {
            List<Personal> personalList = personalService.getPersonalByPositions(positionList);
            List<Position> positions = new ArrayList<>();

            for (Personal personal : personalList) {
                positions.add(personal.getPosition());
            }

            if (num != null) {
                switch (num) {
                    case 1 -> personalList.sort(Comparator.comparing(Personal::getName));
                    case 2 -> personalList.sort(Comparator.comparing(Personal::getPhone));
                }
            }
            model.addAttribute("personals", personalList);
            model.addAttribute("selectedPosition", positions);
            model.addAttribute("selected", num);
            model.addAttribute("positions", personalService.getAllPositions());

            return "personals";

        } else if (num != 0) {
            List<Personal> personalList = personalService.getAllPersonal();
            switch (num) {
                case 1 -> personalList.sort(Comparator.comparing(Personal::getName));
                case 2 -> personalList.sort(Comparator.comparing(Personal::getPhone));
            }
            model.addAttribute("personals", personalList);
            model.addAttribute("selected", num);
            model.addAttribute("positions", personalService.getAllPositions());

            return "personals";
        } else {
            return "redirect:/personal/all";
        }
    }

}

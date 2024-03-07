package com.example.coffeebar.controller;


import com.example.coffeebar.entity.Client;
import com.example.coffeebar.repository.ClientRepository;
import com.example.coffeebar.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {

    private ClientService clientService;


    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/client/all")
    public String getAll(Model model) {
        model.addAttribute("clients", clientService.getAll());
        return "clients";
    }

    @GetMapping("/client/add")
    public String add(Model model) {
        model.addAttribute("client", new Client());
        return "add-client";
    }

    @PostMapping("/client/add")
    public String add(@ModelAttribute Client client) {
        clientService.save(client);
        return "redirect:/client/all";
    }

    @GetMapping("/client/update/{id}")
    public String update(@PathVariable(name = "id") Long idClient, Model model) {
        Client clientById = clientService.findById(idClient);
        model.addAttribute("client", clientById);
        return "add-client";
    }
}

package com.example.coffeebar.service;

import com.example.coffeebar.entity.Client;
import com.example.coffeebar.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void save(Client client) {
        if (client != null) {
            clientRepository.save(client);
        }
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }
    public Client findById(Long idClient) {
        return clientRepository.findById(idClient).orElse(new Client());
    }
}

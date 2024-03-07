package com.example.coffeebar.repository;

import com.example.coffeebar.entity.Client;
import com.example.coffeebar.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findClientByOrderSet(Set<Order> orderSet);

    List<Client> findClientsByAddress(String address);

}

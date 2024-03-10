package com.example.coffeebar.repository;

import com.example.coffeebar.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
    Personal getPersonalByPhone(String phone);

    Personal findPersonalByPhone(String phone);

}

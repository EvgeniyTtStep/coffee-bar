package com.example.coffeebar.repository;

import com.example.coffeebar.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
    Personal getPersonalByPhone(String phone);

    Personal findPersonalByPhone(String phone);

    @Query(value = "SELECT p FROM Personal p INNER JOIN p.position pp where pp.name = ?1")
    List<Personal> findPersonalByPosition(String position);
}

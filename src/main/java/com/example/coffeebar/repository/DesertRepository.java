package com.example.coffeebar.repository;


import com.example.coffeebar.entity.Desert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesertRepository extends JpaRepository<Desert, Long> {
}

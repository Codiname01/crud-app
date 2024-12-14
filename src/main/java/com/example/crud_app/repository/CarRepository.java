package com.example.crud_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crud_app.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
}

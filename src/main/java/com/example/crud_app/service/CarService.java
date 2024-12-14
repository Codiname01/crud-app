package com.example.crud_app.service;

import com.example.crud_app.model.Car;

import com.example.crud_app.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> listarTodos() {
        return carRepository.findAll();
    }

    public Optional<Car> obtenerPorId(int id) {
        return carRepository.findById(id);
    }

    public Car guardar(Car car) {
        return carRepository.save(car);
    }

    public void eliminar(int id) {
        carRepository.deleteById(id);
    }
}

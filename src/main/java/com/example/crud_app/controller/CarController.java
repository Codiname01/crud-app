package com.example.crud_app.controller;

import com.example.crud_app.model.Car;
import com.example.crud_app.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    // Listar todos los registros
    @GetMapping("/all")
    public List<Car> listarTodos() {
        return carService.listarTodos();
    }

    // Obtener un registro por ID
    @GetMapping("/detail/{id}")
    public ResponseEntity<Car> obtenerPorId(@PathVariable int id) {
        return carService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo registro
    @PostMapping("/create")
    public Car crear(@RequestBody Car car) {
        return carService.guardar(car);
    }

    // Actualizar un registro existente
    @PutMapping("/update/{id}")
    public ResponseEntity<Car> actualizar(@PathVariable int id, @RequestBody Car car) {
        return carService.obtenerPorId(id)
                .map(existente -> {
                    car.setId(existente.getId()); // Asegurar que el ID no cambie
                    return ResponseEntity.ok(carService.guardar(car));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un registro por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (carService.obtenerPorId(id).isPresent()) {
            carService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

package com.henrique3g.controle_veiculos.vehicle;

import javax.transaction.Transactional;
import javax.validation.Valid;
import com.henrique3g.controle_veiculos.user.User;
import com.henrique3g.controle_veiculos.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CreateVehicleController {
        @Autowired
        private UserRepository userRepository;

        @Autowired
        private VehicleRepository vehicleRepository;

        @Autowired
        private GetVehiclePrice getVehiclePrice;

        @PostMapping("/vehicles")
        @Transactional
        public ResponseEntity<?> createVehicle(@RequestBody @Valid CreateVehicleDto vehicleData) {
                User user = userRepository.findByCpf(vehicleData.getUserCpf())
                                .orElseThrow(() -> toBadRequest("User not found"));
                Double price = getVehiclePrice
                                .execute(vehicleData.getBrand(), vehicleData.getModel(),
                                                vehicleData.getYear())
                                .orElseThrow(error -> toBadRequest(error));

                System.out.println(price);
                Vehicle vehicle = vehicleData.toVehicle(price, user);
                vehicleRepository.save(vehicle);
                return new ResponseEntity<>(HttpStatus.CREATED);
        }

        private ResponseStatusException toBadRequest(String message) {
                return new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
}

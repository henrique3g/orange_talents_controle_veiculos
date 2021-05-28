package com.henrique3g.controle_veiculos.vehicle;

import java.util.List;
import javax.validation.Valid;
import com.henrique3g.controle_veiculos.user.User;
import com.henrique3g.controle_veiculos.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        public ResponseEntity<?> createVehicle(@RequestBody @Valid CreateVehicleDto vehicleData)
                        throws Exception {
                User user = userRepository.findById(vehicleData.getUserId())
                                .orElseThrow(() -> toResponse("User not found"));
                Double price = getVehiclePrice
                                .execute(vehicleData.getBrand(), vehicleData.getModel(),
                                                vehicleData.getYear())
                                .orElseThrow(() -> toResponse("Vehicle not found"));
                System.out.println(price);
                Vehicle vehicle = vehicleData.toVehicle(price);
                vehicleRepository.save(vehicle);
                return new ResponseEntity<>(HttpStatus.CREATED);
        }

        private ResponseStatusException toResponse(String message) {
                return new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }

        // Metodo para testar
        @GetMapping("/vehicles")
        public List<Vehicle> getAll() {
                return vehicleRepository.findAll();
        }

}

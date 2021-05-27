package com.henrique3g.controle_veiculos;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUserController {
    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserDto userData) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

package com.henrique3g.controle_veiculos;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CreateUserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserDto userData) {
        boolean hasUserWithSameCpf = userRepository.findByCpf(userData.getCpf()).isPresent();
        boolean hasUserWithSameEmail = userRepository.findByEmail(userData.getEmail()).isPresent();
        if (hasUserWithSameCpf || hasUserWithSameEmail) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    getErrorMessage(hasUserWithSameCpf, hasUserWithSameEmail));
        }
        userRepository.save(userData.toUser());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private String getErrorMessage(boolean cpf, boolean email) {
        String message = "User already exists with the";
        if (cpf && !email) {
            return message.concat(" cpf");
        }
        if (!cpf && email) {
            return message.concat(" email");
        }
        return  message.concat(" cpf and the email");
    }
}

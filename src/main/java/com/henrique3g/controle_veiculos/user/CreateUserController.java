package com.henrique3g.controle_veiculos.user;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    @Transactional
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserDto userData) {
        boolean hasUserWithSameCpf = userRepository.findByCpf(userData.getCpf()).isPresent();
        boolean hasUserWithSameEmail = userRepository.findByEmail(userData.getEmail()).isPresent();
        if (hasUserWithSameCpf || hasUserWithSameEmail) {
            return ResponseEntity.badRequest()
                    .body(new UserAlreadyExists(hasUserWithSameCpf, hasUserWithSameEmail));
        }
        userRepository.save(userData.toUser());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

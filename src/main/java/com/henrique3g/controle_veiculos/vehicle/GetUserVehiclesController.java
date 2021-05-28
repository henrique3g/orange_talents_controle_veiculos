package com.henrique3g.controle_veiculos.vehicle;

import com.henrique3g.controle_veiculos.user.User;
import com.henrique3g.controle_veiculos.user.UserRepository;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GetUserVehiclesController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/vehicles/{userCpf}")
    public UserVehiclesView getUserVehicles(@PathVariable @CPF String userCpf) {
        User user = userRepository.findByCpf(userCpf)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "not found user with the cpf"));

        return new UserVehiclesView(user);
    }
}

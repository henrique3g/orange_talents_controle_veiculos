package com.henrique3g.controle_veiculos.user;

import java.util.ArrayList;
import java.util.List;

public class UserAlreadyExists {
    private List<String> errors;

    UserAlreadyExists(boolean existsCpf, boolean existsEmail) {
        errors = new ArrayList<>();
        if (existsCpf) {
            errors.add("Cpf already exists");
        }
        if (existsEmail) {
            errors.add("Email already exists");
        }
    }

    public String getMessage() {
        return "User already exists";
    }

    public List<String> getErrors() {
        return errors;
    }
}

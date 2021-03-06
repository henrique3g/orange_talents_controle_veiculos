package com.henrique3g.controle_veiculos.user;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class CreateUserDto {
    @NotBlank
    private String name;

    @Email
    private String email;

    @CPF
    private String cpf;

    @Past
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate birthdate;

    public User toUser() {
        return new User(getName(), getEmail(), getCpf(), getBirthdate());
    }

    // getters and setters
    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }
}

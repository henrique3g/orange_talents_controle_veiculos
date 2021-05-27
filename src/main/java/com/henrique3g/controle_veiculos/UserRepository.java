package com.henrique3g.controle_veiculos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByCpf(String cpf);

    public Optional<User> findByEmail(String email);
}

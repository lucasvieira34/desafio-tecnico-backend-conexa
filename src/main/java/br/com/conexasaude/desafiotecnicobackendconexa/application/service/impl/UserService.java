package br.com.conexasaude.desafiotecnicobackendconexa.application.service.impl;

import java.util.List;
import java.util.Optional;

import br.com.conexasaude.desafiotecnicobackendconexa.domain.entity.User;
import br.com.conexasaude.desafiotecnicobackendconexa.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

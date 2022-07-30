package com.poli.users.persistence.repository;

import com.poli.users.persistence.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void when_findByUser()
    {
        User user = User.builder()
                .name("prueba")
                .lastName("Apellido Prueba")
                .build();

        User userResponseDB = userRepository.save(user);

        Optional<User> userRes = userRepository.findById(userResponseDB.getId());
        userRes.ifPresent(value -> Assertions.assertEquals(user.getId(), value.getId()));
    }

}
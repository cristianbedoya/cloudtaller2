package com.poli.users.service;

import com.poli.users.clientfeign.BookingsClient;
import com.poli.users.persistence.entity.User;
import com.poli.users.persistence.repository.UserRepository;
import com.poli.users.service.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookingsClient bookingsClient;

    @BeforeEach
    public void begin() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository,bookingsClient);

        User movie = User.builder()
                .id(4L)
                .name("Cristian")
                .lastName("Bedoya").build();
        Mockito.when(userRepository.findById(4L))
                .thenReturn(Optional.of(movie));
    }

    @Test
    public void findById() {
        UserDTO movieDTO = userService.findById(4L);
        Assertions.assertEquals("Cristian",movieDTO.getName());
    }
}
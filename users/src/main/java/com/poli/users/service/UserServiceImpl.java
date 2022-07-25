package com.poli.users.service;

import com.poli.users.persistence.entity.User;
import com.poli.users.persistence.repository.UserRepository;
import com.poli.users.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserDTO save(UserDTO userDto) {

        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDto, User.class);
        user = userRepository.save(user);

        return mapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO findById(Long id) {

        ModelMapper mapper = new ModelMapper();

        Optional<User> user = userRepository.findById(id);

        return user.map(value -> mapper.map(value, UserDTO.class)).orElse(null);

    }

    @Override
    public void Delete(Long id) {

        Optional<User> user = userRepository.findById(id);

        user.ifPresent(userRepository::delete);
    }
}

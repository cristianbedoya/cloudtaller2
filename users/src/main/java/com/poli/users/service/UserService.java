package com.poli.users.service;

import com.poli.users.service.dto.UserDTO;

public interface UserService {

    UserDTO save(UserDTO user);

    UserDTO findById(Long id);

    boolean Delete(Long id);
}

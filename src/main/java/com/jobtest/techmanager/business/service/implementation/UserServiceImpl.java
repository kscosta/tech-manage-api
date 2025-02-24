package com.jobtest.techmanager.business.service.implementation;

import com.jobtest.techmanager.business.mapper.UserMapper;
import com.jobtest.techmanager.business.service.UserService;
import com.jobtest.techmanager.controller.representation.request.UserPostRequest;
import com.jobtest.techmanager.controller.representation.response.UserResponse;
import com.jobtest.techmanager.infrastructure.entity.UserEntity;
import com.jobtest.techmanager.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Classe de implementação de Serviço de Usuários
 *
 * @version 1.0
 * @since Java 21
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {

        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse createUser(UserPostRequest userPostRequest) {

        UserEntity newUser = userMapper.userPostRequestToUserEntity(userPostRequest);
        return userMapper.userEntityToUserResponse(userRepository.save(newUser));
    }
}

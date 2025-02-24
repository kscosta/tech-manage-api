package com.jobtest.techmanager.business.service;

import com.jobtest.techmanager.controller.representation.request.UserPostRequest;
import com.jobtest.techmanager.controller.representation.request.UserPutRequest;
import com.jobtest.techmanager.controller.representation.response.UserResponse;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.rmi.NotBoundException;

/**
 * Interface de Serviço de Usuários
 *
 * @version 1.0
 * @since Java 21
 */

public interface UserService {

    UserResponse createUser(UserPostRequest userPostRequest);

    UserResponse updateUser(UserPutRequest userPutRequest);

    UserResponse findUserById(Long id) throws NotBoundException, ChangeSetPersister.NotFoundException;

}

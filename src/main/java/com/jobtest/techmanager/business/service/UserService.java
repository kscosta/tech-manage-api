package com.jobtest.techmanager.business.service;

import com.jobtest.techmanager.controller.representation.request.UserPostRequest;
import com.jobtest.techmanager.controller.representation.request.UserPutRequest;
import com.jobtest.techmanager.controller.representation.response.UserResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    UserResponse findUserById(Long id);

    void deleteUser(Long id);

    Page<UserResponse> findAllUsers(Pageable pageable);

}

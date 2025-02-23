package com.jobtest.techmanager.business.service;

import com.jobtest.techmanager.controller.representation.request.UserPostRequest;
import com.jobtest.techmanager.controller.representation.response.UserResponse;

/**
 * Interface de Serviço de Usuários
 *
 * @version 1.0
 * @since Java 21
 */

public interface UserService {

    UserResponse createUser(UserPostRequest userPostRequest);

}

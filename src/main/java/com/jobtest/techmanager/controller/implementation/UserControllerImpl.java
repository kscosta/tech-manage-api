package com.jobtest.techmanager.controller.implementation;

import com.jobtest.techmanager.business.service.UserService;
import com.jobtest.techmanager.controller.UserController;
import com.jobtest.techmanager.controller.representation.request.UserPostRequest;
import com.jobtest.techmanager.controller.representation.request.UserPutRequest;
import com.jobtest.techmanager.controller.representation.response.DefaultApiResponse;
import com.jobtest.techmanager.controller.representation.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Implementação do recurso de Usuários
 *
 * @version 1.0
 * @since Java 21
 */

@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<DefaultApiResponse<UserResponse>> createUser(UserPostRequest userPostRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(new DefaultApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.CREATED.value(),
                        "Usuário criado com sucesso!",
                        userService.createUser(userPostRequest)
                )
        );
    }

    @Override
    public ResponseEntity<DefaultApiResponse<UserResponse>> updateUser(Long id, UserPutRequest userPutRequest) {

        return ResponseEntity.status(HttpStatus.OK).body(new DefaultApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Usuário atualizado com sucesso!",
                        userService.updateUser(id, userPutRequest)
                )
        );
    }

    @Override
    public ResponseEntity<DefaultApiResponse<String>> deleteUser(Long id) {

        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.OK).body(new DefaultApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Usuário excluído com sucesso!",
                        null
                )
        );
    }

    @Override
    public ResponseEntity<DefaultApiResponse<UserResponse>> findUserById(Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(new DefaultApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Usuário encontrado com sucesso!",
                        userService.findUserById(id)
                )
        );
    }

    @Override
    public ResponseEntity<DefaultApiResponse<Page<UserResponse>>> findAllUsers(Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(new DefaultApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Solicitação executada com sucesso!",
                        userService.findAllUsers(pageable)
                )
        );
    }
}

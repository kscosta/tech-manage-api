package com.jobtest.techmanager.controller;

import com.jobtest.techmanager.controller.representation.request.UserPostRequest;
import com.jobtest.techmanager.controller.representation.response.DefaultApiResponse;
import com.jobtest.techmanager.controller.representation.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Interface do recurso de Usuários
 *
 * @version 1.0
 * @since Java 21
 */

@RequestMapping("/users")
public interface UserController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cria novo usuário",
            description = "Cria novo registro de usuário",
            tags = {"Usuários"})
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso!",
            content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Solicitação inválida!",
            content = @Content(schema = @Schema()))
    @ApiResponse(responseCode = "404", description = "Solicitação não encontrada!",
            content = @Content(schema = @Schema()))
    @ApiResponse(responseCode = "500", description = "Erro ao execucr a solicitação!",
            content = @Content(schema = @Schema()))
    ResponseEntity<DefaultApiResponse<UserResponse>> createUser(@RequestBody @Valid UserPostRequest userPostRequest);
}
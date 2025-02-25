package com.jobtest.techmanager.controller;

import com.jobtest.techmanager.controller.representation.request.UserPostRequest;
import com.jobtest.techmanager.controller.representation.request.UserPutRequest;
import com.jobtest.techmanager.controller.representation.response.DefaultApiResponse;
import com.jobtest.techmanager.controller.representation.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    @ApiResponse(responseCode = "500", description = "Erro ao executar a solicitação!",
            content = @Content(schema = @Schema()))
    ResponseEntity<DefaultApiResponse<UserResponse>> createUser(@RequestBody @Valid UserPostRequest userPostRequest);

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza usuário",
            description = "Atualiza registro de usuário",
            tags = {"Usuários"})
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso!",
            content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Solicitação inválida!",
            content = @Content(schema = @Schema()))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado!",
            content = @Content(schema = @Schema()))
    @ApiResponse(responseCode = "500", description = "Erro ao executar a solicitação!",
            content = @Content(schema = @Schema()))
    ResponseEntity<DefaultApiResponse<UserResponse>> updateUser(@PathVariable Long id,
                                                                @RequestBody @Valid UserPutRequest userPutRequest);

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Exclui usuário",
            description = "Exclui registro de usuário",
            tags = {"Usuários"})
    @ApiResponse(responseCode = "200", description = "Usuário excluído com sucesso!",
            content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado!",
            content = @Content(schema = @Schema()))
    @ApiResponse(responseCode = "500", description = "Erro ao executar a solicitação!",
            content = @Content(schema = @Schema()))
    ResponseEntity<DefaultApiResponse<String>> deleteUser(@PathVariable Long id);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca usuário por id",
            description = "Busca registro de usuário por id",
            tags = {"Usuários"})
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso!",
            content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado!",
            content = @Content(schema = @Schema()))
    @ApiResponse(responseCode = "500", description = "Erro ao executar a solicitação!",
            content = @Content(schema = @Schema()))
    ResponseEntity<DefaultApiResponse<UserResponse>> findUserById(@PathVariable Long id);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca todos os usuários",
            description = "Busca todos os registros de usuários",
            tags = {"Usuários"})
    @ApiResponse(responseCode = "200", description = "Solicitação executada com sucesso!",
            content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "500", description = "Erro ao executar a solicitação!",
            content = @Content(schema = @Schema()))
    ResponseEntity<DefaultApiResponse<Page<UserResponse>>> findAllUsers(Pageable pageable);
}
package com.jobtest.techmanager.controller.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobtest.techmanager.TestObjectUtil;
import com.jobtest.techmanager.business.enums.UserType;
import com.jobtest.techmanager.controller.representation.request.UserPostRequest;
import com.jobtest.techmanager.controller.representation.response.DefaultApiResponse;
import com.jobtest.techmanager.controller.representation.response.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@DisplayName("Teste de integração dos recursos de usuários")
public class UserControllerITTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Método createUser deve retornar status Created quando executado com sucesso")
    void createUserShouldReturnCreatedStatusWhenSuccessful() throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserPostRequest> request = new HttpEntity<>(TestObjectUtil.userPostRequest(), headers);

        ResponseEntity<DefaultApiResponse<UserResponse>> response = testRestTemplate.exchange(
                "/users",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<DefaultApiResponse<UserResponse>>() {
                });

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().data());
        assertEquals("Fulano da Silva", response.getBody().data().fullName());
        assertEquals("fulano@test.com", response.getBody().data().email());
        assertEquals("+55 11 99999-5544", response.getBody().data().phone());
        assertEquals("1995-10-25", response.getBody().data().birthDate().toString());
        assertEquals("ADMIN", response.getBody().data().userType().getValue());
    }

    @Test
    @DisplayName("Método createUser deve retornar status BadRequest quando parâmetro informado for inválido")
    void createUserShouldReturnBadRequestStatusWhenInvalidParameters() {

        UserPostRequest invalidRequest = new UserPostRequest("Fulano da Silva", "invalid",
                "+55 11 99999-5544",
                LocalDate.of(1995, 10, 25), UserType.ADMIN);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserPostRequest> request = new HttpEntity<>(invalidRequest, headers);

        ResponseEntity<DefaultApiResponse<UserResponse>> response = testRestTemplate.exchange(
                "/users",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<DefaultApiResponse<UserResponse>>() {
                });

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, Objects.requireNonNull(response.getBody()).status());
        assertEquals("Email informado é inválido!", response.getBody().message());
    }
}

package com.jobtest.techmanager.controller.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jobtest.techmanager.business.enums.UserType;
import com.jobtest.techmanager.business.service.UserService;
import com.jobtest.techmanager.controller.representation.request.UserPostRequest;
import com.jobtest.techmanager.controller.representation.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Teste de unidade da classe UserControllerImpl")
@ExtendWith(MockitoExtension.class)
class UserControllerImplTest {

    @InjectMocks
    private UserControllerImpl userControllerImplMock;

    @Mock
    private UserService userServiceMock;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private UserResponse userResponseMock;
    private UserPostRequest userPostRequestMock;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(userControllerImplMock).build();
        objectMapper.registerModule(new JavaTimeModule());

        userResponseMock = new UserResponse(1L, "Fulano da Silva", "fulano@test.com",
                "+55 11 99999-5544", LocalDate.of(1995, 10, 25), UserType.ADMIN);

        userPostRequestMock = new UserPostRequest("Fulano da Silva", "fulano@test.com",
                "+55 11 99999-5544",
                LocalDate.of(1995, 10, 25), UserType.ADMIN);

        lenient().when(userServiceMock.createUser(any())).thenReturn(userResponseMock);
    }

    @DisplayName("Metodo createUser deve retornar um UserResponse quando for executado com sucesso")
    @Test
    void createUserShouldReturnUserResponseWhenSuccess() throws Exception {

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userPostRequestMock)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("Usuário criado com sucesso!"))
                .andExpect(jsonPath("$.data.id").value(userResponseMock.id()))
                .andExpect(jsonPath("$.data.fullName").value(userResponseMock.fullName()))
                .andExpect(jsonPath("$.data.email").value(userResponseMock.email()))
                .andExpect(jsonPath("$.data.phone").value(userResponseMock.phone()))
                .andExpect(jsonPath("$.data.birthDate").exists())
                .andExpect(jsonPath("$.data.userType").value(userResponseMock.userType().getValue()))
        ;

        verify(userServiceMock, times(1)).createUser(userPostRequestMock);
    }

    @DisplayName("Metodo createUser deve retornar BadRequest quando parametros inválidos")
    @Test
    void createUserShouldReturnBadRequestWhenInvalidParameters() throws Exception {

        UserPostRequest invalidUserPostRequestMock = new UserPostRequest("Fulano da Silva", "invalid",
                "+55 11 99999-5544",
                LocalDate.of(1995, 10, 25), UserType.ADMIN);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(invalidUserPostRequestMock)))
                .andExpect(status().isBadRequest());

        verify(userServiceMock, times(0)).createUser(userPostRequestMock);
    }

}
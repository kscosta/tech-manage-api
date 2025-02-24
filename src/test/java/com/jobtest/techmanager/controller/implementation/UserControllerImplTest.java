package com.jobtest.techmanager.controller.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jobtest.techmanager.TestObjectUtil;
import com.jobtest.techmanager.business.enums.UserType;
import com.jobtest.techmanager.business.service.UserService;
import com.jobtest.techmanager.controller.representation.request.UserPostRequest;
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

@DisplayName("Teste do Recurso de Usuários")
@ExtendWith(MockitoExtension.class)
class UserControllerImplTest {

    @InjectMocks
    private UserControllerImpl userControllerImplMock;

    @Mock
    private UserService userServiceMock;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(userControllerImplMock).build();
        objectMapper.registerModule(new JavaTimeModule());

        lenient().when(userServiceMock.createUser(any())).thenReturn(TestObjectUtil.userResponse());
    }

    @DisplayName("Metodo createUser deve retornar um UserResponse quando for executado com sucesso")
    @Test
    void createUserShouldReturnUserResponseWhenSuccess() throws Exception {

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(TestObjectUtil.userPostRequest())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("Usuário criado com sucesso!"))
                .andExpect(jsonPath("$.data.id").value(TestObjectUtil.userResponse().id()))
                .andExpect(jsonPath("$.data.fullName").value(TestObjectUtil.userResponse().fullName()))
                .andExpect(jsonPath("$.data.email").value(TestObjectUtil.userResponse().email()))
                .andExpect(jsonPath("$.data.phone").value(TestObjectUtil.userResponse().phone()))
                .andExpect(jsonPath("$.data.birthDate").exists())
                .andExpect(jsonPath("$.data.userType").value(TestObjectUtil.userResponse().userType().getValue()))
        ;

        verify(userServiceMock, times(1)).createUser(TestObjectUtil.userPostRequest());
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

        verify(userServiceMock, times(0)).createUser(TestObjectUtil.userPostRequest());
    }

}
package com.jobtest.techmanager.controller.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jobtest.techmanager.TestObjectUtil;
import com.jobtest.techmanager.business.enums.UserType;
import com.jobtest.techmanager.business.service.UserService;
import com.jobtest.techmanager.controller.representation.request.UserPostRequest;
import com.jobtest.techmanager.controller.representation.request.UserPutRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    }

    @DisplayName("Metodo createUser deve retornar um UserResponse quando for executado com sucesso")
    @Test
    void createUserShouldReturnUserResponseWhenSuccess() throws Exception {

        when(userServiceMock.createUser(any())).thenReturn(TestObjectUtil.userResponse());
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

    @DisplayName("Metodo updateUser deve retornar um UserResponse quando for executado com sucesso")
    @Test
    void updateUserShouldReturnUserResponseWhenSuccess() throws Exception {

        when(userServiceMock.updateUser(any())).thenReturn(TestObjectUtil.userResponseUpdated());
        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(TestObjectUtil.userPutRequest())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Usuário atualizado com sucesso!"))
                .andExpect(jsonPath("$.data.id").value(TestObjectUtil.userResponseUpdated().id()))
                .andExpect(jsonPath("$.data.fullName").value(TestObjectUtil.userResponseUpdated().fullName()))
                .andExpect(jsonPath("$.data.email").value(TestObjectUtil.userResponseUpdated().email()))
                .andExpect(jsonPath("$.data.phone").value(TestObjectUtil.userResponseUpdated().phone()))
                .andExpect(jsonPath("$.data.birthDate").exists())
                .andExpect(jsonPath("$.data.userType").value(TestObjectUtil.userResponseUpdated().userType().getValue()))
        ;

        verify(userServiceMock, times(1)).updateUser(TestObjectUtil.userPutRequest());
    }

    @DisplayName("Metodo updateUser deve retornar BadRequest quando parametros inválidos")
    @Test
    void updateUserShouldReturnBadRequestWhenInvalidParameters() throws Exception {

        UserPutRequest invalidUserPutRequestMock = new UserPutRequest(1L, "Fulano da Silva", "invalid",
                "+55 11 99999-5544",
                LocalDate.of(1995, 10, 25), UserType.ADMIN);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(invalidUserPutRequestMock)))
                .andExpect(status().isBadRequest());

        verify(userServiceMock, times(0)).updateUser(TestObjectUtil.userPutRequest());
    }

    @DisplayName("Metodo deleteUser deve retornar Status OK quando for executado com sucesso")
    @Test
    void deleteUserShouldReturnStatusOKWhenSuccess() throws Exception {

        doNothing().when(userServiceMock).deleteUser(any());

        mockMvc.perform(delete("/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
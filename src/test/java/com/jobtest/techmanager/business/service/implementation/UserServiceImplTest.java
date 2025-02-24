package com.jobtest.techmanager.business.service.implementation;

import com.jobtest.techmanager.TestObjectUtil;
import com.jobtest.techmanager.business.mapper.UserMapper;
import com.jobtest.techmanager.controller.representation.response.UserResponse;
import com.jobtest.techmanager.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Teste de unidade da classe UserControllerImpl")
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @InjectMocks
    private UserServiceImpl userServiceMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private UserMapper userMapperMock;


    @Test
    @DisplayName("Método createUser deve retornar novo usuário quando executado com sucesso")
    void createUserShouldReturnUserResponseWhenSuccess() {

        when(userRepositoryMock.save(any())).thenReturn(TestObjectUtil.userEntity());
        when(userMapperMock.userPostRequestToUserEntity(any())).thenReturn(TestObjectUtil.userEntity());
        when(userMapperMock.userEntityToUserResponse(any())).thenReturn(TestObjectUtil.userResponse());

        UserResponse response = userServiceMock.createUser(TestObjectUtil.userPostRequest());

        Assertions.assertEquals(response, TestObjectUtil.userResponse());

        verify(userMapperMock, times(1))
                .userPostRequestToUserEntity(TestObjectUtil.userPostRequest());

        verify(userRepositoryMock, times(1)).save(TestObjectUtil.userEntity());

        verify(userMapperMock, times(1)).userEntityToUserResponse(TestObjectUtil.userEntity());

    }

    @Test
    @DisplayName("Método updateUser deve retornar  usuário atualizado quando executado com sucesso")
    void updateUserShouldReturnUserResponseWhenSuccess() {

        when(userRepositoryMock.saveAndFlush(any())).thenReturn(TestObjectUtil.userEntityUpdated());
        when(userRepositoryMock.findById(any())).thenReturn(Optional.of(TestObjectUtil.userEntity()));
        when(userMapperMock.userPutRequestToUserEntity(any())).thenReturn(TestObjectUtil.userEntityUpdated());
        when(userMapperMock.userEntityToUserResponse(any())).thenReturn(TestObjectUtil.userResponseUpdated());

        UserResponse response = userServiceMock.updateUser(TestObjectUtil.userPutRequest());

        Assertions.assertEquals(response, TestObjectUtil.userResponseUpdated());

        verify(userMapperMock, times(1))
                .userPutRequestToUserEntity(TestObjectUtil.userPutRequest());

        verify(userRepositoryMock, times(1)).findById(1L);

        verify(userRepositoryMock, times(1)).saveAndFlush(TestObjectUtil.userEntityUpdated());

    }
}
package com.jobtest.techmanager.business.mapper;

import com.jobtest.techmanager.controller.representation.request.UserPostRequest;
import com.jobtest.techmanager.controller.representation.response.UserResponse;
import com.jobtest.techmanager.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Interface de Mapeamento de Usuários
 *
 * @version 1.0
 * @since Java 21
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity userPostRequestToUserEntity(UserPostRequest userPostRequest);

    UserResponse userEntityToUserResponse(UserEntity userEntity);
}

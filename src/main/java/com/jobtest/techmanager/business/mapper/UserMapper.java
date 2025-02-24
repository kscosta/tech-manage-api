package com.jobtest.techmanager.business.mapper;

import com.jobtest.techmanager.controller.representation.request.UserPostRequest;
import com.jobtest.techmanager.controller.representation.response.UserResponse;
import com.jobtest.techmanager.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Interface de Mapeamento de Usuários
 *
 * @version 1.0
 * @since Java 21
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    default Date map(LocalDate localDate) {
        return (localDate != null) ? Date.valueOf(localDate) : null;
    }

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "id", ignore = true)
    UserEntity userPostRequestToUserEntity(UserPostRequest userPostRequest);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "yyyy-MM-dd")
    UserResponse userEntityToUserResponse(UserEntity userEntity);
}

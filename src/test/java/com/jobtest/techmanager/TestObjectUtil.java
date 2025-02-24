package com.jobtest.techmanager;

import com.jobtest.techmanager.business.enums.UserType;
import com.jobtest.techmanager.controller.representation.request.UserPostRequest;
import com.jobtest.techmanager.controller.representation.response.UserResponse;
import com.jobtest.techmanager.infrastructure.entity.UserEntity;

import java.sql.Date;
import java.time.LocalDate;


public class TestObjectUtil {

    public static UserEntity userEntity() {

        return new UserEntity(1L, "Fulano teste", "test@test.com", Date.valueOf("1995-10-25"),
                "+55 11 99999-5544", UserType.ADMIN);
    }

    public static UserResponse userResponse() {

        return new UserResponse(1L, "Fulano da Silva", "fulano@test.com",
                "+55 11 99999-5544", LocalDate.of(1995, 10, 25), UserType.ADMIN);
    }

    public static UserPostRequest userPostRequest() {

        return new UserPostRequest("Fulano da Silva", "fulano@test.com",
                "+55 11 99999-5544",
                LocalDate.of(1995, 10, 25), UserType.ADMIN);
    }

}

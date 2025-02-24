package com.jobtest.techmanager.controller.representation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jobtest.techmanager.business.enums.UserType;

import java.time.LocalDate;

/**
 * Record para representar o recurso User (DTO)
 *
 * @param id
 * @param fullName
 * @param email
 * @param phone
 * @param birthDate
 * @param userType
 * @version 1.0
 * @since Java 21
 */

public record UserResponse(
        Long id,
        String fullName,
        String email,
        String phone,
        @JsonFormat(pattern = "dd-MM-yyyy") LocalDate birthDate,
        UserType userType
) {
}
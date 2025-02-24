package com.jobtest.techmanager.controller.representation.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jobtest.techmanager.business.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

/**
 * Record para representar o recurso User para requisição REST - POST (DTO)
 *
 * @param fullName
 * @param email
 * @param phone
 * @param birthDate
 * @param userType
 * @version 1.0
 * @since Java 21
 */

@Validated
public record UserPostRequest(
        @NotBlank(message = "fullName deve ser informado!") String fullName,
        @NotBlank(message = "email deve ser informado!") @Email(message = "Email informado é inválido!") String email,
        @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{4,5}[- .]?\\d{4}$",
                message = "Formato de telefone inválido! Exemplo: +99 99 99999-9999") String phone,
        @JsonFormat(pattern = "dd-MM-yyyy") LocalDate birthDate,
        @NotNull(message = "userType deve ser informado!") UserType userType
) {
}

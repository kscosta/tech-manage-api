package com.jobtest.techmanager.controller.representation.response;

import java.time.LocalDateTime;

/**
 * Record para representar a estrutura padrão de resposta da API
 *
 * @param timestamp
 * @param status
 * @param message
 * @param data
 * @param <T>
 * @version 1.0
 * @since Java 21
 */

public record DefaultApiResponse<T>(
        LocalDateTime timestamp,
        Integer status,
        String message,
        T data
) {
}

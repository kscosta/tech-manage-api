package com.jobtest.techmanager.business.enums;

/**
 * Enum dos tipos de Usuários
 *
 * @version 1.0
 * @since Java 21
 */

public enum UserType {

    ADMIN("ADMIN"),
    EDITOR("EDITOR"),
    VIEWER("VIEWER");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

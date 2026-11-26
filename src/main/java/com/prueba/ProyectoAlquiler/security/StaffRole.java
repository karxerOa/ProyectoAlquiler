package com.prueba.ProyectoAlquiler.security;

import java.util.Locale;
import java.util.Optional;

public final class StaffRole {
    private StaffRole() {}

    public static Optional<String> authority(String name) {
        if (name == null) return Optional.empty();
        return switch (name.toUpperCase(Locale.ROOT)) {
            case "ADMIN", "ROLE_ADMIN" -> Optional.of("ROLE_ADMIN");
            case "EMPLEADO", "ROLE_EMPLEADO" -> Optional.of("ROLE_EMPLEADO");
            default -> Optional.empty();
        };
    }
}

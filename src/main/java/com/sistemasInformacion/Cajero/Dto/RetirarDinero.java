package com.sistemasInformacion.Cajero.Dto;

public record RetirarDinero(
        String numeroCuenta,
        String clave,
        int montoRetiro
) {
}

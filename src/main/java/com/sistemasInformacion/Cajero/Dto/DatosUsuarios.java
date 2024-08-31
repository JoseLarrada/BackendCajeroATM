package com.sistemasInformacion.Cajero.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DatosUsuarios {
    private String nombreUsuario;
    private String contraseña;
    private String numeroTarjeta;
    private String clave;
}

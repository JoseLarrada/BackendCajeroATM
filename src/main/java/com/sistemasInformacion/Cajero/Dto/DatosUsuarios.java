package com.sistemasInformacion.Cajero.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "datosusuarios")
public class DatosUsuarios {
    @Id
    @Column(name = "numerotarjeta")
    private String numeroTarjeta;
    private String clave;
    private int saldo;
}

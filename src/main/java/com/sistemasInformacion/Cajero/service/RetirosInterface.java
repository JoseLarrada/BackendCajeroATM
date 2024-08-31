package com.sistemasInformacion.Cajero.service;

import com.sistemasInformacion.Cajero.Dto.TransaccionesDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface RetirosInterface {
    ResponseEntity<String> consultarCuenta(String numeroCuenta);
    ResponseEntity<String> validarClave(TransaccionesDto transaccionesDto);
    ResponseEntity<String> ValidarMonto(TransaccionesDto transaccionesDto);
    ResponseEntity<Map<Integer, Integer>> retirarDinero(int Monto);
}

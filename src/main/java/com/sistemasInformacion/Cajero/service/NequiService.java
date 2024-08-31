package com.sistemasInformacion.Cajero.service;

import com.sistemasInformacion.Cajero.Dto.TransaccionesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NequiService implements RetirosInterface{
    private final RetiroService retiroService;

    @Override
    public ResponseEntity<String> consultarCuenta(String numeroCuenta) {
        return null;
    }

    @Override
    public ResponseEntity<String> validarClave(TransaccionesDto transaccionesDto) {
        return null;
    }

    @Override
    public ResponseEntity<String> ValidarMonto(TransaccionesDto transaccionesDto) {
        return null;
    }

    @Override
    public ResponseEntity<Map<Integer, Integer>> retirarDinero(int Monto) {
        return null;
    }
}

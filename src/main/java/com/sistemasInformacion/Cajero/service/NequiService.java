package com.sistemasInformacion.Cajero.service;

import com.sistemasInformacion.Cajero.Dto.DatosUsuarios;
import com.sistemasInformacion.Cajero.Dto.TransaccionesDto;
import com.sistemasInformacion.Cajero.Repository.RetiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NequiService implements RetirosInterface{
    private final RetiroService retiroService;
    private final DispensadorDineroService dispensador;
    private final RetiroRepository repository;
    private StringBuilder cadena=new StringBuilder("0");
    @Override
    public ResponseEntity<String> consultarCuenta(String numeroCuenta) {
        if (retiroService.validarCredenciales(numeroCuenta,10)){
            String numeroCuentaReal=cadena.append(numeroCuenta).toString();
            if (repository.existsById(numeroCuentaReal)){
                return ResponseEntity.ok("Puede Continuar");
            }
            return ResponseEntity.badRequest().body("No existe la cuenta");
        }
        return ResponseEntity.badRequest().body("Numero de cuenta incorrecto");
    }

    @Override
    public ResponseEntity<String> validarClave(TransaccionesDto transaccionesDto) {
        if (transaccionesDto.numeroCuenta().equals(transaccionesDto.valor())){
            return ResponseEntity.ok("Puede Continuar");
        }
        return ResponseEntity.badRequest().body("Codigo incorrecto");
    }

    @Override
    public ResponseEntity<String> ValidarMonto(TransaccionesDto transaccionesDto) {
        String numeroCuentaReal=cadena.append(transaccionesDto.numeroCuenta()).toString();
        DatosUsuarios cuenta=repository.findById(numeroCuentaReal).orElse(null);
        int valorRetiro= Integer.parseInt(transaccionesDto.valor());
        assert cuenta != null;
        if (valorRetiro>cuenta.getSaldo()){
            return ResponseEntity.badRequest().body("Excede el monto de retiro");
        }
        return ResponseEntity.ok("Puede Continuar");
    }

    @Override
    public ResponseEntity<Map<Integer, Integer>> retirarDinero(int monto) {
        return ResponseEntity.ok(dispensador.dispensarDinero(monto));
    }
}

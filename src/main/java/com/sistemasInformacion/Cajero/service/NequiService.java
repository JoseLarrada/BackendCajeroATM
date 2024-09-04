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
    private final RetiroRepository repository;
    @Override
    public ResponseEntity<String> consultarCuenta(String numeroCuenta) {
        if (retiroService.validarCredenciales(numeroCuenta,10)){
            String numeroCuentaReal= "0" + numeroCuenta;
            System.out.println(numeroCuentaReal);
            if (repository.existsById(numeroCuentaReal)){
                return ResponseEntity.ok("Puede Continuar");
            }
            return ResponseEntity.badRequest().body("No existe la cuenta");
        }
        return ResponseEntity.badRequest().body("Numero de cuenta incorrecto");
    }

    @Override
    public ResponseEntity<String> validarClave(TransaccionesDto transaccionesDto) {
        System.out.println(transaccionesDto);
        if (retiroService.validarCredenciales(transaccionesDto.valor(), 6)){
            if (transaccionesDto.numeroCuenta().equals(transaccionesDto.valor())){
                return ResponseEntity.ok("Puede Continuar");
            }
            return ResponseEntity.badRequest().body("Codigo incorrecto");
        }
        return ResponseEntity.badRequest().body("Limite superado");
    }

    @Override
    public ResponseEntity<String> ValidarMonto(TransaccionesDto transaccionesDto) {
        System.out.println(transaccionesDto);
        DatosUsuarios cuenta=repository.findById(transaccionesDto.numeroCuenta()).orElse(null);
        int valorRetiro= Integer.parseInt(transaccionesDto.valor());
        assert cuenta != null;
        if (valorRetiro>cuenta.getSaldo()){
            return ResponseEntity.badRequest().body("Excede el monto de retiro");
        }
        return ResponseEntity.ok("Puede Continuar");
    }

    @Override
    public ResponseEntity<Map<Integer, Integer>> retirarDinero(int monto) {
        System.out.println(monto);
        return ResponseEntity.ok(retiroService.dispensarDinero(monto));
    }
}

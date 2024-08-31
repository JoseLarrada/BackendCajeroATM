package com.sistemasInformacion.Cajero.controller;

import com.sistemasInformacion.Cajero.Dto.TransaccionesDto;
import com.sistemasInformacion.Cajero.service.TarjetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/retiroTarjeta")
public class TarjetaController {
    private final TarjetaService tarjetaService;

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<String> consultarCuenta(@PathVariable String numeroCuenta){
        return tarjetaService.consultarCuenta(numeroCuenta);
    }
    @GetMapping("/validarClave")
    public ResponseEntity<String> validarClave(@RequestBody TransaccionesDto transaccionesDto){
        return tarjetaService.validarClave(transaccionesDto);
    }
    @GetMapping("/validarMonto")
    public ResponseEntity<String> ValidarMonto(@RequestBody TransaccionesDto transaccionesDto){
        return tarjetaService.ValidarMonto(transaccionesDto);
    }
    @GetMapping("/dispensarDinero/{monto}")
    public ResponseEntity<Map<Integer, Integer>> retirarDinero(@PathVariable int monto){
        return tarjetaService.retirarDinero(monto);
    }
}

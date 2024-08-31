package com.sistemasInformacion.Cajero.controller;

import com.sistemasInformacion.Cajero.Dto.RetirarDinero;
import com.sistemasInformacion.Cajero.Dto.TransaccionesDto;
import com.sistemasInformacion.Cajero.service.NequiService;
import com.sistemasInformacion.Cajero.service.RetiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/retiroNequi")
public class DispensarDineroController {
    private final NequiService nequiService;

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<String> consultarCuenta(@PathVariable String numeroCuenta){
        return nequiService.consultarCuenta(numeroCuenta);
    }
    @GetMapping("/validarClave")
    public ResponseEntity<String> validarClave(@RequestBody TransaccionesDto transaccionesDto){
        return nequiService.validarClave(transaccionesDto);
    }
    @GetMapping("/validarMonto")
    public ResponseEntity<String> ValidarMonto(@RequestBody TransaccionesDto transaccionesDto){
        return nequiService.ValidarMonto(transaccionesDto);
    }
    @GetMapping("/dispensarDinero/{monto}")
    public ResponseEntity<Map<Integer, Integer>> retirarDinero(@PathVariable int monto){
        return nequiService.retirarDinero(monto);
    }
}
package com.sistemasInformacion.Cajero.controller;

import com.sistemasInformacion.Cajero.Dto.RetirarDinero;
import com.sistemasInformacion.Cajero.service.RetiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dispensar")
public class DispensarDineroController {
    private final RetiroService retirarNequi;

    @GetMapping("/nequi")
    public ResponseEntity<Map<Integer, Integer>> retirarNequi(@RequestBody RetirarDinero request){
        return retirarNequi.retirarPorNequi(request);
    }
    @GetMapping("/debito")
    public ResponseEntity<Map<Integer, Integer>> retirarDebito(@RequestBody RetirarDinero request){
        return retirarNequi.retirarPorTajetaDebito(request);
    }
}
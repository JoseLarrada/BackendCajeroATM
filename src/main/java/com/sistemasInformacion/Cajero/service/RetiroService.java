package com.sistemasInformacion.Cajero.service;

import com.sistemasInformacion.Cajero.Dto.RetirarDinero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RetiroService {
    private final DispensadorDineroService dispensador;

    public ResponseEntity<Map<Integer, Integer>> retirarPorNequi(RetirarDinero datos){
        int EXTENSIONCODIGO = 6; int EXTENSIONTELEFONO = 10;
        if (validarCredenciales(datos.numeroCuenta(), EXTENSIONTELEFONO) && validarCredenciales(datos.clave(), EXTENSIONCODIGO)){
            return ResponseEntity.ok(dispensador.dispensarDinero(datos.montoRetiro()));
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Map<Integer, Integer>> retirarPorTajetaDebito(RetirarDinero datos){
        int EXTENSIONCLAVE = 4; int EXTENSIONNUMEROCUENTA = 16;
        if (validarCredenciales(datos.numeroCuenta(), EXTENSIONNUMEROCUENTA) && validarCredenciales(datos.clave(), EXTENSIONCLAVE)){
            if (validarTarjetaDebito(datos.numeroCuenta())){
                return ResponseEntity.ok(dispensador.dispensarDinero(datos.montoRetiro()));
            }
        }
        return ResponseEntity.notFound().build();
    }

    protected boolean validarCredenciales(String credencial,int extension){
        Pattern patronNumerico=Pattern.compile("^-?\\d+$");
        Matcher patronCoincidente= patronNumerico.matcher(credencial);
        return credencial.length() == extension && patronCoincidente.find();
    }
    protected boolean validarTarjetaDebito(String numeroTarjeta){
        //Validamos primero si es visa
        if (numeroTarjeta.charAt(0) =='4'){
            return true;
        }else return ((generarValoresTarjeta(numeroTarjeta)>=51 && generarValoresTarjeta(numeroTarjeta)<=55) ||
                (generarValoresTarjeta(numeroTarjeta) >= 2221 && generarValoresTarjeta(numeroTarjeta) <= 2720)); //Validar si es masterCard
    }
    protected int generarValoresTarjeta(String numeroTarjeta){
        StringBuilder valorNumerico = new StringBuilder();
        int extensionNumericaTarjeta = numeroTarjeta.charAt(0) == '2' ? 4 : numeroTarjeta.charAt(0) == '5' ? 2 : 0;
        for (int i = 0; i < extensionNumericaTarjeta; i++) {
            valorNumerico.append(numeroTarjeta.charAt(i));
        }
        return Integer.parseInt(valorNumerico.toString());
    }
}

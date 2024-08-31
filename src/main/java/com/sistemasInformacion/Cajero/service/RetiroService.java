package com.sistemasInformacion.Cajero.service;

import com.sistemasInformacion.Cajero.Dto.RetirarDinero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RetiroService {

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
    public Map<Integer, Integer> dispensarDinero(int montoSolicitado){
        int[] billetes = { 10000, 20000, 50000, 100000 }; int suma = 0;
        Map<Integer, Integer> entregarBilletes = new HashMap<>();
        for (int billete : billetes) {
            entregarBilletes.put(billete, 0);
        }
        while (montoSolicitado != suma) {
            for (int i = 0; i < billetes.length; i++) {
                if(suma+billetes[i]<= montoSolicitado){
                    suma += billetes[i];
                    entregarBilletes.put(billetes[i], entregarBilletes.get(billetes[i]) + 1);
                }
                for (int j = i + 1; j < billetes.length; j++) {
                    if(suma+billetes[j]<= montoSolicitado){
                        suma += billetes[j];
                        entregarBilletes.put(billetes[j], entregarBilletes.get(billetes[j]) + 1);
                    }
                }
            }
        }
        return entregarBilletes;
    }
}

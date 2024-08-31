package com.sistemasInformacion.Cajero.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@NoArgsConstructor
public class DispensadorDineroService {

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

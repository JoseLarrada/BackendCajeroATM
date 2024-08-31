package com.sistemasInformacion.Cajero.config;

import com.sistemasInformacion.Cajero.Dto.DatosUsuarios;

import java.util.ArrayList;
import java.util.List;

public class DatosDiccionario {
    public List<DatosUsuarios> IngresarValoresEnListas(){
        DatosUsuarios usuario=new DatosUsuarios("pedro","123456","4323456789012345","2715");
        DatosUsuarios usuario1=new DatosUsuarios("juancho","123456","4323456789012345","2715");
        DatosUsuarios usuario2=new DatosUsuarios("jose","123456","4323456789012345","2715");
        DatosUsuarios usuario3=new DatosUsuarios("maria","123456","4323456789012345","2715");
        List<DatosUsuarios> datosUsuariosList= List.of(new DatosUsuarios[]{usuario,usuario1,usuario2,usuario3});
        return datosUsuariosList;
    }

}

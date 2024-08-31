package com.sistemasInformacion.Cajero.Repository;

import com.sistemasInformacion.Cajero.Dto.DatosUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetiroRepository extends JpaRepository<DatosUsuarios,String> {
}

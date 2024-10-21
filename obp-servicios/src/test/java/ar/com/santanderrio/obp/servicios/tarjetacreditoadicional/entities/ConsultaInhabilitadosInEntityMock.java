package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities;

import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosInEntity;

public final class ConsultaInhabilitadosInEntityMock {
    
    private ConsultaInhabilitadosInEntityMock() {
        throw new IllegalAccessError("Clase para testing");
    }

    public static ConsultaInhabilitadosInEntity infoEntity() { 
        ConsultaInhabilitadosInEntity entity = new ConsultaInhabilitadosInEntity();
        entity.setApellido("Godoy");
        entity.setNombre("Silvia");
        entity.setSexo("Femenino");
        entity.setFechaNac("12/05/1972");
        entity.setTipoDoc("DNI");
        entity.setNroDoc("22159478");
        return entity;
    }
    
}

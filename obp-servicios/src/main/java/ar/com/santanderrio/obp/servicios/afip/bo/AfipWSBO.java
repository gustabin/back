package ar.com.santanderrio.obp.servicios.afip.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

public interface AfipWSBO {
    Respuesta<Boolean> tieneDeudaConAfip();
}

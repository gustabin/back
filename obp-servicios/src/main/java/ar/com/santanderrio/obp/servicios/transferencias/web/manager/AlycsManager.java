package ar.com.santanderrio.obp.servicios.transferencias.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

public interface AlycsManager {

    Respuesta<Boolean> limpiarCacheCuitsAlycs();
}

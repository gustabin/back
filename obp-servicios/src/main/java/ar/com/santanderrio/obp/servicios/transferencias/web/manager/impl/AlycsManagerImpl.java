package ar.com.santanderrio.obp.servicios.transferencias.web.manager.impl;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.dao.AlycsDAO;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.AlycsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlycsManagerImpl implements AlycsManager {

    @Autowired
    private AlycsDAO alycsDAO;

    @Autowired
    private RespuestaFactory respuestaFactory;


    @Override
    public Respuesta<Boolean> limpiarCacheCuitsAlycs() {

        alycsDAO.cleanCacheCuitsAlycs();

        return respuestaFactory.crearRespuestaOk(true);
    }
}

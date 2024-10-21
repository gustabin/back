/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.pagos.bo.BuscadorEmpresaIndexadoBO;
import ar.com.santanderrio.obp.servicios.pagos.manager.BuscadorEmpresaIndexadoManager;

/**
 * @author sergio.e.goldentair
 *
 */
@Component("buscadorEmpresaIndexadoManager")
public class BuscadorEmpresaIndexadoManagerImpl implements BuscadorEmpresaIndexadoManager {
    @Autowired
    private BuscadorEmpresaIndexadoBO buscadorEmpresaIndexadoBO;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BuscadorEmpresaIndexadoManagerImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.manager.
     * BuscadorEmpresaIndexadoManager#limpiarMediosDePago()
     */
    @Override
    public Respuesta<Boolean> limpiarMediosDePago() {
        LOGGER.info("Limpiar Lucene.");
        return buscadorEmpresaIndexadoBO.limpiarMediosDePago();
    }

}

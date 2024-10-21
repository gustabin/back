/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesViewIn;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.TransaccionViewIn;

/**
 * The Interface ComprobantesManager.
 * 
 * @author sabrina.cis
 */
public interface ComprobantesManager {

    /**
     * Obtener comprobantes.
     * 
     * @param viewIn
     *            the view in
     * @return the respuesta
     */
    Respuesta<ComprobantesView> obtenerComprobantes(ComprobantesViewIn viewIn);

    Respuesta<TransaccionViewIn> solapaPorDefecto();

}

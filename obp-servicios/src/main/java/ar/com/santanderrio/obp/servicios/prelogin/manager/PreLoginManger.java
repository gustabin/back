/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prelogin.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.prelogin.web.view.PreLoginView;

/**
 * The Interface PreLoginManger.
 *
 * @author sergio.e.goldentair
 */
public interface PreLoginManger {
    
    /**
	 * Obtener RSA Publica.
	 *
	 * @return the respuesta
	 */
    Respuesta<String> obtenerEncPub();

    /**
     * Pre login.
     *
     * @param datoEntrada the dato entrada
     * @return the respuesta
     */
    Respuesta<PreLoginView> preLogin(String datoEntrada);

}

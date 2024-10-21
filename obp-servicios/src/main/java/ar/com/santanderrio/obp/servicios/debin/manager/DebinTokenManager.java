/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debin.manager;

import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.debin.entities.DebinView;

/**
 * The Interface DebinTokenManager.
 *
 * @author sergio.e.goldentair
 */
public interface DebinTokenManager {
    
    /**
	 * Obtener el token junto a la url que se debe utilizar para abrir debin.
	 *
	 * @param debinView
	 *            the debin view
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
    Respuesta<DebinView> obtenerTokenEncriptado(DebinView debinView, MessageContext mc);

    /**
	 * Verificar que en caso de que el cliente no tenga asociados token, tarjeta
	 * de coordenadas ni tarjeta de débito recuperar el mensaje con código 1575
	 * en caso de tenerlos retornar true.
	 *
	 * @return the respuesta
	 */
    Respuesta<Boolean> habilitado();
}

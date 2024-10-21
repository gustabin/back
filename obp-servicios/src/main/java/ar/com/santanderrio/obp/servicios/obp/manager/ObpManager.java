/**
 * 
 */
package ar.com.santanderrio.obp.servicios.obp.manager;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * The Interface ObpManager.
 *
 * @author sergio.e.goldentair
 */
public interface ObpManager {

	/**
	 * Obtener el token para obp.
	 *
	 * @param resumenCliente
	 *            the resumen cliente
	 * @return the respuesta
	 */
	Respuesta<TokenView> obtenerTokenEncriptado(ResumenCliente resumenCliente);
}

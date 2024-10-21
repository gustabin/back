/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.billetera.web.view.ValidarUsuarioBilleteraInView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.ValidarUsuarioBilleteraView;

/**
 * The Interface BilleteraManager.
 */
public interface BilleteraManager {

	/**
	 * Valida usuario de Billetera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ValidarUsuarioBilleteraView> validarUsuario(ValidarUsuarioBilleteraInView viewRequest);

}

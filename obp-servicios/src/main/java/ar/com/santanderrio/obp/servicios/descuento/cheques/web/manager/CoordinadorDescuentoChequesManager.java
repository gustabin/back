/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.OperacionesPrecargadasView;

/**
 * The Interface CoordinadorDescuentoChequesManager.
 */
public interface CoordinadorDescuentoChequesManager {

	/**
	 * Operaciones cabecera.
	 *
	 * @param operacionIn
	 *            the operacion in
	 * @return the respuesta
	 */
	Respuesta<OperacionesPrecargadasView> operacionesCabecera (OperacionesPrecargadasView operacionIn);
}

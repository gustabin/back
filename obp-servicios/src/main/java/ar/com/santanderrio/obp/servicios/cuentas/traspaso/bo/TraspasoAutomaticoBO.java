/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.TraspasoAutomaticoDTO;

/**
 * The Interface TraspasoAutomaticoBO.
 */
public interface TraspasoAutomaticoBO {

	/**
	 * Confirmar traspaso automatico.
	 *
	 * @param traspasoAutomaticoDTO
	 *            the traspaso automatico DTO
	 * @return the respuesta
	 */
	Respuesta<Void> confirmarTraspasoAutomatico(TraspasoAutomaticoDTO traspasoAutomaticoDTO);

}

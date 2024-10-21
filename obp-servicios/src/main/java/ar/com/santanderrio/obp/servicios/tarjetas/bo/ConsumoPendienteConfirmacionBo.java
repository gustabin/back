/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimosConsumosDTO;

/**
 * The Interface ConsumoPendienteConfirmacionBo.
 */
public interface ConsumoPendienteConfirmacionBo {

	/**
	 * Obtener consumo pendiente de autorizacion.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<UltimosConsumosDTO> obtenerConsumoPendiente(Cuenta cuenta);

}
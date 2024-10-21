/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosConfirmacionFinanciacionTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FinanciacionTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FinanciacionTarjetaView;

/**
 * Financiacion Tarjetas BO,
 * <P>
 * Operaciones de negocios de financiacion de tarjetas.
 * </P>
 *
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since Dec 6, 2016
 */
public interface FinanciacionTarjetaBO {

	/**
	 * Ejecutar financiacion del resumen de la tarjeta. DTF: 30757
	 *
	 * @author Manuel.Vargas
	 * @param datos
	 *            the datos
	 * @return the respuesta
	 */
	Respuesta<FinanciacionTarjetaDTO> ejecutarFinanciacionTarjeta(DatosConfirmacionFinanciacionTarjetaDTO datos);

	/**
	 * Solicitar financiacion tarjeta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<FinanciacionTarjetaDTO> solicitarFinanciacionTarjeta(Cuenta cuenta);

	/**
	 * Solicitar financiacion tarjeta.
	 *
	 * @author emilio.watemberg
	 * @param financiacionTarjetaView
	 *            the financiacion tarjeta view
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<FinanciacionTarjetaDTO> simularFinanciacionTarjeta(FinanciacionTarjetaView financiacionTarjetaView,
			Cuenta cuenta);

}

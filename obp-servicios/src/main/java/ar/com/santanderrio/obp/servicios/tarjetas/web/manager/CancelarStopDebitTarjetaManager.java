/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.DatosCancelarStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.view.DatosInicioCancelarStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.view.InicioCancelarStopDebitDTO;

/**
 * The Interface CancelarStopDebitTarjetaManager.
 */
public interface CancelarStopDebitTarjetaManager {

	/**
	 * Inicio cancelar stop debit.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosInicioCancelarStopDebit
	 *            the datos inicio cancelar stop debit
	 * @return the respuesta
	 */
	Respuesta<InicioCancelarStopDebitDTO> inicioCancelarStopDebit(Cliente cliente,
			DatosInicioCancelarStopDebit datosInicioCancelarStopDebit);

	/**
	 * Cancelar stop debit.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosCancelarStopDebit
	 *            the datos cancelar stop debit
	 * @return the respuesta
	 */
	Respuesta<ComprobanteFeedbackView> cancelarStopDebit(Cliente cliente,
			DatosCancelarStopDebit datosCancelarStopDebit);

	/**
	 * Estadistica visualizacion comprobante cancelacion stop debit.
	 */
	void estadisticaVisualizacionComprobanteCancelacionStopDebit();

}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FeedbackFinanciacionTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FinanciacionTarjetaView;

/**
 * The Interface FinanciacionTarjetasManager.
 *
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since Dec 6, 2016
 */
public interface FinanciacionTarjetaManager {

	/**
	 * Solicitar financiacion tarjeta.
	 *
	 * @author emilio.watemberg
	 * @param financiacionTarjetaView
	 *            the financiacion tarjeta view
	 * @return the financiacion tarjeta view
	 * @since Dec 5, 2016
	 */
	Respuesta<FinanciacionTarjetaView> solicitarFinanciacionTarjeta(FinanciacionTarjetaView financiacionTarjetaView);

	/**
	 * Ejecutar financiacion del resumen de la tarjeta. DTF: 30757
	 *
	 * @author B041299 Manuel.Vargas
	 * @param financiacionTarjetaView
	 *            the financiacion tarjeta view
	 * @return the financiacion tarjeta view
	 */
	Respuesta<FeedbackFinanciacionTarjetaView> ejecutarFinanciacionTarjeta(
			FinanciacionTarjetaView financiacionTarjetaView);

	/**
	 * Simular financiacion del resumen de la tarjeta. DTF: 30757
	 *
	 * @author emilio.watemberg
	 * @param financiacionTarjetaView
	 *            the financiacion tarjeta view
	 * @return the financiacion tarjeta view
	 */
	Respuesta<FinanciacionTarjetaView> simularFinanciacionTarjeta(FinanciacionTarjetaView financiacionTarjetaView);

	/**
	 * Comprobante financiacion tarjeta.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the respuesta
	 */
	Respuesta<FinanciacionTarjetaView> comprobanteFinanciacionTarjeta(FinanciacionTarjetaView tarjeta);

}

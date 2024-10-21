/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.ComprobanteRescateFondo;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RescateDesdeGrillaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfiguracionRescateBPrivInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfiguracionRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfiguracionRescateOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateBPrivInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateCitiInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateDesdeGrilla;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionRescateOutView;

/**
 * The Interface RescateManager.
 *
 * @author b039920
 */
public interface RescateManager {

	/**
	 * Obtiene las cuentas del cliente correspondientes a la moneda recibida en
	 * el InView.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionRescateOutView> configuracionRescate(ConfiguracionRescateInView viewRequest);

	/**
	 * Banca Privada: Obtiene las cuentas del cliente correspondientes a la
	 * moneda recibida en el InView.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionRescateOutView> configuracionRescateBPriv(ConfiguracionRescateBPrivInView viewRequest);

	/**
	 * Fondos suscriptos.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<RescateView> obtenerFondosSuscriptos(RescateInView viewRequest);

	/**
	 * Devuelve los fondos suscriptos posibles de rescatar.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<RescateView> obtenerFondosSuscriptosBPriv(RescateInView viewRequest);

	/**
	 * Comprobante rescate citi.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<FinalizarRescateView> finalizarRescateCiti(FinalizarRescateCitiInView viewRequest);
	
	/**
	 * Comprobante rescate.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<FinalizarRescateView> finalizarRescate(FinalizarRescateInView viewRequest);

	/**
	 * Realiza la confirmación de un rescate de fondo.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<SimulacionRescateOutView> simularRescateFondo(SimulacionRescateInView viewRequest);

	/**
	 * Simular rescate fondo B priv.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<SimulacionRescateOutView> simularRescateFondoBPriv(SimulacionRescateInView viewRequest);

	/**
	 * Realiza el rescate y retorna los datos para feedback.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<FinalizarRescateView> finalizarRescateBPriv(FinalizarRescateBPrivInView viewRequest);

	/**
	 * Devuelve el mensaje legal y el SEUO para el comprobante.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ComprobanteSuscripcionView> verComprobante(DatosComprobante viewRequest);

	/**
	 * Devuelve el mensaje legal y el SEUO para el comprobante.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ComprobanteSuscripcionView> verComprobanteBPriv(DatosComprobante viewRequest);

	/**
	 * Devuelve el plazo efectivo y el numero de cuenta operativa son formatear
	 * y los limites minimo y maximo del rescate.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */

	Respuesta<RescateDesdeGrilla> obtenerRescateDesdeGrilla(RescateDesdeGrillaInView viewRequest);

	/**
	 * Descargar comprobante Rescate PDF.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteRescatePDF(ComprobanteRescateFondo viewRequest);

	/**
	 * Realiza el grabado en sesion de los datos para validar mapa en la
	 * confirmacion y descarga de comprobante.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<Void> continuarConfiguracionAConfirmacionRescateExciti(FinalizarRescateCitiInView viewRequest);

}

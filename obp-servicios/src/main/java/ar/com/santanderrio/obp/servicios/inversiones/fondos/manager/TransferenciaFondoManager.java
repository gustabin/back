/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteTransferenciaFondo;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigTransferenciaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentasConsultaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarTransferenciaFondoInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarTransferenciaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionTransferenciaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.TransferenciaView;

/**
 * Gestiona la logica relacionada a Transferencia.
 * 
 * @author
 *
 */
public interface TransferenciaFondoManager {

	/**
	 * Obtiene los datos para la configuracion de la transferencia.
	 *
	 * @param configTransferenciaInView
	 *            the config transferencia in view
	 * @return the respuesta
	 */
	Respuesta<ConfigTransferenciaView> obtenerDatosConfig(ConfigTransferenciaInView configTransferenciaInView);

	/**
	 * Simular transferencia.
	 *
	 * @param simulacionTransferenciaInView
	 *            the simulacion transferencia in view
	 * @return the respuesta
	 */
	Respuesta<SimulacionTransferenciaView> simularTransferencia(
			SimulacionTransferenciaInView simulacionTransferenciaInView);

	/**
	 * Simular transferencia bpriv.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<SimulacionTransferenciaView> simularTransferenciaBpriv(SimulacionInView viewRequest);

	/**
	 * Iniciar transferencia.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	/**
	 * @param viewRequest
	 * @return
	 */
	Respuesta<CuentasConsultaFondoView> iniciarTransferencia(CuentasConsultaFondoView viewRequest);

	/**
	 * Finalizar transferencia fondos.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<TransferenciaView> finalizarTransferenciaFondos(TransferenciaView viewRequest);

	/**
	 * Iniciar transferencia bpriv.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<CuentasConsultaFondoView> iniciarTransferenciaBpriv(CuentasConsultaFondoView viewRequest);

	/**
	 * Graba la estadistica de aceptacion de informacion de gastos para banca
	 * privada.
	 *
	 * @param requestView
	 *            the request view
	 */
	void grabarEstadisticaGastosBPriv(ConfigTransferenciaInView requestView);

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
	 * Finalizar transferencia fondos bpriv.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<FinalizarTransferenciaFondoView> finalizarTransferenciaFondosBpriv(
			FinalizarTransferenciaFondoInView viewRequest);

	/**
	 * Descargar comprobante Rescate PDF.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteTransferenciaPDF(ComprobanteTransferenciaFondo viewRequest);
}

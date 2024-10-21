/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewIn;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DatosCesionView;

/**
 * The Interface AltaChequesManager.
 */
public interface AltaChequesManager {

	/**
	 * Grabar la estadistica de inicio solicitud.
	 *
	 * @param view
	 *            the view
	 */
	void grabarInicioSolicitud(DatosCesionView view);
	
	/**
	 * Inicializa el contador de reintentos
	 * Trae los datos de sesion necesarios para ejecutar la simulacion.
	 * Llama al BO y transforma su respuesta en una vista, aumentando la cantidad de reintentos en caso de error
	 * Tambien graba las estadisticas de simulacion
	 *
	 * @param chequesView the cheques view
	 * @return the respuesta
	 */
	Respuesta<AltaChequesViewOut> simularAltaCheques(AltaChequesViewIn chequesView);
	
	/**
	 * Inicializa el contador de reintentos para el stack de efectivizacion
	 * Trae los datos de sesion necesarios para ejecutar la misma.
	 * Llama al BO y transforma su respuesta en un view, aumentando la cantidad de reintentos en caso de error
	 * Tambien graba las estadisticas de efectivizacion

	 *
	 * @return the respuesta
	 */
	Respuesta<AltaChequesViewOut> efectuarAltaCheques();

	/**
	 * Visualizar alta cheques.
	 *
	 * @param chequesView
	 *            the cheques view
	 * @return the respuesta
	 */
	Respuesta<AltaChequesViewOut> visualizarAltaCheques(AltaChequesViewOut chequesView);

	/**
	 * Descargar PDF.
	 *
	 * @param chequesView
	 *            the cheques view
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarPDF(AltaChequesViewOut chequesView);
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.web.manager;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoEncoladoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.LiquidacionPrestamoEncoladoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ResultadoSimulacionView;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudSimulacionView;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.ConfiguracionPrestamoView;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.PuntoDeAccesoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.BajaPrestamoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 * The Interface SimuladorPrestamoManager.
 */
public interface SimuladorPrestamoManager {

	/**
	 * Inicio simulacion.
	 *
	 * @param puntoDeAcceso
	 *            the punto de acceso
	 * @return the respuesta
	 */
	Respuesta<Void> grabarEstadisticaInicioSimuladorPrestamo(PuntoDeAccesoView puntoDeAcceso);

	/**
	 * Simular.
	 *
	 * @param solicitudSimulacionView
	 *            the solicitud simulacion view
	 * @return the respuesta
	 */
	Respuesta<ResultadoSimulacionView> simular(SolicitudSimulacionView solicitudSimulacionView);

	/**
	 * Adquirir prestamo.
	 *
	 * @param solicitudSimulacion
	 *            the solicitud simulacion
	 * @return the respuesta
	 */
	Respuesta<ComprobanteFeedbackView> adquirirPrestamo(SolicitudSimulacionView solicitudSimulacion);

	/**
	 * Encolar prestamo.
	 *
	 * @param solicitudSimulacion
	 *            the solicitud simulacion
	 * @return the respuesta
	 */
	Respuesta<ComprobanteFeedbackView> encolarPrestamo(SolicitudSimulacionView solicitudSimulacion);

	/**
	 * Obtiene simulacion del prestamo para visualizar
	 */
    Respuesta<ResultadoSimulacionView> obtenerSolicitudPrestamo();

	/**
	 * Liquidar prestamo encolado
	 */
    Respuesta<ComprobanteFeedbackView> adquirirPrestamoEncolado(LiquidacionPrestamoEncoladoView liquidacionPrestamoEncoladoView);


	/**
	 * Cancelar prestamo encolado
	 */
	void eliminarSolicitudPendiente(LiquidacionPrestamoEncoladoView liquidacionPrestamoEncoladoView, PrestamoEncoladoEntity prestamoEncoladoEntity) throws DAOException;

	/**
	 * Grabar estadistica visualizacion comprobante.
	 *
	 * @param comprobanteView
	 *            the comprobante view
	 * @return the boolean
	 */
	Boolean grabarEstadisticaVisualizacionComprobante(ComprobantePrestamoView comprobanteView);

	/**
	 * Obtener datos simulacion.
	 *
	 * @return the respuesta
	 */
	Respuesta<List<ConfiguracionPrestamoView>> obtenerDatosSimulacion();

	Boolean chequearSiEstaEnHorarioOperacion();
}
/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.entity.DatosEntradaAgendaDestinatario;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AgendaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioCBUOutView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioInView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;

/**
 * Manager para agenda destinatarios.
 */
public interface AgendaDestinatariosManager {

	/**
	 * Obtener agenda destinatarios.
	 *
	 * @param dato
	 *            the dato
	 * @return respuesta con lista de destinatarios
	 */
	Respuesta<AgendaDestinatarioView> obtenerAgendaDestinatarios(DatosEntradaAgendaDestinatario dato);

	/**
	 * Derivar servicio alta correspondiente.
	 *
	 * @param configuracionAltaDestinatarioInView
	 *            the configuracion alta destinatario in view
	 * @param userAgent
	 *            the user agent
	 * @return informacion de destinatario a agregar
	 */
	Respuesta<ConfiguracionAltaDestinatarioCBUOutView> derivarServicioAltaCorrespondiente(
			ConfiguracionAltaDestinatarioInView configuracionAltaDestinatarioInView, String userAgent);

	/**
	 * Recibe el tipo de destinatario sobre el que se quiere ver el detalle y
	 * graba la estadistica correspondiente.
	 *
	 * @param tipoDestinatario
	 *            the tipo destinatario
	 */
	void grabarEstadisticaDetalleAgenda(String tipoDestinatario);

	/**
	 * Graba estadistica al ingresar al alta de destinatario rio.
	 */
	void grabarEstadisticaIngresoAltaDestinatarioRio();

	/**
	 * Graba estadistica al comprobante de alta de un destinatario rio.
	 */
	void grabarEstadisticaVerComprobanteRio();

	/**
	 * Grabar estadistica ingreso alta destinatario CBU.
	 */
	void grabarEstadisticaIngresoAltaDestinatarioCBU();

	/**
	 * Grabar estadistica ingreso alta destinatario envio efectivo.
	 */
	void grabarEstadisticaIngresoEnvioEfectivo();

	/**
	 * Grabar estadistica alta destinatario envio efectivo.
	 */
	void grabarEstadisticaAltaEnvioEfectivo();

	/**
	 * Eliminacion destinatario.
	 *
	 * @param id
	 *            - Id de destinatario a eliminar
	 * @return the respuesta
	 */
	Respuesta<Void> eliminacionDestinatario(String id);

	/**
	 * Grabar estadistica Comprobante CBU.
	 */
	void grabarEstadisticaComprobanteCBU();

	/**
	 * Grabar estadistica entrada a Modificacion rio.
	 *
	 * @param datos
	 *            the datos
	 */
	void grabarEstadisticaConfiguracionModificacionDestinatario(DatosEntradaAgendaDestinatario datos);

	/**
	 * Entrada general al flujo de edicion destinatario, se decide cual edicion
	 * es y se deriva al metodo correspondiente.
	 *
	 * @param datosEntrada
	 *            the datos entrada
	 * @return the respuesta
	 */
	Respuesta<ConfirmacionAltaDestinatarioView> obtenerConfirmacionEdicionDestinatario(
			ConfirmacionAltaDestinatarioView datosEntrada);

	/**
	 * Grabar estadistica comprobante Modificacion.
	 *
	 * @param datos
	 *            the datos
	 */
	void grabarEstadisticaComprobanteModificacionDestinatario(DatosEntradaAgendaDestinatario datos);

}

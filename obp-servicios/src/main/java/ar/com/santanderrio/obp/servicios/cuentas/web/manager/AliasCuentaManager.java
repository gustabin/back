/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ComprobanteAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ComprobanteBajaDestinatarioView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView;

/**
 * The Interface AliasCuentaManager.
 */
public interface AliasCuentaManager {

	/**
	 * Obtener alias CBU.
	 *
	 * @param string
	 *            the string
	 * @param userAgent
	 *            the user agent
	 * @return the respuesta
	 */
	Respuesta<DetalleCBUView> obtenerAliasCBU(String string, String userAgent);

	/**
	 * Continuar alias CBU,primer paso(configuracion).
	 *
	 * @param detalle
	 *            the detalle
	 * @return the respuesta
	 */
	Respuesta<DetalleCBUView> continuarAliasCBU(DetalleCBUView detalle);

	/**
	 * Grabado de estadistica al ver comprobante de alta alias cbu.
	 *
	 * @param numeroCuentaOrigen
	 *            the numero cuenta origen
	 */
	void grabarEstadisticaComprobanteAltaAliasCBU(String numeroCuentaOrigen);

	/**
	 * Grabado estadistica ayuda.
	 *
	 * @param detalle
	 *            the detalle
	 */
	void grabadoEstadisticaAyuda(DetalleCBUView detalle);

	/**
	 * Confirmacion editar alias CBU.
	 *
	 * @param view
	 *            the view
	 * @param string
	 *            the string
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaDestinatarioView> confirmacionEditarAliasCBU(ComprobanteAltaDestinatarioView view,
			String string);

	/**
	 * Confirma el alta de alias cbu.
	 *
	 * @param view
	 *            the view
	 * @param string
	 *            the string
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaDestinatarioView> confirmacionCrearAliasCBU(ComprobanteAltaDestinatarioView view,
			String string);

	/**
	 * Grabar estadistica comprobante editar alias CBU.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 */
	void grabarEstadisticaComprobanteEditarAliasCBU(String numeroCuenta);

	/**
	 * Elimina alias CBU.
	 *
	 * @param view
	 *            the view
	 * @param string
	 *            the string
	 * @return the respuesta
	 */
	Respuesta<ComprobanteBajaDestinatarioView> confirmacionBajaAliasCBU(ComprobanteBajaDestinatarioView view,
			String string);

	/**
	 * Busca Alias CBU a eliminar.
	 *
	 * @param detalle
	 *            the detalle
	 * @return the respuesta
	 */
	Respuesta<DetalleCBUView> eliminarAliasCBU(DetalleCBUView detalle);

	/**
	 * Estadistica comprobante de eliminar Alias CBU.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 */
	void grabarEstadisticaComprobanteEliminarAliasCBU(String numeroCuenta);

	/**
	 * Comprobante alias CBU.
	 *
	 * @param view
	 *            the view
	 */
	void comprobanteAliasCBU(DetalleCBUView view);

	/**
	 * Grabar estadistica comprobante reasignacion alias CBU.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 */
	void grabarEstadisticaComprobanteReasignacionAliasCBU(String numeroCuenta);
}

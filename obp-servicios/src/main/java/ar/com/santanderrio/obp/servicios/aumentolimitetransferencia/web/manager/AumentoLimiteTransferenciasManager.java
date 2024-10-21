/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AgendaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto.TISFechasHabilitadasResponse;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AgendaDestinatarioLimiteTransferenciasView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoLimiteTransferenciaInOutView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoTransferenciaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Interface AumentoLimiteTransferenciasManager.
 */
/*
 * The Interface AumentoLimiteTransferenciasManager
 */
public interface AumentoLimiteTransferenciasManager {

	/**
	 * Metodo que valida si el usurio posee el token habilitado.
	 *
	 * @return respuesta WARNING en caso de no poseer token habilitado
	 */
	Respuesta<AgendaDestinatarioLimiteTransferenciasView> verificarTokenAsociado();

	/**
	 * Obtiene agenda de destinatarios, agregando el mensaje de ayuda para
	 * agrear destinatario nuevo.
	 *
	 * @param respuestaAgendaDestinatariosManager
	 *            the respuesta agenda destinatarios manager
	 * @return lista de destinatarios devueltos por el modulo de
	 *         agendaDestinatarios, mas el mensaje de ayuda seteado
	 */
	Respuesta<AgendaDestinatarioLimiteTransferenciasView> obtenerAgendaDestinatarios(
			Respuesta<AgendaDestinatarioView> respuestaAgendaDestinatariosManager);

	/**
	 * Obtiene informacion detallada del destinatario y agrega los montos
	 * minimos en pesos/dolares mas el mensaje de dialogo de ayuda.
	 *
	 * @param destinatarioView
	 *            the destinatario view
	 * @return la info del destinatario, agregados los montos minimos para
	 *         realizar la solicitud
	 */
	Respuesta<AumentoTransferenciaView> obtenerInformacionDestinatario(DestinatarioView destinatarioView);

	/**
	 * Obtiene lista de los dias habiles para realizar la solicitud.
	 *
	 * @return lista de dias habiles
	 */
	Respuesta<AumentoLimiteTransferenciaInOutView> obtenerFechasHabilitadas(AumentoLimiteTransferenciaInOutView inView);

	/**
	 * Da de alta la gestion de solicitud de aumento de limite de transferencia.
	 *
	 * @param inView
	 *            request
	 * @return resultado de la solicitud
	 */
	Respuesta<AumentoLimiteTransferenciaInOutView> altaSolicitudAumentoLimiteTransferencia(
			AumentoLimiteTransferenciaInOutView inView);

	/**
	 * Genera el pdf del comprobante.
	 *
	 * @return pdf
	 */
	Respuesta<ReporteView> generarComprobanteAumentoLimiteTransferencia();

	/**
	 * Limpia el desafio de sesion.
	 *
	 * @return Void
	 */
	Respuesta<Void> vaciarDesafio();
}

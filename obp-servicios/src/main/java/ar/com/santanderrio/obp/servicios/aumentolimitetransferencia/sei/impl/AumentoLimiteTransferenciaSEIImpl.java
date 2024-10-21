/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.sei.impl;

import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto.TISFechasHabilitadasResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.entity.DatosEntradaAgendaDestinatario;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AgendaDestinatariosManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AgendaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.sei.AumentoLimiteTransferenciaSEI;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.manager.AumentoLimiteTransferenciasManager;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AgendaDestinatarioLimiteTransferenciasView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoLimiteTransferenciaInOutView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoTransferenciaView;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.TransferenciaManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * Implementacion de la interfaz AumentoLimiteTransferenciaSEI.
 */
@Component("aumentoLimiteTransferenciaSEI")
public class AumentoLimiteTransferenciaSEIImpl implements AumentoLimiteTransferenciaSEI {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AumentoLimiteTransferenciaSEIImpl.class);

	/** The aumento limite transferencias manager. */
	/* The AumentoLimiteTransferenciasManager. */
	@Autowired
	private AumentoLimiteTransferenciasManager aumentoLimiteTransferenciasManager;

	/** The agenda destinatarios manager. */
	/* The AgendaDestinatariosManager. */
	@Autowired
	private AgendaDestinatariosManager agendaDestinatariosManager;

	/** The transferencia manager. */
	/* The TransferenciaManager. */
	@Autowired
	private TransferenciaManager transferenciaManager;

	/** The estadistica manager. */
	/* The EstadisticaManager */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.sei.
	 * AumentoLimiteTransferenciaSEI#obtenerAgendaDestinatarios(ar.com.
	 * santanderrio.obp.servicios.agenda.destinatarios.web.entity.
	 * DatosEntradaAgendaDestinatario)
	 */
	@Override
	public Respuesta<AgendaDestinatarioLimiteTransferenciasView> obtenerAgendaDestinatarios(
			DatosEntradaAgendaDestinatario dato) {
		LOGGER.info("Post OK: /obtenerListaDestinatario.");
		Respuesta<AgendaDestinatarioLimiteTransferenciasView> respuestaVerificarToken = aumentoLimiteTransferenciasManager
				.verificarTokenAsociado();
		if (EstadoRespuesta.WARNING.equals(respuestaVerificarToken.getEstadoRespuesta())) {
			return respuestaVerificarToken;
		}
		estadisticaManager.add(EstadisticasConstants.SOLICITUD_AUMENTO_LIMITE_TRANSFERENCIA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Respuesta<AgendaDestinatarioView> respuestaAgendaDestinatariosManager = agendaDestinatariosManager
				.obtenerAgendaDestinatarios(dato);
		return aumentoLimiteTransferenciasManager.obtenerAgendaDestinatarios(respuestaAgendaDestinatariosManager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.sei.
	 * AumentoLimiteTransferenciaSEI#obtenerInformacionDestinatario(ar.com.
	 * santanderrio.obp.servicios.agenda.destinatarios.web.view.
	 * DestinatarioView)
	 */
	@Override
	public Respuesta<AumentoTransferenciaView> obtenerInformacionDestinatario(DestinatarioView destinatarioView) {
		LOGGER.info("Post OK: /obtenerInformacionDestinatario.");
		return aumentoLimiteTransferenciasManager.obtenerInformacionDestinatario(destinatarioView);
	}

	@Override
	public Respuesta<AumentoLimiteTransferenciaInOutView> obtenerFechasHabilitadas(AumentoLimiteTransferenciaInOutView inView) {
		LOGGER.info("Post OK: /obtenerFechasHabilitadas.");
		return aumentoLimiteTransferenciasManager.obtenerFechasHabilitadas(inView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.sei.
	 * AumentoLimiteTransferenciaSEI#consultarTitularidad(ar.com.santanderrio.
	 * obp.servicios.transferencias.web.view.TransferenciaView, 
	 * org.apache.cxf.jaxrs.ext.MessageContext)
	 */
	@Override
	public Respuesta<TransferenciaView> consultarTitularidad(TransferenciaView transferencia,
	        org.apache.cxf.jaxrs.ext.MessageContext mc) {
		LOGGER.info("Post OK: /consultarTitularidad.");
		return transferenciaManager.consultarTitularidad(transferencia, 
		        mc.getHttpHeaders().getRequestHeaders().get("User-Agent").get(0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.sei.
	 * AumentoLimiteTransferenciaSEI#altaSolicitudAumentoLimiteTransferencia(ar.
	 * com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.
	 * AumentoLimiteTransferenciaInOutView)
	 */
	@Override
	public Respuesta<AumentoLimiteTransferenciaInOutView> altaSolicitudAumentoLimiteTransferencia(
			AumentoLimiteTransferenciaInOutView inView) {
		LOGGER.info("Post OK: /altaSolicitudAumentoLimiteTransferencia.");
		return aumentoLimiteTransferenciasManager.altaSolicitudAumentoLimiteTransferencia(inView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.sei.
	 * AumentoLimiteTransferenciaSEI#
	 * generarComprobanteAumentoLimiteTransferencia()
	 */
	@Override
	public Respuesta<ReporteView> generarComprobanteAumentoLimiteTransferencia() {
		LOGGER.info("Post OK: /generarComprobanteAumentoLimiteTransferencia.");
		return aumentoLimiteTransferenciasManager.generarComprobanteAumentoLimiteTransferencia();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.sei.
	 * AumentoLimiteTransferenciaSEI#vaciarDesafio()
	 */
	@Override
	public Respuesta<Void> vaciarDesafio() {
		return aumentoLimiteTransferenciasManager.vaciarDesafio();
	}

}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei.AgendaDestinatariosSEI;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.entity.DatosEntradaAgendaDestinatario;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AgendaDestinatariosManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AgendaDestinatariosRCAManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AltaDestinatarioManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AgendaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioCBUOutView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioInView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;

/**
 * SEI de agenda destinatarios.
 */
@Component
public class AgendaDestinatariosSEIImpl implements AgendaDestinatariosSEI {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AgendaDestinatariosSEIImpl.class);

	/** The agenda destinatarios manager. */
	@Autowired
	private AgendaDestinatariosManager agendaDestinatariosManager;

	/** The alta destinatario manager. */
	@Autowired
	private AltaDestinatarioManager altaDestinatarioManager;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The agenda rsa manager. */
	@Autowired
	private AgendaDestinatariosRCAManager agendaRSAManager;

	/**
	 * Obtiene la agenda de destinatarios. s
	 *
	 * @param dato
	 *            the dato
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AgendaDestinatarioView> obtenerAgendaDestinatarios(DatosEntradaAgendaDestinatario dato) {
		LOGGER.info("Post OK: /obtenerListaDestinatario.");
		return agendaDestinatariosManager.obtenerAgendaDestinatarios(dato);
	}

	/**
	 * Graba la estadistica del detalle.
	 *
	 * @param datos
	 *            the datos
	 */
	@Override
	public void grabarEstadisticaVerDetalle(DatosEntradaAgendaDestinatario datos) {
		LOGGER.info("Post OK: /verDetalleDestinatario.");
		agendaDestinatariosManager.grabarEstadisticaDetalleAgenda(datos.getTipoDestinatario());
	}

	/**
	 * Graba la estadistica del alta de un destinatario RIO.
	 */
	@Override
	public void grabarEstadisticaIngresoAltaDestinatarioRio() {
		LOGGER.info("Post OK: /altaDestinatarioRio.");
		agendaDestinatariosManager.grabarEstadisticaIngresoAltaDestinatarioRio();
	}

	/**
	 * Graba la estadistica de un comprobante con destinatario RIO.
	 */
	@Override
	public void grabarEstadisticaVerComprobanteRio() {
		LOGGER.info("Post OK: /configuracionVerComprobanteRio");
		agendaDestinatariosManager.grabarEstadisticaVerComprobanteRio();
	}

	/**
	 * Confirma el alta de un destinatario.
	 *
	 * @param datosConfirmados
	 *            the datos confirmados
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public Respuesta<ConfirmacionAltaDestinatarioView> confirmarAltaDestinatario(
			ConfirmacionAltaDestinatarioView datosConfirmados) throws BusinessException {
		LOGGER.info("Post OK: /confirmarAltaDestinatario.");
		if (datosConfirmados.getAlias() != null) {
			return altaDestinatarioManager.obtenerConfirmacionAltaDestinatarioAlias(datosConfirmados);
		} else if (datosConfirmados.getCbu() != null) {
			return altaDestinatarioManager.obtenerConfirmacionAltaDestinatarioOtrosBancos(datosConfirmados);
		} else if (datosConfirmados.getNroCuenta() != null) {
			return altaDestinatarioManager.obtenerConfirmacionAltaDestinatarioRio(datosConfirmados);
		}
		return altaDestinatarioManager.obtenerConfirmacionAltaDestinatarioEnvioEfectivo(datosConfirmados);
	}

	/**
	 * Configuracion del alta de un destinatario para transferencia CBU.
	 *
	 * @param configuracionAltaDestinatarioViewTransferenciaCBU
	 *            the configuracion alta destinatario view transferencia CBU
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ConfiguracionAltaDestinatarioCBUOutView> configuracionAltaDestinatarioTransferenciaCBU(
			ConfiguracionAltaDestinatarioInView configuracionAltaDestinatarioViewTransferenciaCBU,
			org.apache.cxf.jaxrs.ext.MessageContext mc) {
		LOGGER.info("Post OK: /continuarAltaDestinatario.");
		return agendaDestinatariosManager.derivarServicioAltaCorrespondiente(
				configuracionAltaDestinatarioViewTransferenciaCBU,
				mc.getHttpHeaders().getRequestHeaders().get("User-Agent").get(0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei.
	 * AgendaDestinatariosSEI#grabarEstadisticaIngresoAltaDestinatarioCBU()
	 */
	@Override
	public void grabarEstadisticaIngresoAltaDestinatarioCBU() {
		agendaDestinatariosManager.grabarEstadisticaIngresoAltaDestinatarioCBU();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei.
	 * AgendaDestinatariosSEI#grabarEstadisticaComprobanteAltaDestinatarioCBU()
	 */
	@Override
	public void grabarEstadisticaComprobanteAltaDestinatarioCBU() {
		agendaDestinatariosManager.grabarEstadisticaComprobanteCBU();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei.
	 * AgendaDestinatariosSEI#grabarEstadisticaIngresoEnvioEfectivo()
	 */
	@Override
	public void grabarEstadisticaIngresoEnvioEfectivo() {
		agendaDestinatariosManager.grabarEstadisticaIngresoEnvioEfectivo();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei.
	 * AgendaDestinatariosSEI#grabarEstadisticaComprobanteAltaEnvioEfectivo()
	 */
	@Override
	public void grabarEstadisticaComprobanteAltaEnvioEfectivo() {
		agendaDestinatariosManager.grabarEstadisticaAltaEnvioEfectivo();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei.
	 * AgendaDestinatariosSEI#eliminacionDestinatario(ar.com.santanderrio.obp.
	 * servicios.agenda.destinatarios.web.entity.DatosEntradaAgendaDestinatario)
	 */
	@Override
	public Respuesta<Void> eliminacionDestinatario(DatosEntradaAgendaDestinatario datos) {
		return agendaDestinatariosManager.eliminacionDestinatario(datos.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei.
	 * AgendaDestinatariosSEI#
	 * grabarEstadisticaConfiguracionModificacionDestinatario(ar.com.
	 * santanderrio.obp.servicios.agenda.destinatarios.web.entity.
	 * DatosEntradaAgendaDestinatario)
	 */
	@Override
	public void grabarEstadisticaConfiguracionModificacionDestinatario(DatosEntradaAgendaDestinatario datos) {
		agendaDestinatariosManager.grabarEstadisticaConfiguracionModificacionDestinatario(datos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei.
	 * AgendaDestinatariosSEI#confirmarEdicionDestinatario(ar.com.santanderrio.
	 * obp.servicios.agenda.destinatarios.web.entity.
	 * DatosEntradaEdicionDestinatarioView)
	 */
	@Override
	public Respuesta<ConfirmacionAltaDestinatarioView> confirmarEdicionDestinatario(
			ConfirmacionAltaDestinatarioView datosEntrada) {
		return agendaDestinatariosManager.obtenerConfirmacionEdicionDestinatario(datosEntrada);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei.
	 * AgendaDestinatariosSEI#
	 * grabarEstadisticaComprobanteModificacionDestinatario(ar.com.santanderrio.
	 * obp.servicios.agenda.destinatarios.web.entity.
	 * DatosEntradaAgendaDestinatario)
	 */
	@Override
	public void grabarEstadisticaComprobanteModificacionDestinatario(DatosEntradaAgendaDestinatario datos) {
		agendaDestinatariosManager.grabarEstadisticaComprobanteModificacionDestinatario(datos);
	}

	/**
	 * Cancelar alta agenda destinatario.
	 */
	@Override
	public void cancelarAltaAgendaDestinatario() {
		LOGGER.info("Post OK: /cancelarAltaAgendaDestinatario");
		altaDestinatarioManager.cancelarAltaAgendaDestinatario();
	}

	/**
	 * Graba la estadistica de la configuracion del alta destinatario por alias
	 * CBU.
	 */
	@Override
	public void grabarEstadisticaConfiguracionAltaDestinatarioAliasCBU() {
		LOGGER.info("Post OK: /iniciarAltaDestinatarioAliasCBU");
		altaDestinatarioManager.grabarEstadisticaConfiguracionAltaDestinatarioAliasCBU();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei.
	 * AgendaDestinatariosSEI#
	 * grabarEstadisticaVerComprobanteAltaDestinatarioAlias()
	 */
	@Override
	public Boolean grabarEstadisticaVerComprobanteAltaDestinatarioAlias() {
		estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_ALTA_DESTINATARIO_ALIAS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei.
	 * AgendaDestinatariosSEI#validarSiPoseeSegundoFactorAutenticacion()
	 */
	@Override
	public Respuesta<Boolean> validarSiPoseeSegundoFactorAutenticacion() {
		return agendaRSAManager.validarSiPoseeSegundoFactorAutenticacion();
	}

}
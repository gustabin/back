/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.impl;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.bo.TraspasoAutomaticoBO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.TraspasoAutomaticoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.TraspasoAutomaticoManager;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.util.IndicadorTraspasoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.view.TraspasoAutomaticoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class TraspasoAutomaticoManagerImpl.
 */
@Component("traspasoAutomatico")
public class TraspasoAutomaticoManagerImpl implements TraspasoAutomaticoManager {

	/** The Constant ESTADO_SOLICITUD_INACTIVA. */
	private static final String ESTADO_SOLICITUD_INACTIVA = "Inactivo";

	/** The Constant ESTADO_SOLICITUD_ACTIVA. */
	private static final String ESTADO_SOLICITUD_ACTIVA = "Activo";

	/** The Constant SOLICITUD_INACTIVA. */
	private static final String SOLICITUD_INACTIVA = "inactivo";

	/** The Constant SOLICITUD_ACTIVA. */
	private static final String SOLICITUD_ACTIVA = "activo";
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TraspasoAutomaticoManagerImpl.class);

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The traspaso automatico BO. */
	@Autowired
	TraspasoAutomaticoBO traspasoAutomaticoBO;

	/** The cuenta manager. */
	@Autowired
	CuentaManager cuentaManager;

	/** The mensaje BO. */
	@Autowired
	MensajeBO mensajeBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.
	 * TraspasoAutomaticoManager#solicitarTraspasoAutomatico(ar.com.santanderrio
	 * .obp.servicios.cuentas.traspaso.web.view.TraspasoAutomaticoView)
	 */
	@Override
	public Respuesta<TraspasoAutomaticoView> solicitarTraspasoAutomatico(TraspasoAutomaticoView traspaso) {

		Respuesta<TraspasoAutomaticoView> respuesta = new Respuesta<TraspasoAutomaticoView>();
		TraspasoAutomaticoView traspasoAutomaticoView = new TraspasoAutomaticoView();
		AbstractCuenta cuenta = cuentaManager.obtenerCuentaById(traspaso.getNumeroCuenta());
		String nroSucursalProducto = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal());
		String nroCuentaProducto = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
		String nroCuentaCompleto = nroSucursalProducto + "-" + nroCuentaProducto;

		traspasoAutomaticoView.setAliasCuenta(cuenta.getAlias());
		traspasoAutomaticoView.setNumeroCuenta(nroCuentaCompleto);
		traspasoAutomaticoView.setEstadoActual(
				cuenta.getTraspasoAutomaticoActivo() ? ESTADO_SOLICITUD_ACTIVA : ESTADO_SOLICITUD_INACTIVA);
		traspasoAutomaticoView.setIsActivo(cuenta.getTraspasoAutomaticoActivo());
		traspasoAutomaticoView.setTipoCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
		traspasoAutomaticoView.setIsPendiente(cuenta.getSolicitudPendienteTraspasoAutomatico());

		if (!cuenta.getSolicitudPendienteTraspasoAutomatico()) {
			estadisticaManager.add(EstadisticasConstants.TRASPASO_AUTOMATICO_ACCESO_SOLICITUD_TRASPASO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			respuesta = respuestaFactory.crearRespuestaOk(traspasoAutomaticoView);
		} else {
			estadisticaManager.add(EstadisticasConstants.TRASPASO_AUTOMATICO_ACCESO_CANCELAR_SOLICITUD_TRASPASO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			respuesta = respuestaFactory.crearRespuestaWarning(traspasoAutomaticoView, "",
					TipoError.WARNING_SOLICITUD_TRASPASO_ACTIVO, StringUtils.EMPTY);
		}
		sesionParametros.setContador(new ContadorIntentos(2));
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.
	 * TraspasoAutomaticoManager#confirmarTraspasoAutomatico(ar.com.santanderrio
	 * .obp.servicios.cuentas.traspaso.web.view.TraspasoAutomaticoView)
	 */
	@Override
	public Respuesta<TraspasoAutomaticoView> confirmarTraspasoAutomatico(TraspasoAutomaticoView traspaso) {

		String codigoEstadistica = EstadisticasConstants.TRASPASO_AUTOMATICO_SOLICITUD_TRASPASO;
		String feedbackEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
		Respuesta<TraspasoAutomaticoView> respuesta = null;

		Cliente cliente = sesionCliente.getCliente();
		AbstractCuenta cuenta = cuentaManager.obtenerCuentaById(traspaso.getNumeroCuenta());
		TraspasoAutomaticoDTO traspasoAutomaticoDTO = new TraspasoAutomaticoDTO();
		traspasoAutomaticoDTO.setCliente(cliente);
		traspasoAutomaticoDTO.setNumeroCuenta(cuenta.getNroCuentaProducto());
		traspasoAutomaticoDTO.setSucursalCuenta(cuenta.getNroSucursal());
		traspasoAutomaticoDTO.setTipoCuenta(cuenta.getTipoCuentaEnum().getCodigo().toString());
		Boolean solicitudPendiente = cuenta.getSolicitudPendienteTraspasoAutomatico();
		Boolean traspasoActivo = cuenta.getTraspasoAutomaticoActivo();
		traspasoAutomaticoDTO.setIndicadorFondosCA(getIndicador(solicitudPendiente, traspasoActivo));

		Respuesta<Void> respuestaTraspaso = traspasoAutomaticoBO.confirmarTraspasoAutomatico(traspasoAutomaticoDTO);

		String feedbackError = SOLICITUD_ACTIVA;
		if (traspasoActivo) {
			feedbackError = SOLICITUD_INACTIVA;
		}
		String accionOK = "realiz\u00F3";
		String mensajeFeedback = CodigoMensajeConstantes.ERROR_SOLICITUD_TRASPASO;
		String estadoFeedback = traspasoAutomaticoDTO.getIndicadorFondosCA().getDescripcion();
		if (solicitudPendiente) {
			estadoFeedback = IndicadorTraspasoEnum.INDICADOR_ACTIVO.getDescripcion();
			if (IndicadorTraspasoEnum.INDICADOR_ACTIVO.equals(traspasoAutomaticoDTO.getIndicadorFondosCA())) {
				estadoFeedback = IndicadorTraspasoEnum.INDICADOR_INACTIVO.getDescripcion();
			}
			accionOK = "cancel\u00F3";

			mensajeFeedback = CodigoMensajeConstantes.ERROR_CANCELAR_SOLICITUD_TRASPASO;

			codigoEstadistica = EstadisticasConstants.TRASPASO_AUTOMATICO_CANCELAR_SOLICITUD_TRASPASO;
		}
		if (EstadoRespuesta.OK.equals(respuestaTraspaso.getEstadoRespuesta())) {

			TraspasoAutomaticoView traspasoAutomaticoView = new TraspasoAutomaticoView();
			traspasoAutomaticoView.setMensajeFeedback(obtenerMensajeFeedbackOK(estadoFeedback, accionOK));
			if (solicitudPendiente) {
				cuenta.setSolicitudPendienteTraspasoAutomatico(!solicitudPendiente);
			} else {
				cuenta.setSolicitudPendienteTraspasoAutomatico(Boolean.TRUE);
			}
			respuesta = respuestaFactory.crearRespuestaOk(traspasoAutomaticoView);

		} else {
			feedbackEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
			TipoError tipoError = TipoError.ERROR_SOLICITUD_TRASPASO_INTENTOS;
			if (!sesionParametros.getContador().permiteReintentar()) {
				tipoError = TipoError.ERROR_SOLICITUD_TRASPASO;
			}
			if (solicitudPendiente) {

				respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, tipoError, mensajeFeedback,
						feedbackError);
			} else {
				respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, tipoError, mensajeFeedback,
						"realizar", feedbackError);
			}
		}
		estadisticaManager.add(codigoEstadistica, feedbackEstadistica);

		return respuesta;
	}

	/**
	 * Gets the indicador. De acuerdo al estado actual obtiene el indicador con
	 * el estado deseado
	 * 
	 * @param solicitudPendiente
	 *            the solicitud pendiente
	 * @param traspasoActivo
	 *            the traspaso activo
	 * @return the indicador
	 */
	private IndicadorTraspasoEnum getIndicador(Boolean solicitudPendiente, Boolean traspasoActivo) {

		Boolean activar;
		if (solicitudPendiente) {
			activar = traspasoActivo;
		} else {
			activar = !traspasoActivo;
		}
		if (!activar) {
			return IndicadorTraspasoEnum.INDICADOR_INACTIVO;
		}
		return IndicadorTraspasoEnum.INDICADOR_ACTIVO;
	}

	/**
	 * Obtener mensaje feedback OK.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @param opcion
	 *            the opcion
	 * @return the string
	 */
	private String obtenerMensajeFeedbackOK(String descripcion, String opcion) {
		Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TRASPASO_AUTOMATICO_FEEDBACK_OK);
		return MessageFormat.format(mensaje.getMensaje(), descripcion, opcion);
	}

}

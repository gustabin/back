/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AliasRsaView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ComprobanteAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ComprobanteBajaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleCBUDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ComprobanteAltaCBUDTO;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

/**
 * Manager de Alias cbu cuenta.
 */
@Component
public class AliasCuentaManagerImpl implements AliasCuentaManager {

	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AliasCuentaManagerImpl.class);

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The alias cuenta BO. */
	@Autowired
	private AliasCuentaBO aliasCuentaBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The Constant REINTENTAR. */
	private static final String REINTENTAR = "reintentar";

	/** The Constant CONTINUAR. */
	private static final String CONTINUAR = "continuar";

	/** The autentificacion manager. */
	@Autowired
	private AutentificacionManager autentificacionManager;

	/** The valor desafio para alta alias. */
	@Value("${TRJCOORD.OPERAINDISTINTO.ALTAALIAS}")
	private String valorDesafioAltaAlias;

	/** The valor desafio para alta alias. */
	@Value("${TRJCOORD.OPERAINDISTINTO.MODIFICAALIAS}")
	private String valorDesafioModifiacionAlias;

	/** The valor desafio para baja alias. */
	@Value("${TRJCOORD.OPERAINDISTINTO.BAJAALIAS}")
	private String valorDesafioBajaAlias;

	/** The valor desafio para baja alias. */
	@Value("${TRJCOORD.OPERAINDISTINTO.REASIGNAALIAS}")
	private String valorDesafioReasignacionAlias;

	/** The valor desafio para agenda de transferencias. */
	@Value("${TRJCOORD.OPERAINDISTINTO.TRANSFERENCIAS_ND}")
	private String valorDesafioTransferenciasAgendadas;

	/** The ayuda TIT transferencia auto. */
	@Value("${TRANSFERENCIA.TIT.AYUDA.AUTO}")
	private String ayudaTITTransferenciaAuto;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager#
	 * obtenerAliasCBU(java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<DetalleCBUView> obtenerAliasCBU(String cbu, String userAgent) {
		LOGGER.info("Obtener Alias CBU");
		String ip = sesionCliente.getIpCliente();
		String dni = sesionParametros.getRegistroSession().getDni();
		sesionParametros.setDetalleCBUAlias(null);
		Respuesta<DetalleCBUView> respuestaAlias = aliasCuentaBO.obtenerAliasCBU(cbu, ip, dni, userAgent);
		sesionParametros.setDetalleCBUAlias(respuestaAlias.getRespuesta());
		return respuestaAlias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager#
	 * continuarAliasCBU(ar.com.santanderrio.obp.servicios.cuentas.web.view.
	 * DetalleCBUView)
	 */
	@Override
	public Respuesta<DetalleCBUView> continuarAliasCBU(DetalleCBUView detalle) {
		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuenta = cliente.getCuenta(detalle.getCbu());
		String numeroCuenta = new IdentificacionCuenta(cuenta.getNroSucursal(), cuenta.getNroCuentaProducto())
				.toString();
		Respuesta<DetalleCBUView> respuesta = aliasCuentaBO.obtenerDatosCliente(detalle.getCbu(),
				detalle.getAliasCbu());
		sesionParametros.setContadorAlias(null);
		sesionParametros.setReasigna(null);
		sesionParametros.setValidacionHash(null);
		if (ISBANStringUtils.isEmptyOrNull(detalle.getAliasCbu())) {
			grabarEstadisticaAliasCBU(EstadisticasConstants.ACCESO_A_CONFIGURACION_ALIAS,
					generarCodigoEstadistica(respuesta.getEstadoRespuesta()), numeroCuenta);
		} else {
			grabarEstadisticaAliasCBU(EstadisticasConstants.ACCESO_A_CONFIGURACION_MODIFICACION_ALIAS,
					generarCodigoEstadistica(respuesta.getEstadoRespuesta()), numeroCuenta);
		}
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			String hash = HashUtils.obtenerHash(crearMapaDeLaVista(respuesta.getRespuesta()));
			sesionParametros.setValidacionHash(hash);
		}
		return respuesta;
	}

	/**
	 * Generar codigo estadistica.
	 *
	 * @param estado
	 *            the estado
	 * @return the string
	 */
	private String generarCodigoEstadistica(EstadoRespuesta estado) {
		if (estado == EstadoRespuesta.OK) {
			return EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
		}
		return EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
	}

	/**
	 * Cargar hash de la vista en sesion.
	 *
	 * @param aliasRsaView
	 *            the alias rsa view
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVista(AliasRsaView aliasRsaView) {
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("cbu", aliasRsaView.getCbu());
		mapaAtributos.put("cuit", aliasRsaView.getCuit());
		return mapaAtributos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager#
	 * confirmacionCrearAliasCBU(ar.com.santanderrio.obp.servicios.cuentas.web.
	 * view.DetalleCBUView, java.lang.String)
	 */
	@Override
	public Respuesta<ComprobanteAltaDestinatarioView> confirmacionCrearAliasCBU(ComprobanteAltaDestinatarioView view,
			String detalleTerminal) {

		LOGGER.info("Confirmar Crear Alias CBU");

		Respuesta<ComprobanteAltaDestinatarioView> respuestaFinal;
		respuestaFinal = validarCreacionAlias(view);
		if (!EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())) {
			return respuestaFinal;
		}

		String hashEnSesion = sesionParametros.getValidacionHash();
		String hash = HashUtils.obtenerHash(crearMapaDeLaVista(view));
		if (hashEnSesion.equals(hash)) {
			DetalleCBUDTO detalleCBUDTO = buildDetalleCBUDTO(view, detalleTerminal);
			Respuesta<ComprobanteAltaCBUDTO> respuestaDTO = confirmacionAliasCBU(detalleCBUDTO);
			if (EstadoRespuesta.OK.equals(respuestaDTO.getEstadoRespuesta())) {
				if (detalleCBUDTO.getReasigna() != null) {
					grabarEstadisticaAliasCBU(EstadisticasConstants.REASIGNACION_ALIAS_CBU,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK, detalleCBUDTO.getNroCuenta());
				} else {
					grabarEstadisticaAliasCBU(EstadisticasConstants.ALTA_ALIAS_CBU,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK, detalleCBUDTO.getNroCuenta());
				}
				return respuestaFactory
						.crearRespuestaOk(new ComprobanteAltaDestinatarioView(respuestaDTO.getRespuesta()));
			} else {
				if (detalleCBUDTO.getReasigna() != null) {
					grabarEstadisticaAliasCBU(EstadisticasConstants.REASIGNACION_ALIAS_CBU,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, detalleCBUDTO.getNroCuenta());
				} else {
					grabarEstadisticaAliasCBU(EstadisticasConstants.ALTA_ALIAS_CBU,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, detalleCBUDTO.getNroCuenta());
				}
			}
			List<ItemMensajeRespuesta> items = respuestaDTO.getItemsMensajeRespuesta();
			items.get(0).setDetalleTipoError(obtenerDetalleAliasCBU(items.get(0).getTipoError(), false));
			return respuestaFactory.crearRespuestaError(ComprobanteAltaDestinatarioView.class, items);
		} else {
			Respuesta<ComprobanteAltaDestinatarioView> respuestaDTO = respuestaFactory.crearRespuestaError(
					StringUtils.EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
			return respuestaDTO;
		}
	}

	/**
	 * Builds the detalle CBUDTO.
	 *
	 * @param view
	 *            the view
	 * @param detalleTerminal
	 *            the detalle terminal
	 * @return the detalle CBUDTO
	 */
	private DetalleCBUDTO buildDetalleCBUDTO(ComprobanteAltaDestinatarioView view, String detalleTerminal) {
		DetalleCBUDTO detalleCBUDTO = new DetalleCBUDTO();
		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuenta = cliente.getCuenta(view.getCbu());
		String numeroCuenta = new IdentificacionCuenta(cuenta.getNroSucursal(), cuenta.getNroCuentaProducto())
				.toString();
		detalleCBUDTO.setCliente(cliente);
		detalleCBUDTO.setCuenta(cuenta);
		detalleCBUDTO.setNroCuenta(numeroCuenta);
		detalleCBUDTO.setTipoCuenta(cuenta.getTipoCuenta());
		detalleCBUDTO.setDetallesTerminal(detalleTerminal);
		detalleCBUDTO.setAliasAnterior(view.getAliasAnterior());
		detalleCBUDTO.setAliasCBU(view.getAliasCbu());
		detalleCBUDTO.setReasigna(sesionParametros.getReasigna());
		detalleCBUDTO.setIp(sesionCliente.getIpCliente());
		detalleCBUDTO.setCbu(view.getCbu());
		detalleCBUDTO.setCuit(view.getCuit());
		return detalleCBUDTO;
	}

	/**
	 * Builds the detalle CBUDTO.
	 *
	 * @param view
	 *            the view
	 * @param detalleTerminal
	 *            the detalle terminal
	 * @return the detalle CBUDTO
	 */
	private DetalleCBUDTO buildDetalleCBUDTO(ComprobanteBajaDestinatarioView view, String detalleTerminal) {
		DetalleCBUDTO detalleCBUDTO = new DetalleCBUDTO();
		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuenta = cliente.getCuenta(view.getCbu());
		String numeroCuenta = new IdentificacionCuenta(cuenta.getNroSucursal(), cuenta.getNroCuentaProducto())
				.toString();
		detalleCBUDTO.setCliente(cliente);
		detalleCBUDTO.setCuenta(cuenta);
		detalleCBUDTO.setNroCuenta(numeroCuenta);
		detalleCBUDTO.setTipoCuenta(cuenta.getTipoCuenta());
		detalleCBUDTO.setDetallesTerminal(detalleTerminal);
		detalleCBUDTO.setAliasAnterior(view.getAliasAnterior());
		detalleCBUDTO.setAliasCBU(view.getAliasCbu());
		detalleCBUDTO.setReasigna(sesionParametros.getReasigna());
		detalleCBUDTO.setIp(sesionCliente.getIpCliente());
		detalleCBUDTO.setCbu(view.getCbu());
		detalleCBUDTO.setCuit(view.getCuit());
		return detalleCBUDTO;
	}

	/**
	 * Obtener detalle alias CBU.
	 *
	 * @param tipoError
	 *            the tipo error
	 * @param esEditar
	 *            the es editar
	 * @return the string
	 */
	private String obtenerDetalleAliasCBU(String tipoError, boolean esEditar) {
		boolean condicionAgregar = StringUtils.equals(tipoError, TipoError.ERROR_YA_TIENE_ALIAS.getDescripcion());
		boolean condicionEditar = StringUtils.equals(tipoError, TipoError.ERROR_ALIAS_USADO.getDescripcion());
		if (!((condicionAgregar && !esEditar) || (condicionEditar && esEditar))) {
			if (sesionParametros.getContadorAlias().permiteReintentar()) {
				return REINTENTAR;
			} else {
				sesionParametros.setContadorAlias(null);
			}
		}
		return CONTINUAR;
	}

	/**
	 * Grabar estadistica alias CBU.
	 *
	 * @param codigoEstadistica
	 *            the codigo estadistica
	 * @param codigoError
	 *            the codigo error
	 * @param numeroCuenta
	 *            the numero cuenta
	 */
	private void grabarEstadisticaAliasCBU(String codigoEstadistica, String codigoError, String numeroCuenta) {

		estadisticaManager.add(codigoEstadistica, codigoError, numeroCuenta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager#
	 * grabarEstadisticaComprobanteAltaAliasCBU(java.lang.String)
	 */
	@Override
	public void grabarEstadisticaComprobanteAltaAliasCBU(String numeroCuenta) {

		grabarEstadisticaAliasCBU(EstadisticasConstants.COMPROBANTE_ALIAS_CBU,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK, numeroCuenta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager#
	 * grabarEstadisticaComprobanteReasignacionAliasCBU(java.lang.String)
	 */
	@Override
	public void grabarEstadisticaComprobanteReasignacionAliasCBU(String numeroCuenta) {

		grabarEstadisticaAliasCBU(EstadisticasConstants.ESTADISTICAS_COMPROBANTE_REASIGNACION_ALIAS_CBU,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK, numeroCuenta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager#
	 * grabarEstadisticaComprobanteEditarAliasCBU(java.lang.String)
	 */
	@Override
	public void grabarEstadisticaComprobanteEditarAliasCBU(String numeroCuenta) {

		grabarEstadisticaAliasCBU(EstadisticasConstants.COMPROBANTE_MODIFICACION_ALIAS_CBU,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK, numeroCuenta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager#
	 * grabadoEstadisticaAyuda(ar.com.santanderrio.obp.servicios.cuentas.web.
	 * view.DetalleCBUView)
	 */
	@Override
	public void grabadoEstadisticaAyuda(DetalleCBUView detalle) {
		String estadistica;
		if (detalle.getAliasAnterior() == null) {
			estadistica = EstadisticasConstants.ACCESO_A_AYUDA_ALIAS;
		} else {
			estadistica = EstadisticasConstants.ACCESO_A_AYUDA_MODIFICACION_ALIAS;
		}
		grabarEstadisticaAliasCBU(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK, detalle.getNumeroCuenta());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager#
	 * confirmacionEditarAliasCBU(ar.com.santanderrio.obp.servicios.cuentas.web.
	 * view.DetalleCBUView, java.lang.String)
	 */
	@Override
	public Respuesta<ComprobanteAltaDestinatarioView> confirmacionEditarAliasCBU(
			ComprobanteAltaDestinatarioView detalle, String detallesTerminal) {
		LOGGER.info("Confirmar Modificar Alias CBU");
		Respuesta<ComprobanteAltaDestinatarioView> respuestaFinal;
		respuestaFinal = validarModificacionAlias(detalle);
		if (!EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())) {
			LOGGER.info("Desafio Confirmar Modificar Alias CBU");
			return respuestaFinal;
		}

		DetalleCBUDTO detalleCBUDTO = buildDetalleCBUDTO(detalle, detallesTerminal);
		Respuesta<ComprobanteAltaCBUDTO> respuestaDTO = confirmacionAliasCBU(detalleCBUDTO);
		String codigoEstadistica = EstadisticasConstants.EDITAR_ALIAS_CBU;
		if (detalleCBUDTO.getReasigna() != null) {
			codigoEstadistica = EstadisticasConstants.REASIGNACION_ALIAS_CBU;
		}
		if (EstadoRespuesta.OK.equals(respuestaDTO.getEstadoRespuesta())) {
			grabarEstadisticaAliasCBU(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK,
					detalleCBUDTO.getNroCuenta());
			return respuestaFactory.crearRespuestaOk(new ComprobanteAltaDestinatarioView(respuestaDTO.getRespuesta()));
		}
		grabarEstadisticaAliasCBU(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
				detalleCBUDTO.getNroCuenta());
		List<ItemMensajeRespuesta> item = respuestaDTO.getItemsMensajeRespuesta();
		item.get(0).setDetalleTipoError(obtenerDetalleAliasCBU(item.get(0).getTipoError(), true));
		return respuestaFactory.crearRespuestaError(ComprobanteAltaDestinatarioView.class, item);
	}

	/**
	 * Confirmacion alias CBU.
	 *
	 * @param detalle
	 *            the detalle
	 * @return the respuesta
	 */
	private Respuesta<ComprobanteAltaCBUDTO> confirmacionAliasCBU(DetalleCBUDTO detalle) {
		if (sesionParametros.getContadorAlias() == null) {
			sesionParametros.setContadorAlias(new ContadorIntentos(2));
		}
		Respuesta<ComprobanteAltaCBUDTO> respuesta = aliasCuentaBO.confirmarAltaAlias(detalle);
		// Solo devuelve warning cuando el servicio devuelve CONFIRMA
		if (EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {
			sesionParametros.setReasigna(respuesta.getRespuesta().getReasigna());
			sesionParametros.setContadorAlias(new ContadorIntentos(3));
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager#
	 * eliminarAliasCBU(ar.com.santanderrio.obp.servicios.cuentas.web.view.
	 * DetalleCBUView)
	 */
	@Override
	public Respuesta<DetalleCBUView> eliminarAliasCBU(DetalleCBUView detalle) {
		sesionParametros.setValidacionHash(null);
		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuenta = cliente.getCuenta(detalle.getCbu());
		sesionParametros.setContadorAlias(new ContadorIntentos(2));
		String numeroCuenta = new IdentificacionCuenta(cuenta.getNroSucursal(), cuenta.getNroCuentaProducto())
				.toString();
		Respuesta<DetalleCBUView> respuesta = aliasCuentaBO.obtenerDatosCliente(detalle.getCbu(),
				detalle.getAliasCbu());
		grabarEstadisticaAliasCBU(EstadisticasConstants.ACCESO_ESTADISTICAS_ELIMINAR_ALIAS_CBU,
				generarCodigoEstadistica(respuesta.getEstadoRespuesta()), numeroCuenta);
		if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_SERVICIO_ALIAS_CBU,
					CodigoMensajeConstantes.CODIGO_ASIGNACION_ALIAS_ERROR_GENERICO);
		}
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			String hash = HashUtils.obtenerHash(crearMapaDeLaVista(respuesta.getRespuesta()));
			sesionParametros.setValidacionHash(hash);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager#
	 * confirmacionBajaAliasCBU(ar.com.santanderrio.obp.servicios.cuentas.web.
	 * view.DetalleCBUView, java.lang.String)
	 */
	@Override
	public Respuesta<ComprobanteBajaDestinatarioView> confirmacionBajaAliasCBU(ComprobanteBajaDestinatarioView view,
			String datosTerminal) {

		Respuesta<ComprobanteBajaDestinatarioView> respuestaFinal;
		respuestaFinal = validarRsaBajaAlias(view);
		if (!EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())) {
			return respuestaFinal;
		}
		String hashEnSesion = sesionParametros.getValidacionHash();
		String hash = HashUtils.obtenerHash(crearMapaDeLaVista(view));
		if (hashEnSesion.equals(hash)) {
			DetalleCBUDTO detalleCBUDTO = buildDetalleCBUDTO(view, datosTerminal);
			Respuesta<ComprobanteAltaCBUDTO> respuesta = aliasCuentaBO.confirmarBajaAliasCBU(detalleCBUDTO);
			grabarEstadisticaAliasCBU(EstadisticasConstants.ESTADISTICAS_ELIMINAR_ALIAS_CBU,
					generarCodigoEstadistica(respuesta.getEstadoRespuesta()), view.getNumeroCuenta());
			if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
				if (!sesionParametros.getContadorAlias().permiteReintentar()) {
					return respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
							TipoError.ERROR_ELIMINAR_ALIAS_CBU_INTENTOS,
							CodigoMensajeConstantes.ERROR_ELIMINAR_ALIAS_CBU);
				}
				return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_ELIMINAR_ALIAS_CBU,
						CodigoMensajeConstantes.ERROR_ELIMINAR_ALIAS_CBU);
			}
			return respuestaFactory
					.crearRespuestaOk(new ComprobanteBajaDestinatarioView(respuesta.getRespuesta(), view));

		} else {
			Respuesta<ComprobanteBajaDestinatarioView> respuestaDTO = respuestaFactory.crearRespuestaError(
					StringUtils.EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
			return respuestaDTO;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager#
	 * grabarEstadisticaComprobanteEliminarAliasCBU(java.lang.String)
	 */
	@Override
	public void grabarEstadisticaComprobanteEliminarAliasCBU(String numeroCuenta) {

		grabarEstadisticaAliasCBU(EstadisticasConstants.ESTADISTICAS_COMPROBANTE_ELIMINAR_ALIAS_CBU,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK, numeroCuenta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager#
	 * comprobanteAliasCBU(ar.com.santanderrio.obp.servicios.cuentas.web.view.
	 * DetalleCBUView)
	 */
	@Override
	public void comprobanteAliasCBU(DetalleCBUView view) {
		String reasigna = sesionParametros.getReasigna();
		if (StringUtils.isBlank(reasigna)) {
			if (view.getAliasAnterior() == null) {
				grabarEstadisticaComprobanteAltaAliasCBU(view.getNumeroCuenta());
			} else {
				grabarEstadisticaComprobanteEditarAliasCBU(view.getNumeroCuenta());
			}
		} else {
			grabarEstadisticaAliasCBU(EstadisticasConstants.ESTADISTICAS_COMPROBANTE_REASIGNACION_ALIAS_CBU,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK, view.getNumeroCuenta());
		}
	}

	/**
	 * Validar creacion alias.
	 *
	 * @param comprobanteView
	 *            the comprobante view
	 * @return the respuesta
	 */
	private Respuesta<ComprobanteAltaDestinatarioView> validarCreacionAlias(
			ComprobanteAltaDestinatarioView comprobanteView) {

		AutentificacionDTO autentificacionRSA = new AutentificacionDTO();
		if (autentificacionManager.necesitaNuevoDesafio(comprobanteView.getDesafio())) {

			String valorDesafio = isReasignacion() ? valorDesafioReasignacionAlias : valorDesafioAltaAlias;
			autentificacionRSA.setOperacion(Integer.parseInt(valorDesafio));

			// carga de autentificationDTO
			autentificacionRSA.setRsaDTO(comprobanteView);

			autentificacionRSA.setCoordenadasOperacion(new CoordenadasOperacionDTO(new PedidoCoordenada(
					TarjetaCoordenadaOperacionEnum.VALIDACION, sesionCliente.getIpCliente())));

			// carga de estadisticas
			asignarEstadisticasValidacionCreacionAlias(autentificacionRSA);

		} else {
			autentificacionRSA = comprobanteView.getDesafio();
		}
		Respuesta<AutentificacionDTO> rstaAutentificacion = autentificacionManager
				.ejecutarValidacionRSA(autentificacionRSA);
		comprobanteView.setDesafio(rstaAutentificacion.getRespuesta());
		return respuestaFactory.transformar(comprobanteView, rstaAutentificacion);
	}

	/**
	 * Asignar estadisticas validacion creacion alias.
	 *
	 * @param autentificacionRSA
	 *            the autentificacion RSA
	 */
	private void asignarEstadisticasValidacionCreacionAlias(AutentificacionDTO autentificacionRSA) {

		if (isReasignacion()) {
			asignarEstadisticasRSAReasignacionAlias(autentificacionRSA);
		} else {
			asignarEstadisticasRSAAltaAlias(autentificacionRSA);
		}
	}

	/**
	 * Checks if is reasignacion.
	 *
	 * @return true, if is reasignacion
	 */
	private boolean isReasignacion() {
		return !StringUtils.isEmpty(sesionParametros.getReasigna());
	}

	/**
	 * Validar modificacion alias.
	 *
	 * @param comprobanteView
	 *            the comprobante view
	 * @return the respuesta
	 */
	private Respuesta<ComprobanteAltaDestinatarioView> validarModificacionAlias(
			ComprobanteAltaDestinatarioView comprobanteView) {

		AutentificacionDTO autentificacionRSA = new AutentificacionDTO();
		if (autentificacionManager.necesitaNuevoDesafio(comprobanteView.getDesafio())) {

			String valorDesafio = isReasignacion() ? valorDesafioReasignacionAlias : valorDesafioModifiacionAlias;
			autentificacionRSA.setOperacion(Integer.parseInt(valorDesafio));

			// carga de autentificationDTO
			autentificacionRSA.setRsaDTO(comprobanteView);

			autentificacionRSA.setCoordenadasOperacion(new CoordenadasOperacionDTO(new PedidoCoordenada(
					TarjetaCoordenadaOperacionEnum.VALIDACION, sesionCliente.getIpCliente())));

			// carga de estadisticas
			asignarEstadisticasValidacionModificacionAlias(autentificacionRSA);

		} else {
			autentificacionRSA = comprobanteView.getDesafio();
		}
		Respuesta<AutentificacionDTO> rstaAutentificacion = autentificacionManager
				.ejecutarValidacionRSA(autentificacionRSA);
		comprobanteView.setDesafio(rstaAutentificacion.getRespuesta());
		return respuestaFactory.transformar(comprobanteView, rstaAutentificacion);
	}

	/**
	 * Validar rsa baja alias.
	 *
	 * @param comprobanteView
	 *            the comprobante view
	 * @return the respuesta
	 */
	private Respuesta<ComprobanteBajaDestinatarioView> validarRsaBajaAlias(
			ComprobanteBajaDestinatarioView comprobanteView) {

		AutentificacionDTO autentificacionRSA = new AutentificacionDTO();
		if (autentificacionManager.necesitaNuevoDesafio(comprobanteView.getDesafio())) {

			Integer operacion = Integer.parseInt(valorDesafioBajaAlias);
			autentificacionRSA.setOperacion(operacion);

			// carga de autentificationDTO
			autentificacionRSA.setRsaDTO(comprobanteView);

			autentificacionRSA.setCoordenadasOperacion(new CoordenadasOperacionDTO(new PedidoCoordenada(
					TarjetaCoordenadaOperacionEnum.VALIDACION, sesionCliente.getIpCliente())));

			// carga de estadisticas
			asignarEstadisticasRSABajaAlias(autentificacionRSA);

		} else {
			autentificacionRSA = comprobanteView.getDesafio();
		}
		Respuesta<AutentificacionDTO> rstaAutentificacion = autentificacionManager
				.ejecutarValidacionRSA(autentificacionRSA);
		comprobanteView.setDesafio(rstaAutentificacion.getRespuesta());
		return respuestaFactory.transformar(comprobanteView, rstaAutentificacion);
	}

	/**
	 * Asignar estadisticas RSA baja alias.
	 *
	 * @param autentificacionRSA
	 *            the autentificacion RSA
	 */
	private void asignarEstadisticasRSABajaAlias(AutentificacionDTO autentificacionRSA) {
		autentificacionRSA.setCodigoEstadisticaSolicitudCoordenadas(
				EstadisticasConstants.VALIDACION_RSA_SOLICITUD_COORDENADAS_BAJA_ALIAS_CBU);
		autentificacionRSA.setCodigoEstadisticaValidacionCoordenadas(
				EstadisticasConstants.VALIDACION_RSA_VALIDACION_COORDENADAS_BAJA_ALIAS_CBU);
		autentificacionRSA.setCodigoEstadisticaSolicitudToken(
				EstadisticasConstants.VALIDACION_RSA_SOLICITUD_TOKEN_BAJA_ALIAS_CBU);
		autentificacionRSA.setCodigoEstadisticaValidacionToken(
				EstadisticasConstants.VALIDACION_RSA_VALIDACION_TOKEN_BAJA_ALIAS_CBU);
		autentificacionRSA.setCodigoEstadisticaSinDesafios(EstadisticasConstants.RSA_BAJA_ALIAS_CBU_SIN_DESAFIOS);
	}

	/**
	 * Asignar estadisticas validacion modificacion alias.
	 *
	 * @param autentificacionRSA
	 *            the autentificacion RSA
	 */
	private void asignarEstadisticasValidacionModificacionAlias(AutentificacionDTO autentificacionRSA) {

		if (isReasignacion()) {
			asignarEstadisticasRSAReasignacionAlias(autentificacionRSA);
		} else {
			asignarEstadisticasRSAModificacionAlias(autentificacionRSA);
		}
	}

	/**
	 * Asignar estadisticas RSA modificacion alias.
	 *
	 * @param autentificacionRSA
	 *            the autentificacion RSA
	 */
	private void asignarEstadisticasRSAModificacionAlias(AutentificacionDTO autentificacionRSA) {
		autentificacionRSA.setCodigoEstadisticaSolicitudCoordenadas(
				EstadisticasConstants.VALIDACION_RSA_SOLICITUD_COORDENADAS_MODIFICACION_ALIAS_CBU);
		autentificacionRSA.setCodigoEstadisticaValidacionCoordenadas(
				EstadisticasConstants.VALIDACION_RSA_VALIDACION_COORDENADAS_MODIFICACION_ALIAS_CBU);
		autentificacionRSA.setCodigoEstadisticaSolicitudToken(
				EstadisticasConstants.VALIDACION_RSA_SOLICITUD_TOKEN_MODIFICACION_ALIAS_CBU);
		autentificacionRSA.setCodigoEstadisticaValidacionToken(
				EstadisticasConstants.VALIDACION_RSA_VALIDACION_TOKEN_MODIFICACION_ALIAS_CBU);
		autentificacionRSA
				.setCodigoEstadisticaSinDesafios(EstadisticasConstants.RSA_MODIFICACION_ALIAS_CBU_SIN_DESAFIOS);
	}

	/**
	 * Asignar estadisticas RSA reasignacion alias.
	 *
	 * @param autentificacionRSA
	 *            the autentificacion RSA
	 */
	private void asignarEstadisticasRSAReasignacionAlias(AutentificacionDTO autentificacionRSA) {
		autentificacionRSA.setCodigoEstadisticaSolicitudCoordenadas(
				EstadisticasConstants.VALIDACION_RSA_SOLICITUD_COORDENADAS_REASIGNACION_ALIAS_CBU);
		autentificacionRSA.setCodigoEstadisticaValidacionCoordenadas(
				EstadisticasConstants.VALIDACION_RSA_VALIDACION_COORDENADAS_REASIGNACION_ALIAS_CBU);
		autentificacionRSA.setCodigoEstadisticaSolicitudToken(
				EstadisticasConstants.VALIDACION_RSA_SOLICITUD_TOKEN_REASIGNACION_ALIAS_CBU);
		autentificacionRSA.setCodigoEstadisticaValidacionToken(
				EstadisticasConstants.VALIDACION_RSA_VALIDACION_TOKEN_REASIGNACION_ALIAS_CBU);
		autentificacionRSA
				.setCodigoEstadisticaSinDesafios(EstadisticasConstants.RSA_REASIGNACION_ALIAS_CBU_SIN_DESAFIOS);
	}

	/**
	 * Asignar estadisticas RSA alta alias.
	 *
	 * @param autentificacionRSA
	 *            the autentificacion RSA
	 */
	private void asignarEstadisticasRSAAltaAlias(AutentificacionDTO autentificacionRSA) {
		autentificacionRSA.setCodigoEstadisticaSolicitudCoordenadas(
				EstadisticasConstants.VALIDACION_RSA_SOLICITUD_COORDENADAS_ALTA_ALIAS_CBU);
		autentificacionRSA.setCodigoEstadisticaValidacionCoordenadas(
				EstadisticasConstants.VALIDACION_RSA_VALIDACION_COORDENADAS_ALTA_ALIAS_CBU);
		autentificacionRSA.setCodigoEstadisticaSolicitudToken(
				EstadisticasConstants.VALIDACION_RSA_SOLICITUD_TOKEN_ALTA_ALIAS_CBU);
		autentificacionRSA.setCodigoEstadisticaValidacionToken(
				EstadisticasConstants.VALIDACION_RSA_VALIDACION_TOKEN_ALTA_ALIAS_CBU);
		autentificacionRSA.setCodigoEstadisticaSinDesafios(EstadisticasConstants.RSA_ALTA_ALIAS_CBU_SIN_DESAFIOS);
	}

}

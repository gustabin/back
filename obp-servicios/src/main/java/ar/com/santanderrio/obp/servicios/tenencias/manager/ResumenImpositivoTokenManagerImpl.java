/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.manager;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;
import ar.com.santanderrio.obp.servicios.tenencias.bo.ResumenImpositivoToken;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TenenciasPMCView;
import ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternosManager;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * The Class ResumenImpositivoTokenManagerImpl.
 */
@Component
public class ResumenImpositivoTokenManagerImpl extends AbstractTokenEntesExternosManager
		implements ResumenImpositivoTokenManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ResumenImpositivoTokenManagerImpl.class);

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The autentificacion manager. */
	@Autowired
	private AutentificacionManager autentificacionManager;

	/** The respuestaFactory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The Mensaje dao. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The ResumenImpositivoToken. */
	@Autowired
	ResumenImpositivoToken tokenBO;

	/** The valor desafio. */
	@Value("${TRJCOORD.OPERAINDISTINTO.TENENCIAS}")
	private String desafioTenencias;

	/** The url cancelar. */
	@Value("${PMCNUEVOPAGO.URLCANCELAR}")
	private String urlCancelar;

	/** The url error operacion. */
	@Value("${PMCNUEVOPAGO.URLERROROPERACION}")
	private String urlErrorOperacion;

	/** The url volver. */
	@Value("${PMCNUEVOPAGO.URLVOLVER}")
	private String urlVolver;

	/** The url error. */
	@Value("${PMCNUEVOPAGO.URLERROR}")
	private String urlError;

	/** The url. */
	@Value("${PMCNUEVOPAGO.URLPAGOBANELCO}")
	private String url;

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tenencias.manager.ResumenImpositivoTokenManager#obtenerTokenEncriptado(ar.com.santanderrio.obp.servicios.tenencias.entity.TenenciasPMCView, org.apache.cxf.jaxrs.ext.MessageContext)
	 */
	@Override
	public Respuesta<TenenciasPMCView> obtenerTokenEncriptado(TenenciasPMCView tenenciasView, MessageContext mc) {
		LOGGER.info("Ejecutando validacion de RSA.");
		Respuesta<TenenciasPMCView> respuesta = null;
		respuesta = ejecutarValidacionRSA(tenenciasView);
		if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			return respuesta;
		}
		LOGGER.info("Validacion de token/coordenadas correcta para saltar a PagoMisCuentas.");
		TokenExternoDTO tokenExternoDTO = new TokenExternoDTO(sesionCliente.getCliente());
		Respuesta<TokenView> respuestaToken = armarRespuesta(mc, tokenBO, tokenExternoDTO);
		Respuesta<TenenciasPMCView> respuestaTenencias = respuestaFactory.crearRespuestaOk(TenenciasPMCView.class);
		TenenciasPMCView respuestaView = new TenenciasPMCView();
		respuestaView.setTipoOperacion(tenenciasView.getTipoOperacion());
		setearCamposRespuesta(respuestaView, respuestaToken.getRespuesta());
		respuestaTenencias.setRespuesta(respuestaView);
		return respuestaTenencias;
	}

	/**
	 * Setear campos respuesta.
	 *
	 * @param respuestaView
	 *            the respuesta view
	 * @param respuestaToken
	 *            the respuesta token
	 */
	private void setearCamposRespuesta(TenenciasPMCView respuestaView, TokenView respuestaToken) {
		respuestaView.setUrlCancelar(urlCancelar);
		respuestaView.setUrlErrorOperacion(urlErrorOperacion);
		respuestaView.setUrlVolver(urlVolver);
		respuestaView.setUrlError(urlError);
		respuestaView.setUrl(url);
		respuestaView.setMensaje(buscarMensaje(CodigoMensajeConstantes.SALTO_A_PMC));
		respuestaView.setTokenView(respuestaToken);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tenencias.manager.ResumenImpositivoTokenManager#habilitado()
	 */
	@Override
	public Respuesta<Boolean> habilitado() {
		LOGGER.info("Validar si el usuario tiene los desafios previo a solicitarlos para generar VEP.");
		
		ItemMensajeRespuesta mensajeRespuesta = new ItemMensajeRespuesta();
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		autentificacionDTO.setOperacion(Integer.parseInt(desafioTenencias));
		Respuesta<Boolean> respuesta = autentificacionManager
				.tieneAlgunDesafioHabilitadoParaLaOperacion(autentificacionDTO);
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			mensajeRespuesta.setMensaje(buscarMensaje(CodigoMensajeConstantes.ACCESO_A_GENERACION_VEP));
			respuesta.setItemMensajeRespuesta(Arrays.asList(mensajeRespuesta));
		}
		return respuesta;
	}

	/**
	 * Ejecutar validacion RSA.
	 *
	 * @param tenenciasView
	 *            the tenencias view
	 * @return the respuesta
	 */
	private Respuesta<TenenciasPMCView> ejecutarValidacionRSA(TenenciasPMCView tenenciasView) {
		LOGGER.info("Se ejecuta la validacion por RSA");
		AutentificacionDTO autentificacionDTO;

		Respuesta<TenenciasPMCView> estadoDesafio = autentificacionManager
				.verificarEstadoDesafio(tenenciasView.getDesafio(), Integer.parseInt(desafioTenencias));
		if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = tenenciasView.getDesafio();
		} else if (EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = generarAutentificacionDTO(tenenciasView);
		} else {
			return estadoDesafio;
		}

		// Transformacion respuesta RSA
		Respuesta<AutentificacionDTO> rstaAutentificacionDTO = autentificacionManager
				.ejecutarValidacionRSA(autentificacionDTO);
		tenenciasView.setDesafio(rstaAutentificacionDTO.getRespuesta());
		return respuestaFactory.transformar(tenenciasView, rstaAutentificacionDTO);
	}

	/**
	 * Generar autentificacion DTO.
	 *
	 * @param tenenciasView
	 *            the tenencias view
	 * @return the autentificacion DTO
	 */
	private AutentificacionDTO generarAutentificacionDTO(TenenciasPMCView tenenciasView) {
		LOGGER.info("Se carga la informacion de autentificationDTO");
		Integer operacion = Integer.parseInt(desafioTenencias);
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		autentificacionDTO.setOperacion(operacion);
		autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
				new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));
		// Estadisticas
		autentificacionDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOLICITUD_TOKEN_TENENCIAS);
		autentificacionDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.VALIDACION_TOKEN_TENENCIAS);
		autentificacionDTO
				.setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.SOLICITUD_COORDENADAS_TENENCIAS);
		autentificacionDTO
				.setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.VALIDACION_COORDENADAS_TENENCIAS);
		autentificacionDTO.setRsaDTO(tenenciasView);
		return autentificacionDTO;
	}

	/**
	 * Retorna el mensaje de error desde el dao a partir de un codigo de error.
	 * 
	 * @param codigoError
	 *            the codigo error
	 * @return the string
	 */
	private String buscarMensaje(String codigoError) {
		String mensaje = "";
		if (StringUtils.isNotEmpty(codigoError)) {
			Mensaje msg = mensajeBO.obtenerMensajePorCodigo(codigoError);
			mensaje = msg.getMensaje();
		}
		return mensaje;
	}

}

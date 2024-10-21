package ar.com.santanderrio.obp.servicios.prestamos.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.TokenOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamosRsaBO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.SimuladorPrestamoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.SolicitudPrestamoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PrestamosUtils;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserResponseData;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;

@Component
public class PrestamosRsaBOImpl implements PrestamosRsaBO {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamosRsaBOImpl.class);

	private static final int POS_CAMBIO_CLAVE = 0;

	private static final int POS_CAMBIO_TOKEN = 1;

	@Autowired
	private RsaManager rsaManager;

	@Autowired
	private AutentificacionManager autentificacionManager;

	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The mya BO. */
	@Autowired
	protected MyaBO myaBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	@Autowired
	private ClienteBO clienteBO;

	/** The respuesta Factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The valor desafio. */
	@Value("${TRJCOORD.OPERAINDISTINTO.PRESTAMOS}")
	private String valorDesafioPrestamo;

	@Override
	public Respuesta<SimuladorPrestamoDTO> validarRsa(SolicitudPrestamoDTO solicitudPrestamoDTO, String tokenPrisma,
			String fase, boolean existeDesafioEnCurso) {

		SimuladorPrestamoDTO simuladorPrestamoDTO = crearSimuladorDTO(solicitudPrestamoDTO, fase);
		return ejecutarValidacionRSA(simuladorPrestamoDTO, tokenPrisma, existeDesafioEnCurso);
	}

	private SimuladorPrestamoDTO crearSimuladorDTO(SolicitudPrestamoDTO solicitud, String fase) {
		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuenta = cliente.getCuentaSiContieneNumero(
				solicitud.getCuenta().getNumero().substring(5, solicitud.getCuenta().getNumero().length()));
		SimuladorPrestamoDTO simuladorDTO = new SimuladorPrestamoDTO();

		cargarCambioDatosMyA(cliente, simuladorDTO);

		simuladorDTO.setImporteSeleccionado(solicitud.getMontoTotal().toString());
		simuladorDTO.setCuentaSeleccionada(cuenta);
		simuladorDTO.setFechaSeleccionada(solicitud.getFechaPrimerVencimiento().toString()); // verificar formato
		simuladorDTO.setMaxImpPrest(ISBANStringUtils.ajustadorBigDecimalIatx(solicitud.getDisponible(), 17, 4));
		simuladorDTO.setTipoOperacion(OperacionesRSAEnum.PRESTAMOS);
		simuladorDTO.setValidarBloqueo(Boolean.TRUE);

		simuladorDTO.setTipoPrestamo(solicitud.getTipoOferta().toRSAString());
		simuladorDTO.setFase(fase); // fase encolar

		String codigoProd = solicitud.getCodigoProducto().toString();
		String codigoSubProd = solicitud.getCodigoSubProducto().toString();

		String subproductoFormateado = formatoSubProducto(codigoProd, codigoSubProd);

		LOGGER.info("SubProducto Preaprobado: {}", solicitud.getCodigoSubProducto());
		LOGGER.info("Producto Preaprobado: {}", solicitud.getCodigoProducto());

		simuladorDTO.setSubproductoPrestamo(subproductoFormateado);

		String fechaNacimiento = sesionCliente.getCliente().getFechaNacimiento();
		String anioNacimiento = StringUtils.substring(fechaNacimiento, 0, 4);

		LOGGER.info("Anio Nacimiento Preaprobado: {}", anioNacimiento);
		simuladorDTO.setAnioNacimiento(anioNacimiento);

		cargarAntiguedadCambioClaveYToken(simuladorDTO);

		simuladorDTO.setStopOperacionErrorRSA(true);

		return simuladorDTO;
	}

	private void cargarCambioDatosMyA(Cliente cliente, SimuladorPrestamoDTO simuladorDTO) {
		MyaDTOIn myaDTOIn = new MyaDTOIn();
		myaDTOIn.setNup(cliente.getNup());
		MyaDTOOut datosMyA = myaBO.consultaWsEstadoClienteV3(cliente, myaDTOIn);
		if (datosMyA != null) {
			simuladorDTO.setCantidadDiasCambioCel(PrestamosUtils.calcularDias(datosMyA.getFechaModificadoCelular()));
			simuladorDTO.setCantidadDiasCambioMail(PrestamosUtils.calcularDias(datosMyA.getFechaModificadoEmail()));
		}
	}

	private void cargarAntiguedadCambioClaveYToken(SimuladorPrestamoDTO simuladorDTO) {
		try {
			Respuesta<List<BigDecimal>> rtaAntiguedades = clienteBO
					.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
			if (EstadoRespuesta.OK.equals(rtaAntiguedades.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				List<BigDecimal> antiguedades = rtaAntiguedades.getRespuesta();
				simuladorDTO.setCantDiasUltimoCambioClave(
						PrestamosUtils.obtenerCantidadDiasCambio(antiguedades, POS_CAMBIO_CLAVE));
				simuladorDTO.setCantDiasUltimoCambioToken(
						PrestamosUtils.obtenerCantidadDiasCambio(antiguedades, POS_CAMBIO_TOKEN));
			} else {
				estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} catch (NumberFormatException e) {
			LOGGER.info("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		} catch (BusinessException e) {
			LOGGER.info("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		}
	}

	private String formatoSubProducto(String codigoProd, String codigoSubProd) {
		if (codigoProd != null && codigoSubProd != null) {
			return codigoProd.concat("-".concat(ISBANStringUtils.formateadorConCerosIzq(codigoSubProd, 4)));
		}
		return "";
	}

	private AutentificacionDTO cargarAutentificacionDTO(RsaDTO rsaDTO, String tokenPrisma) {
		AutentificacionDTO autentificacionDTO = PrestamosUtils.cargarAutentificacionDTO(rsaDTO, valorDesafioPrestamo);
		TokenOperacionDTO tokenOperacion = new TokenOperacionDTO();
		tokenOperacion.setIngresoToken(tokenPrisma);
		autentificacionDTO.setTokenOperacion(tokenOperacion);
		autentificacionDTO.setTipoDesafio(TipoDesafioEnum.TOKEN);
		return autentificacionDTO;
	}

	private Respuesta<SimuladorPrestamoDTO> ejecutarValidacionRSA(SimuladorPrestamoDTO simuladorPrestamoDTO,
			String tokenPrisma, boolean existeDesafioEnCurso) {
		LOGGER.info("INICIO validacion por PRESTAMO");

		AutentificacionDTO autentificacionDTO = null;

		if (existeDesafioEnCurso)
			autentificacionDTO = cargarAutentificacionDTO(simuladorPrestamoDTO, tokenPrisma);

		if (!sesionCliente.getTieneTokenRSA()) {
			LOGGER.info("RSA Offline no permite encolar el prestamo.");
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.RSA_OFFLINE,
					CodigoMensajeConstantes.PRESTAMO_DENY_RSA);
		}

		Respuesta<SimuladorPrestamoDTO> estadoDesafio = autentificacionManager
				.verificarEstadoDesafio(autentificacionDTO, Integer.valueOf(valorDesafioPrestamo));
		LOGGER.info("*ESTADO DESAFIO: " + estadoDesafio);

		if (!EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
			// nuevo desafio
			autentificacionDTO = cargarAutentificacionDTO(simuladorPrestamoDTO, tokenPrisma);
		}

		// Transformacion respuesta RSA
		Respuesta<AutentificacionDTO> rstaAutentificacion = autentificacionManager
				.ejecutarValidacionRSA(autentificacionDTO);

		if (EstadoRespuesta.ERROR.equals(rstaAutentificacion.getEstadoRespuesta())) {
			if (TipoError.SIN_METODO_DESAFIO.toString()
					.equals(rstaAutentificacion.getItemsMensajeRespuesta().get(0).getTipoError())) {
				return respuestaFactory.crearRespuestaErrorPersonalizado(SimuladorPrestamoDTO.class,
						rstaAutentificacion.getItemsMensajeRespuesta().get(0).getMensaje(),
						TipoError.SIN_METODO_DESAFIO.toString());
			} else if (rstaAutentificacion.getRespuesta() != null
					&& rstaAutentificacion.getRespuesta().getBloquearUsuario()) {
				LOGGER.info("Inicio de bloqueo de usuario.");
				Respuesta<RsaUpdateUserResponseData> responseBloqueoUsuario = rsaManager
						.updateUser(new RsaUpdateUserRequestData("LOCKOUT"));
				if (EstadoRespuesta.OK.equals(responseBloqueoUsuario.getEstadoRespuesta())) {
					return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.RSA_BLOQUEAR_USUARIO,
							CodigoMensajeConstantes.RSA_LOGIN_USUARIO_BLOQUEADO);
				} else {
					LOGGER.info("Error al bloquear el usuario");
					return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.RSA_OFFLINE,
							CodigoMensajeConstantes.ERROR_GENERICO_RSA_OFFLINE);
				}
			}
		}
		Respuesta<SimuladorPrestamoDTO> respuesta = respuestaFactory.transformar(simuladorPrestamoDTO,
				rstaAutentificacion);

		LOGGER.info("FIN validacion por PRESTAMO");
		return respuesta;
	}
}

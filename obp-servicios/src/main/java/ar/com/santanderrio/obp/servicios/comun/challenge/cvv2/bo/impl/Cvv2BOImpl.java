/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.challenge.cvv2.bo.impl;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.bo.impl.AutentificacionBOImpl;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.Cvv2OperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.cvv2.bo.Cvv2BO;
import ar.com.santanderrio.obp.servicios.comun.challenge.cvv2.dao.Cvv2DAO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class Cvv2BOImpl.
 */
@Component
public class Cvv2BOImpl extends AutentificacionBOImpl implements Cvv2BO {

	/** The Constant CVV2LEN_AMEX. */
	public static final String CVV2LEN_AMEX = "4";

	/** The Constant CVV2LEN_VISA. */
	public static final String CVV2LEN_VISA = "3";

	/** The Constant TIPOCTA_AMEX. */
	public static final String TIPOCTA_AMEX = "42";

	/** The Constant TIPOCTA_DEB. */
	public static final String TIPOCTA_DEB = "05";

	/** The Constant TIPOCTA_VISA. */
	public static final String TIPOCTA_VISA = "07";
	
	/** The Constant TIPOCTA_MASTER. */
	public static final String TIPOCTA_MASTER = "06";

	/** The Constant COD_TIT_A. */
	private static final String COD_TIT_A = "A";

	/** The Constant COD_TIT_T. */
	private static final String COD_TIT_T = "T";

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Cvv2BOImpl.class);

	/** The Constant MSJ_EJECUCION_CVV2_CON_ERRORES. */
	private static final String MSJ_EJECUCION_CVV2_CON_ERRORES = "Ha ejecutado la validación CVV2 con errores";

	/** The Constant MSJ_EJECUCION_CVV2_SATISFACTORIA. */
	private static final String MSJ_EJECUCION_CVV2_SATISFACTORIA = "Ha ejecutado la validación CVV2 satisfactoriamente";

	/** The Constant MSJ_EJECUTANDO_CVV2. */
	private static final String MSJ_EJECUTANDO_CVV2 = "Validando CVV2...";

	/** The Constant MSJ_ERROR_CLIENTE_SIN_CVV2. */
	private static final String MSJ_ERROR_CLIENTE_SIN_CVV2 = "El usuario no dispone de tarjeta para validacion CVV2. ";

	/** The Constant MSJ_ERROR_INESPERADO_CVV2. */
	private static final String MSJ_ERROR_INESPERADO_CVV2 = "Ha ocurrido un error al validar el código CVV2.";

	/** The Constant MSJ_OK_CLIENTE_CON_CVV2. */
	private static final String MSJ_OK_CLIENTE_CON_CVV2 = "El usuario dispone de tarjeta para validacion CVV2. ";

	/** The Constant MSJ_SOLICITANDO_CVV2. */
	private static final String MSJ_SOLICITANDO_CVV2 = "Solicitando CVV2... ";

	/** The cvv2 DAO. */
	@Autowired
	private Cvv2DAO cvv2DAO;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/**
	 * Determina si el cliente tiene al menos una tarjeta de credito.
	 *
	 * @return true si el cliente tiene al menos una tarjeta de credito
	 */
	@Override
	public boolean getTieneTrjCred() {
		Cliente cliente = getSesionCliente().getCliente();
		if (cliente.getTieneTrjCred() != null) {
			return cliente.getTieneTrjCred().booleanValue();
		} else {
			Cuenta cta = buscaTarjetaCreditoValidacion();
			if (cta != null) {
				cliente.setTieneTrjCred(Boolean.TRUE);
				cliente.setCuentaValidacion(cta);
				return true;
			}
			cliente.setTieneTrjCred(Boolean.FALSE);
			return false;
		}
	}

	/**
	 * Solicitar Cvv2.
	 * 
	 * @param codigoEstadisticaSolicitud
	 *            the codigo estadistica solicitud
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AutentificacionDTO> solicitarCvv2(String codigoEstadisticaSolicitud) {
		LOGGER.info(MSJ_SOLICITANDO_CVV2);
		if (!getTieneTrjCred()) {
			LOGGER.info(MSJ_ERROR_CLIENTE_SIN_CVV2);
			return respuestaWarningSinMetodoDesafioCvv2(codigoEstadisticaSolicitud);
		} else {
			LOGGER.info(MSJ_OK_CLIENTE_CON_CVV2);
			return obtenerUltimosDigitosValidacion(codigoEstadisticaSolicitud);
		}
	}

	/**
	 * Validar Cvv2.
	 *
	 * @param autentificacionDTO
	 *            the autentificacion DTO
	 * @param codigoEstadisticaValidacion
	 *            the codigo estadistica validacion
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AutentificacionDTO> validarCvv2(AutentificacionDTO autentificacionDTO,
			String codigoEstadisticaValidacion) {
		return ejecutarValidacionCvv2(autentificacionDTO, codigoEstadisticaValidacion);
	}

	/**
	 * Busca tarjeta VISA o AMEX en una lista.
	 *
	 * @param lst
	 *            Lista a procesar
	 * @return Cuenta asociada en caso de encontrarse una tarjeta, o null en
	 *         caso contrario
	 */
	private Cuenta buscaTarjeta(List<Cuenta> lst) {
		if (lst != null && !lst.isEmpty()) {
			Cuenta cta = buscaTarjetaPorTipo(lst, TIPOCTA_VISA);
			if (cta != null) {
				return cta;
			} else {
				cta = buscaTarjetaPorTipo(lst, TIPOCTA_AMEX);
				if (cta != null) {
					return cta;
				}else{
					cta = buscaTarjetaPorTipo(lst, TIPOCTA_MASTER);
					return cta;
				}
			}
		}
		return null;
	}

	/**
	 * Obtiene la tarjeta a utilizar para la validacion CVV2.
	 *
	 * @return Cuenta asociada a la tarjeta a utilizar para la validacion CVV2
	 */
	private Cuenta buscaTarjetaCreditoValidacion() {
		// Busca tarjetas titulares
		List<Cuenta> titulares = getCuentasPorTitularidad(COD_TIT_T);
		Cuenta cta = buscaTarjeta(titulares);
		if (cta != null) {
			return cta;
		}
		// Busca tarjetas adicionales
		List<Cuenta> adicionales = getCuentasPorTitularidad(COD_TIT_A);
		cta = buscaTarjeta(adicionales);
		if (cta != null) {
			return cta;
		}
		return null;
	}

	/**
	 * Busca una tarjeta de determinado tipo en una lista.
	 *
	 * @param lst
	 *            Lista donde se buscara la tarjeta
	 * @param tipoCta
	 *            Tipo de cuenta a buscar
	 * @return Cuenta encontrada, o null en caso contrario
	 */
	private Cuenta buscaTarjetaPorTipo(List<Cuenta> lst, String tipoCta) {
		for (int i = 0; i < lst.size(); i++) {
			Cuenta cuenta = lst.get(i);
			if (tipoCta.equals(cuenta.getTipoCuenta()) && !isTrjVencida(cuenta.getCbu())) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * Crear autentificacion DTO.
	 *
	 * @param nroTrjFmt
	 *            the nroTrjFmt
	 * @param cvv2Len
	 *            the cvv 2 len
	 * @param fVtoCred
	 *            the fvto cred
	 * @return the autentificacion DTO
	 */
	private AutentificacionDTO crearAutentificacionDTO(String nroTrjFmt, String cvv2Len, String fVtoCred) {
		Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CVV2_MENSAJE_INPUT);
		String mensajeCvv2 = MessageFormat.format(mensaje.getMensaje(), nroTrjFmt, fVtoCred);
		Cvv2OperacionDTO cvv2OperacionDTO = new Cvv2OperacionDTO();
		cvv2OperacionDTO.setMensaje(mensajeCvv2);
		cvv2OperacionDTO.setCvv2Len(cvv2Len);
		cvv2OperacionDTO.setTipoDesafio(TipoDesafioEnum.CVV2);

		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		autentificacionDTO.setTipoDesafio(TipoDesafioEnum.CVV2);
		autentificacionDTO.setCvv2Operacion(cvv2OperacionDTO);
		autentificacionDTO.setReintentosAgotados(false);
		autentificacionDTO.setValorNotificarRSA(false);
		return autentificacionDTO;
	}

	/**
	 * Crear respuesta cvv2.
	 *
	 * @param autentificacionDTO
	 *            the autentificacion DTO
	 * @return the respuesta
	 */
	private Respuesta<AutentificacionDTO> crearRespuestaCvv2(AutentificacionDTO autentificacionDTO) {
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setTipoError(TipoError.DESAFIO.getDescripcion());
		List<ItemMensajeRespuesta> itemsMensaje = new ArrayList<ItemMensajeRespuesta>();
		itemsMensaje.add(itemMensajeRespuesta);
		return getRespuestaFactory().crearRespuestaWarning(AutentificacionDTO.class, autentificacionDTO, itemsMensaje);
	}

	/**
	 * Ejecutar validacion CVV2.
	 *
	 * @param autentificacionDTO
	 *            the autentificacion DTO
	 * @param codigoEstadisticaValidacion
	 *            the codigo estadistica validacion
	 * @return the respuesta
	 */
	private Respuesta<AutentificacionDTO> ejecutarValidacionCvv2(AutentificacionDTO autentificacionDTO,
			String codigoEstadisticaValidacion) {
		try {
			LOGGER.info(MSJ_EJECUTANDO_CVV2);
			Cuenta ctaVal = getSesionCliente().getCliente().getCuentaValidacion();
			String tipoCta = ctaVal.getTipoCuenta();
			String marcaTarjeta;
			if (TIPOCTA_AMEX.equals(tipoCta)) {
				marcaTarjeta = "4";
			} else if (TIPOCTA_VISA.equals(tipoCta)) {
				marcaTarjeta = "1";
			} else {
				LOGGER.error("Error al validar CVV2: tipo de cuenta invalido: {}", tipoCta);
				return crearRespuestaErrorGenerico(codigoEstadisticaValidacion);
			}
			String cbu = ctaVal.getCbu();
			String nroTarjeta = ctaVal.getNroTarjetaCredito().substring(4);
			Cvv2OperacionDTO cvv2DTO = autentificacionDTO.getCvv2Operacion();
			String codSeguridad = ISBANStringUtils.fillNum(cvv2DTO.getIngresoCvv2(), 4);
			String vencTarjeta = "  " + cbu.substring(6, 8) + cbu.substring(4, 6);
			boolean cvv2Valido = cvv2DAO.ejecutarValidacionCvv2(marcaTarjeta, nroTarjeta, vencTarjeta, codSeguridad,
					getSesionCliente().getCliente());
			if (cvv2Valido) {
				LOGGER.info(MSJ_EJECUCION_CVV2_SATISFACTORIA);
				return respuestaOKValidacion(codigoEstadisticaValidacion, autentificacionDTO);
			}
			LOGGER.debug(MSJ_EJECUCION_CVV2_CON_ERRORES);
			return crearRespuestaErrorGenerico(codigoEstadisticaValidacion);
		} catch (DAOException e) {
			LOGGER.error(MSJ_ERROR_INESPERADO_CVV2, e);
			return crearRespuestaErrorGenerico(codigoEstadisticaValidacion);
		}
	}

	/**
	 * Obtiene tarjetas con cierto codigo de titularidad.
	 *
	 * @param codTitularidad
	 *            Codigo de titularidad a buscar
	 * @return Listado de tarjetas encontradas
	 */
	private List<Cuenta> getCuentasPorTitularidad(String codTitularidad) {
		Cliente cliente = getSesionCliente().getCliente();
		List<Cuenta> lst = new ArrayList<Cuenta>();
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (codTitularidad.equals(cuenta.getCodigoTitularidad().substring(0, 1))) {
				lst.add(cuenta);
			}
		}
		return lst;
	}

	/**
	 * Determina si la tarjeta se encuentra vencida.
	 *
	 * @param cbu
	 *            CBU asociado a la cuenta
	 * @return true si la tarjeta esta vencida
	 */
	private boolean isTrjVencida(String cbu) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/yyyy");
		String vtoTrj = cbu.substring(4, 8);
		Date fv;
		try {
			if (!"0000".equals(vtoTrj)) {
				fv = sdf.parse(vtoTrj);
			} else {
				fv = sdf2.parse("12/2020");
			}
		} catch (ParseException e) {
			LOGGER.error("isValidFv() - error en fecha de vencimiento: " + vtoTrj, e.toString());
			return false;
		}
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
		return fv.before(cal.getTime());
	}

	/**
	 * Obtiene ultimos digitos de tarjeta de credito.
	 *
	 * @param codigoEstadisticaSolicitud
	 *            the codigo estadistica solicitud
	 * @return the respuesta
	 */
	private Respuesta<AutentificacionDTO> obtenerUltimosDigitosValidacion(String codigoEstadisticaSolicitud) {
		Cuenta ctaVal = getSesionCliente().getCliente().getCuentaValidacion();
		String nroTrj = ctaVal.getNroTarjetaCredito();
		String tipoCtaCvv2 = ctaVal.getTipoCuenta();
		String cvv2Len;
		String nroTrjFmt;
		if (TIPOCTA_AMEX.equals(tipoCtaCvv2)) {
			cvv2Len = CVV2LEN_AMEX;
			nroTrjFmt = "xxxxxx-" + nroTrj.substring(nroTrj.length() - 5);
		} else if (TIPOCTA_VISA.equals(tipoCtaCvv2)) {
			cvv2Len = CVV2LEN_VISA;
			nroTrjFmt = "xxxx-" + nroTrj.substring(nroTrj.length() - 4);
		} else {
			LOGGER.error("Error: tipo de cuenta invalido: {}", tipoCtaCvv2);
			return crearRespuestaErrorGenerico(codigoEstadisticaSolicitud);
		}
		String cbu = ctaVal.getCbu();
		String vtoTrj = cbu.substring(4, 8);
		SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MMMM yyyy", new Locale("es", "AR"));
		String fVtoCred = "";
		try {
			fVtoCred = "0000".equals(vtoTrj) ? "diciembre 2020" : sdf2.format(sdf.parse(vtoTrj));
		} catch (ParseException e) {
			LOGGER.error(
					"Error al procesar la fecha de vencimiento de tarjeta de credito para validacion CVV2: {} - {}",
					vtoTrj, e.getMessage());
			return crearRespuestaErrorGenerico(codigoEstadisticaSolicitud);
		}
		AutentificacionDTO autentificacionDTO = crearAutentificacionDTO(nroTrjFmt, cvv2Len, fVtoCred);
		return crearRespuestaCvv2(autentificacionDTO);
	}

	/**
	 * Respuesta OK validacion.
	 *
	 * @param codigoEstadistica
	 *            the codigo estadistica
	 * @param autentificacionDTO
	 *            the autentificacion DTO
	 * @return the respuesta
	 */
	private Respuesta<AutentificacionDTO> respuestaOKValidacion(String codigoEstadistica,
			AutentificacionDTO autentificacionDTO) {
		grabarEstadistica(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		autentificacionDTO.setValorNotificarRSA(true);
		return getRespuestaFactory().crearRespuestaOk(AutentificacionDTO.class, autentificacionDTO);
	}

	/**
	 * Respuesta error generico solicitud.
	 *
	 * @param codigoEstadistica
	 *            the codigo estadistica
	 * @return the respuesta
	 */
	private Respuesta<AutentificacionDTO> respuestaWarningSinMetodoDesafioCvv2(String codigoEstadistica) {
		grabarEstadistica(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return getRespuestaFactory().crearRespuestaWarning(AutentificacionDTO.class, null, TipoError.SIN_METODO_DESAFIO,
				CodigoMensajeConstantes.ADHERIR_METODO_DESAFIO_MENSAJE, TipoDesafioEnum.CVV2.getId());
	}

}

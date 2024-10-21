/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.dao.DebitoAutomaticoDAO;

/**
 * The Class DebitoAutomaticoDAOImpl.
 */
@Component
public class DebitoAutomaticoDAOImpl implements DebitoAutomaticoDAO {

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The mensaje dao. */
	@Autowired
	private MensajeDAO mensajeDao;

	/** The legal dao. */
	@Autowired
	private LegalDAO legalDao;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DebitoAutomaticoDAOImpl.class);

	/** The Constant ALTDDIADHE. */
	private static final String ALTDDIADHE = "ALTDDIADHE";

	/** The Constant VERSION. */
	private static final String VERSION = "100";

	/** The Constant CANT_DIGITOS1. */
	private static final int CANT_DIGITOS1 = 14;

	/** The Constant CANT_DIGITOS2. */
	private static final int CANT_DIGITOS2 = 3;

	/** The Constant CANT_DIGITOS3. */
	private static final int CANT_DIGITOS3 = 8;

	/** The Constant TIMEOUT_EXCEPTION. */
	private static final String TIMEOUT_EXCEPTION = "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ";

	/** The Constant SERVICIO_BAJDDIADHE. */
	private static final String SERVICIO_BAJDDIADHE = "BAJDDIADHE";

	/** The Constant VERSION_BAJDDIADHE. */
	private static final String VERSION_BAJDDIADHE = "100";

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant BAJA_ADHESION_YA_SOLICITADA. */
	private static final int BAJA_ADHESION_YA_SOLICITADA = 10000170;

	/** The Constant BAJA_ADHESION_YA_SOLICITADA EN EL DIA . */
	private static final int BAJA_ADHESION_YA_SOLICITADA_EN_EL_DIA = 10000076;

	/** The Constant POSICION_SISTEMA_ASOCIADO_AL_ERROR. */
	private static final int POSICION_SISTEMA_ASOCIADO_AL_ERROR = 1;

	/** The Constant POSICION_CANTIDAD_DESCRIPCIONES. */
	private static final int POSICION_CANTIDAD_DESCRIPCIONES = 2;

	/** The Constant POSICION_INICIO_DESCRIPCIONES. */
	private static final int POSICION_INICIO_DESCRIPCIONES = 3;

	/** The Constant LONG_CUIT. */
	private static final int LONG_CUIT = 11;

	/** The Constant LONG_NOMBRE_SERVICIO_EMPRESA. */
	private static final int LONG_NOMBRE_SERVICIO_EMPRESA = 10;

	/** The Constant LONG_NRO_PARTIDA_SERVICIO_EMPRESA. */
	private static final int LONG_NRO_PARTIDA_SERVICIO_EMPRESA = 22;

	/** The Constant DEBITO_AUTOMATICO. */
	private static final String DEBITO_AUTOMATICO = "débito automático";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.dao.DebitoAutomaticoDAO#adherir(
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.pagoautomatico.entities.
	 * AdhesionDebitoAutomatico)
	 */
	@Override
	public Respuesta<AdhesionDebitoAutomatico> adherir(Cliente cliente,
			AdhesionDebitoAutomatico adhesionDebitoAutomatico) throws DAOException {

		IatxRequest request = new IatxRequest(ALTDDIADHE, VERSION);
		return invocarServicioConfirmar(cliente, adhesionDebitoAutomatico, request);
	}

	/**
	 * (non-Javadoc).
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesion
	 *            the adhesion
	 * @param request
	 *            the request
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 * @see ar.com.santanderrio.obp.servicios.pagos.dao.DebitoAutomaticoDAO#
	 *      invocarServicioConfirmar(ar.com.santanderrio.obp.servicios.clientes.
	 *      entities.Cliente,
	 *      ar.com.santanderrio.obp.servicios.pagoautomatico.entities.
	 *      AdhesionDebitoAutomatico,
	 *      ar.com.santanderrio.obp.base.iatx.entities.IatxRequest)
	 */
	@Override
	public Respuesta<AdhesionDebitoAutomatico> invocarServicioConfirmar(Cliente cliente,
			AdhesionDebitoAutomatico adhesion, IatxRequest request) throws DAOException {

		Respuesta<AdhesionDebitoAutomatico> respuesta = new Respuesta<AdhesionDebitoAutomatico>();
		try {
			IatxRequestData requestData = new IatxRequestData(cliente);
			requestData.addBodyValue(adhesion.getCuit());
			requestData.addBodyValue(adhesion.getNombreServicioEmpresa());
			requestData.addBodyValue(adhesion.getNroPartidaServicioEmpresa());
			requestData.addBodyValue(StringUtils.leftPad(adhesion.getLimiteAdhesion().concat("00"), CANT_DIGITOS1, "0"));
			requestData.addBodyValue(adhesion.getTipoCuentaDebito());
			requestData.addBodyValue(StringUtils.substring(adhesion.getSucursalCuentaDebito(),
					adhesion.getSucursalCuentaDebito().length() - CANT_DIGITOS2));
			requestData.addBodyValue(StringUtils.substring(adhesion.getNroCuentaProductoDebito(),
					adhesion.getNroCuentaProductoDebito().length() - CANT_DIGITOS3));
			requestData.addBodyValue(adhesion.getNroOrdenFirmante());
			request.setData(requestData);

			IatxResponse iatxResponse = iatxComm.exec(request);
			int codigoDeRetorno = iatxResponse.getErrorCode();

			if (codigoDeRetorno == 0) {
				adhesion.setFechaHora(convertirFechaHora(iatxResponse.getFechaHoraReq()));
				adhesion.setNroDeComprobante(iatxResponse.getNroComprobante());
				String mensaje = mensajeDao.obtenerMensajePorCodigo("1280").getMensaje();

				String cuentaFormateada = ISBANStringUtils.formatearSucursal(adhesion.getSucursalCuentaDebito()) + "-"
						+ ISBANStringUtils.formatearNumeroCuenta(adhesion.getNroCuentaProductoDebito());

				mensaje = MessageFormat.format(mensaje, adhesion.getNombreFantasia(), DEBITO_AUTOMATICO,
						cuentaFormateada);

				adhesion.setMensajeFeedback(mensaje);
				adhesion.setLegalesSEUO(legalDao.obtenerLegal("1005"));

				// setear respuesta

				respuesta.setRespuesta(adhesion);
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				respuesta.setRespuestaVacia(false);
			} else {
				LOGGER.debug("Error en Iatx:" + iatxResponse.getErrorCode());

				String mensaje = mensajeDao.obtenerMensajePorCodigo("1281").getMensaje();
				String cuentaFormateada = ISBANStringUtils.formatearSucursal(adhesion.getSucursalCuentaDebito()) + "-"
						+ ISBANStringUtils.formatearNumeroCuenta(adhesion.getNroCuentaProductoDebito());

				mensaje = MessageFormat.format(mensaje, adhesion.getNombreFantasia(), DEBITO_AUTOMATICO,
						cuentaFormateada);

				respuesta = respuestaFactory.crearRespuestaErrorPersonalizado(AdhesionDebitoAutomatico.class, mensaje,
						TipoError.ERROR_REINTENTOS_OPERACION.getDescripcion());
			}
			return respuesta;
		} catch (IatxException e) {

			if (TIMEOUT_EXCEPTION.equals(e.getMessage())) {
				String timeOutException = "TimeOutException. Se envio la transaccion al CICS pero no se recibio respuesta";
				LOGGER.error(timeOutException, e);
				String mensaje = mensajeDao.obtenerMensajePorCodigo("1283").getMensaje();
				mensaje = MessageFormat.format(mensaje, adhesion.getNombreFantasia(), DEBITO_AUTOMATICO);
				return respuestaFactory.crearRespuestaErrorPersonalizado(AdhesionDebitoAutomatico.class, mensaje,
						TipoError.ERROR_REINTENTOS_OPERACION.getDescripcion());
			} else {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.dao.DebitoAutomaticoDAO#
	 * bajaAdhesion(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.pagoautomatico.entities.
	 * AdhesionDebitoAutomatico)
	 * 
	 * Reslutado transaccion contiene un estado respuesta, Si es OK se hizo la
	 * baja exitosa Si es WARNING significa que ya hay una baja solicitada
	 * pendiente de ejecucion Si es ERROR ó el metodo arroja una excepcion
	 * significa que la baja no se ejecuto. Tanto la salida por WARNING como por
	 * ERROR devuelven el detalle de lo ocurrido dentro del resultado.
	 */
	@Override
	public ResultadoTransaccion bajaAdhesion(Cliente cliente, AdhesionDebitoAutomatico adhesion) throws DAOException {
		IatxRequestData iatxRequestData = generarRequestBajaAdhesion(cliente, adhesion);
		IatxResponse iatxResponse = invocarServicio(SERVICIO_BAJDDIADHE, VERSION_BAJDDIADHE, iatxRequestData);
		return parsearResponseBajaAdhesion(iatxResponse);
	}

	/**
	 * Generar request baja adhesion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesion
	 *            the adhesion
	 * @return the iatx request data
	 * @throws DAOException
	 *             the DAO exception
	 */
	private IatxRequestData generarRequestBajaAdhesion(Cliente cliente, AdhesionDebitoAutomatico adhesion)
			throws DAOException {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		try {
			iatxRequestData.addBodyValue(StringUtils.left(adhesion.getCuit(), LONG_CUIT));
			iatxRequestData
					.addBodyValue(StringUtils.left(adhesion.getNombreServicioEmpresa(), LONG_NOMBRE_SERVICIO_EMPRESA));
			iatxRequestData.addBodyValue(
					StringUtils.left(adhesion.getNroPartidaServicioEmpresa(), LONG_NRO_PARTIDA_SERVICIO_EMPRESA));
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new DAOException(ex);
		}
		return iatxRequestData;
	}

	/**
	 * Invocar servicio.
	 *
	 * @param servicio
	 *            the servicio
	 * @param version
	 *            the version
	 * @param iatxRequestData
	 *            the iatx request data
	 * @return the iatx response
	 * @throws DAOException
	 *             the DAO exception
	 */
	private IatxResponse invocarServicio(String servicio, String version, IatxRequestData iatxRequestData)
			throws DAOException {
		IatxResponse iatxResponse;
		try {
			IatxRequest iatxRequest = new IatxRequest(servicio, version);
			iatxRequest.setData(iatxRequestData);
			iatxResponse = iatxComm.exec(iatxRequest);
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return iatxResponse;
	}

	/**
	 * Parsear response baja adhesion.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the resultado transaccion
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ResultadoTransaccion parsearResponseBajaAdhesion(IatxResponse iatxResponse) throws DAOException {
		ResultadoTransaccion resultado = new ResultadoTransaccion();
		try {
			int errorCode = iatxResponse.getErrorCode();
			switch (errorCode) {
			case OK_CODIGO_RETORNO:
				resultado = iatxResponse.getResultadoTransaccion();
				resultado.setEstadoRespuesta(EstadoRespuesta.OK);
				return resultado;
			case BAJA_ADHESION_YA_SOLICITADA:
			case BAJA_ADHESION_YA_SOLICITADA_EN_EL_DIA:
				resultado.setEstadoRespuesta(EstadoRespuesta.WARNING);
				break;
			default:
				resultado.setEstadoRespuesta(EstadoRespuesta.ERROR);
			}
			parsearResponseError(resultado, iatxResponse);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return resultado;
	}

	/**
	 * Parsear response error.
	 *
	 * @param resultadoTransaccion
	 *            the resultado transaccion
	 * @param iatxResponse
	 *            the iatx response
	 */
	private void parsearResponseError(ResultadoTransaccion resultadoTransaccion, IatxResponse iatxResponse) {
		resultadoTransaccion.setDescripcionesDeError(new ArrayList<String>());
		resultadoTransaccion.setSistemaAsociadoError(iatxResponse.getData(POSICION_SISTEMA_ASOCIADO_AL_ERROR));
		int cantidadMensajes = Integer.parseInt(iatxResponse.getData(POSICION_CANTIDAD_DESCRIPCIONES));

		for (int i = POSICION_INICIO_DESCRIPCIONES; i < cantidadMensajes + POSICION_INICIO_DESCRIPCIONES; i++) {
			resultadoTransaccion.getDescripcionesDeError().add(iatxResponse.getData(i));
		}
	}

	/**
	 * Convertir fecha hora.
	 *
	 * @param fechaHoraSinFormato
	 *            the fecha hora sin formato
	 * @return the string
	 */
	private String convertirFechaHora(String fechaHoraSinFormato) {
		LOGGER.info("Convierto la fecha y hora extraida de la trama al formato de salida pedido");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		SimpleDateFormat formatoFechaHoraTrama = new SimpleDateFormat("yyyyMMddHHmmss");
		Date fechaHora = null;

		try {
			fechaHora = formatoFechaHoraTrama.parse(fechaHoraSinFormato);
		} catch (java.text.ParseException ex) {
			LOGGER.error("Ha ocurrido un error al parsear la fecha", ex);
			return "-";
		}

		return dateFormatter.format(fechaHora);

	}

}

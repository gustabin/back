/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.challenge.exception.BloqueoException;
import ar.com.santanderrio.obp.servicios.comun.challenge.exception.SyncException;
import ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.dao.SoftTokenDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class SoftTokenDAOImpl.
 */
@Component
public class SoftTokenDAOImpl implements SoftTokenDAO {

	/** The Constant CAMPO_CANAL. */
	// Individuos
	private final static String CAMPO_CANAL = " ";

	/** The Constant CODIGO_OK. */
	private final static int CODIGO_OK = 0;

	/** The Constant TOKEN_LONGITUD_INPUT. */
	private final static int TOKEN_LONGITUD_INPUT = 11;

	/** The Constant IP_LONGITUD_INPUT. */
	private final static int IP_LONGITUD_INPUT = 16;

	/** Casuistica de errores. */
	private static final int CODIGO_ERROR_US_INEXISTENTE = 10000014;

	/** The Constant CODIGO_ERROR_US_BLOQUEO. */
	private static final int CODIGO_ERROR_US_BLOQUEO = 10000015;

	/** The Constant CODIGO_ERROR_US_BAJA. */
	private static final int CODIGO_ERROR_US_BAJA = 10000050;

	/** The Constant CODIGO_ERROR_AUTENTICACION_FALLIDA. */
	private static final int CODIGO_ERROR_AUTENTICACION_FALLIDA = 10000053;

	/** The Constant CODIGO_ERROR_REITENTOS. */
	private static final int CODIGO_ERROR_REITENTOS = 10000062;

	/** The Constant CODIGO_ERROR_US_INACTIVO. */
	private static final int CODIGO_ERROR_US_INACTIVO = 10000065;

	/** The Constant CODIGO_DE_ERROR_INICIO_STRING. */
	private static final String CODIGO_DE_ERROR_INICIO_STRING = "[codigo_error=";

	/** The Constant CODIGO_DE_ERROR_FIN_STRING. */
	private static final String CODIGO_DE_ERROR_FIN_STRING = "]";

	/** The Constant CODIGO_DE_ERROR__MSG. */
	private static final String CODIGO_DE_ERROR_MSG = "Codigo de error iatx";

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SoftTokenDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The valzatoken servicio. */
	private String valzatokenServicio = "VALZATOKEN";

	/** The valzatoken version. */
	private String valzatokenVersion = "100";

	/**
	 * Ejecutar validacion token. Control de errores.
	 *
	 * @param token
	 *            the token
	 * @param ip
	 *            the ip
	 * @param cliente
	 *            the cliente
	 * @return true, if successful
	 * @throws DAOException
	 *             the DAO exception
	 * @throws SyncException
	 *             the sync exception
	 * @throws BloqueoException
	 *             the bloqueo exception
	 * @see ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.dao.SoftTokenDAO#ejecutarValidacionToken(java.lang.String,
	 *      java.lang.String,
	 *      ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public boolean ejecutarValidacionToken(String token, String ip, Cliente cliente)
			throws DAOException, SyncException, BloqueoException {
		boolean resultadoValidacion;
		IatxResponse iatxResponse;
		try {
			IatxRequest request = generarRequestValidarToken(token, ip, cliente);
			iatxResponse = iatxComm.exec(request);
			resultadoValidacion = procesarResponseValidarToken(iatxResponse); // NO
			resultadoValidacion = false;
			int errorCode = iatxResponse.getErrorCode();
			switch (errorCode) {
			case CODIGO_OK:
				resultadoValidacion = true;
				LOGGER.info("Resultado validación por SoftToken: OK");
				break;
			default:
				LOGGER.info("Resultado validación por SoftToken: Error generico.");
				throw new DAOException(this.generarCodigoErrorDesconocidoMensaje(iatxResponse));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return resultadoValidacion;
	}

	/**
	 * Procesar response validar token.
	 *
	 * @param response
	 *            the response
	 * @return true, if successful
	 * @last_updated: b039542 - ignacio_valek@itrsa.com.ar - 20/09/2016
	 */
	private boolean procesarResponseValidarToken(IatxResponse response) {
		boolean resultadoValidacion = false;
		int errorCode = response.getErrorCode();

		switch (errorCode) {
		case CODIGO_OK:
			resultadoValidacion = true;
			LOGGER.info("Resultado validación por SoftToken: OK");
			break;
		default:
			resultadoValidacion = false;
			LOGGER.info("Resultado validación por SoftToken: Error");
		}

		return resultadoValidacion;
	}

	/**
	 * Generar request validar token.
	 *
	 * @param token
	 *            the token
	 * @param ip
	 *            the ip
	 * @param cliente
	 *            the cliente
	 * @return the iatx request
	 * @last_updated: b039542 - ignacio_valek@itrsa.com.ar - 20/09/2016
	 */
	private IatxRequest generarRequestValidarToken(String token, String ip, Cliente cliente) {
		IatxRequest request = new IatxRequest(valzatokenServicio, valzatokenVersion);
		IatxRequestData data = new IatxRequestData(cliente);
		data.addBodyValue(CAMPO_CANAL);
		data.addBodyValue(ISBANStringUtils.normalizarCampoAlfanumericoIatx(token, TOKEN_LONGITUD_INPUT));
		data.addBodyValue(ISBANStringUtils.normalizarCampoAlfanumericoIatx(ip, IP_LONGITUD_INPUT));

		request.setData(data);
		return request;
	}

	/**
	 * Generar codigo error desconocido mensaje.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the string
	 */
	private String generarCodigoErrorDesconocidoMensaje(IatxResponse iatxResponse) {
		return new StringBuilder().append(CODIGO_DE_ERROR_MSG).append(". errorCode: ")
				.append(CODIGO_DE_ERROR_INICIO_STRING).append(iatxResponse.getErrorCode())
				.append(CODIGO_DE_ERROR_FIN_STRING).append(", errorMessage: ").append(CODIGO_DE_ERROR_INICIO_STRING)
				.append(iatxResponse.getErrorMessage()).append(CODIGO_DE_ERROR_FIN_STRING).append(", errorSystem: ")
				.append(CODIGO_DE_ERROR_INICIO_STRING).append(iatxResponse.getErrorSystem())
				.append(CODIGO_DE_ERROR_FIN_STRING).toString();
	}
}

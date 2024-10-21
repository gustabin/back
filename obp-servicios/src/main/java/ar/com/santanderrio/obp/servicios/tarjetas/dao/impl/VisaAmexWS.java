/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;

/**
 * The Class VisaAmexWS.
 */
@Component("visaAmexWS")
public class VisaAmexWS {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(VisaAmexWS.class);

	/** The url visa amex. */
	@Value("${VISA.AMEX.WS.URL}")
	private String urlVisaAmex;

	/** The visa amex request. */
	@Autowired
	private VisaAmexRequest visaAmexRequest;

	/** The rest template. */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Obtener respuesta visa amex WS.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param operacion
	 *            the operacion
	 * @param cliente
	 *            the cliente
	 * @return the response entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	public ResponseEntity<String> obtenerRespuestaVisaAmexWS(Cuenta cuenta, OperacionTarjetaWSEnum operacion,
			Cliente cliente) throws DAOException {
		LOGGER.info("Consultando WS VisaAmex con operacion: {} para la cuenta {} .", operacion, cuenta);
		ResponseEntity<String> respuestaWs = null;
		try {
			HttpEntity<MultiValueMap<String, String>> entity = obtenerEntity(cuenta, operacion, cliente);
			LOGGER.info("Preparando WS VisaAmex postForEntity urlVisaAmex: {} HttpEntity {} .", urlVisaAmex, entity);
			respuestaWs = restTemplate.postForEntity(urlVisaAmex, entity, String.class);
		} catch (RestClientException rce) {
			LOGGER.error("Error al invocar el ws {}.", urlVisaAmex, rce);
			throw new DAOException(rce);
		} catch (Exception e) {
			LOGGER.error("Error en DAO {}.", e);
			throw new DAOException(e);
		}
		return respuestaWs;
	}

	/**
	 * Obtener entity.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param operacion
	 *            the operacion
	 * @param cliente
	 *            the cliente
	 * @return the http entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private HttpEntity<MultiValueMap<String, String>> obtenerEntity(Cuenta cuenta, OperacionTarjetaWSEnum operacion,
			Cliente cliente) throws DAOException {
		HttpEntity<MultiValueMap<String, String>> entity = null;
		try {
			entity = visaAmexRequest.obtenerRequestVisaAmex(cuenta, operacion, cliente);
		} catch (DAOException eb) {
			LOGGER.info("Error al obtener Token firmado para la operacion {}. {}.", operacion, eb);
			throw new DAOException(eb);
		}
		return entity;
	}

	/**
	 * Obtener respuesta visa amex debitos WS.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param operacion
	 *            the operacion
	 * @param dto
	 *            the dto
	 * @param cliente
	 *            the cliente
	 * @return the response entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	public ResponseEntity<String> obtenerRespuestaVisaAmexDebitosWS(Cuenta cuenta, OperacionTarjetaWSEnum operacion,
			TransaccionDTO dto, Cliente cliente) throws DAOException {
		LOGGER.info("Consultando WS VisaAmex con operacion: {} para la cuenta {} .", operacion, cuenta);
		ResponseEntity<String> respuestaWs = null;
		try {
			HttpEntity<MultiValueMap<String, String>> entity = obtenerEntityDebitos(cuenta, operacion, dto, cliente);
			LOGGER.info("Preparando WS VisaAmex postForEntity urlVisaAmex: {} HttpEntity {} .", urlVisaAmex, entity);
			respuestaWs = restTemplate.postForEntity(urlVisaAmex, entity, String.class);
		} catch (RestClientException rce) {
			LOGGER.error("Error al invocar el ws {}.", urlVisaAmex, rce);
			throw new DAOException(rce);
		} catch (Exception e) {
			LOGGER.error("Error en DAO {}.", e);
			throw new DAOException(e);
		}
		return respuestaWs;
	}

	/**
	 * Obtener entity debitos.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param operacion
	 *            the operacion
	 * @param dto
	 *            the dto
	 * @param cliente
	 *            the cliente
	 * @return the http entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private HttpEntity<MultiValueMap<String, String>> obtenerEntityDebitos(Cuenta cuenta,
			OperacionTarjetaWSEnum operacion, TransaccionDTO dto, Cliente cliente) throws DAOException {
		HttpEntity<MultiValueMap<String, String>> entity = null;
		try {
			entity = visaAmexRequest.obtenerRequestVisaAmex(cuenta, operacion, dto, cliente);
		} catch (DAOException eb) {
			LOGGER.info("Error al obtener Token firmado para la operacion {}. {}.", operacion, eb);
			throw new DAOException(eb);
		}
		return entity;
	}
}

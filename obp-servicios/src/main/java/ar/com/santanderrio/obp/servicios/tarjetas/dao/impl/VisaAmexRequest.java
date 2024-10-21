/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TokenVisaAmex;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TokenVisaAmexFactory;

/**
 * The Class VisaAmexRequest.
 */
@Component("visaAmexRequest")
public class VisaAmexRequest {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(VisaAmexRequest.class);

	/** The Constant MENOS. */
	private static final String MENOS = "-";

	/** The paginado desktop. */
	@Value("${COMPROBANTES.FECHA.ANTERIOR}")
	private String fechaAnterior;

	/** The app encoding. */
	@Value("${APP.ENCODING}")
	private String appEncoding;

	/** The sign. */
	@Autowired
	private Sign sign;

	/** The token visa amex factory. */
	@Autowired
	private TokenVisaAmexFactory tokenVisaAmexFactory;

	/**
	 * Obtener request visa amex.
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
	public HttpEntity<MultiValueMap<String, String>> obtenerRequestVisaAmex(Cuenta cuenta,
			OperacionTarjetaWSEnum operacion, Cliente cliente) throws DAOException {
		LOGGER.debug("Consultando WS VisaAmex con operacion: {} para la cuenta {} .", operacion, cuenta);
		HttpEntity<MultiValueMap<String, String>> entity = null;
		try {

			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
			setearParametros(parameters, operacion, cuenta);
			if (operacion == OperacionTarjetaWSEnum.INFORMEDEBITOSAUTOMATICOS) {
				TransaccionDTO dto = new TransaccionDTO();
				asignarParametrosParaComprobantes(parameters, dto, cliente);
			}
			entity = new HttpEntity<MultiValueMap<String, String>>(parameters, new HttpHeaders());
		} catch (DAOException e) {
			LOGGER.error("Error en DAO {}.", e);
			throw new DAOException(e);
		} catch (NullPointerException e) {
			LOGGER.error("Error en DAO {}.", e);
			throw new DAOException(e);
		}
		return entity;
	}

	/**
	 * Obtener request visa amex.
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
	public HttpEntity<MultiValueMap<String, String>> obtenerRequestVisaAmex(Cuenta cuenta,
			OperacionTarjetaWSEnum operacion, TransaccionDTO dto, Cliente cliente) throws DAOException {
		LOGGER.debug("Consultando WS VisaAmex con operacion: {} para la cuenta {} .", operacion, cuenta);
		HttpEntity<MultiValueMap<String, String>> entity = null;
		try {

			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
			setearParametros(parameters, operacion, cuenta);
			asignarParametrosParaComprobantes(parameters, dto, cliente);
			entity = new HttpEntity<MultiValueMap<String, String>>(parameters, new HttpHeaders());
		} catch (DAOException e) {
			LOGGER.error("Error en DAO {}.", e);
			throw new DAOException(e);
		} catch (NullPointerException e) {
			LOGGER.error("Error en DAO {}.", e);
			throw new DAOException(e);
		}
		return entity;
	}

	/**
	 * Setear parametros.
	 *
	 * @param parameters
	 *            the parameters
	 * @param operacion
	 *            the operacion
	 * @param cuenta
	 *            the cuenta
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void setearParametros(MultiValueMap<String, String> parameters, OperacionTarjetaWSEnum operacion,
			Cuenta cuenta) throws DAOException {
		parameters.add("event", "Login");
		parameters.add("format", "XML");
		parameters.add("token", obtenerTokenFirmado(cuenta, operacion));
//		if (OperacionTarjetaWSEnum.ULTIMOSMOVIMIENTOS.equals(operacion)) {
//			parameters.add("mostrarPromociones", "true");
//		}
		parameters.add("tareaunificada", operacion.getOperacion());
	}

	/**
	 * Asignar parametros para comprobantes.
	 *
	 * @param parameters
	 *            the parameters
	 * @param dto
	 *            the dto
	 * @param cliente
	 *            the cliente
	 */
	public void asignarParametrosParaComprobantes(MultiValueMap<String, String> parameters, TransaccionDTO dto, Cliente cliente) {
	     SimpleDateFormat sdf = new SimpleDateFormat("M");
	     if (cliente == null) {
	            Calendar calendario = GregorianCalendar.getInstance();
	            calendario.add(GregorianCalendar.DAY_OF_YEAR, Integer.parseInt(MENOS + fechaAnterior));
	            parameters.add("anioDesde", String.valueOf(calendario.get(Calendar.YEAR)));
	            parameters.add("mesDesde", sdf.format(calendario.getTime()));
	            calendario = GregorianCalendar.getInstance();
	            parameters.add("anioHasta", String.valueOf(calendario.get(Calendar.YEAR)));
	            parameters.add("mesHasta", sdf.format(calendario.getTime()));
	     } else {
	            Calendar fechaDesde = new GregorianCalendar();
	            Calendar fechaHasta = new GregorianCalendar();
	            fechaDesde.setTime(dto.getFechaDesde());
	            fechaHasta.setTime(dto.getFechaHasta());
	            parameters.add("anioDesde", String.valueOf(fechaDesde.get(Calendar.YEAR)));
	            parameters.add("mesDesde", sdf.format(fechaDesde.getTime()));
	            parameters.add("anioHasta", String.valueOf(fechaHasta.get(Calendar.YEAR)));
	            parameters.add("mesHasta", sdf.format(fechaHasta.getTime()));
	     }
	}
	/**
	 * Obtener el token y luego firmarlo, si hay un error se loguea y se
	 * devuelve cadena vacia.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param operacion
	 *            the operacion
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	public String obtenerTokenFirmado(Cuenta cuenta, OperacionTarjetaWSEnum operacion) throws DAOException {
		LOGGER.info("Generando Token para la cuenta {} y operacion {}.", cuenta, operacion);
		String firma = "";
		try {
			TokenVisaAmex token = crearTocken(cuenta, operacion);
			LOGGER.debug("Generando firma con encoding {}.", appEncoding);
			firma = sign.buildPemSignature(token.toString().getBytes(appEncoding), TarjetaUtils.KEY_STORE_VISAAMEX,
					true);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error al generar firma para la operacion {}. {}.", operacion, e);
			throw new DAOException(e);
		} catch (Exception eb) {
			LOGGER.error("Error al obtener Token firmado para la operacion {}. {}.", operacion, eb);
			throw new DAOException(eb);
		}
		LOGGER.debug("Firma Generada {}.", firma);
		return firma;
	}

	/**
	 * Obtener el token y luego firmarlo, si hay un error se loguea y se
	 * devuelve cadena vacia.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param operacion
	 *            the operacion
	 * @return the token visa amex
	 * @throws DAOException
	 *             the DAO exception
	 */
	private TokenVisaAmex crearTocken(Cuenta cuenta, OperacionTarjetaWSEnum operacion) throws DAOException {
		TokenVisaAmex token = null;
		try {
			token = tokenVisaAmexFactory.createToken(cuenta, operacion);
			LOGGER.info("Token generado\r\n{}", token);
			// sin utf-8 funciona, validar que no afecte el comportamiento.
		} catch (Exception eb) {
			LOGGER.error("Error al generar Token {}.", eb);
			throw new DAOException(eb);
		}
		return token;
	}

}

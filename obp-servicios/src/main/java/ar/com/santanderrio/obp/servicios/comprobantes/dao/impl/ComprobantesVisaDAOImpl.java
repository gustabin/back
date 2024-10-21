/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ComprobantesVisaDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.TarjetasEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ErrorTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.impl.VisaAmexWS;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;

/**
 * WS para consultar VISAAMEX. Ver configuracion del restTemplate (timeout,
 * proxy, etc...) realizada en RestTemplateConfig.java
 *
 */
@Component
public class ComprobantesVisaDAOImpl implements ComprobantesVisaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobantesVisaDAOImpl.class);

	/** The visa amex WS. */
	@Autowired
	private VisaAmexWS visaAmexWS;

	/** The app encoding. */
	@Value("${APP.VISAENCODING}")
	private String appEncoding;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.dao.ComprobantesVisaDAO#
	 * obtenerInformesDebitosAutomaticosWS(ar.com.santanderrio.obp.servicios.
	 * cuentas.entities.Cuenta,
	 * ar.com.santanderrio.obp.servicios.tarjetas.entities.
	 * OperacionTarjetaWSEnum)
	 */
	@Override
	public RetornoTarjetasEntity obtenerInformesDebitosAutomaticosWS(Cuenta cuenta, OperacionTarjetaWSEnum operacion,
			TransaccionDTO dto, Cliente cliente) throws DAOException {
		ResponseEntity<String> respuesta = visaAmexWS.obtenerRespuestaVisaAmexDebitosWS(cuenta, operacion, dto,
				cliente);
		return obtenerDetallesTarjeta(respuesta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.dao.ComprobantesVisaDAO#
	 * obtenerInformesDebitosAutomaticosWS(ar.com.santanderrio.obp.servicios.
	 * cuentas.entities.Cuenta,
	 * ar.com.santanderrio.obp.servicios.tarjetas.entities.
	 * OperacionTarjetaWSEnum)
	 */
	@Override
	public RetornoTarjetasEntity obtenerInformesDebitosAutomaticosWS(Cuenta cuenta, OperacionTarjetaWSEnum operacion,
			Cliente cliente) throws DAOException {
		ResponseEntity<String> respuesta = visaAmexWS.obtenerRespuestaVisaAmexWS(cuenta, operacion, cliente);
		return obtenerDetallesTarjeta(respuesta);
	}

	/**
	 * Si el status del request es 200 hace el unmarshall de los objetos, sino
	 * lanza DAOException.
	 *
	 * @param respuestaWs
	 *            the respuesta ws
	 * @return the retorno tarjetas entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private RetornoTarjetasEntity obtenerDetallesTarjeta(ResponseEntity<String> respuestaWs) throws DAOException {
		if (HttpStatus.OK == respuestaWs.getStatusCode()) {
			try {
				return unmarshallResponse(respuestaWs.getBody());
			} catch (JAXBException e) {
				LOGGER.error("Error al parsear la respuesta {} del WS.", respuestaWs, e);
				throw new DAOException(e);
			} catch (UnsupportedEncodingException e) {
				LOGGER.error("Error al parsear la respuesta {} del WS, encoding {} incorrecto.", respuestaWs,
						appEncoding, e);
				throw new DAOException(e);
			}
		} else {
			LOGGER.debug("Servicio consultado con status {}", respuestaWs.getStatusCode().toString());
			throw new DAOException(respuestaWs.getStatusCode().toString());
		}
	}

	/**
	 * Este metodo es temporal para solucionar el problema del xml retornado por
	 * el ws, ya que si esta OK devuelve un rootElement <tarjetas> y si hay
	 * error devuelve <error>.<br>
	 * 
	 * Ej OK: <?xml version="1.0" encoding="ISO-8859-1" standalone="yes"?>
	 * <tarjetas> <tarjeta>
	 * <document sessionID= "f2s0lSvuWMSHwnzJrbtlciLE(wKgBKRDN)">
	 * <datos id="3839135"> <affinityGroup>9240</affinityGroup>
	 * <apellido>DOE</apellido> <website/> </datos> </document> </tarjeta>
	 * </tarjetas>
	 * 
	 * Ej Error: <error errorID="132185342" sessionID=
	 * "fe2UJOYsgPk2G41V5vkvdSlB(wKgBKBDN)"> <codigo>110005</codigo> </error>
	 *
	 * @param rt
	 *            the rt
	 * @return the retorno tarjetas entity
	 * @throws JAXBException
	 *             the JAXB exception
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	private RetornoTarjetasEntity unmarshallResponse(String rt) throws JAXBException, UnsupportedEncodingException {
		LOGGER.info("XML a transformar a objeto, valor {}", rt);
		RetornoTarjetasEntity retornoWS = new RetornoTarjetasEntity();
		if (StringUtils.isNotEmpty(rt)) {
			if (StringUtils.contains(rt, "tarjetas")) {
				JAXBElement<TarjetasEntity> tarjetas = jAXBElementCreator(TarjetasEntity.class, rt);
				retornoWS.setTarjetas(tarjetas.getValue());
			} else {
				JAXBElement<ErrorTarjetasEntity> error = jAXBElementCreator(ErrorTarjetasEntity.class, rt);
				retornoWS.setErrorTarjetas(error.getValue());
				retornoWS.setError(Boolean.TRUE);
			}
		}
		return retornoWS;
	}

	/**
	 * J AXB element creator.
	 *
	 * @param <T>
	 *            the generic type
	 * @param classObject
	 *            the class object
	 * @param rt
	 *            the rt
	 * @return the JAXB element
	 * @throws JAXBException
	 *             the JAXB exception
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	<T> JAXBElement<T> jAXBElementCreator(Class<T> classObject, String rt)
			throws JAXBException, UnsupportedEncodingException {
		JAXBContext jaxbContext = JAXBContext.newInstance(classObject);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		LOGGER.info("Unmarshall con encoding {}.", appEncoding);
		InputStream is = new ByteArrayInputStream(rt.getBytes(appEncoding));
		return jaxbUnmarshaller.unmarshal(new StreamSource(is), classObject);

	}

}

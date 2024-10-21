/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.TarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ErrorTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;

/**
 * WS para consultar VISAAMEX. Ver configuracion del restTemplate (timeout,
 * proxy, etc...) realizada en RestTemplateConfig.java
 *
 */
@Repository
public class TarjetaDAOImpl implements TarjetaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaDAOImpl.class);

	/** The visa amex WS. */
	@Autowired
	private VisaAmexWS visaAmexWS;

	/** The app encoding. */
	@Value("${APP.VISAENCODING}")
	private String appEncoding;

	/** The app encoding resumenes. */
	@Value("${APP.VISAENCODING.RESUMENES}")
	private String appEncodingResumenes;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.dao.TarjetaDAO#
	 * obtenerTarjetasDeVisaWS(ar.com.santanderrio.obp.servicios.cuentas.
	 * entities.Cuenta, ar.com.santanderrio.obp.servicios.tarjetas.entities.
	 * OperacionTarjetaWSEnum)
	 */
	@Override
	public RetornoTarjetasEntity obtenerTarjetasDeVisaWS(Cuenta cuenta, OperacionTarjetaWSEnum operacion,
			Cliente cliente) throws DAOException {
		ResponseEntity<String> respuesta = visaAmexWS.obtenerRespuestaVisaAmexWS(cuenta, operacion, cliente);
		return obtenerDetallesTarjeta(respuesta, operacion);
	}

	/**
	 * Si el status del request es 200 hace el unmarshall de los objetos, sino
	 * lanza DAOException.
	 *
	 * @param respuestaWs
	 *            the respuesta ws
	 * @param operacion
	 *            the operacion
	 * @return the retorno tarjetas entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private RetornoTarjetasEntity obtenerDetallesTarjeta(ResponseEntity<String> respuestaWs, OperacionTarjetaWSEnum operacion) throws DAOException {
		if (HttpStatus.OK == respuestaWs.getStatusCode()) {
			String encoding = OperacionTarjetaWSEnum.ULTIMALIQUIDACION.equals(operacion) ? appEncodingResumenes : appEncoding;
			try {
				return unmarshallResponse(respuestaWs.getBody(), encoding);
			} catch (JAXBException e) {
				LOGGER.error("Error al parsear la respuesta {} del WS.", respuestaWs, e);
				throw new DAOException(e);
			} catch (UnsupportedEncodingException e) {
				LOGGER.error("Error al parsear la respuesta {} del WS, encoding {} incorrecto.", respuestaWs,
						encoding, e);
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
	 * @param encoding
	 *            the encoding
	 * @return the retorno tarjetas entity
	 * @throws JAXBException
	 *             the JAXB exception
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	private RetornoTarjetasEntity unmarshallResponse(String rt, String encoding) throws JAXBException, UnsupportedEncodingException {
		LOGGER.info("XML a transformar a objeto, valor {}", rt);
		rt = sanitizeString(rt);
		RetornoTarjetasEntity retornoWS = new RetornoTarjetasEntity();
		if (StringUtils.isNotEmpty(rt)) {
			if (StringUtils.contains(rt, "tarjetas")) {
				JAXBElement<TarjetasEntity> tarjetas = jAXBElementCreator(TarjetasEntity.class, rt, encoding);
				retornoWS.setTarjetas(tarjetas.getValue());
			} else {
				JAXBElement<ErrorTarjetasEntity> error = jAXBElementCreator(ErrorTarjetasEntity.class, rt, encoding);
				retornoWS.setErrorTarjetas(error.getValue());
				retornoWS.setError(Boolean.TRUE);
			}
		}
		return retornoWS;
	}

    private String sanitizeString(String rt) {
        Pattern pattern = null;
		Matcher matcher = null;
		pattern = Pattern.compile("[\\000]*");
		matcher = pattern.matcher(rt);
		if (matcher.find()) {
		    rt = matcher.replaceAll("");
		}
        return rt;
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
	<T> JAXBElement<T> jAXBElementCreator(Class<T> classObject, String rt, String encoding)
			throws JAXBException, UnsupportedEncodingException {
		JAXBContext jaxbContext = JAXBContext.newInstance(classObject);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		LOGGER.info("Unmarshall con encoding {}.", encoding);
		InputStream is = new ByteArrayInputStream(rt.getBytes(encoding));
		return jaxbUnmarshaller.unmarshal(new StreamSource(is), classObject);

	}

}

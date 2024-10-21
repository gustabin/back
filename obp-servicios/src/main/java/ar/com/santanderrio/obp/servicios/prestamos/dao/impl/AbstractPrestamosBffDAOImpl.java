package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.prestamos.constants.Constants;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.GenericRestResponseDto;
import ar.com.santanderrio.obp.servicios.prestamos.dto.LiquidacionResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.GenericRestException;
import ar.com.santanderrio.obp.servicios.prestamos.entity.TipoOfertaEnum;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PrestamosUtils;

public abstract class AbstractPrestamosBffDAOImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPrestamosBffDAOImpl.class);

	@Autowired
	protected RestWebClient restWebClient;

	@Autowired
	protected SesionParametros sesionParametros;

	protected String nombreBff;

	protected abstract String getBaseUriEncolar();

	protected abstract String getBaseUriLiquidar();

	private static final String URI_SEPARATOR = "/";

	protected AbstractPrestamosBffDAOImpl(String nombreBff) {
		this.nombreBff = nombreBff;
	}

	public EncolamientoResponseDTO encolarPrestamo(EncolamientoRequestDTO encolamientoRequestDTO) throws DAOException {
		LOGGER.debug("Inicio encolar prestamo. Solicitud {}.", encolamientoRequestDTO);
		return this.postBff(getBaseUriEncolar(), encolamientoRequestDTO, EncolamientoResponseDTO.class);
	}

	public LiquidacionResponseDTO liquidarPrestamo(TipoOfertaEnum tipoOferta, String nup) throws DAOException {
		LOGGER.debug("Inicio liquidar prestamo de nup {} oferta {}.", nup, tipoOferta);
		String uriPath = getBaseUriLiquidar() + URI_SEPARATOR + tipoOferta.name() + URI_SEPARATOR + nup;
		return this.postBff(uriPath, null, LiquidacionResponseDTO.class);
	}

	/**
	 * Hace un request POST a Prestamos-BFF
	 * 
	 * @param <R>  Response class
	 * @param <T>  Request body class
	 * @param uri  Uri rest endpoint
	 * @param body Object body
	 * @return response R instance
	 * @throws DAOException when error
	 */
	protected <R, T> R postBff(String uri, T body, Class<R> responseClass) throws DAOException {
		LOGGER.debug("Llamada al bff {} uri {} body: {}", nombreBff, uri, body);

		try {
			WebClient prestamosClient = this.configurarCliente();
			Response response = prestamosClient.path(uri).post(body);
			isErrorResponseThrowGenericRestException(response);

			R dto = response.readEntity(responseClass);
			LOGGER.debug("Fin llamada al bff {} uri {} response: {}", nombreBff, uri, dto);
			return dto;
		} catch (GenericRestException ex1) {
			throw ex1;
		} catch (Exception ex2) {
			throw new DAOException(ex2);
		}
	}

	/**
	 * If response is error throw GenericRestException
	 * 
	 * @param response
	 * @throws GenericRestException
	 */
	protected void isErrorResponseThrowGenericRestException(Response response) throws GenericRestException {
		if (response.getStatus() >= 400) {
			GenericRestResponseDto responseDto = response.readEntity(GenericRestResponseDto.class);
			throw new GenericRestException(responseDto);
		}
	}

	protected WebClient configurarCliente() throws DAOException {
		String jwt = sesionParametros.getJwt();
		WebClient prestamosClient = restWebClient.obtenerClienteRest(nombreBff);

		String sessionId = PrestamosUtils.getSessionId();
		String correlationId = PrestamosUtils.getCorrelationId();
		return prestamosClient.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
				.acceptEncoding(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header(Constants.X_SAN_CORRELATION_ID_HEADER, correlationId)
				.header(Constants.X_SAN_SESSION_ID_HEADER, sessionId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
	}
}

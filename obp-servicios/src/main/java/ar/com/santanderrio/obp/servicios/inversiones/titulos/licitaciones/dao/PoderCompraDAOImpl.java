/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionResponse;

/**
 * The Class PoderCompraDAOImpl.
 */
@Component
public class PoderCompraDAOImpl implements PoderCompraDAO{
	
	/** The rest web client. */
	@Autowired
	private RestWebClient restWebClient;
	
	/** The Constant NOMBRE_WS. */
	private static final String NOMBRE_WS = "PODER.COMPRA";
	
	/** The inversion WS helper. */
	@Autowired
	private InversionWSHelper inversionWSHelper;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PoderCompraDAOImpl.class);
		
	/** The path Crear adhesion PDC. */
	private static String pathAdhesionPDC = "/CrearAdhesion/";
	
	/** The dato. */
	@Value("${PODER.COMPRA.DATO:Prueba}")
	private String dato;

	/** The firmar. */
	@Value("${PODER.COMPRA:true}")
	private String firmar;

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.PoderCompraDAO#adherirPoderCompra(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionRequestEntity)
	 */
	@Override
	public CrearAdhesionResponse adherirPoderCompra(CrearAdhesionRequestEntity request) throws DAOException {
		request.setFirma(generarFirma());
		request.setDato(dato);
		CrearAdhesionResponse response = new CrearAdhesionResponse();
		
		try {
			WebClient service = crearLlamadaAWebService(pathAdhesionPDC);
			response = service.post(request, CrearAdhesionResponse.class);
			if (response == null || response.getCodigo() != 0 || !response.getDatos().getResultado().equals("1")) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error("Error en el servicio Crear Adhesion PDC");
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error("Error en el servicio Crear Adhesion PDC");
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return response;
	}
	
	/**
	 * genera la firma para llamar a los servicios de P&L.
	 *
	 * @return the string
	 */
	private String generarFirma() {
		String firma = "";
		if (Boolean.TRUE.equals(Boolean.parseBoolean(firmar))) {
			try {
				firma = inversionWSHelper.getDatosFirmados(dato);
			} catch (Exception e) {
				LOGGER.error("Error creando firma", e);
			}
		}
		return firma;
	}
	
	/**
	 * Crear llamada A web service.
	 *
	 * @param pathDeConsulta
	 *            the path de consulta
	 * @return the web client
	 * @throws DAOException
	 *             the DAO exception
	 */
	private WebClient crearLlamadaAWebService(String pathDeConsulta) throws DAOException {

		WebClient service = restWebClient.obtenerClienteRest(NOMBRE_WS);
		service.accept(MediaType.APPLICATION_JSON);
		service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
		service.path(pathDeConsulta);

		return service;
	}


}

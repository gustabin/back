/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionDatosRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionResponse;

/**
 * The Class PoderCompraDAOImpl.
 */
//@Component
public class PoderCompraMockDAOImpl implements PoderCompraDAO{
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PoderCompraMockDAOImpl.class);
	
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
		
		CrearAdhesionResponse response = null;
        String aux = null;
        BufferedReader bReader = null;
        CrearAdhesionDatosRequest datos = request.getDatos();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            String inputAsJsonIn = mapper.writeValueAsString(request);
            LOGGER.info("Request Entreda: {}", inputAsJsonIn);
            String inputAsJson = mapper.writeValueAsString(datos);
            LOGGER.info("Lista como json: {}", inputAsJson);
        } catch (JsonGenerationException e1) {
            throw new DAOException();
        } catch (JsonMappingException e1) {
            throw new DAOException();
        } catch (IOException e1) {
            throw new DAOException();
        }
        try {
            String fileName = "/aplicaciones/hb/conf/obp-properties/obp-config/desa-5/mock/files/mock";
            fileName = fileName + datos.getOperacion();
            
            LOGGER.info("Mock File path: {}:", fileName);
            bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
            aux = IOUtils.toString(bReader);
            
            LOGGER.info("Response crear adhesion mock: {}:",aux);
            response = mapper.readValue(aux, CrearAdhesionResponse.class);
            bReader.close();
        } catch (Exception e) {
            LOGGER.error("Error en el servicio Maps  mock");
            throw new DAOException();
        }
        if (response.getCodigo() != 0) {
            LOGGER.error("Response crear adhesion  con Error: {}", response.getCodigo());
            throw new DAOException();
        }
        return response;
	}
}

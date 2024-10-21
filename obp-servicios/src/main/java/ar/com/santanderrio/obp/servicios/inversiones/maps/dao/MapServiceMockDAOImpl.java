package ar.com.santanderrio.obp.servicios.inversiones.maps.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsResponse;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.BajaAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaAdhesionMapsResponse;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaFeriadosRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.RequestMap;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;

//@Component
public class MapServiceMockDAOImpl implements MapServiceDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MapServiceMockDAOImpl.class);

	/** The firmar. */
	@Value("${INVERSIONES.MAPS.MOCK.FILE}")
	private String mockfile;

    @Override
    public AltaAdhesionMapsResponse altaAdhesionMaps(AltaAdhesionMapsRequestEntity request) throws DAOException {
        AltaAdhesionMapsResponse response = null;
        String aux = null;
        BufferedReader bReader = null;
        RequestMap datos = request.getDatos();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        Boolean stackCarga = Boolean.FALSE;
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
            String opera = StringUtils.EMPTY;
            String fileName = mockfile;
            if ("01390639".equals(datos.getNup())) {
                fileName = mockfile + 1;
            }
            if (datos.getIdServicio() == null) { 
                opera = ".home";
            } else {
                opera = ".alta";
            }
            if (datos.getIdServicio() != null && (datos instanceof FormularioControl)) { 
                opera = ".alta.paso2";
                stackCarga = Boolean.TRUE;
                
            }
            fileName = fileName + opera;
            
            LOGGER.info("Mock File path: {}:", fileName);
            bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
            aux = IOUtils.toString(bReader);
            
            LOGGER.info("Response Alta adhesion mock: {}:",aux);
            response = mapper.readValue(aux, AltaAdhesionMapsResponse.class);
            bReader.close();
        } catch (Exception e) {
            LOGGER.error("Error en el servicio Maps  mock");
            throw new DAOException();
        }
        if (response.getCodigo() != 0) {
            LOGGER.error("Response Alta adhesion  con Error: {}", response.getCodigo());
            throw new DAOException();
        }
        if(stackCarga && ("simulacion".equals(((FormularioControl)request.getDatos()).getEstado()))) {
            ((FormularioControl)response.getDatos()).setEstado("confirmacion");
        }
        response.getDatos().setSegmento(datos.getSegmento());
        return response;
    }

	@Override
	public ConsultaAdhesionMapsResponse consultaAdhesionMaps(ConsultaAdhesionRequestEntity request)
			throws DAOException {
		ConsultaAdhesionMapsResponse response = null;
		String aux = null;
		BufferedReader bReader = null;
		RequestMap datos = request.getDatos();
		ObjectMapper mapper = new ObjectMapper();
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
		mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			String opera = "ConsultaAdhesion.json";
			String fileName = mockfile;
			fileName = fileName + opera;
			LOGGER.info("Mock File path: {}:", fileName);
			bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
			aux = IOUtils.toString(bReader);

			LOGGER.info("Response Alta adhesion mock: {}:", aux);
			response = mapper.readValue(aux, ConsultaAdhesionMapsResponse.class);
			bReader.close();
		} catch (Exception e) {
			LOGGER.error("Error en el servicio Maps  mock");
			throw new DAOException();
		}
		if (response.getCodigo() != 0) {
			LOGGER.error("Response Alta adhesion  con Error: {}", response.getCodigo());
			throw new DAOException();
		}
		return response;
	}

	@Override
	public ConsultaAdhesionMapsResponse bajaAdhesionMaps(BajaAdhesionRequestEntity request) throws DAOException {
		ConsultaAdhesionMapsResponse response = null;
		String aux = null;
		BufferedReader bReader = null;
		RequestMap datos = request.getDatos();
		ObjectMapper mapper = new ObjectMapper();
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
		mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			String opera = "BajaAdhesion.json";
			String fileName = mockfile;
			fileName = fileName + opera;
			LOGGER.info("Mock File path: {}:", fileName);
			bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
			aux = IOUtils.toString(bReader);

			LOGGER.info("Response Alta adhesion mock: {}:", aux);
			response = mapper.readValue(aux, ConsultaAdhesionMapsResponse.class);
			bReader.close();
		} catch (Exception e) {
			LOGGER.error("Error en el servicio Maps  mock");
			throw new DAOException();
		}
		if (response.getCodigo() != 0) {
			LOGGER.error("Response Alta adhesion  con Error: {}", response.getCodigo());
			throw new DAOException();
		}
		return response;
	}

	@Override
	public List<String> consultaFeriados(ConsultaFeriadosRequestEntity request) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}

// Análisis del archivo MapServiceMockDAOImpl.java

// El archivo MapServiceMockDAOImpl.java es una implementación de la interfaz MapServiceDAO que se utiliza para simular la interacción con la capa de datos para realizar operaciones relacionadas con la adhesión y consulta de feriados.

// Observaciones

// La clase utiliza la anotación @Value para inyectar el valor de la propiedad mockfile que se utiliza para leer los archivos de mockeo.
// La clase utiliza la biblioteca Jackson para serializar y deserializar objetos JSON.
// La clase utiliza la biblioteca Apache Commons para leer archivos y realizar operaciones de lectura y escritura.
// La clase implementa los métodos de la interfaz MapServiceDAO para simular la interacción con la capa de datos.
// La clase utiliza archivos de mockeo para simular la respuesta de la capa de datos.
// Preguntas para avanzar

// ¿Cuál es el propósito de la propiedad mockfile y cómo se utiliza en la implementación de MapServiceMockDAOImpl?
// ¿Cómo se crean y se utilizan los archivos de mockeo en la implementación de MapServiceMockDAOImpl?
// ¿Cómo se manejan las excepciones en la implementación de MapServiceMockDAOImpl?
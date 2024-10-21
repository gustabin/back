package ar.com.santanderrio.obp.servicios.referidos.dao;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.referidos.dto.ReferidosTokenInputDTO;

@Component("ReferidosDAO")
public class ReferidosDAOImpl implements ReferidosDAO{

	private static final Logger LOGGER = LoggerFactory.getLogger(ReferidosDAOImpl.class);
	
	private static final String REFERENCIA = "REF_CUENTA_BLANCA_2020";
	
    private static final String NOMBRE_WS = "REFERIDOS.TOKEN";

	@Autowired
    private RestWebClient restWebClient;
	
	@Autowired
	private ArchivoDeRecursosDAO archivoResource;
	
	@Override
	public String obtenerTokenReferidos(String nup) throws DAOException {
		WebClient client = restWebClient.obtenerClienteRest(NOMBRE_WS);

		ReferidosTokenInputDTO input = new ReferidosTokenInputDTO();
		input.setNup(nup);
		input.setSec(REFERENCIA);

		Response response = client
				.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
				.accept(MediaType.APPLICATION_JSON)
				.post(input);
		
		if(response != null) {
			int statusCode = response.getStatusInfo().getStatusCode();
			if(statusCode == HttpStatus.SC_OK) {
				LOGGER.info("Respuesta satisfactoria de la llamada para obtener token en referidos de {}", nup);
				return response.readEntity(String.class);
			} else {
				LOGGER.info("Problemas para obtener token en referidos de {}. Code {}", nup, statusCode);
				throw new DAOException("Problemas para obtener token en referidos. Code " + statusCode);
			}
		} else {
			LOGGER.info("Problemas para obtener token en referidos de {}", nup);
			throw new DAOException("Response Error");
		}
	}
	
	@Override
	public List<String> obtenerNups(String archivo) throws DAOException {
		List<String> nupsProximaBajaTarjCoord = new LinkedList<String>();

		int linea = 0;
		int largoDatosMinimo = 5;
		for (String lineaTexto : archivoResource.leerArchivo(archivo)) {
			++linea;

			if (largoDatosMinimo < lineaTexto.length()) {
				nupsProximaBajaTarjCoord.add(lineaTexto);
			} else {
				LOGGER.debug("Error de formato en la linea: {0} del archivo {1}.txt", linea, archivo);
			}
		}
		return nupsProximaBajaTarjCoord;
	}
}
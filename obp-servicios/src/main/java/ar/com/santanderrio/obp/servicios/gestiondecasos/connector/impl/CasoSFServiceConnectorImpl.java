package ar.com.santanderrio.obp.servicios.gestiondecasos.connector.impl;

import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.gestiondecasos.connector.CasoSFServiceConnector;
import ar.com.santanderrio.obp.servicios.gestiondecasos.connector.models.InformationObpCardsDto;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.base.dao.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.client.WebClient;

@Component
public class CasoSFServiceConnectorImpl implements CasoSFServiceConnector {

	private static final Logger LOGGER = LoggerFactory.getLogger(CasoSFServiceConnectorImpl.class);
	private static final String GET_OBP_CARDS = "api/v1/ObpCards";
	private static final String CASOSF_API_URL = "CASOSF-API";

	@Autowired
    private RestWebClient restWebClient;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

	@Override
	public List<InformationObpCardsDto> getObpCards() {
		InformationObpCardsDto[] response =  {};
		try {
			LOGGER.info("Consultando las tarjetas a mostrar en solicitudes/tarjetas");
			WebClient casoSFClient = this.configCasoSFClient(CASOSF_API_URL);
			LOGGER.info("Configuracion client terminado");
            casoSFClient.path(GET_OBP_CARDS).query("section", "CARDS");			
            response = casoSFClient.get(InformationObpCardsDto[].class);
		} catch (Exception e) {
			LOGGER.error("Ocurrio un error recuperando las tarjetas a mostrar en solicitudes/tarjetas.", e);
		}
		return Arrays.asList(response);
	}

	private WebClient configCasoSFClient(String nombreWs) throws DAOException {

        String jwt = sesionParametros.getJwt();
        WebClient casoSFClient = restWebClient.obtenerClienteRest(nombreWs);		
        return casoSFClient
                .type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
                .acceptEncoding(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("AUTHORIZATION", "Bearer " + jwt);
    }
}
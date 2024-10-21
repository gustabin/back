package ar.com.santanderrio.obp.servicios.recuperaciones.dao.impl;

import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.recuperaciones.dao.RefinancingDAO;
import ar.com.santanderrio.obp.servicios.recuperaciones.entities.FinancialTypeDTO;

@Component
public class RefinancingDAOImpl implements RefinancingDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(RefinancingDAOImpl.class);
	
	@Value(value = "${FINANCIAL_HEALTH.PATH}")
	private String path;

    @Autowired
    private RestWebClient restWebClient;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    private static final String API_NAME = "FINANCIAL_HEALTH";

    private static final String HEADER_AUTHORIZATION = "Authorization";

    /**
     * Indica si el nup visualiza opciones de refi tradicional
     */
	@Override
	public boolean aplicaRefinanciacion() {
		boolean aplica = false;
		try {
            WebClient client = this.configurarCliente(API_NAME);
            client.path(path);
            FinancialTypeDTO response = client.get(FinancialTypeDTO.class);
            aplica =  (response != null && response.getType() != null && !response.getType().isEmpty());
            estadisticaManager.add(EstadisticasConstants.VERIFICAR_OFERTA_REFINANCIACION_TRADICIONAL,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } catch (Exception e) {
            LOGGER.error("Error llamando a la API de Financial Health.");
            estadisticaManager.add(EstadisticasConstants.VERIFICAR_OFERTA_REFINANCIACION_TRADICIONAL,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        return aplica;
    }
	
	
	/**
	 * Obtiene cliente web
	 * @param apiName
	 * @return
	 * @throws DAOException
	 */
	private WebClient configurarCliente(String apiName) throws DAOException{

        String jwt = sesionParametros.getJwt();
        WebClient client = restWebClient.obtenerClienteRest(apiName);

        return client
                .type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
                .acceptEncoding(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HEADER_AUTHORIZATION, "Bearer " + jwt);
    }
	
}

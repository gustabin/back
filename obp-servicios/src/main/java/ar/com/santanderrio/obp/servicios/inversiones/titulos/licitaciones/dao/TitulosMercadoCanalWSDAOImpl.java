/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.mercado.canal.IMercadosCanalService;
import ar.com.santanderrio.obp.generated.webservices.mercado.canal.IMercadosCanalServiceConsultarOperacionesTextMercadosServiceFaultFaultFaultMessage;
import ar.com.santanderrio.obp.servicios.inversiones.licitaciones.ws.MercadosCanalWSImpl;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOperacionesRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaOperacionesResponse;


/**
 * The Class TitulosMercadoCanalWSDAOImpl.
 */
@Component("titulosMercadoCanalDAO")
public class TitulosMercadoCanalWSDAOImpl implements TitulosMercadoCanalDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TitulosMercadoCanalWSDAOImpl.class);
    	
	/** The Constant CODIGO_OK. */
	private static final String CODIGO_OK = "0";
	
	/** The Constant ERROR_FUERA_DE_HORARIO. */
	private static final String ERROR_FUERA_DE_HORARIO = "4";

	/** The Constant LIMITE_TIEMPO_SUPERADO. */
	private static final String LIMITE_TIEMPO_SUPERADO = "3";
	
	
	   /** The ws soap. */
    @Autowired
    private MercadosCanalWSImpl wsSoap;
    
    /** The app encoding. */
    @Value("${APP.ENCODING}")
    private String appEncoding;
    
	
	
    // retornar List<DatosConsultaOperacionesResponse>  
	
	/* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.TitulosMercadoCanalDAO#consultarOperacionesText(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOperacionesRequestEntity)
     */
    @Override
	public List<DatosConsultaOperacionesResponse> consultarOperacionesText(ConsultaOperacionesRequestEntity request) throws DAOException {
		LOGGER.info("llamando a servicio soap MERCADOSCANAL.consultarOperacionesText");
		String miJson = (new GsonBuilder()).create().toJson(request);
		miJson = StringUtils.replace(miJson, "\\\\", "\\");
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        IMercadosCanalService cliente = null;
        String response = null;
        DatosConsultaOperacionesResponse[] datos = null;

        try {
            cliente = wsSoap.obtenerPort();
            try {
                response = cliente.consultarOperacionesText(miJson);
                Type founderListType = new TypeToken<ArrayList<DatosConsultaOperacionesResponse>>(){}.getType();
               datos = gson.fromJson(response, DatosConsultaOperacionesResponse[].class);
                //List<DatosConsultaOperacionesResponse> datosList = gson.fromJson(response, founderListType);
//                datosList = gson.fromJson(response, founderListType);
                
            } catch (IMercadosCanalServiceConsultarOperacionesTextMercadosServiceFaultFaultFaultMessage e) {
                LOGGER.error("Error al invocar al web service MERCADOSCANAL. {}.", e.getMessage(), e);
                throw new DAOException(e);
            } catch (Exception ex) {
            	LOGGER.error("ERRRRRRR", ex);
            }
        } finally {
            wsSoap.liberarPort(cliente);
            if (response == null) {
                LOGGER.debug("Error al invocar al web service BEL. ");
                throw new DAOException();
            }
        }
        
        return Arrays.asList(datos);
    }

	

}

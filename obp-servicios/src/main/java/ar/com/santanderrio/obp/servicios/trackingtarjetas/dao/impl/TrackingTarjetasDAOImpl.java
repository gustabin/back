/*
 * 
 */
package ar.com.santanderrio.obp.servicios.trackingtarjetas.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasIn;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasOut;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasSecurityException_Exception;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasService;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.dao.TrackingTarjetasDAO;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.entities.ConsultaTarjetasMonederoOutEntity;

/**
 * TrackingTarjetasDAOImpl.
 *
 * @author Silvina_Luque
 */
@Component
public class TrackingTarjetasDAOImpl extends IatxBaseDAO  implements TrackingTarjetasDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackingTarjetasDAOImpl.class);
    
    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;
    
    /** The servicio CNSTARXNUP . */
    private static final String SERVICIO_CNSCTAMONE = "CNSCTAMONE";
    
    /** The version CNSTARXNUP . */
    private static final String VERSION_CNSCTAMONE ="100";
    
    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;
    
    /** Gestionar ws Andreani. */
    @Autowired
    private GestionarWS<TrackingTarjetasService> trackingTarjetasClient;

    /**
	 * consultarTraza WS que retorna el estado de distribucion de las tarjetas
	 * del usuario.
	 *
	 * @param inRequest
	 *            the in request
	 * @return the tracking tarjetas out
	 * @throws DAOException
	 *             the DAO exception
	 */
    @Override
    public TrackingTarjetasOut consultarTraza(TrackingTarjetasIn inRequest) throws DAOException {
        LOGGER.debug("Consultado traza de tarjetas WSDelTarjSOAP");
        TrackingTarjetasService services = null;
        TrackingTarjetasOut respuesta = null;
        try {
            services = trackingTarjetasClient.obtenerPort();
            respuesta = services.consultarTraza(inRequest);            
        } catch (TrackingTarjetasSecurityException_Exception e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e);
        } catch (RuntimeException e) {
            LOGGER.error("Hubo un error al invocar al ws de Tracking de Tarjetas.", e);
            throw new DAOException(e);
        } finally {
            trackingTarjetasClient.liberarPort(services);
        }
        return respuesta;
    }
    
    /**
	 * getDatosTarjetaMonedero.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoConsultaMonedero
	 *            the tipo consulta monedero
	 * @return the datos tarjeta monedero
	 * @throws DAOException
	 *             the DAO exception
	 */
    @Override
    public ConsultaTarjetasMonederoOutEntity getDatosTarjetaMonedero(Cliente cliente, String tipoConsultaMonedero) throws DAOException {
        LOGGER.info("TrackingTarjetasDAOImpl _ Iniciando metodo getDatosTarjetaMonedero");
        IatxRequest iatxRequest = new IatxRequest(SERVICIO_CNSCTAMONE, VERSION_CNSCTAMONE);
        ConsultaTarjetasMonederoOutEntity tarjetasMonedero = new ConsultaTarjetasMonederoOutEntity();
        
        try {
            LOGGER.debug("TrackingTarjetasDAOImpl _ Iniciando llamada iatx SERVICIO_CNSCTAMONE");
            IatxRequestData iatxRequestData = this.generateRequestDataCnsMonedero(cliente, tipoConsultaMonedero);
            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            LOGGER.debug("TrackingTarjetasDAOImpl _ Finalizando llamada iatx SERVICIO_CNSCTAMONE");
            int errorCode = iatxResponse.getErrorCode();
            if (OK_CODIGO_RETORNO == errorCode) {
                tarjetasMonedero = processTrama(iatxResponse.getTrama(), ConsultaTarjetasMonederoOutEntity.class);
            } else {
                LOGGER.debug("Error servicio SERVICIO_CNSCTAMONE, codigo retorno distinto de 0");
                throw new DAOException("");
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        LOGGER.debug("TrackingTarjetasDAOImpl _ Finalizando llamada getDatosTarjetaMonedero");
        return tarjetasMonedero;

    }
    
    /**
	 * generateRequestDataCnsMonedero.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoConsultaMonedero
	 *            the tipo consulta monedero
	 * @return the iatx request data
	 */
    private IatxRequestData generateRequestDataCnsMonedero(Cliente cliente, String tipoConsultaMonedero) {
        IatxRequestData iatxRequestData = new IatxRequestData(cliente);
        iatxRequestData.addBodyValue(tipoConsultaMonedero);
        return iatxRequestData;
    }    

    
}

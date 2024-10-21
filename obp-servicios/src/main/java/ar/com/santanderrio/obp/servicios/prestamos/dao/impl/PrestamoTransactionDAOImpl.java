package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.premify.dao.impl.PremifyDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.contracts.TransactionEvent;
import ar.com.santanderrio.obp.servicios.prestamos.contracts.TransactionRequest;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoTransactionDAO;

@Component
public class PrestamoTransactionDAOImpl implements PrestamoTransactionDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PremifyDAOImpl.class);

    @Autowired
    private RestWebClient restWebClient;
    
    private static final String NOMBRE_WS = "LOANS-TRANSACTIONS";

    @Override
    public void postEnqueue(TransactionRequest request){
        LOGGER.info("Encolando Prestamos a 48hs.");
        try {
            WebClient kafkaClient = this.configurarClientePrestamosValidator(NOMBRE_WS);
            kafkaClient.path("/publish");
            
            String propEnqueue = "MALUG-prestamos_transactions-queue"; 
            TransactionEvent event = new TransactionEvent(propEnqueue, request);
            kafkaClient.post(event);

        } catch (Exception e) {
            LOGGER.error("Error llamando al topico de Kafka - Encolar: {}", e.getMessage());
        }
    }
    
    @Override
    public void putDequeue(TransactionRequest request){
        LOGGER.info("Desencolando Prestamos a 48hs.");
        try {
            WebClient kafkaClient = this.configurarClientePrestamosValidator(NOMBRE_WS);
            kafkaClient.path("/publish");
            
        	String propAccredit = "MALUG-prestamos_transactions-dequeue";
            TransactionEvent event = new TransactionEvent(propAccredit, request);
            kafkaClient.post(event);

        } catch (Exception e) {
            LOGGER.error("Error llamando al topico de Kafka - Encolar: {}", e.getMessage());
        }
    }

    private WebClient configurarClientePrestamosValidator(String nombreWs) throws DAOException{

        WebClient kafkaClient = restWebClient.obtenerClienteRest(nombreWs);

        return kafkaClient
                .type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
                .acceptEncoding(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

}

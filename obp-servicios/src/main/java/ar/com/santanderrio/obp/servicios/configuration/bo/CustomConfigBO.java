package ar.com.santanderrio.obp.servicios.configuration.bo;

import java.util.Map;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * The Interface CustomConfigBO.
 */
public interface CustomConfigBO {

    /**
     * Set.
     *
     * @param configID the config ID
     * @param valueMap the value map
     * @return the respuesta
     */
    Respuesta<Void> set(String configID, Map<String, String> valueMap);
 
}

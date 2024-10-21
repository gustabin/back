package ar.com.santanderrio.obp.servicios.configuration.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.configuration.view.CustomConfigView;

/**
 * The Interface CustomConfigManager.
 */
public interface CustomConfigManager {

    /**
     * Set.
     *
     * @param view the view
     * @return the respuesta
     */
    Respuesta<Void> set(CustomConfigView view);

}

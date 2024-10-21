/**
 * 
 */
package ar.com.santanderrio.obp.servicios.modulos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPreAuthorize;

/**
 * @author sergio.e.goldentair
 *
 */
@Component
public class PruebaPermisosDAOImpl implements PruebaPermisosDAO {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PruebaPermisosDAOImpl.class);

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.modulos.PruebaPermisosDAO#probarPermiso()
     */
    @Override
    @CustomPreAuthorize(AccionController.IR_INICIO_CUENTAS)
    public void probarPermiso() {
        LOGGER.info("Ejecuta la implementacion.");
    }

}

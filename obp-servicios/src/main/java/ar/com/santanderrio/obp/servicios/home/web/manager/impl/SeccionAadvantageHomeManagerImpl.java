	/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionAadvantageHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;
import ar.com.santanderrio.obp.servicios.referidos.bo.ReferidosBO;

/**
 * The Class SeccionAdvantageHomeManagerImpl.
 */
@Component
public class SeccionAadvantageHomeManagerImpl extends AbstractSeccionHomeManager
        implements SeccionAadvantageHomeManager {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SeccionAadvantageHomeManagerImpl.class);

    /** The advantage. */
    private static final String ADVANTAGE = "AAdvantage";

    /** The advantage. */
    private static final String SUBTITULO_MILLAS = "Detalle de Millas";

    private static final String ADHESION_SUPERCLUB = "Adhesión a SuperClub";
        
    private static final String REFERIDOS = "Referí y ganá";

    
    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;
    
    @Autowired
    private ReferidosBO referidosBO;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.
     * SeccionAdvantageHomeManager#obtenerSeccion(ar.com.santanderrio.obp.servicios.
     * clientes.entities.Cliente)
     */
    @Override
    public SeccionView obtenerSeccion() {
        if (sesionParametros.getNumeroSocioAadvantage() == null) {
            LOGGER.info("No se cargan los menu del topBar para la accion {} ya que esta deshabilitado desde la base.",
                    AccionController.IR_AADVANTAGE);
            return null;
        } else {
            SeccionView seccionAdvantage = new SeccionView();
            seccionAdvantage.setTitulo(ADVANTAGE);
            seccionAdvantage.setAccion(AccionController.IR_AADVANTAGE.getDescripcion());
            List<SeccionView> items = new ArrayList<SeccionView>();
            agregarItem(true, items, AccionController.IR_INICIO_MILLAS, SUBTITULO_MILLAS);
            agregarItem(true, items, AccionController.IR_CAMBIO_GRUPO_AFINIDAD_POR_SUPERCLUB, ADHESION_SUPERCLUB);
            boolean tieneNupEnListado = referidosBO.tieneNupEnListado();
            if (!tieneNupEnListado) {
            	agregarItem(true, items, AccionController.REFERIDOS_COMPARTIR, REFERIDOS);
            }
            seccionAdvantage.setItems(items);
            return seccionAdvantage;
        }
    }

}

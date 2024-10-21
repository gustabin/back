package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionReclamosHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Class SeccionReclamosHomeManagerImpl.
 */
@Component
public class SeccionReclamosHomeManagerImpl extends AbstractSeccionHomeManager
        implements SeccionReclamosHomeManager {

    /** The Constant RECLAMOS. */
    private static final String RECLAMOS = "Reclamos";
    
    /** The Constant FORM_RECLAMOS. */
    private static final String FORM_RECLAMOS = "Formulario de Reclamos";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SeccionReclamosHomeManagerImpl.class);

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.SeccionReclamosHomeManager#obtenerSeccion(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public SeccionView obtenerSeccion(Cliente cliente) {
        ModuloPermiso moduloPermiso = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_INICIO_FORM_COSMOS_SOLICITUDES);
        if (moduloPermiso.isModuloOculto()) {
            LOGGER.info("No se cargan los menu del topBar para la accion {} ya que esta deshabilitado desde la base.",
                    AccionController.IR_INICIO_FORM_COSMOS_SOLICITUDES);
            return null;
        } else {
            SeccionView seccionReclamos = new SeccionView();
            seccionReclamos.setTitulo(RECLAMOS);
            seccionReclamos.setAccion(AccionController.IR_INICIO_FORM_COSMOS_SOLICITUDES.getDescripcion());
            if (moduloPermiso.tienePermisoDeVisibilidad()) {
                List<SeccionView> items = new ArrayList<SeccionView>();
                agregarItem(true, items, AccionController.IR_INICIO_FORM_COSMOS_SOLICITUDES, FORM_RECLAMOS);
                seccionReclamos.setItems(items);
            } else if (moduloPermiso.muestraMensaje()) {
                seccionReclamos.setMensajeNoDisponible(moduloPermiso.getMensaje());
            }
            return seccionReclamos;
        }
    }

}

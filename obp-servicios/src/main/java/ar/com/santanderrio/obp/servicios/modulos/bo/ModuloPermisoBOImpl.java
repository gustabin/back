/**
 * 
 */
package ar.com.santanderrio.obp.servicios.modulos.bo;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Class ModuloPermisoBOImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("moduloPermisoBO")
public class ModuloPermisoBOImpl implements ModuloPermisoBO {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ModuloPermisoBOImpl.class);

    /** The modulo permiso DAO. */
    @Autowired
    private ModuloPermisoDAO moduloPermisoDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO#
     * limpiarModulosPermisos()
     */
    @Override
    public Respuesta<Boolean> limpiarModulosPermisos() {
        moduloPermisoDAO.limpiarModulosPermisos();
        return respuestaFactory.crearRespuestaOk(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO#
     * obtenerModuloPermisoPorAccion(ar.com.santanderrio.obp.servicios.home.web.
     * view.AccionController)
     */
    @Override
    public ModuloPermiso obtenerModuloPermisoPorAccion(AccionController accionController) {
        try {
            Map<String, ModuloPermiso> modulosPermisos = moduloPermisoDAO.obtenerModulosPermisos();
            ModuloPermiso moduloPermiso = modulosPermisos.get(accionController.getDescripcion());
            if (moduloPermiso != null) {
                return moduloPermiso;
            }
        } catch (DAOException e) {
            LOGGER.error("No fue posible consultar si el modulo {} tiene permitido la operacion solicitada. ",
                    accionController, e);
        }
        LOGGER.info("Continua con permiso default de {}.", ModuloEstado.MOSTRAR);
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setAccionController(accionController);
        moduloPermiso.setMenu(true);
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        return moduloPermiso;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO#
     * tienePermisoMostrar(ar.com.santanderrio.obp.servicios.home.web.view.
     * AccionController)
     */
    @Override
    public boolean tienePermisoMostrar(AccionController accionController) {
        ModuloPermiso moduloPermiso = this.obtenerModuloPermisoPorAccion(accionController);
        return moduloPermiso.tienePermisoDeVisibilidad();
    }

}

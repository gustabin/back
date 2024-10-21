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

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionSuperClubHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.referidos.bo.ReferidosBO;
import ar.com.santanderrio.obp.servicios.referidos.manager.ReferidosManager;
import ar.com.santanderrio.obp.servicios.referidos.view.ReferidosInicioResponseView;

/**
 * The Class SeccionSuperClubHomeManagerImpl.
 */
@Component
public class SeccionSuperClubHomeManagerImpl extends AbstractSeccionHomeManager implements SeccionSuperClubHomeManager {
    
	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SeccionSuperClubHomeManagerImpl.class);
    
	@Autowired
	private ReferidosManager referidosManager;
	
	@Autowired
	private ReferidosBO referidosBO;
    
    private static final String BENEFICIOS = "Beneficios";
    
    private static final String REFERIDOS = "Referí y ganá";

    static final String SUPERCLUB_PREMIFY = "SuperClub+";
    
    static final String PREMIFY = "premify";

    private static final String SORPRESA = "Sorpresa";

    @Autowired
    SesionParametros sesionParametros;

    /**
     * Obtener seccion super club.
     *
     * @param cliente
     *            the cliente
     * @return the seccion view
     */
    public SeccionView obtenerSeccion(Cliente cliente) {
        ModuloPermiso moduloPermiso = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_SUPERCLUB);        
        if (moduloPermiso.isModuloOculto()) {
            LOGGER.info("No se cargan los menu del topBar para la accion {} ya que esta deshabilitado desde la base.",
                    AccionController.IR_SUPERCLUB);
            return null;
        } else {
            SeccionView seccionBeneficios = new SeccionView();
            seccionBeneficios.setTitulo(BENEFICIOS);
            seccionBeneficios.setAccion(AccionController.IR_SUPERCLUB.getDescripcion());
            if (moduloPermiso.tienePermisoDeVisibilidad()) {
                List<SeccionView> items = new ArrayList<SeccionView>();
                agregarItemConMicrofront(items, AccionController.IR_SUPERCLUB_PREMIFY, SUPERCLUB_PREMIFY, PREMIFY);
                agregarItem(true, items, AccionController.IR_SORPRESA, SORPRESA);
                boolean tieneNupEnListado = referidosBO.tieneNupEnListado();
                if (!tieneNupEnListado) {
                	agregarItem(true, items, AccionController.REFERIDOS_COMPARTIR, REFERIDOS);
                }
                seccionBeneficios.setItems(items);
            } else if (moduloPermiso.muestraMensaje()) {
                seccionBeneficios.setMensajeNoDisponible(moduloPermiso.getMensaje());
            }
            return seccionBeneficios;
        }
    }
    
    public String consultaUrlReferidos() {
    	String urlCompletaReferidos = "";
    	Respuesta<ReferidosInicioResponseView> view = referidosManager.obtenerInicioReferidos();
    	urlCompletaReferidos = view.getRespuesta().getUrlInvitacion() + view.getRespuesta().getFirma();
		return urlCompletaReferidos;
    }
    
    public boolean consultarPermisosReferidos() {
    	boolean tienePermisos = false;
    	ModuloPermiso moduloPermisoReferidos = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_REFERIDOS);
    	if (moduloPermisoReferidos.tienePermisoDeVisibilidad()) {
    		tienePermisos = true;
    	}
    	return tienePermisos;
    }
}

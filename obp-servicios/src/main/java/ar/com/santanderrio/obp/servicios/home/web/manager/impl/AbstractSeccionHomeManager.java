/*
 *
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import ar.com.santanderrio.obp.servicios.microfrontend.bo.MicrofrontendsBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.MicrofrontView;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionMicrofrontView;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The Class AbstractSeccionHomeManager.
 */
@Component
public abstract class AbstractSeccionHomeManager {

    /**
     * The modulo permiso BO.
     */
    @Autowired
    protected ModuloPermisoBO moduloPermisoBO;

    /**
     * The administrador permisos.
     */
    @Autowired
    protected AdministradorPermisos administradorPermisos;

    @Autowired
    private MicrofrontendsBO microfrontendsBO;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    @Value("${BFF_URL.MICROFRONTEND}")
    protected String microfrontendUrl;

    /**
     * Agregar Item a la lista que recibe la vista para armar el topBar.
     *
     * @param seMuestra the se muestra
     * @param items     the items
     * @param accion    the accion
     * @param texto     the texto
     */
    void agregarItem(boolean seMuestra, List<SeccionView> items, AccionController accion, String texto) {
        if (seMuestra) {
            administradorPermisos.addNewGrant(accion);
            ModuloPermiso moduloPermiso = moduloPermisoBO.obtenerModuloPermisoPorAccion(accion);
            if (moduloPermiso.tienePermisoDeVisibilidad() || moduloPermiso.muestraMensaje()) {
                SeccionView itemSeccionView = buildItemSeccionView(texto, accion);
                if (moduloPermiso.muestraMensaje()) {
                    itemSeccionView.setMensajeNoDisponible(moduloPermiso.getMensaje());
                }
                items.add(itemSeccionView);
            }
        } else {
            administradorPermisos.removeGrant(accion);
        }

    }
    
    void agregarItem(boolean seMuestra, List<SeccionView> items, String accion, String texto) {
        SeccionView itemSeccionView = buildItemSeccionView(texto, accion);
        items.add(itemSeccionView);
    }

    /**
     * Agregar Item a la lista que recibe la vista para armar el topBar.
     *
     * @param seMuestra the se muestra
     * @param items     the items
     * @param accion    the accion
     * @param texto     the texto
     */
    void agregarItem(boolean seMuestra, List<SeccionView> items, AccionController accion, String texto, String url) {
        if (seMuestra) {
            administradorPermisos.addNewGrant(accion);
            ModuloPermiso moduloPermiso = moduloPermisoBO.obtenerModuloPermisoPorAccion(accion);
            if (moduloPermiso.tienePermisoDeVisibilidad() || moduloPermiso.muestraMensaje()) {
                SeccionView itemSeccionView = buildItemSeccionView(texto, accion, url);
                if (moduloPermiso.muestraMensaje()) {
                    itemSeccionView.setMensajeNoDisponible(moduloPermiso.getMensaje());
                }
                items.add(itemSeccionView);
            }
        } else {
            administradorPermisos.removeGrant(accion);
        }

    }

    /**
     * Agregar Item a la lista que recibe la vista para armar el topBar.
     *
     * @param items     the items
     * @param accion    the accion
     * @param texto     the texto
     */
    void agregarItemConMicrofront(List<SeccionView> items, AccionController accion, String texto, String microFrontName) {
        String nup = sesionCliente.getCliente().getNup();
        boolean seMuestra = microfrontendsBO.consultaVisibilidadPorNup(nup, microFrontName, Boolean.TRUE);

        this.agregarMicrofront(seMuestra, items, accion, texto, microFrontName);
    }

    /**
     * Agregar Item a la lista que recibe la vista para armar el topBar.
     *
     * @param seMuestra el item
     * @param items     the items
     * @param accion    the accion
     * @param texto     the texto
     */
    void agregarItemConMicrofront(boolean seMuestra, List<SeccionView> items, AccionController accion, String texto, String microFrontName) {
    	this.agregarMicrofront(seMuestra, items, accion, texto, microFrontName);
    }

    /**
     * Builds the item seccion view.
     *
     * @param titulo           the titulo
     * @param accionController the accion controller
     * @return the item seccion view
     */
    SeccionView buildItemSeccionView(String titulo, AccionController accionController) {
        SeccionView itemSeccionView = new SeccionView();
        itemSeccionView.setTitulo(titulo);
        if (accionController != null) {
            itemSeccionView.setAccion(accionController.getDescripcion());
        }
        return itemSeccionView;

    }

    SeccionView buildItemSeccionView(String titulo, String accionController) {
        SeccionView itemSeccionView = new SeccionView();
        itemSeccionView.setTitulo(titulo);
        if (accionController != null) {
            itemSeccionView.setAccion(accionController);
        }
        return itemSeccionView;

    }
    
    
    /**
     * Builds the item seccion view with microfront.
     *
     * @param titulo           the titulo
     * @param accionController the accion controller
     * @param microfrontName   the microfront name
     * @param microfrontUrl    the microfront url
     * @return the item seccion view
     */
    private SeccionView buildItemSeccionMicrofrontView(String titulo, AccionController accionController, String microfrontName, String microfrontUrl) {
        SeccionMicrofrontView itemSeccionView = new SeccionMicrofrontView();
        itemSeccionView.setTitulo(titulo);
        itemSeccionView.setMicrofront(new MicrofrontView(microfrontName, microfrontUrl));
        if (accionController != null) {
            itemSeccionView.setAccion(accionController.getDescripcion());
        }
        return itemSeccionView;

    }

    /**
     * Builds the item seccion view.
     *
     * @param titulo           the titulo
     * @param accionController the accion controller
     * @return the item seccion view
     */
    SeccionView buildItemSeccionView(String titulo, AccionController accionController, String url) {
        SeccionView itemSeccionView = new SeccionView();
        itemSeccionView.setTitulo(titulo);
        if (accionController != null) {
            itemSeccionView.setAccion(accionController.getDescripcion());
        }
        itemSeccionView.setUrl(url);
        return itemSeccionView;

    }

    private void agregarMicrofront(boolean seMuestra, List<SeccionView> items, AccionController accion, String texto, String microFrontName){
        if (seMuestra) {
            administradorPermisos.addNewGrant(accion);
            ModuloPermiso moduloPermiso = moduloPermisoBO.obtenerModuloPermisoPorAccion(accion);
            if (moduloPermiso.tienePermisoDeVisibilidad() || moduloPermiso.muestraMensaje()) {
                SeccionView itemSeccionView = buildItemSeccionMicrofrontView(texto, accion, microFrontName, microfrontendUrl);
                if (moduloPermiso.muestraMensaje()) {
                    itemSeccionView.setMensajeNoDisponible(moduloPermiso.getMensaje());
                }
                items.add(itemSeccionView);
            }
        } else {
            administradorPermisos.removeGrant(accion);
        }
    }
}

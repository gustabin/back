/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.home.web.manager.ControllerHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.MenuHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionAadvantageHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionConsultasHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionInversionesHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionSolicitudesHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionSuperClubHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionTransaccionesHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.ControllerView;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;

/**
 * The Class ControllerHomeManagerImpl.
 */
@Component
public class ControllerHomeManagerImpl implements ControllerHomeManager {

    /** The Seccion Consultas Home Manager. */
    @Autowired
    private SeccionConsultasHomeManager seccionConsultasHomeManager;

    /** The Seccion Inversiones Home Manager. */
    @Autowired
    private SeccionInversionesHomeManager seccionInversionesHomeManager;

    /** The Seccion Transacciones Home Manager. */
    @Autowired
    private SeccionTransaccionesHomeManager seccionTransaccionesHomeManager;

    /** The Seccion Solicitudes Home Manager. */
    @Autowired
    private SeccionSolicitudesHomeManager seccionSolicitudesHomeManager;

    /** The Seccion Super Club Home Manager. */
    @Autowired
    private SeccionSuperClubHomeManager seccionSuperClubHomeManager;

    /** The seccion aadvantage home manager. */
    @Autowired
    private SeccionAadvantageHomeManager seccionAadvantageHomeManager;
    
    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;
    
    @Autowired
    private MenuHomeManager menuHomeMaganer;

    
	private static final String CONSULTAS = "Consultas";
	private static final String TRANSACCIONES = "Transacciones";
    private static final String SOLICITUDES = "Solicitudes y trámites";
    private static final String BENEFICIOS = "Beneficios";
	
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.home.web.manager.ControllerHomeManager#
     * obtenerController(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente)
     */
    @Override
    public ControllerView obtenerController(Cliente cliente) {
        ControllerView controllerView = new ControllerView();
        List<SeccionView> secciones = new ArrayList<SeccionView>();
        
        if (menuHomeMaganer.utilizarApiMenu()) {
			secciones = menuHomeMaganer.construirMenu(cliente);
		}else {
	        SeccionView seccionConsultas = seccionConsultasHomeManager.obtenerSeccion(cliente);
	        armarMenu(cliente, secciones, seccionConsultas);
	        
	        SeccionView seccionTransacciones = seccionTransaccionesHomeManager.obtenerSeccion(cliente);
	        armarMenu(cliente, secciones, seccionTransacciones);
	        
	        if (!cliente.isNova()) {
	            SeccionView seccionInversiones = seccionInversionesHomeManager.obtenerSeccion(cliente);
	            secciones.add(seccionInversiones);
	        }
	        
	        SeccionView seccionSolicitudes = seccionSolicitudesHomeManager.obtenerSeccion(cliente);
	        armarMenu(cliente, secciones, seccionSolicitudes);
	        
	        SeccionView seccionReclamos = null;
	        SeccionView seccionBeneficios = null;
	                
	        CollectionUtils.addIgnoreNull(secciones, seccionReclamos);
	        
	        if (!cliente.isNova()) {
		        if (tieneSuperClub()) {
		            seccionBeneficios = seccionSuperClubHomeManager.obtenerSeccion(cliente);
		        } else {
		            seccionBeneficios = seccionAadvantageHomeManager.obtenerSeccion();
		        }
		        armarMenu(cliente, secciones, seccionBeneficios);
	        }
		}
        
        controllerView.setSecciones(secciones);
        return controllerView;
    }
    
    public void armarMenu(Cliente cliente, List<SeccionView> secciones, SeccionView seccionEntera) {
        if (cliente.isNova()) {
        	SeccionView seccionConsultasNova = new SeccionView();
        	seccionConsultasNova.setTitulo(seccionEntera.getTitulo());
        	seccionConsultasNova.setAccion(seccionEntera.getAccion());
        	List<SeccionView> itemsNova = new ArrayList<SeccionView>();
        	for (SeccionView seccion : seccionEntera.getItems()) {
        		if (CONSULTAS.equals(seccionEntera.getTitulo())) {
        			filtrarOpcionesConsultas(itemsNova, seccion);
        		} else if (TRANSACCIONES.equals(seccionEntera.getTitulo())) {
        			filtrarOpcionesTransacciones(itemsNova, seccion);
        		} else if (SOLICITUDES.equals(seccionEntera.getTitulo())) {
        			filtrarOpcionesSolicitudes(itemsNova, seccion);
        		} else {
        			filtrarOpcionesBeneficios(itemsNova, seccion);
        		}
        	}
        	seccionConsultasNova.setItems(itemsNova);
        	secciones.add(seccionConsultasNova);
        } else if (BENEFICIOS.equals(seccionEntera.getTitulo())) {
        	CollectionUtils.addIgnoreNull(secciones, seccionEntera);
        } else {
        	secciones.add(seccionEntera);
        }
    }
    
    public void filtrarOpcionesConsultas(List<SeccionView> itemsNova, SeccionView seccion) {
		if (!AccionController.IR_INICIO_PRESTAMOS_CONSULTAS.getDescripcion().equals(seccion.getAccion()) &&
		    !AccionController.IR_INICIO_SEGUROS.getDescripcion().equals(seccion.getAccion()) &&
		    !AccionController.IR_INICIO_RESUMEN_IMPOSITIVO.getDescripcion().equals(seccion.getAccion()) &&
		    !AccionController.IR_INICIO_ECHEQ.getDescripcion().equals(seccion.getAccion())) {
			itemsNova.add(seccion);
    	}
    }
    
    public void filtrarOpcionesTransacciones(List<SeccionView> itemsNova, SeccionView seccion) {
		if (!AccionController.IR_INICIO_PAGO_DE_TARJETA.getDescripcion().equals(seccion.getAccion()) &&
		    !AccionController.IR_INICIO_PAGO_DE_HABERES.getDescripcion().equals(seccion.getAccion()) &&
		    !AccionController.IR_TRANSFERENCIAS_EXTERIOR_MF.getDescripcion().equals(seccion.getAccion()) &&
		    !AccionController.IR_EXTRACCION_SANTANDER_EXPRESS.getDescripcion().equals(seccion.getAccion()) &&
		    !AccionController.IR_ORDEN_PAGO_EXTERIOR_MF.getDescripcion().equals(seccion.getAccion()) &&
		    !AccionController.IR_INICIO_DOLARES.getDescripcion().equals(seccion.getAccion()) &&
		    !AccionController.IR_INICIO_DEBIN.getDescripcion().equals(seccion.getAccion()) &&
		    !AccionController.IR_INICIO_ECHEQ.getDescripcion().equals(seccion.getAccion()) &&
		    !AccionController.IR_INICIO_DESCUENTO_DE_CHEQUES.getDescripcion().equals(seccion.getAccion())) {
			itemsNova.add(seccion);
    	}    	
    }
    
    public void filtrarOpcionesSolicitudes(List<SeccionView> itemsNova, SeccionView seccion) {
    	if (!AccionController.IR_INICIO_CUENTA_Y_PAQUETES.getDescripcion().equals(seccion.getAccion()) &&
			!AccionController.IR_INICIO_PRESTAMOS_SOLICITUDES.getDescripcion().equals(seccion.getAccion()) &&
		    !AccionController.IR_INICIO_SEGUROS.getDescripcion().equals(seccion.getAccion()) &&
		    !AccionController.IR_INICIO_MEDIOS_PAGO_SOLICITUDES.getDescripcion().equals(seccion.getAccion())) {
			itemsNova.add(seccion);
			
    	}
    }
    
    public void filtrarOpcionesBeneficios(List<SeccionView> itemsNova, SeccionView seccion) {
		if (!AccionController.IR_SORPRESA.getDescripcion().equals(seccion.getAccion()) &&
			!AccionController.REFERIDOS_COMPARTIR.getDescripcion().equals(seccion.getAccion())) {
			itemsNova.add(seccion);
		}
    }
    
    /**
     * Validar si tiene SuperClub.<br/>
     * Se solicito que por demora en la respuesta del gc (cuidado con la
     * concurrencia de request) o por error del mismo (no se pueda validar la
     * afinidad) se muestre la entrada a superclub en el topbar.
     *
     * @return true, if successful
     */
    private boolean tieneSuperClub() {
        return sesionParametros.getOfertasComerciales() == null || sesionParametros.getOfertasComerciales().getMostrarCaja();
    }

}

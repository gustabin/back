package ar.com.santanderrio.obp.servicios.getnet.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.getnet.manager.GetnetManager;
import ar.com.santanderrio.obp.servicios.getnet.sei.GetnetSEI;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetAdhesionOutView;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetOutView;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetAdhesionInView;

/**
 * The Class GetNetSEIImpl.
 */
@Component("getnetSEI")
public class GetnetSEIImpl implements GetnetSEI {

	/** The getnet manager. */
    @Autowired
    private GetnetManager getnetManager;
    
    /**
     * Devuelve los datos a mostrar en el stack de GetNet
     * Puede ser: solicitud / activo / inactivo
     *
     * @return the getnet out view
     */
    @Override
    public Respuesta<GetnetOutView> configuracion() {
    	return getnetManager.configuracion();
    }
    
    /**
     * loguea estadisticas cuando el usuario ingresa en link externo de getnet.
     */
	@Override
    public void loguearEstadisticas() {
    	getnetManager.loguearEstadisticas();
    }
	
	/**
     * Confirma la adhesion a GetNet
     *
     * @return the getnet adhesion out view
     */
    @Override
    public Respuesta<GetnetAdhesionOutView> confirmarAdhesion(GetnetAdhesionInView view) {
    	return getnetManager.confirmarAdhesion(view);
    }
    
    /**
     * Descarga comprobante adhesion
     *
     * @return the reporte view
     */
    @Override
    public Respuesta<ReporteView> descargaComprobanteAdhesion() {
    	return getnetManager.descargaComprobanteAdhesion();
    }

    /**
     * Valida el cliente getnet y devuelve la configuracion
     * acceso desde carrusel
     */
	@Override
	public Respuesta<GetnetOutView> obtenerDatosGetnet() {
		return getnetManager.obtenerDatosGetnet();
	}
    
}

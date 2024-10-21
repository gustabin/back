package ar.com.santanderrio.obp.servicios.getnet.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetAdhesionOutView;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetOutView;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetAdhesionInView;

/**
 * The Interface GetnetManager.
 */
public interface GetnetManager {

	/**
     * Devuelve los datos a mostrar en el stack de GetNet
     * Puede ser: solicitud / activo / inactivo
     *
     * @return the getnet out view
     */
    Respuesta<GetnetOutView> configuracion();
    
	void loguearEstadisticas();
	
	/**
     * Confirma la adhesion a GetNet
     *
     * @return the getnet adhesion out view
     */
	Respuesta<GetnetAdhesionOutView> confirmarAdhesion(GetnetAdhesionInView view);
	
	/**
     * Descarga comprobante adhesion
     *
     * @return the reporte view
     */
    Respuesta<ReporteView> descargaComprobanteAdhesion();
    
    /**
     * Valida la card en otros medios de pago
     *
     * @return the boolean
     */
    Boolean validarCard();
    
    DatosSolicitanteEntity buscarNacionalidad(Cliente cliente);
    
    /**
     * Grabar estadisticas de getNet.
     *
     * @param codigoError
     *            the codigo error
     * @param tipoOperacion
     *            the tipo operacion
     */
    void grabarEstadisticas(String codigoError);
    
    
    /**
     * Devuelve los datos a mostrar en el stack de GetNet 
     * luego de realizar las validaciones requeridas por usuario
     * Puede ser: solicitud / activo / inactivo
     * Acceso a traves de carrusel
     * @return the getnet out view
     */
    Respuesta<GetnetOutView> obtenerDatosGetnet();
}

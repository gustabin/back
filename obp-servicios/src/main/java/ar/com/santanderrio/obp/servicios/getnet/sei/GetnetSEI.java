package ar.com.santanderrio.obp.servicios.getnet.sei;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetAdhesionOutView;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetOutView;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetAdhesionInView;

/**
* The Interface GetnetSEI.
*/
@Path("/getnet")
public interface GetnetSEI {
	
	/**
     * Devuelve los datos a mostrar en el stack de Getnet
     * Puede ser: solicitud / activo / inactivo
     *
     * @return the getnet out view
     */
    @POST
    @Path("/configuracion")
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<GetnetOutView> configuracion();
	
	/**
     * Loguea las estadisticas si el usuario hizo click
     * en enlace externo a getnet
     */
    @POST
    @Path("/loguearEstadisticas")
    @Produces(value = { MediaType.APPLICATION_JSON })
    void loguearEstadisticas();
	
    /**
     * Confirma la adhesion a GetNet
     *
     * @return the getnet adhesion out view
     */
    @POST
    @Path("/confirmarAdhesion")
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<GetnetAdhesionOutView> confirmarAdhesion(GetnetAdhesionInView view);
    
    /**
     * Descarga comprobante adhesion
     *
     * @return the reporte view
     */
    @POST
    @Path("/descargaComprobanteAdhesion")
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<ReporteView> descargaComprobanteAdhesion();
    
    
    /**
     * Goto a Getnet desde carrusel
     *
     * @return the reporte view
     */
    @POST
    @Path("/obtenerDatosGetnet")
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<GetnetOutView> obtenerDatosGetnet();
}

package ar.com.santanderrio.obp.servicios.inversiones.resumen.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenesInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenesMensualesInversionesView;

/**
 * The Interface ResumenInversionesSEI.
 */
@Path("/resumenInversiones")
public interface ResumenInversionesSEI {

	/**
     * Obtener cuentas.
     *
     * @param view the view
     * @return the respuesta
     */
    @POST
    @Path("/obtenerCuentas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ResumenesInversionesView> obtenerCuentas();

    /**
     * Obtener lista resumen.
     *
     * @param view the view
     * @return the respuesta
     */
    @POST
    @Path("/obtenerListaResumen")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ResumenesMensualesInversionesView> obtenerListaResumen(ResumenInversionesView view);

    /**
     * Obtener resumenes PDF.
     *
     * @param view the view
     * @return the respuesta
     */
    @POST
    @Path("/obtenerResumenes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ReporteView> obtenerResumenesPDF(ResumenInversionesView view);
    
    /**
     * Obtener resumenes multiple.
     *
     * @param view the view
     * @return the respuesta
     */
    @POST
    @Path("/obtenerResumenesDescargaMultiple")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ReporteView> obtenerResumenesMultiple(ResumenInversionesView view);

}

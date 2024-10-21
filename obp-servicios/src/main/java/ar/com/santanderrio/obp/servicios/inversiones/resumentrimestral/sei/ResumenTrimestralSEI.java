package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResponsePdfView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenPdfInView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenTrimestralView;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view.ResumenesDispInView;

@Path("/resumenTrimestral")
public interface ResumenTrimestralSEI {
	
	@POST
    @Path("/obtenerDisponiblesPorCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	Respuesta<ResumenTrimestralView> obtenerDisponibles(ResumenesDispInView request);
	
	@POST
    @Path("/obtenerPdf")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	Respuesta<ResponsePdfView> obtenerPdf(ResumenPdfInView viewRequest);

}

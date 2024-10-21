package ar.com.santanderrio.obp.servicios.extraccionefectivo.sei;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.DatosClienteExtraccionEfectivoView;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.ExtraccionEfectivoView;

@Path("/extraccionEfectivo")
public interface ExtraccionEfectivoSEI {

	@POST
	@Path("/configuracion")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<DatosClienteExtraccionEfectivoView> configuracionExtraccionEfectivo();
	
	@POST
	@Path("/ejecutarSolicitud")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ExtraccionEfectivoView> ejecutarSolicitud(ExtraccionEfectivoView solicitarEfectivoView);

	@POST
	@Path("/grabarEstadisticaVisualizacionComprobante")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> grabarEstadisticaVisualizacionComprobante();
	
	@POST
	@Path("/generarComprobantePDF")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> generarComprobantePDF();
	
}

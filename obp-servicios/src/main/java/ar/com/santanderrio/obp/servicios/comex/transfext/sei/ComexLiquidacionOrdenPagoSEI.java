package ar.com.santanderrio.obp.servicios.comex.transfext.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ArchivoTransferenciaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DocumentacionAdjuntaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.FeedbackProcesarOrdenPago;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.InicioLiquidacionOrdenPagoView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarOrdenPagoInView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

@Path("/liquidacionOrdenPago")
public interface ComexLiquidacionOrdenPagoSEI {

	/**
	 * Configuracion liquidacion orden de pago
	 * 
	 * @return
	 */
	@POST
	@Path("/inicio")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<InicioLiquidacionOrdenPagoView> inicioLiquidacionOrdenPago();

	@POST
	@Path("/adjuntarArchivo")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ArchivoTransferenciaView> adjuntarArchivo(DocumentacionAdjuntaView documentacionAdjuntaView);

	@POST
	@Path("/configuracion")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> configuracionOrdenPagoExt();

	@POST
	@Path("/procesarOrden")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<FeedbackProcesarOrdenPago> procesarOrden(ProcesarOrdenPagoInView procesarOrdenPagoViewIn);

	@POST
	@Path("/borrarArchivo")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> borrarArchivo(ArchivoTransferenciaView documentacionAdjuntaView);

	@POST
	@Path("/descargarComprobante")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargarComprobantePDF(ProcesarOrdenPagoInView procesarOrdenPagoViewIn);
	
	@POST
	@Path("/descargarNormativa")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargarNormativa();

	@POST
	@Path("/verComprobante")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> verComprobante();
	
	@POST
	@Path("/estadisticaAdjuntar")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> estadisticaAdjuntar();
	
	@POST
	@Path("/obtenerMensajeVinculante")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<String> obtenerMensajeVinculante();
	

}

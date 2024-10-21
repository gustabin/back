package ar.com.santanderrio.obp.servicios.delete.account.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.DeleteAccountView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.DiscadorView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.ProductosBajaResponseView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.SolicitarBajaCuentaView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.SolicitudDeleteAccountView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.SolicitudDiscadorView;

@Path("/cuenta")
public interface DeleteAccountSEI {

	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DeleteAccountView> deleteAccount(SolicitudDeleteAccountView inView);
	
	@POST
	@Path("/delete/productos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ProductosBajaResponseView> getProductosBaja();
	
	@POST
	@Path("/solicitarBajaCuenta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<SolicitarBajaCuentaView> solicitarBajaCuenta();
	
	@POST
	@Path("/solicitarDiscador")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DiscadorView> solicitarDiscador(SolicitudDiscadorView inView);
	
	@POST
	@Path("/generarComprobanteBaja")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> generarComprobantePDF();
	
	@POST
	@Path("/generarDetalleBaja")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> generarDetallePDF();
	
}

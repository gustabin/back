/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCRequestDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionPDC;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteActivarPoderCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteOrdenCompraView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.EstadisticaComprobantePoderCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarAdhesionPDCResponse;

/**
 * The Interface PoderCompraSEI.
 */
@Path("/poderCompra")
public interface PoderCompraSEI {
	
	/**
	 * Consulta las ordenes de licitaciones para banca personal.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/simularAdhesionPoderCompra")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<AdhesionPDCOutDTO> simularAdhesionPDC(AdhesionPDCRequestDTO request);
	
	/**
	 * Consulta las ordenes de licitaciones para banca personal.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/simularAdhesionPoderCompraBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<AdhesionPDCOutDTO> simularAdhesionPDCBPriv(AdhesionPDCRequestDTO request);
	
	/**
	 * Consulta las ordenes de licitaciones para banca personal.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarAdhesionPoderCompra")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FinalizarAdhesionPDCResponse> finalizarAdhesionPDC(FinalizarAdhesionPDC request);
	
	/**
	 * Consulta las ordenes de licitaciones para banca personal.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarAdhesionPoderCompraBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FinalizarAdhesionPDCResponse> finalizarAdhesionPDCBPriv(FinalizarAdhesionPDC request);
	
	/**
	 * Grabar estadisticas Comprobante Activar Poder Comprar y devolver fecha y hora actual.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/verComprobanteActivarPoderComprar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteOrdenCompraView> verComprobanteActivarPoderComprar(EstadisticaComprobantePoderCompra request);
	
	/**
	 * Grabar estadisticas Comprobante Activar Poder Comprar y devolver fecha y hora actual.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteActivarPoderComprar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteActivarPoderComprar(ComprobanteActivarPoderCompra request);


	/**
	 * configurar adhesion Poder de compra.- Venta titulos valores
	 * @param request
	 * @return
	 */
	@POST
	@Path("/configurarAdhesionPoderCompra")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<AdhesionPDCOutDTO> configurarAdhesionPoderCompra(AdhesionPDCRequestDTO request);
	
	
	/**
	 * Confirmar Adhesion Poder de Compra desde Venta Titulos valores.
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmarAdhesionPDC")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FinalizarAdhesionPDCResponse> confirmarAdhesionPDC(FinalizarAdhesionPDC request);
	
	/**
	 * Grabar estadisticas Comprobante Activar Poder Comprar desde Venta titulos valores.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/verComprobanteActivarPoderComprarVTV")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> verComprobanteActivarPoderComprarVTV();
	
	/**
	 * Descarga comprobante Adhesion PDC desde Venta Titulos valores
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteActivarPoderComprarVTV")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteActivarPoderComprarVTV(ComprobanteActivarPoderCompra request);

}

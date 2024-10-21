/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestBody;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ConfirmarClienteCompraEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ConfirmarClienteVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ContinuarCompraVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.Cotizacion;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.CompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ConfiguracionView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.SimulacionCompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Interface CompraVentaSEI.
 *
 * @author sabrina.cis
 */
@Path("/cuentas")
public interface CompraVentaSEI {

	/**
	 * Get the DatosEntity Iniciales para la configuracion de compra-venta
	 * dolares.
	 *
	 * @param configuracionView
	 *            the configuracion view
	 * @return the CompraVentaDolarView
	 */
	@POST
	@Path("/getDatosIniciales")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CompraVentaDolarView> compraVentaDolaresInicio(ConfiguracionView configuracionView);

	/**
	 * Obtiene la cotizacion para la compra de la cuenta activa.
	 *
	 * @param cotizacion
	 *            the cotizacion
	 * @return the CompraVentaDolarViewl
	 */
	@POST
	@Path("/getCotizacionCompra")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CompraVentaDolarView> obtenerCotizacionCompra(@RequestBody Cotizacion cotizacion);

	/**
	 * Boton confirmar de la pantalla simulacion, si el cliente compra dolares.
	 *
	 * @param confirmarClienteCompra
	 *            the confirmar cliente compra
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmarClienteCompra")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteCompraVentaView> confirmarClienteCompra(
			@RequestBody ConfirmarClienteCompraEntity confirmarClienteCompra);

	/**
	 * Obtiene la cotizacion para la venta de la cuenta activa.
	 *
	 * @param cotizacion
	 *            the cotizacion
	 * @return the CompraVentaDolarViewl
	 */
	@POST
	@Path("/getCotizacionVenta")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CompraVentaDolarView> obtenerCotizacionVenta(Cotizacion cotizacion);

	/**
	 * Boton confirmar de la pantalla simulacion venta.
	 *
	 * @param confirmarClienteVenta
	 *            the confirmar cliente venta
	 * @return the SimulacionCompraVentaDolarView
	 */
	@POST
	@Path("/confirmarClienteVenta")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteCompraVentaView> confirmarClienteVenta(ConfirmarClienteVentaEntity confirmarClienteVenta);

	/**
	 * Boton continuar de la pantalla inicial.
	 *
	 * @param continuarCompraVenta
	 *            the continuar compra venta
	 * @return the CompraVentaDolarViewl
	 */
	@POST
	@Path("/continuarVentaDolares")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<SimulacionCompraVentaDolarView> continuarVentaDolares(
			@RequestBody ContinuarCompraVentaEntity continuarCompraVenta);

	/**
	 * Continuar compra dolares.
	 *
	 * @param continuarCompraVenta
	 *            the continuar compra venta
	 * @return the respuesta
	 */
	@POST
	@Path("/continuarCompraDolares")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<SimulacionCompraVentaDolarView> continuarCompraDolares(ContinuarCompraVentaEntity continuarCompraVenta);

	/**
	 * Estadistica comprobante compra.
	 */
	@POST
	@Path("/estadisticaComprobanteCompra")
	void estadisticaComprobanteCompra();

	/**
	 * Estadistica comprobante venta.
	 */
	@POST
	@Path("/estadisticaComprobanteVenta")
	void estadisticaComprobanteVenta();

	/**
	 * Descargar comprobante PDF.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobantePDF")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargarComprobantePDF();

}

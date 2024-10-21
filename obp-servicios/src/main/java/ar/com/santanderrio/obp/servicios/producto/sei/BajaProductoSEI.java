/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.producto.view.ArrepentimientoProductoView;
import ar.com.santanderrio.obp.servicios.producto.view.BajaProductoInView;
import ar.com.santanderrio.obp.servicios.producto.view.BajaProductoView;
import ar.com.santanderrio.obp.servicios.producto.view.ComprobanteBajaProductoView;

/**
 * The Interface BajaProductoSEI.
 */
@Path("/producto")
public interface BajaProductoSEI {

	/**
	 * Obtener productos baja.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/getProductosBaja")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<BajaProductoView> obtenerProductosBaja(BajaProductoInView view);

	/**
	 * Baja producto.
	 *
	 * @param producto
	 *            the producto
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmarBajaProducto")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteBajaProductoView> bajaProducto(BajaProductoView producto);

	/**
	 * Comprobante baja producto.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/comprobanteBajaProducto")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> comprobanteBajaProducto();
	
	
	@POST
	@Path("/getProductosArrepentimiento")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ArrepentimientoProductoView> obtenerProductosArrepentimiento();
	
}

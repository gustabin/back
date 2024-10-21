/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.producto.view.ArrepentimientoProductoView;
import ar.com.santanderrio.obp.servicios.producto.view.BajaProductoInView;
import ar.com.santanderrio.obp.servicios.producto.view.BajaProductoView;
import ar.com.santanderrio.obp.servicios.producto.view.ComprobanteBajaProductoView;

/**
 * The Interface BajaProductoManager.
 */
public interface BajaProductoManager {

	/**
	 * Obtener productos.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	Respuesta<BajaProductoView> obtenerProductos(BajaProductoInView view);

	/**
	 * Dar baja producto.
	 *
	 * @param bajaProductoView
	 *            the baja producto view
	 * @return the respuesta
	 */
	Respuesta<ComprobanteBajaProductoView> darBajaProducto(BajaProductoView bajaProductoView);

	/**
	 * Generar comprobante.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> generarComprobante();
	
	Respuesta<ArrepentimientoProductoView> obtenerProductosArrepentimiento();
	
}

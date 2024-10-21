/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.producto.manager.BajaProductoManager;
import ar.com.santanderrio.obp.servicios.producto.sei.BajaProductoSEI;
import ar.com.santanderrio.obp.servicios.producto.view.ArrepentimientoProductoView;
import ar.com.santanderrio.obp.servicios.producto.view.BajaProductoInView;
import ar.com.santanderrio.obp.servicios.producto.view.BajaProductoView;
import ar.com.santanderrio.obp.servicios.producto.view.ComprobanteBajaProductoView;

/**
 * The Class BajaProductoSEIImpl.
 */
@Component("bajaProductoSEI")
public class BajaProductoSEIImpl implements BajaProductoSEI {

	/** The baja producto manager. */
	@Autowired
	private BajaProductoManager bajaProductoManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.producto.sei.BajaProductoSEI#
	 * obtenerProductosBaja(ar.com.santanderrio.obp.servicios.producto.view.
	 * BajaProductoInView)
	 */
	@Override
	public Respuesta<BajaProductoView> obtenerProductosBaja(BajaProductoInView view) {

		return bajaProductoManager.obtenerProductos(view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.producto.sei.BajaProductoSEI#
	 * bajaProducto(ar.com.santanderrio.obp.servicios.producto.view.
	 * BajaProductoView)
	 */
	@Override
	public Respuesta<ComprobanteBajaProductoView> bajaProducto(BajaProductoView producto) {

		return bajaProductoManager.darBajaProducto(producto);
	}

	/**
	 * Gets the baja producto manager.
	 *
	 * @return the baja producto manager
	 */
	public BajaProductoManager getBajaProductoManager() {
		return bajaProductoManager;
	}

	/**
	 * Sets the baja producto manager.
	 *
	 * @param bajaProductoManager
	 *            the new baja producto manager
	 */
	public void setBajaProductoManager(BajaProductoManager bajaProductoManager) {
		this.bajaProductoManager = bajaProductoManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.producto.sei.BajaProductoSEI#
	 * comprobanteBajaProducto()
	 */
	@Override
	public Respuesta<ReporteView> comprobanteBajaProducto() {

		return bajaProductoManager.generarComprobante();
	}

	@Override
	public Respuesta<ArrepentimientoProductoView> obtenerProductosArrepentimiento() {
		return bajaProductoManager.obtenerProductosArrepentimiento();
	}

}

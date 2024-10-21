/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ProductosGraficoRendimiento.
 */
public class ProductosGraficoRendimiento {

	/** The producto. */
	private String producto;
	
	/** The id producto. */
	private String idProducto;
	
	/** The values. */
	private List<ValoresProductos> values = new ArrayList<ValoresProductos>();

	/**
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto
	 *            the new producto
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	/**
	 * Gets the id producto.
	 *
	 * @return the id producto
	 */
	public String getIdProducto() {
		return idProducto;
	}

	/**
	 * Sets the id producto.
	 *
	 * @param idProducto
	 *            the new id producto
	 */
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	public List<ValoresProductos> getValues() {
		return values;
	}

	/**
	 * Sets the values.
	 *
	 * @param values
	 *            the new values
	 */
	public void setValues(List<ValoresProductos> values) {
		this.values = values;
	}
	
}

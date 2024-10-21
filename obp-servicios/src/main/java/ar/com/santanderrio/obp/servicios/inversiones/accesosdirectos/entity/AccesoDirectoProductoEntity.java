package ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class AccesoDirectoProductoEntity {
	
	@JsonProperty("Producto")
	private String producto;

	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * @param mensaje the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}


}

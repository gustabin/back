/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.entities;

/**
 * The Class TarjetasDTO.
 */
public class TarjetasDTO {

	/** The apellido nombre. */
	private String apellidoNombre;

	/** The ultimos cuatro. */
	private String ultimosCuatro;

	/** The condicion. */
	private String condicion;

	/** The producto. */
	private String producto;

	/**
	 * Gets the apellido nombre.
	 *
	 * @return the apellido nombre
	 */
	public String getApellidoNombre() {
		return apellidoNombre;
	}

	/**
	 * Sets the apellido nombre.
	 *
	 * @param apellidoNombre the new apellido nombre
	 */
	public void setApellidoNombre(String apellidoNombre) {
		this.apellidoNombre = apellidoNombre;
	}

	/**
	 * Gets the ultimos cuatro.
	 *
	 * @return the ultimos cuatro
	 */
	public String getUltimosCuatro() {
		return ultimosCuatro;
	}

	/**
	 * Sets the ultimos cuatro.
	 *
	 * @param ultimosCuatro the new ultimos cuatro
	 */
	public void setUltimosCuatro(String ultimosCuatro) {
		this.ultimosCuatro = ultimosCuatro;
	}

	/**
	 * Gets the condicion.
	 *
	 * @return the condicion
	 */
	public String getCondicion() {
		return condicion;
	}
 
	/**
	 * Sets the condicion. 
	 *
	 * @param condicion the new condicion
	 */
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}  

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
	 * @param producto the new producto
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}
}

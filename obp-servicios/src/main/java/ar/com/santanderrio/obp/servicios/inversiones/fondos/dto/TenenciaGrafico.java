/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

/**
 * The Class TenenciaGrafico.
 */
public class TenenciaGrafico {

	/** The producto. */
	String producto;

	/** The tenencia pesos. */
	double tenenciaPesos = 0;

	/** The tenencia dolares. */
	double tenenciaDolares = 0;

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
	 * Gets the tenencia pesos.
	 *
	 * @return the tenencia pesos
	 */
	public double getTenenciaPesos() {
		return tenenciaPesos;
	}

	/**
	 * Sets the tenencia pesos.
	 *
	 * @param tenenciaPesos
	 *            the new tenencia pesos
	 */
	public void setTenenciaPesos(double tenenciaPesos) {
		this.tenenciaPesos = tenenciaPesos;
	}

	/**
	 * Adds the tenencia pesos.
	 *
	 * @param tenenciaPesos
	 *            the tenencia pesos
	 */
	public void addTenenciaPesos(double tenenciaPesos) {
		this.tenenciaPesos = this.tenenciaPesos + tenenciaPesos;
	}

	/**
	 * Gets the tenencia dolares.
	 *
	 * @return the tenencia dolares
	 */
	public double getTenenciaDolares() {
		return tenenciaDolares;
	}

	/**
	 * Sets the tenencia dolares.
	 *
	 * @param tenenciaDolares
	 *            the new tenencia dolares
	 */
	public void setTenenciaDolares(double tenenciaDolares) {
		this.tenenciaDolares = tenenciaDolares;
	}

	/**
	 * Adds the tenencia dolares.
	 *
	 * @param tenenciaDolares
	 *            the tenencia dolares
	 */
	public void addTenenciaDolares(double tenenciaDolares) {
		this.tenenciaDolares = this.tenenciaDolares + tenenciaDolares;
	}

}

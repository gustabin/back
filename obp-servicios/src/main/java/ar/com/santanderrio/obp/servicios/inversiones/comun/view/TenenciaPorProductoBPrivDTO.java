/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

/**
 * The Class TenenciaPorProductoBPrivDTO.
 */
public class TenenciaPorProductoBPrivDTO {
	
	/** The Constant GUION_MEDIO. */
	private static final String GUION_MEDIO = "-";

	/** The producto. */
	private String producto;

	/** The tenenciaValuadaHoy. */
	private String tenenciaValuadaHoy;

	/** The tenenciaValuadaCosto. */
	private String tenenciaValuadaCosto;
	
	/** The tenenciaValuadaReexpresada. 
	 * Es el valor de la tenencia expresado en la otra moneda.
	 * Ejemplo, si mi tenencia es en ARS, el valor reexpresado estara en USD*/
	private String tenenciaValuadaReexpresada;

	/** The resultado. */
	private String resultado;

	/** The porcentaje. */
	private int porcentaje;
	

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
	 * Gets the tenencia valuada hoy.
	 *
	 * @return the tenencia valuada hoy
	 */
	public String getTenenciaValuadaHoy() {
		return tenenciaValuadaHoy;
	}

	/**
	 * Sets the tenencia valuada hoy.
	 *
	 * @param tenenciaValuadaHoy
	 *            the new tenencia valuada hoy
	 */
	public void setTenenciaValuadaHoy(String tenenciaValuadaHoy) {
		this.tenenciaValuadaHoy = tenenciaValuadaHoy;
	}

	/**
	 * Gets the tenencia valuada costo.
	 *
	 * @return the tenencia valuada costo
	 */
	public String getTenenciaValuadaCosto() {
		return tenenciaValuadaCosto;
	}

	/**
	 * Sets the tenencia valuada costo.
	 *
	 * @param tenenciaValuadaCosto
	 *            the new tenencia valuada costo
	 */
	public void setTenenciaValuadaCosto(String tenenciaValuadaCosto) {
		if(tenenciaValuadaCosto != null){
			this.tenenciaValuadaCosto = tenenciaValuadaCosto;
		}else{
			this.tenenciaValuadaCosto = GUION_MEDIO;
		}
	}

	/**
	 * Gets the resultado.
	 *
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * Sets the resultado.
	 *
	 * @param resultado
	 *            the new resultado
	 */
	public void setResultado(String resultado) {
		if(resultado != null){
			this.resultado = resultado;
		}else{
			this.resultado = GUION_MEDIO;
		}
	}

	/**
	 * Gets the porcentaje.
	 *
	 * @return the porcentaje
	 */
	public int getPorcentaje() {
		return porcentaje;
	}

	/**
	 * Sets the porcentaje.
	 *
	 * @param porcentaje
	 *            the new porcentaje
	 */
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	/**
	 * Gets the tenencia valuada reexpresada.
	 *
	 * @return the tenencia valuada reexpresada
	 */
	public String getTenenciaValuadaReexpresada() {
		return tenenciaValuadaReexpresada;
	}

	/**
	 * Sets the tenencia valuada reexpresada.
	 *
	 * @param tenenciaValuadaReexpresada
	 *            the new tenencia valuada reexpresada
	 */
	public void setTenenciaValuadaReexpresada(String tenenciaValuadaReexpresada) {
		this.tenenciaValuadaReexpresada = tenenciaValuadaReexpresada;
	}

	
}

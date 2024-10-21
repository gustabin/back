/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

/**
 * The Class TenenciaPorCuentaBPrivDTO.
 */
public class TenenciaPorCuentaBPrivDTO {
	
	/** The numero de cuenta. */
	private String nroCuenta;
	
	/** The nroCuentaFormateado. */
	private String nroCuentaFormateado;
	
	/** The porcentajeMonedaPesos. */
	private String porcentajeMonedaPesos;
	
	/** The lista tenencias Pesos. */
	private TenenciaProductosPorMonedaDTO tenenciaPesos = new TenenciaProductosPorMonedaDTO();

	/** The lista tenencias Dolares. */
	private TenenciaProductosPorMonedaDTO tenenciaDolares = new TenenciaProductosPorMonedaDTO();

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the nro cuenta formateado.
	 *
	 * @return the nro cuenta formateado
	 */
	public String getNroCuentaFormateado() {
		return nroCuentaFormateado;
	}

	/**
	 * Sets the nro cuenta formateado.
	 *
	 * @param sucursal
	 *            the new nro cuenta formateado
	 */
	public void setNroCuentaFormateado(String sucursal) {
		this.nroCuentaFormateado = sucursal;
	}

	/**
	 * Gets the porcentaje moneda pesos.
	 *
	 * @return the porcentaje moneda pesos
	 */
	public String getPorcentajeMonedaPesos() {
		return porcentajeMonedaPesos;
	}

	/**
	 * Sets the porcentaje moneda pesos.
	 *
	 * @param porcentajeMonedaPesos
	 *            the new porcentaje moneda pesos
	 */
	public void setPorcentajeMonedaPesos(String porcentajeMonedaPesos) {
		this.porcentajeMonedaPesos = porcentajeMonedaPesos;
	}

	/**
	 * Gets the tenencia pesos.
	 *
	 * @return the tenencia pesos
	 */
	public TenenciaProductosPorMonedaDTO getTenenciaPesos() {
		return tenenciaPesos;
	}

	/**
	 * Sets the tenencia pesos.
	 *
	 * @param tenenciaPesos
	 *            the new tenencia pesos
	 */
	public void setTenenciaPesos(TenenciaProductosPorMonedaDTO tenenciaPesos) {
		this.tenenciaPesos = tenenciaPesos;
	}

	/**
	 * Gets the tenencia dolares.
	 *
	 * @return the tenencia dolares
	 */
	public TenenciaProductosPorMonedaDTO getTenenciaDolares() {
		return tenenciaDolares;
	}

	/**
	 * Sets the tenencia dolares.
	 *
	 * @param tenenciaDolares
	 *            the new tenencia dolares
	 */
	public void setTenenciaDolares(TenenciaProductosPorMonedaDTO tenenciaDolares) {
		this.tenenciaDolares = tenenciaDolares;
	}
	

}

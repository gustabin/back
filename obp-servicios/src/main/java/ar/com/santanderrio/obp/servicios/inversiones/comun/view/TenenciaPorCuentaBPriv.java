/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

/**
 * The Class TenenciaPorCuentaBPriv.
 */
public class TenenciaPorCuentaBPriv {

	/** The numero de cuenta. */
	private String nroCuenta;
	
	/** The sucursal. */
	private String nroCuentaFormateado;
	
	/** The lista tenencias Pesos. */
	private TenenciaProductosPorMonedaView tenenciaPesos = new TenenciaProductosPorMonedaView();

	/** The lista tenencias Dolares. */
	private TenenciaProductosPorMonedaView tenenciaDolares = new TenenciaProductosPorMonedaView();

	
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
	 * Gets the tenencia pesos.
	 *
	 * @return the tenencia pesos
	 */
	public TenenciaProductosPorMonedaView getTenenciaPesos() {
		return tenenciaPesos;
	}

	/**
	 * Sets the tenencia pesos.
	 *
	 * @param tenenciaPesos
	 *            the new tenencia pesos
	 */
	public void setTenenciaPesos(TenenciaProductosPorMonedaView tenenciaPesos) {
		this.tenenciaPesos = tenenciaPesos;
	}

	/**
	 * Gets the tenencia dolares.
	 *
	 * @return the tenencia dolares
	 */
	public TenenciaProductosPorMonedaView getTenenciaDolares() {
		return tenenciaDolares;
	}

	/**
	 * Sets the tenencia dolares.
	 *
	 * @param tenenciaDolares
	 *            the new tenencia dolares
	 */
	public void setTenenciaDolares(TenenciaProductosPorMonedaView tenenciaDolares) {
		this.tenenciaDolares = tenenciaDolares;
	}

	

}

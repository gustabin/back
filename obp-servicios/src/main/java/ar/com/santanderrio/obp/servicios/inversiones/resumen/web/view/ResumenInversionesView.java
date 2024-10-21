package ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view;

/**
 * The Class ResumenInversionesView.
 */
public class ResumenInversionesView {

	/** The nro cuenta. */
	private String numeroCuenta;

	/** The id. */
	private String[] id;

	/** The nro sucursal. */
	private String nroSucursal;

	/** The cantidad A descargar. */
	private int cantidadADescargar;

	/** The banca privada. */
	private boolean bancaPrivada;

	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta
	 *     the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *     the id
	 */
	public void setId(String[] id) {
		this.id = id;
	}

	/**
	 * Gets the nro sucursal.
	 *
	 * @return the nro sucursal
	 */
	public String getNroSucursal() {
		return nroSucursal;
	}

	/**
	 * Sets the nro sucursal.
	 *
	 * @param nroSucursal
	 *     the new nro sucursal
	 */
	public void setNroSucursal(String nroSucursal) {
		this.nroSucursal = nroSucursal;
	}

	/**
	 * Gets the cantidad A descargar.
	 *
	 * @return the cantidad A descargar
	 */
	public int getCantidadADescargar() {
		return cantidadADescargar;
	}

	/**
	 * Sets the cantidad A descargar.
	 *
	 * @param cantidadADescargar
	 *     the new cantidad A descargar
	 */
	public void setCantidadADescargar(int cantidadADescargar) {
		this.cantidadADescargar = cantidadADescargar;
	}

	/**
	 * Gets the banca privada.
	 *
	 * @return the banca privada
	 */
	public boolean getBancaPrivada() {
		return bancaPrivada;
	}

	/**
	 * Sets the banca privada.
	 *
	 * @param bancaPrivada
	 *     the new banca privada
	 */
	public void setBancaPrivada(boolean bancaPrivada) {
		this.bancaPrivada = bancaPrivada;
	}
}

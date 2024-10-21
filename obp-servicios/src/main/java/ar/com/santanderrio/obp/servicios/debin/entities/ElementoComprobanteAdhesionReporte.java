
package ar.com.santanderrio.obp.servicios.debin.entities;

/**
 * The Class ElementoComprobanteAdhesionReporte.
 */
public class ElementoComprobanteAdhesionReporte {

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The descripcion cuenta. */
	private String descripcionCuenta;

	/** The numero cuenta. */
	private String numeroCuenta;

	
	/**
	 * 
	 */
	public ElementoComprobanteAdhesionReporte() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param tipoCuenta
	 * @param descripcionCuenta
	 * @param numeroCuenta
	 */
	public ElementoComprobanteAdhesionReporte(String tipoCuenta, String descripcionCuenta, String numeroCuenta) {
		super();
		this.tipoCuenta = tipoCuenta;
		this.descripcionCuenta = descripcionCuenta;
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * @param tipoCuenta the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * @return the descripcionCuenta
	 */
	public String getDescripcionCuenta() {
		return descripcionCuenta;
	}

	/**
	 * @param descripcionCuenta the descripcionCuenta to set
	 */
	public void setDescripcionCuenta(String descripcionCuenta) {
		this.descripcionCuenta = descripcionCuenta;
	}

	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
}

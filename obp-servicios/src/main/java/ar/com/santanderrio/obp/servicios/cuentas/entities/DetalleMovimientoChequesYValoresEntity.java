/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Class DetalleMovimientoChequesYValores.
 *
 * @author B039542
 */
public class DetalleMovimientoChequesYValoresEntity extends DetalleMovimientoEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The sucursal origen. */
	private String sucursalOrigen;

	/** The nro comprobante. */
	private String nroComprobante;

	/** The is debito. */
	private boolean isDebito;

	/**
	 * Gets the sucursal origen.
	 *
	 * @return the sucursal origen
	 */
	public String getSucursalOrigen() {
		return sucursalOrigen;
	}

	/**
	 * Setter para sucursal origen.
	 *
	 * @param sucursalOrigen
	 *            el nuevo sucursal origen
	 */
	public void setSucursalOrigen(String sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Setter para nro comprobante.
	 *
	 * @param nroComprobante
	 *            el nuevo nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Devuelve si el movimiento corresponde a un debito o credito.
	 *
	 * @return true si es debito, false si es credito
	 */
	public boolean isDebito() {
		return isDebito;
	}

	/**
	 * Setter para debito.
	 *
	 * @param isDebito
	 *            el nuevo debito
	 */
	public void setDebito(boolean isDebito) {
		this.isDebito = isDebito;
	}

}

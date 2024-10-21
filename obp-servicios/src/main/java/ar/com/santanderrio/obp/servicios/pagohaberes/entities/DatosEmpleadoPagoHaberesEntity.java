/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.entities;

/**
 * The Class DatosEmpleadoPagoHaberes.
 */
public class DatosEmpleadoPagoHaberesEntity {

	/** The cuil cuit destinatario. */
	private String cuilCuitDestinatario;

	/** The alias destino. */
	private String aliasDestino;

	/** The cuenta origen. */
	private String cuentaOrigen;

	/** The tipo cuenta origen. */
	private String tipoCuentaOrigen;

	/** The cuenta destino. */
	private String cuentaDestino;

	/** The tipo cuenta destino. */
	private String tipoCuentaDestino;

	/** The cbu destino. */
	private String cbuDestino;

	/** The cuil. */
	private String cuil;

	/** The importe. */
	private String importe;

	/** The tipo pago. */
	private String tipoPago;

	/** The concepto. */
	private String concepto;

	/** The descripcion empleado. */
	private String descripcionEmpleado;

	/** The banco Destino. */
	private String bancoDestino;

	/** The fecha Ejecucion. */
	private String fechaEjecucion;

	/**
	 * Gets the alias destino.
	 *
	 * @return the aliasDestino
	 */
	public String getAliasDestino() {
		return aliasDestino;
	}

	/**
	 * Sets the alias destino.
	 *
	 * @param aliasDestino
	 *            the aliasDestino to set
	 */
	public void setAliasDestino(String aliasDestino) {
		this.aliasDestino = aliasDestino;
	}

	/**
	 * Gets the cuenta origen.
	 *
	 * @return the cuentaOrigen
	 */
	public String getCuentaOrigen() {
		return cuentaOrigen;
	}

	/**
	 * Sets the cuenta origen.
	 *
	 * @param cuentaOrigen
	 *            the cuentaOrigen to set
	 */
	public void setCuentaOrigen(String cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	/**
	 * Gets the tipo cuenta origen.
	 *
	 * @return the tipoCuentaOrigen
	 */
	public String getTipoCuentaOrigen() {
		return tipoCuentaOrigen;
	}

	/**
	 * Sets the tipo cuenta origen.
	 *
	 * @param tipoCuentaOrigen
	 *            the tipoCuentaOrigen to set
	 */
	public void setTipoCuentaOrigen(String tipoCuentaOrigen) {
		this.tipoCuentaOrigen = tipoCuentaOrigen;
	}

	/**
	 * Gets the cuenta destino.
	 *
	 * @return the cuentaDestino
	 */
	public String getCuentaDestino() {
		return cuentaDestino;
	}

	/**
	 * Sets the cuenta destino.
	 *
	 * @param cuentaDestino
	 *            the cuentaDestino to set
	 */
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	/**
	 * Gets the tipo cuenta destino.
	 *
	 * @return the tipoCuentaDestino
	 */
	public String getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/**
	 * Sets the tipo cuenta destino.
	 *
	 * @param tipoCuentaDestino
	 *            the tipoCuentaDestino to set
	 */
	public void setTipoCuentaDestino(String tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}

	/**
	 * Gets the cuil.
	 *
	 * @return the cuil
	 */
	public String getCuil() {
		return cuil;
	}

	/**
	 * Sets the cuil.
	 *
	 * @param cuil
	 *            the cuil to set
	 */
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the tipo pago.
	 *
	 * @return the tipoPago
	 */
	public String getTipoPago() {
		return tipoPago;
	}

	/**
	 * Sets the tipo pago.
	 *
	 * @param tipoPago
	 *            the tipoPago to set
	 */
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	/**
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Sets the concepto.
	 *
	 * @param concepto
	 *            the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * Gets the cbu destino.
	 *
	 * @return the cbu destino
	 */
	public String getCbuDestino() {
		return cbuDestino;
	}

	/**
	 * Sets the cbu destino.
	 *
	 * @param cbuDestino
	 *            the new cbu destino
	 */
	public void setCbuDestino(String cbuDestino) {
		this.cbuDestino = cbuDestino;
	}

	/**
	 * Gets the descripcion empleado.
	 *
	 * @return the descripcion empleado
	 */
	public String getDescripcionEmpleado() {
		return descripcionEmpleado;
	}

	/**
	 * Sets the descripcion empleado.
	 *
	 * @param descripcionEmpleado
	 *            the new descripcion empleado
	 */
	public void setDescripcionEmpleado(String descripcionEmpleado) {
		this.descripcionEmpleado = descripcionEmpleado;
	}

	/**
	 * Gets the banco destino.
	 *
	 * @return the banco destino
	 */
	public String getBancoDestino() {
		return bancoDestino;
	}

	/**
	 * Sets the banco destino.
	 *
	 * @param bancoDestino
	 *            the new banco destino
	 */
	public void setBancoDestino(String bancoDestino) {
		this.bancoDestino = bancoDestino;
	}

	/**
	 * Gets the fecha ejecucion.
	 *
	 * @return the fecha ejecucion
	 */
	public String getFechaEjecucion() {
		return fechaEjecucion;
	}

	/**
	 * Sets the fecha ejecucion.
	 *
	 * @param fechaEjecucion
	 *            the new fecha ejecucion
	 */
	public void setFechaEjecucion(String fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	/**
	 * Gets the cuil cuit destinatario.
	 *
	 * @return the cuil cuit destinatario
	 */
	public String getCuilCuitDestinatario() {
		return cuilCuitDestinatario;
	}

	/**
	 * Sets the cuil cuit destinatario.
	 *
	 * @param cuilCuitDestinatario
	 *            the new cuil cuit destinatario
	 */
	public void setCuilCuitDestinatario(String cuilCuitDestinatario) {
		this.cuilCuitDestinatario = cuilCuitDestinatario;
	}

}
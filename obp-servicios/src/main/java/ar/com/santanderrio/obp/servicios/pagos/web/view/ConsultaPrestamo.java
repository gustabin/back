/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

/**
 * The Class ConsultaPrestamo.
 */

public class ConsultaPrestamo {

	/** The id. */
	private String id;

	/** The id proceso. */
	private String idProceso;

	/** The numero prestamo. */
	private String numeroPrestamo;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The tipo prestamo. */
	private String tipoPrestamo;

	/** The fecha inicio prestamo. */
	private String fechaInicioPrestamo;

	/** The monto prestamo. */
	private String montoPrestamo;

	/** The plazo. */
	private String plazo;

	/** The motivo prestamo. */
	private String motivoPrestamo;

	/** The cbu. */
	private String cbu;

	/** The num vin. */
	private String numVin;

	/** The fallo plazo importe. */
	private Boolean falloPlazoImporte;
	
	private boolean isUva; 
	

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the id proceso.
	 *
	 * @return the id proceso
	 */
	public String getIdProceso() {
		return idProceso;
	}

	/**
	 * Sets the id proceso.
	 *
	 * @param idProceso
	 *            the new id proceso
	 */
	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}

	/**
	 * Gets the numero prestamo.
	 *
	 * @return the numero prestamo
	 */
	public String getNumeroPrestamo() {
		return numeroPrestamo;
	}

	/**
	 * Sets the numero prestamo.
	 *
	 * @param numeroPrestamo
	 *            the new numero prestamo
	 */
	public void setNumeroPrestamo(String numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the tipo prestamo.
	 *
	 * @return the tipo prestamo
	 */
	public String getTipoPrestamo() {
		return tipoPrestamo;
	}

	/**
	 * Sets the tipo prestamo.
	 *
	 * @param tipoPrestamo
	 *            the new tipo prestamo
	 */
	public void setTipoPrestamo(String tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}

	/**
	 * Gets the fecha inicio prestamo.
	 *
	 * @return the fecha inicio prestamo
	 */
	public String getFechaInicioPrestamo() {
		return fechaInicioPrestamo;
	}

	/**
	 * Sets the fecha inicio prestamo.
	 *
	 * @param fechaInicioPrestamo
	 *            the new fecha inicio prestamo
	 */
	public void setFechaInicioPrestamo(String fechaInicioPrestamo) {
		this.fechaInicioPrestamo = fechaInicioPrestamo;
	}

	/**
	 * Gets the monto prestamo.
	 *
	 * @return the monto prestamo
	 */
	public String getMontoPrestamo() {
		return montoPrestamo;
	}

	/**
	 * Sets the monto prestamo.
	 *
	 * @param montoPrestamo
	 *            the new monto prestamo
	 */
	public void setMontoPrestamo(String montoPrestamo) {
		this.montoPrestamo = montoPrestamo;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the new plazo
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Sets the motivo prestamo.
	 *
	 * @return the string
	 */
	public String getMotivoPrestamo() {
		return motivoPrestamo;
	}

	/**
	 * Sets the motivo prestamo.
	 *
	 * @param motivoPrestamo
	 *            the new motivo prestamo
	 */
	public void setMotivoPrestamo(String motivoPrestamo) {
		this.motivoPrestamo = motivoPrestamo;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the new cbu
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the num vin.
	 *
	 * @return the numVin
	 */
	public String getNumVin() {
		return numVin;
	}

	/**
	 * Sets the num vin.
	 *
	 * @param numVin
	 *            the numVin to set
	 */
	public void setNumVin(String numVin) {
		this.numVin = numVin;
	}

	/**
	 * Gets the fallo plazo importe.
	 *
	 * @return the fallo plazo importe
	 */
	public Boolean getFalloPlazoImporte() {
		return falloPlazoImporte;
	}

	/**
	 * Sets the fallo plazo importe.
	 *
	 * @param falloPlazoImporte
	 *            the new fallo plazo importe
	 */
	public void setFalloPlazoImporte(Boolean falloPlazoImporte) {
		this.falloPlazoImporte = falloPlazoImporte;
	}

	public boolean getIsUva() {
		return isUva;
	}

	public void setIsUva(boolean isUva) {
		this.isUva = isUva;
	}

}

package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Class MovimientoExcelItem.
 */
public class MovimientoExcelItem {

	/** The fecha hora. */
	private String fechaHora;

	/** The sucursal origen. */
	private String sucursalOrigen;

	/** The descripcion. */
	private String descripcion;

	/** The referencia. */
	private String referencia;

	/** The saldo. */
	private Double saldo;
	
	private String saldoString;

	/** The importe. */
	private Double importe;

	private String importeString;
	
	/** The is caja de ahoro en pesos. */
	private Boolean isCajaDeAhoroEnPesos;

	/** The is cuenta corriente en pesos. */
	private Boolean isCuentaCorrienteEnPesos;

	/** The is movimiento en dolares. */
	private Boolean isMovimientoEnDolares;

	/** The is del dia. */
	private Boolean isDelDia;
	
	/**
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public Double getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo
	 *            the new saldo
	 */
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public Double getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(Double importe) {
		this.importe = importe;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fecha hora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Setter para fecha hora.
	 *
	 * @param fechaHora
	 *            el nuevo fecha hora
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

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
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Setter para descripcion.
	 *
	 * @param descripcion
	 *            el nuevo descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the referencia.
	 *
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * Setter para referencia.
	 *
	 * @param referencia
	 *            el nuevo referencia
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * Gets the checks if is caja de ahoro en pesos.
	 *
	 * @return the checks if is caja de ahoro en pesos
	 */
	public Boolean getIsCajaDeAhoroEnPesos() {
		return isCajaDeAhoroEnPesos;
	}

	/**
	 * Setter para checks if is caja de ahoro en pesos.
	 *
	 * @param isCajaDeAhoroEnPesos
	 *            el nuevo checks if is caja de ahoro en pesos
	 */
	public void setIsCajaDeAhoroEnPesos(Boolean isCajaDeAhoroEnPesos) {
		this.isCajaDeAhoroEnPesos = isCajaDeAhoroEnPesos;
	}

	/**
	 * Gets the checks if is cuenta corriente en pesos.
	 *
	 * @return the checks if is cuenta corriente en pesos
	 */
	public Boolean getIsCuentaCorrienteEnPesos() {
		return isCuentaCorrienteEnPesos;
	}

	/**
	 * Setter para checks if is cuenta corriente en pesos.
	 *
	 * @param isCuentaCorrienteEnPesos
	 *            el nuevo checks if is cuenta corriente en pesos
	 */
	public void setIsCuentaCorrienteEnPesos(Boolean isCuentaCorrienteEnPesos) {
		this.isCuentaCorrienteEnPesos = isCuentaCorrienteEnPesos;
	}

	/**
	 * Gets the checks if is movimiento en dolares.
	 *
	 * @return the checks if is movimiento en dolares
	 */
	public Boolean getIsMovimientoEnDolares() {
		return isMovimientoEnDolares;
	}

	/**
	 * Setter para checks if is movimiento en dolares.
	 *
	 * @param isMovimientoEnDolares
	 *            el nuevo checks if is movimiento en dolares
	 */
	public void setIsMovimientoEnDolares(Boolean isMovimientoEnDolares) {
		this.isMovimientoEnDolares = isMovimientoEnDolares;
	}

	/**
	 * Gets the checks if is del dia.
	 *
	 * @return the checks if is del dia
	 */
	public Boolean getIsDelDia() {
		return isDelDia;
	}

	/**
	 * Sets the checks if is del dia.
	 *
	 * @param isDelDia the new checks if is del dia
	 */
	public void setIsDelDia(Boolean isDelDia) {
		this.isDelDia = isDelDia;
	}

	public String getSaldoString() {
		return saldoString;
	}

	public void setSaldoString(String saldoString) {
		this.saldoString = saldoString;
	}

	public String getImporteString() {
		return importeString;
	}

	public void setImporteString(String importeString) {
		this.importeString = importeString;
	}
	
}

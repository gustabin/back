/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

/**
 * The Class ItemMovimiento.
 *
 * @author Jonatan_Bocian
 */
public class ItemMovimiento {

	/** The id. */
	private String id;

	/** The fecha. */
	private String fecha;

	/** The fecha detalle. */
	private String fechaDetalle;

	/** The descripcion. */
	private String descripcion;

	/** The detalle. */
	private String detalle;

	/** The importe. */
	private String importe;

	/** The saldo. */
	private String saldo;

	/** The is debito. */
	private Boolean isDebito = false;

	/** The is caja de ahoro en pesos. */
	private Boolean isCajaDeAhoroEnPesos;

	/** The is cuenta corriente en pesos. */
	private Boolean isCuentaCorrienteEnPesos;

	/** The is movimiento en dolares. */
	private Boolean isMovimientoEnDolares;

	/** The divisa. */
	private String divisa = "";

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The numero referencia. */
	private String numeroReferencia;

	/** The hora. */
	private String hora;

	/** The numero sucursal. */
	private String numeroSucursal;

	/** The descripcion sucursal. */
	private String descripcionSucursal;

	/** The is cheque. */
	private Boolean isCheque = false;

	/** The is rechazado. */
	private Boolean isRechazado = false;

	/** The motivo rechazo. */
	private String motivoRechazo;

	/** The observacion. */
	private String observacion;

	/** The is del dia. */
	private boolean isDelDia;

	/**
	 * Gets the checks if is debito.
	 *
	 * @return the checks if is debito
	 */
	public Boolean getIsDebito() {
		return isDebito;
	}

	/**
	 * Setter para checks if is debito.
	 *
	 * @param isDebito
	 *            el nuevo checks if is debito
	 */
	public void setIsDebito(Boolean isDebito) {
		this.isDebito = isDebito;
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
	 * Setter para fecha.
	 *
	 * @param fecha
	 *            el nuevo fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the detalle.
	 *
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * Setter para detalle.
	 *
	 * @param detalle
	 *            the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
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
	 * Setter para importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}

	/**
	 * Setter para saldo.
	 *
	 * @param saldo
	 *            el nuevo saldo
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numero comprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Setter para numero comprobante.
	 *
	 * @param numeroComprobante
	 *            el nuevo numero comprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Setter para divisa.
	 *
	 * @param divisa
	 *            el nuevo divisa
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the numero referencia.
	 *
	 * @return the numero referencia
	 */
	public String getNumeroReferencia() {
		return numeroReferencia;
	}

	/**
	 * Setter para numero referencia.
	 *
	 * @param numeroReferencia
	 *            el nuevo numero referencia
	 */
	public void setNumeroReferencia(String numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Setter para hora.
	 *
	 * @param hora
	 *            el nuevo hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Gets the numero sucursal.
	 *
	 * @return the numero sucursal
	 */
	public String getNumeroSucursal() {
		return numeroSucursal;
	}

	/**
	 * Setter para numero sucursal.
	 *
	 * @param numeroSucursal
	 *            el nuevo numero sucursal
	 */
	public void setNumeroSucursal(String numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
	}

	/**
	 * Gets the descripcion sucursal.
	 *
	 * @return the descripcion sucursal
	 */
	public String getDescripcionSucursal() {
		return descripcionSucursal;
	}

	/**
	 * Setter para descripcion sucursal.
	 *
	 * @param descripcionSucursal
	 *            el nuevo descripcion sucursal
	 */
	public void setDescripcionSucursal(String descripcionSucursal) {
		this.descripcionSucursal = descripcionSucursal;
	}

	/**
	 * Gets the motivo rechazo.
	 *
	 * @return the motivo rechazo
	 */
	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	/**
	 * Setter para motivo rechazo.
	 *
	 * @param motivoRechazo
	 *            el nuevo motivo rechazo
	 */
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	/**
	 * Gets the observacion.
	 *
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * Setter para observacion.
	 *
	 * @param observacion
	 *            el nuevo observacion
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * Gets the checks if is cheque.
	 *
	 * @return the checks if is cheque
	 */
	public Boolean getIsCheque() {
		return isCheque;
	}

	/**
	 * Setter para checks if is cheque.
	 *
	 * @param isCheque
	 *            el nuevo checks if is cheque
	 */
	public void setIsCheque(Boolean isCheque) {
		this.isCheque = isCheque;
	}

	/**
	 * Gets the checks if is rechazado.
	 *
	 * @return the checks if is rechazado
	 */
	public Boolean getIsRechazado() {
		return isRechazado;
	}

	/**
	 * Setter para checks if is rechazado.
	 *
	 * @param isRechazado
	 *            el nuevo checks if is rechazado
	 */
	public void setIsRechazado(Boolean isRechazado) {
		this.isRechazado = isRechazado;
	}

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
	 * Gets the checks if is del dia.
	 *
	 * @return the checks if is del dia
	 */
	public boolean getIsDelDia() {
		return isDelDia;
	}

	/**
	 * Sets the del dia.
	 *
	 * @param isDelDia
	 *            the new del dia
	 */
	public void setDelDia(boolean isDelDia) {
		this.isDelDia = isDelDia;
	}

	/**
	 * Gets the fecha detalle.
	 *
	 * @return the fecha detalle
	 */
	public String getFechaDetalle() {
		return fechaDetalle;
	}

	/**
	 * Sets the fecha detalle.
	 *
	 * @param fechaDetalle
	 *            the new fecha detalle
	 */
	public void setFechaDetalle(String fechaDetalle) {
		this.fechaDetalle = fechaDetalle;
	}

}

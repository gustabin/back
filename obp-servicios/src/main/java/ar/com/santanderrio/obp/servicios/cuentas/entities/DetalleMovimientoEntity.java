/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class DetalleMovimiento.
 */
public class DetalleMovimientoEntity {

	/** The id. */
	private String id;

	/** The fecha. */
	private Date fecha;

	/** The fecha valor. */
	private Date fechaValor;

	/** The descripcion. */
	private String descripcion;

	/** The descripcion adicional. */
	private String descripcionAdicional;

	/** The saldo cuenta. */
	private BigDecimal saldoCuenta;

	/** The importe movimiento. */
	private BigDecimal importeMovimiento;

	/** The is caja de ahoro en pesos. */
	private boolean isCajaDeAhoroEnPesos;

	/** The is cuenta corriente en pesos. */
	private boolean isCuentaCorrienteEnPesos;

	/** The is movimiento en dolares. */
	private boolean isMovimientoEnDolares;

	/** The numero referencia. */
	private String numeroReferencia;

	/** The hora. */
	private String hora;

	/** The numero sucursal. */
	private String numeroSucursal;

	/** The descripcion sucursal. */
	private String descripcionSucursal;

	/** The is cheque. */
	private boolean isCheque;

	/** The is rechazado. */
	private boolean isRechazado;

	/** The motivo rechazo. */
	private String motivoRechazo;

	/** The observacion. */
	private String observacion;

	/** The isDelDia. */
	private boolean isDelDia;

	/**
	 * Gets the fecha valor.
	 *
	 * @return the fecha valor
	 */
	public Date getFechaValor() {
		Date vuelta = null;
		if (this.fechaValor != null) {
			vuelta = new Date(fechaValor.getTime());
		}
		return vuelta;
	}

	/**
	 * Setter para fecha valor.
	 *
	 * @param fechaValor
	 *            el nuevo fecha valor
	 */
	public void setFechaValor(Date fechaValor) {
		Date inputFecha = new Date(fechaValor.getTime());
		this.fechaValor = inputFecha;
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
	 * Gets the descripcion adicional.
	 *
	 * @return the descripcion adicional
	 */
	public String getDescripcionAdicional() {
		return descripcionAdicional;
	}

	/**
	 * Setter para descripcion adicional.
	 *
	 * @param descripcionAdicional
	 *            el nuevo descripcion adicional
	 */
	public void setDescripcionAdicional(String descripcionAdicional) {
		this.descripcionAdicional = descripcionAdicional;
	}

	/**
	 * Gets the importe movimiento.
	 *
	 * @return the importe movimiento
	 */
	public BigDecimal getImporteMovimiento() {
		return importeMovimiento;
	}

	/**
	 * Setter para importe movimiento.
	 *
	 * @param importeMovimiento
	 *            el nuevo importe movimiento
	 */
	public void setImporteMovimiento(BigDecimal importeMovimiento) {
		this.importeMovimiento = importeMovimiento;
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
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		Date vuelta = null;
		if (this.fecha != null) {
			vuelta = new Date(fecha.getTime());
		}
		return vuelta;
	}

	/**
	 * Setter para fecha.
	 *
	 * @param fecha
	 *            el nuevo fecha
	 */
	public void setFecha(Date fecha) {
		Date inputFecha = new Date(fecha.getTime());
		this.fecha = inputFecha;
	}

	/**
	 * Gets the saldo cuenta.
	 *
	 * @return the saldo cuenta
	 */
	public BigDecimal getSaldoCuenta() {
		return saldoCuenta;
	}

	/**
	 * Setter para saldo cuenta.
	 *
	 * @param saldoCuenta
	 *            el nuevo saldo cuenta
	 */
	public void setSaldoCuenta(BigDecimal saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
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
	 * Checks if is cheque.
	 *
	 * @return true, if is cheque
	 */
	public boolean isCheque() {
		return isCheque;
	}

	/**
	 * Setter para cheque.
	 *
	 * @param isCheque
	 *            el nuevo cheque
	 */
	public void setCheque(boolean isCheque) {
		this.isCheque = isCheque;
	}

	/**
	 * Checks if is rechazado.
	 *
	 * @return true, if is rechazado
	 */
	public boolean isRechazado() {
		return isRechazado;
	}

	/**
	 * Setter para rechazado.
	 *
	 * @param isRechazado
	 *            el nuevo rechazado
	 */
	public void setRechazado(boolean isRechazado) {
		this.isRechazado = isRechazado;
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

}

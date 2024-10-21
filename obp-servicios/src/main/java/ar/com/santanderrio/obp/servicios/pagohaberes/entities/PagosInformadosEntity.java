/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.entities;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class PagosInformados.
 *
 */

public class PagosInformadosEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8066900123547866985L;

	/** The empleado. */
	private String empleado;

	/** The cuit cuil. */
	private String cuitCuil;

	/** The tipo cuit cuil. */
	private String tipoCuitCuil;

	/** The tipo cta credito. */
	private String tipoCtaCredito;

	/** The suc cta credito. */
	private String sucCtaCredito;

	/** The nro cta credito. */
	private String nroCtaCredito;

	/** The tipo pago. */
	private String tipoPago;

	/** The descripcion concepto. */
	private String descripcionConcepto;

	/** The importe. */
	private BigDecimal importe;

	/** The tipo persona. */
	private String tipoPersona;

	/** The tipo id. */
	private String tipoId;

	/** The id cliente. */
	private String idCliente;

	/** The fecha nac. */
	private String fechaNac;

	/** The nup. */
	private String nup;

	/** The id transaccion. */
	private String idTransaccion;

	/** The divisa. */
	private String divisa;

	/** The tipo cta debito. */
	private String tipoCtaDebito;

	/** The suc cta debito. */
	private String sucCtaDebito;

	/** The nro cta debito. */
	private String nroCtaDebito;

	/** The nro Recurrencia. */
	private String nroRecurrencia;

	/** The fecha Recurrencia. */
	private String fechaBaseRecurrencia;

	/** The frec Recurrencia. */
	private String frecRecurrencia;

	/** The max Recurrencia. */
	private String maxRecurrencia;

	/** The tipo Recurrencia. */
	private String tipoRecurrencia;

	/** The tiene Pago Programado. */
	private Boolean tienePagoProgramado;

	/**
	 * Gets the empleado.
	 *
	 * @return the empleado
	 */
	public String getEmpleado() {
		return empleado;
	}

	/**
	 * Sets the empleado.
	 *
	 * @param empleado
	 *            the empleado to set
	 */
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	/**
	 * Gets the cuit cuil.
	 *
	 * @return the cuitCuil
	 */
	public String getCuitCuil() {
		return cuitCuil;
	}

	/**
	 * Sets the cuit cuil.
	 *
	 * @param cuitCuil
	 *            the cuitCuil to set
	 */
	public void setCuitCuil(String cuitCuil) {
		this.cuitCuil = cuitCuil;
	}

	/**
	 * Gets the tipo cuit cuil.
	 *
	 * @return the tipoCuitCuil
	 */
	public String getTipoCuitCuil() {
		return tipoCuitCuil;
	}

	/**
	 * Sets the tipo cuit cuil.
	 *
	 * @param tipoCuitCuil
	 *            the tipoCuitCuil to set
	 */
	public void setTipoCuitCuil(String tipoCuitCuil) {
		this.tipoCuitCuil = tipoCuitCuil;
	}

	/**
	 * Gets the tipo cta credito.
	 *
	 * @return the tipoCtaCredito
	 */
	public String getTipoCtaCredito() {
		return tipoCtaCredito;
	}

	/**
	 * Sets the tipo cta credito.
	 *
	 * @param tipoCtaCredito
	 *            the tipoCtaCredito to set
	 */
	public void setTipoCtaCredito(String tipoCtaCredito) {
		this.tipoCtaCredito = tipoCtaCredito;
	}

	/**
	 * Gets the suc cta credito.
	 *
	 * @return the sucCtaCredito
	 */
	public String getSucCtaCredito() {
		return sucCtaCredito;
	}

	/**
	 * Sets the suc cta credito.
	 *
	 * @param sucCtaCredito
	 *            the sucCtaCredito to set
	 */
	public void setSucCtaCredito(String sucCtaCredito) {
		this.sucCtaCredito = sucCtaCredito;
	}

	/**
	 * Gets the nro cta credito.
	 *
	 * @return the nroCtaCredito
	 */
	public String getNroCtaCredito() {
		return nroCtaCredito;
	}

	/**
	 * Sets the nro cta credito.
	 *
	 * @param nroCtaCredito
	 *            the nroCtaCredito to set
	 */
	public void setNroCtaCredito(String nroCtaCredito) {
		this.nroCtaCredito = nroCtaCredito;
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
	 * Gets the descripcion concepto.
	 *
	 * @return the descripcionConcepto
	 */
	public String getDescripcionConcepto() {
		return descripcionConcepto;
	}

	/**
	 * Sets the descripcion concepto.
	 *
	 * @param descripcionConcepto
	 *            the descripcionConcepto to set
	 */
	public void setDescripcionConcepto(String descripcionConcepto) {
		this.descripcionConcepto = descripcionConcepto;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the tipo persona.
	 *
	 * @return the tipo persona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}

	/**
	 * Sets the tipo persona.
	 *
	 * @param tipoPersona
	 *            the new tipo persona
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	/**
	 * Gets the tipo id.
	 *
	 * @return the tipo id
	 */
	public String getTipoId() {
		return tipoId;
	}

	/**
	 * Sets the tipo id.
	 *
	 * @param tipoId
	 *            the new tipo id
	 */
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	/**
	 * Gets the id cliente.
	 *
	 * @return the id cliente
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * Sets the id cliente.
	 *
	 * @param idCliente
	 *            the new id cliente
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * Gets the fecha nac.
	 *
	 * @return the fecha nac
	 */
	public String getFechaNac() {
		return fechaNac;
	}

	/**
	 * Sets the fecha nac.
	 *
	 * @param fechaNac
	 *            the new fecha nac
	 */
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the id transaccion.
	 *
	 * @return the id transaccion
	 */
	public String getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Sets the id transaccion.
	 *
	 * @param idTransaccion
	 *            the new id transaccion
	 */
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
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
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the new divisa
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the tipo cta debito.
	 *
	 * @return the tipo cta debito
	 */
	public String getTipoCtaDebito() {
		return tipoCtaDebito;
	}

	/**
	 * Sets the tipoCtaDebito.
	 *
	 * @param tipoCtaDebito
	 *            the new tipo cta debito
	 */
	public void setTipoCtaDebito(String tipoCtaDebito) {
		this.tipoCtaDebito = tipoCtaDebito;
	}

	/**
	 * Gets the suc cta debito.
	 *
	 * @return the suc cta debito
	 */
	public String getSucCtaDebito() {
		return sucCtaDebito;
	}

	/**
	 * Sets the sucCtaDebito.
	 *
	 * @param sucCtaDebito
	 *            the new suc cta debito
	 */
	public void setSucCtaDebito(String sucCtaDebito) {
		this.sucCtaDebito = sucCtaDebito;
	}

	/**
	 * Gets the nro cta debito.
	 *
	 * @return the nro cta debito
	 */
	public String getNroCtaDebito() {
		return nroCtaDebito;
	}

	/**
	 * Sets the nroCtaDebito.
	 *
	 * @param nroCtaDebito
	 *            the new nro cta debito
	 */
	public void setNroCtaDebito(String nroCtaDebito) {
		this.nroCtaDebito = nroCtaDebito;
	}

	/**
	 * Gets the nro recurrencia.
	 *
	 * @return the nro recurrencia
	 */
	public String getNroRecurrencia() {
		return nroRecurrencia;
	}

	/**
	 * Sets the nro recurrencia.
	 *
	 * @param nroRecurrencia
	 *            the new nro recurrencia
	 */
	public void setNroRecurrencia(String nroRecurrencia) {
		this.nroRecurrencia = nroRecurrencia;
	}

	/**
	 * Gets the fecha base recurrencia.
	 *
	 * @return the fechaBaseRecurrencia
	 */
	public String getFechaBaseRecurrencia() {
		return fechaBaseRecurrencia;
	}

	/**
	 * Sets the fecha base recurrencia.
	 *
	 * @param fechaBaseRecurrencia
	 *            the fechaBaseRecurrencia to set
	 */
	public void setFechaBaseRecurrencia(String fechaBaseRecurrencia) {
		this.fechaBaseRecurrencia = fechaBaseRecurrencia;
	}

	/**
	 * Gets the frec recurrencia.
	 *
	 * @return the frecRecurrencia
	 */
	public String getFrecRecurrencia() {
		return frecRecurrencia;
	}

	/**
	 * Sets the frec recurrencia.
	 *
	 * @param frecRecurrencia
	 *            the frecRecurrencia to set
	 */
	public void setFrecRecurrencia(String frecRecurrencia) {
		this.frecRecurrencia = frecRecurrencia;
	}

	/**
	 * Gets the max recurrencia.
	 *
	 * @return the maxRecurrencia
	 */
	public String getMaxRecurrencia() {
		return maxRecurrencia;
	}

	/**
	 * Sets the max recurrencia.
	 *
	 * @param maxRecurrencia
	 *            the maxRecurrencia to set
	 */
	public void setMaxRecurrencia(String maxRecurrencia) {
		this.maxRecurrencia = maxRecurrencia;
	}

	/**
	 * Gets the tipo recurrencia.
	 *
	 * @return the tipoRecurrencia
	 */
	public String getTipoRecurrencia() {
		return tipoRecurrencia;
	}

	/**
	 * Sets the tipo recurrencia.
	 *
	 * @param tipoRecurrencia
	 *            the tipoRecurrencia to set
	 */
	public void setTipoRecurrencia(String tipoRecurrencia) {
		this.tipoRecurrencia = tipoRecurrencia;
	}

	/**
	 * Gets the tiene pago programado.
	 *
	 * @return the tiene pago programado
	 */
	public Boolean getTienePagoProgramado() {
		return tienePagoProgramado;
	}

	/**
	 * Sets the tiene pago programado.
	 *
	 * @param tienePagoProgramado
	 *            the new tiene pago programado
	 */
	public void setTienePagoProgramado(Boolean tienePagoProgramado) {
		this.tienePagoProgramado = tienePagoProgramado;
	}

}

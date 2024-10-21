/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.beans.BeanUtils;

import ar.com.santanderrio.base.web.view.View;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleTarjetaPago;

/**
 * The Class DetalleTarjetaPagoView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DetalleTarjetaPagoView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3521870353741713026L;

	/** The tarjeta numero. */
	private String numeroTarjeta;

	/** The alias tarjeta. */
	private String aliasTarjeta;

	/** The tarjeta tipo. */
	private String tarjetaTipo;

	/** The imprte pesos. */
	private String importePesos;

	/** The alias cuenta pesos. */
	private String aliasCuentaPesos;

	/** The tipo cuenta pesos. */
	private String tipoCuentaPesos;

	/** The numero cuenta pesos. */
	private String numeroCuentaPesos;

	/** The importe dolares. */
	private String importeDolares;

	/** The alias cuenta dolares. */
	private String aliasCuentaDolares;

	/** The tipo cuenta dolares. */
	private String tipoCuentaDolares;

	/** The numero cuenta dolares. */
	private String numeroCuentaDolares;

	/** The saldo pesos. */
	private String saldoPesos;

	/** The saldo dolares. */
	private String saldoDolares;

	/** The ultimo resumen pesos. */
	private String ultimoResumenPesos;

	/** The ultimo resumen dolares. */
	private String ultimoResumenDolares;

	/** The pagos realizados pesos. */
	private String pagosRealizadosPesos;

	/** The pagos realizados dolares. */
	private String pagosRealizadosDolares;

	/** The fecha. */
	private String fechaProximoVencimientoTC;

	/** The pago minimo. */
	private String pagoMinimo;

	/** The tipo de pago. */
	private String descripcionTipoDePago;

	/** The tipo de pago. */
	private String tipoDePago;

	/** solo pago programado. */
	private String fechaProgramada;

	/** The is stop debit. */
	private Boolean isStopDebit;

	/** The descripcion tarjeta. */
	private String descripcionTarjeta;

	/**
	 * Instantiates a new detalle tarjeta pago view.
	 */
	public DetalleTarjetaPagoView() {
		super();
	}

	/**
	 * Instantiates a new detalle tarjeta pago view.
	 *
	 * @param datosTarjeta
	 *            the datos tarjeta
	 */
	public DetalleTarjetaPagoView(DetalleTarjetaPago datosTarjeta) {
		BeanUtils.copyProperties(datosTarjeta, this);
		this.tipoDePago = datosTarjeta.getTipoDePago().toString();
		this.isStopDebit = datosTarjeta.isStopDebit();
		this.descripcionTarjeta = datosTarjeta.getTarjetaTipo() + " " + datosTarjeta.getNumeroTarjeta();
	}

	/**
	 * Gets the descripcion tarjeta.
	 *
	 * @return the descripcion tarjeta
	 */
	public String getDescripcionTarjeta() {
		return descripcionTarjeta;
	}

	/**
	 * Sets the descripcion tarjeta.
	 *
	 * @param descripcionTarjeta
	 *            the new descripcion tarjeta
	 */
	public void setDescripcionTarjeta(String descripcionTarjeta) {
		this.descripcionTarjeta = descripcionTarjeta;
	}

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numero tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the new numero tarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * Gets the alias tarjeta.
	 *
	 * @return the alias tarjeta
	 */
	public String getAliasTarjeta() {
		return aliasTarjeta;
	}

	/**
	 * Sets the alias tarjeta.
	 *
	 * @param aliasTarjeta
	 *            the new alias tarjeta
	 */
	public void setAliasTarjeta(String aliasTarjeta) {
		this.aliasTarjeta = aliasTarjeta;
	}

	/**
	 * Gets the tarjeta tipo.
	 *
	 * @return the tarjeta tipo
	 */
	public String getTarjetaTipo() {
		return tarjetaTipo;
	}

	/**
	 * Sets the tarjeta tipo.
	 *
	 * @param tarjetaTipo
	 *            the new tarjeta tipo
	 */
	public void setTarjetaTipo(String tarjetaTipo) {
		this.tarjetaTipo = tarjetaTipo;
	}

	/**
	 * Gets the importe pesos.
	 *
	 * @return the importe pesos
	 */
	public String getImportePesos() {
		return importePesos;
	}

	/**
	 * Sets the importe pesos.
	 *
	 * @param importePesos
	 *            the new importe pesos
	 */
	public void setImportePesos(String importePesos) {
		this.importePesos = importePesos;
	}

	/**
	 * Gets the alias cuenta pesos.
	 *
	 * @return the alias cuenta pesos
	 */
	public String getAliasCuentaPesos() {
		return aliasCuentaPesos;
	}

	/**
	 * Sets the alias cuenta pesos.
	 *
	 * @param aliasCuentaPesos
	 *            the new alias cuenta pesos
	 */
	public void setAliasCuentaPesos(String aliasCuentaPesos) {
		this.aliasCuentaPesos = aliasCuentaPesos;
	}

	/**
	 * Gets the tipo cuenta pesos.
	 *
	 * @return the tipo cuenta pesos
	 */
	public String getTipoCuentaPesos() {
		return tipoCuentaPesos;
	}

	/**
	 * Sets the tipo cuenta pesos.
	 *
	 * @param tipoCuentaPesos
	 *            the new tipo cuenta pesos
	 */
	public void setTipoCuentaPesos(String tipoCuentaPesos) {
		this.tipoCuentaPesos = tipoCuentaPesos;
	}

	/**
	 * Gets the numero cuenta pesos.
	 *
	 * @return the numero cuenta pesos
	 */
	public String getNumeroCuentaPesos() {
		return numeroCuentaPesos;
	}

	/**
	 * Sets the numero cuenta pesos.
	 *
	 * @param numeroCuentaPesos
	 *            the new numero cuenta pesos
	 */
	public void setNumeroCuentaPesos(String numeroCuentaPesos) {
		this.numeroCuentaPesos = numeroCuentaPesos;
	}

	/**
	 * Gets the importe dolares.
	 *
	 * @return the importe dolares
	 */
	public String getImporteDolares() {
		return importeDolares;
	}

	/**
	 * Sets the importe dolares.
	 *
	 * @param importeDolares
	 *            the new importe dolares
	 */
	public void setImporteDolares(String importeDolares) {
		this.importeDolares = importeDolares;
	}

	/**
	 * Gets the alias cuenta dolares.
	 *
	 * @return the alias cuenta dolares
	 */
	public String getAliasCuentaDolares() {
		return aliasCuentaDolares;
	}

	/**
	 * Sets the alias cuenta dolares.
	 *
	 * @param aliasCuentaDolares
	 *            the new alias cuenta dolares
	 */
	public void setAliasCuentaDolares(String aliasCuentaDolares) {
		this.aliasCuentaDolares = aliasCuentaDolares;
	}

	/**
	 * Gets the tipo cuenta dolares.
	 *
	 * @return the tipo cuenta dolares
	 */
	public String getTipoCuentaDolares() {
		return tipoCuentaDolares;
	}

	/**
	 * Sets the tipo cuenta dolares.
	 *
	 * @param tipoCuentaDolares
	 *            the new tipo cuenta dolares
	 */
	public void setTipoCuentaDolares(String tipoCuentaDolares) {
		this.tipoCuentaDolares = tipoCuentaDolares;
	}

	/**
	 * Gets the numero cuenta dolares.
	 *
	 * @return the numero cuenta dolares
	 */
	public String getNumeroCuentaDolares() {
		return numeroCuentaDolares;
	}

	/**
	 * Sets the numero cuenta dolares.
	 *
	 * @param numeroCuentaDolares
	 *            the new numero cuenta dolares
	 */
	public void setNumeroCuentaDolares(String numeroCuentaDolares) {
		this.numeroCuentaDolares = numeroCuentaDolares;
	}

	/**
	 * Gets the saldo pesos.
	 *
	 * @return the saldo pesos
	 */
	public String getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Sets the saldo pesos.
	 *
	 * @param saldoPesos
	 *            the new saldo pesos
	 */
	public void setSaldoPesos(String saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

	/**
	 * Gets the saldo dolares.
	 *
	 * @return the saldo dolares
	 */
	public String getSaldoDolares() {
		return saldoDolares;
	}

	/**
	 * Sets the saldo dolares.
	 *
	 * @param saldoDolares
	 *            the new saldo dolares
	 */
	public void setSaldoDolares(String saldoDolares) {
		this.saldoDolares = saldoDolares;
	}

	/**
	 * Gets the ultimo resumen pesos.
	 *
	 * @return the ultimo resumen pesos
	 */
	public String getUltimoResumenPesos() {
		return ultimoResumenPesos;
	}

	/**
	 * Sets the ultimo resumen pesos.
	 *
	 * @param ultimoResumenPesos
	 *            the new ultimo resumen pesos
	 */
	public void setUltimoResumenPesos(String ultimoResumenPesos) {
		this.ultimoResumenPesos = ultimoResumenPesos;
	}

	/**
	 * Gets the ultimo resumen dolares.
	 *
	 * @return the ultimo resumen dolares
	 */
	public String getUltimoResumenDolares() {
		return ultimoResumenDolares;
	}

	/**
	 * Sets the ultimo resumen dolares.
	 *
	 * @param ultimoResumenDolares
	 *            the new ultimo resumen dolares
	 */
	public void setUltimoResumenDolares(String ultimoResumenDolares) {
		this.ultimoResumenDolares = ultimoResumenDolares;
	}

	/**
	 * Gets the pagos realizados pesos.
	 *
	 * @return the pagos realizados pesos
	 */
	public String getPagosRealizadosPesos() {
		return pagosRealizadosPesos;
	}

	/**
	 * Sets the pagos realizados pesos.
	 *
	 * @param pagosRealizadosPesos
	 *            the new pagos realizados pesos
	 */
	public void setPagosRealizadosPesos(String pagosRealizadosPesos) {
		this.pagosRealizadosPesos = pagosRealizadosPesos;
	}

	/**
	 * Gets the pagos realizados dolares.
	 *
	 * @return the pagos realizados dolares
	 */
	public String getPagosRealizadosDolares() {
		return pagosRealizadosDolares;
	}

	/**
	 * Sets the pagos realizados dolares.
	 *
	 * @param pagosRealizadosDolares
	 *            the new pagos realizados dolares
	 */
	public void setPagosRealizadosDolares(String pagosRealizadosDolares) {
		this.pagosRealizadosDolares = pagosRealizadosDolares;
	}

	/**
	 * Gets the fecha proximo vencimiento TC.
	 *
	 * @return the fecha proximo vencimiento TC
	 */
	public String getFechaProximoVencimientoTC() {
		return fechaProximoVencimientoTC;
	}

	/**
	 * Sets the fecha proximo vencimiento TC.
	 *
	 * @param fechaProximoVencimientoTC
	 *            the new fecha proximo vencimiento TC
	 */
	public void setFechaProximoVencimientoTC(String fechaProximoVencimientoTC) {
		this.fechaProximoVencimientoTC = fechaProximoVencimientoTC;
	}

	/**
	 * Gets the pago minimo.
	 *
	 * @return the pago minimo
	 */
	public String getPagoMinimo() {
		return pagoMinimo;
	}

	/**
	 * Sets the pago minimo.
	 *
	 * @param pagoMinimo
	 *            the new pago minimo
	 */
	public void setPagoMinimo(String pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}

	/**
	 * Gets the descripcion tipo de pago.
	 *
	 * @return the descripcion tipo de pago
	 */
	public String getDescripcionTipoDePago() {
		return descripcionTipoDePago;
	}

	/**
	 * Sets the descripcion tipo de pago.
	 *
	 * @param descripcionTipoDePago
	 *            the new descripcion tipo de pago
	 */
	public void setDescripcionTipoDePago(String descripcionTipoDePago) {
		this.descripcionTipoDePago = descripcionTipoDePago;
	}

	/**
	 * Gets the tipo de pago.
	 *
	 * @return the tipo de pago
	 */
	public String getTipoDePago() {
		return tipoDePago;
	}

	/**
	 * Sets the tipo de pago.
	 *
	 * @param tipoDePago
	 *            the new tipo de pago
	 */
	public void setTipoDePago(String tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

	/**
	 * Gets the fecha programada.
	 *
	 * @return the fecha programada
	 */
	public String getFechaProgramada() {
		return fechaProgramada;
	}

	/**
	 * Sets the fecha programada.
	 *
	 * @param fechaProgramada
	 *            the new fecha programada
	 */
	public void setFechaProgramada(String fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}

	/**
	 * Gets the checks if is stop debit.
	 *
	 * @return the checks if is stop debit
	 */
	public Boolean getIsStopDebit() {
		return isStopDebit;
	}

	/**
	 * Sets the checks if is stop debit.
	 *
	 * @param isStopDebit
	 *            the new checks if is stop debit
	 */
	public void setIsStopDebit(Boolean isStopDebit) {
		this.isStopDebit = isStopDebit;
	}

}

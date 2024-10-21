/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The Class PagoTarjetaCreditoView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagoTarjetaCreditoView {

	/** The cbu pesos. */
	@Pattern(regexp = "[0-9]{22}")
	private String cbuPesos; // Sucursal_Cuenta_Debito

	/** The nro tarjeta. */
	private String numeroTarjeta;

	/** 0: minimo; 1: total; 2: otro. */
	@Pattern(regexp = "[0-2]{1}")
	private String tipoPagoTC;

	/**
	 * Si Tipo_Pago_TC = "0" o "1", se envíara vacio. Si PATJC.Tipo_Pago_TC =2
	 * (otro), enviar importe ingresado por el cliente en el campo otro. Debera
	 * ser mayor que cero y menor a 12 dígitos Si el pago es en ambas monedas
	 * (simultaneo) en este campo salo se envia el importe en pesos.
	 */
	private OtroImportePesosEntity importePagoPesos;

	/** The cbu dolares. */
	@Pattern(regexp = "[0-9]{22}", message = "{validation.pagoTarjetaCreditoView.cbuDolares}")
	private String cbuDolares; // Sucursal_Cuenta_Debito

	/** The importe pago dolares. */
	private OtroImporteDolares importePagoDolares;

	/** The fecha de pago. */
	private String fechaDePago;

	/** The stop debit. */
	private String stopDebit;

	/** 0: pesos; 1: dolares; 2: ambos. */
	@Pattern(regexp = "[0-2]{1}", message = "{validation.pagoTarjetaCreditoView.monedaSeleccionado}")
	private String monedaSeleccionado;

	/** The total A pagar en dolares. */
	private String totalAPagarEnDolares;

	/** The total A pagar en pesos. */
	private String totalAPagarEnPesos;

	/** The saldo dolares TC. */
	private String saldoDolaresTC;

	/** The saldo pesos TC. */
	private String saldoPesosTC;

	/** The importe minimo. */
	private String importeMinimo;

	/** The tiene pagos programados. */
	private String tienePagosProgramados;

	/** The saldo a pagar convertido a dolares. */
	private String saldoAPagarConvertidoADolares;

	/** The saldo a pagar convertido a pesos. */
	private String saldoAPagarConvertidoAPesos;

	/** The saldo sin siguiente cierre dolares. */
	private String saldoSinSiguienteCierreDolares;

	/** The saldo sin siguiente cierre pesos. */
	private String saldoSinSiguienteCierrePesos;

	/** The tipo Tarjeta. */
	private String tipoTarjeta;

	/**
	 * Gets the cbu pesos.
	 *
	 * @return the cbu pesos
	 */
	public String getCbuPesos() {
		return cbuPesos;
	}

	/**
	 * Sets the cbu pesos.
	 *
	 * @param cbuPesos
	 *            the new cbu pesos
	 */
	public void setCbuPesos(String cbuPesos) {
		this.cbuPesos = cbuPesos;
	}

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return the nro tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the new numero tarjeta
	 */
	public void setNumeroTarjeta(String nroTarjeta) {
		this.numeroTarjeta = nroTarjeta;
	}

	/**
	 * Gets the tipo pago TC.
	 *
	 * @return the tipo pago TC
	 */
	public String getTipoPagoTC() {
		return tipoPagoTC;
	}

	/**
	 * Sets the tipo pago TC.
	 *
	 * @param tipoPagoTC
	 *            the new tipo pago TC
	 */
	public void setTipoPagoTC(String tipoPagoTC) {
		this.tipoPagoTC = tipoPagoTC;
	}

	/**
	 * Gets the cbu dolares.
	 *
	 * @return the cbu dolares
	 */
	public String getCbuDolares() {
		return cbuDolares;
	}

	/**
	 * Sets the cbu dolares.
	 *
	 * @param cbuDolares
	 *            the new cbu dolares
	 */
	public void setCbuDolares(String cbuDolares) {
		this.cbuDolares = cbuDolares;
	}

	/**
	 * Gets the fecha de pago.
	 *
	 * @return the fecha de pago
	 */
	public String getFechaDePago() {
		return fechaDePago;
	}

	/**
	 * Sets the fecha de pago.
	 *
	 * @param fechaDePago
	 *            the new fecha de pago
	 */
	public void setFechaDePago(String fechaDePago) {
		this.fechaDePago = fechaDePago;
	}

	/**
	 * Gets the moneda seleccionado.
	 *
	 * @return the moneda seleccionado
	 */
	public String getMonedaSeleccionado() {
		return monedaSeleccionado;
	}

	/**
	 * Sets the moneda seleccionado.
	 *
	 * @param monedaSeleccionado
	 *            the new moneda seleccionado
	 */
	public void setMonedaSeleccionado(String monedaSeleccionado) {
		this.monedaSeleccionado = monedaSeleccionado;
	}

	/**
	 * Gets the total A pagar en dolares.
	 *
	 * @return the total A pagar en dolares
	 */
	public String getTotalAPagarEnDolares() {
		return totalAPagarEnDolares;
	}

	/**
	 * Sets the total A pagar en dolares.
	 *
	 * @param totalAPagarEnDolares
	 *            the new total A pagar en dolares
	 */
	public void setTotalAPagarEnDolares(String totalAPagarEnDolares) {
		this.totalAPagarEnDolares = totalAPagarEnDolares;
	}

	/**
	 * Gets the total A pagar en pesos.
	 *
	 * @return the total A pagar en pesos
	 */
	public String getTotalAPagarEnPesos() {
		return totalAPagarEnPesos;
	}

	/**
	 * Sets the total A pagar en pesos.
	 *
	 * @param totalAPagarEnPesos
	 *            the new total A pagar en pesos
	 */
	public void setTotalAPagarEnPesos(String totalAPagarEnPesos) {
		this.totalAPagarEnPesos = totalAPagarEnPesos;
	}

	/**
	 * Gets the saldo dolares TC.
	 *
	 * @return the saldo dolares TC
	 */
	public String getSaldoDolaresTC() {
		return saldoDolaresTC;
	}

	/**
	 * Sets the saldo dolares TC.
	 *
	 * @param saldoDolaresTC
	 *            the new saldo dolares TC
	 */
	public void setSaldoDolaresTC(String saldoDolaresTC) {
		this.saldoDolaresTC = saldoDolaresTC;
	}

	/**
	 * Gets the saldo pesos TC.
	 *
	 * @return the saldo pesos TC
	 */
	public String getSaldoPesosTC() {
		return saldoPesosTC;
	}

	/**
	 * Gets the stop debit.
	 *
	 * @return the stop debit
	 */
	public String getStopDebit() {
		return stopDebit;
	}

	/**
	 * Sets the saldo pesos TC.
	 *
	 * @param saldoPesosTC
	 *            the new saldo pesos TC
	 */
	public void setSaldoPesosTC(String saldoPesosTC) {
		this.saldoPesosTC = saldoPesosTC;
	}

	/**
	 * Gets the importe minimo.
	 *
	 * @return the importe minimo
	 */
	public String getImporteMinimo() {
		return importeMinimo;
	}

	/**
	 * Sets the importe minimo.
	 *
	 * @param importeMinimo
	 *            the new importe minimo
	 */
	public void setImporteMinimo(String importeMinimo) {
		this.importeMinimo = importeMinimo;
	}

	/**
	 * Sets the stop debit.
	 *
	 * @param stopDebit
	 *            the new stop debit
	 */
	public void setStopDebit(String stopDebit) {
		this.stopDebit = stopDebit;
	}

	/**
	 * Gets the tiene pagos programados.
	 *
	 * @return the tiene pagos programados
	 */
	public String getTienePagosProgramados() {
		return tienePagosProgramados;
	}

	/**
	 * Sets the tiene pagos programados.
	 *
	 * @param tienePagosProgramados
	 *            the new tiene pagos programados
	 */
	public void setTienePagosProgramados(String tienePagosProgramados) {
		this.tienePagosProgramados = tienePagosProgramados;
	}

	/**
	 * Gets the saldo A pagar convertido A dolares.
	 *
	 * @return the saldo A pagar convertido A dolares
	 */
	public String getSaldoAPagarConvertidoADolares() {
		return saldoAPagarConvertidoADolares;
	}

	/**
	 * Sets the saldo A pagar convertido A dolares.
	 *
	 * @param saldoAPagarConvertidoADolares
	 *            the new saldo A pagar convertido A dolares
	 */
	public void setSaldoAPagarConvertidoADolares(String saldoAPagarConvertidoADolares) {
		this.saldoAPagarConvertidoADolares = saldoAPagarConvertidoADolares;
	}

	/**
	 * Gets the saldo A pagar convertido A pesos.
	 *
	 * @return the saldo A pagar convertido A pesos
	 */
	public String getSaldoAPagarConvertidoAPesos() {
		return saldoAPagarConvertidoAPesos;
	}

	/**
	 * Sets the saldo A pagar convertido A pesos.
	 *
	 * @param saldoAPagarConvertidoAPesos
	 *            the new saldo A pagar convertido A pesos
	 */
	public void setSaldoAPagarConvertidoAPesos(String saldoAPagarConvertidoAPesos) {
		this.saldoAPagarConvertidoAPesos = saldoAPagarConvertidoAPesos;
	}

	/**
	 * Gets the saldo sin siguiente cierre dolares.
	 *
	 * @return the saldo sin siguiente cierre dolares
	 */
	public String getSaldoSinSiguienteCierreDolares() {
		return saldoSinSiguienteCierreDolares;
	}

	/**
	 * Sets the saldo sin siguiente cierre dolares.
	 *
	 * @param saldoSinSiguienteCierreDolares
	 *            the new saldo sin siguiente cierre dolares
	 */
	public void setSaldoSinSiguienteCierreDolares(String saldoSinSiguienteCierreDolares) {
		this.saldoSinSiguienteCierreDolares = saldoSinSiguienteCierreDolares;
	}

	/**
	 * Gets the saldo sin siguiente cierre pesos.
	 *
	 * @return the saldo sin siguiente cierre pesos
	 */
	public String getSaldoSinSiguienteCierrePesos() {
		return saldoSinSiguienteCierrePesos;
	}

	/**
	 * Sets the saldo sin siguiente cierre pesos.
	 *
	 * @param saldoSinSiguienteCierrePesos
	 *            the new saldo sin siguiente cierre pesos
	 */
	public void setSaldoSinSiguienteCierrePesos(String saldoSinSiguienteCierrePesos) {
		this.saldoSinSiguienteCierrePesos = saldoSinSiguienteCierrePesos;
	}

	/**
	 * Gets the importe pago pesos.
	 *
	 * @return the importe pago pesos
	 */
	public OtroImportePesosEntity getImportePagoPesos() {
		return importePagoPesos;
	}

	/**
	 * Sets the importe pago pesos.
	 *
	 * @param importePagoPesos
	 *            the new importe pago pesos
	 */
	public void setImportePagoPesos(OtroImportePesosEntity importePagoPesos) {
		this.importePagoPesos = importePagoPesos;
	}

	/**
	 * Gets the importe pago dolares.
	 *
	 * @return the importe pago dolares
	 */
	public OtroImporteDolares getImportePagoDolares() {
		return importePagoDolares;
	}

	/**
	 * Sets the importe pago dolares.
	 *
	 * @param importePagoDolares
	 *            the new importe pago dolares
	 */
	public void setImportePagoDolares(OtroImporteDolares importePagoDolares) {
		this.importePagoDolares = importePagoDolares;
	}

	/**
	 * Obtener importe ingresado pesos.
	 *
	 * @return the string
	 */
	public String obtenerImporteIngresadoPesos() {
		if (this.importePagoPesos != null) {
			return this.importePagoPesos.getNum();
		}
		return null;
	}

	/**
	 * Obtener importe ingresado dolares.
	 *
	 * @return the string
	 */
	public String obtenerImporteIngresadoDolares() {
		if (this.importePagoDolares != null) {
			return this.importePagoDolares.getNum();
		}
		return null;
	}

	/**
	 * Gets the tipo tarjeta.
	 *
	 * @return the tipo tarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Sets the tipo tarjeta.
	 *
	 * @param tipoTarjeta
	 *            the new tipo tarjeta
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
}

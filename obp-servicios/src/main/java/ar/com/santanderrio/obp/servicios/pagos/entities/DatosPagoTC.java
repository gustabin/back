/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * Entity que encapsula los datos para la llamada al servicio de pago de
 * tarjetas de creditos PAGTJC.
 *
 * @author marcelo.ruiz
 */
public class DatosPagoTC {

	/** The sucursal cuenta debito. */
	@Pattern(regexp = "\\d{2}$")
	private String sucursalCuentaDebito; // Sucursal_Cuenta_Debito

	/** The tipo cuenta debito. */
	@Pattern(regexp = "\\d{2}")
	private String tipoCuentaDebito;

	/** The nro cuenta debito. */
	@Pattern(regexp = "\\d{7}")
	private String nroCuentaDebito;

	/** The tipo tarjeta. */
	@Pattern(regexp = "\\d{2}")
	private String tipoTarjeta;

	/** The sucursal cuenta tarjeta. */
	@Pattern(regexp = "\\d{4}")
	private String sucursalCuentaTarjeta;

	/** The nro tarjeta. */
	@Pattern(regexp = "\\d{16}")
	private String nroTarjeta;

	/** The codigo titularidad. */
	@Pattern(regexp = "[A-Z]{1}")
	private String codigoTitularidad;

	/** The nro cuenta tarjeta. */
	private String nroCuentaTarjeta;

	/** The modo pago TC. */
	// 0:EFECTIVO – 1:CHEQUE
	private String modoPagoTC = "2";

	/** 0: minimo; 1: total; 2: otro. */
	private String tipoPagoTC;

	/**
	 * 0: debito en el dia – siempre va al dia, sino es pago programado 1: al
	 * vencimiento.
	 */
	private String momentoPagoTC = "0";

	/**
	 * Si Tipo_Pago_TC = "0" o "1", se envíara vacio. Si PATJC.Tipo_Pago_TC =2
	 * (otro), enviar importe ingresado por el cliente en el campo otro. Debera
	 * ser mayor que cero y menor a 12 dígitos Si el pago es en ambas monedas
	 * (simultaneo) en este campo salo se envia el importe en pesos.
	 */
	private String importePagoTC;

	/** The codigo moneda. */
	private String codigoMoneda = " "; // (2)

	/** The nro sobre. */
	private String nroSobre = "00000"; // 00000 (5 Ceros)

	/** The cant cheques. */
	private String cantCheques = "00";// 00 (2 Ceros)

	/** The tipo cuenta banco dolares. */
	private String tipoCuentaBancoDolares;

	/** The sucursal banco dolares. */
	private String sucursalBancoDolares;

	/** The nro cuenta banco dolares. */
	private String nroCuentaBancoDolares;

	/** The importe dolares. */
	private String importeDolares;

	/** The fecha pago programado. */
	private String fechaPagoProgramado;

	/** The nro tarjeta formateado. */
	private String nroTarjetaFormateado;

	/** The stop debit. */
	private Boolean stopDebit;

	/** The total A pagar en dolares. */
	private String totalAPagarEnDolares;

	/** The total A pagar en pesos. */
	private String totalAPagarEnPesos;

	/** The importe pago dolares. */
	private String importePagoDolares;

	/** The importe pago pesos. */
	private String importePagoPesos;;

	/** The importe minimo. */
	private String importeMinimo;

	/** The es pago programado. */
	private Boolean esPagoProgramado;

	/** The es saldo a pagar. */
	private Boolean esSaldoAPagar = false;

	/** The cotizacion comprador. */
	private String cotizacionComprador;
	
	/** The cotizacion vendedor. */
	private String cotizacionVendedor;
	
	/** The saldo pendiente dolares. */
	private String saldoPendienteDolares;

	/** The saldo pendiente pesos. */
	private String saldoPendientePesos;

	/**
	 * Gets the fecha pago programado.
	 *
	 * @return the fecha pago programado
	 */
	public String getFechaPagoProgramado() {
		return fechaPagoProgramado;
	}

	/**
	 * Sets the fecha pago programado.
	 *
	 * @param fechaPagoProgramado
	 *            the new fecha pago programado
	 */
	public void setFechaPagoProgramado(String fechaPagoProgramado) {
		this.fechaPagoProgramado = fechaPagoProgramado;
	}

	/**
	 * Gets the sucursal cuenta debito.
	 *
	 * @return the sucursal cuenta debito
	 */
	public String getSucursalCuentaDebito() {
		return sucursalCuentaDebito;
	}

	/**
	 * Sets the sucursal cuenta debito.
	 *
	 * @param sucursalCuentaDebito
	 *            the new sucursal cuenta debito
	 */
	public void setSucursalCuentaDebito(String sucursalCuentaDebito) {
		this.sucursalCuentaDebito = sucursalCuentaDebito;
	}

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipo cuenta debito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the new tipo cuenta debito
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the nro cuenta debito.
	 *
	 * @return the nro cuenta debito
	 */
	public String getNroCuentaDebito() {
		return nroCuentaDebito;
	}

	/**
	 * Sets the nro cuenta debito.
	 *
	 * @param nroCuentaDebito
	 *            the new nro cuenta debito
	 */
	public void setNroCuentaDebito(String nroCuentaDebito) {
		this.nroCuentaDebito = nroCuentaDebito;
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

	/**
	 * Gets the sucursal cuenta tarjeta.
	 *
	 * @return the sucursal cuenta tarjeta
	 */
	public String getSucursalCuentaTarjeta() {
		return sucursalCuentaTarjeta;
	}

	/**
	 * Sets the sucursal cuenta tarjeta.
	 *
	 * @param sucursalCuentaTarjeta
	 *            the new sucursal cuenta tarjeta
	 */
	public void setSucursalCuentaTarjeta(String sucursalCuentaTarjeta) {
		this.sucursalCuentaTarjeta = StringUtils.leftPad(sucursalCuentaTarjeta, 4, "0");
	}

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return the nro tarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the new nro tarjeta
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	/**
	 * Gets the codigo titularidad.
	 *
	 * @return the codigo titularidad
	 */
	public String getCodigoTitularidad() {
		return codigoTitularidad;
	}

	/**
	 * Sets the codigo titularidad.
	 *
	 * @param codigoTitularidad
	 *            the new codigo titularidad
	 */
	public void setCodigoTitularidad(String codigoTitularidad) {
		this.codigoTitularidad = codigoTitularidad;
	}

	/**
	 * Gets the nro cuenta tarjeta.
	 *
	 * @return the nro cuenta tarjeta
	 */
	public String getNroCuentaTarjeta() {
		return nroCuentaTarjeta;
	}

	/**
	 * Sets the nro cuenta tarjeta.
	 *
	 * @param nroCuentaTarjeta
	 *            the new nro cuenta tarjeta
	 */
	public void setNroCuentaTarjeta(String nroCuentaTarjeta) {
		this.nroCuentaTarjeta = nroCuentaTarjeta;
	}

	/**
	 * Gets the modo pago TC.
	 *
	 * @return the modo pago TC
	 */
	public String getModoPagoTC() {
		return modoPagoTC;
	}

	/**
	 * Sets the modo pago TC.
	 *
	 * @param modoPagoTC
	 *            the new modo pago TC
	 */
	public void setModoPagoTC(String modoPagoTC) {
		this.modoPagoTC = modoPagoTC;
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
	 * Gets the momento pago TC.
	 *
	 * @return the momento pago TC
	 */
	public String getMomentoPagoTC() {
		return momentoPagoTC;
	}

	/**
	 * Sets the momento pago TC.
	 *
	 * @param momentoPagoTC
	 *            the new momento pago TC
	 */
	public void setMomentoPagoTC(String momentoPagoTC) {
		this.momentoPagoTC = momentoPagoTC;
	}

	/**
	 * Gets the importe pago TC.
	 *
	 * @return the importe pago TC
	 */
	public String getImportePagoTC() {
		return importePagoTC;
	}

	/**
	 * Sets the importe pago TC.
	 *
	 * @param importePagoTC
	 *            the new importe pago TC
	 */
	public void setImportePagoTC(String importePagoTC) {
		this.importePagoTC = importePagoTC;
	}

	/**
	 * Gets the codigo moneda.
	 *
	 * @return the codigo moneda
	 */
	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	/**
	 * Sets the codigo moneda.
	 *
	 * @param codigoMoneda
	 *            the new codigo moneda
	 */
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	/**
	 * Gets the nro sobre.
	 *
	 * @return the nro sobre
	 */
	public String getNroSobre() {
		return nroSobre;
	}

	/**
	 * Sets the nro sobre.
	 *
	 * @param nroSobre
	 *            the new nro sobre
	 */
	public void setNroSobre(String nroSobre) {
		this.nroSobre = nroSobre;
	}

	/**
	 * Gets the cant cheques.
	 *
	 * @return the cant cheques
	 */
	public String getCantCheques() {
		return cantCheques;
	}

	/**
	 * Sets the cant cheques.
	 *
	 * @param cantCheques
	 *            the new cant cheques
	 */
	public void setCantCheques(String cantCheques) {
		this.cantCheques = cantCheques;
	}

	/**
	 * Gets the tipo cuenta banco dolares.
	 *
	 * @return the tipo cuenta banco dolares
	 */
	public String getTipoCuentaBancoDolares() {
		return tipoCuentaBancoDolares;
	}

	/**
	 * Sets the tipo cuenta banco dolares.
	 *
	 * @param tipoCuentaBancoDolares
	 *            the new tipo cuenta banco dolares
	 */
	public void setTipoCuentaBancoDolares(String tipoCuentaBancoDolares) {
		this.tipoCuentaBancoDolares = tipoCuentaBancoDolares;
	}

	/**
	 * Gets the sucursal banco dolares.
	 *
	 * @return the sucursal banco dolares
	 */
	public String getSucursalBancoDolares() {
		return sucursalBancoDolares;
	}

	/**
	 * Sets the sucursal banco dolares.
	 *
	 * @param sucursalBancoDolares
	 *            the new sucursal banco dolares
	 */
	public void setSucursalBancoDolares(String sucursalBancoDolares) {
		this.sucursalBancoDolares = sucursalBancoDolares;
	}

	/**
	 * Gets the nro cuenta banco dolares.
	 *
	 * @return the nro cuenta banco dolares
	 */
	public String getNroCuentaBancoDolares() {
		return nroCuentaBancoDolares;
	}

	/**
	 * Sets the nro cuenta banco dolares.
	 *
	 * @param nroCuentaBancoDolares
	 *            the new nro cuenta banco dolares
	 */
	public void setNroCuentaBancoDolares(String nroCuentaBancoDolares) {
		this.nroCuentaBancoDolares = nroCuentaBancoDolares;
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
	 * Gets the nro tarjeta formateado.
	 *
	 * @return the nro tarjeta formateado
	 */
	public String getNroTarjetaFormateado() {
		return nroTarjetaFormateado;
	}

	/**
	 * Sets the nro tarjeta formateado.
	 *
	 * @param nroTarjetaFormateado
	 *            the new nro tarjeta formateado
	 */
	public void setNroTarjetaFormateado(String nroTarjetaFormateado) {
		this.nroTarjetaFormateado = nroTarjetaFormateado;
	}

	/**
	 * Gets the stop debit.
	 *
	 * @return the stop debit
	 */
	public Boolean getStopDebit() {
		return stopDebit;
	}

	/**
	 * Sets the stop debit.
	 *
	 * @param stopDebit
	 *            the new stop debit
	 */
	public void setStopDebit(Boolean stopDebit) {
		this.stopDebit = stopDebit;
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
	 * Gets the importe pago dolares.
	 *
	 * @return the importe pago dolares
	 */
	public String getImportePagoDolares() {
		return importePagoDolares;
	}

	/**
	 * Sets the importe pago dolares.
	 *
	 * @param importePagoDolares
	 *            the new importe pago dolares
	 */
	public void setImportePagoDolares(String importePagoDolares) {
		this.importePagoDolares = importePagoDolares;
	}

	/**
	 * Gets the importe pago pesos.
	 *
	 * @return the importe pago pesos
	 */
	public String getImportePagoPesos() {
		return importePagoPesos;
	}

	/**
	 * Sets the importe pago pesos.
	 *
	 * @param importePagoPesos
	 *            the new importe pago pesos
	 */
	public void setImportePagoPesos(String importePagoPesos) {
		this.importePagoPesos = importePagoPesos;
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
	 * Gets the pago programado.
	 *
	 * @return the pago programado
	 */
	public Boolean getPagoProgramado() {
		return esPagoProgramado;
	}

	/**
	 * Sets the pago programado.
	 *
	 * @param pagoProgramado
	 *            the new pago programado
	 */
	public void setPagoProgramado(Boolean pagoProgramado) {
		this.esPagoProgramado = pagoProgramado;
	}

	/**
	 * Gets the es saldo A pagar.
	 *
	 * @return the es saldo A pagar
	 */
	public Boolean getEsSaldoAPagar() {
		return esSaldoAPagar;
	}

	/**
	 * Sets the es saldo A pagar.
	 *
	 * @param esSaldoAPagar
	 *            the new es saldo A pagar
	 */
	public void setEsSaldoAPagar(Boolean esSaldoAPagar) {
		this.esSaldoAPagar = esSaldoAPagar;
	}

	/**
	 * Gets the cotizacion comprador.
	 *
	 * @return the cotizacion comprador
	 */
	public String getCotizacionComprador() {
		return cotizacionComprador;
	}

	/**
	 * Sets the cotizacion comprador.
	 *
	 * @param cotizacionComprador the new cotizacion comprador
	 */
	public void setCotizacionComprador(String cotizacionComprador) {
		this.cotizacionComprador = cotizacionComprador;
	}

	/**
	 * Gets the cotizacion vendedor.
	 *
	 * @return the cotizacion vendedor
	 */
	public String getCotizacionVendedor() {
		return cotizacionVendedor;
	}

	/**
	 * Sets the cotizacion vendedor.
	 *
	 * @param cotizacionVendedor the new cotizacion vendedor
	 */
	public void setCotizacionVendedor(String cotizacionVendedor) {
		this.cotizacionVendedor = cotizacionVendedor;
	}

	/**
	 * Gets the saldo pendiente dolares.
	 *
	 * @return the saldo pendiente dolares
	 */
	public String getSaldoPendienteDolares() {
		return saldoPendienteDolares;
	}

	/**
	 * Sets the saldo pendiente dolares.
	 *
	 * @param saldoPendienteDolares the new saldo pendiente dolares
	 */
	public void setSaldoPendienteDolares(String saldoPendienteDolares) {
		this.saldoPendienteDolares = saldoPendienteDolares;
	}

	/**
	 * Gets the saldo pendiente pesos.
	 *
	 * @return the saldo pendiente pesos
	 */
	public String getSaldoPendientePesos() {
		return saldoPendientePesos;
	}

	/**
	 * Sets the saldo pendiente pesos.
	 *
	 * @param saldoPendientePesos the new saldo pendiente pesos
	 */
	public void setSaldoPendientePesos(String saldoPendientePesos) {
		this.saldoPendientePesos = saldoPendientePesos;
	}

	@Override
	public String toString() {
		return "DatosPagoTC [sucursalCuentaDebito=" + sucursalCuentaDebito + ", tipoCuentaDebito=" + tipoCuentaDebito
				+ ", nroCuentaDebito=" + nroCuentaDebito + ", tipoTarjeta=" + tipoTarjeta + ", sucursalCuentaTarjeta="
				+ sucursalCuentaTarjeta + ", nroTarjeta=" + nroTarjeta + ", codigoTitularidad=" + codigoTitularidad
				+ ", nroCuentaTarjeta=" + nroCuentaTarjeta + ", modoPagoTC=" + modoPagoTC + ", tipoPagoTC=" + tipoPagoTC
				+ ", momentoPagoTC=" + momentoPagoTC + ", importePagoTC=" + importePagoTC + ", codigoMoneda="
				+ codigoMoneda + ", nroSobre=" + nroSobre + ", cantCheques=" + cantCheques + ", tipoCuentaBancoDolares="
				+ tipoCuentaBancoDolares + ", sucursalBancoDolares=" + sucursalBancoDolares + ", nroCuentaBancoDolares="
				+ nroCuentaBancoDolares + ", importeDolares=" + importeDolares + ", fechaPagoProgramado="
				+ fechaPagoProgramado + ", nroTarjetaFormateado=" + nroTarjetaFormateado + ", stopDebit=" + stopDebit
				+ ", totalAPagarEnDolares=" + totalAPagarEnDolares + ", totalAPagarEnPesos=" + totalAPagarEnPesos
				+ ", importePagoDolares=" + importePagoDolares + ", importePagoPesos=" + importePagoPesos
				+ ", importeMinimo=" + importeMinimo + ", esPagoProgramado=" + esPagoProgramado + ", esSaldoAPagar=" + esSaldoAPagar
				+ ", cotizacionComprador=" + cotizacionComprador + ", cotizacionVendedor=" + cotizacionVendedor
				+ ", saldoPendienteDolares=" + saldoPendienteDolares + ", saldoPendientePesos=" + saldoPendientePesos + "]";
	}

}
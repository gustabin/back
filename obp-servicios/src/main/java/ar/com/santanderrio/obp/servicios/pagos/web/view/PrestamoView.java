/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;

/**
 * The Class PrestamoView.
 */
public class PrestamoView {

	/** The nro prestamo. */
	private String nroPrestamo;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The cuenta relacionada. */
	private String cuentaRelacionada;

	/** The vencimiento cuota. */
	private Date vencimientoCuota;

	/** The numero recibo. */
	private String numeroRecibo;

	/** The importe amortizacion. */
	private String importeAmortizacion;

	/** The importe intereses. */
	private String importeIntereses;

	/** The comisiones pendientes. */
	private String comisionesPendientes;

	/** The gastos pendientes. */
	private String gastosPendientes;

	/** The importe total seguros. */
	private String importeTotalSeguros;

	/** The impuestos pendientes. */
	private String impuestosPendientes;

	/** The importe cobranzas pendientes. */
	private String importeCobranzasPendientes;

	/** The importes punitorios. */
	private String importesPunitorios;

	/** The importe complementarios. */
	private String importeComplementarios;

	/** The interes compensatorio pendiente. */
	private String interesCompensatorioPendiente;

	/** The tasa prestamo. */
	private String tasaPrestamo;

	/** The importe seguro vida. */
	private String importeSeguroVida;

	/** The importe seguro del bien. */
	private String importeSeguroDelBien;

	/** The importe IVA. */
	private String importeIVA;

	/** The importe IVA adicional. */
	private String importeIVAAdicional;

	/** The importe endeudamiento. */
	private String importeEndeudamiento;

	/** The ingresos brutos. */
	private String ingresosBrutos;

	/** The otros impuestos. */
	private String otrosImpuestos;

	/** The importe total cuota. */
	private BigDecimal importeTotalCuota;

	/** The divisa. */
	private DivisaEnum divisa;

	/** The saldo previo. */
	private String saldoPrevio;

	/** The factor index. */
	private String factorIndex;

	/** The importe ajussal. */
	private String importeAjussal;

	/** The importe ajuscap. */
	private String importeAjuscap;

	/** The importe ajuste capmor. */
	private String importeAjusteCapmor;

	/** The factor capmor. */
	private String factorCapmor;

	/** The index. */
	private String index;

	/** The rendicion seguro vida. */
	private String rendicionSeguroVida;

	/** The tasa anual efectiva. */
	private String tasaAnualEfectiva;

	/** The costo financiero total. */
	private String costoFinancieroTotal;

	/** The clase cuenta. */
	private String claseCuenta;

	/** The numero cuenta producto. */
	private String numeroCuentaProducto;

	/** The costo financiero total sin impuesto. */
	private String costoFinancieroTotalSinImpuesto;

	/** The comprobante. */
	private ComprobanteView comprobante;

	/**
	 * Gets the nro prestamo.
	 *
	 * @return the nro prestamo
	 */
	public String getNroPrestamo() {
		return nroPrestamo;
	}

	/**
	 * Sets the nro prestamo.
	 *
	 * @param nroPrestamo
	 *            the new nro prestamo
	 */
	public void setNroPrestamo(String nroPrestamo) {
		this.nroPrestamo = nroPrestamo;
	}

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public ComprobanteView getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante
	 *            the new comprobante
	 */
	public void setComprobante(ComprobanteView comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * Gets the cuenta relacionada.
	 *
	 * @return the cuentaRelacionada
	 */

	public String getCuentaRelacionada() {
		return cuentaRelacionada;
	}

	/**
	 * Sets the cuenta relacionada.
	 *
	 * @param cuentaRelacionada
	 *            the cuentaRelacionada to set
	 */
	public void setCuentaRelacionada(String cuentaRelacionada) {
		this.cuentaRelacionada = cuentaRelacionada;
	}

	/**
	 * Gets the vencimiento cuota.
	 *
	 * @return the vencimientoCuota
	 */
	public Date getVencimientoCuota() {
		return vencimientoCuota == null ? null : new Date(vencimientoCuota.getTime());
	}

	/**
	 * Sets the vencimiento cuota.
	 *
	 * @param vencimientoCuota
	 *            the vencimientoCuota to set
	 */
	public void setVencimientoCuota(Date vencimientoCuota) {
		this.vencimientoCuota = vencimientoCuota == null ? null : new Date(vencimientoCuota.getTime());
	}

	/**
	 * Gets the numero recibo.
	 *
	 * @return the numeroRecibo
	 */
	public String getNumeroRecibo() {
		return numeroRecibo;
	}

	/**
	 * Sets the numero recibo.
	 *
	 * @param numeroRecibo
	 *            the numeroRecibo to set
	 */
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}

	/**
	 * Gets the importe amortizacion.
	 *
	 * @return the importeAmortizacion
	 */
	public String getImporteAmortizacion() {
		return importeAmortizacion;
	}

	/**
	 * Sets the importe amortizacion.
	 *
	 * @param importeAmortizacion
	 *            the importeAmortizacion to set
	 */
	public void setImporteAmortizacion(String importeAmortizacion) {
		this.importeAmortizacion = importeAmortizacion;
	}

	/**
	 * Gets the importe intereses.
	 *
	 * @return the importeIntereses
	 */
	public String getImporteIntereses() {
		return importeIntereses;
	}

	/**
	 * Sets the importe intereses.
	 *
	 * @param importeIntereses
	 *            the importeIntereses to set
	 */
	public void setImporteIntereses(String importeIntereses) {
		this.importeIntereses = importeIntereses;
	}

	/**
	 * Gets the comisiones pendientes.
	 *
	 * @return the comisionesPendientes
	 */
	public String getComisionesPendientes() {
		return comisionesPendientes;
	}

	/**
	 * Sets the comisiones pendientes.
	 *
	 * @param comisionesPendientes
	 *            the comisionesPendientes to set
	 */
	public void setComisionesPendientes(String comisionesPendientes) {
		this.comisionesPendientes = comisionesPendientes;
	}

	/**
	 * Gets the gastos pendientes.
	 *
	 * @return the gastosPendientes
	 */
	public String getGastosPendientes() {
		return gastosPendientes;
	}

	/**
	 * Sets the gastos pendientes.
	 *
	 * @param gastosPendientes
	 *            the gastosPendientes to set
	 */
	public void setGastosPendientes(String gastosPendientes) {
		this.gastosPendientes = gastosPendientes;
	}

	/**
	 * Gets the importe total seguros.
	 *
	 * @return the importeTotalSeguros
	 */
	public String getImporteTotalSeguros() {
		return importeTotalSeguros;
	}

	/**
	 * Sets the importe total seguros.
	 *
	 * @param importeTotalSeguros
	 *            the importeTotalSeguros to set
	 */
	public void setImporteTotalSeguros(String importeTotalSeguros) {
		this.importeTotalSeguros = importeTotalSeguros;
	}

	/**
	 * Gets the impuestos pendientes.
	 *
	 * @return the impuestosPendientes
	 */
	public String getImpuestosPendientes() {
		return impuestosPendientes;
	}

	/**
	 * Sets the impuestos pendientes.
	 *
	 * @param impuestosPendientes
	 *            the impuestosPendientes to set
	 */
	public void setImpuestosPendientes(String impuestosPendientes) {
		this.impuestosPendientes = impuestosPendientes;
	}

	/**
	 * Gets the importe cobranzas pendientes.
	 *
	 * @return the importeCobranzasPendientes
	 */
	public String getImporteCobranzasPendientes() {
		return importeCobranzasPendientes;
	}

	/**
	 * Sets the importe cobranzas pendientes.
	 *
	 * @param importeCobranzasPendientes
	 *            the importeCobranzasPendientes to set
	 */
	public void setImporteCobranzasPendientes(String importeCobranzasPendientes) {
		this.importeCobranzasPendientes = importeCobranzasPendientes;
	}

	/**
	 * Gets the importes punitorios.
	 *
	 * @return the importesPunitorios
	 */
	public String getImportesPunitorios() {
		return importesPunitorios;
	}

	/**
	 * Sets the importes punitorios.
	 *
	 * @param importesPunitorios
	 *            the importesPunitorios to set
	 */
	public void setImportesPunitorios(String importesPunitorios) {
		this.importesPunitorios = importesPunitorios;
	}

	/**
	 * Gets the importe complementarios.
	 *
	 * @return the importesComplementarios
	 */
	public String getImporteComplementarios() {
		return importeComplementarios;
	}

	/**
	 * Sets the importe complementarios.
	 *
	 * @param importesComplementarios
	 *            the importesComplementarios to set
	 */
	public void setImporteComplementarios(String importesComplementarios) {
		this.importeComplementarios = importesComplementarios;
	}

	/**
	 * Gets the interes compensatorio pendiente.
	 *
	 * @return the interesCompensatorioPendiente
	 */
	public String getInteresCompensatorioPendiente() {
		return interesCompensatorioPendiente;
	}

	/**
	 * Sets the interes compensatorio pendiente.
	 *
	 * @param interesCompensatorioPendiente
	 *            the interesCompensatorioPendiente to set
	 */
	public void setInteresCompensatorioPendiente(String interesCompensatorioPendiente) {
		this.interesCompensatorioPendiente = interesCompensatorioPendiente;
	}

	/**
	 * Gets the tasa prestamo.
	 *
	 * @return the tasaPrestamo
	 */
	public String getTasaPrestamo() {
		return tasaPrestamo;
	}

	/**
	 * Sets the tasa prestamo.
	 *
	 * @param tasaPrestamo
	 *            the tasaPrestamo to set
	 */
	public void setTasaPrestamo(String tasaPrestamo) {
		this.tasaPrestamo = tasaPrestamo;
	}

	/**
	 * Gets the importe seguro vida.
	 *
	 * @return the importeSeguroVida
	 */
	public String getImporteSeguroVida() {
		return importeSeguroVida;
	}

	/**
	 * Sets the importe seguro vida.
	 *
	 * @param importeSeguroVida
	 *            the importeSeguroVida to set
	 */
	public void setImporteSeguroVida(String importeSeguroVida) {
		this.importeSeguroVida = importeSeguroVida;
	}

	/**
	 * Gets the importe seguro del bien.
	 *
	 * @return the importeSeguroDelBien
	 */
	public String getImporteSeguroDelBien() {
		return importeSeguroDelBien;
	}

	/**
	 * Sets the importe seguro del bien.
	 *
	 * @param importeSeguroDelBien
	 *            the importeSeguroDelBien to set
	 */
	public void setImporteSeguroDelBien(String importeSeguroDelBien) {
		this.importeSeguroDelBien = importeSeguroDelBien;
	}

	/**
	 * Gets the importe IVA.
	 *
	 * @return the importeIVA
	 */
	public String getImporteIVA() {
		return importeIVA;
	}

	/**
	 * Sets the importe IVA.
	 *
	 * @param importeIVA
	 *            the importeIVA to set
	 */
	public void setImporteIVA(String importeIVA) {
		this.importeIVA = importeIVA;
	}

	/**
	 * Gets the importe IVA adicional.
	 *
	 * @return the importeIVAAdicional
	 */
	public String getImporteIVAAdicional() {
		return importeIVAAdicional;
	}

	/**
	 * Sets the importe IVA adicional.
	 *
	 * @param importeIVAAdicional
	 *            the importeIVAAdicional to set
	 */
	public void setImporteIVAAdicional(String importeIVAAdicional) {
		this.importeIVAAdicional = importeIVAAdicional;
	}

	/**
	 * Gets the importe endeudamiento.
	 *
	 * @return the importeEndeudamiento
	 */
	public String getImporteEndeudamiento() {
		return importeEndeudamiento;
	}

	/**
	 * Sets the importe endeudamiento.
	 *
	 * @param importeEndeudamiento
	 *            the importeEndeudamiento to set
	 */
	public void setImporteEndeudamiento(String importeEndeudamiento) {
		this.importeEndeudamiento = importeEndeudamiento;
	}

	/**
	 * Gets the ingresos brutos.
	 *
	 * @return the ingresosBrutos
	 */
	public String getIngresosBrutos() {
		return ingresosBrutos;
	}

	/**
	 * Sets the ingresos brutos.
	 *
	 * @param ingresosBrutos
	 *            the ingresosBrutos to set
	 */
	public void setIngresosBrutos(String ingresosBrutos) {
		this.ingresosBrutos = ingresosBrutos;
	}

	/**
	 * Gets the otros impuestos.
	 *
	 * @return the otrosImpuestos
	 */
	public String getOtrosImpuestos() {
		return otrosImpuestos;
	}

	/**
	 * Sets the otros impuestos.
	 *
	 * @param otrosImpuestos
	 *            the otrosImpuestos to set
	 */
	public void setOtrosImpuestos(String otrosImpuestos) {
		this.otrosImpuestos = otrosImpuestos;
	}

	/**
	 * Gets the importe total cuota.
	 *
	 * @return the importeTotalCuota
	 */
	public BigDecimal getImporteTotalCuota() {
		return importeTotalCuota;
	}

	/**
	 * Sets the importe total cuota.
	 *
	 * @param importeTotalCuota
	 *            the importeTotalCuota to set
	 */
	public void setImporteTotalCuota(BigDecimal importeTotalCuota) {
		this.importeTotalCuota = importeTotalCuota;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public DivisaEnum getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the divisa to set
	 */
	public void setDivisa(DivisaEnum divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the saldo previo.
	 *
	 * @return the saldoPrevio
	 */
	public String getSaldoPrevio() {
		return saldoPrevio;
	}

	/**
	 * Sets the saldo previo.
	 *
	 * @param saldoPrevio
	 *            the saldoPrevio to set
	 */
	public void setSaldoPrevio(String saldoPrevio) {
		this.saldoPrevio = saldoPrevio;
	}

	/**
	 * Gets the factor index.
	 *
	 * @return the factorIndex
	 */
	public String getFactorIndex() {
		return factorIndex;
	}

	/**
	 * Sets the factor index.
	 *
	 * @param factorIndex
	 *            the factorIndex to set
	 */
	public void setFactorIndex(String factorIndex) {
		this.factorIndex = factorIndex;
	}

	/**
	 * Gets the importe ajussal.
	 *
	 * @return the importeAjussal
	 */
	public String getImporteAjussal() {
		return importeAjussal;
	}

	/**
	 * Sets the importe ajussal.
	 *
	 * @param importeAjussal
	 *            the importeAjussal to set
	 */
	public void setImporteAjussal(String importeAjussal) {
		this.importeAjussal = importeAjussal;
	}

	/**
	 * Gets the importe ajuscap.
	 *
	 * @return the importeAjuscap
	 */
	public String getImporteAjuscap() {
		return importeAjuscap;
	}

	/**
	 * Sets the importe ajuscap.
	 *
	 * @param importeAjuscap
	 *            the importeAjuscap to set
	 */
	public void setImporteAjuscap(String importeAjuscap) {
		this.importeAjuscap = importeAjuscap;
	}

	/**
	 * Gets the importe ajuste capmor.
	 *
	 * @return the importeAjusteCapmor
	 */
	public String getImporteAjusteCapmor() {
		return importeAjusteCapmor;
	}

	/**
	 * Sets the importe ajuste capmor.
	 *
	 * @param importeAjusteCapmor
	 *            the importeAjusteCapmor to set
	 */
	public void setImporteAjusteCapmor(String importeAjusteCapmor) {
		this.importeAjusteCapmor = importeAjusteCapmor;
	}

	/**
	 * Gets the factor capmor.
	 *
	 * @return the factorCapmor
	 */
	public String getFactorCapmor() {
		return factorCapmor;
	}

	/**
	 * Sets the factor capmor.
	 *
	 * @param factorCapmor
	 *            the factorCapmor to set
	 */
	public void setFactorCapmor(String factorCapmor) {
		this.factorCapmor = factorCapmor;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * Sets the index.
	 *
	 * @param index
	 *            the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * Gets the rendicion seguro vida.
	 *
	 * @return the rendicionSeguroVida
	 */
	public String getRendicionSeguroVida() {
		return rendicionSeguroVida;
	}

	/**
	 * Sets the rendicion seguro vida.
	 *
	 * @param rendicionSeguroVida
	 *            the rendicionSeguroVida to set
	 */
	public void setRendicionSeguroVida(String rendicionSeguroVida) {
		this.rendicionSeguroVida = rendicionSeguroVida;
	}

	/**
	 * Gets the tasa anual efectiva.
	 *
	 * @return the tasaAnualEfectiva
	 */
	public String getTasaAnualEfectiva() {
		return tasaAnualEfectiva;
	}

	/**
	 * Sets the tasa anual efectiva.
	 *
	 * @param tasaAnualEfectiva
	 *            the tasaAnualEfectiva to set
	 */
	public void setTasaAnualEfectiva(String tasaAnualEfectiva) {
		this.tasaAnualEfectiva = tasaAnualEfectiva;
	}

	/**
	 * Gets the costo financiero total.
	 *
	 * @return the costoFinancieroTotal
	 */
	public String getCostoFinancieroTotal() {
		return costoFinancieroTotal;
	}

	/**
	 * Sets the costo financiero total.
	 *
	 * @param costoFinancieroTotal
	 *            the costoFinancieroTotal to set
	 */
	public void setCostoFinancieroTotal(String costoFinancieroTotal) {
		this.costoFinancieroTotal = costoFinancieroTotal;
	}

	/**
	 * Gets the clase cuenta.
	 *
	 * @return the claseCuenta
	 */
	public String getClaseCuenta() {
		return claseCuenta;
	}

	/**
	 * Sets the clase cuenta.
	 *
	 * @param claseCuenta
	 *            the claseCuenta to set
	 */
	public void setClaseCuenta(String claseCuenta) {
		this.claseCuenta = claseCuenta;
	}

	/**
	 * Gets the numero cuenta producto.
	 *
	 * @return the numeroCuentaProducto
	 */
	public String getNumeroCuentaProducto() {
		return numeroCuentaProducto;
	}

	/**
	 * Sets the numero cuenta producto.
	 *
	 * @param numeroCuentaProducto
	 *            the numeroCuentaProducto to set
	 */
	public void setNumeroCuentaProducto(String numeroCuentaProducto) {
		this.numeroCuentaProducto = numeroCuentaProducto;
	}

	/**
	 * Gets the costo financiero total sin impuesto.
	 *
	 * @return the costo financiero total sin impuesto
	 */
	public String getCostoFinancieroTotalSinImpuesto() {
		return costoFinancieroTotalSinImpuesto;
	}

	/**
	 * Sets the costo financiero total sin impuesto.
	 *
	 * @param costoFinancieroTotalSinImpuesto
	 *            the new costo financiero total sin impuesto
	 */
	public void setCostoFinancieroTotalSinImpuesto(String costoFinancieroTotalSinImpuesto) {
		this.costoFinancieroTotalSinImpuesto = costoFinancieroTotalSinImpuesto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((claseCuenta == null) ? 0 : claseCuenta.hashCode());
		result = prime * result + ((comisionesPendientes == null) ? 0 : comisionesPendientes.hashCode());
		result = prime * result + ((cuentaRelacionada == null) ? 0 : cuentaRelacionada.hashCode());
		result = prime * result + ((divisa == null) ? 0 : divisa.hashCode());
		result = prime * result + ((numeroCuentaProducto == null) ? 0 : numeroCuentaProducto.hashCode());
		result = prime * result + ((numeroRecibo == null) ? 0 : numeroRecibo.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrestamoView other = (PrestamoView) obj;
		if (claseCuenta == null) {
			if (other.claseCuenta != null)
				return false;
		} else if (!claseCuenta.equals(other.claseCuenta))
			return false;
		if (comisionesPendientes == null) {
			if (other.comisionesPendientes != null)
				return false;
		} else if (!comisionesPendientes.equals(other.comisionesPendientes))
			return false;
		if (cuentaRelacionada == null) {
			if (other.cuentaRelacionada != null)
				return false;
		} else if (!cuentaRelacionada.equals(other.cuentaRelacionada))
			return false;
		if (divisa != other.divisa)
			return false;
		if (numeroCuentaProducto == null) {
			if (other.numeroCuentaProducto != null)
				return false;
		} else if (!numeroCuentaProducto.equals(other.numeroCuentaProducto))
			return false;
		if (numeroRecibo == null) {
			if (other.numeroRecibo != null)
				return false;
		} else if (!numeroRecibo.equals(other.numeroRecibo))
			return false;
		return true;
	}

}

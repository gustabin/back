/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dto;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;

/**
 * * PrestamoOpenCredit.
 *
 * @author Silvina_Luque
 */
public class PrestamoOpenCreditDTO {

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The vencimiento cuota. */
	private Date vencimientoCuota;

	/** The descripcion. */
	private String descripcion;

	/** The cuenta. */
	private Cuenta cuenta;

	/** The linea credito total. */
	private BigDecimal lineaCreditoTotal;

	/** The disponible para uso. */
	private BigDecimal disponibleParaUso;

	/** The pago minimo. */
	private BigDecimal pagoMinimo;

	/** The tasa TNA. */
	private BigDecimal tasaTNA;

	/** The int compensatorios periodo. */
	private BigDecimal intCompensatoriosPeriodo;

	/** The int compensatorios post vencimiento. */
	private BigDecimal intCompensatoriosPostVencimiento;

	/** The intereses punitorios. */
	private BigDecimal interesesPunitorios;

	/** The iva. */
	private BigDecimal iva;

	/** The ingresos brutos. */
	private BigDecimal ingresosBrutos;

	/** The otros impuestos. */
	private BigDecimal otrosImpuestos;

	/** The divisa. */
	private DivisaEnum divisa;

	/** The numero recibo. */
	private int numeroRecibo;

	/** The fecha inicio. */
	private String fechaInicio;
	
	/** The capital. */
	private BigDecimal capital;
	
	/** The tasaTEA. */
	private BigDecimal tasaTEA;
	
	/** The cftConImp. */
	private BigDecimal cftConImp;
	
	/** The cftSinImp. */
	private BigDecimal cftSinImp;

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio
	 *            the new fecha inicio
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the new fecha vencimiento
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the linea credito total.
	 *
	 * @return the linea credito total
	 */
	public BigDecimal getLineaCreditoTotal() {
		return lineaCreditoTotal;
	}

	/**
	 * Sets the linea credito total.
	 *
	 * @param lineaCreditoTotal
	 *            the new linea credito total
	 */
	public void setLineaCreditoTotal(BigDecimal lineaCreditoTotal) {
		this.lineaCreditoTotal = lineaCreditoTotal;
	}

	/**
	 * Gets the disponible para uso.
	 *
	 * @return the disponible para uso
	 */
	public BigDecimal getDisponibleParaUso() {
		return disponibleParaUso;
	}

	/**
	 * Sets the disponible para uso.
	 *
	 * @param disponibleParaUso
	 *            the new disponible para uso
	 */
	public void setDisponibleParaUso(BigDecimal disponibleParaUso) {
		this.disponibleParaUso = disponibleParaUso;
	}

	/**
	 * Gets the pago minimo.
	 *
	 * @return the pago minimo
	 */
	public BigDecimal getPagoMinimo() {
		return pagoMinimo;
	}

	/**
	 * Sets the pago minimo.
	 *
	 * @param pagoMinimo
	 *            the new pago minimo
	 */
	public void setPagoMinimo(BigDecimal pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}

	/**
	 * Gets the vencimiento cuota.
	 *
	 * @return the vencimiento cuota
	 */
	public Date getVencimientoCuota() {
		return vencimientoCuota;
	}

	/**
	 * Sets the vencimiento cuota.
	 *
	 * @param vencimientoCuota
	 *            the new vencimiento cuota
	 */
	public void setVencimientoCuota(Date vencimientoCuota) {
		this.vencimientoCuota = vencimientoCuota;
	}

	/**
	 * Gets the tasa TNA.
	 *
	 * @return the tasa TNA
	 */
	public BigDecimal getTasaTNA() {
		return tasaTNA;
	}

	/**
	 * Sets the tasa TNA.
	 *
	 * @param tasaTNA
	 *            the new tasa TNA
	 */
	public void setTasaTNA(BigDecimal tasaTNA) {
		this.tasaTNA = tasaTNA;
	}

	/**
	 * Gets the int compensatorios periodo.
	 *
	 * @return the int compensatorios periodo
	 */
	public BigDecimal getIntCompensatoriosPeriodo() {
		return intCompensatoriosPeriodo;
	}

	/**
	 * Sets the int compensatorios periodo.
	 *
	 * @param intCompensatoriosPeriodo
	 *            the new int compensatorios periodo
	 */
	public void setIntCompensatoriosPeriodo(BigDecimal intCompensatoriosPeriodo) {
		this.intCompensatoriosPeriodo = intCompensatoriosPeriodo;
	}

	/**
	 * Gets the int compensatorios post vencimiento.
	 *
	 * @return the int compensatorios post vencimiento
	 */
	public BigDecimal getIntCompensatoriosPostVencimiento() {
		return intCompensatoriosPostVencimiento;
	}

	/**
	 * Sets the int compensatorios post vencimiento.
	 *
	 * @param intCompensatoriosPostVencimiento
	 *            the new int compensatorios post vencimiento
	 */
	public void setIntCompensatoriosPostVencimiento(BigDecimal intCompensatoriosPostVencimiento) {
		this.intCompensatoriosPostVencimiento = intCompensatoriosPostVencimiento;
	}

	/**
	 * Gets the intereses punitorios.
	 *
	 * @return the intereses punitorios
	 */
	public BigDecimal getInteresesPunitorios() {
		return interesesPunitorios;
	}

	/**
	 * Sets the intereses punitorios.
	 *
	 * @param interesesPunitorios
	 *            the new intereses punitorios
	 */
	public void setInteresesPunitorios(BigDecimal interesesPunitorios) {
		this.interesesPunitorios = interesesPunitorios;
	}

	/**
	 * Gets the iva.
	 *
	 * @return the iva
	 */
	public BigDecimal getIva() {
		return iva;
	}

	/**
	 * Sets the iva.
	 *
	 * @param iva
	 *            the new iva
	 */
	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	/**
	 * Gets the ingresos brutos.
	 *
	 * @return the ingresos brutos
	 */
	public BigDecimal getIngresosBrutos() {
		return ingresosBrutos;
	}

	/**
	 * Sets the ingresos brutos.
	 *
	 * @param ingresosBrutos
	 *            the new ingresos brutos
	 */
	public void setIngresosBrutos(BigDecimal ingresosBrutos) {
		this.ingresosBrutos = ingresosBrutos;
	}

	/**
	 * Gets the otros impuestos.
	 *
	 * @return the otros impuestos
	 */
	public BigDecimal getOtrosImpuestos() {
		return otrosImpuestos;
	}

	/**
	 * Sets the otros impuestos.
	 *
	 * @param otrosImpuestos
	 *            the new otros impuestos
	 */
	public void setOtrosImpuestos(BigDecimal otrosImpuestos) {
		this.otrosImpuestos = otrosImpuestos;
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
	 *            the new divisa
	 */
	public void setDivisa(DivisaEnum divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the numero recibo.
	 *
	 * @return the numero recibo
	 */
	public int getNumeroRecibo() {
		return numeroRecibo;
	}

	/**
	 * Sets the numero recibo.
	 *
	 * @param numeroRecibo
	 *            the new numero recibo
	 */
	public void setNumeroRecibo(int numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}

	/**
	 * @return the capital
	 */
	public BigDecimal getCapital() {
		return capital;
	}

	/**
	 * @param capital the capital to set
	 */
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	/**
	 * @return the tasaTEA
	 */
	public BigDecimal getTasaTEA() {
		return tasaTEA;
	}

	/**
	 * @param tasaTEA the tasaTEA to set
	 */
	public void setTasaTEA(BigDecimal tasaTEA) {
		this.tasaTEA = tasaTEA;
	}

	/**
	 * @return the cftConImp
	 */
	public BigDecimal getCftConImp() {
		return cftConImp;
	}

	/**
	 * @param cftConImp the cftConImp to set
	 */
	public void setCftConImp(BigDecimal cftConImp) {
		this.cftConImp = cftConImp;
	}

	/**
	 * @return the cftSinImp
	 */
	public BigDecimal getCftSinImp() {
		return cftSinImp;
	}

	/**
	 * @param cftSinImp the cftSinImp to set
	 */
	public void setCftSinImp(BigDecimal cftSinImp) {
		this.cftSinImp = cftSinImp;
	}

}

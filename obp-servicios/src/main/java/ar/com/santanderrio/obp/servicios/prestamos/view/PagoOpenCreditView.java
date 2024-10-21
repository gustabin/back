/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

/**
 * OLYMPUS.
 *
 * @author Silvina_Luque
 */
public class PagoOpenCreditView {

	/** The numero pago minimo. */
	private String numeroPagoMinimo;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The pago minimo. */
	private String pagoMinimo;

	/** The int compensatorios periodo. */
	private String intCompensatoriosPeriodo;

	/** The impuestos. */
	private String impuestos;

	/** The tasa TNA. */
	private String tasaTNA;

	/** Capital. */
	private String capital;

	/** Tasa efectiva anual. */
	private String tasaTEA;

	/** Costo financiero total con impuestos. */
	private String cftConImp;

	/** Costo financiero total sin impuestos. */
	private String cftSinImp;

	/**
	 * Gets the tasa TNA.
	 *
	 * @return the tasa TNA
	 */
	public String getTasaTNA() {
		return tasaTNA;
	}

	/**
	 * Sets the tasa TNA.
	 *
	 * @param tasaTNA the new tasa TNA
	 */
	public void setTasaTNA(String tasaTNA) {
		this.tasaTNA = tasaTNA;
	}

	/**
	 * Gets the numero pago minimo.
	 *
	 * @return the numero pago minimo
	 */
	public String getNumeroPagoMinimo() {
		return numeroPagoMinimo;
	}

	/**
	 * Sets the numero pago minimo.
	 *
	 * @param numeroPagoMinimo the new numero pago minimo
	 */
	public void setNumeroPagoMinimo(String numeroPagoMinimo) {
		this.numeroPagoMinimo = numeroPagoMinimo;
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
	 * @param fechaVencimiento the new fecha vencimiento
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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
	 * @param pagoMinimo the new pago minimo
	 */
	public void setPagoMinimo(String pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}

	/**
	 * Gets the int compensatorios periodo.
	 *
	 * @return the int compensatorios periodo
	 */
	public String getIntCompensatoriosPeriodo() {
		return intCompensatoriosPeriodo;
	}

	/**
	 * Sets the int compensatorios periodo.
	 *
	 * @param intCompensatoriosPeriodo the new int compensatorios periodo
	 */
	public void setIntCompensatoriosPeriodo(String intCompensatoriosPeriodo) {
		this.intCompensatoriosPeriodo = intCompensatoriosPeriodo;
	}

	/**
	 * Gets the impuestos.
	 *
	 * @return the impuestos
	 */
	public String getImpuestos() {
		return impuestos;
	}

	/**
	 * Sets the impuestos.
	 *
	 * @param impuestos the new impuestos
	 */
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}

	/**
	 * @param capital the capital to set
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * @return the tasaTEA
	 */
	public String getTasaTEA() {
		return tasaTEA;
	}

	/**
	 * @param tasaTEA the tasaTEA to set
	 */
	public void setTasaTEA(String tasaTEA) {
		this.tasaTEA = tasaTEA;
	}

	/**
	 * @return the cftConImp
	 */
	public String getCftConImp() {
		return cftConImp;
	}

	/**
	 * @param cftConImp the cftConImp to set
	 */
	public void setCftConImp(String cftConImp) {
		this.cftConImp = cftConImp;
	}

	/**
	 * @return the cftSinImp
	 */
	public String getCftSinImp() {
		return cftSinImp;
	}

	/**
	 * @param cftSinImp the cftSinImp to set
	 */
	public void setCftSinImp(String cftSinImp) {
		this.cftSinImp = cftSinImp;
	}

}

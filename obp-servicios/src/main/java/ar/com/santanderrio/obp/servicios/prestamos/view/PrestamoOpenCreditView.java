/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

/**
 * OLYMPUS PrestamoOpenCreditView.
 *
 * @author Silvina_Luque
 */
public class PrestamoOpenCreditView {

	/** Id de prestamo. */
	private String id;

	/** Fecha de vencimiento. */
	private String fechaVencimiento;

	/** Descripcion Open Credit. */
	private String descripcion;

	/** Numero de prestamo. */
	private String numeroPrestamo;

	/** Valor total del credito. */
	private String lineaCreditoTotal;

	/** Credito disponible para uso. */
	private String disponibleParaUso;

	/** Porcentaje tasa. */
	private String tasaTNA;

	/** Pago minimo. */
	private String pagoMinimo;

	/** Indica si se puede ver el detalle. */
	private boolean verDetalle;

	/** Indica si se puede ver el historal de pagos. */
	private boolean verHistorialPagosMinimos;

	/** Intereses compensatorios del periodo. */
	private String intCompensatoriosPeriodo;

	/** Intereses compensatorios post vencimiento. */
	private String intCompensatoriosPostVenciemiento;

	/** Intereses punitorios. */
	private String intPunitorios;

	/** IVA. */
	private String iva;

	/** Ingresos brutos. */
	private String ingresosBrutos;

	/** Valor de otros impuestos. */
	private String otrosImpuestos;

	/** Fecha inicio. */
	private String fechaInicio;
	
	/** Capital. */
	private String capital;
	
	/** Tasa efectiva anual. */
	private String tasaTEA;
	
	/** Costo financiero total con impuestos. */
	private String cftConImp;
	
	/** Costo financiero total sin impuestos. */
	private String cftSinImp;

	/**
	 * Gets the numero prestamo.
	 *
	 * @return the numero prestamo
	 */
	public String getNumeroPrestamo() {
		return numeroPrestamo;
	}

	/**
	 * Sets the numero prestamo.
	 *
	 * @param numeroPrestamo
	 *            the new numero prestamo
	 */
	public void setNumeroPrestamo(String numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
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
	public String getLineaCreditoTotal() {
		return lineaCreditoTotal;
	}

	/**
	 * Sets the linea credito total.
	 *
	 * @param lineaCreditoTotal
	 *            the new linea credito total
	 */
	public void setLineaCreditoTotal(String lineaCreditoTotal) {
		this.lineaCreditoTotal = lineaCreditoTotal;
	}

	/**
	 * Gets the disponible para uso.
	 *
	 * @return the disponible para uso
	 */
	public String getDisponibleParaUso() {
		return disponibleParaUso;
	}

	/**
	 * Sets the disponible para uso.
	 *
	 * @param disponibleParaUso
	 *            the new disponible para uso
	 */
	public void setDisponibleParaUso(String disponibleParaUso) {
		this.disponibleParaUso = disponibleParaUso;
	}

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
	 * @param tasaTNA
	 *            the new tasa TNA
	 */
	public void setTasaTNA(String tasaTNA) {
		this.tasaTNA = tasaTNA;
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
	 * Checks if is ver detalle.
	 *
	 * @return true, if is ver detalle
	 */
	public boolean isVerDetalle() {
		return verDetalle;
	}

	/**
	 * Sets the ver detalle.
	 *
	 * @param verDetalle
	 *            the new ver detalle
	 */
	public void setVerDetalle(boolean verDetalle) {
		this.verDetalle = verDetalle;
	}

	/**
	 * Checks if is ver historial pagos minimos.
	 *
	 * @return true, if is ver historial pagos minimos
	 */
	public boolean isVerHistorialPagosMinimos() {
		return verHistorialPagosMinimos;
	}

	/**
	 * Sets the ver historial pagos minimos.
	 *
	 * @param verHistorialPagosMinimos
	 *            the new ver historial pagos minimos
	 */
	public void setVerHistorialPagosMinimos(boolean verHistorialPagosMinimos) {
		this.verHistorialPagosMinimos = verHistorialPagosMinimos;
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
	 * @param intCompensatoriosPeriodo
	 *            the new int compensatorios periodo
	 */
	public void setIntCompensatoriosPeriodo(String intCompensatoriosPeriodo) {
		this.intCompensatoriosPeriodo = intCompensatoriosPeriodo;
	}

	/**
	 * Gets the int compensatorios post venciemiento.
	 *
	 * @return the int compensatorios post venciemiento
	 */
	public String getIntCompensatoriosPostVenciemiento() {
		return intCompensatoriosPostVenciemiento;
	}

	/**
	 * Sets the int compensatorios post venciemiento.
	 *
	 * @param intCompensatoriosPostVenciemiento
	 *            the new int compensatorios post venciemiento
	 */
	public void setIntCompensatoriosPostVenciemiento(String intCompensatoriosPostVenciemiento) {
		this.intCompensatoriosPostVenciemiento = intCompensatoriosPostVenciemiento;
	}

	/**
	 * Gets the int punitorios.
	 *
	 * @return the int punitorios
	 */
	public String getIntPunitorios() {
		return intPunitorios;
	}

	/**
	 * Sets the int punitorios.
	 *
	 * @param intPunitorios
	 *            the new int punitorios
	 */
	public void setIntPunitorios(String intPunitorios) {
		this.intPunitorios = intPunitorios;
	}

	/**
	 * Gets the iva.
	 *
	 * @return the iva
	 */
	public String getIva() {
		return iva;
	}

	/**
	 * Sets the iva.
	 *
	 * @param iva
	 *            the new iva
	 */
	public void setIva(String iva) {
		this.iva = iva;
	}

	/**
	 * Gets the ingresos brutos.
	 *
	 * @return the ingresos brutos
	 */
	public String getIngresosBrutos() {
		return ingresosBrutos;
	}

	/**
	 * Sets the ingresos brutos.
	 *
	 * @param ingresosBrutos
	 *            the new ingresos brutos
	 */
	public void setIngresosBrutos(String ingresosBrutos) {
		this.ingresosBrutos = ingresosBrutos;
	}

	/**
	 * Gets the otros impuestos.
	 *
	 * @return the otros impuestos
	 */
	public String getOtrosImpuestos() {
		return otrosImpuestos;
	}

	/**
	 * Sets the otros impuestos.
	 *
	 * @param otrosImpuestos
	 *            the new otros impuestos
	 */
	public void setOtrosImpuestos(String otrosImpuestos) {
		this.otrosImpuestos = otrosImpuestos;
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

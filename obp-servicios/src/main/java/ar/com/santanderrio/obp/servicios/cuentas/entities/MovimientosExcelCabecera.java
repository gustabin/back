/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Class MovimientosExcelCabecera.
 */
public class MovimientosExcelCabecera {

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The mostrar filtro fecha. */
	private Boolean mostrarFiltroFecha = false;

	/** The fecha desde. */
	private String fechaDesde;

	/** The fecha hasta. */
	private String fechaHasta;

	/** The mostrar filtro palabra clave. */
	private Boolean mostrarFiltroPalabraClave = false;

	/** The palabra clave. */
	private String palabraClave;

	/** The mostrar filtro importes. */
	private Boolean mostrarFiltroImportes = false;

	/** The importe desde. */
	private String importeDesde;

	/** The importe hasta. */
	private String importeHasta;

	/** The mostrar saldo aperturado. */
	private Boolean mostrarSaldoAperturado = false;

	/** The is convenio pas. */
	private Boolean isConvenioPAS = false;

	/** The is moneda. */
	private String moneda;

	/** The is dispositivo. */
	private String dispositivo;

	/** The is tiene cta cte. */
	private Boolean hasCtaCte;

	/**
	 * Gets the checks if is convenio pas.
	 *
	 * @return the checks if is convenio pas
	 */
	public Boolean getIsConvenioPAS() {
		return isConvenioPAS;
	}

	/**
	 * Setter para checks if is convenio pas.
	 *
	 * @param isConvenioPAS
	 *            el nuevo checks if is convenio pas
	 */
	public void setIsConvenioPAS(Boolean isConvenioPAS) {
		this.isConvenioPAS = isConvenioPAS;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Setter para tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            el nuevo tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Setter para numero cuenta.
	 *
	 * @param numeroCuenta
	 *            el nuevo numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the mostrar filtro fecha.
	 *
	 * @return the mostrar filtro fecha
	 */
	public Boolean getMostrarFiltroFecha() {
		return mostrarFiltroFecha;
	}

	/**
	 * Setter para mostrar filtro fecha.
	 *
	 * @param mostrarFiltroFecha
	 *            el nuevo mostrar filtro fecha
	 */
	public void setMostrarFiltroFecha(Boolean mostrarFiltroFecha) {
		this.mostrarFiltroFecha = mostrarFiltroFecha;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Setter para fecha desde.
	 *
	 * @param fechaDesde
	 *            el nuevo fecha desde
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Setter para fecha hasta.
	 *
	 * @param fechaHasta
	 *            el nuevo fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Gets the mostrar filtro palabra clave.
	 *
	 * @return the mostrar filtro palabra clave
	 */
	public Boolean getMostrarFiltroPalabraClave() {
		return mostrarFiltroPalabraClave;
	}

	/**
	 * Setter para mostrar filtro palabra clave.
	 *
	 * @param mostrarFiltroPalabraClave
	 *            el nuevo mostrar filtro palabra clave
	 */
	public void setMostrarFiltroPalabraClave(Boolean mostrarFiltroPalabraClave) {
		this.mostrarFiltroPalabraClave = mostrarFiltroPalabraClave;
	}

	/**
	 * Gets the palabra clave.
	 *
	 * @return the palabra clave
	 */
	public String getPalabraClave() {
		return palabraClave;
	}

	/**
	 * Setter para palabra clave.
	 *
	 * @param palabraClave
	 *            el nuevo palabra clave
	 */
	public void setPalabraClave(String palabraClave) {
		this.palabraClave = palabraClave;
	}

	/**
	 * Gets the mostrar filtro importes.
	 *
	 * @return the mostrar filtro importes
	 */
	public Boolean getMostrarFiltroImportes() {
		return mostrarFiltroImportes;
	}

	/**
	 * Setter para mostrar filtro importes.
	 *
	 * @param mostrarFiltroImportes
	 *            el nuevo mostrar filtro importes
	 */
	public void setMostrarFiltroImportes(Boolean mostrarFiltroImportes) {
		this.mostrarFiltroImportes = mostrarFiltroImportes;
	}

	/**
	 * Gets the importe desde.
	 *
	 * @return the importe desde
	 */
	public String getImporteDesde() {
		return importeDesde;
	}

	/**
	 * Setter para importe desde.
	 *
	 * @param importeDesde
	 *            el nuevo importe desde
	 */
	public void setImporteDesde(String importeDesde) {
		this.importeDesde = importeDesde;
	}

	/**
	 * Gets the importe hasta.
	 *
	 * @return the importe hasta
	 */
	public String getImporteHasta() {
		return importeHasta;
	}

	/**
	 * Setter para importe hasta.
	 *
	 * @param importeHasta
	 *            el nuevo importe hasta
	 */
	public void setImporteHasta(String importeHasta) {
		this.importeHasta = importeHasta;
	}

	/**
	 * Gets the mostrar saldo aperturado.
	 *
	 * @return the mostrar saldo aperturado
	 */
	public Boolean getMostrarSaldoAperturado() {
		return mostrarSaldoAperturado;
	}

	/**
	 * Setter para mostrar saldo aperturado.
	 *
	 * @param mostrarSaldoAperturado
	 *            el nuevo mostrar saldo aperturado
	 */
	public void setMostrarSaldoAperturado(Boolean mostrarSaldoAperturado) {
		this.mostrarSaldoAperturado = mostrarSaldoAperturado;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the dispositivo.
	 *
	 * @return the dispositivo
	 */
	public String getDispositivo() {
		return dispositivo;
	}

	/**
	 * Sets the dispositivo.
	 *
	 * @param dispositivo
	 *            the new dispositivo
	 */
	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	/**
	 * Gets the checks for cta cte.
	 *
	 * @return the checks for cta cte
	 */
	public Boolean getHasCtaCte() {
		return hasCtaCte;
	}

	/**
	 * Sets the checks for cta cte.
	 *
	 * @param hasCtaCte
	 *            the new checks for cta cte
	 */
	public void setHasCtaCte(Boolean hasCtaCte) {
		this.hasCtaCte = hasCtaCte;
	}

}
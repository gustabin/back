package ar.com.santanderrio.obp.servicios.tenencias.view;

/**
 * The Class TenenciasResumenFinancieroView.
 */
public class TenenciasResumenFinancieroView {

	/** The nup. */
	private String nup;
	
	/** The fecha desde. */
	private String fechaDesde;
	
	/** The fecha hasta. */
	private String fechaHasta;
	
	/** The periodo. */
	private String periodo;
	
	/** The sucursal. */
	private String sucursal;
	
	/** The is banca privada. */
	private boolean isBancaPrivada;
	
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
	 * @param nup the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
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
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde the new fecha desde
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
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta the new fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}
	
	/**
	 * Sets the periodo.
	 *
	 * @param periodo the new periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}
	
	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public boolean isBancaPrivada() {
		return isBancaPrivada;
	}

	/**
	 * Sets the banca privada.
	 *
	 * @param isBancaPrivada the new banca privada
	 */
	public void setBancaPrivada(boolean isBancaPrivada) {
		this.isBancaPrivada = isBancaPrivada;
	}
	
}

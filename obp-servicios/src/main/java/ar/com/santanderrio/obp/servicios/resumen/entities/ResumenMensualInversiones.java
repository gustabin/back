/*
 * 
 */
package ar.com.santanderrio.obp.servicios.resumen.entities;

/**
 * The Class ResumenMensualInversiones.
 */
public class ResumenMensualInversiones {

	/** The id. */
	private Long id;

	/** The visto. */
	private Boolean visto;

	/** The fecha desde. */
	private String fechaDesde;

	/** The fecha hasta. */
	private String fechaHasta;

	/** The cuenta. */
	private String cuenta;

	/** The periodo. */
	private String periodo;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *     the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the visto.
	 *
	 * @return the visto
	 */
	public Boolean getVisto() {
		return visto;
	}

	/**
	 * Sets the visto.
	 *
	 * @param visto
	 *     the new visto
	 */
	public void setVisto(Boolean visto) {
		this.visto = visto;
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
	 * @param fecha
	 *     the new fecha desde
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
	 * @param fecha
	 *     the new fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta
	 *     the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo
	 *     the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
}

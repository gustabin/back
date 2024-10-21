package ar.com.santanderrio.obp.servicios.debinws.view;

/**
 * ConsultaDebinInView.
 *
 * @author Silvina_Luque
 */
public class ConsultaDebinWSInView {

	/** consultaDesdeRecibidos. */
	private Boolean consultaDesdeRecibidos = Boolean.TRUE;

	/** estado debin. */
	private String estado;

	/** flag primer llamado. */
	private boolean primerLlamado;

	/** The fecha desde. */
	private String fechaDesde;

	/** The fecha hasta. */
	private String fechaHasta;

	/** The tipo consulta. */
	private String tipoConsulta;

	/** id de recurrencia. */
	private String idRecurrencia;

	/**
	 * Checks if is primer llamado.
	 *
	 * @return true, if is primer llamado
	 */
	public boolean isPrimerLlamado() {
		return primerLlamado;
	}

	/**
	 * Sets the primer llamado.
	 *
	 * @param primerLlamado the new primer llamado
	 */
	public void setPrimerLlamado(boolean primerLlamado) {
		this.primerLlamado = primerLlamado;
	}

	/**
	 * Gets the consulta desde recibidos.
	 *
	 * @return the consulta desde recibidos
	 */
	public Boolean getConsultaDesdeRecibidos() {
		return consultaDesdeRecibidos;
	}

	/**
	 * Sets the consulta desde recibidos.
	 *
	 * @param consultaDesdeRecibidos the new consulta desde recibidos
	 */
	public void setConsultaDesdeRecibidos(Boolean consultaDesdeRecibidos) {
		this.consultaDesdeRecibidos = consultaDesdeRecibidos;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Gets the tipo consulta.
	 *
	 * @return the tipo consulta
	 */
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	/**
	 * Sets the tipo consulta.
	 *
	 * @param tipoConsulta the new tipo consulta
	 */
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	/**
	 * Gets the id recurrencia.
	 *
	 * @return the id recurrencia
	 */
	public String getIdRecurrencia() { return idRecurrencia; }

	/**
	 * Sets the id recurrencia.
	 *
	 * @param idRecurrencia the new tipo consulta
	 */
	public void setIdRecurrencia(String idRecurrencia) { this.idRecurrencia = idRecurrencia; }

}

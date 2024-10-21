package ar.com.santanderrio.obp.servicios.debinws.view;

import ar.com.santanderrio.obp.servicios.debinapi.dto.CurrencyType;

/**
 * The Class DebinWSGrillaView.
 */
public class DebinWSGrillaView {

	/** The fecha vencimiento. */
	private String fechaCreacion;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The nombre solicitante. */
	private String nombreSolicitante;

	/** The cuit solicitante. */
	private String cuitSolicitante;

	/** The importe. */
	private String importe;

	/** The debin id. */
	private String debinId;

	/** The estado. */
	private String estado;

	/** The moneda. */
	private CurrencyType moneda;

	/** The nombre destinatario. */
	private String nombreDestinatario;

	/** The is concepto valido. */
	private boolean isConceptoValido;

	/**
	 * Gets the nombre destinatario.
	 *
	 * @return the nombre destinatario
	 */
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}

	/**
	 * Sets the nombre destinatario.
	 *
	 * @param nombreDestinatario
	 *            the new nombre destinatario
	 */
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public CurrencyType getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(CurrencyType moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the debin id.
	 *
	 * @return the debin id
	 */
	public String getDebinId() {
		return debinId;
	}

	/**
	 * Sets the debin id.
	 *
	 * @param debinId
	 *            the new debin id
	 */
	public void setDebinId(String debinId) {
		this.debinId = debinId;
	}

	/**
	 * Gets the fecha creacion.
	 *
	 * @return the fecha creacion
	 */
	public String getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaCreacion
	 *            the new fecha vencimiento
	 */
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
	 * Gets the nombre solicitante.
	 *
	 * @return the nombre solicitante
	 */
	public String getNombreSolicitante() {
		return nombreSolicitante;
	}

	/**
	 * Sets the nombre solicitante.
	 *
	 * @param nombreSolicitante
	 *            the new nombre solicitante
	 */
	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}

	/**
	 * Gets the cuit solicitante.
	 *
	 * @return the cuit solicitante
	 */
	public String getCuitSolicitante() {
		return cuitSolicitante;
	}

	/**
	 * Sets the cuit solicitante.
	 *
	 * @param cuitSolicitante
	 *            the new cuit solicitante
	 */
	public void setCuitSolicitante(String cuitSolicitante) {
		this.cuitSolicitante = cuitSolicitante;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
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
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Checks if is concepto valido.
	 *
	 * @return true, if is concepto valido
	 */
	public boolean isConceptoValido() {
		return isConceptoValido;
	}

	/**
	 * Sets the concepto valido.
	 *
	 * @param isConceptoValido
	 *            the new concepto valido
	 */
	public void setConceptoValido(boolean isConceptoValido) {
		this.isConceptoValido = isConceptoValido;
	}

}

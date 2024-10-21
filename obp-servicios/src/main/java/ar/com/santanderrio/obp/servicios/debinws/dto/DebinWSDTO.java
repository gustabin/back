package ar.com.santanderrio.obp.servicios.debinws.dto;

import java.util.Date;

import ar.com.santanderrio.obp.servicios.debinapi.dto.CurrencyType;
import ar.com.santanderrio.obp.servicios.debinws.common.EstadoDebinEnum;

/**
 * The Class DebinWSDTO.
 */
public class DebinWSDTO {

	/** The fecha creacion. */
	private Date fechaCreacion;

	/** The fecha vencimiento. */
	private Date fechaVencimiento;

	/** The cuit solicitante. */
	private String cuitSolicitante;

	/** The nombre solicitante. */
	private String nombreSolicitante;

	/** The importe. */
	private String importe;

	/** The moneda. */
	private CurrencyType moneda;

	/** The debin id. */
	private String debinId;

	/** The estado. */
	private EstadoDebinEnum estado;

	/** The nombre destinatario. */
	private String nombreDestinatario;

	/** The concepto. */
	private String concepto;
	
	private boolean isDebinRec;

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
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public EstadoDebinEnum getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(EstadoDebinEnum estado) {
		this.estado = estado;
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
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the new fecha vencimiento
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the fecha creacion.
	 *
	 * @return the fecha vencimiento
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Sets the fecha creacion.
	 *
	 * @param fechaCreacion
	 *            the new fecha vencimiento
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Sets the concepto.
	 *
	 * @param concepto
	 *            the new concepto
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public boolean getIsDebinRec() {
		return isDebinRec;
	}

	public void setIsDebinRec(boolean isDebinRec) {
		this.isDebinRec = isDebinRec;
	}

	public CurrencyType getMoneda() { return moneda; }

	public void setMoneda(CurrencyType moneda) { this.moneda = moneda; }
}

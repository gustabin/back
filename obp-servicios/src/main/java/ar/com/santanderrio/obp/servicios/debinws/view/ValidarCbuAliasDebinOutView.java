package ar.com.santanderrio.obp.servicios.debinws.view;

import java.util.List;

/**
 * The Class ValidarCbuAliasDebinOutView.
 */
public class ValidarCbuAliasDebinOutView {

	/** The nombre destinatario. */
	private String nombreDestinatario;

	/** The cuit destinatario. */
	private String cuitDestinatario;

	/** The cbu destinatario. */
	private String cbuDestinatario;

	/** The alias destinatario. */
	private String aliasDestinatario;

	/** The banco destinatario. */
	private String bancoDestinatario;

	/** The moneda. */
	private String moneda;

	/** The cant dias vencimiento. */
	private String cantDiasVencimiento;

	/** The legales. */
	private String legales;

	/** The fecha hora comprobante. */
	private String fechaHoraComprobante;

	/** The nro comprobante. */
	private String nroComprobante;

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * Gets the fecha hora comprobante.
	 *
	 * @return the fecha hora comprobante
	 */
	public String getFechaHoraComprobante() {
		return fechaHoraComprobante;
	}

	/**
	 * Sets the fecha hora comprobante.
	 *
	 * @param fechaHoraComprobante the new fecha hora comprobante
	 */
	public void setFechaHoraComprobante(String fechaHoraComprobante) {
		this.fechaHoraComprobante = fechaHoraComprobante;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the cant dias vencimiento.
	 *
	 * @return the cant dias vencimiento
	 */
	public String getCantDiasVencimiento() {
		return cantDiasVencimiento;
	}

	/**
	 * Sets the cant dias vencimiento.
	 *
	 * @param cantDiasVencimiento the new cant dias vencimiento
	 */
	public void setCantDiasVencimiento(String cantDiasVencimiento) {
		this.cantDiasVencimiento = cantDiasVencimiento;
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
	 * @param moneda the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/** The conceptos. */
	private List<ConceptoDebinWSView> conceptos;

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
	 * @param nombreDestinatario the new nombre destinatario
	 */
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	/**
	 * Gets the cuit destinatario.
	 *
	 * @return the cuit destinatario
	 */
	public String getCuitDestinatario() {
		return cuitDestinatario;
	}

	/**
	 * Sets the cuit destinatario.
	 *
	 * @param cuitDestinatario the new cuit destinatario
	 */
	public void setCuitDestinatario(String cuitDestinatario) {
		this.cuitDestinatario = cuitDestinatario;
	}

	/**
	 * Gets the cbu destinatario.
	 *
	 * @return the cbu destinatario
	 */
	public String getCbuDestinatario() {
		return cbuDestinatario;
	}

	/**
	 * Sets the cbu destinatario.
	 *
	 * @param cbuDestinatario the new cbu destinatario
	 */
	public void setCbuDestinatario(String cbuDestinatario) {
		this.cbuDestinatario = cbuDestinatario;
	}

	/**
	 * Gets the alias destinatario.
	 *
	 * @return the alias destinatario
	 */
	public String getAliasDestinatario() {
		return aliasDestinatario;
	}

	/**
	 * Sets the alias destinatario.
	 *
	 * @param aliasDestinatario the new alias destinatario
	 */
	public void setAliasDestinatario(String aliasDestinatario) {
		this.aliasDestinatario = aliasDestinatario;
	}

	/**
	 * Gets the banco destinatario.
	 *
	 * @return the banco destinatario
	 */
	public String getBancoDestinatario() {
		return bancoDestinatario;
	}

	/**
	 * Sets the banco destinatario.
	 *
	 * @param bancoDestinatario the new banco destinatario
	 */
	public void setBancoDestinatario(String bancoDestinatario) {
		this.bancoDestinatario = bancoDestinatario;
	}

	/**
	 * Gets the conceptos.
	 *
	 * @return the conceptos
	 */
	public List<ConceptoDebinWSView> getConceptos() {
		return conceptos;
	}

	/**
	 * Sets the conceptos.
	 *
	 * @param conceptos the new conceptos
	 */
	public void setConceptos(List<ConceptoDebinWSView> conceptos) {
		this.conceptos = conceptos;
	}

}

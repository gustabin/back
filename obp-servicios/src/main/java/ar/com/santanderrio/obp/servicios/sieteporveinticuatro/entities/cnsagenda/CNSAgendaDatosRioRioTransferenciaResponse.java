/*
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class CNSAgendaDatosRioRioTransferenciaResponse.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CNSAgendaDatosRioRioTransferenciaResponse extends AbstractCNSAgendaDatosTransferenciaResponse {

	/** The importe debito. */
	@XmlElement(name = "ImporteDebito")
	private String importeDebito;

	/** The tipo cuenta credito. */
	@XmlElement(name = "TipoCtaCredito")
	private String tipoCuentaCredito;

	/** The cotizacion transferencia. */
	@XmlElement(name = "CotizacionTransferencia")
	private String cotizacionTransferencia;

	/** The importe credito. */
	@XmlElement(name = "ImporteCredito")
	private String importeCredito;

	/** The nro comprobante. */
	@XmlElement(name = "NroComprobante")
	private String nroComprobante;

	/** The indicador limite max. */
	@XmlElement(name = "IndicadorLimiteMax")
	private String indicadorLimiteMax;

	/** The indicador afectar disponible. */
	@XmlElement(name = "IndicadorAfectarDisponible")
	private String indicadorAfectarDisponible;

	/** The ind trf dif. */
	@XmlElement(name = "IndTrfDif")
	private String indTrfDif;

	/** The cuenta propia. */
	@XmlElement(name = "CuentaPropia")
	private String cuentaPropia;

	/** The email. */
	@XmlElement(name = "Email")
	private String email;

	/**
	 * Gets the importe debito.
	 *
	 * @return the importe debito
	 */
	public String getImporteDebito() {
		return importeDebito;
	}

	/**
	 * Sets the importe debito.
	 *
	 * @param importeDebito
	 *            the new importe debito
	 */
	public void setImporteDebito(String importeDebito) {
		this.importeDebito = importeDebito;
	}

	/**
	 * Gets the tipo cuenta credito.
	 *
	 * @return the tipo cuenta credito
	 */
	public String getTipoCuentaCredito() {
		return tipoCuentaCredito;
	}

	/**
	 * Sets the tipo cuenta credito.
	 *
	 * @param tipoCuentaCredito
	 *            the new tipo cuenta credito
	 */
	public void setTipoCuentaCredito(String tipoCuentaCredito) {
		this.tipoCuentaCredito = tipoCuentaCredito;
	}

	/**
	 * Gets the cotizacion transferencia.
	 *
	 * @return the cotizacion transferencia
	 */
	public String getCotizacionTransferencia() {
		return cotizacionTransferencia;
	}

	/**
	 * Sets the cotizacion transferencia.
	 *
	 * @param cotizacionTransferencia
	 *            the new cotizacion transferencia
	 */
	public void setCotizacionTransferencia(String cotizacionTransferencia) {
		this.cotizacionTransferencia = cotizacionTransferencia;
	}

	/**
	 * Gets the importe credito.
	 *
	 * @return the importe credito
	 */
	public String getImporteCredito() {
		return importeCredito;
	}

	/**
	 * Sets the importe credito.
	 *
	 * @param importeCredito
	 *            the new importe credito
	 */
	public void setImporteCredito(String importeCredito) {
		this.importeCredito = importeCredito;
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
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the indicador limite max.
	 *
	 * @return the indicador limite max
	 */
	public String getIndicadorLimiteMax() {
		return indicadorLimiteMax;
	}

	/**
	 * Sets the indicador limite max.
	 *
	 * @param indicadorLimiteMax
	 *            the new indicador limite max
	 */
	public void setIndicadorLimiteMax(String indicadorLimiteMax) {
		this.indicadorLimiteMax = indicadorLimiteMax;
	}

	/**
	 * Gets the indicador afectar disponible.
	 *
	 * @return the indicador afectar disponible
	 */
	public String getIndicadorAfectarDisponible() {
		return indicadorAfectarDisponible;
	}

	/**
	 * Sets the indicador afectar disponible.
	 *
	 * @param indicadorAfectarDisponible
	 *            the new indicador afectar disponible
	 */
	public void setIndicadorAfectarDisponible(String indicadorAfectarDisponible) {
		this.indicadorAfectarDisponible = indicadorAfectarDisponible;
	}

	/**
	 * Gets the ind trf dif.
	 *
	 * @return the ind trf dif
	 */
	public String getIndTrfDif() {
		return indTrfDif;
	}

	/**
	 * Sets the ind trf dif.
	 *
	 * @param indTrfDif
	 *            the new ind trf dif
	 */
	public void setIndTrfDif(String indTrfDif) {
		this.indTrfDif = indTrfDif;
	}

	/**
	 * Gets the cuenta propia.
	 *
	 * @return the cuenta propia
	 */
	public String getCuentaPropia() {
		return cuentaPropia;
	}

	/**
	 * Sets the cuenta propia.
	 *
	 * @param cuentaPropia
	 *            the new cuenta propia
	 */
	public void setCuentaPropia(String cuentaPropia) {
		this.cuentaPropia = cuentaPropia;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}

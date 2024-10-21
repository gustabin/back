/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The Class SolicitanteCriteriosDeBusquedaView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SolicitanteCriteriosDeBusquedaView {

	/** The nup. */
	private String nup;

	/** The doc tipo. */
	private String docTipo;

	/** The doc nro. */
	private String docNro;

	/** The secuencia. */
	private String secuencia;

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNUP() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nUP
	 *            the new nup
	 */
	public void setNUP(String nUP) {
		this.nup = nUP;
	}

	/**
	 * Gets the doc tipo.
	 *
	 * @return the doc tipo
	 */
	public String getDocTipo() {
		return docTipo;
	}

	/**
	 * Sets the doc tipo.
	 *
	 * @param docTipo
	 *            the new doc tipo
	 */
	public void setDocTipo(String docTipo) {
		this.docTipo = docTipo;
	}

	/**
	 * Gets the doc nro.
	 *
	 * @return the doc nro
	 */
	public String getDocNro() {
		return docNro;
	}

	/**
	 * Sets the doc nro.
	 *
	 * @param docNro
	 *            the new doc nro
	 */
	public void setDocNro(String docNro) {
		this.docNro = docNro;
	}

	/**
	 * Gets the secuencia.
	 *
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * Sets the secuencia.
	 *
	 * @param secuencia
	 *            the new secuencia
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SolicitanteCriteriosDeBusquedaView [NUP=" + nup + ", docTipo=" + docTipo + ", docNro=" + docNro
				+ ", Secuencia=" + secuencia + "]";
	}
}

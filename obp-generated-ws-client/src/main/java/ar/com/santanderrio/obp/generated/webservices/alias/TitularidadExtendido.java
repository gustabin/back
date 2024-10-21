package ar.com.santanderrio.obp.generated.webservices.alias;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class TitularidadExtendido {
	private CuentaDTO ctaDestino;
	private List<String> cuits;
	private String fiidBanco;
	private String nombreBanco;
	private String nombreTitular;
	private String alias;
	private String fiidOrigenLink;
	
	/**
	 * @return the ctaDestino
	 */
	public CuentaDTO getCtaDestino() {
		return ctaDestino;
	}
	/**
	 * @param ctaDestino the ctaDestino to set
	 */
	public void setCtaDestino(CuentaDTO ctaDestino) {
		this.ctaDestino = ctaDestino;
	}

	public List<String> getCuits() {
		return cuits;
	}

	public void setCuits(List<String> cuits) {
		this.cuits = cuits;
	}

	/**
	 * @return the fiidBanco
	 */
	public String getFiidBanco() {
		return fiidBanco;
	}
	/**
	 * @param fiidBanco the fiidBanco to set
	 */
	public void setFiidBanco(String fiidBanco) {
		this.fiidBanco = fiidBanco;
	}
	/**
	 * @return the nombreBanco
	 */
	public String getNombreBanco() {
		return nombreBanco;
	}
	/**
	 * @param nombreBanco the nombreBanco to set
	 */
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}
	/**
	 * @return the nombreTitular
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}
	/**
	 * @param nombreTitular the nombreTitular to set
	 */
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}
	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * @return the fiidOrigenLink
	 */
	public String getFiidOrigenLink() {
		return fiidOrigenLink;
	}
	/**
	 * @param fiidOrigenLink the fiidOrigenLink to set
	 */
	public void setFiidOrigenLink(String fiidOrigenLink) {
		this.fiidOrigenLink = fiidOrigenLink;
	}
	
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TitularidadExtendido [ctaDestino=" + ctaDestino + ", cuits=" + cuits.toString() + ", fiidBanco=" + fiidBanco
                + ", nombreBanco=" + nombreBanco + ", nombreTitular=" + nombreTitular + ", alias=" + alias
                + ", fiidOrigenLink=" + fiidOrigenLink + "] ";
    }
	
	
}

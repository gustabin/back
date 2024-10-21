/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.canceltotal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * The Class CancelTotalInfoResponse.
 *
 * @author B039543
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CancelTotalInfoResponse {

	/** The sistema. */
	@XmlAttribute(name = "sistema")
	protected String sistema;

	/** The cod. */
	@XmlAttribute(name = "cod")
	protected String cod;

	/** The desc. */
	@XmlAttribute(name = "desc")
	protected String desc;

	/**
	 * Gets the sistema.
	 *
	 * @return the sistema
	 */
	public String getSistema() {
		return sistema;
	}

	/**
	 * Sets the sistema.
	 *
	 * @param sistema
	 *            the sistema to set
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	/**
	 * Gets the cod.
	 *
	 * @return the cod
	 */
	public String getCod() {
		return cod;
	}

	/**
	 * Sets the cod.
	 *
	 * @param cod
	 *            the cod to set
	 */
	public void setCod(String cod) {
		this.cod = cod;
	}

	/**
	 * Gets the desc.
	 *
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Sets the desc.
	 *
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}

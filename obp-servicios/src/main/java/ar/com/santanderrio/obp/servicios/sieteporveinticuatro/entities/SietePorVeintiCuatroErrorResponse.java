/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Ej:
 * 
 * <pre>
 * <code>
 * 	<error sistema="IAServicios7x24" cod="00000000" desc=
"Se ha producido un error, por favor verifique si su operaci�n fue realizada." />
 * </code>
 * </pre>
 * 
 * @author sergio.e.goldentair
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SietePorVeintiCuatroErrorResponse {

	/** The desc. */
	@XmlAttribute
	private String desc;

	/** The cod. */
	@XmlAttribute
	private String cod;

	/** The sistema. */
	@XmlAttribute
	private String sistema;

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

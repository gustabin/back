/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.modificacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class ModificacionTADatosResponse.
 *
 * @author B039543
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ModificacionTADatosResponse {

	/** The id def. */
	@XmlElement(name = "idDef")
	protected String idDef;

	/** The id rec. */
	@XmlElement(name = "idRec")
	protected String idRec;

	/** The id ev. */
	@XmlElement(name = "idEv")
	protected String idEv;

	/**
	 * Gets the id def.
	 *
	 * @return the idDef
	 */
	public String getIdDef() {
		return idDef;
	}

	/**
	 * Sets the id def.
	 *
	 * @param idDef
	 *            the idDef to set
	 */
	public void setIdDef(String idDef) {
		this.idDef = idDef;
	}

	/**
	 * Gets the id rec.
	 *
	 * @return the idRec
	 */
	public String getIdRec() {
		return idRec;
	}

	/**
	 * Sets the id rec.
	 *
	 * @param idRec
	 *            the idRec to set
	 */
	public void setIdRec(String idRec) {
		this.idRec = idRec;
	}

	/**
	 * Gets the id ev.
	 *
	 * @return the idEv
	 */
	public String getIdEv() {
		return idEv;
	}

	/**
	 * Sets the id ev.
	 *
	 * @param idEv
	 *            the idEv to set
	 */
	public void setIdEv(String idEv) {
		this.idEv = idEv;
	}

}

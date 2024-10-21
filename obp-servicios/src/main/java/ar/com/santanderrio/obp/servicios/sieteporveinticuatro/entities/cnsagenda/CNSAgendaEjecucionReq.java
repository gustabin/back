/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class CNSAgendaEjecucionReq.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CNSAgendaEjecucionReq {

	/** The modo. */
	@XmlAttribute
	private String modo;

	/** The accion. */
	@XmlElement
	private String accion;

	/** The id def. */
	@XmlElement
	private String idDef;

	/** The id rec. */
	@XmlElement
	private String idRec;

	/** The id ev. */
	@XmlElement
	private String idEv;

	/**
	 * Gets the accion.
	 *
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Sets the accion.
	 *
	 * @param accion
	 *            the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

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

	/**
	 * Gets the modo.
	 *
	 * @return the modo
	 */
	public String getModo() {
		return modo;
	}

	/**
	 * Sets the modo.
	 *
	 * @param modo
	 *            the modo to set
	 */
	public void setModo(String modo) {
		this.modo = modo;
	}

}

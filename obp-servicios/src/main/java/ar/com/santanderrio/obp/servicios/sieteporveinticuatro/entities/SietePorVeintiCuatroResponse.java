/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class SietePorVeintiCuatroResponse.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SietePorVeintiCuatroResponse {

	/** The cod ret. */
	@XmlElement(required = true)
	protected String codRet;

	/** The severidad. */
	@XmlElement(required = true)
	protected String severidad;

	/** The id oper. */
	@XmlElement(required = true)
	protected String idOper;

	/** The id backend. */
	@XmlElement(required = true)
	protected String idBackend;

	/** The error. */
	@XmlElement
	List<SietePorVeintiCuatroErrorResponse> error;

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public List<SietePorVeintiCuatroErrorResponse> getError() {
		if (error == null) {
			error = new ArrayList<SietePorVeintiCuatroErrorResponse>();
		}
		return this.error;
	}

	/**
	 * Gets the cod ret.
	 *
	 * @return the codRet
	 */
	public String getCodRet() {
		return codRet;
	}

	/**
	 * Sets the cod ret.
	 *
	 * @param codRet
	 *            the codRet to set
	 */
	public void setCodRet(String codRet) {
		this.codRet = codRet;
	}

	/**
	 * Gets the severidad.
	 *
	 * @return the severidad
	 */
	public String getSeveridad() {
		return severidad;
	}

	/**
	 * Sets the severidad.
	 *
	 * @param severidad
	 *            the severidad to set
	 */
	public void setSeveridad(String severidad) {
		this.severidad = severidad;
	}

	/**
	 * Gets the id oper.
	 *
	 * @return the idOper
	 */
	public String getIdOper() {
		return idOper;
	}

	/**
	 * Sets the id oper.
	 *
	 * @param idOper
	 *            the idOper to set
	 */
	public void setIdOper(String idOper) {
		this.idOper = idOper;
	}

	/**
	 * Gets the id backend.
	 *
	 * @return the idBackend
	 */
	public String getIdBackend() {
		return idBackend;
	}

	/**
	 * Sets the id backend.
	 *
	 * @param idBackend
	 *            the idBackend to set
	 */
	public void setIdBackend(String idBackend) {
		this.idBackend = idBackend;
	}

}

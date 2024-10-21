/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class ErrorTarjetasEntity. Clase que representa el tag
 * /tarjetas/tarjeta/error/
 *
 * @author sergio.e.goldentair
 */
@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorTarjetasEntity {

	/** The error ID. */
	@XmlAttribute(name = "errorID")
	private String errorID;

	/** The session ID. */
	@XmlAttribute(name = "sessionID")
	private String sessionID;

	/** The codigo. */
	@XmlElement(name = "codigo")
	private String codigo;

	/**
	 * Gets the error ID.
	 *
	 * @return the errorID
	 */
	public String getErrorID() {
		return errorID;
	}

	/**
	 * Sets the error ID.
	 *
	 * @param errorID
	 *            the errorID to set
	 */
	public void setErrorID(String errorID) {
		this.errorID = errorID;
	}

	/**
	 * Gets the session ID.
	 *
	 * @return the sessionID
	 */
	public String getSessionID() {
		return sessionID;
	}

	/**
	 * Sets the session ID.
	 *
	 * @param sessionID
	 *            the sessionID to set
	 */
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((errorID == null) ? 0 : errorID.hashCode());
		result = prime * result + ((sessionID == null) ? 0 : sessionID.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorTarjetasEntity other = (ErrorTarjetasEntity) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (errorID == null) {
			if (other.errorID != null)
				return false;
		} else if (!errorID.equals(other.errorID))
			return false;
		if (sessionID == null) {
			if (other.sessionID != null)
				return false;
		} else if (!sessionID.equals(other.sessionID))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ErrorTarjetasEntity [errorID=" + errorID + ", sessionID=" + sessionID + ", codigo=" + codigo + "]";
	}
}

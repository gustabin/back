/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class Total. Clase que representa el tag
 * /tarjetas/tarjeta/liquidacion/total/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement(name = "total")
@XmlAccessorType(XmlAccessType.FIELD)
public class Total {

	/** The String sessionID. */
	@XmlAttribute(name = "sessionID")
	private String sessionID;

	/** The String valor. */
	@XmlValue
	private String valor;

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
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Sets the valor.
	 *
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(sessionID);
		hcb.append(valor);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Total other = (Total) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(sessionID, other.getSessionID());
		eb.append(valor, other.getValor());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Total [sessionID=" + sessionID + ", valor=" + valor + "]";
	}

}

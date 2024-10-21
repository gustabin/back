/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosEntity;

/**
 * The Class TarjetaDocumentEntity. Clase que representa el tag
 * /tarjetas/tarjeta/document/
 *
 * @author sergio.e.goldentair
 */
@XmlRootElement(name = "document")
@XmlAccessorType(XmlAccessType.FIELD)
public class TarjetaDocumentEntity {

	/** The String sessionID. */
	@XmlAttribute(name = "sessionID")
	private String sessionID;

	/** The DatosEntity datos. */
	@XmlElement(name = "datos")
	private DatosEntity datos;

	/** The informe debitos automaticos. */
	@XmlElement(name = "informeDebitosAutomaticos")
	private InformeDebitosAutomaticosEntity informeDebitosAutomaticos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosEntity datos) {
		this.datos = datos;
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
	 * Gets the informe debitos automaticos.
	 *
	 * @return the informeDebitosAutomaticos
	 */
	public InformeDebitosAutomaticosEntity getInformeDebitosAutomaticos() {
		return informeDebitosAutomaticos;
	}

	/**
	 * Sets the informe debitos automaticos.
	 *
	 * @param informeDebitosAutomaticos
	 *            the informeDebitosAutomaticos to set
	 */
	public void setInformeDebitosAutomaticos(InformeDebitosAutomaticosEntity informeDebitosAutomaticos) {
		this.informeDebitosAutomaticos = informeDebitosAutomaticos;
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
		hcb.append(datos);
		hcb.append(informeDebitosAutomaticos);
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
		TarjetaDocumentEntity other = (TarjetaDocumentEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(sessionID, other.getSessionID());
		eb.append(datos, other.getDatos());
		eb.append(informeDebitosAutomaticos, other.getInformeDebitosAutomaticos());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(sessionID);
		sb.append(datos);
		sb.append(informeDebitosAutomaticos);
		return sb.toString();
	}
}

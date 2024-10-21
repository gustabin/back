/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ErrorTarjetasEntity;

/**
 * The Class TarjetaEntity. Clase que representa el tag /tarjetas/tarjeta/
 */
@XmlRootElement(name = "tarjeta")
@XmlAccessorType(XmlAccessType.FIELD)
public class TarjetaEntity {

	/** The tarjeta document. */
	@XmlElement(name = "document")
	private TarjetaDocumentEntity tarjetaDocument;

	/** The error. */
	@XmlElement(name = "error")
	private ErrorTarjetasEntity error;

	/**
	 * Gets the tarjeta document.
	 *
	 * @return the tarjetaDocument
	 */
	public TarjetaDocumentEntity getTarjetaDocument() {
		return tarjetaDocument;
	}

	/**
	 * Sets the tarjeta document.
	 *
	 * @param tarjetaDocument
	 *            the tarjetaDocument to set
	 */
	public void setTarjetaDocument(TarjetaDocumentEntity tarjetaDocument) {
		this.tarjetaDocument = tarjetaDocument;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public ErrorTarjetasEntity getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error
	 *            the error to set
	 */
	public void setError(ErrorTarjetasEntity error) {
		this.error = error;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(tarjetaDocument);
		hcb.append(error);
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
		TarjetaEntity other = (TarjetaEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(tarjetaDocument, other.getTarjetaDocument());
		eb.append(error, other.getError());
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
		sb.append(tarjetaDocument);
		sb.append(error);
		return sb.toString();
	}
}

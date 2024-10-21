/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

	/** The posicion consolidada. */
	@XmlElement(name = "posicionConsolidada")
	private PosicionConsolidadaEntity posicionConsolidada;

	/** The UltimaLiquidacion ultimaLiquidacion. */
	@XmlElement(name = "liquidacion")
	private UltimaLiquidacionEntity ultimaLiquidacion;

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
	 * Gets the posicion consolidada.
	 *
	 * @return the posicionConsolidada
	 */
	public PosicionConsolidadaEntity getPosicionConsolidada() {
		return posicionConsolidada;
	}

	/**
	 * Sets the posicion consolidada.
	 *
	 * @param posicionConsolidada
	 *            the posicionConsolidada to set
	 */
	public void setPosicionConsolidada(PosicionConsolidadaEntity posicionConsolidada) {
		this.posicionConsolidada = posicionConsolidada;
	}

	/**
	 * Gets the UltimaLiquidacion ultimaLiquidacion.
	 *
	 * @return the ultimaLiquidacion
	 */
	public UltimaLiquidacionEntity getUltimaLiquidacion() {
		return ultimaLiquidacion;
	}

	/**
	 * Sets the UltimaLiquidacion ultimaLiquidacion.
	 *
	 * @param ultimaLiquidacion
	 *            the ultimaLiquidacion to set
	 */
	public void setUltimaLiquidacion(UltimaLiquidacionEntity ultimaLiquidacion) {
		this.ultimaLiquidacion = ultimaLiquidacion;
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
		hcb.append(posicionConsolidada);
		hcb.append(ultimaLiquidacion);
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
		eb.append(posicionConsolidada, other.getPosicionConsolidada());
		eb.append(ultimaLiquidacion, other.getUltimaLiquidacion());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Tarjeta [tarjetaDocument=" + tarjetaDocument + ", error=" + error + ", posicionConsolidada="
				+ posicionConsolidada + ", ultimaLiquidacion=" + ultimaLiquidacion + "]";
	}
}

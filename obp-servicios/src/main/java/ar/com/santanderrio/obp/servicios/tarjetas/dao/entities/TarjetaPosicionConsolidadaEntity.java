/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class TarjetaPosicionConsolidadaEntity. Clase que representa el tag
 * /tarjetas/tarjeta/posicionConsolidada/tarjeta/
 */
@XmlRootElement(name = "tarjeta")
@XmlAccessorType(XmlAccessType.FIELD)
public class TarjetaPosicionConsolidadaEntity extends Consolidada {

	/** The numero. */
	@XmlAttribute(name = "numero")
	private String numero;

	/** The habiente. */
	@XmlElement(name = "habiente")
	private String habiente;

	/** The autorizaciones. */
	@XmlElement(name = "autorizaciones")
	private ConsumosCuentasAuthEntity autorizaciones;

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the habiente.
	 *
	 * @return the habiente
	 */
	public String getHabiente() {
		return habiente;
	}

	/**
	 * Sets the habiente.
	 *
	 * @param habiente
	 *            the habiente to set
	 */
	public void setHabiente(String habiente) {
		this.habiente = habiente;
	}

	/**
	 * Gets the autorizaciones.
	 *
	 * @return the autorizaciones
	 */
	public ConsumosCuentasAuthEntity getAutorizaciones() {
		return autorizaciones;
	}

	/**
	 * Sets the autorizaciones.
	 *
	 * @param autorizaciones
	 *            the autorizaciones to set
	 */
	public void setAutorizaciones(ConsumosCuentasAuthEntity autorizaciones) {
		this.autorizaciones = autorizaciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(autorizaciones);
		hcb.append(habiente);
		hcb.append(numero);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
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
		TarjetaPosicionConsolidadaEntity other = (TarjetaPosicionConsolidadaEntity) obj;
		return new EqualsBuilder().append(autorizaciones, other.autorizaciones).append(habiente, other.habiente)
				.append(numero, other.numero).isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "TarjetaPosicionConsolidadaEntity [numero=" + numero + ", habiente=" + habiente + ", autorizaciones="
				+ autorizaciones + "]";
	}

}

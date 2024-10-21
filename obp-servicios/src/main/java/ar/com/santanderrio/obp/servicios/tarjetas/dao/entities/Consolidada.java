/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * <p>
 * Clase abstracta para lo referido a la posicion consolidada
 * </p>
 * <ol>
 * <li>PosicionConsolidada</li>
 * <li>TarjetaPosicionConsolidada</li>
 * </ol>
 * .
 *
 * @author sergio.e.goldentair
 */
// @XmlTransient //nocompila
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Consolidada {

	/** The consumos. */
	@XmlElement(name = "consumos")
	private ConsumosCuentasAuthEntity consumos;

	/** The cuotas. */
	@XmlElement(name = "cuotas")
	private ConsumosCuentasAuthEntity cuotas;

	/**
	 * Gets the consumos.
	 *
	 * @return the consumos
	 */
	public ConsumosCuentasAuthEntity getConsumos() {
		return consumos;
	}

	/**
	 * Sets the consumos.
	 *
	 * @param consumos
	 *            the consumos to set
	 */
	public void setConsumos(ConsumosCuentasAuthEntity consumos) {
		this.consumos = consumos;
	}

	/**
	 * Gets the cuotas.
	 *
	 * @return the cuotas
	 */
	public ConsumosCuentasAuthEntity getCuotas() {
		return cuotas;
	}

	/**
	 * Sets the cuotas.
	 *
	 * @param cuotas
	 *            the cuotas to set
	 */
	public void setCuotas(ConsumosCuentasAuthEntity cuotas) {
		this.cuotas = cuotas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(consumos);
		hcb.append(cuotas);
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
		Consolidada other = (Consolidada) obj;
		return new EqualsBuilder().append(consumos, other.consumos).append(cuotas, other.cuotas).isEquals();
	}

}

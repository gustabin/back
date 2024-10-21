/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class TasasTarjeta. Clase que representa el tag
 * /tarjetas/tarjeta/document/saldoenCuenta/tasas/
 */
@XmlRootElement(name = "tasas")
@XmlAccessorType(XmlAccessType.FIELD)
public class TasaTarjeta {

	/** The tasas. */
	@XmlElement(name = "tasa")
	private List<SaldoEnCuentaTarjeta> tasas;

	/**
	 * Gets the tasas.
	 *
	 * @return the tasas
	 */
	public List<SaldoEnCuentaTarjeta> getTasas() {
		return tasas;
	}

	/**
	 * Sets the tasas.
	 *
	 * @param tasas
	 *            the tasas to set
	 */
	public void setTasas(List<SaldoEnCuentaTarjeta> tasas) {
		this.tasas = tasas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(tasas);
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
		TasaTarjeta other = (TasaTarjeta) obj;
		return new EqualsBuilder().append(tasas, other.tasas).isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "TasasTarjeta [tasas=" + tasas + "]";
	}

}

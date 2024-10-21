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
 * The Class SaldosTarjeta. Clase que representa el tag
 * /tarjetas/tarjeta/document/saldoenCuenta/saldos/
 */
@XmlRootElement(name = "saldos")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaldosTarjeta {

	/** The saldos. */
	@XmlElement(name = "saldo")
	private List<SaldoEnCuentaTarjeta> saldos;

	/**
	 * Gets the saldos.
	 *
	 * @return the saldos
	 */
	public List<SaldoEnCuentaTarjeta> getSaldos() {
		return saldos;
	}

	/**
	 * Sets the saldos.
	 *
	 * @param saldos
	 *            the saldos to set
	 */
	public void setSaldos(List<SaldoEnCuentaTarjeta> saldos) {
		this.saldos = saldos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(saldos);
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
		SaldosTarjeta other = (SaldosTarjeta) obj;
		return new EqualsBuilder().append(saldos, other.saldos).isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "SaldosTarjeta [saldos=" + saldos + "]";
	}

}

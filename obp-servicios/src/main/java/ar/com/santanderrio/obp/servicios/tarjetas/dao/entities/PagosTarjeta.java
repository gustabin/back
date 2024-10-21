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
 * The Class PagosTarjeta. Clase que representa el tag
 * /tarjetas/tarjeta/document/saldoenCuenta/pagos/
 */
@XmlRootElement(name = "pagos")
@XmlAccessorType(XmlAccessType.FIELD)
public class PagosTarjeta {

	/** The pagos. */
	@XmlElement(name = "pago")
	private List<SaldoEnCuentaTarjeta> pagos;

	/**
	 * Gets the pagos.
	 *
	 * @return the pagos
	 */
	public List<SaldoEnCuentaTarjeta> getPagos() {
		return pagos;
	}

	/**
	 * Sets the pagos.
	 *
	 * @param pagos
	 *            the pagos to set
	 */
	public void setPagos(List<SaldoEnCuentaTarjeta> pagos) {
		this.pagos = pagos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(pagos);
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
		PagosTarjeta other = (PagosTarjeta) obj;
		return new EqualsBuilder().append(pagos, other.pagos).isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "PagosTarjeta [pagos=" + pagos + "]";
	}

}

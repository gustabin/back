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
 * The Class FechasTarjeta. Clase que representa el tag
 * /tarjetas/tarjeta/document/saldoenCuenta/fechas/
 */
@XmlRootElement(name = "fechas")
@XmlAccessorType(XmlAccessType.FIELD)
public class FechasTarjeta {

	/** The fechas. */
	@XmlElement(name = "fecha")
	private List<FechaTarjetaEntity> fechas;

	/**
	 * Gets the fechas.
	 *
	 * @return the fechas
	 */
	public List<FechaTarjetaEntity> getFechas() {
		return fechas;
	}

	/**
	 * Sets the fechas.
	 *
	 * @param fechas
	 *            the fechas to set
	 */
	public void setFechas(List<FechaTarjetaEntity> fechas) {
		this.fechas = fechas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(fechas);
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
		FechasTarjeta other = (FechasTarjeta) obj;
		return new EqualsBuilder().append(fechas, other.fechas).isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "FechasTarjeta [fechas=" + fechas + "]";
	}

}

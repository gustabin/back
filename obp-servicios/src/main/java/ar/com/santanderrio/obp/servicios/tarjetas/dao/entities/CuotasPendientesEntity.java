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
 * The Class CuotasPendientesEntity. Clase que representa el tag
 * /tarjetas/tarjeta/document/CuotasPendientes/
 */
@XmlRootElement(name = "CuotasPendientes")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuotasPendientesEntity {

	/** The tarjeta list. */
	@XmlElement(name = "tarjeta")
	private List<TarjetaCuotasPendientesEntity> tarjetaList;

	/** The totales. */
	@XmlElement(name = "totales")
	private Totales totales;

	/**
	 * Gets the tarjeta list.
	 *
	 * @return the tarjeta list
	 */
	public List<TarjetaCuotasPendientesEntity> getTarjetaList() {
		return tarjetaList;
	}

	/**
	 * Sets the tarjeta list.
	 *
	 * @param tarjetaList
	 *            the new tarjeta list
	 */
	public void setTarjetaList(List<TarjetaCuotasPendientesEntity> tarjetaList) {
		this.tarjetaList = tarjetaList;
	}

	/**
	 * Gets the totales.
	 *
	 * @return the totales
	 */
	public Totales getTotales() {
		return totales;
	}

	/**
	 * Sets the totales.
	 *
	 * @param totales
	 *            the new totales
	 */
	public void setTotales(Totales totales) {
		this.totales = totales;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(tarjetaList);
		hcb.append(totales);
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
		CuotasPendientesEntity other = (CuotasPendientesEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(tarjetaList, other.getTarjetaList());
		eb.append(totales, other.getTotales());
		return eb.isEquals();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CuotasPendientesEntity [tarjetaList=" + tarjetaList + ", totales=" + totales + "]";
	}

}

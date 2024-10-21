/*
 * 
 */

package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.Totales;

/**
 * The Class DebitosTarjetaEntity. Clase que representa el tag
 * /tarjetas/tarjeta/document/informeDebitosAutomaticos/debitosTarjeta/
 *
 *
 * @author luis.pedro.lopez
 */

@XmlRootElement(name = "debitosTarjeta ")
@XmlAccessorType(XmlAccessType.FIELD)
public class DebitosTarjetaEntity {

	/** The nombre tarjeta. */
	@XmlAttribute
	private String nombreTarjeta;

	/** The totales. */
	@XmlElement(name = "totales")
	private Totales totales;

	/** The debito. */
	@XmlElement(name = "debito")
	private List<DebitoEntity> listDebitos;

	/**
	 * Gets the nombre tarjeta.
	 *
	 * @return the nombreTarjeta
	 */
	public String getNombreTarjeta() {
		return nombreTarjeta;
	}

	/**
	 * Sets the nombre tarjeta.
	 *
	 * @param nombreTarjeta
	 *            the nombreTarjeta to set
	 */
	public void setNombreTarjeta(String nombreTarjeta) {
		this.nombreTarjeta = nombreTarjeta;
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
	 *            the totales to set
	 */
	public void setTotales(Totales totales) {
		this.totales = totales;
	}

	/**
	 * Gets the list debitos.
	 *
	 * @return the listDebitos
	 */
	public List<DebitoEntity> getListDebitos() {
		return listDebitos;
	}

	/**
	 * Sets the list debitos.
	 *
	 * @param listDebitos
	 *            the listDebitos to set
	 */
	public void setListDebitos(List<DebitoEntity> listDebitos) {
		this.listDebitos = listDebitos;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(nombreTarjeta);
		hcb.append(totales);
		hcb.append(listDebitos);
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
		DebitosTarjetaEntity other = (DebitosTarjetaEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(nombreTarjeta, other.getNombreTarjeta());
		eb.append(totales, other.getTotales());
		eb.append(listDebitos, other.getListDebitos());
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
		sb.append(nombreTarjeta);
		sb.append(totales);
		sb.append(listDebitos);
		return sb.toString();
	}
}

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
 * The Class InformeDebitosAutomaticosEntity. Clase que representa el tag
 * /tarjetas/tarjeta/document/informeDebitosAutomaticos/
 *
 * @author luis.pedro.lopez
 */
@XmlRootElement(name = "informeDebitosAutomaticos")
@XmlAccessorType(XmlAccessType.FIELD)
public class InformeDebitosAutomaticosEntity {

	/** The desde. */
	@XmlAttribute
	private String desde;

	/** The hasta. */
	@XmlAttribute
	private String hasta;

	/** The consumo promedio. */
	@XmlElement(name = "consumoPromedio")
	private ConsumoPromedioEntity consumoPromedio;

	/** The totales. */
	@XmlElement(name = "totales")
	private Totales totales;

	/** The debitos tarjeta. */
	@XmlElement(name = "debitosTarjeta")
	private List<DebitosTarjetaEntity> debitosTarjeta;

	/**
	 * Gets the desde.
	 *
	 * @return the desde
	 */
	public String getDesde() {
		return desde;
	}

	/**
	 * Sets the desde.
	 *
	 * @param desde
	 *            the desde to set
	 */
	public void setDesde(String desde) {
		this.desde = desde;
	}

	/**
	 * Gets the hasta.
	 *
	 * @return the hasta
	 */
	public String getHasta() {
		return hasta;
	}

	/**
	 * Sets the hasta.
	 *
	 * @param hasta
	 *            the hasta to set
	 */
	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	/**
	 * Gets the consumo promedio.
	 *
	 * @return the consumoPromedio
	 */
	public ConsumoPromedioEntity getConsumoPromedio() {
		return consumoPromedio;
	}

	/**
	 * Sets the consumo promedio.
	 *
	 * @param consumoPromedio
	 *            the consumoPromedio to set
	 */
	public void setConsumoPromedio(ConsumoPromedioEntity consumoPromedio) {
		this.consumoPromedio = consumoPromedio;
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


	public List<DebitosTarjetaEntity> getDebitosTarjeta() {
		return debitosTarjeta;
	}

	public void setDebitosTarjeta(List<DebitosTarjetaEntity> debitosTarjeta) {
		this.debitosTarjeta = debitosTarjeta;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(desde);
		hcb.append(hasta);
		hcb.append(consumoPromedio);
		hcb.append(totales);
		hcb.append(debitosTarjeta);

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
		InformeDebitosAutomaticosEntity other = (InformeDebitosAutomaticosEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(desde, other.getDesde());
		eb.append(hasta, other.getHasta());
		eb.append(consumoPromedio, other.getConsumoPromedio());
		eb.append(totales, other.getTotales());
		eb.append(debitosTarjeta, other.getDebitosTarjeta());
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
		sb.append("InformeDebitosAutomaticosEntity [desde=");
		sb.append(desde);
		sb.append(", hasta=");
		sb.append(hasta);
		sb.append(", consumoPromedio=");
		sb.append(consumoPromedio);
		sb.append(", totales=");
		sb.append(totales);
		sb.append(", debitosTarjeta=");
		sb.append(debitosTarjeta);
		sb.append("]");
		return sb.toString();
	}

}

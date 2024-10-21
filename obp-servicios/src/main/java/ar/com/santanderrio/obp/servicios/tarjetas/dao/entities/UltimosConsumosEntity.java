/**
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
 * The Class UltimosConsumosEntity. Clase que representa el tag
 * /tarjetas/tarjeta/document/movimientos/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement(name = "movimientos")
@XmlAccessorType(XmlAccessType.FIELD)
public class UltimosConsumosEntity {

	/** The ConsumosCuentasAuth totales. */
	@XmlElement(name = "totales")
	private ConsumosCuentasAuthEntity totales;

	/** The List tarjetaList. */
	@XmlElement(name = "tarjeta")
	private List<TarjetaUltimosConsumosEntity> tarjetaList;

	/**
	 * Getter de totales.
	 * 
	 * @return the totales
	 */
	public ConsumosCuentasAuthEntity getTotales() {
		return totales;
	}

	/**
	 * Setter de totales.
	 * 
	 * @param totales
	 *            the totales to set
	 */
	public void setTotales(ConsumosCuentasAuthEntity totales) {
		this.totales = totales;
	}

	/**
	 * Getter de la lista de tarjeta ultimos movimientos.
	 * 
	 * @return the tarjetaList
	 */
	public List<TarjetaUltimosConsumosEntity> getTarjetaList() {
		return tarjetaList;
	}

	/**
	 * Setter de la lista de tarjeta ultimos movimientos.
	 * 
	 * @param tarjetaList
	 *            the tarjetaList to set
	 */
	public void setTarjetaList(List<TarjetaUltimosConsumosEntity> tarjetaList) {
		this.tarjetaList = tarjetaList;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(totales);
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
		UltimosConsumosEntity other = (UltimosConsumosEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(totales, other.getTotales());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "UltimosConsumosEntity [totales=" + totales + ", tarjetaList=" + tarjetaList + "]";
	}

}

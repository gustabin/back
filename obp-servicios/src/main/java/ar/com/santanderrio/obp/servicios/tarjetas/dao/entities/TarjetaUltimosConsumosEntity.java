/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class TarjetaUltimosConsumosEntity. Clase que representa el tag
 * /tarjetas/tarjeta/document/movimientos/tarjeta/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement(name = "tarjeta")
@XmlAccessorType(XmlAccessType.FIELD)
public class TarjetaUltimosConsumosEntity {

	/** The String codigoTarjeta. */
	@XmlAttribute(name = "codigoTarjeta")
	private String codigoTarjeta;

	/** The String dolares. */
	@XmlElement(name = "dolares")
	private String dolares;

	/** The String pesos. */
	@XmlElement(name = "pesos")
	private String pesos;

	/** The Movimiento List. */
	@XmlElement(name = "movimiento")
	private List<MovimientoEntity> movimientosList;

	/**
	 * Getter de codigoTarjeta.
	 * 
	 * @return the codigoTarjeta
	 */
	public String getCodigoTarjeta() {
		return codigoTarjeta;
	}

	/**
	 * Setter de codigoTarjeta.
	 * 
	 * @param codigoTarjeta
	 *            the codigoTarjeta to set
	 */
	public void setCodigoTarjeta(String codigoTarjeta) {
		this.codigoTarjeta = codigoTarjeta;
	}

	/**
	 * Getter de dolares.
	 * 
	 * @return the dolares
	 */
	public String getDolares() {
		return dolares;
	}

	/**
	 * Setter de dolares.
	 * 
	 * @param dolares
	 *            the dolares to set
	 */
	public void setDolares(String dolares) {
		this.dolares = dolares;
	}

	/**
	 * Getter de pesos.
	 * 
	 * @return the pesos
	 */
	public String getPesos() {
		return pesos;
	}

	/**
	 * Setter de pesos.
	 * 
	 * @param pesos
	 *            the pesos to set
	 */
	public void setPesos(String pesos) {
		this.pesos = pesos;
	}

	/**
	 * Getter de lista de movimientos.
	 * 
	 * @return the movimientosList
	 */
	public List<MovimientoEntity> getMovimientosList() {
		return movimientosList;
	}

	/**
	 * Setter de lista de movimientos.
	 * 
	 * @param movimientosList
	 *            the movimientosList to set
	 */
	public void setMovimientosList(List<MovimientoEntity> movimientosList) {
		this.movimientosList = movimientosList;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigoTarjeta);
		hcb.append(dolares);
		hcb.append(pesos);
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
		TarjetaUltimosConsumosEntity other = (TarjetaUltimosConsumosEntity) obj;
		return new EqualsBuilder().append(codigoTarjeta, other.codigoTarjeta).append(dolares, other.dolares)
				.append(pesos, other.pesos).isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "TarjetaUltimosConsumosEntity [codigoTarjeta=" + codigoTarjeta + ", dolares=" + dolares + ", pesos="
				+ pesos + ", movimientosList=" + movimientosList + "]";
	}

}

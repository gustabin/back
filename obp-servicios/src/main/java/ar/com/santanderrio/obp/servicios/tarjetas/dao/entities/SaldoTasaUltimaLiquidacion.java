/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class SaldoTasaUltimaLiquidacion. Clase que representa el tag
 * /tarjetas/tarjeta/liquidacion/saldos/saldo/ o del tag
 * /tarjetas/tarjeta/liquidacion/tasas/tasa/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SaldoTasaUltimaLiquidacion {

	/** The String descripcion. */
	@XmlElement(name = "descripcion")
	private String descripcion;

	/** The String total. */
	@XmlElement(name = "total")
	private String total;

	/** The String usdTotal. */
	@XmlElement(name = "usdTotal")
	private String usdTotal;

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total
	 *            the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * Gets the usd total.
	 *
	 * @return the usdTotal
	 */
	public String getUsdTotal() {
		return usdTotal;
	}

	/**
	 * Sets the usd total.
	 *
	 * @param usdTotal
	 *            the usdTotal to set
	 */
	public void setUsdTotal(String usdTotal) {
		this.usdTotal = usdTotal;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(descripcion);
		hcb.append(total);
		hcb.append(usdTotal);
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
		SaldoTasaUltimaLiquidacion other = (SaldoTasaUltimaLiquidacion) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(descripcion, other.getDescripcion());
		eb.append(total, other.getTotal());
		eb.append(usdTotal, other.getUsdTotal());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "SaldoTasaUltimaLiquidacion [descripcion=" + descripcion + ", total=" + total + ", usdTotal=" + usdTotal
				+ "]";
	}

}

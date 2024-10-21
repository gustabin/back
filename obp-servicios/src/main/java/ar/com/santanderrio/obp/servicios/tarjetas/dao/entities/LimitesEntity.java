/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.*;

import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.enums.Currency;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class LimiteUltimaLiquidacionEntity. Clase que representa el tag
 * /tarjetas/tarjeta/liquidacion/limites/limite/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement(name = "limite")
@XmlAccessorType(XmlAccessType.FIELD)
public class LimitesEntity {

	/** The String descripcion. */
    @XmlAttribute(name = "descripcion")
	private String descripcion;

	/** The String pesos. */
	@XmlElement(name = "pesos")
	private String pesos;


	/** The String pesos. */
	@XmlTransient
	private Currency moneda;


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
	 * Gets the pesos.
	 *
	 * @return the pesos
	 */
    public String getPesos() {
        return pesos;
    }

    /**
	 * Sets the pesos.
	 *
	 * @param pesos
	 *            the pesos to set
	 */
    public void setPesos(String pesos) {
        this.pesos = pesos;
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
		LimitesEntity other = (LimitesEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(descripcion, other.getDescripcion());
		eb.append(pesos, other.getPesos());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "LimiteUltimaLiquidacionEntity [descripcion=" + descripcion + ", pesos=" + pesos + "]";
	}

	public Currency getMoneda() {
		return moneda;
	}

	public void setMoneda(Currency moneda) {
		this.moneda = moneda;
	}
}

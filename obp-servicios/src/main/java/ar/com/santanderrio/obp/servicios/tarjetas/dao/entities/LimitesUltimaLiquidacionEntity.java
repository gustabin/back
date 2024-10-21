/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class LimitesUltimaLiquidacionEntity. Clase que representa el tag
 * /tarjetas/tarjeta/liquidacion/limites/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement(name = "limites")
@XmlAccessorType(XmlAccessType.FIELD)
public class LimitesUltimaLiquidacionEntity {

	/** The List limites. */
	@XmlElement(name = "limite")
	private List<LimiteUltimaLiquidacionEntity> limites;

	/**
	 * Gets the limites.
	 *
	 * @return the limites
	 */
	public List<LimiteUltimaLiquidacionEntity> getLimites() {
		return limites;
	}

	/**
	 * Sets the limites.
	 *
	 * @param limites
	 *            the limites to set
	 */
	public void setLimites(List<LimiteUltimaLiquidacionEntity> limites) {
		this.limites = limites;
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "LimitesUltimaLiquidacionEntity [limites=" + limites + "]";
	}

}

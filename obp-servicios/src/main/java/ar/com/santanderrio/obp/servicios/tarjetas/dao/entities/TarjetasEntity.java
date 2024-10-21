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
 * The Class TarjetasEntity.
 *
 * @author sergio.e.goldentair
 * 
 *         Clase que representa el tag /tarjetas/
 */
@XmlRootElement(name = "tarjetas")
@XmlAccessorType(XmlAccessType.FIELD)
public class TarjetasEntity {

	/** The tarjeta list. */
	@XmlElement(name = "tarjeta")
	private List<TarjetaEntity> tarjetaList;

	/**
	 * Gets the tarjeta list.
	 *
	 * @return the tarjetaList
	 */
	public List<TarjetaEntity> getTarjetaList() {
		return tarjetaList;
	}

	/**
	 * Sets the tarjeta list.
	 *
	 * @param tarjetaList
	 *            the tarjetaList to set
	 */
	public void setTarjetaList(List<TarjetaEntity> tarjetaList) {
		this.tarjetaList = tarjetaList;
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Tarjetas [tarjetaList=" + tarjetaList + "]";
	}
}

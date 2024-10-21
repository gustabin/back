/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sucursales.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Sucursales.
 */
@XmlRootElement(name = "sucursales")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sucursales {
	/** The sucursales. */
	@XmlElement(name = "cod_entidad")
	private List<Entidad> sucursales;

	/**
	 * Setter para sucursales.
	 *
	 * @param sucursales
	 *            el nuevo sucursales
	 */
	public void setSucursales(List<Entidad> sucursales) {
		this.sucursales = sucursales;
	}

	/**
	 * Gets the sucursales.
	 *
	 * @return the sucursales
	 */
	public List<Entidad> getSucursales() {
		return sucursales;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((sucursales == null) ? 0 : sucursales.hashCode());
		return result;
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
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj.getClass().equals(this.getClass()))) {
			return false;
		}
		Sucursales other = (Sucursales) obj;
		if (sucursales == null) {
			if (other.sucursales != null) {
				return false;
			}
		} else if (!sucursales.equals(other.sucursales)) {
			return false;
		}
		return true;
	}

}
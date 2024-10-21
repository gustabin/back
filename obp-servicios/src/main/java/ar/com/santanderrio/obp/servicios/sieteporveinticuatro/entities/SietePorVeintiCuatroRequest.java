/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class SietePorVeintiCuatroRequest.
 *
 * @author sergio.e.goldentair
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class SietePorVeintiCuatroRequest {

	/** The config. */
	@XmlElement
	private SietePorVeintiCuatroConfigReq config;

	/** The datos. */
	@XmlElement
	private SietePorVeintiCuatroDatosReq datos;

	/**
	 * Gets the config.
	 *
	 * @return the config
	 */
	public SietePorVeintiCuatroConfigReq getConfig() {
		return config;
	}

	/**
	 * Sets the config.
	 *
	 * @param config
	 *            the config to set
	 */
	public void setConfig(SietePorVeintiCuatroConfigReq config) {
		this.config = config;
	}

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public SietePorVeintiCuatroDatosReq getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(SietePorVeintiCuatroDatosReq datos) {
		this.datos = datos;
	}
}
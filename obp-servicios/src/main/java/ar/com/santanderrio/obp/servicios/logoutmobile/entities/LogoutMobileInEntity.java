/**
 * 
 */
package ar.com.santanderrio.obp.servicios.logoutmobile.entities;

import java.io.Serializable;

/**
 * @author sergio.e.goldentair
 *
 */
public class LogoutMobileInEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8484377103527929736L;
	private String datos;

	/**
	 * @param datos
	 */
	public LogoutMobileInEntity(String datos) {
		super();
		this.datos = datos;
	}

	/**
	 * @return the datos
	 */
	public String getDatos() {
		return datos;
	}
}

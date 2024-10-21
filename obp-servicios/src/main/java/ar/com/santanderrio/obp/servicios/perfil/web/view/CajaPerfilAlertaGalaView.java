/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.web.view;

/**
 * The Class CajaPerfilDeudorView.
 */
public class CajaPerfilAlertaGalaView extends CajaPerfil {

	/** The tieneAlertaGala. */
	private Boolean tieneAlertaGala;
	
	/** The nup. */
	private String nup;
	
	/** The nup. */
	private String linkFormPortal;
	

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}



	/**
	 * Gets the link form portal.
	 *
	 * @return the link form portal
	 */
	public String getLinkFormPortal() {
		return linkFormPortal;
	}

	/**
	 * Sets the link form portal.
	 *
	 * @param linkFormPortal the new link form portal
	 */
	public void setLinkFormPortal(String linkFormPortal) {
		this.linkFormPortal = linkFormPortal;
	}

	/**
	 * Gets the tiene alerta gala.
	 *
	 * @return the tiene alerta gala
	 */
	public Boolean getTieneAlertaGala() {
		return tieneAlertaGala;
	}

	/**
	 * Sets the tiene alerta gala.
	 *
	 * @param tieneAlertaGala the new tiene alerta gala
	 */
	public void setTieneAlertaGala(Boolean tieneAlertaGala) {
		this.tieneAlertaGala = tieneAlertaGala;
	}

	/**
	 * Instantiates a new caja perfil alerta gala.
	 */
	public CajaPerfilAlertaGalaView() {
		tieneAlertaGala = true;
	}

}

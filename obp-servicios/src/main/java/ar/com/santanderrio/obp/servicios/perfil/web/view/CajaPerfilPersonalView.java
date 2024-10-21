/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.web.view;

/**
 * The Class CajaPerfilPersonalView.
 */
public class CajaPerfilPersonalView extends CajaPerfil {

	/** The is personal. */
	private Boolean isPersonal;

	/**
	 * Gets the checks if is personal.
	 *
	 * @return the checks if is personal
	 */
	public Boolean getIsPersonal() {
		return isPersonal;
	}

	/**
	 * Sets the checks if is personal.
	 *
	 * @param isPersonal
	 *            the new checks if is personal
	 */
	public void setIsPersonal(Boolean isPersonal) {
		this.isPersonal = isPersonal;
	}

	/**
	 * Instantiates a new caja perfil personal view.
	 */
	public CajaPerfilPersonalView() {
		isPersonal = true;
	}

}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.web.view;

/**
 * The Class CajaPerfilDeudorView.
 */
public class CajaPerfilDeudorView extends CajaPerfil {

	/** The is deudor. */
	private Boolean isDeudor;

	/** The situacion. */
	private String situacion;

	/**
	 * Gets the checks if is deudor.
	 *
	 * @return the checks if is deudor
	 */
	public Boolean getIsDeudor() {
		return isDeudor;
	}

	/**
	 * Sets the checks if is deudor.
	 *
	 * @param isDeudor
	 *            the new checks if is deudor
	 */
	public void setIsDeudor(Boolean isDeudor) {
		this.isDeudor = isDeudor;
	}

	/**
	 * Gets the situacion.
	 *
	 * @return the situacion
	 */
	public String getSituacion() {
		return situacion;
	}

	/**
	 * Sets the situacion.
	 *
	 * @param situacion
	 *            the new situacion
	 */
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	/**
	 * Instantiates a new caja perfil deudor view.
	 */
	public CajaPerfilDeudorView() {
		isDeudor = true;
	}

}

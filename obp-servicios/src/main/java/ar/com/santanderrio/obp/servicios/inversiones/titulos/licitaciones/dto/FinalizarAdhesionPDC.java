/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto;

/**
 * The Class FinalizarAdhesionPDC.
 */
public class FinalizarAdhesionPDC{

	/** The id sim cuenta pdc. */
	private String idSimCuentaPdc;

	/** The tilde. */
	private boolean tilde;
	
	/**
	 * Gets the id sim cuenta pdc.
	 *
	 * @return the id sim cuenta pdc
	 */
	public String getIdSimCuentaPdc() {
		return idSimCuentaPdc;
	}

	/**
	 * Sets the id sim cuenta pdc.
	 *
	 * @param idSimCuentaPdc
	 *            the new id sim cuenta pdc
	 */
	public void setIdSimCuentaPdc(String idSimCuentaPdc) {
		this.idSimCuentaPdc = idSimCuentaPdc;
	}

	/**
	 * Checks if is tilde.
	 *
	 * @return true, if is tilde
	 */
	public boolean isTilde() {
		return tilde;
	}

	/**
	 * Sets the tilde.
	 *
	 * @param tilde
	 *            the new tilde
	 */
	public void setTilde(boolean tilde) {
		this.tilde = tilde;
	}
	
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

/**
 * The Class BaseFondoAbstractDTO.
 */
public abstract class BaseFondoAbstractDTO {

	/** The fechaHora. */
	private String fechaHora;

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fecha hora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the new fecha hora
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

}

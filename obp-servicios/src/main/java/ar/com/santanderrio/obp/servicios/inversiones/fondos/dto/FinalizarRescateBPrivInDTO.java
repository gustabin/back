/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

/**
 * The Class FinalizarRescateBPrivInDTO.
 * 
 * @author marcelo.ruiz
 */
public class FinalizarRescateBPrivInDTO extends FinalizarRescateInDTO {

	/** The dentro del perfil. */
	private String dentroDelPerfil;

	/**
	 * Gets the dentro del perfil.
	 *
	 * @return the dentro del perfil
	 */
	public String getDentroDelPerfil() {
		return dentroDelPerfil;
	}

	/**
	 * Sets the dentro del perfil.
	 *
	 * @param dentroPerfil
	 *            the new dentro del perfil
	 */
	public void setDentroDelPerfil(String dentroPerfil) {
		this.dentroDelPerfil = dentroPerfil;
	}
}

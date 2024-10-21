/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;

/**
 * The Class BanelcoOperacionDTO.
 */
public class BanelcoOperacionDTO extends OperacionDTO {

	/** The digitos. */
	private String digitos;

	/** The ingreso digitos. */
	private String ingresoDigitos;

	/**
	 * Instantiates a new banelco operacion DTO.
	 */
	public BanelcoOperacionDTO() {
		super(TipoDesafioEnum.BANELCO);
	}

	/**
	 * Gets the digitos.
	 *
	 * @return the digitos
	 */
	public String getDigitos() {
		return digitos;
	}

	/**
	 * Sets the digitos.
	 *
	 * @param digitos
	 *            the new digitos
	 */
	public void setDigitos(String digitos) {
		this.digitos = digitos;
	}

	/**
	 * Gets the ingreso digitos.
	 *
	 * @return the ingreso digitos
	 */
	public String getIngresoDigitos() {
		return ingresoDigitos;
	}

	/**
	 * Sets the ingreso digitos.
	 *
	 * @param ingresoDigitos
	 *            the new ingreso digitos
	 */
	public void setIngresoDigitos(String ingresoDigitos) {
		this.ingresoDigitos = ingresoDigitos;
	}

}

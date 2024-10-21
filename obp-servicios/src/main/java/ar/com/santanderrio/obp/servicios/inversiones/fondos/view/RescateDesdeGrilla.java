/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

/**
 * The Class RescateDesdeGrilla.
 *
 * @author
 */
public class RescateDesdeGrilla {

	/** The Plazo Efectivo. */
	private String plazoEfectivo;

	/** The cuenta operativa sin formatear. */
	private String cuentaOperativaSinFormatear;

	/** The limite minimo rescate. */
	private String limiteMinimoRescate;

	/** The limite maximo rescate. */
	private String limiteMaximoRescate;

	/** The Numero de Cuenta Titulo. */
	private String numeroDeCuentaTitulo;

	private boolean fondoNuevo;

	private String soloEspecie;
	
	private boolean puedeRescatar;
	
	

	/**
	 * @return the puedeRescatar
	 */
	public boolean isPuedeRescatar() {
		return puedeRescatar;
	}

	/**
	 * @param puedeRescatar the puedeRescatar to set
	 */
	public void setPuedeRescatar(boolean puedeRescatar) {
		this.puedeRescatar = puedeRescatar;
	}

	/**
	 * Gets the plazo efectivo.
	 *
	 * @return the plazo efectivo
	 */
	public String getPlazoEfectivo() {
		return plazoEfectivo;
	}

	/**
	 * Sets the plazo efectivo.
	 *
	 * @param plazoEfectivo the new plazo efectivo
	 */
	public void setPlazoEfectivo(String plazoEfectivo) {
		this.plazoEfectivo = plazoEfectivo;
	}

	/**
	 * Gets the cuenta operativa sin formatear.
	 *
	 * @return the cuenta operativa sin formatear
	 */
	public String getCuentaOperativaSinFormatear() {
		return cuentaOperativaSinFormatear;
	}

	/**
	 * Sets the cuenta operativa sin formatear.
	 *
	 * @param cuentaOperativaSinFormatear the new cuenta operativa sin formatear
	 */
	public void setCuentaOperativaSinFormatear(String cuentaOperativaSinFormatear) {
		this.cuentaOperativaSinFormatear = cuentaOperativaSinFormatear;
	}

	/**
	 * Gets the limite minimo rescate.
	 *
	 * @return the limite minimo rescate
	 */
	public String getLimiteMinimoRescate() {
		return limiteMinimoRescate;
	}

	/**
	 * Sets the limite minimo rescate.
	 *
	 * @param limiteMinimoRescate the new limite minimo rescate
	 */
	public void setLimiteMinimoRescate(String limiteMinimoRescate) {
		this.limiteMinimoRescate = limiteMinimoRescate;
	}

	/**
	 * Gets the limite maximo rescate.
	 *
	 * @return the limite maximo rescate
	 */
	public String getLimiteMaximoRescate() {
		return limiteMaximoRescate;
	}

	/**
	 * Sets the limite maximo rescate.
	 *
	 * @param limiteMaximoRescate the new limite maximo rescate
	 */
	public void setLimiteMaximoRescate(String limiteMaximoRescate) {
		this.limiteMaximoRescate = limiteMaximoRescate;
	}

	/**
	 * Gets the numero de cuenta titulo.
	 *
	 * @return the numero de cuenta titulo
	 */
	public String getNumeroDeCuentaTitulo() {
		return numeroDeCuentaTitulo;
	}

	/**
	 * Sets the numero de cuenta titulo.
	 *
	 * @param numeroDeCuentaTitulo the new numero de cuenta titulo
	 */
	public void setNumeroDeCuentaTitulo(String numeroDeCuentaTitulo) {
		this.numeroDeCuentaTitulo = numeroDeCuentaTitulo;
	}

	public boolean isFondoNuevo() {
		return fondoNuevo;
	}

	public void setFondoNuevo(boolean fondoNuevo) {
		this.fondoNuevo = fondoNuevo;
	}

	public String getSoloEspecie() {
		return soloEspecie;
	}

	public void setSoloEspecie(String soloEspecie) {
		this.soloEspecie = soloEspecie;
	}
}

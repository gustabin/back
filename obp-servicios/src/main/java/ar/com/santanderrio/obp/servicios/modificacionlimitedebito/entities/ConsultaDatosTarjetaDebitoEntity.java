/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities;

/**
 * The Class ConsultaDatosTarjetaDebitoEntity.
 */
public class ConsultaDatosTarjetaDebitoEntity {

	/** The clase tarjeta debito. */
	private String claseTarjetaDebito;

	/** The cod error. */
	private Integer codError;

	/** The tiene error. */
	private Boolean tieneError = Boolean.FALSE;

	/**
	 * Instantiates a new consulta datos tarjeta debito entity.
	 */
	public ConsultaDatosTarjetaDebitoEntity() {
		super();
	}

	/**
	 * Instantiates a new consulta datos tarjeta debito entity.
	 *
	 * @param codError
	 *            the cod error
	 * @param tieneError
	 *            the tiene error
	 */
	public ConsultaDatosTarjetaDebitoEntity(Integer codError, Boolean tieneError) {
		super();
		this.codError = codError;
		this.tieneError = tieneError;
	}

	/**
	 * Gets the clase tarjeta debito.
	 *
	 * @return the clase tarjeta debito
	 */
	public String getClaseTarjetaDebito() {
		return claseTarjetaDebito;
	}

	/**
	 * Sets the clase tarjeta debito.
	 *
	 * @param claseTarjetaDebito
	 *            the new clase tarjeta debito
	 */
	public void setClaseTarjetaDebito(String claseTarjetaDebito) {
		this.claseTarjetaDebito = claseTarjetaDebito;
	}

	/**
	 * Gets the cod error.
	 *
	 * @return the cod error
	 */
	public Integer getCodError() {
		return codError;
	}

	/**
	 * Sets the cod error.
	 *
	 * @param codError
	 *            the new cod error
	 */
	public void setCodError(Integer codError) {
		this.codError = codError;
	}

	/**
	 * Gets the tiene error.
	 *
	 * @return the tiene error
	 */
	public Boolean getTieneError() {
		return tieneError;
	}

	/**
	 * Sets the tiene error.
	 *
	 * @param tieneError
	 *            the new tiene error
	 */
	public void setTieneError(Boolean tieneError) {
		this.tieneError = tieneError;
	}

}

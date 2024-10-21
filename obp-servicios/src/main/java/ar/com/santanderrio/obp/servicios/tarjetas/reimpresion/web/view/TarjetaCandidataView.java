/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view;

/**
 * The Class TarjetaCandidataView.
 */
public class TarjetaCandidataView {

	/** The nro tarjeta con formato. */
	private String nroTarjetaConFormato;

	/** The nro. */
	private String nro;

	/** The alias. */
	private String alias;

	/** The tipoCuentaDescripcion. */
	private String tipoCuentaDescripcion;

	/** * The titular *. */
	private String titular;
	
	private Boolean puedeSerContactless = Boolean.FALSE;
	

	/**
	 * Gets the nro tarjeta con formato.
	 *
	 * @return the nroTarjetaConFormato
	 */
	public String getNroTarjetaConFormato() {
		return nroTarjetaConFormato;
	}

	/**
	 * Sets the nro tarjeta con formato.
	 *
	 * @param nroTarjetaConFormato
	 *            the nroTarjetaConFormato to set
	 */
	public void setNroTarjetaConFormato(String nroTarjetaConFormato) {
		this.nroTarjetaConFormato = nroTarjetaConFormato;
	}

	/**
	 * Gets the nro.
	 *
	 * @return the nro
	 */
	public String getNro() {
		return nro;
	}

	/**
	 * Sets the nro.
	 *
	 * @param nro
	 *            the nro to set
	 */
	public void setNro(String nro) {
		this.nro = nro;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the tipoCuentaDescripcion.
	 *
	 * @return tipoCuentaDescripcion
	 */
	public String getTipoCuentaDescripcion() {
		return tipoCuentaDescripcion;
	}

	/**
	 * Sets the tipoCuentaDescripcion.
	 *
	 * @param tipoCuentaDescripcion
	 *            the new tipo cuenta descripcion
	 */
	public void setTipoCuentaDescripcion(String tipoCuentaDescripcion) {
		this.tipoCuentaDescripcion = tipoCuentaDescripcion;
	}

	/**
	 * Gets the titular.
	 *
	 * @return titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            to set
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Boolean getPuedeSerContactless() {
		return puedeSerContactless;
	}

	public void setPuedeSerContactless(Boolean puedeSerContactless) {
		this.puedeSerContactless = puedeSerContactless;
	}
	
}

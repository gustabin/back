/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;

/**
 * The Class TarjetasAdhesionDebitoView.
 * 
 * @author mariano.g.pulera
 * 
 */
public class TarjetasAdhesionDebitoView {

	/** The tipo tarjeta. */
	private String tipoTarjeta;

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The alias. */
	private String alias;

	/**
	 * Instantiates a new tarjetas adhesion debito view.
	 */
	public TarjetasAdhesionDebitoView() {
		super();
	}

	/**
	 * Instantiates a new tarjetas adhesion debito view.
	 *
	 * @param tipoCuentaTarjeta
	 *            the tipo cuenta tarjeta
	 * @param cuentaAlias
	 *            the cuenta alias
	 * @param nroTarjetaEnmascarado
	 *            the nro tarjeta enmascarado
	 */
	public TarjetasAdhesionDebitoView(TipoCuentaTarjeta tipoCuentaTarjeta, String cuentaAlias,
			String nroTarjetaEnmascarado) {
		this.setNumeroTarjeta(tipoCuentaTarjeta.getAbreviatura() + " " + nroTarjetaEnmascarado);
		this.setTipoTarjeta(tipoCuentaTarjeta.getCodigo());
		this.setAlias(StringUtils.isNotBlank(cuentaAlias)
				? (tipoCuentaTarjeta.getAbreviatura() + " " + "\"" + cuentaAlias + "\"") : StringUtils.EMPTY);
	}

	/**
	 * Gets the tipo tarjeta.
	 *
	 * @return the tipo tarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Sets the tipo tarjeta.
	 *
	 * @param tipoTarjeta
	 *            the new tipo tarjeta
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numero tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the new numero tarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
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
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

}

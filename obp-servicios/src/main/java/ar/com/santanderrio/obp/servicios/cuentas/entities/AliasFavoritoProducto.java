/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Class AliasFavoritoProducto.
 */
public class AliasFavoritoProducto {

	/** The nup. */
	private Long nup;

	/** The alias. */
	private String alias;

	/** The nro cta producto. */
	private String nroCtaProducto;

	/** The favorita. */
	private Boolean favorita;

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public Long getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(Long nup) {
		this.nup = nup;
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

	/**
	 * Gets the nro cta producto.
	 *
	 * @return the nro cta producto
	 */
	public String getNroCtaProducto() {
		return nroCtaProducto;
	}

	/**
	 * Sets the nro cta producto.
	 *
	 * @param nroCtaProducto
	 *            the new nro cta producto
	 */
	public void setNroCtaProducto(String nroCtaProducto) {
		this.nroCtaProducto = nroCtaProducto;
	}

	/**
	 * Gets the favorita.
	 *
	 * @return the favorita
	 */
	public Boolean getFavorita() {
		return favorita;
	}

	/**
	 * Sets the favorita.
	 *
	 * @param favorita
	 *            the new favorita
	 */
	public void setFavorita(Boolean favorita) {
		this.favorita = favorita;
	}
}

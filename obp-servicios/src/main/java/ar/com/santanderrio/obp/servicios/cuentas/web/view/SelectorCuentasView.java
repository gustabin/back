/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class CuentasView.
 */
public class SelectorCuentasView {

	/** The cuentas. */
	private List<CuentaSelectorDetalleView> cuentas;

	/** The selected. */
	private int selected;

	/** The has cuentas activas. */
	private Boolean hasCuentasActivas = false;

	/** The mensaje favorito. */
	private String mensajeFavorito;

	/** The mensaje no favorito. */
	private String mensajeNoFavorito;

	/** The permite favorito. */
	private Boolean permiteFavorito;

	/** The legales. */
	private String legales;
	
	/** The unica cuenta. */
	private Boolean unicaCuenta = Boolean.FALSE;

	/**
	 * Gets the checks for cuentas activas.
	 *
	 * @return the checks for cuentas activas
	 */
	public Boolean getHasCuentasActivas() {
		return hasCuentasActivas;
	}

	/**
	 * Setter para checks for cuentas activas.
	 *
	 * @param hasCuentasActivas
	 *            el nuevo checks for cuentas activas
	 */
	public void setHasCuentasActivas(Boolean hasCuentasActivas) {
		this.hasCuentasActivas = hasCuentasActivas;
	}

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentaSelectorDetalleView> getCuentas() {
		return cuentas;
	}

	/**
	 * Setter para cuentas.
	 *
	 * @param cuentas
	 *            el nuevo cuentas
	 */
	public void setCuentas(List<CuentaSelectorDetalleView> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the selected.
	 *
	 * @return the selected
	 */
	public int getSelected() {
		return selected;
	}

	/**
	 * Setter para selected.
	 *
	 * @param selected
	 *            el nuevo selected
	 */
	public void setSelected(int selected) {
		this.selected = selected;
	}

	/**
	 * Gets the mensaje favorito.
	 *
	 * @return the mensaje favorito
	 */
	public String getMensajeFavorito() {
		return mensajeFavorito;
	}

	/**
	 * Sets the mensaje favorito.
	 *
	 * @param mensajeFavorito
	 *            the new mensaje favorito
	 */
	public void setMensajeFavorito(String mensajeFavorito) {
		this.mensajeFavorito = mensajeFavorito;
	}

	/**
	 * Gets the mensaje no favorito.
	 *
	 * @return the mensaje no favorito
	 */
	public String getMensajeNoFavorito() {
		return mensajeNoFavorito;
	}

	/**
	 * Sets the mensaje no favorito.
	 *
	 * @param mensajeNoFavorito
	 *            the new mensaje no favorito
	 */
	public void setMensajeNoFavorito(String mensajeNoFavorito) {
		this.mensajeNoFavorito = mensajeNoFavorito;
	}

	/**
	 * Gets the permite favorito.
	 *
	 * @return the permite favorito
	 */
	public Boolean getPermiteFavorito() {
		return permiteFavorito;
	}

	/**
	 * Sets the permite favorito.
	 *
	 * @param permiteFavorito
	 *            the new permite favorito
	 */
	public void setPermiteFavorito(Boolean permiteFavorito) {
		this.permiteFavorito = permiteFavorito;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 * 
	 * @author emilio.watemberg
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cuentas);
		hcb.append(hasCuentasActivas);
		hcb.append(selected);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * @author emilio.watemberg
	 */
	@Override
	public boolean equals(Object obj) {
		SelectorCuentasView other = (SelectorCuentasView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cuentas, other.getCuentas());
		eb.append(hasCuentasActivas, other.getHasCuentasActivas());
		eb.append(selected, other.getSelected());
		eb.append(cuentas, other.getCuentas());
		eb.append(cuentas, other.getCuentas());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CuentasView [cuentas=" + cuentas + ", selected=" + selected + ", hasCuentasActivas=" + hasCuentasActivas
				+ "]";
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * Gets the unica cuenta.
	 *
	 * @return the unica cuenta
	 */
	public Boolean getUnicaCuenta() {
		return unicaCuenta;
	}

	/**
	 * Sets the unica cuenta.
	 *
	 * @param unicaCuenta
	 *            the new unica cuenta
	 */
	public void setUnicaCuenta(Boolean unicaCuenta) {
		this.unicaCuenta = unicaCuenta;
	}
	
}

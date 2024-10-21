/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

import java.util.List;

/**
 * The Class InfoTarjetaAdhesionDebitoView.
 */
public class InfoTarjetaAdhesionDebitoView {

	/** The cuentas. */
	private List<CuentasAdhesionDebitoAutomaticoView> cuentas;

	/** The tarjetas. */
	private List<TarjetasAdhesionDebitoView> tarjetas;

	/** The mensaje adhesion total. */
	private String mensajeAdhesionTotal;

	/** The mensaje adhesion minimo. */
	private String mensajeAdhesionMinimo;

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentasAdhesionDebitoAutomaticoView> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the new cuentas
	 */
	public void setCuentas(List<CuentasAdhesionDebitoAutomaticoView> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<TarjetasAdhesionDebitoView> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the new tarjetas
	 */
	public void setTarjetas(List<TarjetasAdhesionDebitoView> tarjetas) {
		this.tarjetas = tarjetas;
	}

	/**
	 * Gets the mensaje adhesion total.
	 *
	 * @return the mensaje adhesion total
	 */
	public String getMensajeAdhesionTotal() {
		return mensajeAdhesionTotal;
	}

	/**
	 * Sets the mensaje adhesion total.
	 *
	 * @param mensajeAdhesionTotal
	 *            the new mensaje adhesion total
	 */
	public void setMensajeAdhesionTotal(String mensajeAdhesionTotal) {
		this.mensajeAdhesionTotal = mensajeAdhesionTotal;
	}

	/**
	 * Gets the mensaje adhesion minimo.
	 *
	 * @return the mensaje adhesion minimo
	 */
	public String getMensajeAdhesionMinimo() {
		return mensajeAdhesionMinimo;
	}

	/**
	 * Sets the mensaje adhesion minimo.
	 *
	 * @param mensajeAdhesionMinimo
	 *            the new mensaje adhesion minimo
	 */
	public void setMensajeAdhesionMinimo(String mensajeAdhesionMinimo) {
		this.mensajeAdhesionMinimo = mensajeAdhesionMinimo;
	}

}

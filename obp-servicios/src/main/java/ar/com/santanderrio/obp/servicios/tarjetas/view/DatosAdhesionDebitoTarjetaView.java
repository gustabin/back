/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

/**
 * The Class DatosAdhesionDebitoTarjetaView.
 */
public class DatosAdhesionDebitoTarjetaView {

	/** The nro tarjeta enmascarado. */
	private String nroTarjetaEnmascarado;

	/** The nro cuenta formateado. */
	private String nroCuentaFormateado;

	/** The cbu. */
	private String cbu;

	/** The is adhesion total. */
	private Boolean isAdhesionTotal;

	/** The is adhesion minimo. */
	private Boolean isAdhesionMinimo;

	/** The reintentar. */
	private String reintentar;

	/**
	 * Gets the nro tarjeta enmascarado.
	 *
	 * @return the nro tarjeta enmascarado
	 */
	public String getNroTarjetaEnmascarado() {
		return nroTarjetaEnmascarado;
	}

	/**
	 * Sets the nro tarjeta enmascarado.
	 *
	 * @param nroTarjetaEnmascarado
	 *            the new nro tarjeta enmascarado
	 */
	public void setNroTarjetaEnmascarado(String nroTarjetaEnmascarado) {
		this.nroTarjetaEnmascarado = nroTarjetaEnmascarado;
	}

	/**
	 * Gets the nro cuenta formateado.
	 *
	 * @return the nro cuenta formateado
	 */
	public String getNroCuentaFormateado() {
		return nroCuentaFormateado;
	}

	/**
	 * Sets the nro cuenta formateado.
	 *
	 * @param nroCuentaFormateado
	 *            the new nro cuenta formateado
	 */
	public void setNroCuentaFormateado(String nroCuentaFormateado) {
		this.nroCuentaFormateado = nroCuentaFormateado;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the new cbu
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the checks if is adhesion total.
	 *
	 * @return the checks if is adhesion total
	 */
	public Boolean getIsAdhesionTotal() {
		return isAdhesionTotal;
	}

	/**
	 * Sets the checks if is adhesion total.
	 *
	 * @param isAdhesionTotal
	 *            the new checks if is adhesion total
	 */
	public void setIsAdhesionTotal(Boolean isAdhesionTotal) {
		this.isAdhesionTotal = isAdhesionTotal;
	}

	/**
	 * Gets the checks if is adhesion minimo.
	 *
	 * @return the checks if is adhesion minimo
	 */
	public Boolean getIsAdhesionMinimo() {
		return isAdhesionMinimo;
	}

	/**
	 * Sets the checks if is adhesion minimo.
	 *
	 * @param isAdhesionMinimo
	 *            the new checks if is adhesion minimo
	 */
	public void setIsAdhesionMinimo(Boolean isAdhesionMinimo) {
		this.isAdhesionMinimo = isAdhesionMinimo;
	}

	/**
	 * Gets the reintentar.
	 *
	 * @return the reintentar
	 */
	public String getReintentar() {
		return reintentar;
	}

	/**
	 * Sets the reintentar.
	 *
	 * @param reintentar
	 *            the new reintentar
	 */
	public void setReintentar(String reintentar) {
		this.reintentar = reintentar;
	}

}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity;

import java.math.BigDecimal;

/**
 * The Class TraspasoManualDTO.
 */
public class TraspasoManualDTO {

	/** The sucursal cuenta. */
	private String sucursalCuenta;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The indicador CA. */
	private IndicadorDebitoCreditoEnum indicadorCA;

	/** The indicador CC. */
	private IndicadorDebitoCreditoEnum indicadorCC;

	/** The importe. */
	private BigDecimal importe;

	/** The nio. */
	private String nio;

	/** The centro origen. */
	private String centroOrigen;

	/**
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the indicador CA.
	 *
	 * @return the indicador CA
	 */
	public IndicadorDebitoCreditoEnum getIndicadorCA() {
		return indicadorCA;
	}

	/**
	 * Sets the indicador CA.
	 *
	 * @param indicadorCA
	 *            the new indicador CA
	 */
	public void setIndicadorCA(IndicadorDebitoCreditoEnum indicadorCA) {
		this.indicadorCA = indicadorCA;
	}

	/**
	 * Gets the indicador CC.
	 *
	 * @return the indicador CC
	 */
	public IndicadorDebitoCreditoEnum getIndicadorCC() {
		return indicadorCC;
	}

	/**
	 * Sets the indicador CC.
	 *
	 * @param indicadorCC
	 *            the new indicador CC
	 */
	public void setIndicadorCC(IndicadorDebitoCreditoEnum indicadorCC) {
		this.indicadorCC = indicadorCC;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the nio.
	 *
	 * @return the nio
	 */
	public String getNio() {
		return nio;
	}

	/**
	 * Sets the nio.
	 *
	 * @param nio
	 *            the new nio
	 */
	public void setNio(String nio) {
		this.nio = nio;
	}

	/**
	 * Gets the centro origen.
	 *
	 * @return the centro origen
	 */
	public String getCentroOrigen() {
		return centroOrigen;
	}

	/**
	 * Sets the centro origen.
	 *
	 * @param centroOrigen
	 *            the new centro origen
	 */
	public void setCentroOrigen(String centroOrigen) {
		this.centroOrigen = centroOrigen;
	}

}

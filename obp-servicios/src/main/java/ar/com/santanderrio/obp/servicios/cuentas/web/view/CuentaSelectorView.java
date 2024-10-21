/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class CuentaSelectorView.
 */
public class CuentaSelectorView {

	/** The descripcion tipo cuenta. */
	private String descripcionTipoCuenta;

	/** The numero cuenta. */
	private String numero;

	/** The saldo pesos. */
	private String saldoPesos;

	/** The saldo pesos value. */
	private BigDecimal saldoPesosValue;

	/** The saldo dolares. */
	private String saldoDolares;

	/** The saldo dolares value. */
	private BigDecimal saldoDolaresValue;

	/** The saldo descubierto. */
	private String saldoDescubierto;

	/** The saldo descubierto value. */
	private BigDecimal saldoDescubiertoValue;

	/** The saldo caja ahorro. */
	private String saldoCajaAhorro;

	/** The saldo caja ahorro value. */
	private BigDecimal saldoCajaAhorroValue;

	/** The saldo cuenta corriente. */
	private String saldoCuentaCorriente;

	/** The saldo cuenta corriente value. */
	private BigDecimal saldoCuentaCorrienteValue;

	/** The is cerrada. */
	private Boolean isCerrada;

	/** The is cuenta unica. */
	private Boolean isCuentaUnica;

	/** The is cuenta sueldo. */
	private Boolean isCuentaSueldo;

	/** The is descubierto utilizado. */
	private Boolean isDescubiertoUtilizado;

	/** The show descubierto. */
	private Boolean showDescubierto;

	/** The alias. */
	private String alias;

	/** The is favorita. */
	private Boolean isFavorito;
	
	/** The cbu. */
	private String cbu;
	
	/** The has alias. */
	private Boolean hasAlias;

	/** The Constant GUION. */
	protected static final String GUION = "-";

	/** The Constant SOBREGIRO. */
	protected static final String SOBREGIRO = "S";

	/** The resumen detalle cuenta. */
	protected ResumenDetalleCuenta resumenDetalleCuenta;

	/**
	 * Builds the.
	 *
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 */
	public void build(ResumenDetalleCuenta resumenDetalleCuenta) {
		this.resumenDetalleCuenta = resumenDetalleCuenta;
		this.descripcionTipoCuenta = TipoCuenta.fromCodigo(resumenDetalleCuenta.getTipoCuenta())
				.getDescripcionConMoneda();
		this.numero = resumenDetalleCuenta.getNumeroCuentaYSucursal();
		this.isCuentaUnica = resumenDetalleCuenta.isCuentaUnica();
		this.isCerrada = resumenDetalleCuenta.isCuentaCerrada();
		this.alias = resumenDetalleCuenta.getAlias();
		this.isFavorito = resumenDetalleCuenta.isFavorita();
		this.isCuentaSueldo = resumenDetalleCuenta.isConvenioPAS();
	}

	/**
	 * Gets the descripcion tipo cuenta.
	 *
	 * @return the descripcion tipo cuenta
	 */
	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	/**
	 * Sets the descripcion tipo cuenta.
	 *
	 * @param descripcionTipoCuenta
	 *            the new descripcion tipo cuenta
	 */
	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumero(String numeroCuenta) {
		this.numero = numeroCuenta;
	}

	/**
	 * Gets the saldo pesos.
	 *
	 * @return the saldo pesos
	 */
	public String getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Sets the saldo pesos.
	 *
	 * @param saldoPesos
	 *            the new saldo pesos
	 */
	public void setSaldoPesos(String saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

	/**
	 * Gets the saldo pesos value.
	 *
	 * @return the saldo pesos value
	 */
	public BigDecimal getSaldoPesosValue() {
		return saldoPesosValue;
	}

	/**
	 * Sets the saldo pesos value.
	 *
	 * @param saldoPesosValue
	 *            the new saldo pesos value
	 */
	public void setSaldoPesosValue(BigDecimal saldoPesosValue) {
		this.saldoPesosValue = saldoPesosValue;
	}

	/**
	 * Gets the saldo dolares.
	 *
	 * @return the saldo dolares
	 */
	public String getSaldoDolares() {
		return saldoDolares;
	}

	/**
	 * Sets the saldo dolares.
	 *
	 * @param saldoDolares
	 *            the new saldo dolares
	 */
	public void setSaldoDolares(String saldoDolares) {
		this.saldoDolares = saldoDolares;
	}

	/**
	 * Gets the saldo dolares value.
	 *
	 * @return the saldo dolares value
	 */
	public BigDecimal getSaldoDolaresValue() {
		return saldoDolaresValue;
	}

	/**
	 * Sets the saldo dolares value.
	 *
	 * @param saldoDolaresValue
	 *            the new saldo dolares value
	 */
	public void setSaldoDolaresValue(BigDecimal saldoDolaresValue) {
		this.saldoDolaresValue = saldoDolaresValue;
	}

	/**
	 * Gets the saldo descubierto.
	 *
	 * @return the saldo descubierto
	 */
	public String getSaldoDescubierto() {
		return saldoDescubierto;
	}

	/**
	 * Sets the saldo descubierto.
	 *
	 * @param saldoDescubierto
	 *            the new saldo descubierto
	 */
	public void setSaldoDescubierto(String saldoDescubierto) {
		this.saldoDescubierto = saldoDescubierto;
	}

	/**
	 * Gets the saldo descubierto value.
	 *
	 * @return the saldo descubierto value
	 */
	public BigDecimal getSaldoDescubiertoValue() {
		return saldoDescubiertoValue;
	}

	/**
	 * Sets the saldo descubierto value.
	 *
	 * @param saldoDescubiertoValue
	 *            the new saldo descubierto value
	 */
	public void setSaldoDescubiertoValue(BigDecimal saldoDescubiertoValue) {
		this.saldoDescubiertoValue = saldoDescubiertoValue;
	}

	/**
	 * Gets the saldo caja ahorro.
	 *
	 * @return the saldo caja ahorro
	 */
	public String getSaldoCajaAhorro() {
		return saldoCajaAhorro;
	}

	/**
	 * Sets the saldo caja ahorro.
	 *
	 * @param saldoCajaAhorro
	 *            the new saldo caja ahorro
	 */
	public void setSaldoCajaAhorro(String saldoCajaAhorro) {
		this.saldoCajaAhorro = saldoCajaAhorro;
	}

	/**
	 * Gets the saldo caja ahorro value.
	 *
	 * @return the saldo caja ahorro value
	 */
	public BigDecimal getSaldoCajaAhorroValue() {
		return saldoCajaAhorroValue;
	}

	/**
	 * Sets the saldo caja ahorro value.
	 *
	 * @param saldoCajaAhorroValue
	 *            the new saldo caja ahorro value
	 */
	public void setSaldoCajaAhorroValue(BigDecimal saldoCajaAhorroValue) {
		this.saldoCajaAhorroValue = saldoCajaAhorroValue;
	}

	/**
	 * Gets the saldo cuenta corriente.
	 *
	 * @return the saldo cuenta corriente
	 */
	public String getSaldoCuentaCorriente() {
		return saldoCuentaCorriente;
	}

	/**
	 * Sets the saldo cuenta corriente.
	 *
	 * @param saldoCuentaCorriente
	 *            the new saldo cuenta corriente
	 */
	public void setSaldoCuentaCorriente(String saldoCuentaCorriente) {
		this.saldoCuentaCorriente = saldoCuentaCorriente;
	}

	/**
	 * Gets the saldo cuenta corriente value.
	 *
	 * @return the saldo cuenta corriente value
	 */
	public BigDecimal getSaldoCuentaCorrienteValue() {
		return saldoCuentaCorrienteValue;
	}

	/**
	 * Sets the saldo cuenta corriente value.
	 *
	 * @param saldoCuentaCorrienteValue
	 *            the new saldo cuenta corriente value
	 */
	public void setSaldoCuentaCorrienteValue(BigDecimal saldoCuentaCorrienteValue) {
		this.saldoCuentaCorrienteValue = saldoCuentaCorrienteValue;
	}

	/**
	 * Gets the checks if is cerrada.
	 *
	 * @return the checks if is cerrada
	 */
	public Boolean getIsCerrada() {
		return isCerrada;
	}

	/**
	 * Sets the checks if is cerrada.
	 *
	 * @param isCerrada
	 *            the new checks if is cerrada
	 */
	public void setIsCerrada(Boolean isCerrada) {
		this.isCerrada = isCerrada;
	}

	/**
	 * Gets the checks if is cuenta unica.
	 *
	 * @return the checks if is cuenta unica
	 */
	public Boolean getIsCuentaUnica() {
		return isCuentaUnica;
	}

	/**
	 * Sets the checks if is cuenta unica.
	 *
	 * @param isCuentaUnica
	 *            the new checks if is cuenta unica
	 */
	public void setIsCuentaUnica(Boolean isCuentaUnica) {
		this.isCuentaUnica = isCuentaUnica;
	}

	/**
	 * Gets the checks if is cuenta sueldo.
	 *
	 * @return the checks if is cuenta sueldo
	 */
	public Boolean getIsCuentaSueldo() {
		return isCuentaSueldo;
	}

	/**
	 * Sets the checks if is cuenta sueldo.
	 *
	 * @param isCuentaSueldo
	 *            the new checks if is cuenta sueldo
	 */
	public void setIsCuentaSueldo(Boolean isCuentaSueldo) {
		this.isCuentaSueldo = isCuentaSueldo;
	}

	/**
	 * Gets the checks if is descubierto utilizado.
	 *
	 * @return the checks if is descubierto utilizado
	 */
	public Boolean getIsDescubiertoUtilizado() {
		return isDescubiertoUtilizado;
	}

	/**
	 * Sets the checks if is descubierto utilizado.
	 *
	 * @param isDescubiertoUtilizado
	 *            the new checks if is descubierto utilizado
	 */
	public void setIsDescubiertoUtilizado(Boolean isDescubiertoUtilizado) {
		this.isDescubiertoUtilizado = isDescubiertoUtilizado;
	}

	/**
	 * Gets the show descubierto.
	 *
	 * @return the show descubierto
	 */
	public Boolean getShowDescubierto() {
		return showDescubierto;
	}

	/**
	 * Sets the show descubierto.
	 *
	 * @param showDescubierto
	 *            the new show descubierto
	 */
	public void setShowDescubierto(Boolean showDescubierto) {
		this.showDescubierto = showDescubierto;
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
	 * Gets the checks if is favorita.
	 *
	 * @return the checks if is favorita
	 */
	public Boolean getIsFavorito() {
		return isFavorito;
	}

	/**
	 * Sets the checks if is favorita.
	 *
	 * @param isFavorita
	 *            the new checks if is favorita
	 */
	public void setIsFavorito(Boolean isFavorita) {
		this.isFavorito = isFavorita;
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
	 * Gets the checks for alias.
	 *
	 * @return the checks for alias
	 */
	public Boolean getHasAlias() {
		return hasAlias;
	}

	/**
	 * Sets the checks for alias.
	 *
	 * @param hasAlias
	 *            the new checks for alias
	 */
	public void setHasAlias(Boolean hasAlias) {
		this.hasAlias = hasAlias;
	}

}
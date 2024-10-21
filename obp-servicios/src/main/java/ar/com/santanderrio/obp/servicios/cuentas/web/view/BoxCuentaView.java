/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class BoxCuentaView.
 */
public class BoxCuentaView {

	/** The id. */
	private String id;

	/** The is favorita. */
	private Boolean isFavorita;

	/** The is cuenta unica. */
	private Boolean isCuentaUnica;

	/** The alias. */
	private String alias;

	/** The descripcion tipo cuenta. */
	private String descripcionTipoCuenta;

	/** The is cuenta sueldo. */
	private Boolean isCuentaSueldo;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The saldo caja ahorro. */
	private String saldoCajaAhorro;

	/** The saldo cuenta corriente. */
	private String saldoCuentaCorriente;

	/** The saldo caja ahorro value. */
	private BigDecimal saldoCajaAhorroValue;

	/** The saldo cuenta corriente value. */
	private BigDecimal saldoCuentaCorrienteValue;

	/** The saldo. */
	private String saldo;

	/** The saldo value. */
	private BigDecimal saldoValue;

	/** The moneda. */
	private String moneda;

	private Boolean isCuentaRepatriacion = false;

	/** The Constant MONEDA_DOLAR. */
	protected static final String MONEDA_DOLAR = "dolar";

	/** The Constant MONEDA_PESO. */
	protected static final String MONEDA_PESO = "peso";

	/** The Constant SOBREGIRO. */
	protected static final String SOBREGIRO = "S";

	/** The Constant GUION. */
	protected static final String GUION = "-";

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
		this.setAlias(StringUtils.isEmpty(resumenDetalleCuenta.getAlias())? null :resumenDetalleCuenta.getAlias() );
		this.setDescripcionTipoCuenta(obtenerDescripcionTipoCuenta(resumenDetalleCuenta.getTipoCuenta()));
		this.setIsCuentaSueldo(resumenDetalleCuenta.isConvenioPAS());
		this.setIsFavorita(resumenDetalleCuenta.isFavorita());
		this.setNumeroCuenta(resumenDetalleCuenta.getNumeroCuentaYSucursal());
		this.setIsCuentaRepatriacion(resumenDetalleCuenta.isRepatriacion());
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the checks if is favorita.
	 *
	 * @return the checks if is favorita
	 */
	public Boolean getIsFavorita() {
		return isFavorita;
	}

	/**
	 * Sets the checks if is favorita.
	 *
	 * @param isFavorita
	 *            the new checks if is favorita
	 */
	public void setIsFavorita(Boolean isFavorita) {
		this.isFavorita = isFavorita;
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
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo
	 *            the new saldo
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	/**
	 * Gets the saldo value.
	 *
	 * @return the saldo value
	 */
	public BigDecimal getSaldoValue() {
		return saldoValue;
	}

	/**
	 * Sets the saldo value.
	 *
	 * @param saldoValue
	 *            the new saldo value
	 */
	public void setSaldoValue(BigDecimal saldoValue) {
		this.saldoValue = saldoValue;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Boolean getIsCuentaRepatriacion() {
		return isCuentaRepatriacion;
	}

	public void setIsCuentaRepatriacion(Boolean cuentaRepatriacion) {
		isCuentaRepatriacion = cuentaRepatriacion;
	}

	/**
	 * Formatear saldo sin abs.
	 *
	 * @param saldo
	 *            the saldo
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 * @return the string
	 */
	protected String formatearSaldoSinAbs(BigDecimal saldo, ResumenDetalleCuenta resumenDetalleCuenta) {
		if (resumenDetalleCuenta.getTraspasoAutomaticoActivo() == null
				&& resumenDetalleCuenta.getSolicitudPendienteTraspasoAutomatico() == null
				&& !resumenDetalleCuenta.isCuentaCerrada()) {
			return GUION;
		}
		return ISBANStringUtils.formatearSaldoSinAbs(saldo);
	}

	/**
	 * Obtener descripcion tipo cuenta.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the string
	 */
	private String obtenerDescripcionTipoCuenta(String codigo) {
		TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(codigo);
		String descripcion = codigo;
		if (tipoCuenta != null) {
			descripcion = tipoCuenta.getDescripcionConMoneda();
		}
		return descripcion;
	}

}

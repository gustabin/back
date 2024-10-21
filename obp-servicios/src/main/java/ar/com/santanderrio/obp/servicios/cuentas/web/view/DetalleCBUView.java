/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import javax.validation.constraints.Pattern;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AliasRsaView;

/**
 * The Class DetalleCBUView. Se extiende de RsaDTO por un tema del BackReference
 * y se define el constructor y se pone como generico OperacionRsaEnum.ACTIVO
 * como generico. la clase que se utiliza para los flujos de alias es
 * 
 * 
 */
public class DetalleCBUView extends AliasRsaView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The nombre cliente. */
	private String nombreCliente;

	/** The tipo identificacion. */
	private String tipoIdentificacion;

	/** The identificacion cliente. */
	private String identificacionCliente;

	/** The nombre banco. */
	@Pattern(regexp = "^[a-zA-ZáÁéÉíÍóÓúÚñÑ 0-9]{1,20}$")
	private String nombreBanco;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The numero sucursal. */
	@Pattern(regexp = "^[0-9]{3}$")
	private String numeroSucursal;

	/** The numero cuenta. */
	@Pattern(regexp = "^[0-9]{3}[-][0-9]{6}[/][0-9]{1}$")
	private String numeroCuenta;

	/** The alias anterior. */
	private String aliasAnterior;

	/** The reasigna. */
	private String reasigna;

	/**
	 * Instantiates a new detalle CBU view.
	 */
	public DetalleCBUView() {
		super(OperacionesRSAEnum.ACTIVO);
	}

	/**
	 * Gets the nombre cliente.
	 *
	 * @return the nombre cliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * Sets the nombre cliente.
	 *
	 * @param nombreCliente
	 *            the new nombre cliente
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * Gets the nombre banco.
	 *
	 * @return the nombre banco
	 */
	public String getNombreBanco() {
		return nombreBanco;
	}

	/**
	 * Sets the nombre banco.
	 *
	 * @param nombreBanco
	 *            the new nombre banco
	 */
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the numero sucursal.
	 *
	 * @return the numero sucursal
	 */
	public String getNumeroSucursal() {
		return numeroSucursal;
	}

	/**
	 * Sets the numero sucursal.
	 *
	 * @param numeroSucursal
	 *            the new numero sucursal
	 */
	public void setNumeroSucursal(String numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
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
	 * Gets the tipo identificacion.
	 *
	 * @return the tipo identificacion
	 */
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	/**
	 * Sets the tipo identificacion.
	 *
	 * @param tipoIdentificacion
	 *            the new tipo identificacion
	 */
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	/**
	 * Gets the identificacion cliente.
	 *
	 * @return the identificacion cliente
	 */
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	/**
	 * Sets the identificacion cliente.
	 *
	 * @param identificacionCliente
	 *            the new identificacion cliente
	 */
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	/**
	 * Gets the alias anterior.
	 *
	 * @return the alias anterior
	 */
	public String getAliasAnterior() {
		return aliasAnterior;
	}

	/**
	 * Sets the alias anterior.
	 *
	 * @param aliasAnterior
	 *            the new alias anterior
	 */
	public void setAliasAnterior(String aliasAnterior) {
		this.aliasAnterior = aliasAnterior;
	}

	/**
	 * Gets the reasigna.
	 *
	 * @return the reasigna
	 */
	public String getReasigna() {
		return reasigna;
	}

	/**
	 * Sets the reasigna.
	 *
	 * @param reasigna
	 *            the new reasigna
	 */
	public void setReasigna(String reasigna) {
		this.reasigna = reasigna;
	}

}

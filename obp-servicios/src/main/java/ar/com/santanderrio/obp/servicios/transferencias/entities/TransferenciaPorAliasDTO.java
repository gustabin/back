/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * DTO de transferencia a un Alias.
 * 
 * @author Manuel.Vargas B041299
 * @version 1
 */
public class TransferenciaPorAliasDTO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Transferencia.class);

	/** The descripcion. */
	private String descripcion;

	/** The disponible. */
	private Double disponible;

	/** The limite. */
	private Double limite;

	/** The moneda codigo. */
	private String monedaCodigo;

	/** The moneda descripcion. */
	private String monedaDescripcion;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The numero CBU. */
	private String numeroCBU;

	/** The saldo. */
	private Double saldo;

	/** The codigo tipo cuenta. */
	private String codigoTipoCuenta;

	/** The descripcion tipo cuenta. */
	private String descripcionTipoCuenta;

	/** The cuits. */
	private String cuits;

	/** The fiid banco. */
	private String fiidBanco;

	/** The fiid origen link. */
	private String fiidOrigenLink;

	/** The nombre banco. */
	private String nombreBanco;

	/** The nombre titular. */
	private String nombreTitular;

	/** The alias. */
	private String alias;

	/** The is rio rio. */
	private boolean isRioRio;

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the disponible.
	 *
	 * @return the disponible
	 */
	public Double getDisponible() {
		return disponible;
	}

	/**
	 * Sets the disponible.
	 *
	 * @param double1
	 *            the new disponible
	 */
	public void setDisponible(Double double1) {
		this.disponible = double1;
	}

	/**
	 * Gets the limite.
	 *
	 * @return the limite
	 */
	public Double getLimite() {
		return limite;
	}

	/**
	 * Sets the limite.
	 *
	 * @param double1
	 *            the new limite
	 */
	public void setLimite(Double double1) {
		this.limite = double1;
	}

	/**
	 * Gets the moneda codigo.
	 *
	 * @return the moneda codigo
	 */
	public String getMonedaCodigo() {
		return monedaCodigo;
	}

	/**
	 * Sets the moneda codigo.
	 *
	 * @param monedaCodigo
	 *            the new moneda codigo
	 */
	public void setMonedaCodigo(String monedaCodigo) {
		this.monedaCodigo = monedaCodigo;
	}

	/**
	 * Gets the moneda descripcion.
	 *
	 * @return the moneda descripcion
	 */
	public String getMonedaDescripcion() {
		return monedaDescripcion;
	}

	/**
	 * Sets the moneda descripcion.
	 *
	 * @param monedaDescripcion
	 *            the new moneda descripcion
	 */
	public void setMonedaDescripcion(String monedaDescripcion) {
		this.monedaDescripcion = monedaDescripcion;
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
	 * Gets the numero CBU.
	 *
	 * @return the numero CBU
	 */
	public String getNumeroCBU() {
		return numeroCBU;
	}

	/**
	 * Sets the numero CBU.
	 *
	 * @param numeroCBU
	 *            the new numero CBU
	 */
	public void setNumeroCBU(String numeroCBU) {
		this.numeroCBU = numeroCBU;
	}

	/**
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public Double getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param double1
	 *            the new saldo
	 */
	public void setSaldo(Double double1) {
		this.saldo = double1;
	}

	/**
	 * Gets the codigo tipo cuenta.
	 *
	 * @return the codigo tipo cuenta
	 */
	public String getCodigoTipoCuenta() {
		return codigoTipoCuenta;
	}

	/**
	 * Sets the codigo tipo cuenta.
	 *
	 * @param codigoTipoCuenta
	 *            the new codigo tipo cuenta
	 */
	public void setCodigoTipoCuenta(String codigoTipoCuenta) {
		this.codigoTipoCuenta = codigoTipoCuenta;
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
	 * Gets the cuits.
	 *
	 * @return the cuits
	 */
	public String getCuits() {
		return cuits;
	}

	/**
	 * Sets the cuits.
	 *
	 * @param cuits
	 *            the new cuits
	 */
	public void setCuits(String cuits) {
		this.cuits = cuits;
	}

	/**
	 * Gets the fiid banco.
	 *
	 * @return the fiid banco
	 */
	public String getFiidBanco() {
		return fiidBanco;
	}

	/**
	 * Sets the fiid banco.
	 *
	 * @param fiidBanco
	 *            the new fiid banco
	 */
	public void setFiidBanco(String fiidBanco) {
		this.fiidBanco = fiidBanco;
	}

	/**
	 * Gets the fiid origen link.
	 *
	 * @return the fiid origen link
	 */
	public String getFiidOrigenLink() {
		return fiidOrigenLink;
	}

	/**
	 * Sets the fiid origen link.
	 *
	 * @param fiidOrigenLink
	 *            the new fiid origen link
	 */
	public void setFiidOrigenLink(String fiidOrigenLink) {
		this.fiidOrigenLink = fiidOrigenLink;
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
	 * Gets the nombre titular.
	 *
	 * @return the nombre titular
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}

	/**
	 * Sets the nombre titular.
	 *
	 * @param nombreTitular
	 *            the new nombre titular
	 */
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
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
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * Sets the checks if is rio rio.
	 *
	 * @param esRio
	 *            the new checks if is rio rio
	 */
	public void setIsRioRio(Boolean esRio) {
		this.isRioRio = esRio;
	}

	/**
	 * Checks if is rio rio.
	 *
	 * @return true, if is rio rio
	 */
	public boolean isRioRio() {
		return this.isRioRio;
	}

}

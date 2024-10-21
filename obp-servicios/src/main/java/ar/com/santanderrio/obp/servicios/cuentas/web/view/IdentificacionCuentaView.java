/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.base.web.view.View;

/**
 * The Class IdentificacionCuentaView.
 */
@XmlRootElement(name = "identificacionCuentaView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
public class IdentificacionCuentaView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The tipo de cuenta. */
	private String tipoDeCuenta;

	/** The alias de cuenta. */
	private String aliasDeCuenta;

	/** The numero de cuenta. */
	private String numeroDeCuenta;

	/**
	 * Gets the tipo de cuenta.
	 *
	 * @return the tipo de cuenta
	 */
	public String getTipoDeCuenta() {
		return tipoDeCuenta;
	}

	/**
	 * Sets the tipo de cuenta.
	 *
	 * @param tipoDeCuenta
	 *            the new tipo de cuenta
	 */
	public void setTipoDeCuenta(String tipoDeCuenta) {
		this.tipoDeCuenta = tipoDeCuenta;
	}

	/**
	 * Gets the alias de cuenta.
	 *
	 * @return the alias de cuenta
	 */
	public String getAliasDeCuenta() {
		return aliasDeCuenta;
	}

	/**
	 * Sets the alias de cuenta.
	 *
	 * @param aliasDeCuenta
	 *            the new alias de cuenta
	 */
	public void setAliasDeCuenta(String aliasDeCuenta) {
		this.aliasDeCuenta = aliasDeCuenta;
	}

	/**
	 * Gets the numero de cuenta.
	 *
	 * @return the numero de cuenta
	 */
	public String getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	/**
	 * Sets the numero de cuenta.
	 *
	 * @param numeroDeCuenta
	 *            the new numero de cuenta
	 */
	public void setNumeroDeCuenta(String numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

}

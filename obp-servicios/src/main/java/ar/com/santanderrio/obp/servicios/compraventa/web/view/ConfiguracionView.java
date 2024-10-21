/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfiguracionView.
 *
 * @author sabrina.cis
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfiguracionView {

	/** The numero cuenta. */
	private String numeroCuenta;
	
	/** The numero cuenta destino. */
	private String numeroCuentaDestino;

	/** The alias. */
	private String operacion;
	
	/** The fromCuentas. */
	private boolean fromCuentas;
	
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
	 * Gets the operacion.
	 *
	 * @return the operacion
	 */
	public String getOperacion() {
		return operacion;
	}

	/**
	 * Sets the operacion.
	 *
	 * @param operacion
	 *            the new operacion
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	
	/**
	 * Gets the from cuentas.
	 *
	 * @return the from cuentas
	 */
	public boolean getFromCuentas() {
		return fromCuentas;
	}

	/**
	 * Sets the from cuentas.
	 *
	 * @param fromCuentas the new from cuentas
	 */
	public void setFromCuentas(boolean fromCuentas) {
		this.fromCuentas = fromCuentas;
	}

	/**
	 * Gets the numero cuenta destino.
	 *
	 * @return the numero cuenta destino
	 */
	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	/**
	 * Sets the from cuentas.
	 *
	 * @param numeroCuentaDestino the new from numero cuenta destino
	 */
	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConfiguracionView [numeroCuenta=" + numeroCuenta + ", operacion=" + operacion + "]";
	}
}

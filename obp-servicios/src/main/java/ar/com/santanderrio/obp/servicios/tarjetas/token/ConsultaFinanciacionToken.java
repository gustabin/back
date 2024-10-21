/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.token;

import ar.com.santanderrio.obp.base.signer.token.StringToken;
import ar.com.santanderrio.obp.base.signer.token.StringTokenFormat;

/**
 * Token para generar firma y ser utilizado en planV.
 * 
 * @author sergio.e.goldentair
 *
 */
public class ConsultaFinanciacionToken extends StringToken {

	/** The codigo banco. */
	@StringTokenFormat("codigoBanco=")
	private String codigoBanco;

	/** The funcionario. */
	@StringTokenFormat("funcionario=")
	private String funcionario;

	/** The password. */
	@StringTokenFormat("password=")
	private String password;

	/** The numero cuenta. */
	@StringTokenFormat("numeroCuenta=")
	private String numeroCuenta;

	/** The tipo plan V. */
	@StringTokenFormat("tipoPlanV=")
	private String tipoPlanV;

	/**
	 * Gets the codigo banco.
	 *
	 * @return the codigoBanco
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * Sets the codigo banco.
	 *
	 * @param codigoBanco
	 *            the codigoBanco to set
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * Gets the funcionario.
	 *
	 * @return the funcionario
	 */
	public String getFuncionario() {
		return funcionario;
	}

	/**
	 * Sets the funcionario.
	 *
	 * @param funcionario
	 *            the funcionario to set
	 */
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the tipo plan V.
	 *
	 * @return the tipoPlanV
	 */
	public String getTipoPlanV() {
		return tipoPlanV;
	}

	/**
	 * Sets the tipo plan V.
	 *
	 * @param tipoPlanV
	 *            the tipoPlanV to set
	 */
	public void setTipoPlanV(String tipoPlanV) {
		this.tipoPlanV = tipoPlanV;
	}
}

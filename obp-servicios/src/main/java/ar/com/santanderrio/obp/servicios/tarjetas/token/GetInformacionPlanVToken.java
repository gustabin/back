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
public class GetInformacionPlanVToken extends StringToken {

	/** The codigo banco. */
	@StringTokenFormat("codigoBanco=")
	private String codigoBanco;

	/** The funcionario. */
	@StringTokenFormat("funcionario=")
	private String funcionario;

	/** The password. */
	@StringTokenFormat("password=")
	private String password;

	/** The numero tarjeta. */
	@StringTokenFormat("numeroTarjeta=")
	private String numeroTarjeta;

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numero tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the new numero tarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

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

}

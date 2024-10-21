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
public class ConfirmarSolicitudPlanVToken extends StringToken {

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

	/** The monto. */
	@StringTokenFormat("monto=")
	private String monto;

	/** The cuotas. */
	@StringTokenFormat("cuotas=")
	private String cuotas;

	/** The moneda. */
	@StringTokenFormat("moneda=")
	private String moneda;

	/**
	 * Gets the codigo banco.
	 *
	 * @return the codigo banco
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * Sets the codigo banco.
	 *
	 * @param codigoBanco
	 *            the new codigo banco
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
	 *            the new funcionario
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
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

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
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * Sets the monto.
	 *
	 * @param monto
	 *            the new monto
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * Gets the cuotas.
	 *
	 * @return the cuotas
	 */
	public String getCuotas() {
		return cuotas;
	}

	/**
	 * Sets the cuotas.
	 *
	 * @param cuotas
	 *            the new cuotas
	 */
	public void setCuotas(String cuotas) {
		this.cuotas = cuotas;
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

}

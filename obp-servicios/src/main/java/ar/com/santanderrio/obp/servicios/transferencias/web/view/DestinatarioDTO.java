/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.view;

import ar.com.santanderrio.obp.servicios.transferencias.entities.TipoCuentaBanelco;

/**
 * The Class DestinatarioDTO.
 *
 * @author B041299
 */
public class DestinatarioDTO {

	/** The titular. */
	private String titular;

	/** The cuit. */
	private String cuit;

	/** The cuit 2. */
	private String cuit2;

	/** The cuit 3. */
	private String cuit3;

	/** The long cuenta destino banelco. */
	private String longCuentaDestinoBanelco;

	/** The cuenta destino banelco. */
	private String cuentaDestinoBanelco;

	/** The tipo de cuenta to banelco. */
	private TipoCuentaBanelco tipoDeCuentaToBanelco;

	/** The tipo de cuenta from banelco. */
	private TipoCuentaBanelco tipoDeCuentaFromBanelco;

	/** The fiid. */
	private String fiid;

	/** The user. */
	private String user;

	/** The banco receptor. */
	private String bancoReceptor;

	/** The banco destino. */
	private String bancoDestino;

	/**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the titular to set
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the cuit 2.
	 *
	 * @return the cuit2
	 */
	public String getCuit2() {
		return cuit2;
	}

	/**
	 * Sets the cuit 2.
	 *
	 * @param cuit2
	 *            the cuit2 to set
	 */
	public void setCuit2(String cuit2) {
		this.cuit2 = cuit2;
	}

	/**
	 * Gets the cuit 3.
	 *
	 * @return the cuit3
	 */
	public String getCuit3() {
		return cuit3;
	}

	/**
	 * Sets the cuit 3.
	 *
	 * @param cuit3
	 *            the cuit3 to set
	 */
	public void setCuit3(String cuit3) {
		this.cuit3 = cuit3;
	}

	/**
	 * Gets the long cuenta destino banelco.
	 *
	 * @return the longCuentaDestinoBanelco
	 */
	public String getLongCuentaDestinoBanelco() {
		return longCuentaDestinoBanelco;
	}

	/**
	 * Sets the long cuenta destino banelco.
	 *
	 * @param longCuentaDestinoBanelco
	 *            the longCuentaDestinoBanelco to set
	 */
	public void setLongCuentaDestinoBanelco(String longCuentaDestinoBanelco) {
		this.longCuentaDestinoBanelco = longCuentaDestinoBanelco;
	}

	/**
	 * Gets the cuenta destino banelco.
	 *
	 * @return the cuentaDestinoBanelco
	 */
	public String getCuentaDestinoBanelco() {
		return cuentaDestinoBanelco;
	}

	/**
	 * Sets the cuenta destino banelco.
	 *
	 * @param cuentaDestinoBanelco
	 *            the cuentaDestinoBanelco to set
	 */
	public void setCuentaDestinoBanelco(String cuentaDestinoBanelco) {
		this.cuentaDestinoBanelco = cuentaDestinoBanelco;
	}

	/**
	 * Gets the tipo de cuenta to banelco.
	 *
	 * @return the tipo de cuenta to banelco
	 */
	public TipoCuentaBanelco getTipoDeCuentaToBanelco() {
		return tipoDeCuentaToBanelco;
	}

	/**
	 * Sets the tipo de cuenta to banelco.
	 *
	 * @param tipoDeCuentaToBanelco
	 *            the new tipo de cuenta to banelco
	 */
	public void setTipoDeCuentaToBanelco(TipoCuentaBanelco tipoDeCuentaToBanelco) {
		this.tipoDeCuentaToBanelco = tipoDeCuentaToBanelco;
	}

	/**
	 * Gets the tipo de cuenta from banelco.
	 *
	 * @return the tipo de cuenta from banelco
	 */
	public TipoCuentaBanelco getTipoDeCuentaFromBanelco() {
		return tipoDeCuentaFromBanelco;
	}

	/**
	 * Sets the tipo de cuenta from banelco.
	 *
	 * @param tipoDeCuentaFromBanelco
	 *            the new tipo de cuenta from banelco
	 */
	public void setTipoDeCuentaFromBanelco(TipoCuentaBanelco tipoDeCuentaFromBanelco) {
		this.tipoDeCuentaFromBanelco = tipoDeCuentaFromBanelco;
	}

	/**
	 * Gets the fiid.
	 *
	 * @return the fiid
	 */
	public String getFiid() {
		return fiid;
	}

	/**
	 * Sets the fiid.
	 *
	 * @param fiid
	 *            the fiid to set
	 */
	public void setFiid(String fiid) {
		this.fiid = fiid;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets the banco receptor.
	 *
	 * @return the bancoReceptor
	 */
	public String getBancoReceptor() {
		return bancoReceptor;
	}

	/**
	 * Sets the banco receptor.
	 *
	 * @param bancoReceptor
	 *            the bancoReceptor to set
	 */
	public void setBancoReceptor(String bancoReceptor) {
		this.bancoReceptor = bancoReceptor;
	}

	/**
	 * Gets the banco destino.
	 *
	 * @return the banco destino
	 */
	public String getBancoDestino() {
		return bancoDestino;
	}

	/**
	 * Sets the banco destino.
	 *
	 * @param bancoDestino
	 *            the new banco destino
	 */
	public void setBancoDestino(String bancoDestino) {
		this.bancoDestino = bancoDestino;
	}

}

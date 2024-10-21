/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.web.view;

/**
 * The Class ValidarCBUView.
 */
public class ValidacionesPagoPorCBUView {

	/** tipo Cuenta Origen. */
	private String tipoCuentaOrigen;

	/** cuenta Origen. */
	private String cuentaOrigen;

	/** numero CBU Destino. */
	private String numeroCBUDestino;

	/** tarjeta Banelco. */
	private String tarjetaBanelco;

	/** direccion IP. */
	private String direccionIP;

	/** importe. */
	private String importe;

	/** ingreso Datos Manual. */
	private Boolean ingresoDatosManual;

	/** titular. */
	private String titular;

	/** cuit Cuil. */
	private String cuitCuil;

	/** banco. */
	private String banco;

	/**
	 * Gets the tipo cuenta origen.
	 *
	 * @return the tipo cuenta origen
	 */
	public String getTipoCuentaOrigen() {
		return tipoCuentaOrigen;
	}

	/**
	 * Sets the tipo cuenta origen.
	 *
	 * @param tipoCuentaOrigen
	 *            the new tipo cuenta origen
	 */
	public void setTipoCuentaOrigen(String tipoCuentaOrigen) {
		this.tipoCuentaOrigen = tipoCuentaOrigen;
	}

	/**
	 * Gets the cuenta origen.
	 *
	 * @return cuenta Origen
	 */
	public String getCuentaOrigen() {
		return cuentaOrigen;
	}

	/**
	 * Sets the cuenta origen.
	 *
	 * @param cuentaOrigen
	 *            the new cuenta origen
	 */
	public void setCuentaOrigen(String cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	/**
	 * Gets the numero CBU destino.
	 *
	 * @return numero CBU Destino
	 */
	public String getNumeroCBUDestino() {
		return numeroCBUDestino;
	}

	/**
	 * Sets the numero CBU destino.
	 *
	 * @param numeroCBUDestino
	 *            the new numero CBU destino
	 */
	public void setNumeroCBUDestino(String numeroCBUDestino) {
		this.numeroCBUDestino = numeroCBUDestino;
	}

	/**
	 * Gets the tarjeta banelco.
	 *
	 * @return tarjeta Banelco
	 */
	public String getTarjetaBanelco() {
		return tarjetaBanelco;
	}

	/**
	 * Sets the tarjeta banelco.
	 *
	 * @param tarjetaBanelco
	 *            the new tarjeta banelco
	 */
	public void setTarjetaBanelco(String tarjetaBanelco) {
		this.tarjetaBanelco = tarjetaBanelco;
	}

	/**
	 * Gets the direccion IP.
	 *
	 * @return direccion IP
	 */
	public String getDireccionIP() {
		return direccionIP;
	}

	/**
	 * Sets the direccion IP.
	 *
	 * @param direccionIP
	 *            the new direccion IP
	 */
	public void setDireccionIP(String direccionIP) {
		this.direccionIP = direccionIP;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the ingreso datos manual.
	 *
	 * @return the ingreso datos manual
	 */
	public Boolean getIngresoDatosManual() {
		return ingresoDatosManual;
	}

	/**
	 * Sets the ingreso datos manual.
	 *
	 * @param ingresoDatosManual
	 *            the new ingreso datos manual
	 */
	public void setIngresoDatosManual(Boolean ingresoDatosManual) {
		this.ingresoDatosManual = ingresoDatosManual;
	}

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
	 *            the new titular
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the cuit cuil.
	 *
	 * @return the cuit cuil
	 */
	public String getCuitCuil() {
		return cuitCuil;
	}

	/**
	 * Sets the cuit cuil.
	 *
	 * @param cuitCuil
	 *            the new cuit cuil
	 */
	public void setCuitCuil(String cuitCuil) {
		this.cuitCuil = cuitCuil;
	}

	/**
	 * Gets the banco.
	 *
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * Sets the banco.
	 *
	 * @param banco
	 *            the new banco
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

}

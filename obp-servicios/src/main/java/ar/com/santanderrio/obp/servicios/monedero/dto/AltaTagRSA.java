/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.dto;

import java.util.Date;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;

/**
 * The Class AltaTagRSA.
 */
public class AltaTagRSA extends RsaDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8910100929308569053L;

	/** The amount. */
	private Long amount;

	/** The divisa. */
	private DivisaEnum divisa;

	/** fecha de la factura si es que tiene. */
	private Date fechaDeOperacion;

	/** saldo cuenta origen. */
	private Long saldoCuentaOrigen;

	/** The cuenta origen. */
	private Cuenta cuentaOrigen;

	/** The cuenta numeroCuentaDestino. */
	private String numeroTarjetaDestino;

	/**
	 * Instantiates a new alta tag RSA.
	 */
	public AltaTagRSA() {
		super(OperacionesRSAEnum.ALTA_TAG_MONEDERO);
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public Long getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount
	 *            the new amount
	 */
	public void setAmount(Long amount) {
		this.amount = amount;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public DivisaEnum getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the new divisa
	 */
	public void setDivisa(DivisaEnum divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the fecha de operacion.
	 *
	 * @return the fecha de operacion
	 */
	public Date getFechaDeOperacion() {
		return fechaDeOperacion;
	}

	/**
	 * Sets the fecha de operacion.
	 *
	 * @param fechaDeOperacion
	 *            the new fecha de operacion
	 */
	public void setFechaDeOperacion(Date fechaDeOperacion) {
		this.fechaDeOperacion = fechaDeOperacion;
	}

	/**
	 * Gets the saldo cuenta origen.
	 *
	 * @return the saldo cuenta origen
	 */
	public Long getSaldoCuentaOrigen() {
		return saldoCuentaOrigen;
	}

	/**
	 * Sets the saldo cuenta origen.
	 *
	 * @param saldoCuentaOrigen
	 *            the new saldo cuenta origen
	 */
	public void setSaldoCuentaOrigen(Long saldoCuentaOrigen) {
		this.saldoCuentaOrigen = saldoCuentaOrigen;
	}

	/**
	 * Gets the cuenta origen.
	 *
	 * @return the cuenta origen
	 */
	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}

	/**
	 * Sets the cuenta origen.
	 *
	 * @param cuentaOrigen
	 *            the new cuenta origen
	 */
	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	/**
	 * Gets the numero tarjeta destino.
	 *
	 * @return the numero tarjeta destino
	 */
	public String getNumeroTarjetaDestino() {
		return numeroTarjetaDestino;
	}

	/**
	 * Sets the numero tarjeta destino.
	 *
	 * @param numeroTarjetaDestino
	 *            the new numero tarjeta destino
	 */
	public void setNumeroTarjetaDestino(String numeroTarjetaDestino) {
		this.numeroTarjetaDestino = numeroTarjetaDestino;
	}
}

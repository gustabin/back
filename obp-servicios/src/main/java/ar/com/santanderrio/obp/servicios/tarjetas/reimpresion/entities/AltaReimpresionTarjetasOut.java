/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities;

import java.util.List;

/**
 * The Class AltaReimpresionTarjetasOut.
 */
public class AltaReimpresionTarjetasOut {

	/** The codigo error ext. */
	private int codigoErrorExt;

	/** The Codigo tarjeta. */
	private String CodigoTarjeta;

	/** The tarjetas debito. */
	private List<TarjetaOut> tarjetasDebito;

	/** The tarjetas credito. */
	private List<TarjetaOut> tarjetasCredito;

	/**
	 * Gets the tarjetas debito.
	 *
	 * @return the tarjetas debito
	 */
	public List<TarjetaOut> getTarjetasDebito() {
		return tarjetasDebito;
	}

	/**
	 * Sets the tarjetas debito.
	 *
	 * @param tarjetasDebito
	 *            the new tarjetas debito
	 */
	public void setTarjetasDebito(List<TarjetaOut> tarjetasDebito) {
		this.tarjetasDebito = tarjetasDebito;
	}

	/**
	 * Gets the tarjetas credito.
	 *
	 * @return the tarjetas credito
	 */
	public List<TarjetaOut> getTarjetasCredito() {
		return tarjetasCredito;
	}

	/**
	 * Sets the tarjetas credito.
	 *
	 * @param tarjetasCredito
	 *            the new tarjetas credito
	 */
	public void setTarjetasCredito(List<TarjetaOut> tarjetasCredito) {
		this.tarjetasCredito = tarjetasCredito;
	}

	/**
	 * Gets the codigo error ext.
	 *
	 * @return the codigo error ext
	 */
	public int getCodigoErrorExt() {
		return codigoErrorExt;
	}

	/**
	 * Sets the codigo error ext.
	 *
	 * @param codigoErrorExt
	 *            the new codigo error ext
	 */
	public void setCodigoErrorExt(int codigoErrorExt) {
		this.codigoErrorExt = codigoErrorExt;
	}

	/**
	 * Gets the codigo tarjeta.
	 *
	 * @return the codigo tarjeta
	 */
	public String getCodigoTarjeta() {
		return CodigoTarjeta;
	}

	/**
	 * Sets the codigo tarjeta.
	 *
	 * @param codigoTarjeta
	 *            the new codigo tarjeta
	 */
	public void setCodigoTarjeta(String codigoTarjeta) {
		CodigoTarjeta = codigoTarjeta;
	}

}

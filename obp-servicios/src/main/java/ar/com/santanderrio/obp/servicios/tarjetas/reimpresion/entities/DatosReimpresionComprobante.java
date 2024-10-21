/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities;

import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomicilioView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.TarjetaSolicitadaView;

/**
 * The Class DatosComprobante.
 */
public class DatosReimpresionComprobante {

	/** The tarjetas. */
	private List<TarjetaSolicitadaView> tarjetas;

	/** The domicilios. */
	private List<DomicilioView> domicilios;

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<TarjetaSolicitadaView> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the new tarjetas
	 */
	public void setTarjetas(List<TarjetaSolicitadaView> tarjetas) {
		this.tarjetas = tarjetas;
	}

	/**
	 * Gets the domicilios.
	 *
	 * @return the domicilios
	 */
	public List<DomicilioView> getDomicilios() {
		return domicilios;
	}

	/**
	 * Sets the domicilios.
	 *
	 * @param domicilios
	 *            the new domicilios
	 */
	public void setDomicilios(List<DomicilioView> domicilios) {
		this.domicilios = domicilios;
	}

}

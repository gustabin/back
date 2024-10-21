/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view;

import java.util.List;

/**
 * The Class SolicitudTarjetaCreditoAdicionalView.
 */
public class SolicitudTarjetaCreditoAdicionalView {

	/** The tarjetas candidatas. */
	private List<TarjetaCandidataView> tarjetasCandidatas;

	/**
	 * Gets the tarjetas candidatas.
	 *
	 * @return the tarjetasCandidatas
	 */
	public List<TarjetaCandidataView> getTarjetasCandidatas() {
		return tarjetasCandidatas;
	}

	/**
	 * Sets the tarjetas candidatas.
	 *
	 * @param tarjetasCandidatas
	 *            the tarjetasCandidatas to set
	 */
	public void setTarjetasCandidatas(List<TarjetaCandidataView> tarjetasCandidatas) {
		this.tarjetasCandidatas = tarjetasCandidatas;
	}

}

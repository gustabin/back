package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class SolicitarFinanciacionInView.
 */
public class SolicitarFinanciacionInView {

	/** The ofertas. */
	private List<SolicitudFinanciacionView> ofertas = new ArrayList<SolicitudFinanciacionView>();

	/**
	 * Gets the ofertas.
	 *
	 * @return the ofertas
	 */
	public List<SolicitudFinanciacionView> getOfertas() {
		return ofertas;
	}

	/**
	 * Sets the ofertas.
	 *
	 * @param ofertas the new ofertas
	 */
	public void setOfertas(List<SolicitudFinanciacionView> ofertas) {
		this.ofertas = ofertas;
	}

}

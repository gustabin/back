/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaUltimoResumenTarjetaDTO;

/**
 * The Class OtrosConceptosUltimoResumenView.
 */
public class OtrosConceptosUltimoResumenView {

	/** The otros conceptos. */
	private List<LineaUltimoResumenTarjetaView> lineas;

	/**
	 * Instantiates a new otros conceptos ultimo resumen view.
	 *
	 * @param otrosConceptosDTO
	 *            the otros conceptos DTO
	 */
	public OtrosConceptosUltimoResumenView(List<LineaUltimoResumenTarjetaDTO> otrosConceptosDTO) {
		List<LineaUltimoResumenTarjetaView> lineasView = new ArrayList<LineaUltimoResumenTarjetaView>();
		for (LineaUltimoResumenTarjetaDTO lineaDTO : otrosConceptosDTO) {
			LineaUltimoResumenTarjetaView lineaView = new LineaUltimoResumenTarjetaView(lineaDTO);
			lineasView.add(lineaView);
		}
		this.setLineas(lineasView);
	}

	/**
	 * Gets the lineas.
	 *
	 * @return the lineas
	 */
	public List<LineaUltimoResumenTarjetaView> getLineas() {
		return lineas;
	}

	/**
	 * Sets the lineas.
	 *
	 * @param lineas
	 *            the lineas to set
	 */
	public void setLineas(List<LineaUltimoResumenTarjetaView> lineas) {
		this.lineas = lineas;
	}

}

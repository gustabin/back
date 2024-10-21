/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.OperacionPrecargadaDTO;

/**
 * The Class OperacionHistoricoView.
 */
public class OperacionHistoricoView extends OperacionPrecargadaView{

	/** The estado. */
	private String estado;
	
	/** The color. */
	private String color;
	
	/**
	 * Instantiates a new operacion historico view.
	 *
	 * @param operacion
	 *            the operacion
	 * @param isMobile
	 *            the is mobile
	 */
	public OperacionHistoricoView(OperacionPrecargadaDTO operacion, Boolean isMobile) {
		super(operacion, isMobile);
		estado = operacion.getEstado();
		color = operacion.getColor();
	}
	
	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 *
	 * @param color
	 *            the new color
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	
	
}

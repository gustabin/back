/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.OperacionPrecargadaDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.OperacionesPrecargadasDTO;

/**
 * The Class OperacionesPrecargadasView.
 */
public class OperacionesPrecargadasView extends OperacionesGrillaView {

	/** The operaciones. */
	private List<OperacionPrecargadaView> operaciones = new ArrayList<OperacionPrecargadaView>();

	/**
	 * Instantiates a new operaciones precargadas view.
	 */
	public OperacionesPrecargadasView() {
		super();
	}

	/**
	 * Instantiates a new operaciones precargadas view.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param isMobile
	 *            the is mobile
	 */
	public OperacionesPrecargadasView(OperacionesPrecargadasDTO respuesta, Boolean isMobile) {
		hayRellamada = respuesta.getTieneMasOperaciones();
		for (OperacionPrecargadaDTO operacion : respuesta.getDto()) {
			operaciones.add(new OperacionPrecargadaView(operacion, isMobile));
		}
	}

	/**
	 * Gets the operaciones.
	 *
	 * @return the operaciones
	 */
	public List<OperacionPrecargadaView> getOperaciones() {
		return operaciones;
	}

	/**
	 * Sets the operaciones.
	 *
	 * @param operaciones
	 *            the new operaciones
	 */
	public void setOperaciones(List<OperacionPrecargadaView> operaciones) {
		this.operaciones = operaciones;
	}

}

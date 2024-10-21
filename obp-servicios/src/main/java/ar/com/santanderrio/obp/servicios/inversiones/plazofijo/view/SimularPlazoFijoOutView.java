/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ConsultarCondicionesDTO;

/**
 * The Class SimularPlazoFijoOutView.
 *
 * @author juan.pablo.picate
 */
public class SimularPlazoFijoOutView {

	/** The consultar condiciones. */
	private List<ConsultarCondicionesDTO> consultarCondiciones = new ArrayList<ConsultarCondicionesDTO>();

	/** The lista PlazoFijosVentas. */
	private List<ConsultarCondicionesDTO> listaPlazoFijosVentas = new ArrayList<ConsultarCondicionesDTO>();

	/**
	 * Gets the consultar condiciones.
	 *
	 * @return the consultarCondiciones
	 */
	public List<ConsultarCondicionesDTO> getConsultarCondiciones() {
		return consultarCondiciones;
	}

	/**
	 * Sets the consultar condiciones.
	 *
	 * @param consultarCondiciones
	 *            the consultarCondiciones to set
	 */
	public void setConsultarCondiciones(List<ConsultarCondicionesDTO> consultarCondiciones) {
		this.consultarCondiciones = consultarCondiciones;
	}

	/**
	 * Gets the lista plazo fijos ventas.
	 *
	 * @return the lista plazo fijos ventas
	 */
	public List<ConsultarCondicionesDTO> getListaPlazoFijosVentas() {
		return listaPlazoFijosVentas;
	}

	/**
	 * Sets the lista plazo fijos ventas.
	 *
	 * @param listaPlazoFijosVentas
	 *            the new lista plazo fijos ventas
	 */
	public void setListaPlazoFijosVentas(List<ConsultarCondicionesDTO> listaPlazoFijosVentas) {
		this.listaPlazoFijosVentas = listaPlazoFijosVentas;
	}

}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class SimularPlazoFijoOutDTO.
 *
 * @author juan.pablo.picate
 */
public class SimularPlazoFijoOutDTO {

	/** The consultar condiciones. */
	private List<CondicionesDTO> consultarCondiciones = new ArrayList<CondicionesDTO>();

	/** The consultar condiciones. */
	private List<CondicionesDTO> listaPlazoFijosVentas = new ArrayList<CondicionesDTO>();

	/**
	 * Gets the consultar condiciones.
	 *
	 * @return the consultarCondiciones
	 */
	public List<CondicionesDTO> getConsultarCondiciones() {
		return consultarCondiciones;
	}

	/**
	 * Sets the consultar condiciones.
	 *
	 * @param consultarCondiciones
	 *            the consultarCondiciones to set
	 */
	public void setConsultarCondiciones(List<CondicionesDTO> consultarCondiciones) {
		this.consultarCondiciones = consultarCondiciones;
	}

	/**
	 * Gets the lista plazo fijos ventas.
	 *
	 * @return the lista plazo fijos ventas
	 */
	public List<CondicionesDTO> getListaPlazoFijosVentas() {
		return listaPlazoFijosVentas;
	}

	/**
	 * Sets the lista plazo fijos ventas.
	 *
	 * @param listaPlazoFijosVentas
	 *            the new lista plazo fijos ventas
	 */
	public void setListaPlazoFijosVentas(List<CondicionesDTO> listaPlazoFijosVentas) {
		this.listaPlazoFijosVentas = listaPlazoFijosVentas;
	}

}

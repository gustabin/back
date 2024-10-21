/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class TenenciaTitulosOutDTO.
 */
public class TenenciaTitulosOutDTO {

	/** The list. */
	private List<TenenciaTitulosCuentaDTO> list = new ArrayList<TenenciaTitulosCuentaDTO>();

	/** The graficos. */
	private TenenciasGraficos graficos;
	
	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<TenenciaTitulosCuentaDTO> getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list
	 *            the new list
	 */
	public void setList(List<TenenciaTitulosCuentaDTO> list) {
		this.list = list;
	}

	/**
	 * Gets the graficos.
	 *
	 * @return the graficos
	 */
	public TenenciasGraficos getGraficos() {
		return graficos;
	}

	/**
	 * Sets the graficos.
	 *
	 * @param graficos
	 *            the new graficos
	 */
	public void setGraficos(TenenciasGraficos graficos) {
		this.graficos = graficos;
	}

}

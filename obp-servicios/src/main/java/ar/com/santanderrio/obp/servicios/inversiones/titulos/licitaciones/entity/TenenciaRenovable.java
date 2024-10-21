/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.List;

/**
 * The Class TenenciaRenovable.
 */
public class TenenciaRenovable {

	/** The moneda. */
	String moneda;

	/** The datos, lista de tenencias renovables. */
	private List<DatosConsultarTenenciaRenovableResponse> datosTenenciaRenovable;

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the datos tenencia renovable.
	 *
	 * @return the datos tenencia renovable
	 */
	public List<DatosConsultarTenenciaRenovableResponse> getDatosTenenciaRenovable() {
		return datosTenenciaRenovable;
	}

	/**
	 * Sets the datos tenencia renovable.
	 *
	 * @param datosTenenciaRenovable
	 *            the new datos tenencia renovable
	 */
	public void setDatosTenenciaRenovable(List<DatosConsultarTenenciaRenovableResponse> datosTenenciaRenovable) {
		this.datosTenenciaRenovable = datosTenenciaRenovable;
	}

}

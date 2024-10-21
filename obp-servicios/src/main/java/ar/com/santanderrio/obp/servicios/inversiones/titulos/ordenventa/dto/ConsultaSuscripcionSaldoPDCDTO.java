/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaSuscripcionSaldoPDCResponse;

/**
 * The Class ConsultaSuscripcionSaldoPDCDTO.
 */
public class ConsultaSuscripcionSaldoPDCDTO {

	/** The datos. */
	private DatosConsultaSuscripcionSaldoPDCResponse datos;
	
	/** The fallo servicio. */
	private Boolean falloServicio = false;

	
	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosConsultaSuscripcionSaldoPDCResponse getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosConsultaSuscripcionSaldoPDCResponse datos) {
		this.datos = datos;
	}

	/**
	 * Gets the fallo servicio.
	 *
	 * @return the fallo servicio
	 */
	public Boolean getFalloServicio() {
		return falloServicio;
	}

	/**
	 * Sets the fallo servicio.
	 *
	 * @param falloServicio
	 *            the new fallo servicio
	 */
	public void setFalloServicio(Boolean falloServicio) {
		this.falloServicio = falloServicio;
	}
		
		
}
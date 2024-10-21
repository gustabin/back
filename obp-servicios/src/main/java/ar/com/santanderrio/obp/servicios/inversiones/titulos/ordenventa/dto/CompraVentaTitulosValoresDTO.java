/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto;

import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosCompraVtaTitulosResponse;

/**
 * The Class CompraVentaTitulosValoresDTO.
 */
public class CompraVentaTitulosValoresDTO {

	/** The datos. */
	private DatosCompraVtaTitulosResponse datos;
	
	/** The tipo error. */
	private TipoError tipoError;

	
	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosCompraVtaTitulosResponse getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosCompraVtaTitulosResponse datos) {
		this.datos = datos;
	}

	/**
	 * Gets the tipo error.
	 *
	 * @return the tipoError
	 */
	public TipoError getTipoError() {
		return tipoError;
	}

	/**
	 * Sets the tipo error.
	 *
	 * @param tipoError
	 *            the tipoError to set
	 */
	public void setTipoError(TipoError tipoError) {
		this.tipoError = tipoError;
	}
		
}
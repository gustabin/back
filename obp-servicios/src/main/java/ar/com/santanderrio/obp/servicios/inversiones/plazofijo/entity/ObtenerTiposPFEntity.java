/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Record;

/**
 * The Class ObtenerTiposPFEntity.
 */
@Record
public class ObtenerTiposPFEntity {

	/** The obtener tipos PF. */
	private List<String> obtenerTiposPF = new ArrayList<String>();

	/**
	 * Gets the obtener tipos PF.
	 *
	 * @return the obtenerTiposPF
	 */
	public List<String> getObtenerTiposPF() {
		return obtenerTiposPF;
	}

	/**
	 * Sets the obtener tipos PF.
	 *
	 * @param obtenerTiposPF
	 *            the obtenerTiposPF to set
	 */
	public void setObtenerTiposPF(List<String> obtenerTiposPF) {
		this.obtenerTiposPF = obtenerTiposPF;
	}

}

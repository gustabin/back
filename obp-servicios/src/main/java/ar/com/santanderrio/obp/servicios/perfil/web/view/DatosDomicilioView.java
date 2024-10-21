/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.web.view;

import java.util.List;

/**
 * The Class DatosDomicilioView.
 */
public class DatosDomicilioView {

	/** Lista de domicilios. */
	private List<CambioDomicilioView> domicilios;

	/** Lista de provincias. */
	private List<ProvinciaView> provincias;

	/**
	 * Gets the domicilios.
	 *
	 * @return the domicilios
	 */
	public List<CambioDomicilioView> getDomicilios() {
		return domicilios;
	}

	/**
	 * Sets the domicilios.
	 *
	 * @param domicilios
	 *            the new domicilios
	 */
	public void setDomicilios(List<CambioDomicilioView> domicilios) {
		this.domicilios = domicilios;
	}

	/**
	 * Gets the provincias.
	 *
	 * @return the provincias
	 */
	public List<ProvinciaView> getProvincias() {
		return provincias;
	}

	/**
	 * Sets the provincias.
	 *
	 * @param provincias
	 *            the new provincias
	 */
	public void setProvincias(List<ProvinciaView> provincias) {
		this.provincias = provincias;
	}

}

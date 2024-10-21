/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.perfil.web.view.ProvinciaView;

/**
 * The Class DomiciliosDisponiblesView.
 */
public class DomiciliosDisponiblesView {

	/** The domicilios disponibles. */
	private List<DomicilioView> domiciliosDisponibles;

	/** The provincias. */
	private List<ProvinciaView> provincias;

	/** The motivos. */
	private List<MotivoView> motivos;
	
	/** The mensaje informacion. */
	private String mensajeInformacion;
	
	/** The mensaje informacion url. */
	private String mensajeInformacionUrl;

	/**
	 * Gets the domicilios disponibles.
	 *
	 * @return the domicilios disponibles
	 */
	public List<DomicilioView> getDomiciliosDisponibles() {
		return domiciliosDisponibles;
	}

	/**
	 * Sets the domicilios disponibles.
	 *
	 * @param domiciliosDisponibles
	 *            the new domicilios disponibles
	 */
	public void setDomiciliosDisponibles(List<DomicilioView> domiciliosDisponibles) {
		this.domiciliosDisponibles = domiciliosDisponibles;
	}

	/**
	 * Gets the provincias disponibles.
	 *
	 * @return the provincias disponibles
	 */
	public List<ProvinciaView> getProvincias() {
		return provincias;
	}

	/**
	 * Sets the provincias disponibles.
	 *
	 * @param provincias
	 *            the new provincias
	 */
	public void setProvincias(List<ProvinciaView> provincias) {
		this.provincias = provincias;
	}

	/**
	 * Gets the motivos.
	 *
	 * @return the motivos
	 */
	public List<MotivoView> getMotivos() {
		return motivos;
	}

	/**
	 * Sets the motivos.
	 *
	 * @param motivos
	 *            the new motivos
	 */
	public void setMotivos(List<MotivoView> motivos) {
		this.motivos = motivos;
	}

	/**
	 * Gets the mensaje informacion.
	 *
	 * @return the mensaje informacion
	 */
	public String getMensajeInformacion() {
		return mensajeInformacion;
	}

	/**
	 * Sets the mensaje informacion.
	 *
	 * @param mensajeInformacion
	 *            the new mensaje informacion
	 */
	public void setMensajeInformacion(String mensajeInformacion) {
		this.mensajeInformacion = mensajeInformacion;
	}

	/**
	 * Gets the mensaje informacion url.
	 *
	 * @return the mensaje informacion url
	 */
	public String getMensajeInformacionUrl() {
		return mensajeInformacionUrl;
	}

	/**
	 * Sets the mensaje informacion url.
	 *
	 * @param mensajeInformacionUrl
	 *            the new mensaje informacion url
	 */
	public void setMensajeInformacionUrl(String mensajeInformacionUrl) {
		this.mensajeInformacionUrl = mensajeInformacionUrl;
	}

}

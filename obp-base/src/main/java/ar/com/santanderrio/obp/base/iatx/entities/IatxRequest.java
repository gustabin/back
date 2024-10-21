package ar.com.santanderrio.obp.base.iatx.entities;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class IatxRequest.
 */
public class IatxRequest implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The nombre servicio. */
	private String nombreServicio;

	/** The version servicio. */
	private String versionServicio;

	/** The data. */
	private IatxRequestData data;

	/**
	 * Instantiates a new iatx request.
	 *
	 * @param nombreServicio
	 *            the nombre servicio
	 * @param versionServicio
	 *            the version servicio
	 */
	public IatxRequest(String nombreServicio, String versionServicio) {
		super();
		this.nombreServicio = nombreServicio;
		this.versionServicio = versionServicio;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public IatxRequestData getData() {
		return data;
	}

	/**
	 * Setter para data.
	 *
	 * @param data
	 *            el nuevo data
	 */
	public void setData(IatxRequestData data) {
		this.data = data;
	}

	/**
	 * Gets the nombre servicio.
	 *
	 * @return the nombre servicio
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * Setter para nombre servicio.
	 *
	 * @param nombreServicio
	 *            el nuevo nombre servicio
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	/**
	 * Gets the version servicio.
	 *
	 * @return the version servicio
	 */
	public String getVersionServicio() {
		return versionServicio;
	}

	/**
	 * Setter para version servicio.
	 *
	 * @param versionServicio
	 *            el nuevo version servicio
	 */
	public void setVersionServicio(String versionServicio) {
		this.versionServicio = versionServicio;
	}

}

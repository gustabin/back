/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosObtenerCuentasTitulosResponse.
 */
public class DatosObtenerCuentasTitulosResponse {

	/** The cuenta titulos. */
	@JsonProperty("CuentaTitulos")
	private long cuentaTitulos;

	/** The status cv. */
	@JsonProperty("StatusCv")
	private String statusCv;

	/** The status descripcion. */
	@JsonProperty("StatusDescripcion")
	private String statusDescripcion;

	/** The id. */
	@JsonProperty("Id")
	private String id;

	/** The fecha alta. */
	@JsonProperty("FechaAlta")
	private String fechaAlta;

	/** The usuario alta. */
	@JsonProperty("UsuarioAlta")
	private String usuarioAlta;

	/** The ip alta. */
	@JsonProperty("IpAlta")
	private String ipAlta;

	/** The enable. */
	@JsonProperty("Enable")
	private String enable;

	/** The is new. */
	@JsonProperty("IsNew")
	private String isNew;

	/**
	 * Gets the cuenta titulos.
	 *
	 * @return the cuentaTitulos
	 */
	public long getCuentaTitulos() {
		return cuentaTitulos;
	}

	/**
	 * Sets the cuenta titulos.
	 *
	 * @param cuentaTitulos
	 *            the cuentaTitulos to set
	 */
	public void setCuentaTitulos(long cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
	}

	/**
	 * Gets the status cv.
	 *
	 * @return the statusCv
	 */
	public String getStatusCv() {
		return statusCv;
	}

	/**
	 * Sets the status cv.
	 *
	 * @param statusCv
	 *            the statusCv to set
	 */
	public void setStatusCv(String statusCv) {
		this.statusCv = statusCv;
	}

	/**
	 * Gets the status descripcion.
	 *
	 * @return the statusDescripcion
	 */
	public String getStatusDescripcion() {
		return statusDescripcion;
	}

	/**
	 * Sets the status descripcion.
	 *
	 * @param statusDescripcion
	 *            the statusDescripcion to set
	 */
	public void setStatusDescripcion(String statusDescripcion) {
		this.statusDescripcion = statusDescripcion;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fechaAlta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the fechaAlta to set
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * Gets the usuario alta.
	 *
	 * @return the usuarioAlta
	 */
	public String getUsuarioAlta() {
		return usuarioAlta;
	}

	/**
	 * Sets the usuario alta.
	 *
	 * @param usuarioAlta
	 *            the usuarioAlta to set
	 */
	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	/**
	 * Gets the ip alta.
	 *
	 * @return the ipAlta
	 */
	public String getIpAlta() {
		return ipAlta;
	}

	/**
	 * Sets the ip alta.
	 *
	 * @param ipAlta
	 *            the ipAlta to set
	 */
	public void setIpAlta(String ipAlta) {
		this.ipAlta = ipAlta;
	}

	/**
	 * Gets the enable.
	 *
	 * @return the enable
	 */
	public String getEnable() {
		return enable;
	}

	/**
	 * Sets the enable.
	 *
	 * @param enable
	 *            the enable to set
	 */
	public void setEnable(String enable) {
		this.enable = enable;
	}

	/**
	 * Gets the checks if is new.
	 *
	 * @return the isNew
	 */
	public String getIsNew() {
		return isNew;
	}

	/**
	 * Sets the checks if is new.
	 *
	 * @param isNew
	 *            the isNew to set
	 */
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

}

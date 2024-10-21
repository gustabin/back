/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosObtenerSaldoCuentasCustodiaResponse.
 */
public class DatosObtenerSaldoCuentasCustodiaResponse implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7798935113048824070L;

	/** The Cuenta custodia. */
	@JsonProperty("CuentaCustodia")
	private long cuentaCustodia;

	/** The Moneda. */
	@JsonProperty("Moneda")
	private String moneda;

	/** The Cantidad. */
	@JsonProperty("Cantidad")
	private double cantidad;
	
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
	private boolean enable;
	
	/** The is new. */
	@JsonProperty("IsNew")
	private boolean isNew;
	
	/**
	 * Gets the cuenta custodia.
	 *
	 * @return the cuenta custodia
	 */
	public long getCuentaCustodia() {
		return cuentaCustodia;
	}

	/**
	 * Sets the cuenta custodia.
	 *
	 * @param cuentaCustodia
	 *            the new cuenta custodia
	 */
	public void setCuentaCustodia(long cuentaCustodia) {
		this.cuentaCustodia = cuentaCustodia;
	}

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
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad
	 *            the new cantidad
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
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
	 * Checks if is enable.
	 *
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * Sets the enable.
	 *
	 * @param enable
	 *            the enable to set
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * Checks if is new.
	 *
	 * @return the isNew
	 */
	public boolean isNew() {
		return isNew;
	}

	/**
	 * Sets the new.
	 *
	 * @param isNew
	 *            the isNew to set
	 */
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
}

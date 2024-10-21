/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.dto;

import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.SucursalSvcResponse;

/**
 * The Class SucursalesDTO.
 *
 * @author IT Resources
 */
public class SucursalesDTO {

	/** The descripcion. */
	private String descripcion;

	/** The domicilio. */
	private String domicilio;

	/** The localidad. */
	private String localidad;
	
	/** The sucursal. */
	private String sucursal;

	

	/**
	 * Instantiates a new sucursales DTO.
	 */
	public SucursalesDTO() {
		super();
	}

	/**
	 * Instantiates a new sucursales DTO.
	 *
	 * @param p
	 *            the p
	 */
	public SucursalesDTO(SucursalSvcResponse p) {
		this.descripcion = p.getDescri().getValue();
		this.domicilio = p.getDomicilio().getValue();
		this.localidad = p.getLocalidad().getValue();
		this.sucursal = p.getSucursal().getValue();
	}




	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}



	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	/**
	 * Gets the domicilio.
	 *
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}



	/**
	 * Sets the domicilio.
	 *
	 * @param domicilio
	 *            the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}



	/**
	 * Gets the localidad.
	 *
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}



	/**
	 * Sets the localidad.
	 *
	 * @param localidad
	 *            the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}



	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}



	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}



}

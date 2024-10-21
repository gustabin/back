/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.beanio.annotation.Segment;

/**
 * The Class IdentificadorClienteInEntity.
 */
public class IdentificadorClienteInEntity {

	/** The dni. */
	private String dni;

	/** The ip. */
	private String ip;

	/** The fecha. */
	private Date fecha;

	/** The id sesion. */
	private String idSesion;

	/** The ciclo. */
	private Integer ciclo;

	/** The fecha nacimiento. */
	private Date fechaNacimiento;

	/** The cantidad ocurrencias. */
	private Long cantidadOcurrencias;

	/** The nup. */
	private String nup;

	/** The productos. */
	@Segment(occursRef = "cantidadOcurrencias")
	private List<ProductoEntity> productos = new ArrayList<ProductoEntity>();

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Gets the id sesion.
	 *
	 * @return the idSesion
	 */
	public String getIdSesion() {
		return idSesion;
	}

	/**
	 * Sets the id sesion.
	 *
	 * @param idSesion
	 *            the idSesion to set
	 */
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

	/**
	 * Gets the ciclo.
	 *
	 * @return the ciclo
	 */
	public Integer getCiclo() {
		return ciclo;
	}

	/**
	 * Sets the ciclo.
	 *
	 * @param ciclo
	 *            the ciclo to set
	 */
	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	/**
	 * Gets the cantidad ocurrencias.
	 *
	 * @return the cantidadOcurrencias
	 */
	public Long getCantidadOcurrencias() {
		return cantidadOcurrencias;
	}

	/**
	 * Sets the cantidad ocurrencias.
	 *
	 * @param cantidadOcurrencias
	 *            the cantidadOcurrencias to set
	 */
	public void setCantidadOcurrencias(Long cantidadOcurrencias) {
		this.cantidadOcurrencias = cantidadOcurrencias;
	}

	/**
	 * Gets the productos.
	 *
	 * @return the productos
	 */
	public List<ProductoEntity> getProductos() {
		return productos;
	}

	/**
	 * Sets the productos.
	 *
	 * @param productos
	 *            the productos to set
	 */
	public void setProductos(List<ProductoEntity> productos) {
		this.productos = productos;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

}

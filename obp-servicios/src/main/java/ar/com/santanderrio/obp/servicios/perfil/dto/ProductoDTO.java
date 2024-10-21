/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.dto;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * Class ProductoDTO.
 *
 * @author Silvina_Luque
 */

@Record
public class ProductoDTO {

	/** The secuencia domicilio. */
	@Field
	private String secuenciaDomicilio;

	/** The cuenta. */
	@Field
	private String cuenta;

	/** The oficina. */
	@Field
	private String oficina;

	/** The descripcion oficina. */
	@Field
	private String descripcionOficina;

	/** The producto. */
	@Field
	private String producto;

	/** The descripcion producto. */
	@Field
	private String descripcionProducto;

	/** The subproducto. */
	@Field
	private String subproducto;

	/**
	 * Gets the secuencia domicilio.
	 *
	 * @return the secuencia domicilio
	 */
	public String getSecuenciaDomicilio() {
		return secuenciaDomicilio;
	}

	/**
	 * Sets the secuencia domicilio.
	 *
	 * @param secuenciaDomicilio
	 *            the new secuencia domicilio
	 */
	public void setSecuenciaDomicilio(String secuenciaDomicilio) {
		this.secuenciaDomicilio = secuenciaDomicilio;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the oficina.
	 *
	 * @return the oficina
	 */
	public String getOficina() {
		return oficina;
	}

	/**
	 * Sets the oficina.
	 *
	 * @param oficina
	 *            the new oficina
	 */
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	/**
	 * Gets the descripcion oficina.
	 *
	 * @return the descripcion oficina
	 */
	public String getDescripcionOficina() {
		return descripcionOficina;
	}

	/**
	 * Sets the descripcion oficina.
	 *
	 * @param descripcionOficina
	 *            the new descripcion oficina
	 */
	public void setDescripcionOficina(String descripcionOficina) {
		this.descripcionOficina = descripcionOficina;
	}

	/**
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto
	 *            the new producto
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * Gets the descripcion producto.
	 *
	 * @return the descripcion producto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * Sets the descripcion producto.
	 *
	 * @param descripcionProducto
	 *            the new descripcion producto
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * Gets the subproducto.
	 *
	 * @return the subproducto
	 */
	public String getSubproducto() {
		return subproducto;
	}

	/**
	 * Sets the subproducto.
	 *
	 * @param subproducto
	 *            the new subproducto
	 */
	public void setSubproducto(String subproducto) {
		this.subproducto = subproducto;
	}

}

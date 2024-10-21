/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * CuentaOperacionExteriorEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class CuentaOperacionExteriorEntity {

	/** The numero pan. */
	@Field
	private String numeroPan;

	/** The cuenta relacionada. */
	@Field
	private String cuentaRelacionada;

	/** The relacion estado. */
	@Field
	private String relacionEstado;

	/** The plan sueldo. */
	@Field
	private String planSueldo;

	/** The marca fast cash. */
	@Field
	private String marcaFastCash;

	/** The suscriptor. */
	@Field
	private String suscriptor;

	/** The datos alta. */
	@Field
	private String datosAlta;

	/** The datos modificacion. */
	@Field
	private String datosModificacion;

	/** The cuenta preferida. */
	@Field
	private String cuentaPreferida;

	/** The otros datos. */
	@Field
	private String otrosDatos;

	/**
	 * Gets the numero pan.
	 *
	 * @return the numero pan
	 */
	public String getNumeroPan() {
		return numeroPan;
	}

	/**
	 * Sets the numero pan.
	 *
	 * @param numeroPan
	 *            the new numero pan
	 */
	public void setNumeroPan(String numeroPan) {
		this.numeroPan = numeroPan;
	}

	/**
	 * Gets the cuenta relacionada.
	 *
	 * @return the cuenta relacionada
	 */
	public String getCuentaRelacionada() {
		return cuentaRelacionada;
	}

	/**
	 * Sets the cuenta relacionada.
	 *
	 * @param cuentaRelacionada
	 *            the new cuenta relacionada
	 */
	public void setCuentaRelacionada(String cuentaRelacionada) {
		this.cuentaRelacionada = cuentaRelacionada;
	}

	/**
	 * Gets the relacion estado.
	 *
	 * @return the relacion estado
	 */
	public String getRelacionEstado() {
		return relacionEstado;
	}

	/**
	 * Sets the relacion estado.
	 *
	 * @param relacionEstado
	 *            the new relacion estado
	 */
	public void setRelacionEstado(String relacionEstado) {
		this.relacionEstado = relacionEstado;
	}

	/**
	 * Gets the plan sueldo.
	 *
	 * @return the plan sueldo
	 */
	public String getPlanSueldo() {
		return planSueldo;
	}

	/**
	 * Sets the plan sueldo.
	 *
	 * @param planSueldo
	 *            the new plan sueldo
	 */
	public void setPlanSueldo(String planSueldo) {
		this.planSueldo = planSueldo;
	}

	/**
	 * Gets the marca fast cash.
	 *
	 * @return the marca fast cash
	 */
	public String getMarcaFastCash() {
		return marcaFastCash;
	}

	/**
	 * Sets the marca fast cash.
	 *
	 * @param marcaFastCash
	 *            the new marca fast cash
	 */
	public void setMarcaFastCash(String marcaFastCash) {
		this.marcaFastCash = marcaFastCash;
	}

	/**
	 * Gets the suscriptor.
	 *
	 * @return the suscriptor
	 */
	public String getSuscriptor() {
		return suscriptor;
	}

	/**
	 * Sets the suscriptor.
	 *
	 * @param suscriptor
	 *            the new suscriptor
	 */
	public void setSuscriptor(String suscriptor) {
		this.suscriptor = suscriptor;
	}

	/**
	 * Gets the datos alta.
	 *
	 * @return the datos alta
	 */
	public String getDatosAlta() {
		return datosAlta;
	}

	/**
	 * Sets the datos alta.
	 *
	 * @param datosAlta
	 *            the new datos alta
	 */
	public void setDatosAlta(String datosAlta) {
		this.datosAlta = datosAlta;
	}

	/**
	 * Gets the datos modificacion.
	 *
	 * @return the datos modificacion
	 */
	public String getDatosModificacion() {
		return datosModificacion;
	}

	/**
	 * Sets the datos modificacion.
	 *
	 * @param datosModificacion
	 *            the new datos modificacion
	 */
	public void setDatosModificacion(String datosModificacion) {
		this.datosModificacion = datosModificacion;
	}

	/**
	 * Gets the cuenta preferida.
	 *
	 * @return the cuenta preferida
	 */
	public String getCuentaPreferida() {
		return cuentaPreferida;
	}

	/**
	 * Sets the cuenta preferida.
	 *
	 * @param cuentaPreferida
	 *            the new cuenta preferida
	 */
	public void setCuentaPreferida(String cuentaPreferida) {
		this.cuentaPreferida = cuentaPreferida;
	}

	/**
	 * Gets the otros datos.
	 *
	 * @return the otros datos
	 */
	public String getOtrosDatos() {
		return otrosDatos;
	}

	/**
	 * Sets the otros datos.
	 *
	 * @param otrosDatos
	 *            the new otros datos
	 */
	public void setOtrosDatos(String otrosDatos) {
		this.otrosDatos = otrosDatos;
	}

}

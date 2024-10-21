/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.entities;

import org.beanio.annotation.Field;

/**
 * The Class ProductoEntity.
 */
public class ProductoEntity {

	/** The codigo producto. */
	@Field
	private String codigoProducto;

	/** The codigo sub producto. */
	@Field
	private String codigoSubProducto;

	/** The calidad participacion. */
	@Field
	private String calidadParticipacion;

	/** The codigo oficina. */
	@Field
	private String codigoOficina;

	/** The nro contrato. */
	@Field
	private String nroContrato;
	
//	/** The condicion Anph. */
//	@Field
//	private String condicionAnph;
//	
//	/** The nro celular anph. */
//	@Field
//	private String nroCelularAnph;
//	
//	/** The resultado mya obp. */
//	@Field
//	private String resultadoMyaObp;
//	
//	@Field
//	private String campo1;
//	
//	@Field
//	private String campo2;
//
//	public String getCampo1() {
//		return campo1;
//	}
//
//	public void setCampo1(String campo1) {
//		this.campo1 = campo1;
//	}
//
//	public String getCampo2() {
//		return campo2;
//	}
//
//	public void setCampo2(String campo2) {
//		this.campo2 = campo2;
//	}
//
//	public String getCondicionAnph() {
//		return condicionAnph;
//	}
//
//	public void setCondicionAnph(String condicionAnph) {
//		this.condicionAnph = condicionAnph;
//	}
//
//	public String getNroCelularAnph() {
//		return nroCelularAnph;
//	}
//
//	public void setNroCelularAnph(String nroCelularAnph) {
//		this.nroCelularAnph = nroCelularAnph;
//	}
//
//	public String getResultadoMyaObp() {
//		return resultadoMyaObp;
//	}
//
//	public void setResultadoMyaObp(String resultadoMyaObp) {
//		this.resultadoMyaObp = resultadoMyaObp;
//	}

	/**
	 * Gets the codigo producto.
	 *
	 * @return the codigo producto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * Sets the codigo producto.
	 *
	 * @param codigoProducto
	 *            the new codigo producto
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * Gets the codigo sub producto.
	 *
	 * @return the codigo sub producto
	 */
	public String getCodigoSubProducto() {
		return codigoSubProducto;
	}

	/**
	 * Sets the codigo sub producto.
	 *
	 * @param codigoSubProducto
	 *            the new codigo sub producto
	 */
	public void setCodigoSubProducto(String codigoSubProducto) {
		this.codigoSubProducto = codigoSubProducto;
	}

	/**
	 * Gets the calidad participacion.
	 *
	 * @return the calidad participacion
	 */
	public String getCalidadParticipacion() {
		return calidadParticipacion;
	}

	/**
	 * Sets the calidad participacion.
	 *
	 * @param calidadParticipacion
	 *            the new calidad participacion
	 */
	public void setCalidadParticipacion(String calidadParticipacion) {
		this.calidadParticipacion = calidadParticipacion;
	}

	/**
	 * Gets the codigo oficina.
	 *
	 * @return the codigo oficina
	 */
	public String getCodigoOficina() {
		return codigoOficina;
	}

	/**
	 * Sets the codigo oficina.
	 *
	 * @param codigoOficina
	 *            the new codigo oficina
	 */
	public void setCodigoOficina(String codigoOficina) {
		this.codigoOficina = codigoOficina;
	}

	/**
	 * Gets the nro contrato.
	 *
	 * @return the nro contrato
	 */
	public String getNroContrato() {
		return nroContrato;
	}

	/**
	 * Sets the nro contrato.
	 *
	 * @param nroContrato
	 *            the new nro contrato
	 */
	public void setNroContrato(String nroContrato) {
		this.nroContrato = nroContrato;
	}

}

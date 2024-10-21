/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author juan.pablo.picate
 *
 */
public class ConsultarCondicionesImpuestosEntity {

	/** The descripcion. */
	@Field
	private String descripcion;

	/** The tipo impuesto juris. */
	@Field
	private String tipoImpuestoJuris;

	/** 13 enteros, 2 decimales. */
	@Field
	private String montoMonedaLocal;

	/** 13 enteros, 2 decimales. */
	@Field
	private String montoMonedaExtranjera;

	/** The momento de cobro. */
	@Field
	private String momentoDeCobro;

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
	 * Gets the tipo impuesto juris.
	 *
	 * @return the tipoImpuestoJuris
	 */
	public String getTipoImpuestoJuris() {
		return tipoImpuestoJuris;
	}

	/**
	 * Sets the tipo impuesto juris.
	 *
	 * @param tipoImpuestoJuris
	 *            the tipoImpuestoJuris to set
	 */
	public void setTipoImpuestoJuris(String tipoImpuestoJuris) {
		this.tipoImpuestoJuris = tipoImpuestoJuris;
	}

	/**
	 * Gets the monto moneda local.
	 *
	 * @return the montoMonedaLocal
	 */
	public String getMontoMonedaLocal() {
		return montoMonedaLocal;
	}

	/**
	 * Sets the monto moneda local.
	 *
	 * @param montoMonedaLocal
	 *            the montoMonedaLocal to set
	 */
	public void setMontoMonedaLocal(String montoMonedaLocal) {
		this.montoMonedaLocal = montoMonedaLocal;
	}

	/**
	 * Gets the monto moneda extranjera.
	 *
	 * @return the montoMonedaExtranjera
	 */
	public String getMontoMonedaExtranjera() {
		return montoMonedaExtranjera;
	}

	/**
	 * Sets the monto moneda extranjera.
	 *
	 * @param montoMonedaExtranjera
	 *            the montoMonedaExtranjera to set
	 */
	public void setMontoMonedaExtranjera(String montoMonedaExtranjera) {
		this.montoMonedaExtranjera = montoMonedaExtranjera;
	}

	/**
	 * Gets the momento de cobro.
	 *
	 * @return the momentoDeCobro
	 */
	public String getMomentoDeCobro() {
		return momentoDeCobro;
	}

	/**
	 * Sets the momento de cobro.
	 *
	 * @param momentoDeCobro
	 *            the momentoDeCobro to set
	 */
	public void setMomentoDeCobro(String momentoDeCobro) {
		this.momentoDeCobro = momentoDeCobro;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("descripcion", descripcion)
				.append("tipoImpuestoJuris", tipoImpuestoJuris).append("montoMonedaLocal", montoMonedaLocal)
				.append("montoMonedaExtranjera", montoMonedaExtranjera).append("momentoDeCobro", momentoDeCobro)
				.toString();
	}
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun;

import org.apache.commons.lang3.StringUtils;

/**
 * The Enum MonedaEspecieEnum.
 */
public enum MonedaEspecieEnum {

	/** The ars. */
	ARS("ARS", "Pesos"),
	
	/** The usd. */
	USD("USD", "Dólares"),
	
	/** The gbp. */
	GBP("GBp", "Libras"),
	
	/** The brl. */
	BRL("BRL", "Reales"),
	
	/** The clp. */
	CLP("CLP", "Pesos Chilenos"),
	
	/** The eur. */
	EUR("EUR", "Euros"),
	
	/** The mxn. */
	MXN("MXN", "Pesos Mejicanos"),
	
	/** The vef. */
	VEF("VEF", "Bolívares"),
	
	/** The chf. */
	CHF("CHF", "Francos Suizos"),
	
	/** The zac. */
	ZAC("ZAc", "South African Cents"),
	
	/** The aud. */
	AUD("AUD", "Dólares Australianos"),
	
	/** The ats. */
	ATS("ATS", "Chelines Austríacos");

	/** The codigo. */
	private String codigo;
	
	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new moneda especie enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
	private MonedaEspecieEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	/**
	 * Obtener descripcion por codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the string
	 */
	public static String obtenerDescripcionPorCodigo(String codigo) {
		for (MonedaEspecieEnum monedaEnum : values()) {
			if (StringUtils.equals(codigo, monedaEnum.getCodigo())) {
				return monedaEnum.getDescripcion();
			}
		}
		return null;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

}

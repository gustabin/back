/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.dto;

/**
 * The Class ConsultaMonedaOutDTO.
 */
public class ConsultaMonedaOutDTO {

	/** Codigo BCRA moneda. */
	private String codBCRAMoneda;
	
	/** Codigo moneda. */
	private String codigoMoneda;
	
	/** Descripcion moneda. */
	private String descripcionMoneda;

	/**
	 * Gets the cod BCRA moneda.
	 *
	 * @return the codBCRAMoneda
	 */
	public String getCodBCRAMoneda() {
		return codBCRAMoneda;
	}

	/**
	 * Sets the cod BCRA moneda.
	 *
	 * @param codBCRAMoneda
	 *            the codBCRAMoneda to set
	 */
	public void setCodBCRAMoneda(String codBCRAMoneda) {
		this.codBCRAMoneda = codBCRAMoneda;
	}

	/**
	 * Gets the codigo moneda.
	 *
	 * @return the codigoMoneda
	 */
	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	/**
	 * Sets the codigo moneda.
	 *
	 * @param codigoMoneda
	 *            the codigoMoneda to set
	 */
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	/**
	 * Gets the descripcion moneda.
	 *
	 * @return the descripcionMoneda
	 */
	public String getDescripcionMoneda() {
		return descripcionMoneda;
	}

	/**
	 * Sets the descripcion moneda.
	 *
	 * @param descripcionMoneda
	 *            the descripcionMoneda to set
	 */
	public void setDescripcionMoneda(String descripcionMoneda) {
		this.descripcionMoneda = descripcionMoneda;
	}
	
}

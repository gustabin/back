/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.dto;

/**
 * The Class ConsultaPaisesOutDTO.
 */
public class ConsultaPaisesOutDTO implements Comparable<ConsultaPaisesOutDTO>{
	
	/** Codigo Pais. */
	private String codigoPais;
	
	/** Descripcion Pais. */
	private String descripcionPais;

	/**
	 * Gets the codigo pais.
	 *
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * Sets the codigo pais.
	 *
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * Gets the descripcion pais.
	 *
	 * @return the descripcionPais
	 */
	public String getDescripcionPais() {
		return descripcionPais;
	}

	/**
	 * Sets the descripcion pais.
	 *
	 * @param descripcionPais
	 *            the descripcionPais to set
	 */
	public void setDescripcionPais(String descripcionPais) {
		this.descripcionPais = descripcionPais;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ConsultaPaisesOutDTO o) {
		return  this.descripcionPais.compareTo(o.descripcionPais);
	}
}

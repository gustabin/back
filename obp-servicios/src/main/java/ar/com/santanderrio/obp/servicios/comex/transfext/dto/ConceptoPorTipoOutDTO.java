/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.dto;

import java.math.BigDecimal;

/**
 * The Class ConceptoPorTipoOutDTO.
 */
public class ConceptoPorTipoOutDTO {
	
	/** The id Concepto. */
	private BigDecimal idConcepto;
	
	/** The concepto. */
	private String concepto;
	
	/**
	 * Gets the id concepto.
	 *
	 * @return the idConcepto
	 */
	public BigDecimal getIdConcepto() {
		return idConcepto;
	}

	/**
	 * Sets the id concepto.
	 *
	 * @param idConcepto
	 *            the idConcepto to set
	 */
	public void setIdConcepto(BigDecimal idConcepto) {
		this.idConcepto = idConcepto;
	}

	/**
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Sets the concepto.
	 *
	 * @param concepto
	 *            the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	
}

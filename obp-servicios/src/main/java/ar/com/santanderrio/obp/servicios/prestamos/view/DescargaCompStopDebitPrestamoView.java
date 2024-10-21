/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


/**
 * The Class DescargaCompStopDebitPrestamoView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DescargaCompStopDebitPrestamoView {

	
	/** The comprobante. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String comprobante;
	
	/** The legal stop debit prestamos. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String legalStopDebitPrestamos;

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante the new comprobante
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * Gets the legal stop debit prestamos.
	 *
	 * @return the legal stop debit prestamos
	 */
	public String getLegalStopDebitPrestamos() {
		return legalStopDebitPrestamos;
	}

	/**
	 * Sets the legal stop debit prestamos.
	 *
	 * @param legalStopDebitPrestamos the new legal stop debit prestamos
	 */
	public void setLegalStopDebitPrestamos(String legalStopDebitPrestamos) {
		this.legalStopDebitPrestamos = legalStopDebitPrestamos;
	}
	
}

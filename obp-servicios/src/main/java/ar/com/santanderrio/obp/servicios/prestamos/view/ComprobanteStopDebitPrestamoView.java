/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


// TODO: Auto-generated Javadoc
/**
 * The Class ConfirmacionStopDebitView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ComprobanteStopDebitPrestamoView {

	/** The comprobante. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String comprobante;
	
	/** The fecha. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String fecha;

	/** The periodos stop debit prestamos. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private List<PeriodosStopDebitPrestamo> periodosStopDebitPrestamos;

	/** The legal stop debit prestamos. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String legalStopDebitPrestamos;
		
	
	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

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
	 * Gets the periodos stop debit prestamos.
	 *
	 * @return the periodos stop debit prestamos
	 */
	public List<PeriodosStopDebitPrestamo> getPeriodosStopDebitPrestamos() {
		return periodosStopDebitPrestamos;
	}

	/**
	 * Sets the periodos stop debit prestamos.
	 *
	 * @param periodosStopDebitPrestamos the new periodos stop debit prestamos
	 */
	public void setPeriodosStopDebitPrestamos(List<PeriodosStopDebitPrestamo> periodosStopDebitPrestamos) {
		this.periodosStopDebitPrestamos = periodosStopDebitPrestamos;
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

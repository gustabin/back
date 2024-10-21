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
public class ConfirmacionStopDebitView {

	/** The periodos stop debit prestamos. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private List<PeriodosStopDebitPrestamo> periodosStopDebitPrestamos;
	
	/** The legal stop debit prestamos. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String legalStopDebitPrestamos;
	
	

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
    
}

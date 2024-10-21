/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

// TODO: Auto-generated Javadoc
/**
 * The Class PeriodosStopDebitPrestamo.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include = Inclusion.NON_NULL)
public class PeriodosStopDebitPrestamo {	
	
	/** The nro prestamo. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String nroPrestamo;
	
	/** The id. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String id;
	
	/** The periodo stop debit. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String periodoStopDebit;

	
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the nro prestamo.
	 *
	 * @return the nro prestamo
	 */
	public String getNroPrestamo() {
		return nroPrestamo;
	}

	/**
	 * Sets the nro prestamo.
	 *
	 * @param nroPrestamo the new nro prestamo
	 */
	public void setNroPrestamo(String nroPrestamo) {
		this.nroPrestamo = nroPrestamo;
	}

	/**
	 * Gets the periodo stop debit.
	 *
	 * @return the periodo stop debit
	 */
	public String getPeriodoStopDebit() {
		return periodoStopDebit;
	}

	/**
	 * Sets the periodo stop debit.
	 *
	 * @param periodoStopDebit the new periodo stop debit
	 */
	public void setPeriodoStopDebit(String periodoStopDebit) {
		this.periodoStopDebit = periodoStopDebit;
	}
	
	
	
	
}

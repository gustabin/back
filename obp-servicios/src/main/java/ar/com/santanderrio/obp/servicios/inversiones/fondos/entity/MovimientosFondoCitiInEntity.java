/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * The Class MovimientosFondoCitiInEntity.
 */
public class MovimientosFondoCitiInEntity {

    /** The cuenta titulo. */
    private String cuentaTitulo;
    
    /** The codigo fondo. */
    private String codigoFondo;
    
    /** The fecha desde. */
    private XMLGregorianCalendar fechaDesde;
    
    /** The fecha hasta. */
    private XMLGregorianCalendar fechaHasta;
    
    /** The exception. */
    private Exception exception;
    
    /**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuenta titulo
	 */
    public String getCuentaTitulo() {
        return cuentaTitulo;
    }

    /**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the new cuenta titulo
	 */
    public void setCuentaTitulo(String cuentaTitulo) {
        this.cuentaTitulo = cuentaTitulo;
    }

    /**
	 * Gets the codigo fondo.
	 *
	 * @return the codigo fondo
	 */
    public String getCodigoFondo() {
        return codigoFondo;
    }

    /**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the new codigo fondo
	 */
    public void setCodigoFondo(String codigoFondo) {
        this.codigoFondo = codigoFondo;
    }

    /**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
    public XMLGregorianCalendar getFechaDesde() {
        return fechaDesde;
    }

    /**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the new fecha desde
	 */
    public void setFechaDesde(XMLGregorianCalendar fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
    public XMLGregorianCalendar getFechaHasta() {
        return fechaHasta;
    }

    /**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the new fecha hasta
	 */
    public void setFechaHasta(XMLGregorianCalendar fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
	 * Gets the exception.
	 *
	 * @return the exception
	 */
    public Exception getException() {
        return exception;
    }

    /**
	 * Sets the exception.
	 *
	 * @param exception
	 *            the new exception
	 */
    public void setException(Exception exception) {
        this.exception = exception;
    }
    
}

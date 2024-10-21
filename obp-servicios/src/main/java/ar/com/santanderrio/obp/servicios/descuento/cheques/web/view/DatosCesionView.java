/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DatosCesionDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.MensajesDescuentoEntity;

/**
 * The Class DatosCesionView.
 */
public class DatosCesionView {

    /** The linea S. */
    private String lineaS;
    
    /** The monto disponible S. */
    private String montoDisponibleS;
    
    /** The nro cuenta. */
    private String nroCuenta;
    
    /** The dni. */
    private String dni;
    
    /** The mensajes. */
    private MensajesDescuentoEntity mensajes;
    
    /** The error estadistica. */
    private boolean errorEstadistica;

    /** The max importe cheque. */
    private String maxImporteCheque;

    /** The max cant cheques. */
    private String maxCantCheques;

    /**
	 * Instantiates a new datos cesion view.
	 */
    public DatosCesionView(){
        super();
        lineaS = "-";
        montoDisponibleS = "-";
    }
    
    /**
	 * Instantiates a new datos cesion view.
	 *
	 * @param datosCesion
	 *            the datos cesion
	 */
    public DatosCesionView(DatosCesionDTO datosCesion){
        super();
        lineaS = ISBANStringUtils.formatearSaldo(datosCesion.getLineaS());
        montoDisponibleS = ISBANStringUtils.formatearSaldo(datosCesion.getMontoDisponibleS());
        nroCuenta = datosCesion.getNroCuenta();
        dni = datosCesion.getDni();
        mensajes = datosCesion.getMensajes();
        maxCantCheques = datosCesion.getMaxCantCheques();
        maxImporteCheque = datosCesion.getMaxImporteCheque();
    }

    /**
	 * Gets the linea S.
	 *
	 * @return the linea S
	 */
    public String getLineaS() {
        return lineaS;
    }

    /**
	 * Sets the linea S.
	 *
	 * @param lineaS
	 *            the new linea S
	 */
    public void setLineaS(String lineaS) {
        this.lineaS = lineaS;
    }

    /**
	 * Gets the monto disponible S.
	 *
	 * @return the monto disponible S
	 */
    public String getMontoDisponibleS() {
        return montoDisponibleS;
    }

    /**
	 * Sets the monto disponible S.
	 *
	 * @param montoDisponibleS
	 *            the new monto disponible S
	 */
    public void setMontoDisponibleS(String montoDisponibleS) {
        this.montoDisponibleS = montoDisponibleS;
    }

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni
	 *            the new dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Gets the mensajes.
	 *
	 * @return the mensajes
	 */
	public MensajesDescuentoEntity getMensajes() {
		return mensajes;
	}

	/**
	 * Sets the mensajes.
	 *
	 * @param mensajes
	 *            the new mensajes
	 */
	public void setMensajes(MensajesDescuentoEntity mensajes) {
		this.mensajes = mensajes;
	}

	/**
	 * Checks if is error estadistica.
	 *
	 * @return true, if is error estadistica
	 */
	public boolean isErrorEstadistica() {
		return errorEstadistica;
	}

	/**
	 * Sets the error estadistica.
	 *
	 * @param errorEstadistica
	 *            the new error estadistica
	 */
	public void setErrorEstadistica(boolean errorEstadistica) {
		this.errorEstadistica = errorEstadistica;
	}

	/**
	 * Gets the max importe cheque.
	 *
	 * @return the max importe cheque
	 */
	public String getMaxImporteCheque() {
		return maxImporteCheque;
	}

	/**
	 * Gets the max cant cheques.
	 *
	 * @return the max cant cheques
	 */
	public String getMaxCantCheques() {
		return maxCantCheques;
	}

	/**
	 * Sets the max importe cheque.
	 *
	 * @param maxImporteCheque
	 *            the new max importe cheque
	 */
	public void setMaxImporteCheque(String maxImporteCheque) {
		this.maxImporteCheque = maxImporteCheque;
	}

	/**
	 * Sets the max cant cheques.
	 *
	 * @param maxCantCheques
	 *            the new max cant cheques
	 */
	public void setMaxCantCheques(String maxCantCheques) {
		this.maxCantCheques = maxCantCheques;
	}

}

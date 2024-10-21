/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class DatosCesionDTO.
 */
public class DatosCesionDTO  implements Serializable{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6564559645191404587L;

	/** The calificado S. */
	private String calificadoS;
    
    /** The linea S. */
    private BigDecimal lineaS;
    
    /** The monto disponible S. */
    private BigDecimal montoDisponibleS;
    
    /** The tipo linea S. */
    private String tipoLineaS;
    
    /** The nro cuenta. */
    private String nroCuenta;
    
    /** The dni. */
    private String dni;
    
    /** The mensajes. */
    private MensajesDescuentoEntity mensajes;

    /** The max importe cheque. */
    private String maxImporteCheque;

    /** The max cant cheques. */
    private String maxCantCheques;

    /**
	 * Gets the calificado S.
	 *
	 * @return the calificado S
	 */
    public String getCalificadoS() {
        return calificadoS;
    }

    /**
	 * Sets the calificado S.
	 *
	 * @param calificadoS
	 *            the new calificado S
	 */
    public void setCalificadoS(String calificadoS) {
        this.calificadoS = calificadoS;
    }

    /**
	 * Gets the linea S.
	 *
	 * @return the linea S
	 */
    public BigDecimal getLineaS() {
        return lineaS;
    }

    /**
	 * Sets the linea S.
	 *
	 * @param lineaS
	 *            the new linea S
	 */
    public void setLineaS(BigDecimal lineaS) {
        this.lineaS = lineaS;
    }

    /**
	 * Gets the monto disponible S.
	 *
	 * @return the monto disponible S
	 */
    public BigDecimal getMontoDisponibleS() {
        return montoDisponibleS;
    }

    /**
	 * Sets the monto disponible S.
	 *
	 * @param montoDisponibleS
	 *            the new monto disponible S
	 */
    public void setMontoDisponibleS(BigDecimal montoDisponibleS) {
        this.montoDisponibleS = montoDisponibleS;
    }

    /**
	 * Gets the tipo linea S.
	 *
	 * @return the tipo linea S
	 */
    public String getTipoLineaS() {
        return tipoLineaS;
    }

    /**
	 * Sets the tipo linea S.
	 *
	 * @param tipoLineaS
	 *            the new tipo linea S
	 */
    public void setTipoLineaS(String tipoLineaS) {
        this.tipoLineaS = tipoLineaS;
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
	 * @return the maxImporteCheque
	 */
	public String getMaxImporteCheque() {
		return maxImporteCheque;
	}

	/**
	 * @return the maxCantCheques
	 */
	public String getMaxCantCheques() {
		return maxCantCheques;
	}

	/**
	 * @param maxImporteCheque
	 *            the maxImporteCheque to set
	 */
	public void setMaxImporteCheque(String maxImporteCheque) {
		this.maxImporteCheque = maxImporteCheque;
	}

	/**
	 * @param maxCantCheques
	 *            the maxCantCheques to set
	 */
	public void setMaxCantCheques(String maxCantCheques) {
		this.maxCantCheques = maxCantCheques;
	}

}

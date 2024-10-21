package ar.com.santanderrio.obp.servicios.debinws.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.debinws.view.GestionarAdhesionDebinesView;

/**
 * The Class CuentaAdhesionDTO.
 */
public class CuentaAdhesionDTO  implements Cloneable{

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GestionarAdhesionDebinesView.class);
	
    /**  cbu. */
    private int idCuenta;

    /**  nroCuenta. */
    private String cuentaNumero;

    /**  tipoCuenta. */
    private String cuentaDescripcion;

    /**  tipoCuenta. */
    private String cuentaAlias;

    /**  adherida. */
    private boolean adherida;

    /**  especial. */
    private boolean especial;

    /**  gestionOk. */
    private boolean gestionOk;
    
    /**  label cuenta. */
    private String labelCuenta;
    
    /**
     * Gets the id cuenta.
     *
     * @return the idCuenta
     */
    public int getIdCuenta() {
        return idCuenta;
    }

    /**
     * Sets the id cuenta.
     *
     * @param idCuenta the idCuenta to set
     */
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     * Checks if is adherida.
     *
     * @return the nroCuenta
     */

    /**
     * @return the adherida
     */
    public boolean isAdherida() {
        return adherida;
    }

    /**
     * Gets the cuenta numero.
     *
     * @return the cuentaNumero
     */
    public String getCuentaNumero() {
        return cuentaNumero;
    }

    /**
     * Sets the cuenta numero.
     *
     * @param cuentaNumero the cuentaNumero to set
     */
    public void setCuentaNumero(String cuentaNumero) {
        this.cuentaNumero = cuentaNumero;
    }

    /**
     * Gets the cuenta descripcion.
     *
     * @return the cuentaDescripcion
     */
    public String getCuentaDescripcion() {
        return cuentaDescripcion;
    }

    /**
     * Sets the cuenta descripcion.
     *
     * @param cuentaDescripcion the cuentaDescripcion to set
     */
    public void setCuentaDescripcion(String cuentaDescripcion) {
        this.cuentaDescripcion = cuentaDescripcion;
    }

    /**
     * Gets the cuenta alias.
     *
     * @return the cuentaAlias
     */
    public String getCuentaAlias() {
        return cuentaAlias;
    }

    /**
     * Sets the cuenta alias.
     *
     * @param cuentaAlias the cuentaAlias to set
     */
    public void setCuentaAlias(String cuentaAlias) {
        this.cuentaAlias = cuentaAlias;
    }

    /**
     * Sets the adherida.
     *
     * @param adherida the adherida to set
     */
    public void setAdherida(boolean adherida) {
        this.adherida = adherida;
    }

    /**
     * Checks if is especial.
     *
     * @return the especial
     */
    public boolean isEspecial() {
        return especial;
    }

    /**
     * Sets the especial.
     *
     * @param especial the especial to set
     */
    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    /**
     * Checks if is gestion ok.
     *
     * @return the gestionOk
     */
    public boolean isGestionOk() {
        return gestionOk;
    }

    /**
     * Sets the gestion ok.
     *
     * @param gestionOk the gestionOk to set
     */
    public void setGestionOk(boolean gestionOk) {
        this.gestionOk = gestionOk;
    }

	/**
	 * Gets the label cuenta.
	 *
	 * @return the labelCuenta
	 */
	public String getLabelCuenta() {
		return labelCuenta;
	}

	/**
	 * Sets the label cuenta.
	 *
	 * @param labelCuenta the labelCuenta to set
	 */
	public void setLabelCuenta(String labelCuenta) {
		this.labelCuenta = labelCuenta;
	}
    

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public CuentaAdhesionDTO clone() {
		try {
			return (CuentaAdhesionDTO) super.clone();
		} catch (CloneNotSupportedException e) {
			LOGGER.error("Error clonando Objeto", e);
			return null;
		}
	}
       
}

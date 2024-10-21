/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

// TODO: Auto-generated Javadoc
/**
 * The Class ObtenerPrestamoInView.
 */
public class ObtenerPrestamoInView {

    /** The is personal. */
    private boolean isPersonal;

    /** The is hipotecario. */
    private boolean isHipotecario;

    /** The is prendario. */
    private boolean isPrendario;
    
    /** The is prestamo sueldo. */
    private boolean isPrestamoSueldo;
    
    private boolean isStopDebitPrestamo;
    
    private boolean isPreaprobadoMonoproducto;
    
    private boolean isSolicitudPendiente;

    public boolean getIsSolicitudPendiente() {
		return isSolicitudPendiente;
	}

	public void setIsSolicitudPendiente(boolean isSolicitudPendiente) {
		this.isSolicitudPendiente = isSolicitudPendiente;
	}

	/**
     * Gets the checks if is personal.
     *
     * @return the checks if is personal
     */
    public boolean getIsPersonal() {
        return isPersonal;
    }

    /**
     * Sets the checks if is personal.
     *
     * @param isPersonal
     *            the new checks if is personal
     */
    public void setIsPersonal(boolean isPersonal) {
        this.isPersonal = isPersonal;
    }

    /**
     * Gets the checks if is hipotecario.
     *
     * @return the checks if is hipotecario
     */
    public boolean getIsHipotecario() {
        return isHipotecario;
    }

    /**
     * Sets the checks if is hipotecario.
     *
     * @param isHipotecario
     *            the new checks if is hipotecario
     */
    public void setIsHipotecario(boolean isHipotecario) {
        this.isHipotecario = isHipotecario;
    }

    /**
     * Gets the checks if is prendario.
     *
     * @return the checks if is prendario
     */
    public boolean getIsPrendario() {
        return isPrendario;
    }

    /**
     * Sets the checks if is prendario.
     *
     * @param isPrendario
     *            the new checks if is prendario
     */
    public void setIsPrendario(boolean isPrendario) {
        this.isPrendario = isPrendario;
    }

	/**
	 * Checks if is prestamo sueldo.
	 *
	 * @return true, if is prestamo sueldo
	 */
	public boolean getIsPrestamoSueldo() {
		return isPrestamoSueldo;
	}

	/**
	 * Sets the prestamo sueldo.
	 *
	 * @param isPrestamoSueldo the new prestamo sueldo
	 */
	public void setPrestamoSueldo(boolean isPrestamoSueldo) {
		this.isPrestamoSueldo = isPrestamoSueldo;
	}

	public boolean getIsStopDebitPrestamo() {
		return isStopDebitPrestamo;
	}

	public void setStopDebitPrestamo(boolean isStopDebitPrestamo) {
		this.isStopDebitPrestamo = isStopDebitPrestamo;
	}

	/**
	 * Identifica si prestamo preaprobado monoproducto.
	 *
	 * @return
	 */
	public boolean isPreaprobadoMonoproducto() {
		return isPreaprobadoMonoproducto;
	}

	/**
	 * Setea prestamo preaprobado monoproducto.
	 *
	 * @return
	 */
	public void setPreaprobadoMonoproducto(boolean isPreaprobadoMonoproducto) {
		this.isPreaprobadoMonoproducto = isPreaprobadoMonoproducto;
	}
	
	

}

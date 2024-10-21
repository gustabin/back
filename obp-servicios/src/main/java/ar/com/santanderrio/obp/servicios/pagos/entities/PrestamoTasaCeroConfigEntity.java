/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

// TODO: Auto-generated Javadoc
/**
 * The Class PrestamoTasaCeroConfigEntity.
 */
public class PrestamoTasaCeroConfigEntity {
	
	/** The habilitado prestamo. */
	private Boolean habilitadoPrestamo;
	
	/** The tarjeta. */
	private String tarjeta;
	
	/** The email. */
	private String email;
	
	/** The cuit solicitante. */
	private String cuit_solicitante;
	
	/** The importe solicitado. */
	private String importe_solicitado;
	
    /** The tipo. */
    private String tipo;

	/**
	 * Gets the habilitado prestamo.
	 *
	 * @return the habilitado prestamo
	 */
	public Boolean getHabilitadoPrestamo() {
		return habilitadoPrestamo;
	}

	/**
	 * Sets the habilitado prestamo.
	 *
	 * @param habilitadoPrestamo the new habilitado prestamo
	 */
	public void setHabilitadoPrestamo(Boolean habilitadoPrestamo) {
		this.habilitadoPrestamo = habilitadoPrestamo;
	}

	/**
	 * Gets the tarjeta.
	 *
	 * @return the tarjeta
	 */
	public String getTarjeta() {
		return tarjeta;
	}

	/**
	 * Sets the tarjeta.
	 *
	 * @param tarjeta the new tarjeta
	 */
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the cuit solicitante.
	 *
	 * @return the cuit solicitante
	 */
	public String getCuit_solicitante() {
		return cuit_solicitante;
	}

	/**
	 * Sets the cuit solicitante.
	 *
	 * @param cuit_solicitante the new cuit solicitante
	 */
	public void setCuit_solicitante(String cuit_solicitante) {
		this.cuit_solicitante = cuit_solicitante;
	}

	/**
	 * Gets the importe solicitado.
	 *
	 * @return the importe solicitado
	 */
	public String getImporte_solicitado() {
		return importe_solicitado;
	}

	/**
	 * Sets the importe solicitado.
	 *
	 * @param importe_solicitado the new importe solicitado
	 */
	public void setImporte_solicitado(String importe_solicitado) {
		this.importe_solicitado = importe_solicitado;
	}

    @Override
    public String toString() {
        return "PrestamoTasaCeroConfigEntity [habilitadoPrestamo=" + habilitadoPrestamo + ", tarjeta=" + tarjeta
                + ", email=" + email + ", cuit_solicitante=" + cuit_solicitante + ", importe_solicitado="
                + importe_solicitado + ", tipo=" + tipo + "]";
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
	
}

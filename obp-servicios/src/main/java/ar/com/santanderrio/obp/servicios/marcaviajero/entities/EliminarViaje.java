/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.entities;

/**
 * The Class EliminarViaje.
 */
public class EliminarViaje {

	/** The tipo documento. */
	private String tipoDocumento;
	
	/** The numero documento. */
	private String numeroDocumento;
	
	/** The sexo. */
	private String sexo;
	
	/** The email socio. */
	private String emailSocio;
	
	/** The identificador viaje. */
	private long identificadorViaje;

    /**
     * Instantiates a new clase albert.
     */
    public EliminarViaje() {
        super();
    }

    /**
     * Instantiates a new eliminar viaje.
     *
     * @param tipoDocumento the tipo documento
     * @param numeroDocumento the numero documento
     * @param sexo the sexo
     * @param emailSocio the email socio
     * @param identificadorViaje the identificador viaje
     */
    public EliminarViaje(String tipoDocumento, String numeroDocumento,
            String sexo, String emailSocio, long identificadorViaje) {
        super();
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.sexo = sexo;
        this.emailSocio = emailSocio;
        this.identificadorViaje = identificadorViaje;
    }

    /**
     * Gets the tipo documento.
     *
     * @return the tipo documento
     */
    public String getTipoDocumento() {
		return tipoDocumento; 
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento the new tipo documento
	 */
	public void setTipoDocumento(String tipoDocumento) { 
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the numero documento.
	 *
	 * @return the numero documento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * Sets the numero documento.
	 *
	 * @param numeroDocumento the new numero documento
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Gets the sexo.
	 *
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Sets the sexo.
	 *
	 * @param sexo the new sexo
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Gets the email socio.
	 *
	 * @return the email socio
	 */
	public String getEmailSocio() {
		return emailSocio;
	}

	/**
	 * Sets the email socio.
	 *
	 * @param emailSocio the new email socio
	 */
	public void setEmailSocio(String emailSocio) {
		this.emailSocio = emailSocio;
	}

	/**
	 * Gets the identificador viaje.
	 *
	 * @return the identificador viaje
	 */
	public long getIdentificadorViaje() {
		return identificadorViaje;
	}

	/**
	 * Sets the identificador viaje.
	 *
	 * @param identificadorViaje the new identificador viaje
	 */
	public void setIdentificadorViaje(long identificadorViaje) {
		this.identificadorViaje = identificadorViaje;
	}

}

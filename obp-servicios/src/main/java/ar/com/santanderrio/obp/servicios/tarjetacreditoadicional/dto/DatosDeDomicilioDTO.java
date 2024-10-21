/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomicilioView;

/**
 * The Class DatosDeDomicilioDTO.
 */
public class DatosDeDomicilioDTO {

	/** The nup. */
	private String nup;

	/** The tipo de domicilio. */
	private String tipoDeDomicilio;

	/** The secuencia de domicilio. */
	private String secuenciaDeDomicilio;

	/** The nombre calle. */
	private String nombreCalle;

	/** The numero bloque. */
	private String numeroBloque;

	/** The codigo postal. */
	private String codigoPostal;

	/** The provincia. */
	private String provincia;

	/** The localidad. */
	private String localidad;

	/** The barrio. */
	private String barrio;

	/** The piso. */
	private String piso;

	/** The departamento. */
	private String departamento;

	/** The timestamp. */
	private String timestamp;

	/** The puerta. */
	private String puerta;
	
	/** The es sucursal. */
    private Boolean esSucursal;
    
    /** The nro sucursal. */
    private String nroSucursal;
    
    /** The Constant S. */
    private static final String S = "S";
    
    /** The Constant N. */
    private static final String N = "N";
    
	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the tipo de domicilio.
	 *
	 * @return the tipoDeDomicilio
	 */
	public String getTipoDeDomicilio() {
		return tipoDeDomicilio;
	}

	/**
	 * Sets the tipo de domicilio.
	 *
	 * @param tipoDeDomicilio
	 *            the tipoDeDomicilio to set
	 */
	public void setTipoDeDomicilio(String tipoDeDomicilio) {
		this.tipoDeDomicilio = tipoDeDomicilio;
	}

	/**
	 * Gets the secuencia de domicilio.
	 *
	 * @return the secuenciaDeDomicilio
	 */
	public String getSecuenciaDeDomicilio() {
		return secuenciaDeDomicilio;
	}

	/**
	 * Sets the secuencia de domicilio.
	 *
	 * @param secuenciaDeDomicilio
	 *            the secuenciaDeDomicilio to set
	 */
	public void setSecuenciaDeDomicilio(String secuenciaDeDomicilio) {
		this.secuenciaDeDomicilio = secuenciaDeDomicilio;
	}

	/**
	 * Gets the nombre calle.
	 *
	 * @return the nombreCalle
	 */
	public String getNombreCalle() {
		return nombreCalle;
	}

	/**
	 * Sets the nombre calle.
	 *
	 * @param nombreCalle
	 *            the nombreCalle to set
	 */
	public void setNombreCalle(String nombreCalle) {
		this.nombreCalle = nombreCalle;
	}

	/**
	 * Gets the numero bloque.
	 *
	 * @return the numeroBloque
	 */
	public String getNumeroBloque() {
		return numeroBloque;
	}

	/**
	 * Sets the numero bloque.
	 *
	 * @param numeroBloque
	 *            the numeroBloque to set
	 */
	public void setNumeroBloque(String numeroBloque) {
		this.numeroBloque = numeroBloque;
	}

	/**
	 * Gets the codigo postal.
	 *
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Sets the codigo postal.
	 *
	 * @param codigoPostal
	 *            the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * Gets the provincia.
	 *
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * Sets the provincia.
	 *
	 * @param provincia
	 *            the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * Gets the localidad.
	 *
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * Sets the localidad.
	 *
	 * @param localidad
	 *            the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * Gets the barrio.
	 *
	 * @return the barrio
	 */
	public String getBarrio() {
		return barrio;
	}

	/**
	 * Sets the barrio.
	 *
	 * @param barrio
	 *            the barrio to set
	 */
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	/**
	 * Gets the piso.
	 *
	 * @return the piso
	 */
	public String getPiso() {
		return piso;
	}

	/**
	 * Sets the piso.
	 *
	 * @param piso
	 *            the piso to set
	 */
	public void setPiso(String piso) {
		this.piso = piso;
	}

	/**
	 * Gets the departamento.
	 *
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * Sets the departamento.
	 *
	 * @param departamento
	 *            the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * Gets the puerta.
	 *
	 * @return the puerta
	 */
	public String getPuerta() {
		return puerta;
	}

	/**
	 * Sets the puerta.
	 *
	 * @param puerta
	 *            the puerta to set
	 */
	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}
	
	/**
     * Es sucursal.
     *
     * @return the es sucursal
     */
    public Boolean esSucursal() {
        return esSucursal;
    }

    /**
     * Sets the es sucursal.
     *
     * @param esSucursal
     *            the new es sucursal
     */
    public void setEsSucursal(Boolean esSucursal) {
        this.esSucursal = esSucursal;
    }
    
    /**
	 * Gets the nro sucursal.
	 *
	 * @return the nro sucursal
	 */
	public String getNroSucursal() {
		return nroSucursal;
	}

	/**
	 * Sets the nro sucursal.
	 *
	 * @param nroSucursal
	 *            the nro sucursal to set
	 */
	public void setNroSucursal(String nroSucursal) {
		this.nroSucursal = nroSucursal;
	}
	
	public DatosDeDomicilioDTO() {}
	
	public DatosDeDomicilioDTO(DomicilioView domicilio, String nroSucursal) {
		if (domicilio.esSucursal()) {
			this.setTipoDeDomicilio(S);
			this.setNroSucursal(nroSucursal);
		} else {
			this.setTipoDeDomicilio(N);
			this.setNroSucursal(" ");
		}
		
		if (!StringUtils.isBlank(domicilio.getCalle())) {
			this.setNombreCalle(domicilio.getCalle());
		} else {
			this.setNombreCalle(" ");
		}
		
		if (!StringUtils.isBlank(domicilio.getApt())) {
			this.setPuerta(domicilio.getApt());
		} else {
			this.setPuerta(" ");
		}
		
		if (!StringUtils.isBlank(domicilio.getPiso())) {
			this.setPiso(domicilio.getPiso());
		} else {
			this.setPiso(" ");
		}
		
		if (!StringUtils.isBlank(domicilio.getDepartamento())) {
			this.setDepartamento(domicilio.getDepartamento());
		} else {
			this.setDepartamento(" ");
		}
		
		if (!StringUtils.isBlank(domicilio.getLocalidad())) {
			this.setLocalidad(domicilio.getLocalidad());
		} else {
			this.setLocalidad(" ");
		}
		
		if (!StringUtils.isBlank(domicilio.getCodigoPostal())) {
			this.setCodigoPostal(domicilio.getCodigoPostal());
		} else {
			this.setCodigoPostal(" ");
		}
		
		if (!StringUtils.isBlank(domicilio.getDescProvincia())) {
			this.setProvincia(domicilio.getDescProvincia());
		} else {
			this.setProvincia(" ");
		}
	}
	
}

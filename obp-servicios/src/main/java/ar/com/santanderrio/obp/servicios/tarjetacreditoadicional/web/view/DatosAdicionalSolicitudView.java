/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view;

import java.util.List;

/**
 * The Class DatosAdicionalSolicitudView.
 */
public class DatosAdicionalSolicitudView {
    
    /** The tarjetas. */
    private List<DatosTarjetaAdicionalView> tarjetas;
    
    /** The nombre apellido adicional. */
    private String nombreApellidoAdicional;
    
    /** The tipo doc adicional. */
    private String tipoDocAdicional;
    
    /** The nro doc adicional. */
    private String nroDocAdicional;
    
    /** The fecha nac adicional. */
    private String fechaNacAdicional;

    /**
	 * Gets the nombre apellido adicional.
	 *
	 * @return the nombre apellido adicional
	 */
    public String getNombreApellidoAdicional() {
        return nombreApellidoAdicional;
    }

    /**
	 * Sets the nombre apellido adicional.
	 *
	 * @param nombreApellidoAdicional
	 *            the new nombre apellido adicional
	 */
    public void setNombreApellidoAdicional(String nombreApellidoAdicional) {
        this.nombreApellidoAdicional = nombreApellidoAdicional;
    }

    /**
	 * Gets the tipo doc adicional.
	 *
	 * @return the tipo doc adicional
	 */
    public String getTipoDocAdicional() {
        return tipoDocAdicional;
    }

    /**
	 * Sets the tipo doc adicional.
	 *
	 * @param tipoDocAdicional
	 *            the new tipo doc adicional
	 */
    public void setTipoDocAdicional(String tipoDocAdicional) {
        this.tipoDocAdicional = tipoDocAdicional;
    }

    /**
	 * Gets the nro doc adicional.
	 *
	 * @return the nro doc adicional
	 */
    public String getNroDocAdicional() {
        return nroDocAdicional;
    }

    /**
	 * Sets the nro doc adicional.
	 *
	 * @param nroDocAdicional
	 *            the new nro doc adicional
	 */
    public void setNroDocAdicional(String nroDocAdicional) {
        this.nroDocAdicional = nroDocAdicional;
    }

    /**
	 * Gets the fecha nac adicional.
	 *
	 * @return the fecha nac adicional
	 */
    public String getFechaNacAdicional() {
        return fechaNacAdicional;
    }

    /**
	 * Sets the fecha nac adicional.
	 *
	 * @param fechaNacAdicional
	 *            the new fecha nac adicional
	 */
    public void setFechaNacAdicional(String fechaNacAdicional) {
        this.fechaNacAdicional = fechaNacAdicional;
    }

    /**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
    public List<DatosTarjetaAdicionalView> getTarjetas() {
        return tarjetas;
    }

    /**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the new tarjetas
	 */
    public void setTarjetas(List<DatosTarjetaAdicionalView> tarjetas) {
        this.tarjetas = tarjetas;
    }

}

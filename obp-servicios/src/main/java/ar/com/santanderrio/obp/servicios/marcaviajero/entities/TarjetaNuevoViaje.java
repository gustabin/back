/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.entities;

import java.util.List;

/**
 * The Class Tarjeta.
 */
public class TarjetaNuevoViaje {

    /** The nombre. */
    private String nombre;

    /** The is titular. */
    private Boolean isTitular;

    /** The tarjetas asociadas. */
    private List<TarjetaAsociada> tarjetasAsociadas;

    /** The nombre Ws. */
    private String nombreWs;
    
    /**
     * Instantiates a new tarjeta nuevo viaje.
     */
    public TarjetaNuevoViaje() {
        super();
    }

    /**
     * Instantiates a new tarjeta nuevo viaje.
     *
     * @param nombre the nombre
     * @param isTitular the is titular
     */
    public TarjetaNuevoViaje(String nombre, Boolean isTitular) {
        super();
        this.nombre = nombre;
        this.isTitular = isTitular;
    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre            the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the checks if is titular.
     *
     * @return the isTitular
     */
    public Boolean getIsTitular() {
        return isTitular;
    }

    /**
     * Sets the checks if is titular.
     *
     * @param isTitular            the isTitular to set
     */
    public void setIsTitular(Boolean isTitular) {
        this.isTitular = isTitular;
    }

    /**
     * Gets the tarjetas asociadas.
     *
     * @return the tarjetasAsociadas
     */
    public List<TarjetaAsociada> getTarjetasAsociadas() {
        return tarjetasAsociadas;
    }

    /**
     * Sets the tarjetas asociadas.
     *
     * @param tarjetasAsociadas            the tarjetasAsociadas to set
     */
    public void setTarjetasAsociadas(List<TarjetaAsociada> tarjetasAsociadas) {
        this.tarjetasAsociadas = tarjetasAsociadas;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TarjetaNuevoViaje other = (TarjetaNuevoViaje) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

    /**
	 * Gets the nombre ws.
	 *
	 * @return the nombreWs
	 */
    public String getNombreWs() {
        return nombreWs;
    }

    /**
	 * Sets the nombre ws.
	 *
	 * @param nombreWs
	 *            the nombreWs to set
	 */
    public void setNombreWs(String nombreWs) {
        this.nombreWs = nombreWs;
    }

    
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.entities;

import java.util.Date;
import java.util.List;

/**
 * The Class NuevoViaje.
 */
public class NuevoViaje {
    
    /** The fecha desde. */
    private Date fechaDesde;
    
    /** The fecha hasta. */
    private Date fechaHasta;
    
    /** The fecha max inicio viaje. */
    private Date fechaMaxInicioViaje;

    /** The duracion maxima viaje. */
    private Integer duracionMaximaViaje;
    
    /** The email. */
    private String email;

    /** The tarjeta black nacional. */
    private TarjetaBlackNacional tarjetaBlackNacional; 
    
    /** The destinos. */
    private List<Destino> destinos;
    
    /** The tarjetas. */
    private List<TarjetaNuevoViaje> tarjetas;

    /**
	 * Gets the fecha desde.
	 *
	 * @return the fechaDesde
	 */
    public Date getFechaDesde() {
        return fechaDesde;
    }

    /**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the fechaDesde to set
	 */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
	 * Gets the fecha hasta.
	 *
	 * @return the fechaHasta
	 */
    public Date getFechaHasta() {
        return fechaHasta;
    }

    /**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the fechaHasta to set
	 */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * Gets the fecha max inicio viaje.
     *
     * @return the fechaMaxInicioViaje
     */
    public Date getFechaMaxInicioViaje() {
        return fechaMaxInicioViaje;
    }

    /**
     * Sets the fecha max inicio viaje.
     *
     * @param fechaMaxInicioViaje the fechaMaxInicioViaje to set
     */
    public void setFechaMaxInicioViaje(Date fechaMaxInicioViaje) {
        this.fechaMaxInicioViaje = fechaMaxInicioViaje;
    }

    /**
     * Gets the duracion maxima viaje.
     *
     * @return the duracionMaximaViaje
     */
    public Integer getDuracionMaximaViaje() {
        return duracionMaximaViaje;
    }

    /**
     * Sets the duracion maxima viaje.
     *
     * @param duracionMaximaViaje the duracionMaximaViaje to set
     */
    public void setDuracionMaximaViaje(Integer duracionMaximaViaje) {
        this.duracionMaximaViaje = duracionMaximaViaje;
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
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
	 * Gets the tarjeta black nacional.
	 *
	 * @return the tarjetaBlackNacional
	 */
    public TarjetaBlackNacional getTarjetaBlackNacional() {
        return tarjetaBlackNacional;
    }

    /**
	 * Sets the tarjeta black nacional.
	 *
	 * @param tarjetaBlackNacional
	 *            the tarjetaBlackNacional to set
	 */
    public void setTarjetaBlackNacional(TarjetaBlackNacional tarjetaBlackNacional) {
        this.tarjetaBlackNacional = tarjetaBlackNacional;
    }

    /**
     * Gets the destinos.
     *
     * @return the destinos
     */
    public List<Destino> getDestinos() {
        return destinos;
    }

    /**
     * Sets the destinos.
     *
     * @param destinos the destinos to set
     */
    public void setDestinos(List<Destino> destinos) {
        this.destinos = destinos;
    }

    /**
     * Gets the tarjetas.
     *
     * @return the tarjetas
     */
    public List<TarjetaNuevoViaje> getTarjetas() {
        return tarjetas;
    }

    /**
     * Sets the tarjetas.
     *
     * @param tarjetas the tarjetas to set
     */
    public void setTarjetas(List<TarjetaNuevoViaje> tarjetas) {
        this.tarjetas = tarjetas;
    }
    
}

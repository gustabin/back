/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.web.view;

import java.util.Date;
import java.util.List;

/**
 * The Class NuevoViajeView.
 */
public class NuevoViajeView {
    
    /** The fecha desde. */
    private Date fechaDesde;
    
    /** The fecha hasta. */
    private Date fechaHasta;
    
    /** The fecha max inicio viaje. */
    private String fechaMaxInicioViaje;
    
    /** The duracion maxima viaje. */
    private Integer duracionMaximaViaje;
    
    /** The email. */
    private String email;

    /** The mensaje nacional. */
    private String mensajeNacional;
    
    /** The mensaje black. */
    private String mensajeBlack;
    
    /** The destinos. */
    private List<DestinoView> destinos;
    
    /** The tarjetas. */
    private List<TarjetaNuevoViajeView> tarjetas;

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
    public String getFechaMaxInicioViaje() {
        return fechaMaxInicioViaje;
    }

    /**
	 * Sets the fecha max inicio viaje.
	 *
	 * @param fechaMaxInicioViaje
	 *            the fechaMaxInicioViaje to set
	 */
    public void setFechaMaxInicioViaje(String fechaMaxInicioViaje) {
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
	 * @param duracionMaximaViaje
	 *            the duracionMaximaViaje to set
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
	 * @param email
	 *            the email to set
	 */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
	 * Gets the mensaje nacional.
	 *
	 * @return the mensajeNacional
	 */
    public String getMensajeNacional() {
        return mensajeNacional;
    }

    /**
	 * Sets the mensaje nacional.
	 *
	 * @param mensajeNacional
	 *            the mensajeNacional to set
	 */
    public void setMensajeNacional(String mensajeNacional) {
        this.mensajeNacional = mensajeNacional;
    }

    /**
	 * Gets the mensaje black.
	 *
	 * @return the mensajeBlack
	 */
    public String getMensajeBlack() {
        return mensajeBlack;
    }

    /**
	 * Sets the mensaje black.
	 *
	 * @param mensajeBlack
	 *            the mensajeBlack to set
	 */
    public void setMensajeBlack(String mensajeBlack) {
        this.mensajeBlack = mensajeBlack;
    }

    /**
	 * Gets the destinos.
	 *
	 * @return the destinos
	 */
    public List<DestinoView> getDestinos() {
        return destinos;
    }

    /**
	 * Sets the destinos.
	 *
	 * @param destinos
	 *            the destinos to set
	 */
    public void setDestinos(List<DestinoView> destinos) {
        this.destinos = destinos;
    }

    /**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
    public List<TarjetaNuevoViajeView> getTarjetas() {
        return tarjetas;
    }

    /**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the tarjetas to set
	 */
    public void setTarjetas(List<TarjetaNuevoViajeView> tarjetas) {
        this.tarjetas = tarjetas;
    }

}

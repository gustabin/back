/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.entities;

import java.util.Date;
import java.util.List;

/**
 * The Class ViajeHabilitado.
 */
public class ViajeHabilitado {
    
    /** The fecha viaje. */
    private Date fechaViaje;
    
    /** The cantidad dias. */
    private String cantidadDias;
    
    /** The cantidad destinos. */
    private String cantidadDestinos;
    
    /** The cantidad tarjetas. */
    private String cantidadTarjetas;
    
    /** The fecha desde. */
    private Date fechaDesde;
    
    /** The fecha hasta. */
    private Date fechaHasta;
    
    /** The permite eliminar. */
    private Boolean permiteEliminar;
    
    /** The permite modificar. */
    private Boolean permiteModificar;
    
    /** The mail. */
    private String mail;

    /** The identificador viaje. */
    private Long identificadorViaje;
    
    /** The destinos. */
    private List<Destino> destinos;
    
    /** The tarjetas. */
    private List<Tarjeta> tarjetas;

    /** isReintento. */
    private Boolean isReintento;
    
    /**
     * Gets the fecha viaje.
     *
     * @return the fechaViaje
     */
    public Date getFechaViaje() {
        return fechaViaje;
    }
    
    /**
     * Sets the fecha viaje.
     *
     * @param fechaViaje the fechaViaje to set
     */
    public void setFechaViaje(Date fechaViaje) {
        this.fechaViaje = fechaViaje;
    }
    
    /**
     * Gets the cantidad dias.
     *
     * @return the cantidadDias
     */
    public String getCantidadDias() {
        return cantidadDias;
    }
    
    /**
     * Sets the cantidad dias.
     *
     * @param cantidadDias the cantidadDias to set
     */
    public void setCantidadDias(String cantidadDias) {
        this.cantidadDias = cantidadDias;
    }
    
    /**
     * Gets the cantidad destinos.
     *
     * @return the cantidadDestinos
     */
    public String getCantidadDestinos() {
        return cantidadDestinos;
    }
    
    /**
     * Sets the cantidad destinos.
     *
     * @param cantidadDestinos the cantidadDestinos to set
     */
    public void setCantidadDestinos(String cantidadDestinos) {
        this.cantidadDestinos = cantidadDestinos;
    }
    
    /**
     * Gets the cantidad tarjetas.
     *
     * @return the cantidadTarjetas
     */
    public String getCantidadTarjetas() {
        return cantidadTarjetas;
    }
    
    /**
     * Sets the cantidad tarjetas.
     *
     * @param cantidadTarjetas the cantidadTarjetas to set
     */
    public void setCantidadTarjetas(String cantidadTarjetas) {
        this.cantidadTarjetas = cantidadTarjetas;
    }
    
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
     * @param fechaDesde the fechaDesde to set
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
     * @param fechaHasta the fechaHasta to set
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
    
    /**
     * Gets the mail.
     *
     * @return the mail
     */
    public String getMail() {
        return mail;
    }
    
    /**
     * Sets the mail.
     *
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
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
    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }
    
    /**
     * Sets the tarjetas.
     *
     * @param tarjetas the tarjetas to set
     */
    public void setTarjetas(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }
    
    /**
     * Gets the permite eliminar.
     *
     * @return the permiteEliminar
     */
    public Boolean getPermiteEliminar() {
        return permiteEliminar;
    }
    
    /**
     * Sets the permite eliminar.
     *
     * @param permiteEliminar the permiteEliminar to set
     */
    public void setPermiteEliminar(Boolean permiteEliminar) {
        this.permiteEliminar = permiteEliminar;
    }
    
    /**
     * Gets the permite modificar.
     *
     * @return the permiteModificar
     */
    public Boolean getPermiteModificar() {
        return permiteModificar;
    }
    
    /**
     * Sets the permite modificar.
     *
     * @param permiteModificar the permiteModificar to set
     */
    public void setPermiteModificar(Boolean permiteModificar) {
        this.permiteModificar = permiteModificar;
    }
    
    /**
     * Gets the identificador viaje.
     *
     * @return the identificador viaje
     */
    public Long getIdentificadorViaje() {
        return identificadorViaje;
    }
    
    /**
     * Sets the identificador viaje.
     *
     * @param identificadorViaje the new identificador viaje
     */
    public void setIdentificadorViaje(Long identificadorViaje) {
        this.identificadorViaje = identificadorViaje;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ViajeHabilitado [fechaViaje=" + fechaViaje + ", cantidadDias=" + cantidadDias + ", cantidadDestinos="
                + cantidadDestinos + ", cantidadTarjetas=" + cantidadTarjetas + ", fechaDesde=" + fechaDesde
                + ", fechaHasta=" + fechaHasta + ", permiteEliminar=" + permiteEliminar + ", permiteModificar="
                + permiteModificar + ", mail=" + mail + ", destinos=" + destinos + ", identificadorViaje=" + identificadorViaje
                + ", tarjetas=" + tarjetas + "]";
    }

    /**
	 * Gets the checks if is reintento.
	 *
	 * @return the isReintento
	 */
    public Boolean getIsReintento() {
        return isReintento;
    }

    /**
	 * Sets the checks if is reintento.
	 *
	 * @param isReintento
	 *            the isReintento to set
	 */
    public void setIsReintento(Boolean isReintento) {
        this.isReintento = isReintento;
    }
    
}
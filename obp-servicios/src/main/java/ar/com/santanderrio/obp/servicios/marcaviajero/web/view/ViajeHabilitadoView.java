/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.web.view;

import java.util.List;

/**
 * The Class ViajeHabilitadoView.
 */
public class ViajeHabilitadoView {
    
    /** The fecha viaje. */
    private String fechaViaje;
    
    /** The cantidad dias. */
    private String cantidadDias;
    
    /** The cantidad destinos. */
    private String cantidadDestinos;
    
    /** The cantidad tarjetas. */
    private String cantidadTarjetas;
    
    /** The fecha desde. */
    private String fechaDesde;
     
    /** The fecha hasta. */
    private String fechaHasta;  
    
    /** The permite eliminar. */
    private Boolean permiteEliminar;
    
    /** The permite modificar. */
    private Boolean permiteModificar;

    /** The identificador viaje. */
    private String identificadorViaje;
    
    /** The mail. */
    private String mail;
    
    /** The destinos. */
    private List<DestinoView> destinos;
    
    /** The tarjetas. */
    private List<TarjetaView> tarjetas;
    
    /**
     * Gets the fecha viaje.
     *
     * @return the fechaViaje
     */
    public String getFechaViaje() {
        return fechaViaje;
    }
    
    /**
     * Sets the fecha viaje.
     *
     * @param fechaViaje the fechaViaje to set
     */
    public void setFechaViaje(String fechaViaje) {
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
    public String getFechaDesde() {
        return fechaDesde;
    }
    
    /**
     * Sets the fecha desde.
     *
     * @param fechaDesde the fechaDesde to set
     */
    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }
    
    /**
     * Gets the fecha hasta.
     *
     * @return the fechaHasta
     */
    public String getFechaHasta() {
        return fechaHasta;
    }
    
    /**
     * Sets the fecha hasta.
     *
     * @param fechaHasta the fechaHasta to set
     */
    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
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
	 * @param permiteEliminar
	 *            the permiteEliminar to set
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
	 * @param permiteModificar
	 *            the permiteModificar to set
	 */
    public void setPermiteModificar(Boolean permiteModificar) {
        this.permiteModificar = permiteModificar;
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
    public List<DestinoView> getDestinos() {
        return destinos;
    }
    
    /**
     * Sets the destinos.
     *
     * @param destinos the destinos to set
     */
    public void setDestinos(List<DestinoView> destinos) {
        this.destinos = destinos;
    }
    
    /**
     * Gets the tarjetas.
     *
     * @return the tarjetas
     */
    public List<TarjetaView> getTarjetas() {
        return tarjetas;
    }
    
    /**
     * Sets the tarjetas.
     *
     * @param tarjetas the tarjetas to set
     */
    public void setTarjetas(List<TarjetaView> tarjetas) {
        this.tarjetas = tarjetas;
    }
    
    /**
	 * Gets the identificador viaje.
	 *
	 * @return the identificadorViaje
	 */
    public String getIdentificadorViaje() {
        return identificadorViaje;
    }

    /**
	 * Sets the identificador viaje.
	 *
	 * @param identificadorViaje
	 *            the identificadorViaje to set
	 */
    public void setIdentificadorViaje(String identificadorViaje) {
        this.identificadorViaje = identificadorViaje;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ViajeHabilitado [fechaViaje=" + fechaViaje + ", cantidadDias="
                + cantidadDias + ", cantidadDestinos=" + cantidadDestinos
                + ", cantidadTarjetas=" + cantidadTarjetas + ", fechaDesde="
                + fechaDesde + ", fechaHasta=" + fechaHasta
                + ", permiteEliminar=" + permiteEliminar + ", permiteModificar="
                + permiteModificar + ", mail=" + mail + ", destinos=" + destinos
                + ", identificadorViaje=" + identificadorViaje + ", tarjetas=" + tarjetas + "]";
    }
    
    
}
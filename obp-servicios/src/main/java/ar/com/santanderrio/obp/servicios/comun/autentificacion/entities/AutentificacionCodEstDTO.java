package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

/**
 * Registrar los codigos de las estadisticas.<br/>
 * Dejar de cargarlas en el autentificacionDTO y que autentificacionDTO tenga un
 * atributo de este tipo de modo que utilicen estos datos en lugar de los
 * cargados en el AutentificacionManagerImpl ya que es un singleton.
 * 
 * @author sergio.e.goldentair
 *
 */
public class AutentificacionCodEstDTO {

    /** The codigo estadistica solicitud token. */
    private String codigoEstadisticaSolicitudToken;

    /** The codigo estadistica validacion token. */
    private String codigoEstadisticaValidacionToken;

    /** The codigo estadistica solicitud coordenadas. */
    private String codigoEstadisticaSolicitudCoordenadas;

    /** The codigo estadistica validacion coordenadas. */
    private String codigoEstadisticaValidacionCoordenadas;

    /** The codigo estadistica solicitud banelco. */
    private String codigoEstadisticaSolicitudBanelco;

    /** The codigo estadistica validacion banelco. */
    private String codigoEstadisticaValidacionBanelco;

    /** The codigo estadistica sin desafios. */
    private String codigoEstadisticaSinDesafios = null;

    /**
     * @return the codigoEstadisticaSolicitudToken
     */
    public String getCodigoEstadisticaSolicitudToken() {
        return codigoEstadisticaSolicitudToken;
    }

    /**
     * @param codigoEstadisticaSolicitudToken
     *            the codigoEstadisticaSolicitudToken to set
     */
    public void setCodigoEstadisticaSolicitudToken(String codigoEstadisticaSolicitudToken) {
        this.codigoEstadisticaSolicitudToken = codigoEstadisticaSolicitudToken;
    }

    /**
     * @return the codigoEstadisticaValidacionToken
     */
    public String getCodigoEstadisticaValidacionToken() {
        return codigoEstadisticaValidacionToken;
    }

    /**
     * @param codigoEstadisticaValidacionToken
     *            the codigoEstadisticaValidacionToken to set
     */
    public void setCodigoEstadisticaValidacionToken(String codigoEstadisticaValidacionToken) {
        this.codigoEstadisticaValidacionToken = codigoEstadisticaValidacionToken;
    }

    /**
     * @return the codigoEstadisticaSolicitudCoordenadas
     */
    public String getCodigoEstadisticaSolicitudCoordenadas() {
        return codigoEstadisticaSolicitudCoordenadas;
    }

    /**
     * @param codigoEstadisticaSolicitudCoordenadas
     *            the codigoEstadisticaSolicitudCoordenadas to set
     */
    public void setCodigoEstadisticaSolicitudCoordenadas(String codigoEstadisticaSolicitudCoordenadas) {
        this.codigoEstadisticaSolicitudCoordenadas = codigoEstadisticaSolicitudCoordenadas;
    }

    /**
     * @return the codigoEstadisticaValidacionCoordenadas
     */
    public String getCodigoEstadisticaValidacionCoordenadas() {
        return codigoEstadisticaValidacionCoordenadas;
    }

    /**
     * @param codigoEstadisticaValidacionCoordenadas
     *            the codigoEstadisticaValidacionCoordenadas to set
     */
    public void setCodigoEstadisticaValidacionCoordenadas(String codigoEstadisticaValidacionCoordenadas) {
        this.codigoEstadisticaValidacionCoordenadas = codigoEstadisticaValidacionCoordenadas;
    }

    /**
     * @return the codigoEstadisticaSolicitudBanelco
     */
    public String getCodigoEstadisticaSolicitudBanelco() {
        return codigoEstadisticaSolicitudBanelco;
    }

    /**
     * @param codigoEstadisticaSolicitudBanelco
     *            the codigoEstadisticaSolicitudBanelco to set
     */
    public void setCodigoEstadisticaSolicitudBanelco(String codigoEstadisticaSolicitudBanelco) {
        this.codigoEstadisticaSolicitudBanelco = codigoEstadisticaSolicitudBanelco;
    }

    /**
     * @return the codigoEstadisticaValidacionBanelco
     */
    public String getCodigoEstadisticaValidacionBanelco() {
        return codigoEstadisticaValidacionBanelco;
    }

    /**
     * @param codigoEstadisticaValidacionBanelco
     *            the codigoEstadisticaValidacionBanelco to set
     */
    public void setCodigoEstadisticaValidacionBanelco(String codigoEstadisticaValidacionBanelco) {
        this.codigoEstadisticaValidacionBanelco = codigoEstadisticaValidacionBanelco;
    }

    /**
     * @return the codigoEstadisticaSinDesafios
     */
    public String getCodigoEstadisticaSinDesafios() {
        return codigoEstadisticaSinDesafios;
    }

    /**
     * @param codigoEstadisticaSinDesafios
     *            the codigoEstadisticaSinDesafios to set
     */
    public void setCodigoEstadisticaSinDesafios(String codigoEstadisticaSinDesafios) {
        this.codigoEstadisticaSinDesafios = codigoEstadisticaSinDesafios;
    }

}

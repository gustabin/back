/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GotoLink;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.OfertaComercialEntity;

/**
 * The Class OfertaComercialDTO.
 */
public class OfertaComercialDTO {

    /** The imagen url. */
    private String imagenUrl;

    /** The titulo. */
    private String titulo;

    /** The descripcion. */
    private String descripcion;
    
    /** The descripcion seccion. */
    private String descripcionSeccion;

    /** The ubicacion seccion. */
    private String ubicacionSeccion;

    /** The indicador clic seccion. */
    private String indicadorClicSeccion;

    /** The cerrado. */
    private Boolean cerrado = false;

    /** The id rtd. */
    // para notificar interes
    private String idRtd;

    /** The id interno. */
    private String idInterno;

    /** The tipo oferta. */
    private String tipoOferta;

    /** The categoria oferta. */
    private String categoriaOferta;

    /** The grupo control. */
    private String grupoControl;

    /** The ubicacion carrusel. */
    private String ubicacionCarrusel;

    /** The orden prioridad. */
    // para tealium
    private String ordenPrioridad;

    /** The goto link. */
    private GotoLink gotoLink;
    
    /** The ubicacion logoff. */
    private String ubicacionLogoff;

    /** The variable 1 char. */
    private String variable1Char;
    
    /** The tipo oferta SC. */
    private String tipoOfertaSC;

    /** The tipo producto loyalty. */
    private String tipoProductoLoyalty;
    
    private String idLoyalty;
    
    private String urlReferidos;

    private OfertaComercialMicrofrontDto microfront;
    
    private List<String> feedbackAvailable;

    /**
     * Instantiates a new oferta comercial DTO.
     */
    public OfertaComercialDTO() {
        super();
    }

    /**
     * Instantiates a new oferta comercial DTO.
     *
     * @param ofertaComercial
     *            the oferta comercial
     */
    public OfertaComercialDTO(OfertaComercialEntity ofertaComercial) {
        this.imagenUrl = ofertaComercial.getUrl();
        this.titulo = ofertaComercial.getTitulo();
        this.descripcion = ofertaComercial.getDescripcion();
        this.descripcionSeccion = ofertaComercial.getDescripcionSeccion();

        this.ubicacionSeccion = ofertaComercial.getUbicacionSeccion();
        this.indicadorClicSeccion = ofertaComercial.getIndicadorClicSeccion();

        this.idRtd = ofertaComercial.getIdOfertaRtd();
        this.idInterno = ofertaComercial.getIdOfertaInterno();
        this.tipoOferta = ofertaComercial.getTipoOferta();
        this.categoriaOferta = ofertaComercial.getCategoriaOferta();
        this.grupoControl = ofertaComercial.getGrupoControl();
        this.ubicacionCarrusel = ofertaComercial.getUbicacionCarrusel();

        this.ordenPrioridad = ofertaComercial.getOrdenPrioridad();
        this.gotoLink = ofertaComercial.getGotoLink();
        this.ubicacionLogoff = ofertaComercial.getUbicacionLogoff();
        this.variable1Char = ofertaComercial.getVariable1Char();
        
        this.tipoOfertaSC = ofertaComercial.getTipoOfertaSc();
        this.idLoyalty = ofertaComercial.getIdLoyalty();
        this.tipoProductoLoyalty = ofertaComercial.getTipoProductoLoyalty();

        this.feedbackAvailable = ofertaComercial.getFeedbackAvailable();
    }

    /**
     * Pertenece A carrusel.
     *
     * @return true, if successful
     */
    public boolean perteneceACarrusel() {
        return "S".equals(this.ubicacionCarrusel);
    }

    /**
     * Clic en seccion.
     *
     * @return true, if successful
     */
    public boolean clicEnSeccion() {
        return "S".equals(this.indicadorClicSeccion);
    }

    /**
     * Gets the imagen url.
     *
     * @return the imagen url
     */
    public String getImagenUrl() {
        return imagenUrl;
    }

    /**
     * Sets the imagen url.
     *
     * @param imagenUrl
     *            the new imagen url
     */
    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    /**
     * Gets the titulo.
     *
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Sets the titulo.
     *
     * @param titulo
     *            the new titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the descripcion.
     *
     * @param descripcion
     *            the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the cerrado.
     *
     * @return the cerrado
     */
    public Boolean getCerrado() {
        return cerrado;
    }

    /**
     * Sets the cerrado.
     *
     * @param cerrado
     *            the new cerrado
     */
    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }

    /**
     * Gets the id rtd.
     *
     * @return the id rtd
     */
    public String getIdRtd() {
        return idRtd;
    }

    /**
     * Sets the id rtd.
     *
     * @param idRtd
     *            the new id rtd
     */
    public void setIdRtd(String idRtd) {
        this.idRtd = idRtd;
    }

    /**
     * Gets the id interno.
     *
     * @return the id interno
     */
    public String getIdInterno() {
        return idInterno;
    }

    /**
     * Sets the id interno.
     *
     * @param idInterno
     *            the new id interno
     */
    public void setIdInterno(String idInterno) {
        this.idInterno = idInterno;
    }

    /**
     * Gets the tipo oferta.
     *
     * @return the tipo oferta
     */
    public String getTipoOferta() {
        return tipoOferta;
    }

    /**
     * Sets the tipo oferta.
     *
     * @param tipoOferta
     *            the new tipo oferta
     */
    public void setTipoOferta(String tipoOferta) {
        this.tipoOferta = tipoOferta;
    }

    /**
     * Gets the categoria oferta.
     *
     * @return the categoria oferta
     */
    public String getCategoriaOferta() {
        return categoriaOferta;
    }

    /**
     * Sets the categoria oferta.
     *
     * @param categoriaOferta
     *            the new categoria oferta
     */
    public void setCategoriaOferta(String categoriaOferta) {
        this.categoriaOferta = categoriaOferta;
    }

    /**
     * Gets the grupo control.
     *
     * @return the grupo control
     */
    public String getGrupoControl() {
        return grupoControl;
    }

    /**
     * Sets the grupo control.
     *
     * @param grupoControl
     *            the new grupo control
     */
    public void setGrupoControl(String grupoControl) {
        this.grupoControl = grupoControl;
    }

    /**
     * Gets the ubicacion carrusel.
     *
     * @return the ubicacion carrusel
     */
    public String getUbicacionCarrusel() {
        return ubicacionCarrusel;
    }

    /**
     * Sets the ubicacion carrusel.
     *
     * @param ubicacionCarrusel
     *            the new ubicacion carrusel
     */
    public void setUbicacionCarrusel(String ubicacionCarrusel) {
        this.ubicacionCarrusel = ubicacionCarrusel;
    }

    /**
     * Gets the ubicacion seccion.
     *
     * @return the ubicacion seccion
     */
    public String getUbicacionSeccion() {
        return ubicacionSeccion;
    }

    /**
     * Sets the ubicacion seccion.
     *
     * @param ubicacionSeccion
     *            the new ubicacion seccion
     */
    public void setUbicacionSeccion(String ubicacionSeccion) {
        this.ubicacionSeccion = ubicacionSeccion;
    }

    /**
     * Gets the indicador clic seccion.
     *
     * @return the indicador clic seccion
     */
    public String getIndicadorClicSeccion() {
        return indicadorClicSeccion;
    }

    /**
     * Sets the indicador clic seccion.
     *
     * @param indicadorClicSeccion
     *            the new indicador clic seccion
     */
    public void setIndicadorClicSeccion(String indicadorClicSeccion) {
        this.indicadorClicSeccion = indicadorClicSeccion;
    }

    /**
     * Gets the orden prioridad.
     *
     * @return the orden prioridad
     */
    public String getOrdenPrioridad() {
        return ordenPrioridad;
    }

    /**
     * Sets the orden prioridad.
     *
     * @param ordenPrioridad
     *            the new orden prioridad
     */
    public void setOrdenPrioridad(String ordenPrioridad) {
        this.ordenPrioridad = ordenPrioridad;
    }

    /**
	 * Gets the goto link.
	 *
	 * @return the goto link
	 */
    public GotoLink getGotoLink() {
        return gotoLink;
    }

    /**
	 * Sets the goto link.
	 *
	 * @param gotoLink
	 *            the new goto link
	 */
    public void setGotoLink(GotoLink gotoLink) {
        this.gotoLink = gotoLink;
    }

    /**
	 * Gets the descripcion seccion.
	 *
	 * @return the descripcion seccion
	 */
    public String getDescripcionSeccion() {
        return descripcionSeccion;
    }

    /**
	 * Sets the descripcion seccion.
	 *
	 * @param descripcionSeccion
	 *            the new descripcion seccion
	 */
    public void setDescripcionSeccion(String descripcionSeccion) {
        this.descripcionSeccion = descripcionSeccion;
    }

    /**
     * Gets the ubicacion logoff.
     *
     * @return the ubicacion logoff
     */
    public String getUbicacionLogoff() {
        return ubicacionLogoff;
    }

    /**
     * Sets the ubicacion logoff.
     *
     * @param ubicacionLogoff
     *            the new ubicacion logoff
     */
    public void setUbicacionLogoff(String ubicacionLogoff) {
        this.ubicacionLogoff = ubicacionLogoff;
    }
    
    /**
     * Gets the variable 1 char.
     *
     * @return the variable 1 char
     */
    public String getVariable1Char() {
        return variable1Char;
    }

    /**
     * Sets the variable 1 char.
     *
     * @param variable1Char
     *            the new variable 1 char
     */
    public void setVariable1Char(String variable1Char) {
        this.variable1Char = variable1Char;
    }
    
	/**
	 * Gets the tipo oferta SC.
	 *
	 * @return the tipo oferta SC
	 */
	public String getTipoOfertaSC() {
		return tipoOfertaSC;
	}

	/**
	 * Sets the tipo oferta SC.
	 *
	 * @param tipoOfertaSC the new tipo oferta SC
	 */
	public void setTipoOfertaSC(String tipoOfertaSC) {
		this.tipoOfertaSC = tipoOfertaSC;
	}

	/**
	 * Gets the tipo producto loyalty.
	 *
	 * @return the tipo producto loyalty
	 */
	public String getTipoProductoLoyalty() {
		return tipoProductoLoyalty;
	}

	/**
	 * Sets the tipo producto loyalty.
	 *
	 * @param tipoProductoLoyalty the new tipo producto loyalty
	 */
	public void setTipoProductoLoyalty(String tipoProductoLoyalty) {
		this.tipoProductoLoyalty = tipoProductoLoyalty;
	}

	public String getIdLoyalty() {
		return idLoyalty;
	}

	public void setIdLoyalty(String idLoyalty) {
		this.idLoyalty = idLoyalty;
	}

	public String getUrlReferidos() {
		return urlReferidos;
	}

	public void setUrlReferidos(String urlReferidos) {
		this.urlReferidos = urlReferidos;
	}

    public OfertaComercialMicrofrontDto getOfertaComercialMicrofrontDto() {
        return this.microfront;
    }

    public void setOfertaComercialMicrofrontDto(OfertaComercialMicrofrontDto microfront) {
        this.microfront = microfront;
    }

    public boolean isFeedbackAvailable(String feedback) {
        return feedbackAvailable.contains(feedback);
    }

    public List<String> getFeedbackAvailable() {
        return feedbackAvailable;
    }

    public void setFeedbackAvailable(List<String> feedbackAvailable) {
        this.feedbackAvailable = feedbackAvailable;
    }

}
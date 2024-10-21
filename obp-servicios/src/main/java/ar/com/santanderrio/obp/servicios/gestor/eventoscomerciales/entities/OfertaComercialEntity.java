/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.codehaus.jackson.annotate.JsonTypeName;

/**
 * The Class OfertaComercialEntity.
 *
 * @author florencia.n.martinez
 */
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME)
@JsonTypeName("oferta")
public class OfertaComercialEntity {

    /** The oferta default. */
    @JsonProperty("oferta_default")
    private String ofertaDefault;

    /** The id oferta rtd. */
    @JsonProperty("id_oferta_rtd")
    private String idOfertaRtd;

    /** The id oferta interno. */
    @JsonProperty("id_oferta_interno")
    private String idOfertaInterno;

    /** The tipo oferta. */
    @JsonProperty("tipo_oferta")
    private String tipoOferta;

    /** The categoria oferta. */
    @JsonProperty("categoria_oferta")
    private String categoriaOferta;

    /** The url. */
    @JsonProperty("url")
    private String url;

    /** The link. */
    @JsonProperty("link")
    private String link;

    /** The grupo control. */
    @JsonProperty("grupo_control")
    private String grupoControl;

    /** The puntos oferta sc. */
    @JsonProperty("puntos_oferta_sc")
    private String puntosOfertaSc;

    /** The precio oferta sc. */
    @JsonProperty("precio_oferta_sc")
    private String precioOfertaSc;

    /** The titulo. */
    @JsonProperty("titulo")
    private String titulo;

    /** The descripcion. */
    @JsonProperty("descripcion")
    private String descripcion;

    /** The descriopcion seccion. */
    @JsonProperty("descripcion_seccion")
    private String descripcionSeccion;

    /** The seccion oferta. */
    @JsonProperty("seccion_oferta")
    private String seccionOferta;

    /** The indicador clic seccion. */
    @JsonProperty("indicador_clic_seccion")
    private String indicadorClicSeccion;

    /** The orden prioridad. */
    @JsonProperty("orden_prioridad")
    private String ordenPrioridad;

    /** The ubicacion carrusel. */
    @JsonProperty("ubicacion_carrusel")
    private String ubicacionCarrusel;

    /** The ubicacion seccion. */
    @JsonProperty("ubicacion_seccion")
    private String ubicacionSeccion;
    
    /** The origen. */
    @JsonProperty("origen")
    private String origen;

    /** The tipo oferta sc. */
    @JsonProperty("tipo_oferta_sc")
    private String tipoOfertaSc;

    /** The tipo producto loyalty. */
    @JsonProperty("tipo_producto_loyalty")
    private String tipoProductoLoyalty;

    /** The id loyalty. */
    @JsonProperty("id_loyalty")
    private String idLoyalty;
    
    @JsonProperty("importe_disponible_preacordado")
    private String importeDisponiblePreacordado;

    @JsonProperty("importe_disponible_cuota")
    private String importeDisponibleCuota;
    
    /** The goto. */
    private GotoLink gotoLink;
    
    /** The ubicacion logoff. */
    @JsonProperty("ubicacion_logoff")
    private String ubicacionLogoff;

    /** the variable1_char. */
	@JsonProperty("variable1_char")
	private String variable1Char;

    private List<String> feedbackAvailable;
	
    /**
     * Gets the oferta default.
     *
     * @return the oferta default
     */
    public String getOfertaDefault() {
        return ofertaDefault;
    }

    /**
     * Sets the oferta default.
     *
     * @param ofertaDefault
     *            the new oferta default
     */
    public void setOfertaDefault(String ofertaDefault) {
        this.ofertaDefault = ofertaDefault;
    }

    /**
     * Gets the id oferta rtd.
     *
     * @return the id oferta rtd
     */
    public String getIdOfertaRtd() {
        return idOfertaRtd;
    }

    /**
     * Sets the id oferta rtd.
     *
     * @param idOfertaRtd
     *            the new id oferta rtd
     */
    public void setIdOfertaRtd(String idOfertaRtd) {
        this.idOfertaRtd = idOfertaRtd;
    }

    /**
     * Gets the id oferta interno.
     *
     * @return the id oferta interno
     */
    public String getIdOfertaInterno() {
        return idOfertaInterno;
    }

    /**
     * Sets the id oferta interno.
     *
     * @param idOfertaInterno
     *            the new id oferta interno
     */
    public void setIdOfertaInterno(String idOfertaInterno) {
        this.idOfertaInterno = idOfertaInterno;
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
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url.
     *
     * @param url
     *            the new url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the link.
     *
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the link.
     *
     * @param link
     *            the new link
     */
    public void setLink(String link) {
        this.link = link;
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
     * Gets the puntos oferta sc.
     *
     * @return the puntos oferta sc
     */
    public String getPuntosOfertaSc() {
        return puntosOfertaSc;
    }

    /**
     * Sets the puntos oferta sc.
     *
     * @param puntosOfertaSc
     *            the new puntos oferta sc
     */
    public void setPuntosOfertaSc(String puntosOfertaSc) {
        this.puntosOfertaSc = puntosOfertaSc;
    }

    /**
     * Gets the precio oferta sc.
     *
     * @return the precio oferta sc
     */
    public String getPrecioOfertaSc() {
        return precioOfertaSc;
    }

    /**
     * Sets the precio oferta sc.
     *
     * @param precioOfertaSc
     *            the new precio oferta sc
     */
    public void setPrecioOfertaSc(String precioOfertaSc) {
        this.precioOfertaSc = precioOfertaSc;
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
     * Gets the descriopcion seccion.
     *
     * @return the descriopcion seccion
     */
    public String getDescripcionSeccion() {
        return descripcionSeccion;
    }

    /**
	 * Sets the descriopcion seccion.
	 *
	 * @param descripcionSeccion
	 *            the new descripcion seccion
	 */
    public void setDescripcionSeccion(String descripcionSeccion) {
        this.descripcionSeccion = descripcionSeccion;
    }

    /**
     * Gets the seccion oferta.
     *
     * @return the seccion oferta
     */
    public String getSeccionOferta() {
        return seccionOferta;
    }

    /**
     * Sets the seccion oferta.
     *
     * @param seccionOferta
     *            the new seccion oferta
     */
    public void setSeccionOferta(String seccionOferta) {
        this.seccionOferta = seccionOferta;
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
     * Gets the origen.
     *
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Sets the origen.
     *
     * @param origen
     *            the new origen
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * Gets the tipo oferta sc.
     *
     * @return the tipo oferta sc
     */
    public String getTipoOfertaSc() {
        return tipoOfertaSc;
    }

    /**
     * Sets the tipo oferta sc.
     *
     * @param tipoOfertaSc
     *            the new tipo oferta sc
     */
    public void setTipoOfertaSc(String tipoOfertaSc) {
        this.tipoOfertaSc = tipoOfertaSc;
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
     * @param tipoProductoLoyalty
     *            the new tipo producto loyalty
     */
    public void setTipoProductoLoyalty(String tipoProductoLoyalty) {
        this.tipoProductoLoyalty = tipoProductoLoyalty;
    }

    /**
     * Gets the id loyalty.
     *
     * @return the id loyalty
     */
    public String getIdLoyalty() {
        return idLoyalty;
    }

    /**
     * Sets the id loyalty.
     *
     * @param idLoyalty
     *            the new id loyalty
     */
    public void setIdLoyalty(String idLoyalty) {
        this.idLoyalty = idLoyalty;
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
     * Gets the importe disponible preacordado.
     *
     * @return the importe disponible preacordado
     */
    public String getImporteDisponiblePreacordado() {
		return importeDisponiblePreacordado;
	}

    /**
     * Sets the importe disponible preacordado.
     *
     * @param importe disponible preacordado
     *            the new importe disponible preacordado
     */
	public void setImporteDisponiblePreacordado(String importeDisponiblePreacordado) {
		this.importeDisponiblePreacordado = importeDisponiblePreacordado;
	}
	
	/**
	 * Gets the variable1 char.
	 *
	 * @return the variable1Char
	 */
	public String getVariable1Char() {
		return variable1Char;
	}
	
	/**
	 * Sets the variable1 char.
	 *
	 * @param variable1Char
	 *            the variable1 char to set
	 */
	public void setVariable1Char(String variable1Char) {
		this.variable1Char = variable1Char;
	}

    /**
	 * Gets the feedbackAvailable.
	 *
	 * @return the feedbackAvailable
	 */
    public List<String> getFeedbackAvailable() {
        return feedbackAvailable;
    }

    /**
	 * Sets the feedbackAvailable.
	 *
	 * @param feedbackAvailable
	 *            the feedbackAvailable to set
	 */
    public void setFeedbackAvailable(List<String> feedbackAvailable) {
        this.feedbackAvailable = feedbackAvailable;
    }

	/**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(descripcion);
        hcb.append(link);
        hcb.append(titulo);
        hcb.append(url);
        return hcb.toHashCode();
    }

    /**
     * Equals.
     *
     * @param obj
     *            the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OfertaComercialEntity other = (OfertaComercialEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(descripcion, other.getDescripcion());
        eb.append(link, other.getLink());
        eb.append(titulo, other.getTitulo());
        eb.append(url, other.getUrl());
        return eb.isEquals();
    }

    /**
     * ToString.
     *
     * @return the string
     */
    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this).append("ofertaDefault", ofertaDefault).append("idOfertaRtd", idOfertaRtd)
                .append("idOfertaInterno", idOfertaInterno).append("tipoOferta", tipoOferta)
                .append("categoriaOferta", categoriaOferta).append("url", url).append("link", link)
                .append("grupoControl", grupoControl).append("puntosOfertaSc", puntosOfertaSc)
                .append("precioOfertaSc", precioOfertaSc).append("titulo", titulo).append("descripcion", descripcion)
                .append("descripcionSeccion", descripcionSeccion).append("seccionOferta", seccionOferta)
                .append("indicadorClicSeccion", indicadorClicSeccion).append("ordenPrioridad", ordenPrioridad)
                .append("ubicacionCarrusel", ubicacionCarrusel).append("ubicacionSeccion", ubicacionSeccion)
                .append("origen", origen).append("tipoOfertaSc", tipoOfertaSc)
                .append("tipoProductoLoyalty", tipoProductoLoyalty).append("idLoyalty", idLoyalty)
                .append("ubicacionLogoff", ubicacionLogoff).toString();
    }

}

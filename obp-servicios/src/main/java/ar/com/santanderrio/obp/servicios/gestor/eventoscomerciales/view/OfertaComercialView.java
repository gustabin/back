/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GotoLink;

// TODO: Auto-generated Javadoc
/**
 * The Class OfertaComercialView.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class OfertaComercialView {

    /** The imagen url. */
    private String imagenUrl;

    /** The titulo. */
    private String titulo;

    /** The descripcion. */
    private String descripcion;
    
    /** The descripcion seccion. */
    private String descripcionSeccion;

    /** The link. */
    private String link;

    /** The seccion. */
    private String seccion;

    /** The id. */
    // para notificar interes
    private String id;

    /** The id interno. */
    // para tealium
    private String idInterno;

    /** The tipo oferta. */
    private String tipoOferta;

    /** The categoria oferta. */
    private String categoriaOferta;

    /** The grupo control. */
    private String grupoControl;

    /** The indicador clic seccion. */
    private String indicadorClicSeccion;

    /** The orden prioridad. */
    private String ordenPrioridad;

    /** The ubicacion carrusel. */
    private String ubicacionCarrusel;

    /** The boton. */
    private String boton;

    /** The contenido. */
    private WebContentView contenido;

    /** The contenido link. */
    private LinkView contenidoLink;
    
    /** The variable 1 char. */
    private String variable1Char;
    
    /** The numero cuenta. */
    private String numeroCuenta = StringUtils.EMPTY;
    
    /** The numero tarjeta. */
    private String numeroTarjeta = StringUtils.EMPTY;

    /** The tipo producto loyalty. */
    private String tipoProductoLoyalty;
    
    /** The id loyalty. */
    private String idLoyalty;
    
    /** The tipo oferta SC. */
    private String tipoOfertaSC;
    
    /** The url referidos. */
    private String urlReferidos;
    
    /** The goto link. */
    private GotoLink gotoLink;

    /**
     * Instantiates a new oferta comercial view.
     */
    public OfertaComercialView() {
        super();
    }

    /**
     * Instantiates a new oferta comercial view.
     *
     * @param ofertaComercialDTO
     *            the oferta comercial DTO
     * @param esCarrusel
     *            the es carrusel
     */
    public OfertaComercialView(OfertaComercialDTO ofertaComercialDTO, Boolean esCarrusel) {
        if (esCarrusel) {
            this.imagenUrl = ofertaComercialDTO.getImagenUrl();
        }

        if (StringUtils.isNotBlank(ofertaComercialDTO.getTitulo())) {
            this.titulo = ofertaComercialDTO.getTitulo();
        }
        if (StringUtils.isNotBlank(ofertaComercialDTO.getDescripcion())) {
            this.descripcion = ofertaComercialDTO.getDescripcion();
        }
        if (StringUtils.isNotBlank(ofertaComercialDTO.getDescripcionSeccion())) {
            this.setDescripcionSeccion(ofertaComercialDTO.getDescripcionSeccion());
        }

        if (StringUtils.isNotBlank(ofertaComercialDTO.getGotoLink().getLink())
                && (esCarrusel || ofertaComercialDTO.clicEnSeccion())) {
        	
        	this.link = ofertaComercialDTO.getGotoLink().getLink();
        	
        	if("goToLicitar()".equals(this.link) ||
        			"gotoCanjear()".equals(this.link)) {
        		this.link = ofertaComercialDTO.getGotoLink().getLinkLicitarCanjear();
        	}
        	
            this.boton = ofertaComercialDTO.getGotoLink().obtenerTexto();
            
            this.contenido = ofertaComercialDTO.getGotoLink().obtenerWebContent();
            this.contenidoLink = ofertaComercialDTO.getGotoLink().obtenerLinkContent();

        }

        this.seccion = ofertaComercialDTO.getUbicacionSeccion();
        this.id = ofertaComercialDTO.getIdRtd();

        this.idInterno = ofertaComercialDTO.getIdInterno();
        this.tipoOferta = ofertaComercialDTO.getTipoOferta();
        this.categoriaOferta = ofertaComercialDTO.getCategoriaOferta();
        this.grupoControl = ofertaComercialDTO.getGrupoControl();
        this.indicadorClicSeccion = ofertaComercialDTO.getIndicadorClicSeccion();
        this.ordenPrioridad = ofertaComercialDTO.getOrdenPrioridad();
        this.ubicacionCarrusel = ofertaComercialDTO.getUbicacionCarrusel();
        this.variable1Char = ofertaComercialDTO.getVariable1Char();
        this.tipoProductoLoyalty = ofertaComercialDTO.getTipoProductoLoyalty();
        this.idLoyalty = ofertaComercialDTO.getIdLoyalty();
        this.tipoOfertaSC = ofertaComercialDTO.getTipoOfertaSC();
        this.urlReferidos = ofertaComercialDTO.getUrlReferidos();
              
    }

    /**
     * Instantiates a new oferta comercial view.
     *
     * @param imagenUrl
     *            the imagen url
     * @param titulo
     *            the titulo
     * @param descripcion
     *            the descripcion
     * @param link
     *            the link
     */
    public OfertaComercialView(String imagenUrl, String titulo, String descripcion, String link) {
        this.imagenUrl = imagenUrl;
        if (StringUtils.isNotBlank(titulo)) {
            this.titulo = titulo;
        }
        if (StringUtils.isNotBlank(descripcion)) {
            this.descripcion = descripcion;
        }
        if (StringUtils.isNotBlank(link)) {
            this.link = link;
        }
    }

    /**
     * Gets the seccion.
     *
     * @return the seccion
     */
    public String getSeccion() {
        return seccion;
    }

    /**
     * Sets the seccion.
     *
     * @param seccion
     *            the new seccion
     */
    public void setSeccion(String seccion) {
        this.seccion = seccion;
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
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(String id) {
        this.id = id;
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
     * Gets the boton.
     *
     * @return the boton
     */
    public String getBoton() {
        return boton;
    }

    /**
     * Sets the boton.
     *
     * @param boton
     *            the new boton
     */
    public void setBoton(String boton) {
        this.boton = boton;
    }

    /**
     * Gets the contenido.
     *
     * @return the contenido
     */
    public WebContentView getContenido() {
        return contenido;
    }

    /**
     * Sets the contenido.
     *
     * @param contenido
     *            the new contenido
     */
    public void setContenido(WebContentView contenido) {
        this.contenido = contenido;
    }

    /**
     * Gets the contenido link.
     *
     * @return the contenido link
     */
    public LinkView getContenidoLink() {
        return contenidoLink;
    }

    /**
     * Sets the contenido link.
     *
     * @param contenidoLink
     *            the new contenido link
     */
    public void setContenidoLink(LinkView contenidoLink) {
        this.contenidoLink = contenidoLink;
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
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(boton);
        hcb.append(categoriaOferta);
        hcb.append(contenido);
        hcb.append(contenidoLink);
        hcb.append(descripcion);
        hcb.append(grupoControl);
        hcb.append(id);
        hcb.append(idInterno);
        hcb.append(imagenUrl);
        hcb.append(link);
        hcb.append(ordenPrioridad);
        hcb.append(seccion);
        hcb.append(tipoOferta);
        hcb.append(titulo);
        hcb.append(ubicacionCarrusel);
        hcb.append(variable1Char);
        hcb.append(tipoProductoLoyalty);
        hcb.append(tipoOfertaSC);
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
        OfertaComercialView other = (OfertaComercialView) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(boton, other.getBoton());
        eb.append(categoriaOferta, other.getCategoriaOferta());
        eb.append(contenido, other.getContenidoLink());
        eb.append(contenidoLink, other.getContenidoLink());
        eb.append(descripcion, other.getDescripcion());
        eb.append(grupoControl, other.getGrupoControl());
        eb.append(id, other.getId());
        eb.append(idInterno, other.getIdInterno());
        eb.append(imagenUrl, other.getImagenUrl());
        eb.append(link, other.getLink());
        eb.append(ordenPrioridad, other.getOrdenPrioridad());
        eb.append(seccion, other.getSeccion());
        eb.append(tipoOferta, other.getTipoOferta());
        eb.append(titulo, other.getTitulo());
        eb.append(ubicacionCarrusel, other.getUbicacionCarrusel());
        eb.append(variable1Char, other.getVariable1Char());
        eb.append(tipoProductoLoyalty, other.getTipoProductoLoyalty());
        eb.append(tipoOfertaSC, other.getTipoOfertaSC());
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
        return new ToStringBuilder(this).append("imagenUrl", imagenUrl).append("titulo", titulo)
                .append("descripcion", descripcion).append("link", link).append("seccion", seccion).append("id", id)
                .append("idInterno", idInterno).append("tipoOferta", tipoOferta)
                .append("categoriaOferta", categoriaOferta).append("grupoControl", grupoControl)
                .append("indicadorClicSeccion", indicadorClicSeccion).append("ordenPrioridad", ordenPrioridad)
                .append("ubicacionCarrusel", ubicacionCarrusel).append("boton", boton).append("contenido", contenido)
                .append("contenidoLink", contenidoLink).append("variable1Char", variable1Char)
                .append("tipoProductoLoyalty", tipoProductoLoyalty).append("tipoOfertaSC", tipoOfertaSC).toString();
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
	 * Gets the variable 1 char.
	 *
	 * @return the variable1Char
	 */
	public String getVariable1Char() {
		return variable1Char;
	}

	/**
	 * Sets the variable 1 char.
	 *
	 * @param variable1Char the variable1Char to set
	 */
	public void setVariable1Char(String variable1Char) {
		this.variable1Char = variable1Char;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numero tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta the new numero tarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
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
	 * @param idLoyalty the new id loyalty
	 */
	public void setIdLoyalty(String idLoyalty) {
		this.idLoyalty = idLoyalty;
	}

	/**
	 * Gets the url referidos.
	 *
	 * @return the url referidos
	 */
	public String getUrlReferidos() {
		return urlReferidos;
	}

	/**
	 * Sets the url referidos.
	 *
	 * @param urlReferidos the new url referidos
	 */
	public void setUrlReferidos(String urlReferidos) {
		this.urlReferidos = urlReferidos;
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
		
}
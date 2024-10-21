/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.annotate.JsonTypeName;

/**
 * The Class NotificacionOutEntity.
 */
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME)
@JsonTypeName("notificacion")
public class NotificacionOutEntity {

    /** The id notificacion. */
    @NotNull
    @JsonProperty("id_notificacion")
    private String idNotificacion;

    /** The nup. */
    @Pattern(regexp = "^\\d*$")
    @NotNull
    @JsonProperty("nup")
    private String nup;

    /** The codigo. */
    @NotNull
    @JsonProperty("codigo")
    private String codigo;

    /** The sub codigo. */
    @NotNull
    @JsonProperty("sub_codigo")
    private String subCodigo;

    /** The titulo. */
    @NotNull
    @JsonProperty("titulo")
    private String titulo;

    /** The mensaje amigable. */
    @NotNull
    @JsonProperty("mensaje_amigable")
    private String mensajeAmigable;

    /** The indica lectura. */
    @Pattern(regexp = "S|N")
    @NotNull
    @JsonProperty("ind_lectura")
    private String indicaLectura;

    /** The indica inactivable. */
    @Pattern(regexp = "S|N")
    @NotNull
    @JsonProperty("ind_inactivable")
    private String indicaInactivable;

    /** The url. */
    @JsonProperty("url")
    private String url;

    /** The link. */
    @JsonProperty("link")
    private String link;

    /** The fecha alta. */
    @NotNull
    @JsonProperty("fecha_alta")
    private String fechaAlta;
    
    /** The fecha desde. */
    
    @JsonProperty("fecha_desde")
    private String fechaDesde;
    
    /** The fecha hasta. */
    
    @JsonProperty("fecha_hasta")
    private String fechaHasta;
    
    /** The . idCaso*/
    
    @JsonProperty("id_caso")
    private String idCaso;
    
    /** The . link portal*/
    
    @JsonProperty("link_portal")
    private String linkPortal;
    
    /** The . tipo mensaje*/
    
    @JsonProperty("tipo_mensaje")
    private String tipoMensaje;
    
    /** The . tipo dato*/
    
    @JsonProperty("tipo_dato")
    private String tipoDato;

    /** The ddt pago tarjeta id. */
    @JsonProperty("ddt_pago_trj_id")
    private String ddtPagoTarjetaId;

    /** The ddt motivo rechazo. */
    @JsonProperty("ddt_mot_rechazo")
    private String ddtMotivoRechazo;

    /** The ddt info serv id empresa. */
    @JsonProperty("ddt_info_serv_id_empresa")
    private String ddtInfoServIdEmpresa;

    /** The ddt cuenta nro cuenta. */
    @JsonProperty("ddt_cuenta_nro_cuenta")
    private String ddtCuentaNroCuenta;

    /** The goto link. */
    private GotoLink gotoLink;
    
    @JsonProperty("pm_cod_notificacion")
    private String pmCodNotificacion;
    
    @JsonProperty("pm_fecha_promesa")
    private String fechaPromesa;
    
    @JsonProperty("pm_monto_promesa")
    private String montoPromesa;
    
    @JsonProperty("pm_monto_total")
    private String montoTotal;
    
    @JsonProperty("pm_dia")
    private String pmDia;
        
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String mensajeDescripcionPP;
    
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String mensajeDescripcionPPDeudasViejas;
    
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String ayudaPromesaPago;
    
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String ayudaDescubierto;
    
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String mensajeErrorDeudasPP;
    
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String mensajePromesaPagoVencida;
    
    @JsonSerialize(include = Inclusion.NON_NULL)
    @JsonProperty("pm_productos")
    private String pmProductos;
    
      
    /**
     * Gets the id notificacion.
     *
     * @return the id notificacion
     */
    public String getIdNotificacion() {
        return idNotificacion;
    }

    /**
     * Sets the id notificacion.
     *
     * @param idNotificacion
     *            the new id notificacion
     */
    public void setIdNotificacion(String idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    /**
     * Gets the nup.
     *
     * @return the nup
     */
    public String getNup() {
        return nup;
    }

    /**
     * Sets the nup.
     *
     * @param nup
     *            the new nup
     */
    public void setNup(String nup) {
        this.nup = nup;
    }

    /**
     * Gets the codigo.
     *
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the codigo.
     *
     * @param codigo
     *            the new codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets the sub codigo.
     *
     * @return the sub codigo
     */
    public String getSubCodigo() {
        return subCodigo;
    }

    /**
     * Sets the sub codigo.
     *
     * @param subCodigo
     *            the new sub codigo
     */
    public void setSubCodigo(String subCodigo) {
        this.subCodigo = subCodigo;
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
     * Gets the mensaje amigable.
     *
     * @return the mensaje amigable
     */
    public String getMensajeAmigable() {
        return mensajeAmigable;
    }

    /**
     * Sets the mensaje amigable.
     *
     * @param mensajeAmigable
     *            the new mensaje amigable
     */
    public void setMensajeAmigable(String mensajeAmigable) {
        this.mensajeAmigable = mensajeAmigable;
    }

    /**
     * Gets the indica lectura.
     *
     * @return the indica lectura
     */
    public String getIndicaLectura() {
        return indicaLectura;
    }

    /**
     * Sets the indica lectura.
     *
     * @param indicaLectura
     *            the new indica lectura
     */
    public void setIndicaLectura(String indicaLectura) {
        this.indicaLectura = indicaLectura;
    }

    /**
     * Gets the indica inactivable.
     *
     * @return the indica inactivable
     */
    public String getIndicaInactivable() {
        return indicaInactivable;
    }

    /**
     * Sets the indica inactivable.
     *
     * @param indicaInactivable
     *            the new indica inactivable
     */
    public void setIndicaInactivable(String indicaInactivable) {
        this.indicaInactivable = indicaInactivable;
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
     * Gets the fecha alta.
     *
     * @return the fecha alta
     */
    public String getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets the fecha alta.
     *
     * @param fechaAlta
     *            the new fecha alta
     */
    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Gets the ddt pago tarjeta id.
     *
     * @return the ddt pago tarjeta id
     */
    public String getDdtPagoTarjetaId() {
        return ddtPagoTarjetaId;
    }

    /**
     * Sets the ddt pago tarjeta id.
     *
     * @param ddtPagoTarjetaId
     *            the new ddt pago tarjeta id
     */
    public void setDdtPagoTarjetaId(String ddtPagoTarjetaId) {
        this.ddtPagoTarjetaId = ddtPagoTarjetaId;
    }

    /**
     * Gets the ddt motivo rechazo.
     *
     * @return the ddt motivo rechazo
     */
    public String getDdtMotivoRechazo() {
        return ddtMotivoRechazo;
    }

    /**
     * Sets the ddt motivo rechazo.
     *
     * @param ddtMotivoRechazo
     *            the new ddt motivo rechazo
     */
    public void setDdtMotivoRechazo(String ddtMotivoRechazo) {
        this.ddtMotivoRechazo = ddtMotivoRechazo;
    }

    /**
     * Gets the ddt info serv id empresa.
     *
     * @return the ddt info serv id empresa
     */
    public String getDdtInfoServIdEmpresa() {
        return ddtInfoServIdEmpresa;
    }

    /**
     * Sets the ddt info serv id empresa.
     *
     * @param ddtInfoServIdEmpresa
     *            the new ddt info serv id empresa
     */
    public void setDdtInfoServIdEmpresa(String ddtInfoServIdEmpresa) {
        this.ddtInfoServIdEmpresa = ddtInfoServIdEmpresa;
    }

    /**
     * Gets the ddt cuenta nro cuenta.
     *
     * @return the ddt cuenta nro cuenta
     */
    public String getDdtCuentaNroCuenta() {
        return ddtCuentaNroCuenta;
    }

    /**
     * Sets the ddt cuenta nro cuenta.
     *
     * @param ddtCuentaNroCuenta
     *            the new ddt cuenta nro cuenta
     */
    public void setDdtCuentaNroCuenta(String ddtCuentaNroCuenta) {
        this.ddtCuentaNroCuenta = ddtCuentaNroCuenta;
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
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde the new fecha desde
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta the new fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Gets the id caso.
	 *
	 * @return the id caso
	 */
	public String getIdCaso() {
		return idCaso;
	}

	/**
	 * Sets the id caso.
	 *
	 * @param idCaso the new id caso
	 */
	public void setIdCaso(String idCaso) {
		this.idCaso = idCaso;
	}

	/**
	 * Gets the link portal.
	 *
	 * @return the link portal
	 */
	public String getLinkPortal() {
		return linkPortal;
	}

	/**
	 * Sets the link portal.
	 *
	 * @param linkPortal the new link portal
	 */
	public void setLinkPortal(String linkPortal) {
		this.linkPortal = linkPortal;
	}

	/**
	 * Gets the tipo mensaje.
	 *
	 * @return the tipo mensaje
	 */
	public String getTipoMensaje() {
		return tipoMensaje;
	}

	/**
	 * Sets the tipo mensaje.
	 *
	 * @param tipoMensaje the new tipo mensaje
	 */
	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	/**
	 * Gets the tipo dato.
	 *
	 * @return the tipo dato
	 */
	public String getTipoDato() {
		return tipoDato;
	}

	/**
	 * Sets the tipo dato.
	 *
	 * @param tipoDato the new tipo dato
	 */
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public String getMensajePromesaPagoVencida() {
		return mensajePromesaPagoVencida;
	}

	public void setMensajePromesaPagoVencida(String mensajePromesaPagoVencida) {
		this.mensajePromesaPagoVencida = mensajePromesaPagoVencida;
	}

	public String getPmCodNotificacion() {
		return pmCodNotificacion;
	}

	public void setPmCodNotificacion(String pmCodNotificacion) {
		this.pmCodNotificacion = pmCodNotificacion;
	}

	public String getFechaPromesa() {
		return fechaPromesa;
	}

	public void setFechaPromesa(String fechaPromesa) {
		this.fechaPromesa = fechaPromesa;
	}

	public String getMontoPromesa() {
		return montoPromesa;
	}

	public void setMontoPromesa(String montoPromesa) {
		this.montoPromesa = montoPromesa;
	}

	public String getPmDia() {
		return pmDia;
	}

	public void setPmDia(String pmDia) {
		this.pmDia = pmDia;
	}
		
	public String getPmProductos() {
		return pmProductos;
	}

	public void setPmProductos(String pmProductos) {
		this.pmProductos = pmProductos;
	}

	public String getMensajeDescripcionPP() {
		return mensajeDescripcionPP;
	}

	public void setMensajeDescripcionPP(String mensajeDescripcionPP) {
		this.mensajeDescripcionPP = mensajeDescripcionPP;
	}

	public String getAyudaPromesaPago() {
		return ayudaPromesaPago;
	}

	public void setAyudaPromesaPago(String ayudaPromesaPago) {
		this.ayudaPromesaPago = ayudaPromesaPago;
	}

	public String getAyudaDescubierto() {
		return ayudaDescubierto;
	}

	public void setAyudaDescubierto(String ayudaDescubierto) {
		this.ayudaDescubierto = ayudaDescubierto;
	}

	public String getMensajeErrorDeudasPP() {
		return mensajeErrorDeudasPP;
	}

	public void setMensajeErrorDeudasPP(String mensajeErrorDeudasPP) {
		this.mensajeErrorDeudasPP = mensajeErrorDeudasPP;
	}

	public String getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(String montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getMensajeDescripcionPPDeudasViejas() {
		return mensajeDescripcionPPDeudasViejas;
	}

	public void setMensajeDescripcionPPDeudasViejas(String mensajeDescripcionPPDeudasViejas) {
		this.mensajeDescripcionPPDeudasViejas = mensajeDescripcionPPDeudasViejas;
	}
				
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.joda.time.DateTime;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.NotificacionOutEntity;


/**
 * The Class NotificacionComercialView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class NotificacionComercialView {

    /** The id. */
    private final String id;

    /** The titulo. */
    private final String titulo;

    /** The descripcion. */
    private final String descripcion;

    /** The link. */
    private final String link;

    /** The imagen url. */
    private final String imagenUrl;

    /** The fecha alta. */
    private final String fechaAlta;
    
    /** The fecha desde. */
    private final String fechaDesde;
    
    /** The fecha hasta. */
    private final String fechaHasta;
    
    /** The id caso. */
    private final String idCaso;
    
    /** The link portal. */
    private final String linkPortal;
    
    /** The tipo mensaje. */
    private final String tipoMensaje;
    
    /** The tipo dato. */
    private final String tipoDato;

    /** The leido. */
    private final Boolean leido;

    /** The boton. */
    private final String boton;

    /** The contenido. */
    private final WebContentView contenido;

    /** The contenido link. */
    private final LinkView contenidoLink;
    
    private String numeroCuenta = StringUtils.EMPTY;
    
    private String numeroTarjeta = StringUtils.EMPTY;
    
    private final PromesaPagoView promesaPago;
    
    private static final String CODIGO_PROMESA_PAGO_INCUMPLIDA = "PM0003";
    
    private static final String CODIGO_AVISO_DEUDA_PROMESA_PAGO = "PM0001";
    
    private final String urlReferidos;


    /**
     * Instantiates a new notificacion comercial view.
     *
     * @param notificacion
     *            the notificacion
     */
    public NotificacionComercialView(NotificacionOutEntity notificacion, Cliente cliente) {
        this.id = notificacion.getIdNotificacion();
        this.titulo = notificacion.getTitulo();
        this.descripcion = notificacion.getMensajeAmigable();
        this.link = notificacion.getGotoLink().getLink();
        this.imagenUrl = notificacion.getUrl();
        this.leido = "S".equals(notificacion.getIndicaLectura()) ? true : false;        
        String[] auxFecha = notificacion.getFechaAlta().split("-");
        this.fechaAlta = auxFecha[2] + "/" + auxFecha[1] + "/" + auxFecha[0];
        if (null != notificacion.getFechaDesde()) {
            auxFecha = notificacion.getFechaDesde().split("-");
            this.fechaDesde = auxFecha[2] + "/" + auxFecha[1] + "/" + auxFecha[0];
        }else {
        	this.fechaDesde = null;
        }
        if (null != notificacion.getFechaHasta()) {
            auxFecha = notificacion.getFechaHasta().split("-");
            this.fechaHasta = auxFecha[2] + "/" + auxFecha[1] + "/" + auxFecha[0];
        } else {
        	this.fechaHasta = null;
        }
        this.idCaso = notificacion.getIdCaso();
        this.linkPortal = notificacion.getLinkPortal();
        this.tipoMensaje = notificacion.getTipoMensaje();
        this.tipoDato = notificacion.getTipoDato();

        this.boton = notificacion.getGotoLink().obtenerTexto();        
        this.contenido = notificacion.getGotoLink().obtenerWebContent();
        this.contenidoLink = notificacion.getGotoLink().obtenerLinkContent();
        this.urlReferidos = notificacion.getUrl();
        
        if (CODIGO_PROMESA_PAGO_INCUMPLIDA.equals(notificacion.getCodigo()) ||
        	CODIGO_AVISO_DEUDA_PROMESA_PAGO.equals(notificacion.getCodigo())) {
        	this.promesaPago = armarObjetoPromesaPago(notificacion, cliente.getCuentas());
        } else {
        	this.promesaPago = null;
        }        
       
    }

    private PromesaPagoView armarObjetoPromesaPago (NotificacionOutEntity notificacion, List<Cuenta> cuentas) {
    	PromesaPagoView promesaPagoObjeto = new PromesaPagoView();
    	
    	if (StringUtils.isNotEmpty(notificacion.getMensajePromesaPagoVencida())) {
    		String anio = notificacion.getFechaPromesa().substring(0,4);
    		String mes = notificacion.getFechaPromesa().substring(5,7);
    		String dia = notificacion.getFechaPromesa().substring(8,10);
    		String fechaPromesaFormateada = dia+"/"+mes+"/"+anio;
    		
    		BigDecimal bigMonto = new BigDecimal(notificacion.getMontoPromesa().replaceAll("\\,", "\\."));
    		String monto = DivisaEnum.PESO.getSimbolo() + " " + ISBANStringUtils.formatearSaldo(bigMonto);
    		String mensajePromesaPagoVencida = MessageFormat.format(notificacion.getMensajePromesaPagoVencida(), monto, fechaPromesaFormateada);
    		promesaPagoObjeto.setMensajePromesaPagoVencida(mensajePromesaPagoVencida);
    		return promesaPagoObjeto;
    	}
    	
    	if (!"0".equals(notificacion.getMontoTotal())) {
    		BigDecimal bigMonto = new BigDecimal(notificacion.getMontoTotal().replaceAll("\\,", "\\."));
    		promesaPagoObjeto.setMontoTotalAdeudado(DivisaEnum.PESO.getSimbolo() + " " + ISBANStringUtils.formatearSaldo(bigMonto));
    		promesaPagoObjeto.setDoubleMontoTotalAdeudado(bigMonto.doubleValue());
    	} else {
    		promesaPagoObjeto.setMontoTotalAdeudado(DivisaEnum.PESO.getSimbolo() + " " + "0,00");
    		promesaPagoObjeto.setDoubleMontoTotalAdeudado(0);
    	}
    	
    	if (StringUtils.isNotEmpty(notificacion.getPmProductos())) {
    		promesaPagoObjeto.setListaDeudas(cargarListaDeudas(notificacion.getPmProductos(), cuentas, promesaPagoObjeto));
    	}

    	promesaPagoObjeto.setAyudaDescubierto(notificacion.getAyudaDescubierto());
    	promesaPagoObjeto.setAyudaPromesaPago(notificacion.getAyudaPromesaPago());
    	promesaPagoObjeto.setMensajeErrorDeudas(notificacion.getMensajeErrorDeudasPP());
    	promesaPagoObjeto.setMensajeDescripcion(notificacion.getMensajeDescripcionPP());
    	promesaPagoObjeto.setMostrarBotonPromesaPago(revisarSiCorrespondeMostrarBotonPP(promesaPagoObjeto, notificacion.getMensajeDescripcionPPDeudasViejas()));
    	
    	return promesaPagoObjeto;
    }
    
    /**
     * 
     * @param deudasString: el string con todas las deudas concatenadas
     * @param cuentas: la lista con todos los productos del cliente
     * @param promesaPagoView: el objeto view con las deudas formateadas
     * @return
     * 
     * Querida Edna, debo comentarte:
     * Las deudas vienen desde el servicio GEC (Gestor Eventos Comerciales) con este formato:
     * "40|INT |0084|035100303551|100|20190901||05|1001|0084|035100037821|150|20190901||"
     * en donde se separa cada deuda con un ||, y a su vez, cada deuda tiene separados 
     * los distintos campos por un |.
     *  
     * Por que? no puedo decirlo, 
     * adonde ire? no puedes saberlo, 
     * como llegare? aun no lo decido
     * 
     * en realidad, por una imposibilidad tecnica no pueden mandarlas como una lista,
     * y ésta fue una manganeta intermedia, que la gente del servicio podia hacer
     * y que a nosotros nos servia para manipular las deudas.
     * 
     * Woodrow
     * 
     */
    private List<DeudaProductoPromesaPagoView> cargarListaDeudas (String deudasString, List<Cuenta> cuentas, PromesaPagoView promesaPagoView) {
    	
    	List<DeudaProductoPromesaPagoView> listaDeudasView = new ArrayList<DeudaProductoPromesaPagoView>();
    	
    	
    	String[] deudas = deudasString.split("\\|\\|");
    	for (String deudaEntera : deudas) {
    		String[] datosDeudas = deudaEntera.split("\\|");
        	for (Cuenta cuenta : cuentas) {
        		if (datosDeudas[0].equals(cuenta.getProductoAltair()) && datosDeudas[1].equals(cuenta.getSubproductoAltair().trim()) &&
        				datosDeudas[2].equals(cuenta.getNroSucursal())) {
        			DeudaProductoPromesaPagoView deudaProducto = new DeudaProductoPromesaPagoView(datosDeudas, cuenta);
        			listaDeudasView.add(deudaProducto);
        			break;
        		}
        	}
    	}
    	
    	if (listaDeudasView.isEmpty()) { 
    		promesaPagoView.setErrorTotal(Boolean.TRUE);
    	}
    	
    	if (!listaDeudasView.isEmpty() && listaDeudasView.size() < deudas.length) {
    		promesaPagoView.setErrorParcial(Boolean.TRUE);
    	}
    	
    	return listaDeudasView;
    }
    

    
    
    private Boolean revisarSiCorrespondeMostrarBotonPP(PromesaPagoView promesaPagoView, String mensajeDeudasTramoTres) {
    	
    	int cantidadDeudaTramo3 = 0;
    	
    	for (DeudaProductoPromesaPagoView deuda : promesaPagoView.getListaDeudas()) {
    		if (AtrasoDeudaPromesaPagoEnum.TRAMO_3.equals(deuda.getTipoAtrasoDeuda())) {
    			cantidadDeudaTramo3++;
    		}
    	}
    	
    	if (cantidadDeudaTramo3 == promesaPagoView.getListaDeudas().size() && 
    		!promesaPagoView.getErrorParcial() && !promesaPagoView.getErrorTotal()) {
    		promesaPagoView.setMensajeDescripcion(mensajeDeudasTramoTres);
    		promesaPagoView.setAyudaPromesaPago(null);
    		return Boolean.FALSE;
    	} else {
    		promesaPagoView.setListaFechasPago(cargarListaFechasParaPagar(promesaPagoView));
    		return Boolean.TRUE;
    	}
    	
    }
    
    private List<FechaPagoPP> cargarListaFechasParaPagar (PromesaPagoView promesaPagoView) {
    	
    	int cantidadDeudaTramo1 = 0;
    	int cantidadDiasHabiles = 0;
    	
    	for (DeudaProductoPromesaPagoView deuda : promesaPagoView.getListaDeudas()) {
    		if (AtrasoDeudaPromesaPagoEnum.TRAMO_1.equals(deuda.getTipoAtrasoDeuda())) {
    			cantidadDeudaTramo1++;
    		}
    	}
    	
    	if (cantidadDeudaTramo1 == promesaPagoView.getListaDeudas().size()) {
    		cantidadDiasHabiles = 8;
    	} else {
    		cantidadDiasHabiles = 4;
    	}
    	
		List<FechaPagoPP> fechas = new ArrayList<FechaPagoPP>();
		
		DateTime fechaJodaTime = new DateTime();
		
		while (fechas.size() <= cantidadDiasHabiles) {
			if (fechaJodaTime.getDayOfWeek() != 6 && fechaJodaTime.getDayOfWeek() != 7) {
				fechas.add(new FechaPagoPP(fechaJodaTime.toString(("dd/MM/yyyy"))));
				fechaJodaTime = fechaJodaTime.plusDays(1);
			} else {
				fechaJodaTime = fechaJodaTime.plusDays(1);
			}
		}
		
		return fechas;
    	
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
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
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
     * Gets the imagen url.
     *
     * @return the imagen url
     */
    public String getImagenUrl() {
        return imagenUrl;
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
     * Gets the leido.
     *
     * @return the leido
     */
    public Boolean getLeido() {
        return leido;
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
     * Gets the boton.
     *
     * @return the boton
     */
    public String getBoton() {
        return boton;
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
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
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
	 * Gets the link portal.
	 *
	 * @return the link portal
	 */
	public String getLinkPortal() {
		return linkPortal;
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
	 * Gets the tipo dato.
	 *
	 * @return the tipo dato
	 */
	public String getTipoDato() {
		return tipoDato;
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
     * Gets the contenido link.
     *
     * @return the contenido link
     */
    public LinkView getContenidoLink() {
        return contenidoLink;
    }
    
    public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	
	

	public String getUrlReferidos() {
		return urlReferidos;
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
        hcb.append(fechaAlta);
        hcb.append(fechaHasta);
        hcb.append(fechaDesde);
        hcb.append(idCaso);
        hcb.append(linkPortal);
        hcb.append(tipoMensaje);
        hcb.append(tipoDato);
        hcb.append(id);
        hcb.append(imagenUrl);
        hcb.append(leido);
        hcb.append(link);
        hcb.append(titulo);
        hcb.append(urlReferidos);
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NotificacionComercialView other = (NotificacionComercialView) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(descripcion, other.getDescripcion());
        eb.append(fechaAlta, other.getFechaAlta());        
        eb.append(fechaDesde, other.getFechaDesde());
        eb.append(fechaHasta, other.getFechaHasta());
        eb.append(idCaso, other.getIdCaso());
        eb.append(linkPortal, other.getLinkPortal());
        eb.append(tipoMensaje, other.getTipoMensaje());
        eb.append(tipoDato, other.getTipoDato());        
        eb.append(id, other.getId());
        eb.append(imagenUrl, other.getImagenUrl());
        eb.append(leido, other.getLeido());
        eb.append(link, other.getLink());
        eb.append(titulo, other.getTitulo());
        eb.append(urlReferidos, other.getUrlReferidos());
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
        return new ToStringBuilder(this).append("id", id).append("titulo", titulo).append("descripcion", descripcion)
                .append("link", link).append("imagenUrl", imagenUrl).append("fechaAlta", fechaAlta)                
                .append("fechaDesde", fechaDesde).append("fechaHasta", fechaHasta).append("idCaso", idCaso)
                .append("linkPortal", linkPortal).append("tipoMensaje", tipoMensaje).append("tipoDato", tipoDato)                               
                .append("leido", leido).append("boton", boton)
                .append("contenido", contenido).append("contenidoLink", contenidoLink)
                .append("urlReferidos", urlReferidos).toString();
    }

	public PromesaPagoView getPromesaPago() {
		return promesaPago;
	}
	
	public static void main(String[] args) {
		
		BigDecimal monto = new BigDecimal ("271732.44");
		double doubleMonto = monto.doubleValue();
		System.out.println(monto);
		
		System.out.println("----------");
		
		System.out.println(doubleMonto);
		
	}


}
/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import java.util.HashMap;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.LinkView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.WebContentView;

/**
 * The Class GotoLink.
 */
public class GotoLink {

    /** redirecciones que la aplicación puede ejecutar. */
    private static final Map<String, String> REDIRECCIONES_CONTEXTUALES = new HashMap<String, String>();

    /** The Constant CONSULTAR. */
    private static final String CONSULTAR = "Consultar";

    /** The Constant PAGAR. */
    private static final String PAGAR = "Pagar";

    /** The Constant SOLICITAR. */
    private static final String SOLICITAR = "Solicitar";

    /** The Constant TRANSFERIR. */
    private static final String TRANSFERIR = "Transferir";

    /** The Constant RECARGAR. */
    private static final String RECARGAR = "Recargar";

    /** The Constant SUSCRIBIR. */
    private static final String SUSCRIBIR = "Suscribir";

    /** The Constant CONOCE_MAS. */
    private static final String CONOCE_MAS = "Conocé más";

    /** The Constant VER_MAS. */
    private static final String VER_MAS = "Ver más";   

    /** The Constant VER_OPCIONES. */
    private static final String VER_OPCIONES = "Ver opciones";

    /** The Constant FINANCIAR_TARJETA. */
    private static final String FINANCIAR_TARJETA = "Financiar tarjeta";

    /** The Constant COTIZAR. */
    private static final String COTIZAR = "Cotizar";
        
    private static final String PEDILO_AHORA = "Pedilo ahora";
	
	private static final String ADJUNTAR = "Adjuntar";       
    /** The Constant CHAT. */
    @SuppressWarnings("unused")
	private static final String CHAT = "Atencion Personalizada";
    
    private static final String TURNOS = "Solicitar turno"; 
    
    private static final String ADHERIR = "Adherir";
    
    private static final String EXTRAER = "Extraer";
	
    private static final String CANJE = "Canje de Producto";
    
    private static final String EMITIR = "Emitir";
    
    private static final String LICITAR = "Licitar";
    
    private static final String CANJEAR = "Canjear";
    
    private static final String OPERAR = "Operar";

    
    private static final String REFERIR = "Referir";
    
    /** The Constant CONOCE_MAS. */
    private static final String CONOCE_MAS2 = "¡Conocé más!";

    public static final String GO_TO_AVISO_RECUPERACIONES = "gotoAvisoRecuperaciones()";
    
    public static final String GO_TO_AVISO_RECUPERACIONES_CURRENT = "gotoAvisoRecuperacionesCurrent()";
    
    public static final String ACTIVAR = "Activar";

    static {
        REDIRECCIONES_CONTEXTUALES.put("gotoCuentasInicio()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoTarjetasInicio()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoCalendarioDePagos()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoCreditosInicio()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSegurosInicio()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInversionesInicio()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInicioSuperClub()", CONOCE_MAS2);
        REDIRECCIONES_CONTEXTUALES.put("gotoPagoHaberes()", PAGAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoLanding()", CONOCE_MAS);
        REDIRECCIONES_CONTEXTUALES.put("gotoPagoTarjetaCredito()", PAGAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoConstitucionPlazoFijo()", "Constituir");
        REDIRECCIONES_CONTEXTUALES.put("gotoMyASuscripcionSorpresa()", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoComprayVentaDolares()", "Ir a compra-venta");
        REDIRECCIONES_CONTEXTUALES.put("gotoSolicitudTarjetaAdicional()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoFCISuscribir()", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInversionesFCISuscribir()", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoFCIRescatar()", "Rescatar");
        REDIRECCIONES_CONTEXTUALES.put("gotoFCITransferir()", TRANSFERIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoNuevoPago()", PAGAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoTransferencias()", TRANSFERIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoRecargaCelular()", RECARGAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoFinanciacionTarjeta()", "Financiar");
        REDIRECCIONES_CONTEXTUALES.put("gotoSolicitudReimpresionTarjeta()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSolicitudCambioLimiteExtraccion()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSolicitudTarjetaRecargable()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoRecargarTarjetaRecargable()", RECARGAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoLink()", CONOCE_MAS);
        REDIRECCIONES_CONTEXTUALES.put("gotoSolicitudCuentaTitulo()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInicioMiPerfil()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoWebContentVideo()", CONOCE_MAS);
        REDIRECCIONES_CONTEXTUALES.put("gotoCentroDeAyuda()", VER_MAS);
        REDIRECCIONES_CONTEXTUALES.put("gotoModificarDatosPersonales()", "Modificar");
        REDIRECCIONES_CONTEXTUALES.put("gotoSolicitudChequera()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSolicitudCambioCtaOpeExterior()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSolicitudHabilitarTarjViajeExterior()", "Habilitar");
        REDIRECCIONES_CONTEXTUALES.put("gotoSolicitudCambioAfinidad()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSolicitudAumentoLimiteTransferencia()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSeguro()", CONSULTAR);        
        REDIRECCIONES_CONTEXTUALES.put("gotoCampaniaTurbo()", VER_MAS);
        REDIRECCIONES_CONTEXTUALES.put("gotoRecalificacionAdvance()", VER_MAS);
        REDIRECCIONES_CONTEXTUALES.put("gotoTestInversor()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoFormularioIMAUIF()", ADJUNTAR);		
        REDIRECCIONES_CONTEXTUALES.put("gotoSimulacionPrestamoTipo()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSimulacionPrestamoTipoFija()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSimulacionPrestamoTipoVariable()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSimulacionPrestamoTipoUva()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSimulacionPrestamoTipoMonoproducto()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSimulacionDePreaprobado()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSimulacionDePreacordado()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoRecambioChip()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoRecargaTransporte()", RECARGAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoCuentaSueldo()", CONOCE_MAS);
        REDIRECCIONES_CONTEXTUALES.put("gotoComprobantesTransacciones()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSolicitudTarjetas()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoFCIInformacionFondos()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSolicitudCajaAhorro()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoTransferenciasInicio()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSuscripcionMyA()", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoResumenesCuenta()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoResumenesTarjeta()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoTarjetasLimyDisponibles()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSolicCambioSuperClubAAdvantage()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoAAdvantageMillas()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoComprobantesInicio()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSuperClubDetallePuntos()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoTransferirNuevoDestinatario()", TRANSFERIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSeguroCotizarSolicitar()", COTIZAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoCuentasPaquetesSolicitarBajaProducto()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoDetalleCBUyAlias()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSolicitarTransferenciaExterior()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSuscripcionMyaCuentaChequeraDisponible()", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoPPIncumplida()", VER_MAS);
        REDIRECCIONES_CONTEXTUALES.put("gotoAvisoDeudaPP()", VER_OPCIONES);
        REDIRECCIONES_CONTEXTUALES.put("gotoChat()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoReferidos()", REFERIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoAdhesionWoman()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSantanderExpress()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoPrestamoSueldos()", PEDILO_AHORA);
        REDIRECCIONES_CONTEXTUALES.put("gotoTurnos()", TURNOS);
        REDIRECCIONES_CONTEXTUALES.put("gotoBlanqueoPin()", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoAdhesionPagoDebitoAutomatico()", ADHERIR);        
        REDIRECCIONES_CONTEXTUALES.put("gotoExtraccionSinTarjeta()", EXTRAER);
        REDIRECCIONES_CONTEXTUALES.put("gotoProductoSuperclub()", CANJE);
        REDIRECCIONES_CONTEXTUALES.put("gotoSolicitudGetnet()", ADHERIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoAceptarTerminosCondiciones()", VER_MAS);
        REDIRECCIONES_CONTEXTUALES.put(GO_TO_AVISO_RECUPERACIONES, FINANCIAR_TARJETA);
        REDIRECCIONES_CONTEXTUALES.put(GO_TO_AVISO_RECUPERACIONES_CURRENT, FINANCIAR_TARJETA);
        REDIRECCIONES_CONTEXTUALES.put("gotoEmisionEcheq()", EMITIR);
        
        REDIRECCIONES_CONTEXTUALES.put(AccionController.IR_SUPERCLUB_PREMIFY.getDescripcion(), "SuperClub+");
        REDIRECCIONES_CONTEXTUALES.put(AccionController.IR_TRACKING_SOLICITUDES.getDescripcion(), CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put(AccionController.IR_ORDEN_PAGO_EXTERIOR_MF.getDescripcion(), SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put(AccionController.IR_TRANSFERENCIAS_EXTERIOR_MF.getDescripcion(), SOLICITAR);
        
        REDIRECCIONES_CONTEXTUALES.put(AccionController.IR_EXTRACCION_SANTANDER_EXPRESS.getDescripcion(), EXTRAER);

        REDIRECCIONES_CONTEXTUALES.put("gotoFondosInicioRTL", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoFondosInicio()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInversionesFCIRTLSuscribir()", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInversionesFCIRTLSuscribir(007)", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInversionesFCIRTLSuscribir(082)", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInversionesFCIRTLSuscribir(072)", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInversionesFCIRTLSuscribir(002)", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInversionesFCIRTLSuscribir(034)", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInversionesFCIRTLSuscribir(031)", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInversionesFCIRTLSuscribir(110)", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInversionesFCIRTLSuscribir(118)", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInversionesFCIRTLSuscribir(001)", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInversionesFCIRTLSuscribir(023)", SUSCRIBIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoInicioServiciosDeInversion()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("goToServicioInversionRTLCard(AGD)", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("goToServicioInversionRTLCard(ACT)", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("goToServicioInversionRTLCard(PDC)", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("goToServicioInversionRTLCard(VMEP)", SOLICITAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoContactabilidad()", CONOCE_MAS);
        REDIRECCIONES_CONTEXTUALES.put("goToLicitaciones(RTL)", LICITAR);
        REDIRECCIONES_CONTEXTUALES.put("goToLicitaciones(BP)", LICITAR);
        REDIRECCIONES_CONTEXTUALES.put("goToCanjes(RTL)", CANJEAR);
        REDIRECCIONES_CONTEXTUALES.put("goToCanjes(BP)", CANJEAR);
        REDIRECCIONES_CONTEXTUALES.put("goToLicitar()", LICITAR);
        REDIRECCIONES_CONTEXTUALES.put("goToCanjear()", CANJEAR);
        REDIRECCIONES_CONTEXTUALES.put("goToMFETF()", OPERAR);
        REDIRECCIONES_CONTEXTUALES.put("goToMFETF(RTL)", OPERAR);
        REDIRECCIONES_CONTEXTUALES.put("goToMFETF(BP)", OPERAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoPoliticasPrivacidad()", VER_MAS);
        REDIRECCIONES_CONTEXTUALES.put("gotoReferidosCompartir()", REFERIR);
        REDIRECCIONES_CONTEXTUALES.put("gotoTurnos()", VER_MAS);
        REDIRECCIONES_CONTEXTUALES.put("gotoIdentidaddegenero()", VER_MAS);
        REDIRECCIONES_CONTEXTUALES.put("gotoIdentidadGenero()", VER_MAS);
        REDIRECCIONES_CONTEXTUALES.put("TyCgotoMasterBlack()", ACTIVAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoTyCDUO()", ACTIVAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoSegurosVivienda()", CONSULTAR);
        REDIRECCIONES_CONTEXTUALES.put("gotoAceptacionTerminosyCondiciones()", CONSULTAR);
    }

    /** The Constant REDIRECCIONES_CONTEXTUALES_WEBCONTENT. */
    private static final String REDIRECCIONES_CONTEXTUALES_WEBCONTENT = "gotoLanding";

    /** The Constant REDIRECCIONES_CONTEXTUALES_SEGURO. */
    private static final String REDIRECCIONES_CONTEXTUALES_SEGURO = "gotoSeguro";    
    /** The Constant REDIRECCIONES_CONTEXTUALES_CHAT. */
    private static final String REDIRECCIONES_CONTEXTUALES_CHAT = "gotoChat";  
    /** The Constant REDIRECCIONES_CONTEXTUALES_FORM_UMAUFI. */
    private static final String REDIRECCIONES_CONTEXTUALES_FORM_UMAUFI = "gotoFormularioIMAUIF";    
    /** The Constant REDIRECCIONES_CONTEXTUALES_LINK. */
    private static final String REDIRECCIONES_CONTEXTUALES_LINK = "gotoLink";
    /** The Constant REDIRECCIONES_CONTEXTUALES_WEBCONTENT_VIDEO. */
    private static final String REDIRECCIONES_CONTEXTUALES_WEBCONTENT_VIDEO = "gotoWebContentVideo";    
    /** The Constant TIPO_ACCESO_LINK_EXTERNO. */
    private static final String TIPO_ACCESO_LINK_EXTERNO = "E";

    /** The link. */
    String link;

    /** The parametros. */
    String parametros;

	private String linkLicitarCanjear;

    /**
     * Instantiates a new goto link.
     *
     * @param gotoLink
     *            the goto link
     */
    public GotoLink(String gotoLink) {
    	
        if (StringUtils.isNotBlank(gotoLink)) {

            String linkLimpio = gotoLink.trim().replaceFirst(";*$", "");
            String[] linkPartes = linkLimpio.split("\\(", 2);
            if (2 == linkPartes.length) {
                this.link = buscarRedireccionContextuales(linkPartes[0] + "()");
                if (this.link == null) {
                    this.link = buscarRedireccionContextuales(linkLimpio);
                }
                this.parametros = StringUtils.substring(linkPartes[1], 0, -1);
            }

            // Debido a que fondos tiene un comportamiento ambiguo, se agrega la siguiente lógica //
            if("gotoInversionesFCIRTLSuscribir".equals(linkPartes[0])) {
            	this.link = buscarRedireccionContextuales(linkLimpio);
            }
            
            if("goToLicitar".equals(linkPartes[0]) || "gotoCanjear".equals(linkPartes[0])) {
            	this.linkLicitarCanjear = linkLimpio;
            }

            if(linkLimpio.matches("^microfront-[a-z,A-Z]+$")){
                this.link = buscarRedireccionContextuales(linkLimpio);
            }
        }
    }

    /**
     * Buscar redireccion contextuales.
     *
     * @param redireccion
     *            the redireccion
     * @return the string
     */
    private String buscarRedireccionContextuales(String redireccion) {
    	String redireccionCorregida = quitarTildes(redireccion);
        if (REDIRECCIONES_CONTEXTUALES.containsKey(redireccionCorregida)) {
            return redireccionCorregida;
        }
        return null;
    }
    
    /**
     * Quitar tildes.
     *
     * @param redireccion the redireccion
     * @return the string
     */
    private String quitarTildes(String redireccion) {
    	
    	String redireccionCorregida;
    	
    	if (redireccion.contains("á")) {
    		redireccionCorregida = redireccion.replaceAll("á", "a");
    	} else if (redireccion.contains("é")) {
    		redireccionCorregida = redireccion.replaceAll("é", "e");
    	}else if (redireccion.contains("í")) {
    		redireccionCorregida = redireccion.replaceAll("í", "i");
    	} else if (redireccion.contains("ó")) {
    		redireccionCorregida = redireccion.replaceAll("ó", "o");
    	} else if (redireccion.contains("ú")) {
    		redireccionCorregida = redireccion.replaceAll("ú", "u");
    	} else {
    		redireccionCorregida = redireccion;
    	}
    	
    	return redireccionCorregida;
    }

    /**
     * Es web content.
     *
     * @return true, if successful
     */
    public boolean esWebContent() {
        return StringUtils.startsWith(this.link, REDIRECCIONES_CONTEXTUALES_WEBCONTENT) ||
        	   StringUtils.startsWith(this.link, "gotoContactabilidad");
    }
    
    /**
	 * Es seguro link.
	 *
	 * @return true, if successful
	 */
    public boolean esSeguroLink() {
        return StringUtils.startsWith(this.link, REDIRECCIONES_CONTEXTUALES_SEGURO);
    }
    

    /**
     * Es chat link.
     *
     * @return true, if successful
     */
    public boolean esChatLink() {
        return StringUtils.startsWith(this.link, REDIRECCIONES_CONTEXTUALES_CHAT);
    }
    
    /**
	 * Es form UMA-IFA link.
	 *
	 * @return true, if successful
	 */
    public boolean esFormUMAIFALink() {
        return StringUtils.startsWith(this.link, REDIRECCIONES_CONTEXTUALES_FORM_UMAUFI);
    }

    /**
     * Es link.
     *
     * @return true, if successful
     */
    public boolean esLink() {
        return StringUtils.startsWith(this.link, REDIRECCIONES_CONTEXTUALES_LINK);
    }

    /**
     * Es link video.
     *
     * @return true, if successful
     */
    public boolean esWebContentVideo() {
        return StringUtils.startsWith(this.link, REDIRECCIONES_CONTEXTUALES_WEBCONTENT_VIDEO);
    }

    /**
     * Contiene link. verifica si en los parametros, viene un gotoLink().
     *
     * @return the boolean
     */
    public Boolean contieneLink() {
        return StringUtils.contains(this.parametros, REDIRECCIONES_CONTEXTUALES_LINK);
    }

    /**
     * Obtener web content.
     *
     * @return the web content view
     */
    public WebContentView obtenerWebContent() {
        if ((esWebContent() || esWebContentVideo()) && StringUtils.isNotBlank(this.parametros)) {
            String[] valores = this.parametros.split(";");
            if (valores.length == 1) {
                WebContentView webContent = new WebContentView();
                webContent.setDescripcion(parametros);
                return webContent;
            } else if (3 == valores.length || valores.length == 4) {
                WebContentView webContent = new WebContentView();
                webContent.setTitulo(StringUtils.isNotBlank(valores[0]) ? valores[0].trim() : null);
                webContent.setDescripcion(StringUtils.isNotBlank(valores[1]) ? valores[1].trim() : null);
                GotoLink gotoLink = new GotoLink(valores[2]);
                webContent.setLink(gotoLink.getLink());
                webContent.setBoton(gotoLink.obtenerTexto());
                return webContent;
            }
        }
        return null;
    }

    /**
     * Obtener link content.
     *
     * @return the link view
     */
    public LinkView obtenerLinkContent() {
        if (esLink() && StringUtils.isNotBlank(this.parametros)) {
            String[] valores = this.parametros.split(";");
            if (valores.length == 2) {
                return new LinkView(valores[0], valores[1]);
            }
        }
        if (esWebContentConLink()) {
            String[] valores = this.parametros.split(REDIRECCIONES_CONTEXTUALES_LINK);
            if (valores.length == 2) {
                String[] valoresLink = valores[1].split(";");
                if (valoresLink.length == 2) {
                    return new LinkView(StringUtils.remove(valoresLink[0], "("),
                            StringUtils.remove(valoresLink[1], ")"));
                }
            }
        }
        if(esSeguroLink()){
            return new LinkView(StringUtils.remove(this.parametros, "\'"), null);
        }
        
        if(esChatLink()){
            return new LinkView(StringUtils.remove(this.parametros, "\'"), null);
        }
        
        if(esFormUMAIFALink()){        	
            return new LinkView(StringUtils.remove(this.parametros, "\'"), TIPO_ACCESO_LINK_EXTERNO);
        }
        
        return null;
    }

    /**
     * Es web content con link.
     *
     * @return the boolean
     */
    private Boolean esWebContentConLink() {
        return esWebContent() && contieneLink() && StringUtils.isNotBlank(this.parametros);
    }

    /**
     * Obtener texto.
     *
     * @return the string
     */
    public String obtenerTexto() {
        if (StringUtils.isNotBlank(this.link)) {
            return REDIRECCIONES_CONTEXTUALES.get(this.link);
        }
        return null;
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
     * Gets the parametros.
     *
     * @return the parametros
     */
    public String getParametros() {
        return parametros;
    }

    /**
     * Sets the parametros.
     *
     * @param parametros
     *            the new parametros
     */
    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

	public String getLinkLicitarCanjear() {
		return linkLicitarCanjear;
	}
}

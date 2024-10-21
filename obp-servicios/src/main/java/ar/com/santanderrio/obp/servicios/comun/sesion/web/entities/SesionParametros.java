/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sesion.web.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoResponse;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.EmpleadoPagoHaberes;
import ar.com.santanderrio.obp.servicios.transferencias.entities.DatosTransferenciaDestino;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.echeq.IMF;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaMsgMultiple;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.DatosComprobanteAumentoLimiteTransferencia;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.view.BonificacionVigenteView;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.ComprobanteSolicitudCambioAfinidadView;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatSessionEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.CredencialesClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntaValidacionTel;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntasSeguridad;
import ar.com.santanderrio.obp.servicios.clave.online.entities.VerificacionPasosClaveOnline;
import ar.com.santanderrio.obp.servicios.clientes.entities.OperadorEjecutivo;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ComprobanteComexDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaMonedaOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaConceptoOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ReporteView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DatosComprobanteDevolucionDA;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.Desafio;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.entity.ClasificacionDeuda;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.DatosComprobanteDebinRecurrente;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDetalleDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CuentasAdheridasOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.DatosSolicitudDebinDTO;
import ar.com.santanderrio.obp.servicios.debinws.view.GestionarAdhesionDebinesView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.DeleteAccountView;
import ar.com.santanderrio.obp.servicios.echeq.dto.ComprobanteECheqDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ECheqDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.DatosExtraccionYComprasExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.DatosComprobanteExtraccionEfectivoView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaPoliticaPrivacidadDTO;
import ar.com.santanderrio.obp.servicios.getnet.dto.GetnetAdhesionDTO;
import ar.com.santanderrio.obp.servicios.home.entitites.HomeDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorCuentaBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaProductosPorMonedaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaConTenenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaTitulosCuentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciasFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.AltaComprobantePlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ContenidoTenenciaBprivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.TenenciaPlazoFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListaAperturaEspecie;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListaFechasComprobantes;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ComprobanteOrdenVenta;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.ConfirmacionRecargaView;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.Factura;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.Pago;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudasPagoComprasEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoInformadoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPagoMisCuentas;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView;
import ar.com.santanderrio.obp.servicios.perfil.dto.CambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ModificacionCambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CalificacionCrediticiaDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CalificacionCrediticiaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamosPorCuenta;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobanteCancelacionTotalPrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoTasaSubsidiadaView;
import ar.com.santanderrio.obp.servicios.prestamos.view.LimitePrestamoPreaprobadoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamoPreaprobadoMonoproductoInOutView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ProximasCuotasView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ReportSimulacionPrestamosView;
import ar.com.santanderrio.obp.servicios.producto.view.ComprobanteBajaProductoView;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaRiesgoTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.solicitudes.view.AdhesionWomenView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.DatosTarjetaWomen;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.ComprobanteAltaTarjCredAdicionalView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.DatosComprobanteSolicitudTarjetaRecargableView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobantePagoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.DatosReimpresionComprobante;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaCandidataDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ComprobanteRecargaTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsultaFinanciacionView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetasView;
import ar.com.santanderrio.obp.servicios.todopago.web.view.ComprobanteAdhesionTodoPagoView;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.AbstractAccionTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.DestinatarioDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.CitaOutDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.MotivoTurnoDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.dto.SucursalesDTO;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.ComprobanteTurnoInEntity;

/**
 * The Class SesionParametros.
 *
 * @author B039636
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SesionParametros {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SesionParametros.class);

    /** The Constant RESETEO_RSA. */
    private static final String RESETEO_RSA = "Reseteando desafio en curso.";

    /** The Constant MSJ_INFO_VACIANDO_HASH_SESION. */
    private static final String MSJ_INFO_VACIANDO_HASH_SESION = "Vaciando hash de la sesion.";

    /** The Constant MONEDA. */
    private static final String MONEDA = "MONEDA";

    /** The Constant NROCUENTA. */
    private static final String NROCUENTA = "NROCUENTA";

    /** The registro session. */
    private RegistroSesion registroSession;

    /** The respuesta pagos pendientes. */
    private Respuesta<List<PagoPendiente>> respuestaPagosPendientes;

    /**
     * Lista de facturas para validar importes cuando vengo del action sheet de
     * nuevo pago.
     */
    private List<Factura> facturasPagosPendientes;

    /** The cuentas pago mis cuentas. */
    private List<CuentaPagoMisCuentas> cuentasPagoMisCuentas;

    /** The tarjeta recargable. */
    private ComprobanteRecargaTarjetaView comprobanteTarjetaRecargable;

    /** The tarjeta recargable. */
    private ComprobanteBajaProductoView comprobanteBajaProducto;

    /** The comprobante tarjeta cred adic. */
    private ComprobanteAltaTarjCredAdicionalView comprobanteTarjetaCredAdic;

    /** The res cuotas pendientes. */
    private Respuesta<CuotasPendientesView> resCuotasPendientes;

    /** The pago. */
    private Pago pago;

    /** The pago prestamo. */
    private PagoPrestamo pagoPrestamo;

    /** The rsa generic request data. */
    private RsaGenericRequestData rsaGenericRequestData = new RsaGenericRequestData();

    /** The rsa generic response data. */
    private RsaGenericResponseData rsaGenericResponseData = new RsaGenericResponseData();

    /** The rsa estado. */
    private Map<OperacionesRSAEnum, Boolean> rsaEstado;

    /** The RSA cantidad intentos. */
    private int rsaCantidadIntentos = 0;

    /** the RSA regla TIS. */
    private String reglaRsaTis;

    /** the RSA TIS analysis ok. */
    private boolean rsaTisAnalysisOk;

    /** The tipo desafio. */
    private TipoDesafioEnum tipoDesafio;

    /** desafio en proceso de autentificacion. */
    private Desafio<AutentificacionDTO> desafioEnCurso;

    /** The pago pendiente view. */
    // Controlar pagos de la grilla
    private PagoPendienteView pagoPendienteView;

    /** The contador. */
    private ContadorIntentos contador;

    /** The accion transferencia agendada. */
    private AbstractAccionTransferenciaAgendada<?> accionTransferenciaAgendada;

    /** The transferencias agendadas. */
    private List<TransferenciaAgendadaDTO> transferenciasAgendadas;

    /** The home DTO. */
    private HomeDTO homeDTO;

    /**  Lista de calificaciones crediticias DTO. */
    private List<CalificacionCrediticiaDTO> calificacionesCrediticiasDTO;
    
    /**  Lista de calificaciones crediticias. */
    private Map<Cuenta, CalificacionCrediticiaOutEntity> calificacionesCrediticias = new HashMap<Cuenta, CalificacionCrediticiaOutEntity>();

    /** The id ev. */
    private String idEv;

    /** The rsa en aprendizaje. */
    private boolean rsaEnAprendizaje = false;

    /** The credenciales mya. */
    private CredencialesMya credencialesMya;

    /** The credenciales clave online. */
    private CredencialesClaveOnline credencialesClaveOnline;

    /** The timer clave online. */
    private Date timerClaveOnline;

    /** The preguntas seguridad. */
    private PreguntasSeguridad preguntasSeguridad;

    /** TRUE si existe desafio en curso. */
    private boolean existeDesafioEnCurso = false;

    /**
     * Cuando una transferencia tiene riesgo alto guardamos el estado de rsa y su
     * riesgo.
     */
    private RsaRiesgoTransferenciaDTO rsaRiesgoTransferenciaDTO = null;

    /** The contador alias. */
    private ContadorIntentos contadorAlias;

    /** The ultimo acceso. */
    private Date ultimoAcceso;

    /** The primer acceso. */
    private boolean primerAcceso = true;

    /** datos para validar la operacion en curso. */
    private String validacionHash;

    /** datos para validar la operacion en curso en aumento de límite. */
    private String validacionHashTIS;

    /** nro Comprobante *. */
    private String nroComprobante;

    /** The os. */
    private String os;

    /** The acceso mobile. */
    private boolean accesoMobile = false;

    /** The contador pagina comprobantes. */
    private Integer contadorPaginaComprobantes;

    /** The contador pagina comprobantes. */
    private Integer contadorPaginaMovimientosBancaPrivada;

    /** The clasificacion deuda. */
    private ClasificacionDeuda clasificacionDeuda;

    /** The reasigna. */
    private String reasigna;

    /** The mya DTO out. */
    private MyaDTOOut myaDTOOut;

    /** The detalle comprobante view. */
    private DetalleComprobanteView detalleComprobanteView;

    /** The tipoOperacionComprobante. */
    private TipoOperacionComprobanteEnum tipoOperacionComprobante;

    /** The detalle CBU alias. */
    private DetalleCBUView detalleCBUAlias;

    /** The transferencia view. */
    private TransferenciaView transferenciaView;

    /** The ofertas comerciales. */
    private EventosComercialesDTO ofertasComerciales = new EventosComercialesDTO();

    /** The compra venta view. */
    private ComprobanteCompraVentaView compraVentaView;

    /** The datos reimpresion comprobante. */
    private DatosReimpresionComprobante datosReimpresionComprobante;

    /** The nuevo pago view. */
    private NuevoPago nuevoPagoView;

    /** The lista pagos multiples. */
    private List<PagoMultipleView> listaPagosMultiples;

    /** The lista pagos multiples view. */
    private List<PagoMultipleView> listaPagosMultiplesView;

    /** The tipo nuevo pago. */
    private String tipoNuevoPago;

    /** The consulta financiacion. */
    private ConsultaFinanciacionView consultaFinanciacion;

    /** The tarjetas view. */
    private TarjetasView tarjetasView;

    /** The is calificado modulo desc cheques. */
    private Boolean isCalificadoModuloDescCheques;

    /** The is calificado. */
    private Boolean isCalificado;

    /** The detalle sesion chat. */
    private ChatSessionEntity detalleSesionChat;
	
	/** The ingreso desde goto. */
	private Boolean ingresoDesdeGoto = Boolean.FALSE;
    
    /** The numero tarjeta para goto limites. */
    private String numeroTarjetaParaGotoLimites;
    
    /** The compra dolares impcarg. */
    private String compraDolaresImpcarg;

    /**
     * The cuenta seleccionada para transferencia. Usado en la solicitud de
     * transferencia @see TransferecniaManager. Sirve para controlar la consulta de
     * titular CBU guardando el nro de cuenta y la moneda para casos de cuenta
     * unica @see CNSTITCBU. Para evitar/independizarse del uso de favorita.
     */
    private HashMap<String, String> cuentaSeleccionadaParaTransferencia = new HashMap<String, String>();

    /** The destinatario view. */
    private DestinatarioDTO destinatarioView;

    /** Para distinguir cual flujo se inicia en transferencia. */
    private boolean isFromAgendaDestinatario;

    /**
     * Domicilios registrador del cliente para poder hacer cambios y obtener
     * informacion adicional.
     */
    private List<CambioDomicilioDTO> domiciliosCliente;

    /** The domicilio modificado. */
    private ModificacionCambioDomicilioDTO domicilioModificado;

    /**
     * Guardar el dato sin normalizar por si merlin devuelve lista de domicilios y no poseen cp.
     */
    private CambioDomicilioView datosMerlinSinNormalizar;
    
    /** Pago Haberes Informado View. */
    private List<PagoInformadoView> pagoHaberesInformadosView;

    /*** Pago Tarjeta View. */
    private PagoTarjetaCreditoView pagoTarjetaView;

    /*** Pago Tarjeta View. */
    private ComprobantePagoTarjeta comprobantePagoTarjeta;

    /** The comprobante recarga. */
    private ConfirmacionRecargaView comprobanteRecarga;

    /** Tenencias Fondo DTO para obtener graficos fondos. */
    private TenenciasFondoDTO tenenciasFondoDTO;

    /** The comprobanteAdhesionTodoPagoView. */
    private ComprobanteAdhesionTodoPagoView comprobanteAdhesionTodoPagoView;

    /** The datos comprobante solicitud tarjeta recargable view. */
    private DatosComprobanteSolicitudTarjetaRecargableView datosComprobanteSolicitudTarjetaRecargableView;

    /** The datos extraccion Y compras exterior. */
    private DatosExtraccionYComprasExteriorDTO datosExtraccionYComprasExterior;

    /** DatosComprobanteAumentoLimiteTransferencia. */
    private DatosComprobanteAumentoLimiteTransferencia datosComprobanteAumentoLimiteTransferencia;

    /** Comprobante CNV Licitaciones. */
    private String comprobanteCNVLicitaciones;

    /**
     * Permite que en los flujos intermedios del login que no tengan un token se
     * pueda solo liberar la session sin otras operaciones.
     */
    private Boolean jwtTokenActivo = Boolean.FALSE;

    /** ListaMsgMultiplies. */
    private Map<String, List<MyaMsgMultiple>> listaMsgMultiples;

    /** The fecha hora pago. */
    private String fechaHoraPago;

    /** Tarjetas candidatas de reimpresion. */
    private List<TarjetaCandidataDTO> tarjetasCandidatasReimpresion;

    /** The Comprobante orden venta. */
    private ComprobanteOrdenVenta comprobanteOrdenVenta;

    /**
     * Se agrega la marca para indicar que mientras convivan obp y tbanco ante
     * cierta condicion no se debe consultar la tabla e ir directo a tbanco.
     */
    private Boolean ignoraTablaTbanco = Boolean.FALSE;

    /** The numero socio aadvantage. */
    private String numeroSocioAadvantage;

    /** The solucitud cambio afinidad realizada. */
    private Boolean solucitudCambioAfinidadRealizada;

    /** The subproducto paquete. */
    private String subproductoPaquete;

    /** The comprobante solicitud cambio afinidad view. */
    private ComprobanteSolicitudCambioAfinidadView comprobanteSolicitudCambioAfinidadView;

    /** The alta Comprobante Plazo Fijo DTO. */
    private AltaComprobantePlazoFijoDTO altaComprobantePlazoFijoDTO;

    /** The comprobante json. */
    private String comprobanteJson = null;

    /** The contador pagina millas aadvantage. */
    private Integer contadorPaginaMillasAadvantage;

    /** Nro de pagina de debines iniciados. */
    private int nroPaginaDebinIniciado = 1;

    /** Nro de pagina de debines acreditados. */
    private int nroPaginaDebinAcreditado = 1;

    /** Nro de pagina de debines estado usuario. */
    private int nroPaginaDebinEstadoUsuario = 1;

    /** Nro de pagina de lista recurrencia preautorizaciones. */
    private int nroPaginaDebinListaRecurrencia = 1;
  
    /** Lista para guardar id debines o id recurrencias. */
    private List<String> idDebinesRecurrencias = null;
  
    /** Detalle debin. */
    private ConsultaDetalleDebinWSOutDTO detalleDebin;

	/** Id Cuentas Debin. */
	private Map<Integer, String> idCuentasDebin;

    /** The cita out DTO. */
    private CitaOutDTO citaOutDTO;

    /** The id simulaicon pdc. */
    private String idSimulacionPDC;

    /** The en simulacion tasas. */
    private Boolean enSimulacionTasas = Boolean.FALSE;

    /** The report simulacion. */
    private ReportSimulacionPrestamosView reportSimulacion;

    /** The comex monedas. */
    private Map<String, ConsultaMonedaOutDTO> monedasComexMap;

    /** consulta consulta operaciones out view. */
    private ConsultaOperacionesOutView consultaOperacionesOutView;

    /** The archivos. */
    private List<ReporteView> archivos = new ArrayList<ReporteView>();

    /** cantidad de paginas detalle comex. */
    private int cantPaginasDetalleComex;

    /** Nro de pagina comex detalle. */
    private int nroPaginaComexDetalle = 1;

    /** The sucursales turnos web. */
    private List<SucursalesDTO> sucursalesTurnosWeb;

    /** The cuentas comex. */
    private List<ResumenDetalleCuenta> cuentasComex;

    /** The is licitacion B priv. */
    private Boolean isLicitacionBPriv = Boolean.FALSE;

    /** The lista apertura especie. */
    private ListaAperturaEspecie listaAperturaEspecie;

    /**
	 * id de ultima busqueda realizada de informacion mercado, utilizado para
	 * determinar limpieza de cache.
	 */
    private String idAgrupamientoInformacionMercado;
    
    /** The proximas cuotas view. */
    private ProximasCuotasView proximasCuotasView;
    
    /** The info plazo fijo. */
    private TenenciaPlazoFijoView infoPlazoFijo;
    
    /** The lista tenencias por cuenta. */
    private List<TenenciaTitulosCuentaDTO> listaTenenciasPorCuenta;
    
    /** The lista tenencia consolidada RTL. */
    private List<TenenciaProductosPorMonedaView> listaTenenciaConsolidadaRTL;
    
    /** The lista cuentas fondos. */
    private List<CuentaConTenenciaDTO> listaCuentasFondos;
    
    /** The lista cuentas fondos BP. */
    private List<CuentaConTenenciaDTO> listaCuentasFondosBP;
    
    /** The lista plazos fijos BP. */
    private List<ContenidoTenenciaBprivDTO> listaPlazosFijosBP;
    
    /** The lista tenencia consolidada BP. */
    private List<TenenciaPorCuentaBPrivDTO> listaTenenciaConsolidadaBP;
    
    /** The lista tenencias por cuenta BP. */
    private List<TenenciaTitulosCuentaDTO> listaTenenciasPorCuentaBP;
    
    /** The prestamos por cuenta. */
    private List<PrestamosPorCuenta> prestamosPorCuenta;
    
    /** The lista detalle comprobante view. */
    private List<DetalleComprobanteView> listaDetalleComprobanteView;
    
    /** The consulta concepto out view. */
    private ConsultaConceptoOutView consultaConceptoOutView;
    
    /** The id E cheqs. */
    private List<String> idECheqs;

    /** The nro pagina echeq. */
    private int nroPaginaEcheq = 1;

    /** The echeq estado validado. */
    private ECheqDTO echeqEstadoValidado;
    
    /** The cuentas habilitadas alta echeq. */
    private List<Cuenta> cuentasHabilitadasAltaEcheq;
    
    /** The comprobante echeq DTO. */
    private ComprobanteECheqDTO comprobanteEcheqDTO;

 	/** The lista fechas comprobantes TV bpriv. */
	 private ListaFechasComprobantes listaFechasComprobantesTVBpriv;
	
	/** The lista fechas comprobantes FCI bpriv. */
	private ListaFechasComprobantes listaFechasComprobantesFCIBpriv;
    
    /** The tipo banca plazo fijo. */
    private String tipoBancaPlazoFijo;

    /** The id notificacion promesa pago. */
    private String idNotificacionPromesaPago;
    
    /** The lista pagos vencidos productos santander. */
    private List<PagoPendienteView> listaPagosVencidosProductosSantander = new ArrayList<PagoPendienteView>();
	
    /** The operacion para token. */
    private String operacionParaToken;
    
    /** The habilitacion women. */
    private String habilitacionWomen;
    
    /** The tarjetas posibles habilitacion. */
    private List<DatosTarjetaWomen> tarjetasPosiblesHabilitacion;

    /** The tarjetas habilitadas women. */
    private AdhesionWomenView tarjetasHabilitadasWomen;

    /** The motivos turno. */
    private Map<String, List<MotivoTurnoDTO>> motivosTurno;

    /** The cuentas inversiones PF bpriv. */
    List<CuentaTituloView> cuentasInversionesPFBpriv = new ArrayList<CuentaTituloView>();
    
    
    /** The respuesta detalle CNSCTADATO. */
    private List<ResumenDetalleCuenta> respuestaDetalleCNSCTADATO = new ArrayList<ResumenDetalleCuenta>();
    
    /** The datos solicitante. */
    private DatosSolicitanteEntity datosSolicitante;
    
    /** The estado getnet. */
    private String estadoGetNet;
    
    /** The email getnet. */
    private String emailGetNet;
    
    /** The cuentas habilitadas getnet. */
    private List<Cuenta> cuentasHabilitadasGetnet;
    
	/** The ACTADHECNL_isExpuestoPoliticamente. */
    private Boolean ACTADHECNL_isExpuestoPoliticamente;
    
    /** The CNSINFIMPO_categorizacionIva. */
    private String CNSINFIMPO_categorizacionIva;
    
    /**  The CNSINFIMPO_categorizacionIibb. */
    private String CNSINFIMPO_categorizacionIibb;
    
    /** The CNSINFIMPO_numeroIibb. */
    private String CNSINFIMPO_numeroIibb;
    
    /** The contador. */
    private ContadorIntentos contadorAdhesionGetnet;
	
    /** The comprobante getnet dto. */
    private GetnetAdhesionDTO comprobanteGetnetDTO;
    
    /** The getnet Agregado. */
    private Boolean getnetAgregado = false;
    
    /** The recorrio Cuentas Getnet. */
    private Boolean recorrioCuentasGetnet = false;
    
    /** The legal TYC Getnet. */
    private String legalTycGetnet;
    
    /** listadoIMF */
    private List<IMF> listadoIMF;
    
    
    /** The datos comprobante extraccion efectivo view. */
    private DatosComprobanteExtraccionEfectivoView datosComprobanteExtraccionEfectivoView;
    
    /** The estadistica OK descarga PDF extraccion efectivo grabada. */
    private Boolean estadisticaOKDescargaPDFExtraccionEfectivoGrabada = Boolean.FALSE;

    /** The respuesta numero empresa celular clave online. */
    private String respuestaNumeroEmpresaCelularClaveOnline;
    
    private String empresaCelularServicioIdesgiclie;
    
    private DatosComprobanteDevolucionDA datosComprobanteDevolucionDA;
    
    /** Datos del comprobante de prestamo a tasa subsidiada*/
    private ComprobantePrestamoTasaSubsidiadaView comprobantePrestamoTasaSub;
    
    private BigDecimal maxOfertaPrestamoPreaprobado;
    
    /** Limites de ofertas de prestamos preaprobados*/
    private List<LimitePrestamoPreaprobadoView> limitesPreaprobado;
    
    /** Prestamo preaprobado - datos de simulacion*/
    private PrestamoPreaprobadoMonoproductoInOutView prestamoPreaprobadoMonoproducto;
    
    private ComprobanteCancelacionTotalPrestamoView comprobanteCancelacionTotalPrestamo;
    
    private String tipoDePrestamoATP;
    
    /** Variable que indica si el usuario logueado está participando en el nuevo programa de superclub +  **/
    private Boolean participaEnSuperclubPlus;
    
    private OperadorEjecutivo operadorEjecutivo;

    /**
     * Importe acumulado mensual en dolares - cliente vende y banco compra 
     */
    private String importeUSDAcumCpr;
    
    /** Indica si el usuario visualiza opciones de refi tradicional*/
    private Boolean aplicaRefi;
    
    /** Datos para completar el pdf de baja */
    private DeleteAccountView pdfData;
    
    /** Datos para completar el pdf */
    private String descCtaAEliminar;

    private DatosComprobanteDebinRecurrente datosComprobanteDebinRecurrente;

    /** The lista claves json. */
    private String listaClavesJson = null;

    /** The cesion cheques. */
    private String cesionCheques = null;

    /** The cuenta habilitada. */
    private String cuentaHabilitada;

    /** The id cumplimiento venta titulos. */
    private Long idCumplimientoVentaTitulos;

    /** The legal adhesion PDC. */
    private String legalAdhesionPDC;

    /** The fallo legales. */
    private Boolean falloLegales;

    /** datos de solicitud de debin. */
    private DatosSolicitudDebinDTO datosSolicitudDebin;

    /** cuentas del cliente adheridas a debin. */
    private CuentasAdheridasOutDTO cuentasAdheridasDebin;

    /** comprobante de nueva transferencia exterior. */
    private ComprobanteComexDTO comprobanteComex;

    /** The deudas pago compras entity. */
    private DeudasPagoComprasEntity deudasPagoComprasEntity;
    
    /** comprobante de PP is uva. */
    private boolean comprobantePPPIsUva;

    /** The busqueda default cincuenta Y tres dias RTL. */
    private Boolean busquedaDefaultCincuentaYTresDiasRTL = Boolean.FALSE;

    /** The busqueda default cincuenta Y tres dias BP. */
    private Boolean busquedaDefaultCincuentaYTresDiasBP = Boolean.FALSE;

	/** ThePreguntaValidacionTel. */
	private PreguntaValidacionTel preguntaValidacionTel = new PreguntaValidacionTel();
	
	/** verificacionPasosClaveOnline. */
	private VerificacionPasosClaveOnline verificacionPasosClaveOnline;
	
    /**  gestionar adhesion debines view. */
	 private GestionarAdhesionDebinesView gestionarAdhesionDebinesView;

	/** The contador. */
    private ContadorIntentos contadorReemplazoTarjetas;
    
    /** The ofertaRecambioChip. */
    private OfertaComercialDTO ofertaRecambioChip;
    private String transactionTokenRedemptionFund;
    
    /** The check whatsapp. */
    private Boolean checkWhatsapp;
    
    /** The acceso women deeplink. */
    private Boolean accesoWomenDeeplink = Boolean.FALSE;

    /** Dato si es cliente PAS **/
    private Boolean isPas;
    
    private String datosPrestamoSueldosTasaSubsidiada;

    private String jwt;
    
    private Respuesta<List<BonificacionVigenteView>> listaBonificaciones;
    
    private ComprobanteTurnoInEntity comprobanteTurno;

    private Map<String, Boolean> habilitadoFF = new HashMap<String, Boolean>();

    private Boolean desafioPrestamosActivo = false;
    
    private String numeroTarjetaDebitoAValidar;
    
    private int numeroIntentoValidarPinBanelco;

    private Boolean contactabilidadAceptada = false;

    private Boolean bajaCuentaWarning = false;

    private String mensajeInformativo = "";

    private OfertaPoliticaPrivacidadDTO ofertaPoliticaPrivacidad;
    
    private Boolean primerIngreso = true;
    
    private boolean errorSaldo = false;
    
    private Boolean permisoGrabadoEstadistica;
    
    private boolean permisoApiAuth = false;

    private String csid;

    private CambioDatosContactoResponse cambioDatosContactoResponse;

    private Integer cantidadIntentosSMS = 0;

    private Boolean existeClienteTyC = Boolean.FALSE;

    private DatosTransferenciaDestino datosTransferenciaDestino = new DatosTransferenciaDestino();

    private final List<EmpleadoPagoHaberes> empleadoPagoHaberesList = new ArrayList<EmpleadoPagoHaberes>();
    
    private String barrioNormalizacionDomicilio;

    public List<EmpleadoPagoHaberes> getEmpleadoPagoHaberesList() {
        return empleadoPagoHaberesList;
    }

    public DatosTransferenciaDestino getDatosTransferenciaDestino() {
        return datosTransferenciaDestino;
    }

    public String getCsid() {
        return csid;
    }

    public void setCsid(String csid) {
        this.csid = csid;
    }

    /**
     * Gets the check whatsapp.
     *
     * @return the check whatsapp
     */
    public Boolean getCheckWhatsapp() {
		return checkWhatsapp;
	}

	/**
	 * Sets the check whatsapp.
	 *
	 * @param checkWhatsapp the new check whatsapp
	 */
	public void setCheckWhatsapp(Boolean checkWhatsapp) {
		this.checkWhatsapp = checkWhatsapp;
	}
    
    /** The cantidad cuentas titulos canje. */
    private int cantidadCuentasTitulosCanje;

	private Double disponiblePrestamos;
    
    /**
     * Gets the datos solicitud debin.
     *
     * @return the datosSolicitudDebin
     */
    public DatosSolicitudDebinDTO getDatosSolicitudDebin() {
        return datosSolicitudDebin;
    }

    /**
     * Sets the datos solicitud debin.
     *
     * @param datosSolicitudDebin
     *            the datosSolicitudDebin to set
     */
    public void setDatosSolicitudDebin(DatosSolicitudDebinDTO datosSolicitudDebin) {
        this.datosSolicitudDebin = datosSolicitudDebin;
    }

    /**
     * Gets the cuentas adheridas debin.
     *
     * @return the cuentasAdheridasDebin
     */
    public CuentasAdheridasOutDTO getCuentasAdheridasDebin() {
        return cuentasAdheridasDebin;
    }

    /**
     * Sets the cuentas adheridas debin.
     *
     * @param cuentasAdheridasDebin
     *            the cuentasAdheridasDebin to set
     */
    public void setCuentasAdheridasDebin(CuentasAdheridasOutDTO cuentasAdheridasDebin) {
        this.cuentasAdheridasDebin = cuentasAdheridasDebin;
    }

    /**
     * Gets the numero socio aadvantage.
     *
     * @return the numero socio aadvantage
     */
    public String getNumeroSocioAadvantage() {
        return numeroSocioAadvantage;
    }

    /**
     * Sets the numero socio aadvantage.
     *
     * @param numeroSocioAadvantage
     *            the new numero socio aadvantage
     */
    public void setNumeroSocioAadvantage(String numeroSocioAadvantage) {
        this.numeroSocioAadvantage = numeroSocioAadvantage;
    }


    /**
     * Obtengo las calificaciones crediticias.
     *
     * @return the calificaciones crediticias DTO
     */
    public List<CalificacionCrediticiaDTO> getCalificacionesCrediticiasDTO() {
		return calificacionesCrediticiasDTO;
	}

    /**
     * seteo las calificacines crediticias.
     *
     * @param calificacionesCrediticiasDTO the new calificaciones crediticias DTO
     */
	public void setCalificacionesCrediticiasDTO(List<CalificacionCrediticiaDTO> calificacionesCrediticiasDTO) {
		this.calificacionesCrediticiasDTO = calificacionesCrediticiasDTO;
	}

	/**
     * Gets the transferencias agendadas.
     *
     * @return the transferenciasAgendadas
     */
    public List<TransferenciaAgendadaDTO> getTransferenciasAgendadas() {
        return transferenciasAgendadas;
    }

    /**
     * Sets the transferencias agendadas.
     *
     * @param transferenciasAgendadas
     *            the transferenciasAgendadas to set
     */
    public void setTransferenciasAgendadas(List<TransferenciaAgendadaDTO> transferenciasAgendadas) {
        this.transferenciasAgendadas = transferenciasAgendadas;
    }

    /**
     * Gets the rsa estado.
     *
     * @return the rsaEstado
     */
    public Map<OperacionesRSAEnum, Boolean> getRsaEstado() {
        return rsaEstado;
    }

    /**
     * Sets the rsa estado.
     *
     * @param rsaEstado
     *            the rsaEstado to set
     */
    public void setRsaEstado(Map<OperacionesRSAEnum, Boolean> rsaEstado) {
        this.rsaEstado = rsaEstado;
    }

    /**
     * Gets the registro session.
     *
     * @return the registroSession
     */
    public RegistroSesion getRegistroSession() {
        return registroSession;
    }

    /**
     * Setter para registro session.
     *
     * @param registroSession
     *            the registroSession to set
     */
    public void setRegistroSession(RegistroSesion registroSession) {
        this.registroSession = registroSession;
    }

    /**
     * Gets the respuesta pagos pendientes.
     *
     * @return the respuestaPagosPendientes
     */
    public Respuesta<List<PagoPendiente>> getRespuestaPagosPendientes() {
        return respuestaPagosPendientes;
    }

    /**
     * Gets the facturas pagos pendientes.
     *
     * @return the facturas pagos pendientes
     */
    public List<Factura> getFacturasPagosPendientes() {
        return facturasPagosPendientes;
    }

    /**
     * Sets the facturas pagos pendientes.
     *
     * @param facturasPagosPendientes
     *            the new facturas pagos pendientes
     */
    public void setFacturasPagosPendientes(List<Factura> facturasPagosPendientes) {
        this.facturasPagosPendientes = facturasPagosPendientes;
    }

    /**
     * Sets the respuesta pagos pendientes.
     *
     * @param respuestaPagosPendientes
     *            the respuestaPagosPendientes to set
     */
    public void setRespuestaPagosPendientes(Respuesta<List<PagoPendiente>> respuestaPagosPendientes) {
        this.respuestaPagosPendientes = respuestaPagosPendientes;
    }

    /**
     * Gets the cuentas pago mis cuentas.
     *
     * @return the cuentasPagoMisCuentas
     */
    public List<CuentaPagoMisCuentas> getCuentasPagoMisCuentas() {
        return cuentasPagoMisCuentas;
    }

    /**
     * Sets the cuentas pago mis cuentas.
     *
     * @param cuentasPagoMisCuentas
     *            the cuentasPagoMisCuentas to set
     */
    public void setCuentasPagoMisCuentas(List<CuentaPagoMisCuentas> cuentasPagoMisCuentas) {
        this.cuentasPagoMisCuentas = cuentasPagoMisCuentas;
    }

    /**
     * Gets the rsa generic request data.
     *
     * @return the rsa generic request data
     */
    public RsaGenericRequestData getRsaGenericRequestData() {
        return rsaGenericRequestData;
    }

    /**
     * Sets the rsa generic request data.
     *
     * @param rsaGenericRequestData
     *            the new rsa generic request data
     */
    public void setRsaGenericRequestData(RsaGenericRequestData rsaGenericRequestData) {
        this.rsaGenericRequestData = rsaGenericRequestData;
    }

    /**
     * Gets the rsa generic response data.
     *
     * @return the rsa generic response data
     */
    public RsaGenericResponseData getRsaGenericResponseData() {
        return rsaGenericResponseData;
    }

    /**
     * Sets the rsa generic response data.
     *
     * @param rsaGenericResponseData
     *            the new rsa generic response data
     */
    public void setRsaGenericResponseData(RsaGenericResponseData rsaGenericResponseData) {
        this.rsaGenericResponseData = rsaGenericResponseData;
    }

    /**
     * Gets the pago prestamo.
     *
     * @return the pago prestamo
     */
    public PagoPrestamo getPagoPrestamo() {
        return pagoPrestamo;
    }

    /**
     * Sets the pago prestamo.
     *
     * @param pagoPrestamo
     *            the new pago prestamo
     */
    public void setPagoPrestamo(PagoPrestamo pagoPrestamo) {
        this.pagoPrestamo = pagoPrestamo;
    }

    /**
     * Gets the RSA cantidad intentos.
     *
     * @return the rSACantidadIntentos
     */
    public int getRSACantidadIntentos() {
        return this.rsaCantidadIntentos;
    }

    /**
     * Sets the RSA cantidad intentos.
     *
     * @param rSACantidadIntentos
     *            the rSACantidadIntentos to set
     */
    public void setRSACantidadIntentos(int rSACantidadIntentos) {
        this.rsaCantidadIntentos = rSACantidadIntentos;
    }

    public String getReglaRsaTis() {
        return reglaRsaTis;
    }

    public void setReglaRsaTis(String reglaRsaTis) {
        this.reglaRsaTis = reglaRsaTis;
    }

    public boolean isRsaTisAnalysisOk() {
        return rsaTisAnalysisOk;
    }

    public void setRsaTisAnalysisOk(boolean rsaTisAnalysisOk) {
        this.rsaTisAnalysisOk = rsaTisAnalysisOk;
    }

    /**
     * Gets the tipo desafio.
     *
     * @return the tipoDesafio
     */
    public TipoDesafioEnum getTipoDesafio() {
        return tipoDesafio;
    }

    /**
     * Sets the tipo desafio.
     *
     * @param tipoDesafio
     *            the tipoDesafio to set
     */
    public void setTipoDesafio(TipoDesafioEnum tipoDesafio) {
        this.tipoDesafio = tipoDesafio;
    }

    /**
     * Gets the pago pendiente view.
     *
     * @return the pago pendiente view
     */
    public PagoPendienteView getPagoPendienteView() {
        return pagoPendienteView;
    }

    /**
     * Sets the pago pendiente view.
     *
     * @param pagoPendienteView
     *            the new pago pendiente view
     */
    public void setPagoPendienteView(PagoPendienteView pagoPendienteView) {
        this.pagoPendienteView = pagoPendienteView;
    }

    /**
     * Gets the contador.
     *
     * @return the contador
     */
    public ContadorIntentos getContador() {
        return contador;
    }

    /**
     * Sets the contador.
     *
     * @param contador
     *            the contador to set
     */
    public void setContador(ContadorIntentos contador) {
        this.contador = contador;
    }

    /**
     * Gets the accion transferencia agendada.
     *
     * @return the accionTransferenciaAgendada
     */
    public AbstractAccionTransferenciaAgendada<?> getAccionTransferenciaAgendada() {
        return accionTransferenciaAgendada;
    }

    /**
     * Sets the accion transferencia agendada.
     *
     * @param accionTransferenciaAgendada
     *            the accionTransferenciaAgendada to set
     */
    public void setAccionTransferenciaAgendada(AbstractAccionTransferenciaAgendada<?> accionTransferenciaAgendada) {
        this.accionTransferenciaAgendada = accionTransferenciaAgendada;
    }

    /**
     * Gets the desafio en curso.
     *
     * @return the desafio en curso
     */
    public Desafio<AutentificacionDTO> getDesafioEnCurso() {
        return desafioEnCurso;
    }

    /**
     * Sets the desafio en curso.
     *
     * @param desafioEnCurso
     *            the new desafio en curso
     */
    public void setDesafioEnCurso(Desafio<AutentificacionDTO> desafioEnCurso) {
        this.desafioEnCurso = desafioEnCurso;
    }

    /**
     * Gets the home DTO.
     *
     * @return the homeDTO
     */
    public HomeDTO getHomeDTO() {
        return homeDTO;
    }

    /**
     * Sets the home DTO.
     *
     * @param homeDTO
     *            the homeDTO to set
     */
    public void setHomeDTO(HomeDTO homeDTO) {
        this.homeDTO = homeDTO;
    }

    /**
     * Gets the id ev.
     *
     * @return the id ev
     */
    public String getIdEv() {
        return idEv;
    }

    /**
     * Sets the id ev.
     *
     * @param idEv
     *            the new id ev
     */
    public void setIdEv(String idEv) {
        this.idEv = idEv;
    }

    /**
     * Checks if is rsa se encuentra en modo aprendizaje.
     *
     * @return true, if is rsa en aprendizaje
     */
    public boolean isRsaEnAprendizaje() {
        return rsaEnAprendizaje;
    }

    /**
     * Sets the rsa en aprendizaje.
     *
     * @param rsaEnAprendizaje
     *            the new rsa en aprendizaje
     */
    public void setRsaEnAprendizaje(boolean rsaEnAprendizaje) {
        this.rsaEnAprendizaje = rsaEnAprendizaje;
    }

    /**
     * Gets the credenciales mya.
     *
     * @return the credenciales mya
     */
    public CredencialesMya getCredencialesMya() {
        return credencialesMya;
    }

    /**
     * Sets the credenciales mya.
     *
     * @param credencialesMya
     *            the new credenciales mya
     */
    public void setCredencialesMya(CredencialesMya credencialesMya) {
        this.credencialesMya = credencialesMya;
    }

    /**
     * Gets the pago.
     *
     * @return the pago
     */
    public Pago getPago() {
        return pago;
    }

    /**
     * Sets the pago.
     *
     * @param pago
     *            the new pago
     */
    public void setPago(Pago pago) {
        this.pago = pago;
    }

    /**
     * Gets the credenciales clave online.
     *
     * @return the credenciales clave online
     */
    public CredencialesClaveOnline getCredencialesClaveOnline() {
        return credencialesClaveOnline;
    }

    /**
     * Sets the credenciales clave online.
     *
     * @param credencialesClaveOnline
     *            the new credenciales clave online
     */
    public void setCredencialesClaveOnline(CredencialesClaveOnline credencialesClaveOnline) {
        this.credencialesClaveOnline = credencialesClaveOnline;
    }

    /**
     * Gets the preguntas seguridad.
     *
     * @return the preguntasSeguridad
     */
    public PreguntasSeguridad getPreguntasSeguridad() {
        return preguntasSeguridad;
    }

    /**
     * Sets the preguntas seguridad.
     *
     * @param preguntasSeguridad
     *            the preguntasSeguridad to set
     */
    public void setPreguntasSeguridad(PreguntasSeguridad preguntasSeguridad) {
        this.preguntasSeguridad = preguntasSeguridad;
    }

    /**
     * Gets the timer clave online.
     *
     * @return the timer clave online
     */
    public Date getTimerClaveOnline() {
        return timerClaveOnline;
    }

    /**
     * Sets the timer clave online.
     *
     * @param timerClaveOnline
     *            the new timer clave online
     */
    public void setTimerClaveOnline(Date timerClaveOnline) {
        this.timerClaveOnline = timerClaveOnline;
    }

    /**
     * Gets the rsa cantidad intentos.
     *
     * @return the rsa cantidad intentos
     */
    public int getRsaCantidadIntentos() {
        return rsaCantidadIntentos;
    }

    /**
     * Sets the rsa cantidad intentos.
     *
     * @param rsaCantidadIntentos
     *            the new rsa cantidad intentos
     */
    public void setRsaCantidadIntentos(int rsaCantidadIntentos) {
        this.rsaCantidadIntentos = rsaCantidadIntentos;
    }

    /**
     * Checks if is existe desafio en curso.
     *
     * @return true, if is existe desafio en curso
     */
    public boolean isExisteDesafioEnCurso() {
        return existeDesafioEnCurso;
    }

    /**
     * Sets the existe desafio en curso.
     *
     * @param existeDesafioEnCurso
     *            the new existe desafio en curso
     */
    public void setExisteDesafioEnCurso(boolean existeDesafioEnCurso) {
        this.existeDesafioEnCurso = existeDesafioEnCurso;
    }

    /**
     * Gets the contador alias.
     *
     * @return the contador alias
     */
    public ContadorIntentos getContadorAlias() {
        return contadorAlias;
    }

    /**
     * Sets the contador alias.
     *
     * @param contadorAlias
     *            the new contador alias
     * @deprecated cuando se requiere mas de un nivel de contadores invocar a
     *             {@link ContadorIntentos#gestionarContador(@link
     *             ContadorIntentosEnum)}.
     */
    public void setContadorAlias(ContadorIntentos contadorAlias) {
        this.contadorAlias = contadorAlias;
    }

    /**
     * Gets the os.
     *
     * @return the os
     */
    public String getOS() {
        return os;
    }

    /**
     * Sets the os.
     *
     * @param oS
     *            the new os
     */
    public void setOS(String oS) {
        os = oS;
    }

    /**
     * Gets the ultimo acceso.
     *
     * @return the ultimo acceso
     */
    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    /**
     * Sets the ultimo acceso.
     *
     * @param ultimoAcceso
     *            the new ultimo acceso
     */
    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    /**
     * Gets the primer acceso.
     *
     * @return the primer acceso
     */
    public boolean getPrimerAcceso() {
        return primerAcceso;
    }

    /**
     * Sets the primer acceso.
     *
     * @param primerAcceso
     *            the new primer acceso
     */
    public void setPrimerAcceso(boolean primerAcceso) {
        this.primerAcceso = primerAcceso;
    }

    /**
     * Checks if is acceso mobile.
     *
     * @return true, if is acceso mobile
     */
    public boolean isAccesoMobile() {
        return accesoMobile;
    }

    /**
     * Sets the acceso mobile.
     *
     * @param accesoMobile
     *            the new acceso mobile
     */
    public void setAccesoMobile(boolean accesoMobile) {
        this.accesoMobile = accesoMobile;
    }

    /**
     * Gets the validacion hash.
     *
     * @return the validacion hash
     */
    public String getValidacionHash() {
        return validacionHash;
    }

    /**
     * Sets the validacion hash.
     *
     * @param validacionHash
     *            the new validacion hash
     */
    public void setValidacionHash(String validacionHash) {
        LOGGER.info(MSJ_INFO_VACIANDO_HASH_SESION);
        this.validacionHash = validacionHash;
    }

    /**
     * Gets the validacion hash TIS.
     *
     * @return the validacion hash TIS
     */
    public String getValidacionHashTIS() {
        return validacionHashTIS;
    }

    /**
     * Sets the validacion hash TIS.
     *
     * @param validacionHash
     *            the new validacion hash TIS
     */
    public void setValidacionHashTIS(String validacionHashTIS) {
        LOGGER.info(MSJ_INFO_VACIANDO_HASH_SESION);
        this.validacionHashTIS = validacionHashTIS;
    }


    /**
     * Resetea el desafio en curso.
     */
    public void resetearDesafioEnCurso() {
        LOGGER.info(RESETEO_RSA);
        this.setExisteDesafioEnCurso(false);
        this.setDesafioEnCurso(null);
    }

    /**
     * Gets the comprobante tarjeta recargable.
     *
     * @return the comprobante tarjeta recargable
     */
    public ComprobanteRecargaTarjetaView getComprobanteTarjetaRecargable() {
        return comprobanteTarjetaRecargable;
    }

    /**
     * Sets the comprobante tarjeta recargable.
     *
     * @param comprobanteTarjetaRecargable
     *            the new comprobante tarjeta recargable
     */
    public void setComprobanteTarjetaRecargable(ComprobanteRecargaTarjetaView comprobanteTarjetaRecargable) {
        this.comprobanteTarjetaRecargable = comprobanteTarjetaRecargable;
    }

    /**
     * Gets the contador pagina comprobantes.
     *
     * @return the contadorPaginaComprobantes
     */
    public Integer getContadorPaginaComprobantes() {
        return contadorPaginaComprobantes;
    }

    /**
     * Sets the contador pagina comprobantes.
     *
     * @param contadorPaginaComprobantes
     *            the contadorPaginaComprobantes to set
     */
    public void setContadorPaginaComprobantes(Integer contadorPaginaComprobantes) {
        this.contadorPaginaComprobantes = contadorPaginaComprobantes;
    }

    /**
     * Gets the clasificacion deuda.
     *
     * @return the clasificacionDeuda
     */
    public ClasificacionDeuda getClasificacionDeuda() {
        return clasificacionDeuda;
    }

    /**
     * Sets the clasificacion deuda.
     *
     * @param clasificacionDeuda
     *            the clasificacionDeuda to set
     */
    public void setClasificacionDeuda(ClasificacionDeuda clasificacionDeuda) {
        this.clasificacionDeuda = clasificacionDeuda;
    }

    /**
     * Gets the reasigna.
     *
     * @return the reasigna
     */
    public String getReasigna() {
        return reasigna;
    }

    /**
     * Sets the reasigna.
     *
     * @param reasigna
     *            the new reasigna
     */
    public void setReasigna(String reasigna) {
        this.reasigna = reasigna;
    }

    /**
     * Gets the cuenta seleccionada para transferencia.
     *
     * @param valorIndice
     *            the valor indice
     * @return the cuentaSeleccionadaParaTransferencia
     */
    public String getCuentaSeleccionadaParaTransferencia(String valorIndice) {
        return cuentaSeleccionadaParaTransferencia.get(valorIndice);
    }

    /**
     * Sets the cuenta seleccionada para transferencia.
     *
     * @param nroCuenta
     *            the nro cuenta
     * @param moneda
     *            the moneda
     */
    public void setCuentaSeleccionadaParaTransferencia(String nroCuenta, String moneda) {
        this.cuentaSeleccionadaParaTransferencia.put(NROCUENTA, nroCuenta);
        this.cuentaSeleccionadaParaTransferencia.put(MONEDA, moneda);
    }

    /**
     * Gets the mya DTO out.
     *
     * @return the mya DTO out
     */
    public MyaDTOOut getMyaDTOOut() {
        return myaDTOOut;
    }

    /**
     * Sets the mya DTO out.
     *
     * @param myaDTOOut
     *            the new mya DTO out
     */
    public void setMyaDTOOut(MyaDTOOut myaDTOOut) {
        this.myaDTOOut = myaDTOOut;
    }

    /**
     * Gets the destinatario view.
     *
     * @return the destinatario view
     */
    public DestinatarioDTO getDestinatarioView() {
        return destinatarioView;
    }

    /**
     * Sets the destinatario view.
     *
     * @param destinatarioView
     *            the new destinatario view
     */
    public void setDestinatarioView(DestinatarioDTO destinatarioView) {
        this.destinatarioView = destinatarioView;
    }

    /**
     * Gets the detalle comprobante view.
     *
     * @return the detalleComprobanteView
     */
    public DetalleComprobanteView getDetalleComprobanteView() {
        return detalleComprobanteView;
    }

    /**
     * Sets the detalle comprobante view.
     *
     * @param detalleComprobanteView
     *            the detalleComprobanteView to set
     */
    public void setDetalleComprobanteView(DetalleComprobanteView detalleComprobanteView) {
        this.detalleComprobanteView = detalleComprobanteView;
    }

    /**
     * Gets the detalle CBU alias.
     *
     * @return the detalle CBU alias
     */
    public DetalleCBUView getDetalleCBUAlias() {
        return detalleCBUAlias;
    }

    /**
     * Sets the detalle CBU alias.
     *
     * @param detalleCBUAlias
     *            the new detalle CBU alias
     */
    public void setDetalleCBUAlias(DetalleCBUView detalleCBUAlias) {
        this.detalleCBUAlias = detalleCBUAlias;
    }

    /**
     * Gets the tipo operacion comprobante.
     *
     * @return the tipoOperacionComprobante
     */
    public TipoOperacionComprobanteEnum getTipoOperacionComprobante() {
        return tipoOperacionComprobante;
    }

    /**
     * Sets the tipo operacion comprobante.
     *
     * @param tipoOperacionComprobante
     *            the tipoOperacionComprobante to set
     */
    public void setTipoOperacionComprobante(TipoOperacionComprobanteEnum tipoOperacionComprobante) {
        this.tipoOperacionComprobante = tipoOperacionComprobante;
    }

    /**
     * Gets the nro comprobante.
     *
     * @return the nro comprobante
     */
    public String getNroComprobante() {
        return nroComprobante;
    }

    /**
     * Sets the nro comprobante.
     *
     * @param nroComprobante
     *            the new nro comprobante
     */
    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    /**
     * Checks if is from agenda destinatario.
     *
     * @return the isFromAgendaDestinatario
     */
    public boolean isFromAgendaDestinatario() {
        return isFromAgendaDestinatario;
    }

    /**
     * Sets the from agenda destinatario.
     *
     * @param isFromAgendaDestinatario
     *            the isFromAgendaDestinatario to set
     */
    public void setFromAgendaDestinatario(boolean isFromAgendaDestinatario) {
        this.isFromAgendaDestinatario = isFromAgendaDestinatario;
    }

    /**
     * Gets the domicilios cliente.
     *
     * @return the domicilios cliente
     */
    public List<CambioDomicilioDTO> getDomiciliosCliente() {
        return domiciliosCliente;
    }

    /**
     * Sets the domicilios cliente.
     *
     * @param domiciliosCliente
     *            the new domicilios cliente
     */
    public void setDomiciliosCliente(List<CambioDomicilioDTO> domiciliosCliente) {
        this.domiciliosCliente = domiciliosCliente;
    }

    /**
     * Gets the domicilio modificado.
     *
     * @return the domicilio modificado
     */
    public ModificacionCambioDomicilioDTO getDomicilioModificado() {
        return domicilioModificado;
    }

    /**
     * Sets the domicilio modificado.
     *
     * @param domicilioModificado
     *            the new domicilio modificado
     */
    public void setDomicilioModificado(ModificacionCambioDomicilioDTO domicilioModificado) {
        this.domicilioModificado = domicilioModificado;
    }

    /**
     * Gets the transferencia view.
     *
     * @return the transferenciaView
     */
    public TransferenciaView getTransferenciaView() {
        return transferenciaView;
    }

    /**
     * Sets the transferencia view.
     *
     * @param transferenciaView
     *            the transferenciaView to set
     */
    public void setTransferenciaView(TransferenciaView transferenciaView) {
        this.transferenciaView = transferenciaView;
    }

    /**
     * Inicializar contador reintentos.
     *
     * @param maxIntentos
     *            the max intentos
     */
    public void inicializarContadorReintentos(int maxIntentos) {
        if (this.contador == null) {
            this.contador = new ContadorIntentos(maxIntentos);
        }
    }

    /**
     * Gets the pago haberes informados view.
     *
     * @return the pago haberes informados view
     */
    public List<PagoInformadoView> getPagoHaberesInformadosView() {
        return pagoHaberesInformadosView;
    }

    /**
     * Sets the pago haberes informados view.
     *
     * @param pagoHaberesInformadosView
     *            the new pago haberes informados view
     */
    public void setPagoHaberesInformadosView(List<PagoInformadoView> pagoHaberesInformadosView) {
        this.pagoHaberesInformadosView = pagoHaberesInformadosView;
    }

    /**
     * Gets the comprobante baja producto.
     *
     * @return the comprobante baja producto
     */
    public ComprobanteBajaProductoView getComprobanteBajaProducto() {
        return comprobanteBajaProducto;
    }

    /**
     * Sets the comprobante baja producto.
     *
     * @param comprobanteBajaProducto
     *            the new comprobante baja producto
     */
    public void setComprobanteBajaProducto(ComprobanteBajaProductoView comprobanteBajaProducto) {
        this.comprobanteBajaProducto = comprobanteBajaProducto;
    }

    /**
     * Gets the pago tarjeta view.
     *
     * @return the pagoTarjetaView
     */
    public PagoTarjetaCreditoView getPagoTarjetaView() {
        return pagoTarjetaView;
    }

    /**
     * Sets the pago tarjeta view.
     *
     * @param pagoTarjetaView
     *            the pagoTarjetaView to set
     */
    public void setPagoTarjetaView(PagoTarjetaCreditoView pagoTarjetaView) {
        this.pagoTarjetaView = pagoTarjetaView;
    }

    /**
     * Gets the comprobante pago tarjeta.
     *
     * @return the comprobantePagoTarjeta
     */
    public ComprobantePagoTarjeta getComprobantePagoTarjeta() {
        return comprobantePagoTarjeta;
    }

    /**
     * Sets the comprobante pago tarjeta.
     *
     * @param comprobantePagoTarjeta
     *            the comprobantePagoTarjeta to set
     */
    public void setComprobantePagoTarjeta(ComprobantePagoTarjeta comprobantePagoTarjeta) {
        this.comprobantePagoTarjeta = comprobantePagoTarjeta;
    }

    /**
     * Gets the comprobante tarjeta cred adic.
     *
     * @return the comprobante tarjeta cred adic
     */
    public ComprobanteAltaTarjCredAdicionalView getComprobanteTarjetaCredAdic() {
        return comprobanteTarjetaCredAdic;
    }

    /**
     * Sets the comprobante tarjeta cred adic.
     *
     * @param comprobanteTarjetaCredAdic
     *            the new comprobante tarjeta cred adic
     */
    public void setComprobanteTarjetaCredAdic(ComprobanteAltaTarjCredAdicionalView comprobanteTarjetaCredAdic) {
        this.comprobanteTarjetaCredAdic = comprobanteTarjetaCredAdic;
    }

    /**
     * Gets the tenencias fondo DTO.
     *
     * @return the tenenciasFondoDTO
     */
    public TenenciasFondoDTO getTenenciasFondoDTO() {
        return tenenciasFondoDTO;
    }

    /**
     * Sets the tenencias fondo DTO.
     *
     * @param tenenciasFondoDTO
     *            the tenenciasFondoDTO to set
     */
    public void setTenenciasFondoDTO(TenenciasFondoDTO tenenciasFondoDTO) {
        this.tenenciasFondoDTO = tenenciasFondoDTO;
    }

    /**
     * Gets the comprobante adhesion pago view.
     *
     * @return the comprobante adhesion pago view
     */
    public ComprobanteAdhesionTodoPagoView getComprobanteAdhesionTodoPagoView() {
        return comprobanteAdhesionTodoPagoView;
    }

    /**
     * Sets the comprobante adhesion pago view.
     *
     * @param comprobanteAdhesionTodoPagoView
     *            the new comprobante adhesion pago view
     */
    public void setComprobanteAdhesionTodoPagoView(ComprobanteAdhesionTodoPagoView comprobanteAdhesionTodoPagoView) {
        this.comprobanteAdhesionTodoPagoView = comprobanteAdhesionTodoPagoView;
    }

    /**
     * Gets the datos comprobante solicitud tarjeta recargable view.
     *
     * @return the datos comprobante solicitud tarjeta recargable view
     */
    public DatosComprobanteSolicitudTarjetaRecargableView getDatosComprobanteSolicitudTarjetaRecargableView() {
        return datosComprobanteSolicitudTarjetaRecargableView;
    }

    /**
     * Sets the datos comprobante solicitud tarjeta recargable view.
     *
     * @param datosComprobanteSolicitudTarjetaRecargableView
     *            the new datos comprobante solicitud tarjeta recargable view
     */
    public void setDatosComprobanteSolicitudTarjetaRecargableView(
            DatosComprobanteSolicitudTarjetaRecargableView datosComprobanteSolicitudTarjetaRecargableView) {
        this.datosComprobanteSolicitudTarjetaRecargableView = datosComprobanteSolicitudTarjetaRecargableView;
    }

    /**
     * Gets the datos extraccion Y compras exterior.
     *
     * @return the datos extraccion Y compras exterior
     */
    public DatosExtraccionYComprasExteriorDTO getDatosExtraccionYComprasExterior() {
        return datosExtraccionYComprasExterior;
    }

    /**
     * Sets the datos extraccion Y compras exterior.
     *
     * @param datosExtraccionYComprasExterior
     *            the new datos extraccion Y compras exterior
     */
    public void setDatosExtraccionYComprasExterior(DatosExtraccionYComprasExteriorDTO datosExtraccionYComprasExterior) {
        this.datosExtraccionYComprasExterior = datosExtraccionYComprasExterior;
    }

    /**
     * Gets the datos comprobante aumento limite transferencia.
     *
     * @return the datos comprobante aumento limite transferencia
     */
    public DatosComprobanteAumentoLimiteTransferencia getDatosComprobanteAumentoLimiteTransferencia() {
        return datosComprobanteAumentoLimiteTransferencia;
    }

    /**
     * Sets the datos comprobante aumento limite transferencia.
     *
     * @param datosComprobanteAumentoLimiteTransferencia
     *            the new datos comprobante aumento limite transferencia
     */
    public void setDatosComprobanteAumentoLimiteTransferencia(
            DatosComprobanteAumentoLimiteTransferencia datosComprobanteAumentoLimiteTransferencia) {
        this.datosComprobanteAumentoLimiteTransferencia = datosComprobanteAumentoLimiteTransferencia;
    }

    /**
     * Gets the rsa riesgo transferencia DTO. Se utiliza cuando la transferencia
     * tiene riesgo alto. Cuando este valor es distinto de nulo, significa que ya se
     * consulto a RSA y se le informo al cliente de su riesgo, por ende, NO se
     * vuelve a consultar a RSA y se usa el valor almacenado en la sesion.
     *
     * @return the rsa riesgo transferencia DTO
     */
    public RsaRiesgoTransferenciaDTO getRsaRiesgoTransferenciaDTO() {
        return rsaRiesgoTransferenciaDTO;
    }

    /**
     * Sets the rsa riesgo transferencia DTO.
     *
     * @param rsaRiesgoTransferenciaDTO
     *            the new rsa riesgo transferencia DTO
     */
    public void setRsaRiesgoTransferenciaDTO(RsaRiesgoTransferenciaDTO rsaRiesgoTransferenciaDTO) {
        this.rsaRiesgoTransferenciaDTO = rsaRiesgoTransferenciaDTO;
    }

    /**
     * Gets the ofertas comerciales.
     *
     * @return the ofertasComerciales
     */
    public EventosComercialesDTO getOfertasComerciales() {
        return ofertasComerciales;
    }

    /**
     * Sets the ofertas comerciales.
     *
     * @param ofertasComerciales
     *            the ofertasComerciales to set
     */
    public void setOfertasComerciales(EventosComercialesDTO ofertasComerciales) {
        this.ofertasComerciales = ofertasComerciales;
    }

    /**
     * Gets the compra venta view.
     *
     * @return the compra venta view
     */
    public ComprobanteCompraVentaView getCompraVentaView() {
        return compraVentaView;
    }

    /**
     * Sets the compra venta view.
     *
     * @param compraVentaView
     *            the new compra venta view
     */
    public void setCompraVentaView(ComprobanteCompraVentaView compraVentaView) {
        this.compraVentaView = compraVentaView;
    }

    /**
     * Gets the jwt token activo.
     *
     * @return the jwtTokenActivo
     */
    public Boolean getJwtTokenActivo() {
        return jwtTokenActivo;
    }

    /**
     * Sets the jwt token activo.
     *
     * @param jwtTokenActivo
     *            the jwtTokenActivo to set
     */
    public void setJwtTokenActivo(Boolean jwtTokenActivo) {
        this.jwtTokenActivo = jwtTokenActivo;
    }

    /**
     * Gets the comprobante recarga.
     *
     * @return the comprobante recarga
     */
    public ConfirmacionRecargaView getComprobanteRecarga() {
        return comprobanteRecarga;
    }

    /**
     * Sets the comprobante recarga.
     *
     * @param comprobanteRecarga
     *            the new comprobante recarga
     */
    public void setComprobanteRecarga(ConfirmacionRecargaView comprobanteRecarga) {
        this.comprobanteRecarga = comprobanteRecarga;
    }

    /**
     * Gets the nuevo pago view.
     *
     * @return the nuevo pago view
     */
    public NuevoPago getNuevoPagoView() {
        return nuevoPagoView;
    }

    /**
     * Sets the nuevo pago view.
     *
     * @param nuevoPagoView
     *            the new nuevo pago view
     */
    public void setNuevoPagoView(NuevoPago nuevoPagoView) {
        this.nuevoPagoView = nuevoPagoView;
    }

    /**
     * Gets the tipo nuevo pago.
     *
     * @return the tipo nuevo pago
     */
    public String getTipoNuevoPago() {
        return tipoNuevoPago;
    }

    /**
     * Sets the tipo nuevo pago.
     *
     * @param tipoNuevoPago
     *            the new tipo nuevo pago
     */
    public void setTipoNuevoPago(String tipoNuevoPago) {
        this.tipoNuevoPago = tipoNuevoPago;
    }

    /**
     * Gets the lista pagos multiples.
     *
     * @return the lista pagos multiples
     */
    public List<PagoMultipleView> getListaPagosMultiples() {
        return listaPagosMultiples;
    }

    /**
     * Sets the lista pagos multiples.
     *
     * @param listaPagosMultiples
     *            the new lista pagos multiples
     */
    public void setListaPagosMultiples(List<PagoMultipleView> listaPagosMultiples) {
        this.listaPagosMultiples = listaPagosMultiples;
    }

    /**
     * Gets the lista msg multiples.
     *
     * @return the lista msg multiples
     */
    public Map<String, List<MyaMsgMultiple>> getListaMsgMultiples() {
        return listaMsgMultiples;
    }

    /**
     * Sets the lista msg multiples.
     *
     * @param listaMsgMultiples
     *            the new lista msg multiples
     */
    public void setListaMsgMultiples(Map<String, List<MyaMsgMultiple>> listaMsgMultiples) {
        this.listaMsgMultiples = listaMsgMultiples;
    }

    /**
     * Gets the datos reimpresion comprobante.
     *
     * @return the datos reimpresion comprobante
     */
    public DatosReimpresionComprobante getDatosReimpresionComprobante() {
        return datosReimpresionComprobante;
    }

    /**
     * Sets the datos reimpresion comprobante.
     *
     * @param datosReimpresionComprobante
     *            the new datos reimpresion comprobante
     */
    public void setDatosReimpresionComprobante(DatosReimpresionComprobante datosReimpresionComprobante) {
        this.datosReimpresionComprobante = datosReimpresionComprobante;
    }

    /**
     * Gets the lista pagos multiples view.
     *
     * @return the lista pagos multiples view
     */
    public List<PagoMultipleView> getListaPagosMultiplesView() {
        return listaPagosMultiplesView;
    }

    /**
     * Sets the lista pagos multiples view.
     *
     * @param listaPagosMultiplesView
     *            the new lista pagos multiples view
     */
    public void setListaPagosMultiplesView(List<PagoMultipleView> listaPagosMultiplesView) {
        this.listaPagosMultiplesView = listaPagosMultiplesView;
    }

    /**
     * Gets the fecha hora pago.
     *
     * @return the fecha hora pago
     */
    public String getFechaHoraPago() {
        return fechaHoraPago;
    }

    /**
     * Sets the fecha hora pago.
     *
     * @param fechaHoraPago
     *            the new fecha hora pago
     */
    public void setFechaHoraPago(String fechaHoraPago) {
        this.fechaHoraPago = fechaHoraPago;
    }

    /**
     * getTarjetasCandidatasReimpresion.
     *
     * @return the tarjetas candidatas reimpresion
     */
    public List<TarjetaCandidataDTO> getTarjetasCandidatasReimpresion() {
        return tarjetasCandidatasReimpresion;
    }

    /**
     * setTarjetasCandidatasReimpresion.
     *
     * @param tarjetasCandidatasReimpresion
     *            the new tarjetas candidatas reimpresion
     */
    public void setTarjetasCandidatasReimpresion(List<TarjetaCandidataDTO> tarjetasCandidatasReimpresion) {
        this.tarjetasCandidatasReimpresion = tarjetasCandidatasReimpresion;
    }

    /**
     * Gets the ignora tabla tbanco.
     *
     * @return the ignoraTablaTbanco
     */
    public Boolean getIgnoraTablaTbanco() {
        return ignoraTablaTbanco;
    }

    /**
     * Sets the ignora tabla tbanco.
     *
     * @param ignoraTablaTbanco
     *            the ignoraTablaTbanco to set
     */
    public void setIgnoraTablaTbanco(Boolean ignoraTablaTbanco) {
        this.ignoraTablaTbanco = ignoraTablaTbanco;
    }

    /**
     * Gets the comprobante CNV licitaciones.
     *
     * @return the comprobanteCNVLicitaciones
     */
    public String getComprobanteCNVLicitaciones() {
        return comprobanteCNVLicitaciones;
    }

    /**
     * Sets the comprobante CNV licitaciones.
     *
     * @param comprobanteCNVLicitaciones
     *            the comprobanteCNVLicitaciones to set
     */
    public void setComprobanteCNVLicitaciones(String comprobanteCNVLicitaciones) {
        this.comprobanteCNVLicitaciones = comprobanteCNVLicitaciones;
    }

    /**
     * Gets the contador pagina movimientos banca privada.
     *
     * @return the contador pagina movimientos banca privada
     */
    public Integer getContadorPaginaMovimientosBancaPrivada() {
        return contadorPaginaMovimientosBancaPrivada;
    }

    /**
     * Sets the contador pagina movimientos banca privada.
     *
     * @param contadorPaginaMovimientosBancaPrivada
     *            the new contador pagina movimientos banca privada
     */
    public void setContadorPaginaMovimientosBancaPrivada(Integer contadorPaginaMovimientosBancaPrivada) {
        this.contadorPaginaMovimientosBancaPrivada = contadorPaginaMovimientosBancaPrivada;
    }

    /**
     * Gets the subproducto paquete.
     *
     * @return the subproducto paquete
     */
    public String getSubproductoPaquete() {
        return subproductoPaquete;
    }

    /**
     * Sets the subproducto paquete.
     *
     * @param subproductoPaquete
     *            the new subproducto paquete
     */
    public void setSubproductoPaquete(String subproductoPaquete) {
        this.subproductoPaquete = subproductoPaquete;
    }

    /**
     * Gets the checks if is calificado.
     *
     * @return the checks if is calificado
     */
    public Boolean getIsCalificado() {
        return isCalificado;
    }

    /**
     * Sets the checks if is calificado.
     *
     * @param isCalificado
     *            the new checks if is calificado
     */
    public void setIsCalificado(Boolean isCalificado) {
        this.isCalificado = isCalificado;
    }

    /**
     * Gets the checks if is calificado modulo desc cheques.
     *
     * @return the checks if is calificado modulo desc cheques
     */
    public Boolean getIsCalificadoModuloDescCheques() {
        return isCalificadoModuloDescCheques;
    }

    /**
     * Sets the checks if is calificado modulo desc cheques.
     *
     * @param isCalificadoModuloDescCheques
     *            the new checks if is calificado modulo desc cheques
     */
    public void setIsCalificadoModuloDescCheques(Boolean isCalificadoModuloDescCheques) {
        this.isCalificadoModuloDescCheques = isCalificadoModuloDescCheques;
    }

    /**
     * Gets the lista claves json.
     *
     * @return the lista claves json
     */
    public String getListaClavesJson() {
        return listaClavesJson;
    }

    /**
     * Sets the lista claves json.
     *
     * @param listaClavesJson
     *            the new lista claves json
     */
    public void setListaClavesJson(String listaClavesJson) {
        this.listaClavesJson = listaClavesJson;
    }

    /**
     * Gets the comprobante orden venta.
     *
     * @return the comprobante orden venta
     */
    public ComprobanteOrdenVenta getComprobanteOrdenVenta() {
        return comprobanteOrdenVenta;
    }

    /**
     * Sets the comprobante orden venta.
     *
     * @param comprobanteOrdenVenta
     *            the new comprobante orden venta
     */
    public void setComprobanteOrdenVenta(ComprobanteOrdenVenta comprobanteOrdenVenta) {
        this.comprobanteOrdenVenta = comprobanteOrdenVenta;
    }

    /**
     * Gets the id cumplimiento venta titulos.
     *
     * @return the id cumplimiento venta titulos
     */
    public Long getIdCumplimientoVentaTitulos() {
        return idCumplimientoVentaTitulos;
    }

    /**
     * Gets the solucitud cambio afinidad realizada.
     *
     * @return the solucitud cambio afinidad realizada
     */
    public Boolean getSolucitudCambioAfinidadRealizada() {
        return solucitudCambioAfinidadRealizada;
    }

    /**
     * Sets the solucitud cambio afinidad realizada.
     *
     * @param solucitudCambioAfinidadRealizada
     *            the new solucitud cambio afinidad realizada
     */
    public void setSolucitudCambioAfinidadRealizada(Boolean solucitudCambioAfinidadRealizada) {
        this.solucitudCambioAfinidadRealizada = solucitudCambioAfinidadRealizada;
    }

    /**
     * Gets the comprobante solicitud cambio afinidad view.
     *
     * @return the comprobante solicitud cambio afinidad view
     */
    public ComprobanteSolicitudCambioAfinidadView getComprobanteSolicitudCambioAfinidadView() {
        return comprobanteSolicitudCambioAfinidadView;
    }

    /**
     * Sets the comprobante solicitud cambio afinidad view.
     *
     * @param comprobanteSolicitudCambioAfinidadView
     *            the new comprobante solicitud cambio afinidad view
     */
    public void setComprobanteSolicitudCambioAfinidadView(
            ComprobanteSolicitudCambioAfinidadView comprobanteSolicitudCambioAfinidadView) {
        this.comprobanteSolicitudCambioAfinidadView = comprobanteSolicitudCambioAfinidadView;
    }

    /**
     * Gets the alta comprobante plazo fijo DTO.
     *
     * @return the altaComprobantePlazoFijoDTO
     */
    public AltaComprobantePlazoFijoDTO getAltaComprobantePlazoFijoDTO() {
        return altaComprobantePlazoFijoDTO;
    }

    /**
     * Sets the alta comprobante plazo fijo DTO.
     *
     * @param altaComprobantePlazoFijoDTO
     *            the altaComprobantePlazoFijoDTO to set
     */
    public void setAltaComprobantePlazoFijoDTO(AltaComprobantePlazoFijoDTO altaComprobantePlazoFijoDTO) {
        this.altaComprobantePlazoFijoDTO = altaComprobantePlazoFijoDTO;
    }

    /**
     * Gets the res cuotas pendientes.
     *
     * @return the res cuotas pendientes
     */
    public Respuesta<CuotasPendientesView> getResCuotasPendientes() {
        return resCuotasPendientes;
    }

    /**
     * Sets the res cuotas pendientes.
     *
     * @param resCuotasPendientes
     *            the new res cuotas pendientes
     */
    public void setResCuotasPendientes(Respuesta<CuotasPendientesView> resCuotasPendientes) {
        this.resCuotasPendientes = resCuotasPendientes;
    }

    /**
     * Gets the consulta financiacion.
     *
     * @return the consulta financiacion
     */
    public ConsultaFinanciacionView getConsultaFinanciacion() {
        return consultaFinanciacion;
    }

    /**
     * Sets the consulta financiacion.
     *
     * @param consultaFinanciacion
     *            the new consulta financiacion
     */
    public void setConsultaFinanciacion(ConsultaFinanciacionView consultaFinanciacion) {
        this.consultaFinanciacion = consultaFinanciacion;
    }

    /**
     * Gets the tarjetas view.
     *
     * @return the tarjetas view
     */
    public TarjetasView getTarjetasView() {
        return tarjetasView;
    }

    /**
     * Sets the tarjetas view.
     *
     * @param tarjetasView
     *            the new tarjetas view
     */
    public void setTarjetasView(TarjetasView tarjetasView) {
        this.tarjetasView = tarjetasView;
    }

    /**
     * Gets the contador pagina millas aadvantage.
     *
     * @return the contador pagina millas aadvantage
     */
    public Integer getContadorPaginaMillasAadvantage() {
        return contadorPaginaMillasAadvantage;
    }

    /**
     * Sets the contador pagina millas aadvantage.
     *
     * @param contadorPaginaMillasAadvantage
     *            the new contador pagina millas aadvantage
     */
    public void setContadorPaginaMillasAadvantage(Integer contadorPaginaMillasAadvantage) {
        this.contadorPaginaMillasAadvantage = contadorPaginaMillasAadvantage;
    }

    /**
     * Sets the id cumplimiento venta titulos.
     *
     * @param idCumplimientoVentaTitulos
     *            the new id cumplimiento venta titulos
     */
    public void setIdCumplimientoVentaTitulos(Long idCumplimientoVentaTitulos) {
        this.idCumplimientoVentaTitulos = idCumplimientoVentaTitulos;
    }

    /**
     * Gets the comprobante json.
     *
     * @return the comprobante json
     */
    public String getComprobanteJson() {
        return comprobanteJson;
    }

    /**
     * Sets the comprobante json.
     *
     * @param comprobanteJson
     *            the new comprobante json
     */
    public void setComprobanteJson(String comprobanteJson) {
        this.comprobanteJson = comprobanteJson;
    }

    /**
     * Gets the cesion cheques.
     *
     * @return the cesion cheques
     */
    public String getCesionCheques() {
        return cesionCheques;
    }

    /**
     * Sets the cesion cheques.
     *
     * @param cesionCheques
     *            the new cesion cheques
     */
    public void setCesionCheques(String cesionCheques) {
        this.cesionCheques = cesionCheques;
    }

    /**
     * Gets the cuenta habilitada.
     *
     * @return the cuenta habilitada
     */
    public String getCuentaHabilitada() {
        return cuentaHabilitada;
    }

    /**
     * Sets the cuenta habilitada.
     *
     * @param cuentaHabilitada
     *            the new cuenta habilitada
     */
    public void setCuentaHabilitada(String cuentaHabilitada) {
        this.cuentaHabilitada = cuentaHabilitada;
    }

    /**
     * Gets the cita out DTO.
     *
     * @return the citaOutDTO
     */
    public CitaOutDTO getCitaOutDTO() {
        return citaOutDTO;
    }

    /**
     * Sets the cita out DTO.
     *
     * @param citaOutDTO
     *            the citaOutDTO to set
     */
    public void setCitaOutDTO(CitaOutDTO citaOutDTO) {
        this.citaOutDTO = citaOutDTO;
    }

    /**
     * Gets the id simulacion PDC.
     *
     * @return the id simulacion PDC
     */
    public String getIdSimulacionPDC() {
        return idSimulacionPDC;
    }

    /**
     * Sets the id simulacion PDC.
     *
     * @param idSimulacionPDC
     *            the new id simulacion PDC
     */
    public void setIdSimulacionPDC(String idSimulacionPDC) {
        this.idSimulacionPDC = idSimulacionPDC;
    }

    /**
     * Gets the en simulacion tasas.
     *
     * @return the en simulacion tasas
     */
    public Boolean getEnSimulacionTasas() {
        return enSimulacionTasas;
    }

    /**
     * Sets the en simulacion tasas.
     *
     * @param enSimulacionTasas
     *            the new en simulacion tasas
     */
    public void setEnSimulacionTasas(Boolean enSimulacionTasas) {
        this.enSimulacionTasas = enSimulacionTasas;
    }

    /**
     * Gets the report simulacion.
     *
     * @return the report simulacion
     */
    public ReportSimulacionPrestamosView getReportSimulacion() {
        return reportSimulacion;
    }

    /**
     * Sets the report simulacion.
     *
     * @param reportSimulacion
     *            the new report simulacion
     */
    public void setReportSimulacion(ReportSimulacionPrestamosView reportSimulacion) {
        this.reportSimulacion = reportSimulacion;
    }

    /**
     * Gets the legal adhesion PDC.
     *
     * @return the legal adhesion PDC
     */
    public String getLegalAdhesionPDC() {
        return legalAdhesionPDC;
    }

    /**
     * Sets the legal adhesion PDC.
     *
     * @param legalAdhesionPDC
     *            the new legal adhesion PDC
     */
    public void setLegalAdhesionPDC(String legalAdhesionPDC) {
        this.legalAdhesionPDC = legalAdhesionPDC;
    }

    /**
     * Gets the fallo legales.
     *
     * @return the fallo legales
     */
    public Boolean getFalloLegales() {
        return falloLegales;
    }

    /**
     * Sets the fallo legales.
     *
     * @param falloLegales
     *            the new fallo legales
     */
    public void setFalloLegales(Boolean falloLegales) {
        this.falloLegales = falloLegales;
    }

    /**
     * Gets the monedas comex map.
     *
     * @return the monedasComexMap
     */
    public Map<String, ConsultaMonedaOutDTO> getMonedasComexMap() {
        return monedasComexMap;
    }

    /**
     * Sets the monedas comex map.
     *
     * @param monedasComexMap
     *            the monedasComexMap to set
     */
    public void setMonedasComexMap(Map<String, ConsultaMonedaOutDTO> monedasComexMap) {
        this.monedasComexMap = monedasComexMap;
    }

    /**
     * Gets the consulta operaciones out view.
     *
     * @return the consultaOperacionesOutView
     */
    public ConsultaOperacionesOutView getConsultaOperacionesOutView() {
        return consultaOperacionesOutView;
    }

    /**
     * Sets the consulta operaciones out view.
     *
     * @param consultaOperacionesOutView
     *            the consultaOperacionesOutView to set
     */
    public void setConsultaOperacionesOutView(ConsultaOperacionesOutView consultaOperacionesOutView) {
        this.consultaOperacionesOutView = consultaOperacionesOutView;
    }

    /**
     * Gets the cant paginas detalle comex.
     *
     * @return the cantPaginasDetalleComex
     */
    public int getCantPaginasDetalleComex() {
        return cantPaginasDetalleComex;
    }

    /**
     * Sets the cant paginas detalle comex.
     *
     * @param cantPaginasDetalleComex
     *            the cantPaginasDetalleComex to set
     */
    public void setCantPaginasDetalleComex(int cantPaginasDetalleComex) {
        this.cantPaginasDetalleComex = cantPaginasDetalleComex;
    }

    /**
     * Gets the nro pagina comex detalle.
     *
     * @return the nroPaginaComexDetalle
     */
    public int getNroPaginaComexDetalle() {
        return nroPaginaComexDetalle;
    }

    /**
     * Sets the nro pagina comex detalle.
     *
     * @param nroPaginaComexDetalle
     *            the nroPaginaComexDetalle to set
     */
    public void setNroPaginaComexDetalle(int nroPaginaComexDetalle) {
        this.nroPaginaComexDetalle = nroPaginaComexDetalle;
    }

    /**
     * Gets the sucursales turnos web.
     *
     * @return the sucursalesTurnosWeb
     */
    public List<SucursalesDTO> getSucursalesTurnosWeb() {
        return sucursalesTurnosWeb;
    }

    /**
     * Sets the sucursales turnos web.
     *
     * @param sucursalesTurnosWeb
     *            the sucursalesTurnosWeb to set
     */
    public void setSucursalesTurnosWeb(List<SucursalesDTO> sucursalesTurnosWeb) {
        this.sucursalesTurnosWeb = sucursalesTurnosWeb;
    }

    /**
     * Gets the cuentas comex pesos.
     *
     * @return the cuentasComexPesos
     */
    public List<ResumenDetalleCuenta> getCuentasComex() {
        return cuentasComex;
    }

    /**
	 * Sets the cuentas comex pesos.
	 *
	 * @param cuentasComex
	 *            the new cuentas comex
	 */
    public void setCuentasComex(List<ResumenDetalleCuenta> cuentasComex) {
        this.cuentasComex = cuentasComex;
    }

    /**
     * Gets the checks if is licitacion B priv.
     *
     * @return the checks if is licitacion B priv
     */
    public Boolean getIsLicitacionBPriv() {
        return isLicitacionBPriv;
    }

    /**
     * Sets the checks if is licitacion B priv.
     *
     * @param isLicitacionBPriv
     *            the new checks if is licitacion B priv
     */
    public void setIsLicitacionBPriv(Boolean isLicitacionBPriv) {
        this.isLicitacionBPriv = isLicitacionBPriv;
    }

    /**
     * Gets the comprobante comex.
     *
     * @return the comprobanteComex
     */
    public ComprobanteComexDTO getComprobanteComex() {
        return comprobanteComex;
    }

    /**
     * Sets the comprobante comex.
     *
     * @param comprobanteComex
     *            the comprobanteComex to set
     */
    public void setComprobanteComex(ComprobanteComexDTO comprobanteComex) {
        this.comprobanteComex = comprobanteComex;
    }

    /**
     * Gets the deudas pago compras entity.
     *
     * @return the deudas pago compras entity
     */
    public DeudasPagoComprasEntity getDeudasPagoComprasEntity() {
        return deudasPagoComprasEntity;
    }

    /**
	 * Sets the comprobante PPP is uva.
	 *
	 * @param comprobantePPPIsUva
	 *            the comprobantePPPIsUva to set
	 */
    public void setComprobantePPPIsUva(boolean comprobantePPPIsUva) {
        this.comprobantePPPIsUva = comprobantePPPIsUva;
    }

    /**
     * Sets the deudas pago compras entity.
     *
     * @param deudasPagoComprasEntity
     *            the new deudas pago compras entity
     */
    public void setDeudasPagoComprasEntity(DeudasPagoComprasEntity deudasPagoComprasEntity) {
        this.deudasPagoComprasEntity = deudasPagoComprasEntity;
    }

    /**
	 * Gets the id agrupamiento informacion mercado.
	 *
	 * @return the idAgrupamientoInformacionMercado
	 */
    public String getIdAgrupamientoInformacionMercado() {
        return idAgrupamientoInformacionMercado;
    }

    /**
	 * Sets the id agrupamiento informacion mercado.
	 *
	 * @param idAgrupamientoInformacionMercado
	 *            the idAgrupamientoInformacionMercado to set
	 */
    public void setIdAgrupamientoInformacionMercado(String idAgrupamientoInformacionMercado) {
        this.idAgrupamientoInformacionMercado = idAgrupamientoInformacionMercado;
    }

    /**
	 * Gets the cuenta seleccionada para transferencia.
	 *
	 * @return the cuenta seleccionada para transferencia
	 */
    public HashMap<String, String> getCuentaSeleccionadaParaTransferencia() {
        return cuentaSeleccionadaParaTransferencia;
    }

    /**
	 * Sets the cuenta seleccionada para transferencia.
	 *
	 * @param cuentaSeleccionadaParaTransferencia
	 *            the cuenta seleccionada para transferencia
	 */
    public void setCuentaSeleccionadaParaTransferencia(HashMap<String, String> cuentaSeleccionadaParaTransferencia) {
        this.cuentaSeleccionadaParaTransferencia = cuentaSeleccionadaParaTransferencia;
    }

    /**
	 * Gets the lista apertura especie.
	 *
	 * @return the listaAperturaEspecie
	 */
    public ListaAperturaEspecie getListaAperturaEspecie() {
        return listaAperturaEspecie;
    }

    /**
	 * Sets the lista apertura especie.
	 *
	 * @param listaAperturaEspecie
	 *            the listaAperturaEspecie to set
	 */
    public void setListaAperturaEspecie(ListaAperturaEspecie listaAperturaEspecie) {
        this.listaAperturaEspecie = listaAperturaEspecie;
    }

    /**
	 * Gets the comprobante PPP is uva.
	 *
	 * @return the comprobantePPPIsUva
	 */
    public boolean getComprobantePPPIsUva() {
        return comprobantePPPIsUva;
    }

    /**
	 * Gets the busqueda default cincuenta Y tres dias RTL.
	 *
	 * @return the busqueda default cincuenta Y tres dias RTL
	 */
    public Boolean getBusquedaDefaultCincuentaYTresDiasRTL() {
        return busquedaDefaultCincuentaYTresDiasRTL;
    }

    /**
	 * Sets the busqueda default cincuenta Y tres dias RTL.
	 *
	 * @param busquedaDefaultCincuentaYTresDiasRTL
	 *            the new busqueda default cincuenta Y tres dias RTL
	 */
    public void setBusquedaDefaultCincuentaYTresDiasRTL(Boolean busquedaDefaultCincuentaYTresDiasRTL) {
        this.busquedaDefaultCincuentaYTresDiasRTL = busquedaDefaultCincuentaYTresDiasRTL;
    }

    /**
	 * Gets the busqueda default cincuenta Y tres dias BP.
	 *
	 * @return the busqueda default cincuenta Y tres dias BP
	 */
    public Boolean getBusquedaDefaultCincuentaYTresDiasBP() {
        return busquedaDefaultCincuentaYTresDiasBP;
    }

    /**
	 * Sets the busqueda default cincuenta Y tres dias BP.
	 *
	 * @param busquedaDefaultCincuentaYTresDiasBP
	 *            the new busqueda default cincuenta Y tres dias BP
	 */
    public void setBusquedaDefaultCincuentaYTresDiasBP(Boolean busquedaDefaultCincuentaYTresDiasBP) {
        this.busquedaDefaultCincuentaYTresDiasBP = busquedaDefaultCincuentaYTresDiasBP;
    }

    /**
	 * Gets the os.
	 *
	 * @return the os
	 */
    public String getOs() {
        return os;
    }

    /**
	 * Sets the os.
	 *
	 * @param os
	 *            the new os
	 */
    public void setOs(String os) {
        this.os = os;
    }

    /**
	 * Gets the detalle sesion chat.
	 *
	 * @return the detalleSesionChat
	 */
    public ChatSessionEntity getDetalleSesionChat() {
        return detalleSesionChat;
    }

    /**
	 * Sets the detalle sesion chat.
	 *
	 * @param detalleSesionChat
	 *            the detalleSesionChat to set
	 */
    public void setDetalleSesionChat(ChatSessionEntity detalleSesionChat) {
        this.detalleSesionChat = detalleSesionChat;
    }

    /**
	 * Gets the archivos.
	 *
	 * @return the archivos
	 */
    public List<ReporteView> getArchivos() {
        return archivos;
    }

    /**
	 * Sets the archivos.
	 *
	 * @param archivos
	 *            the archivos to set
	 */
    public void setArchivos(List<ReporteView> archivos) {
        this.archivos = archivos;
    }

	/**
	 * Gets the pregunta validacion tel.
	 *
	 * @return the preguntaValidacionTel
	 */
	public PreguntaValidacionTel getPreguntaValidacionTel() {
		return preguntaValidacionTel;
	}

	/**
	 * Sets the pregunta validacion tel.
	 *
	 * @param preguntaValidacionTel the preguntaValidacionTel to set
	 */
	public void setPreguntaValidacionTel(PreguntaValidacionTel preguntaValidacionTel) {
		this.preguntaValidacionTel = preguntaValidacionTel;
	}

	/**
	 * Gets the verificacion pasos clave online.
	 *
	 * @return the verificacionPasosClaveOnline
	 */
	public VerificacionPasosClaveOnline getVerificacionPasosClaveOnline() {
		return verificacionPasosClaveOnline;
	}

	/**
	 * Sets the verificacion pasos clave online.
	 *
	 * @param verificacionPasosClaveOnline the verificacionPasosClaveOnline to set
	 */
	public void setVerificacionPasosClaveOnline(VerificacionPasosClaveOnline verificacionPasosClaveOnline) {
		this.verificacionPasosClaveOnline = verificacionPasosClaveOnline;
	}

    /**
     * Gets the proximas cuotas view.
     *
     * @return the proximas cuotas view
     */
    public ProximasCuotasView getProximasCuotasView() {
        return proximasCuotasView;
    }

    /**
     * Sets the proximas cuotas view.
     *
     * @param proximasCuotasView the new proximas cuotas view
     */
    public void setProximasCuotasView(ProximasCuotasView proximasCuotasView) {
        this.proximasCuotasView = proximasCuotasView;
    }
	
	/**
	 * Gets the gestionar adhesion debines view.
	 *
	 * @return the gestionarAdhesionDebinesView
	 */
	public GestionarAdhesionDebinesView getGestionarAdhesionDebinesView() {
		return gestionarAdhesionDebinesView;
	}

	/**
	 * Sets the gestionar adhesion debines view.
	 *
	 * @param gestionarAdhesionDebinesView the gestionarAdhesionDebinesView to set
	 */
	public void setGestionarAdhesionDebinesView(GestionarAdhesionDebinesView gestionarAdhesionDebinesView) {
		this.gestionarAdhesionDebinesView = gestionarAdhesionDebinesView;
	}

	/**
	 * Gets the nro pagina debin lista recurrencia.
	 *
	 * @return the nroPaginaDebinListaRecurrencia
	 */
	public int getNroPaginaDebinListaRecurrencia() {
		return nroPaginaDebinListaRecurrencia;
	}

	/**
	 * Sets the nro pagina debin lista recurrencia.
	 *
	 * @param nroPaginaDebinListaRecurrencia the nroPaginaDebinListaRecurrencia to set
	 */
	public void setNroPaginaDebinListaRecurrencia(int nroPaginaDebinListaRecurrencia) {
		this.nroPaginaDebinListaRecurrencia = nroPaginaDebinListaRecurrencia;
	}

	/**
	 * Gets the id debines recurrencias.
	 *
	 * @return the idDebinesRecurrencias
	 */
	public List<String> getIdDebinesRecurrencias() {
		return idDebinesRecurrencias;
	}

	/**
	 * Sets the id debines recurrencias.
	 *
	 * @param idDebinesRecurrencias the idDebinesRecurrencias to set
	 */
	public void setIdDebinesRecurrencias(List<String> idDebinesRecurrencias) {
		this.idDebinesRecurrencias = idDebinesRecurrencias;
	}

	/**
	 * Gets the info plazo fijo.
	 *
	 * @return the info plazo fijo
	 */
	public TenenciaPlazoFijoView getInfoPlazoFijo() {
		return infoPlazoFijo;
	}

	/**
	 * Sets the info plazo fijo.
	 *
	 * @param infoPlazoFijo the new info plazo fijo
	 */
	public void setInfoPlazoFijo(TenenciaPlazoFijoView infoPlazoFijo) {
		this.infoPlazoFijo = infoPlazoFijo;
	}

	/**
	 * Gets the lista tenencias por cuenta.
	 *
	 * @return the lista tenencias por cuenta
	 */
	public List<TenenciaTitulosCuentaDTO> getListaTenenciasPorCuenta() {
		return listaTenenciasPorCuenta;
	}

	/**
	 * Sets the lista tenencias por cuenta.
	 *
	 * @param listaTenenciasPorCuenta the new lista tenencias por cuenta
	 */
	public void setListaTenenciasPorCuenta(List<TenenciaTitulosCuentaDTO> listaTenenciasPorCuenta) {
		this.listaTenenciasPorCuenta = listaTenenciasPorCuenta;
	}

	/**
	 * Gets the lista tenencia consolidada RTL.
	 *
	 * @return the lista tenencia consolidada RTL
	 */
	public List<TenenciaProductosPorMonedaView> getListaTenenciaConsolidadaRTL() {
		return listaTenenciaConsolidadaRTL;
	}

	/**
	 * Sets the lista tenencia consolidada RTL.
	 *
	 * @param listaTenenciaConsolidadaRTL the new lista tenencia consolidada RTL
	 */
	public void setListaTenenciaConsolidadaRTL(List<TenenciaProductosPorMonedaView> listaTenenciaConsolidadaRTL) {
		this.listaTenenciaConsolidadaRTL = listaTenenciaConsolidadaRTL;
	}

	/**
	 * Gets the lista cuentas fondos.
	 *
	 * @return the lista cuentas fondos
	 */
	public List<CuentaConTenenciaDTO> getListaCuentasFondos() {
		return listaCuentasFondos;
	}

	/**
	 * Sets the lista cuentas fondos.
	 *
	 * @param listaCuentasFondos the new lista cuentas fondos
	 */
	public void setListaCuentasFondos(List<CuentaConTenenciaDTO> listaCuentasFondos) {
		this.listaCuentasFondos = listaCuentasFondos;
	}

	/**
	 * Gets the lista plazos fijos BP.
	 *
	 * @return the lista plazos fijos BP
	 */
	public List<ContenidoTenenciaBprivDTO> getListaPlazosFijosBP() {
		return listaPlazosFijosBP;
	}

	/**
	 * Sets the lista plazos fijos BP.
	 *
	 * @param listaPlazosFijosBP the new lista plazos fijos BP
	 */
	public void setListaPlazosFijosBP(List<ContenidoTenenciaBprivDTO> listaPlazosFijosBP) {
		this.listaPlazosFijosBP = listaPlazosFijosBP;
	}

	/**
	 * Gets the lista tenencia consolidada BP.
	 *
	 * @return the lista tenencia consolidada BP
	 */
	public List<TenenciaPorCuentaBPrivDTO> getListaTenenciaConsolidadaBP() {
		return listaTenenciaConsolidadaBP;
	}

	/**
	 * Sets the lista tenencia consolidada BP.
	 *
	 * @param listaTenenciaConsolidadaBP the new lista tenencia consolidada BP
	 */
	public void setListaTenenciaConsolidadaBP(List<TenenciaPorCuentaBPrivDTO> listaTenenciaConsolidadaBP) {
		this.listaTenenciaConsolidadaBP = listaTenenciaConsolidadaBP;
	}

	/**
	 * Gets the lista tenencias por cuenta BP.
	 *
	 * @return the lista tenencias por cuenta BP
	 */
	public List<TenenciaTitulosCuentaDTO> getListaTenenciasPorCuentaBP() {
		return listaTenenciasPorCuentaBP;
	}

	/**
	 * Sets the lista tenencias por cuenta BP.
	 *
	 * @param listaTenenciasPorCuentaBP the new lista tenencias por cuenta BP
	 */
	public void setListaTenenciasPorCuentaBP(List<TenenciaTitulosCuentaDTO> listaTenenciasPorCuentaBP) {
		this.listaTenenciasPorCuentaBP = listaTenenciasPorCuentaBP;
	}

	/**
	 * Gets the prestamos por cuenta.
	 *
	 * @return the prestamos por cuenta
	 */
	public List<PrestamosPorCuenta> getPrestamosPorCuenta() {
		return prestamosPorCuenta;
	}

	/**
	 * Sets the prestamos por cuenta.
	 *
	 * @param prestamosPorCuenta the new prestamos por cuenta
	 */
	public void setPrestamosPorCuenta(List<PrestamosPorCuenta> prestamosPorCuenta) {
		this.prestamosPorCuenta = prestamosPorCuenta;
	}

	/**
	 * Gets the calificaciones crediticias.
	 *
	 * @return the calificaciones crediticias
	 */
	public Map<Cuenta, CalificacionCrediticiaOutEntity> getCalificacionesCrediticias() {
		return calificacionesCrediticias;
	}

	/**
	 * Sets the calificaciones crediticias.
	 *
	 * @param calificacionesCrediticias the calificaciones crediticias
	 */
	public void setCalificacionesCrediticias(Map<Cuenta, CalificacionCrediticiaOutEntity> calificacionesCrediticias) {
		this.calificacionesCrediticias = calificacionesCrediticias;
	}
				
	/**
	 * Put calificacion crediticia.
	 *
	 * @param cuenta the cuenta
	 * @param calificacionCrediticia the calificacion crediticia
	 */
	public void putCalificacionCrediticia(Cuenta cuenta, CalificacionCrediticiaOutEntity calificacionCrediticia) {
		this.calificacionesCrediticias.put(cuenta, calificacionCrediticia);
	}
	
	/**
	 * Clear calificaciones crediticias.
	 */
	public void clearCalificacionesCrediticias() {
		this.calificacionesCrediticias = new HashMap<Cuenta, CalificacionCrediticiaOutEntity>();
	}

    /**
     * Gets the lista detalle comprobante view.
     *
     * @return the lista detalle comprobante view
     */
    public List<DetalleComprobanteView> getListaDetalleComprobanteView() {
        return listaDetalleComprobanteView;
    }

    /**
     * Sets the lista detalle comprobante view.
     *
     * @param listaDetalleComprobanteView the new lista detalle comprobante view
     */
    public void setListaDetalleComprobanteView(List<DetalleComprobanteView> listaDetalleComprobanteView) {
        this.listaDetalleComprobanteView = listaDetalleComprobanteView;
    }
	
    /**
     * Gets the detalle comprobante view por id.
     *
     * @param idComprobante the id comprobante
     * @return the detalle comprobante view por id
     */
    public DetalleComprobanteView getDetalleComprobanteViewPorId(String idComprobante) {
        if (CollectionUtils.isNotEmpty(this.listaDetalleComprobanteView)) {
            for (DetalleComprobanteView comprobante : this.listaDetalleComprobanteView) {
                if (StringUtils.equals(comprobante.getIdComprobante(), idComprobante)) {
                    return comprobante;
                }
            }
        }
        return null;
    }

	/**
	 * Gets the consulta concepto out view.
	 *
	 * @return the consulta concepto out view
	 */
    public ConsultaConceptoOutView getConsultaConceptoOutView() {
        return consultaConceptoOutView;
    }

    /**
	 * Sets the consulta concepto out view.
	 *
	 * @param consultaConceptoOutView
	 *            the consulta concepto out view to set
	 */
    public void setConsultaConceptoOutView(ConsultaConceptoOutView consultaConceptoOutView) {
        this.consultaConceptoOutView = consultaConceptoOutView;
    }
    
    /**
     * Gets the contador.
     *
     * @return the contador
     */
    public ContadorIntentos getContadorReemplazoTarjetas() {
        return contadorReemplazoTarjetas;
    }

    /**
     * Sets the contador.
     *
     * @param contadorReemplazoTarjetas the new contador reemplazo tarjetas
     */
    public void setContadorReemplazoTarjetas(ContadorIntentos contadorReemplazoTarjetas) {
        this.contadorReemplazoTarjetas = contadorReemplazoTarjetas;
    }
    
    /**
     * Gets the ofertaRecambioChip.
     *
     * @return the ofertaRecambioChip
     */
    public OfertaComercialDTO getOfertaRecambioChip() {
        return ofertaRecambioChip;
    }

    /**
     * Sets the ofertaRecambioChip.
     *
     * @param ofertaRecambioChip
     *            the ofertaRecambioChip to set
     */
    public void setOfertaRecambioChip(OfertaComercialDTO ofertaRecambioChip) {
        this.ofertaRecambioChip = ofertaRecambioChip;
    }
    

    /**
     * Gets the datos merlin sin normalizar.
     *
     * @return the datosMerlinSinNormalizar
     */
    public CambioDomicilioView getDatosMerlinSinNormalizar() {
        return datosMerlinSinNormalizar;
    }

    /**
     * Sets the datos merlin sin normalizar.
     *
     * @param datosMerlinSinNormalizar the datosMerlinSinNormalizar to set
     */
    public void setDatosMerlinSinNormalizar(CambioDomicilioView datosMerlinSinNormalizar) {
        this.datosMerlinSinNormalizar = datosMerlinSinNormalizar;
    }

	/**
	 * Gets the lista fechas comprobantes TV bpriv.
	 *
	 * @return the lista fechas comprobantes TV bpriv
	 */
	public ListaFechasComprobantes getListaFechasComprobantesTVBpriv() {
		return listaFechasComprobantesTVBpriv;
	}

	/**
	 * Sets the lista fechas comprobantes TV bpriv.
	 *
	 * @param listaFechasComprobantesTVBpriv the new lista fechas comprobantes TV bpriv
	 */
	public void setListaFechasComprobantesTVBpriv(ListaFechasComprobantes listaFechasComprobantesTVBpriv) {
		this.listaFechasComprobantesTVBpriv = listaFechasComprobantesTVBpriv;
	}

	/**
	 * Gets the lista fechas comprobantes FCI bpriv.
	 *
	 * @return the lista fechas comprobantes FCI bpriv
	 */
	public ListaFechasComprobantes getListaFechasComprobantesFCIBpriv() {
		return listaFechasComprobantesFCIBpriv;
	}

	/**
	 * Sets the lista fechas comprobantes FCI bpriv.
	 *
	 * @param listaFechasComprobantesFCIBpriv the new lista fechas comprobantes FCI bpriv
	 */
	public void setListaFechasComprobantesFCIBpriv(ListaFechasComprobantes listaFechasComprobantesFCIBpriv) {
		this.listaFechasComprobantesFCIBpriv = listaFechasComprobantesFCIBpriv;
	}
	
	/**
	 * Gets the id E cheqs.
	 *
	 * @return the idECheqs
	 */
	public List<String> getIdECheqs() {
		return idECheqs;
	}

	/**
	 * Sets the id E cheqs.
	 *
	 * @param idECheqs the idECheqs to set
	 */
	public void setIdECheqs(List<String> idECheqs) {
		this.idECheqs = idECheqs;
	}

	/**
	 * Gets the nro pagina echeq.
	 *
	 * @return the nro pagina echeq
	 */
	public int getNroPaginaEcheq() {
		return nroPaginaEcheq;
	}

	/**
	 * Sets the nro pagina echeq.
	 *
	 * @param nroPaginaEcheq the new nro pagina echeq
	 */
	public void setNroPaginaEcheq(int nroPaginaEcheq) {
		this.nroPaginaEcheq = nroPaginaEcheq;
	}

	/**
	 * Gets the echeq estado validado.
	 *
	 * @return the echeq estado validado
	 */
	public ECheqDTO getEcheqEstadoValidado() {
		return echeqEstadoValidado;
	}

	/**
	 * Sets the echeq estado validado.
	 *
	 * @param echeqEstadoValidado the new echeq estado validado
	 */
	public void setEcheqEstadoValidado(ECheqDTO echeqEstadoValidado) {
		this.echeqEstadoValidado = echeqEstadoValidado;
	}

	/**
	 * Gets the cuentas habilitadas alta echeq.
	 *
	 * @return the cuentas habilitadas alta echeq
	 */
	public List<Cuenta> getCuentasHabilitadasAltaEcheq() {
		return cuentasHabilitadasAltaEcheq;
	}

	/**
	 * Sets the cuentas habilitadas alta echeq.
	 *
	 * @param cuentasHabilitadasAltaEcheq the new cuentas habilitadas alta echeq
	 */
	public void setCuentasHabilitadasAltaEcheq(List<Cuenta> cuentasHabilitadasAltaEcheq) {
		this.cuentasHabilitadasAltaEcheq = cuentasHabilitadasAltaEcheq;
	}

	/**
	 * Gets the comprobante echeq DTO.
	 *
	 * @return the comprobante echeq DTO
	 */
	public ComprobanteECheqDTO getComprobanteEcheqDTO() {
		return comprobanteEcheqDTO;
	}

	/**
	 * Sets the comprobante echeq DTO.
	 *
	 * @param comprobanteEcheqDTO the new comprobante echeq DTO
	 */
	public void setComprobanteEcheqDTO(ComprobanteECheqDTO comprobanteEcheqDTO) {
		this.comprobanteEcheqDTO = comprobanteEcheqDTO;
	}

	/**
	 * Gets the lista cuentas fondos BP.
	 *
	 * @return the lista cuentas fondos BP
	 */
	public List<CuentaConTenenciaDTO> getListaCuentasFondosBP() {
		return listaCuentasFondosBP;
	}

	/**
	 * Gets the ingreso desde goto.
	 *
	 * @return the ingreso desde goto
	 */
	public Boolean getIngresoDesdeGoto() {
		return ingresoDesdeGoto;
	}

	/**
	 * Sets the lista cuentas fondos BP.
	 *
	 * @param listaCuentasFondosBP the new lista cuentas fondos BP
	 */
	public void setListaCuentasFondosBP(List<CuentaConTenenciaDTO> listaCuentasFondosBP) {
		this.listaCuentasFondosBP = listaCuentasFondosBP;
	}

	/**
	 * Sets the ingreso desde goto.
	 *
	 * @param ingresoDesdeGoto the new ingreso desde goto
	 */
	public void setIngresoDesdeGoto(Boolean ingresoDesdeGoto) {
		this.ingresoDesdeGoto = ingresoDesdeGoto;
	}

	/**
	 * Gets the numero tarjeta para goto limites.
	 *
	 * @return the numero tarjeta para goto limites
	 */
	public String getNumeroTarjetaParaGotoLimites() {
		return numeroTarjetaParaGotoLimites;
	}

	/**
	 * Sets the numero tarjeta para goto limites.
	 *
	 * @param numeroTarjetaParaGotoLimites the new numero tarjeta para goto limites
	 */
	public void setNumeroTarjetaParaGotoLimites(String numeroTarjetaParaGotoLimites) {
		this.numeroTarjetaParaGotoLimites = numeroTarjetaParaGotoLimites;
	}

	/**
	 * Gets the id notificacion promesa pago.
	 *
	 * @return the id notificacion promesa pago
	 */
	public String getIdNotificacionPromesaPago() {
		return idNotificacionPromesaPago;
	}

	/**
	 * Sets the id notificacion promesa pago.
	 *
	 * @param idNotificacionPromesaPago the new id notificacion promesa pago
	 */
	public void setIdNotificacionPromesaPago(String idNotificacionPromesaPago) {
		this.idNotificacionPromesaPago = idNotificacionPromesaPago;
	}

	/**
	 * Gets the lista pagos vencidos productos santander.
	 *
	 * @return the lista pagos vencidos productos santander
	 */
	public List<PagoPendienteView> getListaPagosVencidosProductosSantander() {
		return listaPagosVencidosProductosSantander;
	}

	/**
	 * Sets the lista pagos vencidos productos santander.
	 *
	 * @param listaPagosVencidosProductosSantander the new lista pagos vencidos productos santander
	 */
	public void setListaPagosVencidosProductosSantander(List<PagoPendienteView> listaPagosVencidosProductosSantander) {
		this.listaPagosVencidosProductosSantander = listaPagosVencidosProductosSantander;
	}
	

	
	/**
     * Gets the tipo banca plazo fijo.
     *
     * @return the tipo banca plazo fijo
     */
	public String getTipoBancaPlazoFijo() {
		return tipoBancaPlazoFijo;
	}

	/**
     * Sets the tipo banca plazo fijo.
     *
     * @param tipoBancaPlazoFijo
     *            the tipo banca plazo fijo to set
     */
	public void setTipoBancaPlazoFijo(String tipoBancaPlazoFijo) {
		this.tipoBancaPlazoFijo = tipoBancaPlazoFijo;
	}

	/**
	 * Gets the operacion para token.
	 *
	 * @return the operacion para token
	 */
	public String getOperacionParaToken() {
		return operacionParaToken;
	}

	/**
	 * Sets the operacion para token.
	 *
	 * @param operacionParaToken the new operacion para token
	 */
	public void setOperacionParaToken(String operacionParaToken) {
		this.operacionParaToken = operacionParaToken;
	}

	/**
	 * Gets the compra dolares impcarg.
	 *
	 * @return the compraDolaresImpcarg
	 */
	public String getCompraDolaresImpcarg() {
		return compraDolaresImpcarg;
	}

	/**
	 * Sets the compra dolares impcarg.
	 *
	 * @param compraDolaresImpcarg the compraDolaresImpcarg to set
	 */
	public void setCompraDolaresImpcarg(String compraDolaresImpcarg) {
		this.compraDolaresImpcarg = compraDolaresImpcarg;
	}

	/**
	 * Gets the habilitacion women.
	 *
	 * @return the habilitacion women
	 */
	public String getHabilitacionWomen() {
		return habilitacionWomen;
	}

	/**
	 * Sets the habilitacion women.
	 *
	 * @param habilitacionWomen the new habilitacion women
	 */
	public void setHabilitacionWomen(String habilitacionWomen) {
		this.habilitacionWomen = habilitacionWomen;
	}

	/**
	 * Gets the tarjetas posibles habilitacion.
	 *
	 * @return the tarjetas posibles habilitacion
	 */
	public List<DatosTarjetaWomen> getTarjetasPosiblesHabilitacion() {
		return tarjetasPosiblesHabilitacion;
	}

	/**
	 * Sets the tarjetas posibles habilitacion.
	 *
	 * @param tarjetasPosiblesHabilitacion the new tarjetas posibles habilitacion
	 */
	public void setTarjetasPosiblesHabilitacion(List<DatosTarjetaWomen> tarjetasPosiblesHabilitacion) {
		this.tarjetasPosiblesHabilitacion = tarjetasPosiblesHabilitacion;
	}

	/**
	 * Gets the tarjetas habilitadas women.
	 *
	 * @return the tarjetas habilitadas women
	 */
	public AdhesionWomenView getTarjetasHabilitadasWomen() {
		return tarjetasHabilitadasWomen;
	}

	/**
	 * Sets the tarjetas habilitadas women.
	 *
	 * @param tarjetasHabilitadasWomen the new tarjetas habilitadas women
	 */
	public void setTarjetasHabilitadasWomen(AdhesionWomenView tarjetasHabilitadasWomen) {
		this.tarjetasHabilitadasWomen = tarjetasHabilitadasWomen;
	}

	/**
	 * Gets the acceso women deeplink.
	 *
	 * @return the acceso women deeplink
	 */
	public Boolean getAccesoWomenDeeplink() {
		return accesoWomenDeeplink;
	}
	
	/**
	 * Gets the respuesta detalle CNSCTADATO.
	 *
	 * @return the respuesta detalle CNSCTADATO
	 */
	public List<ResumenDetalleCuenta> getRespuestaDetalleCNSCTADATO() {
		return respuestaDetalleCNSCTADATO;
	}

	/**
	 * Sets the respuesta detalle CNSCTADATO.
	 *
	 * @param respuestaDetalleCNSCTADATO the new respuesta detalle CNSCTADATO
	 */
	public void setRespuestaDetalleCNSCTADATO(List<ResumenDetalleCuenta> respuestaDetalleCNSCTADATO) {
		this.respuestaDetalleCNSCTADATO = respuestaDetalleCNSCTADATO;
	}

	/**
	 * Gets the datos solicitante.
	 *
	 * @return the datos solicitante
	 */
	public DatosSolicitanteEntity getDatosSolicitante() {
		return datosSolicitante;
	}

	/**
	 * Sets the datos solicitante.
	 *
	 * @param datosSolicitante the new datos solicitante
	 */
	public void setDatosSolicitante(DatosSolicitanteEntity datosSolicitante) {
		this.datosSolicitante = datosSolicitante;
	}

	/**
	 * Gets the estado get net.
	 *
	 * @return the estado get net
	 */
	public String getEstadoGetNet() {
		return estadoGetNet;
	}

	/**
	 * Sets the estado get net.
	 *
	 * @param estadoGetNet the new estado get net
	 */
	public void setEstadoGetNet(String estadoGetNet) {
		this.estadoGetNet = estadoGetNet;
	}

	/**
	 * Gets the email get net.
	 *
	 * @return the email get net
	 */
	public String getEmailGetNet() {
		return emailGetNet;
	}

	/**
	 * Sets the email get net.
	 *
	 * @param emailGetNet the new email get net
	 */
	public void setEmailGetNet(String emailGetNet) {
		this.emailGetNet = emailGetNet;
	}

	/**
	 * Gets the cuentas habilitadas getnet.
	 *
	 * @return the cuentas habilitadas getnet
	 */
	public List<Cuenta> getCuentasHabilitadasGetnet() {
		return cuentasHabilitadasGetnet;
	}

	/**
	 * Sets the cuentas habilitadas getnet.
	 *
	 * @param cuentasHabilitadasGetnet the new cuentas habilitadas getnet
	 */
	public void setCuentasHabilitadasGetnet(List<Cuenta> cuentasHabilitadasGetnet) {
		this.cuentasHabilitadasGetnet = cuentasHabilitadasGetnet;
	}

	/**
	 * Gets the ACTADHECN L is expuesto politicamente.
	 *
	 * @return the ACTADHECN L is expuesto politicamente
	 */
	public Boolean getACTADHECNL_isExpuestoPoliticamente() {
		return ACTADHECNL_isExpuestoPoliticamente;
	}

	/**
	 * Sets the ACTADHECN L is expuesto politicamente.
	 *
	 * @param aCTADHECNL_isExpuestoPoliticamente the new ACTADHECN L is expuesto politicamente
	 */
	public void setACTADHECNL_isExpuestoPoliticamente(Boolean aCTADHECNL_isExpuestoPoliticamente) {
		ACTADHECNL_isExpuestoPoliticamente = aCTADHECNL_isExpuestoPoliticamente;
	}

	/**
	 * Sets the acceso women deeplink.
	 *
	 * @param accesoWomenDeeplink the new acceso women deeplink
	 */
	public void setAccesoWomenDeeplink(Boolean accesoWomenDeeplink) {
		this.accesoWomenDeeplink = accesoWomenDeeplink;
	}
			

	/**
	 * Gets the cantidad cuentas titulos canje.
	 *
	 * @return the cantidad cuentas titulos canje
	 */
	public int getCantidadCuentasTitulosCanje() {
		return cantidadCuentasTitulosCanje;
	}

	/**
	 * Sets the cantidad cuentas titulos canje.
	 *
	 * @param cantidadCuentasTitulosCanje the new cantidad cuentas titulos canje
	 */
	public void setCantidadCuentasTitulosCanje(int cantidadCuentasTitulosCanje) {
		this.cantidadCuentasTitulosCanje = cantidadCuentasTitulosCanje;
	}

	/**
	 * Gets the motivos turno.
	 *
	 * @return the motivos turno
	 */
	public Map<String, List<MotivoTurnoDTO>> getMotivosTurno() {
		return motivosTurno;
	}

	/**
	 * Sets the motivos turno.
	 *
	 * @param motivosTurno the new motivos turno
	 */
	public void setMotivosTurno(Map<String, List<MotivoTurnoDTO>> motivosTurno) {
		this.motivosTurno = motivosTurno;
	}

	/**
	 * Gets the cuentas inversiones PF bpriv.
	 *
	 * @return the cuentas inversiones PF bpriv
	 */
	public List<CuentaTituloView> getCuentasInversionesPFBpriv() {
		return cuentasInversionesPFBpriv;
	}

	/**
	 * Sets the cuentas inversiones PF bpriv.
	 *
	 * @param cuentasInversionesPFBpriv the new cuentas inversiones PF bpriv
	 */
	public void setCuentasInversionesPFBpriv(List<CuentaTituloView> cuentasInversionesPFBpriv) {
		this.cuentasInversionesPFBpriv = cuentasInversionesPFBpriv;
	}

	/**
	 * Gets the CNSINFIMP O categorizacion iva.
	 *
	 * @return the CNSINFIMP O categorizacion iva
	 */
	public String getCNSINFIMPO_categorizacionIva() {
		return CNSINFIMPO_categorizacionIva;
	}

	/**
	 * Sets the CNSINFIMP O categorizacion iva.
	 *
	 * @param cNSINFIMPO_categorizacionIva the new CNSINFIMP O categorizacion iva
	 */
	public void setCNSINFIMPO_categorizacionIva(String cNSINFIMPO_categorizacionIva) {
		CNSINFIMPO_categorizacionIva = cNSINFIMPO_categorizacionIva;
	}

	/**
	 * Gets the CNSINFIMP O categorizacion iibb.
	 *
	 * @return the CNSINFIMP O categorizacion iibb
	 */
	public String getCNSINFIMPO_categorizacionIibb() {
		return CNSINFIMPO_categorizacionIibb;
	}

	/**
	 * Sets the CNSINFIMP O categorizacion iibb.
	 *
	 * @param cNSINFIMPO_categorizacionIibb the new CNSINFIMP O categorizacion iibb
	 */
	public void setCNSINFIMPO_categorizacionIibb(String cNSINFIMPO_categorizacionIibb) {
		CNSINFIMPO_categorizacionIibb = cNSINFIMPO_categorizacionIibb;
	}

	/**
	 * Gets the CNSINFIMP O numero iibb.
	 *
	 * @return the CNSINFIMP O numero iibb
	 */
	public String getCNSINFIMPO_numeroIibb() {
		return CNSINFIMPO_numeroIibb;
	}

	/**
	 * Sets the CNSINFIMP O numero iibb.
	 *
	 * @param cNSINFIMPO_numeroIibb the new CNSINFIMP O numero iibb
	 */
	public void setCNSINFIMPO_numeroIibb(String cNSINFIMPO_numeroIibb) {
		CNSINFIMPO_numeroIibb = cNSINFIMPO_numeroIibb;
	}

	/**
	 * Gets the contador adhesion getnet.
	 *
	 * @return the contador adhesion getnet
	 */
	public ContadorIntentos getContadorAdhesionGetnet() {
		return contadorAdhesionGetnet;
	}

	/**
	 * Sets the contador adhesion getnet.
	 *
	 * @param contadorAdhesionGetnet the new contador adhesion getnet
	 */
	public void setContadorAdhesionGetnet(ContadorIntentos contadorAdhesionGetnet) {
		this.contadorAdhesionGetnet = contadorAdhesionGetnet;
	}

	/**
	 * Gets the comprobante getnet DTO.
	 *
	 * @return the comprobante getnet DTO
	 */
	public GetnetAdhesionDTO getComprobanteGetnetDTO() {
		return comprobanteGetnetDTO;
	}

	/**
	 * Sets the comprobante getnet DTO.
	 *
	 * @param comprobanteGetnetDTO the new comprobante getnet DTO
	 */
	public void setComprobanteGetnetDTO(GetnetAdhesionDTO comprobanteGetnetDTO) {
		this.comprobanteGetnetDTO = comprobanteGetnetDTO;
	}

	/**
	 * Gets the getnet agregado.
	 *
	 * @return the getnet agregado
	 */
	public Boolean getGetnetAgregado() {
		return getnetAgregado;
	}

	/**
	 * Sets the getnet agregado.
	 *
	 * @param getnetAgregado the new getnet agregado
	 */
	public void setGetnetAgregado(Boolean getnetAgregado) {
		this.getnetAgregado = getnetAgregado;
	}

	/**
	 * Gets the recorrio cuentas getnet.
	 *
	 * @return the recorrio cuentas getnet
	 */
	public Boolean getRecorrioCuentasGetnet() {
		return recorrioCuentasGetnet;
	}

	/**
	 * Sets the recorrio cuentas getnet.
	 *
	 * @param recorrioCuentasGetnet the new recorrio cuentas getnet
	 */
	public void setRecorrioCuentasGetnet(Boolean recorrioCuentasGetnet) {
		this.recorrioCuentasGetnet = recorrioCuentasGetnet;
	}

	/**
	 * Gets the legal tyc getnet.
	 *
	 * @return the legal tyc getnet
	 */
	public String getLegalTycGetnet() {
		return legalTycGetnet;
	}

	/**
	 * Sets the legal tyc getnet.
	 *
	 * @param legalTycGetnet the new legal tyc getnet
	 */
	public void setLegalTycGetnet(String legalTycGetnet) {
		this.legalTycGetnet = legalTycGetnet;
	}

	

	public List<IMF> getListadoIMF() {
		return listadoIMF;
	}

	public void setListadoIMF(List<IMF> listadoIMF) {
		this.listadoIMF = listadoIMF;
	}

	/**
	 * Gets the datos comprobante extraccion efectivo view.
	 *
	 * @return the datos comprobante extraccion efectivo view
	 */
	public DatosComprobanteExtraccionEfectivoView getDatosComprobanteExtraccionEfectivoView() {
		return datosComprobanteExtraccionEfectivoView;
	}

	/**
	 * Sets the datos comprobante extraccion efectivo view.
	 *
	 * @param datosComprobanteExtraccionEfectivoView the new datos comprobante extraccion efectivo view
	 */
	public void setDatosComprobanteExtraccionEfectivoView(
			DatosComprobanteExtraccionEfectivoView datosComprobanteExtraccionEfectivoView) {
		this.datosComprobanteExtraccionEfectivoView = datosComprobanteExtraccionEfectivoView;
	}
			
	/**
	 * Gets the estadistica OK descarga PDF extraccion efectivo grabada.
	 *
	 * @return the estadistica OK descarga PDF extraccion efectivo grabada
	 */
	public Boolean getEstadisticaOKDescargaPDFExtraccionEfectivoGrabada() {
		return estadisticaOKDescargaPDFExtraccionEfectivoGrabada;
	}

	/**
	 * Sets the estadistica OK descarga PDF extraccion efectivo grabada.
	 *
	 * @param estadisticaOKDescargaPDFExtraccionEfectivoGrabada the new estadistica OK descarga PDF extraccion efectivo grabada
	 */
	public void setEstadisticaOKDescargaPDFExtraccionEfectivoGrabada(
			Boolean estadisticaOKDescargaPDFExtraccionEfectivoGrabada) {
		this.estadisticaOKDescargaPDFExtraccionEfectivoGrabada = estadisticaOKDescargaPDFExtraccionEfectivoGrabada;
	}

	/**
	 * Gets the respuesta numero empresa celular clave online.
	 *
	 * @return the respuesta numero empresa celular clave online
	 */
	public String getRespuestaNumeroEmpresaCelularClaveOnline() {
		return respuestaNumeroEmpresaCelularClaveOnline;
	}

	/**
	 * Sets the respuesta numero empresa celular clave online.
	 *
	 * @param respuestaNumeroEmpresaCelularClaveOnline the new respuesta numero empresa celular clave online
	 */
	public void setRespuestaNumeroEmpresaCelularClaveOnline(String respuestaNumeroEmpresaCelularClaveOnline) {
		this.respuestaNumeroEmpresaCelularClaveOnline = respuestaNumeroEmpresaCelularClaveOnline;
	}

	public String getEmpresaCelularServicioIdesgiclie() {
		return empresaCelularServicioIdesgiclie;
	}

	public void setEmpresaCelularServicioIdesgiclie(String empresaCelularServicioIdesgiclie) {
		this.empresaCelularServicioIdesgiclie = empresaCelularServicioIdesgiclie;
	}

	public DatosComprobanteDevolucionDA getDatosComprobanteDevolucionDA() {
		return datosComprobanteDevolucionDA;
	}

	public void setDatosComprobanteDevolucionDA(DatosComprobanteDevolucionDA datosComprobanteDevolucionDA) {
		this.datosComprobanteDevolucionDA = datosComprobanteDevolucionDA;
	}
		
	public BigDecimal getMaxOfertaPrestamoPreaprobado() {
		return maxOfertaPrestamoPreaprobado;
	}

	public void setMaxOfertaPrestamoPreaprobado(BigDecimal maxOfertaPrestamoPreaprobado) {
		this.maxOfertaPrestamoPreaprobado = maxOfertaPrestamoPreaprobado;
	}
	
	public String getDatosPrestamoSueldosTasaSubsidiada() {
		return datosPrestamoSueldosTasaSubsidiada;
	}

	public void setDatosPrestamoSueldosTasaSubsidiada(String datosPrestamoSueldosTasaSubsidiada) {
		this.datosPrestamoSueldosTasaSubsidiada = datosPrestamoSueldosTasaSubsidiada;
	}


	public ComprobantePrestamoTasaSubsidiadaView getComprobantePrestamoTasaSub() {
		return comprobantePrestamoTasaSub;
	}

	public void setComprobantePrestamoTasaSub(ComprobantePrestamoTasaSubsidiadaView comprobantePrestamoTasaSub) {
		this.comprobantePrestamoTasaSub = comprobantePrestamoTasaSub;
	}

	public List<LimitePrestamoPreaprobadoView> getLimitesPreaprobado() {
		return limitesPreaprobado;
	}

	public void setLimitesPreaprobado(List<LimitePrestamoPreaprobadoView> limitesPreaprobado) {
		this.limitesPreaprobado = limitesPreaprobado;
	}

	public PrestamoPreaprobadoMonoproductoInOutView getPrestamoPreaprobadoMonoproducto() {
		return prestamoPreaprobadoMonoproducto;
	}

	public void setPrestamoPreaprobadoMonoproducto(
			PrestamoPreaprobadoMonoproductoInOutView prestamoPreaprobadoMonoproducto) {
		this.prestamoPreaprobadoMonoproducto = prestamoPreaprobadoMonoproducto;
	}

	public String getTipoDePrestamoATP() {
		return tipoDePrestamoATP;
	}

	public void setTipoDePrestamoATP(String tipoDePrestamoATP) {
		this.tipoDePrestamoATP = tipoDePrestamoATP;
	}

	public String getJwt(){
	    return this.jwt;
    }

	public void setJwt(String jwt){
	    this.jwt = jwt;
    }

    public Boolean getParticipaEnSuperclubPlus() { return this.participaEnSuperclubPlus; }

    public void setParticipaEnSuperclubPlus(Boolean participaEnSuperclubPlus) {
        this.participaEnSuperclubPlus = participaEnSuperclubPlus;
    }
    
	public ComprobanteCancelacionTotalPrestamoView getComprobanteCancelacionTotalPrestamo() {
		return comprobanteCancelacionTotalPrestamo;
	}

	public void setComprobanteCancelacionTotalPrestamo(
			ComprobanteCancelacionTotalPrestamoView comprobanteCancelacionTotalPrestamo) {
		this.comprobanteCancelacionTotalPrestamo = comprobanteCancelacionTotalPrestamo;
	}

	public OperadorEjecutivo getOperadorEjecutivo() {
		return operadorEjecutivo;
	}

	public void setOperadorEjecutivo(OperadorEjecutivo operadorEjecutivo) {
		this.operadorEjecutivo = operadorEjecutivo;
	}		
		

	public String getImporteUSDAcumCpr() {
		return importeUSDAcumCpr;
	}

	public void setImporteUSDAcumCpr(String importeUSDAcumCpr) {
		this.importeUSDAcumCpr = importeUSDAcumCpr;
	}

	public Respuesta<List<BonificacionVigenteView>> getListaBonificaciones() {
		return listaBonificaciones;
	}

	public void setListaBonificaciones(Respuesta<List<BonificacionVigenteView>> listaBonificaciones) {
		this.listaBonificaciones = listaBonificaciones;
	}
	

	public Boolean isAplicaRefi() {
		return aplicaRefi;
	}

	public void setAplicaRefi(Boolean aplicaRefi) {
		this.aplicaRefi = aplicaRefi;
	}

	public Map<String, Boolean> getHabilitadoFF() {
		return habilitadoFF;
	}

	public void setHabilitadoFF(Map<String, Boolean> habilitadoFF) {
		this.habilitadoFF = habilitadoFF;
	}

	/**
	 * @return the pdfData
	 */
	public DeleteAccountView getPdfData() {
		return pdfData;
	}

	/**
	 * @param pdfData the pdfData to set
	 */
	public void setPdfData(DeleteAccountView pdfData) {
		this.pdfData = pdfData;
	}

	/**
	 * @return the descCtaAEliminar
	 */
	public String getDescCtaAEliminar() {
		return descCtaAEliminar;
	}

	/**
	 * @param descCtaAEliminar the descCtaAEliminar to set
	 */
	public void setDescCtaAEliminar(String descCtaAEliminar) {
		this.descCtaAEliminar = descCtaAEliminar;
	}

    /**
     * Gets the detalle debin.
     *
     * @return the detalle debin
     */
    public ConsultaDetalleDebinWSOutDTO getDetalleDebin() {
        return detalleDebin;
    }

    /**
     * Sets the detalle debin.
     *
     * @param detalleDebin
     *            the new detalle debin
     */
    public void setDetalleDebin(ConsultaDetalleDebinWSOutDTO detalleDebin) {
        this.detalleDebin = detalleDebin;
    }

    /**
     * Gets the idCuentasDebin.
     *
     * @return the idCuentasDebin
     */
    public Map<Integer, String> getIdCuentasDebin() {
        return idCuentasDebin;
    }

    /**
     * Sets the idCuentasDebin.
     *
     * @param idCuentasDebin
     *            the new idCuentasDebin
     */
    public void setIdCuentasDebin(Map<Integer, String> idCuentasDebin) {
        this.idCuentasDebin = idCuentasDebin;
    }

    /**
     * Gets the nro pagina debin estado usuario.
     *
     * @return the nro pagina debin estado usuario
     */
    public int getNroPaginaDebinEstadoUsuario() {
        return nroPaginaDebinEstadoUsuario;
    }

    /**
     * Sets the nro pagina debin estado usuario.
     *
     * @param nroPaginaDebinEstadoUsuario
     *            the new nro pagina debin estado usuario
     */
    public void setNroPaginaDebinEstadoUsuario(int nroPaginaDebinEstadoUsuario) {
        this.nroPaginaDebinEstadoUsuario = nroPaginaDebinEstadoUsuario;
    }

    /**
     * Gets the nro pagina debin iniciado.
     *
     * @return the nro pagina debin iniciado
     */
    public int getNroPaginaDebinIniciado() {
        return nroPaginaDebinIniciado;
    }

    /**
     * Sets the nro pagina debin iniciado.
     *
     * @param nroPaginaDebinIniciado
     *            the new nro pagina debin iniciado
     */
    public void setNroPaginaDebinIniciado(int nroPaginaDebinIniciado) {
        this.nroPaginaDebinIniciado = nroPaginaDebinIniciado;
    }

    /**
     * Gets the nro pagina debin acreditado.
     *
     * @return the nro pagina debin acreditado
     */
    public int getNroPaginaDebinAcreditado() {
        return nroPaginaDebinAcreditado;
    }

    /**
     * Sets the nro pagina debin acreditado.
     *
     * @param nroPaginaDebinAcreditado
     *            the new nro pagina debin acreditado
     */
    public void setNroPaginaDebinAcreditado(int nroPaginaDebinAcreditado) {
        this.nroPaginaDebinAcreditado = nroPaginaDebinAcreditado;
    }

	public DatosComprobanteDebinRecurrente getDatosComprobanteDebinRecurrente() {
		return datosComprobanteDebinRecurrente;
	}

	public void setDatosComprobanteDebinRecurrente(DatosComprobanteDebinRecurrente datosComprobanteDebinRecurrente) {
		this.datosComprobanteDebinRecurrente = datosComprobanteDebinRecurrente;
	}

    public Boolean getDesafioPrestamosActivo() {
        return desafioPrestamosActivo;
    }

    public void setDesafioPrestamosActivo(Boolean activo) {
        this.desafioPrestamosActivo = activo;
    }

	public Boolean getContactabilidadAceptada() {
		return contactabilidadAceptada;
	}

	public void setContactabilidadAceptada(Boolean contactabilidadAceptada) {
		this.contactabilidadAceptada = contactabilidadAceptada;
	}

	public String getNumeroTarjetaDebitoAValidar() {
		return numeroTarjetaDebitoAValidar;
	}

	public void setNumeroTarjetaDebitoAValidar(String numeroTarjetaDebitoAValidar) {
		this.numeroTarjetaDebitoAValidar = numeroTarjetaDebitoAValidar;
	}

	public int getNumeroIntentoValidarPinBanelco() {
		return numeroIntentoValidarPinBanelco;
	}

	public void setNumeroIntentoValidarPinBanelco(int numeroIntentoValidarPinBanelco) {
		this.numeroIntentoValidarPinBanelco = numeroIntentoValidarPinBanelco;
	}

    public Boolean getBajaCuentaWarning() {
        return bajaCuentaWarning;
    }

    public void setBajaCuentaWarning(Boolean bajaCuentaWarning) {
        this.bajaCuentaWarning = bajaCuentaWarning;
    }

    public String getMensajeInformativo() {
        return mensajeInformativo;
    }

    public void setMensajeInformativo(String mensajeInformativo) {
        this.mensajeInformativo = mensajeInformativo;
    }

	public OfertaPoliticaPrivacidadDTO getOfertaPoliticaPrivacidad() {
		return ofertaPoliticaPrivacidad;
	}
	
	public void setOfertaPoliticaPrivacidad(OfertaPoliticaPrivacidadDTO ofertaPoliticaPrivacidad) {
		this.ofertaPoliticaPrivacidad = ofertaPoliticaPrivacidad;
	}

	public ComprobanteTurnoInEntity getComprobanteTurno() {
		return comprobanteTurno;
	}

	public void setComprobanteTurno(ComprobanteTurnoInEntity comprobanteTurno) {
		this.comprobanteTurno = comprobanteTurno;
	}

	public Double getDisponiblePrestamos() {
		return disponiblePrestamos;
	}

	public void setDisponiblePrestamos(Double disponiblePrestamos) {
		this.disponiblePrestamos = disponiblePrestamos;
	}

	public Boolean isPrimerIngreso() {
		return primerIngreso;
	}

	public void setPrimerIngreso(Boolean primerIngreso) {
		this.primerIngreso = primerIngreso;
	}

	public boolean isErrorSaldo() {
		return errorSaldo;
	}

	public void setErrorSaldo(boolean errorSaldo) {
		this.errorSaldo = errorSaldo;
	}

	public Boolean getPermisoGrabadoEstadistica() {
		return permisoGrabadoEstadistica;
	}

	public void setPermisoGrabadoEstadistica(Boolean permisoGrabadoEstadistica) {
		this.permisoGrabadoEstadistica = permisoGrabadoEstadistica;
	}

	public boolean isPermisoApiAuth() {
		return permisoApiAuth;
	}

	public void setPermisoApiAuth(boolean permisoApiAuth) {
		this.permisoApiAuth = permisoApiAuth;
	}

    public String getTransactionTokenRedemptionFund() {
        return transactionTokenRedemptionFund;
    }

    public void setTransactionTokenRedemptionFund(String transactionTokenRedemptionFund) {
        this.transactionTokenRedemptionFund = transactionTokenRedemptionFund;
    }
	public Integer getCantidadIntentosSMS() {
		return cantidadIntentosSMS;
	}

	public void setCantidadIntentosSMS(Integer cantidadIntentosSMS) {
		this.cantidadIntentosSMS = cantidadIntentosSMS;
	}

    public CambioDatosContactoResponse getCambioDatosContactoResponse() {
        return cambioDatosContactoResponse;
    }

    public void setCambioDatosContactoResponse(CambioDatosContactoResponse cambioDatosContactoResponse) {
        this.cambioDatosContactoResponse = cambioDatosContactoResponse;
    }

    public Boolean getExisteClienteTyC() {
        return existeClienteTyC;
    }

    public void setExisteClienteTyC(Boolean existeClienteTyC) {
        this.existeClienteTyC = existeClienteTyC;
    }

	public String getBarrioNormalizacionDomicilio() {
		return barrioNormalizacionDomicilio;
	}

	public void setBarrioNormalizacionDomicilio(String barrioNormalizacionDomicilio) {
		this.barrioNormalizacionDomicilio = barrioNormalizacionDomicilio;
	}

    public Boolean getPas() {
        return isPas;
    }

    public void setPas(Boolean pas) {
        isPas = pas;
    }
}

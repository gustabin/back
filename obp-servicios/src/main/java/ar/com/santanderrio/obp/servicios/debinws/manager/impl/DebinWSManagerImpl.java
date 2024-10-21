package ar.com.santanderrio.obp.servicios.debinws.manager.impl;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.debin.CuentaDebinDTO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.PagoDebinDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.PagarDebinEntityBuilder;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.bo.EstadoRecurrenciaDebinEnum;
import ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSBO;
import ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSSolicitudesBO;
import ar.com.santanderrio.obp.servicios.debinws.common.DebinWSConstants;
import ar.com.santanderrio.obp.servicios.debinws.common.EstadoDebinEnum;
import ar.com.santanderrio.obp.servicios.debinws.dto.*;
import ar.com.santanderrio.obp.servicios.debinws.manager.DebinWSManager;
import ar.com.santanderrio.obp.servicios.debinws.utils.DebinWSUtils;
import ar.com.santanderrio.obp.servicios.debinws.view.*;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserResponseData;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * DebinWSManagerImpl.
 */
@Component
@Qualifier("debinWSManagerImpl")
public class DebinWSManagerImpl implements DebinWSManager {

    /** Debin WS BO. */
    @Autowired
    @Qualifier("debinWSBOImpl")
    private DebinWSBO debinwsBO;
    
	/** The scomp BO. */
	@Autowired
	private ScompBO scompBO;
    
    /** The solicitudes debin WSBO. */
    @Autowired
    @Qualifier("debinWSSolicitudesBOImpl")
    private DebinWSSolicitudesBO solicitudesDebinWSBO;

    /** The respuesta factory. */
    @Autowired
    protected RespuestaFactory respuestaFactory;

    /** The cuenta manager. */
    @Autowired
    private CuentaManager cuentaManager;

    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;
    
    @Autowired
    private ClienteManager clienteManager;

    /** The Constant LOGGER. */
    protected static final Logger LOGGER = LoggerFactory.getLogger(DebinWSManagerImpl.class);

    /** The Constant CANAL_DEFAULT. */
    protected static final String CANAL_DEFAULT = "E";
    
    /** The Constant TIPO_CONSULTA_DEFAULT. */
    private static final String TIPO_CONSULTA_DEFAULT = "Debin";

    /** The Constant TIPO_CONSULTA_DEBINPLF. */
    private static final String TIPO_CONSULTA_DEBINPLF = "debinplf";

    /** The Constant TIPO_CONSULTA_RECURRENTE. */
    private static final String TIPO_CONSULTA_RECURRENTE = "recurrente";

    /** The Constant FORMATO_FECHA. */
    private static final String FORMATO_FECHA = "dd/MM/yyyy";
    
    /** The Constant MONEDA_DOLAR_WS. */
    protected static final String MONEDA_DOLAR_BANELCO = "840";

    /** The Constant MONEDA_PESOS_WS. */
    protected static final String MONEDA_PESOS_BANELCO = "032";

    /** The constant mmoneda cuenta unica. */
    protected static final String MONEDA_CUENTA_UNICA = "000";

    /** The Constant SIMBOLO_PESOS. */
    protected static final String SIMBOLO_PESOS = "$";

    /** The Constant SIMBOLO_DOLARES. */
   	protected static final String SIMBOLO_DOLARES = "u$s";

    /** The Constant FORMATO_NRO_COMPROBANTE. */
    private static final String FORMATO_NRO_COMPROBANTE = "yyMMddHHmmss";

    /** The conceptos str. */
    @Value("${DEBINWS.CONCEPTOS}")
    private String conceptosStr;

    /** The cantidad dias default. */
    @Value("${DEBINWS.CONSULTA.CANTIDADDIAS}")
    private int cantidadDiasDefault;
    
    /** The valor desafio pagar debin WS. */
    @Value("${TRJCOORD.OPERAINDISTINTO.PAGARDEBIN}")
    private String valorDesafioPagarDebinWS;
    
    /** The sesion cliente. */
    @Autowired
    protected SesionCliente sesionCliente;
    
    /** sesion parametros. */
    @Autowired
    protected SesionParametros sesionParametros;
    
    /** The estadistica manager. */
    @Autowired
    protected EstadisticaManager estadisticaManager;

    /** The mensaje manager. */
    @Autowired
    protected MensajeManager mensajeManager;

    /** The autentificacion manager. */
    @Autowired
    protected AutentificacionManager autentificacionManager;

    /** The pagar debin WS view desafio operacion RSA. */
    @Autowired
    private DesafioOperacionRSA<PagarDebinWSView> pagarDebinWSViewDesafioOperacionRSA;
    
    /** The Constant ELIMINACION. */
    private static final String ELIMINACION = "EliminacionDebin";
    
    /** The Constant DOLARES. */
    private static final String DOLARES = "Dólares";
    
    /** The Constant PESOS. */
    private static final String PESOS = "Pesos";

	/** The rsa manager. */
	@Autowired
	private RsaManager rsaManager;

    /**
	 * obtenerDebines.
	 *
	 * @param consultaDebinInView
	 *            the consulta debin in view
	 * @return the respuesta
	 */
    @Override
    public Respuesta<ConsultaDebinWSOutView> consultaDebin(ConsultaDebinWSInView consultaDebinInView) {
		LOGGER.info("DebinWSManager iniciando obtener debines");
		if (consultaDebinInView.isPrimerLlamado()) {
			sesionParametros.setIdDebinesRecurrencias(new ArrayList<String>());
			restaurarNroPaginaSesion();
		}
		ConsultaDebinWSInDTO consultaDebinesInDTO = generarConsultaDebinWSIn(consultaDebinInView);
		Respuesta<ConsultaDebinWSOutDTO> respuestaBO = debinwsBO.consultaDebin(consultaDebinesInDTO);
		ConsultaDebinWSOutView respConsultaDebinView = null;
		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			respConsultaDebinView = generarConsultaDebinWSOutView(respuestaBO.getRespuesta(), consultaDebinesInDTO);
			LOGGER.info("DebinWSManager OK");
			return respuestaFactory.crearRespuestaOk(respConsultaDebinView);
		} else if (EstadoRespuesta.WARNING.equals(respuestaBO.getEstadoRespuesta())) {
			String tipoError = respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError();
			LOGGER.info("DebinWSManager WARNING");
			if (TipoError.DEBINWS_WARNING_LISTA_ERROR_PARCIAL.getDescripcion().equals(tipoError)) {
				respConsultaDebinView = generarConsultaDebinWSOutView(respuestaBO.getRespuesta(), consultaDebinesInDTO);
				return respuestaFactory.crearRespuestaWarning(respConsultaDebinView, "",
						TipoError.DEBINWS_WARNING_LISTA_ERROR_PARCIAL,
						CodigoMensajeConstantes.DEBINWS_WARNING_LISTA_ERROR_PARCIAL);
			} else {
				return crearRespuestaWarningConsultaDebin(respConsultaDebinView, consultaDebinesInDTO,
						consultaDebinInView);
			}
		} else {
			LOGGER.info("DebinWSManager ERROR");
			return respuestaFactory.crearRespuestaError(ConsultaDebinWSOutView.class,
					respuestaBO.getItemsMensajeRespuesta());
		}
	}

	/**
	 * 
	 * Crea respuesta warning para consulta de debines
	 * 
	 * @param respConsultaDebinView
	 * @param consultaDebinesInDTO
	 * @param consultaDebinInView
	 * @return
	 */
    private Respuesta<ConsultaDebinWSOutView> crearRespuestaWarningConsultaDebin(ConsultaDebinWSOutView respConsultaDebinView, ConsultaDebinWSInDTO consultaDebinesInDTO, ConsultaDebinWSInView consultaDebinInView) {

        respConsultaDebinView = generarConsultaDebinWSOutView(new ConsultaDebinWSOutDTO(), consultaDebinesInDTO);
		if (consultaDebinInView.getEstado() != null || consultaDebinInView.getFechaDesde() != null
				|| consultaDebinInView.getFechaHasta() != null) {
			return respuestaFactory.crearRespuestaWarning(respConsultaDebinView, StringUtils.EMPTY,
					TipoError.DEBINWS_WARNING_SIN_DATOS_FILTRO, CodigoMensajeConstantes.DEBINWS_WARNING_DEBIN_FILTRO);
		} else {
			String mensaje;
			if (consultaDebinInView.getConsultaDesdeRecibidos()) {
				mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_WARNING_SIN_DATOS_INIACRE).getMensaje();
			} else {
				mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_WARNING_SIN_DATOS).getMensaje();
			}
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setTipoError(TipoError.DEBINWS_WARNING_SIN_DATOS.getDescripcion());
			itemMensajeRespuesta.setTag(StringUtils.EMPTY);
			itemMensajeRespuesta.setMensaje(DebinWSUtils.obtenerMensajeConsulta(mensaje, consultaDebinesInDTO.getTipo()));
			return respuestaFactory.crearRespuestaWarning(ConsultaDebinWSOutView.class, respConsultaDebinView, Arrays.asList(itemMensajeRespuesta));
		}
	}

    /**
	 * Generar consulta debin WS out view.
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @param consultaDebinesInDTO
	 *            the consulta debines in DTO
	 * @return the consulta debin WS out view
	 */
    private ConsultaDebinWSOutView generarConsultaDebinWSOutView(ConsultaDebinWSOutDTO respuestaDTO, ConsultaDebinWSInDTO consultaDebinesInDTO) {
        List<DebinWSGrillaView> listaDebinView = new ArrayList<DebinWSGrillaView>();
        LOGGER.info("DebinWSManager iniciando generarConsultaDebinWSOutView");
        for (DebinWSDTO debinDTO : respuestaDTO.getDebinesDTO()) {
            DebinWSGrillaView debinView = new DebinWSGrillaView();
            if (debinDTO.getFechaCreacion() != null) {
                debinView.setFechaCreacion(new SimpleDateFormat(FORMATO_FECHA).format(debinDTO.getFechaCreacion()));
            }
            if (debinDTO.getFechaVencimiento() != null) {
            	debinView.setFechaVencimiento(new SimpleDateFormat(FORMATO_FECHA).format(debinDTO.getFechaVencimiento()));
            }
            String importe = ISBANStringUtils.formatearSaldo(new BigDecimal(debinDTO.getImporte()));
            debinView.setImporte(importe);
            debinView.setMoneda(debinDTO.getMoneda());
            String descConcepto = obtenerDescripcionConcepto(debinDTO.getConcepto());
            debinView.setConceptoValido(!StringUtils.EMPTY.equals(descConcepto));
            debinView.setCuitSolicitante(ISBANStringUtils.agregarGuionesANumeroCuitCuil(debinDTO.getCuitSolicitante()));
            debinView.setNombreSolicitante(debinDTO.getNombreSolicitante());
            debinView.setNombreDestinatario(debinDTO.getNombreDestinatario());
            debinView.setDebinId(debinDTO.getDebinId());
            debinView.setEstado(debinDTO.getEstado() == null ? "" :   debinDTO.getEstado().getDescView());
            sesionParametros.getIdDebinesRecurrencias().add(debinDTO.getDebinId());
            listaDebinView.add(debinView);
        }
        ConsultaDebinWSOutView respuestaView = new ConsultaDebinWSOutView();
        respuestaView.setListaDebin(listaDebinView);
        respuestaView.setHayMasRegistros(validarNroPaginasEnSesion(consultaDebinesInDTO));
        respuestaView.setEstadosBusqueda(consultaDebinesInDTO.getConsultaComprador() ? EstadoDebinEnum.obtenerEstadosRecibidos() : EstadoDebinEnum.obtenerEstadosEnviados());
		CabeceraGrillaDebinView cabeceraGrillaDebinView = obtenerHeaderCuentas();
		respuestaView.setNumeroCuentaDolares(cabeceraGrillaDebinView.getNumeroCuentaDolares());
		respuestaView.setNumeroCuentaPesos(cabeceraGrillaDebinView.getNumeroCuentaPesos());
		respuestaView.setTituloCuentaDolares(cabeceraGrillaDebinView.getTituloCuentaDolares());
		respuestaView.setTituloCuentaPesos(cabeceraGrillaDebinView.getTituloCuentaPesos());
        return respuestaView;
    }

    /**
	 * Validar nro paginas en sesion.
	 *
	 * @param consultaDebinesInDTO
	 *            the consulta debines in DTO
	 * @return true, if successful
	 */
    private boolean validarNroPaginasEnSesion(ConsultaDebinWSInDTO consultaDebinesInDTO) {
        LOGGER.info("DebinWSManager validarNroPaginasEnSesion");
        if (consultaDebinesInDTO.getAplicaFiltro()) {
        	if (sesionParametros.getNroPaginaDebinEstadoUsuario() > 1   && sesionParametros.getNroPaginaDebinEstadoUsuario() != 99) {
        		return true;
        	}
        } else {
		    if ((sesionParametros.getNroPaginaDebinAcreditado() > 1 && sesionParametros.getNroPaginaDebinAcreditado() != 99 )  &&
		            (EstadoDebinEnum.ACREDITADO.equals(consultaDebinesInDTO.getPrimerEstadoBusqueda().getEstado()) ||
		            EstadoDebinEnum.ACREDITADO.equals(consultaDebinesInDTO.getSegundoEstadoBusqueda().getEstado()) )) {
		            return true;
		    }
		    if ((sesionParametros.getNroPaginaDebinIniciado() > 1 && sesionParametros.getNroPaginaDebinIniciado() != 99 )  &&
		                (EstadoDebinEnum.INICIADO.equals(consultaDebinesInDTO.getPrimerEstadoBusqueda().getEstado()) ||
		                EstadoDebinEnum.INICIADO.equals(consultaDebinesInDTO.getSegundoEstadoBusqueda().getEstado()) )) {
		                return true;
		    }
        }
        return false;
    }

    /**
	 * generarConsultaDebines.
	 *
	 * @param consultaDebinInView
	 *            the consulta debin in view
	 * @return the consulta debin WS in DTO
     * @throws ParseException 
	 */
    private ConsultaDebinWSInDTO generarConsultaDebinWSIn(ConsultaDebinWSInView consultaDebinInView) {
        LOGGER.info("DebinWSManager generando datos de consulta in");
        Cliente cliente = sesionCliente.getCliente();
        ConsultaDebinWSInDTO consultaInDTO = new ConsultaDebinWSInDTO();
        consultaInDTO.setCanal(CANAL_DEFAULT);
        consultaInDTO.setConsultaComprador(consultaDebinInView.getConsultaDesdeRecibidos());
        consultaInDTO.setNroDocumento(cliente.getDni());
        consultaInDTO.setTipoDocumento(cliente.getTipoDocumento());
        EstadoDebinWSBusquedaDTO primerEstadoDTO = new EstadoDebinWSBusquedaDTO();
        EstadoDebinWSBusquedaDTO segundoEstadoDTO = new EstadoDebinWSBusquedaDTO();
        if (consultaDebinInView.getEstado() != null) {
            EstadoDebinEnum estadoBusquedaFiltro = EstadoDebinEnum.obtenerEstadoPorId(consultaDebinInView.getEstado());
            primerEstadoDTO.setNroPagina(sesionParametros.getNroPaginaDebinEstadoUsuario());
            primerEstadoDTO.setEstado(estadoBusquedaFiltro);;
            consultaInDTO.setPrimerEstadoBusqueda(primerEstadoDTO);
            if (consultaDebinInView.isPrimerLlamado()) {
                segundoEstadoDTO.setNroPagina(sesionParametros.getNroPaginaDebinEstadoUsuario() + 1);
                segundoEstadoDTO.setEstado(estadoBusquedaFiltro);
                consultaInDTO.setSegundoEstadoBusqueda(segundoEstadoDTO);
            }
            consultaInDTO.setAplicaFiltro(true);
        } else if (consultaInDTO.getConsultaComprador()) {
            primerEstadoDTO.setNroPagina(sesionParametros.getNroPaginaDebinIniciado());
            primerEstadoDTO.setEstado(EstadoDebinEnum.INICIADO);
            consultaInDTO.setPrimerEstadoBusqueda(primerEstadoDTO); 
            segundoEstadoDTO.setNroPagina(sesionParametros.getNroPaginaDebinAcreditado());
            segundoEstadoDTO.setEstado(EstadoDebinEnum.ACREDITADO);
            consultaInDTO.setSegundoEstadoBusqueda(segundoEstadoDTO); 
        } else {
            primerEstadoDTO.setNroPagina(sesionParametros.getNroPaginaDebinIniciado());
            primerEstadoDTO.setEstado(EstadoDebinEnum.INICIADO);
            consultaInDTO.setPrimerEstadoBusqueda(primerEstadoDTO); 
            segundoEstadoDTO.setNroPagina(sesionParametros.getNroPaginaDebinIniciado() + 1);
            segundoEstadoDTO.setEstado(EstadoDebinEnum.INICIADO);
            consultaInDTO.setSegundoEstadoBusqueda(segundoEstadoDTO); 
        }

        String tipoConsulta = null;

        if(TIPO_CONSULTA_DEBINPLF.equals(consultaDebinInView.getTipoConsulta())){
            tipoConsulta = TIPO_CONSULTA_DEBINPLF;
        }else if(TIPO_CONSULTA_RECURRENTE.equals(consultaDebinInView.getTipoConsulta())){
            tipoConsulta = TIPO_CONSULTA_RECURRENTE;
            consultaInDTO.setIdRecurrencia(consultaDebinInView.getIdRecurrencia());
        }else{
            tipoConsulta = TIPO_CONSULTA_DEFAULT;
        }
        consultaInDTO.setTipo(tipoConsulta);
        consultaInDTO.setFechaHasta(obtenerFechaHastaBusquedaDebin(consultaDebinInView.getFechaHasta()));
        consultaInDTO.setFechaDesde(obtenerFechaDesdeBusquedaDebin(consultaDebinInView.getFechaDesde()));
        return consultaInDTO;
    }

    /**
     * Obtiene la fecha hasta para la consulta de DEBIN
     * 
     * @param fechaHasta
     * @return
     */
    private Date obtenerFechaHastaBusquedaDebin(String fechaHasta) {
        Calendar calendarHasta = new GregorianCalendar();
        calendarHasta.setTime(new Date());
        if (fechaHasta != null) {
        	Date fechaDate;
			try {
				fechaDate = new SimpleDateFormat(FORMATO_FECHA).parse(fechaHasta);
	        	calendarHasta.setTime(fechaDate);       
			} catch (ParseException e) {
				LOGGER.error("Error parseando fecha hasta filtro DEBIN, se utilizara la fecha default");
			}
        }
        calendarHasta.set(Calendar.MILLISECOND, 0);
        calendarHasta.set(Calendar.SECOND, 59);
        calendarHasta.set(Calendar.MINUTE, 59);
        calendarHasta.set(Calendar.HOUR_OF_DAY, 23);
        return calendarHasta.getTime();
	}

    /**
     * Obtiene la fecha desde para la consulta de DEBIN
     * 
     * @param fechaDesde
     * @return Date
     */
	private Date obtenerFechaDesdeBusquedaDebin(String fechaDesde) {
        Calendar calendarDesde = new GregorianCalendar();
        calendarDesde.setTime(new Date());
        calendarDesde.add(Calendar.DAY_OF_YEAR, -cantidadDiasDefault);
        if (fechaDesde != null) {
        	Date fechaDate;
			try {
				fechaDate = new SimpleDateFormat(FORMATO_FECHA).parse(fechaDesde);
				calendarDesde.setTime(fechaDate);       
			} catch (ParseException e) {
				LOGGER.error("Error parseando fecha desde filtro DEBIN, se utilizara la fecha default");
			}
        }
        calendarDesde.set(Calendar.SECOND, 00);
        calendarDesde.set(Calendar.MINUTE, 00);
        calendarDesde.set(Calendar.HOUR_OF_DAY, 00);
        return calendarDesde.getTime();
	}

	/**
     * consultaDetalleDebin.
     *
     * @param consultaDetalleDebinWSInView the consulta detalle debin WS in view
     * @return the respuesta
     */
    @Override
    public Respuesta<ConsultaDetalleDebinWSOutView> consultaDetalleDebin(ConsultaDetalleDebinWSInView consultaDetalleDebinWSInView) {
        LOGGER.info("DebinWS ManagerImpl iniciando consulta detalle debin");
        Boolean idDebinValido = existeDebinRecurrenciaEnSesion(consultaDetalleDebinWSInView.getDebinId());
        if (!idDebinValido) {
            return respuestaFactory.crearRespuestaError(ConsultaDetalleDebinWSOutView.class, StringUtils.EMPTY,
            		TipoError.ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
        }
        ConsultaDetalleDebinWSInDTO cnsDetalleDebinInDTO = generarConsultaDetalleDebinWSIn(consultaDetalleDebinWSInView);
        Respuesta<ConsultaDetalleDebinWSOutDTO> respuestaBO = debinwsBO.consultaDetalleDebin(cnsDetalleDebinInDTO);
        if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
            ConsultaDetalleDebinWSOutView respCnsDetalleDebinView = generarCnsDetalleDebinWSOutView(respuestaBO.getRespuesta(), consultaDetalleDebinWSInView.isConsultaDesdeRecibidos() );
            sesionParametros.setDetalleDebin(respuestaBO.getRespuesta());
            LOGGER.info("DebinWSManager OK");
            return respuestaFactory.crearRespuestaOk(respCnsDetalleDebinView);  
        } else {
            LOGGER.info("DebinWSManager ERROR");
            return respuestaFactory.crearRespuestaError(ConsultaDetalleDebinWSOutView.class, respuestaBO.getItemsMensajeRespuesta());
        }
    }

    /**
     * generarCnsDetalleDebinWSOutView.
     *
     * @param respuesta the respuesta
     * @param consultaDesdeRecibidos the consulta desde recibidos
     * @return the consulta detalle debin WS out view
     */
    private ConsultaDetalleDebinWSOutView generarCnsDetalleDebinWSOutView(ConsultaDetalleDebinWSOutDTO respuesta, boolean consultaDesdeRecibidos) {
        ConsultaDetalleDebinWSOutView detalleOutView = new ConsultaDetalleDebinWSOutView();
        String descConcepto = obtenerDescripcionConcepto(respuesta.getConcepto());
		detalleOutView.setConcepto(descConcepto);
		detalleOutView.setConceptoValido(!StringUtils.EMPTY.equals(descConcepto));
        detalleOutView.setDescripcion(respuesta.getDescripcion());
        detalleOutView.setDebinId(respuesta.getIdDebin());
        detalleOutView.setEstado(EstadoDebinEnum.obtenerEstadoPorDescripcion(respuesta.getEstado()).getDescView());
        detalleOutView.setIsDebinRec(respuesta.isPreautorizado());
        boolean tieneDesafio = false;
        if (sesionCliente.getCliente().tieneSoftToken() || sesionCliente.getCliente().tieneTarjetaCoordenadas() || sesionCliente.getCliente().tieneTarjetasDebitoSinCoordenadasNiToken()) {
        	tieneDesafio = true;	
        }
        if (respuesta.getFechaSolicitud()!= null) {
        	detalleOutView.setFechaSolicitud(new SimpleDateFormat(FORMATO_FECHA).format(respuesta.getFechaSolicitud()));	
        }
        if (respuesta.getFechaVencimiento() != null) {
        	detalleOutView.setFechaVencimiento(new SimpleDateFormat(FORMATO_FECHA).format(respuesta.getFechaVencimiento()));	
        }
        validarFlagsOperaciones(detalleOutView, respuesta, consultaDesdeRecibidos);
        String importe = ISBANStringUtils.formatearSaldo(new BigDecimal(respuesta.getImporteSolicitado()));
        detalleOutView.setImporte(importe);
        if (consultaDesdeRecibidos) {
            obtenerDetallesCuentaDebito(detalleOutView, respuesta); 
            detalleOutView.setNombreSolicitante(respuesta.getVendedor().getNombreTitular());
            detalleOutView.setAliasSolicitante(respuesta.getVendedor().getAlias());
            detalleOutView.setCbuSolicitante(respuesta.getVendedor().getCbu());
            detalleOutView.setCuitSolicitante(ISBANStringUtils.agregarGuionesANumeroCuitCuil(respuesta.getVendedor().getCuit()));
            String mensaje = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_PAGAR_TOKEN_NO_ASOCIADO).getMensaje();
            detalleOutView.setMensajeSinDesafio(mensaje);
            String urlAyuda = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_URL_AYUDA).getMensaje();
            detalleOutView.setUrlAyuda(urlAyuda);
            detalleOutView.setTieneMetodoDesafio(tieneDesafio);
        } else {
            obtenerDetallesCuentaAcreditacion(detalleOutView, respuesta);    
            detalleOutView.setCbuDestinatario(respuesta.getComprador().getCbu());
            detalleOutView.setAliasDestinatario(respuesta.getComprador().getAlias());
            detalleOutView.setCuitDestinatario(ISBANStringUtils.agregarGuionesANumeroCuitCuil(respuesta.getComprador().getCuit()));
            detalleOutView.setNombreDestinatario(respuesta.getComprador().getNombreTitular());
		}
		if (MONEDA_PESOS_BANELCO.equals(respuesta.getMoneda())) {
			detalleOutView.setMoneda(SIMBOLO_PESOS);
		} else if (MONEDA_DOLAR_BANELCO.equals(respuesta.getMoneda())) {
			detalleOutView.setMoneda(SIMBOLO_DOLARES);
		} else {
			detalleOutView.setMoneda("");
		}
		return detalleOutView;
	}
    
	/**
	 * Validar flags operaciones.
	 *
	 * @param detalleOutView the detalle out view
	 * @param respuesta the respuesta
	 * @param consultaDesdeRecibidos the consulta desde recibidos
	 */
    private void validarFlagsOperaciones(ConsultaDetalleDebinWSOutView detalleOutView, ConsultaDetalleDebinWSOutDTO respuesta, boolean consultaDesdeRecibidos) {
        boolean conceptoValido = !StringUtils.EMPTY.equals(detalleOutView.getConcepto());
    	if (consultaDesdeRecibidos) {
            detalleOutView.setPermiteEliminar(false);
            detalleOutView.setPermitePagar(conceptoValido && !respuesta.isPreautorizado() && EstadoDebinEnum.INICIADO.getDescripcion().equals(respuesta.getEstado()));
            detalleOutView.setPermiteRechazar(conceptoValido && !respuesta.isPreautorizado() && EstadoDebinEnum.INICIADO.getDescripcion().equals(respuesta.getEstado()));
        } else {
            detalleOutView.setPermitePagar(false);
            detalleOutView.setPermiteRechazar(false);
            detalleOutView.setPermiteEliminar(!respuesta.isPreautorizado() && EstadoDebinEnum.INICIADO.getDescripcion().equals(respuesta.getEstado()));
        }
    }

    /**
	 * Obtener detalles cuenta debito.
	 *
	 * @param detalleOutView
	 *            the detalle out view
	 * @param respuesta
	 *            the respuesta
	 */
    private void obtenerDetallesCuentaDebito(ConsultaDetalleDebinWSOutView detalleOutView, ConsultaDetalleDebinWSOutDTO respuesta) {	
	    Cuenta cuentaCliente = sesionCliente.getCliente().getCuenta(DebinWSUtils.obtenerCbuCuentaContinuadoraDebin(sesionCliente.getCliente().getCuentas(), sesionCliente.getCliente().getCuentasCerradas(), respuesta.getComprador().getCbu())); 
        detalleOutView.setCuentaContinuadoraDebitoNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaCliente));
        detalleOutView.setCuentaDebitoAlias(cuentaCliente.getAlias());
        detalleOutView.setCuentaDebitoDescripcion(TipoCuenta.fromCodigo(cuentaCliente.getTipoCuenta()).getDescripcion());
        detalleOutView.setCuentaDebitoNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaCliente));    
    }
    
    /**
     * Obtener detalles cuenta acreditacion.
     *
     * @param detalleOutView the detalle out view
     * @param respuesta the respuesta
     */
    private void obtenerDetallesCuentaAcreditacion(ConsultaDetalleDebinWSOutView detalleOutView, ConsultaDetalleDebinWSOutDTO respuesta) {
        Cuenta cuentaCliente = sesionCliente.getCliente().getCuenta(respuesta.getVendedor().getCbu());
        detalleOutView.setCuentaAcreditacionAlias(cuentaCliente.getAlias());
        detalleOutView.setCuentaAcreditacionDescripcion(TipoCuenta.fromCodigo(cuentaCliente.getTipoCuenta()).getDescripcion());
        detalleOutView.setCuentaAcreditacionNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaCliente));    
        
    }

    /**
     * obtenerDescripcionConcepto.
     *
     * @param conceptoParam the concepto param
     * @return the string
     */
    protected String obtenerDescripcionConcepto(String conceptoParam) {
        List<ConceptoDebinWSView> conceptos = buildConceptos(true);
        for (ConceptoDebinWSView conceptoView: conceptos) {
            if (conceptoView.getId().equals(conceptoParam)) {
                return conceptoView.getDescripcion();
            }
        }
        return StringUtils.EMPTY;
    }

    /**
	 * generarConsultaDetalleDebinWSIn.
	 *
	 * @param consultaDetalleDebinWSOutView
	 *            the consulta detalle debin WS out view
	 * @return the consulta detalle debin WS in DTO
	 */
    private ConsultaDetalleDebinWSInDTO generarConsultaDetalleDebinWSIn(ConsultaDetalleDebinWSInView consultaDetalleDebinWSOutView) {
        ConsultaDetalleDebinWSInDTO cnsDetalleDebinInDTO = new ConsultaDetalleDebinWSInDTO();
        Cliente cliente = sesionCliente.getCliente();
        cnsDetalleDebinInDTO.setConsultaDesdeRecibidos(consultaDetalleDebinWSOutView.isConsultaDesdeRecibidos());
        cnsDetalleDebinInDTO.setIdDebin(consultaDetalleDebinWSOutView.getDebinId());
        cnsDetalleDebinInDTO.setCanal(CANAL_DEFAULT);
        cnsDetalleDebinInDTO.setNroDocumento(cliente.getDni());
        cnsDetalleDebinInDTO.setTipoDocumento(cliente.getTipoDocumento());
        return cnsDetalleDebinInDTO;
    }

    /**
     * Builds the conceptos.
     *
     * @param incluirPFWeb the incluir PF web
     * @return the list
     */
    protected List<ConceptoDebinWSView> buildConceptos(boolean incluirPFWeb) {
        List<ConceptoDebinWSView> listado = new ArrayList<ConceptoDebinWSView>();
        StringTokenizer tokenizer = new StringTokenizer(conceptosStr, "|");
        while (tokenizer.hasMoreTokens()) {
            StringTokenizer tokenConepto = new StringTokenizer(tokenizer.nextToken(), "-");
            ConceptoDebinWSView concepto = new ConceptoDebinWSView();
            concepto.setId(tokenConepto.nextToken());
            concepto.setDescripcion(tokenConepto.nextToken());
            if (!DebinWSConstants.ID_CONCEPTO_DEBIN_PLAZOFIJO.equalsIgnoreCase(concepto.getId()) || incluirPFWeb) {
            	listado.add(concepto);
            }
        }
        return listado;
    }

    /**
	 * restaurarNroPaginaSesion.
	 */
    private void restaurarNroPaginaSesion() {
        sesionParametros.setNroPaginaDebinAcreditado(1);
        sesionParametros.setNroPaginaDebinEstadoUsuario(1);
        sesionParametros.setNroPaginaDebinIniciado(1);
    }

    /**
     * descargarComprobante.
     *
     * @param tipo the tipo
     * @return the respuesta
     */
    @Override
    public Respuesta<Reporte> descargarComprobante(String tipo) {
        ComprobanteInDTO comprobanteDTO = new ComprobanteInDTO();
        ConsultaDetalleDebinWSOutDTO detalleDebinSesion = sesionParametros.getDetalleDebin();
        comprobanteDTO.setTipoComprobante(tipo);
        comprobanteDTO.setConcepto(obtenerDescripcionConcepto(detalleDebinSesion.getConcepto()));
        comprobanteDTO.setDescripcion(detalleDebinSesion.getDescripcion());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaSolicitud = detalleDebinSesion.getFechaSolicitud() == null ? "" :  dateFormat.format(detalleDebinSesion.getFechaSolicitud());
        comprobanteDTO.setFechaSolicitud(fechaSolicitud);
        String fechaVencimiento = detalleDebinSesion.getFechaVencimiento() == null ? "" : dateFormat.format(detalleDebinSesion.getFechaVencimiento());
        comprobanteDTO.setFechaVencimiento(fechaVencimiento);
        comprobanteDTO.setIdDebin(detalleDebinSesion.getIdDebin());
        String simboloMoneda= obtenerSimboloMoneda(detalleDebinSesion.getMoneda());
        comprobanteDTO.setImporte(simboloMoneda + " " + ISBANStringUtils.formatearSaldo(new BigDecimal(detalleDebinSesion.getImporteSolicitado())));

        Cuenta cuentaCliente = null;
        
        if (ELIMINACION.equals(tipo)) {
        	//SOLO SE PUEDEN ELIMINAR ESTANDO EN EL FLUJO DEL VENDEDOR
        	cuentaCliente = sesionCliente.getCliente().getCuenta(detalleDebinSesion.getVendedor().getCbu());
        	comprobanteDTO.setNumeroCuenta(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaCliente));
            comprobanteDTO.setCbu(detalleDebinSesion.getComprador().getCbu());
            comprobanteDTO.setAlias(detalleDebinSesion.getComprador().getAlias());
            comprobanteDTO.setCuit(ISBANStringUtils.agregarGuionesANumeroCuitCuil(detalleDebinSesion.getComprador().getCuit()));
            comprobanteDTO.setSolicitante(detalleDebinSesion.getComprador().getNombreTitular());
        } else {
            cuentaCliente = sesionCliente.getCliente().getCuenta(DebinWSUtils.obtenerCbuCuentaContinuadoraDebin(sesionCliente.getCliente().getCuentas(), sesionCliente.getCliente().getCuentasCerradas(), sesionParametros.getDetalleDebin().getComprador().getCbu())); 
            comprobanteDTO.setNumeroCuenta(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaCliente));        	
            comprobanteDTO.setAlias(detalleDebinSesion.getVendedor().getAlias());
            comprobanteDTO.setCbu(detalleDebinSesion.getVendedor().getCbu());
            comprobanteDTO.setCuit(ISBANStringUtils.agregarGuionesANumeroCuitCuil(detalleDebinSesion.getVendedor().getCuit()));
            comprobanteDTO.setSolicitante(detalleDebinSesion.getVendedor().getNombreTitular());
        }
        
        comprobanteDTO.setTipoCuenta(TipoCuenta.fromCodigo(cuentaCliente.getTipoCuenta()).getDescripcion());        	        
        comprobanteDTO.setAliasCuenta(cuentaCliente.getAlias());
        
        comprobanteDTO.setFecha(detalleDebinSesion.getFechaComprobante());
        comprobanteDTO.setComprobante(detalleDebinSesion.getNumeroComprobante());
		String tipoDebinNombre = DebinWSConstants.ID_CONCEPTO_DEBIN_PLAZOFIJO
				.equals(detalleDebinSesion.getConcepto()) ? DebinWSConstants.TIPO_PLAZOFIJO_NOMBRE
						: DebinWSConstants.TIPO_DEBIN_NOMBRE;
        comprobanteDTO.setTipoDebinNombre(tipoDebinNombre);
        return debinwsBO.descargarComprobante(comprobanteDTO);
    }

    /**
     * Eliminar debin.
     *
     * @return the respuesta
     */
    @Override
    public Respuesta<DebinWSEliminarOutView> eliminarDebin() {
        LOGGER.info("Inicio DebinWS Manager - Eliminar debin");
        Respuesta<DebinWSEliminarOutDTO> rtaBO = debinwsBO.eliminarDebin();
        if (EstadoRespuesta.OK.equals(rtaBO.getEstadoRespuesta())) {
            LOGGER.info("Respuesta OK - Eliminar debin");
            DebinWSEliminarOutView eliminarOutView = new DebinWSEliminarOutView();
            eliminarOutView.setFechaComprobante(rtaBO.getRespuesta().getFechaComprobante());
            eliminarOutView.setNumeroComprobante(rtaBO.getRespuesta().getNumeroComprobante());
            String mensajeFeedback = MessageFormat.format(mensajeManager
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_ELIMINAR_OK)
                    .getMensaje(), sesionParametros.getDetalleDebin().getComprador().getNombreTitular());
            eliminarOutView.setMensajeFeedback(mensajeFeedback);
            return respuestaFactory.crearRespuestaOk(eliminarOutView);
        } else {
            String tipoError = rtaBO.getItemsMensajeRespuesta().get(0).getTipoError();
            if (TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion().equals(tipoError)){
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS,
                        CodigoMensajeConstantes.DEBINWS_ERROR_ELIMINAR, sesionParametros.getDetalleDebin().getComprador().getNombreTitular());
            } else {
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DEBINWS_ERROR_ELIMINAR,
                        CodigoMensajeConstantes.DEBINWS_ERROR_ELIMINAR, sesionParametros.getDetalleDebin().getVendedor().getNombreTitular());
            }
        }
    }

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.debinws.manager.DebinWSManager#pagarDebin(ar.com.santanderrio.obp.servicios.debinws.view.PagarDebinWSView)
	 */
	@Override
	public Respuesta<PagarDebinWSView> pagarDebin(PagarDebinWSView pagarDebinWSView) {
	    LOGGER.info("Inicio pagar debin DebinWS Manager");
		
	    if(!sesionCliente.getTieneTokenRSA()){
	    	LOGGER.info("RSA Offline no se permite completar la operación.");
	    	return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.RSA_OFFLINE,
	    			CodigoMensajeConstantes.ERROR_GENERICO_RSA_OFFLINE);
	    }
		cargarDatosPagarDebinRSA(pagarDebinWSView);

        // RSA
        AutentificacionCodEstDTO autentificacionCodEstDTO = obtenerAutenticacionCodEstDTO(EstadisticasConstants.PAGAR_DEBIN_DEBINWS);
        pagarDebinWSView.setValidarBloqueo(Boolean.TRUE);
        Respuesta<PagarDebinWSView> respuestaRsa = pagarDebinWSViewDesafioOperacionRSA.validarOperacionRSA(
        		pagarDebinWSView, Integer.parseInt(valorDesafioPagarDebinWS), autentificacionCodEstDTO);
        if (respuestaRsa == null || !EstadoRespuesta.OK.equals(respuestaRsa.getEstadoRespuesta())) {	
        	if(respuestaRsa.getRespuesta().getDesafio() != null && respuestaRsa.getRespuesta().getDesafio().getBloquearUsuario()){
    			LOGGER.info("Inicio de bloqueo de usuario.");
	        	 Respuesta<RsaUpdateUserResponseData> responseBloqueoUsuario = rsaManager.updateUser(new RsaUpdateUserRequestData("LOCKOUT"));
	        	 if(EstadoRespuesta.OK.equals(responseBloqueoUsuario.getEstadoRespuesta())){      
	        		 return respuestaFactory.crearRespuestaError(PagarDebinWSView.class, StringUtils.EMPTY, TipoError.RSA_BLOQUEAR_USUARIO,
			                    CodigoMensajeConstantes.RSA_LOGIN_USUARIO_BLOQUEADO);
	        	 } else{
	        		 LOGGER.info("Error al bloquear el usuario");
	        		 return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DENY_RSA, CodigoMensajeConstantes.DENY_RSA);
	        	 }
    		} else{
    			 return respuestaRsa;
    		}
        }

		String tipoDebinNombre = DebinWSConstants.ID_CONCEPTO_DEBIN_PLAZOFIJO.equals(sesionParametros.getDetalleDebin().getConcepto())
				? DebinWSConstants.TIPO_PLAZOFIJO_NOMBRE
				: DebinWSConstants.TIPO_DEBIN_NOMBRE;
		Respuesta<PagarDebinWSView> rtaBO = debinwsBO.pagarDebin(pagarDebinWSView);
		PagarDebinWSView rtaSolicitud = rtaBO.getRespuesta();
        String simboloMoneda = MONEDA_PESOS_BANELCO.equals(sesionParametros.getDetalleDebin().getMoneda()) ? SIMBOLO_PESOS : SIMBOLO_DOLARES;
        String importe = ISBANStringUtils.formatearSaldo(new BigDecimal(sesionParametros.getDetalleDebin().getImporteSolicitado()));
        if (!EstadoRespuesta.OK.equals(rtaBO.getEstadoRespuesta())) {
            String tipoError = rtaBO.getItemsMensajeRespuesta().get(0).getTipoError();
            LOGGER.info("DebinWS Manager Pagar Debin ERROR: " + tipoError);
            if (TipoError.DEBINWS_ERROR_ACTZKLIMIT.getDescripcion().equals(tipoError)) {
                return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_ACTZKLIMIT,
                        CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
            } else if (TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion().equals(tipoError)) {
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS,
                        CodigoMensajeConstantes.DEBINWS_ERROR_PAGAR, tipoDebinNombre, sesionParametros.getDetalleDebin().getVendedor().getNombreTitular(), simboloMoneda, importe);
            } else if (TipoError.DEBINWS_ERROR_ADHESION_COMPRADOR.getDescripcion().equals(tipoError)) {
                return respuestaFactory.crearRespuestaError("",TipoError.DEBINWS_ERROR_ADHESION_COMPRADOR,
                        CodigoMensajeConstantes.DEBINWS_ERROR_PAGAR, tipoDebinNombre, sesionParametros.getDetalleDebin().getVendedor().getNombreTitular(), simboloMoneda, importe);
            }
            return respuestaFactory.crearRespuestaError("",TipoError.DEBINWS_ERROR_PAGAR,
                    CodigoMensajeConstantes.DEBINWS_ERROR_PAGAR, tipoDebinNombre, sesionParametros.getDetalleDebin().getVendedor().getNombreTitular(), simboloMoneda, importe);
        } else {
            String mensaje = MessageFormat.format(mensajeManager.obtenerMensajePorCodigo
                (CodigoMensajeConstantes.DEBINWS_PAGAR_DEBIN_OK).getMensaje(),  
                tipoDebinNombre, sesionParametros.getDetalleDebin().getVendedor().getNombreTitular(), simboloMoneda, importe);
            LOGGER.info("DebinWS Manager Pagar Debin OK ");
            rtaSolicitud.setMensaje(mensaje);
            restaurarNroPaginaSesion();
            grabarComprobanteScomp();
            return respuestaFactory.crearRespuestaOk(rtaSolicitud);
        }
	}

	/**
	 * Grabar el comprobante en Scomp cuando la salida de pagar debin es ok
	 * 
	 */
	private void grabarComprobanteScomp() {
		Cliente cliente = sesionCliente.getCliente();
		PagarDebinEntityBuilder pagarDebinEntityBuilder = new PagarDebinEntityBuilder(cliente).setPagoDebinDTO(obtenerPagoDebinDTO()) ;
		scompBO.altaScompTransferencia(pagarDebinEntityBuilder);
	}

	/**
	 * Obtener pago debin DTO.
	 *
	 * @return the pago debin DTO
	 */
	private PagoDebinDTO obtenerPagoDebinDTO() {
		PagoDebinDTO pagoDebinDTO = new PagoDebinDTO();
		ConsultaDetalleDebinWSOutDTO detalleDebin = sesionParametros.getDetalleDebin();
    	Cuenta cuentaCliente = sesionCliente.getCliente().getCuenta(detalleDebin.getComprador().getCbu());
    	pagoDebinDTO.setTipoDeCuenta(cuentaCliente.getTipoCuentaEnum().getCodigo().toString());
    	pagoDebinDTO.setNumeroCuenta(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaCliente));
    	pagoDebinDTO.setAliasCuenta(StringUtils.EMPTY);
    	
        String importeFormat = ISBANStringUtils.formatearSaldo(new BigDecimal(detalleDebin.getImporteSolicitado()));
    	
		String importeDebitado = new StringBuilder(obtenerSimboloMoneda(detalleDebin.getMoneda())).append(importeFormat).toString();
		pagoDebinDTO.setImporteDebitado(importeDebitado);
		
		pagoDebinDTO.setFechaSolicitado(ISBANStringUtils.formatearFecha(detalleDebin.getFechaSolicitud()));
		pagoDebinDTO.setFechaVencimiento(ISBANStringUtils.formatearFecha(detalleDebin.getFechaVencimiento()));
		
		pagoDebinDTO.setSolicitante(detalleDebin.getVendedor().getNombreTitular());
		pagoDebinDTO.setIdSolicitante(detalleDebin.getVendedor().getCuit());
		pagoDebinDTO.setCbu(detalleDebin.getVendedor().getCbu());
		pagoDebinDTO.setAlias(detalleDebin.getVendedor().getAlias());

		if (!DebinWSConstants.ID_CONCEPTO_DEBIN_PLAZOFIJO.equals(detalleDebin.getConcepto())) {
			String conceptoDescripcion = detalleDebin.getConcepto().replaceAll("0", "");			
			pagoDebinDTO.setConcepto(ConceptoTransferenciaEnum.getConceptoFromOrdinal(conceptoDescripcion).getDescripcion());
		} else {
			pagoDebinDTO.setConcepto(obtenerDescripcionConcepto(detalleDebin.getConcepto()));
		}

		pagoDebinDTO.setDescripcion(detalleDebin.getDescripcion());
		pagoDebinDTO.setIdDebin(detalleDebin.getIdDebin());
		pagoDebinDTO.setNroComprobante(detalleDebin.getNumeroComprobante());
		return pagoDebinDTO;
	}

	/**
	 * Cargar datos pagar debin RSA.
	 *
	 * @param pagarDebinWSView the pagar debin WS view
	 */
	private void cargarDatosPagarDebinRSA(PagarDebinWSView pagarDebinWSView) {
	    //validar titular comprador igual o distinto a titular vendedor "me to me" "you to me" RSA
	    ConsultaDetalleDebinWSOutDTO detalleDebin = sesionParametros.getDetalleDebin();
	    if (sesionCliente.getCliente().getNumeroCUILCUIT().equals(detalleDebin.getVendedor().getCuit())) {
            pagarDebinWSView.setCuentaPropia(true);
        } else {
            pagarDebinWSView.setCuentaPropia(false);
        }
	    pagarDebinWSView.setCuitComprador(sesionCliente.getCliente().getNumeroCUILCUIT());
	    pagarDebinWSView.setCuitVendedor(ISBANStringUtils.formatearCuil(detalleDebin.getVendedor().getCuit()));
	    pagarDebinWSView.setCbuVendedor(detalleDebin.getVendedor().getCbu());
	    pagarDebinWSView.setNombreSolicitante(detalleDebin.getVendedor().getNombreTitular());
	    pagarDebinWSView.setImporte(detalleDebin.getImporteSolicitado());
	    pagarDebinWSView.setMoneda(MONEDA_PESOS_BANELCO.equals(detalleDebin.getMoneda()) ? "peso" : "dolar");
	    //genero nro de comprobante con timestamp y coloco la fecha actual para fecha de comprobante
        Date fechaComprobante = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_NRO_COMPROBANTE);
        sesionParametros.getDetalleDebin().setNumeroComprobante(sdf.format(fechaComprobante));
        sesionParametros.getDetalleDebin().setFechaComprobante(ISBANStringUtils.formatearFechaConHoraParaComprobante(fechaComprobante));
	    sdf = new SimpleDateFormat(FORMATO_FECHA);
        pagarDebinWSView.setFechaEjecucion(sdf.format(fechaComprobante));
        
        pagarDebinWSView.setStopOperacionErrorRSA(true);
        cargarDatosClaveToken(pagarDebinWSView);
    }
	
	private void cargarDatosClaveToken(PagarDebinWSView pagarDebinWSView) {
    	Respuesta<List<BigDecimal>> antiguedades = clienteManager.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
		if(antiguedades != null && antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
			if(antiguedades.getRespuesta().get(0) != null) {
				pagarDebinWSView.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
			}
			if(antiguedades.getRespuesta().get(1) != null) {
				pagarDebinWSView.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
			}
		}
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.manager.DebinWSManager#rechazarDebin()
     */
    @Override
    public Respuesta<RechazarDebinWSOutDTO> rechazarDebin() {
        LOGGER.info("Inicio Rechazar Debin DebinWS Manager");
        String tipoDebinNombre = DebinWSConstants.ID_CONCEPTO_DEBIN_PLAZOFIJO.equals(sesionParametros.getDetalleDebin().getConcepto())
				? DebinWSConstants.TIPO_PLAZOFIJO_NOMBRE
				: DebinWSConstants.TIPO_DEBIN_NOMBRE;
        Respuesta<RechazarDebinWSOutDTO> rtaBO =  debinwsBO.rechazarDebin();
        RechazarDebinWSOutDTO rtaRechazo = rtaBO.getRespuesta();
        String simboloMoneda = MONEDA_PESOS_BANELCO.equals(sesionParametros.getDetalleDebin().getMoneda()) ? SIMBOLO_PESOS : SIMBOLO_DOLARES;
        String importe = ISBANStringUtils.formatearSaldo(new BigDecimal(sesionParametros.getDetalleDebin().getImporteSolicitado()));
        if (!EstadoRespuesta.OK.equals(rtaBO.getEstadoRespuesta())) {
            String tipoError = rtaBO.getItemsMensajeRespuesta().get(0).getTipoError();
            LOGGER.info("DebinWS Manager Rechazar Debin ERROR: " + tipoError);
            if (TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion().equals(tipoError)) {
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS,
                        CodigoMensajeConstantes.DEBINWS_RECHAZAR_DEBIN_ERROR, tipoDebinNombre, sesionParametros.getDetalleDebin().getVendedor().getNombreTitular(), simboloMoneda, importe);
            }
            return respuestaFactory.crearRespuestaError("",TipoError.DEBINWS_RECHAZAR_DEBIN_ERROR,
                    CodigoMensajeConstantes.DEBINWS_RECHAZAR_DEBIN_ERROR, tipoDebinNombre, sesionParametros.getDetalleDebin().getVendedor().getNombreTitular(), simboloMoneda, importe);
        } else {
        	ConsultaDetalleDebinWSOutDTO detalleDebinSesion = sesionParametros.getDetalleDebin();        	
            LOGGER.info("DebinWS Manager Rechazar Debin OK ");
            String mensaje = MessageFormat.format(mensajeManager.obtenerMensajePorCodigo
                (CodigoMensajeConstantes.DEBINWS_RECHAZAR_DEBIN_OK).getMensaje(),  
                tipoDebinNombre, sesionParametros.getDetalleDebin().getVendedor().getNombreTitular(), simboloMoneda, importe);
            rtaRechazo.setMensaje(mensaje);
            rtaRechazo.setFechaComprobante(detalleDebinSesion.getFechaComprobante());
            restaurarNroPaginaSesion();
            return respuestaFactory.crearRespuestaOk(rtaRechazo);
        }
    }

	/**
	 * Obtener header cuentas.
	 *
	 * @return the cabecera grilla debin view
	 */
	private CabeceraGrillaDebinView obtenerHeaderCuentas() {
		CabeceraGrillaDebinView cabeceraGrillaDebinView = new CabeceraGrillaDebinView();
        List<Cuenta> cuentas = sesionCliente.getCliente().getCuentas();
        boolean setPesos = false;
        boolean setDolares = false;
        for (Cuenta cuenta :cuentas) {           	
	        if ((cuenta.esCuentaUnicaPesos() ||  cuenta.isCuentaPesos()) 
	        		&& !setPesos 
	        		&& cuenta.isCuentaPrincipal()) {
	            setPesos = true;
	            cabeceraGrillaDebinView.setNumeroCuentaPesos(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
	            cabeceraGrillaDebinView.setTituloCuentaPesos(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).getDescripcion());
	        } else if ((cuenta.esCuentaUnicaDolares() ||cuenta.isCuentaDolares()) 
	        		&& !setDolares
	        		&& cuenta.isCuentaPrincipal()) {
	            setDolares = true; 
	            cabeceraGrillaDebinView.setNumeroCuentaDolares(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
	            cabeceraGrillaDebinView.setTituloCuentaDolares(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).getDescripcion());
	        } 
	        if (setPesos && setDolares) {
	            break;
	        }
        }
		return cabeceraGrillaDebinView;
	}

	/**
	 * Existe debin o recurrencia en sesion
	 *
	 * @param debinId the debin id
	 * @return the boolean
	 */
	private Boolean existeDebinRecurrenciaEnSesion(String idParam) {
		Boolean retorno = Boolean.FALSE;
		for (String id : sesionParametros.getIdDebinesRecurrencias()) {
			if (id.equals(idParam)) {
				retorno = Boolean.TRUE;
				break;
			}
		}
		return retorno;
	}

	/**
	 * Obtener simbolo moneda.
	 *
	 * @param codigoMoneda the codigo moneda
	 * @return the string
	 */
	public static String obtenerSimboloMoneda(String codigoMoneda) {
        if (MONEDA_PESOS_BANELCO.equals(codigoMoneda)) {
            return SIMBOLO_PESOS;
        } else if (MONEDA_DOLAR_BANELCO.equals(codigoMoneda)) {
            return SIMBOLO_DOLARES;
        } 
        return StringUtils.EMPTY;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.debinws.manager.DebinWSManager#configuracionGrillaDebinWSView()
	 */
	@Override
	public Respuesta<ConfiguracionGrillaDebinWSView> configuracionGrillaDebinWSView() {
		ConfiguracionGrillaDebinWSView configuracionGrillaDebinWSView = new ConfiguracionGrillaDebinWSView();
    	configuracionGrillaDebinWSView.setListaEstadoDebin(new ArrayList<ConceptoDebinWSView>());
    	for (EstadoDebinEnum e : EstadoDebinEnum.values()) {
    		configuracionGrillaDebinWSView.getListaEstadoDebin().add(new ConceptoDebinWSView(e.getId(), e.getDescView()));
    	}

        List<EstadoRecurrenciaWSView> listaEstadoRecurrencia = new ArrayList<EstadoRecurrenciaWSView>();
        for (EstadoRecurrenciaDebinEnum e : EstadoRecurrenciaDebinEnum.values()) {
            listaEstadoRecurrencia.add(new EstadoRecurrenciaWSView(e.getId(), e.getDescView()));
        }
        configuracionGrillaDebinWSView.setListaEstadoRecurrencia(listaEstadoRecurrencia);
        return respuestaFactory.crearRespuestaOk(configuracionGrillaDebinWSView);
	}
	

	List<ConceptoDebinWSView> convertListDebin(List<DebinWSDTO> list){
		List<ConceptoDebinWSView> converted = new ArrayList<ConceptoDebinWSView>();
		for (DebinWSDTO debinWSDTO : list) {
			ConceptoDebinWSView item = new ConceptoDebinWSView();
			item.setDescripcion(debinWSDTO.getConcepto());
			item.setId(debinWSDTO.getDebinId());
			converted.add(item);
		}
		return converted;
	}
	
	
    /**
	 * Generar cuentas adheridas view.
	 *
	 * @param cuentasAdheridasOutDTO
	 *            the cuentas adheridas out DTO
	 * @return the cuentas adheridas debin out view
	 * @throws BusinessException
	 *             the business exception
	 */
    protected CuentasAdheridasDebinOutView generarCuentasAdheridasView(CuentasAdheridasOutDTO cuentasAdheridasOutDTO) throws BusinessException {
        CuentasAdheridasDebinOutView cuentasView = new CuentasAdheridasDebinOutView();
        Respuesta<CuentasView> respuestaCuentasView = cuentaManager.getCuentasSaldo();
        if (respuestaCuentasView.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
            throw new BusinessException("ERROR_AL_OBTENER_EL_SALDO_DE_LAS_CUENTAS");
        }
        //PARA CADA CUENTA ADHERIDA HAY QUE BUSCARLA EN SESION Y CONVERTIRLA EN VIEW PARA COMBO SELECTOR DE CUENTAS
        for (CuentaDebinDTO cuentaDTO : cuentasAdheridasOutDTO.getCuentasAdheridas()) {
            CuentasAdhesionDebitoView cuentaView = buscarCuentaAdheridaEnCliente(cuentaDTO, respuestaCuentasView.getRespuesta());
            //DIVIDIMOS LAS CUENTAS EN DOLARES Y PESOS
            if (cuentaView != null ) {
                if (MONEDA_CUENTA_UNICA.equals(cuentaDTO.getMoneda()) || cuentaView.getIsCuentaUnica()){
                    cuentasView.getCuentasDolares().add(cuentaView);
                    cuentasView.getCuentasPesos().add(cuentaView);
                } else if (MONEDA_DOLAR_BANELCO.equals(cuentaDTO.getMoneda())) {
                    cuentasView.getCuentasDolares().add(cuentaView);
                } else {
                    cuentasView.getCuentasPesos().add(cuentaView);
                }
            }
        }
        if (!cuentasView.getCuentasPesos().isEmpty()) {
        	MonedaDebinView monedaPesos = new MonedaDebinView(MONEDA_PESOS_BANELCO,PESOS);
        	cuentasView.getMonedas().add(monedaPesos);
        }
        if (!cuentasView.getCuentasDolares().isEmpty()) {
            MonedaDebinView monedaDolares = new MonedaDebinView(MONEDA_DOLAR_BANELCO, DOLARES);
            cuentasView.getMonedas().add(monedaDolares);
        }

        return cuentasView;
    }
    
    /**
	 * Buscar cuenta adherida en cliente.
	 *
	 * @param cuentaDTO
	 *            the cuenta DTO
	 * @param respuesta
	 *            the respuesta
	 * @return the cuentas adhesion debito view
	 */
    private CuentasAdhesionDebitoView buscarCuentaAdheridaEnCliente(CuentaDebinDTO cuentaDTO, CuentasView respuesta) {
       String cbuCuentaAdherida = cuentaDTO.getCbu();
       List<CuentasAdhesionDebitoView> cuentasCliente = respuesta.getCuentas();
       //VERIFICAMOS QUE LA CUENTA ADHERIDA SEA DEL CLIENTE Y NO ESTE CERRADA
       for (CuentasAdhesionDebitoView cuentaViewCliente : cuentasCliente) {
           if (cuentaViewCliente.getCbu().equals(cbuCuentaAdherida) && !cuentaViewCliente.getIsCerrada()) {
               return cuentaViewCliente;
           }
       }
        return null;
    }

	/**
	 * Obtiene entidad para estadisticas RSA
	 * 
	 * @param codigoEstadistica
	 * @return AutentificacionCodEstDTO
	 */
    protected AutentificacionCodEstDTO obtenerAutenticacionCodEstDTO(String codigoEstadistica) {
        AutentificacionCodEstDTO autentificacionCodEstDTO = new AutentificacionCodEstDTO();
        autentificacionCodEstDTO
        		.setCodigoEstadisticaSolicitudToken(codigoEstadistica);
        autentificacionCodEstDTO
        		.setCodigoEstadisticaValidacionToken(codigoEstadistica);
        autentificacionCodEstDTO
                .setCodigoEstadisticaSolicitudCoordenadas(codigoEstadistica);
        autentificacionCodEstDTO
                .setCodigoEstadisticaValidacionCoordenadas(codigoEstadistica);
        autentificacionCodEstDTO
        		.setCodigoEstadisticaSolicitudBanelco(codigoEstadistica);
        autentificacionCodEstDTO
        		.setCodigoEstadisticaValidacionBanelco(codigoEstadistica);
        return autentificacionCodEstDTO;
	}

	/**
     * Cargar hash.
     *
     * @param viewMap
     *            the view map
     */
	protected void cargarHashValidacion(Map<String, Object> viewMap) {
        String hashView = HashUtils.obtenerHash(viewMap);
        LOGGER.info("Se guarda el hash en sesion.");
        sesionParametros.setValidacionHash(hashView);
    }
    
    /**
     * Validar hash.
     *
     * @param viewMap
     *            the view map
     */
    protected void validarHash(Map<String, Object> viewMap) {
        String inputHash = HashUtils.obtenerHash(viewMap);
        LOGGER.info("Validando el hash {} de la sesion con el hash de la entidad {}",
                sesionParametros.getValidacionHash(), inputHash);
        HashUtils.compareHash(sesionParametros.getValidacionHash(), inputHash);
    }

    /**
	 * Obtener numero comprobante.
	 *
	 * @param fechaComprobante
	 *            the fecha comprobante
	 * @return the string
	 */
    protected String obtenerNumeroComprobante(Date fechaComprobante) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_NRO_COMPROBANTE);
        return sdf.format(fechaComprobante);
    }


    
}

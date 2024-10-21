/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.manager.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.http.web.util.NetworkUtil;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.filter.FilterConstants;
import ar.com.santanderrio.obp.servicios.clave.online.bo.ClaveOnlineBO;
import ar.com.santanderrio.obp.servicios.clave.online.entities.AltaSGIClaveInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.CredencialesClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.entities.FuncionEnum;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteOutEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntaAutenticacion;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntaValidacionTel;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntasSeguridad;
import ar.com.santanderrio.obp.servicios.clave.online.entities.RespuestaAutenticacion;
import ar.com.santanderrio.obp.servicios.clave.online.entities.TipoDeCompaniasCelularClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.entities.ValidacionPreguntaIn;
import ar.com.santanderrio.obp.servicios.clave.online.entities.VerificacionPasosClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.SesionExpiradaException;
import ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager;
import ar.com.santanderrio.obp.servicios.clave.online.util.ClaveOnlineUtils;
import ar.com.santanderrio.obp.servicios.clave.online.view.AltaClaveOnlineView;
import ar.com.santanderrio.obp.servicios.clave.online.view.CompaniaCelularView;
import ar.com.santanderrio.obp.servicios.clave.online.view.DatosAutenticacion;
import ar.com.santanderrio.obp.servicios.clave.online.view.HashView;
import ar.com.santanderrio.obp.servicios.clave.online.view.MetodoAutenticacionView;
import ar.com.santanderrio.obp.servicios.clave.online.view.PreguntaAutenticacionView;
import ar.com.santanderrio.obp.servicios.clave.online.view.PreguntaView;
import ar.com.santanderrio.obp.servicios.clave.online.view.RespuestaAutenticacionView;
import ar.com.santanderrio.obp.servicios.clave.online.view.TarjetaBanelcoView;
import ar.com.santanderrio.obp.servicios.clientes.entities.CelularMyaYCompaniasTelResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteEstadoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CelularResponse;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.TipoTeclado;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.rsa.web.controller.util.RSAControllerUtil;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.login.encrypines.EncryPines;
import ar.com.santanderrio.obp.servicios.login.manager.LoginManager;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericRequestData;

/**
 * The Class ClaveOnlineManagerImpl.
 */
@Component
public class ClaveOnlineManagerImpl implements ClaveOnlineManager {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClaveOnlineManagerImpl.class);

    /** The Constant CLAVE_VACIA. */
    private static final String CLAVE_VACIA = "    ";

    /** The Constant CERO. */
    private static final String CERO = "0";
    /** The Constant NUMERO1. */
    private static final String NUMERO1 = "1";

    /** The Constant FORMATO_FECHA. */
    private static final String FORMATO_FECHA = "ddMMyyyy";

    /** The Constant FORMATO_FECHA_ESTADISTICAS. */
    private static final String FORMATO_FECHA_ESTADISTICAS = "yyyyMMdd";

    /** The Constant CICLO_INICIAL. */
    private static final Integer CICLO_INICIAL = 1;

    /** The Constant NO_AUTENTICA. */
    private static final String NO_AUTENTICA = CLAVE_VACIA;
    
    /** The Constant CELU. */
    private static final String CELU = "CELU";

    /** The Constant BANE. */
    private static final String BANE = "BANE";

    /** The Constant VISA. */
    private static final String VISA = "VISA";

    /** The Constant AMEX. */
    private static final String AMEX = "AMEX";

    /** The Constant SMS. */
    private static final String SMS = "SMS";
    
    private static final String WHATSAPP = "WHATSAPP";

    /** The Constant BANELCO. */
    private static final String BANELCO = "BANELCO";
    
    /** The Constant PREFIJO_CELULAR. */
    private static final String PREFIJO_CELULAR = "XXXXXXX-";
    
    /** The Constant OPCION_CELULAR_NINGUNA. */
    private static final String OPCION_CELULAR_NINGUNA = "NINGUNA";    

    /** The Constant OPCION_CELULAR_NINGUNO_ES_CORRECTO. */
    private static final String OPCION_CELULAR_NINGUNO_ES_CORRECTO = "Ninguno es correcto";
    
    /** The Constant LEGAL_WHATSAPP. */
    private static final String LEGAL_WHATSAPP_INFORMACION = "1050";
    
    /** The Constant LEGAL_WHATSAPP_INFORMACION. */
    private static final String LEGAL_WHATSAPP_INFORMACION_REENVIAR = "1051";
    
    private static final String RESPUESTA_OK = "00000000";
    
	private static final String ERROR_REINTENTOS_AGOTADOS = "10050092";
	
	private static final String ERROR_USUARIO_BLOQUEADO1 = "10050011";
	
	private static final String ERROR_USUARIO_BLOQUEADO2 = "10050015";
	
	private static final String ERROR_CLIENTE_SIN_MEDIO_AUTENTICACION_VALIDO = "10050035";
	
	private static final String ERROR_CLIENTE_BLOQUEADO_AGRAVADO1 = "10050016";
	
	private static final String ERROR_CLIENTE_BLOQUEADO_AGRAVADO2 = "10050014";
	
	private static final String ERROR_IP_BLOQUEADA = "10050012"; 
	
	private static final String ERROR_AUTENTICACION_PERMITE_REINTENTAR = "10050091";
    
    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The mensaje manager. */
    @Autowired
    private MensajeManager mensajeManager;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;
    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The ClaveOnlineBO. */
    @Autowired
    private ClaveOnlineBO claveOnlineBO;
    
    /** The mya BO. */
    @Autowired
    private MyaBO myaBO;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The encry pines. */
    @Autowired
    private EncryPines encryPines;
    
	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

    /** The cantidad minutos que permanece activa la sesion. */
    @Value("${JWT_AND_SESSION_EXPIRATION_TIME:600000}")
    private Integer minutosSesionActiva;
    
    /** The companias celular. */
    @Value("#{'${COMPANIAS.CELULAR.CODIGOS}'.split(',')}")
    private List<String> companiasCelular;
    
    /** The Constant TITULO_CELULAR_PRIMARIO. */
    private static final String TITULO_CELULAR_PRIMARIO = "Celular Primario";
    /** The Constant TITULO_CELULAR_SECUNDARIO. */

    @Autowired
    private ClienteManager clienteManager;
    
    @Autowired
    private MensajeBO mensajeBO;
    
    @Autowired
    private LoginManager loginManager;
        
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager
     * #grabarEstadisticaSolucionar()
     */
    @Override
    public Respuesta<Mensaje> grabarEstadisticaSolucionar() {
        Mensaje mensaje = mensajeManager
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.ACCESO_FUNCIONALIDAD_SOLUCIONAR);
        return respuestaFactory.crearRespuestaOk(Mensaje.class, mensaje);
    }

    /**
     * Obtener preguntas autenticacion.
     *
     * @param credencialesClaveOnline
     *            the credenciales clave online
     * @param identificacion
     *            the identificacion
     * @return the respuesta
     */
    private Respuesta<MetodoAutenticacionView> obtenerPreguntasAutenticacion(
            CredencialesClaveOnline credencialesClaveOnline, IdentificadorClienteOutEntity identificacion, HttpServletRequest request) {
        Respuesta<MetodoAutenticacionView> respuesta;
        Respuesta<PreguntasSeguridad> respuestaPreguntasSeguridad = claveOnlineBO
                .obtenerPreguntasSeguridad(credencialesClaveOnline, identificacion);

        String codEstadistica = ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.PRESENTACION_PREGUNTAS_SEGURIDAD, 
				EstadisticasConstants.PRESENTACION_PREGUNTAS_SEGURIDAD_WEBVIEW);
        if (EstadoRespuesta.ERROR.equals(respuestaPreguntasSeguridad.getEstadoRespuesta())) {
            respuesta = Respuesta.copy(MetodoAutenticacionView.class, respuestaPreguntasSeguridad);

            estadisticaManager.add(codEstadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        } else {
            PreguntasSeguridad preguntasSeguridad = respuestaPreguntasSeguridad.getRespuesta();
            preguntasSeguridad.setIndicePregunta(1);
            sesionParametros.setPreguntasSeguridad(preguntasSeguridad);
            MetodoAutenticacionView preguntaView = obtenerPreguntaSeguridadView();

            respuesta = respuestaFactory.crearRespuestaWarning(preguntaView, null, TipoError.PREGUNTAS, "");
            estadisticaManager.add(codEstadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }

        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager
     * #obtenerPreguntaSeguridadView()
     */
    @Override
    public PreguntaAutenticacionView obtenerPreguntaSeguridadView() {

        PreguntaAutenticacion preg = getPreguntaActual();

        if (preg == null) {
            return null;
        }
        
        PreguntaAutenticacionView view = new PreguntaAutenticacionView();
        view.setCantidadPreguntas(getCantidadPreguntas());
        view.setCantidadRespuestas(preg.getCantidadRespuestas());
        List<RespuestaAutenticacionView> resp = new ArrayList<RespuestaAutenticacionView>();
        PreguntaView preguntaView = new PreguntaView();
        preguntaView.setIdPregunta(preg.getIdPregunta());
        preguntaView.setPregunta(preg.getPregunta());
        preguntaView.setIndice(preg.getIndice());

        view.setPregunta(preguntaView);

        for (RespuestaAutenticacion respuesta : preg.getRespuestas()) {
            RespuestaAutenticacionView respView = new RespuestaAutenticacionView();
            respView.setIdRespuesta(respuesta.getOrden());
            respView.setRespuesta(respuesta.getRespuesta());
            resp.add(respView);
        }

        view.setRespuestas(resp);
        return view;
    }

    /**
     * Retorna la cantidad de preguntas de seguridad.
     *
     * @return the cantidad preguntas
     */
    private Integer getCantidadPreguntas() {
        return sesionParametros.getPreguntasSeguridad().getCantidadPreguntas();
    }

    /**
     * Retorna pregunta actual a validar.
     *
     * @return the pregunta actual
     */
    private PreguntaAutenticacion getPreguntaActual() {

        PreguntasSeguridad preguntas = sesionParametros.getPreguntasSeguridad();
        Integer indicePregunta = preguntas.getIndicePregunta();

        if (indicePregunta > preguntas.getCantidadPreguntas()) {
            // no hay mas preguntas
            return null;
        }

        PreguntaAutenticacion preguntaAutenticacion = preguntas.getPreguntas().get(indicePregunta - 1);
        preguntaAutenticacion.setIndice(indicePregunta);
        return preguntaAutenticacion;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager
     * #confirmarRespuesta(java.lang.String)
     */
    @Override
    public Respuesta<MetodoAutenticacionView> confirmarRespuesta(HttpServletRequest request, String datoEntrada) {
        String cryptoInput = encryPines.obtenerSoloCrypto(datoEntrada);
        CredencialesClaveOnline credencialesClaveOnline = encryPines.obtenerViewFromJson(cryptoInput,
                CredencialesClaveOnline.class);
        String opcion = credencialesClaveOnline.getRespuesta();
        try {
            controlarTimeOutSesion(request);
        } catch (SesionExpiradaException e) {
            LOGGER.error(e.getMessage(), e);
            return crearRespuestaSesionExpirada();
        }

        VerificacionPasosClaveOnline verificacionPasosClaveOnline = this.sesionParametros.getVerificacionPasosClaveOnline();
		// si no se cargaron los pasos de validacion o no esta seteado el paso de validacion de preguntas de seguridad: devuelvo error generico
		if (verificacionPasosClaveOnline == null || !verificacionPasosClaveOnline.isValidarPreguntasSeguridad()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}
        
        Respuesta<MetodoAutenticacionView> respuesta = respuestaFactory.crearRespuestaOk(MetodoAutenticacionView.class);
        PreguntaAutenticacion preguntaAutenticacion = getPreguntaActual();
        Integer ciclo = sesionParametros.getCredencialesClaveOnline().getCiclo();
        String sesion = sesionParametros.getPreguntasSeguridad().getIdSesion();
        ValidacionPreguntaIn preguntaIn = claveOnlineBO.getPreguntaValidar(preguntaAutenticacion, opcion, ciclo,
                sesion);
        preguntaIn.setNup(sesionParametros.getCredencialesClaveOnline().getNup());
        preguntaIn.setDni(sesionParametros.getCredencialesClaveOnline().getDniOri());
        Date fechaNacimiento = FechaUtils.parsearFechaNacimiento(
                sesionParametros.getCredencialesClaveOnline().getFechaDeNacimiento(), "ddMMyyyy");
        preguntaIn.setFechaNacimiento(fechaNacimiento);

        Respuesta<Void> respuestaValidacion = claveOnlineBO.validarRespuesta(preguntaIn);

        String codEstadistica = ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.VALIDACION_PREGUNTAS_SEGURIDAD, 
				EstadisticasConstants.VALIDACION_PREGUNTAS_SEGURIDAD_WEBVIEW);
        if (EstadoRespuesta.ERROR.equals(respuestaValidacion.getEstadoRespuesta())) {
            estadisticaManager.add(codEstadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            sesionParametros.getCredencialesClaveOnline()
                    .setCiclo(sesionParametros.getCredencialesClaveOnline().getCiclo() + 1);
            return Respuesta.copy(MetodoAutenticacionView.class, respuestaValidacion);
        } else {
            sesionParametros.getPreguntasSeguridad()
                    .setIndicePregunta(sesionParametros.getPreguntasSeguridad().getIndicePregunta() + 1);
        }

        PreguntaAutenticacionView preguntaView = obtenerPreguntaSeguridadView();

        if (preguntaView == null) {
        	
        	// las preguntas fueron validadas
			this.sesionParametros.getVerificacionPasosClaveOnline().setValidarPreguntasSeguridad(false);
          
			sesionParametros.getCredencialesClaveOnline()
                    .setCiclo(sesionParametros.getCredencialesClaveOnline().getCiclo() + 1);
            estadisticaManager.add(codEstadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            // si no hay mas preguntas, para. Y verifica si falta usar otro
            // metodo de verificacion
            
                   
            RegistroSesion registroSesion = sesionParametros.getRegistroSession();
            DatosAutenticacion datosAutenticacion = registroSesion.getDatosAutenticacion();
            
			//el tipo de autorizacion a usar depende si se paso por la pantalla de validacion de telefono y 
			//se selecciono la opcion ninguna, se sobreescribe el tipo de autorizacion con el del valsgtel.
            //Lo mismo con el numero a mostrar
			//En cualquier otro caso se usa el tipo de autorizacion y el numero devuelto por el el servicio de IDE
			PreguntaValidacionTel preguntaValidacionTel = this.sesionParametros.getPreguntaValidacionTel();
			if (preguntaValidacionTel != null && preguntaValidacionTel.isOpcionNinguna()) {
				datosAutenticacion.setTipoAut(preguntaValidacionTel.getTipoAuthValsgTel());
				datosAutenticacion.setNumero(preguntaValidacionTel.getNumeroValsgTel());
				datosAutenticacion.setNumeroAEnviarEnSegSgiaut(preguntaValidacionTel.getNumeroValsgTel());
			} 
			
			String tipoAut = datosAutenticacion.getTipoAut();
            String numero = datosAutenticacion.getNumero().trim();
            if (debeAutenticar(tipoAut)) {
                grabarEstadisticaIngresoAutenticacionOK(request);
                sesionParametros.getRegistroSession().getDatosAutenticacion().setCiclo(0);
    			this.sesionParametros.getVerificacionPasosClaveOnline().setValidarAutenticacion(true);
                respuesta = obtenerVistaAutenticacion(tipoAut, numero, request);

            }
        } else {
            Respuesta<PreguntaAutenticacionView> respuestaPA = respuestaFactory.crearRespuestaWarning(preguntaView,
                    null, TipoError.PREGUNTAS, "");
            respuesta = Respuesta.copy(MetodoAutenticacionView.class, respuestaPA);
            respuesta.setRespuesta(preguntaView);
        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager
     * #controlarTimeOutSesion(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void controlarTimeOutSesion(HttpServletRequest request) throws SesionExpiradaException {
        Boolean sesionCerrada = true;
        try {
            sesionCerrada = sesionParametros.getTimerClaveOnline().before(Calendar.getInstance().getTime());
        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
        }
        if (sesionCerrada) {
            String codEstadistica = ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.VALIDACION_PREGUNTAS_SEGURIDAD, 
            		EstadisticasConstants.VALIDACION_PREGUNTAS_SEGURIDAD_WEBVIEW);
            estadisticaManager.add(codEstadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            request.getSession(false).setAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION, Boolean.TRUE);
            throw new SesionExpiradaException(TipoError.CLAVE_ONLINE_SESION_EXPIRADA.getDescripcion());
        }
    }

    /**
     * Checks if is autentificacion preguntas.
     *
     * @param identificacion
     *            the identificacion
     * @return the boolean
     */
    private Boolean isAutentificacionPreguntas(IdentificadorClienteOutEntity identificacion) {
        return identificacion != null && CERO.equals(identificacion.getCiclos());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager
     * #identificarCliente(ar.com.santanderrio.obp.servicios.clave.online.
     * entities.CredencialesClaveOnline, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public Respuesta<Void> identificarCliente(CredencialesClaveOnline credencialesClaveOnline,
            HttpServletRequest request) {
        credencialesClaveOnline.setCiclo(CICLO_INICIAL);
        Respuesta<Void> respuesta = respuestaFactory.crearRespuestaOk(Void.class);
        sesionParametros.setCredencialesClaveOnline(credencialesClaveOnline);
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager
     * #generarHash()
     */
    public Respuesta<HashView> generarHash() {
        Double valor = Math.random();
        String hash = HashUtils.obtenerHash(valor, HashUtils.ALGORITMO_MD5);
        Respuesta<HashView> respuesta = respuestaFactory.crearRespuestaOk(new HashView(hash));
        sesionParametros.setValidacionHash(hash);
        return respuesta;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager#confirmarDatos(javax.servlet.http.HttpServletRequest, java.lang.String)
     */
    @Override
    public Respuesta<MetodoAutenticacionView> confirmarDatos(HttpServletRequest request, String datoEntrada) {
    
    	String cryptoInput = encryPines.obtenerSoloCrypto(datoEntrada);
        CredencialesClaveOnline credencialesClaveOnline = encryPines.obtenerViewFromJson(cryptoInput,
                CredencialesClaveOnline.class);

        RsaGenericRequestData rsaGenericRequestData = RSAControllerUtil.getRsaGenericRequestData(request,
                credencialesClaveOnline.getDevicePrint());
        sesionParametros.setRsaGenericRequestData(rsaGenericRequestData);

        DatosAutenticacion datosAutenticacion = new DatosAutenticacion();
        Respuesta<MetodoAutenticacionView> respuesta;
        credencialesClaveOnline.setIngresoRegistrar(sesionParametros.getCredencialesClaveOnline().isIngresoRegistrar());
        credencialesClaveOnline.setIngresoRenovar(sesionParametros.getCredencialesClaveOnline().isIngresoRenovar());
        credencialesClaveOnline
                .setIngresoSolucionar(sesionParametros.getCredencialesClaveOnline().isIngresoSolucionar());
        credencialesClaveOnline.setCiclo(sesionParametros.getCredencialesClaveOnline().getCiclo());
        sesionParametros.setCredencialesClaveOnline(credencialesClaveOnline);
        guardarRegistroSesion(request, credencialesClaveOnline);
        IdentificadorClienteInEntity identificadorClienteInEntity = obtenerIdentificador(credencialesClaveOnline);
        grabarEstadistica(request);
        Respuesta<IdentificadorClienteOutEntity> identificadorClienteOutEntity = claveOnlineBO.confirmarDatos(identificadorClienteInEntity);
        
        String codEstadistica = ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.CLAVE_ONLINE_IDENTIFICACION_OK, 
				EstadisticasConstants.CLAVE_ONLINE_IDENTIFICACION_OK_WEBVIEW);
        if (EstadoRespuesta.OK.equals(identificadorClienteOutEntity.getEstadoRespuesta())) {
        	
            datosAutenticacion.setDni(identificadorClienteInEntity.getDni());
            datosAutenticacion.setFechaDeNacimiento(String.valueOf(identificadorClienteInEntity.getFecha()));
            datosAutenticacion.setIp(NetworkUtil.getHostAddress());
            datosAutenticacion.setNumero(identificadorClienteOutEntity.getRespuesta().getNumero());
            datosAutenticacion.setNumeroAEnviarEnSegSgiaut(identificadorClienteOutEntity.getRespuesta().getNumero());
            datosAutenticacion.setSesion(identificadorClienteOutEntity.getRespuesta().getSesion());
            datosAutenticacion.setTipoAut(identificadorClienteOutEntity.getRespuesta().getTipoAut());
            datosAutenticacion.setNup(identificadorClienteOutEntity.getRespuesta().getNup());
            sesionParametros.getRegistroSession().setDatosAutenticacion(datosAutenticacion);
            IdentificadorClienteOutEntity identificadorRespuesta = identificadorClienteOutEntity.getRespuesta();
            updateRegistroSesion(request, identificadorRespuesta);
            estadisticaManager.add(codEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            inicializarTimer();
         
			VerificacionPasosClaveOnline verificacionPasosClaveOnline = new VerificacionPasosClaveOnline();
            //nuevo flujo celular
			if (NUMERO1.equals(identificadorClienteOutEntity.getRespuesta().getPregCiclo()) && CELU.equals(identificadorClienteOutEntity.getRespuesta().getTipoAut())) {
				credencialesClaveOnline.setNup(identificadorRespuesta.getNup());
				respuesta = obtenerVistaPreguntasCelular(identificadorRespuesta.getTipoAut(), identificadorRespuesta.getNumero().trim(), request, identificadorClienteOutEntity.getRespuesta());
				//Guardo en sesion la empresa que devolvio el servicio de identificacion 
				//para usarla en el paso siguiente al enviar el sms
				this.sesionParametros.setEmpresaCelularServicioIdesgiclie(identificadorClienteOutEntity.getRespuesta().getNumero());
				verificacionPasosClaveOnline.setValidarPreguntaTelefono(true);
				this.sesionParametros.setVerificacionPasosClaveOnline(verificacionPasosClaveOnline);
			} else if (!isAutentificacionPreguntas(identificadorRespuesta)) {
				credencialesClaveOnline.setNup(identificadorRespuesta.getNup());
				respuesta = obtenerPreguntasAutenticacion(credencialesClaveOnline, identificadorRespuesta, request);
				verificacionPasosClaveOnline.setValidarPreguntasSeguridad(true);
				verificacionPasosClaveOnline.setEnviarPantallaActualizacionNumeroCelular(true);
				this.sesionParametros.setVerificacionPasosClaveOnline(verificacionPasosClaveOnline);
				this.sesionParametros.setEmpresaCelularServicioIdesgiclie("    ");
			} else if (debeAutenticar(identificadorRespuesta.getTipoAut())) {
				grabarEstadisticaIngresoAutenticacionOK(request);
				sesionParametros.getRegistroSession().getDatosAutenticacion().setCiclo(0);
				verificacionPasosClaveOnline.setValidarAutenticacion(true);
				verificacionPasosClaveOnline.setEnviarPantallaActualizacionNumeroCelular(true);
				this.sesionParametros.setVerificacionPasosClaveOnline(verificacionPasosClaveOnline);
				sesionParametros.setEmpresaCelularServicioIdesgiclie("    ");
				respuesta = obtenerVistaAutenticacion(identificadorRespuesta.getTipoAut(),identificadorRespuesta.getNumero().trim(), request);
			} else {
			    this.sesionParametros.setVerificacionPasosClaveOnline(verificacionPasosClaveOnline);
				sesionParametros.setEmpresaCelularServicioIdesgiclie("    ");
			    respuesta = Respuesta.copy(MetodoAutenticacionView.class, identificadorClienteOutEntity);
			}
		} else {
			respuesta = Respuesta.copy(MetodoAutenticacionView.class, identificadorClienteOutEntity);
		}
        if (EstadoRespuesta.WARNING.equals(identificadorClienteOutEntity.getEstadoRespuesta())) {
            updateRegistroSesion(request, identificadorClienteOutEntity.getRespuesta());
            estadisticaManager.add(codEstadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_DOCUMENTO_HOMONIMO);
            respuesta = Respuesta.copy(MetodoAutenticacionView.class, identificadorClienteOutEntity);
        }
        if (EstadoRespuesta.ERROR.equals(identificadorClienteOutEntity.getEstadoRespuesta())) {
            estadisticaManager.add(codEstadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            respuesta = Respuesta.copy(MetodoAutenticacionView.class, identificadorClienteOutEntity);
        }
        return respuesta;
    }

   
    /**
     * Grabar estadistica ingreso autenticacion OK.
     */
    private void grabarEstadisticaIngresoAutenticacionOK(HttpServletRequest request) {
        String codEstadistica = ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.INGRESO_METODO_AUTENTICACION, 
        		EstadisticasConstants.INGRESO_METODO_AUTENTICACION_WEBVIEW);
        estadisticaManager.add(codEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager
     * #reintentarPreguntas(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public Respuesta<MetodoAutenticacionView> reintentarPreguntas(HttpServletRequest request) {

        try {
            controlarTimeOutSesion(request);
        } catch (SesionExpiradaException e) {
            LOGGER.error(e.getMessage(), e);
            return crearRespuestaSesionExpirada();
        }
		VerificacionPasosClaveOnline verificacionPasosClaveOnline = this.sesionParametros.getVerificacionPasosClaveOnline();
		// si no se cargaron los pasos de validacion o no esta seteado el paso de validacion de preguntas de seguridad: devuelvo error generico
		if (verificacionPasosClaveOnline == null || !verificacionPasosClaveOnline.isValidarPreguntasSeguridad()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}
        
        Respuesta<MetodoAutenticacionView> respuesta = null;
        CredencialesClaveOnline credenciales = sesionParametros.getCredencialesClaveOnline();
        PreguntasSeguridad preguntas = sesionParametros.getPreguntasSeguridad();
        IdentificadorClienteInEntity identificadorCliente = new IdentificadorClienteInEntity();
        identificadorCliente.setDni(preguntas.getDni());
        identificadorCliente.setNup(credenciales.getNup());
        identificadorCliente.setIdSesion(preguntas.getIdSesion());
        identificadorCliente.setCiclo(credenciales.getCiclo());
        identificadorCliente.setCantidadOcurrencias(preguntas.getCantidadOcurrencias());
        identificadorCliente.setFechaNacimiento(
                FechaUtils.parsearFechaNacimiento(credenciales.getFechaDeNacimiento(), FORMATO_FECHA));
        identificadorCliente.setIp(NetworkUtil.getHostAddress());
        identificadorCliente.setProductos(preguntas.getProductos());

        Respuesta<PreguntasSeguridad> respuestaPreguntasSeguridad = claveOnlineBO
                .obtenerPreguntasSeguridad(identificadorCliente);

        String codEstadistica = ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.PRESENTACION_PREGUNTAS_SEGURIDAD, 
				EstadisticasConstants.PRESENTACION_PREGUNTAS_SEGURIDAD_WEBVIEW);
        if (EstadoRespuesta.ERROR.equals(respuestaPreguntasSeguridad.getEstadoRespuesta())) {
            respuesta = Respuesta.copy(MetodoAutenticacionView.class, respuestaPreguntasSeguridad);
            estadisticaManager.add(codEstadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        } else {
            PreguntasSeguridad preguntasSeguridad = respuestaPreguntasSeguridad.getRespuesta();
            preguntasSeguridad.setIndicePregunta(1);
            sesionParametros.setPreguntasSeguridad(preguntasSeguridad);
            MetodoAutenticacionView preguntaView = obtenerPreguntaSeguridadView();
            respuesta = respuestaFactory.crearRespuestaWarning(preguntaView, null, TipoError.PREGUNTAS, "");
            estadisticaManager.add(codEstadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }

        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager
     * #crearRespuestaSesionExpirada()
     */
    @Override
    public Respuesta<MetodoAutenticacionView> crearRespuestaSesionExpirada() {

        Respuesta<MetodoAutenticacionView> respuesta = respuestaFactory.crearRespuestaError("",
                TipoError.CLAVE_ONLINE_SESION_EXPIRADA, CodigoMensajeConstantes.CLAVE_ONLINE_SESION_EXPIRADA);
        Long milisegEnMinutos = TimeUnit.MILLISECONDS.toMinutes(minutosSesionActiva);
        String mensaje = MessageFormat.format(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(),
                milisegEnMinutos.toString());
        respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensaje);
        respuesta.setSkipLog(Boolean.TRUE);
        return respuesta;
    }
    /**
     * Obtener vista autenticacion.
     *
     * @param tipoAut
     *            the tipo aut
     * @param numero
     *            the numero
     * @param request
     *            the request
     * @return the respuesta
     */
    private Respuesta<MetodoAutenticacionView> obtenerVistaPreguntasCelular(String tipoAut, String numero,
            HttpServletRequest request, IdentificadorClienteOutEntity identificadorClienteOutEntity) {
        return respuestaFactory.crearRespuestaWarning(generarViewVistaOpcionesCelular(identificadorClienteOutEntity), null, TipoError.FLUJO_CELULAR,
                "");
    }
    
    /**
     * 
     * @param identificadorClienteOutEntity
     * @return
     */
	private MetodoAutenticacionView generarViewVistaOpcionesCelular(IdentificadorClienteOutEntity identificadorClienteOutEntity) {
		
		PreguntaAutenticacionView preguntaAutenticacionView = new PreguntaAutenticacionView();
		preguntaAutenticacionView.setCheckWhatsappHabilitado(claveOnlineBO.obtenerClaveWhatsapp());
		List<String> listaNumeros = new ArrayList<String>();
        String opcionVerdadera = PREFIJO_CELULAR + obtenerUltimosCuatroNumeros(identificadorClienteOutEntity.getNumero());
        listaNumeros.add(opcionVerdadera);
        listaNumeros.add(generarOpcionAleatoria(preguntaAutenticacionView.getCheckWhatsappHabilitado()));
		listaNumeros.add(generarOpcionAleatoria(preguntaAutenticacionView.getCheckWhatsappHabilitado()));
		listaNumeros.add(generarOpcionAleatoria(preguntaAutenticacionView.getCheckWhatsappHabilitado()));
		Collections.shuffle(listaNumeros);
//		listaNumeros.add(preguntaAutenticacionView.getCheckWhatsappHabilitado() ? 
//				OPCION_CELULAR_NINGUNO_ES_CORRECTO : OPCION_CELULAR_NINGUNA);
		
        preguntaAutenticacionView.setCantidadPreguntas(1);
        preguntaAutenticacionView.setCantidadRespuestas(listaNumeros.size());
        PreguntaView preguntaView = new PreguntaView();
        preguntaView.setIdPregunta("1");
		preguntaView.setPregunta(this.mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_PREGUNTA_VALIDACION_CELULAR).getMensaje());
        preguntaView.setIndice(1);

        preguntaAutenticacionView.setPregunta(preguntaView);

		List<RespuestaAutenticacionView> listaRespuestas = new ArrayList<RespuestaAutenticacionView>();
		for (String respuesta : listaNumeros) {
			RespuestaAutenticacionView respView = new RespuestaAutenticacionView();
			respView.setRespuesta(respuesta);
			listaRespuestas.add(respView);
		}
		preguntaAutenticacionView.setRespuestas(listaRespuestas);
		preguntaAutenticacionView.setLegal(obtenerLegales(LEGAL_WHATSAPP_INFORMACION));
		if (!preguntaAutenticacionView.getCheckWhatsappHabilitado()) {
			preguntaAutenticacionView.setCompanias(obtenerCompaniasCelular());
		}
		this.sesionParametros.getPreguntaValidacionTel().setOpcionCorrecta(opcionVerdadera);
		this.sesionParametros.getPreguntaValidacionTel().setCiclo(CICLO_INICIAL);
		this.sesionParametros.getPreguntaValidacionTel().setIdSesion(identificadorClienteOutEntity.getSesion());
		this.sesionParametros.getPreguntaValidacionTel().setCheckWhatsappHabilitado(preguntaAutenticacionView.getCheckWhatsappHabilitado());
		
		return preguntaAutenticacionView;
		
	}
	
	private List<CompaniaCelularView> obtenerCompaniasCelular() {
		
		List<CompaniaCelularView> companias = new ArrayList<CompaniaCelularView>();
		
		TipoDeCompaniasCelularClaveOnline[] companiasEnum = TipoDeCompaniasCelularClaveOnline.values();
		for (TipoDeCompaniasCelularClaveOnline companiaEnum : companiasEnum) {
			CompaniaCelularView celularEmpresa = new CompaniaCelularView();
			celularEmpresa.setId(companiaEnum.getCodigo());
			celularEmpresa.setText(companiaEnum.getDescripcion());
			companias.add(celularEmpresa);
		}
		
		return companias;	
	}
	
	/**
	 * 
	 * @return
	 */
	private String generarOpcionAleatoria(Boolean checkWhatsapp) {
		return PREFIJO_CELULAR + generarNumeroAleatorio(4);
	}
	
	/**
	 * 	
	 * @param longitud
	 * @return
	 */
	private String generarNumeroAleatorio(int longitud) {
		return String.valueOf(longitud < 1 ? 0 : new Random().nextInt((9 * (int) Math.pow(10, longitud - 1)) - 1) + (int) Math.pow(10, longitud - 1));
	}
	

	/**
	 * 	Retorna los ultimos 4 digitos del numero de celular
	 * @param longitud
	 * @return
	 */
	private String obtenerUltimosCuatroNumeros(String numeroConCompaniaCelular) {
		String datosCelu[] = numeroConCompaniaCelular.split(" ");
		return datosCelu[0].substring(datosCelu[0].length() - 4, datosCelu[0].length());
	}	
	
    /**
     * Obtener vista autenticacion.
     *
     * @param tipoAut
     *            the tipo aut
     * @param numero
     *            the numero
     * @param request
     *            the request
     * @return the respuesta
     */
    private Respuesta<MetodoAutenticacionView> obtenerVistaAutenticacion(String tipoAut, String numero,
            HttpServletRequest request) {

        if (CELU.equals(tipoAut)) {
            return enviarSMS(request, false,false);
        }


        String ultimosCuatro = numero.substring(numero.length() - 4);
        String mensaje = null;
        
        String accion = null;
        if (BANE.equals(tipoAut)) {
        	accion = BANELCO;
        	mensaje= ultimosCuatro;
        }
        
        if (VISA.equals(tipoAut)) {
            mensaje = ultimosCuatro;
            accion = VISA;
        }
        if (AMEX.equals(tipoAut)) {
            mensaje = ultimosCuatro;
            accion = AMEX;
        }
        MetodoAutenticacionView metodoAutenticacionView = new MetodoAutenticacionView();
        metodoAutenticacionView.setMensaje(mensaje);
        metodoAutenticacionView.setAccion(accion);
        return respuestaFactory.crearRespuestaWarning(metodoAutenticacionView, null, TipoError.METODO_AUTENTICACION,
                "");
    }

    /**
     * Debe autenticar.
     *
     * @param tipoAut
     *            the tipo aut
     * @return true, if successful
     */
    private boolean debeAutenticar(String tipoAut) {
        return !NO_AUTENTICA.equals(tipoAut);
    }

    /**
	 * Update registro sesion.
	 *
	 * @param request
	 *            the request
	 * @param identificadorClienteOutEntity
	 *            the identificador cliente out entity
	 */
    private void updateRegistroSesion(HttpServletRequest request,
            IdentificadorClienteOutEntity identificadorClienteOutEntity) {

        RegistroSesion registroSesion = sesionParametros.getRegistroSession();
        registroSesion.setLogFile((String) request.getSession(Boolean.FALSE).getAttribute("logFileName"));
        registroSesion.setPid((String) request.getSession(Boolean.FALSE).getAttribute("pid"));
        if (identificadorClienteOutEntity != null) {
            registroSesion.setNup(identificadorClienteOutEntity.getNup());
        }
        sesionParametros.setRegistroSession(registroSesion);
    }

    /**
	 * Guarda los datos que son comunes para la session a lo largo de toda la
	 * invocacion.
	 *
	 * @param request
	 *            the request
	 * @param credencialesClaveOnline
	 *            the credenciales clave online
	 */
    private void guardarRegistroSesion(HttpServletRequest request, CredencialesClaveOnline credencialesClaveOnline) {
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setLogFile((String) request.getSession(Boolean.FALSE).getAttribute("logFileName"));
        registroSesion.setPid((String) request.getSession(Boolean.FALSE).getAttribute("pid"));
        registroSesion.setIp(NetworkUtil.getRemoteIp(request));
        registroSesion.setPrefijo(credencialesClaveOnline.getPrefijo());
        registroSesion.setSufijo(credencialesClaveOnline.getSufijo());
        registroSesion
                .setTipoTeclado(TipoTeclado.getTipoTecladoFromDescripcion(credencialesClaveOnline.getTipoTeclado()));
        registroSesion.setHostName(NetworkUtil.getHostName());
        registroSesion.setDispositivo(credencialesClaveOnline.getDispositivo());
        registroSesion.setNavegador(credencialesClaveOnline.getNavegador());
        if (!StringUtils.isBlank(credencialesClaveOnline.getFechaDeNacimiento())) {
            String fechaNac = FechaUtils.modificarFormatoFechas(credencialesClaveOnline.getFechaDeNacimiento(),
                    FORMATO_FECHA, FORMATO_FECHA_ESTADISTICAS);
            registroSesion.setSinonimo(fechaNac);
            registroSesion.setClienteSinonimo(Boolean.TRUE);
        } else {
            registroSesion.setSinonimo("19000101");
            registroSesion.setClienteSinonimo(Boolean.FALSE);
        }
        registroSesion.setNup(credencialesClaveOnline.getNup());
        registroSesion.setDni(credencialesClaveOnline.getDniOri());

        sesionParametros.setRegistroSession(registroSesion);

    }

    /**
     * Grabar estadistica.
     *
     */
    private void grabarEstadistica(HttpServletRequest request) {
        if (sesionParametros.getCredencialesClaveOnline().isIngresoRegistrar()) {
            sesionParametros.getCredencialesClaveOnline().setIngresoRegistrar(Boolean.FALSE);
            String codEstadistica = ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.REGISTRAR_ACCESO_FUNCIONALIDAD_OBTENER_USER_Y_PASSWORD, 
            		EstadisticasConstants.REGISTRAR_ACCESO_FUNCIONALIDAD_OBTENER_USER_Y_PASSWORD_WEBVIEW);
            estadisticaManager.add(codEstadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }
        if (sesionParametros.getCredencialesClaveOnline().isIngresoRenovar()) {
            sesionParametros.getCredencialesClaveOnline().setIngresoRenovar(Boolean.FALSE);
            String codEstadistica = ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.OBTENER_CLAVE_ACCESO_FUNCIONALIDAD_OBTENER_USER_Y_PASSWORD, 
            		EstadisticasConstants.OBTENER_CLAVE_ACCESO_FUNCIONALIDAD_OBTENER_USER_Y_PASSWORD_WEBVIEW);
            estadisticaManager.add(codEstadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }
        if (sesionParametros.getCredencialesClaveOnline().isIngresoSolucionar()) {
            sesionParametros.getCredencialesClaveOnline().setIngresoSolucionar(Boolean.FALSE);
            String codEstadistica = ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.SOLUCIONAR_ACCESO_FUNCIONALIDAD_OBTENER_USER_Y_PASSWORD, 
            		EstadisticasConstants.SOLUCIONAR_ACCESO_FUNCIONALIDAD_OBTENER_USER_Y_PASSWORD_WEBVIEW);
            estadisticaManager.add(codEstadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }
    }

    /**
     * Obtener identificador.
     *
     * @param credencialesClaveOnline
     *            the credenciales clave online
     * @return the identificador cliente in entity
     */
    private IdentificadorClienteInEntity obtenerIdentificador(CredencialesClaveOnline credencialesClaveOnline) {
        IdentificadorClienteInEntity identificadorClienteInEntity = new IdentificadorClienteInEntity();

        identificadorClienteInEntity.setFecha(
                FechaUtils.parsearFechaNacimiento(credencialesClaveOnline.getFechaDeNacimiento(), FORMATO_FECHA));
        identificadorClienteInEntity.setDni(credencialesClaveOnline.getDniOri());
        identificadorClienteInEntity.setIp(NetworkUtil.getHostAddress());

        return identificadorClienteInEntity;
    }

    /**
     * Inicializar timer.
     */
    private void inicializarTimer() {
        Calendar calendar = Calendar.getInstance();
        Long milisegEnMinutos = TimeUnit.MILLISECONDS.toMinutes(minutosSesionActiva);
        calendar.add(Calendar.MINUTE, milisegEnMinutos.intValue());
        Date inicioSesion = calendar.getTime();
        LOGGER.info("Se inicializa el control de expiracion de sesion de clave online.");
        sesionParametros.setTimerClaveOnline(inicioSesion);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager
     * #enviarSMS(javax.servlet.http.HttpServletRequest, java.lang.Boolean)
     */
    @Override
    public Respuesta<MetodoAutenticacionView> enviarSMS(HttpServletRequest request, Boolean reenvio, Boolean checkWhatsapp) {

        try {
            controlarTimeOutSesion(request);
        } catch (SesionExpiradaException e) {
            LOGGER.error(e.getMessage(), e);
            return crearRespuestaSesionExpirada();
        }
        
        VerificacionPasosClaveOnline  verificacionPasosClaveOnline=this.sesionParametros.getVerificacionPasosClaveOnline();
        //si no se cargaron los pasos de validacion o no seteado el paso de validacion de metodo de autenticacion: devuelvo error generico
		if (verificacionPasosClaveOnline == null || !verificacionPasosClaveOnline.isValidarAutenticacion()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}
                
        MetodoAutenticacionView metodoAutenticacionView = new MetodoAutenticacionView();
        RegistroSesion registroSesion = sesionParametros.getRegistroSession();
        Respuesta<MetodoAutenticacionView> respuestaMetodoAutenticacionView = null;
        DatosAutenticacion datosAutenticacion = registroSesion.getDatosAutenticacion();

        if (Boolean.TRUE.equals(reenvio)){
            datosAutenticacion.setCiclo(CICLO_INICIAL);
        }else{
            datosAutenticacion.setCiclo(datosAutenticacion.getCiclo() + 1);
        }

        if (sesionParametros.getCheckWhatsapp() != null) {
        	if(sesionParametros.getCheckWhatsapp()) {
        		datosAutenticacion.setFuncionEnum(FuncionEnum.WHATSAPP);
            	metodoAutenticacionView.setAccion(WHATSAPP);
            	datosAutenticacion.setEmpresaCelular(cargarEmpresaCelular(sesionParametros.getEmpresaCelularServicioIdesgiclie()));
            } else {
            	datosAutenticacion.setFuncionEnum(FuncionEnum.ENVIAR_SMS);
            	metodoAutenticacionView.setAccion(SMS);
                String numeroCelularConEmpresa[] = sesionParametros.getRespuestaNumeroEmpresaCelularClaveOnline().split(" ");
                datosAutenticacion.setEmpresaCelular(numeroCelularConEmpresa[1]);
            }
        } else {
        	datosAutenticacion.setFuncionEnum(FuncionEnum.ENVIAR_SMS);
        	metodoAutenticacionView.setAccion(SMS);
        }
        datosAutenticacion.setClave(CLAVE_VACIA);
        datosAutenticacion.setFechaDeNacimiento(sesionParametros.getCredencialesClaveOnline().getFechaDeNacimiento());
        String numero = datosAutenticacion.getNumero().trim();
        String ultimosCuatro = numero.substring(numero.length() - 4);

        Respuesta<Void> respuestaValidacion = claveOnlineBO.validarClave(datosAutenticacion);
        if (EstadoRespuesta.OK.equals(respuestaValidacion.getEstadoRespuesta())) {
            if (!reenvio) {
            	estadisticaManager.add(EstadisticasConstants.ENVIAR_SMS_SEGSGIAUT,EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                metodoAutenticacionView.setMensaje(ultimosCuatro);
                respuestaMetodoAutenticacionView = respuestaFactory.crearRespuestaWarning(metodoAutenticacionView, null,
                        TipoError.METODO_AUTENTICACION, "");
            } else {
            	estadisticaManager.add(EstadisticasConstants.REENVIAR_SMS_SEGSGIAUT,EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
//                Mensaje mensaje = mensajeManager
//                        .obtenerMensajePorCodigo(CodigoMensajeConstantes.CLAVE_ONLINE_REENVIO_WHATSAPP);
//                metodoAutenticacionView.setMensaje(mensaje.getMensaje());
            	metodoAutenticacionView.setLegal(obtenerLegales(LEGAL_WHATSAPP_INFORMACION_REENVIAR));
                respuestaMetodoAutenticacionView = respuestaFactory.crearRespuestaOk(metodoAutenticacionView);
            }
            if(sesionParametros.getCheckWhatsapp()){
            	estadisticaManager.add(EstadisticasConstants.CLAVE_ONLINE_WHATSAPP_VALIDAR,EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            }
        } else {
            if (reenvio) {
            	estadisticaManager.add(EstadisticasConstants.REENVIAR_SMS_SEGSGIAUT,EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
//                Mensaje mensaje = mensajeManager
//                        .obtenerMensajePorCodigo(CodigoMensajeConstantes.CLAVE_ONLINE_REENVIO_WHATSAPP);
//                metodoAutenticacionView.setMensaje(mensaje.getMensaje());
            	metodoAutenticacionView.setLegal(obtenerLegales(LEGAL_WHATSAPP_INFORMACION_REENVIAR));
                respuestaMetodoAutenticacionView = Respuesta.copy(MetodoAutenticacionView.class, respuestaValidacion);
                respuestaMetodoAutenticacionView.setRespuesta(metodoAutenticacionView);
            } else {
            	estadisticaManager.add(EstadisticasConstants.ENVIAR_SMS_SEGSGIAUT,EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            	respuestaMetodoAutenticacionView = respuestaFactory.crearRespuestaError(MetodoAutenticacionView.class,
            			null, TipoError.LOGIN_ERROR_TOTAL, null);
                /*if(respuestaValidacion.getItemsMensajeRespuesta() != null) {
                	respuestaMetodoAutenticacionView.setItemMensajeRespuesta(respuestaValidacion.getItemsMensajeRespuesta());
                }*/
            }
            if(sesionParametros.getCheckWhatsapp()){
            	estadisticaManager.add(EstadisticasConstants.CLAVE_ONLINE_WHATSAPP_VALIDAR,EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
        }

        return respuestaMetodoAutenticacionView;
    }

    private String cargarEmpresaCelular(String respuestaServicio) {
    	
    	String companiaServicio;
    	if (respuestaServicio.contains(TipoDeCompaniasCelularClaveOnline.CLARO.getCodigo())) {
    		companiaServicio = TipoDeCompaniasCelularClaveOnline.CLARO.getCodigo();
    	} else if (respuestaServicio.contains(TipoDeCompaniasCelularClaveOnline.MOVISTAR.getCodigo())) {
    		companiaServicio = TipoDeCompaniasCelularClaveOnline.MOVISTAR.getCodigo();
    	} else if (respuestaServicio.contains(TipoDeCompaniasCelularClaveOnline.PERSONAL.getCodigo())) {
    		companiaServicio = TipoDeCompaniasCelularClaveOnline.PERSONAL.getCodigo();
    	} else if (respuestaServicio.contains(TipoDeCompaniasCelularClaveOnline.NEXTEL.getCodigo())) {
    		companiaServicio = TipoDeCompaniasCelularClaveOnline.NEXTEL.getCodigo();
    	} else {
    		companiaServicio = "    ";
    	}
    	
    	return companiaServicio;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager#
     * confirmarMetodoAutenticacion(org.apache.cxf.jaxrs.ext.MessageContext,
     * java.lang.String)
     */
    @Override
    public Respuesta<MetodoAutenticacionView> confirmarMetodoAutenticacion(MessageContext mc, String datoEntrada) {

        String cryptoInput = encryPines.obtenerSoloCrypto(datoEntrada);
        CredencialesClaveOnline credencialesClaveOnline = encryPines.obtenerViewFromJson(cryptoInput,
                CredencialesClaveOnline.class);

        try {
            controlarTimeOutSesion(mc.getHttpServletRequest());
        } catch (SesionExpiradaException e) {
            LOGGER.error(e.getMessage(), e);
            return crearRespuestaSesionExpirada();
        }
        VerificacionPasosClaveOnline  verificacionPasosClaveOnline=this.sesionParametros.getVerificacionPasosClaveOnline();
        //si no se cargaron los pasos de validacion o no seteado el paso de validacion de metodo de autenticacion: devuelvo error generico
		if (verificacionPasosClaveOnline == null || !verificacionPasosClaveOnline.isValidarAutenticacion()) {
			
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}
        RegistroSesion registroSesion = sesionParametros.getRegistroSession();
        Respuesta<MetodoAutenticacionView> respuestaMetodoAutenticacionView;
        DatosAutenticacion datosAutenticacion = registroSesion.getDatosAutenticacion();
        datosAutenticacion.setCiclo(datosAutenticacion.getCiclo() + 1);
        datosAutenticacion.setFuncionEnum(obtenerFuncion(datosAutenticacion.getTipoAut()));
        datosAutenticacion.setClave(credencialesClaveOnline.getClave());
        datosAutenticacion.setFechaDeNacimiento(sesionParametros.getCredencialesClaveOnline().getFechaDeNacimiento());
        datosAutenticacion.setNup(registroSesion.getDatosAutenticacion().getNup());
        if (StringUtils.isEmpty(sesionParametros.getRespuestaNumeroEmpresaCelularClaveOnline()) || 
        	OPCION_CELULAR_NINGUNA.equals(sesionParametros.getRespuestaNumeroEmpresaCelularClaveOnline())) {
            datosAutenticacion.setEmpresaCelular(cargarEmpresaCelular(sesionParametros.getEmpresaCelularServicioIdesgiclie()));
        } else {
    		String eleccionEmpresaCliente[] = sesionParametros.getRespuestaNumeroEmpresaCelularClaveOnline().split(" ");
    		String opcionEmpresa = eleccionEmpresaCliente[1];
            datosAutenticacion.setEmpresaCelular(cargarEmpresaCelular(opcionEmpresa));
        }
        Respuesta<Void> respuestaValidacion = claveOnlineBO.validarClave(datosAutenticacion);
        String codigoEstadistica = obtenerCodigoEstadistica(datosAutenticacion.getTipoAut(), mc.getHttpServletRequest());
        String estadoEstadistica;
        if (EstadoRespuesta.OK.equals(respuestaValidacion.getEstadoRespuesta())) {
        	Respuesta<TarjetaBanelcoView> respuestaTarjetaBanelco = obtenerTarjetaParaValidarPin(mc.getHttpServletRequest());
            respuestaMetodoAutenticacionView = respuestaFactory.crearRespuestaOk(MetodoAutenticacionView.class);
            MetodoAutenticacionView respuestaView = new MetodoAutenticacionView();
            if (EstadoRespuesta.OK.equals(respuestaTarjetaBanelco.getEstadoRespuesta())) {
            	respuestaView.setMensajePinBanelco(respuestaTarjetaBanelco.getRespuesta().getMensajeFeedbackOk());
            	respuestaMetodoAutenticacionView.setRespuesta(respuestaView);
            } else {
            	ResumenCliente resumenCliente = new ResumenCliente();
            	resumenCliente.setNup(datosAutenticacion.getNup());
            	resumenCliente.setFechaNacimiento(datosAutenticacion.getFechaDeNacimiento());
            	sesionCliente.setResumenCliente(resumenCliente);
            	Respuesta<Void> respuestaRSA = loginManager.analyzeRsa("SESSION_SIGNIN_GENERACION_CLAVE", null, false, false);
                
                if (EstadoRespuesta.ERROR.equals(respuestaRSA.getEstadoRespuesta())) {
                	return respuestaFactory.crearRespuestaError("", TipoError.LOGIN_ERROR_TOTAL, CodigoMensajeConstantes.DENY_RSA_NUEVO_MENSAJE);
                }
            }
            estadoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
			this.sesionParametros.getVerificacionPasosClaveOnline().setValidarAutenticacion(false);
			
        } else {
            respuestaMetodoAutenticacionView = Respuesta.copy(MetodoAutenticacionView.class, respuestaValidacion);
            estadoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
        }
        	estadisticaManager.add(codigoEstadistica, estadoEstadistica);
        
        return respuestaMetodoAutenticacionView;
    }
        
    
    /**

     * Obtener codigo estadistica.
     *
     * @param tipoAut
     *            the tipo aut
     * @param request
     *            the request
     * @return the string
     */
    private String obtenerCodigoEstadistica(String tipoAut, HttpServletRequest request) {
        if (VISA.equals(tipoAut)) {
            return ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.CLAVE_ONLINE_VISA, 
            		EstadisticasConstants.CLAVE_ONLINE_VISA_WEBVIEW);
        }
        if (AMEX.equals(tipoAut)) {
            return ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.CLAVE_ONLINE_AMEX, 
            		EstadisticasConstants.CLAVE_ONLINE_AMEX_WEBVIEW);
        }
        if (BANE.equals(tipoAut)) {
            return ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.CLAVE_ONLINE_BANELCO, 
            		EstadisticasConstants.CLAVE_ONLINE_BANELCO_WEBVIEW);
        }
        if (CELU.equals(tipoAut)) {
        	if(sesionParametros.getCheckWhatsapp()){
        		return EstadisticasConstants.CLAVE_ONLINE_WHATSAPP_CONTINUAR;
        	}
            return ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.CLAVE_ONLINE_SMS, 
            		EstadisticasConstants.CLAVE_ONLINE_SMS_WEBVIEW);
        }
        return null;
    }

    /**
     * Obtener funcion.
     *
     * @param tipoAut
     *            the tipo aut
     * @return the funcion enum
     */
    private FuncionEnum obtenerFuncion(String tipoAut) {

        if (VISA.equals(tipoAut) || AMEX.equals(tipoAut)) {
            return FuncionEnum.VISA_AMEX;
        }
        if (BANE.equals(tipoAut)) {
            return FuncionEnum.BANE;
        }
        if (CELU.equals(tipoAut)) {
            return FuncionEnum.CELU;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager
     * #identificarClienteSolucionar(org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<Void> identificarClienteSolucionar(MessageContext mc) {
        CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
        credencialesClaveOnline.setIngresoSolucionar(Boolean.TRUE);
        return identificarCliente(credencialesClaveOnline, mc.getHttpServletRequest());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager
     * #identificarClienteRegistrar(org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<Void> identificarClienteRegistrar(MessageContext mc) {
        CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
        credencialesClaveOnline.setIngresoRegistrar(Boolean.TRUE);
        return identificarCliente(credencialesClaveOnline, mc.getHttpServletRequest());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager
     * #identificarClienteRenovar(org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<Void> identificarClienteRenovar(MessageContext mc) {
        CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
        credencialesClaveOnline.setIngresoRenovar(Boolean.TRUE);
        return identificarCliente(credencialesClaveOnline, mc.getHttpServletRequest());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager
     * #altaSGIClave(ar.com.santanderrio.obp.base.clientes.entities.
     * CredencialCliente, org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<AltaClaveOnlineView> altaSGIClave(CredencialCliente credencialCliente, MessageContext mc) {
       
    	try {
            controlarTimeOutSesion(mc.getHttpServletRequest());
        } catch (SesionExpiradaException e) {
            LOGGER.error(e.getMessage(), e);
            Respuesta<MetodoAutenticacionView> respuesta = crearRespuestaSesionExpirada();
            respuesta.setSkipLog(Boolean.TRUE);
            return Respuesta.copy(AltaClaveOnlineView.class, respuesta);
        }
    	
    	if(this.sesionParametros.getVerificacionPasosClaveOnline().faltanCompletarPasos()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
    	}
    	
        Respuesta<AltaClaveOnlineView> altaClaveOnlineView;
        RegistroSesion registroSesion = sesionParametros.getRegistroSession();
        AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
        in.setDni(credencialCliente.getDniOri());
        in.setNup(registroSesion.getNup());
        in.setFechaNacimiento(credencialCliente.getFechaNacimiento());
        in.setPrefijo(credencialCliente.getPrefijo());
        in.setSufijo(credencialCliente.getSufijo());
        in.setClaveEncriptado(credencialCliente.getClave());
        in.setUsuarioEncriptado(credencialCliente.getUsuario());
        in.setIp(credencialCliente.getIp());
        in.setIdSesion(credencialCliente.getIdSesion());
        Respuesta<Void> respuesta = claveOnlineBO.altaSGIClave(in);
        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            altaClaveOnlineView = respuestaFactory.crearRespuestaOk(AltaClaveOnlineView.class);
        } else {
            altaClaveOnlineView = Respuesta.copy(AltaClaveOnlineView.class, respuesta);
        }
        return altaClaveOnlineView;
    }
    
    

    
    

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager
     * #validarHash(ar.com.santanderrio.obp.servicios.clave.online.view. HashView)
     */
    @Override
    public Respuesta<Void> validarHash(HashView hash) {
        String hashSaved = sesionParametros.getValidacionHash();
        if (hashSaved != null && hashSaved.equals(hash.getHash())) {
            return respuestaFactory.crearRespuestaOk(Void.class);
        }
        return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
    }

	@Override
	public Respuesta<MetodoAutenticacionView> confirmarRespuestaTelefono(HttpServletRequest request, String datoEntrada) {
		String cryptoInput = encryPines.obtenerSoloCrypto(datoEntrada);
		CredencialesClaveOnline credencialesClaveOnline = encryPines.obtenerViewFromJson(cryptoInput, CredencialesClaveOnline.class);
		sesionParametros.setRespuestaNumeroEmpresaCelularClaveOnline(credencialesClaveOnline.getRespuesta());
		String eleccionCliente[] = credencialesClaveOnline.getRespuesta().split(" ");
		String opcion = eleccionCliente[0];
		try {
			controlarTimeOutSesion(request);
		} catch (SesionExpiradaException e) {
			LOGGER.error(e.getMessage(), e);
			return crearRespuestaSesionExpirada();
		}
		VerificacionPasosClaveOnline verificacionPasosClaveOnline = this.sesionParametros.getVerificacionPasosClaveOnline();
		// si no se cargaron los pasos de validacion o no esta seteado el paso de validacion de pregunta telefono: devuelvo error generico
		if (verificacionPasosClaveOnline == null || !verificacionPasosClaveOnline.isValidarPreguntaTelefono()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}
		
		Respuesta<MetodoAutenticacionView> respuesta = respuestaFactory.crearRespuestaOk(MetodoAutenticacionView.class);

		PreguntaValidacionTel preguntaValidacionTel = this.sesionParametros.getPreguntaValidacionTel();
		Integer cicloSesionPreguntaValidacionTel =  preguntaValidacionTel.getCiclo();
		String sesion = preguntaValidacionTel.getIdSesion();
		Boolean checkWhatsappHabilitado = preguntaValidacionTel.getCheckWhatsappHabilitado();
		Boolean checkWhatsapp = credencialesClaveOnline.getCheckWhatsapp() != null ? credencialesClaveOnline.getCheckWhatsapp() : Boolean.FALSE;
		Boolean whatsappNoSeleccionado = checkWhatsappHabilitado && !checkWhatsapp;
		// Guardo el valor en sesion para utilizarlo en enviarSMS
		sesionParametros.setCheckWhatsapp(checkWhatsapp);
		ValidacionPreguntaIn preguntaIn = getPreguntaValidarTelefono(preguntaValidacionTel, opcion, 
				cicloSesionPreguntaValidacionTel, sesion, checkWhatsapp);
		preguntaIn.setNup(sesionParametros.getCredencialesClaveOnline().getNup());
		preguntaIn.setDni(sesionParametros.getCredencialesClaveOnline().getDniOri());
		Date fechaNacimiento = FechaUtils.parsearFechaNacimiento(sesionParametros.getCredencialesClaveOnline().getFechaDeNacimiento(), "ddMMyyyy");
		preguntaIn.setFechaNacimiento(fechaNacimiento);
		
		//llama al servicio VALSGTEL100
		Respuesta<IdentificadorClienteOutEntity> respuestaValsgtel = claveOnlineBO.validarRespuestaTelefono(preguntaIn);

		if (EstadoRespuesta.ERROR.equals(respuestaValsgtel.getEstadoRespuesta())) {
			//ver que estadisticas poner
			String codEstadistica = ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.VALIDACION_RESPUESTA_CELULAR, 
            		    EstadisticasConstants.VALIDACION_RESPUESTA_CELULAR_WEBVIEW);
			estadisticaManager.add(codEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			this.sesionParametros.getPreguntaValidacionTel().setCiclo(preguntaValidacionTel.getCiclo() + 1);
			return Respuesta.copy(MetodoAutenticacionView.class, respuestaValsgtel);

		} else {
			//si responde ok la pregunta de telefono fue validada
			this.sesionParametros.getVerificacionPasosClaveOnline().setValidarPreguntaTelefono(false);

			//si el coordinador respondio bien  y es ninguna
			if (OPCION_CELULAR_NINGUNA.equals(opcion) || OPCION_CELULAR_NINGUNO_ES_CORRECTO.equals(opcion) || whatsappNoSeleccionado) {
				//si selecciona ninguna se puede llegar a usar el tipo de autorizacion que devuelve
				this.sesionParametros.getPreguntaValidacionTel().setTipoAuthValsgTel(respuestaValsgtel.getRespuesta().getTipoAut());
				this.sesionParametros.getPreguntaValidacionTel().setOpcionNinguna(true);
				this.sesionParametros.getPreguntaValidacionTel().setNumeroValsgTel(respuestaValsgtel.getRespuesta().getNumero());
				String codEstadistica = ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.VALIDACION_RESPUESTA_CELULAR_NINGUNO, 
	            		    EstadisticasConstants.VALIDACION_RESPUESTA_CELULAR_NINGUNO_WEBVIEW);
				estadisticaManager.add(codEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				//si la cantidad de ciclos es 0 , no hay preguntas autenticacion
				if (CERO.equals(respuestaValsgtel.getRespuesta().getCiclos())) {
					if(debeAutenticar(respuestaValsgtel.getRespuesta().getTipoAut())) {
						grabarEstadisticaIngresoAutenticacionOK(request);
			            sesionParametros.getRegistroSession().getDatosAutenticacion().setCiclo(0);
						respuesta = obtenerVistaAutenticacion(respuestaValsgtel.getRespuesta().getTipoAut(), respuestaValsgtel.getRespuesta().getNumero().trim(), request);
						this.sesionParametros.getVerificacionPasosClaveOnline().setValidarAutenticacion(true);
					}					
				} else {
			        credencialesClaveOnline.setCiclo(sesionParametros.getCredencialesClaveOnline().getCiclo());
					credencialesClaveOnline.setNup(respuestaValsgtel.getRespuesta().getNup());
					respuesta = obtenerPreguntasAutenticacion(credencialesClaveOnline, respuestaValsgtel.getRespuesta(), request);
					this.sesionParametros.getVerificacionPasosClaveOnline().setValidarPreguntasSeguridad(true);

				}
			} else {
				if (checkWhatsappHabilitado) {
					estadisticaManager.add(EstadisticasConstants.CLAVE_ONLINE_WHATSAPP_CHECK, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				} else {
					grabarEstadisticaIngresoAutenticacionOK(request);
				}
				//piso el numero de session de autenticacion a usar en caso de que responda bien la pregunta de celular
				RegistroSesion registroSesion = sesionParametros.getRegistroSession();
				DatosAutenticacion datosAutenticacion = registroSesion.getDatosAutenticacion();
				String datosNumero[] = datosAutenticacion.getNumero().split(" ");
				datosAutenticacion.setNumero(datosNumero[0]);
				sesionParametros.getRegistroSession().getDatosAutenticacion().setCiclo(0);
				this.sesionParametros.getVerificacionPasosClaveOnline().setValidarAutenticacion(true);
				respuesta = this.enviarSMS(request, false,preguntaIn.getCheckWhatsapp());

			}
		}
		return respuesta;
	}

	
	/**
	 * 
	 * @param preguntaAutenticacion
	 * @param opcion
	 * @param ciclo
	 * @param sesion
	 * @return
	 */
	private ValidacionPreguntaIn getPreguntaValidarTelefono(PreguntaValidacionTel preguntaValidacionTel, String opcion, Integer ciclo, String sesion, Boolean checkWhatsapp) {

		ValidacionPreguntaIn preguntaValidar = new ValidacionPreguntaIn();

		preguntaValidar.setIdPregunta(preguntaValidacionTel.getIdPregunta());
		preguntaValidar.setOpcionCorrecta(preguntaValidacionTel.getOpcionCorrecta());
		preguntaValidar.setRespuesta(opcion);
		preguntaValidar.setIp(NetworkUtil.getHostAddress());
		preguntaValidar.setIdSesion(sesion);
		preguntaValidar.setCiclo(ciclo);
		preguntaValidar.setOrdenPregunta(preguntaValidacionTel.getIndice());
		preguntaValidar.setCheckWhatsapp(checkWhatsapp);
		preguntaValidar.setCheckWhatsappHabilitado(preguntaValidacionTel.getCheckWhatsappHabilitado());
		
		String validacion = "M";
		if (preguntaValidacionTel.getOpcionCorrecta().equals(opcion)) {
			validacion = "B";
		}else if (OPCION_CELULAR_NINGUNA.equals(opcion) || OPCION_CELULAR_NINGUNO_ES_CORRECTO.equals(opcion)){
			validacion = "N";
		}
		preguntaValidar.setValidacion(validacion);

		return preguntaValidar;
	}

	@Override
	public Respuesta<CambioDatosContactoView> actualizarNumeroCelular(CambioDatosContactoView cambioDatosContacto) {

		if (this.sesionParametros.getVerificacionPasosClaveOnline() != null && !this.sesionParametros.getVerificacionPasosClaveOnline().faltanCompletarPasos()) {
			return this.clienteManager.cambioDatosContacto(cambioDatosContacto, false);
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, null);
	}

	@Override
	public Respuesta<CelularMyaYCompaniasTelResponse> obtenerNumeroCelularYcompanias() {

		if (this.sesionParametros.getVerificacionPasosClaveOnline() != null && !this.sesionParametros.getVerificacionPasosClaveOnline().faltanCompletarPasos()) {

			MyaDTOIn myaDTOIn = new MyaDTOIn();
			Respuesta<CelularMyaYCompaniasTelResponse> respuesta = new Respuesta<CelularMyaYCompaniasTelResponse>();
			CelularMyaYCompaniasTelResponse celularMyaYCompaniasTelResponse = null;
			Cliente cliente = sesionCliente.getCliente();
			myaDTOIn.setSoloPrincipales(false);
			MyaDTOOut myaDTOOut = myaBO.consultaWsEstadoCliente(cliente, myaDTOIn);
			if (!ClienteEstadoEnum.TIMEOUT.equals(myaDTOOut.getClienteEstadoEnum())) {
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				celularMyaYCompaniasTelResponse = armarRespuestaNumeroCelularYcompanias(myaDTOOut);
				respuesta.setRespuesta(celularMyaYCompaniasTelResponse);

			} else {// Si hay timeout Error generico

				respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_SERVICIO_MYA);
				estadisticaManager.add(EstadisticasConstants.VISUALIZACION_DATOS_DE_DOMICILIO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
			return respuesta;

		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, null);
	}
	
	   /**
     * Builds the cambio conctacto response.
     *
     * @param out
     *            the out
     * @return the cambio datos contacto response
     */
    private CelularMyaYCompaniasTelResponse armarRespuestaNumeroCelularYcompanias (MyaDTOOut out) {
        
    	CelularMyaYCompaniasTelResponse celularMyaYCompaniasTelResponse=new CelularMyaYCompaniasTelResponse();
    	CelularResponse celularPrimario = new CelularResponse();
        if (StringUtils.isNotBlank(out.getCelular())) {
            String porcionVisible = out.getCelular().substring(4, out.getCelular().length());
            String[] array = out.getCelular().split("-");
            celularPrimario.setCodigoArea(array[0]);
            celularPrimario.setNumero(array[1]);
            celularPrimario.setNumeroCompleto(StringUtils.leftPad(porcionVisible, 8, 'X'));
            celularPrimario.setTitulo(TITULO_CELULAR_PRIMARIO);
            celularMyaYCompaniasTelResponse.setHayCelular(true);
            celularMyaYCompaniasTelResponse.setCelularResponse(celularPrimario);
        }
        if (out.getTipoCompaniaEnum() != null) {
            celularPrimario.setEmpresa(out.getTipoCompaniaEnum().getDescripcion());
        }

        celularMyaYCompaniasTelResponse.setCompanias(companiasCelular);
        return celularMyaYCompaniasTelResponse;
    }

	/**
	 * Obtener legales.
	 *
	 * @param codigoLegal
	 *            the codigo legal
	 * @return the string
	 */
	private String obtenerLegales(String codigoLegal) {
		try {
			return legalBO.obtenerLegal(codigoLegal);
		} catch (DAOException e) {
			LOGGER.error("Falla al obtener legales");
			return StringUtils.EMPTY;
		}
	}

	@Override
	public Respuesta<TarjetaBanelcoView> obtenerTarjetaParaValidarPin(HttpServletRequest request) {

		String ip = NetworkUtil.getRemoteIp(request);
		RegistroSesion registroSesion = sesionParametros.getRegistroSession();
		DatosAutenticacion datosAutenticacion = registroSesion.getDatosAutenticacion();
        datosAutenticacion.setFechaDeNacimiento(sesionParametros.getCredencialesClaveOnline().getFechaDeNacimiento());

		try {
			String numeroTarjeta = claveOnlineBO.obtenerTarjetaParaValidarPin(datosAutenticacion, ip);
			TarjetaBanelcoView respuestaView = new TarjetaBanelcoView();
			sesionParametros.setNumeroTarjetaDebitoAValidar(numeroTarjeta);
			String numeroTrimeado = numeroTarjeta.trim();
			String numeroCortado = numeroTrimeado.substring(numeroTrimeado.length()-4, numeroTrimeado.length());
			String mensajeInformativo = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.LOGIN_PIN_BANELCO_SOLICITUD_PIN).getMensaje();
			respuestaView.setMensajeFeedbackOk(MessageFormat.format(mensajeInformativo, numeroCortado));
			estadisticaManager.add(EstadisticasConstants.LOGIN_PIN_BANELCO_PEDIR_PIN, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(respuestaView);
		} catch (BusinessException e) {
			estadisticaManager.add(EstadisticasConstants.LOGIN_PIN_BANELCO_PEDIR_PIN, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
	}

	@Override
	public Respuesta<TarjetaBanelcoView> validarPinBanelco(HttpServletRequest request, TarjetaBanelcoView banelcoView) {
		
		String ip = NetworkUtil.getRemoteIp(request);
		RegistroSesion registroSesion = sesionParametros.getRegistroSession();
		DatosAutenticacion datosAutenticacion = registroSesion.getDatosAutenticacion();
        datosAutenticacion.setFechaDeNacimiento(sesionParametros.getCredencialesClaveOnline().getFechaDeNacimiento());
		
		try {
	        banelcoView.setCiclo(sesionParametros.getNumeroIntentoValidarPinBanelco()+1);
			banelcoView.setNumeroTarjeta(sesionParametros.getNumeroTarjetaDebitoAValidar());
			String codigoError = claveOnlineBO.validarPinBanelco(datosAutenticacion, ip, banelcoView);
			if (RESPUESTA_OK.equals(codigoError)) {
				TarjetaBanelcoView respuestaView = new TarjetaBanelcoView();				
				respuestaView.setMensajeFeedbackOk("VALIDACION OK");
				estadisticaManager.add(EstadisticasConstants.LOGIN_PIN_BANELCO_VALIDAR_PIN, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				
            	ResumenCliente resumenCliente = new ResumenCliente();
            	resumenCliente.setNup(datosAutenticacion.getNup());
            	resumenCliente.setFechaNacimiento(datosAutenticacion.getFechaDeNacimiento());
            	sesionCliente.setResumenCliente(resumenCliente);
                Respuesta<Void> respuestaRSA = loginManager.analyzeRsa("SESSION_SIGNIN_GENERACION_CLAVE", null, false, false);
            	
                if (EstadoRespuesta.ERROR.equals(respuestaRSA.getEstadoRespuesta())) {
                	return respuestaFactory.crearRespuestaError("", TipoError.LOGIN_ERROR_TOTAL, CodigoMensajeConstantes.DENY_RSA_NUEVO_MENSAJE);
                }
				
				return respuestaFactory.crearRespuestaOk(respuestaView);
			} else {
				return manejoRespuestaErrorValidarPin(codigoError);
			}
		} catch (BusinessException e) {
			estadisticaManager.add(EstadisticasConstants.LOGIN_PIN_BANELCO_VALIDAR_PIN, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
	}
	
	private Respuesta<TarjetaBanelcoView> manejoRespuestaErrorValidarPin(String codigoError) {
		
		Respuesta<TarjetaBanelcoView> respuesta;
		
		if (ERROR_REINTENTOS_AGOTADOS.equals(codigoError) || ERROR_USUARIO_BLOQUEADO1.equals(codigoError) || ERROR_USUARIO_BLOQUEADO2.equals(codigoError)) {
			estadisticaManager.add(EstadisticasConstants.LOGIN_PIN_BANELCO_VALIDAR_PIN, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS, CodigoMensajeConstantes.LOGIN_PIN_BANELCO_ERROR_USUARIO_BLOQUEADO);
		} else if (ERROR_CLIENTE_SIN_MEDIO_AUTENTICACION_VALIDO.equals(codigoError)) {
			estadisticaManager.add(EstadisticasConstants.LOGIN_PIN_BANELCO_VALIDAR_PIN, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS, CodigoMensajeConstantes.LOGIN_PIN_BANELCO_ERROR_ERROR_CLIENTE_SIN_MEDIO_AUTENTICACION_VALIDO);
		} else if (ERROR_CLIENTE_BLOQUEADO_AGRAVADO1.equals(codigoError) || ERROR_CLIENTE_BLOQUEADO_AGRAVADO2.equals(codigoError) || ERROR_IP_BLOQUEADA.equals(codigoError)) {
			estadisticaManager.add(EstadisticasConstants.LOGIN_PIN_BANELCO_VALIDAR_PIN, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS, CodigoMensajeConstantes.LOGIN_PIN_BANELCO_ERROR_CLIENTE_BLOQUEADO_AGRAVADO);
		} else if (ERROR_AUTENTICACION_PERMITE_REINTENTAR.equals(codigoError)) {
			estadisticaManager.add(EstadisticasConstants.LOGIN_PIN_BANELCO_VALIDAR_PIN, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.PUEDE_REINTENTAR, CodigoMensajeConstantes.LOGIN_PIN_BANELCO_ERROR_AUTENTICACION_PERMITE_REINTENTAR);
		} else {
			estadisticaManager.add(EstadisticasConstants.LOGIN_PIN_BANELCO_VALIDAR_PIN, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
		
		return respuesta;
	}
	
}
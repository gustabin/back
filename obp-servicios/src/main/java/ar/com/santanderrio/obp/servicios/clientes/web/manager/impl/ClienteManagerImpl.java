/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.web.manager.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaCodOperacionEnum;
import ar.com.santanderrio.obp.servicios.clave.online.util.ClaveOnlineUtils;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.bo.CredencialesBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteEstadoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoCompaniaEnum;
import ar.com.santanderrio.obp.servicios.clientes.service.ClienteService;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoResponse;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioUsuarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CelularResponse;
import ar.com.santanderrio.obp.servicios.clientes.web.view.EmailResponse;
import ar.com.santanderrio.obp.servicios.clientes.web.view.MarcaAPNHResponse;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.TipoTeclado;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMyaIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.login.manager.LoginManager;
import ar.com.santanderrio.obp.servicios.login.manager.TokenManager;
import ar.com.santanderrio.obp.servicios.login.view.LoginResponseView;
import ar.com.santanderrio.obp.servicios.login.view.LoginView;
import ar.com.santanderrio.obp.servicios.perfil.bo.DatosPersonalesBO;
import ar.com.santanderrio.obp.servicios.perfil.dto.DescargarDatosPersonalesDTO;
import ar.com.santanderrio.obp.servicios.perfil.manager.CambioDomicilioManager;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.DatosDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.DatosPersonalesPDF;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericRequestData;
import ar.com.santanderrio.obp.servicios.segmento.manager.SegmentoClienteManager;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class ClienteManagerImpl.
 *
 * @author Jonatan_Bocian
 */

@Component("clienteManager")
public class ClienteManagerImpl implements ClienteManager {

    /** The Constant POSICION_CELULAR_SECUNDARIO. */
    private static final String POSICION_CELULAR_SECUNDARIO = "2";

    /** The Constant POSICION_CELULAR_PRIMARIO. */
    private static final String POSICION_CELULAR_PRIMARIO = "1";

    /** The Constant TITULO_CELULAR_PRIMARIO. */
    private static final String TITULO_CELULAR_PRIMARIO = "Celular Primario";

    /** The Constant TITULO_CELULAR_SECUNDARIO. */
    private static final String TITULO_CELULAR_SECUNDARIO = "Celular Secundario";

    /** The Constant TITULO_EMAIL_DOS_OPCIONAL. */
    private static final String TITULO_EMAIL_DOS_OPCIONAL = "E-mail 2";

    /** The Constant TITULO_EMAIL_UNO. */
    private static final String TITULO_EMAIL_UNO = "E-mail 1";

	/** The Constant HEADER_IS_WEBVIEW. */
	private static final String HEADER_IS_WEBVIEW = "isWebview";

	/** The Constant TRUE. */
	private static final String TRUE = "true";

    /** The companias celular. */
    @Value("#{'${PERFIL.COMPANIAS.CELULAR.CODIGOS}'.split(',')}")
    private List<String> companiasCelular;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteManagerImpl.class);

    /** The valor desafio perfil cambio clave usr. */
    @Value("${TRJCOORD.OPERAINDISTINTO.CAMBIOUSUARIO}")
    private Integer valorDesafioPerfilCambioClaveUsr;

    /** The valor desafio perfil cambio email. */
    @Value("${TRJCOORD.OPERAINDISTINTO.CAMBIOEMAIL}")
    private Integer valorDesafioPerfilCambioEmail;

    /** The valor desafio perfil cambio celular. */
    @Value("${TRJCOORD.OPERAINDISTINTO.CAMBIOCELULAR}")
    private Integer valorDesafioPerfilCambioCelular;
    
    /** The valor desafio perfil cambio email - cliente monoproducto. */
    @Value("${TRJCOORD.OPERAINDISTINTO.CAMBIOEMAIL.CLIENTE.MONOPRODUCTO}")
    private Integer valorDesafioPerfilCambioEmailMonoproducto;

    /** The valor desafio perfil cambio celular - cliente monoproducto. */
    @Value("${TRJCOORD.OPERAINDISTINTO.CAMBIOCELULAR.CLIENTE.MONOPRODUCTO}")
    private Integer valorDesafioPerfilCambioCelularMonoproducto;

    /** Token Manager *. */
    @Autowired
    private TokenManager tokenManager;

    /** The cliente service. */
    @Autowired
    private ClienteService clienteService;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The segmento Cliente Manager. */
    @Autowired
    private SegmentoClienteManager segmentoClienteManager;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The credenciales BO. */
    @Autowired
    private CredencialesBO credencialesBO;

    /** The mensaje manager. */
    @Autowired
    private MensajeManager mensajeManager;

    /** The login manager. */
    @Autowired
    private LoginManager loginManager;

    /** The desafio operacion RSA. */
    @Autowired
    private DesafioOperacionRSA<CambioUsuarioView> desafioOperacionRSA;

    /** The desafio operacion RSA email celular. */
    @Autowired
    private DesafioOperacionRSA<CambioDatosContactoView> desafioOperacionRSAEmailCelular;

    /** The mya BO. */
    @Autowired
    private MyaBO myaBO;

    /** The cambio domicilio manager. */
    @Autowired
    private CambioDomicilioManager cambioDomicilioManager;
    
    @Autowired
    private ClienteBO clienteBO;

    @Autowired
    private DatosPersonalesBO datosPersonalesBO;

    private static final String DOMICILIO_PRINCIPAL = "PRI";

    private static final String DOMICILIO_LABORAL = "LAB";

    private static final String COMA_STRING = ",";

    private static final String PUNTO_STRING = ".";

    private static final String CODIGO_POSTAL = "CP";

    @Value("${RESPONSABLE.NOMBRE}")
    private String responsableDatosNombre;

    @Value("${RESPONSABLE.CUIT}")
    private String responsableDatosCuit;

    @Value("${RESPONSABLE.DOMICILIO}")
    private String responsableDatosDomicilio;

    @Autowired
    private LegalBO legalBO;

    private static final String MASCARA_FECHA_COMPROBANTE = "dd/MM/yyyy - HH:mm";

    @Autowired
    private AutentificacionManager autentificacionManager;

    @Value("${TRJCOORD.OPERAINDISTINTO.DESCARGADATOSPERSONALES}")
    private String valorDesafioDatosPersonalesPDF;

    private Respuesta<ResumenCliente> validarCredenciales(final CredencialCliente credencialCliente) {
        try {
            return clienteService.validarCredenciales(credencialCliente);
        } catch (ServiceException e) {
            return respuestaFactory.crearRespuestaError(ResumenCliente.class, e.getMessage(), null);
        }
    }
    
    
    /**
     * Validamos credenciales que vienen de la app
     * @param credencialCliente
     * @return
     */
    private Respuesta<ResumenCliente> validarCredencialesForApp(final CredencialCliente credencialCliente) {
    	try {
    		return clienteService.validarCredencialesForApp(credencialCliente);
    	} catch (ServiceException e) {
    		return respuestaFactory.crearRespuestaError(ResumenCliente.class, e.getMessage(), null);
    	}
    }

    /**
     * (non-Javadoc).
     *
     * @param resumenCliente
     *            the resumen cliente
     * @return the respuesta
     * @see ClienteManager#obtenerCliente(ar.com.santanderrio.base.CredencialClaveOnline.entities.CredencialCliente)
     */
    @Override
    public Respuesta<Cliente> obtenerCliente(ResumenCliente resumenCliente) {
        try {
            return clienteService.obtenerCliente(resumenCliente);
        } catch (ServiceException e) {
            return respuestaFactory.crearRespuestaError(Cliente.class, e.getMessage(), null);
        }
    }

    /**
     * Gets the cliente service.
     *
     * @return the clienteService
     */
    public ClienteService getClienteService() {
        return clienteService;
    }

    /**
     * Setter para cliente service.
     *
     * @param clienteService
     *            the clienteService to set
     */
    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public Respuesta<LoginResponseView> login(CredencialCliente credencialCliente,
            RsaGenericRequestData rsaGenericRequestData, LoginView loginView, HttpServletRequest request, HttpServletResponse response) {
    	credencialCliente.setIngresoDesdeCrearUsuario(loginView.getIngresoCrearUsuario());
    	Respuesta<ResumenCliente> respuestaClienteValidado = validarCredenciales(credencialCliente);

        if (EstadoRespuesta.ERROR.equals(respuestaClienteValidado.getEstadoRespuesta())) {
            Respuesta<LoginResponseView> respuesta = respuestaFactory.crearRespuestaError(null, respuestaClienteValidado.getItemsMensajeRespuesta());
            guardarRegistroSesion(respuestaClienteValidado.getRespuesta(), credencialCliente, loginView, request);
            if (!evaluarTipoErrores(respuesta)) {
                grabarEstadisticaErrorCredenciales(respuestaClienteValidado);
            }
            return respuesta;
        } else {
            return realizarControlSesionObtenerClienteConSaldo(respuestaClienteValidado.getRespuesta(),
                    credencialCliente, loginView, request, response);
        }
    }
    
    private Respuesta<LoginResponseView> loginTokenApp(CredencialCliente credencialCliente,
    		RsaGenericRequestData rsaGenericRequestData, LoginView loginView, HttpServletRequest request, HttpServletResponse response) {
    	Respuesta<ResumenCliente> respuestaClienteValidado = validarCredencialesForApp(credencialCliente);
    	
    	if (EstadoRespuesta.ERROR.equals(respuestaClienteValidado.getEstadoRespuesta())) {
    		Respuesta<LoginResponseView> respuesta = respuestaFactory.crearRespuestaError(null, respuestaClienteValidado.getItemsMensajeRespuesta());
    		guardarRegistroSesion(respuestaClienteValidado.getRespuesta(), credencialCliente, loginView, request);
    		if (!evaluarTipoErrores(respuesta)) {
    			grabarEstadisticaErrorCredenciales(respuestaClienteValidado);
    		}
    		return respuesta;
    	} else {
    		return realizarControlSesionObtenerClienteConSaldo(respuestaClienteValidado.getRespuesta(),
    				credencialCliente, loginView, request, response);
    	}
    }

    @Override
    public Respuesta<LoginResponseView> realizarControlSesionObtenerClienteConSaldo(ResumenCliente resumenCliente,
            CredencialCliente credencialCliente, LoginView loginView, HttpServletRequest request, HttpServletResponse response) {

        Boolean sinErrores = true;
        if (loginView != null) {

            guardarRegistroSesion(resumenCliente, credencialCliente, loginView, request);
        }

        // Controla la session
        Respuesta<LoginResponseView> respuesta = tokenManager.verificarRespuestaToken(resumenCliente, request, response);
        if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
            respuesta = respuestaFactory.crearRespuestaError(null, respuesta.getItemsMensajeRespuesta());
            grabarEstadisticaControlSesion(respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
            sinErrores = false;

        } else {

            Respuesta<Cliente> respuestaClienteIdentificado = obtenerCliente(resumenCliente);

            if (EstadoRespuesta.ERROR.equals(respuestaClienteIdentificado.getEstadoRespuesta())) {
				String tipoError = respuestaClienteIdentificado.getItemsMensajeRespuesta().get(0).getTipoError();
				if (TipoError.LOGIN_ERROR_TOTAL.getDescripcion().equals(tipoError)) {
			        sesionCliente.setCliente(new Cliente(resumenCliente));
					loginManager.logout(request);
				}
                respuesta = respuestaFactory.crearRespuestaError(null,
                        respuestaClienteIdentificado.getItemsMensajeRespuesta());
                estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_LOGIN,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                sinErrores = false;
            }

            if (!EstadoRespuesta.ERROR.equals(respuestaClienteIdentificado.getEstadoRespuesta())) {
                sesionCliente.setItemsRespuesta(respuestaClienteIdentificado.getItemsMensajeRespuesta());
            }
            try {
                if (sinErrores) {
                    guardarClienteYSegmentoEnSesion(resumenCliente, respuestaClienteIdentificado);                   
                }
            } catch (ServiceException e) {
                LOGGER.debug(e.getMessage());
                respuesta = respuestaFactory.crearRespuestaError(LoginResponseView.class, e.getMessage(), "");
            }
        }

        return respuesta;
    }

    /**
     * contempla casos en que no se debe grabar estadisticas.
     *
     * @param respuesta
     *            the respuesta
     * @return true, if successful
     */
    private boolean evaluarTipoErrores(Respuesta<LoginResponseView> respuesta) {
        String tipoError = respuesta.getItemsMensajeRespuesta().get(0).getTipoError();
        if (TipoError.LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS.getDescripcion().equals(tipoError)) {
            sesionParametros.getRegistroSession()
                    .setIsAlta(Boolean.valueOf(respuesta.getItemsMensajeRespuesta().get(0).getTag()));
        }
        return TipoError.LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS.getDescripcion().equals(tipoError);
    }

    /**
     * Grabar estadistica control sesion.
     *
     * @param tipoError
     *            the tipo error
     */
    private void grabarEstadisticaControlSesion(String tipoError) {

        if (TipoError.LOGIN_ERROR_SESSION_CONCURRENTE.getDescripcion().equals(tipoError)) {

            grabarEstadisticaError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_SESION_CONCURRENTE);
        }
        if (TipoError.LOGIN_ERROR_CONTROL_SESSION.getDescripcion().equals(tipoError)) {

            grabarEstadisticaError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }

    }

    /**
     * Grabar estadistica error credenciales.
     *
     * @param respuestaClienteValidado
     *            the respuesta cliente validado
     */
    private void grabarEstadisticaErrorCredenciales(Respuesta<ResumenCliente> respuestaClienteValidado) {

        ItemMensajeRespuesta item = respuestaClienteValidado.getItemsMensajeRespuesta().get(0);

        String codigoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;

        if (item.getTipoError().equals(TipoError.LOGIN_DATOS_INCORRECTOS.getDescripcion())) {
            codigoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
        }
        if (item.getTipoError().equals(TipoError.LOGIN_DOCUMENTO_INVALIDO.getDescripcion())) {
            codigoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING;
        }
        if (item.getTipoError().equals(TipoError.LOGIN_CLAVE_BLOQUEADA.getDescripcion())) {
            codigoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_USUARIO_BLOQUEADO;
        }
        if (item.getTipoError().equals(TipoError.LOGIN_DOCUMENTO_HOMONIMO.getDescripcion())) {
            codigoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_DOCUMENTO_HOMONIMO;
        }
        if (item.getTipoError().equals(TipoError.LOGIN_ERROR_TOTAL.getDescripcion())) {
            codigoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
        }
        grabarEstadisticaError(codigoEstadistica);
    }

    /**
     * Grabar estadistica error.
     *
     * @param codigoEstadisticasError
     *            the codigo estadisticas error
     */
    private void grabarEstadisticaError(String codigoEstadisticasError) {
        estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_LOGIN, codigoEstadisticasError);
    }

    /**
     * Obtener respuesta cliente.
     *
     * @param resumenCliente
     *            the resumen cliente
     * @param respuestaClienteIdentificado
     *            the respuesta cliente identificado
     * @return the respuesta
     * @throws ServiceException
     *             the service exception
     */
    private void guardarClienteYSegmentoEnSesion(ResumenCliente resumenCliente,
            Respuesta<Cliente> respuestaClienteIdentificado) throws ServiceException {
        Cliente cliente = respuestaClienteIdentificado.getRespuesta();
        Segmento segmento = obtenerSegmento(cliente);
        segmento.setiU(verificarEsIU(cliente.getFechaNacimiento(), cliente.getCuentas()));
        cliente.setSegmento(segmento);
        sesionCliente.setCliente(cliente);
        sesionCliente.setResumenCliente(resumenCliente);
    }

    private Segmento obtenerSegmento(Cliente cliente) {
        Respuesta<Segmento> respuestaSegmento = segmentoClienteManager.obtenerSegmento(cliente);
        return respuestaSegmento.getRespuesta();

    }

    /**
	 * Guarda los datos que son comunes para la session a lo largo de toda la
	 * invocacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param credencialCliente
	 *            the credencial cliente
	 * @param loginView
	 *            the login view
	 * @param request
	 *            the request
	 */
    private void guardarRegistroSesion(ResumenCliente cliente, CredencialCliente credencialCliente, LoginView loginView,
            HttpServletRequest request) {
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setLogFile((String) request.getSession(Boolean.FALSE).getAttribute("logFileName"));
        registroSesion.setPid((String) request.getSession(Boolean.FALSE).getAttribute("pid"));
        registroSesion.setIp(credencialCliente.getIp());
        registroSesion.setPrefijo(loginView.getPrefijo());
        registroSesion.setSufijo(loginView.getSufijo());
        if (cliente != null) {
            if (cliente.getValorSinonimo() != null) {
                registroSesion.setSinonimo(cliente.getValorSinonimo());
            } else {
                registroSesion.setSinonimo(cliente.getFechaNacimiento());
            }
            registroSesion.setNup(cliente.getNup());
            registroSesion.setFechaNacimiento(cliente.getFechaNacimiento());
            registroSesion.setDni(cliente.getDni());
            registroSesion.setDniEncriptado(loginView.getDni());

        } else {
            registroSesion.setSinonimo(obtenerFechaParaEstadistica(credencialCliente.getFechaNacimiento()));
            registroSesion.setDni(credencialCliente.getDniOri());
            registroSesion.setDniEncriptado(loginView.getDni());
        }
        if (loginView.getTipoTeclado() != null && !"".equals(loginView.getTipoTeclado())) {
            registroSesion.setTipoTeclado(TipoTeclado.getTipoTecladoFromDescripcion(loginView.getTipoTeclado()));
            registroSesion.setClave(loginView.getClave());
            registroSesion.setClaveNueva(loginView.getClaveVacia());
            registroSesion.setUsuario(loginView.getUsuario());
            registroSesion.setUsuarioNuevo(loginView.getUsuarioVacio());

        }
        if (StringUtils.isNotBlank(loginView.getFechaDeNacimiento())) {
            registroSesion.setClienteSinonimo(Boolean.TRUE);
        } else {
            registroSesion.setClienteSinonimo(Boolean.FALSE);
        }

        registroSesion.setHostName(credencialCliente.getHostName());
        registroSesion.setDispositivo(credencialCliente.getDispositivo());
        registroSesion.setNavegador(credencialCliente.getNavegador());
        sesionParametros.setRegistroSession(registroSesion);

    }

    /**
     * Obtener fecha para estadistica.
     *
     * @param fechaNacimiento
     *            the fecha nacimiento
     * @return the string
     */
    private String obtenerFechaParaEstadistica(Date fechaNacimiento) {
        if (fechaNacimiento == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String fecha = df.format(fechaNacimiento);
        return fecha;
    }

    @Override
    public ResumenCliente obtenerClienteSesion() {
        if (sesionCliente == null) {
            return null;
        }
        return sesionCliente.getResumenCliente();
    }

    @Override
    public SesionParametros obtenerSesionParametros() {
        return sesionParametros;
    }

    /**
	 * Hace la validacion custom.
	 *
	 * @param authentication
	 *            the authentication
	 * @param loginView
	 *            the login view
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the respuesta
	 */
    public Respuesta<LoginResponseView> login(Authentication authentication, LoginView loginView,
            HttpServletRequest request, HttpServletResponse response) {
        CredencialCliente credenciales = (CredencialCliente) authentication.getCredentials();
        RsaGenericRequestData rsaGenericRequestData = (RsaGenericRequestData) authentication.getPrincipal();
        Respuesta<LoginResponseView> respuesta = login(credenciales, rsaGenericRequestData, loginView, request, response);
        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    credenciales.getDniOri(), credenciales, new ArrayList<GrantedAuthority>()));
        }
        return respuesta;
    }

    @Override
    public Respuesta<LoginResponseView> loginTokenApp(Authentication authentication, LoginView loginView,
    		HttpServletRequest request, HttpServletResponse response) {
    	CredencialCliente credenciales = (CredencialCliente) authentication.getCredentials();
    	RsaGenericRequestData rsaGenericRequestData = (RsaGenericRequestData) authentication.getPrincipal();
    	Respuesta<LoginResponseView> respuesta = loginTokenApp(credenciales, rsaGenericRequestData, loginView, request, response);
    	if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
    		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
    				credenciales.getDniOri(), credenciales, new ArrayList<GrantedAuthority>()));
    	}
    	return respuesta;
    }

    @Override
    public Respuesta<FeedbackMensajeView> loginConfirmarUsuario(CredencialCliente credencialCliente, HttpServletRequest request) {

        Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
        FeedbackMensajeView feedbackMensajeView = new FeedbackMensajeView();
        Respuesta<ResumenCliente> respuestaClienteValidado = credencialesBO.loginConfirmarUsuario(credencialCliente);

        String codEstadistica = ClaveOnlineUtils.getEstadistica(request, EstadisticasConstants.LOGIN_CLAVE_USUARIO_EXPIRADOS, 
        		EstadisticasConstants.LOGIN_CLAVE_USUARIO_EXPIRADOS_WEBVIEW);
        if (EstadoRespuesta.ERROR.equals(respuestaClienteValidado.getEstadoRespuesta())) {
            respuesta = respuestaFactory.crearRespuestaError(null, respuestaClienteValidado.getItemsMensajeRespuesta());
            estadisticaManager.add(codEstadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuesta;
        }

        sesionCliente.setResumenCliente(respuestaClienteValidado.getRespuesta());
        actualizarRegistroSession(respuestaClienteValidado.getRespuesta());
        String codigoMensaje = CodigoMensajeConstantes.LOGIN_CUE_FEEDBACK_OK;
        if (request != null && TRUE.equals(request.getHeader(HEADER_IS_WEBVIEW))) {
			codigoMensaje = CodigoMensajeConstantes.LOGIN_CUE_FEEDBACK_OK_WEBVIEW;
		}
        Mensaje mensaje = mensajeManager.obtenerMensajePorCodigo(codigoMensaje);
        feedbackMensajeView.setMensaje(mensaje.getMensaje());
        respuesta.setRespuesta(feedbackMensajeView);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        try {
        	Respuesta<Boolean> resultado = clienteBO.actualizarUltimaFechaCambioClave(Long.valueOf(sesionCliente.getResumenCliente().getNup()));
        	if(EstadoRespuesta.OK.equals(resultado.getEstadoRespuesta())) {
        		estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
        				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        	} else {
        		estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
    	                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        	}
		} catch (NumberFormatException e) {
			estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
	                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} catch (BusinessException e) {
			estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
	                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
        
        estadisticaManager.add(codEstadistica,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        return respuesta;
    }

    /**
     * Actualizo el registro en session si la fecha esta vacia con lo que obtengo
     * del seginform ya que luego se puede utilizar para hacer un cambio de usuario
     * y o password desde el perfil y el cmbuser requiere la fecha como obligatoria.
     * 
     * @param respuesta
     */
    private void actualizarRegistroSession(ResumenCliente respuesta) {
        RegistroSesion registroSesion = sesionParametros.getRegistroSession();
        if (registroSesion != null && StringUtils.isEmpty(registroSesion.getFechaNacimiento())) {
            registroSesion.setFechaNacimiento(respuesta.getFechaNacimiento());
        }
    }

    @Override
    public Respuesta<FeedbackMensajeView> loginDefinirUsuario(CredencialCliente credencialCliente) {

        Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
        FeedbackMensajeView feedbackMensajeView = new FeedbackMensajeView();
        Respuesta<ResumenCliente> respuestaClienteValidado = credencialesBO.loginDefinirUsuario(credencialCliente);

        if (EstadoRespuesta.ERROR.equals(respuestaClienteValidado.getEstadoRespuesta())) {
            respuesta = respuestaFactory.crearRespuestaError(null, respuestaClienteValidado.getItemsMensajeRespuesta());
            estadisticaManager.add(EstadisticasConstants.LOGIN_USUARIO_NO_DEFINIDO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuesta;
        }

        sesionCliente.setResumenCliente(respuestaClienteValidado.getRespuesta());

        Mensaje mensaje = mensajeManager
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.LOGIN_DEFINIR_USUARIO_FEEDBACK_OK);
        feedbackMensajeView.setMensaje(mensaje.getMensaje());
        respuesta.setRespuesta(feedbackMensajeView);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        try {
            Respuesta<Boolean> resultado = clienteBO.actualizarUltimaFechaCambioClave(Long.valueOf(respuestaClienteValidado.getRespuesta().getNup()));
            if(EstadoRespuesta.OK.equals(resultado.getEstadoRespuesta())) {
                estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            } else {
                estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
        } catch (NumberFormatException e) {
            estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        } catch (BusinessException e) {
            estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }

        estadisticaManager.add(EstadisticasConstants.LOGIN_USUARIO_NO_DEFINIDO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager#
     * cambioUsuarioPendiente(ar.com.santanderrio.obp.base.clientes.entities.
     * CredencialCliente)
     */
    @Override
    public Respuesta<FeedbackMensajeView> cambioUsuarioPendiente(CredencialCliente credencialCliente) {

        Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
        FeedbackMensajeView feedbackMensajeView = new FeedbackMensajeView();
        Respuesta<ResumenCliente> respuestaClienteValidado = credencialesBO.cambioUsuarioPendiente(credencialCliente);

        if (EstadoRespuesta.ERROR.equals(respuestaClienteValidado.getEstadoRespuesta())) {
            respuesta = respuestaFactory.crearRespuestaError(null, respuestaClienteValidado.getItemsMensajeRespuesta());
            estadisticaManager.add(EstadisticasConstants.LOGIN_CAMBIO_PENDIENTE_USUARIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuesta;
        }

        sesionCliente.setResumenCliente(respuestaClienteValidado.getRespuesta());

        Mensaje mensaje = mensajeManager
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.LOGIN_CAMBIO_PENDIENTE_FEEDBACK_OK);
        feedbackMensajeView.setMensaje(mensaje.getMensaje());
        respuesta.setRespuesta(feedbackMensajeView);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        try {
        	Respuesta<Boolean> resultado = clienteBO.actualizarUltimaFechaCambioClave(Long.valueOf(respuestaClienteValidado.getRespuesta().getNup()));
        	if(EstadoRespuesta.OK.equals(resultado.getEstadoRespuesta())) {
            	estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
    	                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        	} else {
        		estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
    	                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        	}
		} catch (NumberFormatException e) {
			estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
	                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} catch (BusinessException e) {
			estadisticaManager.add(EstadisticasConstants.CLIENTE_CONTROL_SEGURIDAD_ACTUALIZACION,
	                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
        
        estadisticaManager.add(EstadisticasConstants.LOGIN_CAMBIO_PENDIENTE_USUARIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager#
     * cambioUsuario(javax.servlet.http.HttpServletRequest,
     * ar.com.santanderrio.obp.servicios.clientes.web.view.CambioUsuarioView)
     */
    @Override
    public Respuesta<CambioUsuarioView> cambioUsuario(HttpServletRequest request, CambioUsuarioView cambioUsuarioView) {
        if (sesionParametros.getContador() == null) {
            sesionParametros.setContador(new ContadorIntentos(2));
        }

        estadisticaManager.add(EstadisticasConstants.ESTADISTICAS_CAMBIO_USUARIO_ANTES_RSA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        LOGGER.info("Gestionar desafio");
        AutentificacionCodEstDTO autentificacionCodEstDTO = asignarEstadisticasDeAutenticacion();
        Respuesta<CambioUsuarioView> respuestaFinal = desafioOperacionRSA.validarOperacionRSA(
                cambioUsuarioView, valorDesafioPerfilCambioClaveUsr, autentificacionCodEstDTO);

        if (!EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())) {
            return respuestaFinal;
        }

        LOGGER.info("LLamada al Seginform");
        CambioUsuarioEntity entity = crearCambioUsuarioEntity(cambioUsuarioView);

        Respuesta<ResumenCliente> respuestaResumenCliente = credencialesBO.cambioUsuario(request, entity, true);

        respuestaFinal = Respuesta.copy(CambioUsuarioView.class, respuestaResumenCliente);

        if (EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())) {
            CambioUsuarioView respCambioUsuarioView = new CambioUsuarioView();
            sesionParametros.setNroComprobante(respuestaResumenCliente.getRespuesta().getNroComprobante());
            respCambioUsuarioView.setMensajeFeedback(mensajeManager
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.CAMBIO_USUARIO_EXITOSO).getMensaje());
            respuestaFinal.setRespuesta(respCambioUsuarioView);
            
            estadisticaManager.add(EstadisticasConstants.ESTADISTICAS_CAMBIO_USUARIO_DPS_RSA,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            
        } else if (EstadoRespuesta.WARNING.equals(respuestaFinal.getEstadoRespuesta())) {
            return respuestaFinal;
        } else if (EstadoRespuesta.ERROR.equals(respuestaFinal.getEstadoRespuesta()) && TipoError.ERROR_GENERICO
                .getDescripcion().equals(respuestaFinal.getItemsMensajeRespuesta().get(0).getTipoError())) {
            return respuestaFinal;
        } else {
            if (!sesionParametros.getContador().permiteReintentar()) {
                sesionParametros.setContador(null);
                respuestaFinal = respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
                        TipoError.ERROR_REINTENTOS_AGOTADOS,
                        respuestaFinal.getItemsMensajeRespuesta().get(0).getMensaje());
            }
            respuestaFinal = respuestaFactory.crearRespuestaError(CambioUsuarioView.class,
                    respuestaFinal.getItemsMensajeRespuesta());
            respuestaFinal.getItemsMensajeRespuesta().get(0).setTipoError("ERROR_CON_REINTENTOS");
            estadisticaManager.add(EstadisticasConstants.ESTADISTICAS_CAMBIO_USUARIO_DPS_RSA,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

        }

        return respuestaFinal;
    }

    /**
     * Crear cambio usuario entity.
     *
     * @param cambioUsuarioView
     *            the cambio usuario view
     * @return the cambio usuario entity
     */
    private CambioUsuarioEntity crearCambioUsuarioEntity(CambioUsuarioView cambioUsuarioView) {
        CambioUsuarioEntity entity = new CambioUsuarioEntity();

        entity.setCliente(sesionCliente.getCliente());
        entity.setStrNewUsr(cambioUsuarioView.getStrNewUsr());
        entity.setStrOldUsr(cambioUsuarioView.getStrOldUsr());
        entity.setStrNewPin(cambioUsuarioView.getStrNewPin());
        entity.setStrOldPin(cambioUsuarioView.getStrOldPin());

        return entity;
    }

    /**
     * Asignar estadisticas de autenticacion.
     * 
     * @return autentificacionCodEstDTO
     */
    private AutentificacionCodEstDTO asignarEstadisticasDeAutenticacion() {
        AutentificacionCodEstDTO autentificacionCodEstDTO = new AutentificacionCodEstDTO();
        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CAMBIO_USUARIO);
        autentificacionCodEstDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CAMBIO_USUARIO);
        autentificacionCodEstDTO
                .setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CAMBIO_USUARIO);
        autentificacionCodEstDTO
                .setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CAMBIO_USUARIO);
        return autentificacionCodEstDTO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager#
     * getMarcaAPNH()
     */
    public Respuesta<MarcaAPNHResponse> getMarcaAPNH() {
        Respuesta<MarcaAPNHResponse> respuesta = new Respuesta<MarcaAPNHResponse>();
        MarcaAPNHResponse apnhResponse = new MarcaAPNHResponse();
        apnhResponse.setMarcaAPNH(sesionCliente.getCliente().getMarcaANPH());
        apnhResponse.setDni(ISBANStringUtils.eliminarCeros(sesionCliente.getCliente().getDni()));// FIX
                                                                                                 // 20180322
                                                                                                 // -
                                                                                                 // Claves
        estadisticaManager.add(EstadisticasConstants.ESTADISTICAS_CAMBIO_USUARIO_ANTES_RSA,
        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
 		apnhResponse.setAvisoCambioClave(mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_AVISO_CAMBIO_CLAVE).getMensaje());
        apnhResponse.setAvisoCambioUsuario(mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_AVISO_CAMBIO_USUARIO).getMensaje());
        


        respuesta.setRespuesta(apnhResponse);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        return respuesta;
    }

    /**
     * * Funcionalidad para la tarjeta de Cambio Datos de ccontacto *.
     *
     * @param cambioDatosContacto
     *            the cambio datos contacto
     * @param isMail
     *            the is mail
     * @return the respuesta
     */

    @Override
    public Respuesta<CambioDatosContactoView> cambioDatosContacto(CambioDatosContactoView cambioDatosContacto,
            boolean isMail) {
        Respuesta<Void> respuesta;
        
        cambioDatosContacto.setTipoOperacion(isMail ? OperacionesRSAEnum.CAMBIOEMAIL : OperacionesRSAEnum.CAMBIOCELULAR);
        MyaDTOOut out = sesionParametros.getMyaDTOOut();
        
        // VIENE DESDE AVISO SANTANDER
        if (out == null) {
        	out = mapearOutConCredencialesMya();
        	sesionParametros.setMyaDTOOut(out);
        }
        
        LOGGER.info("Cargamos numero de telefono o mail, segun corresponda, para agregar en las reglas de RSA");
        cargarDatosParaRSA(cambioDatosContacto, out);
        
        AutentificacionCodEstDTO autentificacionCodEstDTO = asignarEstadisticasDeAutenticacion(isMail);
        Respuesta<CambioDatosContactoView> respuestaFinal = respuestaFactory.crearRespuestaOk(null);
        
        if(sesionCliente.getCliente().esMonoProductoTarjeta()) {
        	respuestaFinal = desafioOperacionRSAEmailCelular.validarOperacionRSA(
        			cambioDatosContacto, isMail ? valorDesafioPerfilCambioEmailMonoproducto : valorDesafioPerfilCambioCelularMonoproducto, autentificacionCodEstDTO);
        } else {        	
        	respuestaFinal = desafioOperacionRSAEmailCelular.validarOperacionRSA(
        			cambioDatosContacto, isMail ? valorDesafioPerfilCambioEmail : valorDesafioPerfilCambioCelular, autentificacionCodEstDTO);
        }
        
        if (respuestaFinal == null || !EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())){
			if(EstadoRespuesta.WARNING.equals(respuestaFinal.getEstadoRespuesta()) && sesionCliente.getCliente().esMonoProductoTarjeta()) {
				respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
			} else {
				return respuestaFinal;
			}
		}

        Cliente cliente = sesionCliente.getCliente();
        

        if (sesionParametros.getContador() == null) {
            sesionParametros.setContador(new ContadorIntentos(2));
        }

        if (verificarNoClienteRegistrado(out)) {
            CredencialesMyaIn datos = buildAltaCredencialesMya(cambioDatosContacto);
            respuesta = myaBO.registrarClienteMya(cliente, datos);
        } else {
            CredencialesMyaIn datos = buildModificacionCredencialesMya(cambioDatosContacto, out, isMail);
            respuesta = myaBO.updateDestinos(datos, cliente);
        }
        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())){
        	respuestaFinal = Respuesta.copy(CambioDatosContactoView.class, respuesta);
        	CambioDatosContactoView response = new CambioDatosContactoView();
            response.setMensajeFeedback(StringUtils.EMPTY);
            respuestaFinal.setRespuesta(response);
            if (isMail) {
                estadisticaManager.add(EstadisticasConstants.ESTADISTICAS_CAMBIO_EMAIL,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            } else {
                estadisticaManager.add(EstadisticasConstants.ESTADISTICAS_CAMBIO_DE_CELULAR,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            }
            actualizarDatosContactoEnSesion(cambioDatosContacto, isMail);
            return respuestaFactory.crearRespuestaOk(CambioDatosContactoView.class, respuestaFinal.getRespuesta());
        }


        if ((EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta()) && (TipoError.MYA_MAIL_YA_REGISTRADO
                        .getDescripcion().equals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError()))
                || TipoError.MYA_CELULAR_YA_REGISTRADO.getDescripcion()
                                .equals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError()))) {
            if (isMail) {
                estadisticaManager.add(EstadisticasConstants.ESTADISTICAS_CAMBIO_EMAIL,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                respuestaFinal = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_CON_REINTENTOS,
    					CodigoMensajeConstantes.MYA_FEEDBACK_MAIL_YA_REGISTRADO );
            } else {
                estadisticaManager.add(EstadisticasConstants.ESTADISTICAS_CAMBIO_DE_CELULAR,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                respuestaFinal = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_CON_REINTENTOS,
    					CodigoMensajeConstantes.MYA_FEEDBACK_CELULAR_YA_REGISTRADO );
            }
        } 
        if ((EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta()) && !(TipoError.MYA_MAIL_YA_REGISTRADO
                .getDescripcion().equals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError()))
        && !TipoError.MYA_CELULAR_YA_REGISTRADO.getDescripcion()
                        .equals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError()))) {
            if (isMail) {
                estadisticaManager.add(EstadisticasConstants.ESTADISTICAS_CAMBIO_EMAIL,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            } else {
                estadisticaManager.add(EstadisticasConstants.ESTADISTICAS_CAMBIO_DE_CELULAR,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }                     
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_CMBDOMYTEL);
            
        }
        if (EstadoRespuesta.ERROR.equals(respuestaFinal.getEstadoRespuesta()) && !sesionParametros.getContador().permiteReintentar()) {
            sesionParametros.setContador(null);
            String codigoMensajeError=CodigoMensajeConstantes.MYA_FEEDBACK_CELULAR_YA_REGISTRADO;
            if (isMail) {
            	codigoMensajeError=CodigoMensajeConstantes.MYA_FEEDBACK_MAIL_YA_REGISTRADO;
            }         
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_REINTENTOS_AGOTADOS,
            		codigoMensajeError);
        }       
        return respuestaFinal;
    }
    
    
    private void cargarDatosParaRSA(CambioDatosContactoView cambioDatosContacto, MyaDTOOut out) {
    	if (OperacionesRSAEnum.CAMBIOCELULAR.equals(cambioDatosContacto.getTipoOperacion())) {
    		if ("1".equals(cambioDatosContacto.getPosicionCelular())) {
    			cambioDatosContacto.setCelularPrevio(StringUtils.isEmpty(out.getCelular()) ? StringUtils.EMPTY : out.getCelular());
    		} else {
    			cambioDatosContacto.setCelularPrevio(StringUtils.isEmpty(out.getCelularSecundario()) ? StringUtils.EMPTY : out.getCelularSecundario());
    		}
    	} else { 
    		if (StringUtils.isNotBlank(cambioDatosContacto.getEmailPrimario())){
    			cambioDatosContacto.setEmailPrimarioPrevio(StringUtils.isEmpty(out.getEmail()) ? StringUtils.EMPTY : out.getEmail());
    		}
    		if (StringUtils.isNotBlank(cambioDatosContacto.getEmailSecundario())) {
    			cambioDatosContacto.setEmailSecundarioPrevio(StringUtils.isEmpty(out.getEmailSecundario()) ? StringUtils.EMPTY : out.getEmailSecundario());
    		}
    	}
    }


    /**
     * Asignar estadisticas de autenticacion.
     *
     * @param isMail the is mail
     * @return the autentificacion cod est DTO
     */
    private AutentificacionCodEstDTO asignarEstadisticasDeAutenticacion(boolean isMail) {
		AutentificacionCodEstDTO autentificacionCodEstDTO = new AutentificacionCodEstDTO();
		autentificacionCodEstDTO
				.setCodigoEstadisticaSolicitudToken(isMail ? EstadisticasConstants.SOFT_TOKEN_VALIDACION_CAMBIO_EMAIL
						: EstadisticasConstants.SOFT_TOKEN_VALIDACION_CAMBIO_CELULAR);
		autentificacionCodEstDTO
				.setCodigoEstadisticaValidacionToken(isMail ? EstadisticasConstants.SOFT_TOKEN_VALIDACION_CAMBIO_EMAIL
						: EstadisticasConstants.SOFT_TOKEN_VALIDACION_CAMBIO_CELULAR);
		autentificacionCodEstDTO.setCodigoEstadisticaSolicitudCoordenadas(
				isMail ? EstadisticasConstants.COORDENADAS_SOLICITUD_CAMBIO_EMAIL
						: EstadisticasConstants.COORDENADAS_SOLICITUD_CAMBIO_CELULAR);
		autentificacionCodEstDTO.setCodigoEstadisticaValidacionCoordenadas(
				isMail ? EstadisticasConstants.COORDENADAS_VALIDACION_CAMBIO_EMAIL
						: EstadisticasConstants.COORDENADAS_VALIDACION_CAMBIO_CELULAR);
		return autentificacionCodEstDTO;
	}

	private void actualizarDatosContactoEnSesion(CambioDatosContactoView cambioDatosContacto, boolean isMail) {
        CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
        if (isMail) {
            credencialesMya.setEmail(cambioDatosContacto.getEmailPrimario());
            credencialesMya.setEmailSecundario(cambioDatosContacto.getEmailSecundario());
        } else {
            if (StringUtils.equals(POSICION_CELULAR_PRIMARIO, cambioDatosContacto.getPosicionCelular())) {
                credencialesMya.setCelular(cambioDatosContacto.getCelularIn());
                credencialesMya.setCodigoArea(cambioDatosContacto.getCodigoArea());
                credencialesMya.setCompaniaSeleccionada(cambioDatosContacto.getCompaniaCelular());
            } else {
                credencialesMya.setCelularSecundario(cambioDatosContacto.getCelularIn());
                credencialesMya.setCodigoAreaSecundario(cambioDatosContacto.getCodigoArea());
                credencialesMya.setCompaniaSeleccionadaSecundaria(cambioDatosContacto.getCompaniaCelular());
            }
        }
    }

    private MyaDTOOut mapearOutConCredencialesMya() {
        MyaDTOOut out = new MyaDTOOut();
        CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
        out.setCelular(credencialesMya.getCelular());
        out.setCelularId(credencialesMya.getCelularId());
        out.setCelularSecundario(credencialesMya.getCelularSecundario());
        out.setCelularSecundarioId(credencialesMya.getCelularSecundarioId());
        out.setClienteEstadoEnum(ClienteEstadoEnum.fromCodigoString(credencialesMya.getClienteEstado()));
        out.setEmail(credencialesMya.getEmail());
        out.setEmailId(credencialesMya.getEmailId());
        out.setEmailSecundario(credencialesMya.getEmailSecundario());
        out.setEmailSecundarioId(credencialesMya.getEmailSecundarioId());
        out.setTipoCompaniaEnum(TipoCompaniaEnum.fromCodigo(credencialesMya.getCompaniaSeleccionada()));
        out.setTipoCompaniaSecundarioEnum(
                TipoCompaniaEnum.fromCodigo(credencialesMya.getCompaniaSeleccionadaSecundaria()));
        return out;
    }

    /**
     * Builds the modificacion credenciales mya.
     *
     * @param cambioDatosContacto
     *            the cambio datos contacto
     * @param out
     *            the out
     * @param isMail
     *            the is mail
     * @return the credenciales mya in
     */
    private CredencialesMyaIn buildModificacionCredencialesMya(CambioDatosContactoView cambioDatosContacto,
            MyaDTOOut out, boolean isMail) {
        CredencialesMyaIn datos = new CredencialesMyaIn();
        datos.setSoloPrimarios(false);
        if (isMail) {
            analizarEmail(cambioDatosContacto, out, datos);
        } else {
            analizarCelular(cambioDatosContacto, out, datos);
        }
        return datos;
    }

    /**
     * Analizar celular.
     *
     * @param cambioDatosContacto
     *            the cambio datos contacto
     * @param out
     *            the out
     * @param datos
     *            the datos
     */
    private void analizarCelular(CambioDatosContactoView cambioDatosContacto, MyaDTOOut out, CredencialesMyaIn datos) {

        if (POSICION_CELULAR_PRIMARIO.equals(cambioDatosContacto.getPosicionCelular())) {
            String celularPrimario = buildCelular(cambioDatosContacto.getCodigoArea(),
                    cambioDatosContacto.getCelularIn());
            datos.setCelular(cambioDatosContacto.getCelularIn());
            datos.setCodigoArea(cambioDatosContacto.getCodigoArea());
            datos.setCompaniaSeleccionada(TipoCompaniaEnum.fromDescripcion(cambioDatosContacto.getCompaniaCelular()));

            if (StringUtils.isNotBlank(out.getCelular()) && StringUtils.isNotBlank(celularPrimario) && (!celularPrimario.equals(out.getCelular()) || 
            		(out.getTipoCompaniaEnum()!= null && !out.getTipoCompaniaEnum().equals(datos.getCompaniaSeleccionada())))) {
                datos.setTipoOperacionCelular(MyaCodOperacionEnum.MODIFICACION);
            } else if (StringUtils.isNotBlank(celularPrimario)) {
                datos.setTipoOperacionCelular(MyaCodOperacionEnum.ALTA);
            }
        }

        if (POSICION_CELULAR_SECUNDARIO.equals(cambioDatosContacto.getPosicionCelular())) {
            String celularSecundario = buildCelular(cambioDatosContacto.getCodigoArea(),
                    cambioDatosContacto.getCelularIn());
            datos.setCelularSecundario(cambioDatosContacto.getCelularIn());
            datos.setCodigoAreaSecundario(cambioDatosContacto.getCodigoArea());
            datos.setCompaniaSeleccionadaSecundaria(
                    TipoCompaniaEnum.fromDescripcion(cambioDatosContacto.getCompaniaCelular()));

            if (StringUtils.isNotBlank(out.getCelularSecundario()) && StringUtils.isNotBlank(celularSecundario)  && !celularSecundario.equals(out.getCelularSecundario()) || 
                    (out.getTipoCompaniaSecundarioEnum()!= null && !out.getTipoCompaniaSecundarioEnum().equals(datos.getCompaniaSeleccionadaSecundaria()))) {
                datos.setTipoOperacionCelularSecundaria(MyaCodOperacionEnum.MODIFICACION);
            } else if (StringUtils.isNotBlank(celularSecundario)) {
                datos.setTipoOperacionCelularSecundaria(MyaCodOperacionEnum.ALTA);
            }
        }
    }

    /**
     * Analizar email.
     *
     * @param cambioDatosContacto
     *            the cambio datos contacto
     * @param out
     *            the out
     * @param datos
     *            the datos
     */
    private void analizarEmail(CambioDatosContactoView cambioDatosContacto, MyaDTOOut out, CredencialesMyaIn datos) {
        if (StringUtils.isNotBlank(out.getEmail())) {
            if (StringUtils.isNotBlank(cambioDatosContacto.getEmailPrimario())
                    && !cambioDatosContacto.getEmailPrimario().equals(out.getEmail())) {
                datos.setEmail(cambioDatosContacto.getEmailPrimario());
                datos.setTipoOperacionEmail(MyaCodOperacionEnum.MODIFICACION);

            }
        } else if (StringUtils.isNotBlank(cambioDatosContacto.getEmailPrimario())) {
            datos.setEmail(cambioDatosContacto.getEmailPrimario());
            datos.setTipoOperacionEmail(MyaCodOperacionEnum.ALTA);
        }
        if (StringUtils.isNotBlank(out.getEmailSecundario())) {
            if (StringUtils.isNotBlank(cambioDatosContacto.getEmailSecundario())){
                if(!cambioDatosContacto.getEmailSecundario().equals(out.getEmailSecundario())){
                    datos.setEmailSecundario(cambioDatosContacto.getEmailSecundario());
                    datos.setTipoOperacionEmailSecundaria(MyaCodOperacionEnum.MODIFICACION);
                }
            } else {
                if(StringUtils.isBlank(cambioDatosContacto.getEmailSecundario())){
                    datos.setEmailSecundario(out.getEmailSecundario());
                    datos.setTipoOperacionEmailSecundaria(MyaCodOperacionEnum.BAJA);
                }
            }
        } else if (StringUtils.isNotBlank(cambioDatosContacto.getEmailSecundario())) {
            datos.setEmailSecundario(cambioDatosContacto.getEmailSecundario());
            datos.setTipoOperacionEmailSecundaria(MyaCodOperacionEnum.ALTA);
        }
    }

    /**
     * Builds the celular.
     *
     * @param codArea
     *            the cod area
     * @param celular
     *            the celular
     * @return the string
     */
    private String buildCelular(String codArea, String celular) {
        if (StringUtils.isNotBlank(codArea) && StringUtils.isNotBlank(celular)) {
            return codArea.concat("-").concat(celular);
        }
        return StringUtils.EMPTY;
    }

    /**
     * Builds the alta credenciales mya.
     *
     * @param cambioDatosContacto
     *            the cambio datos contacto
     * @return the credenciales mya in
     */
    private CredencialesMyaIn buildAltaCredencialesMya(CambioDatosContactoView cambioDatosContacto) {
        CredencialesMyaIn datos = new CredencialesMyaIn();
        if (POSICION_CELULAR_PRIMARIO.equals(cambioDatosContacto.getPosicionCelular())) {
            datos.setCodigoArea(cambioDatosContacto.getCodigoArea());
            datos.setCelular(cambioDatosContacto.getCelularIn());
            datos.setCompaniaSeleccionada(TipoCompaniaEnum.fromDescripcion(cambioDatosContacto.getCompaniaCelular()));
            datos.setTipoOperacionCelular(MyaCodOperacionEnum.ALTA);
        }

        if (POSICION_CELULAR_SECUNDARIO.equals(cambioDatosContacto.getPosicionCelular())) {
            datos.setCodigoAreaSecundario(cambioDatosContacto.getCodigoArea());
            datos.setCelularSecundario(cambioDatosContacto.getCelularIn());
            datos.setCompaniaSeleccionadaSecundaria(
                    TipoCompaniaEnum.fromDescripcion(cambioDatosContacto.getCompaniaCelular()));
            datos.setTipoOperacionCelularSecundaria(MyaCodOperacionEnum.ALTA);
        }

        datos.setEmail(cambioDatosContacto.getEmailPrimario());
        datos.setTipoOperacionEmail(MyaCodOperacionEnum.ALTA);
        datos.setEmailSecundario(cambioDatosContacto.getEmailSecundario());
        datos.setTipoOperacionEmailSecundaria(MyaCodOperacionEnum.ALTA);
        datos.setSoloPrimarios(false);
        return datos;
    }

    /**
     * Verificar no cliente registrado.
     *
     * @param out
     *            the out
     * @return true, if successful
     */
    private boolean verificarNoClienteRegistrado(MyaDTOOut out) {
        return (StringUtils.isBlank(out.getEmail()) && StringUtils.isBlank(out.getEmailId())
                && StringUtils.isBlank(out.getEmailSecundario()) && StringUtils.isBlank(out.getEmailSecundarioId())
                && StringUtils.isBlank(out.getCelular()) && StringUtils.isBlank(out.getCelularId())
                && StringUtils.isBlank(out.getCelularSecundario())
                && StringUtils.isBlank(out.getCelularSecundarioId()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager#
     * getDatosContacto()
     */
    @Override
    public Respuesta<CambioDatosContactoResponse> getDatosContacto() {
        MyaDTOIn myaDTOIn = new MyaDTOIn();
        Respuesta<CambioDatosContactoResponse> respuesta = new Respuesta<CambioDatosContactoResponse>();
        Cliente cliente = sesionCliente.getCliente();
        myaDTOIn.setSoloPrincipales(false);
        MyaDTOOut out = myaBO.consultaWsStatusCliente(cliente, myaDTOIn);
        sesionParametros.setMyaDTOOut(out);
        if (!ClienteEstadoEnum.TIMEOUT.equals(out.getClienteEstadoEnum())) {
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            CambioDatosContactoResponse cambioContactoResponse = buildCambioConctactoResponse(out);
            Respuesta<DatosDomicilioView> respuestaDomicilios = cambioDomicilioManager
                    .   obtenerDomiciliosContacto(Boolean.FALSE);
            if (respuestaDomicilios != null && respuestaDomicilios.getRespuesta() != null) {
            	List<CambioDomicilioView> domiciliosFiltrados = filtrarDomicilios(respuestaDomicilios.getRespuesta().getDomicilios());            	
                cambioContactoResponse.setListaDomicilios(domiciliosFiltrados);
                cambioContactoResponse.setProvincias(respuestaDomicilios.getRespuesta().getProvincias());
            }
            respuesta.setRespuesta(cambioContactoResponse);
            respuesta.setItemMensajeRespuesta(respuestaDomicilios.getItemsMensajeRespuesta());
            sesionParametros.setCambioDatosContactoResponse(cambioContactoResponse);
        } else {// Si hay timeout Error generico
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_SERVICIO_MYA);
            estadisticaManager.add(EstadisticasConstants.VISUALIZACION_DATOS_DE_DOMICILIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            sesionParametros.setCambioDatosContactoResponse(null);
        }
        return respuesta;
    }
    
    private List<CambioDomicilioView> filtrarDomicilios (List<CambioDomicilioView> listaDomicilios) {
    	
    	List<CambioDomicilioView> domiciliosFiltrados = new ArrayList<CambioDomicilioView>();
    	
    	for (CambioDomicilioView domicilio : listaDomicilios) {
    		if (domicilio.getDescripcionesProductos().isEmpty()) {
    			domiciliosFiltrados.add(domicilio);
    		}
    	}
    	    	
    	return domiciliosFiltrados;
    }
    
    @Override
    public Respuesta<List<BigDecimal>> obtenerAntiguedadDiasUltCambioClaveToken(Long nup) {
    	Respuesta<List<BigDecimal>> antiguedades;
		try {
			antiguedades = clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
			if(EstadoRespuesta.OK.equals(antiguedades.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				if(antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
					return antiguedades;
				}
			}
		} catch (NumberFormatException e) {
			LOGGER.info(e.getMessage());
			estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    	return null;
    }

    /**
     * Builds the cambio conctacto response.
     *
     * @param out
     *            the out
     * @return the cambio datos contacto response
     */
    private CambioDatosContactoResponse buildCambioConctactoResponse(MyaDTOOut out) {
        CambioDatosContactoResponse response = new CambioDatosContactoResponse();
        response.setEstadoCliente(out.getClienteEstadoEnum().getCodigo());
        CelularResponse celularPrimario = new CelularResponse();
        if (StringUtils.isNotBlank(out.getCelular())) {
            String porcionVisible = out.getCelular().substring(4, out.getCelular().length());
            String[] array = out.getCelular().split("-");
            celularPrimario.setCodigoArea(array[0]);
            celularPrimario.setNumero(array[1]);
            celularPrimario.setNumeroCompleto(StringUtils.leftPad(porcionVisible, 8, 'X'));
        }
        if (out.getTipoCompaniaEnum() != null) {
            celularPrimario.setEmpresa(out.getTipoCompaniaEnum().getDescripcion());
        }

        celularPrimario.setTitulo(TITULO_CELULAR_PRIMARIO);
        celularPrimario.setPosicionCelular(POSICION_CELULAR_PRIMARIO);

        CelularResponse celularSecundario = new CelularResponse();
        if (StringUtils.isNotBlank(out.getCelularSecundario())) {
            String porcionVisible = out.getCelularSecundario().substring(4, out.getCelularSecundario().length());
            String[] array = out.getCelularSecundario().split("-");
            celularSecundario.setCodigoArea(array[0]);
            celularSecundario.setNumero(array[1]);
            celularSecundario.setNumeroCompleto(StringUtils.leftPad(porcionVisible, 8, 'X'));
        }
        if (out.getTipoCompaniaSecundarioEnum() != null) {
            celularSecundario.setEmpresa(out.getTipoCompaniaSecundarioEnum().getDescripcion());
        }

        celularSecundario.setTitulo(TITULO_CELULAR_SECUNDARIO);
        celularSecundario.setPosicionCelular(POSICION_CELULAR_SECUNDARIO);

        CelularResponse[] celular = new CelularResponse[] { celularPrimario, celularSecundario };
        EmailResponse emailPrimario = new EmailResponse();
        emailPrimario.setTitulo(TITULO_EMAIL_UNO);
        
        String emailPrim = ISBANStringUtils.ofuscarMail(out.getEmail());
        
        emailPrimario.setEmail(emailPrim);
        EmailResponse emailSecundario = new EmailResponse();
        emailSecundario.setTitulo(TITULO_EMAIL_DOS_OPCIONAL);
        
        String emailSec = ISBANStringUtils.ofuscarMail(out.getEmailSecundario());
        
        emailSecundario.setEmail(emailSec);
        EmailResponse[] mail = new EmailResponse[] { emailPrimario, emailSecundario };
        response.setEmail(mail);

        response.setCelular(celular);
        response.setCompanias(companiasCelular);
        return response;
    }

    /**
	 * Verifica si el cliente es iU
	 * @param string
	 * @return
	 */
	private boolean verificarEsIU(String fechaNac, List<Cuenta> cuentas) {
		
		//CHEQUEAMOS PRIMERO SI ES TITULAR DE UNA VISA, AMEX, MASTER O DEBITO
		
		boolean esTitular = false;
		
		for (Cuenta cuenta : cuentas) {
			if(Cuenta.TIPOCTA_BANELCO.equalsIgnoreCase(cuenta.getTipoCuenta()) || Cuenta.TIPOCTA_AMEX.equalsIgnoreCase(cuenta.getTipoCuenta())
					|| Cuenta.TIPOCTA_VISA.equalsIgnoreCase(cuenta.getTipoCuenta()) || Cuenta.TIPOCTA_MASTER.equalsIgnoreCase(cuenta.getTipoCuenta())) {
				if(TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equalsIgnoreCase(cuenta.getCodigoTitularidad())) {
					esTitular = true;
					break;
				}
			}
		}
		
		//PROCEDEMOS A CALCULAR LA EDAD
		
		if(esTitular) {			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar now = Calendar.getInstance();
			Calendar bithDay = Calendar.getInstance();
			int age = 0;
			try {
				bithDay.setTime(sdf.parse(fechaNac));
				age = now.get(Calendar.YEAR) - bithDay.get(Calendar.YEAR);
				
				if(bithDay.get(Calendar.MONTH) > now.get(Calendar.MONTH)) { // aun no cumple
					age--;
				} else if(bithDay.get(Calendar.MONTH) == now.get(Calendar.MONTH) && bithDay.get(Calendar.DATE) > now.get(Calendar.DATE)) { // cumple este mes, pero falta
					age--;
				}
				
				if(age >=18 && age <= 31)
					return true;
			} catch (ParseException e) {
				LOGGER.info("Error al parsear la fecha de nacimiento.");
				return false;
			}
		}
		
		return false;
	}


    @Override
    public Respuesta<Void> getEstadoCensoEconomico() {
        final Respuesta<Void> response = new Respuesta<Void>();
        response.setEstadoRespuesta(EstadoRespuesta.OK);
        return response;
    }

    private DatosPersonalesPDF cargarDatosPersonalesPDF() {
        DatosPersonalesPDF datosPersonalesPDF = new DatosPersonalesPDF();
        Cliente cliente = sesionCliente.getCliente();

        datosPersonalesPDF.setResponsableDatosNombre(responsableDatosNombre);
        datosPersonalesPDF.setResponsableDatosCuit(responsableDatosCuit);
        datosPersonalesPDF.setResponsableDatosDomicilio(responsableDatosDomicilio);
        datosPersonalesPDF.setNombreSolicitante(cliente.getNombre());
        datosPersonalesPDF.setApellidoSolictante(cliente.getApellido1());
        datosPersonalesPDF.setDocumentoSolicitante(ISBANStringUtils.formatearDocumento(ISBANStringUtils.eliminarCeros(cliente.getDni())));
        cargarDomiciliosPDF(datosPersonalesPDF);
        cargarCelularesPDF(datosPersonalesPDF);
        cargarMailsPDF(datosPersonalesPDF);

        datosPersonalesPDF.setLegales(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.DATOS_PERSONALES_LEGALES));
        SimpleDateFormat sdf = new SimpleDateFormat(MASCARA_FECHA_COMPROBANTE);
        datosPersonalesPDF.setFechaComprobante(sdf.format(new Date()));

        return datosPersonalesPDF;
    }

    public void cargarDomiciliosPDF(DatosPersonalesPDF datosPersonalesPDF){
        CambioDatosContactoResponse cambioDatosContactoResponse = sesionParametros.getCambioDatosContactoResponse();

        for (CambioDomicilioView cambioDomicilioView : cambioDatosContactoResponse.getListaDomicilios()) {
            if (DOMICILIO_PRINCIPAL.equals(cambioDomicilioView.getTipoDomicilio())) {
                String domicilioPrincipal = cambioDomicilioView.getCalle() + ISBANStringUtils.ESPACIO_STRING
                        + cambioDomicilioView.getApt() + ISBANStringUtils.ESPACIO_STRING
                        + cambioDomicilioView.getPiso() + ISBANStringUtils.ESPACIO_STRING
                        + cambioDomicilioView.getDepartamento() + ISBANStringUtils.ESPACIO_STRING
                        + cambioDomicilioView.getLocalidad() + COMA_STRING + ISBANStringUtils.ESPACIO_STRING
                        + cambioDomicilioView.getDescProvincia() + ISBANStringUtils.ESPACIO_STRING + ISBANStringUtils.GUION_STRING
                        + ISBANStringUtils.ESPACIO_STRING + CODIGO_POSTAL + ISBANStringUtils.ESPACIO_STRING
                        + cambioDomicilioView.getCodigoPostal() + PUNTO_STRING;
                datosPersonalesPDF.setDomcilioPrincipal(domicilioPrincipal);
            } else if (DOMICILIO_LABORAL.equals(cambioDomicilioView.getTipoDomicilio())) {
                String domicilioLaboral = cambioDomicilioView.getCalle() + ISBANStringUtils.ESPACIO_STRING
                        + cambioDomicilioView.getApt() + ISBANStringUtils.ESPACIO_STRING
                        + cambioDomicilioView.getPiso() + ISBANStringUtils.ESPACIO_STRING
                        + cambioDomicilioView.getDepartamento() + ISBANStringUtils.ESPACIO_STRING
                        + cambioDomicilioView.getLocalidad() + COMA_STRING + ISBANStringUtils.ESPACIO_STRING
                        + cambioDomicilioView.getDescProvincia() + ISBANStringUtils.ESPACIO_STRING + ISBANStringUtils.GUION_STRING
                        + ISBANStringUtils.ESPACIO_STRING + CODIGO_POSTAL + ISBANStringUtils.ESPACIO_STRING
                        + cambioDomicilioView.getCodigoPostal() + PUNTO_STRING;
                datosPersonalesPDF.setDomicilioLaboral(domicilioLaboral);
            }
        }
    }

    public void cargarCelularesPDF(DatosPersonalesPDF datosPersonalesPDF) {
        CambioDatosContactoResponse cambioDatosContactoResponse = sesionParametros.getCambioDatosContactoResponse();

        for (CelularResponse celular : cambioDatosContactoResponse.getCelular()){
            if (POSICION_CELULAR_PRIMARIO.equals(celular.getPosicionCelular()) && celular.getNumero() != null) {
                datosPersonalesPDF.setNumeroCelularPrimario(celular.getCodigoArea() + celular.getNumero());
                datosPersonalesPDF.setEmpresaCelularPrimario(celular.getEmpresa());
            } else if (POSICION_CELULAR_SECUNDARIO.equals(celular.getPosicionCelular()) && celular.getNumero() != null) {
                datosPersonalesPDF.setNumeroCelularSecundario(celular.getCodigoArea() + celular.getNumero());
                datosPersonalesPDF.setEmpresaCelularSecundario(celular.getEmpresa());
            }
        }
    }

    public void cargarMailsPDF(DatosPersonalesPDF datosPersonalesPDF) {
        CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
        String emailPrimario = credencialesMya.getEmail();
        String emailSecundario = credencialesMya.getEmailSecundario();
        if (emailPrimario != null) {
            datosPersonalesPDF.setEmailPrimario(emailPrimario);
        }
        if (emailSecundario != null) {
            datosPersonalesPDF.setEmailSecundario(emailSecundario);
        }
    }

    private Respuesta<ReporteView> validacionRSA(ReporteView desafio) {

        AutentificacionDTO autentificacionDTO;

        Respuesta<ReporteView> estadoDesafio = autentificacionManager
                .verificarEstadoDesafio(desafio.getDesafio(), Integer.parseInt(valorDesafioDatosPersonalesPDF));
        if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = desafio.getDesafio();
        } else if (EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = cargarAutentificacionDTO();
        } else {
            return estadoDesafio;
        }

        Respuesta<AutentificacionDTO> respEjecucionMetodoAutentificacion = autentificacionManager.ejecutarValidacionRSA(autentificacionDTO);

        ReporteView respuesta = new ReporteView();
        respuesta.setDesafio(respEjecucionMetodoAutentificacion.getRespuesta());

        if(EstadoRespuesta.ERROR.equals(respEjecucionMetodoAutentificacion.getEstadoRespuesta())) { // DENY
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO_OPEN_STACK, CodigoMensajeConstantes.ERROR_TOTAL);
        }

        return respuestaFactory.transformar(respuesta, respEjecucionMetodoAutentificacion);

    }

    private AutentificacionDTO cargarAutentificacionDTO() {

        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        OperacionesRSAEnum tipoOperacion = OperacionesRSAEnum.VENTA_USD;
        DescargarDatosPersonalesDTO descargarDatosPersonalesDTO = new DescargarDatosPersonalesDTO(tipoOperacion);

        Integer operacion = Integer.parseInt(valorDesafioDatosPersonalesPDF);
        autentificacionDTO.setOperacion(operacion);
        descargarDatosPersonalesDTO.setIgnorarRSA(Boolean.TRUE);
        autentificacionDTO.setEvitarRsa(Boolean.TRUE);
        autentificacionDTO.setRsaDTO(descargarDatosPersonalesDTO);

        autentificacionDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOLUCITUD_TOKEN_PDF_DATOS_PERSONALES);
        autentificacionDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.VALIDACION_TOKEN_PDF_DATOS_PERSONALES);

        return autentificacionDTO;
    }


    @Override
    public Respuesta<ReporteView> descargarDatosPersonalesPDF(ReporteView reporteView) {

        Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
        Respuesta<Reporte> reporte;

        Respuesta<ReporteView> respuestaRSA = validacionRSA(reporteView);

        if (EstadoRespuesta.OK.equals(respuestaRSA.getEstadoRespuesta())) {
            reporte = datosPersonalesBO.descargarDatosPersonalesPDF(cargarDatosPersonalesPDF());
            if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
                estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_DATOS_PERSONALES, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                ReporteView reporteViewResponse = ReporteView.fromReporte(reporte.getRespuesta());
                respuestaView = respuestaFactory.crearRespuestaOk(reporteViewResponse);
            } else {
                estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_DATOS_PERSONALES, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
            }
        } else {
            return respuestaRSA;
        }

        return respuestaView;
    }

}

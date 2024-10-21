package ar.com.santanderrio.obp.servicios.debinrecurrente.manager;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.debinrecurrente.bo.EstadoRecurrenciaDebinEnum;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.*;
import ar.com.santanderrio.obp.servicios.personas.dao.PersonDAO;
import ar.com.santanderrio.obp.servicios.personas.dto.Person;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserResponseData;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.bo.DebinRecurrenteBO;

import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.BuyerRecurrenceListRequestDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.DebinRecurrenteRSADTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.RecurrenceBuyerItemDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.RecurrenceDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.RecurrenceDebinDTO;

import ar.com.santanderrio.obp.servicios.echeq.view.CuentaView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class DebinRecurrenteManagerImpl.
 */
@Component
public class DebinRecurrenteManagerImpl implements DebinRecurrenteManager{

	public static final String JURIDICAL_PERSON = "JURIDICAL_PERSON";
	public static final String HUMAN_PERSON ="HUMAN_PERSON";
	/** The debin recurrente BO. */

	private DebinRecurrenteBO debinRecurrenteBO;
	
	/** The sesion cliente. */
	private SesionCliente sesionCliente;
	
	/** The respuesta factory. */
	RespuestaFactory respuestaFactory;
	
    /** The valor desafio. */
    private String valorDesafio;
    
    /** The autentificacion manager. */

    private AutentificacionManager autentificacionManager;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DebinRecurrenteManagerImpl.class);

	/** The estadistica manager. */

	private EstadisticaManager estadisticaManager;
	
	/** The sesion parametros. */
	private SesionParametros sesionParametros;
	
	/** The mensaje BO. */
	private MensajeBO mensajeBO;

	private ClienteManager clienteManager;

	private RsaManager rsaManager;

	private PersonDAO personDAO;

	private String enabledNups;

	@Autowired
	public DebinRecurrenteManagerImpl(
			DebinRecurrenteBO debinRecurrenteBO,
			SesionCliente sesionCliente,
			RespuestaFactory respuestaFactory,
			@Value("${TRJCOORD.OPERAINDISTINTO.DEBIN_RECURRENTE}") String valorDesafio,
			AutentificacionManager autentificacionManager,
			EstadisticaManager estadisticaManager,
			SesionParametros sesionParametros,
			MensajeBO mensajeBO,
			ClienteManager clienteManager,
			RsaManager rsaManager,
			PersonDAO personDAO,
			@Value("${DEBINREC.ENABLED_NUPS}") String enabledNups
	){
		this.debinRecurrenteBO = debinRecurrenteBO;
		this.sesionCliente = sesionCliente;
		this.respuestaFactory = respuestaFactory;
		this.valorDesafio = valorDesafio;
		this.autentificacionManager = autentificacionManager;
		this.estadisticaManager = estadisticaManager;
		this.sesionParametros = sesionParametros;
		this.mensajeBO = mensajeBO;
		this.clienteManager = clienteManager;
		this.rsaManager = rsaManager;
		this.personDAO = personDAO;
		this.enabledNups = enabledNups;
	}

	/**
	 * Crear recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	@Override
	public Respuesta<CrearRecurrenciaView> crearRecurrencia(CrearRecurrenciaView recurrencia) {

		if( !sesionCliente.getTieneTokenRSA()){
			LOGGER.info("RSA Offline no se permite completar la operación.");
			return respuestaFactory.crearRespuestaError(org.apache.commons.lang.StringUtils.EMPTY, TipoError.RSA_OFFLINE,
					CodigoMensajeConstantes.ERROR_GENERICO_RSA_OFFLINE);
		}

		String[] nroCuentaPartes = recurrencia.getNroCuenta().split("-");
		String numeroSinSucursal = nroCuentaPartes[1].replaceAll("\\/", StringUtils.EMPTY);
		//Por defecto viene el valor "VAR" para el concepto y el detalle viene vacío; se completa el detalle a partir del concepto
		recurrencia.setDetalle(ConceptoTransferenciaEnum.getConceptoFromCodigo(recurrencia.getConcepto()).getDescripcion());

		recurrencia.setCuitComprador(sesionCliente.getCliente().getNumeroCUILCUIT().replaceAll("-", StringUtils.EMPTY));
		Cuenta cuenta = sesionCliente.getCliente().getCuentaSiContieneNumero(numeroSinSucursal);
		if (cuenta != null) {
			recurrencia.setCbuComprador(cuenta.getCbu());
		} else {
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.CUENTA_INEXISTENTE, CodigoMensajeConstantes.CUENTA_INEXISTENTE);
		}

		if (recurrencia.getDesafio() == null) {
			cargarHashValidacion(crearMapaDeLaVista(recurrencia));
		}


		Respuesta<CrearRecurrenciaView> respuestaRSA = ejecutarValidacionRSA(recurrencia);
		Respuesta<CrearRecurrenciaView> respuestaView;
				
        LOGGER.info("Ejecutando validacion de RSA con respuesta: {}.", respuestaRSA);
        if (!EstadoRespuesta.OK.equals(respuestaRSA.getEstadoRespuesta())  ) {
			if(respuestaRSA.getRespuesta()!= null && respuestaRSA.getRespuesta().getDesafio()!= null && respuestaRSA.getRespuesta().getDesafio().getBloquearUsuario()){
				LOGGER.info("Inicio de bloqueo de usuario.");
				Respuesta<RsaUpdateUserResponseData> responseBloqueoUsuario = rsaManager.updateUser(new RsaUpdateUserRequestData("LOCKOUT"));
				if(EstadoRespuesta.OK.equals(responseBloqueoUsuario.getEstadoRespuesta())){
					return respuestaFactory.crearRespuestaError(CrearRecurrenciaView.class, org.apache.commons.lang.StringUtils.EMPTY, TipoError.RSA_BLOQUEAR_USUARIO,
							CodigoMensajeConstantes.RSA_LOGIN_USUARIO_BLOQUEADO);
				} else{
					LOGGER.info("Error al bloquear el usuario");
					return respuestaFactory.crearRespuestaError(org.apache.commons.lang.StringUtils.EMPTY, TipoError.DENY_RSA, CodigoMensajeConstantes.DENY_RSA);
				}
			} else{
				return respuestaRSA;
			}
        }

		//SE VALIDA QUE NO SE HAYA MODIFICADO EL IMPORTE/CBU CON EL HASH
		validarHash(crearMapaDeLaVista(recurrencia));

        Respuesta<RecurrenceDTO> respuestaServicio = debinRecurrenteBO.crearRecurrencia(recurrencia);
        
        if (EstadoRespuesta.OK.equals(respuestaServicio.getEstadoRespuesta())) {
        	CrearRecurrenciaView respuestaRecurrencia = new CrearRecurrenciaView();
        	respuestaRecurrencia.setMensajeFeedback(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINREC_FEEDBACK_OK_CREAR_RECURRENCIA).getMensaje());
        	DatosComprobanteDebinRecurrente comprobante = new DatosComprobanteDebinRecurrente();
        	comprobante.setEmpresa(recurrencia.getNombreFantasia());
        	comprobante.setServicio(recurrencia.getProvision());
        	comprobante.setCuitVendedor(recurrencia.getCuitVendedor());
        	comprobante.setReferencia(recurrencia.getReferencia());
        	comprobante.setNumeroCuenta(recurrencia.getNroCuenta());
        	comprobante.setTipoCuenta(sesionCliente.getCliente().getCuentaSiContieneNumero(numeroSinSucursal).getTipoCuentaEnum().getDescripcion());
        	comprobante.setCuitComprador(sesionCliente.getCliente().getNumeroCUILCUIT());
        	comprobante.setConcepto(recurrencia.getConcepto());
        	comprobante.setDescripcion(ConceptoTransferenciaEnum.getConceptoFromCodigo(recurrencia.getConcepto()).getDescripcion());
        	comprobante.setFechaOperacion(new DateTime().toString("dd/MM/yyyy"));
        	comprobante.setNumeroComprobante("PROXIMAMENTE");
			comprobante.setIdRecurrencia(respuestaServicio.getRespuesta().getRecurrenceId().toString());
			sesionParametros.setDatosComprobanteDebinRecurrente(comprobante);
        	respuestaRecurrencia.setDatosComprobante(comprobante);
        	respuestaView = respuestaFactory.crearRespuestaOk(respuestaRecurrencia, StringUtils.EMPTY, CodigoMensajeConstantes.DEBINREC_FEEDBACK_OK_CREAR_RECURRENCIA);
        	estadisticaManager.add(EstadisticasConstants.FEEDBACK_ADHESION_DEBIN_RECURRENTE,EstadisticasConstants.CODIGO_ESTADISTICAS_OK); 
        } else {
        	estadisticaManager.add(EstadisticasConstants.FEEDBACK_ADHESION_DEBIN_RECURRENTE,EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR); 
        	respuestaView = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
        }
        return respuestaView;   
	}

	/**
	 * Ejecutar validacion RSA.
	 *
	 * @param crearRecurrenciaView the extraccion efectivo view
	 * @return the respuesta
	 */
	private Respuesta<CrearRecurrenciaView> ejecutarValidacionRSA(CrearRecurrenciaView crearRecurrenciaView) {
		
        AutentificacionDTO autentificacionDTO;

        Respuesta<CrearRecurrenciaView> estadoDesafio = autentificacionManager.verificarEstadoDesafio(crearRecurrenciaView.getDesafio(),
                Integer.parseInt(valorDesafio));

        if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = crearRecurrenciaView.getDesafio();
        } else if (EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = generarAutentificacionDTO(crearRecurrenciaView);
        } else {
            return estadoDesafio;
        }

        Respuesta<AutentificacionDTO> rstaAutentificacion = autentificacionManager
                .ejecutarValidacionRSA(autentificacionDTO);
        crearRecurrenciaView.setDesafio(rstaAutentificacion.getRespuesta());

        return respuestaFactory.transformar(crearRecurrenciaView, rstaAutentificacion);
	}

    /**
     * Generar autentificacion DTO.
     *
     * @return the autentificacion DTO
     */
    private AutentificacionDTO generarAutentificacionDTO(CrearRecurrenciaView crearRecurrenciaView) {
        Integer operacion = Integer.parseInt(valorDesafio);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setOperacion(operacion);
        autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
                new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));

        // Seteo de estadisticas de RSA
        autentificacionDTO
        	.setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.SOLICITAR_COORDENADAS_NUEVA_RECURRENCIA);
        autentificacionDTO
        	.setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.VALIDAR_COORDENADAS_NUEVA_RECURRENCIA);
        autentificacionDTO
        	.setCodigoEstadisticaValidacionToken(EstadisticasConstants.VALIDAR_TOKEN_NUEVA_RECURRENCIA);
        
        // Datos informados a RSA
		DebinRecurrenteRSADTO debinRecurrenteRSADTO = new DebinRecurrenteRSADTO(operacion);
		debinRecurrenteRSADTO.setCuitComprador(crearRecurrenciaView.getCuitComprador());
		debinRecurrenteRSADTO.setCuitVendedor(crearRecurrenciaView.getCuitVendedor());
		debinRecurrenteRSADTO.setMoneda("ARS".equals(crearRecurrenciaView.getMoneda())? "peso":"dolar");
		debinRecurrenteRSADTO.setValidarBloqueo(Boolean.TRUE);
		debinRecurrenteRSADTO.setTipoSegmentoCliente(this.sesionCliente.getCliente().getSegmento() != null ?
				this.sesionCliente.getCliente().getSegmento().getNombre() : "");
		debinRecurrenteRSADTO.setConcepto(crearRecurrenciaView.getConcepto());
		debinRecurrenteRSADTO.setPrestacion(crearRecurrenciaView.getProvision());
		debinRecurrenteRSADTO.setDescripcion(crearRecurrenciaView.getDetalle());
		debinRecurrenteRSADTO.setMismoTitular(crearRecurrenciaView.getCuitComprador().equals(crearRecurrenciaView.getCuitVendedor()));
		debinRecurrenteRSADTO.setVendedorPersonaJuridica(vendedorPersonaJuridica(crearRecurrenciaView.getCuitVendedor()));
		cargarDatosClaveToken(debinRecurrenteRSADTO);
        autentificacionDTO.setRsaDTO(debinRecurrenteRSADTO);

        return autentificacionDTO;
    }

    private boolean vendedorPersonaJuridica(String cuitVendedor) {
    	boolean personaJuridica = false;
    	try {
			Person person = personDAO.findPersonInfoByCuit(cuitVendedor, "bcra");
			personaJuridica = JURIDICAL_PERSON.equalsIgnoreCase(person.getPersonType());
		} catch (DAOException e) {
    		LOGGER.error("Error searching person with cuit {} ", cuitVendedor);
    		personaJuridica = cuitVendedor.startsWith("30");
		}
		return personaJuridica;
	}

	private void cargarDatosClaveToken(DebinRecurrenteRSADTO debinRecurrenteRSADTO ) {
		Respuesta<List<BigDecimal>> antiguedades = clienteManager.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
		if(antiguedades != null && antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
			if(antiguedades.getRespuesta().get(0) != null) {
				debinRecurrenteRSADTO.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
			}
			if(antiguedades.getRespuesta().get(1) != null) {
				debinRecurrenteRSADTO.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
			}
		}
	}
	
	/**
	 * Obtener info cliente config.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<DatosConfigClienteDebinRecurrenteView> obtenerInfoClienteConfig() {
		Cliente cliente = sesionCliente.getCliente();
		
		if (StringUtils.isEmpty(cliente.getEmailLoginMya())) {
        	estadisticaManager.add(EstadisticasConstants.CONFIGURAR_ADHESION_RECURRENCIA ,EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR); 
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
		}
		
		DatosConfigClienteDebinRecurrenteView datosCliente = new DatosConfigClienteDebinRecurrenteView();
		
		if (cliente.getCuentas() == null || cliente.getCuentas().isEmpty()) {
        	estadisticaManager.add(EstadisticasConstants.CONFIGURAR_ADHESION_RECURRENCIA ,EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR); 
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
		}
		
		List<CuentaView> listaCuentas = obtenerCuentas(cliente);
		datosCliente.setListaCuentas(listaCuentas);
		datosCliente.setMensajeInformativo("Mensaje informativo para mostrar en recuadro azul");
		datosCliente.setTerminosCondiciones("Terminos y condiciones para mostrar en stack aparte");
		datosCliente.setListaConceptos(armarListaConceptos());
    	estadisticaManager.add(EstadisticasConstants.CONFIGURAR_ADHESION_RECURRENCIA ,EstadisticasConstants.CODIGO_ESTADISTICAS_OK); 
		return respuestaFactory.crearRespuestaOk(datosCliente);
	}

	/**
	 * Indica si activar o no la funcionalidad dependiendo si el cliente se encuentra
	 * dentro de la lista de nups habilitados o no.
	 * @return true si el cliente está dentro de la lista de nups habilitados; caso contrario, false.
	 */
	@Override
	public Respuesta<ActivarDebinRecurrenteView> activar() {
		try {
			Cliente cliente = sesionCliente.getCliente();
			LOGGER.info("Buscando a " + cliente.toString() + " [" + cliente.getNup() + "] dentro de los nups habilitados...");
			return respuestaFactory.crearRespuestaOk(
					new ActivarDebinRecurrenteView(isClientEnabled(cliente.getNup()))
			);
		} catch (Exception e) {
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
		}
	}

	/**
	 * Pausar recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AccionRecurrenciaResponseView> pausarRecurrencia(RecurrenciaView recurrencia) {
		return debinRecurrenteBO.pausarRecurrencia(recurrencia);
	}

	/**
	 * Reanudar recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AccionRecurrenciaResponseView> reanudarRecurrencia(RecurrenciaView recurrencia) {
		return debinRecurrenteBO.reactivarRecurrencia(recurrencia);
	}
	
	/**
	 * Desubscribir recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AccionRecurrenciaResponseView> desubscribirRecurrencia(RecurrenciaView recurrencia) {
		return debinRecurrenteBO.desubscribirRecurrencia(recurrencia);
	}
	
	/**
	 * Confirmar reanudar recurrencia.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> desconocerPago(DebinView debin) {
		Cliente cliente = sesionCliente.getCliente();
		return debinRecurrenteBO.desconocerPago(cliente, debin.getIdDebin());
	}
	
	/**
	 * Obtenemos todas las recurrencias por comprador, por cada cuenta que posea.
	 * Si hay al menos una recurrencia la retornamos. 
	 *
	 *
	 * @param view the view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<RecurrenciasView> obtenerRecurrenciasPorComprador(ObtenerRecurrenciasView view){
		Cliente cliente = sesionCliente.getCliente();
		RecurrenciasView recurrenciasOut = new RecurrenciasView();

		// Obtengo todas las cuentas para mostrar en el selector y las paso al front.
		recurrenciasOut.setCuentasSelector(obtenerCuentas(cliente));

		// Si el cbu no viene informado, asumimos que es la primera vez que ingresa a la grilla
		if(view.getCbu() == null) {
			// las recurrencias que se muestran en este caso son de la cuenta FAVORITA
			view.setCbu(recurrenciasOut.getCuentasSelector().get(0).getCbu());
		}
		
		Respuesta<BuyerRecurrenceListRequestDTO> obtenerRecurrenciasPorComprador = debinRecurrenteBO.obtenerRecurrenciasPorComprador(cliente, view);
		
		if(obtenerRecurrenciasPorComprador.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_LISTA_RECURRENCIAS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return this.respuestaFactory.crearRespuestaError(RecurrenciasView.class, recurrenciasOut, StringUtils.EMPTY, TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
		}
		
		List<RecurrenciaItemView> recurrences = convertResultSetToView(obtenerRecurrenciasPorComprador);
		
		if(recurrences.isEmpty()) {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_LISTA_RECURRENCIAS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaWarning(recurrenciasOut, StringUtils.EMPTY, TipoError.DEBINWS_WARNING_SIN_DATOS, CodigoMensajeConstantes.DEBINREC_WARNING_SIN_DATOS);
		} else {
			setHayMas(view, recurrenciasOut, obtenerRecurrenciasPorComprador);
			estadisticaManager.add(EstadisticasConstants.CONSULTA_LISTA_RECURRENCIAS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			recurrenciasOut.setListaDebin(recurrences);
			return respuestaFactory.crearRespuestaOk(recurrenciasOut);
		}
	}

	private List<RecurrenciaItemView> convertResultSetToView(
		Respuesta<BuyerRecurrenceListRequestDTO> obtenerRecurrenciasPorComprador) {
		List<RecurrenciaItemView> recurrences = new ArrayList<RecurrenciaItemView>();
		for (RecurrenceBuyerItemDTO item : obtenerRecurrenciasPorComprador.getRespuesta().getRecurrences()) {
				RecurrenciaItemView recurrence = convertResponseDtoToView(item);
				recurrences.add(recurrence);
		}
		return recurrences;
	}

	/**
	 * En el caso de la primera vez desde el front, numeroPagina no vendra informado. Por ello, para saber si hay mas registros me basta con la respuesta del servicio (totalPages > 1)
	 * Si ya viene informado el caso en que hay mas registros depende de numeroPagina. La ultima pagina coincide con totalPages. 
	 *
	 * @param view the view
	 * @param recurrenciasOut the recurrencias out
	 * @param obtenerRecurrenciasPorComprador the obtener recurrencias por comprador
	 */
	private void setHayMas(ObtenerRecurrenciasView view, RecurrenciasView recurrenciasOut,
			Respuesta<BuyerRecurrenceListRequestDTO> obtenerRecurrenciasPorComprador) {
		int totalPages = obtenerRecurrenciasPorComprador.getRespuesta().getTotalPages().intValue();
		if(!view.getNumeroPagina().isEmpty()) {
			int numeroPagina = Integer.parseInt(view.getNumeroPagina());
			recurrenciasOut.setHayMasRegistros(numeroPagina < totalPages);
		}else {
			recurrenciasOut.setHayMasRegistros(totalPages > 1);
		}
	}

	/**
	 * Convert RecurrenceBuyerItemDTO (Recurrence API) to RecurrenciaItemView (debinr).
	 *
	 * @param recurrence the recurrence
	 * @return the recurrencia item view
	 */
	private RecurrenciaItemView convertResponseDtoToView(RecurrenceBuyerItemDTO recurrence) {
		
		RecurrenciaItemView item = new RecurrenciaItemView();
		RecurrenceDebinDTO debin = recurrence.getDebin();

		try {
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    	Date date = formatter.parse(recurrence.getCreationTimestamp());
			item.setFechaAdhesion(ISBANStringUtils.formatearFecha(date));
		} catch (ParseException e) {
			LOGGER.warn("No se puede obtener la fecha de adhesion {}", recurrence.getCreationTimestamp());
			item.setFechaAdhesion(StringUtils.EMPTY);
		}
	    
		item.setCbu(recurrence.getBuyerCbu());
		EstadoRecurrenciaDebinEnum status = EstadoRecurrenciaDebinEnum.obtenerEstadoPorDescripcion(recurrence.getStatus());
		if(status!= null) {
			item.setEstado(status.getDescView());
		}
    	item.setServicio(debin.getProvision());
		item.setEmpresa(recurrence.getSellerName());
		item.setCuitEmpresa(recurrence.getSellerCuit());
		item.setDebinId(recurrence.getRecurrenceId() + StringUtils.EMPTY);
		item.setReferencia(debin.getReference());
		item.setDescripcion(debin.getDetail());
		item.setConcepto(debin.getConcept());
		item.setCuitComprador(recurrence.getBuyerCuit());
		item.setMoneda(debin.getCurrency());
		
		return item;
	}

	/**
	 * Generar cuenta view.
	 *
	 * @param cuenta the cuenta
	 * @return the cuenta view
	 */
	private CuentaView generarCuentaView(Cuenta cuenta) {
		CuentaView cuentaView = new CuentaView();
		cuentaView.setAlias(cuenta.getAlias());
		cuentaView.setDescripcionTipoCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
		cuentaView.setNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
		cuentaView.setSaldoPesos(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar())) ? 
				ISBANStringUtils.formatearConComaYDosDecimales2(cuenta.getSaldoCUPesos()) : ISBANStringUtils.formatearConComaYDosDecimales2(cuenta.getSaldoCuenta()));
		cuentaView.setMoneda(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar())) || 
							 TipoCuenta.CAJA_AHORRO_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar())) ? 
							 DivisaEnum.PESO.getCodigo() : DivisaEnum.DOLAR.getCodigo());
		cuentaView.setCbu(cuenta.getCbu());
		return cuentaView;
	}

	/**
	 * Obtener cuentas.
	 *
	 * @param cliente the cliente
	 * @return the list
	 */
	private List<CuentaView> obtenerCuentas(Cliente cliente) {
		List<CuentaView> listaCuentas = new ArrayList<CuentaView>();
		
		for (Cuenta cuenta : cliente.getCuentasMonetarias()) {
			if (cuenta.getIsFavorita()) {
				listaCuentas.add(0, generarCuentaView(cuenta));
			} else {
				listaCuentas.add(generarCuentaView(cuenta));
			}	
		}
		return listaCuentas;
	}
	
	/**
	 * Armar lista conceptos.
	 *
	 * @return the list
	 */
	private List<ConceptoDebinRecurrente> armarListaConceptos() {
		
		List<String> codigosConceptosRequeridos = new ArrayList<String>();
		codigosConceptosRequeridos.add("ALQ");
		codigosConceptosRequeridos.add("CUO");
		codigosConceptosRequeridos.add("EXP");
		codigosConceptosRequeridos.add("SEG");
		codigosConceptosRequeridos.add("FAC");
		codigosConceptosRequeridos.add("HON");
		codigosConceptosRequeridos.add("VAR");
		codigosConceptosRequeridos.add("HAB");
		
		List<ConceptoDebinRecurrente> listaConceptos = new ArrayList<ConceptoDebinRecurrente>();
				
		for (String conceptoRequerido : codigosConceptosRequeridos) {
			ConceptoTransferenciaEnum conceptoEnum = ConceptoTransferenciaEnum.getConceptoFromCodigo(conceptoRequerido);
			ConceptoDebinRecurrente conceptoDebin = new ConceptoDebinRecurrente();
			conceptoDebin.setId(conceptoEnum.getCodigo());
			conceptoDebin.setDescripcion(conceptoEnum.getDescripcion());
			listaConceptos.add(conceptoDebin);
		}
		
		return listaConceptos;
	}

	/**
	 * Generar comprobante PDF.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ReporteView> generarComprobantePDF() {
		DatosComprobanteDebinRecurrente datosComprobante = sesionParametros.getDatosComprobanteDebinRecurrente();
		DateTime fechaHoy = new DateTime();
		datosComprobante.setFechaHoraComprobante(fechaHoy.toString("dd/MM/yyyy") + " - " + fechaHoy.toString("HH:mm:ss") + " hs.");
		Respuesta<Reporte> reporteRespuesta = debinRecurrenteBO.generarComprobantePDF(datosComprobante);
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
			estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_ADHESION_DEBIN_RECURRENTE, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, "ERROR_GENERICO", CodigoMensajeConstantes.EXTRACCION_EFECTIVO_DESCARGA_PDF_ERROR);
			estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_ADHESION_DEBIN_RECURRENTE, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
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
	 * Crear mapa de la vista.
	 *
	 * @param recurrencia
	 *            the opera exterior view
	 * @return the map
	 */
	public Map<String, Object> crearMapaDeLaVista(CrearRecurrenciaView  recurrencia) {
		LOGGER.info("Creando hash de los atributos...");
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("cuitVendedor", recurrencia.getCuitVendedor() != null ?  recurrencia.getCuitVendedor() : "");
		mapaAtributos.put("moneda", recurrencia.getMoneda() != null ?  recurrencia.getMoneda() : "");
		mapaAtributos.put("provision", recurrencia.getProvision() != null ?  recurrencia.getProvision() : "");
		mapaAtributos.put("referencia", recurrencia.getReferencia() != null ?  recurrencia.getReferencia() : "");

		return mapaAtributos;
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

	private boolean isClientEnabled(String clientNup) {
		boolean clientEnabled = false;
		String[] enabledNupsArray = null;
		if (!StringUtils.isEmpty(this.enabledNups)) {
			enabledNupsArray = this.enabledNups.split(",");
			for (String enabledNup : enabledNupsArray) {
				if (!clientEnabled && enabledNup.trim().equalsIgnoreCase(clientNup)) clientEnabled = true;
			}
		} else {
			clientEnabled = true;
		}
		return clientEnabled;
	}
}

/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.manager;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.accounts.AccountsApi;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountEntity;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountsResponseEntity;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import org.apache.bval.jsr303.ApacheValidationProvider;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.thoughtworks.xstream.XStream;

import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.inversiones.DatosEvaluacionRiesgo;
import ar.com.santanderrio.obp.generated.webservices.inversiones.EvaluacionDeRiesgoResponse;
import ar.com.santanderrio.obp.generated.webservices.inversiones.entities.Disclaimer;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesBO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.ConsultaPerfilInversorViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosTestPerfilViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FondoResumidoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FondoResumidoView;

/**
 * The Class BaseManager.
 */
@Component
public class BaseManager {

	/** The Constant ERROR_GENERICO. */
	protected static final String ERROR_GENERICO = "10422";

	/** The Constant CANAL_VERSION. */
	private static final String CANAL_VERSION = "000";

	/** The Constant CANAL_TIPO. */
	private static final String CANAL_TIPO = "04";

	/** The Constant COD_CANAL. */
	protected static final String COD_CANAL = "OBP";

	/** The Constant CANAL_ID. */
	private static final String CANAL_ID = "HTML";

	/** The Constant USUARIO_TIPO. */
	private static final String USUARIO_TIPO = "03";

	/** The Constant USUARIO_ATRIB. */
	private static final String USUARIO_ATRIB = "  ";

	/** The Constant TIPO_CLIENTE_ID. */
	private static final String TIPO_CLIENTE_ID = "N";

	/** The Constant TIPO_CLIENTE. */
	private static final String TIPO_CLIENTE = "I";

	/** The Constant SUBCANAL_TIPO. */
	private static final String SUBCANAL_TIPO = "99";

	/** The Constant SUBCANAL_ID. */
	private static final String SUBCANAL_ID = "0001";

	/** The Constant COMPRA_VENTA. */
	private static final String COMPRA_VENTA = "1";

	/** The Constant TIPO_OPERACION_SUSCRIPCION_FONDO. */
	protected static final String TIPO_OPERACION_SUSCRIPCION_FONDO = "SFON";

	/** The Constant TIPO_OPERACION_TRANSFERENCIA_FONDO. */
	protected static final String TIPO_OPERACION_TRANSFERENCIA_FONDO = "TFON";

	/** The Constant PRODUCTO. */
	private static final String PRODUCTO = "FC";

	/** The Constant COD_TIPO_CLIENTE. */
	private static final String COD_TIPO_CLIENTE = "INDI";

	/** The Constant COD_TIPO_CLIENTE_BP. */
	private static final String COD_TIPO_CLIENTE_BP = "BPIN";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseManager.class);

	/** The perfil inversor formatter. */
	private final SimpleDateFormat perfilInversorFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/** The Constant valid text Formater  */
	private final SimpleDateFormat validacionTextFormater = new SimpleDateFormat("dd/MM/yyyy");
	
	/** The fechaDesde*/
	private String fechaDesde;
	
	/** The fechaDesde*/
	private String fecchaHasta;
	
	/** The id perfil*/
	private int idPerfil;

	/** The Constant MSJ_INFO_GUARDANDO_HASH_EN_SESION. */
	protected static final String MSJ_INFO_GUARDANDO_HASH_EN_SESION = "Se guarda el hash en sesion.";

	/** The Constant MSJ_INFO_CREANDO_HASH_ATRIBUTOS. */
	protected static final String MSJ_INFO_CREANDO_HASH_ATRIBUTOS = "Creando hash de los atributos...";

	/** The Constant MSJ_INFO_VALIDANDO_HASH. */
	protected static final String MSJ_INFO_VALIDANDO_HASH = "Validando el hash {} de la sesion con el hash de la entidad {}";

	/** producto altair de cuenta ANSES **/
	private static final String PRODUCTO_ALTAIR_ANSES = "02";

	/** sub producto altair de cuenta ANSES **/
	private static final List<String> SUBPRODUCTO_ALTAIR_ANSES = Arrays.asList("0001", "0007", "0009");

	/** sub producto altair de cuenta ANSES **/
	private static final List<String> CONVENIOS_ANSES = Arrays.asList("0427677", "0733505");

	/** The link test perfil inversor. */
	@Value("${INVERSIONES.TESTPERFILINVERSOR}")
	private String linkTestPerfilInversor;

	/** The validator. */
	private Validator validator;

	/** The legal BO. */
	@Autowired
	protected LegalBO legalBO;

	/** The sesion cliente. */
	@Autowired
	protected SesionCliente sesionCliente;

	/** The inversiones BO. */
	@Autowired
	private InversionesBO inversionesBO;

	/** The respuesfactory. */
	@Autowired
	private RespuestaFactory respuesfactory;

	/** The inversion WS helper. */
	@Autowired
	private InversionWSHelper inversionWSHelper;
	
	/** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

	/** The api-accounts */
	@Autowired
	private AccountsApi accountsApi;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

    /** The Constant Mensaje Perfil Inversor. */
    private static final String TEST_INVERSOR_NO_REALIZADO ="SAC-00051";
    
    /** the Mensaje no tiene perfil*/ 
    private String mensaje;

	/**
	 * Dejar publico para poder ser inicializado en el test Validator init.
	 */
	@PostConstruct
	public void validatorInit() {
		ValidatorFactory validatorFactory = Validation.byProvider(ApacheValidationProvider.class).configure()
				.buildValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	/**
	 * Valida un bean, si tiene violaciones de restricciones se genera la
	 * respues correspondiente.
	 *
	 * @param <T>
	 *            the generic type
	 * @param <E>
	 *            the element type
	 * @param bean
	 *            the bean
	 * @param beanOut
	 *            the bean out
	 * @return the respuesta
	 */
	public <T, E> Respuesta<E> validate(T bean, E beanOut) {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(bean);
		Respuesta<E> respuesta = new Respuesta<E>();

		if (!CollectionUtils.isEmpty(constraintViolations)) {
			respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);

			for (ConstraintViolation<T> cv : constraintViolations) {
				ItemMensajeRespuesta item = new ItemMensajeRespuesta(cv.getMessage());
				item.setTag(cv.getPropertyPath().toString());
				respuesta.add(item);
			}
		} else {
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		}

		return respuesta;
	}

	/**
	 * View to DTO.
	 *
	 * @param cuentasTitulo
	 *            the cuentas titulo
	 * @param tipoBanca
	 *            the tipo banca
	 * @return the cuentas consulta fondo DTO
	 */
	protected CuentasConsultaFondoDTO viewToDTO(List<CuentaTituloView> cuentasTitulo, String tipoBanca) {
		CuentasConsultaFondoDTO retorno = new CuentasConsultaFondoDTO();
		retorno.setTipoBanca(tipoBanca);
		List<CuentaTituloDTO> cuentasTituloDTO = new ArrayList<CuentaTituloDTO>();
		for (CuentaTituloView cuentaTituloView : cuentasTitulo) {
			CuentaTituloDTO cuentaTituloDTO = new CuentaTituloDTO();
			cuentaTituloDTO.setNroCuenta(cuentaTituloView.getNroCuenta());
			cuentaTituloDTO.setSucursal(cuentaTituloView.getSucursal());
			cuentaTituloDTO.setNroCuentaFormateado(cuentaTituloView.getNroCuentaFormateado());
			cuentaTituloDTO.setCuentaOperativa(cuentaTituloView.getCuentaOperativa());
			cuentaTituloDTO.setIntervinientes(cuentaTituloView.getIntervinientes());
			cuentasTituloDTO.add(cuentaTituloDTO);
		}
		retorno.setCuentasTitulo(cuentasTituloDTO);
		return retorno;
	}

	/**
	 * carga los datos para el request de evaluacion de riesgo.
	 *
	 * @return the datos evaluacion riesgo
	 */
	protected DatosEvaluacionRiesgo cargarDatosEvalRiesgo() {
		DatosEvaluacionRiesgo parametroDatos = new DatosEvaluacionRiesgo();
		// FIJOS ??
		parametroDatos.setCodCanal(COD_CANAL);
		parametroDatos.setCanalId(CANAL_ID);
		parametroDatos.setCanalTipo(CANAL_TIPO);
		parametroDatos.setCanalVersion(CANAL_VERSION);
		parametroDatos.setSubcanalId(SUBCANAL_ID);
		parametroDatos.setSubcanalTipo(SUBCANAL_TIPO);
		parametroDatos.setTipoCliente(TIPO_CLIENTE);
		parametroDatos.setTipoClienteId(TIPO_CLIENTE_ID);
		parametroDatos.setUsuarioAtrib(USUARIO_ATRIB);
		parametroDatos.setUsuarioTipo(USUARIO_TIPO);
		parametroDatos.setProducto(PRODUCTO);
		parametroDatos.setCompraVenta(COMPRA_VENTA);

		parametroDatos.setNup(sesionCliente.getCliente().getNup());
		parametroDatos.setUsuarioPwd(sesionCliente.getCliente().getPasswordRacf());
		parametroDatos.setUsuarioId(sesionCliente.getCliente().getUsuarioRacf());
		parametroDatos.setIdCliente(sesionCliente.getCliente().getDni());
		parametroDatos.setFechaNac(sesionCliente.getCliente().getFechaNacimiento());

		// VARIABLES

		return parametroDatos;
	}

	/**
	 * Elimina las cuentas cepo de la lista de cuentas cepo, de la lista de
	 * cuentasdebito.
	 *
	 * @param cuentasCepo
	 *            the cuentas cepo
	 * @param cuentasDebito
	 *            the cuentas debito
	 * @return the list
	 */
	protected List<CuentasAdhesionDebitoView> filtrarCuentasCepo(List<Cuenta> cuentasCepo,
			List<CuentasAdhesionDebitoView> cuentasDebito) {
		for (CuentasAdhesionDebitoView cuentaView : cuentasDebito) {

			for (Cuenta cuenta : cuentasCepo) {
				if (cuenta.getCbu().equals(cuentaView.getCbu())) {
					cuentasDebito.remove(cuentaView);
				}
			}
		}
		return cuentasDebito;
	}

	/**
	 * Carga los mensajes que van a ir en el popup, se utiliza cuando el perfil
	 * es riesgoso o bloqueante Recibe el tipo error para setearlo en los items,
	 * que luego afectan a la respuesta que se devuelve.
	 *
	 * @param evaluacionDeRiesgoResponse
	 *            the evaluacion de riesgo response
	 * @param tipoError
	 *            the tipo error
	 * @return the list
	 */
	protected List<ItemMensajeRespuesta> cargarMensajesPopup(EvaluacionDeRiesgoResponse evaluacionDeRiesgoResponse,
			TipoError tipoError) {
		List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		if (evaluacionDeRiesgoResponse.getMensaje() != null
				&& evaluacionDeRiesgoResponse.getMensaje().getDisclaimer() != null
				&& evaluacionDeRiesgoResponse.getMensaje().getDisclaimer().getDisclaimers() != null) {
			for (Disclaimer disclaimer : evaluacionDeRiesgoResponse.getMensaje().getDisclaimer().getDisclaimers()) {
				ItemMensajeRespuesta mensajeRespuesta = new ItemMensajeRespuesta(disclaimer.getText());
				mensajeRespuesta.setExtra(disclaimer.getTitulo());
				mensajeRespuesta.setTipoError(tipoError.toString());
				itemsMensajeRespuesta.add(mensajeRespuesta);
			}
		} else {
			itemsMensajeRespuesta.add(new ItemMensajeRespuesta());
		}
		return itemsMensajeRespuesta;
	}

	/**
	 * Consultar perfil inversor.
	 * @param isBancaPrivada 
	 *
	 * @return the respuesta
	 */
	
	public Respuesta<ConsultaPerfilInversorViewResponse> consultarPerfilInversor(boolean isBancaPrivada) {
		Respuesta<ConsultaPerfilInversorViewResponse> salida = new Respuesta<ConsultaPerfilInversorViewResponse>();
		ConsultaPerfilInversorViewResponse datosSalida = new ConsultaPerfilInversorViewResponse();
		Respuesta<PerfilInversorResponse> datos  = inversionesBO.consultarPerfilInversor(isBancaPrivada);
		
		datosSalida.setIdPerfil(Integer.toString((datos.getRespuesta().getDatos().getIdPerfil())));
		datosSalida.setDescripcionLarga(datos.getRespuesta().getDatos().getDescripcionLarga());
		datosSalida.setPerfil(datos.getRespuesta().getDatos().getDescripcion());
		
		idPerfil= datos.getRespuesta().getDatos().getIdPerfil(); 
		if (idPerfil == 0) {
			mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.PERFIL_INVERSOR_NO_PERFILADO_SIMULACION).getMensaje();
			datosSalida.setDescripcionLargaOperatoria(mensaje);
			datosSalida.setDescripcionLarga(mensaje);
		}
			
		if (datos.getRespuesta().getDatos().getFechaDesde() != null) {
			fechaDesde = validacionTextFormater.format(datos.getRespuesta().getDatos().getFechaDesde());
			fecchaHasta = validacionTextFormater.format(datos.getRespuesta().getDatos().getFechaHasta());
			datosSalida.setTextoVigencia("Vigencia desde el " +   fechaDesde + " hasta el " + fecchaHasta + ".");	
		} else {
			datosSalida.setTextoVigencia(null);
		}
		
		salida.setEstadoRespuesta(datos.getEstadoRespuesta());
		salida.setId(datos.getId());
		salida.setItemMensajeRespuesta(datos.getItemsMensajeRespuesta());
		salida.setRespuestaVacia(datos.isRespuestaVacia());
		salida.setSkipLog(datos.getSkipLog());
	    salida.setRespuesta(datosSalida);
	    
		return salida; 
	}
	
	/**
	 * Obtener datos test perfil.
	 *
	 * @param isBP 
	 * 		the is BP
	 * @return the respuesta
	 */
	public Respuesta<DatosTestPerfilViewResponse> obtenerDatosTestPerfil(boolean isBP) {
		DatosTestPerfilViewResponse datosTestPerfil = new DatosTestPerfilViewResponse(linkTestPerfilInversor);

		datosTestPerfil.setCodCanal(COD_CANAL);
		datosTestPerfil.setCodTipoCliente(isBP ? COD_TIPO_CLIENTE_BP : COD_TIPO_CLIENTE);
		datosTestPerfil.setTimeStamp(perfilInversorFormatter.format(new Date()));
		datosTestPerfil.setNupCliente(sesionCliente.getCliente().getNup());

		XStream xstream = new XStream();
		xstream.processAnnotations(DatosTestPerfilViewResponse.class);

		String firma = "";
		try {
			firma = inversionWSHelper.getDatosFirmados(xstream.toXML(datosTestPerfil));
		} catch (Exception e) {
			LOGGER.error("Error creando firma para test Perfil Inversor", e);
		}
		datosTestPerfil.setFirma(firma);
		return respuesfactory.crearRespuestaOk(DatosTestPerfilViewResponse.class, datosTestPerfil);
	}

	/**
	 * DT oto view.
	 *
	 * @param cuentasRetornoBO
	 *            the cuentas retorno BO
	 * @return the cuentas consulta fondo view
	 */
	protected List<CuentaTituloView> obtenerCuentasTituloView(CuentasConsultaFondoDTO cuentasRetornoBO) {

		List<CuentaTituloView> cuentasTituloView = new ArrayList<CuentaTituloView>();
		if (cuentasRetornoBO.getCuentasTitulo() != null) {
			for (CuentaTituloDTO cuentaTituloDTO : cuentasRetornoBO.getCuentasTitulo()) {
				CuentaTituloView cuentaTituloView = new CuentaTituloView();
				cuentaTituloView.setNroCuenta(cuentaTituloDTO.getNroCuenta());
				cuentaTituloView.setSucursal(cuentaTituloDTO.getSucursal());
				cuentaTituloView.setNroCuentaFormateado(cuentaTituloDTO.getNroCuentaFormateado());
				cuentaTituloView.setCuentaOperativa(cuentaTituloDTO.getCuentaOperativa());
				cuentaTituloView.setCuentaOperativaSinFormatear(cuentaTituloDTO.getCuentaOperativaSinFormatear());
				cuentaTituloView.setIntervinientes(cuentaTituloDTO.getIntervinientes());
				if (cuentaTituloDTO.getFondosSuscriptos() != null) {
					setearFondoResumidoPorCuenta(cuentaTituloDTO, cuentaTituloView);
				}
				if(cuentaTituloDTO.isRepatriacion()) {
					cuentaTituloView.setRepatriacion(true);
				}
				cuentasTituloView.add(cuentaTituloView);
			}
		}

		return cuentasTituloView;
	}
	
	/**
	 * Obtener cuenta operativa sin formatear.
	 *
	 * @param cuentaTitulo
	 *            the cuenta titulo
	 * @param cliente
	 *            the cliente
	 * @return the string
	 */
	@SuppressWarnings("unused")
	protected String obtenerCuentaOperativaSinFormatear(String cuentaTitulo,Cliente cliente ){
		
		List<CuentaBancaPrivada> list = cliente.getCuentasBancaPrivada();
		
		for (CuentaBancaPrivada cuentaBancaPrivada : list) {
			if(Integer.valueOf(cuentaTitulo).equals(Integer.valueOf(cuentaBancaPrivada.getCuentaOperativa().getNroCuentaTit()))){
				
				return ISBANStringUtils.eliminarCeros(cuentaBancaPrivada.getCuentaOperativa().getContratoAltair());
				
			}
		}
		
		return null;
	}

	/**
	 * Setea los fondos resumidos para cada cuenta titulo.
	 *
	 * @param cuentaTituloDTO
	 *            the cuenta titulo DTO
	 * @param cuentaTituloView
	 *            the cuenta titulo view
	 */
	private void setearFondoResumidoPorCuenta(CuentaTituloDTO cuentaTituloDTO, CuentaTituloView cuentaTituloView) {
		for (FondoResumidoDTO fondoResumidoDTO : cuentaTituloDTO.getFondosSuscriptos()) {
			FondoResumidoView fondoResumidoView = new FondoResumidoView();

			fondoResumidoView.setImporteMaximo(fondoResumidoDTO.getImporteMaximo());
			fondoResumidoView.setImporteMinimo(fondoResumidoDTO.getImporteMinimo());
			fondoResumidoView.setMoneda(fondoResumidoDTO.getMoneda());
			fondoResumidoView.setNombreFondo(fondoResumidoDTO.getNombreFondo());
			fondoResumidoView.setSaldo(fondoResumidoDTO.getSaldo());
			fondoResumidoView.setCodigoFondo(fondoResumidoDTO.getCodigoFondo());
			fondoResumidoView.setPlazoEfectivo(fondoResumidoDTO.getPlazoEfectivo());
			fondoResumidoView.setExCiti(fondoResumidoDTO.getExCiti());
			fondoResumidoView.setFondoNuevo(fondoResumidoDTO.isFondoNuevo());
			fondoResumidoView.setSoloEspecie(fondoResumidoDTO.getSoloEspecie());
			if (fondoResumidoDTO.getIdMensajeGastos() != null) {
				fondoResumidoView.setIdMensajeGastos(fondoResumidoDTO.getIdMensajeGastos());
				fondoResumidoView.setTieneGastos(true);
			}
			cuentaTituloView.getFondosSuscriptos().add(fondoResumidoView);
		}
	}

	/**
	 * Dto TO entity.
	 *
	 * @param cuentasDTO
	 *            the cuentas DTO
	 * @return the list
	 */
	protected List<CuentaTituloView> cuentasTituloDtoToView(List<CuentaTituloDTO> cuentasDTO) {
		List<CuentaTituloView> cuentas = new ArrayList<CuentaTituloView>();
		for (CuentaTituloDTO cuentaDTO : cuentasDTO) {
			CuentaTituloView cuentaView = new CuentaTituloView();

			try {
				BeanUtils.copyProperties(cuentaView, cuentaDTO);
			} catch (IllegalAccessException e) {
				LOGGER.error("Error creando DTO", e);
			} catch (InvocationTargetException e) {
				LOGGER.error("Error creando DTO", e);
			}
			cuentas.add(cuentaView);

		}
		return cuentas;
	}

	/**
	 * Formatear importe hash.
	 *
	 * @param importe
	 *            the importe
	 * @return the string
	 */
	protected String formatearImporteHash(String importe) {
		importe = importe.replaceAll("\\$", "").trim();
		importe = importe.replaceAll("us", "").trim();

		return importe;
	}
	
	/**
	 * Obtiene mensaje para Tooltip de rendimiento.
	 *
	 * @return the string
	 */
	protected String obtenerMensajeRendimiento() {
        return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TOOLTIP_RENDIMIENTO)
                .getMensaje();
    }
	
	protected String formatearDecimalesPrecancelableUVA(String monto, int decimales) {
		try {
			return ISBANStringUtils.convertirStrToBigDecimal(monto, decimales).toString();
		} catch (ImporteConvertException e) {
			LOGGER.error("Error al formatear valor", e);
			return "";
		}
	}

	/**
	 * Elimina las cuentas ANSES (Seguridad Social) de la lista de
	 * cuentasdebito.
	 *
	 * @param cuentasAnses
	 *            the cuentas cepo
	 * @param cuentasDebito
	 *            the cuentas debito
	 * @return the list
	 */
	protected List<CuentasAdhesionDebitoView> filtrarCuentasAnses(List<Cuenta> cuentasAnses,
																 List<CuentasAdhesionDebitoView> cuentasDebito) {
		List<CuentasAdhesionDebitoView> cuentasFiltradas = cuentasDebito;
		Iterator<CuentasAdhesionDebitoView> it = cuentasFiltradas.iterator();
		CuentasAdhesionDebitoView cuentaDebito = null;

		while (it.hasNext()) {
			cuentaDebito = it.next();
			for (Cuenta cuenta : cuentasAnses) {
				if (cuenta.getCbu().equals(cuentaDebito.getCbu())) {
					it.remove();
					// Se registra estadistica en base de datos
					estadisticaManager.add(EstadisticasConstants.CONSULTA_API_ACCOUNTS_SEGURIDAD_SOCIAL_FONDOS_FILTRADO,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				}
			}
		}

		return cuentasFiltradas;
	}

	/**
	 * Retorna la lista de cuentas ANSES (Seguridad Social) del cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the cuentas ANSES (Seguridad Social)
	 */
	public List<Cuenta> getCuentasAnses(Cliente cliente) {
		List<Cuenta> cuentasAnses = new ArrayList<Cuenta>();
		String customerId = cliente.getNup();
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (isCuentaAnses(cuenta, customerId)) {
				cuentasAnses.add(cuenta);
			}
		}

		return cuentasAnses;
	}

	/**
	 * Retorna true si es una cuenta ANSES (Seguridad Social).
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if is cuenta ANSES (Seguridad Social)
	 */
	public boolean isCuentaAnses(Cuenta cuenta, String customerId) {
		if (PRODUCTO_ALTAIR_ANSES.equals(cuenta.getProductoAltair()) &&
				SUBPRODUCTO_ALTAIR_ANSES.contains(cuenta.getSubproductoAltair())) {

			if (cuenta.getSubproductoAltair().equals(SUBPRODUCTO_ALTAIR_ANSES.get(0))) {
				AccountsResponseEntity datosCuentas = null;
				try {
					datosCuentas = accountsApi.getAccountsByCustomerId(customerId);
					LOGGER.info("INFO: Se obtienen los datos adicionales de las cuentas del cliente desde accounts-api");
					// Se registra estadistica en base de datos
					estadisticaManager.add(EstadisticasConstants.CONSULTA_API_ACCOUNTS_SEGURIDAD_SOCIAL_FONDOS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

					for (AccountEntity datosCuenta : datosCuentas.getAccounts()) {
						if (datosCuenta.getCbuDetails().getNumber().equals(cuenta.getCbu()) &&
								CONVENIOS_ANSES.contains(datosCuenta.getAgreementNumber())) {
							return true;
						}
					}
				} catch (ApiException e) {
					LOGGER.error("ERROR: Invocacion accounts-api para obtener los datos adicionales de las cuentas del cliente desde account-api.", e);
					// Se registra estadistica en base de datos
					estadisticaManager.add(EstadisticasConstants.CONSULTA_API_ACCOUNTS_SEGURIDAD_SOCIAL_FONDOS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_PARCIAL);
				}
				return false;
			}
			return true;
		}
		return false;
	}
}

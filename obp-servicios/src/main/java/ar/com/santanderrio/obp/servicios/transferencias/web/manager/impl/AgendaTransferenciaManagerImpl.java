/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ar.com.santanderrio.obp.servicios.biocatch.BiocatchManager;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.model.ActivityName;
import ar.com.santanderrio.obp.servicios.biocatch.model.ActivityType;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AgendaDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CabeceraComprobantesEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CuitEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteTransferenciaView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.TipoContratoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.comun.web.view.AyudaView;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.BanelcoDAOImpl;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaRiesgoTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;
import ar.com.santanderrio.obp.servicios.transferencias.bo.AgendaTransferenciaBO;
import ar.com.santanderrio.obp.servicios.transferencias.bo.TransferenciaBO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.Transferencia2;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.AbstractAccionTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.FrecuenciaTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.ModificacionTransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.StopDebitTransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaEjecutadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.AgendaTransferenciaManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.ConfiguracionModificacionViewVisitor;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.ConfiguracionStopDebitViewVisitor;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaAgendadaDetalleViewVisitor;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaAgendadaViewVisitor;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;

/**
 * The Class AgendaTransferenciaManagerImpl.
 */
@Component
public class AgendaTransferenciaManagerImpl extends Transferencia2 implements AgendaTransferenciaManager {

	/** The Constant EJECUTANDO_TRANSFERENCIA_CON_RIESGO_ALTO. */
	private static final String EJECUTANDO_TRANSFERENCIA_CON_RIESGO_ALTO = "Ejecutando transferencia con riesgo Alto.";

	/**
	 * The Constant
	 * OCURRIO_UN_ERROR_AL_VERIFICAR_CUAL_DESAFIO_TIENE_ACTIVO_EL_CLIENTE.
	 */
	private static final String OCURRIO_UN_ERROR_AL_VERIFICAR_CUAL_DESAFIO_TIENE_ACTIVO_EL_CLIENTE = "Ocurrio un error al verificar cual desafio tiene activo el cliente.";

	/** The Constant LA_NUEVA_FECHA_DE_EJECUCION_ES. */
	private static final String LA_NUEVA_FECHA_DE_EJECUCION_ES = "La nueva fecha de ejecucion es: ";

	/** The Constant LA_TRANSFERENCIA_TIENE_RIESGO_ALTO. */
	private static final String LA_TRANSFERENCIA_TIENE_RIESGO_ALTO = "La transferencia tiene riesgo alto. Se modifica la fecha de ejecucion.";

	/**
	 * The Constant
	 * TRANSFERENCIA_DE_RECORDATORIO_NO_INCLUYE_SEGUNDO_FACTOR_DE_DESAFIO.
	 */
	private static final String TRANSFERENCIA_DE_RECORDATORIO_NO_INCLUYE_SEGUNDO_FACTOR_DE_DESAFIO = "Transferencia propia o de Recordatorio, no incluye segundo factor de desafio";

	/**
	 * The Constant
	 * HA_OCURRIDO_UN_ERROR_AL_CONSULTAR_EL_SERVICIO_DE_CONSULTA_DE_CONTRATOS.
	 */
	private static final String HA_OCURRIDO_UN_ERROR_AL_CONSULTAR_EL_SERVICIO_DE_CONSULTA_DE_CONTRATOS = "Ha ocurrido un error al consultar el servicio de consulta de contratos.";

	/** The Constant TIPO_AGENDAMIENTO_PROGRAMADA. */
	private static final String TIPO_AGENDAMIENTO_RECORDATORIO = "RECORDATORIO";

	/** The Constant TIPO_AGENDAMIENTO_AUTOMATICA. */
	private static final String TIPO_AGENDAMIENTO_AUTOMATICO = "AUTOMATICO";
	/**
	 * The Constant HA_OCURRIDO_UN_ERROR_PARCIAL_AL_AGENDAR_LA_TRANSFERENCIA.
	 */
	private static final String HA_OCURRIDO_UN_ERROR_PARCIAL_AL_AGENDAR_LA_TRANSFERENCIA = "Ha ocurrido un error parcial al agendar la transferencia. ";

	/** The Constant LA_TRANSFERENCIA_ES_INVALIDA. */
	private static final String LA_TRANSFERENCIA_ES_INVALIDA = "La transferencia es invalida.";

	/**
	 * The Constant
	 * AGENDANDO_DE_TRANSFERENCIAS_ENTRE_CUENTAS_PROPIAS_NO_SE_REALIZA_AUTENTIFICACION.
	 */
	private static final String AGENDANDO_DE_TRANSFERENCIAS_ENTRE_CUENTAS_PROPIAS_NO_SE_REALIZA_AUTENTIFICACION = "Agendando de transferencias entre cuentas propias... no se realiza autentificacion.";

	/**
	 * The Constant
	 * HA_OCURRIDO_UN_ERROR_INESPERADO_AL_EJECUTAR_UNA_TRANSFERENCIA_AGENDADA.
	 */
	private static final String HA_OCURRIDO_UN_ERROR_INESPERADO_AL_EJECUTAR_UNA_TRANSFERENCIA_AGENDADA = "Ha ocurrido un error inesperado al ejecutar una transferencia agendada.";

	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AgendaTransferenciaManagerImpl.class);

	/** The Constant INICIO_DEL_AGENDAMIENTO. */
	private static final String INICIO_DEL_AGENDAMIENTO = "Inicio del agendamiento de una transferencia...";

	/** The Constant SIMBOLO_CANTIDAD_ERROR. */
	private static final String SIMBOLO_CANTIDAD_ERROR = "-";

	/** The Constant ERROR_AGENDA_TRANSFERENCIA. */
	private static final String ERROR_AGENDA_TRANSFERENCIA = "ERROR AGENDA TRANSFERENCIA";

	/** The Constant FORMATO_FECHA. */
	private static final String FORMATO_FECHA = "dd/MM/yyyy";

	/** The Constant FORMATO_FECHA_DAO. */
	private static final String FORMATO_FECHA_SERVICIO = "yyyyMMdd";

	/** The Constant FORMATO_FECHA_COMPROBANTE. */
	private static final String FORMATO_FECHA_COMPROBANTE = "dd/MM/yyyy HH:mm";

	/** The Constant CAJA_DE_AHORRO. */
	private static final String CAJA_DE_AHORRO = "Caja de ahorro";

	/** The Constant MSJ_SOLICITAR_TRANSFERIR_DESDE_AGENDA. */
	private static final String MSJ_INFO_SOLICITAR_TRANSFERIR_DESDE_AGENDA = "Solicitando realizar una transferencia a traves de la agenda de transferencias...";

	/** The Constant MSJ_ERROR_CUENTA_INVALIDA. */
	private static final String MSJ_ERROR_CUENTA_INVALIDA = "La cuenta no se encuentra en la lista de cuentas disponibles.";

	/**
	 * The Constant
	 * MSJ_INFO_LA_TRANSFERENCIA_RIO_RIO_OTROS_BANCOS_FUE_SATISFACTORIA.
	 */
	private static final String MSJ_INFO_LA_TRANSFERENCIA_RIO_RIO_OTROS_BANCOS_FUE_SATISFACTORIA = "La carga de las transferencias Rio/Rio y Otros bancos fue satisfactoria.";

	/**
	 * The Constant MSJ_INFO_LA_TRANSFERENCIA_RIO_RIO_OTROS_BANCOS_CONTIENE_ERRORES.
	 */
	private static final String MSJ_INFO_LA_TRANSFERENCIA_RIO_RIO_OTROS_BANCOS_CONTIENE_ERRORES = "La carga de las transferencias Rio/Rio y Otros bancos contienen errores.";

	/** The Constant MSJ_INFO_LA_TRANSFERENCIA_OTROS_BANCOS_CONTIENE_ERRORES. */
	private static final String MSJ_INFO_LA_TRANSFERENCIA_OTROS_BANCOS_CONTIENE_ERRORES = "La carga de las transferencias Otros Bancos contiene error.";

	/** The Constant MSJ_INFO_LA_TRANSFERENCIA_RIO_RIO_CONTIENE_ERRORES. */
	private static final String MSJ_INFO_LA_TRANSFERENCIA_RIO_RIO_CONTIENE_ERRORES = "La carga de las transferencias Rio/Rio contienen errores.";

	/** The Constant MSJ_INFO_LA_TRANSFERENCIA_RIO_RIO_CONTIENE_WARNINGS. */
	private static final String MSJ_INFO_LA_TRANSFERENCIA_RIO_RIO_CONTIENE_WARNINGS = "La carga de las transferencias Rio/Rio y Otros contienen warnings";

	/** The Constant MSJ_INFO_LA_TRANSFERENCIA_CONTIENE_WARNINGS. */
	private static final String MSJ_INFO_LA_TRANSFERENCIA_CONTIENE_WARNINGS = "La carga de las transferencias contienen errores";

	/** The Constant MSJ_INFO_EJECUTANDO_STOP_DEBIT. */
	private static final String MSJ_INFO_EJECUTANDO_STOP_DEBIT = "Ejecutando el stop debit...";

	/** The Constant MSJ_INFO_STOP_DEBIT_SATISFACTORIO. */
	private static final String MSJ_INFO_STOP_DEBIT_SATISFACTORIO = "El stop debit se realizo satisfactoriamente.";

	/** The Constant MSJ_INFO_STOP_DEBIT_CON_ERRORES. */
	private static final String MSJ_INFO_STOP_DEBIT_CON_ERRORES = "El stop debit se contiene errores.";

	/** The Constant MSJ_INFO_GRABANDO_ESTADISTICA_COMPROBANTE_STOP_DEBIT. */
	private static final String MSJ_INFO_GRABANDO_ESTADISTICA_COMPROBANTE_STOP_DEBIT = "Grabando estadisticas al ver un comprobante de stop debit...";

	/** The Constant MSG_ERROR_IMPORTE_LIMITE_CVU. */
	private static final String MSG_ERROR_IMPORTE_LIMITE_CVU = "El límite máximo para este tipo de operación es de {0}.";

	/** The Constant CUENTA_CORRIENTE. */
	private static final String CUENTA_CORRIENTE = "Cuenta corriente";

	/** The Constant MSJ_INFO_AGENDAMIENTO_TRANSFERENCIA_CON_EXITO. */
	private static final String MSJ_INFO_AGENDAMIENTO_TRANSFERENCIA_CON_EXITO = "El agendamiento de transferencia fue ejecutada con exito!!.";

	/** The Constant MSJ_INFO_TRANSFERENCIA_CON_WARNING. */
	private static final String MSJ_INFO_AGENDAMIENTO_TRANSFERENCIA_CON_WARNING = "El agendamiento de transferencia contiene warnings.";

	/** The Constant MSJ_INFO_TRANSFERENCIA_CON_ERROR. */
	private static final String MSJ_INFO_AGENDAMIENTO_TRANSFERENCIA_CON_ERROR = "Ha ocurrido un error al ejecutar el agendamiento de transferencia.";

	/** The Constant MSJ_INFO_SOLICITUD_DE_AGENDAMIENTO. */
	private static final String MSJ_INFO_SOLICITUD_DE_AGENDAMIENTO = "INFO SOLICITUD de_Agendamiento. Carga de Ayuda";

	/** The Constant INICIO_MODIFICACION. */
	private static final String INICIO_MODIFICACION = "Inicio de modificacion de un Transferencia Agendada auotomatica...";

	/** The Constant MSJ_INFO_MODIFICACION_TRANSFERENCIA_CON_ERROR. */
	private static final String MSJ_INFO_MODIFICACION_TRANSFERENCIA_CON_ERROR = "Error en modificacion de transferencia agendada.";

	/** The Constant MSJ_INFO_GUARDANDO_HASH_EN_SESION. */
	private static final String MSJ_INFO_GUARDANDO_HASH_EN_SESION = "Se guarda el hash en sesion.";

	/** The Constant MSJ_INFO_CREANDO_HASH_ATRIBUTOS. */
	private static final String MSJ_INFO_CREANDO_HASH_ATRIBUTOS = "Creando hash de los atributos...";

	/** The Constant MSJ_REALIZADA. */
	private static final Object MSJ_REALIZADA = "realizada";

	/** The Constant CUENTA_UNICA. */
	private static final String CUENTA_UNICA = "02";

	/** The Constant TERCEROS_SANTANDER. */
	private static final String TERCEROS_SANTANDER = "TERCEROS SANTANDER";

	/** The Constant IMPORTE. */
	private static final String IMPORTE = "importe";

	/** The agenda transferencia BO. */
	@Autowired
	private AgendaTransferenciaBO agendaTransferenciaBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The mensaje DAO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The mensaje manager. */
	@Autowired
	private MensajeManager mensajeManager;

	/** The autentificacion manager. */
	@Autowired
	private AutentificacionManager autentificacionManager;

	/** The rsa manager. */
	@Autowired
	private RsaManager rsaManager;

	/** The Banelco DAO. */
	@Autowired
	private BanelcoDAOImpl banelcoDAO;
	
	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;

	/** The transferencia BO. */
	@Autowired
	private TransferenciaBO transferenciaBO;

	/** The contrato BO. */
	@Autowired
	private ContratoBO contratoBO;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	/** The agenda destinatarios manager. */
	@Autowired
	private AgendaDestinatarioBO agendaDestinatarioBO;

	/** The cuenta bo. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The legal bo. */
	@Autowired
	private LegalBO legalBO;
	
	@Autowired
	private ClienteManager clienteManager;

	@Autowired
	private BiocatchManager biocatchManager;

	/** The valor desafio para agenda de transferencias. */
	@Value("${TRJCOORD.OPERAINDISTINTO.AGENDARTRANSFERENCIA}")
	private String valorDesafioAgendamientoTransferencias;

	/** The valor desafio para agenda de transferencias. */
	@Value("${TRJCOORD.OPERAINDISTINTO.TRANSFERENCIAS_ND}")
	private String valorDesafioTransferenciasAgendadas;

	/** The ayuda TIT transferencia auto. */
	@Value("${TRANSFERENCIA.TIT.AYUDA.AUTO}")
	private String ayudaTITTransferenciaAuto;

	/** The ayuda MSG transferencia auto. */
	@Value("${TRANSFERENCIA.MSG.AYUDA.AUTO}")
	private String ayudaMSGTransferenciaAuto;

	/** The ayuda TIT transferencia rec. */
	@Value("${TRANSFERENCIA.TIT.AYUDA.REC}")
	private String ayudaTITTransferenciaRec;

	/** The ayuda MSG transferencia rec. */
	@Value("${TRANSFERENCIA.MSG.AYUDA.REC}")
	private String ayudaMSGTransferenciaRec;

	/** The ayuda TIT nueva transferencia fecha. */
	@Value("${TRANSFERENCIA.NUEVA.AYUDA.FECHA.TIT}")
	private String ayudaTITNuevaTransferenciaFecha;

	/** The ayuda template nueva transferencia fecha. */
	@Value("${TRANSFERENCIA.NUEVA.AYUDA.FECHA.TEMP}")
	private String ayudaTemplateNuevaTransferenciaFecha;

	/** The ayuda TIT nueva transferencia desc. */
	@Value("${TRANSFERENCIA.NUEVA.AYUDA.DESC.TIT}")
	private String ayudaTITNuevaTransferenciaDesc;

	/** The ayuda template nueva transferencia desc. */
	@Value("${TRANSFERENCIA.NUEVA.AYUDA.DESC.TEMP}")
	private String ayudaTemplateNuevaTransferenciaDesc;

	/** The valor desafio transferencias. */
	@Value("${TRJCOORD.OPERAINDISTINTO.TRANSFERENCIAS}")
	private String valorDesafioTransferencias;

	/** The trf cvu importe dolares max. */
	@Value("${TRFCVU.IMPORTEDOLARES.MAX}")
	private String trfCVUImporteDolaresMax;

	/** The trf cvu importe pesos max. */
	@Value("${TRFCVU.IMPORTEPESOS.MAX}")
	private String trfCVUImportePesosMax;

	/** The trf cvu dolares habilitado. */
	@Value("${TRFCVU.DOLARES.HABILITADO}")
	private int trfCvuDolaresHabilitado;

	/** The ayuda TIT nueva transferencia limite transferencia. */
	@Value("${TRANSFERENCIA.NUEVA.AYUDA.LIMITE.TIT}")
	private String ayudaTITNuevaTransferenciaLimiteTransferencia;

	/** The ayuda template nueva transferencia limite. */
	@Value("${TRANSFERENCIA.NUEVA.AYUDA.LIMITE.TEMP}")
	private String ayudaTemplateNuevaTransferenciaLimite;

	/** The ayuda TIT plazo acreditación. */
	@Value("${TRANSFERENCIA.NUEVA.AYUDA.PLAZOACREDITACION.TIT}")
	private String ayudaTITNuevaTransferenciaPlazoAcreditacion;

	/** The ayuda template plazo acreditación. */
	@Value("${TRANSFERENCIA.NUEVA.AYUDA.PLAZOACREDITACION.TEMP}")
	private String ayudaTemplateNuevaTransferenciaPlazoAcreditacion;

	@Value("${TRANSFERENCIA.LIMITE.SANTANDER.CUENTAS.PROPIAS.PESOS}")
    private String limiteCuentasPropiasPesos; 
	
	@Value("${TRANSFERENCIA.LIMITE.SANTANDER.CUENTAS.PROPIAS.DOLARES}")
    private String limiteCuentasPropiasDolares; 
	
	@Value("${TRANSFERENCIA.LIMITE.SANTANDER.TERCEROS.PESOS}")
    private String limiteTercerosPesos; 
	
	@Value("${TRANSFERENCIA.LIMITE.SANTANDER.TERCEROS.DOLARES}")
    private String limiteTercerosDolares; 
	
	@Value("${TRANSFERENCIA.LIMITE.SANTANDER.OTROSBANCOS.PESOS}")
    private String limiteOtrosBancosPesos; 
	
	@Value("${TRANSFERENCIA.LIMITE.SANTANDER.OTROSBANCOS.DOLARES}")
    private String limiteOtrosBancosDolares; 

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * AgendaTransferenciaManager#obtenerAgendaTransferencias()
	 */
	@Override
	public Respuesta<AgendaView> obtenerAgendaTransferencias() {
		Cliente cliente = sesionCliente.getCliente();
		if (cliente.getCuentasMonetarias().isEmpty() && cliente.getCuentasBPTransferibles().isEmpty()) {
			return armarRespuestaGrabandoEstadisticasErrorCuentas();
		}
		Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasRioRio = agendaTransferenciaBO
		        .obtenerTransferenciasAgendadasRioRio(cliente);
		Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasOtrosBancos = agendaTransferenciaBO
		        .obtenerTransferenciasAgendadasOtrosBancos(cliente);

		Respuesta<List<TransferenciaAgendadaDTO>> respTransferenciasAgendadasUnificadas = armarRespuestaGrabandoEstadisticas(
		        transferenciasAgendadasRioRio, transferenciasAgendadasOtrosBancos);

		if (respTransferenciasAgendadasUnificadas.getRespuesta() != null
		        && !respTransferenciasAgendadasUnificadas.getRespuesta().isEmpty()) {
			agendaTransferenciaBO.ordenarListaPorFecha(respTransferenciasAgendadasUnificadas.getRespuesta());
		}

		guardarEnSession(respTransferenciasAgendadasUnificadas);
		Respuesta<AgendaView> respuestaAgendaView = obtenerAgendaTransferenciasView(
		        respTransferenciasAgendadasUnificadas);
		validarContratoTransferencias(sesionCliente.getCliente(), respuestaAgendaView);
		return respuestaAgendaView;
	}

	/**
	 * Validar contrato transferencias.
	 *
	 * @param cliente             the cliente
	 * @param respuestaAgendaView the respuesta agenda view
	 */
	public void validarContratoTransferencias(Cliente cliente, Respuesta<AgendaView> respuestaAgendaView) {
		Boolean tieneContrato = contratoBO.tieneContrato(TipoContratoEnum.TRANSFERENCIA, sesionCliente.getCliente());
		if (!tieneContrato) {
			Respuesta<Boolean> respuesta = transferenciaBO
			        .isClienteHabilitadoParaTransferir(sesionCliente.getCliente());
			if (respuesta.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
				LOGGER.info(HA_OCURRIDO_UN_ERROR_AL_CONSULTAR_EL_SERVICIO_DE_CONSULTA_DE_CONTRATOS);
				contratoBO.eliminarContrato(TipoContratoEnum.TRANSFERENCIA, sesionCliente.getCliente());
				respuestaAgendaView.setItemMensajeRespuesta(respuesta.getItemsMensajeRespuesta());
				respuestaAgendaView.setEstadoRespuesta(respuesta.getEstadoRespuesta());
			} else if (respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				if (Boolean.TRUE.equals(respuesta.getRespuesta())) {
					contratoBO.agregarContrato(TipoContratoEnum.TRANSFERENCIA, sesionCliente.getCliente());
				} else if (Boolean.FALSE.equals(respuesta.getRespuesta())) {
					adherirContratoTransferencia(cliente);
				}
			}
		}
	}

	/**
	 * Adherir contrato transferencia.
	 *
	 * @param cliente the cliente
	 */
	private void adherirContratoTransferencia(Cliente cliente) {
		Respuesta<Boolean> respuestaAdhesion = transferenciaBO.adherirContratoTransferencia(cliente);
		if (respuestaAdhesion.getRespuesta().equals(Boolean.TRUE)) {
			Respuesta<Boolean> respuesta = transferenciaBO
			        .isClienteHabilitadoParaTransferir(sesionCliente.getCliente());
			if (Boolean.TRUE.equals(respuesta.getRespuesta())) {
				contratoBO.agregarContrato(TipoContratoEnum.TRANSFERENCIA, cliente);
			} else {
				contratoBO.eliminarContrato(TipoContratoEnum.TRANSFERENCIA, sesionCliente.getCliente());
			}
		} else {
			contratoBO.eliminarContrato(TipoContratoEnum.TRANSFERENCIA, sesionCliente.getCliente());
		}
	}

	/**
	 * Sets the respuesta transferencias agendadas.
	 *
	 * @param transferenciasAgendadasRioRio      the transferencias agendadas rio
	 *                                           rio
	 * @param transferenciasAgendadasOtrosBancos the transferencias agendadas otros
	 *                                           bancos
	 * @return the estado respuesta grabando estadisticas
	 */
	private Respuesta<List<TransferenciaAgendadaDTO>> armarRespuestaGrabandoEstadisticas(
	        Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasRioRio,
	        Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasOtrosBancos) {
		Respuesta<List<TransferenciaAgendadaDTO>> respTransferenciasAgendadasUnificadas = new Respuesta<List<TransferenciaAgendadaDTO>>();
		List<TransferenciaAgendadaDTO> transferenciasAgendadasUnificadas = new ArrayList<TransferenciaAgendadaDTO>();
		if (estadoTodoOK(transferenciasAgendadasRioRio, transferenciasAgendadasOtrosBancos)) {
			armarRespuetaTodasTransferenciasOK(transferenciasAgendadasRioRio, transferenciasAgendadasOtrosBancos,
			        respTransferenciasAgendadasUnificadas, transferenciasAgendadasUnificadas);
		} else if (estadoTodoError(transferenciasAgendadasRioRio, transferenciasAgendadasOtrosBancos)) {
			armarRespuestaTodasTransferenciasERROR(respTransferenciasAgendadasUnificadas);
		} else if (EstadoRespuesta.OK.equals(transferenciasAgendadasRioRio.getEstadoRespuesta())
		        && EstadoRespuesta.ERROR.equals(transferenciasAgendadasOtrosBancos.getEstadoRespuesta())) {
			armarRespuestaTodasErrorParcialOtrosBancos(transferenciasAgendadasRioRio,
			        transferenciasAgendadasOtrosBancos, respTransferenciasAgendadasUnificadas,
			        transferenciasAgendadasUnificadas);
		} else if ((EstadoRespuesta.ERROR.equals(transferenciasAgendadasRioRio.getEstadoRespuesta()))
		        && EstadoRespuesta.OK.equals(transferenciasAgendadasOtrosBancos.getEstadoRespuesta())) {
			armarRespuestaTodasErrorParcialRioRio(transferenciasAgendadasRioRio, transferenciasAgendadasOtrosBancos,
			        respTransferenciasAgendadasUnificadas, transferenciasAgendadasUnificadas);
		} else if (estadoTodoWARN(transferenciasAgendadasRioRio, transferenciasAgendadasOtrosBancos)) {
			armarRespuestaTodasErrorParcialOtrosBancosRioRio(transferenciasAgendadasRioRio,
			        respTransferenciasAgendadasUnificadas);
		} else {
			respTransferenciasAgendadasUnificadas.setEstadoRespuesta(EstadoRespuesta.ERROR);
			LOGGER.error(MSJ_INFO_LA_TRANSFERENCIA_CONTIENE_WARNINGS);
		}
		return respTransferenciasAgendadasUnificadas;
	}

	/**
	 * Armar respuesta grabando estadisticas error cuentas.
	 *
	 * @return the respuesta
	 */
	private Respuesta<AgendaView> armarRespuestaGrabandoEstadisticasErrorCuentas() {
		estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_CABECERA_TRANSFERENCIA,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);

		Respuesta<AgendaView> respuestaError = new Respuesta<AgendaView>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaError.add(getErrorConMensajeYtipo(CodigoMensajeConstantes.INICIO_TRANSFERENCIAS_ERROR_CUENTAS,
		        TipoError.SIN_CUENTAS_VALIDAS));
		AgendaView agendaView = new AgendaView();
		agendaView.setCantidadAutomaticas("0");
		agendaView.setCantidadRecordatorios("0");
		respuestaError.setRespuesta(agendaView);
		return respuestaError;
	}

	/**
	 * Armar respuesta todas error parcial otros bancos rio rio.
	 *
	 * @param transferenciasAgendadasRioRio         the transferencias agendadas rio
	 *                                              rio
	 * @param respTransferenciasAgendadasUnificadas the resp transferencias
	 *                                              agendadas unificadas
	 */
	private void armarRespuestaTodasErrorParcialOtrosBancosRioRio(
	        Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasRioRio,
	        Respuesta<List<TransferenciaAgendadaDTO>> respTransferenciasAgendadasUnificadas) {
		LOGGER.warn(MSJ_INFO_LA_TRANSFERENCIA_RIO_RIO_CONTIENE_WARNINGS);
		respTransferenciasAgendadasUnificadas.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respTransferenciasAgendadasUnificadas
		        .setItemMensajeRespuesta(transferenciasAgendadasRioRio.getItemsMensajeRespuesta());
		respTransferenciasAgendadasUnificadas.setRespuestaVacia(true);
		estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_GRILLA_TRANSFERENCIA,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/**
	 * Armar respuesta todas error parcial rio rio.
	 *
	 * @param transferenciasAgendadasRioRio         the transferencias agendadas rio
	 *                                              rio
	 * @param transferenciasAgendadasOtrosBancos    the transferencias agendadas
	 *                                              otros bancos
	 * @param respTransferenciasAgendadasUnificadas the resp transferencias
	 *                                              agendadas unificadas
	 * @param transferenciasAgendadasUnificadas     the transferencias agendadas
	 *                                              unificadas
	 */
	private void armarRespuestaTodasErrorParcialRioRio(
	        Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasRioRio,
	        Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasOtrosBancos,
	        Respuesta<List<TransferenciaAgendadaDTO>> respTransferenciasAgendadasUnificadas,
	        List<TransferenciaAgendadaDTO> transferenciasAgendadasUnificadas) {
		LOGGER.warn(MSJ_INFO_LA_TRANSFERENCIA_RIO_RIO_CONTIENE_ERRORES);
		respTransferenciasAgendadasUnificadas.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respTransferenciasAgendadasUnificadas
		        .setItemMensajeRespuesta(transferenciasAgendadasRioRio.getItemsMensajeRespuesta());
		estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_GRILLA_TRANSFERENCIA,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
		transferenciasAgendadasUnificadas.addAll(transferenciasAgendadasOtrosBancos.getRespuesta());
		respTransferenciasAgendadasUnificadas.setRespuesta(transferenciasAgendadasUnificadas);
	}

	/**
	 * Armar respuesta todas error parcial otros bancos.
	 *
	 * @param transferenciasAgendadasRioRio         the transferencias agendadas rio
	 *                                              rio
	 * @param transferenciasAgendadasOtrosBancos    the transferencias agendadas
	 *                                              otros bancos
	 * @param respTransferenciasAgendadasUnificadas the resp transferencias
	 *                                              agendadas unificadas
	 * @param transferenciasAgendadasUnificadas     the transferencias agendadas
	 *                                              unificadas
	 */
	private void armarRespuestaTodasErrorParcialOtrosBancos(
	        Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasRioRio,
	        Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasOtrosBancos,
	        Respuesta<List<TransferenciaAgendadaDTO>> respTransferenciasAgendadasUnificadas,
	        List<TransferenciaAgendadaDTO> transferenciasAgendadasUnificadas) {
		LOGGER.warn(MSJ_INFO_LA_TRANSFERENCIA_OTROS_BANCOS_CONTIENE_ERRORES);
		respTransferenciasAgendadasUnificadas.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respTransferenciasAgendadasUnificadas
		        .setItemMensajeRespuesta(transferenciasAgendadasOtrosBancos.getItemsMensajeRespuesta());
		estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_GRILLA_TRANSFERENCIA,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
		transferenciasAgendadasUnificadas.addAll(transferenciasAgendadasRioRio.getRespuesta());
		respTransferenciasAgendadasUnificadas.setRespuesta(transferenciasAgendadasUnificadas);
	}

	/**
	 * Armar respuesta todas transferencias ERROR.
	 *
	 * @param respTransferenciasAgendadasUnificadas the resp transferencias
	 *                                              agendadas unificadas
	 */
	private void armarRespuestaTodasTransferenciasERROR(
	        Respuesta<List<TransferenciaAgendadaDTO>> respTransferenciasAgendadasUnificadas) {
		LOGGER.error(MSJ_INFO_LA_TRANSFERENCIA_RIO_RIO_OTROS_BANCOS_CONTIENE_ERRORES);
		respTransferenciasAgendadasUnificadas.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respTransferenciasAgendadasUnificadas.add(getErrorConMensajeYtipo(
		        CodigoMensajeConstantes.CODIGO_ERROR_AGENDA_TRANSFERENCIAS, TipoError.ERROR_AGENDA_TRANSFERENCIAS));
		grabarEstadisticaErrorCargaGrilla();
	}

	/**
	 * Armar respueta todas transferencias OK.
	 *
	 * @param transferenciasAgendadasRioRio         the transferencias agendadas rio
	 *                                              rio
	 * @param transferenciasAgendadasOtrosBancos    the transferencias agendadas
	 *                                              otros bancos
	 * @param respTransferenciasAgendadasUnificadas the resp transferencias
	 *                                              agendadas unificadas
	 * @param transferenciasAgendadasUnificadas     the transferencias agendadas
	 *                                              unificadas
	 */
	private void armarRespuetaTodasTransferenciasOK(
	        Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasRioRio,
	        Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasOtrosBancos,
	        Respuesta<List<TransferenciaAgendadaDTO>> respTransferenciasAgendadasUnificadas,
	        List<TransferenciaAgendadaDTO> transferenciasAgendadasUnificadas) {
		LOGGER.info(MSJ_INFO_LA_TRANSFERENCIA_RIO_RIO_OTROS_BANCOS_FUE_SATISFACTORIA);
		transferenciasAgendadasUnificadas.addAll(transferenciasAgendadasRioRio.getRespuesta());
		transferenciasAgendadasUnificadas.addAll(transferenciasAgendadasOtrosBancos.getRespuesta());
		grabarEstadisticaOkCargaGrilla();
		respTransferenciasAgendadasUnificadas.setEstadoRespuesta(EstadoRespuesta.OK);
		respTransferenciasAgendadasUnificadas.setRespuesta(transferenciasAgendadasUnificadas);
	}

	/**
	 * Estado WARN.
	 *
	 * @param transferenciasAgendadasRioRio      the transferencias agendadas rio
	 *                                           rio
	 * @param transferenciasAgendadasOtrosBancos the transferencias agendadas otros
	 *                                           bancos
	 * @return true, if successful
	 */
	protected boolean estadoTodoWARN(Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasRioRio,
	        Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasOtrosBancos) {
		return EstadoRespuesta.WARNING.equals(transferenciasAgendadasRioRio.getEstadoRespuesta())
		        && EstadoRespuesta.WARNING.equals(transferenciasAgendadasOtrosBancos.getEstadoRespuesta());
	}

	/**
	 * Estado error.
	 *
	 * @param transferenciasAgendadasRioRio      the transferencias agendadas rio
	 *                                           rio
	 * @param transferenciasAgendadasOtrosBancos the transferencias agendadas otros
	 *                                           bancos
	 * @return true, if successful
	 */
	protected boolean estadoTodoError(Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasRioRio,
	        Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasOtrosBancos) {
		return EstadoRespuesta.ERROR.equals(transferenciasAgendadasRioRio.getEstadoRespuesta())
		        && EstadoRespuesta.ERROR.equals(transferenciasAgendadasOtrosBancos.getEstadoRespuesta());
	}

	/**
	 * Estado OK.
	 *
	 * @param transferenciasAgendadasRioRio      the transferencias agendadas rio
	 *                                           rio
	 * @param transferenciasAgendadasOtrosBancos the transferencias agendadas otros
	 *                                           bancos
	 * @return true, if successful
	 */
	protected boolean estadoTodoOK(Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasRioRio,
	        Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadasOtrosBancos) {
		return EstadoRespuesta.OK.equals(transferenciasAgendadasRioRio.getEstadoRespuesta())
		        && EstadoRespuesta.OK.equals(transferenciasAgendadasOtrosBancos.getEstadoRespuesta())
		        || EstadoRespuesta.OK.equals(transferenciasAgendadasRioRio.getEstadoRespuesta())
		                && EstadoRespuesta.WARNING.equals(transferenciasAgendadasOtrosBancos.getEstadoRespuesta())
		        || EstadoRespuesta.WARNING.equals(transferenciasAgendadasRioRio.getEstadoRespuesta())
		                && EstadoRespuesta.OK.equals(transferenciasAgendadasOtrosBancos.getEstadoRespuesta());
	}

	/**
	 * Grabar estadistica error de carga de la grilla.
	 */
	private void grabarEstadisticaErrorCargaGrilla() {
		estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_CABECERA_TRANSFERENCIA,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_GRILLA_TRANSFERENCIA,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	/**
	 * Grabar estadistica ok de carga de la grilla.
	 */
	private void grabarEstadisticaOkCargaGrilla() {
		estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_CABECERA_TRANSFERENCIA,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_GRILLA_TRANSFERENCIA,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/**
	 * Gets the error con mensaje ytipo.
	 *
	 * @param codigoMensaje the codigo mensaje
	 * @param tipo          the tipo
	 * @return the error con mensaje ytipo
	 */
	private ItemMensajeRespuesta getErrorConMensajeYtipo(String codigoMensaje, TipoError tipo) {
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje(mensajeManager.obtenerMensajePorCodigo(codigoMensaje).getMensaje());
		itemMensajeRespuesta.setTipoError(tipo.getDescripcion());
		return itemMensajeRespuesta;
	}

	/**
	 * Guardar en session.
	 *
	 * @param transferenciasAgendadas the transferencias agendadas
	 */
	private void guardarEnSession(Respuesta<List<TransferenciaAgendadaDTO>> transferenciasAgendadas) {
		if (EstadoRespuesta.OK.equals(transferenciasAgendadas.getEstadoRespuesta())) {
			sesionParametros.setTransferenciasAgendadas(transferenciasAgendadas.getRespuesta());
		}
	}

	/**
	 * Obtener agenda transferencias view.
	 *
	 * @version 2. Manuel Vargas. Estadistica de cabecera 13421.
	 * @param respuestaTransferenciasAgendadas the respuesta transferencias
	 *                                         agendadas
	 * @return the respuesta
	 */
	private Respuesta<AgendaView> obtenerAgendaTransferenciasView(
	        Respuesta<List<TransferenciaAgendadaDTO>> respuestaTransferenciasAgendadas) {

		Respuesta<AgendaView> respuestaAgendaTransferenciasView = Respuesta.copy(AgendaView.class,
		        respuestaTransferenciasAgendadas);

		AgendaView agendaTransferenciasView = new AgendaView();
		if (!hasErrorAgenda(respuestaAgendaTransferenciasView)) {
			List<TransferenciaAgendadaDTO> transferenciasAgendadas = respuestaTransferenciasAgendadas.getRespuesta();

			int cantidadAutomaticas = 0;
			int cantidadRecordatorios = 0;

			if (transferenciasAgendadas != null && !transferenciasAgendadas.isEmpty()) {
				boolean isMobile = sesionParametros.getRegistroSession().isMobile();
				TransferenciaAgendadaViewVisitor visitor = new TransferenciaAgendadaViewVisitor(isMobile);
				List<TransferenciaAgendadaView> transferenciasAgendadasView = new ArrayList<TransferenciaAgendadaView>();

				for (TransferenciaAgendadaDTO transferenciaAgendadaDTO : transferenciasAgendadas) {
					transferenciaAgendadaDTO.accept(visitor);
					transferenciasAgendadasView.add(visitor.getView());
				}
				agendaTransferenciasView.setTransferenciasAgendadas(transferenciasAgendadasView);
				cantidadAutomaticas = visitor.getCantidadAutomaticas();
				cantidadRecordatorios = visitor.getCantidadRecordatorios();
			}

			agendaTransferenciasView.setCantidadAutomaticas(String.valueOf(cantidadAutomaticas));
			agendaTransferenciasView.setCantidadRecordatorios(String.valueOf(cantidadRecordatorios));

		} else {
			agendaTransferenciasView.setCantidadAutomaticas(SIMBOLO_CANTIDAD_ERROR);
			agendaTransferenciasView.setCantidadRecordatorios(SIMBOLO_CANTIDAD_ERROR);
		}
		respuestaAgendaTransferenciasView.setRespuesta(agendaTransferenciasView);

		// visitor get count
		return respuestaAgendaTransferenciasView;
	}

	/**
	 * Checks for error agenda.
	 *
	 * @param respuestaAgendaTransferenciasView the respuesta agenda transferencias
	 *                                          view
	 * @return true, if successful
	 */
	private boolean hasErrorAgenda(Respuesta<AgendaView> respuestaAgendaTransferenciasView) {
		boolean hasError = false;
		if (EstadoRespuesta.OK.equals(respuestaAgendaTransferenciasView.getEstadoRespuesta())) {
			hasError = false;
		} else if (EstadoRespuesta.WARNING.equals(respuestaAgendaTransferenciasView.getEstadoRespuesta())) {
			List<ItemMensajeRespuesta> items = respuestaAgendaTransferenciasView.getItemsMensajeRespuesta();
			for (ItemMensajeRespuesta itemMensajeRespuesta : items) {
				if (itemMensajeRespuesta.getTipoError().equals(TipoError.ERROR_AGENDA_TRANSFERENCIAS.toString())) {
					hasError = true;
				}
			}
		} else {
			hasError = true;
		}
		return hasError;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * AgendaTransferenciaManager#obtenerAgendaTransferenciaDetalle(ar.com.
	 * santanderrio.obp.servicios.cuentas.web.view.AgendaTransferencia)
	 */
	@Override
	public Respuesta<TransferenciaAgendadaDetalleView> obtenerAgendaTransferenciaDetalle(
	        TransferenciaAgendadaView transferenciaAgendadaView) {
		final Long clave = Long.parseLong(transferenciaAgendadaView.getId());
		Respuesta<TransferenciaAgendadaDetalleView> respuesta = new Respuesta<TransferenciaAgendadaDetalleView>();
		TransferenciaAgendadaDTO transferenciaAgendadaDTO = getAgendaTransferenciaSession(clave);
		TransferenciaAgendadaDetalleViewVisitor visitor = new TransferenciaAgendadaDetalleViewVisitor(false);
		transferenciaAgendadaDTO.accept(visitor);
		TransferenciaAgendadaDetalleView agendaTransferenciaDetalleView = visitor.getView();
		formatearTipoCuentas(agendaTransferenciaDetalleView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(agendaTransferenciaDetalleView);
		if (!transferenciaAgendadaDTO.isHaciaOtroBanco()) {
			grabarEstadisticaDetalle(agendaTransferenciaDetalleView,
			        EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DETALLE_TRANSFERENCIA_REC_RIORIO,
			        EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DETALLE_TRANSFERENCIA_ASREC_RIORIO,
			        EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DETALLE_TRANSFERENCIA_AREC_RIORIO);
		} else {
			grabarEstadisticaDetalle(agendaTransferenciaDetalleView,
			        EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DETALLE_TRANSFERENCIA_REC_OB,
			        EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DETALLE_TRANSFERENCIA_ASREC_OB,
			        EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DETALLE_TRANSFERENCIA_AREC_OB);
		}
		return respuesta;
	}

	/**
	 * Formatear tipo cuentas.
	 *
	 * @param agendaTransferenciaDetalleView the agenda transferencia detalle view
	 */
	private void formatearTipoCuentas(TransferenciaAgendadaDetalleView agendaTransferenciaDetalleView) {
		if (agendaTransferenciaDetalleView.getOrigenTipo().startsWith(CAJA_DE_AHORRO)) {
			agendaTransferenciaDetalleView.setOrigenTipo(CAJA_DE_AHORRO);
		} else if (agendaTransferenciaDetalleView.getOrigenTipo().startsWith(CUENTA_CORRIENTE)) {
			agendaTransferenciaDetalleView.setOrigenTipo(CUENTA_CORRIENTE);
		}

		if (agendaTransferenciaDetalleView.getDestinoTipo().startsWith(CAJA_DE_AHORRO)) {
			agendaTransferenciaDetalleView.setDestinoTipo(CAJA_DE_AHORRO);
		} else if (agendaTransferenciaDetalleView.getDestinoTipo().startsWith(CUENTA_CORRIENTE)) {
			agendaTransferenciaDetalleView.setDestinoTipo(CUENTA_CORRIENTE);
		}
	}

	/**
	 * Grabar estadistica detalle.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @param estadisticaRecordatorio          the estadistica recordatorio
	 * @param estadisticaProgramada            the estadistica programada
	 * @param estadisticaRecurrente            the estadistica recurrente
	 */
	private void grabarEstadisticaDetalle(TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView,
	        String estadisticaRecordatorio, String estadisticaProgramada, String estadisticaRecurrente) {
		LOGGER.info("Grabando estadisticas de detalle.");
		if (null != transferenciaAgendadaDetalleView.getIsRecordatorio()
		        && transferenciaAgendadaDetalleView.getIsRecordatorio()) {
			estadisticaManager.add(estadisticaRecordatorio, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else if (null != transferenciaAgendadaDetalleView.getIsProgramada()
		        && transferenciaAgendadaDetalleView.getIsProgramada()) {
			estadisticaManager.add(estadisticaProgramada, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else if (null != transferenciaAgendadaDetalleView.getIsRecurrente()
		        && transferenciaAgendadaDetalleView.getIsRecurrente()) {
			estadisticaManager.add(estadisticaRecurrente, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
	}

	/**
	 * Obtener configuracion modificacion.
	 *
	 * @param transferenciaAgendadaView the transferencia agendada view
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * AgendaTransferenciaManager#obtenerConfiguracionModificacion(ar.com.
	 * santanderrio.obp.servicios.cuentas.web.view.AgendaTransferencia)
	 */
	@Override
	public Respuesta<TransferenciaAgendadaDetalleView> solicitarModificacionTransferencia(
	        TransferenciaAgendadaView transferenciaAgendadaView) {
		final Long clave = Long.parseLong(transferenciaAgendadaView.getId());

		Respuesta<TransferenciaAgendadaDetalleView> respuesta = new Respuesta<TransferenciaAgendadaDetalleView>();
		ModificacionTransferenciaAgendadaDTO modificacion = new ModificacionTransferenciaAgendadaDTO();
		TransferenciaAgendadaDTO transferenciaAgendadaDTO = getAgendaTransferenciaSession(clave);

		ConfiguracionModificacionViewVisitor visitor = new ConfiguracionModificacionViewVisitor(false);

		transferenciaAgendadaDTO.accept(visitor);
		TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView = visitor.getView();

		transferenciaAgendadaDetalleView.generarNuevoIdSesion();

		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(transferenciaAgendadaDetalleView);
		modificacion.setTransferenciaAgendadaDTO(transferenciaAgendadaDTO);

		sesionParametros.setAccionTransferenciaAgendada(modificacion);
		estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_CONFIGURACION_MODIFICACION,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * AgendaTransferenciaManager#obtenerConfiguracionStopDebit(ar.com.
	 * santanderrio.obp.servicios.cuentas.web.view.AgendaTransferencia)
	 */
	@Override
	public Respuesta<ConfiguracionStopDebitView> solicitarStopDebit(TransferenciaAgendadaView agendaTransferenciaView) {
		final Long clave = Long.parseLong(agendaTransferenciaView.getId());

		Respuesta<ConfiguracionStopDebitView> respuesta = new Respuesta<ConfiguracionStopDebitView>();
		TransferenciaAgendadaDTO transferenciaAgendadaDTO;
		if (sesionParametros.getAccionTransferenciaAgendada() == null || !agendaTransferenciaView.getId()
		        .equals(sesionParametros.getAccionTransferenciaAgendada().getId().toString())) {
			StopDebitTransferenciaAgendadaDTO stopDebit = new StopDebitTransferenciaAgendadaDTO();
			transferenciaAgendadaDTO = getAgendaTransferenciaSession(clave);

			stopDebit.setTransferenciaAgendadaDTO(transferenciaAgendadaDTO);
			stopDebit.setIdProceso(UUID.randomUUID().toString());
			sesionParametros.setAccionTransferenciaAgendada(stopDebit);
		} else {
			transferenciaAgendadaDTO = sesionParametros.getAccionTransferenciaAgendada().getTransferenciaAgendadaDTO();
		}

		ConfiguracionStopDebitViewVisitor visitor = new ConfiguracionStopDebitViewVisitor(false);
		transferenciaAgendadaDTO.accept(visitor);

		ConfiguracionStopDebitView configuracionStopDebitView = visitor.getView();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(configuracionStopDebitView);

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * AgendaTransferenciaManager#stopDebit(ar.com.santanderrio.obp.servicios.
	 * cuentas.web.view.AgendaTransferencia)
	 */
	@Override
	public Respuesta<FeedbackStopDebitView> ejecutarStopDebit(TransferenciaAgendadaView agendaTransferenciaView) {

		AbstractAccionTransferenciaAgendada<?> accion = sesionParametros.getAccionTransferenciaAgendada();
		StopDebitTransferenciaAgendadaDTO stopDebitTransferenciaAgendada = (StopDebitTransferenciaAgendadaDTO) accion;
		LOGGER.info(MSJ_INFO_EJECUTANDO_STOP_DEBIT);
		Mensaje mensajeError = mensajeBO
		        .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_STOP_DEBIT_AGENDA_TRANSFERENCIAS);
		String mensajeErrorFormateado = MessageFormat.format(mensajeError.getMensaje(),
		        stopDebitTransferenciaAgendada.getTransferenciaAgendadaDTO().getDestinatario().getNombre());
		try {

			inicializarContadorIntentosStopDebit(agendaTransferenciaView, mensajeErrorFormateado);
			boolean isRioRio = !stopDebitTransferenciaAgendada.getTransferenciaAgendadaDTO().isHaciaOtroBanco();
			Cliente cliente = sesionCliente.getCliente();
			Respuesta<FeedbackStopDebitView> respuesta = agendaTransferenciaBO.stopDebit(cliente,
			        stopDebitTransferenciaAgendada.getTransferenciaAgendadaDTO());
			sesionParametros.setAccionTransferenciaAgendada(stopDebitTransferenciaAgendada);
			if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
				return armarRespuestaOKyGrabarEstadisticas(stopDebitTransferenciaAgendada, isRioRio, respuesta);
			} else {
				return armarRespuestaWARNINGyGrabarEstadisticas(agendaTransferenciaView, stopDebitTransferenciaAgendada,
				        isRioRio, respuesta);
			}
		} catch (NullPointerException e) {
			return armarRespuestaERRORyGrabarEstadisticas(mensajeErrorFormateado, e);
		}
	}

	/**
	 * Armar respuesta WARNIN gy grabar estadisticas.
	 *
	 * @param agendaTransferenciaView        the agenda transferencia view
	 * @param stopDebitTransferenciaAgendada the stop debit transferencia agendada
	 * @param isRioRio                       the is rio rio
	 * @param respuesta                      the respuesta
	 * @return the respuesta
	 */
	private Respuesta<FeedbackStopDebitView> armarRespuestaWARNINGyGrabarEstadisticas(
	        TransferenciaAgendadaView agendaTransferenciaView,
	        StopDebitTransferenciaAgendadaDTO stopDebitTransferenciaAgendada, boolean isRioRio,
	        Respuesta<FeedbackStopDebitView> respuesta) {
		LOGGER.info(MSJ_INFO_STOP_DEBIT_CON_ERRORES);
		grabarEstadisticasStopDebit(isRioRio, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
		        stopDebitTransferenciaAgendada);
		respuesta = respuestaFactory.crearRespuestaError(FeedbackStopDebitView.class,
		        respuesta.getItemsMensajeRespuesta());
		return sesionParametros.getContador().excedeReintentos(agendaTransferenciaView.getIdSesion(), respuesta);
	}

	/**
	 * Armar respuesta O ky grabar estadisticas.
	 *
	 * @param stopDebitTransferenciaAgendada the stop debit transferencia agendada
	 * @param isRioRio                       the is rio rio
	 * @param respuesta                      the respuesta
	 * @return the respuesta
	 */
	private Respuesta<FeedbackStopDebitView> armarRespuestaOKyGrabarEstadisticas(
	        StopDebitTransferenciaAgendadaDTO stopDebitTransferenciaAgendada, boolean isRioRio,
	        Respuesta<FeedbackStopDebitView> respuesta) {
		LOGGER.info(MSJ_INFO_STOP_DEBIT_SATISFACTORIO);
		grabarEstadisticasStopDebit(isRioRio, EstadisticasConstants.CODIGO_ESTADISTICAS_OK,
		        stopDebitTransferenciaAgendada);
		return respuesta;
	}

	/**
	 * Armar respuesta ERRO ry grabar estadisticas.
	 *
	 * @param mensajeErrorFormateado the mensaje error formateado
	 * @param e                      the e
	 * @return the respuesta
	 */
	private Respuesta<FeedbackStopDebitView> armarRespuestaERRORyGrabarEstadisticas(String mensajeErrorFormateado,
	        NullPointerException e) {
		LOGGER.error("Capturo el nullpointer.", e);
		estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_A_RECURRENCIA_ESTADISTICAS_STOP_DEBIT_OB,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		Respuesta<FeedbackStopDebitView> respuesta = new Respuesta<FeedbackStopDebitView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setTipoError(TipoError.ERROR_AGENDA_TRANSFERENCIAS.getDescripcion());
		itemMensajeRespuesta.setMensaje(mensajeErrorFormateado);
		respuesta.add(itemMensajeRespuesta);
		return respuesta;
	}

	/**
	 * Inicializar contador intentos stop debit.
	 *
	 * @param agendaTransferenciaView the agenda transferencia view
	 * @param mensajeErrorFormateado  the mensaje error formateado
	 */
	private void inicializarContadorIntentosStopDebit(TransferenciaAgendadaView agendaTransferenciaView,
	        String mensajeErrorFormateado) {
		if (sesionParametros.getContador() == null) {
			ContadorIntentos contador = new ContadorIntentos();
			contador.setIdView(agendaTransferenciaView.getIdSesion(), 2, mensajeErrorFormateado);
			sesionParametros.setContador(contador);
		} else {
			sesionParametros.getContador().setIdView(agendaTransferenciaView.getIdSesion(), 2, mensajeErrorFormateado);
		}
	}

	/**
	 * Grabar estadisticas stop debit.
	 *
	 * @param isRioRio                          the is rio rio
	 * @param estado                            the estado
	 * @param stopDebitTransferenciaAgendadaDTO the stop debit transferencia
	 *                                          agendada DTO
	 */
	private void grabarEstadisticasStopDebit(boolean isRioRio, String estado,
	        StopDebitTransferenciaAgendadaDTO stopDebitTransferenciaAgendadaDTO) {
		if (EstadisticasConstants.CODIGO_ESTADISTICAS_OK.equalsIgnoreCase(estado)) {
			if (isRioRio) {
				loguearEstadistica(stopDebitTransferenciaAgendadaDTO,
				        EstadisticasConstants.CODIGO_TRANSACCION_A_RECURRENCIA_ESTADISTICAS_STOP_DEBIT_RIORIO, estado);
			} else {
				loguearEstadistica(stopDebitTransferenciaAgendadaDTO,
				        EstadisticasConstants.CODIGO_TRANSACCION_A_RECURRENCIA_ESTADISTICAS_STOP_DEBIT_OB, estado);
			}
		} else {
			loguearEstadistica(stopDebitTransferenciaAgendadaDTO,
			        EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_COMPROBANTE_STOP_DEBIT_RIORIO, estado);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * AgendaTransferenciaManager#eliminar(ar.com.santanderrio.obp.servicios.
	 * cuentas.web.view.AgendaTransferencia)
	 */
	@Override
	public Respuesta<FeedbackEliminarView> eliminarTransferencia(TransferenciaAgendadaView agendaTransferenciaView) {

		inicializarContadorDeIntentos(agendaTransferenciaView);
		final Long clave = Long.parseLong(agendaTransferenciaView.getId());
		TransferenciaAgendadaDTO transferenciaAgendadaDTO = getAgendaTransferenciaSession(clave);

		Cliente cliente = sesionCliente.getCliente();
		Respuesta<FeedbackEliminarView> respuestaBO = agendaTransferenciaBO.eliminarTransferencia(cliente,
		        transferenciaAgendadaDTO);

		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ELIMINAR_TRANSFERENCIA,
			        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaBO;
		} else {
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ELIMINAR_TRANSFERENCIA,
			        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return sesionParametros.getContador().excedeReintentos(agendaTransferenciaView.getIdSesion(), respuestaBO);
		}
	}

	/**
	 * Inicializar contador de intentos.
	 *
	 * @param agendaTransferenciaView the agenda transferencia view
	 */
	private void inicializarContadorDeIntentos(TransferenciaAgendadaView agendaTransferenciaView) {
		Mensaje mensajeError = mensajeManager
		        .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_ELIMINAR_TRANSFERENCIA);
		if (sesionParametros.getContador() == null) {
			ContadorIntentos contador = new ContadorIntentos();
			contador.setIdView(agendaTransferenciaView.getIdSesion(), 2, mensajeError.getMensaje());
			sesionParametros.setContador(contador);
		} else {
			sesionParametros.getContador().setIdView(agendaTransferenciaView.getIdSesion(), 2,
			        mensajeError.getMensaje());
		}
	}

	/**
	 * Gets the agenda transferencia session.
	 *
	 * @param id the id
	 * @return the agenda transferencia session
	 */
	private TransferenciaAgendadaDTO getAgendaTransferenciaSession(final Long id) {
		TransferenciaAgendadaDTO transferenciaAgendadaDTO = null;
		List<TransferenciaAgendadaDTO> lista = sesionParametros.getTransferenciasAgendadas();
		if (lista != null && !lista.isEmpty()) {
			Object found = CollectionUtils.find(lista, new Predicate() {
				public boolean evaluate(Object object) {
					TransferenciaAgendadaDTO transferenciaAgendadaDTO = (TransferenciaAgendadaDTO) object;
					return id.equals(transferenciaAgendadaDTO.getId());
				}

			});
			transferenciaAgendadaDTO = (TransferenciaAgendadaDTO) found;
		}
		return transferenciaAgendadaDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * AgendaTransferenciaManager#grabarEstadisticaComprobanteStopDebit()
	 */
	@Override
	public void grabarEstadisticaComprobanteStopDebit() {
		LOGGER.info(MSJ_INFO_GRABANDO_ESTADISTICA_COMPROBANTE_STOP_DEBIT);
		StopDebitTransferenciaAgendadaDTO stopDebitTransferenciaAgendadaDTO = (StopDebitTransferenciaAgendadaDTO) sesionParametros
		        .getAccionTransferenciaAgendada();
		boolean isRioRio = !stopDebitTransferenciaAgendadaDTO.getTransferenciaAgendadaDTO().isHaciaOtroBanco();
		if (isRioRio) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_COMPROBANTE_STOP_DEBIT_RIORIO,
			        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_COMPROBANTE_STOP_DEBIT_OB,
			        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
	}

	/**
	 * Cargar frecuencias.
	 *
	 * @param agendamientoTransferenciaView the agendamiento transferencia view
	 */
	private void cargarFrecuencias(AgendamientoTransferenciaView agendamientoTransferenciaView) {
		FrecuenciaTransferenciaAgendada[] frecuenciasValues = FrecuenciaTransferenciaAgendada.values();
		List<String> frecuencias = new ArrayList<String>();
		for (FrecuenciaTransferenciaAgendada frecuencia : frecuenciasValues) {
			if ("S".equals(frecuencia.getCodigo())) {
				continue;
			}
			frecuencias.add(frecuencia.getDescripcion());
		}
		agendamientoTransferenciaView.setFrecuencias(frecuencias);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * TransferenciaManager#solicitarAgendarTransferenciaDesdeFeedback(ar.com.
	 * santanderrio.obp.servicios.transferencias.web.view.TransferenciaView)
	 */
	@Override
	public Respuesta<AgendamientoTransferenciaView> solicitarAgendamientoTransferencia(
	        AgendamientoTransferenciaView agendamientoTransferenciaView) {
		LOGGER.info(MSJ_INFO_SOLICITUD_DE_AGENDAMIENTO);
		sesionParametros.setContador(null);
		sesionParametros.resetearDesafioEnCurso();
		agendamientoTransferenciaView.setDesafio(null);
		estadisticaManager.add(EstadisticasConstants.CODIGO_SOLICITAR_AGENDAMIENTO_TRANSFERENCIA,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		cargarAyudas(agendamientoTransferenciaView);
		cargarFrecuencias(agendamientoTransferenciaView);
		agendamientoTransferenciaView.setLegalConcepto(obtenerLegalConcepto());
		agendamientoTransferenciaView.setConceptoTransferencia(ConceptoTransferenciaEnum.getConceptoView());
		Respuesta<AgendamientoTransferenciaView> respuestaTransferenciaView = new Respuesta<AgendamientoTransferenciaView>();
		respuestaTransferenciaView.setRespuesta(agendamientoTransferenciaView);
		respuestaTransferenciaView.setEstadoRespuesta(EstadoRespuesta.OK);
		return respuestaTransferenciaView;

	}

	/**
	 * Cargar ayudas.
	 *
	 * @param agendamientoTransferenciaView the agendamiento transferencia view
	 */
	private void cargarAyudas(AgendamientoTransferenciaView agendamientoTransferenciaView) {
		AyudaView ayudaView1 = new AyudaView(ayudaTITTransferenciaAuto, ayudaMSGTransferenciaAuto);
		AyudaView ayudaView2 = new AyudaView(ayudaTITTransferenciaRec, ayudaMSGTransferenciaRec);
		agendamientoTransferenciaView
		        .setMensajesAyuda(TransferenciaUtil.cargarListaTemplateDeAyudas(ayudaView1, ayudaView2));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * AgendaTransferenciaManager#solicitarAgendarTransferencia(ar.com.
	 * santanderrio.obp.servicios.cuentas.web.view.AgendaTransferencia)
	 */
	@Override
	public Respuesta<TransferenciaAgendadaDetalleView> solicitarTransferenciaAgendada(
	        TransferenciaAgendadaDetalleView agendaTransferencia) {

		grabarEstadisticas(EstadisticasConstants.CODIGO_SOLICITAR_AGENDAMIENTO_TRANSFERENCIA,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		Respuesta<TransferenciaAgendadaDetalleView> respuesta = new Respuesta<TransferenciaAgendadaDetalleView>();
		sesionParametros.resetearDesafioEnCurso();
		sesionParametros.setRsaRiesgoTransferenciaDTO(null);
		final Long clave = Long.parseLong(agendaTransferencia.getId());
		TransferenciaAgendadaDTO transferenciaAgendadaDTO = getAgendaTransferenciaSession(clave);
		boolean cuentaPropia = false;
		cuentaPropia = esCuentaPropia(transferenciaAgendadaDTO);

		if (!cuentaPropia) {
			boolean contratoHabilitado = contratoBO.tieneContrato(TipoContratoEnum.TRANSFERENCIA,
			        sesionCliente.getCliente());
			if (!contratoHabilitado) {
				String mensajeError = mensajeBO
				        .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_CONTRATO_TRANSFERENCIA)
				        .getMensaje();
				return respuestaFactory.crearRespuestaErrorPersonalizado(TransferenciaAgendadaDetalleView.class,
				        mensajeError, TipoError.ERROR_SIN_CONTRATO_TRANSFERENCIA.getDescripcion());
			}
		}

		LOGGER.info(MSJ_INFO_SOLICITAR_TRANSFERIR_DESDE_AGENDA);
		try {
			cargarAgendaTransferenciaDetalleViewYgrabarEstadisticaOK(respuesta, transferenciaAgendadaDTO, cuentaPropia);

		} catch (Exception e) {
			return cargarAgendaTransferenciaDetalleViewYgrabarEstadisticaERROR(respuesta, transferenciaAgendadaDTO, e);
		}
		super.obtenerMensajeSiHorarioDeTransferenciaNoEsValido(respuesta);
		return respuesta;
	}

	/**
	 * Es cuenta propia.
	 *
	 * @param transferenciaAgendadaDTO the transferencia agendada DTO
	 * @return true, if successful
	 */
	private boolean esCuentaPropia(TransferenciaAgendadaDTO transferenciaAgendadaDTO) {
		boolean cuentaPropia;
		if (StringUtils.isEmpty(transferenciaAgendadaDTO.getCbuCuenta())) {
			IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(
			        transferenciaAgendadaDTO.getCuentaDestino());
			cuentaPropia = TransferenciaUtil.esCuentaPropia(sesionCliente.getCliente(), identificacionCuenta,
			        transferenciaAgendadaDTO.getCuentaDestinoTipo().getDescripcion(), true);
		} else {
			cuentaPropia = TransferenciaUtil.esCuentaPropiaCBU(sesionCliente.getCliente(),
			        transferenciaAgendadaDTO.getCbuCuenta());
		}
		return cuentaPropia;
	}

	/**
	 * Cargar agenda transferencia detalle view ygrabar estadistica ERROR.
	 *
	 * @param respuesta                the respuesta
	 * @param transferenciaAgendadaDTO the transferencia agendada DTO
	 * @param e                        the e
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaAgendadaDetalleView> cargarAgendaTransferenciaDetalleViewYgrabarEstadisticaERROR(
	        Respuesta<TransferenciaAgendadaDetalleView> respuesta, TransferenciaAgendadaDTO transferenciaAgendadaDTO,
	        Exception e) {
		LOGGER.error(ERROR_AGENDA_TRANSFERENCIA, e);
		if (!transferenciaAgendadaDTO.isHaciaOtroBanco()) {
			grabarEstadisticas(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CONFIGURACION_TRANSFERENCIA_RIORIO,
			        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else {
			grabarEstadisticas(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CONFIFURACION_TRANSFERENCIA_OB,
			        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuesta.add(getErrorConMensajeYtipo(CodigoMensajeConstantes.CODIGO_ERROR_AGENDA_TRANSFERENCIAS,
		        TipoError.ERROR_AGENDA_TRANSFERENCIAS));
		return respuesta;
	}

	/**
	 * Cargar agenda transferencia detalle view ygrabar estadistica OK.
	 *
	 * @param respuesta                the respuesta
	 * @param transferenciaAgendadaDTO the transferencia agendada DTO
	 * @param cuentaPropia             the cuenta propia
	 * @throws BusinessException the business exception
	 */
	private void cargarAgendaTransferenciaDetalleViewYgrabarEstadisticaOK(
	        Respuesta<TransferenciaAgendadaDetalleView> respuesta, TransferenciaAgendadaDTO transferenciaAgendadaDTO,
	        boolean cuentaPropia) throws BusinessException {
		TransferenciaUtil.limpiarDatosTransferenciaDestinoDeSesionParametros(sesionParametros);
		TransferenciaAgendadaDetalleViewVisitor visitor = new TransferenciaAgendadaDetalleViewVisitor(false);
		transferenciaAgendadaDTO.accept(visitor);
		TransferenciaAgendadaDetalleView agendaTransferenciaDetalleView = visitor.getView();
		agendaTransferenciaDetalleView.generarNuevoIdSesion();
		agendaTransferenciaDetalleView.setFecha(new SimpleDateFormat(FORMATO_FECHA).format(new Date()));
		setearSaldos(agendaTransferenciaDetalleView, transferenciaAgendadaDTO);
		agendaTransferenciaDetalleView.generarNuevoIdSesion();
		agendaTransferenciaDetalleView.setMensajesAyuda(obtenerAyudasConTemplate());
		DestinatarioAgendaDTO destinatarioAgendaDTO = null;
		sesionParametros.getDatosTransferenciaDestino().setTransferenciaAgendadaHaciaOtroBanco(transferenciaAgendadaDTO.isHaciaOtroBanco());
		sesionParametros.getDatosTransferenciaDestino().setCbu(transferenciaAgendadaDTO.getCbuCuenta());
		sesionParametros.getDatosTransferenciaDestino().setAlias(transferenciaAgendadaDTO.getCuentaAliasDestino());
		sesionParametros.getDatosTransferenciaDestino().setNumeroCuenta(transferenciaAgendadaDTO.getCuentaDestino());
		if (!cuentaPropia) {
			destinatarioAgendaDTO = agendaDestinatarioBO.obtenerAgendaDestinatarioPuntual(sesionCliente.getCliente(),
			        transferenciaAgendadaDTO);
		}
		if (destinatarioAgendaDTO != null) {
			agendaTransferenciaDetalleView.setFechaCreacionDestinatario(destinatarioAgendaDTO.getFechaCreacion());
			sesionParametros.getDatosTransferenciaDestino().setFechaCreacion(destinatarioAgendaDTO.getFechaCreacion());
		}
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(agendaTransferenciaDetalleView);
		if (!transferenciaAgendadaDTO.isHaciaOtroBanco()) {
			grabarEstadisticas(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CONFIGURACION_TRANSFERENCIA_RIORIO,
			        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			grabarEstadisticas(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CONFIFURACION_TRANSFERENCIA_OB,
			        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
	}

	/**
	 * Cargar ayudas.
	 *
	 * @return the list
	 */
	private List<AyudaView> obtenerAyudasConTemplate() {
		AyudaView ayudaView1 = new AyudaView(ayudaTITNuevaTransferenciaLimiteTransferencia, ayudaTemplateNuevaTransferenciaLimite);
		AyudaView ayudaView2 = new AyudaView(ayudaTITNuevaTransferenciaPlazoAcreditacion, ayudaTemplateNuevaTransferenciaPlazoAcreditacion);
		AyudaView ayudaView3 = new AyudaView(ayudaTITNuevaTransferenciaFecha, ayudaTemplateNuevaTransferenciaFecha);
		AyudaView ayudaView4 = new AyudaView(ayudaTITNuevaTransferenciaDesc, ayudaTemplateNuevaTransferenciaDesc);

		return TransferenciaUtil.cargarListaTemplateDeAyudas(ayudaView1, ayudaView2, ayudaView3, ayudaView4);
	}

	/**
	 * Setear saldos.
	 *
	 * @param agendaTransferenciaDetalleView the agenda transferencia detalle view
	 * @param transferenciaAgendadaDTO       the transferencia agendada DTO
	 * @throws BusinessException the business exception
	 */
	private void setearSaldos(TransferenciaAgendadaDetalleView agendaTransferenciaDetalleView,
	        TransferenciaAgendadaDTO transferenciaAgendadaDTO) throws BusinessException {
		Cuenta cuentaOrigen = sesionCliente.getCliente()
		        .getCuentaPorNumero(parsearACuentaConCeros(transferenciaAgendadaDTO.getNroCuentaOrigen()));
		if (cuentaOrigen == null) {
			LOGGER.error("Ocurrio un error al buscar la cuenta de origen en la sesion del cliente.");
			throw new BusinessException();
		}
		cuentaBO.obtenerSaldoActualizado(cuentaOrigen);

		agendaTransferenciaDetalleView.setOrigenSaldo(cuentaOrigen.obtenerSaldoFormateado());

		if (null != transferenciaAgendadaDTO.getCuentaDestino()) {
			Cuenta cuentaDestinatario = sesionCliente.getCliente()
			        .getCuentaPorNumero(parsearACuentaConCeros(transferenciaAgendadaDTO.getCuentaDestino()));

			if (null != cuentaDestinatario) {
				agendaTransferenciaDetalleView.setDestinatarioSaldo(cuentaDestinatario.obtenerSaldoFormateado());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * AgendaTransferenciaManager#ejecutarAgendarTransferencia(ar.com.
	 * santanderrio.obp.servicios.cuentas.web.view.AgendaTransferencia)
	 */
	@Override
	public Respuesta<TransferenciaAgendadaDetalleView> ejecutarTransferenciaAgendada(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {

		cargarMontosLimitesYMensaje(transferenciaAgendadaDetalleView);
		TransferenciaAgendadaDTO trfDTO = getAgendaTransferenciaSession(
		        Long.parseLong(transferenciaAgendadaDetalleView.getId()));
		if (trfDTO != null && ISBANStringUtils.validarCVU(trfDTO.getCbuCuenta())) {
			DivisaEnum moneda = trfDTO.getMoneda();
			if (!DivisaEnum.PESO.equals(moneda) && trfCvuDolaresHabilitado == 0) {
				return armarRespuestaErrorMonedaCVU();
			}
			String importe = transferenciaAgendadaDetalleView.getImporte().replace(".", "");
			importe = importe.replace(",", ".");
			if (!TransferenciaUtil.validarTransferenciaCVU(new BigDecimal(importe), moneda, trfCvuDolaresHabilitado,
			        trfCVUImporteDolaresMax, trfCVUImportePesosMax)) {
				return armarRespuestaWarningValidacionImporteCVU(moneda);
			}
		}

		cargarVistaConDatosDeBiocatchParaRsa(transferenciaAgendadaDetalleView);

		Respuesta<TransferenciaAgendadaDetalleView> respuestaFinal = llamarRSAyAnalizarDesafiosParaUnaTransferenciaAgendada(
		        transferenciaAgendadaDetalleView);

		if (!EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())) {
			return respuestaFinal;
		}

		try {
            banelcoDAO.obtenerCuentasBanelcoHabilitadasSinCache(sesionCliente.getCliente());
        } catch (DAOException e) {
            LOGGER.error("Error en llamada IDEPESBANE");
        }

		TransferenciaAgendadaDTO transferenciaAgendadaDTO = cargarTransferenciaAgendadaDTOdeUnaTransferenciaAgendadaView(
		        transferenciaAgendadaDetalleView);

		boolean riesgoAlto = sesionParametros.getRsaRiesgoTransferenciaDTO() != null
		        && sesionParametros.getRsaRiesgoTransferenciaDTO().getRiesgoAlto();

		if (sesionParametros.getRsaRiesgoTransferenciaDTO() == null
		        || !sesionParametros.getRsaRiesgoTransferenciaDTO().getRiesgoAlto()) {
			transferenciaAgendadaDTO.setTransferenciaInmediata(true);
			sesionParametros.setRsaRiesgoTransferenciaDTO(null);
		} else {
			transferenciaAgendadaDTO.setTransferenciaInmediata(false);
			sesionParametros.setRsaRiesgoTransferenciaDTO(null);
		}
		inicializarContadorIntentosNuevaTransferencia(transferenciaAgendadaDetalleView, transferenciaAgendadaDTO);

		if (!transferenciaAgendadaDTO.isTransferenciaInmediata()) {
			// TODO VERIFICAR FUNCIONAMIENTO DE LOGICA DE AGENDAMIENTO
			// AUTOMATICO.
			// boolean cuentaPropia = ""
			// .equals(StringUtils.defaultString(transferenciaAgendadaDetalleView.getCuentaPropia()))
			// || "N".equalsIgnoreCase(
			// StringUtils.defaultString(transferenciaAgendadaDetalleView.getCuentaPropia()))
			// ? false
			// : true;
			TransferenciaDTO transferenciaDTO = cargarTransferenciaDTOConTransferenciaView(
			        transferenciaAgendadaDetalleView);
			transferenciaDTO.setRiesgoAlto(riesgoAlto);
			Respuesta<TransferenciaDTO> respuestaTransferenciaBO = transferenciaBO.ejecutarTransferenciaProgramada(
			        sesionCliente.getCliente(), transferenciaDTO, false,
			        !transferenciaAgendadaDTO.isTransferenciaInmediata());

			switch (respuestaTransferenciaBO.getEstadoRespuesta()) {
			case OK:
				return armarRespuestaOK(transferenciaAgendadaDetalleView, true,
				        respuestaTransferenciaBO.getRespuesta());
			case WARNING:
				return armarRespuestaWARNING(transferenciaAgendadaDetalleView, respuestaTransferenciaBO);
			default:
				throw new RobotException(HA_OCURRIDO_UN_ERROR_INESPERADO_AL_EJECUTAR_UNA_TRANSFERENCIA_AGENDADA);
			}
		}

		Respuesta<TransferenciaEjecutadaDTO> respuestaBO = agendaTransferenciaBO
		        .ejecutarTransferenciaAgendada(transferenciaAgendadaDTO, sesionCliente.getCliente());
		EstadoRespuesta estadoRespuesta = respuestaBO.getEstadoRespuesta();
		switch (estadoRespuesta) {
		case OK:
			return armarRespuestaOKyGrabarEstadisticas(transferenciaAgendadaDetalleView, transferenciaAgendadaDTO,
			        respuestaBO);
		case WARNING:
			return armarRespuestaWARNINGyGrabarEstadisticas(transferenciaAgendadaDetalleView, respuestaFinal,
			        transferenciaAgendadaDTO, respuestaBO.getItemsMensajeRespuesta());
		case ERROR:
			return armarRespuestaERRORyGrabarEstadisticas(transferenciaAgendadaDetalleView, respuestaFinal,
			        transferenciaAgendadaDTO, respuestaBO.getItemsMensajeRespuesta());
		default:
			grabarEstadisticasConfirmacion(transferenciaAgendadaDTO.esRioRio(),
			        transferenciaAgendadaDetalleView.getCuentaPropia(), transferenciaAgendadaDetalleView.getImporte(),
			        transferenciaAgendadaDetalleView.getDivisa(), transferenciaAgendadaDetalleView.getOrigenNumero(),
			        transferenciaAgendadaDetalleView.getDestinoNumero(),
			        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			throw new RobotException(HA_OCURRIDO_UN_ERROR_INESPERADO_AL_EJECUTAR_UNA_TRANSFERENCIA_AGENDADA);
		}
	}

	private void cargarVistaConDatosDeBiocatchParaRsa(TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {

		BiocatchResponseDataDTO biocatchResponse = biocatchManager.getScore(sesionCliente.getCliente().getNup(), sesionCliente.getIpCliente(), ActivityName.TRANSFERENCIA, ActivityType.WIRE_PAYMENT);
		transferenciaAgendadaDetalleView.setBiocatchRsaData(biocatchResponse);
	}

	private void cargarMontosLimitesYMensaje(TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {
		
		transferenciaAgendadaDetalleView.setLimiteCuentasPropiasDolares(new BigDecimal(limiteCuentasPropiasDolares));
		transferenciaAgendadaDetalleView.setLimiteCuentasPropiasPesos(new BigDecimal(limiteCuentasPropiasPesos));
		transferenciaAgendadaDetalleView.setLimiteTercerosDolares(new BigDecimal(limiteTercerosDolares));
		transferenciaAgendadaDetalleView.setLimiteTercerosPesos(new BigDecimal(limiteTercerosPesos));
		transferenciaAgendadaDetalleView.setLimiteOtrosBancosDolares(new BigDecimal(limiteOtrosBancosDolares));
		transferenciaAgendadaDetalleView.setLimiteOtrosBancosPesos(new BigDecimal(limiteOtrosBancosPesos));
		transferenciaAgendadaDetalleView.setMensajeLimiteDiario(mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_ERROR_LIMITE_DIARIO).getMensaje());
		
	}
	
	
	/**
	 * Armar respuesta error moneda CVU.
	 *
	 * @param <T> the generic type
	 * @return the respuesta
	 */
	private <T> Respuesta<T> armarRespuestaErrorMonedaCVU() {
		LOGGER.error("Transferencia Agendada CVU - Moneda invalida");
		Respuesta<T> respuesta = new Respuesta<T>();
		respuesta.setRespuestaVacia(Boolean.TRUE);
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
		        TransferenciaUtil.MSG_ERROR_MONEDA_INVALIDA_CVU);
		itemMensajeRespuesta.setTipoError(TipoError.ERROR_CVU_DOLARES_NO_HABILITADO.getDescripcion());
		respuesta.add(itemMensajeRespuesta);
		return respuesta;
	}

	/**
	 * Armar respuesta warning validacion importe CVU.
	 *
	 * @param <T>    the generic type
	 * @param moneda the moneda
	 * @return the respuesta
	 */
	private <T> Respuesta<T> armarRespuestaWarningValidacionImporteCVU(DivisaEnum moneda) {
		Respuesta<T> respuesta = new Respuesta<T>();
		respuesta.setRespuestaVacia(Boolean.TRUE);
		respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		String importeMax = DivisaEnum.PESO.equals(moneda) ? trfCVUImportePesosMax : trfCVUImporteDolaresMax;
		String mensajeError = MessageFormat.format(MSG_ERROR_IMPORTE_LIMITE_CVU,
		        moneda.getSimbolo().concat(importeMax));
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(mensajeError);
		itemMensajeRespuesta.setTag(IMPORTE);
		itemMensajeRespuesta.setTipoError(TipoError.ERROR_IMPORTE_LIMITE_TRANSFERENCIA.getDescripcion());
		respuesta.add(itemMensajeRespuesta);
		return respuesta;
	}

	/**
	 * Armar respuesta WARNING.
	 *
	 * @param transferenciaView        the transferencia view
	 * @param respuestaTransferenciaBO the respuesta transferencia BO
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaAgendadaDetalleView> armarRespuestaWARNING(
	        TransferenciaAgendadaDetalleView transferenciaView, Respuesta<TransferenciaDTO> respuestaTransferenciaBO) {
		// LOGGER.info(MSJ_INFO_TRANSFERENCIA_CON_WARNING);
		Respuesta<TransferenciaAgendadaDetalleView> respuestaFinal;
		respuestaFinal = new Respuesta<TransferenciaAgendadaDetalleView>();
		respuestaFinal.setEstadoRespuesta(respuestaTransferenciaBO.getEstadoRespuesta());
		respuestaFinal.setItemMensajeRespuesta(respuestaTransferenciaBO.getItemsMensajeRespuesta());
		respuestaFinal.getItemsMensajeRespuesta().get(0)
		        .setTipoError(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion());
		respuestaFinal.setEstadoRespuesta(EstadoRespuesta.ERROR);
		// transferenciaView.setCuentasView(cuentaManager.getCuentasSaldo().getRespuesta());
		respuestaFinal.setRespuesta(transferenciaView);
		return respuestaFinal;
	}

	/**
	 * Armar respuesta OK.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @param isProgramada                     the is programada
	 * @param transferenciaBO                  the transferencia BO
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaAgendadaDetalleView> armarRespuestaOK(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView, boolean isProgramada,
	        TransferenciaDTO transferenciaBO) {
		// LOGGER.info(MSJ_INFO_TRANSFERENCIA_CON_EXITO);
		Respuesta<TransferenciaAgendadaDetalleView> respuestaFinal = respuestaFactory
		        .crearRespuestaOk(TransferenciaAgendadaDetalleView.class);
		transferenciaAgendadaDetalleView = generarTransferenciaView(transferenciaAgendadaDetalleView, transferenciaBO);

		respuestaFinal.setRespuesta(transferenciaAgendadaDetalleView);

		// sesionParametros.setTipoOperacionComprobante(TipoOperacionComprobanteEnum.TRANSFERENCIA);
		// sesionParametros.setTransferenciaView(transferenciaView);
		cargarHashDeLaVistaEnSesion(transferenciaAgendadaDetalleView);
		return respuestaFinal;
	}

	/**
	 * Generar transferencia view.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @param transferenciaDTO                 the transferencia DTO
	 * @return the transferencia agendada detalle view
	 */
	private TransferenciaAgendadaDetalleView generarTransferenciaView(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView, TransferenciaDTO transferenciaDTO) {
		transferenciaAgendadaDetalleView.setNumeroComprobante(
		        transferenciaDTO.getIdRecibo().substring(8, transferenciaDTO.getIdRecibo().length()));
		transferenciaAgendadaDetalleView.setFechaComprobante(transferenciaDTO.getFechaCompensacion());
		transferenciaAgendadaDetalleView.setMensaje(transferenciaDTO.getMensaje());
		transferenciaAgendadaDetalleView.setIdTransaccion(transferenciaDTO.getIdTransaccion());
		return transferenciaAgendadaDetalleView;
	}

	/**
	 * Cargar transferencia DTO con transferencia view.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @return the transferencia DTO
	 */
	public TransferenciaDTO cargarTransferenciaDTOConTransferenciaView(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {
		TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
		transferenciaDTO
		        .setFechaProgramada(ISBANStringUtils.formatearFecha(transferenciaAgendadaDetalleView.getFecha()));
		transferenciaDTO.setCuentaOrigen(obtenerCuentaPorNroCuenta(transferenciaAgendadaDetalleView.getOrigenNumero()));
		if (!transferenciaAgendadaDetalleView.getIsRioRio()) {
			transferenciaDTO.setCbuCuenta(transferenciaAgendadaDetalleView.getDestinoNumero());
			transferenciaDTO.setCuit(transferenciaAgendadaDetalleView.getNumeroCuilCuit().replace("-", ""));
		} else {
			transferenciaDTO.setTipoCuentaDestino(
			        TipoCuenta.fromDescripcionConMoneda(transferenciaAgendadaDetalleView.getDestinoTipo()));
			transferenciaDTO.setNumeroCuentaDestino(
			        cargarCuentaDestinatario(transferenciaAgendadaDetalleView.getDestinoNumero()));
			if (transferenciaDTO.getTipoCuentaDestino().getCodigo() == 2) {
				transferenciaDTO.setTipoCuentaDestino(TransferenciaUtil.matchearTipoCuentaDestino(
				        DivisaEnum.fromSimboloString(transferenciaAgendadaDetalleView.getDivisa())));
			}
			transferenciaDTO.setCuit("");
		}
		transferenciaDTO.setImporte(new BigDecimal(transferenciaAgendadaDetalleView.getImporte()));
		transferenciaDTO.setVaPorCoelsa(
		        "Inmediata".equalsIgnoreCase(transferenciaAgendadaDetalleView.getPlazoAcreditacion()) ? Boolean.FALSE
		                : Boolean.TRUE);
		if (ISBANStringUtils.validarCVU(transferenciaAgendadaDetalleView.getCbu())) {
			transferenciaDTO.setVaPorCoelsa(true);
		}
		transferenciaDTO.setNombreReceptor(transferenciaAgendadaDetalleView.getDestinatarioNombre());
		transferenciaDTO.setTitular(transferenciaAgendadaDetalleView.getDestinatarioNombre());
		transferenciaDTO.setConcepto(ConceptoTransferenciaEnum
		        .getConceptoFromOrdinal(transferenciaAgendadaDetalleView.getConcepto().getId()));
		transferenciaDTO.setDescripcionConcepto(transferenciaAgendadaDetalleView.getDescripcion());
		transferenciaDTO.setInformacionAdicional(transferenciaAgendadaDetalleView.getDescripcion());
		transferenciaDTO.setEmail(transferenciaAgendadaDetalleView.getEmail());
		transferenciaDTO.setEmailMensaje(transferenciaAgendadaDetalleView.getMensajeEmail());
		transferenciaDTO.setComentario(StringUtils.isNotEmpty(transferenciaAgendadaDetalleView.getMensajeEmail())
		        ? transferenciaAgendadaDetalleView.getMensajeEmail()
		        : "");
		transferenciaDTO.setHaciaCuentaPropia(
		        "N".equalsIgnoreCase(StringUtils.defaultString(transferenciaAgendadaDetalleView.getCuentaPropia()))
		                ? Boolean.FALSE
		                : Boolean.TRUE);
		transferenciaDTO.setHaciaOtroBanco(!transferenciaAgendadaDetalleView.getIsRioRio());
		transferenciaDTO.setMoneda(DivisaEnum.fromSimboloString(transferenciaAgendadaDetalleView.getDivisa()));
		transferenciaDTO.setBancoDestino(
		        TERCEROS_SANTANDER.equalsIgnoreCase(transferenciaAgendadaDetalleView.getDestinatarioBanco())
		                ? BancoEnum.SANTANDER_RIO.getDescripcion()
		                : transferenciaAgendadaDetalleView.getDestinatarioBanco());
		transferenciaDTO.setDescripcionAdicional(transferenciaAgendadaDetalleView.getDescripcion());

		return transferenciaDTO;
	}

	/**
	 * Obtener cuenta por nro cuenta.
	 *
	 * @param nroCuenta the nro cuenta
	 * @return the cuenta
	 */
	private Cuenta obtenerCuentaPorNroCuenta(String nroCuenta) {
		Cuenta cuentaOrigen = sesionCliente.getCliente()
		        .getCuentaPorNumero(TransferenciaUtil.parsearACuentaConCeros(nroCuenta));
		return cuentaOrigen;
	}

	/**
	 * Cargar cuenta destinatario.
	 *
	 * @param nroCuentaDestino the nro cuenta destino
	 * @return the identificacion cuenta
	 */
	private IdentificacionCuenta cargarCuentaDestinatario(String nroCuentaDestino) {
		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
		identificacionCuenta.setNroSucursal(ISBANStringUtils.extraerSucursal(nroCuentaDestino));
		identificacionCuenta.setNroCuentaProducto(ISBANStringUtils.extraerCuenta(nroCuentaDestino));
		return identificacionCuenta;
	}

	/**
	 * Armar respuesta WARNIN gy grabar estadisticas.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @param respuestaFinal                   the respuesta final
	 * @param transferenciaAgendadaDTO         the transferencia agendada DTO
	 * @param itemMensajeRespuestaList         the item mensaje respuesta list
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaAgendadaDetalleView> armarRespuestaWARNINGyGrabarEstadisticas(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView,
	        Respuesta<TransferenciaAgendadaDetalleView> respuestaFinal,
	        TransferenciaAgendadaDTO transferenciaAgendadaDTO, List<ItemMensajeRespuesta> itemMensajeRespuestaList) {
		LOGGER.info(HA_OCURRIDO_UN_ERROR_PARCIAL_AL_AGENDAR_LA_TRANSFERENCIA);
		grabarEstadisticasConfirmacion(transferenciaAgendadaDTO.esRioRio(),
		        transferenciaAgendadaDetalleView.getCuentaPropia(), transferenciaAgendadaDetalleView.getImporte(),
		        transferenciaAgendadaDetalleView.getDivisa(), transferenciaAgendadaDetalleView.getOrigenNumero(),
		        transferenciaAgendadaDetalleView.getDestinoNumero(), EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		respuestaFinal.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuestaFinal.setRespuesta(transferenciaAgendadaDetalleView);
		respuestaFinal.setItemMensajeRespuesta(itemMensajeRespuestaList);
		return sesionParametros.getContador().excedeReintentos(transferenciaAgendadaDetalleView.getIdSesion(),
		        respuestaFinal);
	}

	/**
	 * Armar respuesta ERRO ry grabar estadisticas.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @param respuestaFinal                   the respuesta final
	 * @param transferenciaAgendadaDTO         the transferencia agendada DTO
	 * @param itemMensajeRespuestaList         the item mensaje respuesta list
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaAgendadaDetalleView> armarRespuestaERRORyGrabarEstadisticas(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView,
	        Respuesta<TransferenciaAgendadaDetalleView> respuestaFinal,
	        TransferenciaAgendadaDTO transferenciaAgendadaDTO, List<ItemMensajeRespuesta> itemMensajeRespuestaList) {
		if (null != itemMensajeRespuestaList) {
			String mensaje = itemMensajeRespuestaList.get(0).getMensaje();
			mensaje = MessageFormat.format(mensaje,
			        TransferenciaUtil.obtenerTituloTipoTransferencia(transferenciaAgendadaDetalleView.getFecha(), true),
			        transferenciaAgendadaDetalleView.getDestinatarioNombre(),
			        transferenciaAgendadaDetalleView.getDivisa() + " " + ISBANStringUtils
			                .formatearSaldo(new BigDecimal(transferenciaAgendadaDetalleView.getImporte())));
			itemMensajeRespuestaList.get(0).setMensaje(mensaje);
			respuestaFinal.setItemMensajeRespuesta(itemMensajeRespuestaList);
		}

		respuestaFinal.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaFinal.setRespuesta(null);
		respuestaFinal.setRespuestaVacia(true);
		return respuestaFinal;
	}

	/**
	 * Armar respuesta O ky grabar estadisticas.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @param transferenciaAgendadaDTO         the transferencia agendada DTO
	 * @param respuestaBO                      the respuesta BO
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaAgendadaDetalleView> armarRespuestaOKyGrabarEstadisticas(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView,
	        TransferenciaAgendadaDTO transferenciaAgendadaDTO, Respuesta<TransferenciaEjecutadaDTO> respuestaBO) {
		Respuesta<TransferenciaAgendadaDetalleView> respuestaFinal;
		LOGGER.info("La transferencia se agendo satisfactoriamente.");
		respuestaFinal = respuestaFactory.crearRespuestaOk(TransferenciaAgendadaDetalleView.class, null);
		mapearEjecucionView(transferenciaAgendadaDetalleView, respuestaBO.getRespuesta());
		Mensaje mensajeOk = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_AGENDAR_TRANSFERENCIA_OK);
		transferenciaAgendadaDetalleView.setMensaje(
		        MessageFormat.format(mensajeOk.getMensaje(), transferenciaAgendadaDetalleView.getDestinatarioNombre(),
		                transferenciaAgendadaDetalleView.getDivisa() + " "
		                        + ISBANStringUtils
		                                .formatearSaldo(new BigDecimal(transferenciaAgendadaDetalleView.getImporte())),
		                MSJ_REALIZADA, TransferenciaUtil.obtenerTituloTipoTransferencia(
		                        transferenciaAgendadaDetalleView.getFechaComprobante(), false)));
		respuestaFinal.setRespuesta(transferenciaAgendadaDetalleView);
		grabarEstadisticasConfirmacion(transferenciaAgendadaDTO.esRioRio(),
		        transferenciaAgendadaDetalleView.getCuentaPropia(), transferenciaAgendadaDetalleView.getImporte(),
		        transferenciaAgendadaDetalleView.getDivisa(), transferenciaAgendadaDetalleView.getOrigenNumero(),
		        transferenciaAgendadaDetalleView.getDestinoNumero(), EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		cargarHashDeLaVistaEnSesion(transferenciaAgendadaDetalleView);
		return respuestaFinal;
	}

	/**
	 * Cargar hash de la vista en sesion.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 */
	private void cargarHashDeLaVistaEnSesion(TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {
		String hashView = HashUtils.obtenerHash(crearMapaDeTransferenciaView(transferenciaAgendadaDetalleView));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sesionParametros.setValidacionHash(hashView);
	}

	/**
	 * Crear mapa de transferencia view.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @return the object
	 */
	private Map<String, String> crearMapaDeTransferenciaView(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, String> mapaAtributos = new HashMap<String, String>();
		mapaAtributos.put("id", transferenciaAgendadaDetalleView.getId());
		mapaAtributos.put("idSesion", transferenciaAgendadaDetalleView.getIdSesion());
		mapaAtributos.put("titular", transferenciaAgendadaDetalleView.getDestinatarioNombre());
		mapaAtributos.put("cbu", transferenciaAgendadaDetalleView.getCbu());
		mapaAtributos.put("nroCuentaOrigen", transferenciaAgendadaDetalleView.getOrigenNumero());
		mapaAtributos.put("nroCuentaDestino", transferenciaAgendadaDetalleView.getDestinoNumero());
		mapaAtributos.put("importe", transferenciaAgendadaDetalleView.getImporte());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Armar respuesta O ky grabar estadisticas 1.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @param transferenciaAgendadaDTO         the transferencia agendada DTO
	 * @param respuestaBO                      the respuesta BO
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaAgendadaDetalleView> armarRespuestaModificacionOKyGrabarEstadisticas(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView,
	        TransferenciaAgendadaDTO transferenciaAgendadaDTO, Respuesta<TransferenciaAgendadaDTO> respuestaBO) {
		Respuesta<TransferenciaAgendadaDetalleView> respuestaFinal;
		LOGGER.info("La transferencia se agendo satisfactoriamente.");
		respuestaFinal = respuestaFactory.crearRespuestaOk(TransferenciaAgendadaDetalleView.class, null);
		mapearEjecucionView(transferenciaAgendadaDetalleView, respuestaBO.getRespuesta());
		transferenciaAgendadaDetalleView.setMensaje(respuestaBO.getRespuesta().getMensaje());
		respuestaFinal.setRespuesta(transferenciaAgendadaDetalleView);
		estadisticaManager.add(EstadisticasConstants.CODIGO_ESTADISTICA_EJECUCION_MODIFICACION_TRANSFERENCIA_AUTO,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFinal;
	}

	/**
	 * Este metodo se encarga de llamar a RSA y desafiar, si asi lo requiere, al
	 * usuario.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaAgendadaDetalleView> llamarRSAyAnalizarDesafiosParaUnaTransferenciaAgendada(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {
		if (transferenciaAgendadaDetalleView.getIsRioRio()
		        && !"N".equals(transferenciaAgendadaDetalleView.getCuentaPropia())) {
			LOGGER.info(AGENDANDO_DE_TRANSFERENCIAS_ENTRE_CUENTAS_PROPIAS_NO_SE_REALIZA_AUTENTIFICACION);

			if(!TransferenciaUtil.esCuentaPropiaCliente(sesionCliente.getCliente(), cargarCuentaDestinatario(transferenciaAgendadaDetalleView.getDestinoNumero()))){

				TransferenciaUtil.loguearInfoPosibleFraudeCuentaPropia(LOGGER, transferenciaAgendadaDetalleView.getDestinoNumero());

				return respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.RULE_DERIVACIONAPP, CodigoMensajeConstantes.RSA_DERIVACION_APP_USUARIO);
			}

			return respuestaFactory.crearRespuestaOk(TransferenciaAgendadaDetalleView.class);

		} else {
			validarPosibleFraude(transferenciaAgendadaDetalleView);
			return analizarRSAyEjecutarDesafio(transferenciaAgendadaDetalleView);
		}
	}

	private void validarPosibleFraude(TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {

		boolean coincideCBU = transferenciaAgendadaDetalleView.getDestinoNumero().equals(sesionParametros.getDatosTransferenciaDestino().getCbu());
		boolean coincideNroCuenta = transferenciaAgendadaDetalleView.getDestinoNumero().equals(sesionParametros.getDatosTransferenciaDestino().getNumeroCuenta());
		boolean esPosibleFraude = false;

		transferenciaAgendadaDetalleView.setPif(false);


		if(sesionParametros.getDatosTransferenciaDestino().isTransferenciaAgendadaHaciaOtroBanco()) {

			if(!coincideCBU) esPosibleFraude = true;

		} else {

			if(!coincideNroCuenta) esPosibleFraude = true;

		}

		LOGGER.info("Datos de validarPosibleFraude");
		LOGGER.info("TransferenciaAgendadaDetalleView: fechaCreacion: {}, CBU: {}, isRioRio: {}, numeroCuentaDestino: {}", transferenciaAgendadaDetalleView.getFechaCreacionDestinatario(),
				 transferenciaAgendadaDetalleView.getCbu(), transferenciaAgendadaDetalleView.getIsRioRio(), transferenciaAgendadaDetalleView.getDestinoNumero());
		LOGGER.info("DatosTransferenciaDestino: CBU: {}, numeroCuenta: {}", sesionParametros.getDatosTransferenciaDestino().getCbu(), sesionParametros.getDatosTransferenciaDestino().getNumeroCuenta());
		LOGGER.info("Validaciones: coincideCBU: {}, coincideNroCuenta: {}", coincideCBU, coincideNroCuenta);

		if(esPosibleFraude) {

			transferenciaAgendadaDetalleView.setPif(true);
			TransferenciaUtil.loguearInfoPosibleFraudeTercerosTransferenciaAgendada(LOGGER, transferenciaAgendadaDetalleView, sesionParametros.getDatosTransferenciaDestino());

		}

	}

	/**
	 * Este metodo analiza el estado de RSA.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia view
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaAgendadaDetalleView> analizarRSAyEjecutarDesafio(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {

		if (sesionParametros.getRsaRiesgoTransferenciaDTO() == null) {
			AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
			autentificacionDTO.setOperacion(Integer.parseInt(valorDesafioTransferenciasAgendadas));
			TipoDesafioEnum desafioAplicable = autentificacionManager
					.obtenerDesafioHabilitadoOperacion(autentificacionDTO);

			cargarDatosClaveToken(transferenciaAgendadaDetalleView);
			
			Respuesta<RsaRiesgoTransferenciaDTO> respuestaRsaRiesgoTransferenciaDTO = rsaManager
			        .analizarRiesgoTransferencia(transferenciaAgendadaDetalleView, desafioAplicable);
			RsaRiesgoTransferenciaDTO rsaRiesgoTransferenciaDTO = respuestaRsaRiesgoTransferenciaDTO.getRespuesta();
			sesionParametros.setRsaRiesgoTransferenciaDTO(rsaRiesgoTransferenciaDTO);

			if (rsaRiesgoTransferenciaDTO.getRiesgoAlto()) {
				return crearRespuestaWarningRiesgoAlto(transferenciaAgendadaDetalleView);
			}

			Respuesta<TransferenciaAgendadaDetalleView> respuestaAutentificacion = analizarActionCodeRSA(
			        transferenciaAgendadaDetalleView, rsaRiesgoTransferenciaDTO);

			return respuestaAutentificacion;
		} else {
			// Cuando este valor es distinto de nulo, significa que ya se
			// consulto a
			// RSA y se le informo al cliente de su riesgo.
			if (sesionParametros.getRsaRiesgoTransferenciaDTO().getActionCode().equals(ActionCode.CHALLENGE)) {
				LOGGER.info(EJECUTANDO_TRANSFERENCIA_CON_RIESGO_ALTO);
				return chequearRiesgoYejecutarDesafio(transferenciaAgendadaDetalleView);
			} else {
				LOGGER.info(EJECUTANDO_TRANSFERENCIA_CON_RIESGO_ALTO);
				return respuestaFactory.crearRespuestaOk(TransferenciaAgendadaDetalleView.class,
				        transferenciaAgendadaDetalleView);

			}
		}

	}
	
	private void cargarDatosClaveToken(TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {
    	Respuesta<List<BigDecimal>> antiguedades = clienteManager.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
		if(antiguedades != null && antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
			if(antiguedades.getRespuesta().get(0) != null) {
				transferenciaAgendadaDetalleView.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
			}
			if(antiguedades.getRespuesta().get(1) != null) {
				transferenciaAgendadaDetalleView.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
			}
		}
    }

	/**
	 * Analizar action code RSA.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia view
	 * @param rsaRiesgoTransferenciaDTO        the rsa riesgo transferencia DTO
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaAgendadaDetalleView> analizarActionCodeRSA(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView,
	        RsaRiesgoTransferenciaDTO rsaRiesgoTransferenciaDTO) {
		Respuesta<TransferenciaAgendadaDetalleView> respuestaAutentificacion = null;
		switch (rsaRiesgoTransferenciaDTO.getActionCode()) {
		case ALLOW:
			respuestaAutentificacion = respuestaFactory.crearRespuestaOk(TransferenciaAgendadaDetalleView.class,
			        transferenciaAgendadaDetalleView);
			break;
		case DENY:
			respuestaAutentificacion = respuestaFactory.crearRespuestaError(TransferenciaAgendadaDetalleView.class,
			        StringUtils.EMPTY, TipoError.DENY_RSA, CodigoMensajeConstantes.DENY_RSA_TRANSFERENCIA_BLACK_LIST);
			respuestaAutentificacion.setRespuesta(transferenciaAgendadaDetalleView);
			break;
		case CHALLENGE:
			respuestaAutentificacion = chequearRiesgoYejecutarDesafio(transferenciaAgendadaDetalleView);
			break;
		default:
			throw new RobotException(OCURRIO_UN_ERROR_AL_VERIFICAR_CUAL_DESAFIO_TIENE_ACTIVO_EL_CLIENTE);
		}
		return respuestaAutentificacion;
	}

	/**
	 * Chequear riesgo yejecutar desafio.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaAgendadaDetalleView> chequearRiesgoYejecutarDesafio(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {
		Respuesta<AutentificacionDTO> respuestaAutentificacion = autentificacionManager
		        .ejecutarValidacionRSA(cargarAutentificacionDTO(transferenciaAgendadaDetalleView));
		Respuesta<TransferenciaAgendadaDetalleView> respuesta = new Respuesta<TransferenciaAgendadaDetalleView>();
		respuesta.setEstadoRespuesta(respuestaAutentificacion.getEstadoRespuesta());
		respuesta.setItemMensajeRespuesta(respuestaAutentificacion.getItemsMensajeRespuesta());
		transferenciaAgendadaDetalleView.setDesafio(respuestaAutentificacion.getRespuesta());
		respuesta.setRespuesta(transferenciaAgendadaDetalleView);
		return respuesta;
	}

	/**
	 * Crear respuesta warning riesgo alto.
	 *
	 * @param transferenciaView the transferencia view
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaAgendadaDetalleView> crearRespuestaWarningRiesgoAlto(
	        TransferenciaAgendadaDetalleView transferenciaView) {
		LOGGER.info(LA_TRANSFERENCIA_TIENE_RIESGO_ALTO);
		transferenciaView.setFecha(TransferenciaUtil.sumarDiasAunaFecha(transferenciaView.getFecha(), 2));
		LOGGER.info(LA_NUEVA_FECHA_DE_EJECUCION_ES + transferenciaView.getFecha());

		return respuestaFactory.crearRespuestaWarning(transferenciaView, "", TipoError.TRANFERENCIA_CON_RIESGO_ALTO,
		        CodigoMensajeConstantes.CODIGO_ERROR_TRANSFERENCIA_RIESGO_ALTO);
	}

	/**
	 * Cargar transferencia agendada DTO.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @return the transferencia agendada DTO
	 */
	private TransferenciaAgendadaDTO cargarTransferenciaAgendadaDTOdeUnaTransferenciaAgendadaView(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {
		TransferenciaAgendadaDTO transferenciaAgendadaDTO = getAgendaTransferenciaSession(
		        Long.parseLong(transferenciaAgendadaDetalleView.getId()));
		transferenciaAgendadaDTO.setEmail(transferenciaAgendadaDetalleView.getEmail());
		transferenciaAgendadaDTO.setEmailMensaje(transferenciaAgendadaDetalleView.getMensajeEmail());
		transferenciaAgendadaDTO.setImporte(new BigDecimal(transferenciaAgendadaDetalleView.getImporte()));
		transferenciaAgendadaDTO.setConcepto(ConceptoTransferenciaEnum
		        .getConceptoFromOrdinal(transferenciaAgendadaDetalleView.getConcepto().getId()));

		// ESTE CAMPO ES EL CAMPO DESCRIPCION DEL FRONTEND SI EXISTE CARGADO SE
		// UTILIZA LA DESCRIPCION DEL MISMO, EN CASO QUE NO TOMA LA DESCRIPCION
		// DEL CONCEPTO
		String referencia = StringUtils.isBlank(transferenciaAgendadaDetalleView.getDescripcion()) == false
		        ? transferenciaAgendadaDetalleView.getDescripcion()
		        : ConceptoTransferenciaEnum.getConceptoFromOrdinal(transferenciaAgendadaDTO.getConcepto().getOrdinal())
		                .getDescripcion();
		transferenciaAgendadaDTO.setReferencia(referencia);
		try {
			transferenciaAgendadaDTO.setFechaEjecucion(
			        new SimpleDateFormat(FORMATO_FECHA).parse(transferenciaAgendadaDetalleView.getFecha()));
		} catch (ParseException e) {
			LOGGER.error("Ocurrio un error al parsear las fechas desde la vista al DTO");
		}
		return transferenciaAgendadaDTO;
	}

	/**
	 * Inicializar contador intentos.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @param transferenciaAgendadaDTO         the transferencia agendada DTO
	 */
	private void inicializarContadorIntentosNuevaTransferencia(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView,
	        TransferenciaAgendadaDTO transferenciaAgendadaDTO) {
		String importeMoneda = transferenciaAgendadaDetalleView.getDivisa()
		        + ISBANStringUtils.formatearConComaYDosDecimales(transferenciaAgendadaDetalleView.getImporte());
		String mensajeError = mensajeBO
		        .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_ERROR_GENERICO).getMensaje();
		mensajeError = MessageFormat.format(mensajeError,
		        TransferenciaUtil.obtenerTituloTipoTransferencia(
		                new SimpleDateFormat(FORMATO_FECHA).format(transferenciaAgendadaDTO.getFechaEjecucion()), true),
		        transferenciaAgendadaDTO.getDestinatario().getNombre(), importeMoneda);
		if (sesionParametros.getContador() == null) {
			ContadorIntentos contador = new ContadorIntentos();
			contador.setIdView(transferenciaAgendadaDetalleView.getIdSesion(), 2, mensajeError);
			sesionParametros.setContador(contador);
		} else {
			sesionParametros.getContador().setIdView(transferenciaAgendadaDetalleView.getIdSesion(), 2, mensajeError);
		}
	}

	/**
	 * Grabar estadisticas confirmacion.
	 *
	 * @param isRioRio         the is rio rio
	 * @param isCuentasPropias the is cuentas propias
	 * @param importe          the importe
	 * @param moneda           the moneda
	 * @param cuentaOrigen     the cuenta origen
	 * @param cuentaDestino    the cuenta destino
	 * @param estado           the estado
	 */
	private void grabarEstadisticasConfirmacion(boolean isRioRio, String isCuentasPropias, String importe,
	        String moneda, String cuentaOrigen, String cuentaDestino, String estado) {
		boolean isPropia = "S".equals(isCuentasPropias);

		Estadistica estadistica = new Estadistica();
		estadistica.setCodigoError(estado);
		estadistica.setNroCtaDestino(cuentaDestino);
		estadistica.setNroCtaOrigen(cuentaOrigen);
		estadistica.setMoneda(moneda);
		estadistica.setImporte(importe);
		if (isPropia) {
			estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_CUENTAS_PROPIAS);
		} else if (isRioRio) {
			estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_RIO_RIO);
		} else {
			estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_RIO_OTROS_BANCOS);
		}
		estadisticaManager.add(estadistica);
	}

	/**
	 * Cargar autentificacion DTO con los datos de entrada.
	 *
	 * @see #valorDesafioAgendamientoTransferencias en hbconfig properties
	 * @author manuel.vargas B041299
	 * @param transferenciaView the transferencia agendada detalle view
	 * @return the autentificacion DTO
	 */
	private AutentificacionDTO cargarAutentificacionDTO(AgendamientoTransferenciaView agendamientotransferenciaView) {

		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		autentificacionDTO.setOperacion(Integer.parseInt(valorDesafioTransferenciasAgendadas));
		asignarEstadisticasDeAutenticacion(autentificacionDTO);
		if (agendamientotransferenciaView.getDesafio() != null) {
			autentificacionDTO = agendamientotransferenciaView.getDesafio();
			if (agendamientotransferenciaView.getDesafio().getTipoDesafio().equals(TipoDesafioEnum.TOKEN)) {
				autentificacionDTO.setOperacion(Integer.parseInt(valorDesafioTransferenciasAgendadas));
			}
		}
		// TODO **** LIMPIAR CUANDO SE UNIFIQUE OPERACION COORDENADA, ver
		// @leonardo.medina
		PedidoCoordenada pedidoCoordenada = new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION);
		pedidoCoordenada.setIp(sesionParametros.getRegistroSession().getIp());
		if (autentificacionDTO.getCoordenadasOperacion() != null) {
			autentificacionDTO.getCoordenadasOperacion().setPedidoCoordenada(pedidoCoordenada);
		} else {
			CoordenadasOperacionDTO coordenadasOperacionDTO = new CoordenadasOperacionDTO();
			coordenadasOperacionDTO.setPedidoCoordenada(pedidoCoordenada);
			autentificacionDTO.setCoordenadasOperacion(coordenadasOperacionDTO);
		}
		// ************************
		autentificacionDTO.setRsaDTO(agendamientotransferenciaView);
		return autentificacionDTO;
	}

	/**
	 * Asignar estadisticas de autenticacion de un agendamiento de una
	 * transferencia.
	 *
	 * @param autentificacionDTO the autentificacion DTO
	 */
	private void asignarEstadisticasDeAutenticacion(AutentificacionDTO autentificacionDTO) {
		autentificacionDTO.setCodigoEstadisticaValidacionToken(
		        EstadisticasConstants.SOFT_TOKEN_VALIDACION_AGENDAMIENTO_TRANSFERENCIA_RIO_RIO);
		autentificacionDTO.setCodigoEstadisticaSolicitudCoordenadas(
		        EstadisticasConstants.COORDENADAS_SOLICITUD_AGENDAMIENTO_TRANSFERENCIA_RIO_RIO);
		autentificacionDTO.setCodigoEstadisticaValidacionCoordenadas(
		        EstadisticasConstants.COORDENADAS_VALIDACION_AGENDAMIENTO_TRANSFERENCIA_RIO_RIO);
	}

	/**
	 * Cargar autentificacion DTO con los datos de la vista.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @return the autentificacion DTO
	 */
	private AutentificacionDTO cargarAutentificacionDTO(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		asignarEstadisticasDeAutenticacion(autentificacionDTO, transferenciaAgendadaDetalleView.getIsRioRio());
		autentificacionDTO.setOperacion(Integer.parseInt(valorDesafioTransferenciasAgendadas));
		if (transferenciaAgendadaDetalleView.getDesafio() != null) {
			autentificacionDTO = transferenciaAgendadaDetalleView.getDesafio();
		}
		// TODO **** LIMPIAR CUANDO SE UNIFIQUE OPERACION COORDENADA, ver
		// @leonardo.medina
		PedidoCoordenada pedidoCoordenada = new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION);
		pedidoCoordenada.setIp(sesionParametros.getRegistroSession().getIp());
		if (autentificacionDTO.getCoordenadasOperacion() != null) {
			autentificacionDTO.getCoordenadasOperacion().setPedidoCoordenada(pedidoCoordenada);
		} else {
			CoordenadasOperacionDTO coordenadasOperacionDTO = new CoordenadasOperacionDTO();
			coordenadasOperacionDTO.setPedidoCoordenada(pedidoCoordenada);
			autentificacionDTO.setCoordenadasOperacion(coordenadasOperacionDTO);
		}
		// ************************
		autentificacionDTO.setRsaDTO(transferenciaAgendadaDetalleView);
		return autentificacionDTO;
	}

	/**
	 * Asignar estadisticas de autenticacion.
	 *
	 * @param autentificacionDTO the autentificacion DTO
	 * @param isRioRio           true, si la transfencia es hacia cuentas del mismo
	 *                           banco.
	 */
	private void asignarEstadisticasDeAutenticacion(AutentificacionDTO autentificacionDTO, boolean isRioRio) {
		if (isRioRio) {
			autentificacionDTO.setCodigoEstadisticaSolicitudToken(
			        EstadisticasConstants.SOFT_TOKEN_SOLICITUD_TRANSFERENCIA_AGENDADA_RIO_RIO);
			autentificacionDTO.setCodigoEstadisticaValidacionToken(
			        EstadisticasConstants.SOFT_TOKEN_VALIDACION_TRANSFERENCIA_AGENDADA_RIO_RIO);
			autentificacionDTO.setCodigoEstadisticaSolicitudCoordenadas(
			        EstadisticasConstants.COORDENADAS_SOLICITUD_TRANSFERENCIA_AGENDADA_RIO_RIO);
			autentificacionDTO.setCodigoEstadisticaValidacionCoordenadas(
			        EstadisticasConstants.COORDENADAS_VALIDACION_TRANSFERENCIA_AGENDADA_RIO_RIO);
		} else {
			autentificacionDTO.setCodigoEstadisticaSolicitudToken(
			        EstadisticasConstants.SOFT_TOKEN_SOLICITUD_TRANSFERENCIA_AGENDADA_OTROS_BANCOS);
			autentificacionDTO.setCodigoEstadisticaValidacionToken(
			        EstadisticasConstants.SOFT_TOKEN_VALIDACION_TRANSFERENCIA_AGENDADA_OTROS_BANCOS);
			autentificacionDTO.setCodigoEstadisticaSolicitudCoordenadas(
			        EstadisticasConstants.COORDENADAS_SOLICITUD_TRANSFERENCIA_AGENDADA_OTROS_BANCOS);
			autentificacionDTO.setCodigoEstadisticaValidacionCoordenadas(
			        EstadisticasConstants.COORDENADAS_VALIDACION_TRANSFERENCIA_AGENDADA_OTROS_BANCOS);
		}
	}

	/**
	 * Mapear ejecucion view.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @param transferenciaEjecutadaDTO        the transferencia ejecutada DTO
	 */
	private void mapearEjecucionView(TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView,
	        TransferenciaEjecutadaDTO transferenciaEjecutadaDTO) {
		if (transferenciaAgendadaDetalleView.getIsRioRio()) {
			transferenciaAgendadaDetalleView.setNumeroComprobante(transferenciaEjecutadaDTO.getComprobanteBackEnd());
		} else {
			transferenciaAgendadaDetalleView.setNumeroComprobante(transferenciaEjecutadaDTO.getNroComprobante());
		}
		transferenciaAgendadaDetalleView.setIdTransaccion(transferenciaEjecutadaDTO.getIdTransaccion());
		transferenciaAgendadaDetalleView
		        .setFechaComprobante(ISBANStringUtils.formatearFecha(new Date(), FORMATO_FECHA_COMPROBANTE));
		Date fechaCompensacion = new Date();
		try {
			fechaCompensacion = new SimpleDateFormat(FORMATO_FECHA_SERVICIO)
			        .parse(transferenciaEjecutadaDTO.getFechaCompensacion());
		} catch (ParseException e) {
			LOGGER.error("Ha ocurrido un error al parsear las fechas");
		}
		transferenciaAgendadaDetalleView.setFecha(new SimpleDateFormat(FORMATO_FECHA).format(fechaCompensacion));
		if (transferenciaAgendadaDetalleView.getDesafio() != null) {
			// Eliminamos informacion que no se usa en la vista.
			transferenciaAgendadaDetalleView.setDesafio(null);
		}
	}

	/**
	 * Mapear ejecucion view.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @param transferenciaEjecutadaDTO        the transferencia ejecutada DTO
	 */
	private void mapearEjecucionView(TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView,
	        TransferenciaAgendadaDTO transferenciaEjecutadaDTO) {
		transferenciaAgendadaDetalleView.setNumeroComprobante(transferenciaEjecutadaDTO.getIdRecibo());
		transferenciaAgendadaDetalleView
		        .setFechaComprobante(ISBANStringUtils.formatearFecha(new Date(), FORMATO_FECHA_COMPROBANTE));
		if (transferenciaAgendadaDetalleView.getDesafio() != null) {
			// Eliminamos informacion que no se usa en la vista.
			transferenciaAgendadaDetalleView.setDesafio(null);
		}
	}

	/**
	 * Consultar conceptos transferencia agendada.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ConceptosTransferenciaAgendadaView> consultarConceptosTransferenciaAgendada() {
		Respuesta<ConceptosTransferenciaAgendadaView> respuesta = new Respuesta<ConceptosTransferenciaAgendadaView>();
		ConceptosTransferenciaAgendadaView conceptos = new ConceptosTransferenciaAgendadaView();
		conceptos.setLegalConcepto(obtenerLegalConcepto());
		conceptos.setConceptosAgendaTransferencia(ConceptoTransferenciaEnum.getConceptoView());
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuestaVacia(true);
		respuesta.setRespuesta(conceptos);
		return respuesta;
	}

	/**
	 * Obtener legal concepto.
	 *
	 * @return the string
	 */
	private String obtenerLegalConcepto() {
		try {
			return legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_CONCEPTO);
		} catch (DAOException e) {
			LOGGER.error("Falla al obtener legal concepto.");
			return StringUtils.EMPTY;
		}
	}

	/**
	 * Grabar estadisticas.
	 *
	 * @param codigoTransaccion the codigo transaccion
	 * @param codigoEstadistica the codigo estadistica
	 */
	private void grabarEstadisticas(String codigoTransaccion, String codigoEstadistica) {
		estadisticaManager.add(codigoTransaccion, codigoEstadistica);
	}

	/**
	 * Parsear A cuenta con ceros.
	 *
	 * @param cuenta the cuenta
	 * @return the string
	 */
	private String parsearACuentaConCeros(String cuenta) {
		return ISBANStringUtils.formateadorConCerosIzq(cuenta.substring(4, cuenta.length()).replace("/", ""), 16);
	}

	/**
	 * Loguear estadistica.
	 *
	 * @param stopDebitTransferenciaAgendadaDTO the stop debit transferencia
	 *                                          agendada DTO
	 * @param codigoTransaccion                 the codigo transaccion
	 * @param codigoEstadistica                 the codigo estadistica
	 */
	private void loguearEstadistica(StopDebitTransferenciaAgendadaDTO stopDebitTransferenciaAgendadaDTO,
	        String codigoTransaccion, String codigoEstadistica) {
		Estadistica estadistica = new Estadistica();
		TransferenciaAgendadaDTO transferencia = stopDebitTransferenciaAgendadaDTO.getTransferenciaAgendadaDTO();
		String tmp = ISBANStringUtils
		        .formatearSaldo(stopDebitTransferenciaAgendadaDTO.getTransferenciaAgendadaDTO().getImporte());
		String importe = tmp.replace(".", "").replace(",", "");
		estadistica.setImporte(importe);
		estadistica.setMoneda(transferencia.getMoneda().getSimbolo());
		estadistica.setNroCtaOrigen(transferencia.getNroCuentaOrigen());

		if (StringUtils.isNotEmpty(transferencia.getCbuCuenta())) {
			estadistica.setNroCtaDestino(transferencia.getCbuCuenta());
		} else {
			estadistica.setNroCtaDestino(transferencia.getCuentaDestino());
		}
		estadistica.setCodigoTransaccion(codigoTransaccion);

		estadistica.setCodigoError(codigoEstadistica);
		estadisticaManager.add(estadistica);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * AgendaTransferenciaManager#ejecutarAgendamientoTransferencia(ar.com.
	 * santanderrio.obp.servicios.transferencias.web.view.
	 * TransferenciaAgendadaDetalleView)
	 */
	@Override
	public Respuesta<AgendamientoTransferenciaView> ejecutarAgendamientoTransferencia(
	        AgendamientoTransferenciaView transferenciaView) {

		LOGGER.info(INICIO_DEL_AGENDAMIENTO);

		if (ISBANStringUtils.validarCVU(transferenciaView.getCbu())) {
			DivisaEnum moneda = DivisaEnum.fromMonedaString(transferenciaView.getMoneda().toLowerCase());
			if (!DivisaEnum.PESO.equals(moneda) && trfCvuDolaresHabilitado == 0) {
				return armarRespuestaErrorMonedaCVU();
			}
			String importe = transferenciaView.getImporte().replace(".", "");
			importe = importe.replace(",", ".");
			if (!TransferenciaUtil.validarTransferenciaCVU(new BigDecimal(importe), moneda, trfCvuDolaresHabilitado,
			        trfCVUImporteDolaresMax, trfCVUImportePesosMax)) {
				return armarRespuestaErrorValidacionImporte(moneda);
			}
		}

		cargarDatosNecesariosParaRSA(transferenciaView);

		Respuesta<AgendamientoTransferenciaView> respuestaFinal = llamarRSAyAnalizarDesafiosParaUnAgendamientoTransferencia(
		        transferenciaView);
		if (!EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())) {
			return respuestaFinal;
		}

		inicializarContadorIntentosAgendamientoTransferencia(transferenciaView);
		TransferenciaDTO transferenciaDTO = cargarTransferenciaDTOdeUnaTransferenciaView(transferenciaView);

		Respuesta<TransferenciaDTO> respuestaTransferenciaBO = null;
		if (!transferenciaView.isAutomatica()) {
			respuestaTransferenciaBO = agendaTransferenciaBO
			        .ejecutarAgendamientoRecordatorioTransferencia(transferenciaDTO, sesionCliente.getCliente());
		} else {
			respuestaTransferenciaBO = agendaTransferenciaBO
			        .ejecutarAgendamientoAutomaticoTransferencia(transferenciaDTO, sesionCliente.getCliente());
		}

		switch (respuestaTransferenciaBO.getEstadoRespuesta()) {
		case OK:
			return armarRespuestaOKyGrabarEstadisticas(transferenciaView, transferenciaDTO, respuestaTransferenciaBO);
		case WARNING:
			return armarRespuestaWARNINGyGrabarEstadisticas(transferenciaView, transferenciaDTO,
			        respuestaTransferenciaBO);
		case ERROR:
			return armarRespuestaERRORyGrabarEstadisticas(transferenciaView, respuestaTransferenciaBO);
		default:
			return armarRespuestaERRORyGrabarEstadisticas(transferenciaView, respuestaTransferenciaBO);
		}

	}

	/**
	 * Cargar datos necesarios para RSA.
	 *
	 * @param transferenciaView the transferencia view
	 */
	private void cargarDatosNecesariosParaRSA(AgendamientoTransferenciaView transferenciaView) {
		String nroProductoCuenta = ISBANStringUtils
		        .formateadorConCerosIzq(ISBANStringUtils.extraerCuenta(transferenciaView.getNroCuenta()), 16);
		Cuenta cuentaOrigen = sesionCliente.getCliente().getCuentaPorNumero(nroProductoCuenta);
		if (cuentaOrigen == null) {
			LOGGER.info(MSJ_ERROR_CUENTA_INVALIDA);
			throw new RobotException(MSJ_ERROR_CUENTA_INVALIDA);
		} else {
			transferenciaView.setMonedaAltair(DivisaEnum.fromMonedaString(transferenciaView.getMoneda()).getCodigo());
			if (DivisaEnum.DOLAR.getMoneda().equalsIgnoreCase(transferenciaView.getMoneda())) {
				transferenciaView.setSaldoCuentaOrigen(cuentaOrigen.getSaldoCUDls());
			} else {
				if (cuentaOrigen.getTipoCuenta().equals(CUENTA_UNICA))
					transferenciaView.setSaldoCuentaOrigen(cuentaOrigen.getSaldoCUPesos());
				else
					transferenciaView.setSaldoCuentaOrigen(cuentaOrigen.getSaldoCuenta());
			}
		}
	}

	/**
	 * Este metodo se encarga de llamar a RSA y desafiar, si asi lo requiere, al
	 * usuario. Solo si es una tx agendada automatica.
	 *
	 * @param agendamientoTransferenciaView the agendamiento transferencia view
	 * @return the respuesta
	 */
	private Respuesta<AgendamientoTransferenciaView> llamarRSAyAnalizarDesafiosParaUnAgendamientoTransferencia(
	        AgendamientoTransferenciaView agendamientoTransferenciaView) {

		if (isAgendamientoValido(agendamientoTransferenciaView)) {
			if (agendamientoTransferenciaView.isAutomatica() && !agendamientoTransferenciaView.isCuentaPropia()) {
				Respuesta<AutentificacionDTO> respuestaAutentificacion = autentificacionManager
				        .ejecutarValidacionRSA(cargarAutentificacionDTO(agendamientoTransferenciaView));
				Respuesta<AgendamientoTransferenciaView> respuesta = new Respuesta<AgendamientoTransferenciaView>();
				respuesta.setEstadoRespuesta(respuestaAutentificacion.getEstadoRespuesta());
				respuesta.setItemMensajeRespuesta(respuestaAutentificacion.getItemsMensajeRespuesta());
				agendamientoTransferenciaView.setDesafio(respuestaAutentificacion.getRespuesta());
				respuesta.setRespuesta(agendamientoTransferenciaView);
				return respuesta;
			} else {
				LOGGER.info(TRANSFERENCIA_DE_RECORDATORIO_NO_INCLUYE_SEGUNDO_FACTOR_DE_DESAFIO);
				return respuestaFactory.crearRespuestaOk(AgendamientoTransferenciaView.class,
				        agendamientoTransferenciaView);
			}
		} else {
			// TODO: @manuel.vargas validar si esta bien que tire un robot
			// exception
			throw new RobotException(LA_TRANSFERENCIA_ES_INVALIDA);
		}

	}

	/**
	 * Armar respuesta O ky grabar estadisticas.
	 *
	 * @param transferenciaView        the transferencia view
	 * @param transferenciaDTO         the transferencia DTO
	 * @param respuestaTransferenciaBO the respuesta transferencia BO
	 * @return the respuesta
	 */
	private Respuesta<AgendamientoTransferenciaView> armarRespuestaOKyGrabarEstadisticas(
	        AgendamientoTransferenciaView transferenciaView, TransferenciaDTO transferenciaDTO,
	        Respuesta<TransferenciaDTO> respuestaTransferenciaBO) {
		LOGGER.info(MSJ_INFO_AGENDAMIENTO_TRANSFERENCIA_CON_EXITO);
		Respuesta<AgendamientoTransferenciaView> respuestaFinal = respuestaFactory
		        .crearRespuestaOk(AgendamientoTransferenciaView.class);
		transferenciaView = generarTransferenciaView(transferenciaView, respuestaTransferenciaBO.getRespuesta());
		respuestaFinal.setRespuesta(transferenciaView);
		grabarEstadisticasConfirmacion(transferenciaView.getIsRioRio(), transferenciaView.isCuentaPropia(),
		        transferenciaView.isAutomatica(), transferenciaDTO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFinal;
	}

	/**
	 * Armar respuesta ERRO ry grabar estadisticas.
	 *
	 * @param transferenciaView        the transferencia view
	 * @param respuestaTransferenciaBO the respuesta transferencia BO
	 * @return the respuesta
	 */
	private Respuesta<AgendamientoTransferenciaView> armarRespuestaERRORyGrabarEstadisticas(
	        AgendamientoTransferenciaView transferenciaView, Respuesta<TransferenciaDTO> respuestaTransferenciaBO) {
		Respuesta<AgendamientoTransferenciaView> respuestaFinal;
		LOGGER.info(MSJ_INFO_AGENDAMIENTO_TRANSFERENCIA_CON_ERROR);
		respuestaFinal = new Respuesta<AgendamientoTransferenciaView>();
		respuestaFinal.setEstadoRespuesta(respuestaTransferenciaBO.getEstadoRespuesta());
		respuestaFinal.setItemMensajeRespuesta(respuestaTransferenciaBO.getItemsMensajeRespuesta());
		respuestaFinal.setRespuesta(transferenciaView);
		return respuestaFinal;
	}

	/**
	 * Armar respuesta WARNIN gy grabar estadisticas.
	 *
	 * @param transferenciaView        the transferencia view
	 * @param transferenciaDTO         the transferencia DTO
	 * @param respuestaTransferenciaBO the respuesta transferencia BO
	 * @return the respuesta
	 */
	private Respuesta<AgendamientoTransferenciaView> armarRespuestaWARNINGyGrabarEstadisticas(
	        AgendamientoTransferenciaView transferenciaView, TransferenciaDTO transferenciaDTO,
	        Respuesta<TransferenciaDTO> respuestaTransferenciaBO) {
		Respuesta<AgendamientoTransferenciaView> respuestaFinal;
		LOGGER.info(MSJ_INFO_AGENDAMIENTO_TRANSFERENCIA_CON_WARNING);
		respuestaFinal = new Respuesta<AgendamientoTransferenciaView>();
		respuestaFinal.setEstadoRespuesta(respuestaTransferenciaBO.getEstadoRespuesta());
		respuestaFinal.setItemMensajeRespuesta(respuestaTransferenciaBO.getItemsMensajeRespuesta());
		grabarEstadisticasConfirmacion(transferenciaView.getIsRioRio(), transferenciaView.isCuentaPropia(),
		        transferenciaView.isAutomatica(), transferenciaDTO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return sesionParametros.getContador().excedeReintentos(transferenciaView.getIdSesion(), respuestaFinal);
	}

	/**
	 * Inicializar contador intentos agendamiento transferencia.
	 *
	 * @param transferenciaView the transferencia view
	 */
	private void inicializarContadorIntentosAgendamientoTransferencia(AgendamientoTransferenciaView transferenciaView) {
		String importeMoneda = DivisaEnum.fromMonedaString(transferenciaView.getMoneda()).getSimbolo() + " "
		        + ISBANStringUtils.agregadorDecimales(transferenciaView.getImporte());
		String mensajeError = mensajeBO
		        .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_AGENDAMIENTO_TRANSFERENCIA).getMensaje();
		mensajeError = MessageFormat.format(mensajeError, transferenciaView.getTitular(), importeMoneda);
		if (sesionParametros.getContador() == null) {
			ContadorIntentos contador = new ContadorIntentos();
			contador.setIdView(transferenciaView.getIdSesion(), 2, mensajeError);
			sesionParametros.setContador(contador);
		} else {
			sesionParametros.getContador().setIdView(transferenciaView.getIdSesion(), 2, mensajeError);
		}
	}

	/**
	 * Checks if is agendamiento valido. Para saber si es valido: no se necesita
	 * saber si es cuenta propia.
	 *
	 * @param agendamiento the agendamiento
	 * @return true, if is agendamiento valido
	 */
	private boolean isAgendamientoValido(AgendamientoTransferenciaView agendamiento) {
		if (!agendamiento.isCuentaPropia() && agendamiento.isAutomatica()) {
			String nroProductoCuenta = ISBANStringUtils
			        .formateadorConCerosIzq(ISBANStringUtils.extraerCuenta(agendamiento.getNroCuenta()), 16);
			Cuenta cuentaOrigen = sesionCliente.getCliente().getCuentaPorNumero(nroProductoCuenta);
			if (cuentaOrigen == null) {
				LOGGER.info(MSJ_ERROR_CUENTA_INVALIDA);
				return false;
			}
			return true;
		} else {
			// TODO:evaluar hay que agregar validaciones de seguridad de trans.
			// recordatorio.
			return true;
		}
	}

	/**
	 * Generar transferencia view.
	 *
	 * @param view          the view
	 * @param transferencia the transferencia
	 * @return the agendamiento transferencia view
	 */
	private AgendamientoTransferenciaView generarTransferenciaView(AgendamientoTransferenciaView view,
	        TransferenciaDTO transferencia) {

		Date fechaProgramada = transferencia.getFechaProgramada();
		Cuenta cuentaOrigen = transferencia.getCuentaOrigen();
		view.setTitular(ISBANStringUtils.convertirStringToCamelcase(transferencia.getTitular()));
		view.setImporte(
		        ISBANStringUtils.formatearSaldoSinAbsConDivisa(transferencia.getImporte(), transferencia.getMoneda()));
		view.setFechaEjecucion(new SimpleDateFormat(FORMATO_FECHA).format(fechaProgramada));
		view.setFrecuencia(transferencia.getFrecuencia().getDescripcion());
		view.setNroCuenta(TransferenciaUtil.formatearNumeroCuenta(cuentaOrigen));
		if (TipoCuenta.CUENTA_UNICA_DOLARES.equals(cuentaOrigen.getTipoCuentaEnum())
		        || TipoCuenta.CUENTA_UNICA_PESOS.equals(cuentaOrigen.getTipoCuentaEnum())) {
			view.setTipoCuentaDescripcion(cuentaOrigen.getTipoCuentaEnum().getDescripcion());
		} else {
			view.setTipoCuentaDescripcion(cuentaOrigen.getTipoCuentaEnum().getDescripcionConMoneda());
		}
		view.setCuit(ISBANStringUtils.formatearCuil(transferencia.getCuit()));
		view.setCbu(transferencia.getCbuCuenta());
		String bancoDestino = transferencia.getBancoDestino();

		if (bancoDestino != null && transferencia.isHaciaOtroBanco()) {
			view.setBanco(ISBANStringUtils.convertirStringToCamelcase(bancoDestino));
		} else {
			view.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
		}

		if (transferencia.getFechaDeOperacion() != null) {
			view.setFechaOperacion(new SimpleDateFormat(FORMATO_FECHA).format(transferencia.getFechaDeOperacion()));
			view.setNumeroComprobante(transferencia.getNumeroComprobante());
		} else {
			view.setFechaOperacion(transferencia.getFechaCompensacion());
			view.setNumeroComprobante(transferencia.getIdRecibo().substring(8, transferencia.getIdRecibo().length()));
		}
		if (transferencia.getConcepto().getDescripcion() != null) {
			view.setConcepto(new ConceptoView(transferencia.getConcepto().getDescripcion(),
			        transferencia.getConcepto().getOrdinal(), transferencia.getConcepto().getCodigo(),
			        transferencia.getConcepto().getDescripcionAbreviada(),
			        transferencia.getConcepto().getHabilitaLegal()));
		}
		if (transferencia.getDescripcionAdicional() != null) {
			view.setDescripcion(transferencia.getDescripcionAdicional());
		}
		view.setMensaje(transferencia.getMensaje());
		String email = transferencia.getEmail();
		view.setEmail(email);
		view.setEnviaEmail(email == null || email.trim().isEmpty() ? "No" : "Si");
		view.setMensajeEmail(transferencia.getEmailMensaje());
		if (!transferencia.isHaciaOtroBanco() && transferencia.getNumeroCuentaDestino() != null
		        && transferencia.getTipoCuentaDestino() != null) {
			view.setNroCuentaDestino(transferencia.getNumeroCuentaDestino().toString());
			if (TipoCuenta.CUENTA_UNICA_DOLARES.equals(transferencia.getTipoCuentaDestino())
			        || TipoCuenta.CUENTA_UNICA_PESOS.equals(transferencia.getTipoCuentaDestino())) {
				view.setTipoCuentaDestino(transferencia.getTipoCuentaDestino().getDescripcion());
				view.setTipoCuentaDestinoDescripcion(transferencia.getTipoCuentaDestino().getDescripcion());
			} else {
				view.setTipoCuentaDestino(transferencia.getTipoCuentaDestino().getDescripcionConMoneda());
				view.setTipoCuentaDestinoDescripcion(transferencia.getTipoCuentaDestino().getDescripcionConMoneda());
			}
		}

		view.setTitular(transferencia.getNombreReceptor());

		return view;
	}

	/**
	 * Grabar estadisticas confirmacion.
	 *
	 * @param isRioRio         the is rio rio
	 * @param isCuentasPropias the is cuentas propias
	 * @param isAutomatica     the is automatica
	 * @param transferenciaDTO the transferencia DTO
	 * @param estado           the estado
	 */
	private void grabarEstadisticasConfirmacion(boolean isRioRio, boolean isCuentasPropias, boolean isAutomatica,
	        TransferenciaDTO transferenciaDTO, String estado) {
		Estadistica estadistica = new Estadistica();
		estadistica.setCodigoError(estado);
		if (isAutomatica) {
			armarEstadisticasAutomaticas(isRioRio, isCuentasPropias, estadistica, transferenciaDTO);
		} else {
			armarEstadisticasRecordatorios(isRioRio, isCuentasPropias, estadistica);
		}
		estadisticaManager.add(estadistica);
	}

	/**
	 * Armar estadisticas recordatorios.
	 *
	 * @param isRioRio         the is rio rio
	 * @param isCuentasPropias the is cuentas propias
	 * @param estadistica      the estadistica
	 */
	private void armarEstadisticasRecordatorios(boolean isRioRio, boolean isCuentasPropias, Estadistica estadistica) {
		if (isCuentasPropias) {
			estadistica.setCodigoTransaccion(
			        EstadisticasConstants.CODIGO_ESTADISTICA_AGENDAMIENTO_TRANSFERENCIA_RECORDATORIO_CUENTAS_PROPIAS);
		} else if (isRioRio) {
			estadistica.setCodigoTransaccion(
			        EstadisticasConstants.CODIGO_ESTADISTICA_AGENDAMIENTO_TRANSFERENCIA_RECORDATORIO_TERCEROS);
		} else {
			estadistica.setCodigoTransaccion(
			        EstadisticasConstants.CODIGO_ESTADISTICA_AGENDAMIENTO_TRANSFERENCIA_RECORDATORIO_OB);
		}
	}

	/**
	 * Armar estadisticas automaticas.
	 *
	 * @param isRioRio         the is rio rio
	 * @param isCuentasPropias the is cuentas propias
	 * @param estadistica      the estadistica
	 * @param transferenciaDTO the transferencia DTO
	 */
	private void armarEstadisticasAutomaticas(boolean isRioRio, boolean isCuentasPropias, Estadistica estadistica,
	        TransferenciaDTO transferenciaDTO) {
		estadistica.setNroCtaDestino(transferenciaDTO.getNumeroCuentaDestino().getNroCuentaProducto());
		estadistica.setNroCtaOrigen(transferenciaDTO.getCuentaOrigen().getNroCuentaProducto());
		estadistica.setMoneda(transferenciaDTO.getMoneda().getSimbolo());
		estadistica.setImporte(transferenciaDTO.getImporte().toString());
		if (isCuentasPropias) {
			estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_AUTOMATICO_CUENTAS_PROPIAS);
		} else if (isRioRio) {
			estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_AUTOMATICO_RIO_RIO);
		} else {
			estadistica.setCodigoTransaccion(
			        EstadisticasConstants.CODIGO_ESTADISTICA_AGENDAMIENTO_TRANSFERENCIA_AUTOMATICO_OB);
		}
	}

	/**
	 * Cargar transferencia DT ode una transferencia view.
	 *
	 * @param transferenciaView the transferencia view
	 * @return the transferencia DTO
	 */
	// Pasar a un Utils
	public TransferenciaAgendadaDTO cargarTransferenciaDTOdeUnaTransferenciaView(TransferenciaView transferenciaView) {
		TransferenciaAgendadaDTO transferenciaDTO = new TransferenciaAgendadaDTO();
		transferenciaDTO.setFechaProgramada(ISBANStringUtils.formatearFecha(transferenciaView.getFechaEjecucion()));
		transferenciaDTO.setCuentaOrigen(sesionCliente.getCliente()
		        .getCuentaPorNumero(TransferenciaUtil.parsearACuentaConCeros(transferenciaView.getNroCuenta())));
		if (!transferenciaView.getIsRioRio()) {
			transferenciaDTO.setCbuCuenta(transferenciaView.getCbu());
		} else {
			transferenciaDTO.setTipoCuentaDestino(
			        TipoCuenta.fromDescripcionConMoneda(transferenciaView.getTipoCuentaDestinoDescripcion()));
			transferenciaDTO.setNumeroCuentaDestino(
			        ISBANStringUtils.cargarCuentaDestinatario(transferenciaView.getNroCuentaDestino()));
			if (transferenciaDTO.getTipoCuentaDestino().getCodigo() == 2) {
				transferenciaDTO.setTipoCuentaDestino(
				        TransferenciaUtil.matchearTipoCuentaDestino(transferenciaView.getMoneda()));
			}
		}
		String importe = transferenciaView.getImporte().replace(".", "");
		transferenciaDTO.setImporte(new BigDecimal(importe.replace(",", ".")));
		transferenciaDTO.setVaPorCoelsa(obtenerVaPorCoelsa(transferenciaView));
		transferenciaDTO.setNombreReceptor(transferenciaView.getTitular());
		transferenciaDTO.setTitular(transferenciaView.getTitular());
		transferenciaDTO
		        .setConcepto(ConceptoTransferenciaEnum.getConceptoFromOrdinal(transferenciaView.getConcepto().getId()));
		transferenciaDTO.setDescripcionConcepto(transferenciaView.getDescripcion());
		transferenciaDTO.setInformacionAdicional(transferenciaView.getDescripcion());
		transferenciaDTO.setReferencia(transferenciaView.getDescripcion());
		transferenciaDTO.setEmail(transferenciaView.getEmail());
		transferenciaDTO.setEmailMensaje(transferenciaView.getMensajeEmail());
		transferenciaDTO.setComentario(
		        StringUtils.isNotEmpty(transferenciaView.getMensajeEmail()) ? transferenciaView.getMensajeEmail() : "");
		transferenciaDTO.setHaciaCuentaPropia(transferenciaView.isCuentaPropia());
		transferenciaDTO.setHaciaOtroBanco(!transferenciaView.getIsRioRio());
		transferenciaDTO.setMoneda(DivisaEnum.fromMonedaString(transferenciaView.getMoneda().toLowerCase()));
		transferenciaDTO.setBancoDestino(transferenciaView.getBanco());
		transferenciaDTO.setCuit(transferenciaView.getCuit());
		transferenciaDTO
		        .setFrecuencia(FrecuenciaTransferenciaAgendada.fromDescripcion(transferenciaView.getFrecuencia()));
		transferenciaDTO.setIsAutomatica(transferenciaView.isAutomatica());
		return transferenciaDTO;
	}

	/**
	 * Obtener va por coelsa.
	 *
	 * @param transferenciaView the transferencia view
	 * @return true, if successful
	 */
	private boolean obtenerVaPorCoelsa(TransferenciaView transferenciaView) {
		if (transferenciaView.isTransferenciaManual()) {
			return true;
		}
		if (!StringUtils.isBlank(transferenciaView.getCbu())
		        && ISBANStringUtils.validarCVU(transferenciaView.getCbu())) {
			return true;
		}
		if ("48 Horas".equals(transferenciaView.getFechaAcreditacion())) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * AgendaTransferenciaManager#ejecutarModificacionTransferenciaAgendada(ar.
	 * com.santanderrio.obp.servicios.transferencias.web.view.
	 * TransferenciaAgendadaView)
	 */
	@Override
	public Respuesta<TransferenciaAgendadaDetalleView> ejecutarModificacionDeTransferenciaAgendada(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {

		LOGGER.info(INICIO_MODIFICACION);

		TransferenciaAgendadaDTO transferenciaAgendadaDTO = getAgendaTransferenciaSession(
		        Long.parseLong(transferenciaAgendadaDetalleView.getId()));

		if (transferenciaAgendadaDTO != null && ISBANStringUtils.validarCVU(transferenciaAgendadaDTO.getCbuCuenta())) {
			DivisaEnum moneda = transferenciaAgendadaDTO.getMoneda();
			if (!DivisaEnum.PESO.equals(moneda) && trfCvuDolaresHabilitado == 0) {
				return armarRespuestaErrorMonedaCVU();
			}
			String importe = transferenciaAgendadaDetalleView.getImporte().replace(".", "");
			importe = importe.replace(",", ".");
			if (!TransferenciaUtil.validarTransferenciaCVU(new BigDecimal(importe), moneda, trfCvuDolaresHabilitado,
			        trfCVUImporteDolaresMax, trfCVUImportePesosMax)) {
				return armarRespuestaErrorValidacionImporte(transferenciaAgendadaDTO.getMoneda());
			}
		}

		inicializarContadorIntentosModificacionTransferencia(transferenciaAgendadaDetalleView);

		cargarTransferenciaDTOdeUnaTransferenciaView(transferenciaAgendadaDetalleView, transferenciaAgendadaDTO);
		String tipoAgendamiento = false == transferenciaAgendadaDetalleView.getIsRecordatorio()
		        ? TIPO_AGENDAMIENTO_AUTOMATICO
		        : TIPO_AGENDAMIENTO_RECORDATORIO;
		Respuesta<TransferenciaAgendadaDTO> respuestaModificacionTransferenciaBO = agendaTransferenciaBO
		        .ejecutarModificacionDeTransferenciaAgendada(transferenciaAgendadaDTO, tipoAgendamiento,
		                sesionCliente.getCliente());

		switch (respuestaModificacionTransferenciaBO.getEstadoRespuesta()) {
		case OK:
			return armarRespuestaModificacionOKyGrabarEstadisticas(transferenciaAgendadaDetalleView,
			        transferenciaAgendadaDTO, respuestaModificacionTransferenciaBO);
		case ERROR:
			return armarRespuestaModificarERRORyGrabarEstadisticas(transferenciaAgendadaDetalleView,
			        transferenciaAgendadaDTO, respuestaModificacionTransferenciaBO);
		default:
			return armarRespuestaModificarERRORyGrabarEstadisticas(transferenciaAgendadaDetalleView,
			        transferenciaAgendadaDTO, respuestaModificacionTransferenciaBO);
		}
	}

	/**
	 * armarRespuestaErrorValidacionImporte.
	 *
	 * @param <T>    the generic type
	 * @param moneda the moneda
	 * @return the respuesta
	 */
	private <T> Respuesta<T> armarRespuestaErrorValidacionImporte(DivisaEnum moneda) {
		Respuesta<T> respuesta = new Respuesta<T>();
		respuesta.setRespuestaVacia(Boolean.TRUE);
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		String importeMax = DivisaEnum.PESO.equals(moneda) ? trfCVUImportePesosMax : trfCVUImporteDolaresMax;
		String mensajeError = MessageFormat.format(MSG_ERROR_IMPORTE_LIMITE_CVU,
		        moneda.getSimbolo().concat(importeMax));
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(mensajeError);
		itemMensajeRespuesta.setTag(IMPORTE);
		itemMensajeRespuesta.setTipoError(TipoError.ERROR_IMPORTE_LIMITE_TRANSFERENCIA.getDescripcion());
		respuesta.add(itemMensajeRespuesta);
		return respuesta;
	}

	/**
	 * Inicializar contador intentos modificacion transferencia.
	 * 
	 * @author B041299 Manuel
	 * @param transferenciaAgendadaView the transferencia view
	 */
	private void inicializarContadorIntentosModificacionTransferencia(
	        TransferenciaAgendadaView transferenciaAgendadaView) {
		String mensajeError = mensajeBO
		        .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MODIFICACION_TRANSFERENCIA_AGENDADA_ERROR)
		        .getMensaje();
		mensajeError = MessageFormat.format(mensajeError, transferenciaAgendadaView.getDestinatarioNombre());
		if (sesionParametros.getContador() == null) {
			ContadorIntentos contador = new ContadorIntentos();
			contador.setIdView(transferenciaAgendadaView.getIdSesion(), 2, mensajeError);
			sesionParametros.setContador(contador);
		} else {
			sesionParametros.getContador().setIdView(transferenciaAgendadaView.getIdSesion(), 2, mensajeError);
		}
	}

	/**
	 * Armar respuesta ERROR, reintentos y grabar estadisticas, al modificar
	 * transferencia.
	 *
	 * @author B041299 Manuel.
	 * @param transferenciaAgendadaDetalleView     the transferencia agendada
	 *                                             detalle view
	 * @param transferenciaAgendadaDTO             the transferencia agendada DTO
	 * @param respuestaModificacionTransferenciaBO the respuesta modificacion
	 *                                             transferencia BO
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaAgendadaDetalleView> armarRespuestaModificarERRORyGrabarEstadisticas(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView,
	        TransferenciaAgendadaDTO transferenciaAgendadaDTO,
	        Respuesta<TransferenciaAgendadaDTO> respuestaModificacionTransferenciaBO) {
		LOGGER.info(MSJ_INFO_MODIFICACION_TRANSFERENCIA_CON_ERROR);
		estadisticaManager.add(EstadisticasConstants.CODIGO_ESTADISTICA_EJECUCION_MODIFICACION_TRANSFERENCIA_AUTO,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		Respuesta<TransferenciaAgendadaDetalleView> respuestaFinal = new Respuesta<TransferenciaAgendadaDetalleView>();

		respuestaFinal.setEstadoRespuesta(respuestaModificacionTransferenciaBO.getEstadoRespuesta());
		respuestaFinal.setItemMensajeRespuesta(respuestaModificacionTransferenciaBO.getItemsMensajeRespuesta());
		respuestaFinal.setRespuesta(transferenciaAgendadaDetalleView);
		sesionParametros.getContador().excedeReintentos(transferenciaAgendadaDetalleView.getIdSesion(), respuestaFinal);

		return respuestaFinal;
	}

	/**
	 * Cargar transferencia DTO de una transferencia view con los datos variables.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @param transferenciaAgendadaDTO         the transferencia agendada DTO
	 * @return the transferencia agendada DTO
	 */
	private void cargarTransferenciaDTOdeUnaTransferenciaView(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView,
	        TransferenciaAgendadaDTO transferenciaAgendadaDTO) {
		transferenciaAgendadaDTO.setHaciaOtroBanco(!transferenciaAgendadaDetalleView.getIsRioRio());
		transferenciaAgendadaDTO
		        .setFechaProgramada(ISBANStringUtils.formatearFecha(transferenciaAgendadaDetalleView.getFecha()));
		transferenciaAgendadaDTO.setFrecuencia(
		        FrecuenciaTransferenciaAgendada.fromDescripcion(transferenciaAgendadaDetalleView.getFrecuencia()));
		transferenciaAgendadaDTO.setConcepto(ConceptoTransferenciaEnum
		        .getConceptoFromOrdinal(transferenciaAgendadaDetalleView.getConcepto().getId()));
		transferenciaAgendadaDTO.setDescripcionConcepto(transferenciaAgendadaDetalleView.getDescripcion());
		transferenciaAgendadaDTO.setInformacionAdicional(transferenciaAgendadaDetalleView.getDescripcion());
		transferenciaAgendadaDTO.setReferencia(transferenciaAgendadaDetalleView.getDescripcion());
		String importe = transferenciaAgendadaDetalleView.getImporte().replace(".", "");
		importe = importe.replace(",", ".");
		transferenciaAgendadaDTO.setImporte(new BigDecimal(importe));
		transferenciaAgendadaDTO.setEmail(transferenciaAgendadaDetalleView.getEmail());
		transferenciaAgendadaDTO.setEmailMensaje(transferenciaAgendadaDetalleView.getMensajeEmail());

	}

	/**
	 * Mapear transferencia DTO.
	 *
	 * @param transferenciaAgendadaDTO the transferencia agendada DTO
	 * @return the transferencia DTO
	 */
	@SuppressWarnings("deprecation")
	public TransferenciaDTO mapearTransferenciaDTO(TransferenciaAgendadaDTO transferenciaAgendadaDTO) {
		TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
		TransferenciaAgendada transferenciaAgendada = transferenciaAgendadaDTO.getDatosOrigen()
		        .getTransferenciaAgendada();
		transferenciaDTO.setFechaProgramada(new Date(transferenciaAgendada.getFechaAgendamiento()));
		transferenciaDTO
		        .setFrecuencia(FrecuenciaTransferenciaAgendada.fromDescripcion(transferenciaAgendada.getRecurrencia()));
		transferenciaDTO.setCuentaOrigen(sesionCliente.getCliente()
		        .getCuentaPorNumero(TransferenciaUtil.parsearACuentaConCeros(transferenciaAgendada.getCuentaPropia())));
		transferenciaDTO.setMoneda(transferenciaAgendadaDTO.getMoneda());
		// transferenciaDTO.setTipoCuenta
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * AgendaTransferenciaManager#descargarComprobantePDF(ar.com.santanderrio.
	 * obp.servicios.transferencias.web.view.TransferenciaAgendadaDetalleView)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobantePDF(
	        TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;

		String hashViewInput = HashUtils.obtenerHash(crearMapaDeTransferenciaView(transferenciaAgendadaDetalleView));
		HashUtils.compareHash(sesionParametros.getValidacionHash(), hashViewInput);

		reporte = reporteBO.obtenerComprobantePDF(mapear(transferenciaAgendadaDetalleView));
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(
			        EstadisticasConstants.DESCARGA_PDF_COMPROBANTES_TRANSFERENCIA_PROGRAMADA_DE_RECORDATORIO,
			        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(
			        EstadisticasConstants.DESCARGA_PDF_COMPROBANTES_TRANSFERENCIA_PROGRAMADA_DE_RECORDATORIO,
			        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/**
	 * Mapear TransferenciaAgendadaDetalleView a DetalleComprobanteView.
	 *
	 * @param transferenciaAgendadaDetalleView the transferencia agendada detalle
	 *                                         view
	 * @return the detalle comprobante view
	 */
	private DetalleComprobanteView mapear(TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {
		DetalleComprobanteTransferenciaView detalle = new DetalleComprobanteTransferenciaView();
		detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_TRANSFERENCIA.getDetalle());
		if ("$".equals(transferenciaAgendadaDetalleView.getDivisa())) {
			detalle.setImportePesos(
			        ISBANStringUtils.formatearSaldo(new BigDecimal(transferenciaAgendadaDetalleView.getImporte())));
		} else {
			detalle.setImporteDolares(
			        ISBANStringUtils.formatearSaldo(new BigDecimal(transferenciaAgendadaDetalleView.getImporte())));
		}
		detalle.setNroCuentaOrigen(transferenciaAgendadaDetalleView.getOrigenNumero());
		detalle.setTipoCuentaOrigen(transferenciaAgendadaDetalleView.getOrigenTipo());
		if (StringUtils.isNotBlank(transferenciaAgendadaDetalleView.getCbu())) {
			detalle.setNroCuentaDestino(transferenciaAgendadaDetalleView.getCbu());
			detalle.setTipoCuentaDestino("CBU");
		} else {
			detalle.setNroCuentaDestino(transferenciaAgendadaDetalleView.getDestinoNumero());
			detalle.setTipoCuentaDestino(transferenciaAgendadaDetalleView.getDestinoTipo());
		}
		if (!transferenciaAgendadaDetalleView.getIsRioRio()) {
			detalle.setCuit(transferenciaAgendadaDetalleView.getNumeroCuilCuit());
		}
		detalle.setTipoCuit(CuitEnum.CUIT_DESTINATARIO_TRANSFERENCIAS.getDetalle());
		detalle.setBanco(transferenciaAgendadaDetalleView.getDestinatarioBanco());
		detalle.setDestinatario(transferenciaAgendadaDetalleView.getDestinatarioNombre());
		detalle.setFecha(transferenciaAgendadaDetalleView.getFecha());
		detalle.setFechaOperacion(transferenciaAgendadaDetalleView.getFechaComprobante());
		detalle.setPlazoAcreditacion(transferenciaAgendadaDetalleView.getPlazoAcreditacion());
		detalle.setConcepto(transferenciaAgendadaDetalleView.getConcepto().getDescripcion());
		detalle.setDesConcepto(transferenciaAgendadaDetalleView.getDescripcion());
		String mailActivo = null;
		if (null != transferenciaAgendadaDetalleView.getEmailActivo()) {
			mailActivo = transferenciaAgendadaDetalleView.getEmailActivo() ? "SI" : "NO";
		}
		detalle.setAvisoTransfMail(mailActivo);
		detalle.setNroComprobante(transferenciaAgendadaDetalleView.getNumeroComprobante());
		detalle.setMail(transferenciaAgendadaDetalleView.getEmail());
		detalle.setComentarios(transferenciaAgendadaDetalleView.getMensajeEmail());
		return detalle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * AgendaTransferenciaManager#
	 * grabarEstadisticasComprobanteAgendamientoTransferencia()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticasComprobanteAgendamientoTransferencia() {
		LOGGER.info("Grabando estadisicas: ",
		        EstadisticasConstants.ESTADISTICAS_COMPROBANTE_AGENDAMIENTO_TRANSFERENCIA);
		estadisticaManager.add(EstadisticasConstants.ESTADISTICAS_COMPROBANTE_AGENDAMIENTO_TRANSFERENCIA,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		return respuestaFactory.crearRespuestaOk(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * AgendaTransferenciaManager#volverAgendaTransferencias()
	 */
	@Override
	public void volverAgendaTransferencias() {
		sesionParametros.setRsaRiesgoTransferenciaDTO(null);
	}

}
/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.manager.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.api.accounts.AccountsApi;
import ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances.IFundsMessageHelper;
import ar.com.santanderrio.obp.servicios.api.transfers.scoring.ScoringApi;
import ar.com.santanderrio.obp.servicios.api.transfers.scoring.exception.ScoringApiException;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchTransferInfoDTO;
import ar.com.santanderrio.obp.servicios.biocatch.model.ActivityName;
import ar.com.santanderrio.obp.servicios.biocatch.model.ActivityType;
import ar.com.santanderrio.obp.servicios.transferencias.bo.DestinatariosFrecuentesBO;
import ar.com.santanderrio.obp.servicios.transferencias.bo.MetricBuilder;
import ar.com.santanderrio.obp.servicios.transferencias.bo.MetricRegisterBO;
import ar.com.santanderrio.obp.servicios.transferencias.dao.AlycsDAO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.DestinatariosFrecuentesDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.DetalleError;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.TransferMetricInfoDTO;
import ar.com.santanderrio.obp.servicios.transferenciarsaapi.connector.TransferenciaRsaApi;
import ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto.TransferenciaCreationRequest;
import ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto.TransferenciaSumResponse;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.TransferStatus;
import ar.com.santanderrio.obp.servicios.transferencias.utils.TransferUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
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
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AltaDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.AgendaDestinatarioDAO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.alias.exception.AliasCorrespondienteCuentaPropiaUnicaException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteEstadoEnum;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO;
import ar.com.santanderrio.obp.servicios.comun.contrato.dao.ContratoDAO;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.TipoContratoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.web.view.AyudaView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.TipoDeCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dto.CuentaIntermedioDTO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaRiesgoTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserResponseData;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;
import ar.com.santanderrio.obp.servicios.transferencias.bo.TransferenciaBO;
import ar.com.santanderrio.obp.servicios.transferencias.dao.OperacionBancaPrivadaTransferenciaDAO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.InsertarTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransInmediataOBBuilder;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransInmediataRioBuilder;
import ar.com.santanderrio.obp.servicios.transferencias.entities.Transferencia2;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.TransferenciaManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.TransferenciaPorAliasManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConceptoView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;
import ar.com.santanderrio.obp.servicios.biocatch.BiocatchManager;

/**
 * The Class TransferenciaManagerImpl.
 *
 * @author B039636
 */
@Component
public class TransferenciaManagerImpl extends Transferencia2 implements TransferenciaManager {

	/** The Constant SOLICITANDO_TRANSFERENCIA_POR_CBU_O_CUENTA. */
	private static final String SOLICITANDO_TRANSFERENCIA_POR_CBU_O_CUENTA = "Solicitando transferencia por cbu o cuenta...";

	/** The Constant SOLICITANDO_TRANSFERENCIA_POR_ALIAS. */
	private static final String SOLICITANDO_TRANSFERENCIA_POR_ALIAS = "Solicitando transferencia por alias...";

	/** The Constant EJECUTANDO_TRANSFERENCIA_CON_RIESGO_ALTO. */
	private static final String EJECUTANDO_TRANSFERENCIA_CON_RIESGO_ALTO = "Ejecutando transferencia con riesgo Alto.";

	/** The Constant LA_NUEVA_FECHA_DE_EJECUCION_ES. */
	private static final String LA_NUEVA_FECHA_DE_EJECUCION_ES = "La nueva fecha de ejecucion es: ";

	/**
	 * The Constant
	 * OCURRIO_UN_ERROR_AL_VERIFICAR_CUAL_DESAFIO_TIENE_ACTIVO_EL_CLIENTE.
	 */
	private static final String OCURRIO_UN_ERROR_AL_VERIFICAR_CUAL_DESAFIO_TIENE_ACTIVO_EL_CLIENTE = "Ocurrio un error al verificar cual desafio tiene activo el cliente.";

	/** The Constant LA_TRANSFERENCIA_TIENE_RIESGO_ALTO. */
	private static final String LA_TRANSFERENCIA_TIENE_RIESGO_ALTO = "La transferencia tiene riesgo alto. Se modifica la fecha de ejecucion.";

	/** The Constant INFO_SIN_NRO_DE_CUENTA. */
	private static final String INFO_SIN_NRO_DE_CUENTA = "Si no tiene nro de cuenta, se debe tomar la primer cuenta que tenga el cliente antes de impactar al servicio.";

	/** The Constant OK. */
	private static final String OK = "OK";

	/** The Constant SI_MINUSCULA. */
	private static final String SI_MINUSCULA = "Si";

	/** The Constant NO_MINUSCULA. */
	private static final String NO_MINUSCULA = "No";

	/** The Constant NO. */
	private static final String NO = "NO";

	/** The Constant SI. */
	private static final String SI = "SI";

	/** The Constant INICIALIZANDO_EL_CONTADOR_DE_INTENTOS. */
	private static final String INICIALIZANDO_EL_CONTADOR_DE_INTENTOS = "Inicializando el contador de intentos.";

	/** The Constant VALIDANDO_HABILITACION_DE_DESAFIOS. */
	private static final String VALIDANDO_HABILITACION_DE_DESAFIOS = "Validando habilitacion de desafios.";

	/** The Constant TIENE_CONTRATO. */
	private static final String TIENE_CONTRATO = "Tiene contrato: ";

	/** The Constant ADHESION_A_M_Y_A. */
	private static final String ADHESION_A_M_Y_A = "Adhesion a  M y A: ";

	/** The Constant VALIDANDO_ADHESION_A_CONTRATO. */
	private static final String VALIDANDO_ADHESION_A_CONTRATO = "Validando adhesion a contrato.";

	/** The Constant VALIDANDO_ADHESION_A_M_Y_A. */
	private static final String VALIDANDO_ADHESION_A_M_Y_A = "Validando adhesion a M y A.";

	/** The Constant ERROR_AL_OBTENER_EL_SALDO_DE_LAS_CUENTAS. */
	private static final String ERROR_AL_OBTENER_EL_SALDO_DE_LAS_CUENTAS = "Error al obtener el saldo de las cuentas.";

	/** The Constant NO_EXISTEN_CUENTAS_ASOCIADAS_AL_CLIENTE. */
	private static final String NO_EXISTEN_CUENTAS_ASOCIADAS_AL_CLIENTE = "No existen cuentas asociadas al cliente";

	/** The Constant MENSAJE_ERROR. */
	private static final String MENSAJE_ERROR = "Ocurrio un error y no podemos procesar tu solicitud en este momento.";

	/** The Constant CUARENTA_Y_OCHO_HORAS. */
	private static final String CUARENTA_Y_OCHO_HORAS = "48 Horas";

	/** The Constant ERROR_EN_PARAMETRO. */
	private static final String ERROR_EN_PARAMETRO = "Error en parametro.";

	/** The Constant ERROR_EN_PREPARACION_CONSULTA. */
	private static final String ERROR_EN_PREPARACION_CONSULTA = "Error en preparacion consulta.";

	/** The Constant CONSULTA_EN_CURSO. */
	private static final String CONSULTA_EN_CURSO = "consulta en curso...";

	/** The Constant ERROR_TARJETA_BANELCO_INACTIVA. */
	private static final String ERROR_TARJETA_BANELCO_INACTIVA = "ERROR tarjeta banelco inactiva.";

	/**
	 * The Constant INICIANDO_CONSULTA_TITULAR_POR_CUENTA_ORIGEN_SELECCIONADA.
	 */
	private static final String INICIANDO_CONSULTA_TITULAR_POR_CUENTA_ORIGEN_SELECCIONADA = "Iniciando consulta del titular segun cuenta origen seleccionada...";

	/** The Constant TAG_AYUDA. */
	private static final String TAG_AYUDA = "AYUDA";



	/** The Constant MSJ_CBU_INVALIDO. */
	private static final String MSJ_CBU_INVALIDO = "El CBU {} es invalido. ";

	/** The Constant MSJ_SOLICITANDO_NUEVA_TRANSFERENCIA. */
	private static final String MSJ_SOLICITANDO_NUEVA_TRANSFERENCIA = "Solicitando una nueva transferencia... ";

	/** The Constant MSJ_ERROR_SOLICITAR_NUEVA_TRANSFERENCIA. */
	private static final String MSJ_ERROR_SOLICITAR_NUEVA_TRANSFERENCIA = "Ha ocurrido al solicitar una nueva transferencia. ";

	/** The Constant MSJ_INFO_OBTENIENDO_CUENTAS_CLIENTE. */
	private static final String MSJ_INFO_OBTENIENDO_CUENTAS_CLIENTE = "Obteniendo las cuentas del cliente... ";

	/** The Constant VALIDA_TRJ_BANELCO_ACTIVA. */
	private static final String VALIDA_TRJ_BANELCO_ACTIVA = "Validando Tarjeta Banelco Activa";

	/** The Constant VALIDA_CUENTAS_ORIGEN_Y_DESTINO_CON_MISMA_MONEDA. */
	private static final String VALIDA_CUENTAS_ORIGEN_Y_DESTINO_CON_MISMA_MONEDA = "Validando Cuentas origen y desino en misma moneda...";

	/** The Constant MSJ_INFO_OBTENIENDO_DESTINATARIO. */
	private static final String MSJ_INFO_OBTENIENDO_DESTINATARIO = "Obteniendo destinatario, cuenta RIO RIO no propia...";

	/** The Constant ERROR_GENERICO_MANAGER. */
	private static final String ERROR_GENERICO_MANAGER = "Error generico dentro del manager... ";

	/** The Constant MSJ_INFO_AGREGANDO_CUENTAS. */
	private static final String MSJ_INFO_AGREGANDO_CUENTAS = "Agregando la: {} a la lista de cuentas disponibles.";

	/** The Constant MSJ_INFO_OBTENIENDO_CUENTAS. */
	private static final String MSJ_INFO_OBTENIENDO_CUENTAS = "Obteniendo cuentas....";

	/** The Constant MSJ_INFO_VERIFICANDO_SI_CUENTA_ES_RIO_RIO. */
	private static final String MSJ_INFO_VERIFICANDO_SI_CUENTA_ES_RIO_RIO = "Transferencia a cuenta Rio. Verificando el formato de la cuenta...";

	/** The Constant MSJ_INFO_VERIFICANDO_SI_CUENTA_ES_PROPIA. */
	private static final String MSJ_INFO_VERIFICANDO_SI_CUENTA_ES_PROPIA = "Verificando si es una cuenta propia...";

	/** The Constant MSJ_INFO_VERIFICANDO_SI_LA_CUENTA_ES_LA_UNICA. */
	private static final String MSJ_INFO_VERIFICANDO_SI_LA_CUENTA_ES_LA_UNICA = "Verificando si la cuenta es la unica que posee el cliente";

	/** The Constant MSJ_INFO_DESTINATARIO_CON_DIFERENTE_MONEDA_INCOMPATIBLE. */
	private static final String MSJ_INFO_DESTINATARIO_CON_DIFERENTE_MONEDA_INCOMPATIBLE = "El destinatario no fue verificado moneda destino incompatible...";

	/** The Constant MSJ_INFO_DESTINATARIO_NO_VERIFICADO. */
	private static final String MSJ_INFO_DESTINATARIO_NO_VERIFICADO = "El destinatario no fue verificado...";

	/** The Constant MSJ_INFO_ERROR_VALIDACION_ORIGEN_DESTINO. */
	private static final String MSJ_INFO_ERROR_VALIDACION_ORIGEN_DESTINO = "Error en servicio al validar origen-destino";

	/** The Constant MSJ_INFO_OBTENIENDO_TARJETA_BANELCO_ACTIVA. */
	private static final String MSJ_INFO_OBTENIENDO_TARJETA_BANELCO_ACTIVA = "Obteniendo tarjetas banelcos activas...";

	/** The Constant MSJ_INFO_NO_EXISTEN_TARJETA_BANELCO_ACTIVAS. */
	private static final String MSJ_INFO_NO_EXISTEN_TARJETA_BANELCO_ACTIVAS = "El cliente no tiene una tarjeta banelco activa.";

	/** The Constant MSJ_INFO_TRANSFERENCIA_CON_EXITO. */
	private static final String MSJ_INFO_TRANSFERENCIA_CON_EXITO = "La transferencia fue ejecutada con exito!!.";

	/** The Constant MSJ_INFO_TRANSFERENCIA_CON_WARNING. */
	private static final String MSJ_INFO_TRANSFERENCIA_CON_WARNING = "La transferencia contiene warnings";

	/** The Constant MSJ_INFO_TRANSFERENCIA_CON_ERROR. */
	private static final String MSJ_INFO_TRANSFERENCIA_CON_ERROR = "Ha ocurrido un error al ejecutar la transferencia.";

	/** The Constant MSJ_INFO_TRANSFERENCIA_DE_TIPO. */
	private static final String MSJ_INFO_LA_TRANSFERENCIA_ES_PROGRAMADA = "La transferencia es programada: {}.";

	/** The Constant MSJ_INFO_TRANSFERENCIA_ENTRE_CUENTAS_PROPIAS. */
	private static final String MSJ_INFO_TRANSFERENCIA_ENTRE_CUENTAS_PROPIAS = "Ejecutando transferencias entre cuentas propias... no se realiza autentificacion.";

	/** transfer execution given biocatch is allow */
	private static final String MSJ_INFO_TRANSFERENCIA_PERMITIDA_BIOCATCH_ALLOW = "Ejecutando transferencia habilitada por Biocatch... no se realiza autentificacion.";

	/** The Constant MSJ_ERROR_CNS_TIT_CTA. */
	private static final String MSJ_ERROR_CNS_TIT_CTA = "ERROR en servicio a CNSTITCTA";

	/** The Constant MSJ_WARNING_CNS_TIT_CTA. */
	private static final String MSJ_WARNING_CNS_TIT_CTA = "ERROR en verificacion a CNSTITCTA";

	/** The Constant MSJ_INFO_NUEVA_TRANSFERENCIA. */
	private static final String MSJ_INFO_NUEVA_TRANSFERENCIA = "Ejecutando una nueva transferencia...";

	/** The Constant MSJ_ERROR_CUENTA_INVALIDA. */
	private static final String MSJ_ERROR_CUENTA_INVALIDA = "La cuenta no se encuentra en la lista de cuentas disponibles.";

	/** The Constant MSG_ERROR_IMPORTE_LIMITE_CVU. */
	private static final String MSG_ERROR_IMPORTE_LIMITE_CVU = "El límite máximo para este tipo de operación es de {0}.";
	
	/** The Constant LA_CUENTA_ES_UNICA. */
	private static final String LA_CUENTA_ES_UNICA = "LA_CUENTA_ES_UNICA";

	/** The Constant STRING_VACIO. */
	private static final String STRING_VACIO = "";

	/** * TIPO CUENTA_BANELCO. */
	private static final int TIPO_CUENTA_BANELCO = 5;

	/** The Constant HOY_STRING. */
	private static final String HOY_STRING = "Hoy";

	/** The Constant TARJETA_CREDITO_ACTIVA. */
	private static final String TARJETA_CREDITO_ACTIVA = "01";

	/** The Constant moneda. */
	private static final String PESOS = "PESOS";

	/** The Constant REALIZAR. */
	private static final String REALIZAR = "realizar";

	/** The Constant INICIANDO_OBTENER_INFORMACION_DESTINATARIO. */
	private static final String INICIANDO_OBTENER_INFORMACION_DESTINATARIO = "Obteniendo la Informacion de Destinatario";

	/** The Constant VALIDACION_SIN_CAMBIOS_DE_ORIGEN__OK. */
	private static final String VALIDACION_SIN_CAMBIOS_DE_ORIGEN_OK = "En Consulta titularidad SIN_CAMBIOS_DE_ORIGEN__OK";

	/** The Constant INICIANDO_CONSULTA_TITULAR_POR_CAMBIO_EN_CNTA_ORIGEN. */
	private static final String INICIANDO_CONSULTA_TITULAR_POR_CAMBIO_EN_CNTA_ORIGEN = "Iniciando consulta del titular por cambio en cuenta origen seleccionada...";

	/** The Constant VERIFICANDO_CON_CONS_TITULAR_CBU. */
	private static final String VERIFICANDO_CON_CONS_TITULAR_CBU = "Verificando el CBU con CNSTIT";

	/** The Constant SOLICITUD_POR_COELSA. */
	private static final String SOLICITUD_POR_COELSA = "solicitud por COELSA";

	/** The Constant ERROR_CON_MONEDA_O_TRJ. */
	private static final String ERROR_CON_MONEDA_O_TRJ = "Error con moneda o tarjeta";
	
	/** The Constant ERROR_INSERTAR_TRANSFERENCIA. */
	private static final String ERROR_INSERTAR_TRANSFERENCIA = "ER";
	
	/** The Constant OK_INSERTAR_TRANSFERENCIA. */
	private static final String OK_INSERTAR_TRANSFERENCIA = "OK";

	/** The Constant biocatch RSA offline */
	private static final String ALLOW = "ALLOW";

	private static final String CHALLENGE = "CHALLENGE";

	/** The transferencia maximo rio ob pesos. */
	@Value("${TRFINMEDIATA.OB.IMPORTEPESOS.MAX}")
	private String transferenciaMaximoRioObPesos;

	/** The transferencia maximo rio rio dolares. */
	@Value("${TRFINMEDIATA.BR.IMPORTEDOLARES.MAX}")
	private String transferenciaMaximoRioRioDolares;

	/** The transferencia maximo rio ob dolares. */
	@Value("${TRFINMEDIATA.OB.IMPORTEDOLARES.MAX}")
	private String transferenciaMaximoRioObDolares;
	
    /** The trfinmediata br importedolares max. */
    @Value("${TRFINMEDIATA.BR.IMPORTEDOLARES.MAX}")
    private String trfInmediataBrImporteDolaresMax;

    /** The trfinmediata brt importepesos max. */
    @Value("${TRFINMEDIATA.BRT.IMPORTEPESOS.MAX}")
    private String trfInmediataBrtImportePesosMax;

    /** The trfinmediata ob importepesos max. */
    @Value("${TRFINMEDIATA.OB.IMPORTEPESOS.MAX}")
    private String trfInmediataObImportePesosMax;

    /** The trfinmediata brp importepesos max. */
    @Value("${TRFINMEDIATA.BRP.IMPORTEPESOS.MAX}")
    private String trfInmediataBrpImportePesosMax;

    /** The trfinmediata ob importedolares max. */
    @Value("${TRFINMEDIATA.OB.IMPORTEDOLARES.MAX}")
    private String trfInmediataObImporteDolareMax;

    /** The trfinmediata obp importedolarespesos max. */
    @Value("${TRFINMEDIATA.OBP.IMPORTEDOLARESPESOS.MAX}")
    private String trfInmediataObpImporteDolarespesosMax;
	
	/** The trf cvu importe dolares max. */
    @Value("${TRFCVU.IMPORTEDOLARES.MAX}")
    private String trfCVUImporteDolaresMax;
    
    /** The trf cvu importe pesos max. */
    @Value("${TRFCVU.IMPORTEPESOS.MAX}")
    private String trfCVUImportePesosMax;

	/** The cuenta unica habilitada. */
	@Value("${CUENTA_UNICA.HABILITADO}")
	private String cuentaUnicaHabilitada;

	/** The caja ahorro pesos habilitada. */
	@Value("${CAJA_AHORRO_PESOS.HABILITADO}")
	private String cajaAhorroPesosHabilitada;

	/** The caja ahorro dolares habilitada. */
	@Value("${CAJA_AHORRO_DOLARES.HABILITADO}")
	private String cajaAhorroDolaresHabilitada;

	/** The cuenta corriente pesos habilitada. */
	@Value("${CUENTA_CORRIENTE_PESOS.HABILITADO}")
	private String cuentaCorrientePesosHabilitada;

	/** The cuenta corriente dolares habilitada. */
	@Value("${CUENTA_CORRIENTE_DOLARES.HABILITADO}")
	private String cuentaCorrienteDolaresHabilitada;
	
	/** The trf cvu dolares habilitado. */
	@Value("${TRFCVU.DOLARES.HABILITADO}")
	private int trfCvuDolaresHabilitado;

	/** The hora desde otros bancos. */
	@Value("${TRANSFERENCIAS.HORADESDEACH}")
	private String horaDesdeOtrosBancos;

	/** The hora hasta otros bancos. */
	@Value("${TRANSFERENCIAS.HORAHASTAACH}")
	private String horaHastaOtrosBancos;

	/** The hora desde rio no propia. */
	@Value("${TRANSFERENCIAS.HORADESDERIO}")
	private String horaDesdeRioNoPropia;

	/** The hora hasta rio no propia. */
	@Value("${TRANSFERENCIAS.HORAHASTARIO}")
	private String horaHastaRioNoPropia;

	/** The hora desde rio propia. */
	@Value("${TRANSFERENCIAS.HORADESDERIOPROPIA}")
	private String horaDesdeRioPropia;

	/** The hora hasta rio propia. */
	@Value("${TRANSFERENCIAS.HORAHASTARIOPROPIA}")
	private String horaHastaRioPropia;

	/** The hora desde rio bimonetaria propia. */
	@Value("${TRANSFERENCIAS.HORADESDERIOBIMONETARIAPROPIA}")
	private String horaDesdeRioBimonetariaPropia;

	/** The hora hasta rio bimonetaria propia. */
	@Value("${TRANSFERENCIAS.HORAHASTARIOBIMONETARIAPROPIA}")
	private String horaHastaRioBimonetariaPropia;

	/** The hora desde inmediata. */
	@Value("${TRANSFERENCIAS.HORADESDEINMEDIATA}")
	private String horaDesdeInmediata;

	/** The hora hasta inmediata. */
	@Value("${TRANSFERENCIAS.HORAHASTAINMEDIATA}")
	private String horaHastaInmediata;

	/** The valor desafio transferencias. */
	@Value("${TRJCOORD.OPERAINDISTINTO.TRANSFERENCIAS}")
	private String valorDesafioTransferencias;
	
	/** The ayuda TIT nueva transferencia limite transferencia. */
	@Value("${TRANSFERENCIA.NUEVA.AYUDA.LIMITE.TIT}")
	private String ayudaTITNuevaTransferenciaLimiteTransferencia;
	
	/** The ayuda template nueva transferencia limite. */
	@Value("${TRANSFERENCIA.NUEVA.AYUDA.LIMITE.TEMP}")
	private String ayudaTemplateNuevaTransferenciaLimite;
	
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

	/** The ayuda TIT plazo acreditación. */
	@Value("${TRANSFERENCIA.NUEVA.AYUDA.PLAZOACREDITACION.TIT}")
	private String ayudaTITNuevaTransferenciaPlazoAcreditacion;

	/** The ayuda template plazo acreditación. */
	@Value("${TRANSFERENCIA.NUEVA.AYUDA.PLAZOACREDITACION.TEMP}")
	private String ayudaTemplateNuevaTransferenciaPlazoAcreditacion;

	/** The error banelco coelsa habilitado. */
	@Value("${ERRORBANELCO.COELSA.HABILITADO}")
    private String errorBanelcoCoelsaHabilitado; 

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


	/**
	 * Constante CONTRATO_ACEPTADO.
	 */
	private static final String CONTRATO_ACEPTADO = "1";

	/** The Constant MONEDA. */
	private static final String MONEDA = "MONEDA";
	
	/** The Constant NROCUENTA. */
	private static final String NROCUENTA = "NROCUENTA";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaManagerImpl.class);

	/** The fecha operacion formatter. */
	private final SimpleDateFormat fechaOperacionFormatter = new SimpleDateFormat("dd/MM/yyyy · HH:mm");

	/** The fecha ejecucion formatter. */
	private final SimpleDateFormat fechaEjecucionFormatter = new SimpleDateFormat("dd/MM/yyyy");

	/** The api-accounts */
	@Autowired
	private AccountsApi accountsApi;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The transferencia BO. */
	@Autowired
	private TransferenciaBO transferenciaBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The alta scomp bo. */
	@Autowired
	private ScompBO altaScompBO;

	/** The cuenta manager. */
	@Autowired
	private CuentaManager cuentaManager;

	/** The mensaje manager. */
	@Autowired
	private MensajeManager mensajeManager;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The autentificacion manager. */
	@Autowired
	private AutentificacionManager autentificacionManager;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	/** The mensaje DAO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The contrato BO. */
	@Autowired
	private ContratoBO contratoBO;

	/** The rsa manager. */
	@Autowired
	private RsaManager rsaManager;

	/** The transferencia por alias manager. */
	@Autowired
	private TransferenciaPorAliasManager transferenciaPorAliasManager;

	/** The alta destinatario BO. */
	@Autowired
	private AltaDestinatarioBO altaDestinatarioBO;

	/** The sesion agenda. */
	@Autowired
	private SesionAgendaDestinatarios sesionAgenda;
	
	/** The agenda destinatario DAO. */
	@Autowired
	private AgendaDestinatarioDAO agendaDestinatarioDAO;

	/** The agenda operacion Banca Privada Transferencia DAO. */
	@Autowired
	private OperacionBancaPrivadaTransferenciaDAO operacionBancaPrivadaTransferenciaDAO;
	
	@Autowired
	private ClienteManager clienteManager;

	@Autowired
	private BiocatchManager biocatchManager;

	@Autowired
	private AlycsDAO alycsDAO;


	/** The Constant DOCE. */
	private static final int DOCE = 12;
	
	/** The Constant CUATRO. */
	private static final int CUATRO = 4;	

	/** The Constant STRING_CERO. */
	private static final String STRING_CERO = "0";

	private static final int MIN_REQUIRED_DAYS_TO_TRANSFER_WHEN_RSA_OFFLINE = 10;

	private static final float DEFAULT_DESTINATION_SCORING = -1;

    /** The legal BO. */
    @Autowired
    protected LegalBO legalBO;
    
    /** The cuentas Banca Privada BO. */
    @Autowired
    private CuentasBancaPrivadaBO cuentasBancaPrivadaBO;

	@Autowired
	private MetricBuilder metricBuilder;

	@Autowired
	private MetricRegisterBO metricRegisterBO;

	@Autowired private TransferenciaRsaApi transferenciaRsaApi;

	@Autowired
	private DestinatariosFrecuentesBO destinatariosFrecuentesBO;

	@Autowired
	private ScoringApi scoringApi;

	@Autowired
	private IFundsMessageHelper fundsMessageHelper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * TransferenciaManager#consultarTitularidad(ar.com.santanderrio.obp.
	 * servicios.transferencias.web.view.TransferenciaView)
	 */
	@Override
	public Respuesta<TransferenciaView> consultarTitularidad(TransferenciaView transferenciaView, String userAgent) {
		
		estadisticaManager.add(EstadisticasConstants.CODIGO_NUEVA_TRANSFERENCIA_INMEDIATA_BP, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		
		sesionParametros.setRsaRiesgoTransferenciaDTO(null);
		Respuesta<TransferenciaView> respuestaView = new Respuesta<TransferenciaView>();
		transferenciaView
				.setInmediata(!TransferenciaUtil.isTransferenciaProgramada(transferenciaView.getFechaEjecucion()));
		if ((transferenciaView.getAliasDestino() == null || transferenciaView.getAliasDestino().isEmpty())
				|| (transferenciaView.getAliasDestino() != null && transferenciaView.isErrorConAlias() == true)) {
			LOGGER.info(SOLICITANDO_TRANSFERENCIA_POR_CBU_O_CUENTA);
			Respuesta<Boolean> respuestaContrato = validarContratoOperacionesProgramadas(transferenciaView);
			if (!respuestaContrato.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				return cargarRespuestaContratoGrabandoEstadistica(transferenciaView, respuestaContrato);
			} else {
			    if (ISBANStringUtils.validarCVU(transferenciaView.getCbu())) {
		            DivisaEnum moneda = DivisaEnum.fromMonedaString(transferenciaView.getMoneda().toLowerCase());
		            if (!DivisaEnum.PESO.equals(moneda) && trfCvuDolaresHabilitado == 0) {
		                return armarRespuestaErrorMonedaCVU();
		            }
		            if (!TransferenciaUtil.validarTransferenciaCVU(new BigDecimal(transferenciaView.getImporte()), moneda, 
		                    trfCvuDolaresHabilitado, trfCVUImporteDolaresMax, trfCVUImportePesosMax)) {
		                return armarRespuestaWarningValidacionImporteCVU(moneda);
		            }
		        }
				
				Respuesta<TransferenciaView> repuestaConsultaTitularidad = validacionDeConsultaTitularidad(transferenciaView, respuestaView, userAgent);
				TransferenciaUtil.cargarHashDeLaVistaEnSesion(transferenciaView, sesionParametros);
				super.obtenerMensajeSiHorarioDeTransferenciaNoEsValido(repuestaConsultaTitularidad);
				return repuestaConsultaTitularidad;
			}
		} else {
			LOGGER.info(SOLICITANDO_TRANSFERENCIA_POR_ALIAS);
			respuestaView = transferenciaPorAliasManager.solicitarNuevaTransferencia(transferenciaView, userAgent);
			grabarEstadistica(respuestaView);
			return respuestaView;
		}
	}

	/**
	 * Validacion de consulta titularidad.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param respuestaView
	 *            the respuesta view
	 * @param userAgent
	 *            the user agent
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> validacionDeConsultaTitularidad(TransferenciaView transferenciaView,
			Respuesta<TransferenciaView> respuestaView, String userAgent) {
		if (!TransferenciaUtil.hayCambioEnCuentaOrigen(transferenciaView,
				sesionParametros.getCuentaSeleccionadaParaTransferencia(NROCUENTA),
				sesionParametros.getCuentaSeleccionadaParaTransferencia(MONEDA), sesionParametros)
				&& !transferenciaView.getIsRioRio()) {
			respuestaView = consultarTitularidadOtrosBancos(transferenciaView, userAgent);
			grabarEstadistica(respuestaView);
			return respuestaView;
		} else if (sesionParametros.isFromAgendaDestinatario()) {
			if (transferenciaView.getIsRioRio()) {
				respuestaView = consultarTitularidadRioRio(transferenciaView);
				grabarEstadistica(respuestaView);
				return respuestaView;
			} else if (transferenciaView.isPrimerIngresoDeAgenda()) {
				respuestaView = consultarTitularidadOtrosBancos(transferenciaView, userAgent);
				respuestaView.getRespuesta().setPrimerIngresoDeAgenda(false);
				grabarEstadistica(respuestaView);
				return respuestaView;
			} else if (!TransferenciaUtil.hayCambioEnCuentaOrigen(transferenciaView,
					sesionParametros.getCuentaSeleccionadaParaTransferencia(NROCUENTA),
					sesionParametros.getCuentaSeleccionadaParaTransferencia(MONEDA), sesionParametros)) {
				respuestaView = consultarTitularidadOtrosBancos(transferenciaView, userAgent);
				grabarEstadistica(respuestaView);
				return respuestaView;
			} else {
				return obtenerRespuestaOkConEstadistica(transferenciaView);
			}
		} else {
			return obtenerRespuestaOkConEstadistica(transferenciaView);
		}
	}

	/**
	 * Obtener respuesta ok con estadistica.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> obtenerRespuestaOkConEstadistica(TransferenciaView transferenciaView) {
		LOGGER.info(VALIDACION_SIN_CAMBIOS_DE_ORIGEN_OK);
		transferenciaView.setImporte(ISBANStringUtils.formatearSaldo(new BigDecimal(transferenciaView.getImporte())));
		estadisticaManager.add(EstadisticasConstants.ESTADISTICA_CONFIGURACION,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(TransferenciaView.class, transferenciaView);
	}

	/**
	 * Cargar respuesta contrato grabando estadistica.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param respuestaContrato
	 *            the respuesta contrato
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> cargarRespuestaContratoGrabandoEstadistica(TransferenciaView transferenciaView,
			Respuesta<Boolean> respuestaContrato) {
		estadisticaManager.add(EstadisticasConstants.ESTADISTICA_CONFIGURACION,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		Respuesta<TransferenciaView> respuesta = new Respuesta<TransferenciaView>();
		respuesta.setEstadoRespuesta(respuestaContrato.getEstadoRespuesta());
		respuesta.setItemMensajeRespuesta(respuestaContrato.getItemsMensajeRespuesta());
		respuesta.setRespuesta(transferenciaView);
		return respuesta;
	}

	/**
	 * Consultar titularidad otros bancos.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param userAgent
	 *            the user agent
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> consultarTitularidadOtrosBancos(TransferenciaView transferenciaView, String userAgent) {
		LOGGER.info(INICIANDO_CONSULTA_TITULAR_POR_CAMBIO_EN_CNTA_ORIGEN);
		String numeroTarjetaActiva = obtenerTarjetaBanelcoActiva();
		if (null != numeroTarjetaActiva && !STRING_VACIO.equals(numeroTarjetaActiva)) {
			LOGGER.info(CONSULTA_EN_CURSO);
			Respuesta<TransferenciaDTO> transferenciaRespuesta = new Respuesta<TransferenciaDTO>();
			Respuesta<TransferenciaView> respuesta = new Respuesta<TransferenciaView>();
			TransferenciaDTO transferenciaDTO = cargarTransferenciaDTO(transferenciaView);
			transferenciaDTO.setIp(sesionCliente.obtenerIpV4SinPuntos());
			LOGGER.info(VALIDA_CUENTAS_ORIGEN_Y_DESTINO_CON_MISMA_MONEDA);
			Cuenta cuentaOrigenConMonedaDeDestinoValida;
			if (transferenciaDTO.getCuentaOrigen() == null) {
				LOGGER.info(INFO_SIN_NRO_DE_CUENTA);
				cuentaOrigenConMonedaDeDestinoValida = TransferenciaUtil.obtenerPrimerCuentaConMonedaDestino(
						sesionCliente.getCliente().getCuentas(), transferenciaView.getMoneda());
				transferenciaDTO.setCuentaOrigen(cuentaOrigenConMonedaDeDestinoValida);
			}
			transferenciaRespuesta = transferenciaBO.validarOrigenDestinoTransferencia(sesionCliente.getCliente(),
					transferenciaDTO, numeroTarjetaActiva, transferenciaView.getMoneda(), transferenciaDTO.getCuentaOrigen(), userAgent);
			return armarRespuesta(transferenciaView, transferenciaDTO, null, respuesta, transferenciaRespuesta);
		} else {
			LOGGER.info(ERROR_EN_PREPARACION_CONSULTA);
			return respuestaFactory.crearRespuestaError(TransferenciaView.class, null, null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CON_TARJETA_CREDITO);
		}
	}

	/**
	 * Consultar titularidad rio rio.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> consultarTitularidadRioRio(TransferenciaView transferenciaView) {
		// el importe simpre se devuelve como string y formateado.
		transferenciaView.setImporte(ISBANStringUtils.formatearSaldo(new BigDecimal(transferenciaView.getImporte())));
		String tipoCuentaDestino = transferenciaView.getTipoCuentaDestino();
		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(transferenciaView.getNroCuentaDestino());
		transferenciaView.setNroCuentaDestino(transferenciaView.getNroCuentaDestino());
		boolean esCuentaPropia = TransferenciaUtil.esCuentaPropia(sesionCliente.getCliente(), identificacionCuenta,
				tipoCuentaDestino, true);
		if(!esCuentaPropia) {
			esCuentaPropia = TransferenciaUtil.esCuentaPropiaBP(sesionCliente.getCliente(), identificacionCuenta,
					tipoCuentaDestino, true);
		}
		if (esCuentaPropia) {
			sesionParametros.setDestinatarioView(null);
			return respuestaFactory.crearRespuestaOk(TransferenciaView.class, transferenciaView);
		} else {
			LOGGER.info(INICIANDO_CONSULTA_TITULAR_POR_CUENTA_ORIGEN_SELECCIONADA);
			Respuesta<Cliente> cuentaDestinoVerificada = cuentaManager.verificarCuenta(transferenciaView, 2);
			if(EstadoRespuesta.OK.equals(cuentaDestinoVerificada.getEstadoRespuesta())) {
				transferenciaView.setCuit(cuentaDestinoVerificada.getRespuesta().getNumeroCUILCUIT());
			}
			if (EstadoRespuesta.ERROR.equals(cuentaDestinoVerificada.getEstadoRespuesta())) {
				sesionParametros.setDestinatarioView(null);
				LOGGER.info(MSJ_ERROR_CNS_TIT_CTA);
				// Cierre de sucursales
				if (cuentaDestinoVerificada.getItemsMensajeRespuesta().get(0).getTipoError().equals(TipoError.ERROR_CUENTA_MIGRADA.getDescripcion())) {
					Respuesta<TransferenciaView> resp = respuestaFactory.crearRespuestaErrorPersonalizado(TransferenciaView.class,
							cuentaDestinoVerificada.getItemsMensajeRespuesta().get(0).getMensaje(), cuentaDestinoVerificada.getItemsMensajeRespuesta().get(0).getTipoError());
					resp.getItemsMensajeRespuesta().get(0).setTag(cuentaDestinoVerificada.getItemsMensajeRespuesta().get(0).getTag());
					return resp;
				}
				return respuestaFactory.crearRespuestaError(TransferenciaView.class,
						cuentaDestinoVerificada.getItemsMensajeRespuesta().get(0).getMensaje(), "");
			} else {
			    //Setteo el nombre del destinatario en sesion para usar en el proximo paso
			    if(sesionParametros.getDestinatarioView() != null) {
			        String titular = WordUtils.capitalizeFully(cuentaDestinoVerificada.getRespuesta().getNombre());
			        sesionParametros.getDestinatarioView().setTitular(titular);
			    }
			    // Cierre de sucursales
				if(transferenciaView.getIsCuentaMigrada()) {
					try {
						// Actualizamos el destinatario de la agenda con la nueva cta migrada
						DestinatarioEntity destinatario = buscarDestinatario(
								transferenciaView.getNroCuentaDestinoMigrada());
						String nroCuentaDestino = ISBANStringUtils.extraerCuenta(transferenciaView.getNroCuentaDestino());
						String sucursalDestino = ISBANStringUtils.extraerSucursal(transferenciaView.getNroCuentaDestino());
						destinatario.setNumeroCuentaDestinatario(StringUtils.leftPad(nroCuentaDestino, DOCE, STRING_CERO));
						destinatario
								.setSucursalCuentaDestinatario(StringUtils.leftPad(sucursalDestino, CUATRO, STRING_CERO));
						ConfirmacionAltaDestinatarioView datos = new ConfirmacionAltaDestinatarioView();
						datos.setReferencia(destinatario.getDescripcionCuentaDestinatario());
						datos.setDireccionCorreo(destinatario.getDireccionCorreo());
						datos.setAlias(destinatario.getAlias());
						AgendaDestinatarioInEntity inEntity = new AgendaDestinatarioInEntity(sesionCliente.getCliente(),
								destinatario, datos);
						agendaDestinatarioDAO.eliminarUAgregar(inEntity, sesionParametros.getRegistroSession().getIp(),
								TipoOperacionACTAGEDESTEnum.MODIFICACION);
					} catch (DAOException e) {
						LOGGER.error("Error de servicio", e);
					}
				}
				return respuestaFactory.crearRespuestaOk(TransferenciaView.class, transferenciaView);
			}
		}
	}

    /**
     * Buscar destinatario.
     *
     * @param nroCuenta
     *            the nroCuenta
     * @return the destinatario entity
     */
    private DestinatarioEntity buscarDestinatario(String nroCuenta) {
        LOGGER.info("Busco destinatario por nroCuenta");
        DestinatarioEntity destinatario = new DestinatarioEntity();
        for (DestinatarioEntity entity : sesionAgenda.getDestinatariosEntity()) {
            if (StringUtils.equals(ISBANStringUtils.formatearNroCuentaProductoConSucursal(entity.getNumeroCuentaDestinatario(), entity.getSucursalCuentaDestinatario()), nroCuenta)) {
                destinatario = entity;
                break;
            }
        }
        return destinatario;
    }
	
	/**
	 * Validar contrato operaciones programadas.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the respuesta
	 */
	private Respuesta<Boolean> validarContratoOperacionesProgramadas(TransferenciaView transferenciaView) {
		boolean isProgramada = TransferenciaUtil.isTransferenciaProgramada(transferenciaView.getFechaEjecucion());
		Cliente cliente = sesionCliente.getCliente();
		if (isProgramada) {
			Respuesta<String> respuestaContratoAceptado = contratoBO.buscarContratoAceptado(
					cliente.getFechaNacimiento(), cliente.getDni(), CampoEnum.OPPROG, SinonimoEnum.NO_REPETIDO);
			if (respuestaContratoAceptado.getEstadoRespuesta().equals(EstadoRespuesta.OK)
					&& respuestaContratoAceptado.getRespuesta().equals(CONTRATO_ACEPTADO)) {
				return respuestaFactory.crearRespuestaOk(Boolean.class, Boolean.TRUE);
			} else {
				return aceptacionContratoPagoProgramado(cliente);
			}
		}
		return respuestaFactory.crearRespuestaOk(Boolean.class, Boolean.TRUE);
	}

	/**
	 * Inicia el flujo que acepta el contrato de pago programado.
	 *
	 * @param cliente
	 *            the cliente
	 * @return OK o ERROR de acuerdo al resultado
	 */
	private Respuesta<Boolean> aceptacionContratoPagoProgramado(Cliente cliente) {
		Respuesta<String> respuestaAceptacion = contratoBO.confirmarAceptacionContrato(cliente.getFechaNacimiento(),
				cliente.getDni(), CampoEnum.OPPROG, SinonimoEnum.NO_REPETIDO);
		if (respuestaAceptacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)
				&& respuestaAceptacion.getRespuesta().equals(OK)) {
			estadisticaManager.add(EstadisticasConstants.ACEPTACION_CONTRATO_PAGO_PROGRAMADO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(Boolean.class, Boolean.TRUE);
		} else {
			estadisticaManager.add(EstadisticasConstants.ACEPTACION_CONTRATO_PAGO_PROGRAMADO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(Boolean.FALSE,
					TipoError.ERROR_SIN_CONTRATO_TRANSFERENCIA.getDescripcion(),
					CodigoMensajeConstantes.ERROR_SIN_CONTRATO_TRANSFERENCIA, null);
		}
	}

	/**
	 * Solicitar transferencia Rio rio.
	 *
	 * @author manuel.vargs B041299
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> solicitarTransferenciaRioRio(TransferenciaView transferenciaView) {
		LOGGER.info(MSJ_SOLICITANDO_NUEVA_TRANSFERENCIA);
		transferenciaView.generarNuevoIdSesion();

		try {
			LOGGER.info(MSJ_INFO_OBTENIENDO_CUENTAS_CLIENTE);
			CuentasView cuentasViewFiltradas = null;
			if(!sesionCliente.getCliente().getCuentasMonetarias().isEmpty()) {//Si no tiene cuentas retail no llama a los servicios de saldos.
				cuentasViewFiltradas = armarCuentasOrigen(transferenciaView);
			}

			Respuesta<List<ResumenDetalleCuenta>> respResumenesDC = obtenerCuentasySaldosBP();
			//error al consultar los saldos de banca privada
			if( !EstadoRespuesta.OK.equals(respResumenesDC.getEstadoRespuesta())) {
				return respuestaFactory.transformar(null, respResumenesDC);
			}
			Respuesta<CuentasView> listaCuentaBP = CuentasBancaPrivadaUtil.convertirCuentasView(respResumenesDC);
			if(cuentasViewFiltradas == null) {
				cuentasViewFiltradas = listaCuentaBP.getRespuesta();
			} else {
			cuentasViewFiltradas.getCuentas().addAll(listaCuentaBP.getRespuesta().getCuentas());
			}
			
			if (null == cuentasViewFiltradas) {
				Mensaje mensaje = this.mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_GENERICO);
				return respuestaFactory.crearRespuestaError(TransferenciaView.class, mensaje.getMensaje(), null);
			}
			TransferenciaDTO transferenciaDTO = cargarTransferenciaDTO(transferenciaView);
			transferenciaView.setIsRioRio(true);

			return verificandoSiDestinoEsRioRio(transferenciaView, transferenciaDTO, cuentasViewFiltradas);

		} catch (NullPointerException nex) {
			LOGGER.error(MSJ_ERROR_SOLICITAR_NUEVA_TRANSFERENCIA, nex);
			return getRespuestaErrorParaUnaTransferencia(transferenciaView);
		} catch (BusinessException bex) {
			LOGGER.error(MSJ_ERROR_SOLICITAR_NUEVA_TRANSFERENCIA, bex);
			return getRespuestaErrorParaUnaTransferencia(transferenciaView);
		}
	}

	/**
	 * Armar cuentas origen.
	 *
	 * @author manuel.vargas B041299
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the cuentas view
	 * @throws AliasCorrespondienteCuentaPropiaUnicaException
	 *             the alias correspondiente cuenta propia unica exception
	 */
	private CuentasView armarCuentasOrigen(TransferenciaView transferenciaView) {
		Respuesta<CuentasView> respuestaCuentasView = cuentaManager.getCuentasSaldo();
		if (respuestaCuentasView.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return null;
		}
		List<CuentasAdhesionDebitoView> listaCuentasAdhesionDebitoViewList = TransferenciaUtil
				.eliminarCuentasCerradasYFiltroOrigen(respuestaCuentasView.getRespuesta(), transferenciaView);
		CuentasView cuentasViewFiltradas = new CuentasView();
		cuentasViewFiltradas.setCuentas(listaCuentasAdhesionDebitoViewList);
		transferenciaView.setFromAgendaDestinatario(false);
		return cuentasViewFiltradas;
	}

	/**
	 * Solicitar transferencia a Otros Bancos.
	 *
	 * @author manuel.vargas B041299
	 * @param transferenciaView
	 *            the transferencia view
	 * @param userAgent
	 *            the user agent
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> solicitarTransferenciaOtroBanco(TransferenciaView transferenciaView, String userAgent) {
		LOGGER.info(MSJ_SOLICITANDO_NUEVA_TRANSFERENCIA);
		transferenciaView.generarNuevoIdSesion();
		transferenciaView.setIsRioRio(false);
		validarFormatoCBU(transferenciaView);

		try {

			LOGGER.info(VALIDA_TRJ_BANELCO_ACTIVA);
			String tarjetaBanelcoActiva = obtenerTarjetaBanelcoActiva();
			if (null == tarjetaBanelcoActiva ) {
				LOGGER.info(ERROR_TARJETA_BANELCO_INACTIVA);
				Mensaje mensaje = mensajeBO
						.obtenerMensajePorCodigo(CodigoMensajeConstantes.CUENTA_SIN_TARJETA_BANELCO_ACTIVA);
				return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(TransferenciaView.class,
						mensaje.getMensaje(), TipoError.CLIENTE_SIN_MONEDA_SIN_TRJ_BANELCO_HABILITADA.getDescripcion());
			}

			LOGGER.info(VALIDA_CUENTAS_ORIGEN_Y_DESTINO_CON_MISMA_MONEDA);

			String moneda = transferenciaView.getMoneda();
			Cuenta cuentaOrigenConMonedaDeDestinoValida = TransferenciaUtil
					.obtenerPrimerCuentaConMonedaDestino(sesionCliente.getCliente().getCuentas(), moneda);
			
			//si no tiene cuentas retail que tenga el tipo de moneda selecciona => busco en cuentas banca privada si tiene alguna.
			if(cuentaOrigenConMonedaDeDestinoValida == null) {
				cuentaOrigenConMonedaDeDestinoValida = TransferenciaUtil.obtenerPrimerCuentaConMonedaDestino(sesionCliente.getCliente().getCuentasPrivadas(), moneda);
			}
			
			TransferenciaDTO transferenciaDTO = cargarTransferenciaDTO(transferenciaView);
			if (null != cuentaOrigenConMonedaDeDestinoValida) {
				transferenciaDTO.setCuentaOrigen(cuentaOrigenConMonedaDeDestinoValida);
				LOGGER.info(MSJ_INFO_OBTENIENDO_CUENTAS_CLIENTE);
				CuentasView cuentasViewFiltradas = new CuentasView();
				cuentasViewFiltradas.setCuentas(new ArrayList<CuentasAdhesionDebitoView>());
				if(!sesionCliente.getCliente().getCuentasMonetarias().isEmpty()) {//Si no tiene cuentas retail no llama a los servicios de saldos.
					cuentasViewFiltradas = armarCuentasOrigen(transferenciaView);
				}

				Respuesta<List<ResumenDetalleCuenta>> respResumenesDC = obtenerCuentasySaldosBP();
				//error al consultar los saldos de banca privada
				if( !EstadoRespuesta.OK.equals(respResumenesDC.getEstadoRespuesta())) {
					return respuestaFactory.transformar(null, respResumenesDC);
				}
				Respuesta<CuentasView> listaCuentaBP = CuentasBancaPrivadaUtil.convertirCuentasView(respResumenesDC);
				cuentasViewFiltradas.getCuentas().addAll(listaCuentaBP.getRespuesta().getCuentas());
				
		        if (cuentasViewFiltradas.getCuentas().size() == 0) {
		            LOGGER.info(NO_EXISTEN_CUENTAS_ASOCIADAS_AL_CLIENTE);
		            throw new AliasCorrespondienteCuentaPropiaUnicaException();
		        }
				if (!transferenciaView.isDestinatarioNoVerificado()) {
				    if (ISBANStringUtils.validarCVU(transferenciaView.getCbu()) && !moneda.equalsIgnoreCase(PESOS) && 
				            !moneda.equalsIgnoreCase(DivisaEnum.PESO.getMoneda()) && trfCvuDolaresHabilitado == 0) {
				        return armarRespuestaErrorMonedaCVU();
			        }
					LOGGER.info(VERIFICANDO_CON_CONS_TITULAR_CBU);
					return verificandoSiDestinoOtrosBancos(transferenciaView, transferenciaDTO, cuentasViewFiltradas,
							tarjetaBanelcoActiva, userAgent);
				} else {
					LOGGER.info(SOLICITUD_POR_COELSA);
					Respuesta<TransferenciaView> respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(TransferenciaView.class,
							"", TipoError.DEST_NO_VERIFICADO_ERR.getDescripcion());
					respuesta.setRespuesta(cargarVistaSolicitar(transferenciaView, sesionCliente.getCliente(),
							cuentasViewFiltradas, transferenciaDTO, false, Boolean.FALSE));
					respuesta.getRespuesta().setTransferenciaManual(true);
					return respuesta;
				}
			} else {
				LOGGER.info(ERROR_CON_MONEDA_O_TRJ);
				Mensaje mensaje = mensajeBO
						.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CON_MONEDA_DESTINO_O_TARJETA_DEBITO);
				return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(TransferenciaView.class,
						mensaje.getMensaje(), TipoError.CLIENTE_NO_HABILITADO_PARA_TRANSFERIR.getDescripcion());
			}

		} catch (AliasCorrespondienteCuentaPropiaUnicaException e) {
			LOGGER.error(MSJ_ERROR_SOLICITAR_NUEVA_TRANSFERENCIA, e);
			return getRespuestaErrorParaUnaTransferencia(transferenciaView);
		} catch (NullPointerException nex) {
			LOGGER.error(MSJ_ERROR_SOLICITAR_NUEVA_TRANSFERENCIA, nex);
			return getRespuestaErrorParaUnaTransferencia(transferenciaView);
		} catch (BusinessException bex) {
			LOGGER.error(MSJ_ERROR_SOLICITAR_NUEVA_TRANSFERENCIA, bex);
			return getRespuestaErrorParaUnaTransferencia(transferenciaView);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * TransferenciaManager#solicitarNuevaTransferencia(ar.com.santanderrio.obp.
	 * servicios.transferencias.web.view.TransferenciaView)
	 */
	@Override
	public Respuesta<TransferenciaView> solicitarNuevaTransferencia(TransferenciaView transferenciaView, String userAgent) {
		String moneda = transferenciaView.getMoneda();
		Respuesta<TransferenciaView> respuesta;
		sesionParametros.setValidacionHash(null);
		sesionParametros.resetearDesafioEnCurso();
		sesionParametros.setRsaRiesgoTransferenciaDTO(null);
		sesionParametros.setCuentaSeleccionadaParaTransferencia("", "");
		TransferenciaUtil.limpiarDatosTransferenciaDestinoDeSesionParametros(sesionParametros);
		transferenciaView.setMensajesAyuda(obtenerAyudasConTemplate());
		if (null != moneda && !STRING_VACIO.equals(moneda)) {
			respuesta = solicitarTransferenciaOtroBanco(transferenciaView, userAgent);

			if(respuesta.getRespuesta() != null) {

				this.setFlagShowMsgMEP(respuesta.getRespuesta());
				this.fundsMessageValidations(respuesta.getRespuesta());
			}

		} else {
			respuesta = solicitarTransferenciaRioRio(transferenciaView);
		}
		grabarEstadistica(respuesta);
		if (!EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			super.obtenerMensajeSiHorarioDeTransferenciaNoEsValido(respuesta);
		}

		return respuesta;
	}

	/**
	 * Graba una estadistica simple segun estado de respuesta.
	 *
	 * @param respuesta
	 *            the respuesta
	 */
	private void grabarEstadistica(Respuesta<TransferenciaView> respuesta) {
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_CONFIGURACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_CONFIGURACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
	}

	/**
	 * Cargar ayudas.
	 *
	 * @return the list
	 */
	@Override
	public List<AyudaView> obtenerAyudasConTemplate() {
		AyudaView ayudaView1 = new AyudaView(ayudaTITNuevaTransferenciaLimiteTransferencia, ayudaTemplateNuevaTransferenciaLimite);
		AyudaView ayudaView2 = new AyudaView(ayudaTITNuevaTransferenciaPlazoAcreditacion, ayudaTemplateNuevaTransferenciaPlazoAcreditacion);
		AyudaView ayudaView3 = new AyudaView(ayudaTITNuevaTransferenciaFecha, ayudaTemplateNuevaTransferenciaFecha);
		AyudaView ayudaView4 = new AyudaView(ayudaTITNuevaTransferenciaDesc, ayudaTemplateNuevaTransferenciaDesc);
		return TransferenciaUtil.cargarListaTemplateDeAyudas(ayudaView1, ayudaView2, ayudaView3, ayudaView4);
	}

	/**
	 * Obtener cuenta con misma moneda y activa (5, TIPO_CUENTA_BANELCO).
	 * 
	 * @return el numero de la tarjeta de credito
	 */
	private String obtenerTarjetaBanelcoActiva() {
		LOGGER.info(MSJ_INFO_OBTENIENDO_TARJETA_BANELCO_ACTIVA);
		List<Cuenta> listaCuentas = sesionCliente.getCliente().getCuentas();
		for (Cuenta cuenta : listaCuentas) {
			if (TARJETA_CREDITO_ACTIVA.equals(cuenta.getEstadoTarjetaCredito())) {
				int tipocuenta = TipoCuenta.fromAbreviatura(cuenta.getTipoCuentaEnum().getAbreviatura()).getCodigo();
				if (tipocuenta == TIPO_CUENTA_BANELCO) {
					return cuenta.getNroTarjetaCredito();
				}
			}
		}
		
		List<Cuenta> listasCuentasBP = sesionCliente.getCliente().getCuentasPrivadas();
		for (Cuenta cuenta : listasCuentasBP) {
			if (TARJETA_CREDITO_ACTIVA.equals(cuenta.getEstadoTarjetaCredito())) {
				int tipocuenta = TipoCuenta.fromAbreviatura(cuenta.getTipoCuentaEnum().getAbreviatura()).getCodigo();
				if (tipocuenta == TIPO_CUENTA_BANELCO) {
					return cuenta.getNroTarjetaCredito();
				}
			}
		}
		
		LOGGER.info(MSJ_INFO_NO_EXISTEN_TARJETA_BANELCO_ACTIVAS);
		return null;
	}

	/**
	 * Verificando si la cuenta destino pertenece a otros bancos. En el
	 * <code>flujo de NuevoDestinatario</code>.
	 * <p>
	 * A este punto se llega desde
	 * {@link #solicitarNuevaTransferencia(TransferenciaView)} con
	 * <code>isFromAgendaDestinatario</code> en false. Y <code>isRioRio</code>
	 * en false.
	 * <ul>
	 * <li>se utiliza <code>CNSTITCBU</code> para consultar titular por CBU.
	 * </ul>
	 *
	 * @author B041299 Manuel.Vargas
	 * @param transferenciaView
	 *            the transferencia view
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param cuentasViewFiltradas
	 *            the cuentas view filtradas
	 * @param nroTarjetaActiva
	 *            the nro tarjeta activa
	 * @param userAgent
	 *            the user agent
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 * @see #solicitarNuevaTransferencia(TransferenciaView)
	 * @see #armarRespuesta(TransferenciaView, TransferenciaDTO, CuentasView,
	 *      Respuesta, Respuesta)
	 * @see ContratoDAO.validarOrigenDestinoTransferencia
	 * @see TransferenciaBO.validarOrigenDestinoTransferencia
	 */
	private Respuesta<TransferenciaView> verificandoSiDestinoOtrosBancos(TransferenciaView transferenciaView,
			TransferenciaDTO transferenciaDTO, CuentasView cuentasViewFiltradas, String nroTarjetaActiva, String userAgent)
			throws BusinessException {
		Respuesta<TransferenciaView> respuesta = new Respuesta<TransferenciaView>();
		Respuesta<TransferenciaDTO> transferenciaRespuesta = new Respuesta<TransferenciaDTO>();

		transferenciaDTO.setIp(sesionCliente.obtenerIpV4SinPuntos());

		if (PESOS.equalsIgnoreCase(transferenciaView.getMoneda())) {
			transferenciaDTO.setMoneda(DivisaEnum.PESO);
		} else {
			transferenciaDTO.setMoneda(DivisaEnum.DOLAR);
		}

		transferenciaRespuesta = transferenciaBO.validarOrigenDestinoTransferencia(sesionCliente.getCliente(),
				transferenciaDTO, nroTarjetaActiva, transferenciaView.getMoneda(), transferenciaDTO.getCuentaOrigen(), userAgent);

		if (!EstadoRespuesta.OK.equals(transferenciaRespuesta.getEstadoRespuesta())) {

			// @maximilianos al venir por Nuevo Destinatario el feedback es
			// distinto
			// al de agenda cuando no coincide moneda
			if (transferenciaRespuesta.getItemsMensajeRespuesta().get(0).getTipoError()
					.equals(TipoError.MONEDA_NO_COINCIDE_CON_MONEDA_CBU.getDescripcion())) {
				transferenciaRespuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
				transferenciaRespuesta.getItemsMensajeRespuesta().get(0)
						.setTipoError(TipoError.ERROR_CONSULTA_TITULAR.getDescripcion());
			}
			// TODO: emilio.watemberg: parche agregado porque Banelco devuelve
			// un OK
			// con respuestas vacia, entonces cuando no se puede verificar
			// correctamente el destinatario, cambiamos el error de tipo
			// 48_HORAS a TRANSFERENCIA_CON_REINTENTO_MANUAL
			if (transferenciaRespuesta.getEstadoRespuesta() == EstadoRespuesta.WARNING
					&& transferenciaRespuesta.getItemsMensajeRespuesta().size() > 0
					&& transferenciaRespuesta.getItemsMensajeRespuesta().get(0).getTipoError()
							.equals(TipoError.TRANSFERENCIA_48_HORAS.getDescripcion())) {
				transferenciaRespuesta.getItemsMensajeRespuesta().get(0)
						.setTipoError(TipoError.TRANSFERENCIA_CON_REINTENTO_MANUAL.getDescripcion());
			}
			// ***********************
		}

		return armarRespuesta(transferenciaView, transferenciaDTO, cuentasViewFiltradas, respuesta,
				transferenciaRespuesta);
	}

	/**
	 * Armar respuesta a Nuevo Destinatario cuando se esta en el <code>flujo de
	 * NuevoDestinatario</code>.
	 * <ul>
	 * <li>transferencia a SRio
	 * <li>transferencia a CBU
	 * </ul>
	 *
	 * @author B041299 Manuel.Vargas
	 * @param transferenciaView
	 *            the transferencia view
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param cuentasViewFiltradas
	 *            the cuentas view filtradas
	 * @param respuesta
	 *            the respuesta
	 * @param transferenciaRespuesta
	 *            the transferencia respuesta
	 * @return the respuesta
	 * @ver {@link #solicitarNuevaTransferencia(TransferenciaView)}
	 */
	private Respuesta<TransferenciaView> armarRespuesta(TransferenciaView transferenciaView,
			TransferenciaDTO transferenciaDTO, CuentasView cuentasViewFiltradas, Respuesta<TransferenciaView> respuesta,
			Respuesta<TransferenciaDTO> transferenciaRespuesta) {
		if (transferenciaRespuesta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			LOGGER.info(MSJ_INFO_ERROR_VALIDACION_ORIGEN_DESTINO);
			grabarEstadisticaDeInvocacion(transferenciaView.getIsRioRio(),
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuesta.setItemMensajeRespuesta(transferenciaRespuesta.getItemsMensajeRespuesta());
			respuesta.setRespuestaVacia(true);
			return respuesta;
		} else if (transferenciaRespuesta.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			LOGGER.info(MSJ_INFO_ERROR_VALIDACION_ORIGEN_DESTINO);
			grabarEstadisticaDeInvocacion(transferenciaView.getIsRioRio(),
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			if (transferenciaView.getImporte() != null) {
				transferenciaView
						.setImporte(ISBANStringUtils.formatearSaldo(new BigDecimal(transferenciaView.getImporte())));
			}
			transferenciaView.setDestinatarioNoVerificado(true);
			respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
			respuesta.setItemMensajeRespuesta(transferenciaRespuesta.getItemsMensajeRespuesta());
			respuesta.setRespuesta(transferenciaView);
			if (transferenciaRespuesta.getItemsMensajeRespuesta().get(0).getTipoError()
					.equals(TipoError.TRANSFERENCIA_48_HORAS.getDescripcion())) {
				respuesta.getRespuesta().setInmediata(transferenciaRespuesta.getRespuesta().isTransferenciaInmediata());
			}
            if (transferenciaDTO.isVaPorCoelsa()
                    || TipoError.DEST_NO_VERIFICADO_ERR.getDescripcion()
                            .equals(transferenciaRespuesta.getItemsMensajeRespuesta().get(0).getTipoError())
                    || TipoError.TRANSFERENCIA_CON_REINTENTO_MANUAL.getDescripcion()
                            .equals(transferenciaRespuesta.getItemsMensajeRespuesta().get(0).getTipoError())) {
                estadisticaManager.add(EstadisticasConstants.ESTADISTICA_CONFIGURACION,
	                    EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
			}
			return respuesta;
		} else {
			grabarEstadisticaDeInvocacion(transferenciaView.getIsRioRio(),
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			respuesta = respuestaFactory.crearRespuestaOk(TransferenciaView.class);
			transferenciaDTO.setTitular(transferenciaRespuesta.getRespuesta().getTitular());
			try {
				respuesta.setRespuesta(cargarVistaSolicitar(transferenciaView, sesionCliente.getCliente(),
						cuentasViewFiltradas, transferenciaDTO, false, Boolean.FALSE));
			} catch (BusinessException bex) {
				LOGGER.error(MSJ_ERROR_SOLICITAR_NUEVA_TRANSFERENCIA, bex);
				estadisticaManager.add(EstadisticasConstants.ESTADISTICA_CONFIGURACION,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return getRespuestaErrorParaUnaTransferencia(transferenciaView);
			}
		}

		return respuesta;
	}

	/**
	 * Verificando si la cuenta destino pertenece a este banco (Rio/Rio). Busca
	 * en la lista de cuentas de session del cliente para ver si es propia. Usa
	 * CNSCTATIT, en cuentaManager.verificarCuenta(...)
	 * 
	 * @param transferenciaView
	 *            the transferencia view
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param cuentasViewFiltradas
	 *            the cuentas view filtradas
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 * @see CuentaManager
	 */
	private Respuesta<TransferenciaView> verificandoSiDestinoEsRioRio(TransferenciaView transferenciaView,
			TransferenciaDTO transferenciaDTO, CuentasView cuentasViewFiltradas) throws BusinessException {
		LOGGER.info(MSJ_INFO_VERIFICANDO_SI_CUENTA_ES_RIO_RIO);
		boolean isFormatoValido = TransferenciaUtil.validarFormatoCuenta(transferenciaView);
		if (isFormatoValido) {
			LOGGER.info(MSJ_INFO_VERIFICANDO_SI_CUENTA_ES_PROPIA);

			Respuesta<Cliente> cuentaDestinoVerificada = cuentaManager.verificarCuenta(transferenciaView, 1);
			if (EstadoRespuesta.ERROR.equals(cuentaDestinoVerificada.getEstadoRespuesta())) {
				LOGGER.info(MSJ_ERROR_CNS_TIT_CTA);
				grabarEstadisticaDeInvocacion(transferenciaView.getIsRioRio(),
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				// Cierre de sucursales
				if (cuentaDestinoVerificada.getItemsMensajeRespuesta().get(0).getTipoError().equals(TipoError.ERROR_CUENTA_MIGRADA.getDescripcion())) {
					Respuesta<TransferenciaView> resp = respuestaFactory.crearRespuestaErrorPersonalizado(TransferenciaView.class,
							cuentaDestinoVerificada.getItemsMensajeRespuesta().get(0).getMensaje(), cuentaDestinoVerificada.getItemsMensajeRespuesta().get(0).getTipoError());
					resp.getItemsMensajeRespuesta().get(0).setTag(cuentaDestinoVerificada.getItemsMensajeRespuesta().get(0).getTag());
					return resp;
				}
				return respuestaFactory.crearRespuestaError(TransferenciaView.class, "",
						TipoError.ERROR_EN_SERVICIO_TIMEOUT_CUENTA, CodigoMensajeConstantes.ERROR_GENERICO);
			} else if (EstadoRespuesta.WARNING.equals(cuentaDestinoVerificada.getEstadoRespuesta())) {
				LOGGER.info(MSJ_WARNING_CNS_TIT_CTA);
				grabarEstadisticaDeInvocacion(transferenciaView.getIsRioRio(),
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				Mensaje respMensaje = mensajeManager
						.obtenerMensajePorCodigo(CodigoMensajeConstantes.TRANSFERENCIA_ERROR_CNS_CUENTA);
				return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(TransferenciaView.class,
						respMensaje.getMensaje(), TipoError.ERROR_EN_SERVICIO_CONSULTA_CUENTA.getDescripcion());
			}
			grabarEstadisticaDeInvocacion(transferenciaView.getIsRioRio(),
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			String tipoCuentaDestino = transferenciaView.getTipoCuentaDestino();
			IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(
					transferenciaView.getNroCuentaDestino());
			boolean esCuentaPropia = TransferenciaUtil.esCuentaPropia(sesionCliente.getCliente(), identificacionCuenta,
					tipoCuentaDestino, true);

			
			if(!esCuentaPropia) {
				esCuentaPropia = TransferenciaUtil.esCuentaPropiaBP(sesionCliente.getCliente(), identificacionCuenta,
						tipoCuentaDestino, true);
			}
			
			return verificandoIsCuentaPropia(transferenciaView, transferenciaDTO, cuentasViewFiltradas,
					tipoCuentaDestino, esCuentaPropia, cuentaDestinoVerificada);
		} else {
			Mensaje respMensaje = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.SIN_CUENTA_DESTINO);
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje(respMensaje.getMensaje());
			return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(TransferenciaView.class,
					item.getMensaje(), TipoError.ERROR_EN_FORMATO_CUENTA.getDescripcion());
		}
	}

	/**
	 * Verificando is cuenta propia.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param cuentasViewFiltradas
	 *            the cuentas view filtradas
	 * @param tipoCuentaDestino
	 *            the tipo cuenta destino
	 * @param esCuentaPropia
	 *            the es cuenta propia
	 * @param cuentaDestinoVerificada
	 *            the cuenta destino verificada
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	private Respuesta<TransferenciaView> verificandoIsCuentaPropia(TransferenciaView transferenciaView,
			TransferenciaDTO transferenciaDTO, CuentasView cuentasViewFiltradas, String tipoCuentaDestino,
			boolean esCuentaPropia, Respuesta<Cliente> cuentaDestinoVerificada) throws BusinessException {
		if (esCuentaPropia) {
			LOGGER.info(MSJ_INFO_VERIFICANDO_SI_LA_CUENTA_ES_LA_UNICA);
			if (cuentasViewFiltradas.getCuentas().size() < 1) {
				LOGGER.info(LA_CUENTA_ES_UNICA);
				Mensaje respMensaje = mensajeManager
						.obtenerMensajePorCodigo(CodigoMensajeConstantes.CUENTA_PROPIA_Y_UNICA);
				return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(TransferenciaView.class,
						respMensaje.getMensaje(), TipoError.CUENTA_PROPIA_Y_UNICA.getDescripcion());
			} else {
				TransferenciaView transView = cargarVistaSolicitar(transferenciaView,
						cuentaDestinoVerificada.getRespuesta(), cuentasViewFiltradas, transferenciaDTO, esCuentaPropia,
						Boolean.TRUE);
				return respuestaFactory.crearRespuestaOk(TransferenciaView.class, transView);
			}
		} else {
			LOGGER.info(MSJ_INFO_OBTENIENDO_DESTINATARIO);
			if (EstadoRespuesta.OK.equals(cuentaDestinoVerificada.getEstadoRespuesta())) {
				boolean destinoValido = false;
				destinoValido = TransferenciaUtil.obtenerCuentasValidasDelTipoCuentaDestino(
						TipoCuenta.fromAbreviatura(tipoCuentaDestino), sesionCliente.getCliente());

				if (destinoValido) {
					TransferenciaView transView = cargarVistaSolicitar(transferenciaView, sesionCliente.getCliente(),
							cuentaDestinoVerificada.getRespuesta(), cuentasViewFiltradas, transferenciaDTO,
							esCuentaPropia, Boolean.TRUE);
					return respuestaFactory.crearRespuestaOk(TransferenciaView.class, transView);
				} else {
					LOGGER.info(MSJ_INFO_DESTINATARIO_CON_DIFERENTE_MONEDA_INCOMPATIBLE);
					Mensaje mensaje = mensajeManager
							.obtenerMensajePorCodigo(CodigoMensajeConstantes.SIN_CUENTA_DESTINO);
					return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(TransferenciaView.class,
							mensaje.getMensaje(), TipoError.NO_EXISTE_DESTINATARIO.getDescripcion());
				}
			}
			LOGGER.info(MSJ_INFO_DESTINATARIO_NO_VERIFICADO);
			Mensaje respMensaje = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.SIN_CUENTA_DESTINO);
			return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(TransferenciaView.class,
					respMensaje.getMensaje(), TipoError.NO_EXISTE_DESTINATARIO.getDescripcion());
		}
	}

	// /**
	// * Crear mapa de la vista.
	// *
	// * @param transferenciaView
	// * the transferencia view
	// * @return the map
	// */
	// private Map<String, Object>
	// crearMapaDeTransferenciaView(TransferenciaView transferenciaView) {
	// LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
	// Map<String, Object> mapaAtributos = new HashMap<String, Object>();
	// mapaAtributos.put("id", transferenciaView.getId());
	// mapaAtributos.put("idSesion", transferenciaView.getIdSesion());
	// mapaAtributos.put("titular", transferenciaView.getTitular());
	// mapaAtributos.put("cbu", transferenciaView.getCbu());
	// mapaAtributos.put("cuit", transferenciaView.getCuit());
	// mapaAtributos.put("desafio", transferenciaView.getDesafio());
	// mapaAtributos.put("nroCuentaDestino",
	// transferenciaView.getNroCuentaDestino());
	// mapaAtributos.put("banco", transferenciaView.getBanco());
	// mapaAtributos.put("moneda", transferenciaView.getMoneda());
	// mapaAtributos.put("importe", transferenciaView.getImporte());
	// mapaAtributos.put("nroCuenta", transferenciaView.getNroCuenta());
	// mapaAtributos.put("fechaEjecucion",
	// transferenciaView.getFechaEjecucion());
	// LOGGER.info("String mapa vista: " + mapaAtributos.toString());
	// return mapaAtributos;
	// }

	// /**
	// * Cargar hash de la vista en sesion.
	// *
	// * <P>
	// * Solo crea un hash de los datos estaticos, es decir, que el usuario no
	// * puede cambiar desde la vista
	// * </P>
	// *
	// * @author emilio.watemberg
	// * @param transferenciaView
	// * the transferencia view
	// */
	// private void cargarHashDeLaVistaEnSesion(TransferenciaView
	// transferenciaView) {
	// String hashView =
	// HashUtils.obtenerHash(crearMapaDeTransferenciaView(transferenciaView));
	// LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
	// sesionParametros.setValidacionHash(hashView);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * TransferenciaManager#validarContratoTransferencia()
	 */
	@Override
	public Respuesta<Boolean> validarContratoTransferencia() {

		this.grabarEstadistica(EstadisticasConstants.SELECCION_DESTINATARIO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		boolean tieneAdhesionMyA = verificandoSiTieneAdhesionMyA();
		if (!tieneAdhesionMyA) {
			tieneAdhesionMyA = true;
		}
		Boolean contratoHabilitado = verificandoSiContieneAdhesionContratoTransferencias();
		Respuesta<Boolean> respuestaTieneDesafiosHabilitados = verificandoSiContieneDesafiosHabilitados();

		Respuesta<Boolean> respuesta = new Respuesta<Boolean>();
		respuesta.setRespuesta(Boolean.TRUE);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		LOGGER.info(TIENE_CONTRATO + contratoHabilitado);
		if (!contratoHabilitado) {
			respuesta = armarRespuestaErrorSinContrato();
		} else if (!tieneAdhesionMyA) {
			respuesta = armarRespuestaErrorSinMyA();
		} else if (respuestaTieneDesafiosHabilitados.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)
				&& respuestaTieneDesafiosHabilitados.getRespuesta().equals(Boolean.FALSE)) {
			respuesta = respuestaTieneDesafiosHabilitados;
		}
		return respuesta;
	}

	/**
	 * Verificando si contiene adhesion contrato transferencias.
	 *
	 * @return true, if successful
	 */
	private Boolean verificandoSiContieneAdhesionContratoTransferencias() {
		LOGGER.info(VALIDANDO_ADHESION_A_CONTRATO);
		return contratoBO.tieneContrato(TipoContratoEnum.TRANSFERENCIA, sesionCliente.getCliente());
	}

	/**
	 * Verificando si tiene adhesion my A.
	 *
	 * @return true, if successful
	 */
	private boolean verificandoSiTieneAdhesionMyA() {
		LOGGER.info(VALIDANDO_ADHESION_A_M_Y_A);
		CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
		boolean tieneAdhesionMyA = credencialesMya.getClienteEstado()
				.equals(ClienteEstadoEnum.SUSCRIPTO_ACTIVO.getCodigo());
		LOGGER.info(ADHESION_A_M_Y_A + tieneAdhesionMyA);
		return tieneAdhesionMyA;
	}

	/**
	 * Verificando si contiene desafios habilitados.
	 *
	 * @return the respuesta
	 */
	private Respuesta<Boolean> verificandoSiContieneDesafiosHabilitados() {
		LOGGER.info(VALIDANDO_HABILITACION_DE_DESAFIOS);
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		autentificacionDTO.setOperacion(Integer.parseInt(valorDesafioTransferencias));
		Respuesta<Boolean> respuestaTieneDesafiosHabilitados = autentificacionManager
				.tieneAlgunDesafioHabilitadoParaLaOperacion(autentificacionDTO);
		return respuestaTieneDesafiosHabilitados;
	}

	/**
	 * Armar respuesta error sin my A.
	 *
	 * @return the respuesta
	 */
	private Respuesta<Boolean> armarRespuestaErrorSinMyA() {
		Respuesta<Boolean> respuesta;
		String mensajeError;
		mensajeError = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_ADHESION_MYA)
				.getMensaje();
		respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(Boolean.class, mensajeError,
				TipoError.ERROR_SIN_CONTRATO_TRANSFERENCIA.getDescripcion());
		respuesta.setRespuesta(Boolean.FALSE);
		return respuesta;
	}

	/**
	 * Armar respuesta error sin contrato.
	 *
	 * @return the respuesta
	 */
	private Respuesta<Boolean> armarRespuestaErrorSinContrato() {
		Respuesta<Boolean> respuesta;
		String mensajeError;
		mensajeError = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_CONTRATO_TRANSFERENCIA)
				.getMensaje();
		respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(Boolean.class, mensajeError,
				TipoError.ERROR_SIN_CONTRATO_TRANSFERENCIA.getDescripcion());
		respuesta.setRespuesta(Boolean.FALSE);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * TransferenciaManager#ejecutarNuevaTransferencia(ar.com.santanderrio.obp.
	 * servicios.transferencias.web.view.TransferenciaView)
	 */
	@Override
	public Respuesta<TransferenciaView> ejecutarNuevaTransferencia(TransferenciaView transferenciaView) {
		TransferenciaUtil.validarHashDeLaVistaEnSesion(transferenciaView, sesionParametros);
		Boolean isProgramada;
		if (sesionParametros.getRsaRiesgoTransferenciaDTO() == null
				|| !sesionParametros.getRsaRiesgoTransferenciaDTO().getRiesgoAlto()) {
			isProgramada = TransferenciaUtil.isTransferenciaProgramada(transferenciaView.getFechaEjecucion());
		} else {
			isProgramada = !transferenciaView.isInmediata();
		}
		
		if (ISBANStringUtils.validarCVU(transferenciaView.getCbu())) {
		    DivisaEnum moneda = DivisaEnum.fromMonedaString(transferenciaView.getMoneda().toLowerCase());
		    if (!DivisaEnum.PESO.equals(moneda) && trfCvuDolaresHabilitado == 0) {
				registrarMetricaTransferenciaConErrores(transferenciaView, DetalleError.CVU_DOLARES_NO_HABILITADO, TransferStatus.FAIL);
	            return armarRespuestaErrorMonedaCVU();
	        }
		    if (!TransferenciaUtil.validarTransferenciaCVU(new BigDecimal(transferenciaView.getImporte()), moneda, 
		            trfCvuDolaresHabilitado, trfCVUImporteDolaresMax, trfCVUImportePesosMax)) {
				registrarMetricaTransferenciaConErrores(transferenciaView, DetalleError.IMPORTE_LIMITE_TRANSFERENCIA, TransferStatus.WARNING);
    		    return armarRespuestaWarningValidacionImporteCVU(moneda);
		    }
		}
		//hay que tener encuenta las cuentas de banca privada. (aca se fija las cuentas del cliente)
		cargarVistaConDatosNecesariosParaRsa(transferenciaView, isProgramada);

		Respuesta<TransferenciaView> respuestaFinal = llamarRSAyAnalizarDesafiosParaUnaNuevaTransferencia(
				transferenciaView);
		
		boolean riesgoAlto = sesionParametros.getRsaRiesgoTransferenciaDTO() != null
                && sesionParametros.getRsaRiesgoTransferenciaDTO().getRiesgoAlto();

        if (!EstadoRespuesta.WARNING.equals(respuestaFinal.getEstadoRespuesta())) {
			sesionParametros.setRsaRiesgoTransferenciaDTO(null);
		}

		if (!EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())) {
			registrarMetricaTransferenciaConErrorRsa(transferenciaView, respuestaFinal);
			return respuestaFinal;
		}

		if (transferenciaView.getDesafio() == null) {
			estadisticaManager.add(EstadisticasConstants.NUEVA_TRANSFERENCIA_SIN_REGLAS_DESAFIOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}

		inicializarContadorDeIntentos(transferenciaView);
		LOGGER.info(MSJ_INFO_NUEVA_TRANSFERENCIA);
		Respuesta<TransferenciaDTO> respuestaTransferenciaBO = null;
		LOGGER.info(MSJ_INFO_LA_TRANSFERENCIA_ES_PROGRAMADA, isProgramada == true ? SI : NO);
		TransferenciaDTO transferenciaDTO = cargarTransferenciaDTOConTransferenciaView(transferenciaView);
		transferenciaDTO.setRiesgoAlto(riesgoAlto);
		if (isProgramada) {
			respuestaTransferenciaBO = transferenciaBO.ejecutarTransferenciaProgramada(sesionCliente.getCliente(),
					transferenciaDTO, transferenciaView.isFromAgendaDestinatario(), isProgramada);
		} else {
			respuestaTransferenciaBO = transferenciaBO.ejecutarTransferenciaInmediata(sesionCliente.getCliente(),
					transferenciaDTO, transferenciaView.isFromAgendaDestinatario(), isProgramada);
		}
		sesionParametros.setCuentaSeleccionadaParaTransferencia("", "");
		switch (respuestaTransferenciaBO.getEstadoRespuesta()) {
		case OK:
			saveTransactionMeter(transferenciaView);
			registrarMetricaTransferenciaOk();
			return armarRespuestaOK(transferenciaView, isProgramada, respuestaTransferenciaBO);
		case WARNING:
			registrarMetricaTransferenciaConErrores(transferenciaView, respuestaTransferenciaBO, TransferStatus.WARNING);
			return armarRespuestaWARNING(transferenciaView, respuestaTransferenciaBO);
		case ERROR:
			registrarMetricaTransferenciaConErrores(transferenciaView, respuestaTransferenciaBO, TransferStatus.FAIL);
			return armarRespuestaERROR(transferenciaView, respuestaTransferenciaBO);
		default:
			registrarMetricaTransferenciaConErrores(transferenciaView, DetalleError.ERROR_GENERICO_MANAGER, TransferStatus.FAIL);
			throw new RobotException(ERROR_GENERICO_MANAGER);
		}

	}

	private void setTransactionMeter(TransferenciaView transferenciaView) {
		if(canInvokeTransferRsaApi(transferenciaView)) {
			final TransferenciaSumResponse response = transferenciaRsaApi.getTransactionSum(
					Long.parseLong(sesionCliente.getCliente().getNup()),
					formatearCuit(transferenciaView.getCuit()),
					DivisaEnum.fromMonedaString(transferenciaView.getMoneda()).getCodigo()
			);
			transferenciaView.setControlSum(response);
		}

	}



	private void saveTransactionMeter(TransferenciaView transferenciaView) {
		if(canInvokeTransferRsaApi(transferenciaView)) {
			final TransferenciaCreationRequest request = new TransferenciaCreationRequest(
					new BigDecimal(transferenciaView.getImporte()),
					LocalDateTime.now().toString("yyyy-MM-dd HH:mm:ss"),
					formatearCuit(transferenciaView.getCuit()),
					DivisaEnum.fromMonedaString(transferenciaView.getMoneda()).getCodigo()
			);
			transferenciaRsaApi.postTransaction(
					Long.parseLong(sesionCliente.getCliente().getNup()),
					request
			);
		}
	}

	private String formatearCuit(String cuit) {
		if(!cuit.matches("^[0-9]{2}-[0-9]{8,9}-[0-9]{1}")) {
			return cuit.substring(0, 2) + "-" + cuit.substring(2, cuit.length() - 1) + "-" + cuit.substring(cuit.length() - 1);
		}
		return cuit;
	}

	private boolean canInvokeTransferRsaApi(TransferenciaView transferenciaView) {
		return transferenciaView.getCuit() != null && transferenciaView.getMoneda() != null;
	}

    /**
	 * Armar respuesta error moneda CVU.
	 *
	 * @return the respuesta
	 */
    private Respuesta<TransferenciaView> armarRespuestaErrorMonedaCVU() {
        LOGGER.error("Transferencia CVU - Moneda invalida");
        Respuesta<TransferenciaView> resp = new Respuesta<TransferenciaView>();
        resp.setRespuestaVacia(Boolean.TRUE);
        resp.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(TransferenciaUtil.MSG_ERROR_MONEDA_INVALIDA_CVU);
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_CVU_DOLARES_NO_HABILITADO.getDescripcion());
        resp.add(itemMensajeRespuesta);
        return resp;
    }

    /**
	 * Armar respuesta warning validacion importe CVU.
	 *
	 * @param moneda
	 *            the moneda
	 * @return the respuesta
	 */
    private Respuesta<TransferenciaView> armarRespuestaWarningValidacionImporteCVU(DivisaEnum moneda) {
        Respuesta<TransferenciaView> resp = new Respuesta<TransferenciaView>();
        resp.setRespuestaVacia(Boolean.TRUE);
        resp.setEstadoRespuesta(EstadoRespuesta.WARNING);
        String importeMax = DivisaEnum.PESO.equals(moneda) ? trfCVUImportePesosMax : trfCVUImporteDolaresMax;
        String mensajeError = MessageFormat.format(MSG_ERROR_IMPORTE_LIMITE_CVU, moneda.getSimbolo().concat(importeMax));
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(mensajeError);
        itemMensajeRespuesta.setTag("importe");
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_IMPORTE_LIMITE_TRANSFERENCIA.getDescripcion());
        resp.add(itemMensajeRespuesta);
        agregarMensajeAyuda(resp);
        return resp;
    }

	/**
	 * Agregar mensaje ayuda.
	 *
	 * @param resp
	 *            the resp
	 */
	private void agregarMensajeAyuda(Respuesta<TransferenciaView> resp) {
        String mensajeAyudaFormateado = MessageFormat.format(TransferenciaUtil.TRANSF_MSG_AYUDA_IMPORTE_MAX,
                trfInmediataBrImporteDolaresMax, trfInmediataBrtImportePesosMax, trfInmediataObImportePesosMax,
                trfInmediataBrpImportePesosMax, trfInmediataObImporteDolareMax,
                trfInmediataObpImporteDolarespesosMax, trfCVUImportePesosMax);
        ItemMensajeRespuesta itemMensajeRespuestaAyuda = new ItemMensajeRespuesta(mensajeAyudaFormateado);
        itemMensajeRespuestaAyuda.setTipoError(TAG_AYUDA);
        resp.add(itemMensajeRespuestaAyuda);
    }

	/**
	 * Ejecutar alta destinatario.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the string
	 */
	private String ejecutarAltaDestinatario(TransferenciaView transferenciaView) {
		Cliente cliente = sesionCliente.getCliente();
		String ip = sesionParametros.getRegistroSession().getIp();
		String cuitCuil = sesionAgenda.getCuitCuil();

		String respuestaAltaDestinatario = altaDestinatarioBO.realizarAltaDestinatario(ip, cliente, transferenciaView,
				transferenciaView.getTitular(), cuitCuil);
		return respuestaAltaDestinatario;
	}

	/**
	 * Grabar estadistica previa a la invocacion de la ejecucion. Tanto para OB
	 * como para RR/RTercero.
	 *
	 * @param isRioRio
	 *            the is rio rio
	 * @param estadoEstadistica
	 *            the estado estadistica
	 */
	private void grabarEstadisticaDeInvocacion(boolean isRioRio, String estadoEstadistica) {
		if (isRioRio) {
			grabarEstadistica(EstadisticasConstants.SELECCION_DESTINATARIO_CUENTA_INGRESADA, estadoEstadistica);
		} else {
			grabarEstadistica(EstadisticasConstants.SELECCION_DESTINATARIO_VALIDACION_CBU, estadoEstadistica);
		}
	}

	/**
	 * Este metodo se encarga de llamar a RSA y desafiar, si asi lo requiere, al
	 * usuario.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> llamarRSAyAnalizarDesafiosParaUnaNuevaTransferencia(
			TransferenciaView transferenciaView) {

		if (transferenciaView.getIsRioRio() && transferenciaView.isCuentaPropia()) {
			LOGGER.info(MSJ_INFO_TRANSFERENCIA_ENTRE_CUENTAS_PROPIAS);

			if(!TransferenciaUtil.esCuentaPropiaCliente(sesionCliente.getCliente(), cargarCuentaDestinatario(transferenciaView.getNroCuentaDestino()))){

				TransferenciaUtil.loguearInfoPosibleFraudeCuentaPropia(LOGGER, transferenciaView.getNroCuentaDestino());

				return respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.RULE_DERIVACIONAPP, CodigoMensajeConstantes.RSA_DERIVACION_APP_USUARIO);
			}

			return respuestaFactory.crearRespuestaOk(TransferenciaView.class);

		} else {

			validarPosibleFraude(transferenciaView);

			if(!sesionCliente.getTieneTokenRSA()){
				registrarMetricaTransferenciaConErrores(transferenciaView, DetalleError.RSA_OFFLINE, TransferStatus.FAIL);

				if (canTransferWhenRSAOffline(transferenciaView)) {
					LOGGER.info("Validacion exitosa, el cliente puede transferir con RSA offline");

					if (transferenciaView.getBiocatchResponseData().getPolicyAction().equalsIgnoreCase(ALLOW)) {
						LOGGER.info(MSJ_INFO_TRANSFERENCIA_PERMITIDA_BIOCATCH_ALLOW);
						return respuestaFactory.crearRespuestaOk(TransferenciaView.class);
					}

					return analizarRSAyEjecutarDesafio(transferenciaView);

				} else {

					return armarRespuestaErrorRSAOffline(transferenciaView);

				}
			}

			return analizarRSAyEjecutarDesafio(transferenciaView);
		}
	}

	private void validarPosibleFraude(TransferenciaView transferenciaView) {

		boolean esFechaNull = transferenciaView.getFechaCreacionDestinatario() == null;
		boolean esDeAgenda = transferenciaView.isFromAgendaDestinatario();
		boolean coincideCBU = transferenciaView.getCbu() != null && transferenciaView.getCbu().equals(sesionParametros.getDatosTransferenciaDestino().getCbu());
		boolean esRioRio = transferenciaView.getIsRioRio();
		boolean coincideNroCuenta = transferenciaView.getNroCuentaDestino() != null && transferenciaView.getNroCuentaDestino().equals(sesionParametros.getDatosTransferenciaDestino().getNumeroCuenta());

		transferenciaView.setPif(false);

		boolean esPosibleFraude = false;



		if(esDeAgenda) {

			if(esRioRio && !coincideNroCuenta) esPosibleFraude = true;
			if(!esRioRio && !coincideCBU) esPosibleFraude = true;

		} else {

			if(!esFechaNull) esPosibleFraude = true;
		}

		LOGGER.info("Datos de validarPosibleFraude");
		LOGGER.info("TransferenciaView: fechaCreacion: {}, isFromAgenda: {}, CBU: {}, isRioRio: {}, numeroCuentaDestino: {}", transferenciaView.getFechaCreacionDestinatario(),
				transferenciaView.isFromAgendaDestinatario(), transferenciaView.getCbu(), transferenciaView.getIsRioRio(), transferenciaView.getNroCuentaDestino());
		LOGGER.info("DatosTransferenciaDestino: CBU: {}, numeroCuenta: {}", sesionParametros.getDatosTransferenciaDestino().getCbu(), sesionParametros.getDatosTransferenciaDestino().getNumeroCuenta());
		LOGGER.info("Validaciones: esFechaNull: {}, esDeAgenda: {}, coincideCBU: {}, esRioRio: {}, coincideNroCuenta: {}", esFechaNull, esDeAgenda, coincideCBU, esRioRio, coincideNroCuenta);

		if(esPosibleFraude) {
			transferenciaView.setPif(true);
			TransferenciaUtil.loguearInfoPosibleFraudeTerceros(LOGGER, transferenciaView, sesionParametros.getDatosTransferenciaDestino());
		}
	}

	private Respuesta<TransferenciaView> armarRespuestaErrorRSAOffline(TransferenciaView transferenciaView) {
		if(transferenciaView.getBiocatchResponseData().getPolicyAction() == null){
			LOGGER.info("RSA Offline y Biocatch offline, no se permite completar la transferencia.");
			Respuesta<TransferenciaView> respuestaRSABiocatchOffline = respuestaFactory.crearRespuestaError(TransferenciaView.class, StringUtils.EMPTY,
					TipoError.BIOCATCH_OFFLINE, CodigoMensajeConstantes.ERROR_GENERICO_RSA_OFFLINE);
			respuestaRSABiocatchOffline.setRespuesta(transferenciaView);
			return respuestaRSABiocatchOffline;
		}

		LOGGER.info("RSA Offline y el cliente no paso la validacion para operar offline, no se permite completar la transferencia.");
		Respuesta<TransferenciaView> respuestaRSAOfline = respuestaFactory.crearRespuestaError(TransferenciaView.class, StringUtils.EMPTY,
				TipoError.RSA_OFFLINE_VALIDACION_FALLIDA, CodigoMensajeConstantes.ERROR_GENERICO_RSA_OFFLINE);
		respuestaRSAOfline.setRespuesta(transferenciaView);
		return respuestaRSAOfline;
	}

	private boolean canTransferWhenRSAOffline(TransferenciaView transferenciaView) {
		//en este metodo deberia ir la validacion de biocatch...
		try {

			LOGGER.info("Inicio validacion para comprobar si el cliente puede transferir con RSA offline");

			if (transferenciaView.isPif()) {

				TransferenciaUtil.loguearInfoPosibleFraudeTerceros(LOGGER, transferenciaView, sesionParametros.getDatosTransferenciaDestino());

				return false;

			}

			String policyAction = transferenciaView.getBiocatchResponseData().getPolicyAction();

			LOGGER.info("PolicyAction: {}",policyAction);

			return policyAction.equalsIgnoreCase(ALLOW) || policyAction.equalsIgnoreCase(CHALLENGE);

		} catch (Exception e) {

			LOGGER.error("Error al validar si el cliente puede transferir con RSA offline", e);
			return false;
		}
	}

	private DestinatarioAgendaDTO getRecipientByCbu(Respuesta<DestinatariosFrecuentesDTO> destinatariosFrecuentesDTO, String cbu){

		List<DestinatarioAgendaDTO> favoritos = destinatariosFrecuentesDTO.getRespuesta().getDestinatariosFavoritos();
		List<DestinatarioAgendaDTO> noFavoritos = destinatariosFrecuentesDTO.getRespuesta().getDestinatariosNoFavoritos();
		DestinatarioAgendaDTO destinatarioAValidar = null;

		if (!favoritos.isEmpty()){
			for (DestinatarioAgendaDTO destinatario : favoritos) {
				if (StringUtils.equals(destinatario.getCbu(), cbu)){
					destinatarioAValidar = destinatario;
					break;
				}
			}
		}

		if (destinatarioAValidar == null && !noFavoritos.isEmpty()){
			for (DestinatarioAgendaDTO destinatario : noFavoritos) {
				if (StringUtils.equals(destinatario.getCbu(), cbu)){
					destinatarioAValidar = destinatario;
					break;
				}
			}
		}

		return destinatarioAValidar;
	}

	/**
	 * Este metodo analiza el estado de RSA.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> analizarRSAyEjecutarDesafio(TransferenciaView transferenciaView) {
		if (sesionParametros.getRsaRiesgoTransferenciaDTO() == null) {
			AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
			autentificacionDTO.setOperacion(Integer.parseInt(valorDesafioTransferencias));
			TipoDesafioEnum desafioAplicable = autentificacionManager
					.obtenerDesafioHabilitadoOperacion(autentificacionDTO);
			
			cargarDatosClaveToken(transferenciaView);

			Respuesta<RsaRiesgoTransferenciaDTO> respuestaRsaRiesgoTransferenciaDTO = rsaManager
					.analizarRiesgoTransferencia(transferenciaView, desafioAplicable);
			RsaRiesgoTransferenciaDTO rsaRiesgoTransferenciaDTO = respuestaRsaRiesgoTransferenciaDTO.getRespuesta();
			sesionParametros.setRsaRiesgoTransferenciaDTO(rsaRiesgoTransferenciaDTO);

			return validarReglaRSA(rsaRiesgoTransferenciaDTO,transferenciaView);


		} else {

			if (sesionParametros.getRsaRiesgoTransferenciaDTO().getActionCode().equals(ActionCode.CHALLENGE)) {
				LOGGER.info(EJECUTANDO_TRANSFERENCIA_CON_RIESGO_ALTO);
				return chequearRiesgoYejecutarDesafio(transferenciaView, ActionCode.CHALLENGE);
			} else {
				LOGGER.info(EJECUTANDO_TRANSFERENCIA_CON_RIESGO_ALTO);
				return respuestaFactory.crearRespuestaOk(TransferenciaView.class, transferenciaView);
			}
		}
	}

	private Respuesta<TransferenciaView> validarReglaRSA(RsaRiesgoTransferenciaDTO rsaRiesgoTransferenciaDTO,TransferenciaView transferenciaView){
		if(rsaRiesgoTransferenciaDTO.getRuleId() != null && rsaRiesgoTransferenciaDTO.getRuleId().startsWith("DERIVACIONAPP")) {
			LOGGER.info("Inicio de derivacion a app.");
			return respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.RULE_DERIVACIONAPP, CodigoMensajeConstantes.RSA_DERIVACION_APP_USUARIO);
		}


		if(rsaRiesgoTransferenciaDTO.getRuleId() != null && rsaRiesgoTransferenciaDTO.getRuleId().startsWith("BLOQUEARUSUARIO")) {
			LOGGER.info("Inicio de bloqueo de usuario.");
			Respuesta<RsaUpdateUserResponseData> responseBloqueoUsuario = rsaManager.updateUser(new RsaUpdateUserRequestData("LOCKOUT"));
			if (EstadoRespuesta.OK.equals(responseBloqueoUsuario.getEstadoRespuesta())) {
				return respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.RSA_BLOQUEAR_USUARIO,
						CodigoMensajeConstantes.RSA_LOGIN_USUARIO_BLOQUEADO);
			} else {
				LOGGER.info("Error al bloquear el usuario");
				return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.RSA_OFFLINE,
						CodigoMensajeConstantes.ERROR_GENERICO_RSA_OFFLINE);
			}
		}
		if (transferenciaView.isInmediata() && rsaRiesgoTransferenciaDTO.getRiesgoAlto()) {
			if(rsaRiesgoTransferenciaDTO.getRuleId().startsWith("TRANSFERENCIA24")) {
				// pedimos desafio, RSA devuelve ALLOW para no aumentar scoring del cliente si cancela
				sesionParametros.getRsaRiesgoTransferenciaDTO().setActionCode(ActionCode.CHALLENGE);
			}
			return crearRespuestaWarningRiesgoAlto(transferenciaView);
		}
		return analizarActionCodeRSA(transferenciaView, rsaRiesgoTransferenciaDTO);
	}

	private void cargarDatosClaveToken(TransferenciaView transferenciaView) {
    	Respuesta<List<BigDecimal>> antiguedades = clienteManager.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
		if(antiguedades != null && antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
			if(antiguedades.getRespuesta().get(0) != null) {
				transferenciaView.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
			}
			if(antiguedades.getRespuesta().get(1) != null) {
				transferenciaView.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
			}
		}
    }
	
	/**
	 * Crear respuesta warning riesgo alto.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> crearRespuestaWarningRiesgoAlto(TransferenciaView transferenciaView) {
		LOGGER.info(LA_TRANSFERENCIA_TIENE_RIESGO_ALTO);
		transferenciaView.setInmediata(Boolean.FALSE);
		transferenciaView
				.setFechaEjecucion(TransferenciaUtil.sumarDiasAunaFecha(transferenciaView.getFechaEjecucion(), 2));
		LOGGER.info(LA_NUEVA_FECHA_DE_EJECUCION_ES + transferenciaView.getFechaEjecucion());

		String codigoMensaje;
		if (transferenciaView.isTransferenciaManual()) {
			codigoMensaje = CodigoMensajeConstantes.CODIGO_ERROR_TRANSFERENCIA_RIESGO_ALTO_COELSA;
		} else {
			codigoMensaje = CodigoMensajeConstantes.CODIGO_ERROR_TRANSFERENCIA_RIESGO_ALTO;
		}

		return respuestaFactory.crearRespuestaWarning(transferenciaView, "", TipoError.TRANFERENCIA_CON_RIESGO_ALTO,
				codigoMensaje);
	}
	
	/**
	 * Crear respuesta warning riesgo alto y desafío.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the respuesta
	 */
	/*private Respuesta<TransferenciaView> crearRespuestaWarningRiesgoAltoDesafio(TransferenciaView transferenciaView) {
		
		LOGGER.info(LA_TRANSFERENCIA_TIENE_RIESGO_ALTO);
		transferenciaView.setInmediata(Boolean.FALSE);
		transferenciaView
				.setFechaEjecucion(TransferenciaUtil.sumarDiasAunaFecha(transferenciaView.getFechaEjecucion(), 2));
		LOGGER.info(LA_NUEVA_FECHA_DE_EJECUCION_ES + transferenciaView.getFechaEjecucion());

		String codigoMensaje;
		if (transferenciaView.isTransferenciaManual()) {
			codigoMensaje = CodigoMensajeConstantes.CODIGO_ERROR_TRANSFERENCIA_RIESGO_ALTO_COELSA;
		} else {
			codigoMensaje = CodigoMensajeConstantes.CODIGO_ERROR_TRANSFERENCIA_RIESGO_ALTO;
		}

		Respuesta<AutentificacionDTO> respuestaAutentificacion = autentificacionManager
				.ejecutarValidacionRSA(cargarAutentificacionDTO(transferenciaView, ActionCode.CHALLENGE));
		transferenciaView.setDesafio(respuestaAutentificacion.getRespuesta());
		
		Respuesta<TransferenciaView> respuesta =  respuestaFactory.crearRespuestaWarning(transferenciaView, "", TipoError.DESAFIO,
				codigoMensaje);
		respuesta.getItemsMensajeRespuesta().get(0).setDetalleTipoError(TipoError.TRANFERENCIA_CON_RIESGO_ALTO.getDescripcion());
		return respuesta;
	}*/

	/**
	 * Analizar action code RSA.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param rsaRiesgoTransferenciaDTO
	 *            the rsa riesgo transferencia DTO
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> analizarActionCodeRSA(TransferenciaView transferenciaView,
			RsaRiesgoTransferenciaDTO rsaRiesgoTransferenciaDTO) {
		Respuesta<TransferenciaView> respuestaAutentificacion = null;
		switch (rsaRiesgoTransferenciaDTO.getActionCode()) {
		case ALLOW:
			respuestaAutentificacion = respuestaFactory.crearRespuestaOk(TransferenciaView.class, transferenciaView);
			break;
		case DENY:
			respuestaAutentificacion = respuestaFactory.crearRespuestaError(TransferenciaView.class, StringUtils.EMPTY,
					TipoError.DENY_RSA, CodigoMensajeConstantes.DENY_RSA_TRANSFERENCIA_BLACK_LIST);
			respuestaAutentificacion.setRespuesta(transferenciaView);
			break;
		case CHALLENGE:
			respuestaAutentificacion = chequearRiesgoYejecutarDesafio(transferenciaView, ActionCode.CHALLENGE);
			break;
		default:
			throw new RobotException(OCURRIO_UN_ERROR_AL_VERIFICAR_CUAL_DESAFIO_TIENE_ACTIVO_EL_CLIENTE);
		}
		return respuestaAutentificacion;
	}

	/**
	 * Chequear riesgo yejecutar desafio.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param actionCodeRsa
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> chequearRiesgoYejecutarDesafio(TransferenciaView transferenciaView, ActionCode actionCodeRsa) {
		Respuesta<AutentificacionDTO> respuestaAutentificacion = autentificacionManager
				.ejecutarValidacionRSA(cargarAutentificacionDTO(transferenciaView, actionCodeRsa));
		Respuesta<TransferenciaView> respuesta = new Respuesta<TransferenciaView>();
		respuesta.setEstadoRespuesta(respuestaAutentificacion.getEstadoRespuesta());
		respuesta.setItemMensajeRespuesta(respuestaAutentificacion.getItemsMensajeRespuesta());
		transferenciaView.setDesafio(respuestaAutentificacion.getRespuesta());
		respuesta.setRespuesta(transferenciaView);
		return respuesta;
	}
	/**
	 * Armar respuesta O ky grabar estadisticas.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param isProgramada
	 *            the is programada
	 * @param respuestaTransferenciaBO
	 *            the respuesta transferencia BO
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> armarRespuestaOK(TransferenciaView transferenciaView, boolean isProgramada,
			Respuesta<TransferenciaDTO> respuestaTransferenciaBO) {
		LOGGER.info(MSJ_INFO_TRANSFERENCIA_CON_EXITO);
		Respuesta<TransferenciaView> respuestaFinal = respuestaFactory.crearRespuestaOk(TransferenciaView.class);
		TransferenciaDTO transferenciaDTO = cargarTransferenciaDTOConTransferenciaView(transferenciaView);
		transferenciaView = generarTransferenciaView(transferenciaView, respuestaTransferenciaBO.getRespuesta());
		respuestaFinal.setRespuesta(transferenciaView);
		String resp = null;
		try {
		InsertarTransferenciaDTO insertarTransferenciaDTO= new InsertarTransferenciaDTO(transferenciaDTO, sesionCliente.getCliente());
		if(!isProgramada) { //is INMEDIATA
			if(transferenciaDTO.esRioRio()) {
				if(CuentasBancaPrivadaUtil.isCuentaBP(transferenciaDTO.getNumeroCuentaDestino().getNroSucursal()) || 
						CuentasBancaPrivadaUtil.isCuentaBP(transferenciaDTO.getCuentaOrigen().getNroSucursal())) {
					resp = operacionBancaPrivadaTransferenciaDAO.insertarTransferenciaRIORIOOB(insertarTransferenciaDTO);
				}
			} else {
				if(CuentasBancaPrivadaUtil.isCuentaBP(transferenciaDTO.getCuentaOrigen().getNroSucursal())) {
					resp = operacionBancaPrivadaTransferenciaDAO.insertarTransferenciaEntreBancosOB(insertarTransferenciaDTO);
				}
			}
		}
		} catch (DAOException e) {
			LOGGER.info("ERROR DAOException en insertarTransferenciaRIORIOOB o insertarTransferenciaEntreBancosOB",e);
		}
		
		if(resp != null) {//si consulto insertarTransferencia.... es distinto a null
			resp = resp.substring(0,2);
			if(transferenciaDTO.esRioRio() && OK_INSERTAR_TRANSFERENCIA.equals(resp)) { 
				estadisticaManager.add(EstadisticasConstants.CODIGO_SE_INSERTO_TRANSFERENCIA_RIORIO_OB, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else if(transferenciaDTO.esRioRio() && ERROR_INSERTAR_TRANSFERENCIA.equals(resp)) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_SE_INSERTO_TRANSFERENCIA_RIORIO_OB, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			} else if(!transferenciaDTO.esRioRio() && OK_INSERTAR_TRANSFERENCIA.equals(resp)) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_SE_INSERTO_TRANSFERENCIA_ENTRE_BANCO_OB, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else if(!transferenciaDTO.esRioRio() && ERROR_INSERTAR_TRANSFERENCIA.equals(resp)) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_SE_INSERTO_TRANSFERENCIA_ENTRE_BANCO_OB, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		}
		
		if ((transferenciaView.isErrorConAlias() && null != transferenciaView.getAliasDestino())
				|| (transferenciaView.isWarningConAlias() && null != transferenciaView.getAliasDestino())) {
			String mensajeDeFeedback = ejecutarAltaDestinatario(transferenciaView);
			if (!mensajeDeFeedback.isEmpty()) {
				List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
				ItemMensajeRespuesta itemFeedbackAMdeAlias = new ItemMensajeRespuesta(mensajeDeFeedback);
				itemsMensajeRespuesta.add(itemFeedbackAMdeAlias);
				respuestaFinal.setItemMensajeRespuesta(itemsMensajeRespuesta);
			}
		}

		if (!isProgramada) {
			if (esTransferenciaRio(transferenciaView)) {
				TransInmediataRioBuilder altaReq = new TransInmediataRioBuilder(sesionCliente.getCliente())
						.setTransferenciaView(transferenciaView);
				altaScompBO.altaScompTransferencia(altaReq);
			} else {
				TransInmediataOBBuilder altaReq = new TransInmediataOBBuilder(sesionCliente.getCliente())
						.setTransferenciaView(transferenciaView);
				altaScompBO.altaScompTransferencia(altaReq);
			}
		}

		sesionParametros.setTipoOperacionComprobante(TipoOperacionComprobanteEnum.TRANSFERENCIA);
		sesionParametros.setTransferenciaView(transferenciaView);
		return respuestaFinal;
	}

	/**
	 * Armar respuesta ERRO ry grabar estadisticas.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param respuestaTransferenciaBO
	 *            the respuesta transferencia BO
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> armarRespuestaERROR(TransferenciaView transferenciaView,
			Respuesta<TransferenciaDTO> respuestaTransferenciaBO) {
		LOGGER.info(MSJ_INFO_TRANSFERENCIA_CON_ERROR);
		Respuesta<TransferenciaView> respuestaFinal = new Respuesta<TransferenciaView>();
		respuestaFinal.setEstadoRespuesta(respuestaTransferenciaBO.getEstadoRespuesta());
		respuestaFinal.setItemMensajeRespuesta(respuestaTransferenciaBO.getItemsMensajeRespuesta());
		transferenciaView.setCuentasView(cuentaManager.getCuentasSaldo().getRespuesta());
		respuestaFinal.setRespuesta(transferenciaView);
		return respuestaFinal;
	}

	/**
	 * Armar respuesta WARNIN gy grabar estadisticas.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param respuestaTransferenciaBO
	 *            the respuesta transferencia BO
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> armarRespuestaWARNING(TransferenciaView transferenciaView,
			Respuesta<TransferenciaDTO> respuestaTransferenciaBO) {
		LOGGER.info(MSJ_INFO_TRANSFERENCIA_CON_WARNING);
		Respuesta<TransferenciaView> respuestaFinal;
		respuestaFinal = new Respuesta<TransferenciaView>();
		respuestaFinal.setEstadoRespuesta(respuestaTransferenciaBO.getEstadoRespuesta());
		respuestaFinal.setItemMensajeRespuesta(respuestaTransferenciaBO.getItemsMensajeRespuesta());
		if (respuestaTransferenciaBO.getItemsMensajeRespuesta().get(0).getTipoError() != null
				&& respuestaTransferenciaBO.getItemsMensajeRespuesta().get(0).getTipoError()
						.equals(TipoError.ERROR_CONOCIDO_TRANSFERENCIA.getDescripcion())) {
			return sesionParametros.getContador().excedeReintentos(transferenciaView.getIdSesion(), respuestaFinal);
		} else {
			transferenciaView.setCuentasView(cuentaManager.getCuentasSaldo().getRespuesta());
			respuestaFinal.setRespuesta(transferenciaView);
			return respuestaFinal;
		}
	}

	/**
	 * Inicializar contador de intentos.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 */
	private void inicializarContadorDeIntentos(TransferenciaView transferenciaView) {
		LOGGER.info(INICIALIZANDO_EL_CONTADOR_DE_INTENTOS);
		String importeMoneda = DivisaEnum.fromMonedaString(transferenciaView.getMoneda()).getSimbolo()
				+ ISBANStringUtils.agregadorDecimales(transferenciaView.getImporte());
		String mensajeError = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_ERROR_GENERICO).getMensaje();
		mensajeError = MessageFormat.format(mensajeError, REALIZAR, transferenciaView.getTitular(), importeMoneda);

		if (sesionParametros.getContador() == null) {
			ContadorIntentos contador = new ContadorIntentos();
			contador.setIdView(transferenciaView.getIdSesion(), 2, mensajeError);
			sesionParametros.setContador(contador);
		} else {
			sesionParametros.getContador().setIdView(transferenciaView.getIdSesion(), 2, mensajeError);
		}

	}

	/**
	 * Es transferencia rio.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the boolean
	 */
	private Boolean esTransferenciaRio(TransferenciaView transferenciaView) {
		if (StringUtils.isNotBlank(transferenciaView.getNroCuentaDestino())
				&& StringUtils.isNotBlank(transferenciaView.getTipoCuentaDestino())) {
			return true;
		}
		return false;
	}

	/**
	 * Cargar vista con datos necesarios para rsa.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param isProgramada
	 *            the is programada
	 */
	private void cargarVistaConDatosNecesariosParaRsa(TransferenciaView transferenciaView, boolean isProgramada) {
		transferenciaView.setInmediata(!isProgramada);
		String nroProductoCuenta = ISBANStringUtils
				.formateadorConCerosIzq(ISBANStringUtils.extraerCuenta(transferenciaView.getNroCuenta()), 16);
		Cuenta cuentaOrigen = sesionCliente.getCliente().getCuentaPorNumero(nroProductoCuenta);
		Cuenta cuentaOrigenBancaPrivada = sesionCliente.getCliente().getCuentaPrivadaSiContieneNumero(nroProductoCuenta);
		if (cuentaOrigen == null && cuentaOrigenBancaPrivada == null) {
			LOGGER.info(MSJ_ERROR_CUENTA_INVALIDA);
			throw new RobotException(MSJ_ERROR_CUENTA_INVALIDA);
		} else {
			if(cuentaOrigen == null) {
				cuentaOrigen = cuentaOrigenBancaPrivada;
			}
		
			transferenciaView.setTitularOrigen(
					sesionCliente.getCliente().getApellido1() + " " + sesionCliente.getCliente().getNombre());
			String tipoCuentaCodigo = TransferenciaUtil.validarTipoCuentaUnificadaSegunMoneda(
					cuentaOrigen.getTipoCuenta(), DivisaEnum.fromMonedaString(transferenciaView.getMoneda()));
			TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(Integer.valueOf(tipoCuentaCodigo));
			transferenciaView.setTipoCuentaEnum(tipoCuenta);
			transferenciaView.setMonedaAltair(DivisaEnum.fromMonedaString(transferenciaView.getMoneda()).getCodigo());
			if (DivisaEnum.DOLAR.getMoneda().equalsIgnoreCase(transferenciaView.getMoneda())) {
				transferenciaView.setSaldoCuentaOrigen(cuentaOrigen.getSaldoCUDls());
			} else {
				transferenciaView.setSaldoCuentaOrigen(cuentaOrigen.getSaldoCUPesos());
			}
		}
		setTransactionMeter(transferenciaView);
		transferenciaView.setCuitCliente(sesionCliente.getCliente().getNumeroCUILCUIT());
		transferenciaView.setStopOperacionErrorRSA(true);
		transferenciaView.setScoringDestinatario(getDestinationScoring(transferenciaView.getCuitCuil()));
		transferenciaView.setNupCliente(sesionCliente.getCliente().getNup());
		setBiocatchData(transferenciaView);
	}

	private float getDestinationScoring(String cuit) {

		try {

			return scoringApi.getScoring(cuit);

		} catch (ScoringApiException e) {

			LOGGER.error("Error al obtener scoring del cuit " + cuit, e);

			return DEFAULT_DESTINATION_SCORING;
		}
	}

	private void setBiocatchData(TransferenciaView transferenciaView) {

		LOGGER.info("Iniciando getScore Biocatch para transferencia");
		BiocatchTransferInfoDTO biocatchTransferInfoDTO = new BiocatchTransferInfoDTO();

		biocatchTransferInfoDTO.setBiocatchTransferInfoDTO(transferenciaView,accountsApi);

		BiocatchResponseDataDTO biocatchResponse = biocatchManager.getScoreTransferencia(sesionCliente.getCliente().getNup(),  sesionCliente.getIpCliente(),
		ActivityName.TRANSFERENCIA, ActivityType.WIRE_PAYMENT,biocatchTransferInfoDTO);

		transferenciaView.setBiocatchResponseData(biocatchResponse);
	}

	/**
	 * Eliminar cuentas cerradas. Y filtra de la lista de destinos la cuenta de
	 * origen. Si es tx RioRio.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 * @see TransferenciaManager#solicitarNuevaTransferencia(TransferenciaView, String)
	 */
	private List<CuentasAdhesionDebitoView> eliminarCuentasCerradas(CuentasView cuentas) {
		List<CuentasAdhesionDebitoView> cuentasAdhesionDebitoViewList = new ArrayList<CuentasAdhesionDebitoView>();
		LOGGER.info("Excluyendo cuentas cerradas.");
		for (CuentasAdhesionDebitoView cuentaView : cuentas.getCuentas()) {
			if (!cuentaView.getIsCerrada()) {
				cuentasAdhesionDebitoViewList.add(cuentaView);
			}
		}
		return cuentasAdhesionDebitoViewList;
	}

	/**
	 * Mapear viewa dto.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the transferencia DTO
	 */
	public TransferenciaDTO cargarTransferenciaDTOConTransferenciaView(TransferenciaView transferenciaView) {
		TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
		transferenciaDTO.setFechaProgramada(ISBANStringUtils.formatearFecha(transferenciaView.getFechaEjecucion()));
		transferenciaDTO.setCuentaOrigen(obtenerCuentaPorNroCuenta(transferenciaView.getNroCuenta()));
		if (!transferenciaView.getIsRioRio()) {
			transferenciaDTO.setCbuCuenta(transferenciaView.getCbu());
		} else {
			transferenciaDTO.setTipoCuentaDestino(
					TipoCuenta.fromDescripcionConMoneda(transferenciaView.getTipoCuentaDestinoDescripcion()));
			transferenciaDTO.setNumeroCuentaDestino(cargarCuentaDestinatario(transferenciaView.getNroCuentaDestino()));
			if (transferenciaDTO.getTipoCuentaDestino().getCodigo() == 2) {
                transferenciaDTO.setTipoCuentaDestino(
                        TransferenciaUtil.matchearTipoCuentaDestino(transferenciaView.getMoneda()));
            }
		}
		transferenciaDTO.setImporte(new BigDecimal(transferenciaView.getImporte()));
		transferenciaDTO.setSaveContact(transferenciaView.isSaveContact());

		if (ISBANStringUtils.validarCVU(transferenciaView.getCbu())) {
		    transferenciaDTO.setVaPorCoelsa(Boolean.TRUE);
		} else if (!"1".equals(errorBanelcoCoelsaHabilitado.trim())) {
			transferenciaDTO.setVaPorCoelsa(CUARENTA_Y_OCHO_HORAS.equalsIgnoreCase(transferenciaView.getFechaAcreditacion())
					? Boolean.TRUE : Boolean.FALSE);
		} else {
			transferenciaDTO.setVaPorCoelsa(transferenciaView.isDestinatarioNoVerificado() ? Boolean.TRUE : Boolean.FALSE);
		}

		transferenciaDTO.setNombreReceptor(transferenciaView.getTitular());
		transferenciaDTO.setTitular(transferenciaView.getTitular());
		transferenciaDTO.setConcepto(ConceptoTransferenciaEnum.getConceptoFromOrdinal(transferenciaView.getConcepto().getId()));
		transferenciaDTO.setDescripcionConcepto(transferenciaView.getDescripcion());
		transferenciaDTO.setInformacionAdicional(transferenciaView.getDescripcion());
		transferenciaDTO.setEmail(transferenciaView.getEmail());
		transferenciaDTO.setEmailMensaje(transferenciaView.getMensajeEmail());
		transferenciaDTO.setComentario(
				StringUtils.isNotEmpty(transferenciaView.getMensajeEmail()) ? transferenciaView.getMensajeEmail() : "");
		transferenciaDTO.setHaciaCuentaPropia(transferenciaView.isCuentaPropia());
		transferenciaDTO.setHaciaOtroBanco(!transferenciaView.getIsRioRio());
		transferenciaDTO.setMoneda(DivisaEnum.fromMonedaString(transferenciaView.getMoneda().toLowerCase()));
		transferenciaDTO.setBancoDestino(transferenciaView.getBanco());
		transferenciaDTO.setCuit(transferenciaView.getCuit());
		transferenciaDTO.setEsCuil(transferenciaView.isEsCuil());
		transferenciaDTO.setDescripcionAdicional(transferenciaView.getDescripcion());
		transferenciaDTO.setAlias(transferenciaView.getAliasDestino());

		return transferenciaDTO;
	}

	/**
	 * Cargar cuenta destinatario.
	 *
	 * @param nroCuentaDestino
	 *            the nro cuenta destino
	 * @return the identificacion cuenta
	 */
	private IdentificacionCuenta cargarCuentaDestinatario(String nroCuentaDestino) {
		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
		identificacionCuenta.setNroSucursal(ISBANStringUtils.extraerSucursal(nroCuentaDestino));
		identificacionCuenta.setNroCuentaProducto(ISBANStringUtils.extraerCuenta(nroCuentaDestino));
		return identificacionCuenta;
	}

	/**
	 * Obtener cuenta por nro cuenta.
	 *
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the cuenta
	 */
	private Cuenta obtenerCuentaPorNroCuenta(String nroCuenta) {
		Cuenta cuentaOrigen = sesionCliente.getCliente()
				.getCuentaPorNumero(TransferenciaUtil.parsearACuentaConCeros(nroCuenta));
		
		if(cuentaOrigen == null) {
			cuentaOrigen = sesionCliente.getCliente()
					.getCuentaPrivadaSiContieneNumero(TransferenciaUtil.parsearACuentaConCeros(nroCuenta));
		}
		
		return cuentaOrigen;
	}

	/**
	 * Cargar vista solicitar. Se sobrecargo el metodo
	 * {@link #cargarVistaSolicitar(TransferenciaView, Cliente, CuentasView, TransferenciaDTO, boolean, boolean)}
	 * para que admita el parametro destino (cuando es cuenta rio rio, no
	 * propia).
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param cliente
	 *            the cliente
	 * @param destino
	 *            the destino
	 * @param cuentasViewFiltradas
	 *            the cuentas view filtradas
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param isCuentaPropia
	 *            the is cuenta propia
	 * @param isRioRio
	 *            the is rio rio
	 * @return the transferencia view
	 * @throws BusinessException
	 *             the business exception
	 */
	private TransferenciaView cargarVistaSolicitar(TransferenciaView transferenciaView, Cliente cliente,
			Cliente destino, CuentasView cuentasViewFiltradas, TransferenciaDTO transferenciaDTO,
			boolean isCuentaPropia, boolean isRioRio) throws BusinessException {
		transferenciaView.setCuentasView(cuentasViewFiltradas);
		if (transferenciaDTO.getCbuCuenta() != null) {
			transferenciaView.setCbu(transferenciaDTO.getCbuCuenta());
		}
		transferenciaView.setLegalConcepto(obtenerLegalConcepto());
		transferenciaView.setConceptoTransferencia(ConceptoTransferenciaEnum.getConceptoView());
		if (isCuentaPropia) {
			transferenciaView.setTitular(ISBANStringUtils.convertirStringToCamelcase(cliente.getNombre()));
		} else if (isRioRio && null != destino) {
			transferenciaView.setTitular(ISBANStringUtils.convertirStringToCamelcase(destino.getNombre()));
		} else {
			transferenciaView.setTitular(ISBANStringUtils.convertirStringToCamelcase(transferenciaDTO.getTitular()));
		}

		if (transferenciaDTO.getNumeroCuentaDestino() != null
				&& !STRING_VACIO.equals(transferenciaDTO.getNumeroCuentaDestino())) {
			transferenciaView.setNroCuentaDestino(transferenciaDTO.getNumeroCuentaDestino().toString());
		}
		transferenciaView.setCuentaPropia(isCuentaPropia);
		transferenciaView.setIsRioRio(isRioRio);
        if (null != destino) {
            transferenciaView.setEsCuil("L ".equals(destino.getTipoCUILCUIT()));
            transferenciaView.setCuit(destino.getNumeroCUILCUIT());
        } else if (null != transferenciaDTO.getCuit()) {
            transferenciaView.setCuit(transferenciaDTO.getCuit());
        }
		TipoCuenta tipoCuentaDestino = transferenciaDTO.getTipoCuentaDestino();
		if (tipoCuentaDestino != null) {
			transferenciaView.setTipoCuentaDestino(tipoCuentaDestino.getAbreviatura());
			if (TipoCuenta.CUENTA_UNICA_DOLARES.equals(tipoCuentaDestino)
                    || TipoCuenta.CUENTA_UNICA_PESOS.equals(tipoCuentaDestino)) {
                transferenciaView.setTipoCuentaDestinoDescripcion(tipoCuentaDestino.getDescripcion());
            } else {
                transferenciaView.setTipoCuentaDestinoDescripcion(tipoCuentaDestino.getDescripcionConMoneda());
            }
		}
		transferenciaView.setMonedasDisponibles(
				getMonedasValidasParaTransferirView(transferenciaDTO, transferenciaView.getMoneda()));
		transferenciaView.setErrorBanelco(transferenciaDTO.isErrorBanelco());
		transferenciaView.setCelularMyA(transferenciaDTO.isCelularMyA());
		if (transferenciaDTO.isHaciaOtroBanco()) {
			transferenciaView.setBanco(transferenciaDTO.getBancoDestino());
		} else {
			transferenciaView.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
		}
		transferenciaView.setAliasDestino(transferenciaDTO.getAlias());
		if (transferenciaView.getImporte() != null)
			transferenciaView
					.setImporte(ISBANStringUtils.formatearSaldo(new BigDecimal(transferenciaView.getImporte())));
		TransferenciaUtil.cargarHashDeLaVistaEnSesion(transferenciaView, sesionParametros);
		return transferenciaView;
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
	 * Cargar vista solicitar.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param cliente
	 *            the cliente
	 * @param cuentasViewFiltradas
	 *            the cuentas view filtradas
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param isCuentaPropia
	 *            the is cuenta propia
	 * @param isRioRio
	 *            the is rio rio
	 * @return the transferencia view
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public TransferenciaView cargarVistaSolicitar(TransferenciaView transferenciaView, Cliente cliente,
			CuentasView cuentasViewFiltradas, TransferenciaDTO transferenciaDTO, boolean isCuentaPropia,
			boolean isRioRio) throws BusinessException {
		transferenciaView.setCuentasView(cuentasViewFiltradas);
		return cargarVistaSolicitar(transferenciaView, cliente, null, cuentasViewFiltradas, transferenciaDTO,
				isCuentaPropia, isRioRio);
	}

	/**
	 * Retorna una respuesta con error generico para transferencia con el
	 * mensaje para el feedback.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the respuesta error
	 */
	private Respuesta<TransferenciaView> getRespuestaErrorParaUnaTransferencia(TransferenciaView transferenciaView) {
		Mensaje mensaje = this.mensajeManager
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_ERROR_GENERICO);
		String mensajeError = MessageFormat.format(mensaje.getMensaje(), transferenciaView.getTitular(),
				transferenciaView.getMoneda()
						+ new BigDecimal(transferenciaView.getImporte()).setScale(2, RoundingMode.FLOOR));
		return respuestaFactory.crearRespuestaError(TransferenciaView.class, mensajeError, null);
	}

	/**
	 * Gets the moneda valida para transferir view.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @param moneda
	 *            the moneda
	 * @return the moneda valida para transferir view
	 * @throws BusinessException
	 *             the business exception
	 * @last_updated: Manuel.Vargas - 13/06/2017 - correcciones, se mueve a
	 *                TransferenciaUtil. @see TransferenciaUtil, @see
	 *                armarListaDeMonedas
	 * @last_updated: ignacio.valek - 14/09/2016 - se cambia la respuesta de
	 *                String a un array de String por pedido de front
	 */
	private List<String> getMonedasValidasParaTransferirView(TransferenciaDTO transferencia, String moneda)
			throws BusinessException {
		List<Cuenta> cuentas = TransferenciaUtil.obtenerCuentasValidasParaTransferir(sesionCliente.getCliente());
		int nroCuentasEnPesos = 0;
		int nroCuentasEnDolares = 0;
		nroCuentasEnPesos = TransferenciaUtil.obtenerCantidadDeCuentasPesos(cuentas);
		nroCuentasEnDolares = TransferenciaUtil.obtenerCantidadDeCuentasDolares(cuentas);
		boolean tienePesos = nroCuentasEnPesos > 0;
		boolean tieneDolares = nroCuentasEnDolares > 0;
		return transferencia.armarListaDeMonedas(tienePesos, tieneDolares, moneda);
	}

	/**
	 * Generar transferencia view.
	 *
	 * @param view
	 *            the view
	 * @param transferencia
	 *            the transferencia
	 * @return the transferencia view
	 */
	private TransferenciaView generarTransferenciaView(TransferenciaView view, TransferenciaDTO transferencia) {

		view.setTitular(ISBANStringUtils.convertirStringToCamelcase(transferencia.getTitular()));
		view.setImporte(ISBANStringUtils.formatearSaldo(transferencia.getImporte().setScale(2, RoundingMode.FLOOR)));

		Cuenta cuentaOrigen = transferencia.getCuentaOrigen();
		view.setNroCuenta(TransferenciaUtil.formatearNumeroCuenta(cuentaOrigen));
		if (TipoCuenta.CUENTA_UNICA_DOLARES.equals(cuentaOrigen.getTipoCuentaEnum())
                || TipoCuenta.CUENTA_UNICA_PESOS.equals(cuentaOrigen.getTipoCuentaEnum())) {
		    view.setTipoCuentaDescripcion(cuentaOrigen.getTipoCuentaEnum().getDescripcion());
		} else {
		    view.setTipoCuentaDescripcion(cuentaOrigen.getTipoCuentaEnum().getDescripcionConMoneda());
		}
		view.setCuit(ISBANStringUtils.formatearCuil(transferencia.getCuit()));
		view.setCbu(transferencia.getCbuCuenta());
		String bancoDestino = transferencia.getBancoDestino().toUpperCase();

		if (bancoDestino != null && transferencia.isHaciaOtroBanco()) {
			view.setBanco(ISBANStringUtils.convertirStringToCamelcase(bancoDestino));
		} else {
			view.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
		}

		Date fechaProgramada = transferencia.getFechaProgramada();
		view.setFechaEjecucion(fechaProgramada == null ? HOY_STRING : fechaEjecucionFormatter.format(fechaProgramada));
		view.setNroOperacionProgramada(fechaProgramada == null ? null : transferencia.getIdTransaccion());
		if (!ISBANStringUtils.validarCVU(transferencia.getCbuCuenta()) 
				&& (view.isTransferenciaManual() || CUARENTA_Y_OCHO_HORAS.equals(view.getFechaAcreditacion()))) {
			view.setFechaAcreditacion(CUARENTA_Y_OCHO_HORAS);
		}

		if (transferencia.getFechaDeOperacion() != null) {
			view.setFechaOperacion(fechaOperacionFormatter.format(transferencia.getFechaDeOperacion()));
			// view.setTextoLegales(transferencia.getTextoLegales());
			view.setNumeroComprobante(transferencia.getNumeroComprobante());
		} else {
			view.setFechaOperacion(transferencia.getFechaCompensacion());
			view.setNumeroComprobante(transferencia.getIdRecibo().substring(8, transferencia.getIdRecibo().length()));
		}
		if (transferencia.getConcepto() != null) {
            view.setConcepto(new ConceptoView(transferencia.getConcepto().getDescripcion(), transferencia.getConcepto().getOrdinal(), transferencia.getConcepto().getCodigo(), transferencia.getConcepto().getDescripcionAbreviada(), transferencia.getConcepto().getHabilitaLegal()));
		}
		if (transferencia.getDescripcionAdicional() != null) {
			view.setDescripcion(transferencia.getDescripcionAdicional());
		}
		view.setMensaje(transferencia.getMensaje());
		String email = transferencia.getEmail();
		view.setEmail(email);
		view.setEnviaEmail(email == null || email.trim().isEmpty() ? NO_MINUSCULA : SI_MINUSCULA);
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
	 * Valida el formato del CBU.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the respuesta
	 */
	private void validarFormatoCBU(TransferenciaView transferenciaView) {
		if (!ISBANStringUtils.validarCBU(transferenciaView.getCbu())) {
			LOGGER.info(MSJ_CBU_INVALIDO, transferenciaView.getCbu());
			new RobotException(MSJ_CBU_INVALIDO);
		}

	}

	/**
	 * Armar respuesta DTO. Si el nroCuentaDestino es nulo interpreta que es a
	 * OB.
	 *
	 * @param transferenciaView
	 *            the tview
	 * @return the respuesta
	 */
	private TransferenciaDTO cargarTransferenciaDTO(TransferenciaView transferenciaView) {
		TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
		transferenciaDTO.setCbuCuenta(transferenciaView.getCbu());
		transferenciaDTO.setBancoDestino(transferenciaView.getBanco());
		transferenciaDTO.setMoneda(DivisaEnum.fromMonedaString(transferenciaView.getMoneda()));
		if (transferenciaView.getNroCuenta() != null) {
			transferenciaDTO.setCuentaOrigen(obtenerCuentaPorNroCuenta(transferenciaView.getNroCuenta()));
		}
		String nroCuentaDestino = transferenciaView.getNroCuentaDestino();
		if (null == nroCuentaDestino || STRING_VACIO.equals(nroCuentaDestino)) {
			transferenciaDTO.setTitular(transferenciaView.getTitular());
			transferenciaDTO.setNumeroCuentaDestino(new IdentificacionCuenta());
			transferenciaDTO.setNombreReceptor(STRING_VACIO);
			transferenciaDTO.setCuit(transferenciaView.getCuit());
			transferenciaDTO.setHaciaOtroBanco(true);
			LOGGER.info("El CBU {} es valido", transferenciaView.getCbu());
		} else {
			IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(
					transferenciaView.getNroCuentaDestino());
			String tipoCuentaDestino = transferenciaView.getTipoCuentaDestino();
			transferenciaDTO.setNumeroCuentaDestino(identificacionCuenta);
			transferenciaDTO.setTipoCuentaDestino(TipoCuenta.fromAbreviatura(tipoCuentaDestino));
			transferenciaDTO.setHaciaOtroBanco(false);
		}

		return transferenciaDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * TransferenciaManager#obtenerTiposDeCuenta()
	 */
	@Override
	public Respuesta<List<TipoDeCuentaView>> obtenerTiposDeCuenta() {
		Respuesta<List<TipoDeCuentaView>> respuesta = new Respuesta<List<TipoDeCuentaView>>();
		LOGGER.info(MSJ_INFO_OBTENIENDO_CUENTAS);
		try {
			List<TipoDeCuentaView> lista = new ArrayList<TipoDeCuentaView>();
			if (Boolean.TRUE.equals(Boolean.parseBoolean(cuentaUnicaHabilitada))) {
				LOGGER.info(MSJ_INFO_AGREGANDO_CUENTAS, "CUENTA_UNICA");
				lista.add(new TipoDeCuentaView(TipoCuenta.CUENTA_UNICA.getAbreviatura(),
						TipoCuenta.CUENTA_UNICA.getDescripcionConMoneda()));
			}
			if (Boolean.TRUE.equals(Boolean.parseBoolean(cajaAhorroPesosHabilitada))) {
				LOGGER.info(MSJ_INFO_AGREGANDO_CUENTAS, "CAJA AHORRO");
				lista.add(new TipoDeCuentaView(TipoCuenta.CAJA_AHORRO_PESOS.getAbreviatura(),
						TipoCuenta.CAJA_AHORRO_PESOS.getDescripcionConMoneda()));
			}
			if (Boolean.TRUE.equals(Boolean.parseBoolean(cajaAhorroDolaresHabilitada))) {
				LOGGER.info(MSJ_INFO_AGREGANDO_CUENTAS, "CAJA AHORRO EN DOLARES");
				lista.add(new TipoDeCuentaView(TipoCuenta.CAJA_AHORRO_DOLARES.getAbreviatura(),
						TipoCuenta.CAJA_AHORRO_DOLARES.getDescripcionConMoneda()));
			}
			if (Boolean.TRUE.equals(Boolean.parseBoolean(cuentaCorrientePesosHabilitada))) {
				LOGGER.info(MSJ_INFO_AGREGANDO_CUENTAS, "CUENTA CORRIENTE EN PESOS");
				lista.add(new TipoDeCuentaView(TipoCuenta.CUENTA_CORRIENTE_PESOS.getAbreviatura(),
						TipoCuenta.CUENTA_CORRIENTE_PESOS.getDescripcionConMoneda()));
			}
			if (Boolean.TRUE.equals(Boolean.parseBoolean(cuentaCorrienteDolaresHabilitada))) {
				LOGGER.info(MSJ_INFO_AGREGANDO_CUENTAS, "CUENTA CORRIENTE EN DOLARES");
				lista.add(new TipoDeCuentaView(TipoCuenta.CUENTA_CORRIENTE_DOLARES.getAbreviatura(),
						TipoCuenta.CUENTA_CORRIENTE_DOLARES.getDescripcionConMoneda()));
			}
			respuesta.setRespuesta(lista);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			return respuesta;
		} catch (Exception ex) {
			LOGGER.error(ERROR_GENERICO_MANAGER, ex);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			return respuesta;
		}
	}

	/**
	 * Agrega ActionCode en caso de recibirlo para evitar volver a invocar a rsa con dicho valor.
	 * 
	 * @param transferenciaView
	 * @param actionCodeRsa
	 * @return
	 */
	private AutentificacionDTO cargarAutentificacionDTO(TransferenciaView transferenciaView, ActionCode actionCodeRsa) {
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		if(actionCodeRsa != null) {
		    autentificacionDTO.setCnsActionCodePrevioRsaAnalizar(actionCodeRsa);
		}
		autentificacionDTO.setOperacion(Integer.parseInt(valorDesafioTransferencias));
		asignarEstadisticasDeAutenticacion(autentificacionDTO, transferenciaView.getIsRioRio());
		if (transferenciaView.getDesafio() != null) {
			autentificacionDTO = transferenciaView.getDesafio();
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

		autentificacionDTO.setRsaDTO(transferenciaView);
		return autentificacionDTO;
	}

	/**
	 * Asignar estadisticas de autenticacion.
	 *
	 * @param autentificacionDTO
	 *            the autentificacion DTO
	 * @param isRioRio
	 *            true, si la transfencia es hacia cuentas del mismo banco.
	 */
	private void asignarEstadisticasDeAutenticacion(AutentificacionDTO autentificacionDTO, boolean isRioRio) {
		autentificacionDTO
				.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOFT_TOKEN_SOLICITUD_NUEVA_TRANSFERENCIA);
		autentificacionDTO
				.setCodigoEstadisticaValidacionToken(EstadisticasConstants.SOFT_TOKEN_VALIDACION_NUEVA_TRANSFERENCIA);
		autentificacionDTO.setCodigoEstadisticaSolicitudCoordenadas(
				EstadisticasConstants.COORDENADAS_SOLICITUD_NUEVA_TRANSFERENCIA);
		autentificacionDTO.setCodigoEstadisticaValidacionCoordenadas(
				EstadisticasConstants.COORDENADAS_VALIDACION_NUEVA_TRANSFERENCIA);
	}

	/**
	 * R Sets the cuenta unica habilitada.
	 *
	 * @param cuentaUnicaHabilitada
	 *            the new cuenta unica habilitada
	 */
	public void setCuentaUnicaHabilitada(String cuentaUnicaHabilitada) {
		this.cuentaUnicaHabilitada = cuentaUnicaHabilitada;
	}

	/**
	 * Sets the caja ahorro pesos habilitada.
	 *
	 * @param cajaAhorroPesosHabilitada
	 *            the new caja ahorro pesos habilitada
	 */
	public void setCajaAhorroPesosHabilitada(String cajaAhorroPesosHabilitada) {
		this.cajaAhorroPesosHabilitada = cajaAhorroPesosHabilitada;
	}

	/**
	 * Sets the caja ahorro dolares habilitada.
	 *
	 * @param cajaAhorroDolaresHabilitada
	 *            the new caja ahorro dolares habilitada
	 */
	public void setCajaAhorroDolaresHabilitada(String cajaAhorroDolaresHabilitada) {
		this.cajaAhorroDolaresHabilitada = cajaAhorroDolaresHabilitada;
	}

	/**
	 * Sets the cuenta corriente pesos habilitada.
	 *
	 * @param cuentaCorrientePesosHabilitada
	 *            the new cuenta corriente pesos habilitada
	 */
	public void setCuentaCorrientePesosHabilitada(String cuentaCorrientePesosHabilitada) {
		this.cuentaCorrientePesosHabilitada = cuentaCorrientePesosHabilitada;
	}

	/**
	 * Sets the cuenta corriente dolares habilitada.
	 *
	 * @param cuentaCorrienteDolaresHabilitada
	 *            the new cuenta corriente dolares habilitada
	 */
	public void setCuentaCorrienteDolaresHabilitada(String cuentaCorrienteDolaresHabilitada) {
		this.cuentaCorrienteDolaresHabilitada = cuentaCorrienteDolaresHabilitada;
	}

	/**
	 * Cancelar desafio en curso.
	 */
	@Override
	public void cancelarDesafioEnCurso() {
		sesionParametros.resetearDesafioEnCurso();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * TransferenciaManager#obtenerInformacionDestinatario(ar.com.santanderrio.
	 * obp.servicios.transferencias.web.view.TransferenciaView)
	 */
	@Override
	public Respuesta<TransferenciaView> obtenerInformacionDestinatario(DestinatarioView destinatarioView) {

		LOGGER.info(INICIANDO_OBTENER_INFORMACION_DESTINATARIO);
		grabarEstadisticasConfiguracionDesdeAgenda(destinatarioView);
		
		 String mensajeAyudaFormateado = MessageFormat.format(TransferenciaUtil.TRANSF_MSG_AYUDA_IMPORTE_MAX,
	                trfInmediataBrImporteDolaresMax, trfInmediataBrtImportePesosMax, trfInmediataObImportePesosMax,
	                trfInmediataBrpImportePesosMax, trfInmediataObImporteDolareMax,
	                trfInmediataObpImporteDolarespesosMax, trfCVUImportePesosMax);
	        ItemMensajeRespuesta itemMensajeRespuestaAyuda = new ItemMensajeRespuesta(mensajeAyudaFormateado);
	        itemMensajeRespuestaAyuda.setTipoError(TAG_AYUDA);
	        List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
	        itemsMensajeRespuesta.add(itemMensajeRespuestaAyuda);
		
		
		if (null != destinatarioView.getTipoCuenta() && !destinatarioView.getTipoCuenta().equals("")) {
			boolean destinoValido = true;
			destinoValido = TransferenciaUtil.obtenerCuentasValidasDelTipoCuentaDestino(
					TipoCuenta.fromDescripcionConMoneda(destinatarioView.getTipoCuenta()), sesionCliente.getCliente());
			if (!destinoValido) {
				LOGGER.info(MSJ_INFO_DESTINATARIO_CON_DIFERENTE_MONEDA_INCOMPATIBLE);
				Respuesta<TransferenciaView> respuesta = new Respuesta<TransferenciaView>();
				Mensaje mensaje = mensajeManager
						.obtenerMensajePorCodigo(CodigoMensajeConstantes.CUENTA_SIN_CTAS_CON_MONEDA_INDICADA);
				respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(TransferenciaView.class,
						mensaje.getMensaje(), TipoError.NO_EXISTE_DESTINATARIO.getDescripcion());
				respuesta.setItemMensajeRespuesta(itemsMensajeRespuesta);
				return respuesta;
			}
		}

		if (null != destinatarioView.getId())
			sesionAgenda.setIdDestinatarioSeleccionado(Integer.valueOf(destinatarioView.getId()) - 1);

		this.cargarDatosDestinatarioEnSesion(destinatarioView);

		try {
			if (null != destinatarioView && destinatarioView.isFromAgendaDestinatario() == Boolean.TRUE) {
				boolean cuentaPropia = false;
				Respuesta<TransferenciaView> respuesta = new Respuesta<TransferenciaView>();
				TransferenciaView transferenciaView = new TransferenciaView();
				if (null == destinatarioView.getCbu()) {
					IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(
							destinatarioView.getNroCuenta());
					cuentaPropia = TransferenciaUtil.esCuentaPropia(sesionCliente.getCliente(), identificacionCuenta,
							destinatarioView.getTipoDestinatario(), false);
					if(!cuentaPropia) {
						cuentaPropia = TransferenciaUtil.esCuentaPropiaBP(sesionCliente.getCliente(), identificacionCuenta,
								destinatarioView.getTipoDestinatario(), false);
					}
					transferenciaView.setTipoCuentaDestino(
							TipoCuenta.fromDescripcionConMoneda(destinatarioView.getTipoCuenta()).getAbreviatura());
				}
				boolean tieneAdhesionMyA = verificandoSiTieneAdhesionMyA();
				if (!tieneAdhesionMyA) {
					tieneAdhesionMyA = true;
				}

				if (!cuentaPropia) {
					boolean contratoHabilitado = verificandoSiContieneAdhesionContratoTransferencias();					
					if (!contratoHabilitado) {
						respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(transferenciaView,
								TipoError.ERROR_SIN_CONTRATO_TRANSFERENCIA.getDescripcion(),
								CodigoMensajeConstantes.CODIGO_ERROR_CONTRATO_TRANSFERENCIA, "");
						respuesta.getRespuesta().setPrimerIngresoDeAgenda(true);
						respuesta.setItemMensajeRespuesta(itemsMensajeRespuesta);
						return respuesta;
					} else if (!tieneAdhesionMyA) {
						respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(transferenciaView,
								TipoError.ERROR_SIN_CONTRATO_TRANSFERENCIA.getDescripcion(),
								CodigoMensajeConstantes.CODIGO_ERROR_ADHESION_MYA, "");
						respuesta.getRespuesta().setPrimerIngresoDeAgenda(true);
						respuesta.setItemMensajeRespuesta(itemsMensajeRespuesta);
						return respuesta;
					}
				}
				LOGGER.info(CONSULTA_EN_CURSO);
				transferenciaView.setNroCuentaDestino(destinatarioView.getNroCuenta());

				LOGGER.info(MSJ_INFO_OBTENIENDO_CUENTAS_CLIENTE);
				Respuesta<CuentasView> respuestaCuentasView = new Respuesta<CuentasView>();
				if(!sesionCliente.getCliente().getCuentasMonetarias().isEmpty()) {//Si no tiene cuentas retail no llama a los servicios de saldos.
					respuestaCuentasView = cuentaManager.getCuentasSaldo();
				}
			
				Respuesta<List<ResumenDetalleCuenta>> respResumenesDC = obtenerCuentasySaldosBP();
				Respuesta<CuentasView> listaCuentaBP = CuentasBancaPrivadaUtil.convertirCuentasView(respResumenesDC);
				if( !EstadoRespuesta.OK.equals(respResumenesDC.getEstadoRespuesta())) {//error al consultar los saldos de banca privada
					return respuestaFactory.transformar(null, respResumenesDC);
				}
				if(respuestaCuentasView.getRespuesta() == null) {
					respuestaCuentasView = listaCuentaBP;
				}else {
					respuestaCuentasView.getRespuesta().getCuentas().addAll(listaCuentaBP.getRespuesta().getCuentas());
				}
				if (EstadoRespuesta.ERROR.equals(respuestaCuentasView.getEstadoRespuesta()) || 
						EstadoRespuesta.ERROR.equals(listaCuentaBP.getEstadoRespuesta())) {//error al consultar los saldos de retail y saldo de banca privada 
					throw new BusinessException(ERROR_AL_OBTENER_EL_SALDO_DE_LAS_CUENTAS);
				}
				List<CuentasAdhesionDebitoView> listaCuentasAdhesionDebitoViewList = new ArrayList<CuentasAdhesionDebitoView>();
				if (null != destinatarioView.getCbu() && !BancoEnum.SANTANDER_RIO.getDescripcion().equalsIgnoreCase(destinatarioView.getBanco())) {
					listaCuentasAdhesionDebitoViewList = eliminarCuentasCerradas(respuestaCuentasView.getRespuesta());
				} else {
					listaCuentasAdhesionDebitoViewList = TransferenciaUtil.eliminarCuentasCerradasYFiltroOrigen(
							respuestaCuentasView.getRespuesta(), transferenciaView);
				}
				CuentasView cuentasViewFiltradas = new CuentasView();
				cuentasViewFiltradas.setCuentas(listaCuentasAdhesionDebitoViewList);//agrego cuentas retail con sus saldos y las cuenta BP
				sesionParametros.setFromAgendaDestinatario(true);
				transferenciaView = cargarTransferenciaView(destinatarioView, cuentasViewFiltradas);
				transferenciaView.setPrimerIngresoDeAgenda(true);

				this.setFlagShowMsgMEP(transferenciaView);

				this.fundsMessageValidations(transferenciaView);

				respuesta = respuestaFactory.crearRespuestaOk(TransferenciaView.class, transferenciaView);
				respuesta.setItemMensajeRespuesta(itemsMensajeRespuesta);
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				respuesta.setRespuesta(transferenciaView);
				super.obtenerMensajeSiHorarioDeTransferenciaNoEsValido(respuesta);
				
				transferenciaView.setLimiteCuentasPropiasDolares(new BigDecimal(limiteCuentasPropiasDolares));
				transferenciaView.setLimiteCuentasPropiasPesos(new BigDecimal(limiteCuentasPropiasPesos));
				transferenciaView.setLimiteTercerosDolares(new BigDecimal(limiteTercerosDolares));
				transferenciaView.setLimiteTercerosPesos(new BigDecimal(limiteTercerosPesos));
				transferenciaView.setLimiteOtrosBancosDolares(new BigDecimal(limiteOtrosBancosDolares));
				transferenciaView.setLimiteOtrosBancosPesos(new BigDecimal(limiteOtrosBancosPesos));
				
				transferenciaView.generarNuevoIdSesion();
				transferenciaView.setMensajesAyuda(obtenerAyudasConTemplate());
				transferenciaView.setMensajeLimiteDiario(mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_ERROR_LIMITE_DIARIO).getMensaje());
				this.mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_GENERICO);
				return respuesta;
			} else {
				throw new BusinessException(ERROR_EN_PARAMETRO);
			}
		} catch (BusinessException bex) {
			Respuesta<TransferenciaView> respuesta = new Respuesta<TransferenciaView>();
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(MENSAJE_ERROR);
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuesta.add(itemMensajeRespuesta);
			respuesta.setItemMensajeRespuesta(itemsMensajeRespuesta);
			return respuesta;
		}
		
	}

	private void setFlagShowMsgMEP(TransferenciaView transferenciaView){

		try {
			List<String> alycsCuits = alycsDAO.getCuitsAlycs();

			if (TransferUtils.isRecipientCuitAlyc(transferenciaView.getCuit(), alycsCuits)){

				transferenciaView.setShowMsgMEP(true);
			}

		} catch (DAOException e) {

			LOGGER.error("Error al obtener el listado de cuits de Alycs", e);
		}

	}

	private void fundsMessageValidations(TransferenciaView transferenciaView) {

		Cliente cliente = sesionCliente.getCliente();

		if (fundsMessageHelper.showFundMessage(transferenciaView, cliente)) {

			String performanceFund = fundsMessageHelper.getPerformanceFund();

			if (!performanceFund.isEmpty()) {

				transferenciaView.setShowMsgFund(true);

				transferenciaView.setPerformanceFund(performanceFund);

				boolean hasTitleAccountBP = fundsMessageHelper.hasTitleAccountBP(cliente);
				boolean hasTitleAccountRetail = fundsMessageHelper.hasTitleAccountRetail(cliente);

				transferenciaView.setSegmentBP(hasTitleAccountBP);
				transferenciaView.setAccountTitle(hasTitleAccountRetail || hasTitleAccountBP);

			}
		}
	}





	private void cargarDatosDestinatarioEnSesion(DestinatarioView destinatarioView) {

		if (null != destinatarioView.getId()) {
			String id = destinatarioView.getId();
			DestinatarioEntity destinatario = sesionAgenda.getDestinatariosEntity().get(Integer.parseInt(id) - 1);

			LOGGER.info("Carga DatosTransferenciaDestino - ID: {}, Destinatario: {}", id, destinatario);

			sesionParametros.getDatosTransferenciaDestino().setId(id);
			sesionParametros.getDatosTransferenciaDestino().setFechaCreacion(destinatario.getTimestampAlta());
			sesionParametros.getDatosTransferenciaDestino().setAlias(destinatario.getAlias());
			sesionParametros.getDatosTransferenciaDestino().setCbu(destinatario.getCbuDestinatario());
			sesionParametros.getDatosTransferenciaDestino().setNumeroCuenta(TransferenciaUtil.formatearNumeroCuenta(destinatario));
		}
	}

	private Respuesta<List<ResumenDetalleCuenta>> obtenerCuentasySaldosBP(){
		final List<ResumenDetalleCuenta> respuestaDetalleCuenta = new ArrayList<ResumenDetalleCuenta>();
		List<CuentaIntermedioDTO> saldosCuentaBP = new ArrayList<CuentaIntermedioDTO>();

		try {
			saldosCuentaBP = cuentasBancaPrivadaBO.obtenerSaldoServicioIatx(sesionCliente.getCliente());
		} catch (SQLException e) {
			LOGGER.info("SQL Exception. obtenerSaldoServicioIatx   ", e);
		}
		
		for (CuentaIntermedioDTO cuentaDTO: saldosCuentaBP) {
			//si alguna cuentaDTO tiene un error en la consulta de saldo, grabo estadisticas ERROR y retorno warning. Flujo alternativo
			if(cuentaDTO.getSaldosServicioIatx().getErrorEnConsulta()) {
				LOGGER.info("ERROR al obtenerSaldoServicioIatx: cuentaDTO.getSaldosServicioIatx().getErrorEnConsulta() ");
				estadisticaManager.add(EstadisticasConstants.CODIGO_SOLICITUD_TRANSFERENCIA_SALDOS_BP, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		}
		//si ninguna cuentaDTO tiene un error en la consulta de saldo, grabo estadisticas OK. Flujo normal
		estadisticaManager.add(EstadisticasConstants.CODIGO_SOLICITUD_TRANSFERENCIA_SALDOS_BP, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		for (Cuenta cuenta : sesionCliente.getCliente().getCuentasPrivadas()) {
			if(CuentasBancaPrivadaUtil.isCuentaHabilitadaTransferenciaBP(cuenta)) {
				ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();

				for (CuentaIntermedioDTO saldoCuentaBP : saldosCuentaBP) {
					ConsultaSaldoCtasConAperturaOutEntity saldoCuenta;
					if (cuenta.obtenerNroCuentaFormateado().equals(saldoCuentaBP.getNumeroCuenta())) {
						saldoCuenta = saldoCuentaBP.getSaldosServicioIatx();
						resumenDetalleCuenta = CuentasBancaPrivadaUtil.initResumenDetalleCuenta(cuenta, saldoCuenta);
					}
				}
				respuestaDetalleCuenta.add(resumenDetalleCuenta);
				resumenDetalleCuenta.setAlias(null);
				resumenDetalleCuenta.setFavorita(Boolean.FALSE);
			}
		}
		return respuestaFactory.crearRespuestaOk(respuestaDetalleCuenta);
	}
    
	/**
	 * Grabar estadisticas configuracion desde agenda.
	 *
	 * @param destinatarioView
	 *            the destinatario view
	 */
	private void grabarEstadisticasConfiguracionDesdeAgenda(DestinatarioView destinatarioView) {
		if (null != destinatarioView.getAlias() && !destinatarioView.getAlias().isEmpty()) {
			estadisticaManager.add(EstadisticasConstants.CONFIGURACION_DESDE_AGENDA_ALIAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else if (null != destinatarioView.getNroCuenta() && !destinatarioView.getNroCuenta().isEmpty()) {
			estadisticaManager.add(EstadisticasConstants.SELECCION_DESTINATARIO_POR_DESTAGENDADO_POR_CUENTA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else if (null != destinatarioView.getCbu() && !destinatarioView.getCbu().isEmpty()) {
			estadisticaManager.add(EstadisticasConstants.SELECCION_DESTINATARIO_POR_DESTAGENDADO_POR_CBU,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
	}

	/**
	 * Cargar transferencia view.
	 *
	 * @param destinatarioView
	 *            the destinatario view
	 * @param cuentasViewFiltradas
	 *            the cuentas view filtradas
	 * @return the transferencia view
	 * @throws BusinessException
	 *             the business exception
	 */
	private TransferenciaView cargarTransferenciaView(DestinatarioView destinatarioView,
			CuentasView cuentasViewFiltradas) throws BusinessException {
		TransferenciaView transferenciaView = new TransferenciaView();
		transferenciaView.setCuentasView(cuentasViewFiltradas);
		transferenciaView.setNroCuentaDestino(destinatarioView.getNroCuenta());
		transferenciaView.setCbu(destinatarioView.getCbu());
		transferenciaView.setLegalConcepto(obtenerLegalConcepto());
		transferenciaView.setConceptoTransferencia(ConceptoTransferenciaEnum.getConceptoView());
		transferenciaView.setCuentaPropia(destinatarioView.getEsCuentaPropia());
		transferenciaView.setFromAgendaDestinatario(true);
		transferenciaView.setCuit(destinatarioView.getCuitOCuil());
		transferenciaView.setBanco(destinatarioView.getBanco());
		transferenciaView.setTitular(destinatarioView.getTitular());
		transferenciaView.setAliasDestino(destinatarioView.getAlias());
		transferenciaView.setTipoCuentaDestinoDescripcion(destinatarioView.getTipoCuenta());
		TipoCuenta tipoCuentaDestino = null;
		if (null != destinatarioView.getTipoCuenta()) {
			tipoCuentaDestino = TipoCuenta.fromDescripcionConMoneda(destinatarioView.getTipoCuenta());
			transferenciaView.setTipoCuentaDestino(
					tipoCuentaDestino == null ? STRING_VACIO : tipoCuentaDestino.getAbreviatura());
		}
		if (BancoEnum.SANTANDER_RIO.getDescripcion().equalsIgnoreCase(destinatarioView.getBanco())) {
			transferenciaView.setIsRioRio(true);
			transferenciaView.setCuitCuil(null);
		} else {
			transferenciaView.setIsRioRio(false);
		}
		transferenciaView.setFechaCreacionDestinatario(destinatarioView.getFechaCreacion());
		transferenciaView.setMonedasDisponibles(TransferenciaUtil.getMonedasValidasParaTransferirView(
				sesionCliente.getCliente(), tipoCuentaDestino, !transferenciaView.getIsRioRio()));
		transferenciaView.setReferenciaApodo(StringUtils.defaultString(destinatarioView.getReferenciaApodo()));

		return transferenciaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * TransferenciaManager#actualizarSaldo()
	 */
	@Override
	public Respuesta<CuentasView> actualizarSaldo() {
		Respuesta<List<ResumenDetalleCuenta>> respResumenesDC = obtenerCuentasySaldosBP();
		Respuesta<CuentasView> listaCuentaBP = CuentasBancaPrivadaUtil.convertirCuentasView(respResumenesDC);
		Respuesta<CuentasView> respuestaCuentas = new Respuesta<CuentasView>();
		if(!sesionCliente.getCliente().getCuentasMonetarias().isEmpty()) {
			respuestaCuentas = cuentaManager.getCuentasSaldo();
			if( respuestaCuentas.getRespuesta().getCuentas() != null ) {
				respuestaCuentas.getRespuesta().getCuentas().addAll(listaCuentaBP.getRespuesta().getCuentas());
			}
		} else {
			listaCuentaBP.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
			respuestaCuentas = listaCuentaBP;
		}
		super.obtenerMensajeSiHorarioDeTransferenciaNoEsValido(respuestaCuentas);
		return respuestaCuentas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.manager.
	 * ComprobantesManager#descargarComprobantePDF()
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobantePDF() {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		reporte = reporteBO.obtenerComprobantePDF(sesionParametros.getTransferenciaView());
		Boolean isProgramada = TransferenciaUtil
				.isTransferenciaProgramada(sesionParametros.getTransferenciaView().getFechaEjecucion());
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			grabarEstadisticaDescarga(isProgramada, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			grabarEstadisticaDescarga(isProgramada, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/**
	 * Grabar estadistica descarga. para cualquier flujo de transferencia desde
	 * el action sheet.
	 *
	 * @param isProgramada
	 *            the is programada
	 * @param codigoEstadisticas
	 *            the codigo estadisticas
	 */
	private void grabarEstadisticaDescarga(Boolean isProgramada, String codigoEstadisticas) {
		if (isProgramada) {
			estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_COMPROBANTES_TRANSFERENCIA_PROGRAMADA,
					codigoEstadisticas);
		} else {
			estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_COMPROBANTES_TRANSFERENCIA_INMEDIATA,
					codigoEstadisticas);
		}

	}

	/**
	 * Grabar estadistica.
	 *
	 * @param constanteEstadistica
	 *            the constante estadistica
	 * @param codigoEstadistica
	 *            the codigo estadistica
	 */
	private void grabarEstadistica(String constanteEstadistica, String codigoEstadistica) {
		estadisticaManager.add(constanteEstadistica, codigoEstadistica);
	}

	/**
	 * Consultar horarios.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<TransferenciaView> consultarHorarios() {
		Respuesta<TransferenciaView> respuesta = new Respuesta<TransferenciaView>();
		super.obtenerMensajeSiHorarioDeTransferenciaNoEsValido(respuesta);
		return respuesta;
	}

	private void registrarMetricaTransferenciaOk() {

		try {

			LOGGER.info("Inicio registro de metrica transferencia con status OK en MetricGateway");

			TransferMetricInfoDTO transferInfo = new TransferMetricInfoDTO(sesionCliente.getCliente().getNup(), TransferStatus.OK);

			metricRegisterBO.register(metricBuilder.createMetricTransfer(transferInfo));

			LOGGER.info("Registro exitoso de metrica transferencia con status OK en MetricGateway");

		} catch (Exception ex) {

			LOGGER.error("Error no esperado al registrar la metrica transferencia con status OK en MetricGateway", ex);

		}

	}

	private void registrarMetricaTransferenciaConErrores(TransferenciaView transferenciaView, Respuesta<TransferenciaDTO> respuesta, TransferStatus status) {

		try {

			LOGGER.info("Inicio registro de metrica de transferencia fallida en MetricGateway");

			TransferMetricInfoDTO transferInfo = new TransferMetricInfoDTO(sesionCliente.getCliente().getNup(), status);
			transferInfo.setRioRio(transferenciaView.getIsRioRio());
			transferInfo.setCuentaPropia(transferenciaView.isCuentaPropia());

			transferInfo.setDetalleError(respuesta.getRespuesta().getDetalleError());

			metricRegisterBO.register(metricBuilder.createMetricTransfer(transferInfo));

			LOGGER.info("Registro exitoso de metrica transferencia fallida en MetricGateway");

		} catch (Exception ex) {

			LOGGER.error("Error no esperado al registrar la metrica transferencia fallida en MetricGateway", ex);

		}

	}

	private void registrarMetricaTransferenciaConErrores(TransferenciaView transferenciaView , DetalleError detalleError, TransferStatus status) {

		try {

			LOGGER.info("Inicio registro de metrica transferencia fallida en MetricGateway");

			TransferMetricInfoDTO transferInfo = new TransferMetricInfoDTO(sesionCliente.getCliente().getNup(), status);
			transferInfo.setRioRio(transferenciaView.getIsRioRio());
			transferInfo.setCuentaPropia(transferenciaView.isCuentaPropia());
			transferInfo.setDetalleError(detalleError);

			metricRegisterBO.register(metricBuilder.createMetricTransfer(transferInfo));

			LOGGER.info("Registro exitoso de metrica transferencia fallida en MetricGateway");

		} catch (Exception ex) {

			LOGGER.error("Error no esperado al registrar la metrica transferencia fallida en MetricGateway", ex);

		}

	}

	private void registrarMetricaTransferenciaConErrorRsa(TransferenciaView transferenciaView, Respuesta<TransferenciaView> respuestaRsa) {

		try {

			LOGGER.info("Inicio registro de metrica transferencia fallida debido a RSA en MetricGateway");

			TipoError error = TipoError.valueOf(respuestaRsa.getItemsMensajeRespuesta().get(0).getTipoError());

			if (respuestaRsa.getEstadoRespuesta() == EstadoRespuesta.ERROR) {

				registrarMetricaTransferenciaConErrores(transferenciaView, getDetalleErrorFromTipoError(error), TransferStatus.FAIL);

			} else {

				registrarMetricaTransferenciaConErrores(transferenciaView, getDetalleErrorFromTipoError(error), TransferStatus.WARNING);

			}

			LOGGER.info("Registro exitoso de metrica transferencia fallida debido a RSA en MetricGateway");

		} catch (Exception ex) {

			LOGGER.error("Error no esperado al registrar la métrica transferencia fallida debido a RSA en MetricGateway", ex);

		}

	}

	private DetalleError getDetalleErrorFromTipoError(TipoError error) {

		switch (error) {

			case RSA_OFFLINE:
				return DetalleError.RSA_OFFLINE;
			case RSA_OFFLINE_VALIDACION_FALLIDA:
				return DetalleError.RSA_OFFLINE_VALIDACION_FALLIDA;
			case DENY_RSA:
				return DetalleError.RSA_DENY_TRANSFERENCIA_BLACK_LIST;
			case RSA_BLOQUEAR_USUARIO:
				return DetalleError.RSA_USUARIO_BLOQUEADO;
			case TRANFERENCIA_CON_RIESGO_ALTO:
				return DetalleError.RSA_TRANSFERENCIA_RIESGO_ALTO;
			default:
				return DetalleError.RSA_GENERIC_ERROR;

		}

	}

}
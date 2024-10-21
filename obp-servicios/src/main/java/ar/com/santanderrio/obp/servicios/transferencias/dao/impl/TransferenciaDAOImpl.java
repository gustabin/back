/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidad;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadResponse;
import ar.com.santanderrio.obp.generated.webservices.alias.CuentaDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.TerminalDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.Titularidad;
import ar.com.santanderrio.obp.generated.webservices.alias.UsuarioDTO;
import ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.debinws.bo.impl.TipoDocumentoDebinWSEnum;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.constants.SietePorVeintiCuatroConstant;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.SietePorVeintiCuatroServicioEnum;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao.SietePorVenticuatroV1DAO;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity;
import ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.IndicadorFuncion;
import ar.com.santanderrio.obp.servicios.transferencias.entities.PlazoAcreditacion;
import ar.com.santanderrio.obp.servicios.transferencias.entities.RequestCNSTITCBU;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TipoCuentaBanelco;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.exception.BeneficiarioNoVerificadException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaCerradaException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaOrigenSinBanelcoException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinDebitosNiAcreditacionesException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinOperarException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.DestinatarioNoVerificadoERRORenCNSException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.DestinatarioNoVerificadoException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ExcedeLimiteDolaresBeneficiarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ImporteLimiteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TitularidadNoVerificadaException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TransferenciaDolaresInhabilitadaException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaAlfaNoVerificadaNuncaOperoUsdException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaAlfaNoVerificadaException;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Conector con el servicio TRFCCI.
 *
 * @author B039636
 */
@Component
public class TransferenciaDAOImpl implements TransferenciaDAO {

	/** The Constant TRANSFERENCIA_PROGRAMADA_CON_CARGA_MANUAL_COELSA. */
	private static final String TRANSFERENCIA_PROGRAMADA_CON_CARGA_MANUAL_COELSA = "Transferencia programada con carga manual (COELSA)";

	/** The Constant ERROR_AL_CONSULTAR_EL_SERVICIO. */
	private static final String ERROR_AL_CONSULTAR_EL_SERVICIO = "Error al consultar el servicio.";

	/** The Constant TIMEOUT_EXCEPTION. */
	private static final String TIMEOUT_EXCEPTION = "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ";

	/** The Constant STRING_VACIO_11. */
	private static final String STRING_VACIO_11 = "           ";

	/** The Constant STRING_VACIO_LONGITUD_7. */
	private static final String STRING_VACIO_LONGITUD_7 = "       ";

	/** The Constant STRING_VACIO_LONGITUD_4. */
	private static final String STRING_VACIO_LONGITUD_4 = "    ";

	/** The Constant STRING_VACIO_LONGITUD_28. */
	private static final String STRING_VACIO_LONGITUD_28 = "                            ";

	/** The Constant STRING_VACIO_LOGINTUD_2. */
	private static final String STRING_VACIO_LONGITUD_2 = "  ";

	/** The Constant STRING_VACIO_LONGITUD_1. */
	private static final String STRING_VACIO_LONGITUD_1 = " ";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaDAOImpl.class);

	/** The Constant ERROR_AL_GENERAR_PDF. */
	private static final String ERROR_AL_GENERAR_PDF = "Error al generar PDF";

	/** The Constant HOY_STRING. */
	private static final String HOY_STRING = "Hoy";

	/** The Constant TITULAR_LONGITUD. */
	private static final int TITULAR_LONGITUD = 30;

	/** The Constant CODIGO_ERROR_SALDO_INSUFICIENTE_1A. */
	private static final int CODIGO_ERROR_SALDO_INSUFICIENTE_1A = 10000515;

	/** The Constant CODIGO_ERROR_SALDO_INSUFICIENTE_1B. */
	private static final int CODIGO_ERROR_SALDO_INSUFICIENTE_1B = 10002122;
	
	/** The Constant CODIGO_ERROR_SALDO_INSUFICIENTE_1B. */
	private static final int CODIGO_ERROR_SALDO_INSUFICIENTE_1C = 10000122;

	/** The Constant ID_SISTEMA_SALDO_INSUFICIENTE_1. */
	private static final String ID_SISTEMA_SALDO_INSUFICIENTE_1 = "ABG";

	/** The Constant CODIGO_ERROR_SALDO_INSUFICIENTE_2A. */
	private static final int CODIGO_ERROR_SALDO_INSUFICIENTE_2A = 10000058;
	
	/** The Constant CODIGO_ERROR_SALDO_INSUFICIENTE_2B. */
    private static final int CODIGO_ERROR_SALDO_INSUFICIENTE_2B = 10000059;

	/** The Constant ID_SISTEMA_SALDO_INSUFICIENTE_2. */
	private static final String ID_SISTEMA_SALDO_INSUFICIENTE_2 = "ZAE";

	/** The Constant CODIGO_ERROR_CUENTA_SIN_OPERAR. */
	private static final int CODIGO_ERROR_CUENTA_SIN_OPERAR = 10000117;

	/** The Constant ID_SISTEMA_AZB. */
	private static final String ID_SISTEMA_AZB = "AZB";

	/** The Constant CODIGO_ERROR_IMPORTE_LIMITE. */
	private static final int CODIGO_ERROR_IMPORTE_LIMITE = 10000004;

	/** The Constant ID_SISTEMA_IMPORTE_LIMITE. */
	private static final String ID_SISTEMA_IMPORTE_LIMITE = "AZK";

	/** The Constant CODIGO_ERROR_CUENTA_CERRADA. */
	private static final int CODIGO_ERROR_CUENTA_CERRADA = 10000114;

	/** The Constant CODIGO_ERROR_CUENTA_NO_DEBITOS_NI_ACREDITACIONES. */
	private static final int CODIGO_ERROR_CUENTA_NO_DEBITOS_NI_ACREDITACIONES = 10002065;

	/** The Constant CODIGO_ERROR_TIME_OUT. */
	private static final int CODIGO_ERROR_TIME_OUT = 10009976;

	/** The Constant CODIGO_ERROR_TIME_OUT. */
	private static final int CODIGO_ERROR_TRF_EXCEDE_LIMITE_USD_BENEFICIARIO = 10009076;
	
	private static final int TRANSFERENCIA_DOLARES_INHABILITADA = 10009077;
	
	private static final int COD_CUENTA_ALFA_NO_VERIFICADA_NUNCA_OPERO_USD = 10009079;

	private static final int COD_CUENTA_ALFA_NO_VERIFICADA = 10009080;

	/** The Constant LOGO_CABECERA. */
	private static final String LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant LOGO_MARCA_AGUA. */
	private static final String LOGO_MARCA_AGUA = "LOGO_MARCA_AGUA";

	/** The Constant DESTINATARIO. */
	private static final String DESTINATARIO = "DESTINATARIO";

	/** The Constant IMPORTE. */
	private static final String IMPORTE = "IMPORTE";

	/** The Constant CUENTA_DE_ORIGEN_INDEX. */
	private static final int CUENTA_DE_ORIGEN_INDEX = 0;

	/** The Constant CUENTA_DE_ORIGEN_LABEL. */
	private static final String CUENTA_DE_ORIGEN_LABEL = "Origen";

	/** The Constant DESTINO_INDEX. */
	private static final int DESTINO_INDEX = 1;

	/** The Constant DESTINO_LABEL. */
	private static final String DESTINO_LABEL = "Destino";

	/** The Constant CBU_LABEL. */
	private static final String CBU_LABEL = "CBU";

	/** The Constant CUIT_INDEX. */
	private static final int CUIT_INDEX = 2;

	/** The Constant CUIT_LABEL. */
	private static final String CUIT_LABEL = "CUIT/CUIL del destinatario";

	/** The Constant BANCO_INDEX. */
	private static final int BANCO_INDEX = 3;

	/** The Constant BANCO_LABEL. */
	private static final String BANCO_LABEL = "Banco";

	/** The Constant FECHA_DE_EJECUCION_INDEX. */
	private static final int FECHA_DE_EJECUCION_INDEX = 4;

	/** The Constant FECHA_DE_EJECUCION_LABEL. */
	private static final String FECHA_DE_EJECUCION_LABEL = "Fecha de ejecucion";

	/** The Constant PLAZO_DE_ACREDITACION_INDEX. */
	private static final int PLAZO_DE_ACREDITACION_INDEX = 5;

	/** The Constant PLAZO_DE_ACREDITACION_LABEL. */
	private static final String PLAZO_DE_ACREDITACION_LABEL = "Plazo de acreditacion";

	/** The Constant CONCEPTO_INDEX. */
	private static final int CONCEPTO_INDEX = 6;

	/** The Constant CONCEPTO_LABEL. */
	private static final String CONCEPTO_LABEL = "Concepto";

	/** The Constant DESCRIPCION_INDEX. */
	private static final int DESCRIPCION_INDEX = 7;

	/** The Constant DESCRIPCION_LABEL. */
	private static final String DESCRIPCION_LABEL = "Descripcion";

	/** The Constant EMAIL_DESTINATARIO_INDEX. */
	private static final int EMAIL_DESTINATARIO_INDEX = 8;

	/** The Constant EMAIL_DESTINATARIO_LABEL. */
	private static final String EMAIL_DESTINATARIO_LABEL = "E-Mail";

	/** The Constant NUMERO_COMPROBANTE_INDEX. */
	private static final int NUMERO_COMPROBANTE_INDEX = 9;

	/** The Constant NUMERO_COMPROBANTE_LABEL. */
	private static final String NUMERO_COMPROBANTE_LABEL = "Nro de comprobante";

	/** The Constant FECHA_COMPROBANTE. */
	private static final String FECHA_COMPROBANTE = "FECHA_COMPROBANTE";

	/** The Constant LOGO_CIERRE. */
	private static final String LOGO_CIERRE = "LOGO_CIERRE";

	/** The Constant MENSAJE_CONSERVAR_COMPROBANTE. */
	private static final String MENSAJE_CONSERVAR_COMPROBANTE = "MENSAJE_CONSERVAR_COMPROBANTE";

	/** The fecha ejecucion formatter. */
	private final SimpleDateFormat fechaEjecucionFormatter = new SimpleDateFormat("dd/MM/yyyy");

	/** Fin Jasper. */
	private static final String CODIGO_DE_ERROR_INICIO_STRING = "[codigo_error=";

	/** The Constant CODIGO_DE_ERROR_FIN_STRING. */
	private static final String CODIGO_DE_ERROR_FIN_STRING = "]";

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant POSICION_SISTEMA_ASOCIADO_AL_ERROR. */
	private static final int POSICION_SISTEMA_ASOCIADO_AL_ERROR = 1;

	/** The Constant POSICION_CANTIDAD_DESCRIPCIONES. */
	private static final int POSICION_CANTIDAD_DESCRIPCIONES = 2;

	/** The Constant POSICION_INICIO_DESCRIPCIONES. */
	private static final int POSICION_INICIO_DESCRIPCIONES = 3;

	/** The Constant SERVICIO_ALTADHABCF. */
	private static final String SERVICIO_ALTADHABCF = "ALTADHABCF";

	/** The Constant SERVICIO_ALTADHABCF_VERSION. */
	private static final String SERVICIO_ALTADHABCF_VERSION = "100";

	/** The servicio cnsmodtrfe. */
	private String servicioCnsmodtrfe = "CNSMODTRFE";

	/** The version cnsmodtrfe. */
	private String versionCnsmodtrfe = "110";

	/** The servicio cnstitcbu. */
	private String servicioCnstitcbu = "CNSTITCBU_";

	/** The version cnstitcbu. */
	private String versionCnstitcbu = "110";

	/** El servicio trfcci. */
	private static final String SERVICIOTRFCCI = "TRFCCI____";

	/** La version de trfcci. */
	private static final String VERSIONTRFCCI = "130";

	/** The Constant IDECLTSDO. */
	private static final String TRFCTA7X24 = "TRFCTA7X24";

	/** The Constant VERSIONIDECLTSDO. */
	private static final String VERSIONTRFCTA7X24 = "100";

	/** The Constant CODIGO_DE_ERROR_DESCONOCIDO_MSG. */
	private static final String CODIGO_DE_ERROR_DESCONOCIDO_MSG = "Codigo de error iatx desconocido";

	/** The Constant CON_MOVIMIETOS_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO_EXTENDIDO = 0;

	/** The Constant DEPOSITO_DE_EFECTIVO_CODIGO. */

	/** The Constant A_CONFIRMAR_CODIGO. */
	private static final int LONGITUD_REF_UNIV = 15;

	/** The Constant LONGITUD_INF_DISC. */
	private static final int LONGITUD_INF_DISC = 20;

	/** The Constant POSICION_MODALIDAD_TRANSFERENCIA_RESPUESTA. */
	private static final int POSICION_MODALIDAD_TRANSFERENCIA_RESPUESTA = 3;

	/** The Constant POSICION_INDICADOR_ADHESION_BEE_RESPUESTA. */
	private static final int POSICION_INDICADOR_ADHESION_BEE_RESPUESTA = 4;

	/** The Constant POSICION_RESPUESTA. */
	private static final int POSICION_RESPUESTA = 5;

	/** The Constant LONGITUD_ID_BENEF. */
	private static final int LONGITUD_ID_BENEF = 11;

	/** The Constant LONGITUD_ENTIDAD_CTA_CRED. */
	private static final int LONGITUD_ENTIDAD_CTA_CRED = 4;

	/** The Constant LONGITUD_SUCURSA_CTA_CRED. */
	private static final int LONGITUD_SUCURSA_CTA_CRED = 4;

	/** The Constant LONGITUD_CANT_DIAS. */
	private static final int LONGITUD_CANT_DIAS = 3;

	/** The Constant LONGITUD_TITULAR. */
	private static final int LONGITUD_TITULAR = 64;

	/** The Constant LONGITUD_CUIT. */
	private static final int LONGITUD_CUIT = 11;

	/** The Constant LONGITUD_LNG_CTA_BANE. */
	private static final int LONGITUD_LNG_CTA_BANE = 2;

	/** The Constant LONGITUD_CTA_DEST_BANE. */
	private static final int LONGITUD_CTA_DEST_BANE = 28;

	/** The Constant LONGITUD_EMAIL. */
	private static final int LONGITUD_EMAIL = 100;

	/** The Constant LONGITUD_COMENTARIO. */
	private static final int LONGITUD_COMENTARIO = 450;

	/** The Constant LONGITUD_CONCEPTO_ADICIONAL. */
	private static final int LONGITUD_CONCEPTO_ADICIONAL = 40;

	/** The Constant LONGITUD_DESCRIPCION_ADICIONAL. */
	private static final int LONGITUD_DESCRIPCION_ADICIONAL = 40;

	/** The Constant LONGITUD_MAX_CONCEPTO. */
	private static final int LONGITUD_MAX_CONCEPTO = 12;

	/** The Constant LONGITUD_DESCRIPCION_BANCO. */
	private static final int LONGITUD_DESCRIPCION_BANCO = 22;

	/** The Constant LONGITUD_SUCURSAL. */
	private static final int LONGITUD_SUCURSAL = 4;

	/** The Constant LONGITUD_SUCURSAL_DEBITO. */
	private static final int LONGITUD_SUCURSAL_DEBITO = 3;

	/** The Constant LONGITUD_NRO_CTA. */
	private static final int LONGITUD_NRO_CTA = 12;

	/** The Constant MAX_NRO_SUCURSAL. */
	private static final int MAX_NRO_SUCURSAL = 3;

	/** The Constant MAX_NRO_CUENTA. */
	private static final int MAX_NRO_CUENTA = 7;

	/** The Constant LONGITUD_NRO_CUENTA. */
	private static final int LONGITUD_NRO_CUENTA = 17;

	/** The Constant LONGITUD_INFORMACION_ADICIONAL. */
	private static final int LONGITUD_INFORMACION_ADICIONAL = 12;

	/** The Constant PDF_EXTENSION. */
	private static final String PDF_EXTENSION = ".pdf";

	/** The Constant GUION_MEDIO. */
	private static final String GUION_MEDIO = "-";

	/** The Constant COTIAZACION_TRANSFERENCIA_14_CEROS. */
	private static final String COTIAZACION_TRANSFERENCIA_14_CEROS = "00000000000000";

	/** The Constant NUMERO_COMPRBANTE_8_CEROS. */
	private static final String NUMERO_COMPRBANTE_8_CEROS = "00000000";

	/** The Constant N_CHAR. */
	private static final String N_CHAR = "N";

	/** The Constant VACIO_40. */
	private static final String VACIO_40 = "                                        ";

	/** The Constant LONGITUD_NRO_CUENTA_DEBITO. */
	private static final int LONGITUD_NRO_CUENTA_DEBITO = 7;

	/** The Constant LONGITUD_NRO_CUENTA_CREDITO. */
	private static final int LONGITUD_NRO_CUENTA_CREDITO = 7;

	/** The Constant DETALLE_LIST_DATASOURCE. */
	private static final String DETALLE_LIST_DATASOURCE = "REPORT_DATA_SOURCE";

	/** The Constant DESTINATARIO_NO_VERIFICADO_1. */
	private static final int DESTINATARIO_NO_VERIFICADO_1 = 1;

	/** The Constant DESTINATARIO_NO_VERIFICADO_2. */
	private static final int DESTINATARIO_NO_VERIFICADO_2 = 2;

	/** The Constant ERROR_SIN_REITENTO_505. */
	private static final int ERROR_SIN_REITENTO_505 = 10000505;

	/** The Constant ERROR_FLUJO_MANUAL_65. */
	private static final int ERROR_FLUJO_MANUAL_65 = 10000065;

	/** The Constant ERROR_FLUJO_MANUAL_56. */
	private static final int ERROR_FLUJO_MANUAL_56 = 10000056;

	/** The Constant ERROR_FLUJO_MANUAL_70. */
	private static final int ERROR_FLUJO_MANUAL_70 = 10000070;

	/** The Constant ERROR_FLUJO_MANUAL_74. */
	private static final int ERROR_FLUJO_MANUAL_74 = 10000074;

	/** The Constant ERROR_FLUJO_MANUAL_76. */
	private static final int ERROR_FLUJO_MANUAL_76 = 10000076;

	/** The Constant NUP_LENGTH. */
	private static final int NUP_LENGTH = 8;

	/** The Constant FORMATO_FECHA. */
	private static final String FORMATO_FECHA = "yyyyMMdd";

	/** The Constant CUENTA_UNICA. */
	private static final String CUENTA_UNICA = "02";

	/** The Constant ERROR_EN_BANELCO_CNSTITCBU_CAMPOS_VACIOS. */
	private static final String ERROR_EN_BANELCO_CNSTITCBU_CAMPOS_VACIOS = "ERROR en BANELCO-CNSTITCBU por campos VACIOS y cod error en 0000000.";

	/** The Constant STRING_VACIO. */
	private static final String STRING_VACIO = "";

	/** The Constant IND_OPERACION_A. */
	private static final String IND_OPERACION_A = "A";

	/** The Constant IND_MODALIDAD_PAGO_04. */
	private static final String IND_MODALIDAD_PAGO_04 = "04";

	/** The Constant IND_MODALIDAD_BANCA_FACIL_03. */
	private static final String IND_MODALIDAD_BANCA_FACIL_03 = "03";

	/** The Constant MARCA_GRAVADO N. */
	private static final String MARCA_GRAVADO_N = "N";

	/** The Constant MARCA_GRAVADA S. */
	private static final String MARCA_GRAVADO_S = "S";
	
	/** The Constant MONEDA_DTO_CODIGO_PESOS. */
	private static final String MONEDA_DTO_CODIGO_PESOS = "1";

	/** The Constant MONEDA_DTO_CODIGO_USD. */
	private static final String MONEDA_DTO_CODIGO_USD = "2";

	/** The Constant TIPO_CUENTA_DTO_CODIGO_CC. */
	private static final String TIPO_CUENTA_DTO_CODIGO_CC = "0";

	/** The Constant TIPO_CUENTA_DTO_CODIGO_CA. */
	private static final String TIPO_CUENTA_DTO_CODIGO_CA = "1";

	/** The Constant ERROR_EN_BANELCO_CONSULTA_TIT_CAMPOS_VACIOS. */
	private static final String ERROR_EN_BANELCO_CONSULTA_TIT_CAMPOS_VACIOS = "ERROR en BANELCO-CONSULTA TITULARIDAD por campos VACIOS y cod error en 0000000.";

    /** The siete por venticuatro V 1 DAO. */
	// @Qualifier("SietePorVenticuatroV1WSDAO")
	@Autowired
	private SietePorVenticuatroV1DAO sietePorVenticuatroV1DAO;

	/** Inicio Jasper. */
	@Value("classpath:/report/transferencias/ComprobanteTransferencia.jasper")
	private Resource fileJasper;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoCierre;

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo marca de agua. */
	@Value("classpath:/report/comprobantes/logo_marca_agua.png")
	private Resource logoMarcaDeAgua;

	/** The legal dao. */
	@Autowired
	private LegalDAO legalDao;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The sesionCliente. */
	@Autowired
	private SesionCliente sesionCliente;
	
	/** The alias cbu DAO. */
	@Autowired
	private AliasCbuDAO aliasCbuDAO;

    /** The error banelco coelsa habilitado. */
	@Value("${ERRORBANELCO.COELSA.HABILITADO}")
    private String errorBanelcoCoelsaHabilitado; 
	
	/** The canal banca privada. */
	@Value("${CANAL.BANCA.PRIVADA}")
	private String canalBP; 
	
	/** The sub canal banca privada. */
	@Value("${SUB.CANAL.BANCA.PRIVADA}")
	private String subCanalBP;

	/**
	 * Realizar transferencia. Retorna el mismo objeto recibido como parametro
	 * modificado para incluir el numero de operacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transferencia
	 *            la transferencia a realizar
	 * @param indicadorFuncion
	 *            the indicador funcion
	 * @return la transferencia realizada, con el número de operación informado
	 * @throws SaldoInsuficienteException
	 *             the saldo insuficiente exception
	 * @throws CuentaSinOperarException
	 *             the cuenta sin operar exception
	 * @throws ImporteLimiteException
	 *             the importe limite exception
	 * @throws DAOException
	 *             the DAO exception
	 * @throws CuentaOrigenSinBanelcoException
	 *             the cuenta origen sin banelco exception
	 */
	public TransferenciaDTO ejecutarTransferenciaInmediataOtrosBancos(Cliente cliente, TransferenciaDTO transferencia,
			IndicadorFuncion indicadorFuncion)
			throws SaldoInsuficienteException, CuentaSinOperarException, ImporteLimiteException, DAOException, CuentaOrigenSinBanelcoException {
		IatxRequest request = generarRequestTransferenciaOtrosBancos(cliente, transferencia, indicadorFuncion);
		invocarServicioTransferencia(cliente, transferencia, request);
		return transferencia;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO#
	 * ejecutarAgendarTransferenciaProgramada(ar.com.santanderrio.obp.servicios.
	 * transferencias.entities.TransferenciaDTO,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public TransferenciaDTO ejecutarAgendarTransferenciaProgramada(TransferenciaDTO transferenciaDTO, Cliente cliente)
			throws DAOException {

		XMLRequestEntity request = new XMLRequestEntity();
		if (!transferenciaDTO.isHaciaOtroBanco()) {
			request = armarRequestRioRioProgramada(transferenciaDTO,
					SietePorVeintiCuatroServicioEnum.TRANSF_BCO_RIO_110, cliente);
		} else {
			request = armarRequestOtrosBancosProgramada(transferenciaDTO,
					SietePorVeintiCuatroServicioEnum.TRANSF_TRFCCI_130, cliente);
		}

		XMLResponseEntity respuesta = sietePorVenticuatroV1DAO.ejecutar(request);

		String errorId = respuesta.getDATOSRESULTADO().getCodRet();

		if (!Long.valueOf(errorId).equals(0L)) {
			throw new DAOException(ERROR_AL_CONSULTAR_EL_SERVICIO);
		} else {
			return armarResponseDTO(respuesta, transferenciaDTO);
		}
	}

	/**
	 * Armar response DTO. IdTransaccion corresponde a id Operacion Programada.
	 * 
	 * @param respuesta
	 *            the respuesta
	 * @param transferenciaEjecutada
	 *            the transferencia ejecutada
	 * @return the transferencia DTO
	 */
	private TransferenciaDTO armarResponseDTO(XMLResponseEntity respuesta, TransferenciaDTO transferenciaEjecutada) {
		TransferenciaDTO transferencia = transferenciaEjecutada;
		String fechaFormateada = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		fechaFormateada = sdf.format(new Date());
		transferencia.setFechaCompensacion(fechaFormateada);
		transferencia.setIdRecibo(respuesta.getDATOSRESULTADO().getIdRecibo());
		transferencia.setIdTransaccion(respuesta.getDATOSRESULTADO().getIdTransaccion());
		return transferencia;
	}

	/**
	 * Armar request otros bancos programada.
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param sietePorVeintiCuatroServicioEnum
	 *            the siete por veinti cuatro servicio enum
	 * @param clienteSesion
	 *            the cliente sesion
	 * @return the XML request entity
	 */
	private XMLRequestEntity armarRequestOtrosBancosProgramada(TransferenciaDTO transferenciaDTO,
			SietePorVeintiCuatroServicioEnum sietePorVeintiCuatroServicioEnum, Cliente clienteSesion) {
		XMLRequestEntity xmlRequest = setearDatosComunesRequest(clienteSesion, transferenciaDTO);

		xmlRequest.getMETA().setNombre(SietePorVeintiCuatroConstant.NOMBRE_7X24_TX_OTROSBANCOS);
		xmlRequest.getMETA().setVersion(SietePorVeintiCuatroConstant.VERSION_7X24_130);
		xmlRequest.getMETA().setFechaEjecucion(
				ISBANStringUtils.formatearFecha(transferenciaDTO.getFechaProgramada(), FORMATO_FECHA));
		xmlRequest.getMETA().setTipoAgendamiento(SietePorVeintiCuatroConstant.TIPO_AGENDAMIENTO_7X24);

		xmlRequest.getMETA().setRecurrencias(new XMLRequestEntity.META.Recurrencias());
		xmlRequest.getMETA().getRecurrencias().setTipoRecurrencia(SietePorVeintiCuatroConstant.TIPO_RECURRENCIA_7x24);
		xmlRequest.getMETA().getRecurrencias().setFechaBaseRecurrencia(
				ISBANStringUtils.formatearFecha(transferenciaDTO.getFechaProgramada(), FORMATO_FECHA));
		xmlRequest.getMETA().getRecurrencias().setFrecRecurrencia(SietePorVeintiCuatroConstant.FREC_RECURRENCIA_7x24);
		xmlRequest.getMETA().getRecurrencias().setMaxRecurrencia(SietePorVeintiCuatroConstant.MAX_RECURRENCIA_7x24);

		XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

		XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
		parametros.setIndicadorFuncion(SietePorVeintiCuatroConstant.INDICADOR_FUNCION_OB);
		parametros.setTipoCtaDebito(StringUtils.leftPad(transferenciaDTO.getTipoCuentaOrigen(), 2, "0"));

		String nroSucursal = transferenciaDTO.getCuentaOrigen().getNroSucursal();
		String nroCuentaProduxcto = transferenciaDTO.getCuentaOrigen().getNroCuentaProducto();
		parametros.setSucCtaDebito(
				StringUtils.leftPad(nroSucursal.substring(nroSucursal.length() - 3, nroSucursal.length()), 3, "0"));
		parametros.setNroCtaDebito(StringUtils.leftPad(
				nroCuentaProduxcto.substring(nroCuentaProduxcto.length() - 7, nroCuentaProduxcto.length()), 7, "0"));
		parametros.setFirmanteCtaDebito(transferenciaDTO.getCuentaOrigen().getNroOrdenFirmante());
		parametros.setTipoTransferencia(SietePorVeintiCuatroConstant.TIPO_TRANSFERENCIA);
		parametros.setReferenciaUnivoca(
				transferenciaDTO.getConcepto().getCodigo().concat(transferenciaDTO.getDescConcepto()));

		DecimalFormat df = new DecimalFormat("0.00");
		parametros.setImporteTransferencia(df.format(transferenciaDTO.getImporte()));
		parametros.setMonedaTransferencia("peso".equals(transferenciaDTO.getMoneda().getMoneda()) ? "0" : "1");
		parametros.setInformacDiscrecional(SietePorVeintiCuatroConstant.INFORMACION_DISCRECIONAL);

		parametros.setIdentificBeneficiario(transferenciaDTO.getCuit().replace("-", ""));
		parametros.setEntidadCtaCredito("0".concat(transferenciaDTO.getCbuCuenta().substring(0, 3)));
		parametros.setSucCtaCredito(transferenciaDTO.getCbuCuenta().substring(3, 7));
		parametros.setDigitoCtaCredito(transferenciaDTO.getCbuCuenta().substring(7, 8));
		parametros.setNroCtaCredito(
				"000".concat(transferenciaDTO.getCbuCuenta().substring(8, transferenciaDTO.getCbuCuenta().length())));
		parametros.setNombreCtaCredito(transferenciaDTO.getTitular());
	    parametros.setCaracteristicaCtaCredito(esTransferenciaOtrosBancosCuentaPropia(transferenciaDTO, clienteSesion));
		parametros.setMarcaLimite(SietePorVeintiCuatroConstant.MARCAR_LIMITE);
		parametros.setMarcaGravado(obtenerMarcaGravado(transferenciaDTO, true));
		if ("1".equals(errorBanelcoCoelsaHabilitado.trim())) {
			parametros.setPlazoAcreditacion(
					transferenciaDTO.isVaPorCoelsa() ? PlazoAcreditacion.INMEDIATO_COELSA.getCodigoTrfcci()
							: PlazoAcreditacion.INMEDIATO.getCodigoTrfcci());
		} else {
			if (ISBANStringUtils.validarCVU(transferenciaDTO.getCbuCuenta())) {
				parametros.setPlazoAcreditacion(PlazoAcreditacion.INMEDIATO_COELSA.getCodigoTrfcci());
			} else {
				parametros.setPlazoAcreditacion(
						transferenciaDTO.isVaPorCoelsa() ? PlazoAcreditacion.PLAZO_24_HS.getCodigoTrfcci()
								: PlazoAcreditacion.INMEDIATO.getCodigoTrfcci());
			}
		}
		parametros.setPeriodica(SietePorVeintiCuatroConstant.PERIODICA);
		parametros.setCantidadDias(SietePorVeintiCuatroConstant.CANTIDAD_DIAS);
		parametros.setiPMaquina(sesionCliente.obtenerIpV4SinPuntos());
		parametros.setTitular(transferenciaDTO.getTitular());
        String cuit = transferenciaDTO.getCuit().replace(STRING_VACIO_LONGITUD_1, STRING_VACIO);
        parametros.setCuit1(StringUtils.replace(cuit, "-",""));
		parametros.setCbu(transferenciaDTO.getCbuCuenta());
		parametros.setCodConcepto(transferenciaDTO.getConcepto().getOrdinal());
		parametros.setDescConcepto(transferenciaDTO.getConcepto().getCodigo());

		if (transferenciaDTO.getTipoDeCuentaToBanelco() == null) {
			LOGGER.info(TRANSFERENCIA_PROGRAMADA_CON_CARGA_MANUAL_COELSA);
			parametros.setCuit2(STRING_VACIO_11);
			parametros.setCuit3(STRING_VACIO_11);
			parametros.setLongCtaDesBane(STRING_VACIO_LONGITUD_2);
			parametros.setCtaBane(STRING_VACIO_LONGITUD_28);
			parametros.setTpoCtaToBane(STRING_VACIO_LONGITUD_2);
			parametros.setTpoCtaFromBane(STRING_VACIO_LONGITUD_2);
			parametros.setFiid(STRING_VACIO_LONGITUD_4);
			parametros.setUser(STRING_VACIO_LONGITUD_7);
			parametros.setBancoReceptor(STRING_VACIO_LONGITUD_4);
			parametros.setBancoDestino(transferenciaDTO.getBancoDestino());
		} else {
			parametros.setCuit2(transferenciaDTO.getCuit2().replace(STRING_VACIO_LONGITUD_1, STRING_VACIO));
			parametros.setCuit3(transferenciaDTO.getCuit3().replace(STRING_VACIO_LONGITUD_1, STRING_VACIO));
			parametros.setLongCtaDesBane(String.valueOf(transferenciaDTO.getCuentaDestinoBanelco().length()));
			parametros.setCtaBane(transferenciaDTO.getCuentaDestinoBanelco());
			parametros.setTpoCtaToBane(transferenciaDTO.getTipoDeCuentaToBanelco().getCodigoTrfcci());
			parametros.setTpoCtaFromBane(transferenciaDTO.getTipoDeCuentaFromBanelco().getCodigoTrfcci());
			parametros.setFiid(StringUtils.leftPad(transferenciaDTO.getFiid(), 4, " "));
			parametros.setUser(StringUtils.leftPad(transferenciaDTO.getUser(), 7, " "));
			parametros.setBancoReceptor(transferenciaDTO.getBancoReceptor());
			parametros.setBancoDestino(transferenciaDTO.getBancoDestino());
		}
		xmlRequest.getDatosAdicionales().getDatosMensAvisos().setTitularCredito(transferenciaDTO.getTitular());
		xmlRequest.getDatosAdicionales().getDatosMensAvisos()
				.setTitularDebito(transferenciaDTO.getCuentaOrigen().getCliente().getNombre() + STRING_VACIO_LONGITUD_1
						+ transferenciaDTO.getCuentaOrigen().getCliente().getApellido1());
		datosentrada.setParametros(parametros);
		xmlRequest.setDATOSENTRADA(datosentrada);

		return xmlRequest;
	}

	/**
	 * @param transferenciaDTO
	 * @return
	 */
    private String esTransferenciaOtrosBancosCuentaPropia(TransferenciaDTO transferenciaDTO, Cliente clienteSesion) {
        List<String> cuitDestinoList = Arrays.asList(transferenciaDTO.getCuit(), transferenciaDTO.getCuit2(), transferenciaDTO.getCuit3());
        if (StringUtils.isNotBlank(clienteSesion.getNumeroCUILCUIT()) && cuitDestinoList.contains(StringUtils.replace(clienteSesion.getNumeroCUILCUIT(), "-", StringUtils.EMPTY))) {
            return "01"; 
        } else {
            return SietePorVeintiCuatroConstant.CARACTERISTICA_CUENTA_CREDITO;
        }
    }

    /**
	 * Obtener marca gravado. Estas pruebas corresponden a destinatarios nuevos
	 * o agendados de otros bancos, sea por alias o cbu y por acreditación
	 * inmediata o por flujo coelsa.
	 * 
	 * *se esta usando para el destino TipoDeCuentaFromBanelco porque la salida
	 * consulta esta invertida.
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param isProgramada
	 *            the is programada
	 * @return the string
	 */
	private String obtenerMarcaGravado(TransferenciaDTO transferenciaDTO, boolean isProgramada) {
		int tipoCuentaOrigen = new Integer(transferenciaDTO.getTipoCuentaOrigen()).intValue();
		/**
		 * TODO: *se esta usando para el destino TipoDeCuentaFromBanelco porque
		 * la salida consulta esta invertida.
		 */
		if (null == transferenciaDTO.getTipoDeCuentaFromBanelco()) {
			return MARCA_GRAVADO_N; // 4
		}
		String tipoCuentaDestino = transferenciaDTO.getTipoDeCuentaFromBanelco().getCodigoTrfcci();

		if (tipoCuentaOrigen == TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo()
				&& tipoCuentaDestino.equals(TipoCuentaBanelco.TIPO_01.getCodigoTrfcci())) {
			return MARCA_GRAVADO_N;// 1
		}
		if (tipoCuentaOrigen == TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo()
				&& !tipoCuentaDestino.equals(TipoCuentaBanelco.TIPO_01.getCodigoTrfcci())) {
			return MARCA_GRAVADO_S; // 2
		}
		if (tipoCuentaOrigen == TipoCuenta.CUENTA_CORRIENTE_DOLARES.getCodigo()
				&& !tipoCuentaDestino.equals(TipoCuentaBanelco.TIPO_01.getCodigoTrfcci())) {
			return MARCA_GRAVADO_S; // 3
		}
		if (tipoCuentaOrigen != TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo()
				&& tipoCuentaOrigen != TipoCuenta.CUENTA_CORRIENTE_DOLARES.getCodigo()) {
			return MARCA_GRAVADO_N; // 6
		}
		return MARCA_GRAVADO_N; // 5
	}

	/**
	 * Armar request rio rio programada.
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param sietePorVeintiCuatroServicioEnum
	 *            the siete por veinti cuatro servicio enum
	 * @param clienteSesion
	 *            the cliente sesion
	 * @return the XML request entity
	 */
	public XMLRequestEntity armarRequestRioRioProgramada(TransferenciaDTO transferenciaDTO,
			SietePorVeintiCuatroServicioEnum sietePorVeintiCuatroServicioEnum, Cliente clienteSesion) {

		XMLRequestEntity xmlRequest = setearDatosComunesRequest(clienteSesion, transferenciaDTO);

		xmlRequest.getMETA().setNombre(SietePorVeintiCuatroConstant.NOMBRE_7X24_TX_BC_RIO);
		xmlRequest.getMETA().setVersion(SietePorVeintiCuatroConstant.VERSION_7X24_110);
		xmlRequest.getMETA().setFechaEjecucion(
				ISBANStringUtils.formatearFecha(transferenciaDTO.getFechaProgramada(), FORMATO_FECHA));
		xmlRequest.getMETA().setTipoAgendamiento(SietePorVeintiCuatroConstant.TIPO_AGENDAMIENTO_7X24);

		xmlRequest.getMETA().setRecurrencias(new XMLRequestEntity.META.Recurrencias());
		xmlRequest.getMETA().getRecurrencias().setTipoRecurrencia(SietePorVeintiCuatroConstant.TIPO_RECURRENCIA_7x24);
		xmlRequest.getMETA().getRecurrencias().setFechaBaseRecurrencia(
				ISBANStringUtils.formatearFecha(transferenciaDTO.getFechaProgramada(), FORMATO_FECHA));
		xmlRequest.getMETA().getRecurrencias().setFrecRecurrencia(SietePorVeintiCuatroConstant.FREC_RECURRENCIA_7x24);
		xmlRequest.getMETA().getRecurrencias().setMaxRecurrencia(SietePorVeintiCuatroConstant.MAX_RECURRENCIA_7x24);

		XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

		XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
		if (transferenciaDTO.getCuentaOrigen().esCuentaUnica()) {
            TipoCuenta tipoCuenta = TransferenciaUtil.matchearTipoCuentaDestino(transferenciaDTO.getMoneda());
			parametros.setTipoCtaDebito(StringUtils.leftPad(tipoCuenta.getCodigo().toString(), 2, "0"));
		} else {
			parametros.setTipoCtaDebito(StringUtils.leftPad(transferenciaDTO.getTipoCuentaOrigen(), 2, "0"));
		}

		String nroSucursal = transferenciaDTO.getCuentaOrigen().getNroSucursal();
		String nroCuentaProduxcto = transferenciaDTO.getCuentaOrigen().getNroCuentaProducto();
		parametros.setSucCtaDebito(
				StringUtils.leftPad(nroSucursal.substring(nroSucursal.length() - 3, nroSucursal.length()), 3, "0"));
		parametros.setNroCtaDebito(StringUtils.leftPad(
				nroCuentaProduxcto.substring(nroCuentaProduxcto.length() - 7, nroCuentaProduxcto.length()), 7, "0"));
		DecimalFormat df = new DecimalFormat("0.00");
		parametros.setImporteDebito(df.format(transferenciaDTO.getImporte()));
		parametros.setTipoCtaCredito(
				StringUtils.leftPad(transferenciaDTO.getTipoCuentaDestino().getCodigo().toString(), 2, "0"));
		parametros.setSucCtaCredito(
				StringUtils.leftPad(transferenciaDTO.getNumeroCuentaDestino().getNroSucursal(), 3, "0"));
		parametros.setNroCtaCredito(
				StringUtils.leftPad(transferenciaDTO.getNumeroCuentaDestino().getNroCuentaProducto(), 7, "0"));
		parametros.setNombreCtaCredito(transferenciaDTO.getNombreReceptor());
		parametros.setCotizacionTransferencia(SietePorVeintiCuatroConstant.COTIZACION_TRANSFEERENCIA);
		parametros.setImporteCredito(SietePorVeintiCuatroConstant.IMPORTE_CREDITO_7X24);
		parametros.setNroComprobante(SietePorVeintiCuatroConstant.NRO_COMPROBANTE);
		parametros.setIndicadorAfectarDisponible(SietePorVeintiCuatroConstant.INDICADOR_AFECTAR_DISPONIBLE);
		parametros.setCodigoConcepto(transferenciaDTO.getConcepto().getCodigo());
		parametros.setDescripcionConcepto(transferenciaDTO.getDescConcepto());
		parametros.setCuentaPropia(transferenciaDTO.isHaciaCuentaPropia() == true ? "S" : "N");
		parametros.setIndicadorLimiteMax(SietePorVeintiCuatroConstant.INDICADOR_LIMITE_MAX);
		parametros.setIndTransfDiferida(
				PlazoAcreditacion.PLAZO_48_HS.equals(transferenciaDTO.getPlazoAcreditacion()) ? "S" : "N");
		datosentrada.setParametros(parametros);
		xmlRequest.getDatosAdicionales().getDatosMensAvisos().setTitularCredito(StringUtils.right(transferenciaDTO.getNombreReceptor(), 30));
		xmlRequest.getDatosAdicionales().getDatosMensAvisos()
				.setTitularDebito(transferenciaDTO.getCuentaOrigen().getCliente().getNombre() + STRING_VACIO_LONGITUD_1
						+ transferenciaDTO.getCuentaOrigen().getCliente().getApellido1());
		xmlRequest.setDATOSENTRADA(datosentrada);

		return xmlRequest;

	}
	
	/**
	 * Setear datos comunes request.
	 *
	 * @param clienteSesion
	 *            the cliente sesion
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @return the XML request entity
	 */
	private XMLRequestEntity setearDatosComunesRequest(Cliente clienteSesion, TransferenciaDTO transferenciaDTO) {
		String nup = StringUtils.leftPad(clienteSesion.getNup(), NUP_LENGTH, '0');
		XMLRequestEntity request = new XMLRequestEntity();

		XMLRequestEntity.CONFIG config = new XMLRequestEntity.CONFIG();
		config.setEcoDatosEntrada(SietePorVeintiCuatroConstant.ECO_DATOS_ENTRADA_7X24);
		config.setVersionXML(SietePorVeintiCuatroConstant.VERSION_XML_7X24);

		XMLRequestEntity.META meta = new XMLRequestEntity.META();
		meta.setIndAuten(SietePorVeintiCuatroConstant.IND_AUTENT_7X24);
		meta.setIdSesionCnt(SietePorVeintiCuatroConstant.ID_SESIONCNT_7X24);
		meta.setModoEjecucion(SietePorVeintiCuatroConstant.MODO_EJECUCION_7X24_A);
		meta.setLogueoAgendaHistorica(SietePorVeintiCuatroConstant.LOGUEO_AGENDA_HISTORICO);

		XMLRequestEntity.META.Cliente cliente = new XMLRequestEntity.META.Cliente();
		cliente.setTipoPersona(SietePorVeintiCuatroConstant.TIPO_PERSONA_7X24);
		cliente.setTipoId(clienteSesion.getTipoDocumento());
		cliente.setIdCliente(clienteSesion.getDni());
		cliente.setFechaNac(clienteSesion.getFechaNacimiento());
		cliente.setNUP(nup);

		meta.setCliente(cliente);

		XMLRequestEntity.META.Usuario usuario = new XMLRequestEntity.META.Usuario();
		usuario.setUsuarioTipo(SietePorVeintiCuatroConstant.USUARIO_TIPO_7X24);
		usuario.setUsuarioAtrib(SietePorVeintiCuatroConstant.USUARIO_ATRIBUTO_7X24);
		usuario.setUsuarioPwd(clienteSesion.getPasswordRacf());
		usuario.setUsuarioId(clienteSesion.getUsuarioRacf());
		meta.setUsuario(usuario);

		XMLRequestEntity.META.Canal canal = new XMLRequestEntity.META.Canal();
		canal.setCanalTipo(SietePorVeintiCuatroConstant.CANAL_TIPO_7X24);
		canal.setCanalId(SietePorVeintiCuatroConstant.CANAL_ID_7X24);
		canal.setCanalVersion(SietePorVeintiCuatroConstant.CANAL_VERSION_7X24);
		meta.setCanal(canal);

		XMLRequestEntity.META.Subcanal subcanal = new XMLRequestEntity.META.Subcanal();
		subcanal.setSubcanalTipo(SietePorVeintiCuatroConstant.SUBCANAL_TIPO_7X24);
		subcanal.setSubcanalId(SietePorVeintiCuatroConstant.SUBCANAL_ID_7X24);
		meta.setSubcanal(subcanal);

		XMLRequestEntity.DATOSADICIONALES datosAdicionales = new XMLRequestEntity.DATOSADICIONALES();
		XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos datosMensAvisos = new XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos();
		datosMensAvisos
				.setTitularDebito(clienteSesion.getNombre() + STRING_VACIO_LONGITUD_1 + clienteSesion.getApellido1());
		datosMensAvisos.setMailCredito(transferenciaDTO.getEmail());
		datosMensAvisos.setComentario(transferenciaDTO.getComentario());
		datosMensAvisos.setConcepto(transferenciaDTO.getConcepto().getCodigo());
		datosMensAvisos.setInfoAdicional(transferenciaDTO.getDescConcepto());
		datosMensAvisos.setMailClienteReply(STRING_VACIO);
		datosMensAvisos.setConceptoAdicional1(STRING_VACIO);
		datosMensAvisos.setDescripcionAdicional1(STRING_VACIO);
		datosMensAvisos.setConceptoAdicional2(STRING_VACIO);
		datosMensAvisos.setDescripcionAdicional2(STRING_VACIO);
		datosMensAvisos.setConceptoAdicional3(STRING_VACIO);
		datosMensAvisos.setDescripcionAdicional3(STRING_VACIO);
		datosMensAvisos.setAnotacionesPersonales(STRING_VACIO);
		datosAdicionales.setDatosMensAvisos(datosMensAvisos);

		request.setDatosAdicionales(datosAdicionales);
		request.setCONFIG(config);
		request.setMETA(meta);
		return request;
	}

	/**
	 * Generar request transferencia otros bancos.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transferencia
	 *            the transferencia
	 * @param indicadorFuncion
	 *            the indicador funcion
	 * @return the iatx request
	 * @throws DAOException
	 *             the DAO exception
	 */
	private IatxRequest generarRequestTransferenciaOtrosBancos(Cliente cliente, TransferenciaDTO transferencia,
			IndicadorFuncion indicadorFuncion) throws DAOException {
		IatxRequest request = new IatxRequest(SERVICIOTRFCCI, VERSIONTRFCCI);
		String[] tramaRequest = generarRequestTransferenciaTrfcciIatx(cliente, transferencia, indicadorFuncion);
		IatxRequestData iatxRequestData = generarIatxRequestData(cliente, tramaRequest);
		if(CuentasBancaPrivadaUtil.isCuentaBP(transferencia.getCuentaOrigen().getNroSucursal())) {
			iatxRequestData.setCanalTipo(canalBP);
			iatxRequestData.setSubCanalTipo(subCanalBP);
		}
		request.setData(iatxRequestData);
		return request;

	}

	/**
	 * Realizar transferencia a cuenta Rio. Retorna el mismo objeto recibido
	 * como parametro modificado para incluir el numero de operacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transferencia
	 *            la transferencia a realizar
	 * @return la transferencia realizada, con el número de operación informado
	 * @throws SaldoInsuficienteException
	 *             the saldo insuficiente exception
	 * @throws CuentaSinOperarException
	 *             the cuenta sin operar exception
	 * @throws ImporteLimiteException
	 *             the importe limite exception
	 * @throws DAOException
	 *             the DAO exception
	 * @throws CuentaOrigenSinBanelcoException
	 *             the cuenta origen sin banelco exception
	 */
	public TransferenciaDTO realizarTransferenciaInmediataRioRio(Cliente cliente, TransferenciaDTO transferencia)
			throws SaldoInsuficienteException, CuentaSinOperarException, ImporteLimiteException, DAOException, CuentaOrigenSinBanelcoException {
		IatxRequest request = generarRequestTransferenciaRio(cliente, transferencia);
		invocarServicioTransferencia(cliente, transferencia, request);
		return transferencia;
	}

	/**
	 * Generar request transferencia rio.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transferencia
	 *            the transferencia
	 * @return the iatx request
	 * @throws DAOException
	 *             the DAO exception
	 */
	private IatxRequest generarRequestTransferenciaRio(Cliente cliente, TransferenciaDTO transferencia)
			throws DAOException {
		IatxRequest request = new IatxRequest(TRFCTA7X24, VERSIONTRFCTA7X24);
		String[] tramaRequest = generarRequestTransferenciaTrfctaIatx(transferencia);
		IatxRequestData iatxRequestData = generarIatxRequestData(cliente, tramaRequest);
		if(CuentasBancaPrivadaUtil.isCuentaBP(transferencia.getNumeroCuentaDestino().getNroSucursal()) || 
				CuentasBancaPrivadaUtil.isCuentaBP(transferencia.getCuentaOrigen().getNroSucursal())) {
			iatxRequestData.setCanalTipo(canalBP);
			iatxRequestData.setSubCanalTipo(subCanalBP);
		}
		request.setData(iatxRequestData);
		return request;
	}

	/**
	 * Generar request transferencia trfcta iatx.
	 * 
	 * 1. Tipo_Cuenta_Debito(1) N2 2. Sucursal_Cuenta_Debito N3 3.
	 * Nro_Cuenta_Debito N7 4. Importe_Debito N14 5. Tipo_Cuenta_Credito(1) N2
	 * 6. Sucursal_Cuenta_Credito N3 7. Nro_Cuenta_Credito N7 8. Importe_Credito
	 * N14 9. Cotizacion_Transferencia(2) N14 10. Nro_Comprobante N8 11.
	 * Indicador_Limite_Max A1 12. Indicador de afectar disponible A1 13. Codigo
	 * de concepto A3 14. Descripcion del concepto A12 15. Indicador
	 * transferencia Diferida A1 16. Direccion_Email A100 17.
	 * Titular_Cuenta_Crédito A30 18. Comentario A450 19. Concepto_Adicional_1
	 * A40 20. Descripcion-Adicional_1 A40 21. Concepto_Adicional_2 A40 22.
	 * Descripcion-Adicional_2 A40 23. Concepto_Adicional_3 A40 24.
	 * Descripcion-Adicional_3 A40 25. Concepto A12 26. Informacion_Adicional
	 * A12
	 *
	 * @param transferencia
	 *            the transferencia
	 * @return the string[]
	 */
	private String[] generarRequestTransferenciaTrfctaIatx(TransferenciaDTO transferencia) {
		List<String> elementosTrama = new ArrayList<String>();

		Cuenta cuentaOrigen = transferencia.getCuentaOrigen();
		if (cuentaOrigen.getTipoCuenta().equals(CUENTA_UNICA)) {
			TipoCuenta tipoCuenta = TransferenciaUtil.matchearTipoCuentaDestino(transferencia.getMoneda().getMoneda());
			elementosTrama.add(StringUtils.leftPad(tipoCuenta.getCodigo().toString(), LONGITUD_LNG_CTA_BANE, "0"));
		} else {
			elementosTrama.add(StringUtils.leftPad(cuentaOrigen.getTipoCuentaEnum().getCodigo().toString(),
					LONGITUD_LNG_CTA_BANE, "0"));
		}
		elementosTrama.add(StringUtils.right(cuentaOrigen.getNroSucursal(), LONGITUD_SUCURSAL_DEBITO));
		elementosTrama.add(StringUtils.right(cuentaOrigen.getNroCuentaProducto(), LONGITUD_NRO_CUENTA_DEBITO));
		String importeString = String.format(Locale.US, "%015.2f", transferencia.getImporte()).replace(".", "");
		elementosTrama.add(importeString);
		elementosTrama.add(StringUtils.leftPad(transferencia.getTipoCuentaDestino().getCodigo().toString(),
				LONGITUD_LNG_CTA_BANE, "0"));
		elementosTrama.add(StringUtils.leftPad(transferencia.getNumeroCuentaDestino().getNroSucursal(),
				LONGITUD_SUCURSAL_DEBITO, "0"));
		elementosTrama.add(StringUtils.leftPad(transferencia.getNumeroCuentaDestino().getNroCuentaProducto(),
				LONGITUD_NRO_CUENTA_CREDITO, "0"));
		elementosTrama.add(importeString);
		elementosTrama.add(COTIAZACION_TRANSFERENCIA_14_CEROS);
		elementosTrama.add(NUMERO_COMPRBANTE_8_CEROS);
		elementosTrama.add(N_CHAR);
		elementosTrama.add(N_CHAR);
		elementosTrama.add(transferencia.getConcepto().getCodigo());
		elementosTrama.add(StringUtils.rightPad(
				ISBANStringUtils.removerCaraceteresEspeciales(transferencia.getDescripcionAdicional()),
				LONGITUD_MAX_CONCEPTO));
		elementosTrama.add(N_CHAR);
		elementosTrama.add(StringUtils.rightPad(StringUtils.defaultString(transferencia.getEmail()), LONGITUD_EMAIL));
		elementosTrama.add(StringUtils.rightPad(transferencia.getNombreReceptor(), TITULAR_LONGITUD));
		elementosTrama.add(StringUtils.rightPad(transferencia.getEmailMensaje(), LONGITUD_COMENTARIO));
		elementosTrama.add(VACIO_40);
		elementosTrama.add(VACIO_40);
		elementosTrama.add(VACIO_40);
		elementosTrama.add(VACIO_40);
		elementosTrama.add(VACIO_40);
		elementosTrama.add(VACIO_40);
		elementosTrama.add(StringUtils.rightPad(transferencia.getConcepto().getCodigo(), LONGITUD_MAX_CONCEPTO));
		elementosTrama.add(StringUtils.rightPad(
				ISBANStringUtils.removerCaraceteresEspeciales(transferencia.getDescripcionAdicional()),
				LONGITUD_MAX_CONCEPTO));
		return elementosTrama.toArray(new String[0]);
	}

	/**
	 * Generar request ALTADHABCF.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the iatx request
	 * @throws DAOException
	 *             the DAO exception
	 */
	private IatxRequest generarRequestALTADHABCF(Cliente cliente) throws DAOException {
		IatxRequest request = new IatxRequest(SERVICIO_ALTADHABCF, SERVICIO_ALTADHABCF_VERSION);
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(IND_OPERACION_A);
		iatxRequestData.addBodyValue(IND_MODALIDAD_PAGO_04);
		iatxRequestData.addBodyValue(IND_MODALIDAD_BANCA_FACIL_03);

		if( cliente.getCuentasMonetarias().isEmpty() && !cliente.getCuentasBPTransferibles().isEmpty() ) {
			iatxRequestData.setCanalTipo(canalBP);
			iatxRequestData.setSubCanalTipo(subCanalBP);
		}

		request.setData(iatxRequestData);
		return request;
	}

	/**
	 * Invocar servicio.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transferencia
	 *            la transferencia a realizar
	 * @param request
	 *            the request
	 * @return la transferencia realizada, con el número de operación informado
	 * @throws SaldoInsuficienteException
	 *             the saldo insuficiente exception
	 * @throws CuentaSinOperarException
	 *             the cuenta sin operar exception
	 * @throws ImporteLimiteException
	 *             the importe limite exception
	 * @throws CuentaCerradaException
	 *             the cuenta cerrada exception
	 * @throws CuentaSinDebitosNiAcreditacionesException
	 *             the cuenta sin debitos ni acreditaciones exception
	 * @throws DAOException
	 *             the DAO exception
	 * @throws CuentaOrigenSinBanelcoException
	 *             the cuenta origen sin banelco exception
	 */
	private TransferenciaDTO invocarServicioTransferencia(Cliente cliente, TransferenciaDTO transferencia,
			IatxRequest request) throws SaldoInsuficienteException, CuentaSinOperarException, ImporteLimiteException,
			CuentaCerradaException, CuentaSinDebitosNiAcreditacionesException, DAOException, CuentaOrigenSinBanelcoException {

		IatxResponse iatxResponse = null;
		try {
			iatxResponse = iatxComm.exec(request);

			/** S1. Cod_retorno */
			int codigoDeRetorno = iatxResponse.getErrorCode();
			switch (codigoDeRetorno) {
			case OK_CODIGO_RETORNO_EXTENDIDO:
				parsearResponseOk(transferencia, iatxResponse);
				break;
			case CODIGO_ERROR_SALDO_INSUFICIENTE_1A:
			case CODIGO_ERROR_SALDO_INSUFICIENTE_1B:
			case CODIGO_ERROR_SALDO_INSUFICIENTE_1C:
				parsearResponseError(transferencia, iatxResponse);
				if (transferencia.getSistemaAsociadoAlError().equalsIgnoreCase(ID_SISTEMA_SALDO_INSUFICIENTE_1)) {
					throw new SaldoInsuficienteException(generarCodigoErrorDesconocidoMensaje(iatxResponse));
				}
				break;
			case CODIGO_ERROR_SALDO_INSUFICIENTE_2A:
			case CODIGO_ERROR_SALDO_INSUFICIENTE_2B:
				parsearResponseError(transferencia, iatxResponse);
				if (transferencia.getSistemaAsociadoAlError().equalsIgnoreCase(ID_SISTEMA_SALDO_INSUFICIENTE_2)) {
					throw new SaldoInsuficienteException(generarCodigoErrorDesconocidoMensaje(iatxResponse));
				}
				throw new DAOException(generarCodigoErrorDesconocidoMensaje(iatxResponse));
			case CODIGO_ERROR_IMPORTE_LIMITE:
				parsearResponseError(transferencia, iatxResponse);
				if (transferencia.getSistemaAsociadoAlError().equalsIgnoreCase(ID_SISTEMA_IMPORTE_LIMITE)) {
					throw new ImporteLimiteException(generarCodigoErrorDesconocidoMensaje(iatxResponse));
				} else {
					throw new DAOException(generarCodigoErrorDesconocidoMensaje(iatxResponse));
				}
			case CODIGO_ERROR_CUENTA_SIN_OPERAR:
				parsearResponseError(transferencia, iatxResponse);
				if (transferencia.getSistemaAsociadoAlError().equalsIgnoreCase(ID_SISTEMA_AZB)) {
					throw new CuentaSinOperarException(generarCodigoErrorDesconocidoMensaje(iatxResponse));
				} else {
					throw new DAOException(generarCodigoErrorDesconocidoMensaje(iatxResponse));
				}
			case CODIGO_ERROR_CUENTA_CERRADA:
				parsearResponseError(transferencia, iatxResponse);
				if (transferencia.getSistemaAsociadoAlError().equalsIgnoreCase(ID_SISTEMA_AZB)) {
					throw new CuentaCerradaException(generarCodigoErrorDesconocidoMensaje(iatxResponse));
				} else {
					throw new DAOException(generarCodigoErrorDesconocidoMensaje(iatxResponse));
				}
			case CODIGO_ERROR_CUENTA_NO_DEBITOS_NI_ACREDITACIONES:
				parsearResponseError(transferencia, iatxResponse);
				throw new CuentaSinDebitosNiAcreditacionesException(generarCodigoErrorDesconocidoMensaje(iatxResponse));
			case ERROR_FLUJO_MANUAL_56:
			    parsearResponseError(transferencia, iatxResponse);
	            throw new CuentaOrigenSinBanelcoException();
			case CODIGO_ERROR_TIME_OUT:
				parsearResponseError(transferencia, iatxResponse);
				throw new HostTimeOutException(generarCodigoErrorDesconocidoMensaje(iatxResponse));
			case CODIGO_ERROR_TRF_EXCEDE_LIMITE_USD_BENEFICIARIO:
			    parsearResponseError(transferencia, iatxResponse);
			    throw new ExcedeLimiteDolaresBeneficiarioException(generarCodigoErrorDesconocidoMensaje(iatxResponse));
			case TRANSFERENCIA_DOLARES_INHABILITADA:
			    throw new TransferenciaDolaresInhabilitadaException(""+iatxResponse.getErrorCode());
			case COD_CUENTA_ALFA_NO_VERIFICADA_NUNCA_OPERO_USD:
			    throw new CuentaAlfaNoVerificadaNuncaOperoUsdException(""+iatxResponse.getErrorCode());
			case COD_CUENTA_ALFA_NO_VERIFICADA:
			    throw new CuentaAlfaNoVerificadaException(""+iatxResponse.getErrorCode());
			default:
				throw new DAOException(generarCodigoErrorDesconocidoMensaje(iatxResponse));
			}
		} catch (IatxException e) {
			if (TIMEOUT_EXCEPTION.equals(e.getMessage())) {
				LOGGER.error("TimeOutException. Se envio la transaccion al CICS pero no se recibio respuesta", e);
				throw new HostTimeOutException();
			} else {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e);
			}
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}

		return transferencia;
	}

	/**
	 * Parsear response error.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @param iatxResponse
	 *            the iatx response
	 */
	private void parsearResponseError(TransferenciaDTO transferencia, IatxResponse iatxResponse) {
		transferencia.setDescripcionesDeError(new ArrayList<String>());
		transferencia.setSistemaAsociadoAlError(iatxResponse.getData(POSICION_SISTEMA_ASOCIADO_AL_ERROR));
		int cantidadMensajes = Integer.valueOf(iatxResponse.getData(POSICION_CANTIDAD_DESCRIPCIONES));

		for (int i = POSICION_INICIO_DESCRIPCIONES; i < cantidadMensajes + POSICION_INICIO_DESCRIPCIONES; i++) {
			transferencia.getDescripcionesDeError().add(iatxResponse.getData(i));
		}
	}

	/**
	 * Generar iatx request data.
	 *
	 * @param cliente
	 *            the cliente
	 * @param trama
	 *            the trama
	 * @return the iatx request data
	 */
	private IatxRequestData generarIatxRequestData(Cliente cliente, String[] trama) {
		IatxRequestData requestData = new IatxRequestData(cliente);
		/** itero y agrego los elementos de la trama al iatx request */
		for (String string : trama) {
			requestData.addBodyValue(string);
		}
		return requestData;
	}

	/**
	 * Arma un String[] apropiado para llenar el IatxRequestData para la
	 * transferencia. No agrega codigo de repeticion ni NUO
	 *
	 * @param cliente
	 * @param transferencia
	 *            la transferencia a realizar
	 * @param indicadorFuncion
	 *            the indicador funcion
	 * @return un String[] apropiado para llenar el IatxRequestData de la
	 *         transferencia
	 * @throws DAOException
	 *             the DAO exception
	 */
	private String[] generarRequestTransferenciaTrfcciIatx(Cliente cliente, TransferenciaDTO transferencia,
			IndicadorFuncion indicadorFuncion) throws DAOException {
		return generarTramaRequestTransferencia(cliente, transferencia, indicadorFuncion);
	}

	/**
	 * Arma un String[] apropiado para llenar el IatxRequestData para la
	 * transferencia. No agrega codigo de repeticion ni NUO<br>
	 * LUIS MOYANO: al momento no es posible achicar el metodo ya que alteraria
	 * la logica de armada de la trama.
	 *
	 * @param transferenciaDTO
	 *            la transferencia a realizar
	 * @param indicadorFuncion
	 *            the indicador funcion
	 * @return un String[] apropiado para llenar el IatxRequestData de la
	 *         transferencia
	 */
	private String[] generarTramaRequestTransferencia(Cliente cliente, TransferenciaDTO transferenciaDTO,
			IndicadorFuncion indicadorFuncion) {

		List<String> elementosTrama = new ArrayList<String>();
		elementosTrama.add(indicadorFuncion.getValorTrfcci());

		Cuenta cuentaOrigen = transferenciaDTO.getCuentaOrigen();
		if (cuentaOrigen.getTipoCuenta().equals(CUENTA_UNICA)) {
			TipoCuenta tipoCuenta = TransferenciaUtil
					.matchearTipoCuentaDestino(transferenciaDTO.getMoneda().getMoneda());
			elementosTrama.add(StringUtils.leftPad(tipoCuenta.getCodigo().toString(), LONGITUD_LNG_CTA_BANE, "0"));
		} else {
			elementosTrama.add(StringUtils.leftPad(cuentaOrigen.getTipoCuentaEnum().getCodigo().toString(),
					LONGITUD_LNG_CTA_BANE, "0"));
		}
		elementosTrama
				.add(StringUtils.leftPad(transferenciaDTO.getCuentaOrigen().getNroSucursal(), LONGITUD_SUCURSAL, '0'));
		elementosTrama
				.add(StringUtils.right(transferenciaDTO.getCuentaOrigen().getNroCuentaProducto(), LONGITUD_NRO_CTA));
		elementosTrama.add(StringUtils.right(StringUtils.leftPad(
				StringUtils.defaultString(transferenciaDTO.getCuentaOrigen().getNroOrdenFirmante()), 2, '0'), 2));
		elementosTrama.add(String.valueOf(transferenciaDTO.getTipoTransferencia()));
		elementosTrama.add(StringUtils.rightPad(transferenciaDTO.getReferenciaUnivoca(), LONGITUD_REF_UNIV));
		elementosTrama.add(String.format(Locale.US, "%012.2f", transferenciaDTO.getImporte()).replace(".", ""));
		elementosTrama.add(String.valueOf(getCodigoMonedaTrfcci(transferenciaDTO.getMoneda())));
		elementosTrama.add(StringUtils
				.rightPad(StringUtils.defaultString(transferenciaDTO.getInformacionDiscrecional()), LONGITUD_INF_DISC));
		elementosTrama.add(StringUtils.leftPad(StringUtils.defaultString(transferenciaDTO.getCuit().replace("-", "")),
				LONGITUD_ID_BENEF, '0'));
		elementosTrama
				.add(StringUtils.leftPad(transferenciaDTO.getEntidadCuentaCredito(), LONGITUD_ENTIDAD_CTA_CRED, '0'));
		elementosTrama
				.add(StringUtils.leftPad(transferenciaDTO.getSucursalCuentaCredito(), LONGITUD_SUCURSA_CTA_CRED, '0'));
		elementosTrama.add(String.valueOf(transferenciaDTO.getDigitoCuentaCredito()));
		elementosTrama.add(StringUtils.leftPad(transferenciaDTO.getNumeroCuentaCredito(), LONGITUD_NRO_CUENTA, '0'));
		elementosTrama.add(StringUtils.defaultString(transferenciaDTO.getMarcaLimite()));
		elementosTrama.add(obtenerMarcaGravado(transferenciaDTO, false));
		elementosTrama.add(esTransferenciaOtrosBancosCuentaPropia(transferenciaDTO, cliente));
		if ("1".equals(errorBanelcoCoelsaHabilitado.trim())) {
			elementosTrama.add(transferenciaDTO.isVaPorCoelsa() ? PlazoAcreditacion.INMEDIATO_COELSA.getCodigoTrfcci()
					: PlazoAcreditacion.INMEDIATO.getCodigoTrfcci());
		} else {
			if (ISBANStringUtils.validarCVU(transferenciaDTO.getCbuCuenta())) {
				elementosTrama.add(PlazoAcreditacion.INMEDIATO_COELSA.getCodigoTrfcci());
			} else {
				elementosTrama.add(transferenciaDTO.isVaPorCoelsa() ? PlazoAcreditacion.PLAZO_24_HS.getCodigoTrfcci()
						: PlazoAcreditacion.INMEDIATO.getCodigoTrfcci());
			}
		}
		// TODO: emilio, se leia de transferenciaDTO
		elementosTrama.add(StringUtils.defaultString("N"));
		elementosTrama.add(StringUtils.leftPad("0", LONGITUD_CANT_DIAS, '0'));

		elementosTrama.add(sesionCliente.obtenerIpV4SinPuntos());

		// Si existe el cuit, entonces los datos del destinatarios se
		// encontraron en coelsa durante la solicitud de la transferencia.
		if (transferenciaDTO.getTipoDeCuentaToBanelco() != null) {
			elementosTrama.add(
					StringUtils.rightPad(StringUtils.defaultString(transferenciaDTO.getTitular()), LONGITUD_TITULAR));
			elementosTrama.add(formatearCuit(transferenciaDTO.getCuit()));
			elementosTrama.add(formatearCuit(transferenciaDTO.getCuit2()));
			elementosTrama.add(formatearCuit(transferenciaDTO.getCuit3()));
		} else {
			elementosTrama.add(StringUtils.rightPad(StringUtils.defaultString(transferenciaDTO.getNombreReceptor()),
					LONGITUD_TITULAR));
			elementosTrama.add(formatearCuit(transferenciaDTO.getCuit()));
			elementosTrama.add(STRING_VACIO_11);
			elementosTrama.add(STRING_VACIO_11);
		}

		if (transferenciaDTO.getPlazoAcreditacion().equals(PlazoAcreditacion.INMEDIATO)) {
			// Si existe el cuit, entonces los datos del destinatarios se
			// encontraron en coelsa durante la solicitud de la transferencia.
			if (transferenciaDTO.getTipoDeCuentaToBanelco() != null) {
				elementosTrama
						.add(StringUtils.leftPad(String.valueOf(transferenciaDTO.getCuentaDestinoBanelco().length()),
								LONGITUD_LNG_CTA_BANE, '0'));
				elementosTrama
						.add(StringUtils.rightPad(StringUtils.defaultString(transferenciaDTO.getCuentaDestinoBanelco()),
								LONGITUD_CTA_DEST_BANE, ' '));
				elementosTrama.add(transferenciaDTO.getTipoDeCuentaToBanelco().getCodigoTrfcci());
				elementosTrama.add(transferenciaDTO.getTipoDeCuentaFromBanelco().getCodigoTrfcci());
			} else {
				elementosTrama.add(STRING_VACIO_LONGITUD_2);
				elementosTrama.add(STRING_VACIO_LONGITUD_28);
				elementosTrama.add(STRING_VACIO_LONGITUD_2);
				elementosTrama.add(STRING_VACIO_LONGITUD_2);
			}
		} else {
			elementosTrama.add(StringUtils.leftPad("", LONGITUD_LNG_CTA_BANE, '0'));
			elementosTrama.add(StringUtils.leftPad("", LONGITUD_CTA_DEST_BANE, ' '));
			elementosTrama.add(StringUtils.leftPad("", LONGITUD_LNG_CTA_BANE, '0'));
			elementosTrama.add(StringUtils.leftPad("", LONGITUD_LNG_CTA_BANE, '0'));
		}
		elementosTrama
				.add(StringUtils.rightPad(StringUtils.defaultString(transferenciaDTO.getEmail()), LONGITUD_EMAIL));
		elementosTrama.add(StringUtils.rightPad(transferenciaDTO.getEmailMensaje(), LONGITUD_COMENTARIO));
		elementosTrama.add(StringUtils.rightPad(StringUtils.defaultString(transferenciaDTO.getConceptoAdicional()),
				LONGITUD_CONCEPTO_ADICIONAL));
		elementosTrama.add(StringUtils.rightPad(STRING_VACIO, LONGITUD_DESCRIPCION_ADICIONAL));
		elementosTrama.add(StringUtils.rightPad(StringUtils.defaultString(transferenciaDTO.getConceptoAdicional2()),
				LONGITUD_CONCEPTO_ADICIONAL));
		elementosTrama.add(StringUtils.rightPad(StringUtils.defaultString(transferenciaDTO.getDescripcionAdicional2()),
				LONGITUD_DESCRIPCION_ADICIONAL));
		elementosTrama.add(StringUtils.rightPad(StringUtils.defaultString(transferenciaDTO.getConceptoAdicional3()),
				LONGITUD_CONCEPTO_ADICIONAL));
		elementosTrama.add(StringUtils.rightPad(StringUtils.defaultString(transferenciaDTO.getDescripcionAdicional3()),
				LONGITUD_DESCRIPCION_ADICIONAL));
		elementosTrama
				.add(StringUtils.leftPad(StringUtils.defaultString(transferenciaDTO.getConcepto().getCodigo()), LONGITUD_MAX_CONCEPTO, STRING_VACIO));
		elementosTrama.add(StringUtils.rightPad(StringUtils.defaultString(transferenciaDTO.getInformacionAdicional()),
				LONGITUD_INFORMACION_ADICIONAL));
		elementosTrama.add(transferenciaDTO.getCbuCuenta());
		// Si existe el cuit, entonces los datos del destinatarios se
		// encontraron en coelsa durante la solicitud de la transferencia.
		if (transferenciaDTO.getBancoReceptor() != null) {
			elementosTrama
					.add(StringUtils.leftPad(StringUtils.defaultString(transferenciaDTO.getFiid()), 4, STRING_VACIO));
			elementosTrama
					.add(StringUtils.leftPad(StringUtils.defaultString(transferenciaDTO.getUser()), 7, STRING_VACIO));
			elementosTrama.add(StringUtils.defaultString(transferenciaDTO.getBancoReceptor()));
		} else {
			elementosTrama.add(STRING_VACIO_LONGITUD_4);
			elementosTrama.add(STRING_VACIO_LONGITUD_7);
			elementosTrama.add(STRING_VACIO_LONGITUD_4);
		}
		elementosTrama.add(transferenciaDTO.getConcepto().getOrdinal());
		elementosTrama.add(transferenciaDTO.getConcepto().getCodigo());
		elementosTrama.add(StringUtils.leftPad(StringUtils.defaultString(transferenciaDTO.getBancoDestino()),
				LONGITUD_DESCRIPCION_BANCO, STRING_VACIO_LONGITUD_1));
		elementosTrama.add(StringUtils.defaultString(transferenciaDTO.getAgenda()));

		return elementosTrama.toArray(new String[0]);
	}

	/**
	 * Formatear cuit.
	 *
	 * @param cuit
	 *            the cuit
	 * @return the string
	 */
	private String formatearCuit(String cuit) {
		if (cuit == null || "".equalsIgnoreCase(cuit.trim())) {
			return StringUtils.repeat(STRING_VACIO_LONGITUD_1, LONGITUD_CUIT);
		}
		String cuitSinGuiones = cuit.replaceAll("-", "");
		// Pattern patternCuit =
		// Pattern.compile("(\\d{2})[-/](\\d{3,8})[-/](\\d)");
		// Matcher matcherCuit = patternCuit.matcher(cuitSinPuntos);
		// StringBuilder cuitRet = new StringBuilder(LONGITUD_CUIT);
		// if (matcherCuit.matches()) {
		// matcherCuit.find();
		// cuitRet.append(matcherCuit.group(1)).append(StringUtils.leftPad(matcherCuit.group(2),
		// LONGITUD_DOC_CUIT))
		// .append(matcherCuit.group(3));
		// return cuitRet.toString();
		// }
		if (cuitSinGuiones.matches(String.format(Locale.US, "\\d{%d}", LONGITUD_CUIT))) {
			return cuitSinGuiones;
		}
		throw new IllegalArgumentException("CUIT/CUIL no formateable: [" + cuit + "].");
	}

	/**
	 * Gets the codigo moneda trfcci.
	 *
	 * @param moneda
	 *            the moneda
	 * @return the codigo moneda trfcci
	 */
	private byte getCodigoMonedaTrfcci(DivisaEnum moneda) {
		switch (moneda) {
		case PESO:
			return 0;
		case DOLAR:
			return 1;
		default:
			throw new IllegalArgumentException("Moneda no reconocida: " + moneda);
		}
	}

	/**
	 * Parsear response obtiene el número de operación.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @param iatxResponse
	 *            the iatx response
	 * @return el número de operación de la respuesta de iatx por la operación
	 *         de transferencia
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void parsearResponseOk(TransferenciaDTO transferencia, IatxResponse iatxResponse) throws DAOException {
		SimpleDateFormat dateParser = new SimpleDateFormat("yyyyMMddHHmmss");
		transferencia.setNumeroComprobante(iatxResponse.getNroComprobante());
		String fechaHoraString = iatxResponse.getFechaHoraReq();
		try {
			transferencia.setFechaDeOperacion(dateParser.parse(fechaHoraString));
		} catch (ParseException e) {
			LOGGER.info("Error al obtener fecha de la cadena: ", fechaHoraString);
			LOGGER.info("Se agrega la fecha actual");
			transferencia.setFechaDeOperacion(new Date());
		}
	}


	@Cacheable(cacheNames = CacheConstants.Names.CACHE_CNS_PAGO_HAB, key = "#cliente.nup" )
	@Override
	public TransferenciaModtrfe ejecutarModTrfe(Cliente cliente) throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(servicioCnsmodtrfe, versionCnsmodtrfe);
		try {
			IatxRequestData iatxRequestData = new IatxRequestData(cliente);
			iatxRequestData.addBodyValue("00");
			iatxRequestData.addBodyValue("000");
			iatxRequestData.addBodyValue("0000000");
			iatxRequestData.setIdCliente("999"+cliente.getNup());

			if( cliente.getCuentasMonetarias().isEmpty() && !cliente.getCuentasBPTransferibles().isEmpty() ) {
				iatxRequestData.setCanalTipo(canalBP);
				iatxRequestData.setSubCanalTipo(subCanalBP);
			}

			iatxRequest.setData(iatxRequestData);
			LOGGER.info("Ejecutando el servicio: " + servicioCnsmodtrfe + versionCnsmodtrfe);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				LOGGER.info("La ejecucion del servicio es {}, se procesa la respuesta", servicioCnsmodtrfe, versionCnsmodtrfe, errorCode);
				return new TransferenciaModtrfe.TransferenciaModtrfeBuilder()
				.addModalidadTransferencia(iatxResponse.getData(POSICION_MODALIDAD_TRANSFERENCIA_RESPUESTA))
				.addIndicadorAdhesionBee(iatxResponse.getData(POSICION_INDICADOR_ADHESION_BEE_RESPUESTA))
				.addPosicionRespuesta(iatxResponse.getData(POSICION_RESPUESTA))
				.build();
			} else {
				LOGGER.info("El codigo de retorno {} para el servicio no permite continuar el flujo normal", errorCode);
				throw new DAOException(generarCodigoErrorDesconocidoMensaje(errorCode));
			}
		} catch (IatxException e) {
			LOGGER.error("Servicio retorna error, no se pudo procesar la respuesta", e);
			throw new DAOException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO#
	 * ejecutarALTADHABCF(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	public void ejecutarALTADHABCF(Cliente cliente) throws DAOException {
		IatxRequest iatxRequest = generarRequestALTADHABCF(cliente);
		try {
			LOGGER.info(
					"Ejecutando el servicio: " + iatxRequest.getNombreServicio() + iatxRequest.getVersionServicio());
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO != errorCode) {
				LOGGER.error("Error al ejecutar el servicio: " + iatxRequest.getNombreServicio()
						+ iatxRequest.getVersionServicio() + " Cod Error:" + errorCode);
				throw new DAOException(generarCodigoErrorDesconocidoMensaje(errorCode));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO#
	 * validarOrigenDestinoTransferencia(ar.com.santanderrio.obp.servicios.
	 * clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.transferencias.entities.Transferencia)
	 */
	@Override
	public TransferenciaDTO validarOrigenDestinoTransferencia(Cliente cliente, TransferenciaDTO transferencia,
			String nroTarjetaActiva)
			throws DestinatarioNoVerificadoException, DAOException, TitularidadNoVerificadaException,
			BeneficiarioNoVerificadException, DestinatarioNoVerificadoERRORenCNSException {

		RequestCNSTITCBU requestCNSTITCBU = new RequestCNSTITCBU();
		requestCNSTITCBU.setCbuDestino(transferencia.getCbuCuenta());
		requestCNSTITCBU.setDireccionIP(transferencia.getIp());
		requestCNSTITCBU.setNroCuenta(
				StringUtils.right(transferencia.getCuentaOrigen().getNroCuentaProducto(), MAX_NRO_CUENTA));
		requestCNSTITCBU
				.setNroSucursal(StringUtils.right(transferencia.getCuentaOrigen().getNroSucursal(), MAX_NRO_SUCURSAL));
		requestCNSTITCBU.setNroTarjeta(nroTarjetaActiva);

		if (transferencia.getCuentaOrigen().getTipoCuenta().equals("02")) {
			if (DivisaEnum.PESO.equals(transferencia.getMoneda())) {
				requestCNSTITCBU.setTipoCuenta(StringUtils.leftPad(
						transferencia.getCuentaOrigen().getTipoCuentaSinUnificar(), LONGITUD_LNG_CTA_BANE, "0"));
			} else if (DivisaEnum.DOLAR.equals(transferencia.getMoneda())) {
				requestCNSTITCBU.setTipoCuenta(StringUtils.leftPad(
						transferencia.getCuentaOrigen().getTipoCuentaSinUnificarDls(), LONGITUD_LNG_CTA_BANE, "0"));
			}
		} else {
			requestCNSTITCBU.setTipoCuenta(
					StringUtils.leftPad(transferencia.getCuentaOrigen().getTipoCuenta(), LONGITUD_LNG_CTA_BANE, "0"));
		}
		TransferenciaDTO transferenciaRetorno = null;
		IatxResponse iatxResponse = conexionCNSTITICBU(cliente, requestCNSTITCBU);
		int errorCode = iatxResponse.getErrorCode();
		switch (errorCode) {
		case OK_CODIGO_RETORNO:
			transferenciaRetorno = parsearResponseOrigenDestinoValido(iatxResponse);
			transferenciaRetorno.setCbuCuenta(transferencia.getCbuCuenta());
			transferenciaRetorno.setCuentaOrigen(transferencia.getCuentaOrigen());
			transferencia.setLongitudCuentaDestinoBanelco(transferenciaRetorno.getLongitudCuentaDestinoBanelco());
			transferencia.setCuentaDestinoBanelco(transferenciaRetorno.getCuentaDestinoBanelco());
			transferencia.setTipoDeCuentaToBanelco(transferenciaRetorno.getTipoDeCuentaToBanelco());
			transferencia.setTipoDeCuentaFromBanelco(transferenciaRetorno.getTipoDeCuentaFromBanelco());
			if(!StringUtils.isNotBlank(transferencia.getBancoDestino())){
			    transferencia.setBancoDestino(transferenciaRetorno.getBancoDestino());
			}
			if(!StringUtils.isNotBlank(transferencia.getBancoReceptor())){
			    transferencia.setBancoReceptor(transferenciaRetorno.getBancoReceptor());
			}
			transferencia.setFiid(transferenciaRetorno.getFiid());
			transferencia.setUser(transferenciaRetorno.getUser());
			break;
		case TransferenciaUtil.ERROR_SIN_REITENTO_36:
		    throw new TitularidadNoVerificadaException(TransferenciaUtil.ERROR_SIN_REITENTO_36);
		case TransferenciaUtil.ERROR_SIN_REINTENTO_66:
			throw new TitularidadNoVerificadaException(TransferenciaUtil.ERROR_SIN_REINTENTO_66);
		case TransferenciaUtil.ERROR_SIN_REITENTO_57:
			throw new TitularidadNoVerificadaException(TransferenciaUtil.ERROR_SIN_REITENTO_57);
		case TransferenciaUtil.ERROR_USUARIOTARJETA_INEXISTENTE_50:
			throw new TitularidadNoVerificadaException(TransferenciaUtil.ERROR_USUARIOTARJETA_INEXISTENTE_50);
			// Manuel.Vargas: estas casuisticas de errores se usan en
			// PagoHaberesDAO. Verificar cuando CNSTITCBU funcione correctamente
			// su vigencia: ***************************************************
		case DESTINATARIO_NO_VERIFICADO_1:
		case DESTINATARIO_NO_VERIFICADO_2:
			throw new DestinatarioNoVerificadoException();
			// *****************************************************************
		case ERROR_FLUJO_MANUAL_56:
		case ERROR_FLUJO_MANUAL_65:
		case ERROR_FLUJO_MANUAL_70:
		case ERROR_FLUJO_MANUAL_74:
		case ERROR_FLUJO_MANUAL_76:
		case ERROR_SIN_REITENTO_505:
		default:
			// manuel.vargas: cualquier error debe forzar ir por coelsa
			// throw new DAOException();
			throw new TitularidadNoVerificadaException(errorCode);
		}
		return transferenciaRetorno;
	}

	/**
	 * Generar codigo error desconocido mensaje.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the string
	 */
	private String generarCodigoErrorDesconocidoMensaje(IatxResponse iatxResponse) {
		return new StringBuilder().append(CODIGO_DE_ERROR_DESCONOCIDO_MSG).append(". errorCode: ")
				.append(CODIGO_DE_ERROR_INICIO_STRING).append(iatxResponse.getErrorCode())
				.append(CODIGO_DE_ERROR_FIN_STRING).append(", errorMessage: ").append(CODIGO_DE_ERROR_INICIO_STRING)
				.append(iatxResponse.getErrorMessage()).append(CODIGO_DE_ERROR_FIN_STRING).append(", errorSystem: ")
				.append(CODIGO_DE_ERROR_INICIO_STRING).append(iatxResponse.getErrorSystem())
				.append(CODIGO_DE_ERROR_FIN_STRING).toString();
	}

	/**
	 * Generar codigo error desconocido mensaje.
	 *
	 * @param errorCode
	 *            the error code
	 * @return the string
	 */
	private String generarCodigoErrorDesconocidoMensaje(int errorCode) {
		return new StringBuilder().append(CODIGO_DE_ERROR_DESCONOCIDO_MSG).append(CODIGO_DE_ERROR_INICIO_STRING)
				.append(errorCode).append(CODIGO_DE_ERROR_FIN_STRING).toString();
	}

	/**
	 * Parsear response origen destino valido.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the transferencia
	 * @throws DAOException
	 *             the DAO exception
	 * @throws DestinatarioNoVerificadoERRORenCNSException
	 *             the destinatario no verificado ERRO ren CNS exception
	 */
	private TransferenciaDTO parsearResponseOrigenDestinoValido(IatxResponse iatxResponse)
			throws DAOException, DestinatarioNoVerificadoERRORenCNSException {
		TransferenciaDTO transferencia = new TransferenciaDTO();
		transferencia.setTitular(iatxResponse.getNextData());

		// TODO: @Manuel.Vargas. Agregado por intermitencia del
		// servicio, sacar cuando se pase a HOMO/PRODUCCION.
		if (STRING_VACIO.equals(transferencia.getTitular().trim())) {
			LOGGER.info(ERROR_EN_BANELCO_CNSTITCBU_CAMPOS_VACIOS);
			throw new DestinatarioNoVerificadoERRORenCNSException();
		} // ****

		transferencia.setCuit(iatxResponse.getNextData());
		transferencia.setCuit2(iatxResponse.getNextData());
		transferencia.setCuit3(iatxResponse.getNextData());
		transferencia.setBancoDestino(iatxResponse.getNextData());
		iatxResponse.getNextData();
		transferencia.setCuentaDestinoBanelco(iatxResponse.getNextData());
		transferencia.setTipoDeCuentaToBanelco(TipoCuentaBanelco.buscarPorCodigoTrfcci(iatxResponse.getNextData()));
		transferencia.setTipoDeCuentaFromBanelco(TipoCuentaBanelco.buscarPorCodigoTrfcci(iatxResponse.getNextData()));
		transferencia.setBancoReceptor(iatxResponse.getNextData());
		transferencia.setFiid(iatxResponse.getNextData());
		transferencia.setUser(iatxResponse.getNextData());

		return transferencia;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO#validarOrigenDestinoTransferenciaCVU(ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO, java.lang.String, java.lang.String, ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta)
	 */
	public TransferenciaDTO validarOrigenDestinoTransferenciaCVU(TransferenciaDTO transferencia, String userAgent, 
	        String monedaSeleccionada, Cuenta cuentaOrigenSeleccionada) throws DAOException, TitularidadNoVerificadaException {
        
	    TransferenciaDTO transferenciaRetorno = null;
        LOGGER.debug("TransferenciaDAO Consultar Titularidad Request de entrada");
        ConsultarDatosTitularidad consultarDatosTitularidadIn = generarRequestWsConsultarTitularidad(userAgent, cuentaOrigenSeleccionada, transferencia.getCbuCuenta());
        try {
            LOGGER.debug("TransferenciaDAO Consultar Titularidad Llamando alias Dao");
            ConsultarDatosTitularidadResponse response = aliasCbuDAO.consultarDatosTitularidad(consultarDatosTitularidadIn);
            if (response.getTitularidad() != null) {
                transferenciaRetorno = parsearResponseTitularidadOrigenDestinoValido(response, monedaSeleccionada, cuentaOrigenSeleccionada);
                transferenciaRetorno.setCbuCuenta(transferencia.getCbuCuenta());
                transferenciaRetorno.setCuentaOrigen(transferencia.getCuentaOrigen());
                transferencia.setLongitudCuentaDestinoBanelco(transferenciaRetorno.getLongitudCuentaDestinoBanelco());
                transferencia.setCuentaDestinoBanelco(transferenciaRetorno.getCuentaDestinoBanelco());
                transferencia.setTipoDeCuentaToBanelco(transferenciaRetorno.getTipoDeCuentaToBanelco());
                transferencia.setTipoDeCuentaFromBanelco(transferenciaRetorno.getTipoDeCuentaFromBanelco());
                transferencia.setBancoDestino(transferenciaRetorno.getBancoDestino());
                transferencia.setBancoReceptor(transferenciaRetorno.getBancoReceptor());
                transferencia.setFiid(transferenciaRetorno.getFiid());
                transferencia.setUser(transferenciaRetorno.getUser());
            } else {
                // No se obtuvieron datos de titularidad
                LOGGER.error("TransferenciaDAO Consultar Titularidad - Sin datos titularidad");
                throw new DAOException();
            }
        } catch (Exception e) {
            LOGGER.error("TransferenciaDAO Consultar Titularidad Exception DAO");
            throw new TitularidadNoVerificadaException();
        }
        return transferenciaRetorno;
    }

	/**
	 * Parsear response titularidad origen destino valido.
	 *
	 * @param response
	 *            the response
	 * @param monedaSeleccionada
	 *            the moneda seleccionada
	 * @param cuentaOrigenSeleccionada
	 *            the cuenta origen seleccionada
	 * @return the transferencia DTO
	 * @throws DestinatarioNoVerificadoERRORenCNSException
	 *             the destinatario no verificado ERRO ren CNS exception
	 */
	private TransferenciaDTO parsearResponseTitularidadOrigenDestinoValido(ConsultarDatosTitularidadResponse response,
	        String monedaSeleccionada, Cuenta cuentaOrigenSeleccionada) throws DestinatarioNoVerificadoERRORenCNSException {
	    TransferenciaDTO transferencia = new TransferenciaDTO();
	    Titularidad titularidad = response.getTitularidad();
        transferencia.setTitular(titularidad.getNombreTitular());

        if (STRING_VACIO.equals(transferencia.getTitular().trim())) {
            LOGGER.info(ERROR_EN_BANELCO_CONSULTA_TIT_CAMPOS_VACIOS);
            throw new DestinatarioNoVerificadoERRORenCNSException();
        }

        transferencia.setCuit(titularidad.getCuits());
		if (!ISBANStringUtils.validarCVU(titularidad.getCtaDestino().getNumeroCBU())) {
			transferencia.setBancoDestino(titularidad.getNombreBanco());
        } else {
        	transferencia.setBancoDestino("-");
        }
        transferencia.setCuentaDestinoBanelco(titularidad.getCtaDestino().getNumero());
        TipoCuenta tipoCuentaOrigen = TipoCuenta.fromCodigo(cuentaOrigenSeleccionada.getTipoCuenta());
        if (tipoCuentaOrigen.equals(TipoCuenta.CUENTA_UNICA)) {
            tipoCuentaOrigen = TransferenciaUtil.matchearTipoCuentaDestino(monedaSeleccionada);
            transferencia.setTipoDeCuentaToBanelco(TipoCuentaBanelco
                    .buscarPorCodigoTrfcci(matchearCodigoTipoCuentaConTipoCuentaBanelco(tipoCuentaOrigen)));
        } else {
            transferencia.setTipoDeCuentaToBanelco(TipoCuentaBanelco
                    .buscarPorCodigoTrfcci(matchearCodigoTipoCuentaConTipoCuentaBanelco(tipoCuentaOrigen)));
        }
        String moneda = titularidad.getCtaDestino().getMoneda().getCodigo().equals("1")
                ? DivisaEnum.PESO.getMoneda() : DivisaEnum.DOLAR.getMoneda();
        transferencia.setMoneda(DivisaEnum.fromMonedaString(moneda));
        transferencia.setCbuCuenta(titularidad.getCtaDestino().getNumeroCBU());
        transferencia.setTipoCuentaDestino(obtenerTipoCuentaPorMonedaYporCuenta(moneda,
                titularidad.getCtaDestino().getTipo().getCodigo()));
        transferencia.setTipoDeCuentaFromBanelco(TipoCuentaBanelco.buscarPorCodigoTrfcci(titularidad.getCtaDestino()
                .getTipo().getCodigo().concat(titularidad.getCtaDestino().getMoneda().getCodigo())));
        transferencia.setBancoReceptor(titularidad.getFiidBanco());
        transferencia.setFiid(titularidad.getFiidOrigenLink());
        transferencia.setUser("");
        transferencia.setAlias("");
  
        return transferencia;
    }

	/**
     * Obtener tipo cuenta por moneda y codigo de cuenta en Alias.
     *
     * @param moneda
     *            the moneda
     * @param codigoCuenta
     *            the codigo cuenta
     * @return the tipo cuenta
     */
    public TipoCuenta obtenerTipoCuentaPorMonedaYporCuenta(String moneda, String codigoCuenta) {
        if (moneda.equalsIgnoreCase(DivisaEnum.PESO.getMoneda())) {
            if (codigoCuenta.equals("0")) {
                return TipoCuenta.CUENTA_CORRIENTE_PESOS;
            } else {
                return TipoCuenta.CAJA_AHORRO_PESOS;
            }
        } else {
            if (codigoCuenta.equals("0")) {
                return TipoCuenta.CUENTA_CORRIENTE_DOLARES;
            } else {
                return TipoCuenta.CAJA_AHORRO_DOLARES;
            }
        }
    }

	/**
	 * Matchear codigo tipo cuenta con tipo cuenta banelco.
	 *
	 * @param tipoCuentaOrigen
	 *            the tipo cuenta origen
	 * @return the string
	 */
	private String matchearCodigoTipoCuentaConTipoCuentaBanelco(TipoCuenta tipoCuentaOrigen) {
        String codigoTipoCuentaBanelco = "";
        if (tipoCuentaOrigen.getCodigo().equals(TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo())) {
            codigoTipoCuentaBanelco = "01";
        } else if (tipoCuentaOrigen.getCodigo().equals(TipoCuenta.CAJA_AHORRO_PESOS.getCodigo())) {
            codigoTipoCuentaBanelco = "11";
        } else if (tipoCuentaOrigen.getCodigo().equals(TipoCuenta.CUENTA_CORRIENTE_DOLARES.getCodigo())) {
            codigoTipoCuentaBanelco = "02";
        } else if (tipoCuentaOrigen.getCodigo().equals(TipoCuenta.CAJA_AHORRO_DOLARES.getCodigo())) {
            codigoTipoCuentaBanelco = "12";
        } else if (tipoCuentaOrigen.getCodigo().equals(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo())) {
            codigoTipoCuentaBanelco = "01";
        } else if (tipoCuentaOrigen.getCodigo().equals(TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo())) {
            codigoTipoCuentaBanelco = "02";
        }
        return codigoTipoCuentaBanelco;
    }

    /**
	 * Generar request ws consultar titularidad.
	 *
	 * @param userAgent
	 *            the user agent
	 * @param cuenta
	 *            the cuenta
	 * @param cvuDestino
	 *            the cvu destino
	 * @return the consultar datos titularidad
	 */
    private ConsultarDatosTitularidad generarRequestWsConsultarTitularidad(String userAgent, Cuenta cuenta, String cvuDestino) {
        Cliente cliente = sesionCliente.getCliente();
        String tipoDoc = TipoDocumentoDebinWSEnum.getTipoDocumentoDebinWS(sesionCliente.getCliente().getTipoDocumento()).getCodigoNumero();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNroDocumento(cliente.getDni());
        usuarioDTO.setTipoDocumento(tipoDoc);
        TerminalDTO terminalDTO = new TerminalDTO(userAgent, sesionCliente.getIpCliente());
        terminalDTO.setCanal("E");
        CuentaDTO cuentaDTO = new CuentaDTO(obtenerCodigoTipoMonedaPorTipoDeCuenta(cuenta.getTipoCuentaEnum()),
                                    obtenerNroCuenta(cuenta), 
                                    cuenta.getCbu(), 
                                    obtenerCodigoTipoCuenta(cuenta.getTipoCuentaEnum()));
        return new ConsultarDatosTitularidad(usuarioDTO, terminalDTO, cuentaDTO, cvuDestino);
    }
    
    /**
     * Obtener codigo en base al tipo de cuenta.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the string
     */
    private String obtenerCodigoTipoCuenta(TipoCuenta tipoCuenta) {
        if (tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_PESOS)
                || tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_DOLARES)
                || tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_PESOS)
                || tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_DOLARES)) {
            return TIPO_CUENTA_DTO_CODIGO_CC;
        }
        if (tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_PESOS) || tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_DOLARES)
                || tipoCuenta.equals(TipoCuenta.CUENTA_UNICA)) {
            return TIPO_CUENTA_DTO_CODIGO_CA;
        }
        return StringUtils.EMPTY;
    }

    /**
     * Obtener nro cuenta.
     *
     * @param cuenta
     *            the cuenta
     * @return the string
     */
    private String obtenerNroCuenta(Cuenta cuenta) {
        return cuenta.getNroSucursal().concat("00").concat(cuenta.getNroCuentaProducto());
    }
    
    /**
     * Obtener codigo tipo moneda en base a la cuenta.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the string
     */
    private String obtenerCodigoTipoMonedaPorTipoDeCuenta(TipoCuenta tipoCuenta) {
        if (tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_PESOS) || tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_PESOS)
                || tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_PESOS)) {
            return MONEDA_DTO_CODIGO_PESOS;
        }
        if (tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_DOLARES) || tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_DOLARES)
                || tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_DOLARES)) {
            return MONEDA_DTO_CODIGO_USD;
        }
        return StringUtils.EMPTY;
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO#
	 * generarComprobanteTransferencia(ar.com.santanderrio.obp.servicios.
	 * transferencias.entities.Transferencia)
	 */
	@Override
	public Reporte generarComprobanteTransferencia(TransferenciaDTO transferencia) throws DAOException {

		Reporte reporte = new Reporte();
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy · HH:mm");
		try {
			jasperReport = (JasperReport) JRLoader.loadObject(fileJasper.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(LOGO_MARCA_AGUA, logoMarcaDeAgua.getFile().getPath());
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(DESTINATARIO, ISBANStringUtils.convertirStringToCamelcase(transferencia.getTitular()));
			parameters.put(IMPORTE, transferencia.getMoneda().getSimbolo()
					+ ISBANStringUtils.formatearSaldo(transferencia.getImporte()));

			JRBeanCollectionDataSource dataSource = generarDatosDetalleTransferencia(transferencia);
			parameters.put(DETALLE_LIST_DATASOURCE, dataSource);
			Date fechaDeOperacion = transferencia.getFechaDeOperacion();
			parameters.put(FECHA_COMPROBANTE, dateFormatter.format(fechaDeOperacion));
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());

			parameters.put(MENSAJE_CONSERVAR_COMPROBANTE,
					legalDao.obtenerLegal(CodigoMensajeConstantes.LEGALES_CONSERVAR_COMPROBANTE));

			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			reporte.setNombre("Comprobante_Transferencia_" + transferencia.getNumeroComprobante() + PDF_EXTENSION);
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
		return reporte;
	}

	/**
	 * Generar datos detalle transferencia.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @return the JR bean collection data source
	 */
	private JRBeanCollectionDataSource generarDatosDetalleTransferencia(TransferenciaDTO transferencia) {

		List<ElementoDetalleReporte> dataCollection = new ArrayList<ElementoDetalleReporte>();
		Cuenta cuentaOrigen = transferencia.getCuentaOrigen();
		String tipoCuentaTexto = obtenerLabelTipoCuenta(cuentaOrigen.getTipoCuentaEnum());
		String cuentaDeOrigen = formatearNumeroCuenta(cuentaOrigen.getNroSucursal(),
				cuentaOrigen.getNroCuentaProducto());
		dataCollection.add(new ElementoDetalleReporte(CUENTA_DE_ORIGEN_INDEX, CUENTA_DE_ORIGEN_LABEL, cuentaDeOrigen,
				tipoCuentaTexto));
		if (transferencia.isHaciaOtroBanco()) {
			dataCollection.add(
					new ElementoDetalleReporte(DESTINO_INDEX, DESTINO_LABEL, transferencia.getCbuCuenta(), CBU_LABEL));
		} else {
			tipoCuentaTexto = obtenerLabelTipoCuenta(transferencia.getTipoCuentaDestino());
			dataCollection.add(new ElementoDetalleReporte(DESTINO_INDEX, DESTINO_LABEL,
					transferencia.getNumeroCuentaDestino().toString(), tipoCuentaTexto));
		}
		String cuit = ISBANStringUtils.formatearCuil(transferencia.getCuit());
		if (cuit != null && !cuit.trim().isEmpty()) {
			dataCollection.add(new ElementoDetalleReporte(CUIT_INDEX, CUIT_LABEL, cuit));
		}
		if (transferencia.isHaciaOtroBanco() && transferencia.isTransferenciaInmediata()) {
			String bancoDestino = ISBANStringUtils.convertirStringToCamelcase(transferencia.getBancoDestino());
			dataCollection.add(new ElementoDetalleReporte(BANCO_INDEX, BANCO_LABEL, bancoDestino));
		}
		Date fechaProgramada = transferencia.getFechaProgramada();
		String fechaEjecucion = null;
		if (fechaProgramada == null) {
			fechaEjecucion = HOY_STRING;
		} else {
			fechaEjecucion = fechaEjecucionFormatter.format(fechaProgramada);
		}
		dataCollection
				.add(new ElementoDetalleReporte(FECHA_DE_EJECUCION_INDEX, FECHA_DE_EJECUCION_LABEL, fechaEjecucion));
		String plazoAcreditacion = null;
		PlazoAcreditacion plazoAcreditacionEnum = transferencia.getPlazoAcreditacion();
		if (plazoAcreditacionEnum != null) {
			plazoAcreditacion = plazoAcreditacionEnum.getDescripcion();
		} else {
			if (transferencia.isTransferenciaInmediata()) {
				plazoAcreditacion = PlazoAcreditacion.INMEDIATO.getDescripcion();
			} else {
				plazoAcreditacion = PlazoAcreditacion.PLAZO_24_HS.getDescripcion();
			}
		}
		dataCollection.add(new ElementoDetalleReporte(PLAZO_DE_ACREDITACION_INDEX, PLAZO_DE_ACREDITACION_LABEL,
				plazoAcreditacion));
		ConceptoTransferenciaEnum concepto = transferencia.getConcepto();
		if (concepto != null) {
			dataCollection.add(new ElementoDetalleReporte(CONCEPTO_INDEX, CONCEPTO_LABEL, concepto.getDescripcion()));
		}
		String descripcion = transferencia.getDescripcionAdicional();
		if (descripcion != null) {
			dataCollection.add(new ElementoDetalleReporte(DESCRIPCION_INDEX, DESCRIPCION_LABEL, descripcion));
		}
		String email = transferencia.getEmail();
		if (email != null && !email.trim().isEmpty()) {
			dataCollection.add(new ElementoDetalleReporte(EMAIL_DESTINATARIO_INDEX, EMAIL_DESTINATARIO_LABEL, email));
		}
		String nroComprobante = transferencia.getNumeroComprobante();
		dataCollection
				.add(new ElementoDetalleReporte(NUMERO_COMPROBANTE_INDEX, NUMERO_COMPROBANTE_LABEL, nroComprobante));
		Collections.sort(dataCollection);
		return new JRBeanCollectionDataSource(dataCollection);
	}

	/**
	 * Obtener label tipo cuenta.
	 *
	 * @param tipoCuentaEnum
	 *            the tipo cuenta enum
	 * @return the string
	 */
	private String obtenerLabelTipoCuenta(TipoCuenta tipoCuentaEnum) {
		if (tipoCuentaEnum == null) {
			throw new ISBANRuntimeException(ERROR_AL_GENERAR_PDF + ". Tipo de cuenta es null");
		}
		String tipoCuentaTexto = null;
		switch (tipoCuentaEnum) {
		case CUENTA_UNICA:
		case CUENTA_UNICA_DOLARES:
		case CUENTA_UNICA_PESOS:
			tipoCuentaTexto = TipoCuenta.CUENTA_UNICA.getDescripcion();
			break;
		case CAJA_AHORRO_PESOS:
		case CAJA_AHORRO_DOLARES:
		case CUENTA_CORRIENTE_PESOS:
		case CUENTA_CORRIENTE_DOLARES:
			tipoCuentaTexto = tipoCuentaEnum.getDescripcion();
			break;
		default:
			throw new ISBANRuntimeException(
					ERROR_AL_GENERAR_PDF + ". Tipo de cuenta no válido: " + tipoCuentaEnum.getDescripcion());
		}
		return tipoCuentaTexto;
	}

	/**
	 * Formatear numero cuenta.
	 *
	 * @param nroSucursal
	 *            the nro sucursal
	 * @param nroCuentaProducto
	 *            the nro cuenta producto
	 * @return the string
	 */
	private String formatearNumeroCuenta(String nroSucursal, String nroCuentaProducto) {
		String sucursal = ISBANStringUtils.formatearSucursal(nroSucursal);
		String numeroCuenta = ISBANStringUtils.formatearNumeroCuenta(nroCuentaProducto);
		return new StringBuilder().append(sucursal).append(GUION_MEDIO).append(numeroCuenta).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO#
	 * conexionCNSTITICBU(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, ar.com.santanderrio.obp.servicios.transferencias.entities.
	 * RequestCNSTITCBU)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO#
	 * conexionCNSTITICBU(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, ar.com.santanderrio.obp.servicios.transferencias.entities.
	 * RequestCNSTITCBU)
	 */
	public IatxResponse conexionCNSTITICBU(Cliente cliente, RequestCNSTITCBU requestCNSTITCBU) throws DAOException {
		try {
			IatxRequest iatxRequest = new IatxRequest(servicioCnstitcbu, versionCnstitcbu);
			IatxRequestData iatxRequestData = new IatxRequestData(cliente);
			iatxRequestData.addBodyValue(requestCNSTITCBU.getTipoCuenta());
			iatxRequestData.addBodyValue(requestCNSTITCBU.getNroSucursal());
			iatxRequestData.addBodyValue(requestCNSTITCBU.getNroCuenta());
			iatxRequestData.addBodyValue(requestCNSTITCBU.getCbuDestino());
			iatxRequestData.addBodyValue(requestCNSTITCBU.getNroTarjeta());
			iatxRequestData.addBodyValue(requestCNSTITCBU.getDireccionIP());
			if(CuentasBancaPrivadaUtil.isCuentaBP(requestCNSTITCBU.getNroSucursal())) {
				iatxRequestData.setCanalTipo(canalBP);
				iatxRequestData.setSubCanalTipo(subCanalBP);
			}
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			return iatxResponse;
		} catch (IatxException e) {
			if (StringUtils.contains(e.getMessage(), TransferenciaUtil.TIMEOUT_EXCEPTION)) {
				LOGGER.error(e.getMessage(), e);
				throw new TimeOutException(e.getMessage());
			} else {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e);
			}
		}
	}

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.transferencias.dao.impl.TransferenciaDAO#limpiarCache(
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    @CacheEvict(value = CacheConstants.Names.CACHE_CNS_PAGO_HAB, key = "#cliente.nup")
    public void limpiarCache(Cliente cliente) {
        LOGGER.info("Se limpia la cache: {}", cliente.getNup());
    }
}

	/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.manager.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteEstadoEnum;
import ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexCanalesBO;
import ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexConsultasBO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ComprobanteComexDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConceptoOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConceptoPorTipoOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaBancosInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaBancosOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaMonedaOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaPaisesOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaConceptosPorTipoInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexConsultasManager;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ArchivoTransferenciaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.BancoOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ComboView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ComprobanteInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConfiguracionAdjuntarArchivosOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaBancosInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaBancosOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaConceptoOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaMonedasView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.CuentaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DatosBeneficiarioOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DatosPersonalesView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DatosTransferenciaOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DomicilioView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.MotivoComexView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarTransferenciaComexView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ReporteView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.TransferenciaComexOutView;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.perfil.bo.CambioDomicilioBO;
import ar.com.santanderrio.obp.servicios.perfil.dto.CambioDomicilioDTO;

/**
 * The Class ComexConsultasManagerImpl.
 * 
 * @author B040619
 */
@Component
public class ComexConsultasManagerImpl implements ComexConsultasManager {

	/** The Constant MSJ_ERROR_DESCARGA_PDF. */
	private static final String MSJ_ERROR_DESCARGA_PDF = "Se ha producido un error, intenta nuevamente mas tarde.";

	/** The Constant UBICACION. */
	private static final String UBICACION = "Normativa.pdf";

	/** The Constant NOMBRE_ARCHIVO. */
	private static final String NOMBRE_ARCHIVO = "Normativa";

	/** The Constant CODIGO_GBP. */
	private static final String CODIGO_GBP = "GBP";

	/** The Constant CODIGO_EUR. */
	private static final String CODIGO_EUR = "EUR";

	/** The Constant CODIGO_USD. */
	private static final String CODIGO_USD = "USD";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComexConsultasManagerImpl.class);

	/** The Constant LOGGER. */
	private static final String CUENTA = "Cuenta";

	/** The Constant VACIO. */
	private static final String VACIO = "";

	/** The Constant ESPACIO. */
	private static final String ESPACIO = " ";

	/** The Constant DELIMITADORES. */
	private static final char[] DELIMITADORES = { '.', ' ', '-', '(', ')', '/' };

	/** The Constant TRANSFERENCIAS_PERSONALES. */
	private static final String TRANSFERENCIAS_PERSONALES = "I07";

	/** The Constant TRANSFERENCIAS_CORRIENTES. */
	private static final String TRANSFERENCIAS_CORRIENTES = "I08";

	/** The Constant PAGO_ANTICIPADO_IMPORTACION. */
	private static final String PAGO_ANTICIPADO_IMPORTACION = "B05";

	/** The Constant PAGO_DIFERIDO_IMPORTACION. */
	private static final String PAGO_DIFERIDO_IMPORTACION = "B06";

	/** The Constant PAGO_VISTA_IMPORTACION. */
	private static final String PAGO_VISTA_IMPORTACION = "B07";

	private static final String SERVICIOS_DE_GOBIERNO = "S25";

	/** The Constant TURISMO_VIAJES. */
	private static final String TURISMO_VIAJES = "S06";

	/** The Constant DEPOSITOS_RESIDENTES_EXTERIOR. */
	private static final String DEPOSITOS_RESIDENTES_EXTERIOR = "A07";
	
	private static final String OPERACIONES_ENTRE_RESIDENTES = "A17";

	/** The alias cuenta BO. */
	@Autowired
	private AliasCuentaBO aliasCuentaBO;

	/** The Constant MOTIVOS. */
	private static final String[] MOTIVOS = { "S06 TURISMO Y VIAJES", "A07 DEPOSITOS DE RESIDENTES EN EL EXTERIOR",
			"I08 OTRAS TRANSFERENCIAS CORRIENTES", "B05 PAGOS ANTICIPADOS DE IMPORTACIONES DE BIENES",
			"B06 PAGOS DIFERIDOS DE IMPORTACIONES DE BIENES", "B07 PAGO VISTA DE IMPORTACIONES DE BIENES",
			"I07 TRANSFERENCIAS PERSONALES", "A17 TRANSFERENCIAS AL EXTERIOR ASOCIADAS A OPERACIONES ENTRE RESIDENTES" };

	/** The Constant EXP_REG_ESPACIO. */
	private static final String EXP_REG_ESPACIO = "\\s";

	/** The Constant DOMICILIO_PRIMARIO. */
	private static final String DOMICILIO_PRIMARIO = "PRI";

	/** The Constant BARRA. */
	private static final String BARRA = "/";

	/** The Constant SEPARADOR_NOMBRE_DOCUMENTACION. */
	private static final String SEPARADOR_NOMBRE_DOCUMENTACION = "/";

	/** The Constant ERROR_AL_OBTENER_RESUMEN_CUENTA. */
	private static final String ERROR_AL_OBTENER_RESUMEN_CUENTA = "Error al traer resumen de cuenta";

	private static final String COMBO_DESCR_PAIS_AR = "Argentina";

	private static final String COMBO_COD_PAIS_AR = "AR";

	/** The Constant PRODUCTO. */
	private static final String PRODUCTO = "12";

	/** The Constant TIPO_CONCEPTO. */
	private static final String TIPO_CONCEPTO = "5";

	/** The Cuenta BO. */
	@Autowired
	protected CuentaBO cuentaBO;

	/** The tipos cuentas validas. */
	@Value("#{'${TIPO.CUENTAS.VALIDAS.COMEX}'.split('\\|')}")
	private List<String> tiposCuentasValidas;

	/** The max file size. */
	@Value("${COMEX.MAX.FILESIZE}")
	private String maxFileSize;

	/** The max file size. */
	@Value("${COMEX.TIPO.ARCHIVOS.ADJ}")
	private String tiposDeArchivosPermitidos;

	/** The cantidad max de archivos posibles ajuntos. */
	@Value("${COMEX.MAX.FILES.ATACHED}")
	private String cantidadMaxDeArchivosPosiblesAjuntos;

	/** The habilitar adjuntar activo. */
	@Value("${COMEX.HABILITAR.DOCUMENTACION.ADJUNTA:true}")
	private Boolean habilitarAdjuntarActivo;

	/** The tipos conceptos validos para adjuntar. */
	@Value("#{'${COMEX.CONCEPTOS.DOCUMENTACION.ADJUNTA}'.split('\\|')}")
	private List<String> conceptosValidosAdjuntar;

	@Value("#{'${COMEX.TIPO.CONCEPTOS.ARGENTINA}'.split('\\|')}")
	private List<String> habilitarArgentina;

	/** The comex operaciones hora desde. */
	@Value("${COMEX.OPERACIONES.HORA.DESDE}")
	private String comexOperacionesHoraDesde;

	/** The comex operaciones hora hasta. */
	@Value("${COMEX.OPERACIONES.HORA.HASTA}")
	private String comexOperacionesHoraHasta;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The ComexConsultas BO. */
	@Autowired
	private ComexConsultasBO comexConsultasBO;

	/** The ComexCanales BO. */
	@Autowired
	private ComexCanalesBO comexCanalesBO;

	/** The CambioDomicilio BO. */
	@Autowired
	protected CambioDomicilioBO cambioDomicilioBO;

	/** The legal bo. */
	@Autowired
	private LegalBO legalBO;

	/** The selectores BO. */
	@Autowired
	private DatosSelectoresBO selectoresBO;

	/** The ModuloPermiso BO. */
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The ReporteComprobantePDF BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	/**
	 * obtiene los datos personales.
	 *
	 * @return datosPersonales
	 */
	private Respuesta<DatosPersonalesView> obtenerDatosPersonales() {
		DatosPersonalesView datosPersonales = new DatosPersonalesView();
		datosPersonales.setNombre(WordUtils.capitalizeFully(sesionCliente.getCliente().getNombre()));
		datosPersonales.setPrimerApellido(WordUtils.capitalizeFully(sesionCliente.getCliente().getApellido1()));
		// Marca especial que se puso para el módulo de Cierre de Sucursales M en
		// posicion 18.
		if (sesionCliente.getCliente().getIsCuentaMigrada()) {
			if (sesionCliente.getCliente().getApellido2().length() > 17) {
				datosPersonales.setSegundoApellido(sesionCliente.getCliente().getApellido2().substring(0, 17).trim());
			} else {
				datosPersonales.setSegundoApellido(StringUtils.EMPTY);
			}
		} else {
			datosPersonales.setSegundoApellido(sesionCliente.getCliente().getApellido2());
		}
		if (sesionCliente.getCliente().getNroDocCnsDocXNup() != null) {
			datosPersonales.setCuit(sesionCliente.getCliente().getNroDocCnsDocXNup());
		} else {
			DetalleDocumentoDTO detalleDocumentoDTO = null;
			try {
				detalleDocumentoDTO = aliasCuentaBO.obtenerDocumentoValidoDTO(sesionCliente.getCliente());
			} catch (DAOException e) {
				return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
			}
			if (detalleDocumentoDTO.getTieneError() && detalleDocumentoDTO.getNroDocumento() == null) {
				return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
			} else {
				datosPersonales.setCuit(detalleDocumentoDTO.getNroDocumento());
			}
		}
		DomicilioView domicilio = new DomicilioView();
		if (sesionParametros.getDomiciliosCliente() == null) {
			Respuesta<List<CambioDomicilioDTO>> domiciliosSesion = cambioDomicilioBO.consultarDomiciliosRegistrados();
			if (!domiciliosSesion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
			} else {
				sesionParametros.setDomiciliosCliente(domiciliosSesion.getRespuesta());
			}
		}
		List<CambioDomicilioDTO> domicilios = sesionParametros.getDomiciliosCliente();
		sesionParametros.setDomiciliosCliente(domicilios);
		for (CambioDomicilioDTO domicilioPrimario : domicilios) {
			if (DOMICILIO_PRIMARIO.equals(domicilioPrimario.getTipoDomicilio())) {
				domicilio.setCalle(WordUtils.capitalizeFully(domicilioPrimario.getCalle().replaceAll("\\s+$", VACIO)));
				domicilio.setCodigoPostal(domicilioPrimario.getCodigoPostal().replaceAll(EXP_REG_ESPACIO, VACIO));
				domicilio.setLocalidad(
						WordUtils.capitalizeFully(domicilioPrimario.getLocalidad().replaceAll("\\s+$", VACIO)));
				domicilio.setNumero(ISBANStringUtils.sacarCerosYBlancosIzq(domicilioPrimario.getApt()));
				datosPersonales.setTelefono(domicilioPrimario.getCaracteristica()
						.concat(domicilioPrimario.getNumeroTelefono()).replaceAll(EXP_REG_ESPACIO, VACIO));
				datosPersonales.setPrefijoTelefono(domicilioPrimario.getPrefijo().replaceAll("\\s+$", VACIO));
			}
		}
		CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
		if (!ClienteEstadoEnum.NO_SUSCRIPTO.getCodigo().equals(credencialesMya.getClienteEstado())) {
			datosPersonales.setCelular(credencialesMya.getCelular());
			datosPersonales.setPrefijoCelular(credencialesMya.getCodigoArea());
			datosPersonales.setMailPrimario(credencialesMya.getEmail());
			datosPersonales.setMailSecundario(credencialesMya.getEmailSecundario());
		} else {
			datosPersonales.setCelular(VACIO);
			datosPersonales.setPrefijoCelular(VACIO);
			datosPersonales.setMailPrimario(VACIO);
			datosPersonales.setMailSecundario(VACIO);
		}

		datosPersonales.setDomicilio(domicilio);
		return respuestaFactory.crearRespuestaOk(datosPersonales);
	}

	/**
	 * Obtiene la lista de motivos.
	 *
	 * @return lista motivosComex
	 */
	private Respuesta<List<MotivoComexView>> obtenerMotivosComex() {
		ConsultaConceptosPorTipoInEntity consultaConceptosPorTipoInEntity = new ConsultaConceptosPorTipoInEntity();
		consultaConceptosPorTipoInEntity.setProducto(PRODUCTO);
		consultaConceptosPorTipoInEntity.setTipoConcepto(TIPO_CONCEPTO);
		Respuesta<List<ConceptoPorTipoOutDTO>> respuestaBO = comexCanalesBO
				.consultaConceptoPorTipo(consultaConceptosPorTipoInEntity);
		if (!respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
		}
		List<MotivoComexView> motivosView = createMotivosViewSelector(respuestaBO.getRespuesta());
		return respuestaFactory.crearRespuestaOk(motivosView);
	}

	/**
	 * Obtiene ConceptoPorTipoOutDTO segun codigoConcepto.
	 *
	 * @param codigoConcepto the codigo concepto
	 * @return conceptoPorTipo
	 */
	private Respuesta<ConceptoPorTipoOutDTO> obtenerMotivoPorCodigoConcepto(String codigoConcepto) {
		ConsultaConceptosPorTipoInEntity consultaConceptosPorTipoInEntity = new ConsultaConceptosPorTipoInEntity();
		consultaConceptosPorTipoInEntity.setProducto(PRODUCTO);
		consultaConceptosPorTipoInEntity.setTipoConcepto(TIPO_CONCEPTO);
		Respuesta<List<ConceptoPorTipoOutDTO>> respuestaBO = comexCanalesBO
				.consultaConceptoPorTipo(consultaConceptosPorTipoInEntity);
		if (!respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
		}
		List<ConceptoPorTipoOutDTO> listConceptos = respuestaBO.getRespuesta();
		ConceptoPorTipoOutDTO conceptoPorTipo = new ConceptoPorTipoOutDTO();
		for (ConceptoPorTipoOutDTO concepto : listConceptos) {
			if (codigoConcepto.equals(String.valueOf(concepto.getIdConcepto()))) {
				conceptoPorTipo = concepto;
			}
		}

		return respuestaFactory.crearRespuestaOk(conceptoPorTipo);
	}

	/**
	 * Obtiene la ayuda para los motivos y los agrega al motivo segun el codigo de
	 * BCRA.
	 *
	 * @param conceptosPorTipo the listConceptosPorTipo
	 * @return motivosView
	 */
	private List<MotivoComexView> createMotivosViewSelector(List<ConceptoPorTipoOutDTO> conceptosPorTipo) {
		List<MotivoComexView> motivosView = new ArrayList<MotivoComexView>();
		for (ConceptoPorTipoOutDTO conceptoTipo : conceptosPorTipo) {
			String idMotivo = conceptoTipo.getConcepto().substring(0, 3);
			MotivoComexView motivoComex = new MotivoComexView();
			motivoComex.setId(idMotivo);
			buscarMotivoDescripcion(idMotivo, motivoComex);
			motivoComex.setIdConcepto(String.valueOf(conceptoTipo.getIdConcepto()));
			motivoComex.setDescripcion(WordUtils.capitalizeFully(conceptoTipo.getConcepto().substring(4)));
			if (habilitarArgentina.contains(idMotivo)) {
				motivoComex.setHabilitarArgentina(Boolean.TRUE);
			} else {
				motivoComex.setHabilitarArgentina(Boolean.FALSE);
			}
			motivosView.add(motivoComex);
		}
		return motivosView;
	}

	/**
	 * Creates the motivos view ayuda.
	 *
	 * @return the list
	 */
	private List<MotivoComexView> createMotivosViewAyuda() {
		List<MotivoComexView> motivosView = new ArrayList<MotivoComexView>();
		for (int i = 0; i < MOTIVOS.length; i++) {
			String idMotivo = MOTIVOS[i].substring(0, 3);
			MotivoComexView motivoComex = new MotivoComexView();
			motivoComex.setDescripcion(WordUtils.capitalizeFully(MOTIVOS[i].substring(4)));
			if (TRANSFERENCIAS_PERSONALES.equalsIgnoreCase(idMotivo)) {
				motivoComex.setTemplate(obtenerLegales(CodigoMensajeConstantes.COMEX_AYUDA_TRANSFERENCIAS_PERSONALES));
			} else if (TRANSFERENCIAS_CORRIENTES.equalsIgnoreCase(idMotivo)) {
				motivoComex.setTemplate(obtenerLegales(CodigoMensajeConstantes.COMEX_AYUDA_TRANSFERENCIAS_CORRIENTE));
			} else if (PAGO_ANTICIPADO_IMPORTACION.equalsIgnoreCase(idMotivo)) {
				motivoComex
						.setTemplate(obtenerLegales(CodigoMensajeConstantes.COMEX_AYUDA_PAGOS_ANTICIPADOS_IMPORTACION));
			} else if (PAGO_DIFERIDO_IMPORTACION.equalsIgnoreCase(idMotivo)) {
				motivoComex
						.setTemplate(obtenerLegales(CodigoMensajeConstantes.COMEX_AYUDA_PAGOS_DIFERIDOS_IMPORTACION));
			} else if (PAGO_VISTA_IMPORTACION.equalsIgnoreCase(idMotivo)) {
				motivoComex.setTemplate(obtenerLegales(CodigoMensajeConstantes.COMEX_AYUDA_PAGOS_VISTA_IMPORTACION));
			} else if (SERVICIOS_DE_GOBIERNO.equals(idMotivo)) {
				motivoComex.setMensaje(mensajeBO
						.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_SERVICIOS_DE_GOBIERNO).getMensaje());
			} else if (TURISMO_VIAJES.equalsIgnoreCase(idMotivo)) {
				motivoComex.setTemplate(obtenerLegales(CodigoMensajeConstantes.COMEX_AYUDA_TURISMO_VIAJES));
			} else if (DEPOSITOS_RESIDENTES_EXTERIOR.equalsIgnoreCase(idMotivo)) {
				motivoComex.setTemplate(
						obtenerLegales(CodigoMensajeConstantes.COMEX_AYUDA_DEPOSITOS_RESIDENTES_EN_EL_EXTERIOR));
			}
			motivosView.add(motivoComex);
		}
		return motivosView;
	}

	public void buscarMotivoDescripcion(String idMotivo, MotivoComexView motivoComex) {
		if (TRANSFERENCIAS_PERSONALES.equalsIgnoreCase(idMotivo)) {
			motivoComex.setMensaje(mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_TRANSFERENCIAS_PERSONALES).getMensaje());
		} else if (TRANSFERENCIAS_CORRIENTES.equalsIgnoreCase(idMotivo)) {
			motivoComex.setMensaje(
					mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_OTRAS_TRANSFERENCIAS_CORRIENTES)
							.getMensaje());
		} else if (PAGO_ANTICIPADO_IMPORTACION.equalsIgnoreCase(idMotivo)) {
			motivoComex.setTemplate(obtenerLegales(CodigoMensajeConstantes.COMEX_AYUDA_PAGOS_ANTICIPADOS_IMPORTACION));
		} else if (PAGO_DIFERIDO_IMPORTACION.equalsIgnoreCase(idMotivo)) {
			motivoComex.setTemplate(obtenerLegales(CodigoMensajeConstantes.COMEX_AYUDA_PAGOS_DIFERIDOS_IMPORTACION));
		} else if (PAGO_VISTA_IMPORTACION.equalsIgnoreCase(idMotivo)) {
			motivoComex.setTemplate(obtenerLegales(CodigoMensajeConstantes.COMEX_AYUDA_PAGOS_VISTA_IMPORTACION));
		} else if (SERVICIOS_DE_GOBIERNO.equals(idMotivo)) {
			motivoComex.setMensaje(mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_SERVICIOS_DE_GOBIERNO).getMensaje());
		} else if (TURISMO_VIAJES.equalsIgnoreCase(idMotivo)) {
			motivoComex.setMensaje(
					mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_TURISMO_VIAJES).getMensaje());
		} else if (DEPOSITOS_RESIDENTES_EXTERIOR.equalsIgnoreCase(idMotivo)) {
			motivoComex.setMensaje(
					mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_DEPOSITO_RESIDENTES_EN_EXTERIOR)
							.getMensaje());
		} else if (OPERACIONES_ENTRE_RESIDENTES.equalsIgnoreCase(idMotivo)) {
			//TODO: Código de mensaje.
			motivoComex.setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_OPERACIONES_ENTRE_RESIDENTES)
					.getMensaje());
		}
	}

	/**
	 * Obtiene los datos de transferencia.
	 *
	 * @return datosTransferencia
	 * @throws BusinessException the business exception
	 */
	private DatosTransferenciaOutView obtenerDatosTransferencia() throws BusinessException {
		DatosTransferenciaOutView datosTransferencia = new DatosTransferenciaOutView();
		List<ComboView> comboGastoACargo = new ArrayList<ComboView>();
		List<Opcion> opcionesGasto;
		opcionesGasto = selectoresBO.obtenerGastoACargo();
		for (Opcion opcionGasto : opcionesGasto) {
			ComboView gasto = new ComboView();
			gasto.setDescripcion(opcionGasto.getOpcion());
			gasto.setId(opcionGasto.getId());
			comboGastoACargo.add(gasto);
		}

		List<ComboView> comboCodBancario = new ArrayList<ComboView>();
		List<Opcion> opcionesCodBancario;
		opcionesCodBancario = selectoresBO.obtenerCodigoBancario();
		for (Opcion opcionCodBancario : opcionesCodBancario) {
			ComboView codBancario = new ComboView();
			codBancario.setDescripcion(opcionCodBancario.getOpcion());
			codBancario.setId(opcionCodBancario.getId());
			comboCodBancario.add(codBancario);
		}

		List<CuentaView> cuentasViewPesos = new ArrayList<CuentaView>();
		List<CuentaView> cuentasViewDolares = new ArrayList<CuentaView>();
		obtenerCuentasOrigenComex(cuentasViewDolares, cuentasViewPesos);

		datosTransferencia.setComboCodigoBancario(comboCodBancario);
		datosTransferencia.setComboGastosACargo(comboGastoACargo);
		List<CuentaView> cuentasUnificadas = new ArrayList<CuentaView>();
		cuentasUnificadas.addAll(cuentasViewPesos);
		cuentasUnificadas.addAll(cuentasViewDolares);
		datosTransferencia.setCuentas(obtenerListaCuentas(cuentasUnificadas));
		datosTransferencia.setCuentasEnDolares(obtenerListaCuentas(cuentasViewDolares));
		return datosTransferencia;
	}

	private List<CuentaView> obtenerListaCuentas(List<CuentaView> cuentas) {
		Collections.sort(cuentas, new Comparator<CuentaView>() {
			Map<TipoCuenta, Integer> mapaPrioridadesCuentas = obtenerMapaPrioridadesCuentas();

			@Override
			public int compare(CuentaView cuenta1, CuentaView cuenta2) {
				return mapaPrioridadesCuentas
						.get(TipoCuenta.fromDescripcionConMoneda(cuenta1.getDescripcionTipoCuenta()))
						.compareTo(mapaPrioridadesCuentas
								.get(TipoCuenta.fromDescripcionConMoneda(cuenta2.getDescripcionTipoCuenta())));
			}
		});
		return cuentas;
	}

	private Map<TipoCuenta, Integer> obtenerMapaPrioridadesCuentas() {
		Map<TipoCuenta, Integer> mapaPrioridadesCuentas = new HashMap<TipoCuenta, Integer>();
		mapaPrioridadesCuentas.put(TipoCuenta.CUENTA_UNICA_PESOS, 1);
		mapaPrioridadesCuentas.put(TipoCuenta.CUENTA_UNICA_DOLARES, 2);
		mapaPrioridadesCuentas.put(TipoCuenta.CUENTA_CORRIENTE_PESOS, 3);
		mapaPrioridadesCuentas.put(TipoCuenta.CAJA_AHORRO_PESOS, 4);
		mapaPrioridadesCuentas.put(TipoCuenta.CAJA_AHORRO_DOLARES, 5);
		return mapaPrioridadesCuentas;
	}

	/**
	 * Obtiene las cuentas en pesos del Cliente.
	 *
	 * @param cuentasViewDolares the cuentas view dolares
	 * @param cuentasViewPesos   the cuentas view pesos
	 * @throws BusinessException the business exception
	 */
	private void obtenerCuentasOrigenComex(List<CuentaView> cuentasViewDolares, List<CuentaView> cuentasViewPesos)
			throws BusinessException {
		List<ResumenDetalleCuenta> cuentasValidas = sesionParametros.getCuentasComex();
		if (cuentasValidas == null || cuentasValidas.isEmpty()) {
			cuentasValidas = obtenerCuentasValidas(sesionCliente.getCliente());
            if (sesionCliente.getCliente().getClienteBancaPrivada()) {
				cargarCuentasBP(cuentasValidas);
            }
			sesionParametros.setCuentasComex(cuentasValidas);
		}

		for (int i = 0; i < cuentasValidas.size(); i++) {
			boolean isSaldoPesosNegativo = false;
			boolean isSaldoDolaresNegativo = false;
			int tipoCuenta = Integer.valueOf(cuentasValidas.get(i).getTipoCuenta());
			CuentaView cuentaView = new CuentaView();
			cuentaView.setId(i);
			cuentaView.setAlias(cuentasValidas.get(i).getAlias());
			cuentaView.setDescripcionTipoCuenta(TipoCuenta.fromCodigo(tipoCuenta).getDescripcionConMoneda());
			cuentaView.setNumero(CUENTA.concat(ESPACIO).concat(ISBANStringUtils
					.formatearSucursal(cuentasValidas.get(i).getNroSucursal()).concat(ISBANStringUtils.GUION_STRING)
					.concat(ISBANStringUtils.formatearNumeroCuenta(cuentasValidas.get(i).getNroCuentaProducto()))));
			if (TipoCuenta.CUENTA_UNICA.getCodigo() == tipoCuenta) {
				cuentaView.setSaldoPesos(ISBANStringUtils
						.formatearConComaYDosDecimales(String.valueOf(cuentasValidas.get(i).getSaldoPesos())));
				if (cuentasValidas.get(i).getSaldoDolares().equals(BigDecimal.ZERO)) {
					cuentaView.setSaldoDolares(ISBANStringUtils.formatearConComaYDosDecimales(
							String.valueOf(cuentasValidas.get(i).getSaldoCajaAhorroDolares())));
				} else {
					cuentaView.setSaldoDolares(ISBANStringUtils
							.formatearConComaYDosDecimales(String.valueOf(cuentasValidas.get(i).getSaldoDolares())));
				}
				cuentaView.setDescripcionTipoCuenta(TipoCuenta.CUENTA_UNICA_PESOS.getDescripcionConMoneda());
				cuentasViewPesos.add(cuentaView);
				cuentasViewDolares.add(cuentaView);
			} else if (TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo() == tipoCuenta
					|| TipoCuenta.CAJA_AHORRO_PESOS.getCodigo() == tipoCuenta) {
				cuentaView.setSaldoPesos(ISBANStringUtils
						.formatearConComaYDosDecimales(String.valueOf(cuentasValidas.get(i).getSaldoPesos())));
				cuentasViewPesos.add(cuentaView);
			} else if (TipoCuenta.CUENTA_CORRIENTE_DOLARES.getCodigo() == tipoCuenta
					|| TipoCuenta.CAJA_AHORRO_DOLARES.getCodigo() == tipoCuenta) {
				cuentaView.setSaldoDolares(ISBANStringUtils
						.formatearConComaYDosDecimales(String.valueOf(cuentasValidas.get(i).getSaldoDolares())));
				cuentasViewDolares.add(cuentaView);
			}
			if (cuentaView.getSaldoPesos() != null && cuentasValidas.get(i).getSaldoPesos().signum() < 0) {
				cuentaView.setIsSaldoPesosNegativo(!isSaldoPesosNegativo);
			} else {
				cuentaView.setIsSaldoPesosNegativo(isSaldoPesosNegativo);
			}

			if (cuentaView.getSaldoDolares() != null && cuentasValidas.get(i).getSaldoDolares().signum() < 0) {
				cuentaView.setIsSaldoDolaresNegativo(!isSaldoPesosNegativo);
			} else if (cuentaView.getSaldoDolares() != null
					&& cuentasValidas.get(i).getSaldoCajaAhorroDolares().signum() < 0) {
				cuentaView.setIsSaldoDolaresNegativo(!isSaldoDolaresNegativo);
			} else {
				cuentaView.setIsSaldoDolaresNegativo(isSaldoDolaresNegativo);
			}
			if (TipoCuenta.CUENTA_UNICA.getCodigo() == tipoCuenta) {
				CuentaView cuentaDolares = cuentaView.clone();
				cuentaDolares.setDescripcionTipoCuenta(TipoCuenta.CUENTA_UNICA_DOLARES.getDescripcionConMoneda());
				cuentaDolares.setIsSaldoPesosNegativo(null);
				cuentasViewDolares.set(cuentasViewDolares.size() - 1, cuentaDolares);
			}
		}
	}

	/**
	 * Cargar cuentas BP.
	 *
	 * @param cuentas the cuentas
	 * @throws BusinessException the business exception
	 */
	private void cargarCuentasBP(List<ResumenDetalleCuenta> cuentas) throws BusinessException {
		try {
			Respuesta<List<ResumenDetalleCuenta>> respuesta = 
					comexConsultasBO.getCuentasSaldoOrdenadasBP(sesionCliente.getCliente());
			cuentas.addAll(respuesta.getRespuesta());
		} catch (SQLException e) {
			LOGGER.info("Error obteniendo cuentas BP");
		} catch (ImporteConvertException e) {
			LOGGER.info("Error obteniendo cuentas BP");
		}
	}

	/**
	 * Obtiene los datos del beneficiario.
	 *
	 * @return datosBenefiaciario
	 */
	@Override
	public Respuesta<DatosBeneficiarioOutView> obtenerDatosBeneficiarios() {

		DatosBeneficiarioOutView retornoView = new DatosBeneficiarioOutView();

		Respuesta<List<ConsultaPaisesOutDTO>> respuestaBO = comexConsultasBO.consultaPaises();
		if (!respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)
//				|| comboVinculo.isEmpty()
		) {
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
		}

		List<ComboView> comboPaises = createComboPaises(respuestaBO.getRespuesta());
		retornoView.setComboPaises(comboPaises);

		return respuestaFactory.crearRespuestaOk(DatosBeneficiarioOutView.class, retornoView);
	}

	/**
	 * Obtiene los datos del beneficiario.
	 *
	 * @return datosBenefiaciario
	 */
	@Override
	public Respuesta<DatosBeneficiarioOutView> obtenerDatosBeneficiariosA07() {

		DatosBeneficiarioOutView retornoView = new DatosBeneficiarioOutView();

		Respuesta<List<ConsultaPaisesOutDTO>> respuestaBO = comexConsultasBO.consultaPaises();
		if (!respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)
//				|| comboVinculo.isEmpty()
		) {
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
		}

		ComboView comboAR = new ComboView();
		List<ComboView> comboPaises = new ArrayList<ComboView>();
		comboAR.setDescripcion(COMBO_DESCR_PAIS_AR);
		comboAR.setId(COMBO_COD_PAIS_AR);
		comboPaises.add(comboAR);

		retornoView.setComboPaises(comboPaises);
		return respuestaFactory.crearRespuestaOk(DatosBeneficiarioOutView.class, retornoView);
	}

	/**
	 * Genera el comboView de paises.
	 *
	 * @param consultaPaises the listPaises
	 * @return comboPaises
	 */
	private List<ComboView> createComboPaises(List<ConsultaPaisesOutDTO> consultaPaises) {
		List<ComboView> comboPaises = new ArrayList<ComboView>();
		for (ConsultaPaisesOutDTO pais : consultaPaises) {
			ComboView comboPais = new ComboView();
			comboPais.setId(pais.getCodigoPais());
			comboPais.setDescripcion(WordUtils.capitalizeFully(pais.getDescripcionPais(), DELIMITADORES));
			comboPaises.add(comboPais);
		}
		return comboPaises;
	}

	/**
	 * Obtener cuentas validas para transferir.
	 *
	 * @param clienteOrigen the cliente origen
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	private List<ResumenDetalleCuenta> obtenerCuentasValidas(Cliente clienteOrigen) throws BusinessException {
		List<Cuenta> cuentas = clienteOrigen.getCuentas();
		List<Cuenta> cuentasFiltradas = new ArrayList<Cuenta>();
		for (Cuenta cuenta : cuentas) {
			TipoCuenta tipoCuentaEnum = cuenta.getTipoCuentaEnum();
			if (tipoCuentaEnum == null) {
				continue;
			}

			if (tiposCuentasValidas.contains(String.valueOf(tipoCuentaEnum.getCodigo()))) {
				cuentasFiltradas.add(cuenta);
			}
		}
		if (cuentasFiltradas.isEmpty()) {
			return new ArrayList<ResumenDetalleCuenta>();
		}

		List<ResumenDetalleCuenta> cuentasValidas = cuentaBO.getCuentasSaldo(clienteOrigen, cuentasFiltradas)
				.getRespuesta();

		if (cuentasValidas == null || cuentasValidas.isEmpty()) {
			throw new BusinessException(ERROR_AL_OBTENER_RESUMEN_CUENTA);
		}

		return cuentasValidas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.manager.
	 * TransferenciaComexManager#obtenerDatosComex()
	 */
	@Override
	public Respuesta<TransferenciaComexOutView> obtenerDatosComex() {

		// Verifica si puede operarse o presentar mensaje
		ModuloPermiso permisoOperaciones = moduloPermisoBO
				.obtenerModuloPermisoPorAccion(AccionController.TRANSFERENCIAS_EXTERIOR_OPERACIONES);
		try {
			if (permisoOperaciones.muestraMensaje() && (!validarLunesAViernes() || !validarHorario())) {
				LOGGER.info("Operacion Transferencia al exterior deshabilitada por fin de semana o rango horario");
				estadisticaManager.add(EstadisticasConstants.SOLICITAR_TRANSFERENCIA_EXTERIOR_COMEX,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaErrorPersonalizado(
						TransferenciaComexOutView.class, MessageFormat.format(permisoOperaciones.getMensaje(),
								comexOperacionesHoraDesde, comexOperacionesHoraHasta),
						TipoError.FUERA_DE_HORARIO.getDescripcion());
			} else if (permisoOperaciones.isModuloOculto()) {
				LOGGER.info("Operacion Transferencia al exterior deshabilitada");
				estadisticaManager.add(EstadisticasConstants.SOLICITAR_TRANSFERENCIA_EXTERIOR_COMEX,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
			}
		} catch (ParseException e) {
			LOGGER.info("Error validando fuera de horario", e);
			estadisticaManager.add(EstadisticasConstants.SOLICITAR_TRANSFERENCIA_EXTERIOR_COMEX,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
		}

		TransferenciaComexOutView transferenciaComexOutView = new TransferenciaComexOutView();

		DatosTransferenciaOutView datosTransferencia;
		try {
			datosTransferencia = obtenerDatosTransferencia();
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			estadisticaManager.add(EstadisticasConstants.SOLICITAR_TRANSFERENCIA_EXTERIOR_COMEX,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
		}

		if (datosTransferencia.getCuentas().isEmpty()) {
			estadisticaManager.add(EstadisticasConstants.SOLICITAR_TRANSFERENCIA_EXTERIOR_COMEX,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.SIN_CUENTAS_VALIDAS,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_SIN_CUENTAS);
		}

		if (datosTransferencia.getComboCodigoBancario().isEmpty()
				|| datosTransferencia.getComboGastosACargo().isEmpty()) {
			estadisticaManager.add(EstadisticasConstants.SOLICITAR_TRANSFERENCIA_EXTERIOR_COMEX,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
		}
		transferenciaComexOutView.setDatosTransferencia(datosTransferencia);

		Respuesta<List<MotivoComexView>> respuestaMotivos = obtenerMotivosComex();
		if (!respuestaMotivos.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			estadisticaManager.add(EstadisticasConstants.SOLICITAR_TRANSFERENCIA_EXTERIOR_COMEX,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
		}
		// Se pidio que el selector de motivos fuera dinamico y la ayuda estatica.
		transferenciaComexOutView.setMotivosComex(respuestaMotivos.getRespuesta());
		transferenciaComexOutView.setMotivosAyuda(createMotivosViewAyuda());
		Respuesta<DatosPersonalesView> respuestaDatosPersonales = obtenerDatosPersonales();
		if (!respuestaDatosPersonales.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			estadisticaManager.add(EstadisticasConstants.SOLICITAR_TRANSFERENCIA_EXTERIOR_COMEX,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
		}
		transferenciaComexOutView.setDatosPersonales(respuestaDatosPersonales.getRespuesta());

		Respuesta<DatosBeneficiarioOutView> respuestaDatosBeneficiario = null;
		// if
		// (transferenciaComexOutView.getMotivosComex().get(0).getId().endsWith(DEPOSITOS_RESIDENTES_EXTERIOR))
		// {
		// respuestaDatosBeneficiario = obtenerDatosBeneficiariosA07();
		// }else {
		respuestaDatosBeneficiario = obtenerDatosBeneficiarios();
		// }

		if (!respuestaDatosBeneficiario.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			estadisticaManager.add(EstadisticasConstants.SOLICITAR_TRANSFERENCIA_EXTERIOR_COMEX,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
		}
		transferenciaComexOutView.setDatosBeneficiario(respuestaDatosBeneficiario.getRespuesta());

//		Se comenta el codigo ya que se obtienen los TyC en el endpoint /obtenerTyC
//		Respuesta<String> respuestaLegales = legalBO.buscarLegal(CodigoMensajeConstantes.COMEX_LEGALES_TYC);
//		LOGGER.info("Obteniendo Legales");
//				
// 		Si el legal no se obtuvo ok, respuesta error
//		if (!respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
//			LOGGER.error("El legal no fue obtenido correctamente");
//			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
//		}
//
//		transferenciaComexOutView.setLegales(respuestaLegales.getRespuesta());

		Respuesta<List<ConsultaMonedaOutDTO>> respuestaBOConsultaMonedas = comexConsultasBO.consultaMonedas();
		if (respuestaBOConsultaMonedas.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			comexConsultasBO.limpiarMonedas();
			estadisticaManager.add(EstadisticasConstants.SOLICITAR_TRANSFERENCIA_EXTERIOR_COMEX,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
		} else {
			transferenciaComexOutView
					.setConsultaMonedasList(obtenerListaMonedas(respuestaBOConsultaMonedas.getRespuesta()));
		}
		estadisticaManager.add(EstadisticasConstants.SOLICITAR_TRANSFERENCIA_EXTERIOR_COMEX,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(TransferenciaComexOutView.class, transferenciaComexOutView);
	}

	private List<ConsultaMonedasView> obtenerListaMonedas(List<ConsultaMonedaOutDTO> respuesta) {
		ordenarMonedas(respuesta);
		List<ConsultaMonedasView> listaMonedasView = new ArrayList<ConsultaMonedasView>();
		for (ConsultaMonedaOutDTO monedaDTO : respuesta) {
			listaMonedasView.add(new ConsultaMonedasView(monedaDTO));
		}
		return listaMonedasView;
	}

	private void ordenarMonedas(List<ConsultaMonedaOutDTO> respuesta) {
		Collections.sort(respuesta, new Comparator<ConsultaMonedaOutDTO>() {
			@Override
			public int compare(ConsultaMonedaOutDTO moneda1, ConsultaMonedaOutDTO moneda2) {
				if (StringUtils.equals(CODIGO_USD, moneda1.getCodBCRAMoneda())) {
					return -1;
				} else if (StringUtils.equals(CODIGO_USD, moneda2.getCodBCRAMoneda())) {
					return 1;
				}
				if (StringUtils.equals(CODIGO_EUR, moneda1.getCodBCRAMoneda())) {
					return -1;
				} else if (StringUtils.equals(CODIGO_EUR, moneda2.getCodBCRAMoneda())) {
					return 1;
				}
				if (StringUtils.equals(CODIGO_GBP, moneda1.getCodBCRAMoneda())) {
					return -1;
				} else if (StringUtils.equals(CODIGO_GBP, moneda2.getCodBCRAMoneda())) {
					return 1;
				}
				return moneda1.getDescripcionMoneda().compareTo(moneda2.getDescripcionMoneda());
			}
		});
	}

	/**
	 * Informa si el dia actual esta en el rango de lunes a viernes
	 *
	 * @return true, if successful
	 */
	private boolean validarLunesAViernes() {
		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY);
	}

	/**
	 * Validar horario.
	 *
	 * @return true, if successful
	 * @throws ParseException the parse exception
	 */
	private boolean validarHorario() throws ParseException {
		if (!ISBANStringUtils.isNullOrBlank(comexOperacionesHoraDesde)
				&& !ISBANStringUtils.isNullOrBlank(comexOperacionesHoraHasta)) {
			return ISBANStringUtils.compararHoras(comexOperacionesHoraDesde, comexOperacionesHoraHasta);
		}
		return false;
	}

	/**
	 * Obtener legales.
	 *
	 * @param codigoMensaje the codigoMensaje
	 * @return the string
	 */
	private String obtenerLegales(String codigoMensaje) {
		try {
			return legalBO.obtenerLegal(codigoMensaje);
		} catch (DAOException e) {
			LOGGER.error("Falla al obtener legales o motivos de comex");
			return StringUtils.EMPTY;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.manager.
	 * TransferenciaComexManager#obtenerBancos()
	 */
	@Override
	public Respuesta<ConsultaBancosOutView> obtenerBancos(ConsultaBancosInView consultaBancosInView) {
		ConsultaBancosInDTO consultaBancosInDTO = new ConsultaBancosInDTO();
		consultaBancosInDTO.setCodigoBancario(consultaBancosInView.getCodigo());
		Respuesta<List<ConsultaBancosOutDTO>> respuestaBO = comexConsultasBO.consultaBancos(consultaBancosInDTO);
		if (!respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_CONSULTA_BANCOS);
		}
		ConsultaBancosOutView bancosView = new ConsultaBancosOutView();
		bancosView.setBancos(createConsultaBancosView(respuestaBO.getRespuesta()));
		estadisticaManager.add(EstadisticasConstants.CONSULTA_BANCOS_COMEX,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(ConsultaBancosOutView.class, bancosView);
	}

	/**
	 * Crean la listaView de bancos.
	 *
	 * @param consultaBancosOutDTO the bancos
	 * @return listBancos
	 */
	private List<BancoOutView> createConsultaBancosView(List<ConsultaBancosOutDTO> consultaBancosOutDTO) {
		List<BancoOutView> bancosView = new ArrayList<BancoOutView>();
		for (ConsultaBancosOutDTO banco : consultaBancosOutDTO) {
			BancoOutView bancoView = new BancoOutView();
			if (StringUtils.isNotBlank(banco.getSwiftBanco())) {
				bancoView.setCodigo(banco.getSwiftBanco());
			} else if (StringUtils.isNotBlank(banco.getAbaBanco())) {
				bancoView.setCodigo(banco.getAbaBanco().replace(BARRA, VACIO));
			}
			bancoView.setNombre(WordUtils.capitalizeFully(banco.getNombreBanco(), DELIMITADORES));
			bancoView.setPais(WordUtils.capitalizeFully(banco.getPaisBanco(), DELIMITADORES));
			bancoView.setLocalidad(WordUtils.capitalizeFully(banco.getLocalidadBanco(), DELIMITADORES));
			bancosView.add(bancoView);
		}
		return bancosView;
	}

	/**
	 * Obtiene la descripcion de gasto a cargo segun el idGasto.
	 *
	 * @param idGasto the id gasto
	 * @return gastoACargo
	 */
	private String obtenerGastoACargo(String idGasto) {
		List<Opcion> opcionesGasto;
		opcionesGasto = selectoresBO.obtenerGastoACargo();
		String retorno = VACIO;
		for (Opcion opcionGasto : opcionesGasto) {
			if (idGasto.equals(opcionGasto.getId())) {
				retorno = opcionGasto.getOpcion();
			}
		}
		return retorno;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.manager.
	 * TransferenciaComexManager#descargarComprobante()
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobante(ComprobanteInView documentacion) {
		ComprobanteComexDTO comprobante = sesionParametros.getComprobanteComex();
		Respuesta<ConceptoPorTipoOutDTO> respuestaMotivo = obtenerMotivoPorCodigoConcepto(comprobante.getIdConcepto());
		if (!respuestaMotivo.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			Estadistica estadistica = new Estadistica();
			estadistica.setCodigoTransaccion(EstadisticasConstants.DESCARGA_COMPROBANTE_TRANSFERENCIA_AL_EXTERIOR);
			estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			estadisticaManager.add(estadistica);
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
		}
		StringBuilder documentacionBuilder = new StringBuilder();
		for (String nombre : comprobante.getArchivos()) {
			documentacionBuilder.append(SEPARADOR_NOMBRE_DOCUMENTACION);
			documentacionBuilder.append(nombre);
		}

		String motivo = WordUtils.capitalizeFully(respuestaMotivo.getRespuesta().getConcepto());
		String conceptoCodigo = WordUtils.capitalizeFully(respuestaMotivo.getRespuesta().getConcepto().substring(0, 3));

		comprobante.setNombreDocumentacion(
				documentacionBuilder.toString().replaceFirst(SEPARADOR_NOMBRE_DOCUMENTACION, StringUtils.EMPTY));
		comprobante.setMotivo(motivo);
		comprobante.setLegales(sesionParametros.getConsultaConceptoOutView().getTerminosYCondiciones());
		comprobante.setGastosACargo(obtenerGastoACargo(comprobante.getIdGastosACargo()));
		comprobante.setConceptoCodigo(conceptoCodigo);

		Respuesta<Reporte> reporteRespuesta = comexConsultasBO.obtenerComprobante(comprobante);
		if (EstadoRespuesta.ERROR.equals(reporteRespuesta.getEstadoRespuesta())){
			estadisticaManager.add(EstadisticasConstants.DESCARGA_COMPROBANTE_TRANSFERENCIA_AL_EXTERIOR, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(ReporteView.class, reporteRespuesta.getItemsMensajeRespuesta());
		}
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
		respuestaView.setRespuesta(reporteView);

		Estadistica estadistica = new Estadistica();
		estadistica.setCodigoTransaccion(EstadisticasConstants.DESCARGA_COMPROBANTE_TRANSFERENCIA_AL_EXTERIOR);
		estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		estadisticaManager.add(estadistica);
		return respuestaView;
	}

	/**
	 * Crear adjuntar archivos in dto.
	 *
	 * @param archivoTransferenciaView the archivo transferencia view
	 * @return the adjuntar archivos DTO
	 */
	private AdjuntarArchivosDTO crearAdjuntarArchivosInDto(ArchivoTransferenciaView archivoTransferenciaView) {
		AdjuntarArchivosDTO adjuntarArchivosInDto = new AdjuntarArchivosDTO();
		adjuntarArchivosInDto.setNroTransferencia(archivoTransferenciaView.getNroTransferencia());
		adjuntarArchivosInDto.setArchivo(archivoTransferenciaView.getArchivo());
		return adjuntarArchivosInDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.manager.
	 * ComexConsultasManager#verificarArchivo(ar.com.santanderrio.obp.servicios.
	 * comex.transfext.view.ReporteView)
	 */
	@Override
	public Respuesta<Boolean> verificarArchivo(ReporteView archivo) {
		ArchivoTransferenciaView archivoTransferenciaView = new ArchivoTransferenciaView();
		archivoTransferenciaView.setArchivo(archivo);
		AdjuntarArchivosDTO archivoAdjuntosDto = crearAdjuntarArchivosInDto(archivoTransferenciaView);
		return this.comexConsultasBO.verificarArchivo(archivoAdjuntosDto);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.manager.
	 * ComexConsultasManager#obtenerConfiguracionAdjuntarArchivo()
	 */
	@Override
	public Respuesta<ConfiguracionAdjuntarArchivosOutView> obtenerConfiguracionAdjuntarArchivo() {
		ConfiguracionAdjuntarArchivosOutView configuracionAdjuntarArchivosOutView = new ConfiguracionAdjuntarArchivosOutView();
		configuracionAdjuntarArchivosOutView
				.setCantidadMaxDeArchivosPosiblesAjuntos(this.cantidadMaxDeArchivosPosiblesAjuntos);
		configuracionAdjuntarArchivosOutView.setTamMaximoArchivo(this.maxFileSize);
		configuracionAdjuntarArchivosOutView.setTiposDeArchivosSoportados(this.tiposDeArchivosPermitidos);
		configuracionAdjuntarArchivosOutView.setAdjuntarActivo(habilitarAdjuntarActivo);
		configuracionAdjuntarArchivosOutView.setConceptosValidos(conceptosValidosAdjuntar);
		return this.respuestaFactory.crearRespuestaOk(ConfiguracionAdjuntarArchivosOutView.class,
				configuracionAdjuntarArchivosOutView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.manager.
	 * ComexConsultasManager#vaciarPaises()
	 */
	@Override
	public Respuesta<Boolean> vaciarPaises() {
		return comexConsultasBO.limpiarPaises();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.manager.
	 * ComexConsultasManager#vaciarMonedas()
	 */
	@Override
	public Respuesta<Boolean> vaciarMonedas() {
		return comexConsultasBO.limpiarMonedas();
	}

	/**
	 * Obtiene consulta concepto.
	 *
	 * @return consulta concepto
	 */
	public Respuesta<ConsultaConceptoOutView> obtenerConsultaConcepto(int idConcepto) {
		LOGGER.info("Obteniendo Consulta Concepto");
		ConsultaConceptoOutView consultaConceptoOutView = new ConsultaConceptoOutView();
		Respuesta<ConceptoOutDTO> respuestaConsultaConcepto = obtenerConsultaConceptoComex(idConcepto);

		// Si el legal no se obtuvo ok, respuesta error
		if (!respuestaConsultaConcepto.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			LOGGER.error("El legal no fue obtenido correctamente");
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
		}

		consultaConceptoOutView
				.setTerminosYCondiciones(respuestaConsultaConcepto.getRespuesta().getDeclaracionJurada());
		sesionParametros.setConsultaConceptoOutView(consultaConceptoOutView);
		return respuestaFactory.crearRespuestaOk(consultaConceptoOutView);
	}

	/**
	 * Obtiene consulta concepto comex.
	 *
	 * @return the concepto out DTO
	 */
	private Respuesta<ConceptoOutDTO> obtenerConsultaConceptoComex(int idConcepto) {
		Respuesta<ConceptoOutDTO> respuestaBO = comexCanalesBO.consultaConcepto(idConcepto);
		if (!respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError(VACIO, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
		}

		return respuestaBO;
	}

	@Override
	public Respuesta<String> obtenerMensajeVinculante() {
		Mensaje mensaje = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.AYUDA_VINCULANTE_CONFIGURACION_TRANSFEXT);
		if (StringUtils.isNotBlank(mensaje.getMensaje())) {
			return respuestaFactory.crearRespuestaOk(mensaje.getMensaje());
		}
		throw new RobotException(
				"Error al consultar el mensaje " + CodigoMensajeConstantes.AYUDA_VINCULANTE_CONFIGURACION_TRANSFEXT);
	}

	@Override
	public Respuesta<ReporteView> descargarNormativa() {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		reporte = reporteBO.obtenerArchivoPDF(UBICACION, NOMBRE_ARCHIVO);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
		} else {
			reporte.getItemsMensajeRespuesta().get(0).setMensaje(MSJ_ERROR_DESCARGA_PDF);
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
		}
		return respuestaView;
	}

	@Override
	public Respuesta<Void> estadisticaVinculante(ProcesarTransferenciaComexView view) {
		if (view.getVinculante() != null) {
			estadisticaManager.add(
					view.getVinculante() ? EstadisticasConstants.TRANSFEXT_VINCULANTE_SI
							: EstadisticasConstants.TRANSFEXT_VINCULANTE_NO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		estadisticaManager.add(EstadisticasConstants.TRANSFEXT_ACCESO_CONFIRMACION, EstadisticasConstants.CODIGO_ESTADISTICAS_OK); 
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	@Override
	public Respuesta<Void> estadisticaDatosBeneficiario() {
		estadisticaManager.add(EstadisticasConstants.TRANSFEXT_GUARDAR_DATOS_BENEFICIARIO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	@Override
	public Respuesta<Void> estadisticaDatosTransferencia() {
		estadisticaManager.add(EstadisticasConstants.TRANSFEXT_GUARDAR_DATOS_TRANSFERENCIA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	@Override
	public Respuesta<Void> estadisticaIngresoAdjuntar() {
		estadisticaManager.add(EstadisticasConstants.TRANSFEXT_ADJUNTAR_ARCHIVOS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	@Override
	public Respuesta<Void> estadisticaVerComprobante() {
		estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_TRANSFERENCIA_AL_EXTERIOR,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

}
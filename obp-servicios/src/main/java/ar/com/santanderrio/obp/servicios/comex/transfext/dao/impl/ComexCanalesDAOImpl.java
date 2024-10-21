package ar.com.santanderrio.obp.servicios.comex.transfext.dao.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import javax.xml.bind.JAXBElement;
import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ICanalesTrfService;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ICanalesTrfServiceBorrarDocBaseFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ICanalesTrfServiceCargaDocBaseFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ICanalesTrfServiceConceptosPorTipoBaseFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ICanalesTrfServiceConsultaConceptoBaseFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ICanalesTrfServiceConsultaDetalleTrfOBPBaseFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ICanalesTrfServiceConsultaImagenTrfBaseFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ICanalesTrfServiceConsultaOperacionesBaseFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ICanalesTrfServiceProcesarTransferenciaOBPBaseFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.FirmaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.BorrarDocRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.CargaDocRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConceptosPorTipoRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaConceptoRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaDetalleTrfOBPRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaImagenTrfRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaOperacionesRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ProcesarTransferenciaOBPRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BorrarDocResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaDocResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConceptosPorTipoResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaConceptoResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfOBPResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaImagenTrfResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaOperacionesResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse;
import ar.com.santanderrio.obp.servicios.comex.transfext.common.TiposDocumentoComexEnum;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexCanalesDAO;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.AdjuntarArchivosInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaConceptoInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaConceptosPorTipoInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaDetalleTrfOBPInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaOperacionesInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ProcesarTransferenciaComexInEntity;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;

/**
 * The Class ComexCanalesDAOImpl.
 *
 * @author IT Resources
 */
@Component
public class ComexCanalesDAOImpl implements ComexCanalesDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComexCanalesDAOImpl.class);

	/** The ws canales Trf client. */
	@Autowired
	@Qualifier("comexCanalesGestor")
	private GestionarWS<ICanalesTrfService> wsCanalesTrfClient;

	/** The sign. */
	@Autowired
	private Sign sign;

	/** The app encoding. */
	@Value("${APP.ENCODING}")
	private String appEncoding;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;
	
	/** The max file size. */
	@Value("${TRANSFEXT.CANALES.CANT.MAX.OPERACIONES}")
	private Integer cantMaxConsultaOperaciones;

	/** The Constant JKS_COMEX_CANALES. */
	private static final String JKS_COMEX_CANALES = "TRANSFEXT.CANALES";

	/** The Constant FIRMA_DATOS_DENTRO. */
	private static final String FIRMA_DATOS_DENTRO_S = "S";

	/** The Constant FIRMA_FORMATO_B64. */
	private static final String FIRMA_FORMATO_B64 = "B64";

	/** The Constant CANAL. */
	private static final String CANAL = "04";

	/** The Constant PRODUCTO. */
	private static final String PRODUCTO = "12";

	/** The Constant TIPO_CONCEPTO. */
	private static final String TIPO_CONCEPTO = "5";

	/** The Constant INST_VENDIDO. */
	private static final String INST_VENDIDO = "02";

	/** The Constant INST_RECIBIDO. */
	private static final String INST_RECIBIDO = "07";
	
	/** The Constant TIPO_PERSONA_F. */
	private static final String TIPO_PERSONA_F = "F";

	/** The Constant TIPO_PERSONA_J. */
	private static final String TIPO_PERSONA_J = "J";

	/** The Constant TIPO_PERSONA_E. */
	private static final String TIPO_PERSONA_E= "E";

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexCanalesDAO#consultaDetalleTrf(ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaDetalleTrfOBPInEntity)
	 */
	@Override
	public ConsultaDetalleTrfOBPResponse consultaDetalleTrf(ConsultaDetalleTrfOBPInEntity consultaDetalleTrfOBPInEntity) throws DAOException {
		LOGGER.info("Consulta detalle trf comex");
		ICanalesTrfService services = null;
		ConsultaDetalleTrfOBPResponse response;
		try {
			ConsultaDetalleTrfOBPRequest request = createConsultaDetalleTrfOBPRequest(consultaDetalleTrfOBPInEntity);
			services = wsCanalesTrfClient.obtenerPort();
			response = services.consultaDetalleTrfOBP(request);
			LOGGER.info("Respuesta del WS: {}.", response);
			return response;
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} catch (WebServiceException e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} catch (ICanalesTrfServiceConsultaDetalleTrfOBPBaseFaultFaultFaultMessage e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} finally {
			wsCanalesTrfClient.liberarPort(services);
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexCanalesDAO#consultaConceptosPorTipo()
	 */
	@Override
	public ConceptosPorTipoResponse consultaConceptosPorTipo(ConsultaConceptosPorTipoInEntity consultaConceptosPorTipoInEntity) throws DAOException {
		LOGGER.info("Se consulta conceptos por tipo");
		ICanalesTrfService services = null;
		try {
			ConceptosPorTipoRequest request = createConceptoPorTipoRequest(consultaConceptosPorTipoInEntity);
			services = wsCanalesTrfClient.obtenerPort();
			ConceptosPorTipoResponse respConcepto = services.conceptosPorTipo(request);
			LOGGER.info("Respuesta del WS: {}.", respConcepto);
			return respConcepto;
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error en el WS ConceptosPorTipo.", e);
			throw new DAOException(e);
		} catch (WebServiceException e) {
			LOGGER.error("Error en el WS ConceptosPorTipo.", e);
			throw new DAOException(e);
		} catch (ICanalesTrfServiceConceptosPorTipoBaseFaultFaultFaultMessage e) {
			LOGGER.error("Error en el WS ConceptosPorTipo.", e);
			throw new DAOException(e);
		} finally {
			wsCanalesTrfClient.liberarPort(services);
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexCanalesDAO#consultaConceptos(ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaConceptoInEntity)
	 */
	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_LEGALES_COMEX })
	@Override
	public ConsultaConceptoResponse consultaConceptos(ConsultaConceptoInEntity consultaConceptoInEntity) throws DAOException {
		LOGGER.info("Se consulta conceptos (ayuda motivos comex)");
		ICanalesTrfService services = null;
		try {
			ConsultaConceptoRequest request = createConsultaConceptoRequest(consultaConceptoInEntity);
			services = wsCanalesTrfClient.obtenerPort();
			ConsultaConceptoResponse respConcepto = services.consultaConcepto(request);
			LOGGER.info("Respuesta del WS: {}.", respConcepto);
			return respConcepto;
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error en el WS ConsultaConceptos.", e);
			throw new DAOException(e);
		} catch (WebServiceException e) {
			LOGGER.error("Error en el WS ConsultaConceptos.", e);
			throw new DAOException(e);
		} catch (ICanalesTrfServiceConsultaConceptoBaseFaultFaultFaultMessage e) {
			LOGGER.error("Error en el WS ConsultaConceptos.", e);
			throw new DAOException(e);
		} finally {
			wsCanalesTrfClient.liberarPort(services);
		}
	}

	/**
	 * Creates the consulta detalle trf OBP request.
	 *
	 * @param consultaDetalleTrfOBPInEntity
	 *            the consulta detalle trf OBP in entity
	 * @return the consulta detalle trf OBP request
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ConsultaDetalleTrfOBPRequest createConsultaDetalleTrfOBPRequest(ConsultaDetalleTrfOBPInEntity consultaDetalleTrfOBPInEntity) throws UnsupportedEncodingException, DAOException {
		ConsultaDetalleTrfOBPRequest request = new ConsultaDetalleTrfOBPRequest();
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory factory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory();
		FirmaRequest firmaRequest = obtenerFirmaRequest();
		JAXBElement<Integer> nroTransferencia = factory.createConsultaDetalleTrfRequestNroTransferencia(consultaDetalleTrfOBPInEntity.getNroTransferencia());
		request.setNroTransferencia(nroTransferencia);
		request.setFirmaDatos(firmaRequest.getFirmaDatos());
		request.setFirmaDatosDentro(firmaRequest.getFirmaDatosDentro());
		request.setFirmaFormato(firmaRequest.getFirmaFormato());
		request.setFirmaHash(firmaRequest.getFirmaHash());
		return request;
	}

	/**
	 * Creates the concepto por tipo request.
	 *
	 * @return the conceptos por tipo request
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ConceptosPorTipoRequest createConceptoPorTipoRequest(ConsultaConceptosPorTipoInEntity consultaConceptosPorTipoInEntity) throws UnsupportedEncodingException, DAOException {
		ConceptosPorTipoRequest request = new ConceptosPorTipoRequest();
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory factory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory();
		FirmaRequest firmaRequest = obtenerFirmaRequest();
		request.setCanal(factory.createConceptosPorTipoRequestCanal(CANAL));
		request.setProducto(factory.createConceptosPorTipoRequestProducto(consultaConceptosPorTipoInEntity.getProducto()));
		request.setTipoConcepto(factory.createConceptosPorTipoRequestTipoConcepto(consultaConceptosPorTipoInEntity.getTipoConcepto()));
		request.setFirmaDatos(firmaRequest.getFirmaDatos());
		request.setFirmaDatosDentro(firmaRequest.getFirmaDatosDentro());
		request.setFirmaFormato(firmaRequest.getFirmaFormato());
		request.setFirmaHash(firmaRequest.getFirmaHash());
		return request;
	}

	/**
	 * Creates the consulta concepto request.
	 *
	 * @param consultaConceptoInEntity
	 *            the consulta concepto in entity
	 * @return the consulta concepto request
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ConsultaConceptoRequest createConsultaConceptoRequest(ConsultaConceptoInEntity consultaConceptoInEntity) throws UnsupportedEncodingException, DAOException {
		ConsultaConceptoRequest request = new ConsultaConceptoRequest();
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory factory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory();
		FirmaRequest firmaRequest = obtenerFirmaRequest();
		request.setCanal(factory.createConsultaConceptoRequestCanal((CANAL)));
		request.setIdConcepto(factory.createConsultaConceptoRequestIdConcepto(consultaConceptoInEntity.getIdConcepto()));
		request.setFirmaDatos(firmaRequest.getFirmaDatos());
		request.setFirmaDatosDentro(firmaRequest.getFirmaDatosDentro());
		request.setFirmaFormato(firmaRequest.getFirmaFormato());
		request.setFirmaHash(firmaRequest.getFirmaHash());
		request.setProducto(factory.createConsultaConceptoRequestProducto((Integer.parseInt(PRODUCTO))));
		return request;
	}

	/**
	 * Obtener firma request.
	 *
	 * @return the firma request
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private FirmaRequest obtenerFirmaRequest() throws UnsupportedEncodingException, DAOException {
		FirmaRequest firmaRequest = new FirmaRequest();
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.ObjectFactory factory = new ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.ObjectFactory();
		firmaRequest.setFirmaDatos(factory.createFirmaRequestFirmaDatos(obtenerFirmaDatosXML()));
		firmaRequest.setFirmaDatosDentro(factory.createFirmaRequestFirmaDatosDentro(FIRMA_DATOS_DENTRO_S));
		firmaRequest.setFirmaFormato(factory.createFirmaRequestFirmaFormato(FIRMA_FORMATO_B64));
		firmaRequest.setFirmaHash(factory.createFirmaRequestFirmaHash(obtenerFirma(obtenerFirmaDatosXML())));
		return firmaRequest;
	}

	/**
	 * Obtener firma.
	 *
	 * @param param
	 *            the param
	 * @return the string
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private String obtenerFirma(String param) throws UnsupportedEncodingException, DAOException {
		return new String(sign.buildB64Signature(param.getBytes(appEncoding), JKS_COMEX_CANALES, true));
	}

	/**
	 * Obtener firma datos XML.
	 *
	 * @return the string
	 */
	private String obtenerFirmaDatosXML() {
		org.dom4j.Document doc = org.dom4j.DocumentHelper.createDocument();
		org.dom4j.Element datos = doc.addElement("datos");
		datos.addElement("nup").setText(sesionCliente.getCliente().getNup());
		datos.addElement("tipodocumento").setText(sesionCliente.getCliente().getTipoDocumento());
		datos.addElement("numerodocumento").setText(sesionCliente.getCliente().getDni());
		return datos.asXML();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexCanalesDAO#consultaOperaciones(ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaOperacionesInEntity)
	 */
	@Override
	public ConsultaOperacionesResponse consultaOperaciones(ConsultaOperacionesInEntity consultaOperacionesInEntity) throws DAOException {
		LOGGER.info("Consulta operaciones comex");
		ICanalesTrfService services = null;
		ConsultaOperacionesResponse response;
		try {
			ConsultaOperacionesRequest request = createConsultaOperacionesRequest(consultaOperacionesInEntity);
			services = wsCanalesTrfClient.obtenerPort();
			response = services.consultaOperaciones(request);
			LOGGER.info("Respuesta del WS: {}.", response);
			return response;
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} catch (WebServiceException e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} catch (ICanalesTrfServiceConsultaOperacionesBaseFaultFaultFaultMessage e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} finally {
			wsCanalesTrfClient.liberarPort(services);
		}
	}

	/**
	 * Creates the consulta operaciones request.
	 *
	 * @param consultaOperacionesInEntity
	 *            the consulta operaciones in entity
	 * @return the consulta operaciones request
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ConsultaOperacionesRequest createConsultaOperacionesRequest(ConsultaOperacionesInEntity consultaOperacionesInEntity) throws UnsupportedEncodingException, DAOException {
		ConsultaOperacionesRequest request = new ConsultaOperacionesRequest();
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory factory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory();
		FirmaRequest firmaRequest = obtenerFirmaRequest();
		JAXBElement<Integer> cantidadRegistros = factory.createConsultaOperacionesRequestCantidadRegistros(cantMaxConsultaOperaciones);
		JAXBElement<String> estadoTransferencia = factory.createConsultaOperacionesRequestEstadoTransferencia(consultaOperacionesInEntity.getEstadoTransferencia());
		JAXBElement<String> fechaDesde = factory.createConsultaOperacionesRequestFechaDesde(consultaOperacionesInEntity.getFechaDesde());
		JAXBElement<String> fechaHasta = factory.createConsultaOperacionesRequestFechaHasta(consultaOperacionesInEntity.getFechaHasta());
		JAXBElement<String> moneda = factory.createConsultaOperacionesRequestMoneda(consultaOperacionesInEntity.getMoneda());
		JAXBElement<BigDecimal> montoDesde = factory.createConsultaOperacionesRequestMontoDesde(consultaOperacionesInEntity.getMontoDesde());
		JAXBElement<BigDecimal> montoHasta = factory.createConsultaOperacionesRequestMontoHasta(consultaOperacionesInEntity.getMontoHasta());
		JAXBElement<String> tipoDocCliente = factory.createConsultaOperacionesRequestTipoDocCliente(TiposDocumentoComexEnum.obtenerDescripcionPorCodigo(sesionCliente.getCliente().getTipoDocumento()));
		JAXBElement<String> setNupCliente = factory.createConsultaOperacionesRequestNupCliente(sesionCliente.getCliente().getNup());
		JAXBElement<String> setNroDocCliente = factory.createConsultaOperacionesRequestNroDocCliente(sesionCliente.getCliente().getDni());
		request.setCantidadRegistros(cantidadRegistros);
		request.setEstadoTransferencia(estadoTransferencia);
		request.setFechaDesde(fechaDesde);
		request.setFechaHasta(fechaHasta);
		request.setMoneda(moneda);
		request.setTipoDocCliente(tipoDocCliente);
		request.setNupCliente(setNupCliente);
		request.setNroDocCliente(setNroDocCliente);
		request.setMontoDesde(montoDesde);
		request.setMontoHasta(montoHasta);
		request.setFirmaDatos(firmaRequest.getFirmaDatos());
		request.setFirmaDatosDentro(firmaRequest.getFirmaDatosDentro());
		request.setFirmaFormato(firmaRequest.getFirmaFormato());
		request.setFirmaHash(firmaRequest.getFirmaHash());
		return request;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexCanalesDAO#procesarTransferenciaComex(ar.com.santanderrio.obp.servicios.comex.transfext.entities.ProcesarTransferenciaComexInEntity)
	 */
	@Override
	public ProcesarTransferenciaOBPResponse procesarTransferenciaComex(ProcesarTransferenciaComexInEntity procesarTransferenciaComexInEntity) throws DAOException {
		LOGGER.info("Procesar Transferencia Comex");
		ICanalesTrfService services = null;
		ProcesarTransferenciaOBPResponse response;
		try {
			ProcesarTransferenciaOBPRequest request = createProcesarTransferenciaOBPRequest(procesarTransferenciaComexInEntity);
			services = wsCanalesTrfClient.obtenerPort();
			response = services.procesarTransferenciaOBP(request);
			LOGGER.info("Respuesta del WS: {}.", response);
			return response;
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} catch (WebServiceException e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} catch (ICanalesTrfServiceProcesarTransferenciaOBPBaseFaultFaultFaultMessage e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} finally {
			wsCanalesTrfClient.liberarPort(services);
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexCanalesDAO#consultaImagenTrf(ar.com.santanderrio.obp.servicios.comex.transfext.entities.AdjuntarArchivosInEntity)
	 */
	@Override
	public ConsultaImagenTrfResponse consultaImagenTrf(AdjuntarArchivosInEntity adjuntarArchivosInEntity) throws DAOException {
		LOGGER.info("Obtener Archivo Comex");
		ICanalesTrfService services = null;
		ConsultaImagenTrfResponse response;
		try {
			ConsultaImagenTrfRequest request = createConsultaImagenTrfRequest(adjuntarArchivosInEntity);
			services = wsCanalesTrfClient.obtenerPort();
			response = services.consultaImagenTrf(request);
			LOGGER.info("Respuesta del WS: {}.", response);
		} catch (WebServiceException e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} catch (ICanalesTrfServiceConsultaImagenTrfBaseFaultFaultFaultMessage e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} finally {
			wsCanalesTrfClient.liberarPort(services);
		}

		return response;

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexCanalesDAO#adjuntarArchivos(ar.com.santanderrio.obp.servicios.comex.transfext.entities.AdjuntarArchivosInEntity)
	 */
	@Override
	public CargaDocResponse adjuntarArchivos(AdjuntarArchivosInEntity adjuntarArchivosInEntity) throws DAOException, UnsupportedEncodingException {
		LOGGER.info("Carga Documentacion Comex");
		ICanalesTrfService services = null;
		CargaDocResponse response;
		try {
			CargaDocRequest request = createCargaDocRequest(adjuntarArchivosInEntity);
			services = wsCanalesTrfClient.obtenerPort();
			response = services.cargaDoc(request);
			LOGGER.info("Respuesta del WS: {}.", response);
		} catch (WebServiceException e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} catch (ICanalesTrfServiceCargaDocBaseFaultFaultFaultMessage e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} finally {
			wsCanalesTrfClient.liberarPort(services);
		}

		return response;

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexCanalesDAO#borrarDoc(ar.com.santanderrio.obp.servicios.comex.transfext.entities.AdjuntarArchivosInEntity)
	 */
	@Override
	public BorrarDocResponse borrarDoc(AdjuntarArchivosInEntity adjuntarArchivosInEntity) throws DAOException {

		LOGGER.info("Borrar Documentacion Comex");
		ICanalesTrfService services = null;
		BorrarDocResponse response;
		try {
			BorrarDocRequest request = createBorrarDocRequest(adjuntarArchivosInEntity);
			services = wsCanalesTrfClient.obtenerPort();
			response = services.borrarDoc(request);
			LOGGER.info("Respuesta del WS: {}.", response);
		} catch (WebServiceException e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} catch (ICanalesTrfServiceBorrarDocBaseFaultFaultFaultMessage e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} finally {
			wsCanalesTrfClient.liberarPort(services);
		}

		return response;

	}

	/**
	 * Creates the carga doc request.
	 * 
	 * @param adjuntarArchivosInEntity
	 *            the adjuntar archivos in entity
	 * @return the carga doc request
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private CargaDocRequest createCargaDocRequest(AdjuntarArchivosInEntity adjuntarArchivosInEntity) throws UnsupportedEncodingException, DAOException {
		CargaDocRequest cargaDocRequest = new CargaDocRequest();
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory factory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory();
		cargaDocRequest.setNroTransferencia(factory.createProcesarTransferenciaOBPRequestNroTransferencia(adjuntarArchivosInEntity.getNroTransferencia()));
		cargaDocRequest.setTipoArchivo(factory.createCargaDocRequestTipoArchivo(adjuntarArchivosInEntity.getArchivo().getTipoArchivo()));
		cargaDocRequest.setImagen(factory.createCargaDocRequestImagen(adjuntarArchivosInEntity.getArchivo().getByteArray()));
		cargaDocRequest.setNombreArchivo(factory.createCargaDocRequestNombreArchivo(adjuntarArchivosInEntity.getArchivo().getNombre()));

		FirmaRequest firmaRequest = obtenerFirmaRequest();
		cargaDocRequest.setFirmaDatos(firmaRequest.getFirmaDatos());
		cargaDocRequest.setFirmaDatosDentro(firmaRequest.getFirmaDatosDentro());
		cargaDocRequest.setFirmaFormato(firmaRequest.getFirmaFormato());
		cargaDocRequest.setFirmaHash(firmaRequest.getFirmaHash());

		return cargaDocRequest;

	}

	/**
	 * Creates the consulta imagen trf request.
	 * 
	 * @param adjuntarArchivosInEntity
	 *            the adjuntar archivos in entity
	 * @return the consulta imagen trf request
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ConsultaImagenTrfRequest createConsultaImagenTrfRequest(AdjuntarArchivosInEntity adjuntarArchivosInEntity) throws UnsupportedEncodingException, DAOException {
		ConsultaImagenTrfRequest request = new ConsultaImagenTrfRequest();
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory factory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory();
		request.setNroTransferencia(factory.createConsultaImagenTrfRequestNroTransferencia(adjuntarArchivosInEntity.getNroTransferencia()));
		request.setIdImagen(factory.createConsultaImagenTrfRequestIdImagen(Integer.parseInt(adjuntarArchivosInEntity.getArchivo().getId())));

		FirmaRequest firmaRequest = obtenerFirmaRequest();
		request.setFirmaDatos(firmaRequest.getFirmaDatos());
		request.setFirmaDatosDentro(firmaRequest.getFirmaDatosDentro());
		request.setFirmaFormato(firmaRequest.getFirmaFormato());
		request.setFirmaHash(firmaRequest.getFirmaHash());

		return request;

	}

	/**
	 * Creates the borrar doc request.
	 * 
	 * @param adjuntarArchivosInEntity
	 *            the adjuntar archivos in entity
	 * @return the borrar doc request
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private BorrarDocRequest createBorrarDocRequest(AdjuntarArchivosInEntity adjuntarArchivosInEntity) throws UnsupportedEncodingException, DAOException {
		BorrarDocRequest request = new BorrarDocRequest();
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory factory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory();
		request.setNroTransferencia(factory.createProcesarTransferenciaOBPRequestNroTransferencia(adjuntarArchivosInEntity.getNroTransferencia()));
		request.setNroHoja(factory.createBorrarDocRequestNroHoja(Integer.parseInt(adjuntarArchivosInEntity.getArchivo().getId())));
		FirmaRequest firmaRequest = obtenerFirmaRequest();
		request.setFirmaDatos(firmaRequest.getFirmaDatos());
		request.setFirmaDatosDentro(firmaRequest.getFirmaDatosDentro());
		request.setFirmaFormato(firmaRequest.getFirmaFormato());
		request.setFirmaHash(firmaRequest.getFirmaHash());
		return request;

	}

	/**
	 * Creates the procesar transferencia OBP request.
	 *
	 * @param procesarTransferenciaComexInEntity
	 *            the procesar transferencia comex in entity
	 * @return the procesar transferencia OBP request
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ProcesarTransferenciaOBPRequest createProcesarTransferenciaOBPRequest(ProcesarTransferenciaComexInEntity procesarTransferenciaComexInEntity)
			throws UnsupportedEncodingException, DAOException {
		ProcesarTransferenciaOBPRequest request = new ProcesarTransferenciaOBPRequest();
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory factory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObjectFactory();
		FirmaRequest firmaRequest = obtenerFirmaRequest();

		request.setNroTransferencia(factory.createProcesarTransferenciaOBPRequestNroTransferencia(procesarTransferenciaComexInEntity.getNroTransferencia()));
		request.setImporteTransferencia(factory.createProcesarTransferenciaOBPRequestImporteTransferencia(procesarTransferenciaComexInEntity.getImporteTransferencia()));
		request.setCuentaDebito(factory.createProcesarTransferenciaOBPRequestCuentaDebito(procesarTransferenciaComexInEntity.getCuentaDebito()));
		request.setTipoDocumentoCliente(
				factory.createProcesarTransferenciaOBPRequestTipoDocumentoCliente(TiposDocumentoComexEnum.obtenerDescripcionPorCodigo(sesionCliente.getCliente().getTipoDocumento())));
		request.setNroDocumentoCliente(factory.createProcesarTransferenciaOBPRequestNroDocumentoCliente(sesionCliente.getCliente().getDni()));
		request.setMoneda(factory.createProcesarTransferenciaOBPRequestMoneda(procesarTransferenciaComexInEntity.getMoneda()));
		request.setConcepto(factory.createProcesarTransferenciaOBPRequestConcepto(procesarTransferenciaComexInEntity.getConcepto()));
		request.setInstRecibido(factory.createProcesarTransferenciaOBPRequestInstRecibido(INST_RECIBIDO));
		request.setInstVendido(factory.createProcesarTransferenciaOBPRequestInstVendido(INST_VENDIDO));
		request.setBeneficiario(factory.createProcesarTransferenciaOBPRequestBeneficiario(procesarTransferenciaComexInEntity.getBeneficiario()));
		request.setCuentaBeneficiario(factory.createProcesarTransferenciaOBPRequestCuentaBeneficiario(procesarTransferenciaComexInEntity.getCuentaBeneficiario()));
		request.setBancoBeneficiario(factory.createProcesarTransferenciaOBPRequestBancoBeneficiario(procesarTransferenciaComexInEntity.getBancoBeneficiario()));
		request.setBancoIntermediario(factory.createProcesarTransferenciaOBPRequestBancoIntermediario(procesarTransferenciaComexInEntity.getBancoIntermediario()));
		request.setCuentaBcoIntermediario(factory.createProcesarTransferenciaOBPRequestCuentaBcoIntermediario(procesarTransferenciaComexInEntity.getCuentaBcoIntermediario()));
		request.setTipoOperacion(factory.createProcesarTransferenciaOBPRequestTipoOperacion(procesarTransferenciaComexInEntity.getTipoOperacion()));
		request.setNupCliente(factory.createProcesarTransferenciaOBPRequestNupCliente(sesionCliente.getCliente().getNup()));
		request.setCtaAltair(factory.createProcesarTransferenciaOBPRequestCtaAltair(procesarTransferenciaComexInEntity.getCtaAltair()));
		request.setCanal(factory.createProcesarTransferenciaOBPRequestCanal(CANAL));
		request.setRazonSocial(factory.createProcesarTransferenciaOBPRequestRazonSocial(procesarTransferenciaComexInEntity.getRazonSocial()));
		if(TIPO_PERSONA_E.equalsIgnoreCase(sesionCliente.getCliente().getTipoPersona())) {
			request.setTipoPersona(factory.createProcesarTransferenciaOBPRequestTipoPersona(TIPO_PERSONA_J));
		} else {
			request.setTipoPersona(factory.createProcesarTransferenciaOBPRequestTipoPersona(TIPO_PERSONA_F));
		}
		request.setVinculo(factory.createProcesarTransferenciaOBPRequestVinculo(procesarTransferenciaComexInEntity.getVinculo()));
		request.setBeneficiarioDomicilio(factory.createProcesarTransferenciaOBPRequestBeneficiarioDomicilio(procesarTransferenciaComexInEntity.getBeneficiarioDomicilio()));
		request.setBeneficiarioPais(factory.createProcesarTransferenciaOBPRequestBeneficiarioPais(procesarTransferenciaComexInEntity.getBeneficiarioPais()));

		if (procesarTransferenciaComexInEntity.getAceptaDDJJ() != null) {
			request.setAceptaDdjj(factory.createProcesarTransferenciaOBPRequestAceptaDdjj(new Short(procesarTransferenciaComexInEntity.getAceptaDDJJ())));
		}
		if (procesarTransferenciaComexInEntity.getGastoACargo() != null) {
			request.setGastos(factory.createProcesarTransferenciaOBPRequestGastos(new Short(procesarTransferenciaComexInEntity.getGastoACargo())));
		}
		request.setEstadoTransferencia(factory.createProcesarTransferenciaOBPRequestEstadoTransferencia(procesarTransferenciaComexInEntity.getEstadoTransferencia()));
		request.setNroTransferenciaRel(factory.createProcesarTransferenciaOBPRequestNroTransferenciaRel(procesarTransferenciaComexInEntity.getNroFormRel()));

		// request.setTipoTransferencia(factory.createProcesarTransferenciaOBPRequestTipoTransferencia(new
		// Short("2")));
		// request.setReferenciaCliente(factory.createProcesarTransferenciaOBPRequestReferenciaCliente(""));
		// request.setObservaciones(factory.createProcesarTransferenciaOBPRequestObservaciones(""));
		// request.setDeclaracionAdicional(factory.createProcesarTransferenciaOBPRequestDeclaracionAdicional(new
		// Short("")));

		request.setFirmaFormato(firmaRequest.getFirmaFormato());
		request.setFirmaHash(firmaRequest.getFirmaHash());
		request.setFirmaDatos(firmaRequest.getFirmaDatos());
		request.setFirmaDatosDentro(firmaRequest.getFirmaDatosDentro());
		request.setEmpresaVinculada(factory.createProcesarTransferenciaOBPRequestEmpresaVinculada(procesarTransferenciaComexInEntity.getVinculante()));
		return request;
	}

}

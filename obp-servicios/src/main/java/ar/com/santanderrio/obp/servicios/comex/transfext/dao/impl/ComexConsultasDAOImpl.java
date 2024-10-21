/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.ws.WebServiceException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ICanalesTrfService;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.FirmaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ConsultaBancosRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ConsultaMonedasRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ConsultaPaisesRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaBancosResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaMonedasResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaPaisesResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.IConsultasService;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.IConsultasServiceConsultaBancosBaseFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.IConsultasServiceConsultaMonedasBaseFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.IConsultasServiceConsultaPaisesBaseFaultFaultFaultMessage;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexConsultasDAO;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.AdjuntarArchivosInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ComprobanteComexInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaBancosInEntity;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class ComexConsultasDAOImpl.
 * 
 */
@Component
public class ComexConsultasDAOImpl implements ComexConsultasDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComexConsultasDAOImpl.class);
	
	

	/** Gestionar ws comexConsultas. */
	@Autowired
	@Qualifier("comexConsultasGestor")
	private GestionarWS<IConsultasService> wsComexConsulta;

	/** The ws canales Trf client. */
	@Autowired
	@Qualifier("comexCanalesGestor")
	private GestionarWS<ICanalesTrfService> wsCanalesTrfClient;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sign. */
	@Autowired
	private Sign sign;

	/** The app encoding. */
	@Value("${APP.ENCODING}")
	private String appEncoding;

	/** The file jasper. */
	@Value("classpath:/report/comex/transferenciasComex.jasper")
	private Resource fileJasperComex;

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoCierre;

	/** The comex path. */
	@Value("${COMEX.VALIDADOR.VIRUS.DOCUMENTOS.PATH}")
	private String comexDocumentosPath;

	/** The Constant NOMBRE_COMPROBANTE. */
	private static final String NOMBRE_COMPROBANTE = "Comprobante_Transferencia_Comex.pdf";

	/** The Constant JKS_COMEX_CONSULTAS. */
	private static final String JKS_COMEX_CONSULTAS = "TRANSFEXT.CONSULTAS";

	/** The Constant FIRMA_DATOS_DENTRO. */
	private static final String FIRMA_DATOS_DENTRO_S = "S";

	/** The Constant FIRMA_FORMATO_B64. */
	private static final String FIRMA_FORMATO_B64 = "B64";

	// Si esta constante esta en 0 no tendra limite de registros;
	/** The Constant CANT_REGISTROS. */
	private static final int CANT_REGISTROS = 0;

	/** The Constant PREFIJO_COD_ABA. */
	private static final String PREFIJO_COD_ABA = "FW";

	/** The Constant LOGO_CABECERA. */
	private static final String LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant LOGO_CIERRE. */
	private static final String LOGO_CIERRE = "LOGO_CIERRE";

	/** The Constant DOCUMENTACION. */
	private static final String DOCUMENTACION = "DOCUMENTACION";
	
	/** The Constant NOMBRE. */
	private static final String NOMBRE = "NOMBRE";

	/** The Constant IMPORTE. */
	private static final String IMPORTE = "IMPORTE";

	/** The Constant MOTIVO. */
	private static final String MOTIVO = "MOTIVO";

	/** The Constant VINCULO. */
	private static final String VINCULO = "VINCULO";

	/** The Constant DOMICILIO. */
	private static final String DOMICILIO = "DOMICILIO";

	/** The Constant PAIS. */
	private static final String PAIS = "PAIS";

	/** The Constant NOMBRE_EMISOR. */
	private static final String NOMBRE_EMISOR = "NOMBRE_EMISOR";

	/** The Constant CUENTA. */
	private static final String CUENTA = "CUENTA";

	/** The Constant DESCRIPCION_CUENTA. */
	private static final String DESCRIPCION_CUENTA = "DESCRIPCION_CUENTA";

	/** The Constant CUENTA_DESTINO. */
	private static final String CUENTA_DESTINO = "CUENTA_DESTINO";

	/** The Constant NUMERO_COMPROBANTE. */
	private static final String NUMERO_COMPROBANTE = "NUMERO_COMPROBANTE";

	/** The Constant CODIGO_BANCARIO. */
	private static final String CODIGO_BANCARIO = "CODIGO_BANCARIO";

	/** The Constant GASTI_A_CARGO. */
	private static final String GASTO_A_CARGO = "GASTO_A_CARGO";

	/** The Constant FECHA. */
	private static final String FECHA = "FECHA";

	/** The Constant LEGALES. */
	private static final String LEGALES = "LEGALES";
	
	/** The Constant VINCULANTE. */
	private static final String VINCULANTE = "VINCULANTE";

	/** The Constant CODIGO_BANCO_INTERMEDIARIO. */
	private static final String CODIGO_BANCO_INTERMEDIARIO = "CODIGO_BANCO_INTERMEDIARIO";

	/** The Constant CUENTA_INTERMEDIARIO. */
	private static final String CUENTA_INTERMEDIARIO = "CUENTA_INTERMEDIARIO";
	
	/** The Constant CONCEPTO_VALIDO_ADJUNTAR. */
	private static final String CONCEPTO_VALIDO_ADJUNTAR = "CONCEPTO_VALIDO_ADJUNTAR";
	
	/** The tipos conceptos validos para adjuntar. */
	@Value("#{'${COMEX.CONCEPTOS.DOCUMENTACION.ADJUNTA}'.split('\\|')}")
	private List<String> conceptosValidosAdjuntar;
	
	@Value("${COMEX.MAX.FILES.ATACHED}")
	private Integer cantidadMaxDeArchivosPosiblesAjuntos;
	@Value("${COMEX.TIEMPO.ESPERA.VERIFICACION.ARCHIVO.ANTIVIRUS}")
	private Integer tiempoEsperaAntivirus;
	
	/** The Constant FECHA. */
	private static final String MONEDA = "MONEDA";
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.dao.
	 * ComexConsultasDAO#ConsultaPaisesResponse()
	 */
	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_PAISES_COMEX })
	@Override
	public ConsultaPaisesResponse consultaPaises() throws DAOException {
		LOGGER.info("Se llama al servicio de ComexConsultas consultaPaises");
		IConsultasService services = null;
		try {
			ConsultaPaisesRequest request = generarRequestConsultaPaises();
			services = wsComexConsulta.obtenerPort();
			ConsultaPaisesResponse response = services.consultaPaises(request);
			return response;
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error en el WS ConsultaPaises.", e);
			throw new DAOException(e);
		} catch (WebServiceException e) {
			LOGGER.error("Error en el WS ConsultaPaises.", e);
			throw new DAOException(e);
		} catch (IConsultasServiceConsultaPaisesBaseFaultFaultFaultMessage e) {
			LOGGER.error("Error en el WS ConsultaPaises.", e);
			throw new DAOException(e);
		} finally {
			wsComexConsulta.liberarPort(services);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.dao.
	 * ComexConsultasDAO#ConsultaMonedasResponse()
	 */
	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_MONEDAS_COMEX })
	@Override
	public ConsultaMonedasResponse consultaMonedas() throws DAOException {
		LOGGER.info("Se llama al servicio de ComexConsultas ConsultaMonedas.");
		IConsultasService services = null;
		try {
			ConsultaMonedasRequest request = generarRequestConsultaMoneda();
			services = wsComexConsulta.obtenerPort();
			ConsultaMonedasResponse response = services.consultaMonedas(request);
			LOGGER.info("Respuesta del WS: {}.", response);
			return response;
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error en el WS ConsultaMonedas.", e);
			throw new DAOException(e);
		} catch (WebServiceException e) {
			LOGGER.error("Error en el WS ConsultaMonedas.", e);
			throw new DAOException(e);
		} catch (IConsultasServiceConsultaMonedasBaseFaultFaultFaultMessage e) {
			LOGGER.error("Error en el WS ConsultaMonedas.", e);
			throw new DAOException(e);
		} finally {
			wsComexConsulta.liberarPort(services);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.dao.
	 * ComexConsultasDAO#consultaBancos()
	 */
	@Override
	public ConsultaBancosResponse consultaBancos(ConsultaBancosInEntity consultaBancosInEntity) throws DAOException {
		LOGGER.info("Se llama al servicio de ComexConsultas ConsultaBancos");
		IConsultasService services = null;
		try {
			services = wsComexConsulta.obtenerPort();
			ConsultaBancosResponse response = services.consultaBancos(generarRequestConsultaBanco(consultaBancosInEntity));
			LOGGER.info("Respuesta del WS: {}.", response);
			return response;
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error en el WS ConsultaBancos.", e);
			throw new DAOException(e);
		} catch (WebServiceException e) {
			LOGGER.error("Error en el WS ConsultaBancos.", e);
			throw new DAOException(e);
		} catch (IConsultasServiceConsultaBancosBaseFaultFaultFaultMessage e) {
			LOGGER.error("Error en el WS ConsultaBancos.", e);
			throw new DAOException(e);
		} finally {
			wsComexConsulta.liberarPort(services);
		}
	}

	/**
	 * Genera el request para el servicio ConsultaPaises.
	 *
	 * @return the ConsultaPaisesRequest
	 * @throws UnsupportedEncodingException
	 *             the UnsupportedEncoding exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ConsultaPaisesRequest generarRequestConsultaPaises() throws UnsupportedEncodingException, DAOException {
		LOGGER.info("ComexConsulta generando request ConsultaPaises");
		FirmaRequest firmaRequest = obtenerFirmaRequest();
		ConsultaPaisesRequest request = new ConsultaPaisesRequest();
		request.setFirmaFormato(firmaRequest.getFirmaFormato());
		request.setFirmaDatosDentro(firmaRequest.getFirmaDatosDentro());
		request.setFirmaDatos(firmaRequest.getFirmaDatos());
		request.setFirmaHash(firmaRequest.getFirmaHash());
		return request;
	}

	/**
	 * Genera el request para el servicio ConsultaMonedas.
	 *
	 * @return the ConsultaMonedasRequest
	 * @throws UnsupportedEncodingException
	 *             the UnsupportedEncoding exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ConsultaMonedasRequest generarRequestConsultaMoneda() throws UnsupportedEncodingException, DAOException {
		LOGGER.info("ComexConsulta generando request ConsultaMonedas");
		ConsultaMonedasRequest request = new ConsultaMonedasRequest();
		FirmaRequest firmaRequest = obtenerFirmaRequest();
		request.setFirmaFormato(firmaRequest.getFirmaFormato());
		request.setFirmaDatosDentro(firmaRequest.getFirmaDatosDentro());
		request.setFirmaDatos(firmaRequest.getFirmaDatos());
		request.setFirmaHash(firmaRequest.getFirmaHash());
		return request;
	}

	/**
	 * Genera el request para el servicio ConsultaBancos.
	 *
	 * @param consultaBancosInEntity
	 *            the consulta bancos in entity
	 * @return the ConsultaBancosRequest
	 * @throws UnsupportedEncodingException
	 *             the UnsupportedEncoding exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ConsultaBancosRequest generarRequestConsultaBanco(ConsultaBancosInEntity consultaBancosInEntity) throws UnsupportedEncodingException, DAOException {
		LOGGER.info("ComexConsultas generando request consultaBancos");
		String codigoBancario = consultaBancosInEntity.getCodigoBancario();
		ConsultaBancosRequest request = new ConsultaBancosRequest();
		FirmaRequest firmaRequest = obtenerFirmaRequest();
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.ObjectFactory baseFactory = new ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.ObjectFactory();
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ObjectFactory objectFactory = new ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ObjectFactory();
		if (NumberUtils.isNumber(codigoBancario) && codigoBancario.length() == 9) {
			request.setAbaBanco(objectFactory.createConsultaBancosRequestAbaBanco(PREFIJO_COD_ABA.concat(codigoBancario)));
		} else if (codigoBancario != null && codigoBancario.length() >= 6 && codigoBancario.length() <= 11) {
			request.setSwiftBanco(objectFactory.createConsultaBancosRequestSwiftBanco(codigoBancario));
		} else {
			throw new DAOException();
		}
		request.setCantidadRegistros(baseFactory.createBaseCursorCantidadRegistros(CANT_REGISTROS));

		request.setFirmaFormato(firmaRequest.getFirmaFormato());
		request.setFirmaDatosDentro(firmaRequest.getFirmaDatosDentro());
		request.setFirmaDatos(firmaRequest.getFirmaDatos());
		request.setFirmaHash(firmaRequest.getFirmaHash());
		return request;
	}

	/**
	 * Genera los parametros de firma para los request de ComexConsultas.
	 *
	 * 
	 * @return the FirmaRequest
	 * @throws UnsupportedEncodingException
	 *             the UnsupportedEncoding exception
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
	 * Genera el archivo xml para firmar.
	 *
	 * @return String datos a firmar
	 */
	private String obtenerFirmaDatosXML() {
		org.dom4j.Document doc = org.dom4j.DocumentHelper.createDocument();
		org.dom4j.Element datos = doc.addElement("datos");
		datos.addElement("nup").setText(sesionCliente.getCliente().getNup());
		datos.addElement("tipodocumento").setText(sesionCliente.getCliente().getTipoDocumento());
		datos.addElement("numerodocumento").setText(sesionCliente.getCliente().getDni());
		return datos.asXML();
	}

	/**
	 * Firma los datos.
	 *
	 * @param param
	 *            the param
	 * @return String firma
	 * @throws UnsupportedEncodingException
	 *             the UnsupportedEncoding exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private String obtenerFirma(String param) throws UnsupportedEncodingException, DAOException {
		return new String(sign.buildB64Signature(param.getBytes(appEncoding), JKS_COMEX_CONSULTAS, true));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comex.transext.dao.
	 * ComexConsultasDAO#GenerarComprobante()
	 */
	@Override
	public Reporte generarComprobante(ComprobanteComexInEntity comprobanteInEntity) throws DAOException {
		int nroDocumento = 1;
		LOGGER.info("TransferenciaComex iniciando descargar comprobante");
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			jasperReport = (JasperReport) JRLoader.loadObject(fileJasperComex.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			LOGGER.debug("Completando parametros de reporte TransferenciaComex");
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());
			String nombreArchivos =  comprobanteInEntity.getDocumentacion().replace("/", "-");
			//for(String nombre :comprobanteInEntity.getDocumentacion().split("/")) {
			//	parameters.put(DOCUMENTACION.concat(String.valueOf(nroDocumento)),nombre);
			//	nroDocumento++;
			//} 
			parameters.put(DOCUMENTACION+"1",nombreArchivos);
			parameters.put(NOMBRE, comprobanteInEntity.getNombre());
			parameters.put(IMPORTE, comprobanteInEntity.getImporte());
			parameters.put(MOTIVO, comprobanteInEntity.getMotivo());
			parameters.put(VINCULO, comprobanteInEntity.getVinculo());
			parameters.put(DOMICILIO, comprobanteInEntity.getDomicilio());
			parameters.put(PAIS, comprobanteInEntity.getPais());
			parameters.put(NOMBRE_EMISOR, comprobanteInEntity.getNombreEmisor());
			parameters.put(CUENTA, comprobanteInEntity.getCuenta());
			parameters.put(DESCRIPCION_CUENTA, comprobanteInEntity.getDescripcionCuenta());
			parameters.put(CUENTA_DESTINO, comprobanteInEntity.getCuentaDestino());
			parameters.put(CODIGO_BANCARIO, comprobanteInEntity.getCodigoBancario());
			parameters.put(GASTO_A_CARGO, comprobanteInEntity.getGastosACargo());
			parameters.put(CUENTA_INTERMEDIARIO, comprobanteInEntity.getCuentaBancoIntermediario());
			parameters.put(CODIGO_BANCO_INTERMEDIARIO, comprobanteInEntity.getCodigoBancarioIntermediario());
			parameters.put(NUMERO_COMPROBANTE, comprobanteInEntity.getNumeroComprobante());
			parameters.put(FECHA, comprobanteInEntity.getFecha());
			parameters.put(LEGALES, comprobanteInEntity.getLegales());
			parameters.put(VINCULANTE, comprobanteInEntity.getVinculante());
			if (comprobanteInEntity.getDocumentacion().length() > 0) {
				parameters.put(CONCEPTO_VALIDO_ADJUNTAR,true);
			}else {
				parameters.put(CONCEPTO_VALIDO_ADJUNTAR,false);
			}
			//parameters.put(CONCEPTO_VALIDO_ADJUNTAR,conceptosValidosAdjuntar.contains(comprobanteInEntity.getConceptoCodigo()));
			parameters.put(MONEDA, comprobanteInEntity.getMoneda());
			
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			byte[] byteArray = outStream.toByteArray();
			Reporte reporteOperaExterior = new Reporte();
			reporteOperaExterior.setTipoArchivo(TipoArchivoEnum.PDF);
			reporteOperaExterior.setBytes(byteArray);
			reporteOperaExterior.setNombre(NOMBRE_COMPROBANTE);
			LOGGER.debug("ComexConsultasDAO finalizando descargar comprobante");
			return reporteOperaExterior;
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new DAOException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexConsultasDAO#verificarVirusArchivoFs(ar.com.santanderrio.obp.servicios.comex.transfext.entities.AdjuntarArchivosInEntity)
	 */
	@Override
	public Respuesta<Boolean> verificarVirusArchivoFs(AdjuntarArchivosInEntity adjuntarArchivosInEntity) {
		Respuesta<Boolean> response = new Respuesta<Boolean>();
		LOGGER.info("Verificar si archivo tiene virus");
		OutputStream out = null;
		File filePath = null;
		File archivo = null;
		try {

			filePath = new File(this.comexDocumentosPath + "/" + this.sesionCliente.getCliente().getNup());

			if (!filePath.exists()) {
				filePath.mkdir();
			}
			
			

			archivo = new File(this.comexDocumentosPath + "/" + this.sesionCliente.getCliente().getNup() + "/" + adjuntarArchivosInEntity.getArchivo().getNombre());

			out = new FileOutputStream(archivo);
			out.write(adjuntarArchivosInEntity.getArchivo().getByteArray());
			out.flush();
			out.close();
			
			esperarQueAntivirusVerifiqueArchivo();

			if (archivo.exists()) {
				response.setRespuesta(true);
				
			} else {
				LOGGER.error("Error el archivo tiene virus!");
				response.setRespuesta(false);
			}

		} catch (IOException e) {
			LOGGER.error("Error verificando archivo", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (filePath != null) {
					FileUtils.deleteDirectory(filePath);
				}
			} catch (IOException e) {
				LOGGER.error("Error verificando archivo", e);
			}
		}
		return response;
	}
	
	
	/**
	 * 
	 */
	private void esperarQueAntivirusVerifiqueArchivo() {
		try {
			TimeUnit.MILLISECONDS.sleep(tiempoEsperaAntivirus);
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
		}

	}



	@CacheEvict(value = CacheConstants.Names.CACHE_PAISES_COMEX, allEntries = true)
	@Override
	public void limpiarPaises() {
		LOGGER.info("Se limpia la cache de paises.");
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexConsultasDAO#limpiarMonedas()
	 */
	@CacheEvict(value = CacheConstants.Names.CACHE_MONEDAS_COMEX, allEntries = true)
	@Override
	public void limpiarMonedas() {
		LOGGER.info("Se limpia la cache de monedas.");
	}

}

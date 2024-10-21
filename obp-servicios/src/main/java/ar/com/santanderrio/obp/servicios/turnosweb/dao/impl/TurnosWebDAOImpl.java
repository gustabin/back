/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

import javax.xml.bind.JAXBException;
import javax.xml.ws.WebServiceException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.cxf.binding.soap.SoapFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.JaxbUtils;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.ConsultaCitaRequest;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.GetConsultaCitaSvc;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.IDireccionador;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.ObjectFactory;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.ParametrosCita;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetAltaCitaConMotivoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetBajaTurnoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaCitaConMotivoSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaHorariosDisponiblesSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetConsultaSucursalesSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetModificacionCitaSvcResponse;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts.GetMotivosPorSucursalYSectorCliNoCliSvcResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import ar.com.santanderrio.obp.servicios.turnosweb.dao.TurnosWebDAO;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.AltaModificacionCitaInEntity;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.ComprobanteTurnoInEntity;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.HorariosDisponiblesInEntity;
import ar.com.santanderrio.obp.servicios.turnosweb.entity.MotivosInEntity;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class TurnosWebDAOImpl.
 *
 * @author IT Resources
 */
@Component
public class TurnosWebDAOImpl implements TurnosWebDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TurnosWebDAOImpl.class);

	/** The ws direccionador client. */
	@Autowired
	@Qualifier("gestionDireccionador")
	private GestionarWS<IDireccionador> wsDireccionadorClient;

	/** The sign. */
	@Autowired
	private Sign sign;

	/** The legal bo. */
	@Autowired
	private LegalBO legalBO;

	/** The app encoding. */
	@Value("${APP.ENCODING}")
	private String appEncoding;

	/** The Constant PLATAFORMA. */
	private static final String PLATAFORMA = "P";

	/** The Constant JKS_CITA. */
	private static final String JKS_CITA = "CONSULTACITASVC";

	/** The Constant XMLNS_CITA. */
	private static final String XMLNS_CITA = "xmlns=\"http://tempuri.org/\"";

	/** The Constant MENSAJE_ERROR. */
	private static final String MENSAJE_ERROR = "Error de jaxb al transformar el token a xml.";
	
	/** The Constant XML_FIRMAR_LOGGER. */
	private static final String XML_FIRMAR_LOGGER = "Xml a firmar {} con jks {}.";

	/** The file jasper. */
	@Value("classpath:/report/turnosWeb/TurnoWeb.jasper")
	private Resource fileJasperTurnoWeb;
	
	/** The file jasper. */
	@Value("classpath:/report/turnosWeb/TurnoWebRemoto.jasper")
	private Resource fileJasperTurnoWebRemoto;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoCierre;

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The Constant LOGO_CABECERA. */
	private static final String LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant LOGO_CIERRE. */
	private static final String LOGO_CIERRE = "LOGO_CIERRE";

	/** The Constant DIA. */
	private static final String DIA = "DIA";

	/** The Constant HORA. */
	private static final String HORA = "HORA";

	/** The Constant SUCURSAL. */
	private static final String SUCURSAL = "SUCURSAL";

	/** The Constant DIRECCION. */
	private static final String DIRECCION = "DIRECCION";

	/** The Constant FECHA_OPERACION. */
	private static final String FECHA_OPERACION = "FECHA_OPERACION";

	/** The Constant LOGO_DEFAULT. */
	private static final String LOGO_DEFAULT = "LOGO_DEFAULT";

	/** The Constant NUMERO_CELULAR. */
	private static final String CELULAR = "CELULAR";

	/** The Constant EMPRESA_CELULAR. */
	private static final String EMPRESA_CELULAR = "EMPRESA_CELULAR";

	/** The Constant EMAIL. */
	private static final String EMAIL = "EMAIL";

	/** The Constant TITULO. */
	private static final String TITULO = "TITULO";

	/** The Constant DNI. */
	private static final String DNI = "DNI";

	/** The Constant NYA. */
	private static final String NYA = "NYA";

	/** The Constant MENSAJE. */
	private static final String MENSAJE = "MENSAJE";
	
	/** The Constant MENSAJE_2. */
	private static final String MENSAJE_2 = "MENSAJE_2";

	/** The Constant MENSAJE_3. */
	private static final String MENSAJE_3 = "MENSAJE_3";

	/** The Constant MOTIVO. */
	private static final String MOTIVO = "MOTIVO";

	/** The Constant MOTIVO_DEFAULT. */
	private static final String MOTIVO_DEFAULT = "99";

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.dao.TurnosWebDAO#consultarCitas(java.lang.String)
	 */
	@Override
	public GetConsultaCitaConMotivoSvcResponse consultarCitas(String nup) throws DAOException {
		LOGGER.info("Se consulta para saber las citas del cliente Select con numero de nup: {}.", nup);
		IDireccionador services = null;
		GetConsultaCitaSvc getConsultaCitaSvc = generarFirmaConsultaCita(nup);
		try {
			services = wsDireccionadorClient.obtenerPort();
			String firmaToken = getConsultaCitaSvc.getValor().getValue();
			GetConsultaCitaConMotivoSvcResponse response = services.getConsultaCitaConMotivoSvc(firmaToken);
			LOGGER.info("Respuesta del WS: {}.", response);
			return response;
		} catch (DAOException e) {
			LOGGER.error("Error en el WS getConsultaCitaConMotivoSvc.", e);
			throw new DAOException(e);
		} finally {
			wsDireccionadorClient.liberarPort(services);
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.dao.TurnosWebDAO#consultaHorarioDisponibles(ar.com.santanderrio.obp.servicios.turnosweb.entity.HorariosDisponiblesInEntity)
	 */
	@Override
	public GetConsultaHorariosDisponiblesSvcResponse consultaHorarioDisponibles(
			HorariosDisponiblesInEntity horariosDisponiblesInEntity) throws DAOException {
		LOGGER.info("Se consulta para saber los horarios disponibles para sucursal {} sector {}.", horariosDisponiblesInEntity.getNroSuc(),horariosDisponiblesInEntity.getSector());
		IDireccionador services = null;
		try {
			String firmaToken = armarFirmaConsultaHorariosDisponibles(horariosDisponiblesInEntity);
			services = wsDireccionadorClient.obtenerPort();
			GetConsultaHorariosDisponiblesSvcResponse response = services.getConsultaHorariosDisponiblesSvc(firmaToken);
			LOGGER.info("Respuesta del WS: {}.", response);
			return response;
		} catch (SoapFault sfe) {
			LOGGER.error("Error en el WS getConsultaHorariosDisponiblesSvc.", sfe);
			throw new DAOException(sfe);
		} catch (WebServiceException wse) {
			LOGGER.error("Error en el WS getConsultaHorariosDisponiblesSvc.", wse);
			throw new DAOException(wse);
		} catch (UnsupportedEncodingException uee) {
			LOGGER.error("Error en el WS getConsultaHorariosDisponiblesSvc.", uee);
			throw new DAOException(uee);
		} finally {
			wsDireccionadorClient.liberarPort(services);
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.dao.TurnosWebDAO#consultaSucursales()
	 */
	@Override
	public GetConsultaSucursalesSvcResponse consultaSucursales() throws DAOException {
		LOGGER.info("Se consulta para saber las sucursales disponibles.");
		IDireccionador services = null;
		try {
			ParametrosCita parametrosCita = new ParametrosCita();
			String firmaToken = generarRequestFirmado(parametrosCita, XMLNS_CITA, JKS_CITA);
			services = wsDireccionadorClient.obtenerPort();
			GetConsultaSucursalesSvcResponse response = services.getConsultaSucursalesSvc(firmaToken);
			LOGGER.info("Respuesta del WS: {}.", response);
			return response;
		} catch (SoapFault sfe) {
			LOGGER.error("Error en el WS getConsultaSucursalesSvc.", sfe);
			throw new DAOException(sfe);
		} catch (WebServiceException wse) {
			LOGGER.error("Error en el WS getConsultaSucursalesSvc.", wse);
			throw new DAOException(wse);
		} finally {
			wsDireccionadorClient.liberarPort(services);
		}
	}


	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.dao.TurnosWebDAO#altaCita(ar.com.santanderrio.obp.servicios.turnosweb.entity.AltaModificacionCitaInEntity)
	 */
	@Override
	public GetAltaCitaConMotivoSvcResponse altaCita(AltaModificacionCitaInEntity altaModificacionCitaInEntity) throws DAOException {
		LOGGER.info("Alta Cita Id Turno:{}, Nup:{}, Nro. Suc:{}, Fecha:{}, Fraccion:{}, Sector:{}.",
				altaModificacionCitaInEntity.getIdTurno(),altaModificacionCitaInEntity.getNup(),altaModificacionCitaInEntity.getNroSuc(),
				altaModificacionCitaInEntity.getFecha(),altaModificacionCitaInEntity.getFraccion(),altaModificacionCitaInEntity.getSector());
		IDireccionador services = null;
		try {
			String firmaToken = armarFirmaAltaModificacionCita(altaModificacionCitaInEntity);
			services = wsDireccionadorClient.obtenerPort();
			GetAltaCitaConMotivoSvcResponse response = services.getAltaCitaTransactionConMotivoSvc(firmaToken);
			LOGGER.info("Respuesta del WS: {}.", response);
			return response;
		} catch (SoapFault sfe) {
			LOGGER.error("Error en el WS getAltaCitaTransactionConMotivoSvc.", sfe);
			throw new DAOException(sfe);
		} catch (WebServiceException wse) {
			if(wse.getCause() instanceof SocketTimeoutException) {
				LOGGER.error("Time Out Error en el WS getAltaCitaTransactionConMotivoSvc.", wse);
				throw new TimeOutException(wse.getMessage());
			}
			LOGGER.error("Error en el WS getAltaCitaTransactionConMotivoSvc.", wse);
			throw new DAOException(wse);
		} catch (UnsupportedEncodingException uee) {
			LOGGER.error("Error en el WS getAltaCitaTransactionConMotivoSvc.", uee);
			throw new DAOException(uee);
		} finally {
			wsDireccionadorClient.liberarPort(services);
		}
	}


	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.dao.TurnosWebDAO#bajaTurno(java.lang.Long)
	 */
	@Override
	public GetBajaTurnoSvcResponse bajaTurno(Long idTurno) throws DAOException {
		LOGGER.info("Baja Turno - Id Turno:{}.",idTurno);
		IDireccionador services = null;
		try {
			String firmaToken = armarFirmaBajaTurno(idTurno);
			services = wsDireccionadorClient.obtenerPort();
			GetBajaTurnoSvcResponse response = services.getBajaTurnoSvc(firmaToken);
			LOGGER.info("Respuesta del WS: {}.", response);
			return response;
		} catch (SoapFault sfe) {
			LOGGER.error("Error en el WS getBajaTurnoSvc.", sfe);
			throw new DAOException(sfe);
		} catch (WebServiceException wse) {
			LOGGER.error("Error en el WS getBajaTurnoSvc.", wse);
			throw new DAOException(wse);
		} catch (UnsupportedEncodingException uee) {
			LOGGER.error("Error en el WS getBajaTurnoSvc.", uee);
			throw new DAOException(uee);
		}finally {
			wsDireccionadorClient.liberarPort(services);
		}
	}


	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.dao.TurnosWebDAO#modificacionCita(ar.com.santanderrio.obp.servicios.turnosweb.entity.AltaModificacionCitaInEntity)
	 */
	@Override
	public GetModificacionCitaSvcResponse modificacionCita(AltaModificacionCitaInEntity altaModificacionCitaInEntity)
			throws DAOException {
		LOGGER.info("Modificacion Cita Id Turno:{}, Nup:{}, Nro. Suc:{}, Fecha:{}, Fraccion:{}, Sector:{}.",
				altaModificacionCitaInEntity.getIdTurno(),altaModificacionCitaInEntity.getNup(),altaModificacionCitaInEntity.getNroSuc(),altaModificacionCitaInEntity.getFecha(),altaModificacionCitaInEntity.getFraccion(),altaModificacionCitaInEntity.getSector());
		IDireccionador services = null;
		try {
			String firmaToken = armarFirmaAltaModificacionCita(altaModificacionCitaInEntity);
			services = wsDireccionadorClient.obtenerPort();
			GetModificacionCitaSvcResponse response = services.getModificacionCitaTransactionSvc(firmaToken);
			LOGGER.info("Respuesta del WS: {}.", response);
			return response;
		} catch (SoapFault sfe) {
			LOGGER.error("Error en el WS getModificacionCitaTransactionSvc.", sfe);
			throw new DAOException(sfe);
		} catch (WebServiceException wse) {
			if(wse.getCause() instanceof SocketTimeoutException) {
				LOGGER.error("Time Out Error en el WS getModificacionCitaTransactionSvc.", wse);
				throw new TimeOutException(wse.getMessage());
			}
			LOGGER.error("Error en el WS getModificacionCitaTransactionSvc.", wse);
			throw new DAOException(wse);
		} catch (UnsupportedEncodingException uee) {
			LOGGER.error("Error en el WS getModificacionCitaTransactionSvc.", uee);
			throw new DAOException(uee);	
		} finally {
			wsDireccionadorClient.liberarPort(services);
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.dao.TurnosWebDAO#obtenerMotivosTurno(MotivosInEntity)
	 */
	@Override
	public GetMotivosPorSucursalYSectorCliNoCliSvcResponse obtenerMotivosTurno(MotivosInEntity motivosInEntity) throws DAOException {
		LOGGER.info("obtenerMotivosTurno");
		IDireccionador services = null;
		try {
			//ParametrosCita parametrosCita = new ParametrosCita();
			//String firmaToken = generarRequestFirmado(parametrosCita, XMLNS_CITA, JKS_CITA);
			String firmaToken = armarFirmaConsultaMotivos(motivosInEntity);
			services = wsDireccionadorClient.obtenerPort();
			GetMotivosPorSucursalYSectorCliNoCliSvcResponse response = services.getMotivosPorSucursalYSectorCliNoCliSvc(firmaToken);
			LOGGER.info("Respuesta del WS: {}.", response);
			return response;
		} catch (SoapFault sfe) {
			LOGGER.error("Error en el WS getMotivosSvc.", sfe);
			throw new DAOException(sfe);
		} catch (WebServiceException wse) {
			if(wse.getCause() instanceof SocketTimeoutException) {
				LOGGER.error("Time Out Error en el WS getMotivosPorSucursalYSectorCliNoCliSvc.", wse);
				throw new TimeOutException(wse.getMessage());
			}
			LOGGER.error("Error en el WS getMotivosPorSucursalYSectorCliNoCliSvc.", wse);
			throw new DAOException(wse);
		}  catch (UnsupportedEncodingException uee) {
			LOGGER.error("Error en el WS getMotivosPorSucursalYSectorCliNoCliSvc.", uee);
			throw new DAOException(uee);
		} finally {
			wsDireccionadorClient.liberarPort(services);
		}
	}

	/**
	 * Genera la firma de la consulta por cita.
	 *
	 * @param nup
	 *            the nup
	 * @return the gets the consulta cita svc
	 * @throws DAOException
	 *             the DAO exception
	 */
	private GetConsultaCitaSvc generarFirmaConsultaCita(String nup) throws DAOException {
		ConsultaCitaRequest token = generarDatosParaFirmaCita(nup);
		String firmaToken = generarRequestFirmado(token, XMLNS_CITA, JKS_CITA);
		ObjectFactory of = new ObjectFactory();
		GetConsultaCitaSvc ret = of.createGetConsultaCitaSvc();
		ret.setValor(of.createGetConsultaCitaSvcValor(firmaToken));
		return ret;
	}

	/**
	 * Genera los datos para firma de la consulta por cita.
	 *
	 * @param nup
	 *            the nup
	 * @return the consulta cita request
	 */
	private ConsultaCitaRequest generarDatosParaFirmaCita(String nup) {
		ConsultaCitaRequest request = new ConsultaCitaRequest();
		request.setParametros(new ParametrosCita(nup));
		return request;
	}

	/**
	 * Genera el request firmado.
	 *
	 * @param datos
	 *            the datos
	 * @param xmlns
	 *            the xmlns
	 * @param jks
	 *            the jks
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	private String generarRequestFirmado(Object datos, String xmlns, String jks) throws DAOException {
		try {
			String xmlDatosConNS = JaxbUtils.transformarObjetoAXml(datos, appEncoding, Boolean.TRUE, Boolean.FALSE, null);
			String xmlDatos = cleanNS(xmlDatosConNS, xmlns);
			LOGGER.info(XML_FIRMAR_LOGGER, xmlDatos, jks);
			return new String(sign.buildB64Signature(xmlDatos.getBytes(appEncoding), jks, true));
		}  catch (JAXBException e) {
			LOGGER.error(MENSAJE_ERROR, e);
			throw new DAOException(MENSAJE_ERROR);
		} catch (UnsupportedEncodingException uee) {
			LOGGER.error(MENSAJE_ERROR, uee);
			throw new DAOException(MENSAJE_ERROR);
		}
	}

	/**
	 * Clean NS.
	 *
	 * @param xmlDatosConNS
	 *            the xml datos con NS
	 * @param xmlns
	 *            the xmlns
	 * @return the string
	 */
	private String cleanNS(String xmlDatosConNS, String xmlns) {
		return StringUtils.remove(xmlDatosConNS, xmlns);
	}

	/**
	 * Armar firma consulta horarios disponibles.
	 *
	 * @param consultaHorarioDisponiblesInEntity the consulta horario disponibles in entity
	 * @return the string
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws DAOException the DAO exception
	 */
	private String armarFirmaConsultaHorariosDisponibles(
			HorariosDisponiblesInEntity consultaHorarioDisponiblesInEntity) throws UnsupportedEncodingException, DAOException {
		StringBuilder request = new StringBuilder();
		request.append("<parametros>");
		request.append("<nrosuc>");
		request.append(consultaHorarioDisponiblesInEntity.getNroSuc());
		request.append("</nrosuc>");
		request.append("<sector>");
		request.append(consultaHorarioDisponiblesInEntity.getSector());
		request.append("</sector>");
		request.append("</parametros>");
		LOGGER.info(XML_FIRMAR_LOGGER, request, JKS_CITA);
		return obtenerFirma(request.toString());
	}

	/**
	 * arma el request para el alta o modificacion de turno y lo devuelve firmado.
	 *
	 * @param altaModificacionCitaInEntity the alta modificacion cita in entity
	 * @return the string
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws DAOException the DAO exception
	 */
	private String armarFirmaAltaModificacionCita(AltaModificacionCitaInEntity altaModificacionCitaInEntity) throws UnsupportedEncodingException, DAOException {
		StringBuilder request = new StringBuilder();
		request.append("<parametros>");
		request.append("<idturno>");
		request.append(altaModificacionCitaInEntity.getIdTurno());
		request.append("</idturno>");
		request.append("<nup>");
		request.append(altaModificacionCitaInEntity.getNup());
		request.append("</nup>");
		request.append("<nrosuc>");
		request.append(altaModificacionCitaInEntity.getNroSuc());
		request.append("</nrosuc>");
		request.append("<fecha>");
		request.append(altaModificacionCitaInEntity.getFecha());
		request.append("</fecha>");
		request.append("<fraccion>");
		request.append(altaModificacionCitaInEntity.getFraccion());
		request.append("</fraccion>");
		request.append("<sector>");
		request.append(altaModificacionCitaInEntity.getSector());
		request.append("</sector>");
		request.append("<motivo>");
		if (PLATAFORMA.equals(altaModificacionCitaInEntity.getSector())) {
			request.append(altaModificacionCitaInEntity.getMotivoId());
		} else {
			request.append(MOTIVO_DEFAULT);
		}
		request.append("</motivo>");
		request.append("<cuit>");
		request.append(altaModificacionCitaInEntity.getCuit());
		request.append("</cuit>");
		request.append("</parametros>");
		LOGGER.info(XML_FIRMAR_LOGGER, request, JKS_CITA);
		return obtenerFirma(request.toString());
	}

	/**
	 * arma el request para la baja de turno y lo devuelve firmado.
	 *
	 * @param idTurno the id turno
	 * @return the string
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws DAOException the DAO exception
	 */
	private String armarFirmaBajaTurno(Long idTurno) throws UnsupportedEncodingException, DAOException {
		StringBuilder request = new StringBuilder();
		request.append("<parametros>");
		request.append("<idturno>");
		request.append(idTurno);
		request.append("</idturno>");
		request.append("</parametros>");
		LOGGER.info(XML_FIRMAR_LOGGER, request, JKS_CITA);
		return obtenerFirma(request.toString());
	}

	/**
	 * Armar firma consulta motivos.
	 *
	 * @param motivosInEntity the motivos in entity
	 * @return the string
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws DAOException the DAO exception
	 */
	private String armarFirmaConsultaMotivos(MotivosInEntity motivosInEntity) throws UnsupportedEncodingException, DAOException {
		StringBuilder request = new StringBuilder();
		request.append("<parametros>");
		request.append("<nrosuc>");
		request.append(motivosInEntity.getNroSuc());
		request.append("</nrosuc>");
		request.append("<sector>");
		request.append(motivosInEntity.getSector());
		request.append("</sector>");
		request.append("<escliente>S</escliente>");
		request.append("</parametros>");
		LOGGER.info(XML_FIRMAR_LOGGER, request, JKS_CITA);
		return obtenerFirma(request.toString());
	}

	/**
	 * obtiene el string firmado.
	 *
	 * @param param the param
	 * @return the string
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws DAOException the DAO exception
	 */
	private String obtenerFirma(String param) throws UnsupportedEncodingException, DAOException {
		return  new String(sign.buildB64Signature(param.getBytes(appEncoding), JKS_CITA, true));
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.dao.TurnosWebDAO#generarComprobante(ar.com.santanderrio.obp.servicios.turnosweb.entity.ComprobanteTurnoInEntity, 
	 * ar.com.santanderrio.obp.servicios.clientes.entitie.Cliente)
	 */
	@Override
	public Reporte generarComprobante(ComprobanteTurnoInEntity comprobanteTurnoInEntity, Cliente cliente) {
		LOGGER.info("TurnosWeb iniciando descargar comprobante");
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			jasperReport = (JasperReport) JRLoader.loadObject(fileJasperTurnoWeb.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			LOGGER.debug("Completando parametros de reporte TurnosWeb");
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(LOGO_DEFAULT, comprobanteTurnoInEntity.getLogoDefault());
			parameters.put(TITULO, comprobanteTurnoInEntity.getTitulo());
			parameters.put(DIA, comprobanteTurnoInEntity.getDia());
			parameters.put(HORA, comprobanteTurnoInEntity.getHora());
			parameters.put(SUCURSAL, comprobanteTurnoInEntity.getSucursal());
			parameters.put(DIRECCION, comprobanteTurnoInEntity.getDireccion());
			parameters.put(FECHA_OPERACION, comprobanteTurnoInEntity.getFecha());
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());
			parameters.put(CELULAR, comprobanteTurnoInEntity.getAreaCelular()+" - "+comprobanteTurnoInEntity.getNumeroCelular());
			parameters.put(EMPRESA_CELULAR, comprobanteTurnoInEntity.getEmpresaCelular());
			parameters.put(EMAIL, comprobanteTurnoInEntity.getEmail());
			parameters.put(DNI, ISBANStringUtils.formatearDocumento(ISBANStringUtils.eliminarCeros(cliente.getDni())));
			String nya = cliente.getNombre().concat(ISBANStringUtils.ESPACIO_STRING).concat(cliente.getApellido1());
			parameters.put(NYA, WordUtils.capitalizeFully(nya));
			if (PLATAFORMA.equals(comprobanteTurnoInEntity.getSector())) {
				parameters.put(MENSAJE, legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_COMPROBANTE_TURNOS_PDF_AVISO_PLATAFORMA));
				parameters.put(MENSAJE_2, legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_COMPROBANTE_TURNOS_PDF_ATENCION_CAJAS));
				parameters.put(MENSAJE_3, "");
			} else {
				parameters.put(MENSAJE, legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_COMPROBANTE_TURNOS_PDF_AVISO_CAJA));
				parameters.put(MENSAJE_2, legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_COMPROBANTE_TURNOS_PDF_ATENCION_CAJAS));
				parameters.put(MENSAJE_3, "");
			}
			parameters.put(MOTIVO, comprobanteTurnoInEntity.getMotivoDescripcion());
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			byte[] byteArray = outStream.toByteArray();
			Reporte reporteOperaExterior = new Reporte();
			reporteOperaExterior.setTipoArchivo(TipoArchivoEnum.PDF);
			reporteOperaExterior.setBytes(byteArray);
			reporteOperaExterior.setNombre("Comprobante_TurnoWeb.pdf");
			LOGGER.debug("TurnosWebDAO finalizando descargar comprobante");
			return reporteOperaExterior;
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
	}	
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.turnosweb.dao.TurnosWebDAO#generarComprobanteRemoto(ar.com.santanderrio.obp.servicios.turnosweb.entity.ComprobanteTurnoInEntity, 
	 * ar.com.santanderrio.obp.servicios.clientes.entitie.Cliente)
	 */
	@Override
	public Reporte generarComprobanteRemoto(ComprobanteTurnoInEntity comprobanteTurnoInEntity, Cliente cliente) {
		LOGGER.info("TurnosWeb Remoto iniciando descargar comprobante");
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			jasperReport = (JasperReport) JRLoader.loadObject(fileJasperTurnoWebRemoto.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			LOGGER.debug("Completando parametros de reporte TurnosWeb");
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(LOGO_DEFAULT, comprobanteTurnoInEntity.getLogoDefault());
			parameters.put(TITULO, comprobanteTurnoInEntity.getTitulo());
			parameters.put(DIA, comprobanteTurnoInEntity.getDia());
			parameters.put(HORA, comprobanteTurnoInEntity.getHora());
			parameters.put(SUCURSAL, comprobanteTurnoInEntity.getSucursal());			
			parameters.put(FECHA_OPERACION, comprobanteTurnoInEntity.getFecha());
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());
			parameters.put(CELULAR, comprobanteTurnoInEntity.getAreaCelular()+" - "+comprobanteTurnoInEntity.getNumeroCelular());
			parameters.put(EMPRESA_CELULAR, comprobanteTurnoInEntity.getEmpresaCelular());
			parameters.put(EMAIL, comprobanteTurnoInEntity.getEmail());
			parameters.put(DNI, ISBANStringUtils.formatearDocumento(ISBANStringUtils.eliminarCeros(cliente.getDni())));
			String nya = cliente.getNombre().concat(ISBANStringUtils.ESPACIO_STRING).concat(cliente.getApellido1());
			parameters.put(NYA, WordUtils.capitalizeFully(nya));
			
			parameters.put(MENSAJE, legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_COMPROBANTE_TURNOS_REMOTO_PDF_AUTOGESTION_MENSAJE1));
			parameters.put(MENSAJE_2, legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_COMPROBANTE_TURNOS_REMOTO_PDF_AUTOGESTION_MENSAJE2));
			parameters.put(MENSAJE_3, " ");
						
			parameters.put(MOTIVO, comprobanteTurnoInEntity.getMotivoDescripcion());
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			byte[] byteArray = outStream.toByteArray();
			Reporte reporteOperaExterior = new Reporte();
			reporteOperaExterior.setTipoArchivo(TipoArchivoEnum.PDF);
			reporteOperaExterior.setBytes(byteArray);
			reporteOperaExterior.setNombre("Comprobante_TurnoWebRemoto.pdf");
			LOGGER.debug("TurnosWebDAO finalizando descargar comprobante Remoto");
			return reporteOperaExterior;
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
	}
}

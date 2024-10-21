/**
 * 
 */
package ar.com.santanderrio.obp.servicios.mya.dao.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.JaxbUtils;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.mya.MyaServices;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestino;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestinoV3;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaXmlRequest;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaXmlResponse;
import ar.com.santanderrio.obp.generated.webservices.mya.v2.Services;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteEstadoEnum;
import ar.com.santanderrio.obp.servicios.mya.dao.MyaDAO;
import ar.com.santanderrio.obp.servicios.mya.entities.GetEstadoClienteV3DTOOut;
import ar.com.santanderrio.obp.servicios.mya.entities.GetStatusClienteDTOOut;

/**
 * DAO para acceder a los servicios de MYA.
 * 
 * @author sergio.e.goldentair
 *
 */
@Component("myaDAO")
public class MyaDAOImpl implements MyaDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MyaDAOImpl.class);
	/** The Constant JKS de MYA. */
	private static final String JKS = "MYA";
	/** The Constant XMLNS con el string a quitar de los xml. */
	private static final String XMLNS = " xmlns=\"http://services.mya.ar.bsch\"";
	
	private static final String CABECERA_SOAP = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Header/><soapenv:Body>";
	
	private static final String FINAL_SOAP = "</soapenv:Body></soapenv:Envelope>";
	
	private static final String RESPUESTA_OK = "0";
	
	private static final String RESPUESTA_ERROR = "1";

	/** The sign. */
	@Autowired
	private Sign sign;

	/** The app encoding. */
	@Value("${APP.ENCODING}")
	private String appEncoding;

	/** Gestionar ws MyA. */
	@Autowired
	@Qualifier("gestionMya")
	private GestionarWS<MyaServices> wsMyaClient;

	/** Gestionar ws MyA. */
	@Autowired
	@Qualifier("gestionMya2")
	private GestionarWS<Services> wsMyaClientv2;

	/** Gestionar ws MyA. */
	@Autowired
	@Qualifier("gestionMya3")
	private GestionarWS<ar.com.santanderrio.obp.generated.webservices.mya.v3.Services> wsMyaClientv3;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.mya.dao.MyaDAO#invocarMya(ar.com.
	 * santanderrio.obp.generated.webservices.mya.dominio.MyaXmlRequest)
	 */
	@Override
	public MyaXmlResponse invocarMya(MyaXmlRequest request) throws DAOException {
		MyaServices services = null;
		MyaXmlResponse respuesta = null;
		try {
			MyaXmlRequest xmlAEnviar = generarRequestFirmado(request);
			LOGGER.info("Se invoca a la operacion {}.", xmlAEnviar.getHeader().getServicio());
			services = wsMyaClient.obtenerPort();
			respuesta = services.generalInvoke(xmlAEnviar);
		} finally {
			wsMyaClient.liberarPort(services);
		}
		return respuesta;
	}

	/**
	 * Recibe el objecto con los datos populados segun el negocio y Retorna un
	 * MyaXmlRequest con el xml procesado a enviar.
	 *
	 * @param token
	 *            the token
	 * @return el xml de respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	private MyaXmlRequest generarRequestFirmado(MyaXmlRequest token) throws DAOException {
		try {
			String xmlDatosConNS = JaxbUtils.transformarObjetoAXml(token.getDatosAFirmar().getDatos(), appEncoding,
					Boolean.TRUE, Boolean.FALSE, null);
			String xmlDatos = cleanNS(xmlDatosConNS);
			LOGGER.info("Xml a firmar {} con jks {}.", xmlDatos, JKS);
			String xmlFirmado = new String(sign.buildB64Signature(xmlDatos.getBytes(appEncoding), JKS, true));

			MyaXmlRequest tokenFirmado = new MyaXmlRequest();
			tokenFirmado.setHeader(token.getHeader());
			tokenFirmado.setXmlDatosFirmados(xmlFirmado);

			return tokenFirmado;
		} catch (JAXBException e) {
			LOGGER.error("Error al transformar el token a xml.", e);
			throw new DAOException("Error de jaxb en MyaXmlRequest");
		} catch (UnsupportedEncodingException uee) {
			LOGGER.error("Error al transformar el token a xml.", uee);
			throw new DAOException("Error de jaxb en MyaXmlRequest");
		}
	}

	/**
	 * Limpia el xml del namespace que se agrega por el package-info.
	 *
	 * @param xmlDatosConNS
	 *            the xml datos con NS
	 * @return the string
	 */
	private String cleanNS(String xmlDatosConNS) {
		return StringUtils.remove(xmlDatosConNS, XMLNS);
	}

	@Override
	public GetStatusClienteDTOOut invocarGetStatusCliente(MyaXmlRequest request) throws DAOException {
		Services services = null;
		String respuestaString = StringUtils.EMPTY;
		GetStatusClienteDTOOut respuesta = new GetStatusClienteDTOOut();

		try {
			String xmlRequest = JaxbUtils.transformarObjetoAXml(request, appEncoding,
						Boolean.TRUE, Boolean.FALSE, null);
		
			xmlRequest = StringUtils.remove(xmlRequest, XMLNS).replaceAll("\\s","");

			services = wsMyaClientv2.obtenerPort();
			respuestaString = services.getStatusCliente(xmlRequest);
			respuestaString = StringUtils.remove(respuestaString, CABECERA_SOAP);
			respuestaString = StringUtils.remove(respuestaString, FINAL_SOAP);
			respuesta = generarObjeto(respuestaString);
		} catch (JAXBException e) {
			LOGGER.error("Error al transformar el token a xml.", e);
			throw new DAOException("Error de jaxb");
		} finally {
			wsMyaClientv2.liberarPort(services);
		}

		return respuesta;
	}

	@Override
	public GetEstadoClienteV3DTOOut invocarGetEstadoClienteV3(MyaXmlRequest request) throws DAOException {
		ar.com.santanderrio.obp.generated.webservices.mya.v3.Services services = null;
		String respuestaString = StringUtils.EMPTY;
		GetEstadoClienteV3DTOOut respuesta = new GetEstadoClienteV3DTOOut();
		try {
			String xmlRequest = JaxbUtils.transformarObjetoAXml(request, appEncoding,
						Boolean.TRUE, Boolean.FALSE, null);
			xmlRequest = StringUtils.remove(xmlRequest, XMLNS).replaceAll("\\s","");
			services = wsMyaClientv3.obtenerPort();
			respuestaString = services.getEstadoClienteV3(xmlRequest);
			respuestaString = StringUtils.remove(respuestaString, CABECERA_SOAP);
			respuestaString = StringUtils.remove(respuestaString, FINAL_SOAP);
			respuesta = generarObjetoV3(respuestaString);
		} catch (JAXBException e) {
			LOGGER.error("Error al transformar el token a xml.", e);
			throw new DAOException("Error de jaxb");
		} finally {
			wsMyaClientv3.liberarPort(services);
		}
		return respuesta;
	}

	/**
	 * generarObjetoV3
	 *
	 * @param xml the xml
	 * @return the GetEstadoClienteV3DTOOut
	 * @throws DAOException the DAO exception
	 */
	private GetEstadoClienteV3DTOOut generarObjetoV3(String xml) throws DAOException {
		xml = xml.replaceAll("><", ">|<");
		String[]  xml2 = xml.split("\\|");
		
		GetEstadoClienteV3DTOOut getEstadoClienteV3DTOOut = new GetEstadoClienteV3DTOOut();
		List<MyaDestinoV3> listMyaDestino = new ArrayList<MyaDestinoV3>();
		if (xml2[1].startsWith("<CodRet>")) {
			getEstadoClienteV3DTOOut.setCodigoRetorno(xml2[1].replaceAll("<CodRet>", "").replaceAll("</CodRet>", ""));
		}
		
		if (xml2[2].startsWith("<ClienteEstado>")) {
			getEstadoClienteV3DTOOut.setClienteEstado(xml2[2].replaceAll("<ClienteEstado>", "").replaceAll("</ClienteEstado>", ""));
		}
		
		if (!"0".equals(getEstadoClienteV3DTOOut.getCodigoRetorno())) {
			throw new DAOException("Error de servicio");
		}
		if(ClienteEstadoEnum.NO_SUSCRIPTO.getCodigo().equals(getEstadoClienteV3DTOOut.getClienteEstado())) {
		    getEstadoClienteV3DTOOut.setListMyaDestino(listMyaDestino);
            return getEstadoClienteV3DTOOut;
		}
		if (xml2[5].startsWith("<Destino id=\"1\">")) {
			MyaDestinoV3 destino1 = new MyaDestinoV3();
			if (xml2[6].startsWith("<DestinoTipo>")) {
				destino1.setTipo(xml2[6].replaceAll("<DestinoTipo>", "").replaceAll("</DestinoTipo>", ""));
			}
			if (xml2[7].startsWith("<DestinoSecuencia>")) {
				destino1.setSecuencia(xml2[7].replaceAll("<DestinoSecuencia>", "").replaceAll("</DestinoSecuencia>", ""));
			}
			if (xml2[8].startsWith("<DestinoDescripcion>")) {
				destino1.setDescripcion(xml2[8].replaceAll("<DestinoDescripcion>", "").replaceAll("</DestinoDescripcion>", ""));
			}
			if (xml2[9].startsWith("<DestinoValidado>")) {
				destino1.setDestinoValidado(xml2[9].replaceAll("<DestinoValidado>", "").replaceAll("</DestinoValidado>", ""));
			}
			if (xml2[10].startsWith("<DestinoAlta>")) {
				destino1.setDestinoAlta(xml2[10].replaceAll("<DestinoAlta>", "").replaceAll("</DestinoAlta>", ""));
			}
			if (xml2[11].startsWith("<DestinoModificado>")) {
				destino1.setDestinoModificado(xml2[11].replaceAll("<DestinoModificado>", "").replaceAll("</DestinoModificado>", ""));
			}
			if (xml2[12].startsWith("<DestinoCanal>")) {
				destino1.setDestinoCanal(xml2[12].replaceAll("<DestinoCanal>", "").replaceAll("</DestinoCanal>", ""));
			}
			if (xml2[13].startsWith("<DestinoSubCanal>")) {
				destino1.setDestinoSubCanal(xml2[13].replaceAll("<DestinoSubCanal>", "").replaceAll("</DestinoSubCanal>", ""));
			}
			if (xml2[14].startsWith("<DestinoEmpresaCel>")) {
				destino1.setEmpresaCel(xml2[14].replaceAll("<DestinoEmpresaCel>", "").replaceAll("</DestinoEmpresaCel>", ""));
			}
			if (xml2[15].startsWith("<DestinoEstado>")) {
				destino1.setEstado(xml2[15].replaceAll("<DestinoEstado>", "").replaceAll("</DestinoEstado>", ""));
			}
			if (xml2[16].startsWith("</Destino>")) {
				listMyaDestino.add(destino1);
			}	
		}
		
		if (xml2[17].startsWith("<Destino id=\"2\">")) {
			MyaDestinoV3 destino2 = new MyaDestinoV3();
			if (xml2[18].startsWith("<DestinoTipo>")) {
				destino2.setTipo(xml2[18].replaceAll("<DestinoTipo>", "").replaceAll("</DestinoTipo>", ""));
			}
			if (xml2[19].startsWith("<DestinoSecuencia>")) {
				destino2.setSecuencia(xml2[19].replaceAll("<DestinoSecuencia>", "").replaceAll("</DestinoSecuencia>", ""));
			}
			if (xml2[20].startsWith("<DestinoDescripcion>")) {
				destino2.setDescripcion(xml2[20].replaceAll("<DestinoDescripcion>", "").replaceAll("</DestinoDescripcion>", ""));
			}
			if (xml2[21].startsWith("<DestinoValidado>")) {
				destino2.setDestinoValidado(xml2[21].replaceAll("<DestinoValidado>", "").replaceAll("</DestinoValidado>", ""));
			}
			if (xml2[22].startsWith("<DestinoAlta>")) {
				destino2.setDestinoAlta(xml2[22].replaceAll("<DestinoAlta>", "").replaceAll("</DestinoAlta>", ""));
			}
			if (xml2[23].startsWith("<DestinoModificado>")) {
				destino2.setDestinoModificado(xml2[23].replaceAll("<DestinoModificado>", "").replaceAll("</DestinoModificado>", ""));
			}
			if (xml2[24].startsWith("<DestinoCanal>")) {
				destino2.setDestinoCanal(xml2[24].replaceAll("<DestinoCanal>", "").replaceAll("</DestinoCanal>", ""));
			}
			if (xml2[25].startsWith("<DestinoSubCanal>")) {
				destino2.setDestinoSubCanal(xml2[25].replaceAll("<DestinoSubCanal>", "").replaceAll("</DestinoSubCanal>", ""));
			}
			if (xml2[26].startsWith("<DestinoEmpresaCel>")) {
				destino2.setEmpresaCel(xml2[26].replaceAll("<DestinoEmpresaCel>", "").replaceAll("</DestinoEmpresaCel>", ""));
			}
			if (xml2[27].startsWith("<DestinoEstado>")) {
				destino2.setEstado(xml2[27].replaceAll("<DestinoEstado>", "").replaceAll("</DestinoEstado>", ""));
			}
			if (xml2[28].startsWith("</Destino>")) {
				listMyaDestino.add(destino2);
			}
		} else {
			getEstadoClienteV3DTOOut.setListMyaDestino(listMyaDestino);
			return getEstadoClienteV3DTOOut;
		}
		
		if (xml2[29].startsWith("<Destino id=\"3\">")) {
			MyaDestinoV3 destino3 = new MyaDestinoV3();
			if (xml2[30].startsWith("<DestinoTipo>")) {
				destino3.setTipo(xml2[30].replaceAll("<DestinoTipo>", "").replaceAll("</DestinoTipo>", ""));
			}
			if (xml2[31].startsWith("<DestinoSecuencia>")) {
				destino3.setSecuencia(xml2[31].replaceAll("<DestinoSecuencia>", "").replaceAll("</DestinoSecuencia>", ""));
			}
			if (xml2[32].startsWith("<DestinoDescripcion>")) {
				destino3.setDescripcion(xml2[32].replaceAll("<DestinoDescripcion>", "").replaceAll("</DestinoDescripcion>", ""));
			}
			if (xml2[33].startsWith("<DestinoValidado>")) {
				destino3.setDestinoValidado(xml2[33].replaceAll("<DestinoValidado>", "").replaceAll("</DestinoValidado>", ""));
			}
			if (xml2[34].startsWith("<DestinoAlta>")) {
				destino3.setDestinoAlta(xml2[34].replaceAll("<DestinoAlta>", "").replaceAll("</DestinoAlta>", ""));
			}
			if (xml2[35].startsWith("<DestinoModificado>")) {
				destino3.setDestinoModificado(xml2[35].replaceAll("<DestinoModificado>", "").replaceAll("</DestinoModificado>", ""));
			}
			if (xml2[36].startsWith("<DestinoCanal>")) {
				destino3.setDestinoCanal(xml2[36].replaceAll("<DestinoCanal>", "").replaceAll("</DestinoCanal>", ""));
			}
			if (xml2[37].startsWith("<DestinoSubCanal>")) {
				destino3.setDestinoSubCanal(xml2[37].replaceAll("<DestinoSubCanal>", "").replaceAll("</DestinoSubCanal>", ""));
			}
			if (xml2[38].startsWith("<DestinoEmpresaCel>")) {
				destino3.setEmpresaCel(xml2[38].replaceAll("<DestinoEmpresaCel>", "").replaceAll("</DestinoEmpresaCel>", ""));
			}
			if (xml2[39].startsWith("<DestinoEstado>")) {
				destino3.setEstado(xml2[39].replaceAll("<DestinoEstado>", "").replaceAll("</DestinoEstado>", ""));
			}
			if (xml2[40].startsWith("</Destino>")) {
				listMyaDestino.add(destino3);
			}
		} else {
			getEstadoClienteV3DTOOut.setListMyaDestino(listMyaDestino);
			return getEstadoClienteV3DTOOut;
		}
		
		if (xml2[41].startsWith("<Destino id=\"4\">")) {
			MyaDestinoV3 destino4 = new MyaDestinoV3();
			if (xml2[42].startsWith("<DestinoTipo>")) {
				destino4.setTipo(xml2[42].replaceAll("<DestinoTipo>", "").replaceAll("</DestinoTipo>", ""));
			}
			if (xml2[43].startsWith("<DestinoSecuencia>")) {
				destino4.setSecuencia(xml2[43].replaceAll("<DestinoSecuencia>", "").replaceAll("</DestinoSecuencia>", ""));
			}
			if (xml2[44].startsWith("<DestinoDescripcion>")) {
				destino4.setDescripcion(xml2[44].replaceAll("<DestinoDescripcion>", "").replaceAll("</DestinoDescripcion>", ""));
			}
			if (xml2[45].startsWith("<DestinoValidado>")) {
				destino4.setDestinoValidado(xml2[45].replaceAll("<DestinoValidado>", "").replaceAll("</DestinoValidado>", ""));
			}
			if (xml2[46].startsWith("<DestinoAlta>")) {
				destino4.setDestinoAlta(xml2[46].replaceAll("<DestinoAlta>", "").replaceAll("</DestinoAlta>", ""));
			}
			if (xml2[47].startsWith("<DestinoModificado>")) {
				destino4.setDestinoModificado(xml2[47].replaceAll("<DestinoModificado>", "").replaceAll("</DestinoModificado>", ""));
			}
			if (xml2[48].startsWith("<DestinoCanal>")) {
				destino4.setDestinoCanal(xml2[48].replaceAll("<DestinoCanal>", "").replaceAll("</DestinoCanal>", ""));
			}
			if (xml2[49].startsWith("<DestinoSubCanal>")) {
				destino4.setDestinoSubCanal(xml2[49].replaceAll("<DestinoSubCanal>", "").replaceAll("</DestinoSubCanal>", ""));
			}
			if (xml2[50].startsWith("<DestinoEmpresaCel>")) {
				destino4.setEmpresaCel(xml2[50].replaceAll("<DestinoEmpresaCel>", "").replaceAll("</DestinoEmpresaCel>", ""));
			}
			if (xml2[51].startsWith("<DestinoEstado>")) {
				destino4.setEstado(xml2[51].replaceAll("<DestinoEstado>", "").replaceAll("</DestinoEstado>", ""));
			}
			if (xml2[52].startsWith("</Destino>")) {
				listMyaDestino.add(destino4);
			}
		} else {
			getEstadoClienteV3DTOOut.setListMyaDestino(listMyaDestino);
			return getEstadoClienteV3DTOOut;
		}
		
		getEstadoClienteV3DTOOut.setListMyaDestino(listMyaDestino);
		return getEstadoClienteV3DTOOut;
	}

	@Override
	public String invocarConfirmarEmail(MyaXmlRequest request) throws DAOException {
		Services services = null;
		String respuestaString = StringUtils.EMPTY;

		try {
			String mailFirmado = generarMailFirmado(request);
			MyaXmlRequest myaXmlRequest = new MyaXmlRequest();
			myaXmlRequest.setHeader(request.getHeader());
			myaXmlRequest.setXmlDatosFirmados(mailFirmado);
			String xmlRequest = JaxbUtils.transformarObjetoAXml(myaXmlRequest, appEncoding,
						Boolean.TRUE, Boolean.FALSE, null);
			
			xmlRequest = StringUtils.remove(xmlRequest, XMLNS).replaceAll("\\s","");
			services = wsMyaClientv2.obtenerPort();
			respuestaString = services.confirmarEmail(xmlRequest);
			respuestaString = StringUtils.remove(respuestaString, CABECERA_SOAP);
			respuestaString = StringUtils.remove(respuestaString, FINAL_SOAP);
		} catch (JAXBException e) {
			LOGGER.error("Error al transformar el token a xml.", e);
			throw new DAOException("Error de jaxb");
		} finally {
			wsMyaClientv2.liberarPort(services);
		}

		if (respuestaString.contains("<CodRet>0</CodRet>")) {
			return RESPUESTA_OK;
		} else {
			return RESPUESTA_ERROR;
		}
	}
	
	private String generarMailFirmado(MyaXmlRequest token) throws DAOException {
		try {
			String xmlDatosConNS = JaxbUtils.transformarObjetoAXml(token.getDatosAFirmar().getDatos(), appEncoding,
					Boolean.TRUE, Boolean.FALSE, null);
			String xmlDatos = cleanNS(xmlDatosConNS);
			LOGGER.info("Xml a firmar {} con jks {}.", xmlDatos, JKS);
			return new String(sign.buildB64Signature(xmlDatos.getBytes(appEncoding), JKS, true));
		} catch (JAXBException e) {
			LOGGER.error("Error al transformar el token a xml.", e);
			throw new DAOException("Error de jaxb en MyaXmlRequest");
		} catch (UnsupportedEncodingException uee) {
			LOGGER.error("Error al transformar el token a xml.", uee);
			throw new DAOException("Error de jaxb en MyaXmlRequest");
		}
	}
	
	private GetStatusClienteDTOOut generarObjeto(String xml) throws DAOException{
		
		xml = xml.replaceAll("><", ">|<");
		String[]  xml2 = xml.split("\\|");
		
		GetStatusClienteDTOOut getStatusClienteDTOOut = new GetStatusClienteDTOOut();
		List<MyaDestino> listMyaDestino = new ArrayList<MyaDestino>();
		if (xml2[1].startsWith("<CodRet>")) {
			getStatusClienteDTOOut.setCodigoRetorno(xml2[1].replaceAll("<CodRet>", "").replaceAll("</CodRet>", ""));
		}
		
		if (xml2[2].startsWith("<ClienteEstado>")) {
			getStatusClienteDTOOut.setClienteEstado(xml2[2].replaceAll("<ClienteEstado>", "").replaceAll("</ClienteEstado>", ""));
		}
		
		if (!"0".equals(getStatusClienteDTOOut.getCodigoRetorno())) {
			throw new DAOException("Error de servicio");
		}
		if(ClienteEstadoEnum.NO_SUSCRIPTO.getCodigo().equals(getStatusClienteDTOOut.getClienteEstado())) {
		    getStatusClienteDTOOut.setListMyaDestino(listMyaDestino);
            return getStatusClienteDTOOut;
		}
		if (xml2[4].startsWith("<Destino id=\"1\">")) {
			MyaDestino destino1 = new MyaDestino();
			if (xml2[5].startsWith("<DestinoTipo>")) {
				destino1.setTipo(xml2[5].replaceAll("<DestinoTipo>", "").replaceAll("</DestinoTipo>", ""));
			}
			if (xml2[6].startsWith("<DestinoSecuencia>")) {
				destino1.setSecuencia(xml2[6].replaceAll("<DestinoSecuencia>", "").replaceAll("</DestinoSecuencia>", ""));
			}
			if (xml2[7].startsWith("<DestinoDescripcion>")) {
				destino1.setDescripcion(xml2[7].replaceAll("<DestinoDescripcion>", "").replaceAll("</DestinoDescripcion>", ""));
			}
			if (xml2[8].startsWith("<DestinoValidado>")) {
				destino1.setDestinoValidado(xml2[8].replaceAll("<DestinoValidado>", "").replaceAll("</DestinoValidado>", ""));
			}
			if (xml2[9].startsWith("<DestinoEmpresaCel>")) {
				destino1.setEmpresaCel(xml2[9].replaceAll("<DestinoEmpresaCel>", "").replaceAll("</DestinoEmpresaCel>", ""));
			}
			if (xml2[10].startsWith("<DestinoEstado>")) {
				destino1.setEstado(xml2[10].replaceAll("<DestinoEstado>", "").replaceAll("</DestinoEstado>", ""));
			}
			if (xml2[11].startsWith("</Destino>")) {
				listMyaDestino.add(destino1);
			}	
		}
		
		if (xml2[12].startsWith("<Destino id=\"2\">")) {
			MyaDestino destino2 = new MyaDestino();
			if (xml2[13].startsWith("<DestinoTipo>")) {
				destino2.setTipo(xml2[13].replaceAll("<DestinoTipo>", "").replaceAll("</DestinoTipo>", ""));
			}
			if (xml2[14].startsWith("<DestinoSecuencia>")) {
				destino2.setSecuencia(xml2[14].replaceAll("<DestinoSecuencia>", "").replaceAll("</DestinoSecuencia>", ""));
			}
			if (xml2[15].startsWith("<DestinoDescripcion>")) {
				destino2.setDescripcion(xml2[15].replaceAll("<DestinoDescripcion>", "").replaceAll("</DestinoDescripcion>", ""));
			}
			if (xml2[16].startsWith("<DestinoValidado>")) {
				destino2.setDestinoValidado(xml2[16].replaceAll("<DestinoValidado>", "").replaceAll("</DestinoValidado>", ""));
			}
			if (xml2[17].startsWith("<DestinoEmpresaCel>")) {
				destino2.setEmpresaCel(xml2[17].replaceAll("<DestinoEmpresaCel>", "").replaceAll("</DestinoEmpresaCel>", ""));
			}
			if (xml2[18].startsWith("<DestinoEstado>")) {
				destino2.setEstado(xml2[18].replaceAll("<DestinoEstado>", "").replaceAll("</DestinoEstado>", ""));
			}
			if (xml2[19].startsWith("</Destino>")) {
				listMyaDestino.add(destino2);
			}
		} else {
			getStatusClienteDTOOut.setListMyaDestino(listMyaDestino);
			return getStatusClienteDTOOut;
		}
		
		if (xml2[20].startsWith("<Destino id=\"3\">")) {
			MyaDestino destino3 = new MyaDestino();
			if (xml2[21].startsWith("<DestinoTipo>")) {
				destino3.setTipo(xml2[21].replaceAll("<DestinoTipo>", "").replaceAll("</DestinoTipo>", ""));
			}
			if (xml2[22].startsWith("<DestinoSecuencia>")) {
				destino3.setSecuencia(xml2[22].replaceAll("<DestinoSecuencia>", "").replaceAll("</DestinoSecuencia>", ""));
			}
			if (xml2[23].startsWith("<DestinoDescripcion>")) {
				destino3.setDescripcion(xml2[23].replaceAll("<DestinoDescripcion>", "").replaceAll("</DestinoDescripcion>", ""));
			}
			if (xml2[24].startsWith("<DestinoValidado>")) {
				destino3.setDestinoValidado(xml2[24].replaceAll("<DestinoValidado>", "").replaceAll("</DestinoValidado>", ""));
			}
			if (xml2[25].startsWith("<DestinoEmpresaCel>")) {
				destino3.setEmpresaCel(xml2[25].replaceAll("<DestinoEmpresaCel>", "").replaceAll("</DestinoEmpresaCel>", ""));
			}
			if (xml2[26].startsWith("<DestinoEstado>")) {
				destino3.setEstado(xml2[26].replaceAll("<DestinoEstado>", "").replaceAll("</DestinoEstado>", ""));
			}
			if (xml2[27].startsWith("</Destino>")) {
				listMyaDestino.add(destino3);
			}
		} else {
			getStatusClienteDTOOut.setListMyaDestino(listMyaDestino);
			return getStatusClienteDTOOut;
		}
		
		if (xml2[28].startsWith("<Destino id=\"4\">")) {
			MyaDestino destino4 = new MyaDestino();
			if (xml2[29].startsWith("<DestinoTipo>")) {
				destino4.setTipo(xml2[29].replaceAll("<DestinoTipo>", "").replaceAll("</DestinoTipo>", ""));
			}
			if (xml2[30].startsWith("<DestinoSecuencia>")) {
				destino4.setSecuencia(xml2[30].replaceAll("<DestinoSecuencia>", "").replaceAll("</DestinoSecuencia>", ""));
			}
			if (xml2[31].startsWith("<DestinoDescripcion>")) {
				destino4.setDescripcion(xml2[31].replaceAll("<DestinoDescripcion>", "").replaceAll("</DestinoDescripcion>", ""));
			}
			if (xml2[32].startsWith("<DestinoValidado>")) {
				destino4.setDestinoValidado(xml2[32].replaceAll("<DestinoValidado>", "").replaceAll("</DestinoValidado>", ""));
			}
			if (xml2[33].startsWith("<DestinoEmpresaCel>")) {
				destino4.setEmpresaCel(xml2[33].replaceAll("<DestinoEmpresaCel>", "").replaceAll("</DestinoEmpresaCel>", ""));
			}
			if (xml2[34].startsWith("<DestinoEstado>")) {
				destino4.setEstado(xml2[34].replaceAll("<DestinoEstado>", "").replaceAll("</DestinoEstado>", ""));
			}
			if (xml2[35].startsWith("</Destino>")) {
				listMyaDestino.add(destino4);
			}
		} else {
			getStatusClienteDTOOut.setListMyaDestino(listMyaDestino);
			return getStatusClienteDTOOut;
		}
		
		getStatusClienteDTOOut.setListMyaDestino(listMyaDestino);
		return getStatusClienteDTOOut;
		
	}

}

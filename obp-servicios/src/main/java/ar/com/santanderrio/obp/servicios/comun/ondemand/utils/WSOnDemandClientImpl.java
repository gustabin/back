/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.ondemand.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.dom4j.io.DocumentSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.ondemand.ws.OnDemandResponse;
import ar.com.santanderrio.obp.servicios.comun.ondemand.ws.OnDemandResponseData;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODRequest;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODResponse;

/**
 * Implementacion para consumir ws ondemand temporal hasta resolver problema de
 * classloader de was con libs de cxf, cxf-rt-transports-http.
 * 
 * @author sergio.e.goldentair
 *
 */
@Component("wSOnDemandClient")
public class WSOnDemandClientImpl implements WSOnDemandClient {
	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(WSOnDemandClientImpl.class);
	/**
	 * media type consume del ws.
	 */
	private static final String WS_ACCEPT = "multipart/mixed; type=\"text/xml\";";

	/** The rest web client. */
	@Autowired
	private RestWebClient restWebClient;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comun.ondemand.utils.WSOnDemandClient#
	 * send(ar.com.santanderrio.obp.servicios.ondemand.entities.WSODRequest,
	 * java.lang.String)
	 */
	@Override
	public WSODResponse send(WSODRequest req, String nombreWS) throws WSODException {
		LOGGER.info("OnDemand Nombre de servicio a invocar {}.", nombreWS);
		InputStream is = null;
		ByteArrayInputStream bais = null;
		OnDemandResponse respuestaData = new OnDemandResponse();

		try {
			WebClient clienteWS = restWebClient.obtenerClienteRest(nombreWS);
			clienteWS.type(MediaType.TEXT_XML).accept(WS_ACCEPT);

			MultipartBody respuesta = clienteWS.post(obtenerRequest(req), MultipartBody.class);
			for (int i = 0; i < respuesta.getAllAttachments().size(); i++) {
				Attachment attachment = respuesta.getAllAttachments().get(i);
				if (attachment.getContentType().toString().contains(MediaType.TEXT_XML_TYPE.toString())
						&& NumberUtils.INTEGER_ZERO.equals(i)) {
					respuestaData = getRespuestaData(attachment.getObject(String.class));
				} else {
					is = attachment.getDataHandler().getInputStream();
					break;
				}
			}
			if (is == null) {
				LOGGER.info("No hay datos adjuntos.");
			} else {
				byte[] bytes = IOUtils.toByteArray(is);
				bais = new ByteArrayInputStream(bytes);
			}
		} catch (ResponseProcessingException e) {
			String respuestaError = e.getResponse().readEntity(String.class);
			LOGGER.error("Error al invocar OnDemand {}", respuestaError, e);
			respuestaData = getRespuestaData(respuestaError);
		} catch (DAOException e) {
			throw new WSODException(e);
		} catch (IOException e) {
			LOGGER.error("Error al manipular el array de byte atachado en la respuesta de OnDemand.", e);
			throw new WSODException(e);
		} catch (RuntimeException e) {
			LOGGER.error("Error al manipular el array de byte atachado en la respuesta de OnDemand.", e);
			throw new WSODException(e);
		}
		LOGGER.info("Status de respuesta obtenido {}.", respuestaData);
		return new WSODResponse(respuestaData.getCodigoRetorno(), respuestaData.getInfo(), respuestaData.getError(),
				bais);
	}

	/**
	 * Parsea el objeto respuesta con el status de la operacion. Ej:
	 * 
	 * <pre>
	 *     <code>
	 *         <soapenv:Envelope xmlns:soapenv=
	"http://schemas.xmlsoap.org/soap/envelope/"
	 *                           xmlns:soapenc=
	"http://schemas.xmlsoap.org/soap/encoding/" 
	 *                           xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
	 *                           xmlns:xsi=
	"http://www.w3.org/2001/XMLSchema-instance">
	 *             <soapenv:Header/>
	 *                 <soapenv:Body>
	 *                     <xml>    
	 *                         <cod-ret>0</cod-ret>
	 *                         <msj-info cod="2167" desc=
	"No documents meet the search criteria"/>
	 *                     </xml>
	 *             </soapenv:Body>
	 *         </soapenv:Envelope>
	 *     </code>
	 * </pre>
	 *
	 * @param respuetaOnDemand
	 *            the respueta on demand
	 * @return the respuesta data
	 */
	private OnDemandResponse getRespuestaData(String respuetaOnDemand) {
		LOGGER.info("Respuesta status onDemand {}", respuetaOnDemand);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		OnDemandResponse data = new OnDemandResponse();

		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			ByteArrayInputStream input = new ByteArrayInputStream(respuetaOnDemand.getBytes(CharEncoding.UTF_8));
			Document doc = dBuilder.parse(input);

			data.setCodigoRetorno(obtenerCodigoRetorno(doc));
			data.setError(obtenerList(doc, OnDemandResponse.MENSAJE_ERROR));
			data.setInfo(obtenerList(doc, OnDemandResponse.MENSAJE_INFO));
		} catch (ParserConfigurationException pce) {
			LOGGER.error("Error en la configuracion de dom para parsear el xml de respuesta.", pce);
		} catch (SAXException se) {
			LOGGER.error("Error al parsear el xml de respuesta", se);
		} catch (IOException e) {
			LOGGER.error("Error de lectura en la respuesta", e);
		}

		return data;
	}

	/**
	 * Parsear el listado de elementos msj-info e msj-error contenido en la
	 * respuesta.
	 * 
	 * <pre>
	 *     <code>
	 *         <msj-error cod="0" desc="Error en Dispatcher.execute():"/>
	 *         <msj-info cod="2167" desc=
	"No documents meet the search criteria"/>
	 *     </code>
	 * </pre>
	 *
	 * @param doc
	 *            the doc
	 * @param nodo
	 *            the nodo
	 * @return the list
	 */
	private List<OnDemandResponseData> obtenerList(Document doc, String nodo) {
		List<OnDemandResponseData> listaRetorno = new ArrayList<OnDemandResponseData>();
		NodeList nodoList = doc.getElementsByTagName(nodo);
		if (nodoList == null || nodoList.getLength() == 0) {
			LOGGER.info("El elemento {} no esta presente en la respuesta", nodo);
		} else {
			for (int posicionEnNodoLista = 0; posicionEnNodoLista < nodoList.getLength(); posicionEnNodoLista++) {
				Node nodoEnPosicion = nodoList.item(posicionEnNodoLista);
				OnDemandResponseData dataItem = new OnDemandResponseData();
				dataItem.setCod(
						nodoEnPosicion.getAttributes().getNamedItem(OnDemandResponseData.CODIGO).getNodeValue());
				dataItem.setDesc(
						nodoEnPosicion.getAttributes().getNamedItem(OnDemandResponseData.DESCRIPCION).getNodeValue());
				listaRetorno.add(dataItem);
			}
		}
		return listaRetorno;
	}

	/**
	 * Obtener el codigo de error en el xml retornado por ondemand.
	 * 
	 * <pre>
	 *     <code>
	 *         <cod-ret>1</cod-ret>
	 *     </code>
	 * </pre>
	 *
	 * @param doc
	 *            the doc
	 * @return codigo retornado.
	 */
	private String obtenerCodigoRetorno(Document doc) {
		NodeList codigoRet = doc.getElementsByTagName(OnDemandResponse.CODIGO_RETORNO);
		String codigo = "";
		if (codigoRet == null || codigoRet.getLength() == 0) {
			LOGGER.info("El elemento {} no esta presente en la respuesta", OnDemandResponse.CODIGO_RETORNO);
		} else {
			codigo = codigoRet.item(0).getTextContent();
		}
		return codigo;
	}

	/**
	 * Crear el SoapRequest que sera utilizado para consultar el ws. Ej:
	 * 
	 * <pre>
	 *     <code>
	 *     <consulta>
	 *         <nombre>CONSULTA_RESUMEN_POR_FECHAS_TC</nombre>
	 *         <version>100</version>
	 *         <canal>04</canal>
	 *         <subcanal>99</subcanal>
	 *         <usuario>odtest</usuario>
	 *         <clave>prueba2006</clave>
	 *         <parametros>
	 *             <usuario-consulta>01576531</usuario-consulta>
	 *         </parametros>
	 *         <filtros>
	 *             <paquete>P</paquete>
	 *             <fecha-desde>14/03/15</fecha-desde>
	 *             <fecha-hasta>14/09/16</fecha-hasta>
	 *             <cuenta>42728401</cuenta>
	 *             <sucursal>0</sucursal>
	 *             <persona>I</persona>
	 *             <proveedor-tarjeta>VISA</proveedor-tarjeta>
	 *         </filtros>
	 *     </consulta>
	 *     </code>
	 * </pre>
	 *
	 * @param req
	 *            the req
	 * @return soap xml con el request.
	 * @throws DAOException
	 *             the DAO exception
	 */
	private String obtenerRequest(WSODRequest req) throws DAOException {
		StringBuilder request = new StringBuilder(
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body>");

		String body = null;
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			ByteArrayOutputStream s = new ByteArrayOutputStream();
			t.setOutputProperty(OutputKeys.INDENT, "no");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.transform(new DocumentSource(req.getAsDOM()), new StreamResult(s));
			body = new String(s.toByteArray());
		} catch (TransformerConfigurationException tce) {
			LOGGER.error("Error con la configuracion para obtener el xml ondemand request.", tce);
			throw new DAOException(tce);
		} catch (TransformerFactoryConfigurationError tfce) {
			LOGGER.error("Error con la factoria para la configuracion para obtener el xml ondemand request.", tfce);
			throw new DAOException(tfce);
		} catch (TransformerException te) {
			LOGGER.error("Error con la configuracion para obtener el xml ondemand request.", te);
			throw new DAOException(te);
		}
		if (StringUtils.isEmpty(body)) {
			throw new DAOException("No se genero el body para armar el request OnDemand, no se podra consultar el ws.");
		}
		request.append(body).append("</soapenv:Body></soapenv:Envelope>");
		LOGGER.info("Request generado para OnDemand {}", request.toString());
		return request.toString();
	}
}

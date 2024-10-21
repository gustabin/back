/**
 * 
 */
package ar.com.santanderrio.obp.base.comun;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

// TODO: Auto-generated Javadoc
/**
 * Clase utilitaria para realizar Marshall o UnMarshall de clases marcadas con
 * JAXB.
 * 
 * @author sergio.e.goldentair
 *
 */
public class JaxbUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(JaxbUtils.class);

	/** Excluir del xml el encabezado. */
	private static final String NO_XML = "com.sun.xml.bind.xmlDeclaration";

	/**
	 * hacer el marshall de un objeto a xml con la configuracion indicada
	 * (formateado, encoding, y schema). Undo
	 * {@link transformarXmlAObject(String, Class<G>)}
	 *
	 * @param <G>
	 *            the generic type
	 * @param objetoToXml
	 *            the objeto to xml
	 * @param enconding
	 *            the enconding
	 * @param formateado
	 *            the formateado
	 * @param xmlConHeader
	 *            incluir el encabezado xml
	 * @param schema
	 *            the schema
	 * @return G
	 * @throws JAXBException
	 *             the JAXB exception
	 */
	public static <G> String transformarObjetoAXml(G objetoToXml, String enconding, Boolean formateado,
			Boolean xmlConHeader, String schema) throws JAXBException {
		LOGGER.info("Objeto a transformar {}", objetoToXml.toString());
		JAXBContext jaxbContext = JAXBContext.newInstance(objetoToXml.getClass());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		if (StringUtils.isNotEmpty(enconding)) {
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, enconding);
		}
		// el xml generado este tabulado
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formateado);
		if (schema != null) {
			jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schema);
		}
		jaxbMarshaller.setProperty(NO_XML, xmlConHeader);
		StringWriter writer = new StringWriter();
		jaxbMarshaller.marshal(objetoToXml, writer);
		String salida = writer.toString();

		LOGGER.info("Marshall generado: \r\n{}\r\n", salida);
		return salida;
	}

	/**
	 * Transformar un Xml a un Objeto. Undo {@link transformarObjetoAXml(G,
	 * String, Boolean, String)}
	 *
	 * @param <G>
	 *            the generic type
	 * @param xml
	 *            the xml
	 * @param clazz
	 *            the clazz
	 * @return the g
	 * @throws JAXBException
	 *             the JAXB exception
	 */
	@SuppressWarnings("unchecked")
	public static <G> G transformarXmlAObject(String xml, Class<G> clazz) throws JAXBException {
		String pretty = format(xml);
		LOGGER.info("Xml a transformar \r\n{}\r\n", pretty);
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xml);
		G respuesta = (G) jaxbUnmarshaller.unmarshal(reader);
		LOGGER.info("Objeto transformado {}", respuesta.toString());
		return respuesta;
	}

	/**
	 * Formatear el xml de entrada.
	 *
	 * @param xml
	 *            the xml
	 * @return the string
	 */
	private static String format(String xml) {
		try {
			final InputSource src = new InputSource(new StringReader(xml));
			final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src)
					.getDocumentElement();
			final Boolean keepDeclaration = Boolean.valueOf(xml.startsWith("<?xml"));
			final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
			final LSSerializer writer = impl.createLSSerializer();

			writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
			writer.getDomConfig().setParameter("xml-declaration", keepDeclaration);

			return writer.writeToString(document);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

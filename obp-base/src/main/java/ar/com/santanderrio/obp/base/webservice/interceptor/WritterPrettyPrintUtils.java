/**
 * 
 */
package ar.com.santanderrio.obp.base.webservice.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.staxutils.PrettyPrintXMLStreamWriter;
import org.apache.cxf.staxutils.StaxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.context.ContextHolder;

// TODO: Auto-generated Javadoc
/**
 * The Class WritterPrettyPrintUtils.
 *
 * @author sergio.e.goldentair
 */
public class WritterPrettyPrintUtils {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(WritterPrettyPrintUtils.class);

	/**
	 * Limpia el response eliminando palabras definidas.
	 *
	 * @param response
	 *            the response
	 * @return the string
	 */
	private static String limpiarResponse(String response) {

		String tag = ContextHolder.getContext().getEnvironment().getProperty("TAGS.A.ELIMINAR.XML");

		if (StringUtils.isEmpty(tag)) {
			return response;
		}
		String[] tagsAEliminar = tag.split(",");
		String respuesta = response;
		for (String palabraABuscar : tagsAEliminar) {

			String tagInicio = "<" + palabraABuscar + ">";
			String tagFinal = "</" + palabraABuscar + ">";

			if (respuesta.indexOf(tagInicio) != -1) {
				String start = respuesta.substring(0, respuesta.indexOf(tagInicio) + tagInicio.length());
				String end = respuesta.substring(respuesta.indexOf(tagFinal), respuesta.length());
				respuesta = start + "*****" + end;
			}
		}

		return RestMaskLoggingUtils.procesarLogRest(respuesta, tagsAEliminar);
	}

	/**
	 * Obtener el xml del Stream formateado.
	 *
	 * @param cos
	 *            the cos
	 * @return the pretty print
	 */
	public static String getPrettyPrint(CachedOutputStream cos) {
		StringWriter swriter = new StringWriter();
		try {
			XMLStreamWriter xwriter = StaxUtils.createXMLStreamWriter(swriter);
			xwriter = new PrettyPrintXMLStreamWriter(xwriter, 2);
			InputStream in = cos.getInputStream();
			BufferedReader r = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			try {
				StaxUtils.copy(new StreamSource(r), xwriter);
			} catch (XMLStreamException xse) {
				LOGGER.info("Error al generar el pretty print del xml.", xse);
			} finally {
				try {
					xwriter.flush();
					xwriter.close();
				} catch (XMLStreamException xse2) {
					LOGGER.info("Error al cerrar el recurso para el pretty print del xml.", xse2);
				}
				in.close();
			}
		} catch (IOException e) {
			LOGGER.debug("Error while pretty printing cxf message, returning what we got till now.", e);
		}
		return limpiarResponse(swriter.toString());
	}

}

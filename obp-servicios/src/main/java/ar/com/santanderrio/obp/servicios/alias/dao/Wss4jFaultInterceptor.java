/**
 * 
 */
package ar.com.santanderrio.obp.servicios.alias.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.webservice.interceptor.WritterPrettyPrintUtils;

/**
 * The Class Wss4jFaultInterceptor.
 *
 * @author sergio.e.goldentair
 */
public class Wss4jFaultInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Wss4jFaultInterceptor.class);

	/**
	 * Instantiates a new wss 4 j fault interceptor.
	 *
	 * @param phase
	 *            the phase
	 */
	public Wss4jFaultInterceptor(String phase) {
		super(phase);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.
	 * message.Message)
	 */
	@Override
	public void handleMessage(SoapMessage message) throws SoapFault {
		InputStream is = message.getContent(InputStream.class);
		if (is != null) {
			CachedOutputStream os = new CachedOutputStream();
			try {
				IOUtils.copy(is, os);
				os.flush();
				message.setContent(InputStream.class, os.getInputStream());
				is.close();

				String xml = WritterPrettyPrintUtils.getPrettyPrint(os);
				if (StringUtils.contains(xml, "faultcode")) {
					os.close();
					throw new SoapFault(extraerValorXml(xml, "<faultstring>(.*?)</faultstring>"),
							new QName(extraerValorXml(xml, "<faultcode>soap:(.*?)</faultcode>")));
				}
				os.close();
			} catch (IOException e) {
				LOGGER.error("Problemas al leer el error.", e);
			}

		}
	}

	/**
	 * Extraer valor xml.
	 *
	 * @param xml
	 *            the xml
	 * @param patron
	 *            the patron
	 * @return the string
	 */
	private String extraerValorXml(String xml, String patron) {
		Pattern pattern = Pattern.compile(patron);
		Matcher matcher = pattern.matcher(xml);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return StringUtils.EMPTY;
	}
	// public static void main(String[] args) {
	// String mydata = "<faultcode>soap:1232</faultcode>";
	// Pattern pattern = Pattern.compile("<faultcode>soap:(.*?)</faultcode>");
	// Matcher matcher = pattern.matcher(mydata);
	// if (matcher.find())
	// {
	// System.out.println(matcher.group(1));
	// }
	// System.out.println(Wss4jFaultInterceptor.extraerValorXml(mydata,
	// "<faultcode>soap:(.*?)</faultcode>"));
	//
	// }
}

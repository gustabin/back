/**
 * 
 */
package ar.com.santanderrio.obp.servicios.mya.dao.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

/**
 * Modificar los namespace del header en el mensaje enviado.
 * 
 * @author sergio.e.goldentair
 *
 */
public class MyaSchemaOutInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	/**
	 * Constructor MyaSchemaOutInterceptor Phase.PREPARE_SEND.
	 */
	public MyaSchemaOutInterceptor() {
		super(Phase.PREPARE_SEND);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.
	 * message.Message)
	 */
	public void handleMessage(SoapMessage message) {
		Map<String, String> hmap = new LinkedHashMap<String, String>();
		hmap.put("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
		hmap.put("soapenc", "http://schemas.xmlsoap.org/soap/encoding/");
		hmap.put("xsd", "http://www.w3.org/2001/XMLSchema");
		hmap.put("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		message.put("soap.env.ns.map", hmap);
		message.put("disable.outputstream.optimization", true);
	}
}

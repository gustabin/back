/**
 * 
 */
package ar.com.santanderrio.obp.base.webservice.interceptor;

import java.io.OutputStream;

import javax.xml.stream.XMLStreamWriter;

import org.apache.cxf.interceptor.AttachmentOutInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.staxutils.StaxUtils;

// TODO: Auto-generated Javadoc
/**
 * Interceptor para agregarle a los elementosConCdata indicados los CDATA.
 * 
 * @author sergio.e.goldentair
 *
 */
public class CDataOutLoggingInterceptor extends LoggingOutInterceptor {
	/** The elementosConCdata. */
	private String[] elementosConCdata;

	/**
	 * Constructor CDataOutLoggingInterceptor.
	 *
	 * @param elementosConCdata
	 *            the elementos con cdata
	 */
	public CDataOutLoggingInterceptor(String[] elementosConCdata) {
		super(Phase.PRE_STREAM);
		addAfter(AttachmentOutInterceptor.class.getName());
		this.elementosConCdata = elementosConCdata;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.cxf.interceptor.LoggingOutInterceptor#handleMessage(org.apache
	 * .cxf.message.Message)
	 */
	@Override
	public void handleMessage(Message message) throws Fault {
		final OutputStream os = message.getContent(OutputStream.class);
		if (os == null) {
			return;
		}
		final CacheAndWriteOutputStream newOut = new CacheAndWriteOutputStream(os);
		message.put("disable.outputstream.optimization", Boolean.TRUE);
		XMLStreamWriter writer = StaxUtils.createXMLStreamWriter(newOut);
		message.setContent(XMLStreamWriter.class, new CDataXMLStreamWriter(writer, elementosConCdata));
		message.setContent(OutputStream.class, newOut);
		newOut.registerCallback(new LoggingCallback());
	}

}
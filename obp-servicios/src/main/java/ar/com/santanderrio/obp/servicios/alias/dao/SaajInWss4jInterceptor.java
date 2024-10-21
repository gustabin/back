/**
 * 
 */
package ar.com.santanderrio.obp.servicios.alias.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.dom.DOMSource;

import org.apache.cxf.Bus;
import org.apache.cxf.attachment.AttachmentDataSource;
import org.apache.cxf.binding.soap.Soap11;
import org.apache.cxf.binding.soap.Soap12;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.ReadHeadersInterceptor;
import org.apache.cxf.binding.soap.saaj.SAAJFactoryResolver;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.binding.soap.saaj.SAAJStreamWriter;
import org.apache.cxf.binding.soap.saaj.SAAJUtils;
import org.apache.cxf.common.i18n.BundleUtils;
import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.databinding.DataBinding;
import org.apache.cxf.headers.Header;
import org.apache.cxf.headers.HeaderManager;
import org.apache.cxf.headers.HeaderProcessor;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Attachment;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.phase.PhaseInterceptor;
import org.apache.cxf.staxutils.StaxUtils;
import org.apache.cxf.staxutils.W3CDOMStreamWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Sobre escribo esta clase ya que rompe al loguear la resuesta cuando se
 * combina con wss4j.
 * 
 * @author sergio.e.goldentair
 *
 */
public class SaajInWss4jInterceptor extends AbstractSoapInterceptor {

	/** The Constant INSTANCE. */
	public static final SAAJInInterceptor INSTANCE = new SAAJInInterceptor();

	/** The Constant BUNDLE. */
	private static final ResourceBundle BUNDLE = BundleUtils.getBundle(SAAJInInterceptor.class);

	/** The Constant BODY_FILLED_IN. */
	private static final String BODY_FILLED_IN = SAAJInInterceptor.class.getName() + ".BODY_DONE";

	/** The pre interceptor. */
	private SAAJPreInInterceptor preInterceptor = SAAJPreInInterceptor.INSTANCE;

	/** The extras. */
	private List<PhaseInterceptor<? extends Message>> extras = new ArrayList<PhaseInterceptor<? extends Message>>(1);

	/**
	 * Instantiates a new saaj in wss 4 j interceptor.
	 */
	public SaajInWss4jInterceptor() {
		super(Phase.PRE_PROTOCOL);
		extras.add(preInterceptor);
	}

	/**
	 * Instantiates a new saaj in wss 4 j interceptor.
	 *
	 * @param phase
	 *            the phase
	 */
	public SaajInWss4jInterceptor(String phase) {
		super(phase);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.cxf.phase.AbstractPhaseInterceptor#getAdditionalInterceptors()
	 */
	@Override
	public Collection<PhaseInterceptor<? extends Message>> getAdditionalInterceptors() {
		return extras;
	}

	/**
	 * This class sets up the Document in the Message so that the
	 * ReadHeadersInterceptor can read directly into the SAAJ document instead
	 * of creating a new DOM that we would need to copy into the SAAJ later.
	 */
	public static class SAAJPreInInterceptor extends AbstractSoapInterceptor {

		/** The Constant INSTANCE. */
		public static final SAAJPreInInterceptor INSTANCE = new SAAJPreInInterceptor();

		/** The factory 11. */
		private MessageFactory factory11;

		/** The factory 12. */
		private MessageFactory factory12;

		/**
		 * Instantiates a new SAAJ pre in interceptor.
		 */
		public SAAJPreInInterceptor() {
			super(Phase.READ);
			addBefore(ReadHeadersInterceptor.class.getName());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.
		 * message.Message)
		 */
		public void handleMessage(SoapMessage message) throws Fault {
			if (isGET(message)) {
				return;
			}
			if (isRequestor(message) && message.getExchange().getInMessage() == null) {
				// already processed
				return;
			}
			try {
				XMLStreamReader xmlReader = message.getContent(XMLStreamReader.class);
				if (xmlReader == null) {
					return;
				}
				// Rompe cuando se utiliza con wss4jstax
				// if (xmlReader.nextTag() == XMLStreamConstants.START_ELEMENT)
				// {
				// ReadHeadersInterceptor.readVersion(xmlReader, message);
				// }
				MessageFactory factory = getFactory(message);
				SOAPMessage soapMessage = factory.createMessage();
				message.setContent(SOAPMessage.class, soapMessage);

				SOAPPart part = soapMessage.getSOAPPart();
				message.setContent(Node.class, part);
				message.put(W3CDOMStreamWriter.class, new SAAJStreamWriter(part));
				message.put(BODY_FILLED_IN, Boolean.FALSE);

			} catch (RuntimeException ex) {
				throw ex;
			} catch (Exception e) {
				throw new SoapFault("XML_STREAM_EXC", BUNDLE, e, message.getVersion().getSender());
			}
		}

		/**
		 * Gets the factory.
		 *
		 * @param message
		 *            the message
		 * @return the factory
		 * @throws SOAPException
		 *             the SOAP exception
		 */
		public synchronized MessageFactory getFactory(SoapMessage message) throws SOAPException {
			if (message.getVersion() instanceof Soap11) {
				if (factory11 == null) {
					factory11 = SAAJFactoryResolver.createMessageFactory(message.getVersion());
				}
				return factory11;
			}
			if (message.getVersion() instanceof Soap12) {
				if (factory12 == null) {
					factory12 = SAAJFactoryResolver.createMessageFactory(message.getVersion());
				}
				return factory12;
			}
			return SAAJFactoryResolver.createMessageFactory(null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.
	 * message.Message)
	 */
	public void handleMessage(SoapMessage message) throws Fault {
		if (isGET(message)) {
			return;
		}
		Boolean bodySet = (Boolean) message.get(BODY_FILLED_IN);
		if (Boolean.TRUE.equals(bodySet)) {
			return;
		}
		message.put(BODY_FILLED_IN, Boolean.TRUE);

		try {
			SOAPMessage soapMessage = message.getContent(SOAPMessage.class);
			if (soapMessage == null) {
				MessageFactory factory = preInterceptor.getFactory(message);
				soapMessage = factory.createMessage();
				message.setContent(SOAPMessage.class, soapMessage);
			}
			XMLStreamReader xmlReader = message.getContent(XMLStreamReader.class);
			if (xmlReader == null) {
				return;
			}
			final SOAPPart part = soapMessage.getSOAPPart();
			Document node = (Document) message.getContent(Node.class);
			if (node != part && node != null) {
				StaxUtils.copy(node, new SAAJStreamWriter(part));
			}
			message.setContent(Node.class, soapMessage.getSOAPPart());

			Collection<Attachment> atts = message.getAttachments();
			if (atts != null) {
				for (Attachment a : atts) {
					if (a.getDataHandler().getDataSource() instanceof AttachmentDataSource) {
						try {
							((AttachmentDataSource) a.getDataHandler().getDataSource()).cache(message);
						} catch (IOException e) {
							throw new Fault(e);
						}
					}
					AttachmentPart ap = soapMessage.createAttachmentPart(a.getDataHandler());
					Iterator<String> i = a.getHeaderNames();
					while (i != null && i.hasNext()) {
						String h = i.next();
						String val = a.getHeader(h);
						ap.addMimeHeader(h, val);
					}
					if (StringUtils.isEmpty(ap.getContentId())) {
						ap.setContentId(a.getId());
					}
					soapMessage.addAttachmentPart(ap);
				}
			}

			// replace header element if necessary
			if (message.hasHeaders()) {
				replaceHeaders(soapMessage, message);
			}

			if (soapMessage.getSOAPPart().getEnvelope().getHeader() == null) {
				soapMessage.getSOAPPart().getEnvelope().addHeader();
			}

			StaxUtils.copy(xmlReader,
					new SAAJStreamWriter(soapMessage.getSOAPPart(), soapMessage.getSOAPPart().getEnvelope().getBody()),
					true, true);
			DOMSource bodySource = new DOMSource(soapMessage.getSOAPPart().getEnvelope().getBody());
			xmlReader = StaxUtils.createXMLStreamReader(bodySource);
			xmlReader.nextTag();
			xmlReader.nextTag(); // move past body tag
			message.setContent(XMLStreamReader.class, xmlReader);
		} catch (SOAPException soape) {
			throw new SoapFault(new org.apache.cxf.common.i18n.Message("SOAPHANDLERINTERCEPTOR_EXCEPTION", BUNDLE),
					soape, message.getVersion().getSender());
		} catch (XMLStreamException e) {
			throw new SoapFault(new org.apache.cxf.common.i18n.Message("SOAPHANDLERINTERCEPTOR_EXCEPTION", BUNDLE), e,
					message.getVersion().getSender());
		}
	}

	/**
	 * Replace headers.
	 *
	 * @param soapMessage
	 *            the soap message
	 * @param message
	 *            the message
	 * @throws SOAPException
	 *             the SOAP exception
	 */
	public static void replaceHeaders(SOAPMessage soapMessage, SoapMessage message) throws SOAPException {
		SOAPHeader header = SAAJUtils.getHeader(soapMessage);
		if (header == null) {
			return;
		}
		Element elem = DOMUtils.getFirstElement(header);
		while (elem != null) {
			Bus b = message.getExchange() == null ? null : message.getExchange().get(Bus.class);
			HeaderProcessor p = null;
			if (b != null && b.getExtension(HeaderManager.class) != null) {
				p = b.getExtension(HeaderManager.class).getHeaderProcessor(elem.getNamespaceURI());
			}

			Object obj;
			DataBinding dataBinding = null;
			if (p == null || p.getDataBinding() == null) {
				obj = elem;
			} else {
				dataBinding = p.getDataBinding();
				obj = p.getDataBinding().createReader(Node.class).read(elem);
			}
			// TODO - add the interceptors

			SoapHeader shead = new SoapHeader(new QName(elem.getNamespaceURI(), elem.getLocalName()), obj, dataBinding);
			shead.setDirection(SoapHeader.Direction.DIRECTION_IN);

			String mu = elem.getAttributeNS(message.getVersion().getNamespace(),
					message.getVersion().getAttrNameMustUnderstand());
			String act = elem.getAttributeNS(message.getVersion().getNamespace(),
					message.getVersion().getAttrNameRole());

			shead.setActor(act);
			shead.setMustUnderstand(Boolean.valueOf(mu) || "1".equals(mu));
			Header oldHdr = message.getHeader(new QName(elem.getNamespaceURI(), elem.getLocalName()));
			if (oldHdr != null) {
				message.getHeaders().remove(oldHdr);
			}
			message.getHeaders().add(shead);

			elem = DOMUtils.getNextElement(elem);
		}
	}

}

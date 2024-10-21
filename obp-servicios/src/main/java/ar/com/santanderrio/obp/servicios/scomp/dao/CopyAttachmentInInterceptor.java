/*
 * 
 */
package ar.com.santanderrio.obp.servicios.scomp.dao;

import java.io.IOException;
import java.util.Collection;

import javax.activation.DataHandler;
import javax.xml.bind.JAXBException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Attachment;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MimeTypeUtils;

import ar.com.santanderrio.obp.base.comun.JaxbUtils;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.ComprobanteResponse;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.RespuestaScomp;

/**
 * Interceptor para obtener los attachments del ws de scomp.
 * 
 * @author sergio.e.goldentair
 *
 */
public class CopyAttachmentInInterceptor extends AbstractPhaseInterceptor<Message> {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CopyAttachmentInInterceptor.class);

	/**
	 * El Constructor.
	 */
	public CopyAttachmentInInterceptor() {
		super(Phase.PRE_INVOKE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.
	 * message.Message)
	 */
	@Override
	public void handleMessage(Message message) throws Fault {
		LOGGER.info("Se buscan los attachments retornados.");
		Exchange exchange = message.getExchange();
		BindingOperationInfo bop = exchange.getBindingOperationInfo();
		if (bop == null) {
			return;
		}
		Collection<Attachment> atts = message.getAttachments();
		if (CollectionUtils.isEmpty(atts)) {
			LOGGER.info("No tiene attachment para procesar.");
			MessageContentsList inObjects = MessageContentsList.getContentsList(message);
			ComprobanteResponse lcr = (ComprobanteResponse) inObjects.get(0);
			RespuestaScomp rs = new RespuestaScomp();
			lcr.setRespuestaScomp(rs);
		} else {
			for (Attachment attachment : atts) {
				DataHandler data = attachment.getDataHandler();
				if (data.getContentType().startsWith(MimeTypeUtils.APPLICATION_XML_VALUE)) {
					LOGGER.info("Tiene attachment para procesar.");
					MessageContentsList inObjects = MessageContentsList.getContentsList(message);
					ComprobanteResponse lcr = (ComprobanteResponse) inObjects.get(0);
					try {
						String xml = IOUtils.toString(data.getInputStream());
						RespuestaScomp respuesta = JaxbUtils.transformarXmlAObject(xml, RespuestaScomp.class);
						lcr.setRespuestaScomp(respuesta);
					} catch (JAXBException jaxbe) {
						LOGGER.error("Error al realizar el unmarshall del xml atachado.", jaxbe);
						throw new Fault(jaxbe);
					} catch (IOException ioe) {
						LOGGER.error("Error al transformar el inputStream atachado a String.", ioe);
						throw new Fault(ioe);
					}
				} else if (data.getContentType().startsWith(MimeTypeUtils.TEXT_HTML_VALUE)) {
					LOGGER.info("Tiene detalle attachment para procesar.");
					MessageContentsList inObjects = MessageContentsList.getContentsList(message);
					ComprobanteResponse lcr = (ComprobanteResponse) inObjects.get(0);
					try {
						
						String detalleHtml = IOUtils.toString(data.getInputStream(), "UTF-8");
						lcr.setDetalleHtml(detalleHtml);
					} catch (IOException ioe) {
						LOGGER.error("Error al transformar el inputStream atachado a String.", ioe);
						throw new Fault(ioe);
					}
				}
			}
		}
	}
}

/**
 * 
 */
package ar.com.santanderrio.obp.base.signer.token;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLToken.
 *
 * @author sergio.e.goldentair
 */
public class XMLToken extends Token {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(XMLToken.class);

	/**
	 * Instantiates a new XML token.
	 */
	public XMLToken() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringWriter sw = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			LOGGER.debug("output pretty printed enabled");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(this, sw);
		} catch (JAXBException e) {
			LOGGER.error("Error al hacer el marshall para obtener el xml.", e);
		}
		return sw.toString();
	}

}

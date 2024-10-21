/**
 * 
 */
package ar.com.santanderrio.obp.base.webservice.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.cxf.staxutils.DelegatingXMLStreamWriter;

// TODO: Auto-generated Javadoc
/**
 * Escribe el elemento indicado con el CDATA para que no sea interpretado.
 * 
 * @author sergio.e.goldentair
 *
 */
public class CDataXMLStreamWriter extends DelegatingXMLStreamWriter {

	/** The Constant DEFAULT_ELEMENTS. */
	private static final String DEFAULT_ELEMENTS = "MessageBody";

	/** The current element name. */
	private String currentElementName;

	/** The cdata elements. */
	private List<String> cdataElements;

	/**
	 * Recibe el listado de elementos que se deben filtrar para agregarle el
	 * cdata. Si no recibe valores utiliza el valor por default MessageBody.
	 *
	 * @param del
	 *            the del
	 * @param elements
	 *            the elements
	 */
	public CDataXMLStreamWriter(XMLStreamWriter del, String... elements) {
		super(del);
		if (elements != null) {
			cdataElements = Arrays.asList(elements);
		} else {
			cdataElements = Arrays.asList(DEFAULT_ELEMENTS);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.cxf.staxutils.DelegatingXMLStreamWriter#writeCharacters(java.
	 * lang.String)
	 */
	@Override
	public void writeCharacters(String text) throws XMLStreamException {
		if (cdataElements.contains(currentElementName)) {
			super.writeCData(text);
		} else {
			super.writeCharacters(text);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.cxf.staxutils.DelegatingXMLStreamWriter#writeStartElement(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	public void writeStartElement(String prefix, String local, String uri) throws XMLStreamException {
		currentElementName = local;
		super.writeStartElement(prefix, local, uri);
	}
}
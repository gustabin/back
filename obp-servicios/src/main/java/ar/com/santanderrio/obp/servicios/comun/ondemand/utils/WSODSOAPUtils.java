/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package ar.com.santanderrio.obp.servicios.comun.ondemand.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.Text;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODError;

/**
 * The Class WSODSOAPUtils.
 */
public final class WSODSOAPUtils {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(WSODSOAPUtils.class);

	/**
	 * Instantiates a new WSODSOAP utils.
	 */
	private WSODSOAPUtils() {
	}

	/**
	 * Sets the soap body.
	 *
	 * @param doc
	 *            the doc
	 * @param body
	 *            the body
	 */
	public static void setSoapBody(Document doc, SOAPBody body) {
		try {
			SOAPFactory sf = SOAPFactory.newInstance();
			Name name = sf.createName(doc.getRootElement().getName());
			SOAPElement sele = body.addBodyElement(name);
			Iterator it = doc.getRootElement().elementIterator();
			Element ele = null;
			while (it.hasNext()) {
				ele = (Element) it.next();
				addSoapElement(ele, sele, sf);
			}
		} catch (Exception exc) {
			LOGGER.error("Error", exc);
		}
	}

	/**
	 * Adds the soap element.
	 *
	 * @param doc
	 *            the doc
	 * @param parent
	 *            the parent
	 * @param sf
	 *            the sf
	 * @throws SOAPException
	 *             the SOAP exception
	 */
	public static void addSoapElement(Element doc, SOAPElement parent, SOAPFactory sf) throws SOAPException {
		SOAPElement element = parent.addChildElement(doc.getName());

		if ((doc.getText() != null) && (!("".equals(doc.getText())))) {
			element.addTextNode(doc.getText());
		}

		Name name = null;
		Iterator it = doc.attributeIterator();
		Attribute att = null;
		while (it.hasNext()) {
			att = (Attribute) it.next();
			name = sf.createName(att.getName());
			element.addAttribute(name, att.getValue());
		}

		it = doc.elementIterator();
		while (it.hasNext()) {
			addSoapElement((Element) it.next(), element, sf);
		}
	}

	/**
	 * Gets the cod ret.
	 *
	 * @param body
	 *            the body
	 * @return the cod ret
	 */
	public static String getCodRet(SOAPBody body) {
		String codRet = null;

		Iterator it = ((SOAPElement) body.getChildElements().next()).getChildElements();

		while (it.hasNext()) {
			javax.xml.soap.Node node = (javax.xml.soap.Node) it.next();
			if (!(node instanceof SOAPElement)) {
				continue;
			}
			SOAPElement element = (SOAPElement) node;
			if (!("cod-ret".equals(element.getElementName().getQualifiedName()))) {
				continue;
			}
			codRet = getElementValue(element);

			break;
		}

		return codRet;
	}

	/**
	 * Gets the array message.
	 *
	 * @param body
	 *            the body
	 * @param messageName
	 *            the message name
	 * @return the array message
	 */
	@SuppressWarnings("rawtypes")
	public static List getArrayMessage(SOAPBody body, String messageName) {
		List al = new ArrayList();

		Iterator it = ((SOAPElement) body.getChildElements().next()).getChildElements();

		while (it.hasNext()) {
			javax.xml.soap.Node node = (javax.xml.soap.Node) it.next();
			if (!(node instanceof SOAPElement)) {
				continue;
			}
			SOAPElement element = (SOAPElement) node;
			if (!(messageName.equals(element.getElementName().getQualifiedName()))) {
				continue;
			}

			String cod = "";
			String des = "";

			Iterator attit = element.getAllAttributes();
			while (attit.hasNext()) {
				Name attrName = (Name) attit.next();

				if ("cod".equals(attrName.getQualifiedName())) {
					cod = element.getAttributeValue(attrName);
				} else {
					if (!("desc".equals(attrName.getQualifiedName()))) {
						continue;
					}
					des = element.getAttributeValue(attrName);
				}
			}

			al.add(new WSODError(cod, des));
		}

		return al;
	}

	/**
	 * Gets the element value.
	 *
	 * @param element
	 *            the element
	 * @return the element value
	 */
	public static String getElementValue(SOAPElement element) {
		String value = null;

		Iterator it = element.getChildElements();

		while (it.hasNext()) {
			javax.xml.soap.Node node = (javax.xml.soap.Node) it.next();
			if (!(node instanceof Text)) {
				continue;
			}
			Text text = (Text) node;
			value = text.getValue();

			break;
		}

		return value;
	}
}
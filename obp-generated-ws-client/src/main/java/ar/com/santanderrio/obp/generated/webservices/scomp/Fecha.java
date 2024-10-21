/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.scomp;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * The Class Fecha.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "desde", "hasta" })
public class Fecha {

    /** The desde. */
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar desde;

    /** The hasta. */
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar hasta;

    /**
     * Gets the desde.
     *
     * @return the desde
     */
    public XMLGregorianCalendar getDesde() {
        return desde;
    }

    /**
     * Sets the desde.
     *
     * @param desde
     *            the desde to set
     */
    public void setDesde(XMLGregorianCalendar desde) {
        this.desde = desde;
    }

    /**
     * Gets the hasta.
     *
     * @return the hasta
     */
    public XMLGregorianCalendar getHasta() {
        return hasta;
    }

    /**
     * Sets the hasta.
     *
     * @param hasta
     *            the hasta to set
     */
    public void setHasta(XMLGregorianCalendar hasta) {
        this.hasta = hasta;
    }

    /**
     * Devuelve un objeto XMLGregorianCalendar a partir de un objeto Date.
     *
     * @param fecha
     *            the fecha
     * @return the xml gregorian calendar
     * @throws DatatypeConfigurationException
     *             the datatype configuration exception
     */
    public XMLGregorianCalendar getXmlGregorianCalendar(Date fecha) throws DatatypeConfigurationException {
        GregorianCalendar calendario = new GregorianCalendar();
        calendario.setTime(fecha);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendario);
    }
}
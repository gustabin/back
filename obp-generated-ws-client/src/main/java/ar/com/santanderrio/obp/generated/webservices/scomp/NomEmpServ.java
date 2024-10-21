/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.scomp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * The Class NomEmpServ.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "desde", "hasta" })
public class NomEmpServ {

    /** The desde. */
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar desde;

    /** The hasta. */
    @XmlElement(required = true)
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

}

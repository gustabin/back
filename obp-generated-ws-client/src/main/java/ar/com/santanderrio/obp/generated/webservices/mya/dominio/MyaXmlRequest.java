/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sergio.e.goldentair
 *
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyaXmlRequest {
    /** Encabezado request. **/
    @XmlElement
    private MyaHeaderRequest header;
    /** Cuerpo del request. **/
    private MyaBodyRequest datosAFirmar;
    /** Firma. **/
    @XmlElement(name = "datosFirmados")
    private String xmlDatosFirmados;

    /**
     * @return the header
     */
    public MyaHeaderRequest getHeader() {
        return header;
    }

    /**
     * @param header
     *            the header to set
     */
    public void setHeader(MyaHeaderRequest header) {
        this.header = header;
    }

    /**
     * @return the datosAFirmar
     */
    public MyaBodyRequest getDatosAFirmar() {
        return datosAFirmar;
    }

    /**
     * @param datosAFirmar
     *            the datosFirmados to set
     */
    public void setDatosAFirmar(MyaBodyRequest datosAFirmar) {
        this.datosAFirmar = datosAFirmar;
    }

    /**
     * @return the xmlDatosFirmados
     */
    public String getXmlDatosFirmados() {
        return xmlDatosFirmados;
    }

    /**
     * @param xmlDatosFirmados
     *            the xmlDatosFirmados to set
     */
    public void setXmlDatosFirmados(String xmlDatosFirmados) {
        this.xmlDatosFirmados = xmlDatosFirmados;
    }
}

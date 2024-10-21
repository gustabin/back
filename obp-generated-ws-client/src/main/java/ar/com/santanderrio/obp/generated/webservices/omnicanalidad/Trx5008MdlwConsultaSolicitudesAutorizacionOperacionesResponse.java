
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx5008MdlwConsultaSolicitudesAutorizacionOperacionesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx5008MdlwConsultaSolicitudesAutorizacionOperacionesResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="ConsultaSolicitudesAutorizacion" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ResultConsultaSolicitudesAutorizacion" minOccurs="0"/>
 *         &lt;element name="SolicitudesAutorizacionNPF" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfResultSolicitudesAutorizacionNPF" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx5008MdlwConsultaSolicitudesAutorizacionOperacionesResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5008", propOrder = {
    "consultaSolicitudesAutorizacion",
    "solicitudesAutorizacionNPF"
})
public class Trx5008MdlwConsultaSolicitudesAutorizacionOperacionesResponse
    extends ResponseBase
{

    @XmlElementRef(name = "ConsultaSolicitudesAutorizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5008", type = JAXBElement.class)
    protected JAXBElement<ResultConsultaSolicitudesAutorizacion> consultaSolicitudesAutorizacion;
    @XmlElementRef(name = "SolicitudesAutorizacionNPF", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5008", type = JAXBElement.class)
    protected JAXBElement<ArrayOfResultSolicitudesAutorizacionNPF> solicitudesAutorizacionNPF;

    /**
     * Gets the value of the consultaSolicitudesAutorizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ResultConsultaSolicitudesAutorizacion }{@code >}
     *     
     */
    public JAXBElement<ResultConsultaSolicitudesAutorizacion> getConsultaSolicitudesAutorizacion() {
        return consultaSolicitudesAutorizacion;
    }

    /**
     * Sets the value of the consultaSolicitudesAutorizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ResultConsultaSolicitudesAutorizacion }{@code >}
     *     
     */
    public void setConsultaSolicitudesAutorizacion(JAXBElement<ResultConsultaSolicitudesAutorizacion> value) {
        this.consultaSolicitudesAutorizacion = value;
    }

    /**
     * Gets the value of the solicitudesAutorizacionNPF property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfResultSolicitudesAutorizacionNPF }{@code >}
     *     
     */
    public JAXBElement<ArrayOfResultSolicitudesAutorizacionNPF> getSolicitudesAutorizacionNPF() {
        return solicitudesAutorizacionNPF;
    }

    /**
     * Sets the value of the solicitudesAutorizacionNPF property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfResultSolicitudesAutorizacionNPF }{@code >}
     *     
     */
    public void setSolicitudesAutorizacionNPF(JAXBElement<ArrayOfResultSolicitudesAutorizacionNPF> value) {
        this.solicitudesAutorizacionNPF = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx278ConsultaModalidadTransferenciaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx278ConsultaModalidadTransferenciaResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CantRegistros" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorAdhesionBEE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorRegistrosAdicionales" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ModalidadesTransferencia" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx278}ArrayOfTrx278ConsultaModalidadTransferenciaIterationResponse" minOccurs="0"/>
 *         &lt;element name="TipoEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoModalidadPHH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoModalidadTransferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx278ConsultaModalidadTransferenciaResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx278", propOrder = {
    "cantRegistros",
    "indicadorAdhesionBEE",
    "indicadorRegistrosAdicionales",
    "modalidadesTransferencia",
    "tipoEmpresa",
    "tipoModalidadPHH",
    "tipoModalidadTransferencia"
})
public class Trx278ConsultaModalidadTransferenciaResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CantRegistros", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx278", type = JAXBElement.class)
    protected JAXBElement<String> cantRegistros;
    @XmlElementRef(name = "IndicadorAdhesionBEE", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx278", type = JAXBElement.class)
    protected JAXBElement<String> indicadorAdhesionBEE;
    @XmlElementRef(name = "IndicadorRegistrosAdicionales", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx278", type = JAXBElement.class)
    protected JAXBElement<String> indicadorRegistrosAdicionales;
    @XmlElementRef(name = "ModalidadesTransferencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx278", type = JAXBElement.class)
    protected JAXBElement<ArrayOfTrx278ConsultaModalidadTransferenciaIterationResponse> modalidadesTransferencia;
    @XmlElementRef(name = "TipoEmpresa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx278", type = JAXBElement.class)
    protected JAXBElement<String> tipoEmpresa;
    @XmlElementRef(name = "TipoModalidadPHH", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx278", type = JAXBElement.class)
    protected JAXBElement<String> tipoModalidadPHH;
    @XmlElementRef(name = "TipoModalidadTransferencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx278", type = JAXBElement.class)
    protected JAXBElement<String> tipoModalidadTransferencia;

    /**
     * Gets the value of the cantRegistros property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantRegistros() {
        return cantRegistros;
    }

    /**
     * Sets the value of the cantRegistros property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantRegistros(JAXBElement<String> value) {
        this.cantRegistros = value;
    }

    /**
     * Gets the value of the indicadorAdhesionBEE property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorAdhesionBEE() {
        return indicadorAdhesionBEE;
    }

    /**
     * Sets the value of the indicadorAdhesionBEE property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorAdhesionBEE(JAXBElement<String> value) {
        this.indicadorAdhesionBEE = value;
    }

    /**
     * Gets the value of the indicadorRegistrosAdicionales property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorRegistrosAdicionales() {
        return indicadorRegistrosAdicionales;
    }

    /**
     * Sets the value of the indicadorRegistrosAdicionales property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorRegistrosAdicionales(JAXBElement<String> value) {
        this.indicadorRegistrosAdicionales = value;
    }

    /**
     * Gets the value of the modalidadesTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx278ConsultaModalidadTransferenciaIterationResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTrx278ConsultaModalidadTransferenciaIterationResponse> getModalidadesTransferencia() {
        return modalidadesTransferencia;
    }

    /**
     * Sets the value of the modalidadesTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx278ConsultaModalidadTransferenciaIterationResponse }{@code >}
     *     
     */
    public void setModalidadesTransferencia(JAXBElement<ArrayOfTrx278ConsultaModalidadTransferenciaIterationResponse> value) {
        this.modalidadesTransferencia = value;
    }

    /**
     * Gets the value of the tipoEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoEmpresa() {
        return tipoEmpresa;
    }

    /**
     * Sets the value of the tipoEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoEmpresa(JAXBElement<String> value) {
        this.tipoEmpresa = value;
    }

    /**
     * Gets the value of the tipoModalidadPHH property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoModalidadPHH() {
        return tipoModalidadPHH;
    }

    /**
     * Sets the value of the tipoModalidadPHH property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoModalidadPHH(JAXBElement<String> value) {
        this.tipoModalidadPHH = value;
    }

    /**
     * Gets the value of the tipoModalidadTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoModalidadTransferencia() {
        return tipoModalidadTransferencia;
    }

    /**
     * Sets the value of the tipoModalidadTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoModalidadTransferencia(JAXBElement<String> value) {
        this.tipoModalidadTransferencia = value;
    }

}

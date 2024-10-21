
package ar.com.santanderrio.obp.generated.webservices.extractos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MovFondosResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MovFondosResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ListaMovimientos" type="{http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos}ArrayOfMovFondos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MovFondosResponse", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", propOrder = {
    "codigo",
    "descripcion",
    "listaMovimientos"
})
public class MovFondosResponse {

    @XmlElementRef(name = "Codigo", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", type = JAXBElement.class)
    protected JAXBElement<String> codigo;
    @XmlElementRef(name = "Descripcion", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", type = JAXBElement.class)
    protected JAXBElement<String> descripcion;
    @XmlElementRef(name = "ListaMovimientos", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", type = JAXBElement.class)
    protected JAXBElement<ArrayOfMovFondos> listaMovimientos;

    /**
     * Gets the value of the codigo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigo() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigo(JAXBElement<String> value) {
        this.codigo = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcion(JAXBElement<String> value) {
        this.descripcion = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the listaMovimientos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfMovFondos }{@code >}
     *     
     */
    public JAXBElement<ArrayOfMovFondos> getListaMovimientos() {
        return listaMovimientos;
    }

    /**
     * Sets the value of the listaMovimientos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfMovFondos }{@code >}
     *     
     */
    public void setListaMovimientos(JAXBElement<ArrayOfMovFondos> value) {
        this.listaMovimientos = ((JAXBElement<ArrayOfMovFondos> ) value);
    }

}

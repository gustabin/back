
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx146CnsPuntosSuperclubCliResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx146CnsPuntosSuperclubCliResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CantCuentas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaCartola" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCartola" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PuntosAVencer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PuntosCanjeados" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PuntosGanados" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PuntosPerdidos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoActualDePuntos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoAnteriorDePuntos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx146CnsPuntosSuperclubCliResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx146", propOrder = {
    "cantCuentas",
    "fechaCartola",
    "fechaVencimiento",
    "nroCartola",
    "puntosAVencer",
    "puntosCanjeados",
    "puntosGanados",
    "puntosPerdidos",
    "saldoActualDePuntos",
    "saldoAnteriorDePuntos"
})
public class Trx146CnsPuntosSuperclubCliResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CantCuentas", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx146", type = JAXBElement.class)
    protected JAXBElement<String> cantCuentas;
    @XmlElementRef(name = "FechaCartola", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx146", type = JAXBElement.class)
    protected JAXBElement<String> fechaCartola;
    @XmlElementRef(name = "FechaVencimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx146", type = JAXBElement.class)
    protected JAXBElement<String> fechaVencimiento;
    @XmlElementRef(name = "NroCartola", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx146", type = JAXBElement.class)
    protected JAXBElement<String> nroCartola;
    @XmlElementRef(name = "PuntosAVencer", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx146", type = JAXBElement.class)
    protected JAXBElement<String> puntosAVencer;
    @XmlElementRef(name = "PuntosCanjeados", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx146", type = JAXBElement.class)
    protected JAXBElement<String> puntosCanjeados;
    @XmlElementRef(name = "PuntosGanados", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx146", type = JAXBElement.class)
    protected JAXBElement<String> puntosGanados;
    @XmlElementRef(name = "PuntosPerdidos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx146", type = JAXBElement.class)
    protected JAXBElement<String> puntosPerdidos;
    @XmlElementRef(name = "SaldoActualDePuntos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx146", type = JAXBElement.class)
    protected JAXBElement<String> saldoActualDePuntos;
    @XmlElementRef(name = "SaldoAnteriorDePuntos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx146", type = JAXBElement.class)
    protected JAXBElement<String> saldoAnteriorDePuntos;

    /**
     * Gets the value of the cantCuentas property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantCuentas() {
        return cantCuentas;
    }

    /**
     * Sets the value of the cantCuentas property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantCuentas(JAXBElement<String> value) {
        this.cantCuentas = value;
    }

    /**
     * Gets the value of the fechaCartola property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaCartola() {
        return fechaCartola;
    }

    /**
     * Sets the value of the fechaCartola property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaCartola(JAXBElement<String> value) {
        this.fechaCartola = value;
    }

    /**
     * Gets the value of the fechaVencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Sets the value of the fechaVencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaVencimiento(JAXBElement<String> value) {
        this.fechaVencimiento = value;
    }

    /**
     * Gets the value of the nroCartola property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroCartola() {
        return nroCartola;
    }

    /**
     * Sets the value of the nroCartola property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroCartola(JAXBElement<String> value) {
        this.nroCartola = value;
    }

    /**
     * Gets the value of the puntosAVencer property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPuntosAVencer() {
        return puntosAVencer;
    }

    /**
     * Sets the value of the puntosAVencer property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPuntosAVencer(JAXBElement<String> value) {
        this.puntosAVencer = value;
    }

    /**
     * Gets the value of the puntosCanjeados property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPuntosCanjeados() {
        return puntosCanjeados;
    }

    /**
     * Sets the value of the puntosCanjeados property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPuntosCanjeados(JAXBElement<String> value) {
        this.puntosCanjeados = value;
    }

    /**
     * Gets the value of the puntosGanados property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPuntosGanados() {
        return puntosGanados;
    }

    /**
     * Sets the value of the puntosGanados property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPuntosGanados(JAXBElement<String> value) {
        this.puntosGanados = value;
    }

    /**
     * Gets the value of the puntosPerdidos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPuntosPerdidos() {
        return puntosPerdidos;
    }

    /**
     * Sets the value of the puntosPerdidos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPuntosPerdidos(JAXBElement<String> value) {
        this.puntosPerdidos = value;
    }

    /**
     * Gets the value of the saldoActualDePuntos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoActualDePuntos() {
        return saldoActualDePuntos;
    }

    /**
     * Sets the value of the saldoActualDePuntos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoActualDePuntos(JAXBElement<String> value) {
        this.saldoActualDePuntos = value;
    }

    /**
     * Gets the value of the saldoAnteriorDePuntos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoAnteriorDePuntos() {
        return saldoAnteriorDePuntos;
    }

    /**
     * Sets the value of the saldoAnteriorDePuntos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoAnteriorDePuntos(JAXBElement<String> value) {
        this.saldoAnteriorDePuntos = value;
    }

}

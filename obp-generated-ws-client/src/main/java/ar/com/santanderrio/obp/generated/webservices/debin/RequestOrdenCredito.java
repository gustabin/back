
package ar.com.santanderrio.obp.generated.webservices.debin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para requestOrdenCredito complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="requestOrdenCredito">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}request">
 *       &lt;sequence>
 *         &lt;element name="concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="credito" type="{http://webservices.api.debin.prismamp.com/}creditoDTO" minOccurs="0"/>
 *         &lt;element name="debito" type="{http://webservices.api.debin.prismamp.com/}debitoDTO" minOccurs="0"/>
 *         &lt;element name="importe" type="{http://webservices.api.debin.prismamp.com/}importeDTO" minOccurs="0"/>
 *         &lt;element name="mismoTitular" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="referencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestOrdenCredito", propOrder = {
    "concepto",
    "credito",
    "debito",
    "importe",
    "mismoTitular",
    "referencia"
})
public class RequestOrdenCredito
    extends Request
{

    protected String concepto;
    protected CreditoDTO credito;
    protected DebitoDTO debito;
    protected ImporteDTO importe;
    protected Integer mismoTitular;
    protected String referencia;

    /**
     * Obtiene el valor de la propiedad concepto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * Define el valor de la propiedad concepto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConcepto(String value) {
        this.concepto = value;
    }

    /**
     * Obtiene el valor de la propiedad credito.
     * 
     * @return
     *     possible object is
     *     {@link CreditoDTO }
     *     
     */
    public CreditoDTO getCredito() {
        return credito;
    }

    /**
     * Define el valor de la propiedad credito.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditoDTO }
     *     
     */
    public void setCredito(CreditoDTO value) {
        this.credito = value;
    }

    /**
     * Obtiene el valor de la propiedad debito.
     * 
     * @return
     *     possible object is
     *     {@link DebitoDTO }
     *     
     */
    public DebitoDTO getDebito() {
        return debito;
    }

    /**
     * Define el valor de la propiedad debito.
     * 
     * @param value
     *     allowed object is
     *     {@link DebitoDTO }
     *     
     */
    public void setDebito(DebitoDTO value) {
        this.debito = value;
    }

    /**
     * Obtiene el valor de la propiedad importe.
     * 
     * @return
     *     possible object is
     *     {@link ImporteDTO }
     *     
     */
    public ImporteDTO getImporte() {
        return importe;
    }

    /**
     * Define el valor de la propiedad importe.
     * 
     * @param value
     *     allowed object is
     *     {@link ImporteDTO }
     *     
     */
    public void setImporte(ImporteDTO value) {
        this.importe = value;
    }

    /**
     * Obtiene el valor de la propiedad mismoTitular.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMismoTitular() {
        return mismoTitular;
    }

    /**
     * Define el valor de la propiedad mismoTitular.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMismoTitular(Integer value) {
        this.mismoTitular = value;
    }

    /**
     * Obtiene el valor de la propiedad referencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Define el valor de la propiedad referencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferencia(String value) {
        this.referencia = value;
    }

}


package ar.com.santanderrio.obp.generated.webservices.debin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para responseOrdenCredito complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="responseOrdenCredito">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}response">
 *       &lt;sequence>
 *         &lt;element name="credito" type="{http://webservices.api.debin.prismamp.com/}creditoDTO" minOccurs="0"/>
 *         &lt;element name="fechaNegocio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importe" type="{http://webservices.api.debin.prismamp.com/}importeDTO" minOccurs="0"/>
 *         &lt;element name="objeto" type="{http://webservices.api.debin.prismamp.com/}objetoResponseDTO" minOccurs="0"/>
 *         &lt;element name="respuesta" type="{http://webservices.api.debin.prismamp.com/}respuestaDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseOrdenCredito", propOrder = {
    "credito",
    "fechaNegocio",
    "importe",
    "objeto",
    "respuesta"
})
public class ResponseOrdenCredito
    extends Response
{

    protected CreditoDTO credito;
    protected String fechaNegocio;
    protected ImporteDTO importe;
    protected ObjetoResponseDTO objeto;
    protected RespuestaDTO respuesta;

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
     * Obtiene el valor de la propiedad fechaNegocio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNegocio() {
        return fechaNegocio;
    }

    /**
     * Define el valor de la propiedad fechaNegocio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNegocio(String value) {
        this.fechaNegocio = value;
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
     * Obtiene el valor de la propiedad objeto.
     * 
     * @return
     *     possible object is
     *     {@link ObjetoResponseDTO }
     *     
     */
    public ObjetoResponseDTO getObjeto() {
        return objeto;
    }

    /**
     * Define el valor de la propiedad objeto.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjetoResponseDTO }
     *     
     */
    public void setObjeto(ObjetoResponseDTO value) {
        this.objeto = value;
    }

    /**
     * Obtiene el valor de la propiedad respuesta.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaDTO }
     *     
     */
    public RespuestaDTO getRespuesta() {
        return respuesta;
    }

    /**
     * Define el valor de la propiedad respuesta.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaDTO }
     *     
     */
    public void setRespuesta(RespuestaDTO value) {
        this.respuesta = value;
    }

}

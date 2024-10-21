
package ar.com.santanderrio.obp.generated.webservices.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubResponseGrupal complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubResponseGrupal">
 *   &lt;complexContent>
 *     &lt;extension base="{http://model.alertas.crm.bancorio.com/}Response">
 *       &lt;sequence>
 *         &lt;element name="codAlerta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="claveUnica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contLectura" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="estGestion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubResponseGrupal", propOrder = {
    "codAlerta",
    "descripcion",
    "claveUnica",
    "contLectura",
    "estGestion"
})
public class SubResponseGrupal
    extends Response
{

    protected int codAlerta;
    protected String descripcion;
    protected String claveUnica;
    protected int contLectura;
    protected int estGestion;

    /**
     * Gets the value of the codAlerta property.
     * 
     */
    public int getCodAlerta() {
        return codAlerta;
    }

    /**
     * Sets the value of the codAlerta property.
     * 
     */
    public void setCodAlerta(int value) {
        this.codAlerta = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the claveUnica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveUnica() {
        return claveUnica;
    }

    /**
     * Sets the value of the claveUnica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveUnica(String value) {
        this.claveUnica = value;
    }

    /**
     * Gets the value of the contLectura property.
     * 
     */
    public int getContLectura() {
        return contLectura;
    }

    /**
     * Sets the value of the contLectura property.
     * 
     */
    public void setContLectura(int value) {
        this.contLectura = value;
    }

    /**
     * Gets the value of the estGestion property.
     * 
     */
    public int getEstGestion() {
        return estGestion;
    }

    /**
     * Sets the value of the estGestion property.
     * 
     */
    public void setEstGestion(int value) {
        this.estGestion = value;
    }

}

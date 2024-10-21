
package ar.com.santanderrio.obp.generated.webservices.productos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.echeq.Firmante;


/**
 * <p>Java class for InfoRequeridaWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InfoRequeridaWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valor" type="{http://webService.core.ges.rio.com}ArrayOf_32493182_1440052060_nillable_string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoRequeridaWS", namespace = "http://webService.core.ges.rio.com", propOrder = {
    "codigo",
    "descripcion",
    "valor",
    "valores"
})
public class InfoRequeridaWS {

    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer codigo;
    @XmlElement(required = true, nillable = true)
    protected String descripcion;
    protected ArrayOf324931821440052060NillableString valor;
    @XmlElement(name = "valor")
    protected List<Valor> valores = new ArrayList<Valor>();

    /**
     * Gets the value of the codigo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigo(Integer value) {
        this.codigo = value;
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
     * Gets the value of the valor property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOf324931821440052060NillableString }
     *     
     */
    public ArrayOf324931821440052060NillableString getValor() {
        return valor;
    }

    /**
     * Sets the value of the valor property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOf324931821440052060NillableString }
     *     
     */
	public void setValor(ArrayOf324931821440052060NillableString valor) {
		this.valor = valor;
	}

    public List<Valor> getValores() {
        if (valores == null) {
        	valores = new ArrayList<Valor>();
        }
        return this.valores;
    }
    
    public void setValores(List<Valor> valores) {
		this.valores = valores;
	}

}

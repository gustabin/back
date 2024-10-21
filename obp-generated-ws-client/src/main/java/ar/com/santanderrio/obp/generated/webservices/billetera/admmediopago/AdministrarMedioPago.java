
package ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCuenta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="banco" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="canal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MediosPago" type="{http://billetera.prismamp.com.ar/administrarMedioPago/}MediosPago"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idCuenta",
    "banco",
    "canal",
    "mediosPago"
})
@XmlRootElement(name = "administrarMedioPago")
public class AdministrarMedioPago {

    protected int idCuenta;
    @XmlElement(required = true)
    protected String banco;
    @XmlElement(required = true)
    protected String canal;
    @XmlElement(name = "MediosPago", required = true)
    protected MediosPago mediosPago;

    /**
     * Obtiene el valor de la propiedad idCuenta.
     * 
     */
    public int getIdCuenta() {
        return idCuenta;
    }

    /**
     * Define el valor de la propiedad idCuenta.
     * 
     */
    public void setIdCuenta(int value) {
        this.idCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad banco.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBanco() {
        return banco;
    }

    /**
     * Define el valor de la propiedad banco.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBanco(String value) {
        this.banco = value;
    }

    /**
     * Obtiene el valor de la propiedad canal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Define el valor de la propiedad canal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanal(String value) {
        this.canal = value;
    }

    /**
     * Obtiene el valor de la propiedad mediosPago.
     * 
     * @return
     *     possible object is
     *     {@link MediosPago }
     *     
     */
    public MediosPago getMediosPago() {
        return mediosPago;
    }

    /**
     * Define el valor de la propiedad mediosPago.
     * 
     * @param value
     *     allowed object is
     *     {@link MediosPago }
     *     
     */
    public void setMediosPago(MediosPago value) {
        this.mediosPago = value;
    }

}

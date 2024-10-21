
package ar.com.santanderrio.obp.generated.webservices.solicitudes.conssolupgrade;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ConsSolUpgrade")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsSolUpgrade {

    @XmlElement(name = "nombre")
    protected String nombre;
    @XmlElement(name = "version")
    protected String version;
    @XmlElement(name = "canal")
    protected String canal;
    @XmlElement(name = "subcanal")
    protected String subcanal;
    @XmlElement(name = "firma")
    protected String firma;
    
    private ConsSolUpgradeBodyDatosFirma datosAFirmar;
    
    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the canal property.
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
     * Sets the value of the canal property.
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
     * Gets the value of the subcanal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubcanal() {
        return subcanal;
    }

    /**
     * Sets the value of the subcanal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubcanal(String value) {
        this.subcanal = value;
    }

    /**
     * Gets the value of the firma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirma() {
        return firma;
    }

    /**
     * Sets the value of the firma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirma(String value) {
        this.firma = value;
    }

    public ConsSolUpgradeBodyDatosFirma getDatosAFirmar() {
        return datosAFirmar;
    }

    public void setDatosAFirmar(ConsSolUpgradeBodyDatosFirma datosAFirmar) {
        this.datosAFirmar = datosAFirmar;
    }
    
}

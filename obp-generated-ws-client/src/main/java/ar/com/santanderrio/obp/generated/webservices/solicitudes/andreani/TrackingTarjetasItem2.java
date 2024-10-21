package ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sucursal",
    "codProd",
    "nroIdComp",
    "codEstado",
    "tipoCuenta"
})
public class TrackingTarjetasItem2 {

    @XmlElement(name = "Sucursal")
    protected String sucursal;
    @XmlElement(name = "Cod_Prod")
    protected String codProd;
    @XmlElement(name = "Nro_Id_Comp")
    protected String nroIdComp;
    @XmlElement(name = "Cod_Estado")
    protected String codEstado;
    @XmlElement(name = "TipoCuenta")
    protected String tipoCuenta;

    /**
     * Gets the value of the sucursal property.
     * 
     */
    public String getSucursal() {
        return sucursal;
    }

    /**
     * Sets the value of the sucursal property.
     * 
     */
    public void setSucursal(String value) {
        this.sucursal = value;
    }

    /**
     * Gets the value of the codProd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodProd() {
        return codProd;
    }

    /**
     * Sets the value of the codProd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodProd(String value) {
        this.codProd = value;
    }

    /**
     * Gets the value of the nroIdComp property.
     * 
     */
    public String getNroIdComp() {
        return nroIdComp;
    }

    /**
     * Sets the value of the nroIdComp property.
     * 
     */
    public void setNroIdComp(String value) {
        this.nroIdComp = value;
    }

    /**
     * Gets the value of the codEstado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodEstado() {
        return codEstado;
    }

    /**
     * Sets the value of the codEstado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodEstado(String value) {
        this.codEstado = value;
    }

    /**
     * Gets the value of the tipoCuenta property.
     * 
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Sets the value of the tipoCuenta property.
     * 
     */
    public void setTipoCuenta(String value) {
        this.tipoCuenta = value;
    }

}

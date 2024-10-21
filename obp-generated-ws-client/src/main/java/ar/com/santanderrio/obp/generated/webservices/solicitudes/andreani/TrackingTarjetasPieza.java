package ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "statusPieza",
    "resStatusPieza",
    "nroPieza",
    "codProd",
    "tipoCuenta",
    "medEnvi",
    "nyAp",
    "fecNac",
    "tipoDocum",
    "nroDocum",
    "tipoPersona",
    "bancaPieza",
    "sucUbic",
    "conDelivery",
    "codEstadoActual",
    "motivoEstadoActual",
    "fecCodEstadoActual",
    "codEstadoAnterior",
    "userId",
    "piezaAbierta",
    "nroPiezaOrigen",
    "canal",
    "codCampania",
    "codMotivoEmi",
    "codPaq",
    "tieneChequera",
    "nroIdentProd",
    "nroIdComponente",
    "canComp",
    "areaVariable",
    "codSucAnt",
    "fechaCodEstAnt",
    "codTransp",
    "statusEvento",
    "statusEventoSubCode",
    "resStatusEvento",
    "evento"
})
public class TrackingTarjetasPieza {

    @XmlElement(name = "StatusPieza")
    protected String statusPieza;
    @XmlElement(name = "ResStatusPieza")
    protected TrackingTarjetasResStatusPieza resStatusPieza;
    @XmlElement(name = "NroPieza")
    protected String nroPieza;
    @XmlElement(name = "CodProd")
    protected String codProd;
    @XmlElement(name = "TipoCuenta")
    protected String tipoCuenta;
    @XmlElement(name = "Med_Envi")
    protected String medEnvi;
    @XmlElement(name = "NyAp")
    protected String nyAp;
    @XmlElement(name = "FecNac")
    protected String fecNac;
    @XmlElement(name = "TipoDocum")
    protected String tipoDocum;
    @XmlElement(name = "NroDocum")
    protected String nroDocum;
    @XmlElement(name = "TipoPersona")
    protected String tipoPersona;
    @XmlElement(name = "BancaPieza")
    protected String bancaPieza;
    @XmlElement(name = "SucUbic")
    protected String sucUbic;
    @XmlElement(name = "ConDelivery")
    protected String conDelivery;
    @XmlElement(name = "CodEstadoActual")
    protected String codEstadoActual;
    @XmlElement(name = "MotivoEstadoActual")
    protected String motivoEstadoActual;
    @XmlElement(name = "FecCodEstadoActual")
    protected String fecCodEstadoActual;
    @XmlElement(name = "CodEstadoAnterior")
    protected String codEstadoAnterior;
    @XmlElement(name = "UserId")
    protected String userId;
    @XmlElement(name = "PiezaAbierta")
    protected String piezaAbierta;
    @XmlElement(name = "NroPiezaOrigen")
    protected String nroPiezaOrigen;
    @XmlElement(name = "Canal")
    protected String canal;
    @XmlElement(name = "CodCampania")
    protected String codCampania;
    @XmlElement(name = "CodMotivoEmi")
    protected String codMotivoEmi;
    @XmlElement(name = "CodPaq")
    protected String codPaq;
    @XmlElement(name = "TieneChequera")
    protected String tieneChequera;
    @XmlElement(name = "NroIdentProd")
    protected String nroIdentProd;
    @XmlElement(name = "NroIdComponente")
    protected String nroIdComponente;
    @XmlElement(name = "CanComp")
    protected String canComp;
    @XmlElement(name = "AreaVariable")
    protected String areaVariable;
    @XmlElement(name = "CodSucAnt")
    protected String codSucAnt;
    @XmlElement(name = "FechaCodEstAnt")
    protected String fechaCodEstAnt;
    @XmlElement(name = "CodTransp")
    protected String codTransp;
    @XmlElement(name = "StatusEvento")
    protected String statusEvento;
    @XmlElement(name = "StatusEvento_sub-code")
    protected String statusEventoSubCode;
    @XmlElement(name = "ResStatusEvento")
    protected TrackingTarjetasResStatusPieza resStatusEvento;
    @XmlElement(name = "Evento")
    protected TrackingTarjetasEvento evento;

    /**
     * Gets the value of the statusPieza property.
     * 
     */
    public String getStatusPieza() {
        return statusPieza;
    }

    /**
     * Sets the value of the statusPieza property.
     * 
     */
    public void setStatusPieza(String value) {
        this.statusPieza = value;
    }

    public TrackingTarjetasResStatusPieza getResStatusPieza() {
        return resStatusPieza;
    }

    public void setResStatusPieza(TrackingTarjetasResStatusPieza resStatusPieza) {
        this.resStatusPieza = resStatusPieza;
    }

    /**
     * Gets the value of the nroPieza property.
     * 
     */
    public String getNroPieza() {
        return nroPieza;
    }

    /**
     * Sets the value of the nroPieza property.
     * 
     */
    public void setNroPieza(String value) {
        this.nroPieza = value;
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

    /**
     * Gets the value of the medEnvi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedEnvi() {
        return medEnvi;
    }

    /**
     * Sets the value of the medEnvi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedEnvi(String value) {
        this.medEnvi = value;
    }

    /**
     * Gets the value of the nyAp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNyAp() {
        return nyAp;
    }

    /**
     * Sets the value of the nyAp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNyAp(String value) {
        this.nyAp = value;
    }

    /**
     * Gets the value of the fecNac property.
     * 
     */
    public String getFecNac() {
        return fecNac;
    }

    /**
     * Sets the value of the fecNac property.
     * 
     */
    public void setFecNac(String value) {
        this.fecNac = value;
    }

    /**
     * Gets the value of the tipoDocum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocum() {
        return tipoDocum;
    }

    /**
     * Sets the value of the tipoDocum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocum(String value) {
        this.tipoDocum = value;
    }

    /**
     * Gets the value of the nroDocum property.
     * 
     */
    public String getNroDocum() {
        return nroDocum;
    }

    /**
     * Sets the value of the nroDocum property.
     * 
     */
    public void setNroDocum(String value) {
        this.nroDocum = value;
    }

    /**
     * Gets the value of the tipoPersona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Sets the value of the tipoPersona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPersona(String value) {
        this.tipoPersona = value;
    }

    /**
     * Gets the value of the bancaPieza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBancaPieza() {
        return bancaPieza;
    }

    /**
     * Sets the value of the bancaPieza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBancaPieza(String value) {
        this.bancaPieza = value;
    }

    /**
     * Gets the value of the sucUbic property.
     * 
     */
    public String getSucUbic() {
        return sucUbic;
    }

    /**
     * Sets the value of the sucUbic property.
     * 
     */
    public void setSucUbic(String value) {
        this.sucUbic = value;
    }

    /**
     * Gets the value of the conDelivery property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConDelivery() {
        return conDelivery;
    }

    /**
     * Sets the value of the conDelivery property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConDelivery(String value) {
        this.conDelivery = value;
    }

    /**
     * Gets the value of the codEstadoActual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodEstadoActual() {
        return codEstadoActual;
    }

    /**
     * Sets the value of the codEstadoActual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodEstadoActual(String value) {
        this.codEstadoActual = value;
    }

    /**
     * Gets the value of the motivoEstadoActual property.
     * 
     */
    public String getMotivoEstadoActual() {
        return motivoEstadoActual;
    }

    /**
     * Sets the value of the motivoEstadoActual property.
     * 
     */
    public void setMotivoEstadoActual(String value) {
        this.motivoEstadoActual = value;
    }

    /**
     * Gets the value of the fecCodEstadoActual property.
     * 
     */
    public String getFecCodEstadoActual() {
        return fecCodEstadoActual;
    }

    /**
     * Sets the value of the fecCodEstadoActual property.
     * 
     */
    public void setFecCodEstadoActual(String value) {
        this.fecCodEstadoActual = value;
    }

    /**
     * Gets the value of the codEstadoAnterior property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodEstadoAnterior() {
        return codEstadoAnterior;
    }

    /**
     * Sets the value of the codEstadoAnterior property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodEstadoAnterior(String value) {
        this.codEstadoAnterior = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the piezaAbierta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPiezaAbierta() {
        return piezaAbierta;
    }

    /**
     * Sets the value of the piezaAbierta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPiezaAbierta(String value) {
        this.piezaAbierta = value;
    }

    /**
     * Gets the value of the nroPiezaOrigen property.
     * 
     */
    public String getNroPiezaOrigen() {
        return nroPiezaOrigen;
    }

    /**
     * Sets the value of the nroPiezaOrigen property.
     * 
     */
    public void setNroPiezaOrigen(String value) {
        this.nroPiezaOrigen = value;
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
     * Gets the value of the codCampania property.
     * 
     */
    public String getCodCampania() {
        return codCampania;
    }

    /**
     * Sets the value of the codCampania property.
     * 
     */
    public void setCodCampania(String value) {
        this.codCampania = value;
    }

    /**
     * Gets the value of the codMotivoEmi property.
     * 
     */
    public String getCodMotivoEmi() {
        return codMotivoEmi;
    }

    /**
     * Sets the value of the codMotivoEmi property.
     * 
     */
    public void setCodMotivoEmi(String value) {
        this.codMotivoEmi = value;
    }

    /**
     * Gets the value of the codPaq property.
     * 
     */
    public String getCodPaq() {
        return codPaq;
    }

    /**
     * Sets the value of the codPaq property.
     * 
     */
    public void setCodPaq(String value) {
        this.codPaq = value;
    }

    /**
     * Gets the value of the tieneChequera property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTieneChequera() {
        return tieneChequera;
    }

    /**
     * Sets the value of the tieneChequera property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTieneChequera(String value) {
        this.tieneChequera = value;
    }

    /**
     * Gets the value of the nroIdentProd property.
     * 
     */
    public String getNroIdentProd() {
        return nroIdentProd;
    }

    /**
     * Sets the value of the nroIdentProd property.
     * 
     */
    public void setNroIdentProd(String value) {
        this.nroIdentProd = value;
    }

    /**
     * Gets the value of the nroIdComponente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroIdComponente() {
        return nroIdComponente;
    }

    /**
     * Sets the value of the nroIdComponente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroIdComponente(String value) {
        this.nroIdComponente = value;
    }

    /**
     * Gets the value of the canComp property.
     * 
     */
    public String getCanComp() {
        return canComp;
    }

    /**
     * Sets the value of the canComp property.
     * 
     */
    public void setCanComp(String value) {
        this.canComp = value;
    }

    /**
     * Gets the value of the areaVariable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaVariable() {
        return areaVariable;
    }

    /**
     * Sets the value of the areaVariable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaVariable(String value) {
        this.areaVariable = value;
    }

    /**
     * Gets the value of the codSucAnt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodSucAnt() {
        return codSucAnt;
    }

    /**
     * Sets the value of the codSucAnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodSucAnt(String value) {
        this.codSucAnt = value;
    }

    /**
     * Gets the value of the fechaCodEstAnt property.
     * 
     */
    public String getFechaCodEstAnt() {
        return fechaCodEstAnt;
    }

    /**
     * Sets the value of the fechaCodEstAnt property.
     * 
     */
    public void setFechaCodEstAnt(String value) {
        this.fechaCodEstAnt = value;
    }

    /**
     * Gets the value of the codTransp property.
     * 
     */
    public String getCodTransp() {
        return codTransp;
    }

    /**
     * Sets the value of the codTransp property.
     * 
     */
    public void setCodTransp(String value) {
        this.codTransp = value;
    }

    /**
     * Gets the value of the statusEvento property.
     * 
     */
    public String getStatusEvento() {
        return statusEvento;
    }

    /**
     * Sets the value of the statusEvento property.
     * 
     */
    public void setStatusEvento(String value) {
        this.statusEvento = value;
    }

    public String getStatusEventoSubCode() {
        return statusEventoSubCode;
    }

    public void setStatusEventoSubCode(String statusEventoSubCode) {
        this.statusEventoSubCode = statusEventoSubCode;
    }

    public TrackingTarjetasResStatusPieza getResStatusEvento() {
        return resStatusEvento;
    }

    public void setResStatusEvento(TrackingTarjetasResStatusPieza resStatusEvento) {
        this.resStatusEvento = resStatusEvento;
    }

    /**
     * Gets the value of the evento property.
     * 
     * @return
     *     possible object is
     *     {@link TrackingTarjetasEvento }
     *     
     */
    public TrackingTarjetasEvento getEvento() {
        return evento;
    }

    /**
     * Sets the value of the evento property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrackingTarjetasEvento }
     *     
     */
    public void setEvento(TrackingTarjetasEvento value) {
        this.evento = value;
    }

}

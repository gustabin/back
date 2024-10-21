package ar.com.santanderrio.obp.servicios.scomp.client.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "CuentaOrigen")
public class CuentaOrigen {

    @XmlElement(name = "TipoCuentaOrigen")
    protected String tipoCuentaOrigen;
    
    @XmlElement(name = "SucursalCuentaOrigen")
    protected String sucursalCuentaOrigen;
    
    @XmlElement(name = "NumeroCuentaOrigen")
    protected String numeroCuentaOrigen;

    public String getTipoCuentaOrigen() {
        return tipoCuentaOrigen;
    }

    public void setTipoCuentaOrigen(String tipoCuentaOrigen) {
        this.tipoCuentaOrigen = tipoCuentaOrigen;
    }

    public String getSucursalCuentaOrigen() {
        return sucursalCuentaOrigen;
    }

    public void setSucursalCuentaOrigen(String sucursalCuentaOrigen) {
        this.sucursalCuentaOrigen = sucursalCuentaOrigen;
    }

    public String getNumeroCuentaOrigen() {
        return numeroCuentaOrigen;
    }

    public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
        this.numeroCuentaOrigen = numeroCuentaOrigen;
    }
    
}

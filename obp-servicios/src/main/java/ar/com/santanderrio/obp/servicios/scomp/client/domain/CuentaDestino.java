package ar.com.santanderrio.obp.servicios.scomp.client.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "CuentaDestino")
public class CuentaDestino {
    
    @XmlElement(name = "TipoCuentaDestino")
    protected String tipoCuentaDestino;
    
    @XmlElement(name = "SucursalCuentaDestino")
    protected String sucursalCuentaDestino;
    
    @XmlElement(name = "NumeroCuentaDestino")
    protected String numeroCuentaDestino;

    public String getTipoCuentaDestino() {
        return tipoCuentaDestino;
    }

    public void setTipoCuentaDestino(String tipoCuentaDestino) {
        this.tipoCuentaDestino = tipoCuentaDestino;
    }

    public String getSucursalCuentaDestino() {
        return sucursalCuentaDestino;
    }

    public void setSucursalCuentaDestino(String sucursalCuentaDestino) {
        this.sucursalCuentaDestino = sucursalCuentaDestino;
    }

    public String getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }

    public void setNumeroCuentaDestino(String numeroCuentaDestino) {
        this.numeroCuentaDestino = numeroCuentaDestino;
    }
    
    
}

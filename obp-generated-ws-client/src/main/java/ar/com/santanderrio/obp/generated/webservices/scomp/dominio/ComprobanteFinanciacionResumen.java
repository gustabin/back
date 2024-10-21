package ar.com.santanderrio.obp.generated.webservices.scomp.dominio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
public class ComprobanteFinanciacionResumen  extends ComprobanteAltaBase {
    
    @XmlElement(name = "Tarjeta")
    protected TarjetaFinanciacionResumen tarjeta;

    public TarjetaFinanciacionResumen getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaFinanciacionResumen tarjeta) {
        this.tarjeta = tarjeta;
    }
    
    
}

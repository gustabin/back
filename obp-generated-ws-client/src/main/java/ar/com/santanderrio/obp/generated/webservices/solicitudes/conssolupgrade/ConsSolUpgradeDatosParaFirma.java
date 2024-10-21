package ar.com.santanderrio.obp.generated.webservices.solicitudes.conssolupgrade;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "datos")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsSolUpgradeDatosParaFirma {

    @XmlElement(name = "nup")
    private String nup;
    
    @XmlElement(name = "timeStamp")
    private String timeStamp;

    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    
}

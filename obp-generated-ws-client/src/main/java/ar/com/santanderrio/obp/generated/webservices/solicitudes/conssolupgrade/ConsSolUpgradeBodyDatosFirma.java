package ar.com.santanderrio.obp.generated.webservices.solicitudes.conssolupgrade;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ConsSolUpgradeBodyDatosFirma {

    @XmlElement
    private ConsSolUpgradeDatosParaFirma datos;

    public ConsSolUpgradeDatosParaFirma getDatos() {
        return datos;
    }

    public void setDatos(ConsSolUpgradeDatosParaFirma datos) {
        this.datos = datos;
    }
    
}

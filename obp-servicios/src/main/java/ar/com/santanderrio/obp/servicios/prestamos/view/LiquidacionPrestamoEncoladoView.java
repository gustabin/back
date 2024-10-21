
package ar.com.santanderrio.obp.servicios.prestamos.view;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LiquidacionPrestamoEncoladoView {

    private String id;

    private String solicitudId;

    private AutentificacionDTO desafio;

    private String tokenSMS;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AutentificacionDTO getDesafio() {
        return desafio;
    }

    public void setDesafio(AutentificacionDTO desafio) {
        this.desafio = desafio;
    }

    public String getTokenSMS() {
        return tokenSMS;
    }

    public void setTokenSMS(String tokenSMS) {
        this.tokenSMS = tokenSMS;
    }

    public String getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(String solicitudId) {
        this.solicitudId = solicitudId;
    }

}
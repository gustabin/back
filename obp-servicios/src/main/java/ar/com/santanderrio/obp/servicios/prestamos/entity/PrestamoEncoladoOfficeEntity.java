package ar.com.santanderrio.obp.servicios.prestamos.entity;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Clase para consumir api de prestamos encolados
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PrestamoEncoladoOfficeEntity {

    @JsonProperty("HolderCode")
    private String HolderCode;

    @JsonProperty("LiftingCode")
    private String LiftingCode;

    @JsonProperty("HostCode")
    private String HostCode;

    public String getHolderCode() {
        return HolderCode;
    }

    public void setHolderCode(String holderCode) {
        HolderCode = holderCode;
    }

    public String getLiftingCode() {
        return LiftingCode;
    }

    public void setLiftingCode(String liftingCode) {
        LiftingCode = liftingCode;
    }

    public String getHostCode() {
        return HostCode;
    }

    public void setHostCode(String hostCode) {
        HostCode = hostCode;
    }
}
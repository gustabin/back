package ar.com.santanderrio.obp.servicios.solicitudes.dto;

import ar.com.santanderrio.obp.servicios.api.notificationsservice.entities.NotificationsServiceResponse;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;

public class TransferiSueldoResponseDTO {

    private String id;
    private Integer autoincrementalId;
    private String nup;
    private String typification;
    private String fechaCreacion;
    private String rpaState;
    private String resolutionType;
    private String stateChangeDate;
    private String rpaDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAutoincrementalId() {
        return autoincrementalId;
    }

    public void setAutoincrementalId(Integer autoincrementalId) {
        this.autoincrementalId = autoincrementalId;
    }

    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public String getTypification() {
        return typification;
    }

    public void setTypification(String typification) {
        this.typification = typification;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getRpaState() {
        return rpaState;
    }

    public void setRpaState(String rpaState) {
        this.rpaState = rpaState;
    }

    public String getResolutionType() {
        return resolutionType;
    }

    public void setResolutionType(String resolutionType) {
        this.resolutionType = resolutionType;
    }

    public String getStateChangeDate() {
        return stateChangeDate;
    }

    public void setStateChangeDate(String stateChangeDate) {
        this.stateChangeDate = stateChangeDate;
    }

    public String getRpaDescription() {
        return rpaDescription;
    }

    public void setRpaDescription(String rpaDescription) {
        this.rpaDescription = rpaDescription;
    }

}

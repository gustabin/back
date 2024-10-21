package ar.com.santanderrio.obp.servicios.api.sso.consent.entities;

import java.util.Date;

public class UserConsentRepresentationModel {
    private String clientId;
    private Date createdDate;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}

package ar.com.santanderrio.obp.servicios.consent.dto;

import ar.com.santanderrio.obp.servicios.ws.jackson.FormattedDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;
import java.util.List;

public class ConsentDTO {
    private String clientId;
    private String clientName;
    @JsonSerialize(using = FormattedDateSerializer.class)
    private Date createdDate;
    private List<String> consentedAccounts;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getConsentedAccounts() {
        return consentedAccounts;
    }

    public void setConsentedAccounts(List<String> consentedAccounts) {
        this.consentedAccounts = consentedAccounts;
    }
}

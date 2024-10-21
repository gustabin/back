package ar.com.santanderrio.obp.servicios.consent.sei.view;

import ar.com.santanderrio.obp.servicios.consent.dto.ConsentDTO;

import java.util.List;

public class UserConsentsResponse {
    private List<ConsentDTO> consents;

    public UserConsentsResponse(List<ConsentDTO> consents) {
        super();
        this.consents = consents;
    }

    public List<ConsentDTO> getConsents() {
        return consents;
    }

    public void setConsents(List<ConsentDTO> consents) {
        this.consents = consents;
    }
}

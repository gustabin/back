package ar.com.santanderrio.obp.servicios.echeqapi.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class GuarantySign extends GuarantySigner {
    @JsonProperty("sign_id")
    private String signId;
    @JsonProperty("signer_sign_date")
    private String singDate;

    public GuarantySign() {
        // Do nothing
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public String getSingDate() {
        return singDate;
    }

    public void setSingDate(String singDate) {
        this.singDate = singDate;
    }
}

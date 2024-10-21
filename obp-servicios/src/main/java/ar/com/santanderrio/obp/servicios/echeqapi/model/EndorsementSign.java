package ar.com.santanderrio.obp.servicios.echeqapi.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class EndorsementSign extends EndorsementSigner {

    @JsonProperty("sign_id")
    private String signId;

    @JsonProperty("sign_date")
    private Date signDate;

    public EndorsementSign() {
        super();
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }
}

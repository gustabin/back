package ar.com.santanderrio.obp.servicios.echeqapi.dto;

import ar.com.santanderrio.obp.servicios.echeqapi.model.Client;
import ar.com.santanderrio.obp.servicios.echeqapi.model.Guaranty;
import ar.com.santanderrio.obp.servicios.echeqapi.model.GuarantySign;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class GuarantyOrderCreationResponse {

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("requester")
    private Client requester;

    @JsonProperty("guaranties")
    private List<Guaranty> guaranties;

    @JsonProperty("signers")
    private List<GuarantySign> signers;

    @JsonProperty("status")
    private String status;

    @JsonProperty("action")
    private String action;

    public GuarantyOrderCreationResponse() {
        // Do nothing
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Client getRequester() {
        return requester;
    }

    public void setRequester(Client requester) {
        this.requester = requester;
    }

    public List<Guaranty> getGuaranties() {
        return guaranties;
    }

    public void setGuaranties(List<Guaranty> guaranties) {
        this.guaranties = guaranties;
    }

    public List<GuarantySign> getSigners() {
        return signers;
    }

    public void setSigners(List<GuarantySign> signers) {
        this.signers = signers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

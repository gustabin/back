package ar.com.santanderrio.obp.servicios.echeqapi.dto;

import ar.com.santanderrio.obp.servicios.echeqapi.model.Echeq;
import ar.com.santanderrio.obp.servicios.echeqapi.model.EndorsementSign;
import ar.com.santanderrio.obp.servicios.echeqapi.model.EndorsementSigner;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class EcheqEndorsementCancellationResponse {

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("endorser")
    private EndorsementSigner endorser;

    @JsonProperty("echeqs")
    private List<Echeq> echeqs;

    @JsonProperty("signers")
    private List<EndorsementSign> signers;

    @JsonProperty("cancel_motive")
    private String cancelMotive;

    @JsonProperty("order_action")
    private String orderAction;

    @JsonProperty("order_status")
    private String orderStatus;

    @JsonProperty("status")
    private String status;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    public EcheqEndorsementCancellationResponse(){
        // Do nothing
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public EndorsementSigner getEndorser() {
        return endorser;
    }

    public void setEndorser(EndorsementSigner endorser) {
        this.endorser = endorser;
    }

    public List<Echeq> getEcheqs() {
        return echeqs;
    }

    public void setEcheqs(List<Echeq> echeqs) {
        this.echeqs = echeqs;
    }

    public List<EndorsementSign> getSigners() {
        return signers;
    }

    public void setSigners(List<EndorsementSign> signers) {
        this.signers = signers;
    }

    public String getCancelMotive() {
        return cancelMotive;
    }

    public void setCancelMotive(String cancelMotive) {
        this.cancelMotive = cancelMotive;
    }

    public String getOrderAction() {
        return orderAction;
    }

    public void setOrderAction(String orderAction) {
        this.orderAction = orderAction;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

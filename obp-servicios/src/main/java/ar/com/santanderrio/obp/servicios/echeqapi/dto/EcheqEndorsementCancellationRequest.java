package ar.com.santanderrio.obp.servicios.echeqapi.dto;

import ar.com.santanderrio.obp.servicios.echeqapi.model.Client;
import ar.com.santanderrio.obp.servicios.echeqapi.model.Echeq;
import ar.com.santanderrio.obp.servicios.echeqapi.model.EndorsementSigner;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class EcheqEndorsementCancellationRequest {

    @JsonProperty("cancel_motive")
    private String cancelMotive;

    @JsonProperty("endorser")
    private EndorsementSigner endorser;

    @JsonProperty("echeqs")
    private List<Echeq> echeqs;

    @JsonProperty("signers")
    private List<EndorsementSigner> endorsementSigners;

    @JsonProperty("client")
    private Client client;

    @JsonProperty("amount")
    private String amount;

    private EcheqEndorsementCancellationRequest(Builder builder){
        this.cancelMotive = builder.cancelMotive;
        this.endorser = builder.endorser;
        this.echeqs = builder.echeqs;
        this.endorsementSigners = builder.endorsementSigners;
        this.client = builder.client;
        this.amount = builder.amount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getCancelMotive() {
        return cancelMotive;
    }

    public void setCancelMotive(String cancelMotive) {
        this.cancelMotive = cancelMotive;
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

    public List<EndorsementSigner> getSigners() {
        return endorsementSigners;
    }

    public void setSigners(List<EndorsementSigner> endorsementSigners) {
        this.endorsementSigners = endorsementSigners;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public static class Builder {

        private String cancelMotive;
        private EndorsementSigner endorser;
        private List<Echeq> echeqs;
        private List<EndorsementSigner> endorsementSigners;
        private Client client;
        private String amount;

        public Builder cancelMotive(String cancelMotive) {
            this.cancelMotive = cancelMotive;
            return this;
        }

        public Builder endorser(EndorsementSigner endorser) {
            this.endorser = endorser;
            return this;
        }

        public Builder echeqs(List<Echeq> echeqs) {
            this.echeqs = echeqs;
            return this;
        }

        public Builder signers(List<EndorsementSigner> endorsementSigners) {
            this.endorsementSigners = endorsementSigners;
            return this;
        }

        public Builder client(Client client) {
            this.client = client;
            return this;
        }

        public Builder amount(String amount) {
            this.amount = amount;
            return this;
        }

        public EcheqEndorsementCancellationRequest build(){
            return new EcheqEndorsementCancellationRequest(this);
        }
    }

}

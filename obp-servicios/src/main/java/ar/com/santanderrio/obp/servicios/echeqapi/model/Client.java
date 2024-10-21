package ar.com.santanderrio.obp.servicios.echeqapi.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Client {

    @JsonProperty("client_document_number")
    private String documentNumber;

    @JsonProperty("client_document_type")
    private String documentType;

    @JsonProperty("client_nup")
    private String nup;

    @JsonProperty("client_type")
    private String clientType;

    @JsonProperty("client_name")
    private String clientName;

    @JsonProperty("client_birth")
    private String clientBirth;

    public Client() {}

    public static Builder builder () {
        return new Builder();
    }

    private Client (Builder builder) {
        this.documentNumber = builder.documentNumber;
        this.documentType = builder.documentType;
        this.nup = builder.nup;
        this.clientType = builder.clientType;
        this.clientName = builder.clientName;
        this.clientBirth = builder.clientBirth;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientBirth() {
        return clientBirth;
    }

    public void setClientBirth(String clientBirth) {
        this.clientBirth = clientBirth;
    }

    public static class Builder {
        private String documentNumber;
        private String documentType;
        private String nup;
        private String clientType;
        private String clientName;
        private String clientBirth;

        public Builder documentNumber(String documentNumber){
            this.documentNumber = documentNumber;
            return this;
        }

        public Builder documentType(String documentType){
            this.documentType = documentType;
            return this;
        }

        public Builder nup(String nup){
            this.nup = nup;
            return this;
        }

        public Builder clientType(String clientType){
            this.clientType = clientType;
            return this;
        }

        public Builder clientName(String clientName){
            this.clientName = clientName;
            return this;
        }

        public Builder clientBirth(String clientBirth){
            this.clientBirth = clientBirth;
            return this;
        }

        public Client build() {
            return new Client(this);
        }

    }
}

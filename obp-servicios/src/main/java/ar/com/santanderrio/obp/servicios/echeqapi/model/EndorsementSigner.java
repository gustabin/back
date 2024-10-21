package ar.com.santanderrio.obp.servicios.echeqapi.model;

import ar.com.santanderrio.obp.servicios.comun.model.TipoDocumentoEnum;
import org.codehaus.jackson.annotate.JsonProperty;

public class EndorsementSigner {

    @JsonProperty("client_name")
    private String clientName;

    @JsonProperty("document_number")
    private String documentNumber;

    @JsonProperty("document_type")
    private TipoDocumentoEnum documentType;

    public EndorsementSigner(){}

    public EndorsementSigner(Builder builder){
        this.clientName = builder.clientName;
        this.documentNumber = builder.documentNumber;
        this.documentType = builder.documentType;
    }

    public EndorsementSigner(String clientName, String documentNumber, TipoDocumentoEnum documentType) {
        this.clientName = clientName;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public TipoDocumentoEnum getDocumentType() {
        return documentType;
    }

    public void setDocumentType(TipoDocumentoEnum documentType) {
        this.documentType = documentType;
    }

    public static class Builder {
        private String clientName;
        private String documentNumber;
        private TipoDocumentoEnum documentType;

        public Builder clientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public Builder documentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }

        public Builder documentType(TipoDocumentoEnum documentType) {
            this.documentType = documentType;
            return this;
        }

        public EndorsementSigner build() {
            return new EndorsementSigner(this);
        }
    }
}

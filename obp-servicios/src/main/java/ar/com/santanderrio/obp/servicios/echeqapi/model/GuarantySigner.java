package ar.com.santanderrio.obp.servicios.echeqapi.model;

import ar.com.santanderrio.obp.servicios.comun.model.TipoDocumentoEnum;
import org.codehaus.jackson.annotate.JsonProperty;

public class GuarantySigner {

    @JsonProperty("signer_name")
    private String signerName;

    @JsonProperty("signer_document_number")
    private String signerDocumentNumber;

    @JsonProperty("signer_document_type")
    private TipoDocumentoEnum signerDocumentType;

    public GuarantySigner(){
        // Do nothing
    }

    public GuarantySigner(Builder builder) {
        this.signerName = builder.signerName;
        this.signerDocumentNumber = builder.signerDocumentNumber;
        this.signerDocumentType = builder.signerDocumentType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String signerName;
        private String signerDocumentNumber;
        private TipoDocumentoEnum signerDocumentType;

        public Builder clientName(String signerName) {
            this.signerName = signerName;
            return this;
        }

        public Builder documentNumber(String signerDocumentNumber) {
            this.signerDocumentNumber = signerDocumentNumber;
            return this;
        }

        public Builder documentType(TipoDocumentoEnum signerDocumentType) {
            this.signerDocumentType = signerDocumentType;
            return this;
        }

        public GuarantySigner build() {
            return new GuarantySigner(this);
        }
    }

    public String getSignerName() {
        return signerName;
    }

    public void setSignerName(String signerName) {
        this.signerName = signerName;
    }

    public String getSignerDocumentNumber() {
        return signerDocumentNumber;
    }

    public void setSignerDocumentNumber(String signerDocumentNumber) {
        this.signerDocumentNumber = signerDocumentNumber;
    }

    public TipoDocumentoEnum getSignerDocumentType() {
        return signerDocumentType;
    }

    public void setSignerDocumentType(TipoDocumentoEnum signerDocumentType) {
        this.signerDocumentType = signerDocumentType;
    }
}

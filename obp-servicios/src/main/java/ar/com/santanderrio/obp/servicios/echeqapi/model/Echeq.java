package ar.com.santanderrio.obp.servicios.echeqapi.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Echeq {

    @JsonProperty("coelsa_id")
    private String coelsaId;

    @JsonProperty("number")
    private String number;

    @JsonProperty("cmc7")
    private String cmc7;

    @JsonProperty("endorsee_name")
    private String endorseeName;

    @JsonProperty("endorsee_document_number")
    private String endorseeDocumentNumber;

    @JsonProperty("endorsee_document_type")
    private String endorseeDocumentType;

    @JsonProperty("endorsement_type")
    private String endorsementType;

    @JsonProperty("status")
    private String status;

    public Echeq() {}

    public Echeq(Builder builder) {
        super();
        this.coelsaId = builder.coelsaId;
        this.number = builder.number;
        this.cmc7 = builder.cmc7;
        this.endorseeName = builder.endorseeName;
        this.endorseeDocumentNumber = builder.endorseeDocumentNumber;
        this.endorseeDocumentType = builder.endorseeDocumentType;
        this.endorsementType = builder.endorsementType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getCoelsaId() {
        return coelsaId;
    }

    public void setCoelsaId(String coelsaId) {
        this.coelsaId = coelsaId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCmc7() {
        return cmc7;
    }

    public void setCmc7(String cmc7) {
        this.cmc7 = cmc7;
    }

    public String getEndorseeName() {
        return endorseeName;
    }

    public void setEndorseeName(String endorseeName) {
        this.endorseeName = endorseeName;
    }

    public String getEndorseeDocumentNumber() {
        return endorseeDocumentNumber;
    }

    public void setEndorseeDocumentNumber(String endorseeDocumentNumber) {
        this.endorseeDocumentNumber = endorseeDocumentNumber;
    }

    public String getEndorseeDocumentType() {
        return endorseeDocumentType;
    }

    public void setEndorseeDocumentType(String endorseeDocumentType) {
        this.endorseeDocumentType = endorseeDocumentType;
    }

    public String getEndorsementType() {
        return endorsementType;
    }

    public void setEndorsementType(String endorsementType) {
        this.endorsementType = endorsementType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class Builder {
        private String coelsaId;
        private String number;
        private String cmc7;
        private String endorseeName;
        private String endorseeDocumentNumber;
        private String endorseeDocumentType;
        private String endorsementType;

        public Builder coelsaId(String coelsaId) {
            this.coelsaId = coelsaId;
            return this;
        }
        public Builder number(String number) {
            this.number = number;
            return this;
        }
        public Builder cmc7(String cmc7) {
            this.cmc7 = cmc7;
            return this;
        }
        public Builder endorseeName(String endorseeName) {
            this.endorseeName = endorseeName;
            return this;
        }
        public Builder endorseeDocumentNumber(String endorseeDocumentNumber) {
            this.endorseeDocumentNumber = endorseeDocumentNumber;
            return this;
        }
        public Builder endorseeDocumentType(String endorseeDocumentType) {
            this.endorseeDocumentType = endorseeDocumentType;
            return this;
        }
        public Builder endorsementType(String endorsementType) {
            this.endorsementType = endorsementType;
            return this;
        }

        public Echeq build() {
            return new Echeq(this);
        }
    }
}

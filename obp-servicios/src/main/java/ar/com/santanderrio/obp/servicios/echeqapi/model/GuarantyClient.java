package ar.com.santanderrio.obp.servicios.echeqapi.model;

import ar.com.santanderrio.obp.servicios.comun.model.TipoDocumentoEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

public class GuarantyClient {
    @JsonProperty("name")
    protected String name;

    @JsonProperty("document_type")
    protected TipoDocumentoEnum documentType;

    @JsonProperty("document_number")
    protected String documentNumber;

    @JsonProperty("type")
    protected String type;

    @JsonProperty("nup")
    protected String nup;

    @JsonProperty("birth_date")
    protected String birthDate;

    public GuarantyClient() {}

    public static Builder builder() {
        return new Builder();
    }

    public GuarantyClient(Builder builder) {
        this.name = builder.name;
        this.documentType = builder.documentType;
        this.documentNumber = builder.documentNumber;
        this.type = builder.type;
        this.nup = builder.nup;
        this.birthDate = builder.birthDate;
    }

    public static class Builder {
        private String name;
        private TipoDocumentoEnum documentType;
        private String documentNumber;
        private String type;
        private String nup;
        private String birthDate;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder documentType(TipoDocumentoEnum documentType) {
            this.documentType = documentType;
            return this;
        }

        public Builder documentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder nup(String nup) {
            this.nup = nup;
            return this;
        }

        public Builder birthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public GuarantyClient build() {
            return new GuarantyClient(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TipoDocumentoEnum getDocumentType() {
        return documentType;
    }

    public void setDocumentType(TipoDocumentoEnum documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuarantyClient that = (GuarantyClient) o;

        return new EqualsBuilder()
                .append(name, that.name)
                .append(documentType, that.documentType)
                .append(documentNumber, that.documentNumber)
                .append(type, that.type)
                .append(nup, that.nup)
                .append(birthDate, that.birthDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(documentType)
                .append(documentNumber)
                .append(type)
                .append(nup)
                .append(birthDate)
                .toHashCode();
    }
}


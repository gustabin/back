package ar.com.santanderrio.obp.servicios.echeqapi.model;

import ar.com.santanderrio.obp.servicios.comun.model.TipoDocumentoEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

public class Guarantor extends GuarantyClient {

    @JsonProperty("address")
    private String address;

    public Guarantor(GuarantorBuilder builder) {
        this.name = builder.name;
        this.documentType = builder.documentType;
        this.documentNumber = builder.documentNumber;
        this.type = builder.type;
        this.nup = builder.nup;
        this.birthDate = builder.birthDate;
        this.address = builder.address;
    }

    public Guarantor() {
        super();
    }

    public static GuarantorBuilder guarantorBuilder() {
        return new GuarantorBuilder();
    }

    public static class GuarantorBuilder {
        private String address;
        private String name;
        private TipoDocumentoEnum documentType;
        private String documentNumber;
        private String type;
        private String nup;
        private String birthDate;

        public GuarantorBuilder address(String address) {
            this.address = address;
            return this;
        }

        public GuarantorBuilder name(String name) {
            this.name = name;
            return this;
        }

        public GuarantorBuilder documentType(TipoDocumentoEnum documentType) {
            this.documentType = documentType;
            return this;
        }

        public GuarantorBuilder documentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }

        public GuarantorBuilder type(String type) {
            this.type = type;
            return this;
        }

        public GuarantorBuilder nup(String nup) {
            this.nup = nup;
            return this;
        }

        public GuarantorBuilder birthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Guarantor build() {
            return new Guarantor(this);
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guarantor guarantor = (Guarantor) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(address, guarantor.address)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(address)
                .toHashCode();
    }
}

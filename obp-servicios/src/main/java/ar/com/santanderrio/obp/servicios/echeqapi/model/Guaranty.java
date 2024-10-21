package ar.com.santanderrio.obp.servicios.echeqapi.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.math.BigDecimal;

public class Guaranty {
    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private GuarantyType type;

    @JsonProperty("status")
    private String status;

    @JsonProperty("holder")
    private GuarantyClient holder;

    @JsonProperty("guarantor")
    private Guarantor guarantor;

    @JsonProperty("echeq")
    private Echeq echeq;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("motive")
    private String motive;

    public Guaranty() {}

    public Guaranty(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.status = builder.status;
        this.holder = builder.holder;
        this.guarantor = builder.guarantor;
        this.echeq = builder.echeq;
        this.amount = builder.amount;
        this.motive = builder.motive;

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private GuarantyType type;
        private String status;
        private GuarantyClient holder;
        private Guarantor guarantor;
        private Echeq echeq;
        private BigDecimal amount;
        private String motive;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder type(GuarantyType type) {
            this.type = type;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder holder(GuarantyClient holder) {
            this.holder = holder;
            return this;
        }

        public Builder guarantor(Guarantor guarantor) {
            this.guarantor = guarantor;
            return this;
        }

        public Builder echeq(Echeq echeq) {
            this.echeq = echeq;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder motive(String motive) {
            this.motive = motive;
            return this;
        }

        public Guaranty build() {
            return new Guaranty(this);
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GuarantyType getType() {
        return type;
    }

    public void setType(GuarantyType type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GuarantyClient getHolder() {
        return holder;
    }

    public void setHolder(GuarantyClient holder) {
        this.holder = holder;
    }

    public Guarantor getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(Guarantor guarantor) {
        this.guarantor = guarantor;
    }

    public Echeq getEcheq() {
        return echeq;
    }

    public void setEcheq(Echeq echeq) {
        this.echeq = echeq;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guaranty guaranty = (Guaranty) o;

        return new EqualsBuilder()
                .append(type, guaranty.type)
                .append(holder, guaranty.holder)
                .append(guarantor, guaranty.guarantor)
                .append(echeq, guaranty.echeq)
                .append(amount, guaranty.amount)
                .append(motive, guaranty.motive)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(type)
                .append(holder)
                .append(guarantor)
                .append(echeq)
                .append(amount)
                .append(motive)
                .toHashCode();
    }
}

package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Document {
    private Boolean isPrincipal;
    private DocumentTypeEnum type;
    private String number;

    public Document isPrincipal(Boolean isPrincipal) {
        this.isPrincipal = isPrincipal;
        return this;
    }

    public Document type(DocumentTypeEnum type) {
        this.type = type;
        return this;
    }

    public Document number(String number) {
        this.number = number;
        return this;
    }

    public Boolean getIsPrincipal() {
        return isPrincipal;
    }

    public void setIsPrincipal(Boolean isPrincipal) {
        this.isPrincipal = isPrincipal;
    }

    public DocumentTypeEnum getType() {
        return type;
    }

    public void setType(DocumentTypeEnum type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document)) return false;

        Document document = (Document) o;
        return new EqualsBuilder()
                .append(isPrincipal, document.isPrincipal)
                .append(type, document.type)
                .append(number, document.number)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(isPrincipal)
                .append(type)
                .append(number)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Document{" +
                "isPrincipal=" + isPrincipal +
                ", type=" + type +
                ", number='" + number + '\'' +
                '}';
    }
}

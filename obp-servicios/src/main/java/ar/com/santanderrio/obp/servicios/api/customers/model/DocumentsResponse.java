package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class DocumentsResponse {
    private List<Document> documents = null;

    public DocumentsResponse addDocumentsItem(Document documentsItem) {
        if (this.documents == null) {
            this.documents = new ArrayList<Document>();
        }
        this.documents.add(documentsItem);
        return this;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentsResponse)) return false;

        DocumentsResponse documents1 = (DocumentsResponse) o;
        return new EqualsBuilder()
                .append(documents, documents1.documents)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(documents)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Documents{" +
                "documents=" + documents +
                '}';
    }
}

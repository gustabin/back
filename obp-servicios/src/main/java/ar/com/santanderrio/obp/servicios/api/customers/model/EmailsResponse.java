package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class EmailsResponse {
    private List<Email> emails = null;

    public EmailsResponse addEmailsItem(Email emailsItem) {
        if (this.emails == null) {
            this.emails = new ArrayList<Email>();
        }
        this.emails.add(emailsItem);
        return this;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailsResponse)) return false;

        EmailsResponse emails1 = (EmailsResponse) o;
        return new EqualsBuilder()
                .append(emails, emails1.emails)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(emails)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Emails{" +
                "emails=" + emails +
                '}';
    }
}

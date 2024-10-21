package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class Contacts {
    private List<Phone> phones = null;
    private List<Email> emails = null;
    private List<Address> addresses = null;

    public Contacts addPhonesItem(Phone phonesItem) {
        if (this.phones == null) {
            this.phones = new ArrayList<Phone>();
        }
        this.phones.add(phonesItem);
        return this;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    //Chained setters methods
    public Contacts phones(List<Phone> phones) {
        this.phones = phones;
        return this;
    }

    public Contacts emails(List<Email> emails) {
        this.emails = emails;
        return this;
    }

    public Contacts addEmailsItem(Email emailsItem) {
        if (this.emails == null) {
            this.emails = new ArrayList<Email>();
        }
        this.emails.add(emailsItem);
        return this;
    }

    public Contacts addresses(List<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    public Contacts addAddressesItem(Address addressesItem) {
        if (this.addresses == null) {
            this.addresses = new ArrayList<Address>();
        }
        this.addresses.add(addressesItem);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Contacts)) return false;

        Contacts contacts = (Contacts) o;

        return new EqualsBuilder()
                .append(phones, contacts.phones)
                .append(emails, contacts.emails)
                .append(addresses, contacts.addresses)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(phones)
                .append(emails)
                .append(addresses)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "phones=" + phones +
                ", emails=" + emails +
                ", addresses=" + addresses +
                '}';
    }
}

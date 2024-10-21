package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customers {
    private String id;
    private CustomerTypeEnum type;
    private LanguageEnum language;
    private CustomerConditionEnum condition;
    private SegmentEnum segment;
    private List<String> audiences = null;
    private List<Document> documents = null;
    private PhoneBasic phone;
    private String email;
    private AddressBasic address;
    private HumanPerson humanPerson;
    private LegalPerson legalPerson;
    private Boolean isStaff;
    private Date updatedAt;
    private Date createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerTypeEnum getType() {
        return type;
    }

    public void setType(CustomerTypeEnum type) {
        this.type = type;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public CustomerConditionEnum getCondition() {
        return condition;
    }

    public void setCondition(CustomerConditionEnum condition) {
        this.condition = condition;
    }

    public SegmentEnum getSegment() {
        return segment;
    }

    public void setSegment(SegmentEnum segment) {
        this.segment = segment;
    }

    public List<String> getAudiences() {
        return audiences;
    }

    public void setAudiences(List<String> audiences) {
        this.audiences = audiences;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public PhoneBasic getPhone() {
        return phone;
    }

    public void setPhone(PhoneBasic phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressBasic getAddress() {
        return address;
    }

    public void setAddress(AddressBasic address) {
        this.address = address;
    }

    public HumanPerson getHumanPerson() {
        return humanPerson;
    }

    public void setHumanPerson(HumanPerson humanPerson) {
        this.humanPerson = humanPerson;
    }

    public LegalPerson getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(LegalPerson legalPerson) {
        this.legalPerson = legalPerson;
    }

    public Boolean getStaff() {
        return isStaff;
    }

    public void setStaff(Boolean staff) {
        isStaff = staff;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    //Chained setters methods
    public Customers id(String id) {
        this.id = id;
        return this;
    }

    public Customers type(CustomerTypeEnum type) {
        this.type = type;
        return this;
    }

    public Customers language(LanguageEnum language) {
        this.language = language;
        return this;
    }

    public Customers condition(CustomerConditionEnum condition) {
        this.condition = condition;
        return this;
    }

    public Customers segment(SegmentEnum segment) {
        this.segment = segment;
        return this;
    }

    public Customers audiences(List<String> audiences) {
        this.audiences = audiences;
        return this;
    }

    public Customers addAudiencesItem(String audiencesItem) {
        if (this.audiences == null) {
            this.audiences = new ArrayList<String>();
        }
        this.audiences.add(audiencesItem);
        return this;
    }


    public Customers documents(List<Document> documents) {
        this.documents = documents;
        return this;
    }

    public Customers addDocumentsItem(Document documentsItem) {
        if (this.documents == null) {
            this.documents = new ArrayList<Document>();
        }
        this.documents.add(documentsItem);
        return this;
    }

    public Customers phone(PhoneBasic phone) {
        this.phone = phone;
        return this;
    }

    public Customers email(String email) {
        this.email = email;
        return this;
    }

    public Customers address(AddressBasic address) {
        this.address = address;
        return this;
    }

    public Customers humanPerson(HumanPerson humanPerson) {
        this.humanPerson = humanPerson;
        return this;
    }

    public Customers legalPerson(LegalPerson legalPerson) {
        this.legalPerson = legalPerson;
        return this;
    }

    public Customers isStaff(Boolean isStaff) {
        this.isStaff = isStaff;
        return this;
    }

    public Customers updatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Customers createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Customers)) return false;

        Customers customers = (Customers) o;

        return new EqualsBuilder()
                .append(id, customers.id)
                .append(type, customers.type)
                .append(language, customers.language)
                .append(condition, customers.condition)
                .append(segment, customers.segment)
                .append(audiences, customers.audiences)
                .append(documents, customers.documents)
                .append(phone, customers.phone)
                .append(email, customers.email)
                .append(address, customers.address)
                .append(humanPerson, customers.humanPerson)
                .append(legalPerson, customers.legalPerson)
                .append(isStaff, customers.isStaff)
                .append(updatedAt, customers.updatedAt)
                .append(createdAt, customers.createdAt)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(type)
                .append(language)
                .append(condition)
                .append(segment)
                .append(audiences)
                .append(documents)
                .append(phone)
                .append(email)
                .append(address)
                .append(humanPerson)
                .append(legalPerson)
                .append(isStaff)
                .append(updatedAt)
                .append(createdAt)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", language=" + language +
                ", condition=" + condition +
                ", segment=" + segment +
                ", audiences=" + audiences +
                ", documents=" + documents +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", humanPerson=" + humanPerson +
                ", legalPerson=" + legalPerson +
                ", isStaff=" + isStaff +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                '}';
    }
}

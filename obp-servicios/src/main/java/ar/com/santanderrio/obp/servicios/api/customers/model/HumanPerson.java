package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class HumanPerson {
    private String firstName;
    private String lastName1;
    private String lastName2;
    private CustomerGenderEnum gender;
    private MaritalStatusEnum maritalStatus;
    private CustomerStudiesLevelEnum studiesLevel;
    private String nationality;
    private String residenceCountry;
    private String birthCountry;
    private EmploymentStatusEnum employmentStatus;
    private Date birthDate;
    private Date deceaseDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public CustomerGenderEnum getGender() {
        return gender;
    }

    public void setGender(CustomerGenderEnum gender) {
        this.gender = gender;
    }

    public MaritalStatusEnum getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatusEnum maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public CustomerStudiesLevelEnum getStudiesLevel() {
        return studiesLevel;
    }

    public void setStudiesLevel(CustomerStudiesLevelEnum studiesLevel) {
        this.studiesLevel = studiesLevel;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getResidenceCountry() {
        return residenceCountry;
    }

    public void setResidenceCountry(String residenceCountry) {
        this.residenceCountry = residenceCountry;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public EmploymentStatusEnum getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(EmploymentStatusEnum employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getDeceaseDate() {
        return deceaseDate;
    }

    public void setDeceaseDate(Date deceaseDate) {
        this.deceaseDate = deceaseDate;
    }

    //
    //Chained Setters methods
    public HumanPerson firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public HumanPerson lastName1(String lastName1) {
        this.lastName1 = lastName1;
        return this;
    }

    public HumanPerson lastName2(String lastName2) {
        this.lastName2 = lastName2;
        return this;
    }

    public HumanPerson gender(CustomerGenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public HumanPerson maritalStatus(MaritalStatusEnum maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public HumanPerson studiesLevel(CustomerStudiesLevelEnum studiesLevel) {
        this.studiesLevel = studiesLevel;
        return this;
    }


    public HumanPerson nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }


    public HumanPerson residenceCountry(String residenceCountry) {
        this.residenceCountry = residenceCountry;
        return this;
    }


    public HumanPerson birthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
        return this;
    }


    public HumanPerson employmentStatus(EmploymentStatusEnum employmentStatus) {
        this.employmentStatus = employmentStatus;
        return this;
    }


    public HumanPerson birthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public HumanPerson deceaseDate(Date deceaseDate) {
        this.deceaseDate = deceaseDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HumanPerson)) return false;

        HumanPerson that = (HumanPerson) o;
        return new EqualsBuilder()
                .append(firstName, that.firstName)
                .append(lastName1, that.lastName1)
                .append(lastName2, that.lastName2)
                .append(gender, that.gender)
                .append(maritalStatus, that.maritalStatus)
                .append(studiesLevel, that.studiesLevel)
                .append(nationality, that.nationality)
                .append(residenceCountry, that.residenceCountry)
                .append(birthCountry, that.birthCountry)
                .append(employmentStatus, that.employmentStatus)
                .append(birthDate, that.birthDate)
                .append(deceaseDate, that.deceaseDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(firstName)
                .append(lastName1)
                .append(lastName2)
                .append(gender)
                .append(maritalStatus)
                .append(studiesLevel)
                .append(nationality)
                .append(residenceCountry)
                .append(birthCountry)
                .append(employmentStatus)
                .append(birthDate)
                .append(deceaseDate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "HumanPerson{" +
                "firstName='" + firstName + '\'' +
                ", lastName1='" + lastName1 + '\'' +
                ", lastName2='" + lastName2 + '\'' +
                ", gender=" + gender +
                ", maritalStatus=" + maritalStatus +
                ", studiesLevel=" + studiesLevel +
                ", nationality='" + nationality + '\'' +
                ", residenceCountry='" + residenceCountry + '\'' +
                ", birthCountry='" + birthCountry + '\'' +
                ", employmentStatus=" + employmentStatus +
                ", birthDate=" + birthDate +
                ", deceaseDate=" + deceaseDate +
                '}';
    }
}

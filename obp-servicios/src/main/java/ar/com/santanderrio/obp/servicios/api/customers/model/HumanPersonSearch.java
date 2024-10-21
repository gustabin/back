package ar.com.santanderrio.obp.servicios.api.customers.model;

import java.util.Date;

public class HumanPersonSearch {
    private String firstName;
    private String lastName;
    private Date birthDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    //Chained Setter methods
    public HumanPersonSearch firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public HumanPersonSearch lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public HumanPersonSearch birthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    @Override
    public String toString() {
        return "HumanPersonSearch{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

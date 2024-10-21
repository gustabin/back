package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AddressBasic {
    private String streetName;
    private String buildingNumber;
    private String floor;
    private String department;
    private String city;
    private String zipCode;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    //Chained setters methods
    public AddressBasic streetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public AddressBasic buildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
        return this;
    }

    public AddressBasic floor(String floor) {
        this.floor = floor;
        return this;
    }

    public AddressBasic department(String department) {
        this.department = department;
        return this;
    }

    public AddressBasic city(String city) {
        this.city = city;
        return this;
    }

    public AddressBasic zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressBasic)) return false;

        AddressBasic that = (AddressBasic) o;
        return new EqualsBuilder()
                .append(streetName, that.streetName)
                .append(buildingNumber, that.buildingNumber)
                .append(floor, that.floor)
                .append(department, that.department)
                .append(city, that.city)
                .append(zipCode, that.zipCode)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(streetName)
                .append(buildingNumber)
                .append(floor)
                .append(department)
                .append(city)
                .append(zipCode)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "AddressBasic{" +
                "streetName='" + streetName + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", floor='" + floor + '\'' +
                ", department='" + department + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}


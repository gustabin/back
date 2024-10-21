package ar.com.santanderrio.obp.servicios.api.customers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;

public class Address {
    private String id;
    private AddressTypeEnum type;
    private String streetName;
    private BigDecimal buildingNumber;
    private String floor;
    private String department;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AddressTypeEnum getType() {
        return type;
    }

    public void setType(AddressTypeEnum type) {
        this.type = type;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public BigDecimal getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(BigDecimal buildingNumber) {
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    //Chained setters methods
    public Address id(String id) {
        this.id = id;
        return this;
    }

    public Address type(AddressTypeEnum type) {
        this.type = type;
        return this;
    }

    public Address streetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public Address buildingNumber(BigDecimal buildingNumber) {
        this.buildingNumber = buildingNumber;
        return this;
    }

    public Address floor(String floor) {
        this.floor = floor;
        return this;
    }

    public Address department(String department) {
        this.department = department;
        return this;
    }

    public Address city(String city) {
        this.city = city;
        return this;
    }

    public Address state(String state) {
        this.state = state;
        return this;
    }

    public Address country(String country) {
        this.country = country;
        return this;
    }

    public Address zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;
        return new EqualsBuilder()
                .append(id, address.id)
                .append(type, address.type)
                .append(streetName, address.streetName)
                .append(buildingNumber, address.buildingNumber)
                .append(floor, address.floor)
                .append(department, address.department)
                .append(city, address.city)
                .append(state, address.state)
                .append(country, address.country)
                .append(zipCode, address.zipCode)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(type)
                .append(streetName)
                .append(buildingNumber)
                .append(floor)
                .append(department)
                .append(city)
                .append(state)
                .append(country)
                .append(zipCode)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", streetName='" + streetName + '\'' +
                ", buildingNumber=" + buildingNumber +
                ", floor='" + floor + '\'' +
                ", department='" + department + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}

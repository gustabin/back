package ar.com.santanderrio.obp.servicios.personas.dto;

public class Person {
    private String nup;
    private String birthDate;
    private String personType;
    private String documentType;
    private String documentNumber;
    private String companyName;
    private String firstName;
    private String lastName;
    private String condition;
    private String gender;

    public Person() {
    }

    public Person(String nup, String birthDate, String personType, String documentType,
                  String documentNumber,
                  String companyName,
                  String firstName,
                  String lastName,
                  String condition) {
        this.nup = nup;
        this.birthDate = birthDate;
        this.personType = personType;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.condition = condition;
    }

    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }
    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCondition() {
        return condition;
    }

    public String getGender () {
        return gender;
    }

    public void setGender ( String gender ) {
        this.gender = gender;
    }
}

package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.creditcard;


public class CardAccountHolderDto {
    private String uniquePersonIdentifier;

    private String identificationType;

    private Long identificationNumber;

	private String firstName;

	private String lastName;

	public String getUniquePersonIdentifier() {
		return uniquePersonIdentifier;
	}

	public void setUniquePersonIdentifier(String uniquePersonIdentifier) {
		this.uniquePersonIdentifier = uniquePersonIdentifier;
	}

	public String getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}

	public Long getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(Long identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

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
}

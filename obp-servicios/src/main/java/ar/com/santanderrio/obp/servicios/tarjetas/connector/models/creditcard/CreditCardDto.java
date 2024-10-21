package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.creditcard;

public class CreditCardDto {
    private String id;

    private CreditAccountDto creditAccount;

    private String brand;

    private Long creditCardNumber;

    private String embossedName;

    private String expirationYear;

    private String expirationMonth;

    private String validFromMonth;

    private String validFromYear;

    private String fourthLine;

    private Boolean isWomen;

    private String status;

    private Integer signer;

    private String type;

    private String createDate;

    private PercentageLimitsDto percentageLimits;
    private CardAccountHolderDto cardOwner;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CreditAccountDto getCreditAccount() {
		return creditAccount;
	}
	public void setCreditAccount(CreditAccountDto creditAccount) {
		this.creditAccount = creditAccount;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Long getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(Long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getEmbossedName() {
		return embossedName;
	}
	public void setEmbossedName(String embossedName) {
		this.embossedName = embossedName;
	}
	public String getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}
	public String getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	public String getValidFromMonth() {
		return validFromMonth;
	}
	public void setValidFromMonth(String validFromMonth) {
		this.validFromMonth = validFromMonth;
	}
	public String getValidFromYear() {
		return validFromYear;
	}
	public void setValidFromYear(String validFromYear) {
		this.validFromYear = validFromYear;
	}
	public String getFourthLine() {
		return fourthLine;
	}
	public void setFourthLine(String fourthLine) {
		this.fourthLine = fourthLine;
	}
	public Boolean getIsWomen() {
		return isWomen;
	}
	public void setIsWomen(Boolean isWomen) {
		this.isWomen = isWomen;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getSigner() {
		return signer;
	}
	public void setSigner(Integer signer) {
		this.signer = signer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public PercentageLimitsDto getPercentageLimits() {
		return percentageLimits;
	}
	public void setPercentageLimits(PercentageLimitsDto percentageLimits) {
		this.percentageLimits = percentageLimits;
	}
	public CardAccountHolderDto getCardOwner() {
		return cardOwner;
	}
	public void setCardOwner(CardAccountHolderDto cardOwner) {
		this.cardOwner = cardOwner;
	}
}
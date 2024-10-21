package ar.com.santanderrio.obp.servicios.tarjetas.connector.models.creditcard;

public class CreditAccountDto {
    private String id;

    private Long creditAccountNumber;

    private Integer verifyCode;

    private Integer billCycle;

    private Integer branchCode;

    private String brand;

    private String companyBranch;

    private String companyCode;

    private Integer durationMonthly;

    private AccountPercentageLimitDto accountPercentageLimit;

    private String gaf;

    private String status;

    private String product;

    private String category;

    private Boolean controlLimitEnabled;

    private Boolean bonusEnabled;

    private Boolean warrantyEnabled;

    private CardAccountHolderDto accountOwner;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCreditAccountNumber() {
		return creditAccountNumber;
	}

	public void setCreditAccountNumber(Long creditAccountNumber) {
		this.creditAccountNumber = creditAccountNumber;
	}

	public Integer getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(Integer verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Integer getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(Integer billCycle) {
		this.billCycle = billCycle;
	}

	public Integer getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(Integer branchCode) {
		this.branchCode = branchCode;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCompanyBranch() {
		return companyBranch;
	}

	public void setCompanyBranch(String companyBranch) {
		this.companyBranch = companyBranch;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Integer getDurationMonthly() {
		return durationMonthly;
	}

	public void setDurationMonthly(Integer durationMonthly) {
		this.durationMonthly = durationMonthly;
	}

	public AccountPercentageLimitDto getAccountPercentageLimit() {
		return accountPercentageLimit;
	}

	public void setAccountPercentageLimit(AccountPercentageLimitDto accountPercentageLimit) {
		this.accountPercentageLimit = accountPercentageLimit;
	}

	public String getGaf() {
		return gaf;
	}

	public void setGaf(String gaf) {
		this.gaf = gaf;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getControlLimitEnabled() {
		return controlLimitEnabled;
	}

	public void setControlLimitEnabled(Boolean controlLimitEnabled) {
		this.controlLimitEnabled = controlLimitEnabled;
	}

	public Boolean getBonusEnabled() {
		return bonusEnabled;
	}

	public void setBonusEnabled(Boolean bonusEnabled) {
		this.bonusEnabled = bonusEnabled;
	}

	public Boolean getWarrantyEnabled() {
		return warrantyEnabled;
	}

	public void setWarrantyEnabled(Boolean warrantyEnabled) {
		this.warrantyEnabled = warrantyEnabled;
	}

	public CardAccountHolderDto getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(CardAccountHolderDto accountOwner) {
		this.accountOwner = accountOwner;
	}
}

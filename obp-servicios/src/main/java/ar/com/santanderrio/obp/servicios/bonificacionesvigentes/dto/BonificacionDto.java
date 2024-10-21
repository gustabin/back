package ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BonificacionDto {
	
	private String nup;
    private String legal;
    private String insuranceType;
    private int policyNumber;
    private double policyAmount;
    private int certificateNumber;
    private int branchCode;
    private String branchDescription;
    private int benefitPercentage;
    private int benefitCap;
    private Date policyRegistrationDate;
    private Date validityDateFrom;
    private Date validityDateTo;
    private double benefitAmount;
	
    
    public String getNup() {
		return nup;
	}
	public void setNup(String nup) {
		this.nup = nup;
	}
	public String getLegal() {
		return legal;
	}
	public void setLegal(String legal) {
		this.legal = legal;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public int getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(int policyNumber) {
		this.policyNumber = policyNumber;
	}
	public double getPolicyAmount() {
		return policyAmount;
	}
	public void setPolicyAmount(double policyAmount) {
		this.policyAmount = policyAmount;
	}
	public int getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(int certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public int getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(int branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchDescription() {
		return branchDescription;
	}
	public void setBranchDescription(String branchDescription) {
		this.branchDescription = branchDescription;
	}
	public int getBenefitPercentage() {
		return benefitPercentage;
	}
	public void setBenefitPercentage(int benefitPercentage) {
		this.benefitPercentage = benefitPercentage;
	}
	public int getBenefitCap() {
		return benefitCap;
	}
	public void setBenefitCap(int benefitCap) {
		this.benefitCap = benefitCap;
	}
	public Date getPolicyRegistrationDate() {
		return policyRegistrationDate;
	}
	public void setPolicyRegistrationDate(Date policyRegistrationDate) {
		this.policyRegistrationDate = policyRegistrationDate;
	}
	public Date getValidityDateFrom() {
		return validityDateFrom;
	}
	public void setValidityDateFrom(Date validityDateFrom) {
		this.validityDateFrom = validityDateFrom;
	}
	public Date getValidityDateTo() {
		return validityDateTo;
	}
	public void setValidityDateTo(Date validityDateTo) {
		this.validityDateTo = validityDateTo;
	}
	public double getBenefitAmount() {
		return benefitAmount;
	}
	public void setBenefitAmount(double benefitAmount) {
		this.benefitAmount = benefitAmount;
	}
	
	
    
    

}

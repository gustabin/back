package ar.com.santanderrio.obp.servicios.prestamos.entity;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Clase para consumir api de prestamos encolados
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PrestamoEncoladoEntity {


    @JsonProperty("_id")
    private String id;

    @JsonProperty("Amount")
    private BigDecimal Amount;

    @JsonProperty("CreationDate")
    private Date CreationDate;

    @JsonProperty("LoanType")
    private String LoanType;

    @JsonProperty("Origin")
    private String Origin;

    @JsonProperty("ProductCode")
    private Integer ProductCode;

    @JsonProperty("SubProductCode")
    private Integer SubProductCode;

    @JsonProperty("DestinationFunds")
    private Integer DestinationFunds;

    @JsonProperty("Office")
    private PrestamoEncoladoOfficeEntity Office;

    @JsonProperty("DebitAccount")
    private PrestamoEncoladoDebitAccountEntity DebitAccount;

    @JsonProperty("Quotas")
    private Integer Quotas;

    @JsonProperty("ApprovalDate")
    private Date ApprovalDate;

    @JsonProperty("FormDate")
    private Date FormDate;

    @JsonProperty("FirstExpirationDate")
    private Date FirstExpirationDate;

    @JsonProperty("FeeType")
    private String FeeType;

    @JsonProperty("Tna")
    private String Tna;

    @JsonProperty("IsLineUva")
    private Boolean IsLineUva;

    @JsonProperty("FrontOrigin")
    private String frontOrigin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public String getLoanType() {
        return LoanType;
    }

    public void setLoanType(String loanType) {
        LoanType = loanType;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public Integer getProductCode() {
        return ProductCode;
    }

    public void setProductCode(Integer productCode) {
        ProductCode = productCode;
    }

    public Integer getSubProductCode() {
        return SubProductCode;
    }

    public void setSubProductCode(Integer subProductCode) {
        SubProductCode = subProductCode;
    }

    public Integer getDestinationFunds() {
        return DestinationFunds;
    }

    public void setDestinationFunds(Integer destinationFunds) {
        DestinationFunds = destinationFunds;
    }

    public PrestamoEncoladoOfficeEntity getOffice() {
        return Office;
    }

    public void setOffice(PrestamoEncoladoOfficeEntity office) {
        Office = office;
    }

    public PrestamoEncoladoDebitAccountEntity getDebitAccount() {
        return DebitAccount;
    }

    public void setDebitAccount(PrestamoEncoladoDebitAccountEntity debitAccount) {
        DebitAccount = debitAccount;
    }

    public Integer getQuotas() {
        return Quotas;
    }

    public void setQuotas(Integer quotas) {
        Quotas = quotas;
    }

    public Date getApprovalDate() {
        return ApprovalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        ApprovalDate = approvalDate;
    }

    public Date getFormDate() {
        return FormDate;
    }

    public void setFormDate(Date formDate) {
        FormDate = formDate;
    }

    public Date getFirstExpirationDate() {
        return FirstExpirationDate;
    }

    public void setFirstExpirationDate(Date firstExpirationDate) {
        FirstExpirationDate = firstExpirationDate;
    }

    public String getFeeType() {
        return FeeType;
    }

    public void setFeeType(String feeType) {
        FeeType = feeType;
    }

    public String getTna() {
        return Tna;
    }

    public void setTna(String tna) {
        Tna = tna;
    }

    public Boolean getLineUva() {
        return IsLineUva;
    }

    public void setLineUva(Boolean lineUva) {
        IsLineUva = lineUva;
    }

	public String getFrontOrigin() {
		return frontOrigin;
	}

	public void setFrontOrigin(String frontOrigin) {
		this.frontOrigin = frontOrigin;
	}
}
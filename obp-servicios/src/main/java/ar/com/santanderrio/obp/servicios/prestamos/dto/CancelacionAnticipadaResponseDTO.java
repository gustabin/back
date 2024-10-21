package ar.com.santanderrio.obp.servicios.prestamos.dto;

import java.math.BigDecimal;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CancelacionAnticipadaResponseDTO {

	private String loanNumber;
	private String acountNumber;
	private BigDecimal totalAmount;
	private BigDecimal capitalAmount;
	private BigDecimal interestAmount;
	private BigDecimal iva;
	private BigDecimal ivaAdditional;
	private BigDecimal ivaTotalAmount;
	private BigDecimal otherTaxesAmount;
	private BigDecimal financialDebtTaxAmount;
	private BigDecimal iibbTaxAmount;
	private BigDecimal commissionTotalAmount;
	private BigDecimal totalAmountUE;
	private BigDecimal capitalAmountUE;
	private BigDecimal interestAmountUE;
	private BigDecimal expositionUnitCotizationUE;
	private String cotizationDateUE;
	private String nio;

	public CancelacionAnticipadaResponseDTO() {
		super();
	}

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public String getAcountNumber() {
		return acountNumber;
	}

	public void setAcountNumber(String acountNumber) {
		this.acountNumber = acountNumber;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getIvaAdditional() {
		return ivaAdditional;
	}

	public void setIvaAdditional(BigDecimal ivaAdditional) {
		this.ivaAdditional = ivaAdditional;
	}

	public BigDecimal getIvaTotalAmount() {
		return ivaTotalAmount;
	}

	public void setIvaTotalAmount(BigDecimal ivaTotalAmount) {
		this.ivaTotalAmount = ivaTotalAmount;
	}

	public BigDecimal getOtherTaxesAmount() {
		return otherTaxesAmount;
	}

	public void setOtherTaxesAmount(BigDecimal otherTaxesAmount) {
		this.otherTaxesAmount = otherTaxesAmount;
	}

	public BigDecimal getFinancialDebtTaxAmount() {
		return financialDebtTaxAmount;
	}

	public void setFinancialDebtTaxAmount(BigDecimal financialDebtTaxAmount) {
		this.financialDebtTaxAmount = financialDebtTaxAmount;
	}

	public BigDecimal getIibbTaxAmount() {
		return iibbTaxAmount;
	}

	public void setIibbTaxAmount(BigDecimal iibbTaxAmount) {
		this.iibbTaxAmount = iibbTaxAmount;
	}

	public BigDecimal getCommissionTotalAmount() {
		return commissionTotalAmount;
	}

	public void setCommissionTotalAmount(BigDecimal commissionTotalAmount) {
		this.commissionTotalAmount = commissionTotalAmount;
	}

	public BigDecimal getTotalAmountUE() {
		return totalAmountUE;
	}

	public void setTotalAmountUE(BigDecimal totalAmountUE) {
		this.totalAmountUE = totalAmountUE;
	}

	public BigDecimal getCapitalAmountUE() {
		return capitalAmountUE;
	}

	public void setCapitalAmountUE(BigDecimal capitalAmountUE) {
		this.capitalAmountUE = capitalAmountUE;
	}

	public BigDecimal getInterestAmountUE() {
		return interestAmountUE;
	}

	public void setInterestAmountUE(BigDecimal interestAmountUE) {
		this.interestAmountUE = interestAmountUE;
	}

	public BigDecimal getExpositionUnitCotizationUE() {
		return expositionUnitCotizationUE;
	}

	public void setExpositionUnitCotizationUE(BigDecimal expositionUnitCotizationUE) {
		this.expositionUnitCotizationUE = expositionUnitCotizationUE;
	}

	public String getCotizationDateUE() {
		return cotizationDateUE;
	}

	public void setCotizationDateUE(String cotizationDateUE) {
		this.cotizationDateUE = cotizationDateUE;
	}

	public String getNio() {
		return nio;
	}

	public void setNio(String nio) {
		this.nio = nio;
	}

	public BigDecimal getCapitalAmount() {
		return capitalAmount;
	}

	public void setCapitalAmount(BigDecimal capitalAmount) {
		this.capitalAmount = capitalAmount;
	}
}

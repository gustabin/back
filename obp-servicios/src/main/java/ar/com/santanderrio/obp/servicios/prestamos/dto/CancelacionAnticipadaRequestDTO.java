package ar.com.santanderrio.obp.servicios.prestamos.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CancelacionAnticipadaRequestDTO {
	private String loanBranch;
	private String loanNumber;
	private String currencyCode;
	private String accountBranch;
	private String accountNumber;

	public CancelacionAnticipadaRequestDTO() {
		super();
	}

	public CancelacionAnticipadaRequestDTO(String loanBranch, String loanNumber, String currencyCode,
			String accountBranch, String accountNumber) {
		this();
		this.loanBranch = loanBranch;
		this.loanNumber = loanNumber;
		this.currencyCode = currencyCode;
		this.accountBranch = accountBranch;
		this.accountNumber = accountNumber;
	}

	public String getLoanBranch() {
		return loanBranch;
	}

	public void setLoanBranch(String loanBranch) {
		this.loanBranch = loanBranch;
	}

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}

package ar.com.santanderrio.obp.servicios.prestamos.dto;

import java.math.BigDecimal;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PagoCuotaRequestDTO {

	private String accountBranch;
	private String accountType;
	private String accountNumber;
	private String loanBranch;
	private BigDecimal amount;
	private String loanNumber;
	private BigDecimal loanQuote;
	private String overdraftLimit;
	private String nio;

	public PagoCuotaRequestDTO() {
	}

	public PagoCuotaRequestDTO(String accountBranch, String accountType, String accountNumber, String loanBranch,
			BigDecimal amount, String loanNumber, BigDecimal loanQuote) {
		this();
		this.accountBranch = accountBranch;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.loanBranch = loanBranch;
		this.amount = amount;
		this.loanNumber = loanNumber;
		this.loanQuote = loanQuote;
	}

	public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getLoanBranch() {
		return loanBranch;
	}

	public void setLoanBranch(String loanBranch) {
		this.loanBranch = loanBranch;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public BigDecimal getLoanQuote() {
		return loanQuote;
	}

	public void setLoanQuote(BigDecimal loanQuote) {
		this.loanQuote = loanQuote;
	}

	public String getOverdraftLimit() {
		return overdraftLimit;
	}

	public void setOverdraftLimit(String overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	public String getNio() {
		return nio;
	}

	public void setNio(String nio) {
		this.nio = nio;
	}
}

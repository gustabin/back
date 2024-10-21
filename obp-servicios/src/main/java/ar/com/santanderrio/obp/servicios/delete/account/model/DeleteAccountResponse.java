package ar.com.santanderrio.obp.servicios.delete.account.model;

import java.util.List;

public class DeleteAccountResponse {

    private String code;
    private String message;
    private String newAccountNumber;
    private List<Error> errors;
    private List<Error> warnings;
    private String arsBalanceAmount;
    private String usdBalanceAmount;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public List<Error> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<Error> warnings) {
		this.warnings = warnings;
	}

	public String getNewAccountNumber() {
		return newAccountNumber;
	}

	public void setNewAccountNumber(String newAccountNumber) {
		this.newAccountNumber = newAccountNumber;
	}

	public String getArsBalanceAmount() {
		return arsBalanceAmount;
	}

	public void setArsBalanceAmount(String arsBalanceAmount) {
		this.arsBalanceAmount = arsBalanceAmount;
	}

	public String getUsdBalanceAmount() {
		return usdBalanceAmount;
	}

	public void setUsdBalanceAmount(String usdBalanceAmount) {
		this.usdBalanceAmount = usdBalanceAmount;
	}
}

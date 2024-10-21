package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class SolicitudFinanciacionView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class SolicitudFinanciacionView {

	/** The offer id. */
	private String offerId;

	/** The term requested. */
	private String termRequested;

	/** The first due date. */
	private String firstDueDate;

	/** The amount requested. */
	private String amountRequested;

	/** The declared income. */
	private String declaredIncome;

	/** The mail. */
	private String mail;

	/**
	 * Gets the offer id.
	 *
	 * @return the offer id
	 */
	public String getOfferId() {
		return offerId;
	}

	/**
	 * Sets the offer id.
	 *
	 * @param offerId the new offer id
	 */
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	/**
	 * Gets the term requested.
	 *
	 * @return the term requested
	 */
	public String getTermRequested() {
		return termRequested;
	}

	/**
	 * Sets the term requested.
	 *
	 * @param termRequested the new term requested
	 */
	public void setTermRequested(String termRequested) {
		this.termRequested = termRequested;
	}

	/**
	 * Gets the first due date.
	 *
	 * @return the first due date
	 */
	public String getFirstDueDate() {
		return firstDueDate;
	}

	/**
	 * Sets the first due date.
	 *
	 * @param firstDueDate the new first due date
	 */
	public void setFirstDueDate(String firstDueDate) {
		this.firstDueDate = firstDueDate;
	}

	/**
	 * Gets the amount requested.
	 *
	 * @return the amount requested
	 */
	public String getAmountRequested() {
		return amountRequested;
	}

	/**
	 * Sets the amount requested.
	 *
	 * @param amountRequested the new amount requested
	 */
	public void setAmountRequested(String amountRequested) {
		this.amountRequested = amountRequested;
	}

	/**
	 * Gets the declared income.
	 *
	 * @return the declared income
	 */
	public String getDeclaredIncome() {
		return declaredIncome;
	}

	/**
	 * Sets the declared income.
	 *
	 * @param declaredIncome the new declared income
	 */
	public void setDeclaredIncome(String declaredIncome) {
		this.declaredIncome = declaredIncome;
	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

}

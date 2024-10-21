package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class OfertaFinanciacionEntity.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class OfertaFinanciacionEntity {

	/** The offer id. */
	@JsonProperty("offerId")
	private String offerId;

	/** The nup. */
	@JsonProperty("nup")
	private String nup;

	/** The product description. */
	@JsonProperty("productDescription")
	private String productDescription;

	/** The term. */
	@JsonProperty("term")
	private String term;

	/** The validity date. */
	@JsonProperty("validityDate")
	private String validityDate;

	/** The state. */
	@JsonProperty("state")
	private String state;

	/** The max amount. */
	@JsonProperty("maxAmount")
	private String maxAmount;

	/** The rate. */
	@JsonProperty("rate")
	private String rate;

	/** The first quota value. */
	@JsonProperty("firstQuotaValue")
	private String firstQuotaValue;

	/** The interest. */
	@JsonProperty("interest")
	private String interest;

	/** The iva. */
	@JsonProperty("iva")
	private String iva;

	/** The insurance. */
	@JsonProperty("insurance")
	private String insurance;

	/** The other charges. */
	@JsonProperty("otherCharges")
	private String otherCharges;

	/** The pure quota value. */
	@JsonProperty("pureQuotaValue")
	private String pureQuotaValue;

	/** The cft. */
	@JsonProperty("cft")
	private String cft;

	/** The rate type. */
	@JsonProperty("rateType")
	private String rateType;


	/** The rate type. */
	@JsonProperty("refinancingType")
	private String refinancingType;

	/** The tna. */
	@JsonProperty("tna")
	private String tna;

	/** The tea. */
	@JsonProperty("tea")
	private String tea;

	/** The credit card description. */
	@JsonProperty("creditCardDescription")
	private String creditCardDescription;

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
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the product description.
	 *
	 * @return the product description
	 */
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * Sets the product description.
	 *
	 * @param productDescription the new product description
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	 * Gets the term.
	 *
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * Sets the term.
	 *
	 * @param term the new term
	 */
	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * Gets the validity date.
	 *
	 * @return the validity date
	 */
	public String getValidityDate() {
		return validityDate;
	}

	/**
	 * Sets the validity date.
	 *
	 * @param validityDate the new validity date
	 */
	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the max amount.
	 *
	 * @return the max amount
	 */
	public String getMaxAmount() {
		return maxAmount;
	}

	/**
	 * Sets the max amount.
	 *
	 * @param maxAmount the new max amount
	 */
	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}

	/**
	 * Gets the rate.
	 *
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * Sets the rate.
	 *
	 * @param rate the new rate
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

	/**
	 * Gets the first quota value.
	 *
	 * @return the first quota value
	 */
	public String getFirstQuotaValue() {
		return firstQuotaValue;
	}

	/**
	 * Sets the first quota value.
	 *
	 * @param firstQuotaValue the new first quota value
	 */
	public void setFirstQuotaValue(String firstQuotaValue) {
		this.firstQuotaValue = firstQuotaValue;
	}

	/**
	 * Gets the interest.
	 *
	 * @return the interest
	 */
	public String getInterest() {
		return interest;
	}

	/**
	 * Sets the interest.
	 *
	 * @param interest the new interest
	 */
	public void setInterest(String interest) {
		this.interest = interest;
	}

	/**
	 * Gets the iva.
	 *
	 * @return the iva
	 */
	public String getIva() {
		return iva;
	}

	/**
	 * Sets the iva.
	 *
	 * @param iva the new iva
	 */
	public void setIva(String iva) {
		this.iva = iva;
	}

	/**
	 * Gets the insurance.
	 *
	 * @return the insurance
	 */
	public String getInsurance() {
		return insurance;
	}

	/**
	 * Sets the insurance.
	 *
	 * @param insurance the new insurance
	 */
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	/**
	 * Gets the other charges.
	 *
	 * @return the other charges
	 */
	public String getOtherCharges() {
		return otherCharges;
	}

	/**
	 * Sets the other charges.
	 *
	 * @param otherCharges the new other charges
	 */
	public void setOtherCharges(String otherCharges) {
		this.otherCharges = otherCharges;
	}

	/**
	 * Gets the pure quota value.
	 *
	 * @return the pure quota value
	 */
	public String getPureQuotaValue() {
		return pureQuotaValue;
	}

	/**
	 * Sets the pure quota value.
	 *
	 * @param pureQuotaValue the new pure quota value
	 */
	public void setPureQuotaValue(String pureQuotaValue) {
		this.pureQuotaValue = pureQuotaValue;
	}

	/**
	 * Gets the cft.
	 *
	 * @return the cft
	 */
	public String getCft() {
		return cft;
	}

	/**
	 * Sets the cft.
	 *
	 * @param cft the new cft
	 */
	public void setCft(String cft) {
		this.cft = cft;
	}

	/**
	 * Gets the rate type.
	 *
	 * @return the rate type
	 */
	public String getRateType() {
		return rateType;
	}

	/**
	 * Sets the rate type.
	 *
	 * @param rateType the new rate type
	 */
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	/**
	 * Gets the tna.
	 *
	 * @return the tna
	 */
	public String getTna() {
		return tna;
	}

	/**
	 * Sets the tna.
	 *
	 * @param tna the new tna
	 */
	public void setTna(String tna) {
		this.tna = tna;
	}

	/**
	 * Gets the tea.
	 *
	 * @return the tea
	 */
	public String getTea() {
		return tea;
	}

	/**
	 * Sets the tea.
	 *
	 * @param tea the new tea
	 */
	public void setTea(String tea) {
		this.tea = tea;
	}

	/**
	 * Gets the credit card description.
	 *
	 * @return the credit card description
	 */
	public String getCreditCardDescription() {
		return creditCardDescription;
	}

	/**
	 * Sets the credit card description.
	 *
	 * @param creditCardDescription the new credit card description
	 */
	public void setCreditCardDescription(String creditCardDescription) {
		this.creditCardDescription = creditCardDescription;
	}

	public String getRefinancingType() {
		return refinancingType;
	}

	public void setRefinancingType(String refinancingType) {
		this.refinancingType = refinancingType;
	}
}

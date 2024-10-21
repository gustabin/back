/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.nuevopago.entities.Pago;

/**
 * The Class RsaAnalyzePaymentRequestData.
 */
public class RsaAnalyzePaymentRequestData extends RsaRequestData {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5108780060654389817L;

	/** The pago. */
	private Pago pago;

	/**
	 * Instantiates a new rsa analyze payment request data.
	 */
	public RsaAnalyzePaymentRequestData() {
		super();
	}

	/**
	 * Gets the pago.
	 *
	 * @return the pago
	 */
	public Pago getPago() {
		return pago;
	}

	/**
	 * Sets the pago.
	 *
	 * @param pago
	 *            the new pago
	 */
	public void setPago(Pago pago) {
		this.pago = pago;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.entities.Entity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o.getClass().equals(this.getClass()))) {
			return false;
		}
		RsaAnalyzePaymentRequestData other = (RsaAnalyzePaymentRequestData) o;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getOperacion(), other.getOperacion());
		builder.append(getPago(), other.getPago());
		return builder.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.entities.Entity#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getOperacion());
		builder.append(getPago());
		return builder.hashCode();
	}

}

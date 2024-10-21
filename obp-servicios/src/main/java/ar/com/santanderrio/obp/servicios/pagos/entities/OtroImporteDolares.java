/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class OtroImporteDolares.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OtroImporteDolares {

	/** The monto. */
	private String monto;

	/** The on change. */
	private Boolean onChange;

	/** The num. */
	private String num;

	/** The text. */
	private String text;

	/**
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * Sets the monto.
	 *
	 * @param monto
	 *            the new monto
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * Gets the on change.
	 *
	 * @return the on change
	 */
	public Boolean getOnChange() {
		return onChange;
	}

	/**
	 * Sets the on change.
	 *
	 * @param onChange
	 *            the new on change
	 */
	public void setOnChange(Boolean onChange) {
		this.onChange = onChange;
	}

	/**
	 * Gets the num.
	 *
	 * @return the num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * Sets the num.
	 *
	 * @param num
	 *            the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(monto);
		hcb.append(num);
		hcb.append(onChange);
		hcb.append(text);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		OtroImporteDolares other = (OtroImporteDolares) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(monto, other.getMonto());
		eb.append(num, other.getNum());
		eb.append(onChange, other.getOnChange());
		eb.append(text, other.getText());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("monto", monto).append("onChange", onChange).append("num", num)
				.append("text", text).toString();
	}

}
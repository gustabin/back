/**
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;

/**
 * The Class RsaAnalyzeRequestData.
 *
 * @author Ignacio_Valek
 */
public class RsaAnalyzeRequestData extends RsaRequestData {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The rsa DTO. */
	private RsaDTO rsaDTO;
	
	/** The desafio aplicable. */
	private TipoDesafioEnum desafioAplicable;

	/**
	 * Instantiates a new rsa analyze request data.
	 */
	public RsaAnalyzeRequestData() {
		super();
	}

	/**
	 * Instantiates a new rsa analyze request data.
	 *
	 * @param dto
	 *            the dto
	 * @param desafioAplicable
	 *            the desafioAplicable
	 */
	public RsaAnalyzeRequestData(RsaDTO dto, TipoDesafioEnum desafioAplicable) {
		this.setRsaDTO(dto);
		this.setOperacion(dto.getTipoOperacion());
		this.setDesafioAplicable(desafioAplicable);
	}

	/**
	 * Gets the rsa DTO.
	 *
	 * @return the rsa DTO
	 */
	public RsaDTO getRsaDTO() {
		return rsaDTO;
	}

	/**
	 * Sets the rsa DTO.
	 *
	 * @param rsaDTO
	 *            the new rsa DTO
	 */
	public void setRsaDTO(RsaDTO rsaDTO) {
		this.rsaDTO = rsaDTO;
	}

	/**
	 * Gets the desafio aplicable.
	 *
	 * @return the desafio aplicable
	 */
	public TipoDesafioEnum getDesafioAplicable() {
		return desafioAplicable;
	}

	/**
	 * Sets the desafio aplicable.
	 *
	 * @param desafioAplicable the new desafio aplicable
	 */
	public void setDesafioAplicable(TipoDesafioEnum desafioAplicable) {
		this.desafioAplicable = desafioAplicable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(getRsaGenericRequestData()).append(getWsUserType())
				.append(getUserStatus()).append(getOperacion()).append(getDesafioAplicable()).toString();
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(getOperacion());
		hcb.append(getRsaGenericRequestData());
		hcb.append(getUserStatus());
		hcb.append(getWsUserType());
		hcb.append(getDesafioAplicable());
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj.getClass().equals(this.getClass()))) {
			return false;
		}
		RsaAnalyzeRequestData other = (RsaAnalyzeRequestData) obj;

		return new EqualsBuilder().append(getOperacion(), other.getOperacion())
				.append(getRsaGenericRequestData(), other.getRsaGenericRequestData())
				.append(getUserStatus(), other.getUserStatus()).append(getWsUserType(), other.getWsUserType())
				.append(getDesafioAplicable(), other.getDesafioAplicable())
				.isEquals();

	}

}

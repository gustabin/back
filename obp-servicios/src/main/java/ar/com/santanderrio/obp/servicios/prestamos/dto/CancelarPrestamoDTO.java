/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dto;

import ar.com.santanderrio.obp.servicios.prestamos.entity.CancelarPrestamoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoNormativoOutEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CancelarPrestamoDTO {

	private CancelarPrestamoOutEntity iatxResponse;

	private PrestamoNormativoOutEntity prestamoNormativo;

	public CancelarPrestamoOutEntity getIatxResponse() {
		return iatxResponse;
	}

	public void setIatxResponse(CancelarPrestamoOutEntity iatxResponse) {
		this.iatxResponse = iatxResponse;
	}

	public PrestamoNormativoOutEntity getPrestamoNormativo() {
		return prestamoNormativo;
	}

	public void setPrestamoNormativo(PrestamoNormativoOutEntity prestamoNormativo) {
		this.prestamoNormativo = prestamoNormativo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		CancelarPrestamoDTO that = (CancelarPrestamoDTO) o;

		return new EqualsBuilder()
				.append(iatxResponse, that.iatxResponse)
				.append(prestamoNormativo, that.prestamoNormativo)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(iatxResponse)
				.append(prestamoNormativo)
				.toHashCode();
	}
}

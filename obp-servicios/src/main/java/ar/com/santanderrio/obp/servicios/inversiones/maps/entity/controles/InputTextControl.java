/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author b039920
 *
 */
public class InputTextControl extends ControlMaps {

	@JsonProperty("Valor")
	protected String valor;

	@NotNull
	@JsonProperty("MinLength")
	protected Integer minLength;

	@NotNull
	@JsonProperty("MaxLength")
	protected Integer maxLength;

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * @return the minLength
	 */
	public Integer getMinLength() {
		return minLength;
	}

	/**
	 * @param minLength
	 *            the minLength to set
	 */
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	/**
	 * @return the maxLength
	 */
	public Integer getMaxLength() {
		return maxLength;
	}

	/**
	 * @param maxLength
	 *            the maxLength to set
	 */
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public String obtenerAlias() {
		if ("alias".equals(this.getNombre())) {
			return valor;
		}
		return null;
	}

	public String obtenerDescripcionDinamica() {
		if ("descripcion-dinamica".equals(this.getNombre())) {
			return valor;
		}
		return null;
	}

	@Override
	public String obtenerEstadoAdhesion() {
		if ("estado-adhesion".equals(this.getNombre())) {
			return valor;
		}
		return null;
	}
	
	@Override
	public String valorComprobante() {
		return this.getValor();
	}
}

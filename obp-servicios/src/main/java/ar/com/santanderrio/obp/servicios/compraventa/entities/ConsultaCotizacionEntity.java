/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class ConsultaCotizacionEntity.
 *
 * @author sabrina.cis
 */
public class ConsultaCotizacionEntity extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The ceros. Resultado del procesamiento en Host.
	 */
	private String ceros;

	/**
	 * The cotizacion salida. Cotización de salida solicitada.
	 */
	private String cotizacionSalida;

	/**
	 * The importe salida.Importe salida calculado.
	 */
	private String importeSalida;

	/**
	 * The spred aplicado. Spred aplicado si corresponde según indicado en
	 * T0309.
	 */
	private String spredAplicado;

	/** The codigo retorno. */
	private String codigoRetorno;

	/** The id sistema. */
	private String idSistema;

	/** The cant desc status resultado. */
	private String cantDescStatusResultado;

	/** The descripcion status resultado. */
	private String descripcionStatusResultado;

	/**
	 * Gets the ceros.
	 *
	 * @return the ceros
	 */
	public String getCeros() {
		return ceros;
	}

	/**
	 * Sets the ceros.
	 *
	 * @param ceros
	 *            the ceros to set
	 */
	public void setCeros(String ceros) {
		this.ceros = ceros;
	}

	/**
	 * Gets the cotizacion salida.
	 *
	 * @return the cotizacionSalida
	 */
	public String getCotizacionSalida() {
		return cotizacionSalida;
	}

	/**
	 * Sets the cotizacion salida.
	 *
	 * @param cotizacionSalida
	 *            the cotizacionSalida to set
	 */
	public void setCotizacionSalida(String cotizacionSalida) {
		this.cotizacionSalida = cotizacionSalida;
	}

	/**
	 * Gets the importe salida.
	 *
	 * @return the importeSalida
	 */
	public String getImporteSalida() {
		return importeSalida;
	}

	/**
	 * Sets the importe salida.
	 *
	 * @param importeSalida
	 *            the importeSalida to set
	 */
	public void setImporteSalida(String importeSalida) {
		this.importeSalida = importeSalida;
	}

	/**
	 * Gets the spred aplicado.
	 *
	 * @return the spredAplicado
	 */
	public String getSpredAplicado() {
		return spredAplicado;
	}

	/**
	 * Sets the spred aplicado.
	 *
	 * @param spredAplicado
	 *            the spredAplicado to set
	 */
	public void setSpredAplicado(String spredAplicado) {
		this.spredAplicado = spredAplicado;
	}

	/**
	 * Gets the codigo retorno.
	 *
	 * @return the codigoRetorno
	 */
	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	/**
	 * Sets the codigo retorno.
	 *
	 * @param codigoRetorno
	 *            the codigoRetorno to set
	 */
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	/**
	 * Gets the id sistema.
	 *
	 * @return the idSistema
	 */
	public String getIdSistema() {
		return idSistema;
	}

	/**
	 * Sets the id sistema.
	 *
	 * @param idSistema
	 *            the idSistema to set
	 */
	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	/**
	 * Gets the cant desc status resultado.
	 *
	 * @return the cantDescStatusResultado
	 */
	public String getCantDescStatusResultado() {
		return cantDescStatusResultado;
	}

	/**
	 * Sets the cant desc status resultado.
	 *
	 * @param cantDescStatusResultado
	 *            the cantDescStatusResultado to set
	 */
	public void setCantDescStatusResultado(String cantDescStatusResultado) {
		this.cantDescStatusResultado = cantDescStatusResultado;
	}

	/**
	 * Gets the descripcion status resultado.
	 *
	 * @return the descripcionStatusResultado
	 */
	public String getDescripcionStatusResultado() {
		return descripcionStatusResultado;
	}

	/**
	 * Sets the descripcion status resultado.
	 *
	 * @param descripcionStatusResultado
	 *            the descripcionStatusResultado to set
	 */
	public void setDescripcionStatusResultado(String descripcionStatusResultado) {
		this.descripcionStatusResultado = descripcionStatusResultado;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cantDescStatusResultado);
		hcb.append(ceros == null);
		hcb.append(codigoRetorno);
		hcb.append(cotizacionSalida);
		hcb.append(descripcionStatusResultado);
		hcb.append(idSistema);
		hcb.append(importeSalida);
		hcb.append(spredAplicado);
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
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ConsultaCotizacionEntity other = (ConsultaCotizacionEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cantDescStatusResultado, other.cantDescStatusResultado);
		eb.append(ceros, other.ceros);
		eb.append(codigoRetorno, other.codigoRetorno);
		eb.append(cotizacionSalida, other.cotizacionSalida);
		eb.append(descripcionStatusResultado, other.descripcionStatusResultado);
		eb.append(idSistema, other.idSistema);
		eb.append(importeSalida, other.importeSalida);
		eb.append(spredAplicado, other.spredAplicado);
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
		return new ToStringBuilder(this).append("ceros", ceros).append("cotizacionSalida", cotizacionSalida)
				.append("importeSalida", importeSalida).append("spredAplicado", spredAplicado)
				.append("codigoRetorno", codigoRetorno).append("idSistema", idSistema)
				.append("cantDescStatusResultado", cantDescStatusResultado)
				.append("descripcionStatusResultado", descripcionStatusResultado).toString();
	}
}
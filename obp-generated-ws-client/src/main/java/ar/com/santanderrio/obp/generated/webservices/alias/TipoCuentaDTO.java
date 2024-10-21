/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.alias;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class TipoCuentaDTO.
 *
 * @author leonardo.medina
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class TipoCuentaDTO {

	/** The codigo. */
	private String codigo;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new tipo cuenta DTO.
	 */
	public TipoCuentaDTO() {
		super();
	}

	/**
	 * Instantiates a new tipo cuenta DTO.
	 *
	 * @param codigo
	 *            the codigo
	 */
	public TipoCuentaDTO(String codigo) {
		super();
		this.codigo = codigo;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigo);
		hcb.append(descripcion);
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoCuentaDTO other = (TipoCuentaDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(codigo, other.getCodigo());
		eb.append(descripcion, other.getDescripcion());
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
		return new ToStringBuilder(this).append("codigo", codigo).append("descripcion", descripcion).toString();
	}

}

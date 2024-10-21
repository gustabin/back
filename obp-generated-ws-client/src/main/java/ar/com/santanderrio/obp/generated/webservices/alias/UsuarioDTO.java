
package ar.com.santanderrio.obp.generated.webservices.alias;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <p>
 * Java class for usuarioDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="usuarioDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nroDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usuarioDTO", propOrder = { "nroDocumento", "tipoDocumento" })
public class UsuarioDTO {

	/** The nro documento. */
	protected String nroDocumento;

	/** The tipo documento. */
	protected String tipoDocumento;

	/**
	 * Instantiates a new usuario DTO.
	 */
	public UsuarioDTO() {
		super();
	}

	/**
	 * Instantiates a new usuario DTO.
	 *
	 * @param nroDocumento
	 *            the nro documento
	 */
	public UsuarioDTO(String nroDocumento) {
		super();
		this.nroDocumento = nroDocumento;
		this.tipoDocumento = "1";
	}

	/**
	 * Gets the value of the nroDocumento property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}

	/**
	 * Sets the value of the nroDocumento property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNroDocumento(String value) {
		this.nroDocumento = value;
	}

	/**
	 * Gets the value of the tipoDocumento property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the value of the tipoDocumento property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTipoDocumento(String value) {
		this.tipoDocumento = value;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(nroDocumento);
		hcb.append(tipoDocumento);
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
		UsuarioDTO other = (UsuarioDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(nroDocumento, other.getNroDocumento());
		eb.append(tipoDocumento, other.getTipoDocumento());
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
		return new ToStringBuilder(this).append("nroDocumento", nroDocumento).append("tipoDocumento", tipoDocumento)
				.toString();
	}

}


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
 * Java class for terminalDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="terminalDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="canal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datosTerminal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="direccionIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "terminalDTO", propOrder = { "canal", "datosTerminal", "direccionIp", "terminal" })
public class TerminalDTO {

	/** The canal. */
	protected String canal;

	/** The datos terminal. */
	protected String datosTerminal;

	/** The direccion ip. */
	protected String direccionIp;

	/** The terminal. */
	protected String terminal;

	/**
	 * Instantiates a new terminal DTO.
	 */
	public TerminalDTO() {
		super();
	}

	/**
	 * Instantiates a new terminal DTO.
	 *
	 * @param datosTerminal
	 *            the datos terminal
	 * @param ip
	 *            the ip
	 */
	public TerminalDTO(String datosTerminal, String ip) {
		super();
		this.canal = "E";
		this.datosTerminal = datosTerminal;
		this.direccionIp = ip;
	}

	/**
	 * Gets the value of the canal property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the value of the canal property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCanal(String value) {
		this.canal = value;
	}

	/**
	 * Gets the value of the datosTerminal property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDatosTerminal() {
		return datosTerminal;
	}

	/**
	 * Sets the value of the datosTerminal property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDatosTerminal(String value) {
		this.datosTerminal = value;
	}

	/**
	 * Gets the value of the direccionIp property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDireccionIp() {
		return direccionIp;
	}

	/**
	 * Sets the value of the direccionIp property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDireccionIp(String value) {
		this.direccionIp = value;
	}

	/**
	 * Gets the value of the terminal property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTerminal() {
		return terminal;
	}

	/**
	 * Sets the value of the terminal property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTerminal(String value) {
		this.terminal = value;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(canal);
		hcb.append(datosTerminal);
		hcb.append(direccionIp);
		hcb.append(terminal);
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
		TerminalDTO other = (TerminalDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(canal, other.getCanal());
		eb.append(datosTerminal, other.getDatosTerminal());
		eb.append(direccionIp, other.getDireccionIp());
		eb.append(terminal, other.getTerminal());
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
		return new ToStringBuilder(this).append("canal", canal).append("datosTerminal", datosTerminal)
				.append("direccionIp", direccionIp).append("terminal", terminal).toString();
	}
}

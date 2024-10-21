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
 * The Class ConsultarDatosTitularidad.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ConsultarDatosTitularidad extends Request {

	/** The usuario. */
	private UsuarioDTO usuario;

	/** The terminal. */
	private TerminalDTO terminal;

	/** The cuenta origen. */
	private CuentaDTO cuentaOrigen;

	/** The cbu destino. */
	private String cbuDestino;

	/**
	 * Instantiates a new consultar datos titularidad.
	 */
	public ConsultarDatosTitularidad() {
		super();
	}

	/**
	 * Instantiates a new consultar datos titularidad.
	 *
	 * @param usuarioDTO
	 *            the usuario DTO
	 * @param terminalDTO
	 *            the terminal DTO
	 * @param cuentaDTO
	 *            the cuenta DTO
	 * @param cbuDestino
	 *            the cbu Destino
	 */
	public ConsultarDatosTitularidad(UsuarioDTO usuarioDTO, TerminalDTO terminalDTO, CuentaDTO cuentaDTO,
			String cbuDestino) {
		super();
		this.usuario = usuarioDTO;
		this.terminal = terminalDTO;
		this.cuentaOrigen = cuentaDTO;
		this.cbuDestino = cbuDestino;
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public UsuarioDTO getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the terminal.
	 *
	 * @return the terminal
	 */
	public TerminalDTO getTerminal() {
		return terminal;
	}

	/**
	 * Sets the terminal.
	 *
	 * @param terminal
	 *            the terminal to set
	 */
	public void setTerminal(TerminalDTO terminal) {
		this.terminal = terminal;
	}

	/**
	 * Gets the cbuDestino.
	 *
	 * @return the cbuDestino
	 */
	public String getCbuDestino() {
		return cbuDestino;
	}

	/**
	 * Sets the cbuDestino.
	 *
	 * @param cbuDestino
	 *            the cbuDestino to set
	 */
	public void setCbuDestino(String cbuDestino) {
		this.cbuDestino = cbuDestino;
	}

	/**
	 * Gets the cuenta origen.
	 *
	 * @return the cuentaOrigen
	 */
	public CuentaDTO getCuentaOrigen() {
		return cuentaOrigen;
	}

	/**
	 * Sets the cuenta origen.
	 *
	 * @param cuentaOrigen
	 *            the cuentaOrigen to set
	 */
	public void setCuentaOrigen(CuentaDTO cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cbuDestino);
		hcb.append(cuentaOrigen);
		hcb.append(terminal);
		hcb.append(usuario);
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
		ConsultarDatosTitularidad other = (ConsultarDatosTitularidad) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cbuDestino, other.getCbuDestino());
		eb.append(cuentaOrigen, other.getCuentaOrigen());
		eb.append(terminal, other.getTerminal());
		eb.append(usuario, other.getUsuario());
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
		return new ToStringBuilder(this).append("usuario", usuario).append("terminal", terminal)
				.append("cuentaOrigen", cuentaOrigen).append("cbuDestino", cbuDestino).toString();
	}
}

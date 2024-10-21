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
 * The Class ConsultarDatosTitularidadExtendido.
 *
 * @author leonardo.medina
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ConsultarDatosTitularidadExtendido extends Request {

	/** The usuario. */
	private UsuarioDTO usuario;

	/** The terminal. */
	private TerminalDTO terminal;

	/** The cuenta origen. */
	private CuentaDTO cuentaOrigen;

	/** The alias. */
	private String alias;

	/**
	 * Instantiates a new consultar datos titularidad extendido.
	 */
	public ConsultarDatosTitularidadExtendido() {
		super();
	}

	/**
	 * Instantiates a new consultar datos titularidad extendido.
	 *
	 * @param usuarioDTO
	 *            the usuario DTO
	 * @param terminalDTO
	 *            the terminal DTO
	 * @param cuentaDTO
	 *            the cuenta DTO
	 * @param alias
	 *            the alias
	 */
	public ConsultarDatosTitularidadExtendido(UsuarioDTO usuarioDTO, TerminalDTO terminalDTO, CuentaDTO cuentaDTO,
			String alias) {
		super();
		this.usuario = usuarioDTO;
		this.terminal = terminalDTO;
		this.cuentaOrigen = cuentaDTO;
		this.alias = alias;
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
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
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
		hcb.append(alias);
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
		ConsultarDatosTitularidadExtendido other = (ConsultarDatosTitularidadExtendido) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(alias, other.getAlias());
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
				.append("cuentaOrigen", cuentaOrigen).append("alias", alias).toString();
	}
}

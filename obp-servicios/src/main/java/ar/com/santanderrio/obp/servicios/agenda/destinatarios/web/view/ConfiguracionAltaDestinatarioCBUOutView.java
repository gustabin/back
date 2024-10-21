/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioCBUDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;

/**
 * The Class ConfiguracionAltaDestinatarioCBUOutView.
 */
@XmlRootElement(name = "configuracionAltaDestinatario", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class ConfiguracionAltaDestinatarioCBUOutView extends ConfiguracionAltaDestinatarioOutView {

	/** The banco. */
	private String banco;

	/** The moneda. */
	private String moneda;

	/** The is rio. */
	private Boolean isRio;

	/** The sucursal cuenta. */
	private String sucursalCuenta;

	/** The alias. */
	private String alias;

	/** The numero cuenta. */
	private String nroCuenta;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The cbu. */
	private String cbu;

	/** The digito identificador. */
	private String digitoIdentificador;

	/** The email. */
	private String email;

	/** The referencia rio. */
	private String referenciaRio;

	/** The direccion correo rio. */
	private String direccionCorreoRio;

	/** The cod tipo cuenta. */
	private String codTipoCuenta;

	/** The destinatario agenda. */
	private DestinatarioView destinatarioAgenda;

	/**
	 * Instantiates a new configuracion alta destinatario CBU out view.
	 *
	 * @param dto
	 *            the dto
	 */
	public ConfiguracionAltaDestinatarioCBUOutView(ConfiguracionAltaDestinatarioDTO dto) {
		super(dto);

	}

	/**
	 * Instantiates a new configuracion alta destinatario CBU out view.
	 */
	public ConfiguracionAltaDestinatarioCBUOutView() {
		super();
	}

	/**
	 * Instantiates a new configuracion alta destinatario CBU out view.
	 *
	 * @param respuesta
	 *            the respuesta
	 */
	public ConfiguracionAltaDestinatarioCBUOutView(ConfiguracionAltaDestinatarioCBUDTO respuesta) {
		super();
		isRio = respuesta.getIsRio();
		if (isRio) {
			sucursalCuenta = respuesta.getSucursal();
			nroCuenta = respuesta.getNumeroCuenta();
			digitoIdentificador = respuesta.getDigitoVerificador();
			banco = BancoEnum.SANTANDER_RIO.getDescripcion();
		} else {
			setTitular(StringUtils.substring(respuesta.getTitular(), 0, 30));
			banco = respuesta.getBanco();
			setNumeroCuil(respuesta.getNumeroCuil());
		}

	}

	/**
	 * Gets the banco.
	 *
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * Sets the banco.
	 *
	 * @param banco
	 *            the new banco
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Checks if is rio.
	 *
	 * @return true, if is rio
	 */
	public Boolean getIsRio() {
		return isRio;
	}

	/**
	 * Sets the checks if is rio.
	 *
	 * @param isAlta
	 *            the new checks if is rio
	 */
	public void setIsRio(Boolean isAlta) {
		this.isRio = isAlta;
	}

	/**
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param nroCuenta
	 *            the new numero cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the digito identificador.
	 *
	 * @return the digito identificador
	 */
	public String getDigitoIdentificador() {
		return digitoIdentificador;
	}

	/**
	 * Sets the digito identificador.
	 *
	 * @param digitoIdentificador
	 *            the new digito identificador
	 */
	public void setDigitoIdentificador(String digitoIdentificador) {
		this.digitoIdentificador = digitoIdentificador;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the referencia rio.
	 *
	 * @return the referencia rio
	 */
	public String getReferenciaRio() {
		return referenciaRio;
	}

	/**
	 * Sets the referencia rio.
	 *
	 * @param referenciaRio
	 *            the new referencia rio
	 */
	public void setReferenciaRio(String referenciaRio) {
		this.referenciaRio = referenciaRio;
	}

	/**
	 * Gets the direccion correo rio.
	 *
	 * @return the direccion correo rio
	 */
	public String getDireccionCorreoRio() {
		return direccionCorreoRio;
	}

	/**
	 * Sets the direccion correo rio.
	 *
	 * @param direccionCorreoRio
	 *            the new direccion correo rio
	 */
	public void setDireccionCorreoRio(String direccionCorreoRio) {
		this.direccionCorreoRio = direccionCorreoRio;
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
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the cbu to set
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the cod tipo cuenta.
	 *
	 * @return the cod tipo cuenta
	 */
	public String getCodTipoCuenta() {
		return codTipoCuenta;
	}

	/**
	 * Sets the cod tipo cuenta.
	 *
	 * @param codTipoCuenta
	 *            the new cod tipo cuenta
	 */
	public void setCodTipoCuenta(String codTipoCuenta) {
		this.codTipoCuenta = codTipoCuenta;
	}

	/**
	 * Gets the destinatario agenda.
	 *
	 * @return the destinatario agenda
	 */
	public DestinatarioView getDestinatarioAgenda() {
		return destinatarioAgenda;
	}

	/**
	 * Sets the destinatario agenda.
	 *
	 * @param destinatarioAgenda
	 *            the new destinatario agenda
	 */
	public void setDestinatarioAgenda(DestinatarioView destinatarioAgenda) {
		this.destinatarioAgenda = destinatarioAgenda;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(banco);
		hcb.append(cbu);
		hcb.append(digitoIdentificador);
		hcb.append(direccionCorreoRio);
		hcb.append(email);
		hcb.append(isRio);
		hcb.append(moneda);
		hcb.append(nroCuenta);
		hcb.append(referenciaRio);
		hcb.append(sucursalCuenta);
		hcb.append(tipoCuenta);
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
		ConfiguracionAltaDestinatarioCBUOutView other = (ConfiguracionAltaDestinatarioCBUOutView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(banco, other.getBanco());
		eb.append(cbu, other.getCbu());
		eb.append(digitoIdentificador, other.getDigitoIdentificador());
		eb.append(direccionCorreoRio, other.getDireccionCorreoRio());
		eb.append(email, other.getEmail());
		eb.append(isRio, other.getIsRio());
		eb.append(moneda, other.getMoneda());
		eb.append(nroCuenta, other.getNroCuenta());
		eb.append(referenciaRio, other.getReferenciaRio());
		eb.append(sucursalCuenta, other.getSucursalCuenta());
		eb.append(tipoCuenta, other.getTipoCuenta());
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
		return new ToStringBuilder(this).append("banco", banco).append("moneda", moneda).append("isRio", isRio)
				.append("sucursalCuenta", sucursalCuenta).append("alias", alias).append("numeroCuenta", nroCuenta)
				.append("tipoCuenta", tipoCuenta).append("cbu", cbu).append("digitoIdentificador", digitoIdentificador)
				.append("email", email).append("referenciaRio", referenciaRio)
				.append("direccionCorreoRio", direccionCorreoRio).toString();
	}

}

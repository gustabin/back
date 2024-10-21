/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendidoResponse;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.AltaDestinatarioAliasBOImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaOutCBUEntity;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class ConfiguracionAltaDestinatarioCBUDTO.
 */
public class ConfiguracionAltaDestinatarioCBUDTO {

	/** The banco. */
	private String banco;

	/** The is rio. */
	private Boolean isRio;

	/** The titular. */
	private String titular;

	/** The sucursal. */
	private String sucursal;

	/** The cuenta. */
	private String numeroCuenta;

	/** The digito verificador. */
	private String digitoVerificador;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The cbu. */
	private String cbu;

	/** The numero cuil. */
	private String numeroCuil;

	/** The cuit O cuil. */
	private String cuitOCuil;

	/** The cod tipo cuenta. */
	private String codTipoCuenta;

	/**
	 * Instantiates a new configuracion alta destinatario CBUDTO.
	 *
	 * @param entity
	 *            the entity
	 * @param isRio
	 *            the is rio
	 * @param cuenta
	 *            the cuenta
	 */
	public ConfiguracionAltaDestinatarioCBUDTO(ValidacionCuentaOutCBUEntity entity, boolean isRio, String cuenta) {
		this.isRio = isRio;
		this.numeroCuil = entity.getCuit();
		if (!isRio) {
			this.banco = entity.getBandes();
			this.titular = entity.getTitular();
		} else {
			IdentificacionCuenta cuentaFormateada = new IdentificacionCuenta(cuenta);
			this.sucursal = cuentaFormateada.getNroSucursal();
			this.numeroCuenta = cuentaFormateada.getNroCuentaProducto().substring(0,
					cuentaFormateada.getNroCuentaProducto().length() - 1);
			this.digitoVerificador = cuentaFormateada.getNroCuentaProducto()
					.substring(cuentaFormateada.getNroCuentaProducto().length() - 1);
			// sucursal, cuenta y digito verificador
		}
	}

	/**
	 * Instantiates a new configuracion alta destinatario CBUDTO.
	 */
	public ConfiguracionAltaDestinatarioCBUDTO() {
		super();
	}

	/**
	 * Instantiates a new configuracion alta destinatario CBUDTO.
	 *
	 * @param responseWS
	 *            the response WS
	 * @param esRio
	 *            the es rio
	 * @throws BusinessException
	 *             the business exception
	 */
	public ConfiguracionAltaDestinatarioCBUDTO(ConsultarDatosTitularidadExtendidoResponse responseWS, Boolean esRio)
			throws BusinessException {
		this.titular = responseWS.getTitularidadExtendido().getNombreTitular();
		this.isRio = esRio;
		if (esRio && !ISBANStringUtils.validarCBUCtaRecaudadora(responseWS.getTitularidadExtendido().getCtaDestino().getNumeroCBU())) {
			TipoCuenta tipoCuentaEnum = AltaDestinatarioAliasBOImpl.obtenerTipoCuenta(responseWS);
			this.numeroCuenta = new IdentificacionCuenta(
					StringUtils.substring(responseWS.getTitularidadExtendido().getCtaDestino().getNumeroCBU(), 4, 7),
					responseWS.getTitularidadExtendido().getCtaDestino().getNumero()).toString();

			this.tipoCuenta = tipoCuentaEnum.getDescripcionConMoneda();
			this.codTipoCuenta = tipoCuentaEnum.getCodigo().toString();
			this.banco = BancoEnum.SANTANDER_RIO.getDescripcion();
		} else {
			if (!ISBANStringUtils.validarCVU(responseWS.getTitularidadExtendido().getCtaDestino().getNumeroCBU())) {
				this.banco = responseWS.getTitularidadExtendido().getNombreBanco().trim();
	        } else {
	        	this.banco = "-";
	        }
		}
		this.cbu = responseWS.getTitularidadExtendido().getCtaDestino().getNumeroCBU();
		this.numeroCuil = ISBANStringUtils
				.agregarGuionesANumeroCuitCuil(responseWS.getTitularidadExtendido().getCuits().get(0));
	}

	/**
	 * Gets the cuit O cuil.
	 *
	 * @return the cuitOCuil
	 */
	public String getCuitOCuil() {
		return cuitOCuil;
	}

	/**
	 * Sets the cuit O cuil.
	 *
	 * @param cuitOCuil
	 *            the cuitOCuil to set
	 */
	public void setCuitOCuil(String cuitOCuil) {
		this.cuitOCuil = cuitOCuil;
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
	 * Checks if is rio.
	 *
	 * @return true, if is rio
	 */
	public Boolean getIsRio() {
		return isRio;
	}

	/**
	 * Sets the rio.
	 *
	 * @param isRio
	 *            the new rio
	 */
	public void setIsRio(Boolean isRio) {
		this.isRio = isRio;
	}

	/**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the new titular
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the numero cuil.
	 *
	 * @return the numero cuil
	 */
	public String getNumeroCuil() {
		return numeroCuil;
	}

	/**
	 * Sets the numero cuil.
	 *
	 * @param numeroCuil
	 *            the new numero cuil
	 */
	public void setNumeroCuil(String numeroCuil) {
		this.numeroCuil = numeroCuil;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param numeroCuenta
	 *            the new cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the digito verificador.
	 *
	 * @return the digito verificador
	 */
	public String getDigitoVerificador() {
		return digitoVerificador;
	}

	/**
	 * Sets the digito verificador.
	 *
	 * @param digitoVerificador
	 *            the new digito verificador
	 */
	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
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
	 * @return the codTipoCuenta
	 */
	public final String getCodTipoCuenta() {
		return codTipoCuenta;
	}

	/**
	 * Sets the cod tipo cuenta.
	 *
	 * @param codTipoCuenta
	 *            the codTipoCuenta to set
	 */
	public final void setCodTipoCuenta(String codTipoCuenta) {
		this.codTipoCuenta = codTipoCuenta;
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
		hcb.append(codTipoCuenta);
		hcb.append(cuitOCuil);
		hcb.append(digitoVerificador);
		hcb.append(isRio);
		hcb.append(numeroCuenta);
		hcb.append(numeroCuil);
		hcb.append(sucursal);
		hcb.append(tipoCuenta);
		hcb.append(titular);
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
		ConfiguracionAltaDestinatarioCBUDTO other = (ConfiguracionAltaDestinatarioCBUDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(banco, other.getBanco());
		eb.append(cbu, other.getCbu());
		eb.append(codTipoCuenta, other.getCodTipoCuenta());
		eb.append(cuitOCuil, other.getCuitOCuil());
		eb.append(digitoVerificador, other.getDigitoVerificador());
		eb.append(isRio, other.getIsRio());
		eb.append(numeroCuenta, other.getNumeroCuenta());
		eb.append(numeroCuil, other.getNumeroCuil());
		eb.append(sucursal, other.getSucursal());
		eb.append(tipoCuenta, other.getTipoCuenta());
		eb.append(titular, other.getTitular());
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
		return new ToStringBuilder(this).append("banco", banco).append("isRio", isRio).append("titular", titular)
				.append("sucursal", sucursal).append("numeroCuenta", numeroCuenta)
				.append("digitoVerificador", digitoVerificador).append("tipoCuenta", tipoCuenta).append("cbu", cbu)
				.append("numeroCuil", numeroCuil).append("cuitOCuil=" + cuitOCuil)
				.append("codTipoCuenta", codTipoCuenta).toString();
	}

}

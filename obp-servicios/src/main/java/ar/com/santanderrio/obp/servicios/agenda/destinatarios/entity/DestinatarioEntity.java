/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.beanio.annotation.Field;

/**
 * The Class DestinatarioEntity.
 */
public class DestinatarioEntity {

	/** The Tipo fondo (A02). */
	@Field
	@Pattern(regexp = "^00|01|02|03|04|09|10|  $")
	private String tipoCuentaDestinatario;

	/** The sucursal cuenta destinatario (A04). */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{4}|[ ]{4}$")
	private String sucursalCuentaDestinatario;

	/** The numero cuenta destinatario (A12). */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{12}|[ ]{12}$")
	private String numeroCuentaDestinatario;

	/** The cbu destinatario (A22). */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{22}|[ ]{22}$")
	private String cbuDestinatario;

	/** The descripcion cuenta destinatario (A30). */
	@Field
	@Size(min = 30, max = 30)
	private String descripcionCuentaDestinatario;

	/** The banco destinatario (A50). */
	@Field
	@Size(min = 50, max = 50)
	private String bancoDestinatario;

	/** The caracteristicas cuenta destinatario (A10). */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{10}|[ ]{10}$")
	private String caracteristicasCuentaDestinatario;

	/** The documento destinatario (A11). */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{11}|[ ]{11}$")
	private String documentoDestinatario;

	/** The tipo documento destinatario (A02). */
	@Field
	@Pattern(regexp = "[a-zA-Z0-9]{1}[ ]{1}|[ ]{2}$")
	private String tipoDocumentoDestinatario;

	/** The titular (A64). */
	@Field
	@Size(min = 64, max = 64)
	private String titular;

	/** The direccion correo (A100). */
	@Field
	@Size(min = 100, max = 100)
	@Pattern(regexp = "(.+)@(.+ )|[ ]{100}")
	private String direccionCorreo;

	/** The timestamp activacion. */
	@Field
	private String timestampActivacion;

	/** The canal operacion. */
	@Field
	private String canalOperacion;

	/** The sub canal operacion. */
	@Field
	private String subCanalOperacion;

	/** The telefono destinatario (A16). */
	@Field
	@Pattern(regexp = "^[a-zA-Z0-9]{16}|[ ]{16}$")
	private String telefonoDestinatario;

	/** The timestamp alta. */
	@Field
	private String timestampAlta;

	/** The tipo agenda ocurrencia (A03). * */
	@Field
	@Pattern(regexp = "RIO|OB |EXT|TOD")
	private String tipoAgendaOcurrencia;

	/** The alias. */
	@Field
	private String alias;

	/** The id. */
	private String id;
	
	/** The is Transferencia BP. */
	private Boolean isTransferenciaBP = Boolean.FALSE;

	/**
	 * Gets the tipo cuenta destinatario.
	 *
	 * @return the tipoCuentaDestinatario
	 */
	public String getTipoCuentaDestinatario() {
		return tipoCuentaDestinatario;
	}

	/**
	 * Sets the tipo cuenta destinatario.
	 *
	 * @param tipoCuentaDestinatario
	 *            the tipoCuentaDestinatario to set
	 */
	public void setTipoCuentaDestinatario(String tipoCuentaDestinatario) {
		this.tipoCuentaDestinatario = tipoCuentaDestinatario;
	}

	/**
	 * Gets the sucursal cuenta destinatario.
	 *
	 * @return the sucursalCuentaDestinatario
	 */
	public String getSucursalCuentaDestinatario() {
		return sucursalCuentaDestinatario;
	}

	/**
	 * Sets the sucursal cuenta destinatario.
	 *
	 * @param sucursalCuentaDestinatario
	 *            the sucursalCuentaDestinatario to set
	 */
	public void setSucursalCuentaDestinatario(String sucursalCuentaDestinatario) {
		this.sucursalCuentaDestinatario = sucursalCuentaDestinatario;
	}

	/**
	 * Gets the numero cuenta destinatario.
	 *
	 * @return the numerroCuentaDestinatario
	 */
	public String getNumeroCuentaDestinatario() {
		return numeroCuentaDestinatario;
	}

	/**
	 * Sets the numero cuenta destinatario.
	 *
	 * @param numeroCuentaDestinatario
	 *            the numerroCuentaDestinatario to set
	 */
	public void setNumeroCuentaDestinatario(String numeroCuentaDestinatario) {
		this.numeroCuentaDestinatario = numeroCuentaDestinatario;
	}

	/**
	 * Gets the cbu destinatario.
	 *
	 * @return the cbuDestinatario
	 */
	public String getCbuDestinatario() {
		return cbuDestinatario;
	}

	/**
	 * Sets the cbu destinatario.
	 *
	 * @param cbuDestinatario
	 *            the cbuDestinatario to set
	 */
	public void setCbuDestinatario(String cbuDestinatario) {
		this.cbuDestinatario = cbuDestinatario;
	}

	/**
	 * Gets the descripcion cuenta destinatario.
	 *
	 * @return the descripcionCuentaDestinatario
	 */
	public String getDescripcionCuentaDestinatario() {
		return descripcionCuentaDestinatario;
	}

	/**
	 * Sets the descripcion cuenta destinatario.
	 *
	 * @param descripcionCuentaDestinatario
	 *            the descripcionCuentaDestinatario to set
	 */
	public void setDescripcionCuentaDestinatario(String descripcionCuentaDestinatario) {
		this.descripcionCuentaDestinatario = descripcionCuentaDestinatario;
	}

	/**
	 * Gets the banco destinatario.
	 *
	 * @return the bancoDestinatario
	 */
	public String getBancoDestinatario() {
		return bancoDestinatario;
	}

	/**
	 * Sets the banco destinatario.
	 *
	 * @param bancoDestinatario
	 *            the bancoDestinatario to set
	 */
	public void setBancoDestinatario(String bancoDestinatario) {
		this.bancoDestinatario = bancoDestinatario;
	}

	/**
	 * Gets the caracteristicas cuenta destinatario.
	 *
	 * @return the caracteristicasCuentaDestinatario
	 */
	public String getCaracteristicasCuentaDestinatario() {
		return caracteristicasCuentaDestinatario;
	}

	/**
	 * Sets the caracteristicas cuenta destinatario.
	 *
	 * @param caracteristicasCuentaDestinatario
	 *            the caracteristicasCuentaDestinatario to set
	 */
	public void setCaracteristicasCuentaDestinatario(String caracteristicasCuentaDestinatario) {
		this.caracteristicasCuentaDestinatario = caracteristicasCuentaDestinatario;
	}

	/**
	 * Gets the documento destinatario.
	 *
	 * @return the documentoDestinatario
	 */
	public String getDocumentoDestinatario() {
		return documentoDestinatario;
	}

	/**
	 * Sets the documento destinatario.
	 *
	 * @param documentoDestinatario
	 *            the documentoDestinatario to set
	 */
	public void setDocumentoDestinatario(String documentoDestinatario) {
		if (StringUtils.contains(documentoDestinatario, '-')) {
			this.documentoDestinatario = StringUtils.remove(documentoDestinatario, "-");
		} else {
			this.documentoDestinatario = documentoDestinatario;
		}
	}

	/**
	 * Gets the tipo documento destinatario.
	 *
	 * @return the tipoDocumentoDestinatario
	 */
	public String getTipoDocumentoDestinatario() {
		return tipoDocumentoDestinatario;
	}

	/**
	 * Sets the tipo documento destinatario.
	 *
	 * @param tipoDocumentoDestinatario
	 *            the tipoDocumentoDestinatario to set
	 */
	public void setTipoDocumentoDestinatario(String tipoDocumentoDestinatario) {
		this.tipoDocumentoDestinatario = tipoDocumentoDestinatario;
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
	 *            the titular to set
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the direccion correo.
	 *
	 * @return the direccionCorreo
	 */
	public String getDireccionCorreo() {
		return direccionCorreo;
	}

	/**
	 * Sets the direccion correo.
	 *
	 * @param direccionCorreo
	 *            the direccionCorreo to set
	 */
	public void setDireccionCorreo(String direccionCorreo) {
		this.direccionCorreo = direccionCorreo;
	}

	/**
	 * Gets the timestamp activacion.
	 *
	 * @return the timestampActivacion
	 */
	public String getTimestampActivacion() {
		return timestampActivacion;
	}

	/**
	 * Sets the timestamp activacion.
	 *
	 * @param timestampActivacion
	 *            the timestampActivacion to set
	 */
	public void setTimestampActivacion(String timestampActivacion) {
		this.timestampActivacion = timestampActivacion;
	}

	/**
	 * Gets the canal operacion.
	 *
	 * @return the canalOperacion
	 */
	public String getCanalOperacion() {
		return canalOperacion;
	}

	/**
	 * Sets the canal operacion.
	 *
	 * @param canalOperacion
	 *            the canalOperacion to set
	 */
	public void setCanalOperacion(String canalOperacion) {
		this.canalOperacion = canalOperacion;
	}

	/**
	 * Gets the sub canal operacion.
	 *
	 * @return the subCanalOperacion
	 */
	public String getSubCanalOperacion() {
		return subCanalOperacion;
	}

	/**
	 * Sets the sub canal operacion.
	 *
	 * @param subCanalOperacion
	 *            the subCanalOperacion to set
	 */
	public void setSubCanalOperacion(String subCanalOperacion) {
		this.subCanalOperacion = subCanalOperacion;
	}

	/**
	 * Gets the telefono destinatario.
	 *
	 * @return the telefonoDestinatario
	 */
	public String getTelefonoDestinatario() {
		return telefonoDestinatario;
	}

	/**
	 * Sets the telefono destinatario.
	 *
	 * @param telefonoDestinatario
	 *            the telefonoDestinatario to set
	 */
	public void setTelefonoDestinatario(String telefonoDestinatario) {
		this.telefonoDestinatario = telefonoDestinatario;
	}

	/**
	 * Gets the timestamp alta.
	 *
	 * @return the timestampAlta
	 */
	public String getTimestampAlta() {
		return timestampAlta;
	}

	/**
	 * Sets the timestamp alta.
	 *
	 * @param timestampAlta
	 *            the timestampAlta to set
	 */
	public void setTimestampAlta(String timestampAlta) {
		this.timestampAlta = timestampAlta;
	}

	/**
	 * Gets the tipo agenda ocurrencia.
	 *
	 * @return the tipoAgendaOcurrencia
	 */
	public String getTipoAgendaOcurrencia() {
		return tipoAgendaOcurrencia;
	}

	/**
	 * Sets the tipo agenda ocurrencia.
	 *
	 * @param tipoAgendaOcurrencia
	 *            the tipoAgendaOcurrencia to set
	 */
	public void setTipoAgendaOcurrencia(String tipoAgendaOcurrencia) {
		this.tipoAgendaOcurrencia = tipoAgendaOcurrencia;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the isTransferenciaBP
	 */
	public Boolean getIsTransferenciaBP() {
		return isTransferenciaBP;
	}

	/**
	 * @param isTransferenciaBP 
	 * 					the isTransferenciaBP
	 */
	public void setIsTransferenciaBP(Boolean isTransferenciaBP) {
		this.isTransferenciaBP = isTransferenciaBP;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(bancoDestinatario);
		hcb.append(canalOperacion);
		hcb.append(caracteristicasCuentaDestinatario);
		hcb.append(cbuDestinatario);
		hcb.append(descripcionCuentaDestinatario);
		hcb.append(direccionCorreo);
		hcb.append(documentoDestinatario);
		hcb.append(numeroCuentaDestinatario);
		hcb.append(subCanalOperacion);
		hcb.append(sucursalCuentaDestinatario);
		hcb.append(telefonoDestinatario);
		hcb.append(timestampActivacion);
		hcb.append(timestampAlta);
		hcb.append(tipoAgendaOcurrencia);
		hcb.append(tipoCuentaDestinatario);
		hcb.append(tipoDocumentoDestinatario);
		hcb.append(titular);
		hcb.append(id);
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
		DestinatarioEntity other = (DestinatarioEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(bancoDestinatario, other.getBancoDestinatario());
		eb.append(canalOperacion, other.getCanalOperacion());
		eb.append(caracteristicasCuentaDestinatario, other.getCaracteristicasCuentaDestinatario());
		eb.append(cbuDestinatario, other.getCbuDestinatario());
		eb.append(descripcionCuentaDestinatario, other.getDescripcionCuentaDestinatario());
		eb.append(direccionCorreo, other.getDireccionCorreo());
		eb.append(documentoDestinatario, other.getDocumentoDestinatario());
		eb.append(numeroCuentaDestinatario, other.getNumeroCuentaDestinatario());
		eb.append(subCanalOperacion, other.getSubCanalOperacion());
		eb.append(sucursalCuentaDestinatario, other.getSucursalCuentaDestinatario());
		eb.append(telefonoDestinatario, other.getTelefonoDestinatario());
		eb.append(timestampActivacion, other.getTimestampActivacion());
		eb.append(timestampAlta, other.getTimestampAlta());
		eb.append(tipoAgendaOcurrencia, other.getTipoAgendaOcurrencia());
		eb.append(tipoCuentaDestinatario, other.getTipoCuentaDestinatario());
		eb.append(tipoDocumentoDestinatario, other.getTipoDocumentoDestinatario());
		eb.append(titular, other.getTitular());
		eb.append(id, other.getId());
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
		return new ToStringBuilder(this).append("tipoCuentaDestinatario", tipoCuentaDestinatario)
				.append("sucursalCuentaDestinatario", sucursalCuentaDestinatario)
				.append("numerroCuentaDestinatario", numeroCuentaDestinatario)
				.append("cbuDestinatario", cbuDestinatario).append("id", id).toString();
	}

	/**
	 * Obtener tipo agenda.
	 *
	 * @return the tipo agenda enum
	 */
	public TipoAgendaEnum obtenerTipoAgenda() {
		if (this.alias != null) {
			return TipoAgendaEnum.AGENDA_ALIAS;
		} else if ("RIO".equals(this.tipoAgendaOcurrencia)) {
			return TipoAgendaEnum.AGENDA_RIO;
		} else if ("OB ".equals(this.tipoAgendaOcurrencia)) {
			return TipoAgendaEnum.AGENDA_OTROS_BANCOS;
		} else if ("EXT".equals(this.tipoAgendaOcurrencia)) {
			return TipoAgendaEnum.AGENDA_EXTRACCIONES;
		}
		return TipoAgendaEnum.TODAS_LAS_AGENDAS;
	}

	/**
	 * No tiene referencia ni titular.
	 *
	 * @return true, if successful
	 */
	public boolean noTieneReferenciaNiTitular() {
		return StringUtils.isBlank(this.titular) && StringUtils.isBlank(this.descripcionCuentaDestinatario);
	}

	/**
	 * Validar si los destinatarios provienen de obe ya que se filtran del
	 * listado.
	 *
	 * @return true, if successful
	 */
	public boolean esFiltradoOBE() {
		return ("04".equals(this.canalOperacion) && "11".equals(StringUtils.trim(this.subCanalOperacion)));
	}
}

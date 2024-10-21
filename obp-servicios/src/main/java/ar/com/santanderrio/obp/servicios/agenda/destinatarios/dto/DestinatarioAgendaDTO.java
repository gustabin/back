/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class DestinatarioAgendaDTO.
 *
 * @author florencia.n.martinez
 */
public class DestinatarioAgendaDTO {

	/** The referencia apodo. */
	private String referenciaApodo;

	/** The titular. */
	private String titular;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The tipo cuenta abreviatura. */
	private String tipoCuentaAbreviatura;

	/** The banco. */
	private String banco;

	/** The cuit cuil. */
	private String cuitCuil;

	/** The email. */
	private String email;

	/** The cbu. */
	private String cbu;

	/** The tipoDocumento. */
	private String tipoDocumento;

	/** The documento. */
	private String documento;

	/** The tipo agenda enum. */
	private TipoAgendaEnum tipoAgendaEnum;

	/** The muestra referencia apodo. */
	private Boolean muestraReferenciaApodo = Boolean.FALSE;

	/** The esCuentaPropia. */
	private Boolean esCuentaPropia = Boolean.FALSE;

	/** The id. */
	private String id;

	/** The alias. */
	private String alias;

	/** The es pesos. */
	private Boolean esPesos = Boolean.FALSE;

	/** The fecha creacion. */
	private String fechaCreacion;

	/** The idFavorito. */
	private String idFavorito;
	
	/** The tipoCuentaServicio. */
	private String tipoCuentaServicio;
	
	/** The sucursalServicio. */
	private String sucursalServicio;
	
	/** The nroCuentaServicio. */
	private String nroCuentaServicio;

	/**
	 * Gets the referencia apodo.
	 *
	 * @return the referenciaApodo
	 */
	public String getReferenciaApodo() {
		return referenciaApodo;
	}

	/**
	 * Sets the referencia apodo.
	 *
	 * @param referenciaApodo
	 *            the referenciaApodo to set
	 */
	public void setReferenciaApodo(String referenciaApodo) {
		this.referenciaApodo = referenciaApodo;
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
	 * Gets the nro cuenta.
	 *
	 * @return the nroCuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the nroCuenta to set
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
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
	 * Gets the tipo cuenta abreviatura.
	 *
	 * @return the tipo cuenta abreviatura
	 */
	public String getTipoCuentaAbreviatura() {
		return tipoCuentaAbreviatura;
	}

	/**
	 * Sets the tipo cuenta abreviatura.
	 *
	 * @param tipoCuentaAbreviatura
	 *            the new tipo cuenta abreviatura
	 */
	public void setTipoCuentaAbreviatura(String tipoCuentaAbreviatura) {
		this.tipoCuentaAbreviatura = tipoCuentaAbreviatura;
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
	 *            the banco to set
	 */
	public void setBanco(String banco) {
		if (StringUtils.isNotBlank(banco)) {
			this.banco = banco;
		} else {
			this.banco = ISBANStringUtils.GUION_STRING;
		}
	}

	/**
	 * Gets the cuit cuil.
	 *
	 * @return the cuitCuil
	 */
	public String getCuitCuil() {
		return cuitCuil;
	}

	/**
	 * Sets the cuit cuil.
	 *
	 * @param cuitCuil
	 *            the cuitCuil to set
	 */
	public void setCuitCuil(String cuitCuil) {
		if (StringUtils.isNotBlank(cuitCuil)) {
			this.cuitCuil = cuitCuil;
		} else {
			this.cuitCuil = ISBANStringUtils.GUION_STRING;
		}
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
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * Gets the tipo agenda enum.
	 *
	 * @return the tipoAgendaEnum
	 */
	public TipoAgendaEnum getTipoAgendaEnum() {
		return tipoAgendaEnum;
	}

	/**
	 * Sets the tipo agenda enum.
	 *
	 * @param tipoAgendaEnum
	 *            the tipoAgendaEnum to set
	 */
	public void setTipoAgendaEnum(TipoAgendaEnum tipoAgendaEnum) {
		this.tipoAgendaEnum = tipoAgendaEnum;
	}

	/**
	 * Gets the muestra referencia apodo.
	 *
	 * @return the muestra referencia apodo
	 */
	public Boolean getMuestraReferenciaApodo() {
		return muestraReferenciaApodo;
	}

	/**
	 * Sets the muestra referencia apodo.
	 *
	 * @param muestraReferenciaApodo
	 *            the new muestra referencia apodo
	 */
	public void setMuestraReferenciaApodo(Boolean muestraReferenciaApodo) {
		this.muestraReferenciaApodo = muestraReferenciaApodo;
	}

	/**
	 * Gets the tipo documento.
	 *
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		if (StringUtils.isNotBlank(tipoDocumento)) {
			this.tipoDocumento = tipoDocumento;
		} else {
			this.tipoDocumento = ISBANStringUtils.GUION_STRING;
		}
	}

	/**
	 * Gets the documento.
	 *
	 * @return the documento
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * Sets the documento.
	 *
	 * @param documento
	 *            the documento to set
	 */
	public void setDocumento(String documento) {
		if (StringUtils.isNotBlank(documento)) {
			if (documento.endsWith(" ")) {
				this.documento = ISBANStringUtils.formatearDocumento(documento);
			} else {
				this.documento = ISBANStringUtils.formatearDocumento(ISBANStringUtils.eliminarCeros(documento));
			}
		} else {
			this.documento = ISBANStringUtils.GUION_STRING;
		}
	}

	/**
	 * Gets the es cuenta propia.
	 *
	 * @return the esCuentaPropia
	 */
	public Boolean getEsCuentaPropia() {
		return esCuentaPropia;
	}

	/**
	 * Sets the es cuenta propia.
	 *
	 * @param esCuentaPropia
	 *            the esCuentaPropia to set
	 */
	public void setEsCuentaPropia(Boolean esCuentaPropia) {
		this.esCuentaPropia = esCuentaPropia;
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
	 * Gets the es pesos.
	 *
	 * @return the esPesos
	 */
	public final Boolean getEsPesos() {
		return esPesos;
	}

	/**
	 * Sets the es pesos.
	 *
	 * @param esPesos
	 *            the esPesos to set
	 */
	public final void setEsPesos(Boolean esPesos) {
		this.esPesos = esPesos;
	}

	/**
	 * Gets the fecha creacion.
	 *
	 * @return the fecha creacion
	 */
	public String getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Sets the fecha creacion.
	 *
	 * @param fechaCreacion
	 *            the new fecha creacion
	 */
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the idFavorito
	 */
	public String getIdFavorito() {
		return idFavorito;
	}

	/**
	 * @param idFavorito
	 *            the idFavorito to set
	 */
	public void setIdFavorito(String idFavorito) {
		this.idFavorito = idFavorito;
	}

	/**
	 * @return the tipoCuentaServicio
	 */
	public String getTipoCuentaServicio() {
		return tipoCuentaServicio;
	}

	/**
	 * @param tipoCuentaServicio the tipoCuentaServicio to set
	 */
	public void setTipoCuentaServicio(String tipoCuentaServicio) {
		this.tipoCuentaServicio = tipoCuentaServicio;
	}

	/**
	 * @return the sucursalServicio
	 */
	public String getSucursalServicio() {
		return sucursalServicio;
	}

	/**
	 * @param sucursalServicio the sucursalServicio to set
	 */
	public void setSucursalServicio(String sucursalServicio) {
		this.sucursalServicio = sucursalServicio;
	}

	/**
	 * @return the nroCuentaServicio
	 */
	public String getNroCuentaServicio() {
		return nroCuentaServicio;
	}

	/**
	 * @param nroCuentaServicio the nroCuentaServicio to set
	 */
	public void setNroCuentaServicio(String nroCuentaServicio) {
		this.nroCuentaServicio = nroCuentaServicio;
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
		hcb.append(cuitCuil);
		hcb.append(email);
		hcb.append(muestraReferenciaApodo);
		hcb.append(nroCuenta);
		hcb.append(referenciaApodo);
		hcb.append(tipoAgendaEnum);
		hcb.append(tipoCuenta);
		hcb.append(titular);
		hcb.append(tipoDocumento);
		hcb.append(documento);
		hcb.append(id);
		hcb.append(esCuentaPropia);
		hcb.append(fechaCreacion);
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
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DestinatarioAgendaDTO other = (DestinatarioAgendaDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(banco, other.getBanco());
		eb.append(cbu, other.getCbu());
		eb.append(cuitCuil, other.getCuitCuil());
		eb.append(email, other.getEmail());
		eb.append(muestraReferenciaApodo, other.getMuestraReferenciaApodo());
		eb.append(nroCuenta, other.getNroCuenta());
		eb.append(referenciaApodo, other.getReferenciaApodo());
		eb.append(tipoAgendaEnum, other.getTipoAgendaEnum());
		eb.append(tipoCuenta, other.getTipoCuenta());
		eb.append(titular, other.getTitular());
		eb.append(tipoDocumento, other.getTipoDocumento());
		eb.append(documento, other.getDocumento());
		eb.append(id, other.getId());
		eb.append(esCuentaPropia, other.getEsCuentaPropia());
		eb.append(fechaCreacion, other.getFechaCreacion());
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
		return new ToStringBuilder(this).append("id", id).append("referenciaApodo", referenciaApodo)
				.append("titular", titular).append("nroCuenta", nroCuenta).append("tipoCuenta", tipoCuenta)
				.append("banco", banco).append("cuitCuil", cuitCuil).append("email", email)
				.append("tipoAgendaEnum", tipoAgendaEnum).append("muestraReferenciaApodo", muestraReferenciaApodo)
				.append("tipoDocumento", tipoDocumento).append("documento", documento)
				.append("esCuentaPropia", esCuentaPropia).append("esPesos", esPesos).toString();
	}

}
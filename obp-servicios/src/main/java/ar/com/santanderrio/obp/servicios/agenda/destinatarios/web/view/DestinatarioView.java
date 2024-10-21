/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;

/**
 * 
 * The Class DestinatarioView.
 * 
 * @author dante.omar.olmedo
 */

@XmlRootElement(name = "destinatarioView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DestinatarioView {

	/** The referencia. */
	private String referenciaApodo;

	/** The nrocuenta. */
	private String nroCuenta;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The banco. */
	private String banco;

	/** The titular. */
	private String titular;

	/** The cuit O cuil. */
	private String cuitOCuil;

	/** The e mail. */
	private String eMail;

	/** The cbu. */
	private String cbu;

	/** The dni. */
	private String documento;

	/** Tipo de documento. */
	private String tipoDocumento;

	/** The iniciales. */
	private String iniciales;

	/** The tipo destinatario. */
	private String tipoDestinatario;

	/** The esCuentaPropia. */
	private Boolean esCuentaPropia;

	/** The is from agenda destinatario. */
	private boolean isFromAgendaDestinatario = false;

	/** The id. */
	private String id;

	/** The alias. */
	private String alias;

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
	 * Instantiates a new destinatario view.
	 */
	DestinatarioView() {

	}

	/**
	 * Constructor de view desde dto.
	 *
	 * @param dto
	 *            the dto
	 */
	public DestinatarioView(DestinatarioAgendaDTO dto) {
		this.setReferenciaApodo(dto.getReferenciaApodo());
		this.setNroCuenta(dto.getNroCuenta());
		this.setTipoCuenta(dto.getTipoCuenta());
		this.setBanco(dto.getBanco());
		this.setTitular(dto.getTitular());
		this.setCuitOCuil(dto.getCuitCuil());
		this.seteMail(StringUtils.trim(dto.getEmail()));
		this.setCbu(dto.getCbu());
		this.setDocumento(dto.getDocumento());
		this.setInicialesByApodo(dto.getMuestraReferenciaApodo());
		this.setTipoDestinatario(
				dto.getAlias() != null ? TipoAgendaEnum.AGENDA_ALIAS.getTag() : dto.getTipoAgendaEnum().getTag());
		this.setTipoDocumento(dto.getTipoDocumento());
		this.setId(dto.getId());
		this.setEsCuentaPropia(dto.getEsCuentaPropia());
		this.setAlias(dto.getAlias());
		this.setFechaCreacion(dto.getFechaCreacion());
		this.setIdFavorito(dto.getIdFavorito());
		this.setTipoCuentaServicio(dto.getTipoCuentaServicio());
		this.setSucursalServicio(dto.getSucursalServicio());
		this.setNroCuentaServicio(dto.getNroCuentaServicio());
	}

	/**
	 * Gets the referencia o apodo.
	 *
	 * @return the referencia o apodo
	 */
	public String getReferenciaApodo() {
		return referenciaApodo;
	}

	/**
	 * Sets the referencia.
	 *
	 * @param referenciaApodo
	 *            the new referencia apodo
	 */
	public void setReferenciaApodo(String referenciaApodo) {
		this.referenciaApodo = referenciaApodo;
	}

	/**
	 * Gets the nrocuenta.
	 *
	 * @return the nrocuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nrocuenta.
	 *
	 * @param nrocuenta
	 *            the new nrocuenta
	 */
	public void setNroCuenta(String nrocuenta) {
		this.nroCuenta = nrocuenta;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
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
	 * Gets the cuit O cuil.
	 *
	 * @return the cuit O cuil
	 */
	public String getCuitOCuil() {
		return cuitOCuil;
	}

	/**
	 * Sets the cuit O cuil.
	 *
	 * @param cuitOCuil
	 *            the new cuit O cuil
	 */
	public void setCuitOCuil(String cuitOCuil) {
		this.cuitOCuil = cuitOCuil;
	}

	/**
	 * Gets the e mail.
	 *
	 * @return the e mail
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * Sets the e mail.
	 *
	 * @param eMail
	 *            the new e mail
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
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
	 *            the new cbu
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * Sets the dni.
	 *
	 * @param documento
	 *            the new documento
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}

	/**
	 * Gets the iniciales.
	 *
	 * @return the iniciales
	 */
	public String getIniciales() {
		return iniciales;
	}

	/**
	 * Sets the iniciales.
	 *
	 * @param tieneReferenciaOapodo
	 *            the new iniciales
	 */
	public void setInicialesByApodo(Boolean tieneReferenciaOapodo) {
		String avatar;
		if (tieneReferenciaOapodo) {
			avatar = StringUtils.left(this.getReferenciaApodo(), 2);
		} else {
			avatar = StringUtils.left(this.getTitular(), 2);
		}
		this.iniciales = StringUtils.upperCase(avatar);
	}

	/**
	 * Gets the tipo destinatario.
	 *
	 * @return the tipo destinatario
	 */
	public String getTipoDestinatario() {
		return tipoDestinatario;
	}

	/**
	 * Sets the tipo destinatario.
	 *
	 * @param tipoDestinatario
	 *            the new tipo destinatario
	 */
	public void setTipoDestinatario(String tipoDestinatario) {
		this.tipoDestinatario = tipoDestinatario;
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
		this.tipoDocumento = tipoDocumento;
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
		if (id != null) {
			this.id = id;
		}
	}

	/**
	 * Sets the iniciales.
	 *
	 * @param iniciales
	 *            the iniciales to set
	 */
	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}

	/**
	 * Checks if is from agenda destinatario.
	 *
	 * @return the isFromAgendaDestinatario
	 */
	public boolean isFromAgendaDestinatario() {
		return isFromAgendaDestinatario;
	}

	/**
	 * Sets the from agenda destinatario.
	 *
	 * @param isFromAgendaDestinatario
	 *            the isFromAgendaDestinatario to set
	 */
	public void setFromAgendaDestinatario(boolean isFromAgendaDestinatario) {
		this.isFromAgendaDestinatario = isFromAgendaDestinatario;
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
	 * @param tipoCuentaServicio
	 *            the tipoCuentaServicio to set
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
	 * @param sucursalServicio
	 *            the sucursalServicio to set
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
	 * @param nroCuentaServicio
	 *            the nroCuentaServicio to set
	 */
	public void setNroCuentaServicio(String nroCuentaServicio) {
		this.nroCuentaServicio = nroCuentaServicio;
	}

}

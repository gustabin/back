/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.base.iatx.helpers.SessionUsuarioProvider;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class CabeceraOrdenesTitulosEntity.
 */
public class CabeceraOrdenesTitulosEntity {

	/** The canal tipo. */
	@JsonProperty("H_CanalTipo")
	private String canalTipo;

	/** The sub canal id. */
	@JsonProperty("H_SubCanalId")
	private String subCanalId;

	/** The canal ver. */
	@JsonProperty("H_CanalVer")
	private String canalVer;

	/** The sub canal tipo. */
	@JsonProperty("H_SubCanalTipo")
	private String subCanalTipo;

	/** The canal id. */
	@JsonProperty("H_CanalId")
	private String canalId;

	/** The usuario tipo. */
	@JsonProperty("H_UsuarioTipo")
	private String usuarioTipo;

	/** The usuario id. */
	@JsonProperty("H_UsuarioID")
	private String usuarioId;

	/** The usuario attr. */
	@JsonProperty("H_UsuarioAttr")
	private String usuarioAttr;

	/** The usuario pwd. */
	@JsonProperty("H_UsuarioPwd")
	private String usuarioPwd;

	/** The id us conc. */
	@JsonProperty("H_IdusConc")
	private String idUsConc;

	/** The num sec. */
	@JsonProperty("H_NumSec")
	private String numSec;

	/** The nup. */
	@JsonProperty("H_Nup")
	private String nup;

	/** The ind sincro. */
	@JsonProperty("H_IndSincro")
	private String indSincro;

	/** The tipo cliente. */
	@JsonProperty("H_TipoCliente")
	private String tipoCliente;

	/** The tipo id cliente. */
	@JsonProperty("H_TipoIDCliente")
	private String tipoIdCliente;

	/** The nro id cliente. */
	@JsonProperty("H_NroIDCliente")
	private String nroIdCliente;

	/** The fecha nac. */
	@JsonProperty("H_FechaNac")
	private String fechaNac;

	/**
	 * Gets the canal tipo.
	 *
	 * @return the canalTipo
	 */
	public String getCanalTipo() {
		return canalTipo;
	}

	/**
	 * Sets the canal tipo.
	 *
	 * @param canalTipo
	 *            the canalTipo to set
	 */
	public void setCanalTipo(String canalTipo) {
		this.canalTipo = canalTipo;
	}

	/**
	 * Gets the sub canal id.
	 *
	 * @return the subCanalId
	 */
	public String getSubCanalId() {
		return subCanalId;
	}

	/**
	 * Sets the sub canal id.
	 *
	 * @param subCanalId
	 *            the subCanalId to set
	 */
	public void setSubCanalId(String subCanalId) {
		this.subCanalId = subCanalId;
	}

	/**
	 * Gets the canal ver.
	 *
	 * @return the canalVer
	 */
	public String getCanalVer() {
		return canalVer;
	}

	/**
	 * Sets the canal ver.
	 *
	 * @param canalVer
	 *            the canalVer to set
	 */
	public void setCanalVer(String canalVer) {
		this.canalVer = canalVer;
	}

	/**
	 * Gets the sub canal tipo.
	 *
	 * @return the subCanalTipo
	 */
	public String getSubCanalTipo() {
		return subCanalTipo;
	}

	/**
	 * Sets the sub canal tipo.
	 *
	 * @param subCanalTipo
	 *            the subCanalTipo to set
	 */
	public void setSubCanalTipo(String subCanalTipo) {
		this.subCanalTipo = subCanalTipo;
	}

	/**
	 * Gets the canal id.
	 *
	 * @return the canalId
	 */
	public String getCanalId() {
		return canalId;
	}

	/**
	 * Sets the canal id.
	 *
	 * @param canalId
	 *            the canalId to set
	 */
	public void setCanalId(String canalId) {
		this.canalId = canalId;
	}

	/**
	 * Gets the usuario tipo.
	 *
	 * @return the usuarioTipo
	 */
	public String getUsuarioTipo() {
		return usuarioTipo;
	}

	/**
	 * Sets the usuario tipo.
	 *
	 * @param usuarioTipo
	 *            the usuarioTipo to set
	 */
	public void setUsuarioTipo(String usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	/**
	 * Gets the usuario id.
	 *
	 * @return the usuarioId
	 */
	public String getUsuarioId() {
		return usuarioId;
	}

	/**
	 * Sets the usuario id.
	 *
	 * @param usuarioId
	 *            the usuarioId to set
	 */
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	/**
	 * Gets the usuario attr.
	 *
	 * @return the usuarioAttr
	 */
	public String getUsuarioAttr() {
		return usuarioAttr;
	}

	/**
	 * Sets the usuario attr.
	 *
	 * @param usuarioAttr
	 *            the usuarioAttr to set
	 */
	public void setUsuarioAttr(String usuarioAttr) {
		this.usuarioAttr = usuarioAttr;
	}

	/**
	 * Gets the usuario pwd.
	 *
	 * @return the usuarioPwd
	 */
	public String getUsuarioPwd() {
		return usuarioPwd;
	}

	/**
	 * Sets the usuario pwd.
	 *
	 * @param usuarioPwd
	 *            the usuarioPwd to set
	 */
	public void setUsuarioPwd(String usuarioPwd) {
		this.usuarioPwd = usuarioPwd;
	}

	/**
	 * Gets the id us conc.
	 *
	 * @return the idUsConc
	 */
	public String getIdUsConc() {
		return idUsConc;
	}

	/**
	 * Sets the id us conc.
	 *
	 * @param idUsConc
	 *            the idUsConc to set
	 */
	public void setIdUsConc(String idUsConc) {
		this.idUsConc = idUsConc;
	}

	/**
	 * Gets the num sec.
	 *
	 * @return the numSec
	 */
	public String getNumSec() {
		return numSec;
	}

	/**
	 * Sets the num sec.
	 *
	 * @param numSec
	 *            the numSec to set
	 */
	public void setNumSec(String numSec) {
		this.numSec = numSec;
	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the ind sincro.
	 *
	 * @return the indSincro
	 */
	public String getIndSincro() {
		return indSincro;
	}

	/**
	 * Sets the ind sincro.
	 *
	 * @param indSincro
	 *            the indSincro to set
	 */
	public void setIndSincro(String indSincro) {
		this.indSincro = indSincro;
	}

	/**
	 * Gets the tipo cliente.
	 *
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * Sets the tipo cliente.
	 *
	 * @param tipoCliente
	 *            the tipoCliente to set
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * Gets the tipo id cliente.
	 *
	 * @return the tipoIdCliente
	 */
	public String getTipoIdCliente() {
		return tipoIdCliente;
	}

	/**
	 * Sets the tipo id cliente.
	 *
	 * @param tipoIdCliente
	 *            the tipoIdCliente to set
	 */
	public void setTipoIdCliente(String tipoIdCliente) {
		this.tipoIdCliente = tipoIdCliente;
	}

	/**
	 * Gets the nro id cliente.
	 *
	 * @return the nroIdCliente
	 */
	public String getNroIdCliente() {
		return nroIdCliente;
	}

	/**
	 * Sets the nro id cliente.
	 *
	 * @param nroIdCliente
	 *            the nroIdCliente to set
	 */
	public void setNroIdCliente(String nroIdCliente) {
		this.nroIdCliente = nroIdCliente;
	}

	/**
	 * Gets the fecha nac.
	 *
	 * @return the fechaNac
	 */
	public String getFechaNac() {
		return fechaNac;
	}

	/**
	 * Sets the fecha nac.
	 *
	 * @param fechaNac
	 *            the fechaNac to set
	 */
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	/**
	 * Generar cabecera request.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the cabecera ordenes titulos entity
	 */
	public static CabeceraOrdenesTitulosEntity generarCabeceraRequest(Cliente cliente) {
		CabeceraOrdenesTitulosEntity cabecera = new CabeceraOrdenesTitulosEntity();
		cabecera.setSubCanalId("HTML");
		cabecera.setCanalVer("000");
		cabecera.setCanalId("0001");
		cabecera.setUsuarioTipo("03");
		cabecera.setUsuarioId(cliente.getUsuarioRacf());
		cabecera.setUsuarioAttr("  ");
		cabecera.setUsuarioPwd(cliente.getPasswordRacf());
		cabecera.setIdUsConc(SessionUsuarioProvider.getSessionUsuario());
		cabecera.setNumSec(SessionUsuarioProvider.getNroReq());
		cabecera.setNup(cliente.getNup());
		cabecera.setIndSincro("1");
		cabecera.setTipoCliente("I");
		cabecera.setTipoIdCliente(cliente.getTipoDocumento());
		cabecera.setNroIdCliente(cliente.getDni());
		cabecera.setFechaNac(cliente.getFechaNacimiento());
		return cabecera;
	}

}
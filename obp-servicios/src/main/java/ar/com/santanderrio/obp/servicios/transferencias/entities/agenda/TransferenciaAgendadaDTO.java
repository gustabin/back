/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities.agenda;

import java.util.Date;

import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class TransferenciaAgendadaDTO.
 */
public class TransferenciaAgendadaDTO extends TransferenciaDTO {

	/** The fecha ejecucion. */
	private Date fechaEjecucion;

	/** The destinatario. */
	private DestinatarioDTO destinatario;

	// TODO : Obtener datos cuentaOrigen - cuentaOrigenTipo -
	// cuentaOrigenTipoSinUnificar de Cuenta de TransferenciaDTO
	/** The cuenta origen. */
	private String nroCuentaOrigen;

	/** The cuenta origen tipo. */
	private TipoCuenta cuentaOrigenTipo;

	/** The cuenta origen tipo sin unificar. */
	private TipoCuenta cuentaOrigenTipoSinUnificar;

	/** The cuenta alias origen. */
	private String cuentaAliasOrigen;

	// TODO : Obtener datos cuentaDestino - cuentaAliasDestino -
	// cuentaDestinoTipoSinUnificar de Cuenta de TransferenciaDTO
	/** The cuenta destino. */
	private String cuentaDestino;

	/** The cuenta alias destino. */
	private String cuentaAliasDestino;

	/** The cuenta destino tipo. */
	private TipoCuenta cuentaDestinoTipo;

	/** The cuenta destino tipo sin unificar. */
	private TipoCuenta cuentaDestinoTipoSinUnificar;

	// TODO : Revisar el campo, ya que el del padre es cuit no cuil, este
	// atributo representa ambos
	/** The cuit cuil. */
	private String cuitCuil;

	/** The id def. */
	private String idDef;

	/** The id datos rec. */
	private String idDatosRec;

	/** The id evento. */
	private String idEvento;

	/** The datos origen. */
	private DatosOrigenTransferenciaAgendadaDTO datosOrigen;

	/** The tipo transferencia agendada. */
	private TipoTransferenciaAgendada tipoTransferenciaAgendada;
	
	/** The referencia. */
	private String referencia;

	/**
	 * Gets the id def.
	 *
	 * @return the idDef
	 */
	public String getIdDef() {
		return idDef;
	}

	/**
	 * Sets the id def.
	 *
	 * @param idDef
	 *            the idDef to set
	 */
	public void setIdDef(String idDef) {
		this.idDef = idDef;
	}

	/**
	 * Gets the id datos rec.
	 *
	 * @return the idDatosRec
	 */
	public String getIdDatosRec() {
		return idDatosRec;
	}

	/**
	 * Sets the id datos rec.
	 *
	 * @param idDatosRec
	 *            the idDatosRec to set
	 */
	public void setIdDatosRec(String idDatosRec) {
		this.idDatosRec = idDatosRec;
	}

	/**
	 * Gets the id evento.
	 *
	 * @return the idEvento
	 */
	public String getIdEvento() {
		return idEvento;
	}

	/**
	 * Sets the id evento.
	 *
	 * @param idEvento
	 *            the idEvento to set
	 */
	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	/**
	 * Gets the cuenta origen tipo.
	 *
	 * @return the cuenta origen tipo
	 */
	public TipoCuenta getCuentaOrigenTipo() {
		return cuentaOrigenTipo;
	}

	/**
	 * Sets the cuenta origen tipo.
	 *
	 * @param cuentaOrigenTipo
	 *            the new cuenta origen tipo
	 */
	public void setCuentaOrigenTipo(TipoCuenta cuentaOrigenTipo) {
		this.cuentaOrigenTipo = cuentaOrigenTipo;
	}

	/**
	 * Gets the cuenta destino tipo.
	 *
	 * @return the cuenta destino tipo
	 */
	public TipoCuenta getCuentaDestinoTipo() {
		return cuentaDestinoTipo;
	}

	/**
	 * Sets the cuenta destino tipo.
	 *
	 * @param cuentaDestinoTipo
	 *            the new cuenta destino tipo
	 */
	public void setCuentaDestinoTipo(TipoCuenta cuentaDestinoTipo) {
		this.cuentaDestinoTipo = cuentaDestinoTipo;
	}

	/**
	 * Gets the fecha ejecucion.
	 *
	 * @return the fechaEjecucion
	 */
	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

	/**
	 * Sets the fecha ejecucion.
	 *
	 * @param fechaEjecucion
	 *            the fechaEjecucion to set
	 */
	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	/**
	 * Gets the destinatario.
	 *
	 * @return the destinatario
	 */
	public DestinatarioDTO getDestinatario() {
		return destinatario;
	}

	/**
	 * Sets the destinatario.
	 *
	 * @param destinatario
	 *            the destinatario to set
	 */
	public void setDestinatario(DestinatarioDTO destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * Gets the cuenta origen.
	 *
	 * @return the cuenta origen
	 */
	public String getNroCuentaOrigen() {
		return nroCuentaOrigen;
	}

	/**
	 * Sets the cuenta origen.
	 *
	 * @param cuentaOrigen
	 *            the new cuenta origen
	 */
	public void setNroCuentaOrigen(String cuentaOrigen) {
		this.nroCuentaOrigen = cuentaOrigen;
	}

	/**
	 * Gets the cuenta destino.
	 *
	 * @return the cuenta destino
	 */
	public String getCuentaDestino() {
		return cuentaDestino;
	}

	/**
	 * Sets the cuenta destino.
	 *
	 * @param cuentaDestino
	 *            the new cuenta destino
	 */
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	/**
	 * Gets the cuenta alias origen.
	 *
	 * @return the cuenta alias origen
	 */
	public String getCuentaAliasOrigen() {
		return cuentaAliasOrigen;
	}

	/**
	 * Sets the cuenta alias origen.
	 *
	 * @param cuentaAliasOrigen
	 *            the new cuenta alias origen
	 */
	public void setCuentaAliasOrigen(String cuentaAliasOrigen) {
		this.cuentaAliasOrigen = cuentaAliasOrigen;
	}

	/**
	 * Gets the cuenta alias destino.
	 *
	 * @return the cuenta alias destino
	 */
	public String getCuentaAliasDestino() {
		return cuentaAliasDestino;
	}

	/**
	 * Sets the cuenta alias destino.
	 *
	 * @param cuentaAliasDestino
	 *            the new cuenta alias destino
	 */
	public void setCuentaAliasDestino(String cuentaAliasDestino) {
		this.cuentaAliasDestino = cuentaAliasDestino;
	}

	/**
	 * Gets the cuenta origen tipo sin unificar.
	 *
	 * @return the cuenta origen tipo sin unificar
	 */
	public TipoCuenta getCuentaOrigenTipoSinUnificar() {
		return cuentaOrigenTipoSinUnificar;
	}

	/**
	 * Sets the cuenta origen tipo sin unificar.
	 *
	 * @param cuentaOrigenTipoSinUnificar
	 *            the new cuenta origen tipo sin unificar
	 */
	public void setCuentaOrigenTipoSinUnificar(TipoCuenta cuentaOrigenTipoSinUnificar) {
		this.cuentaOrigenTipoSinUnificar = cuentaOrigenTipoSinUnificar;
	}

	/**
	 * Gets the cuenta destino tipo sin unificar.
	 *
	 * @return the cuenta destino tipo sin unificar
	 */
	public TipoCuenta getCuentaDestinoTipoSinUnificar() {
		return cuentaDestinoTipoSinUnificar;
	}

	/**
	 * Sets the cuenta destino tipo sin unificar.
	 *
	 * @param cuentaDestinoTipoSinUnificar
	 *            the new cuenta destino tipo sin unificar
	 */
	public void setCuentaDestinoTipoSinUnificar(TipoCuenta cuentaDestinoTipoSinUnificar) {
		this.cuentaDestinoTipoSinUnificar = cuentaDestinoTipoSinUnificar;
	}

	/**
	 * Gets the datos origen.
	 *
	 * @return the datosOrigen
	 */
	public DatosOrigenTransferenciaAgendadaDTO getDatosOrigen() {
		return datosOrigen;
	}

	/**
	 * Sets the datos origen.
	 *
	 * @param datosOrigen
	 *            the datosOrigen to set
	 */
	public void setDatosOrigen(DatosOrigenTransferenciaAgendadaDTO datosOrigen) {
		this.datosOrigen = datosOrigen;
	}

	/**
	 * Gets the tipo transferencia agendada.
	 *
	 * @return the tipo transferencia agendada
	 */
	public TipoTransferenciaAgendada getTipoTransferenciaAgendada() {
		return tipoTransferenciaAgendada;
	}

	/**
	 * Sets the tipo transferencia agendada.
	 *
	 * @param tipoTransferenciaAgendada
	 *            the new tipo transferencia agendada
	 */
	public void setTipoTransferenciaAgendada(TipoTransferenciaAgendada tipoTransferenciaAgendada) {
		this.tipoTransferenciaAgendada = tipoTransferenciaAgendada;
	}

	/**
	 * Gets the cuit cuil.
	 *
	 * @return the cuit cuil
	 */
	public String getCuitCuil() {
		return cuitCuil;
	}

	/**
	 * Sets the cuit cuil.
	 *
	 * @param cuitCuil
	 *            the new cuit cuil
	 */
	public void setCuitCuil(String cuitCuil) {
		this.cuitCuil = cuitCuil;
	}

	/**
	 * Accept.
	 *
	 * @param visitor
	 *            the visitor
	 */
	public void accept(TransferenciaAgendadaVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Gets the referencia.
	 *
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * Sets the referencia.
	 *
	 * @param referencia
	 *            the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	
}
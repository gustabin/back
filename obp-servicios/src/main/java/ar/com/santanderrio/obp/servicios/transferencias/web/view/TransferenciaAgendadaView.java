/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Class TransferenciaAgendadaView.
 */
@XmlRootElement(name = "transferenciaAgendadaView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferenciaAgendadaView extends RsaDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5354309546956939519L;

	/** The id. */
	private String id;

	/** The fecha. */
	private String fecha;

	/** The apodo transferencia. */
	private String apodoTransferencia;

	/** The destinatario nombre. */
	private String destinatarioNombre;

	/** The destinatario banco. */
	private String destinatarioBanco;

	/** The destinatario saldo. */
	private String destinatarioSaldo;

	/** The tipo. */
	private String tipo;

	/** The frecuencia. */
	private String frecuencia;

	/** The importe. */
	private String importe;

	/** The divisa. */
	private String divisa;

	/** The is programada. */
	private Boolean isProgramada;

	/** The is recordatorio. */
	private Boolean isRecordatorio;

	/** The is recurrente. */
	private Boolean isRecurrente;

	/** The is rio rio. */
	private Boolean isRioRio;

	/** The cuenta propia. */
	private String cuentaPropia;

	/** The email. */
	private String email;

	/** The mensaje email. */
	private String mensajeEmail;

	/** The desafio. */
	@JsonIgnore
	@JsonManagedReference
	private AutentificacionDTO desafio;

	/**
	 * La antiguedad del destinatario agendado expresado en dias. Se usa en RSA.
	 */
	private Integer antiguedad = 0;

	/** Se usa en RSA. Siempre que se envia desde la agenda sera falso */
	private boolean banelco = false;

	/** The tiene celular my A. Se usa en RSA. */
	private boolean tieneCelularMyA = false;

	/** The es agendamiento transferencia. */
	private boolean agendamientoTransferencia = false;

	/**
	 * The mensaje que se muestra en caso que se guarde satisfactoriamente la
	 * transferencia.
	 */
	private String mensaje;

	/** The fecha creacion de Destinatario en la agenda. Se usa en RSA. */
	private String fechaCreacionDestinatario = null;

	/**
	 * Instantiates a new transferencia.
	 * 
	 * @see {@link RsaDTO}
	 */
	public TransferenciaAgendadaView() {
		super(OperacionesRSAEnum.AGENDAR_TRANSFERENCIA);

	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the apodo transferencia.
	 *
	 * @return the apodo
	 */
	public String getApodoTransferencia() {
		return apodoTransferencia;
	}

	/**
	 * Sets the apodo transferencia.
	 *
	 * @param apodoTransferencia
	 *            the new apodo transferencia
	 */
	public void setApodoTransferencia(String apodoTransferencia) {
		this.apodoTransferencia = apodoTransferencia;
	}

	/**
	 * Gets the destinatario nombre.
	 *
	 * @return the destinatarioNombre
	 */
	public String getDestinatarioNombre() {
		return destinatarioNombre;
	}

	/**
	 * Sets the destinatario nombre.
	 *
	 * @param destinatarioNombre
	 *            the destinatarioNombre to set
	 */
	public void setDestinatarioNombre(String destinatarioNombre) {
		this.destinatarioNombre = destinatarioNombre;
	}

	/**
	 * Gets the destinatario banco.
	 *
	 * @return the destinatarioBanco
	 */
	public String getDestinatarioBanco() {
		return destinatarioBanco;
	}

	/**
	 * Sets the destinatario banco.
	 *
	 * @param destinatarioBanco
	 *            the destinatarioBanco to set
	 */
	public void setDestinatarioBanco(String destinatarioBanco) {
		this.destinatarioBanco = destinatarioBanco;
	}

	/**
	 * Gets the destinatario saldo.
	 *
	 * @return the destinatario saldo
	 */
	public String getDestinatarioSaldo() {
		return destinatarioSaldo;
	}

	/**
	 * Sets the destinatario saldo.
	 *
	 * @param destinatarioSaldo
	 *            the new destinatario saldo
	 */
	public void setDestinatarioSaldo(String destinatarioSaldo) {
		this.destinatarioSaldo = destinatarioSaldo;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the frecuencia.
	 *
	 * @return the frecuencia
	 */
	public String getFrecuencia() {
		return frecuencia;
	}

	/**
	 * Sets the frecuencia.
	 *
	 * @param frecuencia
	 *            the frecuencia to set
	 */
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the divisa to set
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the checks if is programada.
	 *
	 * @return the isProgramada
	 */
	public Boolean getIsProgramada() {
		return isProgramada;
	}

	/**
	 * Sets the checks if is programada.
	 *
	 * @param isProgramada
	 *            the isProgramada to set
	 */
	public void setIsProgramada(Boolean isProgramada) {
		this.isProgramada = isProgramada;
	}

	/**
	 * Gets the checks if is recordatorio.
	 *
	 * @return the isRecordatorio
	 */
	public Boolean getIsRecordatorio() {
		return isRecordatorio;
	}

	/**
	 * Sets the checks if is recordatorio.
	 *
	 * @param isRecordatorio
	 *            the isRecordatorio to set
	 */
	public void setIsRecordatorio(Boolean isRecordatorio) {
		this.isRecordatorio = isRecordatorio;
	}

	/**
	 * Gets the checks if is recurrente.
	 *
	 * @return the isRecurrente
	 */
	public Boolean getIsRecurrente() {
		return isRecurrente;
	}

	/**
	 * Sets the checks if is recurrente.
	 *
	 * @param isRecurrente
	 *            the isRecurrente to set
	 */
	public void setIsRecurrente(Boolean isRecurrente) {
		this.isRecurrente = isRecurrente;
	}

	/**
	 * Gets the cuenta propia.
	 *
	 * @return the cuentaPropia
	 */
	public String getCuentaPropia() {
		return cuentaPropia;
	}

	/**
	 * Sets the cuenta propia.
	 *
	 * @param cuentaPropia
	 *            the cuentaPropia to set
	 */
	public void setCuentaPropia(String cuentaPropia) {
		this.cuentaPropia = cuentaPropia;
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
	 * Gets the mensaje email.
	 *
	 * @return the mensajeEmail
	 */
	public String getMensajeEmail() {
		return mensajeEmail;
	}

	/**
	 * Sets the mensaje email.
	 *
	 * @param mensajeEmail
	 *            the mensajeEmail to set
	 */
	public void setMensajeEmail(String mensajeEmail) {
		this.mensajeEmail = mensajeEmail;
	}

	/**
	 * Gets the desafio.
	 *
	 * @return the desafio
	 */
	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	/**
	 * Sets the desafio.
	 *
	 * @param desafio
	 *            the new desafio
	 */
	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	/**
	 * Gets the antiguedad.
	 *
	 * @return the antiguedad
	 */
	public Integer getAntiguedad() {
		return antiguedad;
	}

	/**
	 * Sets the antiguedad.
	 *
	 * @param antiguedad
	 *            the new antiguedad
	 */
	public void setAntiguedad(Integer antiguedad) {
		this.antiguedad = antiguedad;
	}

	/**
	 * Checks if is banelco.
	 *
	 * @return true, if is banelco
	 */
	public boolean isBanelco() {
		return banelco;
	}

	/**
	 * Sets the banelco.
	 *
	 * @param banelco
	 *            the new banelco
	 */
	public void setBanelco(boolean banelco) {
		this.banelco = banelco;
	}

	/**
	 * Checks if is tiene celular my A.
	 *
	 * @return true, if is tiene celular my A
	 */
	public boolean isTieneCelularMyA() {
		return tieneCelularMyA;
	}

	/**
	 * Sets the tiene celular my A.
	 *
	 * @param tieneCelularMyA
	 *            the new tiene celular my A
	 */
	public void setTieneCelularMyA(boolean tieneCelularMyA) {
		this.tieneCelularMyA = tieneCelularMyA;
	}

	/**
	 * Gets the checks if is rio rio.
	 *
	 * @return the checks if is rio rio
	 */
	public Boolean getIsRioRio() {
		return isRioRio;
	}

	/**
	 * Sets the checks if is rio rio.
	 *
	 * @param isRioRio
	 *            the new checks if is rio rio
	 */
	public void setIsRioRio(Boolean isRioRio) {
		this.isRioRio = isRioRio;
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
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Checks if is agendamiento transferencia.
	 *
	 * @return true, if is agendamiento transferencia
	 */
	public boolean isAgendamientoTransferencia() {
		return agendamientoTransferencia;
	}

	/**
	 * Sets the agendamiento transferencia.
	 *
	 * @param agendamientoTransferencia
	 *            the new agendamiento transferencia
	 */
	public void setAgendamientoTransferencia(boolean agendamientoTransferencia) {
		this.agendamientoTransferencia = agendamientoTransferencia;
	}

	/**
	 * Gets the fecha creacion destinatario.
	 *
	 * @return the fecha creacion destinatario
	 */
	public String getFechaCreacionDestinatario() {
		return fechaCreacionDestinatario;
	}

	/**
	 * Sets the fecha creacion destinatario.
	 *
	 * @param fechaCreacionDestinatario
	 *            the new fecha creacion destinatario
	 */
	public void setFechaCreacionDestinatario(String fechaCreacionDestinatario) {
		this.fechaCreacionDestinatario = fechaCreacionDestinatario;
	}

}

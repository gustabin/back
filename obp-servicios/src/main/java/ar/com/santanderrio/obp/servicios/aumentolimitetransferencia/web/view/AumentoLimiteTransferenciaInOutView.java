/*
 *
 */
package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto.TISFechasHabilitadasResponse;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto.TransferenciaSumResponse;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;

/**
 * The Class AumentoLimiteTransferenciaInOutView.
 */
@XmlRootElement(name = "AumentoLimiteTransferenciaInOutView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AumentoLimiteTransferenciaInOutView extends RsaDTOParaDesafio {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4994045127504618131L;

	/** The cbu. */
	private String cbu;

	/** The cbu 2. */
	private String cbu2;

	/** The titular. */
	private String titular;

	/** The banco. */
	private String banco;

	/** The cuit. */
	private String cuitCuil;

	/** The importe. */
	private String importe;

	/** The concepto. */
	private String concepto;

	/** The descripcion. */
	private String descripcion;

	/** The moneda. */
	private String moneda;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The nro cuenta destino. */
	private String nroCuentaDestino = "";

	/** The fecha ejecucion. */
	private String fechaEjecucion;

	/** The fecha acreditacion. */
	private String fechaAcreditacion;

	/** The envia email. */
	private String enviaEmail;

	/** The email. */
	private String email;

	/** The mensaje email. */
	private String mensajeEmail;

	/** The cuentas view. */
	private CuentasView cuentasView;

	/** The numero comprobante. */
	private String numeroComprobante = "";

	/** The tipo cuenta destino. */
	private String tipoCuentaDestino = "";

	/** The mensaje. */
	private String mensaje;

	/** Alias del destinatario. */
	private String aliasDestino;

	/** The cuenta propia. */
	private boolean cuentaPropia;

	/** The is rio rio. */
	private Boolean isRioRio;

	/** The fecha operacion. */
	private String fechaOperacion;

	/** The antiguedad. Se usa en RSA. */
	private String fechaCreacionDestinatario;

	/** The monedas disponibles. */
	private List<String> monedasDisponibles;

	/** The tipo cuenta descripcion. */
	private String tipoCuentaDescripcion;

	/** The tipo cuenta destino descripcion. */
	private String tipoCuentaDestinoDescripcion;
	/** The transferencia manual. */
	private boolean transferenciaManual;

	/** The destinatarioNoVerificadodestino boolean. */
	private boolean destinatarioNoVerificado = false;

	/** The is from agenda destinatario. */
	private boolean isFromAgendaDestinatario = false;

	/** The is automatica. */
	private boolean isAutomatica = false;

	/** Se usa en RSA. */
	@JsonIgnore
	private boolean isInmediata = false;

	/** Se usa en RSA. */
	@JsonIgnore
	private String saldoCuentaOrigen;

	/** Se usa en RSA. */
	@JsonIgnore
	private TipoCuenta tipoCuentaEnum;

	/** Se usa en RSA. */
	@JsonIgnore
	private String monedaAltair;

	/** Se usa en RSA. */
	@JsonIgnore
	private boolean pedidoAumentoLimite = true;

	/** Se usa en RSA. */
	private TransferenciaSumResponse controlSum;

	/** Se usa en RSA. */
	@JsonIgnore
	private Integer cantDiasUltimoCambioToken;

	/** Se usa en RSA. */
	@JsonIgnore
	private Integer cantDiasUltimoCambioClave;

	@JsonIgnore
	private String cuitCliente;

	@JsonIgnore
	private float scoringDestinatario;

	/** Se usa en RSA. */
	private BiocatchResponseDataDTO biocatchResponseDataDTO;

	private TISFechasHabilitadasResponse tisFechasHabilitadasResponse;

	/** Se usa en RSA. */
	private Integer cantDiasUltimoCambioMail;

	/**
	 * Este flag permite determinar si es una nueva transferencia. Se usa para
	 * las estadisticas
	 */
	private boolean nuevaTransferencia = false;

	/**
	 * Instantiates a new aumento limite transferencia in out view.
	 */
	public AumentoLimiteTransferenciaInOutView() {
		super(OperacionesRSAEnum.AUMENTO_LIMITE_TRANSFERENCIA);
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
	 * Gets the alias destino.
	 *
	 * @return the aliasDestino
	 */
	public String getAliasDestino() {
		return aliasDestino;
	}

	/**
	 * Sets the alias destino.
	 *
	 * @param aliasDestino
	 *            the aliasDestino to set
	 */
	public void setAliasDestino(String aliasDestino) {
		this.aliasDestino = aliasDestino;
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
	 * Gets the tipo cuenta destino.
	 *
	 * @return the tipo cuenta destino
	 */
	public String getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/**
	 * Sets the tipo cuenta destino.
	 *
	 * @param tipoCuentaDestino
	 *            the new tipo cuenta destino
	 */
	public void setTipoCuentaDestino(String tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
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
	 * Gets the cbu 2.
	 *
	 * @return the cbu2
	 */
	public String getCbu2() {
		return cbu2;
	}

	/**
	 * Sets the cbu 2.
	 *
	 * @param cbu2
	 *            the cbu2 to set
	 */
	public void setCbu2(String cbu2) {
		this.cbu2 = cbu2;
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
		this.banco = banco;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuitCuil;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuitCuil = cuit;
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
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Sets the concepto.
	 *
	 * @param concepto
	 *            the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Checks if is cuenta propia.
	 *
	 * @return true, if is cuenta propia
	 */
	public boolean isCuentaPropia() {
		return cuentaPropia;
	}

	/**
	 * Sets the cuenta propia.
	 *
	 * @param cuentaPropia
	 *            the new cuenta propia
	 */
	public void setCuentaPropia(boolean cuentaPropia) {
		this.cuentaPropia = cuentaPropia;
	}

	/**
	 * Gets the cuentas view.
	 *
	 * @return the cuentasView
	 */
	public CuentasView getCuentasView() {
		return cuentasView;
	}

	/**
	 * Sets the cuentas view.
	 *
	 * @param cuentasView
	 *            the cuentasView to set
	 */
	public void setCuentasView(CuentasView cuentasView) {
		this.cuentasView = cuentasView;
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
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
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
	 * Gets the nro cuenta destino.
	 *
	 * @return numero de cuenta destino
	 */
	public String getNroCuentaDestino() {
		return nroCuentaDestino;
	}

	/**
	 * numero de cuenta destino.
	 *
	 * @param nroCuentaDestino
	 *            the new nro cuenta destino
	 */
	public void setNroCuentaDestino(String nroCuentaDestino) {
		this.nroCuentaDestino = nroCuentaDestino;
	}

	/**
	 * Gets the fecha ejecucion.
	 *
	 * @return the fechaEjecucion
	 */
	public String getFechaEjecucion() {
		return fechaEjecucion;
	}

	/**
	 * Sets the fecha ejecucion.
	 *
	 * @param fechaEjecucion
	 *            the fechaEjecucion to set
	 */
	public void setFechaEjecucion(String fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	/**
	 * Gets the fecha acreditacion.
	 *
	 * @return the fechaAcreditacion
	 */
	public String getFechaAcreditacion() {
		return fechaAcreditacion;
	}

	/**
	 * Sets the fecha acreditacion.
	 *
	 * @param fechaAcreditacion
	 *            the fechaAcreditacion to set
	 */
	public void setFechaAcreditacion(String fechaAcreditacion) {
		this.fechaAcreditacion = fechaAcreditacion;
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
	 * Gets the envia email.
	 *
	 * @return the envia email
	 */
	public String getEnviaEmail() {
		return enviaEmail;
	}

	/**
	 * Sets the envia email.
	 *
	 * @param enviaEmail
	 *            the new envia email
	 */
	public void setEnviaEmail(String enviaEmail) {
		this.enviaEmail = enviaEmail;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the monedas disponibles.
	 *
	 * @return the monedas disponibles
	 */
	public List<String> getMonedasDisponibles() {
		return monedasDisponibles;
	}

	/**
	 * Sets the monedas disponibles.
	 *
	 * @param monedasDisponibles
	 *            the new monedas disponibles
	 */
	public void setMonedasDisponibles(List<String> monedasDisponibles) {
		this.monedasDisponibles = monedasDisponibles;
	}

	/**
	 * Gets the tipo cuenta descripcion.
	 *
	 * @return the tipo cuenta descripcion
	 */
	public String getTipoCuentaDescripcion() {
		return tipoCuentaDescripcion;
	}

	/**
	 * Sets the tipo cuenta descripcion.
	 *
	 * @param tipoCuentaDescripcion
	 *            the new tipo cuenta descripcion
	 */
	public void setTipoCuentaDescripcion(String tipoCuentaDescripcion) {
		this.tipoCuentaDescripcion = tipoCuentaDescripcion;
	}

	/**
	 * Checks if is transferencia manual.
	 *
	 * @return true, if is transferencia manual
	 */
	public boolean isTransferenciaManual() {
		return transferenciaManual;
	}

	/**
	 * Sets the transferencia manual.
	 *
	 * @param transferenciaManual
	 *            the new transferencia manual
	 */
	public void setTransferenciaManual(boolean transferenciaManual) {
		this.transferenciaManual = transferenciaManual;
	}

	/**
	 * Gets the tipo cuenta destino descripcion.
	 *
	 * @return the tipo cuenta destino descripcion
	 */
	public String getTipoCuentaDestinoDescripcion() {
		return tipoCuentaDestinoDescripcion;
	}

	/**
	 * Sets the tipo cuenta destino descripcion.
	 *
	 * @param tipoCuentaDestinoDescripcion
	 *            the new tipo cuenta destino descripcion
	 */
	public void setTipoCuentaDestinoDescripcion(String tipoCuentaDestinoDescripcion) {
		this.tipoCuentaDestinoDescripcion = tipoCuentaDestinoDescripcion;
	}

	/**
	 * Checks if is destinatario no verificado.
	 *
	 * @return true, if is destinatario no verificado
	 */
	public boolean isDestinatarioNoVerificado() {
		return destinatarioNoVerificado;
	}

	/**
	 * Sets the destinatario no verificado.
	 *
	 * @param destinatarioNoVerificado
	 *            the new destinatario no verificado
	 */
	public void setDestinatarioNoVerificado(boolean destinatarioNoVerificado) {
		this.destinatarioNoVerificado = destinatarioNoVerificado;
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
	 * Gets the fecha operacion.
	 *
	 * @return the fecha operacion
	 */
	public String getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * Sets the fecha operacion.
	 *
	 * @param fechaOperacion
	 *            the new fecha operacion
	 */
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
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
	 * Checks if is inmediata.
	 *
	 * @return true, if is inmediata
	 */
	public boolean isInmediata() {
		return isInmediata;
	}

	/**
	 * Sets the inmediata.
	 *
	 * @param isInmediata
	 *            the new inmediata
	 */
	public void setInmediata(boolean isInmediata) {
		this.isInmediata = isInmediata;
	}

	public String getFechaCreacionDestinatario() {
		return fechaCreacionDestinatario;
	}

	public void setFechaCreacionDestinatario(String fechaCreacionDestinatario) {
		this.fechaCreacionDestinatario = fechaCreacionDestinatario;
	}

	/**
	 * Gets the saldo cuenta origen.
	 *
	 * @return the saldo cuenta origen
	 */
	public String getSaldoCuentaOrigen() {
		return saldoCuentaOrigen;
	}

	/**
	 * Sets the saldo cuenta origen.
	 *
	 * @param saldoCuentaOrigen
	 *            the new saldo cuenta origen
	 */
	public void setSaldoCuentaOrigen(String saldoCuentaOrigen) {
		this.saldoCuentaOrigen = saldoCuentaOrigen;
	}

	/**
	 * Gets the tipo cuenta enum.
	 *
	 * @return the tipo cuenta enum
	 */
	public TipoCuenta getTipoCuentaEnum() {
		return tipoCuentaEnum;
	}

	/**
	 * Sets the tipo cuenta enum.
	 *
	 * @param tipoCuentaEnum
	 *            the new tipo cuenta enum
	 */
	public void setTipoCuentaEnum(TipoCuenta tipoCuentaEnum) {
		this.tipoCuentaEnum = tipoCuentaEnum;
	}

	/**
	 * Gets the moneda altair.
	 *
	 * @return the moneda altair
	 */
	public String getMonedaAltair() {
		return monedaAltair;
	}

	/**
	 * Sets the moneda altair.
	 *
	 * @param monedaAltair
	 *            the new moneda altair
	 */
	public void setMonedaAltair(String monedaAltair) {
		this.monedaAltair = monedaAltair;
	}

	/**
	 * Checks if is automatica.
	 *
	 * @return the isAutomatica
	 */
	public boolean isAutomatica() {
		return isAutomatica;
	}

	/**
	 * Sets the automatica.
	 *
	 * @param isAutomatica
	 *            the isAutomatica to set
	 */
	public void setAutomatica(boolean isAutomatica) {
		this.isAutomatica = isAutomatica;
	}

	/**
	 * Checks if is es nueva transferencia.
	 *
	 * @return true, if is es nueva transferencia
	 */
	public boolean isNuevaTransferencia() {
		return nuevaTransferencia;
	}

	/**
	 * Sets the es nueva transferencia.
	 *
	 * @param esNuevaTransferencia
	 *            the new es nueva transferencia
	 */
	public void setNuevaTransferencia(boolean esNuevaTransferencia) {
		this.nuevaTransferencia = esNuevaTransferencia;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aliasDestino == null) ? 0 : aliasDestino.hashCode());
		result = prime * result + ((fechaCreacionDestinatario == null) ? 0 : fechaCreacionDestinatario.hashCode());
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		result = prime * result + ((cbu == null) ? 0 : cbu.hashCode());
		result = prime * result + ((cbu2 == null) ? 0 : cbu2.hashCode());
		result = prime * result + ((concepto == null) ? 0 : concepto.hashCode());
		result = prime * result + (cuentaPropia ? 1231 : 1237);
		result = prime * result + ((cuentasView == null) ? 0 : cuentasView.hashCode());
		result = prime * result + ((cuitCuil == null) ? 0 : cuitCuil.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + (destinatarioNoVerificado ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enviaEmail == null) ? 0 : enviaEmail.hashCode());
		result = prime * result + ((fechaAcreditacion == null) ? 0 : fechaAcreditacion.hashCode());
		result = prime * result + ((fechaEjecucion == null) ? 0 : fechaEjecucion.hashCode());
		result = prime * result + ((fechaOperacion == null) ? 0 : fechaOperacion.hashCode());
		result = prime * result + ((importe == null) ? 0 : importe.hashCode());
		result = prime * result + (isAutomatica ? 1231 : 1237);
		result = prime * result + (isFromAgendaDestinatario ? 1231 : 1237);
		result = prime * result + (isInmediata ? 1231 : 1237);
		result = prime * result + ((isRioRio == null) ? 0 : isRioRio.hashCode());
		result = prime * result + ((mensaje == null) ? 0 : mensaje.hashCode());
		result = prime * result + ((mensajeEmail == null) ? 0 : mensajeEmail.hashCode());
		result = prime * result + ((moneda == null) ? 0 : moneda.hashCode());
		result = prime * result + ((monedaAltair == null) ? 0 : monedaAltair.hashCode());
		result = prime * result + ((monedasDisponibles == null) ? 0 : monedasDisponibles.hashCode());
		result = prime * result + ((nroCuenta == null) ? 0 : nroCuenta.hashCode());
		result = prime * result + ((nroCuentaDestino == null) ? 0 : nroCuentaDestino.hashCode());
		result = prime * result + (nuevaTransferencia ? 1231 : 1237);
		result = prime * result + ((numeroComprobante == null) ? 0 : numeroComprobante.hashCode());
		result = prime * result + ((saldoCuentaOrigen == null) ? 0 : saldoCuentaOrigen.hashCode());
		result = prime * result + ((tipoCuentaDescripcion == null) ? 0 : tipoCuentaDescripcion.hashCode());
		result = prime * result + ((tipoCuentaDestino == null) ? 0 : tipoCuentaDestino.hashCode());
		result = prime * result
				+ ((tipoCuentaDestinoDescripcion == null) ? 0 : tipoCuentaDestinoDescripcion.hashCode());
		result = prime * result + ((tipoCuentaEnum == null) ? 0 : tipoCuentaEnum.hashCode());
		result = prime * result + ((titular == null) ? 0 : titular.hashCode());
		result = prime * result + (transferenciaManual ? 1231 : 1237);
		return result;
	}

	public boolean isPedidoAumentoLimite() {
		return pedidoAumentoLimite;
	}

	public void setPedidoAumentoLimite(boolean pedidoAumentoLimite) {
		this.pedidoAumentoLimite = pedidoAumentoLimite;
	}

	public TransferenciaSumResponse getControlSum() {
		return controlSum;
	}

	public void setControlSum(TransferenciaSumResponse controlSum) {
		this.controlSum = controlSum;
	}

	public Integer getCantDiasUltimoCambioToken() {
		return cantDiasUltimoCambioToken;
	}

	public void setCantDiasUltimoCambioToken(Integer cantDiasUltimoCambioToken) {
		this.cantDiasUltimoCambioToken = cantDiasUltimoCambioToken;
	}

	public Integer getCantDiasUltimoCambioClave() {
		return cantDiasUltimoCambioClave;
	}

	public void setCantDiasUltimoCambioClave(Integer cantDiasUltimoCambioClave) {
		this.cantDiasUltimoCambioClave = cantDiasUltimoCambioClave;
	}

	public Boolean getRioRio() {
		return isRioRio;
	}

	public void setRioRio(Boolean rioRio) {
		isRioRio = rioRio;
	}

	public String getCuitCliente() {
		return cuitCliente;
	}

	public void setCuitCliente(String cuitCliente) {
		this.cuitCliente = cuitCliente;
	}

	public float getScoringDestinatario() {
		return scoringDestinatario;
	}

	public void setScoringDestinatario(float scoringDestinatario) {
		this.scoringDestinatario = scoringDestinatario;
	}

	public BiocatchResponseDataDTO getBiocatchResponseDataDTO() {
		return biocatchResponseDataDTO;
	}

	public void setBiocatchResponseDataDTO(BiocatchResponseDataDTO biocatchResponseDataDTO) {
		this.biocatchResponseDataDTO = biocatchResponseDataDTO;
	}

	public Integer getCantDiasUltimoCambioMail() {
		return cantDiasUltimoCambioMail;
	}

	public void setCantDiasUltimoCambioMail(Integer cantDiasUltimoCambioMail) {
		this.cantDiasUltimoCambioMail = cantDiasUltimoCambioMail;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AumentoLimiteTransferenciaInOutView other = (AumentoLimiteTransferenciaInOutView) obj;
		if (aliasDestino == null) {
			if (other.aliasDestino != null)
				return false;
		} else if (!aliasDestino.equals(other.aliasDestino))
			return false;
		if (fechaCreacionDestinatario != other.fechaCreacionDestinatario)
			return false;
		if (banco == null) {
			if (other.banco != null)
				return false;
		} else if (!banco.equals(other.banco))
			return false;
		if (cbu == null) {
			if (other.cbu != null)
				return false;
		} else if (!cbu.equals(other.cbu))
			return false;
		if (cbu2 == null) {
			if (other.cbu2 != null)
				return false;
		} else if (!cbu2.equals(other.cbu2))
			return false;
		if (concepto == null) {
			if (other.concepto != null)
				return false;
		} else if (!concepto.equals(other.concepto))
			return false;
		if (cuentaPropia != other.cuentaPropia)
			return false;
		if (cuentasView == null) {
			if (other.cuentasView != null)
				return false;
		} else if (!cuentasView.equals(other.cuentasView))
			return false;
		if (cuitCuil == null) {
			if (other.cuitCuil != null)
				return false;
		} else if (!cuitCuil.equals(other.cuitCuil))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (destinatarioNoVerificado != other.destinatarioNoVerificado)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enviaEmail == null) {
			if (other.enviaEmail != null)
				return false;
		} else if (!enviaEmail.equals(other.enviaEmail))
			return false;
		if (fechaAcreditacion == null) {
			if (other.fechaAcreditacion != null)
				return false;
		} else if (!fechaAcreditacion.equals(other.fechaAcreditacion))
			return false;
		if (fechaEjecucion == null) {
			if (other.fechaEjecucion != null)
				return false;
		} else if (!fechaEjecucion.equals(other.fechaEjecucion))
			return false;
		if (fechaOperacion == null) {
			if (other.fechaOperacion != null)
				return false;
		} else if (!fechaOperacion.equals(other.fechaOperacion))
			return false;
		if (importe == null) {
			if (other.importe != null)
				return false;
		} else if (!importe.equals(other.importe))
			return false;
		if (isAutomatica != other.isAutomatica)
			return false;
		if (isFromAgendaDestinatario != other.isFromAgendaDestinatario)
			return false;
		if (isInmediata != other.isInmediata)
			return false;
		if (isRioRio == null) {
			if (other.isRioRio != null)
				return false;
		} else if (!isRioRio.equals(other.isRioRio))
			return false;
		if (mensaje == null) {
			if (other.mensaje != null)
				return false;
		} else if (!mensaje.equals(other.mensaje))
			return false;
		if (mensajeEmail == null) {
			if (other.mensajeEmail != null)
				return false;
		} else if (!mensajeEmail.equals(other.mensajeEmail))
			return false;
		if (moneda == null) {
			if (other.moneda != null)
				return false;
		} else if (!moneda.equals(other.moneda))
			return false;
		if (monedaAltair == null) {
			if (other.monedaAltair != null)
				return false;
		} else if (!monedaAltair.equals(other.monedaAltair))
			return false;
		if (monedasDisponibles == null) {
			if (other.monedasDisponibles != null)
				return false;
		} else if (!monedasDisponibles.equals(other.monedasDisponibles))
			return false;
		if (nroCuenta == null) {
			if (other.nroCuenta != null)
				return false;
		} else if (!nroCuenta.equals(other.nroCuenta))
			return false;
		if (nroCuentaDestino == null) {
			if (other.nroCuentaDestino != null)
				return false;
		} else if (!nroCuentaDestino.equals(other.nroCuentaDestino))
			return false;
		if (nuevaTransferencia != other.nuevaTransferencia)
			return false;
		if (numeroComprobante == null) {
			if (other.numeroComprobante != null)
				return false;
		} else if (!numeroComprobante.equals(other.numeroComprobante))
			return false;
		if (saldoCuentaOrigen == null) {
			if (other.saldoCuentaOrigen != null)
				return false;
		} else if (!saldoCuentaOrigen.equals(other.saldoCuentaOrigen))
			return false;
		if (tipoCuentaDescripcion == null) {
			if (other.tipoCuentaDescripcion != null)
				return false;
		} else if (!tipoCuentaDescripcion.equals(other.tipoCuentaDescripcion))
			return false;
		if (tipoCuentaDestino == null) {
			if (other.tipoCuentaDestino != null)
				return false;
		} else if (!tipoCuentaDestino.equals(other.tipoCuentaDestino))
			return false;
		if (tipoCuentaDestinoDescripcion == null) {
			if (other.tipoCuentaDestinoDescripcion != null)
				return false;
		} else if (!tipoCuentaDestinoDescripcion.equals(other.tipoCuentaDestinoDescripcion))
			return false;
		if (tipoCuentaEnum != other.tipoCuentaEnum)
			return false;
		if (titular == null) {
			if (other.titular != null)
				return false;
		} else if (!titular.equals(other.titular))
			return false;
		if (transferenciaManual != other.transferenciaManual)
			return false;
		if(cuitCliente != other.cuitCliente)
			 return Boolean.FALSE;

		return true;
	}

	public TISFechasHabilitadasResponse getTisFechasHabilitadasResponse() {
		return tisFechasHabilitadasResponse;
	}

	public void setTisFechasHabilitadasResponse(TISFechasHabilitadasResponse tisFechasHabilitadasResponse) {
		this.tisFechasHabilitadasResponse = tisFechasHabilitadasResponse;
	}

	/*
	 * (non-Javadoc)A
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AumentoLimiteTransferenciaInOutView [cbu=" + cbu + ", cbu2=" + cbu2 + ", titular=" + titular
				+ ", banco=" + banco + ", cuitCuil=" + cuitCuil + ", importe=" + importe + ", concepto=" + concepto
				+ ", descripcion=" + descripcion + ", moneda=" + moneda + ", nroCuenta=" + nroCuenta
				+ ", nroCuentaDestino=" + nroCuentaDestino + ", fechaEjecucion=" + fechaEjecucion
				+ ", fechaAcreditacion=" + fechaAcreditacion + ", enviaEmail=" + enviaEmail + ", email=" + email
				+ ", mensajeEmail=" + mensajeEmail + ", cuentasView=" + cuentasView + ", numeroComprobante="
				+ numeroComprobante + ", tipoCuentaDestino=" + tipoCuentaDestino + ", mensaje="
				+ mensaje + ", aliasDestino=" + aliasDestino + ", cuentaPropia=" + cuentaPropia + ", isRioRio="
				+ isRioRio + ", fechaOperacion=" + fechaOperacion + ", antiguedad=" + fechaCreacionDestinatario
				+", monedasDisponibles=" + monedasDisponibles
				+ ", tipoCuentaDescripcion=" + tipoCuentaDescripcion + ", tipoCuentaDestinoDescripcion="
				+ tipoCuentaDestinoDescripcion + ", transferenciaManual=" + transferenciaManual
				+ ", destinatarioNoVerificado=" + destinatarioNoVerificado + ", isFromAgendaDestinatario="
				+ isFromAgendaDestinatario + ", isAutomatica=" + isAutomatica + ", isInmediata=" + isInmediata
				+ ", saldoCuentaOrigen=" + saldoCuentaOrigen + ", tipoCuentaEnum=" + tipoCuentaEnum + ", monedaAltair="
				+ monedaAltair + ", nuevaTransferencia=" + nuevaTransferencia + "cuitDestino=" + cuitCliente + "]";
	}

}

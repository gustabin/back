/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.servicios.comun.web.view.AyudaView;

/**
 * Clase representativa del inicio de transferencias.
 *
 * @author B039543
 */
@XmlRootElement(name = "transferenciaAgendadaDetalleView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferenciaAgendadaDetalleView extends TransferenciaAgendadaView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1868919582670157569L;

	/** The descripcion. */
	private String descripcion;

	/** The abreviatura. */
	private String abreviatura;

	/** The origen numero. */
	private String origenNumero;

	/** The origen tipo. */
	private String origenTipo;

	/** The origen saldo. */
	private String origenSaldo;

	/** The alias origen. */
	private String aliasOrigen;

	/** The destino numero. */
	private String destinoNumero;

	/** The destino tipo. */
	private String destinoTipo;

	/** The alias destino. */
	private String aliasDestino;

	/** The concepto. */
	private ConceptoView concepto;

	/** The plazo acreditacion. */
	private String plazoAcreditacion;

	/** The email activo. */
	private Boolean emailActivo;

	/** The fecha inicio. */
	private String fechaInicio;

	/** The numero cuil cuit. */
	private String numeroCuilCuit;

	/** The cbu. */
	private String cbu;

	/** The id proceso. */
	private String idProceso;

	/** The origen nombre titular. */
	private String origenNombreTitular;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The id transaccion. */
	private String idTransaccion;

	/** The fecha comprobante. */
	private String fechaComprobante;

	/** The is pesos. */
	private boolean isPesos;

	/** The frecuencia de recurrencia codigo. */
	private String frecuenciaCodigo;

	/** The concepto codigo. */
	private String conceptoCodigo;

	/** The frecuencias. */
	private List<FrecuenciaTransferenciaAgendadaView> frecuencias;

	/** The conceptos. */
	private List<ConceptoTransferenciaAgendadaView> conceptos;

	/** The ayuda view. */
	@JsonIgnore
	private List<AyudaView> mensajesAyuda = new ArrayList<AyudaView>();

	/** The fecha creacion de Destinatario en la agenda. Se usa en RSA. */
	private String fechaCreacionDestinatario = null;
	
    private BigDecimal limiteCuentasPropiasPesos;
    
    private BigDecimal limiteCuentasPropiasDolares;
    
    private BigDecimal limiteTercerosPesos;
    
    private BigDecimal limiteTercerosDolares;
    
    private BigDecimal limiteOtrosBancosPesos;
    
    private BigDecimal limiteOtrosBancosDolares;
    
    private String mensajeLimiteDiario;

	@JsonIgnore
	private BiocatchResponseDataDTO biocatchRsaData;

    /** Se usa en RSA. */
    @JsonIgnore
    private Integer cantDiasUltimoCambioClave;
    
    /** Se usa en RSA. */
    @JsonIgnore
    private Integer cantDiasUltimoCambioToken;

	private boolean pif = false;

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
	 * Gets the email activo.
	 *
	 * @return the email activo
	 */
	public Boolean isEmailActivo() {
		return emailActivo;
	}

	/**
	 * Sets the email activo.
	 *
	 * @param emailActivo
	 *            the new email activo
	 */
	public void setEmailActivo(Boolean emailActivo) {
		this.emailActivo = emailActivo;
	}

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
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
	 * Gets the origen numero.
	 *
	 * @return the origenNumero
	 */
	public String getOrigenNumero() {
		return origenNumero;
	}

	/**
	 * Sets the origen numero.
	 *
	 * @param origenNumero
	 *            the origenNumero to set
	 */
	public void setOrigenNumero(String origenNumero) {
		this.origenNumero = origenNumero;
	}

	/**
	 * Gets the origen tipo.
	 *
	 * @return the origenTipo
	 */
	public String getOrigenTipo() {
		return origenTipo;
	}

	/**
	 * Sets the origen tipo.
	 *
	 * @param origenTipo
	 *            the origenTipo to set
	 */
	public void setOrigenTipo(String origenTipo) {
		this.origenTipo = origenTipo;
	}

	/**
	 * Gets the destino numero.
	 *
	 * @return the destinoNumero
	 */
	public String getDestinoNumero() {
		return destinoNumero;
	}

	/**
	 * Sets the destino numero.
	 *
	 * @param destinoNumero
	 *            the destinoNumero to set
	 */
	public void setDestinoNumero(String destinoNumero) {
		this.destinoNumero = destinoNumero;
	}

	/**
	 * Gets the destino tipo.
	 *
	 * @return the destinoTipo
	 */
	public String getDestinoTipo() {
		return destinoTipo;
	}

	/**
	 * Sets the destino tipo.
	 *
	 * @param destinoTipo
	 *            the destinoTipo to set
	 */
	public void setDestinoTipo(String destinoTipo) {
		this.destinoTipo = destinoTipo;
	}

	/**
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
	public ConceptoView getConcepto() {
		return concepto;
	}

	/**
	 * Sets the concepto.
	 *
	 * @param concepto
	 *            the concepto to set
	 */
	public void setConcepto(ConceptoView concepto) {
		this.concepto = concepto;
	}

	/**
	 * Gets the plazo acreditacion.
	 *
	 * @return the plazoAcreditacion
	 */
	public String getPlazoAcreditacion() {
		return plazoAcreditacion;
	}

	/**
	 * Sets the plazo acreditacion.
	 *
	 * @param plazoAcreditacion
	 *            the plazoAcreditacion to set
	 */
	public void setPlazoAcreditacion(String plazoAcreditacion) {
		this.plazoAcreditacion = plazoAcreditacion;
	}

	/**
	 * Gets the abreviatura.
	 *
	 * @return the abreviatura
	 */
	public String getAbreviatura() {
		return abreviatura;
	}

	/**
	 * Sets the abreviatura.
	 *
	 * @param abreviatura
	 *            the new abreviatura
	 */
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	/**
	 * Gets the numero cuil cuit.
	 *
	 * @return the numero cuil cuit
	 */
	public String getNumeroCuilCuit() {
		return numeroCuilCuit;
	}

	/**
	 * Sets the numero cuil cuit.
	 *
	 * @param numeroCuilCuit
	 *            the new numero cuil cuit
	 */
	public void setNumeroCuilCuit(String numeroCuilCuit) {
		this.numeroCuilCuit = numeroCuilCuit;
	}

	/**
	 * Gets the alias origen.
	 *
	 * @return the alias origen
	 */
	public String getAliasOrigen() {
		return aliasOrigen;
	}

	/**
	 * Sets the alias origen.
	 *
	 * @param aliasOrigen
	 *            the new alias origen
	 */
	public void setAliasOrigen(String aliasOrigen) {
		this.aliasOrigen = aliasOrigen;
	}

	/**
	 * Gets the alias destino.
	 *
	 * @return the alias destino
	 */
	public String getAliasDestino() {
		return aliasDestino;
	}

	/**
	 * Sets the alias destino.
	 *
	 * @param aliasDestino
	 *            the new alias destino
	 */
	public void setAliasDestino(String aliasDestino) {
		this.aliasDestino = aliasDestino;
	}

	/**
	 * Gets the id proceso.
	 *
	 * @return the id proceso
	 */
	public String getIdProceso() {
		return idProceso;
	}

	/**
	 * Sets the id proceso.
	 *
	 * @param idProceso
	 *            the new id proceso
	 */
	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}

	/**
	 * Gets the origen saldo.
	 *
	 * @return the origen saldo
	 */
	public String getOrigenSaldo() {
		return origenSaldo;
	}

	/**
	 * Sets the origen saldo.
	 *
	 * @param origenSaldo
	 *            the new origen saldo
	 */
	public void setOrigenSaldo(String origenSaldo) {
		this.origenSaldo = origenSaldo;
	}

	/**
	 * Gets the origen nombre titular.
	 *
	 * @return the origen nombre titular
	 */
	public String getOrigenNombreTitular() {
		return origenNombreTitular;
	}

	/**
	 * Sets the origen nombre titular. Se utiliza cuando cargamos los datos para
	 * enviar a rsa.
	 *
	 * @param origenNombreTitular
	 *            the new origen nombre titular
	 */
	public void setOrigenNombreTitular(String origenNombreTitular) {
		this.origenNombreTitular = origenNombreTitular;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numero comprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the new numero comprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the id transaccion.
	 *
	 * @return the id transaccion
	 */
	public String getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Sets the id transaccion.
	 *
	 * @param idTransaccion
	 *            the new id transaccion
	 */
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	/**
	 * Gets the fecha comprobante.
	 *
	 * @return the fecha comprobante
	 */
	public String getFechaComprobante() {
		return fechaComprobante;
	}

	/**
	 * Sets the fecha comprobante.
	 *
	 * @param fechaEjecucion
	 *            the new fecha comprobante
	 */
	public void setFechaComprobante(String fechaEjecucion) {
		this.fechaComprobante = fechaEjecucion;
	}

	/**
	 * Gets the email activo.
	 *
	 * @return the email activo
	 */
	public Boolean getEmailActivo() {
		return emailActivo;
	}

	/**
	 * Gets the frecuencia codigo.
	 *
	 * @return the frecuenciaCodigo
	 */
	public String getFrecuenciaCodigo() {
		return frecuenciaCodigo;
	}

	/**
	 * Sets the frecuencia codigo.
	 *
	 * @param frecuenciaCodigo
	 *            the frecuenciaCodigo to set
	 */
	public void setFrecuenciaCodigo(String frecuenciaCodigo) {
		this.frecuenciaCodigo = frecuenciaCodigo;
	}

	/**
	 * Gets the concepto codigo.
	 *
	 * @return the conceptoCodigo
	 */
	public String getConceptoCodigo() {
		return conceptoCodigo;
	}

	/**
	 * Sets the concepto codigo.
	 *
	 * @param conceptoCodigo
	 *            the conceptoCodigo to set
	 */
	public void setConceptoCodigo(String conceptoCodigo) {
		this.conceptoCodigo = conceptoCodigo;
	}

	/**
	 * Gets the frecuencias.
	 *
	 * @return the frecuencias
	 */
	public List<FrecuenciaTransferenciaAgendadaView> getFrecuencias() {
		return frecuencias;
	}

	/**
	 * Sets the frecuencias.
	 *
	 * @param frecuencias
	 *            the frecuencias to set
	 */
	public void setFrecuencias(List<FrecuenciaTransferenciaAgendadaView> frecuencias) {
		this.frecuencias = frecuencias;
	}

	/**
	 * Gets the conceptos.
	 *
	 * @return the conceptos
	 */
	public List<ConceptoTransferenciaAgendadaView> getConceptos() {
		return conceptos;
	}

	/**
	 * Sets the conceptos.
	 *
	 * @param conceptos
	 *            the conceptos to set
	 */
	public void setConceptos(List<ConceptoTransferenciaAgendadaView> conceptos) {
		this.conceptos = conceptos;
	}

	/**
	 * Checks if is pesos.
	 *
	 * @return the isPesos
	 */
	public boolean isPesos() {
		return isPesos;
	}

	/**
	 * Sets the checks if is pesos.
	 *
	 * @param isPesos
	 *            the isPesos to set
	 */
	public void setPesos(boolean isPesos) {
		this.isPesos = isPesos;
	}

	/**
	 * Gets the mensajes ayuda.
	 *
	 * @return the mensajesAyuda
	 */
	public List<AyudaView> getMensajesAyuda() {
		return mensajesAyuda;
	}

	/**
	 * Sets the mensajes ayuda.
	 *
	 * @param mensajesAyuda
	 *            the mensajesAyuda to set
	 */
	public void setMensajesAyuda(List<AyudaView> mensajesAyuda) {
		this.mensajesAyuda = mensajesAyuda;
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

	public BigDecimal getLimiteCuentasPropiasPesos() {
		return limiteCuentasPropiasPesos;
	}

	public void setLimiteCuentasPropiasPesos(BigDecimal limiteCuentasPropiasPesos) {
		this.limiteCuentasPropiasPesos = limiteCuentasPropiasPesos;
	}

	public BigDecimal getLimiteCuentasPropiasDolares() {
		return limiteCuentasPropiasDolares;
	}

	public void setLimiteCuentasPropiasDolares(BigDecimal limiteCuentasPropiasDolares) {
		this.limiteCuentasPropiasDolares = limiteCuentasPropiasDolares;
	}

	public BigDecimal getLimiteTercerosPesos() {
		return limiteTercerosPesos;
	}

	public void setLimiteTercerosPesos(BigDecimal limiteTercerosPesos) {
		this.limiteTercerosPesos = limiteTercerosPesos;
	}

	public BigDecimal getLimiteTercerosDolares() {
		return limiteTercerosDolares;
	}

	public void setLimiteTercerosDolares(BigDecimal limiteTercerosDolares) {
		this.limiteTercerosDolares = limiteTercerosDolares;
	}

	public BigDecimal getLimiteOtrosBancosPesos() {
		return limiteOtrosBancosPesos;
	}

	public void setLimiteOtrosBancosPesos(BigDecimal limiteOtrosBancosPesos) {
		this.limiteOtrosBancosPesos = limiteOtrosBancosPesos;
	}

	public BigDecimal getLimiteOtrosBancosDolares() {
		return limiteOtrosBancosDolares;
	}

	public void setLimiteOtrosBancosDolares(BigDecimal limiteOtrosBancosDolares) {
		this.limiteOtrosBancosDolares = limiteOtrosBancosDolares;
	}

	public String getMensajeLimiteDiario() {
		return mensajeLimiteDiario;
	}

	public void setMensajeLimiteDiario(String mensajeLimiteDiario) {
		this.mensajeLimiteDiario = mensajeLimiteDiario;
	}

	public Integer getCantDiasUltimoCambioClave() {
		return cantDiasUltimoCambioClave;
	}

	public void setCantDiasUltimoCambioClave(Integer cantDiasUltimoCambioClave) {
		this.cantDiasUltimoCambioClave = cantDiasUltimoCambioClave;
	}

	public Integer getCantDiasUltimoCambioToken() {
		return cantDiasUltimoCambioToken;
	}

	public void setCantDiasUltimoCambioToken(Integer cantDiasUltimoCambioToken) {
		this.cantDiasUltimoCambioToken = cantDiasUltimoCambioToken;
	}

	public boolean isPif() {
		return pif;
	}

	public void setPif(boolean pif) {
		this.pif = pif;
	}

	public BiocatchResponseDataDTO getBiocatchRsaData() {
		return biocatchRsaData;
	}

	public void setBiocatchRsaData(BiocatchResponseDataDTO biocatchRsaData) {
		this.biocatchRsaData = biocatchRsaData;
	}
}
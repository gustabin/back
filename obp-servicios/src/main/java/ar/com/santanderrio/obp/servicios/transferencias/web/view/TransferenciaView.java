/**
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
import ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto.TransferenciaSumResponse;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.codehaus.jackson.annotate.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.view.EstadisticaView;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.web.view.AyudaView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;

/**
 * The Class TransferenciaView.
 *
 * @author B039636
 */
@XmlRootElement(name = "transferenciaView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferenciaView extends RsaDTO implements EstadisticaView {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5788745262046640280L;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaView.class);

    /** The cbu. */
    private String cbu;

    /** The cbu 2. */
    private String cbu2;

    /** The titular, nombre titular destino. */
    private String titular;

    /** The titular origen. */
    private String titularOrigen;

    /** The banco. */
    private String banco;

    /** The cuit. */
    private String cuitCuil;

    /** The importe. */
    private String importe;

    /** The concepto. */
    private ConceptoView concepto;

    /** The descripcion. */
    private String descripcion;

    /** The moneda. */
    private String moneda;

    /** The nro cuenta. */
    private String nroCuenta;

    /** The nro cuenta destino. */
    private String nroCuentaDestino = "";

    /**
     * seleccionada por el usuario en la vista Determina si es Programada o
     * Inmediata.
     */
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

    /** The concepto transferencia. */
    private List<ConceptoView> conceptoTransferencia;

    /** The frecuencia. */
    private String frecuencia;

    /** The numero comprobante. */
    private String numeroComprobante = "";

    /** The tipo cuenta destino. */
    private String tipoCuentaDestino = "";

    /** The desafio. */
    @JsonManagedReference
    private AutentificacionDTO desafio;

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

    /** The error banelco. Se usa en RSA. */
    private boolean errorBanelco = false;

    /** The celular my A. Se usa en RSA. */
    private boolean celularMyA = false;

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
    private boolean isInmediata = false;

    /** Se usa en RSA. */
    @JsonIgnore
    private String saldoCuentaOrigen;

    /** Se usa en RSA. */
    private TipoCuenta tipoCuentaEnum;

    /** Se usa en RSA. */
    @JsonIgnore
    private String monedaAltair;
    
    /** Se usa en RSA. */
    @JsonIgnore
    private Integer cantDiasUltimoCambioClave;
    
    /** Se usa en RSA. */
    @JsonIgnore
    private Integer cantDiasUltimoCambioToken;

    @JsonIgnore
    private float scoringDestinatario;

    /** The ayuda view. */
    private List<AyudaView> mensajesAyuda = new ArrayList<AyudaView>();

    /** The error con alias. */
    private boolean errorConAlias = false;

    /** The warning con alias. */
    private boolean warningConAlias = false;
    

    /**
     * Esta variable se usa para almacenar el error de 48 HORAS posterior a la carga
     * manual (COELSA) a traves de nuevo destinatario y poder mostrar el warning 48
     * hs en la confirmacion. En esta casuistica el front envia el error para
     * mantenerlo en el request/response del boton continuar @author jonatan.briceño
     */
    private String mensaje48Horas;

    /** The fecha creacion de Destinatario en la agenda. Se usa en RSA. */
    private String fechaCreacionDestinatario = null;

    /** The nro operacion programada. */
    private String nroOperacionProgramada;

    /** The referencia apodo. */
    private String referenciaApodo;

    /** The primer ingreso de agenda. */
    private boolean primerIngresoDeAgenda = false;

    /** The cuenta migrada. */
    private boolean isCuentaMigrada = false;

    /** The nro cuenta destino migrada. */
    private String nroCuentaDestinoMigrada = "";

    /** The legal concepto. */
    private String legalConcepto;

    private boolean esCuil;
    
    private BigDecimal limiteCuentasPropiasPesos;
    
    private BigDecimal limiteCuentasPropiasDolares;
    
    private BigDecimal limiteTercerosPesos;
    
    private BigDecimal limiteTercerosDolares;
    
    private BigDecimal limiteOtrosBancosPesos;
    
    private BigDecimal limiteOtrosBancosDolares;
    
    private String mensajeLimiteDiario;

    private TransferenciaSumResponse controlSum;

    private String cuitCliente;

    @JsonIgnore
    private boolean pif = false;

    private boolean showMsgMEP = false;

    private boolean isChecked;

    private String performanceFund;

    private boolean accountTitle = false;

    private boolean showMsgFund = false;

    private boolean isSegmentBP = false;
    @JsonIgnore
    private String nupCliente;

    @JsonIgnore
    private BiocatchResponseDataDTO biocatchResponseData;

    /**
     * Instantiates a new transferencia.
     * 
     * @see {@link RsaDTO}
     */
    public TransferenciaView() {
        super(OperacionesRSAEnum.TRANSFERENCIA);

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
     * Gets the concepto transferencia.
     *
     * @return the conceptoTransferencia
     */
    public List<ConceptoView> getConceptoTransferencia() {
        return conceptoTransferencia;
    }

    /**
     * Sets the concepto transferencia.
     *
     * @param conceptoTransferencia
     *            the conceptoTransferencia to set
     */
    public void setConceptoTransferencia(List<ConceptoView> conceptoTransferencia) {
        this.conceptoTransferencia = conceptoTransferencia;
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
     *            the new frecuencia
     */
    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
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

    /**
     * Checks if is error banelco.
     *
     * @return true, if is error banelco
     */
    public boolean isErrorBanelco() {
        return errorBanelco;
    }

    /**
     * Sets the error banelco.
     *
     * @param errorBanelco
     *            the new error banelco
     */
    public void setErrorBanelco(boolean errorBanelco) {
        this.errorBanelco = errorBanelco;
    }

    /**
     * Checks if is celular my A.
     *
     * @return true, if is celular my A
     */
    public boolean isCelularMyA() {
        return celularMyA;
    }

    /**
     * Sets the celular my A.
     *
     * @param celularMyA
     *            the new celular my A
     */
    public void setCelularMyA(boolean celularMyA) {
        this.celularMyA = celularMyA;
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

    /**
     * Checks if is error con alias.
     *
     * @return true, if is error con alias
     */
    public boolean isErrorConAlias() {
        return errorConAlias;
    }

    /**
     * Sets the error con alias.
     *
     * @param errorConAlias
     *            the new error con alias
     */
    public void setErrorConAlias(boolean errorConAlias) {
        this.errorConAlias = errorConAlias;
    }

    /**
     * Gets the nro operacion programada.
     *
     * @return the nro operacion programada
     */
    public String getNroOperacionProgramada() {
        return this.nroOperacionProgramada;
    }

    /**
     * Sets the nro operacion programada.
     *
     * @param nroOperacionProgramada
     *            the nroOperacionProgramada to set
     */
    public void setNroOperacionProgramada(String nroOperacionProgramada) {
        this.nroOperacionProgramada = nroOperacionProgramada;
    }

    /**
     * Gets the referencia apodo.
     *
     * @return the referencia apodo
     */
    public String getReferenciaApodo() {
        return referenciaApodo;
    }

    /**
     * Sets the referencia apodo.
     *
     * @param referenciaApodo
     *            the new referencia apodo
     */
    public void setReferenciaApodo(String referenciaApodo) {
        this.referenciaApodo = referenciaApodo;
    }

    /**
     * Gets the mensaje 48 horas.
     *
     * @return the mensaje 48 horas
     */
    public String getMensaje48Horas() {
        return mensaje48Horas;
    }

    /**
     * Sets the mensaje 48 horas.
     *
     * @param mensaje48Horas
     *            the new mensaje 48 horas
     */
    public void setMensaje48Horas(String mensaje48Horas) {
        this.mensaje48Horas = mensaje48Horas;
    }

    /**
     * Gets the titular origen.
     *
     * @return the titularOrigen
     */
    public String getTitularOrigen() {
        return titularOrigen;
    }

    /**
     * Sets the titular origen.
     *
     * @param titularOrigen
     *            the titularOrigen to set
     */
    public void setTitularOrigen(String titularOrigen) {
        this.titularOrigen = titularOrigen;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        TransferenciaView other = (TransferenciaView) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(aliasDestino, other.getAliasDestino());
        eb.append(banco, other.getBanco());
        eb.append(cbu, other.getCbu());
        eb.append(cbu2, other.getCbu2());
        eb.append(concepto, other.getConcepto());
        eb.append(conceptoTransferencia, other.getConceptoTransferencia());
        eb.append(cuentaPropia, other.isCuentaPropia());
        eb.append(cuentasView, other.getCuentasView());
        eb.append(cuitCuil, other.getCuitCuil());
        eb.append(desafio, other.getDesafio());
        eb.append(descripcion, other.getDescripcion());
        eb.append(destinatarioNoVerificado, other.isDestinatarioNoVerificado());
        eb.append(email, other.getEmail());
        eb.append(enviaEmail, other.getEnviaEmail());
        eb.append(moneda, other.getMoneda());
        eb.append(fechaAcreditacion, other.getFechaAcreditacion());
        eb.append(fechaEjecucion, other.getFechaAcreditacion());
        eb.append(fechaOperacion, other.getFechaOperacion());
        eb.append(importe, other.getImporte());
        eb.append(isInmediata, other.isInmediata());
        eb.append(isRioRio, other.getIsRioRio());
        eb.append(mensaje, other.getMensaje());
        eb.append(mensajeEmail, other.mensajeEmail);
        eb.append(monedasDisponibles, other.getMonedasDisponibles());
        eb.append(nroCuenta, other.getNroCuenta());
        eb.append(nroCuentaDestino, other.getNroCuentaDestino());
        eb.append(numeroComprobante, other.getNumeroComprobante());
        eb.append(tipoCuentaDescripcion, other.getTipoCuentaDescripcion());
        eb.append(tipoCuentaDestino, other.getTipoCuentaDestino());
        eb.append(tipoCuentaDestinoDescripcion, other.getTipoCuentaDescripcion());
        eb.append(titular, other.getTitular());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.estadistica.web.view.
     * EstadisticaView#cargarEstadistica()
     */
    @Override
    public Estadistica cargarEstadistica() {
        LOGGER.info("Grabando estadisicas: ", EstadisticasConstants.ESTADISTICAS_COMPROBANTE_NUEVA_TRANSFERENCIA);
        return new Estadistica(EstadisticasConstants.ESTADISTICAS_COMPROBANTE_NUEVA_TRANSFERENCIA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Checks if is warning con alias.
     *
     * @return true, if is warning con alias
     */
    public boolean isWarningConAlias() {
        return warningConAlias;
    }

    /**
     * Sets the warning con alias.
     *
     * @param warningConAlias
     *            the new warning con alias
     */
    public void setWarningConAlias(boolean warningConAlias) {
        this.warningConAlias = warningConAlias;
    }

    /**
     * Checks if is primer ingreso de agenda.
     *
     * @return true, if is primer ingreso de agenda
     */
    public boolean isPrimerIngresoDeAgenda() {
        return primerIngresoDeAgenda;
    }

    /**
     * Sets the primer ingreso de agenda.
     *
     * @param primerIngresoDeAgenda
     *            the new primer ingreso de agenda
     */
    public void setPrimerIngresoDeAgenda(boolean primerIngresoDeAgenda) {
        this.primerIngresoDeAgenda = primerIngresoDeAgenda;
    }

    /**
     * Gets the checks if is cuenta migrada.
     *
     * @return the checks if is cuenta migrada
     */
    public boolean getIsCuentaMigrada() {
        return isCuentaMigrada;
    }

    /**
     * Sets the checks if is cuenta migrada.
     *
     * @param isCuentaMigrada
     *            the new checks if is cuenta migrada
     */
    public void setIsCuentaMigrada(boolean isCuentaMigrada) {
        this.isCuentaMigrada = isCuentaMigrada;
    }

    /**
     * Gets the nro cuenta destino migrada.
     *
     * @return the nro cuenta destino migrada
     */
    public String getNroCuentaDestinoMigrada() {
        return nroCuentaDestinoMigrada;
    }

    /**
     * Sets the nro cuenta destino migrada.
     *
     * @param nroCuentaDestinoMigrada
     *            the new nro cuenta destino migrada
     */
    public void setNroCuentaDestinoMigrada(String nroCuentaDestinoMigrada) {
        this.nroCuentaDestinoMigrada = nroCuentaDestinoMigrada;
    }

    /**
     * Gets the legal concepto.
     *
     * @return the legalConcepto
     */
    public String getLegalConcepto() {
        return legalConcepto;
    }

    /**
     * Sets the legal concepto.
     *
     * @param legalConcepto
     *            the legalConcepto to set
     */
    public void setLegalConcepto(String legalConcepto) {
        this.legalConcepto = legalConcepto;
    }

    public boolean isEsCuil() {
        return esCuil;
    }

    public void setEsCuil(boolean esCuil) {
        this.esCuil = esCuil;
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

	public String getMensajeLimiteDiario() {
		return mensajeLimiteDiario;
	}

	public void setMensajeLimiteDiario(String mensajeLimiteDiario) {
		this.mensajeLimiteDiario = mensajeLimiteDiario;
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

    public TransferenciaSumResponse getControlSum() {
        return controlSum;
    }

    public void setControlSum(TransferenciaSumResponse controlSum) {
        this.controlSum = controlSum;
    }

    public String getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(String cuitCliente) {
        this.cuitCliente = cuitCliente;
    }

    public BiocatchResponseDataDTO getBiocatchResponseData() {
        return biocatchResponseData;
    }

    public void setBiocatchResponseData(BiocatchResponseDataDTO biocatchResponseData) {
        this.biocatchResponseData = biocatchResponseData;
    }

    public float getScoringDestinatario() {
        return scoringDestinatario;
    }

    public void setScoringDestinatario(float scoringDestinatario) {
        this.scoringDestinatario = scoringDestinatario;
    }

    public boolean isPif() {
        return pif;
    }

    public void setPif(boolean pif) {
        this.pif = pif;
    }

    public boolean isShowMsgMEP() {
        return showMsgMEP;
    }

    public void setShowMsgMEP(boolean showMsgMEP) {
        this.showMsgMEP = showMsgMEP;
    }

    public String getPerformanceFund() {
        return performanceFund;
    }

    public void setPerformanceFund(String performanceFund) {
        this.performanceFund = performanceFund;
    }

    public boolean isAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(boolean accountTitle) {
        this.accountTitle = accountTitle;
    }

    public boolean isShowMsgFund() {
        return showMsgFund;
    }

    public void setShowMsgFund(boolean showMsgFund) {
        this.showMsgFund = showMsgFund;
    }
    public boolean isSaveContact() {
        return isChecked;
    }
    public void setSaveContact(boolean isChecked) { this.isChecked = isChecked; }

    public boolean isSegmentBP() {
        return isSegmentBP;
    }

    public void setSegmentBP(boolean segmentBP) {
        isSegmentBP = segmentBP;
    }
    public boolean isMoneyTypeUSD(){
        return this.moneda.equalsIgnoreCase("dolar");
    }

    public String getNupCliente() {
        return nupCliente;
    }

    public void setNupCliente(String nupCliente) {
        this.nupCliente = nupCliente;
    }

    public boolean isBcaPrivada(){
        return this.nroCuenta.startsWith("250");
    }
}
/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.DetalleError;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.FrecuenciaTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class Transferencia.
 *
 * @author B039636
 */
public class TransferenciaDTO {

	/** The id. */
	private Long id;

	/** The Constant LAS_CUENTA_DESTINO_ES_NULA_O_VACIA. */
	private static final String LAS_CUENTA_DESTINO_ES_NULA_O_VACIA = "Las cuenta destino es nula o vacia";

	/** The Constant LA_TRANSFERENCIA_ES_HACIA_EL_MISMO_BANCO_RIO_RIO. */
	private static final String LA_TRANSFERENCIA_ES_HACIA_EL_MISMO_BANCO_RIO_RIO = "La transferencia es hacia el mismo banco, RIO/RIO";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaDTO.class);

	/** The Constant MONEDA_PESO. */
	private static final String MONEDA_PESO = "pesos";

	/** The Constant MAX_NUMERO_ENTIDAD. */
	private static final int MAX_NUMERO_ENTIDAD = 3;

	/** The Constant SUCURSAL_INICIO. */
	private static final int SUCURSAL_INICIO = 3;

	/** The Constant SUCURSA_FIN. */
	private static final int SUCURSA_FIN = 7;

	/** The Constant DIGITO_INICIO. */
	private static final int DIGITO_INICIO = 7;

	/** The Constant DIGITO_FIN. */
	private static final int DIGITO_FIN = 8;

	/** The Constant CUENTA_INICIO. */
	private static final int CUENTA_INICIO = 8;

	/** The Constant CUENTA_FIN. */
	private static final int CUENTA_FIN = 22;

	/** The Constant CONST_S. */
	private static final String CONST_S = "S";

	/** The Constant CONST_N. */
	private static final String CONST_N = "N";

	/** The Constant TIPO_TRANSFERENCIA_DEFAULT. */
	private static final byte TIPO_TRANSFERENCIA_DEFAULT = 3;
	/** The Constant PESOS. */
	private static final String PESOS = "Pesos";
	/** The Constant DOLARES. */
	private static final String DOLARES = "Dolares";

	/** Es el numero del comprobante. */
	private String idRecibo;

	/** The id transaccion. */
	private String idTransaccion;

	/** The fecha compensacion. */
	private String fechaCompensacion;

	/** The tipo transferencia. */
	private byte tipoTransferencia = TIPO_TRANSFERENCIA_DEFAULT;

	/** The importe. */
	private BigDecimal importe;

	/** The moneda. */
	private DivisaEnum moneda;

	/** The informacion discrecional. */
	private String informacionDiscrecional;

	/** The marca limite. */
	private String marcaLimite = "S";

	/** The caracteristicas cuenta credito. */
	private CaracteristicasCuentaCredito caracteristicasCuentaCredito = CaracteristicasCuentaCredito.INMEDIATO;

	/** The plazo acreditacion. */
	private PlazoAcreditacion plazoAcreditacion = PlazoAcreditacion.INMEDIATO;

	/** The ip. */
	private String ip;

	/** The titular. */
	private String titular;

	/** The cuit. */
	private String cuit;

	/** The cuit 2. */
	private String cuit2;

	/** The cuit 3. */
	private String cuit3;

	/** The va por coelsa. */
	private boolean vaPorCoelsa;

	/** The cbu. */
	private String cbu = "";

	/** The cuenta origen. */
	private Cuenta cuentaOrigen;

	/** The longitud cuenta destino banelco. */
	private String longitudCuentaDestinoBanelco;

	/** The numero cuenta destino. */
	private IdentificacionCuenta numeroCuentaDestino = new IdentificacionCuenta();

	/** The tipo cuenta destino. */
	private TipoCuenta tipoCuentaDestino;

	/** The cuenta destino banelco. */
	private String cuentaDestinoBanelco;

	/** The tipo de cuenta to banelco. */
	private TipoCuentaBanelco tipoDeCuentaToBanelco;

	/** The tipo de cuenta from banelco. */
	private TipoCuentaBanelco tipoDeCuentaFromBanelco;

	/** The email. */
	private String email;

	/** The email mensaje. */
	private String emailMensaje = "";

	/** The comentario. */
	private String comentario;

	/** The concepto adicional. */
	private String conceptoAdicional;

	/** The descripcion adicional. */
	private String descripcionAdicional;

	/** The concepto adicional 2. */
	private String conceptoAdicional2;

	/** The descripcion adicional 2. */
	private String descripcionAdicional2;

	/** The concepto adicional 3. */
	private String conceptoAdicional3;

	/** The descripcion adicional 3. */
	private String descripcionAdicional3;

	/** The concepto. */
	private ConceptoTransferenciaEnum concepto;

	/** The informacion adicional. */
	private String informacionAdicional;

	/** The fiid. */
	private String fiid;

	/** The user. */
	private String user;

	/** The banco receptor. */
	private String bancoReceptor;

	/** The codigo concepto. */
	private String codigoConcepto;

	/** The banco destino. */
	private String bancoDestino;

	/** The agenda. */
	private String agenda = "N";

	/** The numero comprobante. */
	private String numeroComprobante = "";

	/** The fecha de operacion. */
	private Date fechaDeOperacion;

	/** The hacia otro banco. */
	private boolean haciaOtroBanco = true;

	/** The hacia cuenta propia. */
	private boolean haciaCuentaPropia = false;

	/** The transferencia inmediata. */
	private boolean transferenciaInmediata = true;

	/** The fecha programada. */
	private Date fechaProgramada;

	/** The nombre receptor. */
	private String nombreReceptor;

	/** The error banelco. */
	private boolean errorBanelco = false;

	/** The celular my A. */
	private boolean celularMyA = false;

	/** The descripciones de error. */
	private List<String> descripcionesDeError;

	/** The sistema asociado al error. */
	private String sistemaAsociadoAlError;

	/** The descripcion concepto. */
	protected String descripcionConcepto;

	/** The frecuencia. */
	private FrecuenciaTransferenciaAgendada frecuencia;

	/** The mensaje. */
	private String mensaje;

	/** The alias. */
	private String alias;

	/**
	 * The cuentas saldos filtrada. Se utilizan al transferir por alias desde
	 * Nueva Transferencia
	 */
	private List<CuentasAdhesionDebitoView> cuentasSaldosFiltrada = new ArrayList<CuentasAdhesionDebitoView>();

	/** The is automatica. */
	private Boolean isAutomatica;
	
	/** The is from agenda destinatario. */
	private Boolean isFromAgendaDestinatario;
	
	private boolean riesgoAlto;
	
	private boolean esCuil;

	private boolean isChecked;

	private DetalleError detalleError;
	

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
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

	/** The saldo cuenta origen. */
	private String saldoCuentaOrigen = "";

	/**
	 * Gets the cbu cuenta.
	 *
	 * @return the cBU
	 */
	public String getCbuCuenta() {
		return cbu;
	}

	/**
	 * Sets the cbu cuenta.
	 *
	 * @param cbu
	 *            the cBU to set
	 */
	public void setCbuCuenta(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the cuenta origen.
	 *
	 * @return the cuentaOrigen
	 */
	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}

	/**
	 * Sets the cuenta origen.
	 *
	 * @param cuentaOrigen
	 *            the cuentaOrigen to set
	 */
	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	/**
	 * Gets the tipo cuenta origen.
	 *
	 * @return the tipoCuentaOrigen
	 */
	public String getTipoCuentaOrigen() {
		return TransferenciaUtil.validarTipoCuentaUnificadaSegunMoneda(this.cuentaOrigen.getTipoCuenta(), this.moneda);
	}

	/**
	 * Gets the tipo transferencia.
	 *
	 * @return the tipoTransferencia
	 */
	public byte getTipoTransferencia() {
		return tipoTransferencia;
	}

	/**
	 * Sets the tipo transferencia.
	 *
	 * @param tipoTransferencia
	 *            the tipoTransferencia to set
	 */
	public void setTipoTransferencia(byte tipoTransferencia) {
		this.tipoTransferencia = tipoTransferencia;
	}

	/**
	 * Gets the referencia univoca.
	 *
	 * @return the referenciaUnivoca
	 */
	public String getReferenciaUnivoca() {
		return concepto.getCodigo() + ISBANStringUtils.removerCaraceteresEspeciales(getDescConcepto());
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public DivisaEnum getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(DivisaEnum moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the informacion discrecional.
	 *
	 * @return the informacionDiscrecional
	 */
	public String getInformacionDiscrecional() {
		return informacionDiscrecional;
	}

	/**
	 * Sets the informacion discrecional.
	 *
	 * @param informacionDiscrecional
	 *            the informacionDiscrecional to set
	 */
	public void setInformacionDiscrecional(String informacionDiscrecional) {
		this.informacionDiscrecional = informacionDiscrecional;
	}

	/**
	 * Gets the entidad cuenta credito.
	 *
	 * @return the entidadCuentaCredito
	 */
	public String getEntidadCuentaCredito() {
		return cbu.substring(0, MAX_NUMERO_ENTIDAD);
	}

	/**
	 * Gets the sucursal cuenta credito.
	 *
	 * @return the sucursalCuentaCredito
	 */
	public String getSucursalCuentaCredito() {
		return cbu.substring(SUCURSAL_INICIO, SUCURSA_FIN);
	}

	/**
	 * Gets the digito cuenta credito.
	 *
	 * @return the digitoCuentaCredito
	 */
	public String getDigitoCuentaCredito() {
		return cbu.substring(DIGITO_INICIO, DIGITO_FIN);
	}

	/**
	 * Gets the numero cuenta credito.
	 *
	 * @return the numeroCuentaCredito
	 */
	public String getNumeroCuentaCredito() {
		return cbu.substring(CUENTA_INICIO, CUENTA_FIN);
	}

	/**
	 * Gets the marca limite.
	 *
	 * @return the marcaLimite
	 */
	public String getMarcaLimite() {
		return marcaLimite;
	}

	/**
	 * Sets the marca limite.
	 *
	 * @param marcaLimite
	 *            the marcaLimite to set
	 */
	public void setMarcaLimite(String marcaLimite) {
		this.marcaLimite = marcaLimite;
	}

	/**
	 * Gets the marca gravado.
	 *
	 * @return the marcaGravado
	 */
	public String getMarcaGravado() {
		if (TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(this.cuentaOrigen.getTipoCuentaEnum())
				|| TipoCuenta.CAJA_AHORRO_DOLARES.equals(this.cuentaOrigen.getTipoCuentaEnum())) {
			return CONST_S;
		}
		return CONST_N;
	}

	/**
	 * Gets the caracteristicas cuenta credito.
	 *
	 * @return the caracteristicasCuentaCredito
	 */
	public CaracteristicasCuentaCredito getCaracteristicasCuentaCredito() {
		return caracteristicasCuentaCredito;
	}

	/**
	 * Sets the caracteristicas cuenta credito.
	 *
	 * @param caracteristicasCuentaCredito
	 *            the caracteristicasCuentaCredito to set
	 */
	public void setCaracteristicasCuentaCredito(CaracteristicasCuentaCredito caracteristicasCuentaCredito) {
		this.caracteristicasCuentaCredito = caracteristicasCuentaCredito;
	}

	/**
	 * Gets the plazo acreditacion.
	 *
	 * @return the plazoAcreditacion
	 */
	public PlazoAcreditacion getPlazoAcreditacion() {
		return plazoAcreditacion;
	}

	/**
	 * Sets the plazo acreditacion.
	 *
	 * @param plazoAcreditacion
	 *            the plazoAcreditacion to set
	 */
	public void setPlazoAcreditacion(PlazoAcreditacion plazoAcreditacion) {
		this.plazoAcreditacion = plazoAcreditacion;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
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
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the cuit 2.
	 *
	 * @return the cuit2
	 */
	public String getCuit2() {
		return cuit2;
	}

	/**
	 * Sets the cuit 2.
	 *
	 * @param cuit2
	 *            the cuit2 to set
	 */
	public void setCuit2(String cuit2) {
		this.cuit2 = cuit2;
	}

	/**
	 * Gets the cuit 3.
	 *
	 * @return the cuit3
	 */
	public String getCuit3() {
		return cuit3;
	}

	/**
	 * Sets the cuit 3.
	 *
	 * @param cuit3
	 *            the cuit3 to set
	 */
	public void setCuit3(String cuit3) {
		this.cuit3 = cuit3;
	}

	/**
	 * Gets the cuenta destino banelco.
	 *
	 * @return the cuentaDestinoBanelco
	 */
	public String getCuentaDestinoBanelco() {
		if (cuentaDestinoBanelco == null || "".equals(cuentaDestinoBanelco)) {
			return "";
		}
		return cuentaDestinoBanelco.trim();
	}

	/**
	 * Sets the cuenta destino banelco.
	 *
	 * @param cuentaDestinoBanelco
	 *            the cuentaDestinoBanelco to set
	 */
	public void setCuentaDestinoBanelco(String cuentaDestinoBanelco) {
		this.cuentaDestinoBanelco = cuentaDestinoBanelco;
	}

	/**
	 * Gets the tipo de cuenta to banelco.
	 *
	 * @return the tipoDeCuentaToBanelco
	 */
	public TipoCuentaBanelco getTipoDeCuentaToBanelco() {
		return tipoDeCuentaToBanelco;
	}

	/**
	 * Sets the tipo de cuenta to banelco.
	 *
	 * @param tipoDeCuentaToBanelco
	 *            the tipoDeCuentaToBanelco to set
	 */
	public void setTipoDeCuentaToBanelco(TipoCuentaBanelco tipoDeCuentaToBanelco) {
		this.tipoDeCuentaToBanelco = tipoDeCuentaToBanelco;
	}

	/**
	 * Gets the tipo de cuenta from banelco.
	 *
	 * @return the tipoDeCuentaFromBanelco
	 */
	public TipoCuentaBanelco getTipoDeCuentaFromBanelco() {
		return tipoDeCuentaFromBanelco;
	}

	/**
	 * Sets the tipo de cuenta from banelco.
	 *
	 * @param tipoDeCuentaFromBanelco
	 *            the tipoDeCuentaFromBanelco to set
	 */
	public void setTipoDeCuentaFromBanelco(TipoCuentaBanelco tipoDeCuentaFromBanelco) {
		this.tipoDeCuentaFromBanelco = tipoDeCuentaFromBanelco;
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
	 * Gets the comentario.
	 *
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * Sets the comentario.
	 *
	 * @param comentario
	 *            the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * Gets the concepto adicional.
	 *
	 * @return the conceptoAdicional
	 */
	public String getConceptoAdicional() {
		return conceptoAdicional;
	}

	/**
	 * Sets the concepto adicional.
	 *
	 * @param conceptoAdicional
	 *            the conceptoAdicional to set
	 */
	public void setConceptoAdicional(String conceptoAdicional) {
		this.conceptoAdicional = conceptoAdicional;
	}

	/**
	 * Gets the descripcion adicional.
	 *
	 * @return the descripcionAdicional
	 */
	public String getDescripcionAdicional() {
		return descripcionAdicional;
	}

	/**
	 * Sets the descripcion adicional.
	 *
	 * @param descripcionAdicional
	 *            the descripcionAdicional to set
	 */
	public void setDescripcionAdicional(String descripcionAdicional) {
		this.descripcionAdicional = descripcionAdicional;
	}

	/**
	 * Gets the concepto adicional 2.
	 *
	 * @return the conceptoAdicional2
	 */
	public String getConceptoAdicional2() {
		return conceptoAdicional2;
	}

	/**
	 * Sets the concepto adicional 2.
	 *
	 * @param conceptoAdicional2
	 *            the conceptoAdicional2 to set
	 */
	public void setConceptoAdicional2(String conceptoAdicional2) {
		this.conceptoAdicional2 = conceptoAdicional2;
	}

	/**
	 * Gets the descripcion adicional 2.
	 *
	 * @return the descripcionAdicional2
	 */
	public String getDescripcionAdicional2() {
		return descripcionAdicional2;
	}

	/**
	 * Sets the descripcion adicional 2.
	 *
	 * @param descripcionAdicional2
	 *            the descripcionAdicional2 to set
	 */
	public void setDescripcionAdicional2(String descripcionAdicional2) {
		this.descripcionAdicional2 = descripcionAdicional2;
	}

	/**
	 * Gets the concepto adicional 3.
	 *
	 * @return the conceptoAdicional3
	 */
	public String getConceptoAdicional3() {
		return conceptoAdicional3;
	}

	/**
	 * Sets the concepto adicional 3.
	 *
	 * @param conceptoAdicional3
	 *            the conceptoAdicional3 to set
	 */
	public void setConceptoAdicional3(String conceptoAdicional3) {
		this.conceptoAdicional3 = conceptoAdicional3;
	}

	/**
	 * Gets the descripcion adicional 3.
	 *
	 * @return the descripcionAdicional3
	 */
	public String getDescripcionAdicional3() {
		return descripcionAdicional3;
	}

	/**
	 * Sets the descripcion adicional 3.
	 *
	 * @param descripcionAdicional3
	 *            the descripcionAdicional3 to set
	 */
	public void setDescripcionAdicional3(String descripcionAdicional3) {
		this.descripcionAdicional3 = descripcionAdicional3;
	}

	/**
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
	public ConceptoTransferenciaEnum getConcepto() {
		return concepto;
	}

	/**
	 * Sets the concepto.
	 *
	 * @param concepto
	 *            the concepto to set
	 */
	public void setConcepto(ConceptoTransferenciaEnum concepto) {
		this.concepto = concepto;
	}

	/**
	 * Gets the informacion adicional.
	 *
	 * @return the informacionAdicional
	 */
	public String getInformacionAdicional() {
		return informacionAdicional;
	}

	/**
	 * Sets the informacion adicional.
	 *
	 * @param informacionAdicional
	 *            the informacionAdicional to set
	 */
	public void setInformacionAdicional(String informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
	}

	/**
	 * Gets the fiid.
	 *
	 * @return the fiid
	 */
	public String getFiid() {
		return fiid;
	}

	/**
	 * Sets the fiid.
	 *
	 * @param fiid
	 *            the fiid to set
	 */
	public void setFiid(String fiid) {
		this.fiid = fiid;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets the banco receptor.
	 *
	 * @return the bancoReceptor
	 */
	public String getBancoReceptor() {
		return bancoReceptor;
	}

	/**
	 * Sets the banco receptor.
	 *
	 * @param bancoReceptor
	 *            the bancoReceptor to set
	 */
	public void setBancoReceptor(String bancoReceptor) {
		this.bancoReceptor = bancoReceptor;
	}

	/**
	 * Gets the codigo concepto.
	 *
	 * @return the codigoConcepto
	 */
	public String getCodigoConcepto() {
		return codigoConcepto;
	}

	/**
	 * Sets the codigo concepto.
	 *
	 * @param codigoConcepto
	 *            the codigoConcepto to set
	 */
	public void setCodigoConcepto(String codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}

	/**
	 * Gets the banco destino.
	 *
	 * @return the bancoDestino
	 */
	public String getBancoDestino() {
		if (bancoDestino == null || bancoDestino.equalsIgnoreCase("")) {
			return "";
		}
		return bancoDestino.trim();
	}

	/**
	 * Sets the banco destino.
	 *
	 * @param bancoDestino
	 *            the bancoDestino to set
	 */
	public void setBancoDestino(String bancoDestino) {
		this.bancoDestino = bancoDestino;
	}

	/**
	 * Gets the agenda.
	 *
	 * @return the agenda
	 */
	public String getAgenda() {
		return agenda;
	}

	/**
	 * Sets the agenda.
	 *
	 * @param agenda
	 *            the agenda to set
	 */
	public void setAgenda(String agenda) {
		this.agenda = agenda;
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
	 * Gets the fecha de operacion.
	 *
	 * @return the fecha de operacion
	 */
	public Date getFechaDeOperacion() {
		return fechaDeOperacion == null ? null : new Date(fechaDeOperacion.getTime());
	}

	/**
	 * Sets the fecha de operacion.
	 *
	 * @param fechaDeOperacion
	 *            the new fecha de operacion
	 */
	public void setFechaDeOperacion(Date fechaDeOperacion) {
		this.fechaDeOperacion = fechaDeOperacion == null ? null : new Date(fechaDeOperacion.getTime());
	}

	/**
	 * Checks if is hacia otro banco.
	 *
	 * @return true, if is hacia otro banco
	 */
	public boolean isHaciaOtroBanco() {
		return haciaOtroBanco;
	}

	/**
	 * Sets the hacia otro banco.
	 *
	 * @param haciaOtroBanco
	 *            the new hacia otro banco
	 */
	public void setHaciaOtroBanco(boolean haciaOtroBanco) {
		this.haciaOtroBanco = haciaOtroBanco;
	}

	/**
	 * Checks if is hacia cuenta propia.
	 *
	 * @return true, if is hacia cuenta propia
	 */
	public boolean isHaciaCuentaPropia() {
		return haciaCuentaPropia;
	}

	/**
	 * Sets the hacia cuenta propia.
	 *
	 * @param haciaCuentaPropia
	 *            the new hacia cuenta propia
	 */
	public void setHaciaCuentaPropia(boolean haciaCuentaPropia) {
		this.haciaCuentaPropia = haciaCuentaPropia;
	}

	/**
	 * Checks if is transferencia inmediata.
	 *
	 * @return true, if is transferencia inmediata
	 */
	public boolean isTransferenciaInmediata() {
		return transferenciaInmediata;
	}

	/**
	 * Sets the transferencia inmediata.
	 *
	 * @param transferenciaInmediata
	 *            the new transferencia inmediata
	 */
	public void setTransferenciaInmediata(boolean transferenciaInmediata) {
		this.transferenciaInmediata = transferenciaInmediata;
	}

	/**
	 * Gets the fecha programada.
	 *
	 * @return the fecha programada
	 */
	public Date getFechaProgramada() {
		return fechaProgramada == null ? null : new Date(fechaProgramada.getTime());
	}

	/**
	 * Sets the fecha programada.
	 *
	 * @param fechaProgramada
	 *            the new fecha programada
	 */
	public void setFechaProgramada(Date fechaProgramada) {
		this.fechaProgramada = fechaProgramada == null ? null : new Date(fechaProgramada.getTime());
	}

	/**
	 * Gets the nombre receptor.
	 *
	 * @return the nombre receptor
	 */
	public String getNombreReceptor() {
		return nombreReceptor;
	}

	/**
	 * Sets the nombre receptor.
	 *
	 * @param nombreReceptor
	 *            the new nombre receptor
	 */
	public void setNombreReceptor(String nombreReceptor) {
		this.nombreReceptor = nombreReceptor;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.entities.Entity#equals(java.lang.Object)
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

		TransferenciaDTO other = (TransferenciaDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cbu, other.getCbuCuenta());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.entities.Entity#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cbu);
		return hcb.toHashCode();
	}

	/**
	 * Gets the descripciones de error.
	 *
	 * @return the descripcionesDeError
	 */
	public List<String> getDescripcionesDeError() {
		return descripcionesDeError;
	}

	/**
	 * Sets the descripciones de error.
	 *
	 * @param mensajes
	 *            the descripcionesDeError to set
	 */
	public void setDescripcionesDeError(List<String> mensajes) {
		this.descripcionesDeError = mensajes;
	}

	/**
	 * Gets the sistema asociado al error.
	 *
	 * @return the sistemaAsociadoAlError
	 */
	public String getSistemaAsociadoAlError() {
		return sistemaAsociadoAlError;
	}

	/**
	 * Sets the sistema asociado al error.
	 *
	 * @param sistemaAsociadoAlError
	 *            the sistemaAsociadoAlError to set
	 */
	public void setSistemaAsociadoAlError(String sistemaAsociadoAlError) {
		this.sistemaAsociadoAlError = sistemaAsociadoAlError;
	}

	/**
	 * Gets the email mensaje.
	 *
	 * @return the emailMensaje
	 */
	public String getEmailMensaje() {
		return emailMensaje;
	}

	/**
	 * Sets the email mensaje.
	 *
	 * @param emailMensaje
	 *            the emailMensaje to set
	 */
	public void setEmailMensaje(String emailMensaje) {
		this.emailMensaje = emailMensaje;
	}

	/**
	 * Gets the tipo cuenta destino.
	 *
	 * @return the tipo cuenta destino
	 */
	public TipoCuenta getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/**
	 * Sets the tipo cuenta destino.
	 *
	 * @param tipoCuentaDestino
	 *            the new tipo cuenta destino
	 */
	public void setTipoCuentaDestino(TipoCuenta tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}

	/**
	 * Gets the numero cuenta destino.
	 *
	 * @return the numero cuenta destino
	 */
	public IdentificacionCuenta getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	/**
	 * Sets the numero cuenta destino.
	 *
	 * @param numeroCuentaDestino
	 *            the new numero cuenta destino
	 */
	public void setNumeroCuentaDestino(IdentificacionCuenta numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}

	/**
	 * Sets the saldo cuenta origen.
	 *
	 * @param saldo
	 *            the new saldo cuenta origen
	 */
	public void setSaldoCuentaOrigen(String saldo) {
		this.saldoCuentaOrigen = saldo;
	}

	/**
	 * Gets the saldo cuenta origen.
	 *
	 * @return the saldo cuenta origen
	 */
	public String getSaldoCuentaOrigen() {
		return this.saldoCuentaOrigen;
	}

	/**
	 * Este metodo retorna la moneda disponible para transferir. Si es hacia
	 * otros bancos, puedo determinar la moneda destino. Porque es indicada por
	 * el usuario
	 *
	 *
	 * @param tienePesos
	 *            the tiene pesos
	 * @param tieneDolares
	 *            the tiene dolares
	 * @param moneda
	 *            la moneda viene si es transferencia con CBU, sino es null.
	 * @return the string
	 * @throws BusinessException
	 *             the business exception
	 */
	public List<String> armarListaDeMonedas(boolean tienePesos, boolean tieneDolares, String moneda)
			throws BusinessException {
		boolean destinoPesos = false;
		boolean destinoDolares = false;
		if (!this.haciaOtroBanco) {
			LOGGER.info(LA_TRANSFERENCIA_ES_HACIA_EL_MISMO_BANCO_RIO_RIO);
			if (this.tipoCuentaDestino == null) {
				throw new BusinessException(LAS_CUENTA_DESTINO_ES_NULA_O_VACIA);
			}
			// resuelvo monedas destino posibles
			switch (tipoCuentaDestino) {
			case CAJA_AHORRO_DOLARES:
			case CUENTA_CORRIENTE_DOLARES:
			case CUENTA_UNICA_DOLARES:
				destinoDolares = true;
				break;
			case CAJA_AHORRO_PESOS:
			case CUENTA_CORRIENTE_PESOS:
			case CUENTA_UNICA_PESOS:
				destinoPesos = true;
				break;
			case CUENTA_UNICA:
				destinoPesos = true;
				destinoDolares = true;
				break;
			default:
				destinoPesos = false;
				destinoDolares = false;
				break;
			}
		} else {
			LOGGER.info("La transferencia es HACIA OTROS BANCOS");
			// hacia otros bancos, puedo determinar la moneda destino.
			if (null != moneda && MONEDA_PESO.equalsIgnoreCase(moneda)) {
				destinoPesos = true;
			} else {
				destinoDolares = true;
			}
		}

		List<String> resultado = new ArrayList<String>();
		if (destinoPesos && tienePesos) {
			resultado.add(PESOS);
		}
		if (destinoDolares && tieneDolares) {
			resultado.add(DOLARES);
		}

		// workaround por defecto pesos
		if (resultado.isEmpty()) {
			resultado.add(PESOS);
		}
		return resultado;
	}

	/**
	 * getDescConcepto.
	 *
	 * @return the desc concepto
	 */
	public String getDescConcepto() {
	    return this.descripcionConcepto == null ? this.concepto.getDescripcionAbreviada() : this.descripcionConcepto;
	}

	/**
	 * getDescFromConcepto.
	 *
	 * @return the desc from concepto
	 */
    public String getDescFromConcepto() {
    	return this.concepto.getDescripcionAbreviada();
    }

	/**
	 * Gets the descripcion concepto.
	 *
	 * @return the descripcion concepto
	 */
	public String getDescripcionConcepto() {
		return descripcionConcepto;
	}

	/**
	 * Sets the descripcion concepto.
	 *
	 * @param descripcionConcepto
	 *            the new descripcion concepto
	 */
	public void setDescripcionConcepto(String descripcionConcepto) {
		this.descripcionConcepto = descripcionConcepto;
	}

	/**
	 * Checks if is va por coelsa.
	 *
	 * @return true, if is va por coelsa
	 */
	public boolean isVaPorCoelsa() {
		return vaPorCoelsa;
	}

	/**
	 * Sets the va por coelsa.
	 *
	 * @param vaPorCoelsa
	 *            the new va por coelsa
	 */
	public void setVaPorCoelsa(boolean vaPorCoelsa) {
		this.vaPorCoelsa = vaPorCoelsa;
	}

	/**
	 * Gets the id recibo.
	 *
	 * @return the id recibo
	 */
	public String getIdRecibo() {
		return idRecibo;
	}

	/**
	 * Sets the id recibo.
	 *
	 * @param idRecibo
	 *            the new id recibo
	 */
	public void setIdRecibo(String idRecibo) {
		this.idRecibo = idRecibo;
	}

	/**
	 * Gets the fecha compensacion.
	 *
	 * @return the fecha compensacion
	 */
	public String getFechaCompensacion() {
		return fechaCompensacion;
	}

	/**
	 * Sets the fecha compensacion.
	 *
	 * @param fechaCompensacion
	 *            the new fecha compensacion
	 */
	public void setFechaCompensacion(String fechaCompensacion) {
		this.fechaCompensacion = fechaCompensacion;
	}

	/**
	 * Gets the longitud cuenta destino banelco.
	 *
	 * @return the longitudCuentaDestinoBanelco
	 */
	public String getLongitudCuentaDestinoBanelco() {
		return longitudCuentaDestinoBanelco;
	}

	/**
	 * Sets the longitud cuenta destino banelco.
	 *
	 * @param longitudCuentaDestinoBanelco
	 *            the longitudCuentaDestinoBanelco to set
	 */
	public void setLongitudCuentaDestinoBanelco(String longitudCuentaDestinoBanelco) {
		this.longitudCuentaDestinoBanelco = longitudCuentaDestinoBanelco;
	}

	/**
	 * Gets the frecuencia.
	 *
	 * @return the frecuencia
	 */
	public FrecuenciaTransferenciaAgendada getFrecuencia() {
		return frecuencia;
	}

	/**
	 * Sets the frecuencia.
	 *
	 * @param frecuencia
	 *            the new frecuencia
	 */
	public void setFrecuencia(FrecuenciaTransferenciaAgendada frecuencia) {
		this.frecuencia = frecuencia;
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
	 * Gets the cuentas saldos filtrada.
	 *
	 * @return the cuentas saldos filtrada
	 */
	public List<CuentasAdhesionDebitoView> getCuentasSaldosFiltrada() {
		return cuentasSaldosFiltrada;
	}

	/**
	 * Sets the cuentas saldos filtrada.
	 *
	 * @param cuentasSaldosFiltrada
	 *            the new cuentas saldos filtrada
	 */
	public void setCuentasSaldosFiltrada(List<CuentasAdhesionDebitoView> cuentasSaldosFiltrada) {
		this.cuentasSaldosFiltrada = cuentasSaldosFiltrada;
	}

	/**
	 * Gets the id transaccion.
	 *
	 * @return the idTransaccion
	 */
	public String getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Sets the id transaccion.
	 *
	 * @param idTransaccion
	 *            the idTransaccion to set
	 */
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	/**
	 * Gets the checks if is automatica.
	 *
	 * @return the isAutomatica
	 */
	public Boolean getIsAutomatica() {
		return isAutomatica;
	}

	/**
	 * Sets the checks if is automatica.
	 *
	 * @param isAutomatica
	 *            the isAutomatica to set
	 */
	public void setIsAutomatica(Boolean isAutomatica) {
		this.isAutomatica = isAutomatica;
	}

	/**
	 * Sets the checks if is from agenda destinatario.
	 *
	 * @param isFromAgendaDestinatario the new checks if is from agenda destinatario
	 */
	public void setIsFromAgendaDestinatario(Boolean isFromAgendaDestinatario) {
		this.isFromAgendaDestinatario = isFromAgendaDestinatario;
		
	}

	/**
	 * Gets the checks if is from agenda destinatario.
	 *
	 * @return the isFromAgendaDestinatario
	 */
	public Boolean isFromAgendaDestinatario() {
		return isFromAgendaDestinatario;
	}

    public boolean isRiesgoAlto() {
        return riesgoAlto;
    }

    public void setRiesgoAlto(boolean riesgoAlto) {
        this.riesgoAlto = riesgoAlto;
    }
    
    public boolean esRioRio() {
        return !this.haciaOtroBanco;
    }

    public String nombreParaMensaje() {
        String nombre;
        if (StringUtils.isNotBlank(this.nombreReceptor)) {
            nombre = this.nombreReceptor;
        } else if (this.haciaOtroBanco) {
            nombre = this.cbu;
        } else {
            nombre = this.numeroCuentaDestino.getNroCuentaProducto();
        }
        return nombre;
    }
    
    public String importeConMoneda() {
        return ISBANStringUtils.formatearSaldoSinAbsConDivisa(this.importe, this.moneda);
    }

    public boolean isEsCuil() {
        return esCuil;
    }

    public void setEsCuil(boolean esCuil) {
        this.esCuil = esCuil;
    }

	public DetalleError getDetalleError() {
		return detalleError;
	}

	public void setDetalleError(DetalleError detalleError) {
		this.detalleError = detalleError;
	}
	public boolean isSaveContact() {
		return isChecked;
	}
	public void setSaveContact(boolean isChecked) { this.isChecked = isChecked; }

}

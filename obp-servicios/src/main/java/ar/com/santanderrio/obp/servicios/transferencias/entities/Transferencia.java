/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenada;

/**
 * The Class Transferencia.
 *
 * @author B039636
 */
public class Transferencia extends RsaDTO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Transferencia.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5785628023363396490L;

	/** The Constant MAX_CONCEPTO. */
	private static final int MAX_CONCEPTO = 3;

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

	/** The cbu. */
	private String cbu = "";

	/** The cuenta origen. */
	private Cuenta cuentaOrigen;

	/** The numero cuenta destino. */
	private IdentificacionCuenta numeroCuentaDestino = new IdentificacionCuenta();

	/** The tipo cuenta destino. */
	private TipoCuenta tipoCuentaDestino;

	/** The tipo transferencia. */
	private byte tipoTransferencia = TIPO_TRANSFERENCIA_DEFAULT;

	/** The importe. */
	private BigDecimal importe;

	/** The moneda. */
	private DivisaEnum moneda;

	/** The informacion discrecional. */
	private String informacionDiscrecional;

	/** The identificacion beneficiario. */
	private String identificacionBeneficiario;

	/** The marca limite. */
	private String marcaLimite = "S";

	/** The caracteristicas cuenta credito. */
	private CaracteristicasCuentaCredito caracteristicasCuentaCredito = CaracteristicasCuentaCredito.INMEDIATO;

	/** The plazo acreditacion. */
	private PlazoAcreditacion plazoAcreditacion = PlazoAcreditacion.INMEDIATO;

	/** The periodica. */
	private String periodica = "N";

	/** The cantidad dias. */
	private short cantidadDias = 0;

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

	/** The antiguedad. */
	private int antiguedad;

	/** The error banelco. */
	private boolean errorBanelco = false;

	/** The celular my A. */
	private boolean celularMyA = false;

	/** The hacer challenge. */
	private boolean hacerChallenge = false;

	/** The descripciones de error. */
	private List<String> descripcionesDeError;

	/** The sistema asociado al error. */
	private String sistemaAsociadoAlError;

	/** The valor desafio transferencias. */
	@Value("${TRJCOORD.OPERAINDISTINTO.TRANSFERENCIAS}")
	private String valorDesafioTransferencias;

	/** The tarjeta coordenada. */
	/*
	 * TODO: emilio.watemberg: cuando se migre tarjeta de coordenadas azzz
	 * CoordenadaOperacionDTO utilizar el atributo tipoDesafio que existe en
	 * AutentificacionDTO para indicar cuando es una operacion u otra
	 */
	private TipoDesafioEnum tipoDesafio;

	/** The metodo desafio. */
	private AutentificacionDTO metodoDesafio;

	/** The tarjeta coordenada. */
	/*
	 * TODO: emilio.watemberg: cuando se migre tarjeta de coordenadas a
	 * CoordenadaOperacionDTO utilizar el atributo tipoDesafio que existe en
	 * AutentificacionDTO para indicar cuando es una operacion u otra
	 */
	private TarjetaCoordenada tarjetaCoordenada;

	/** The mensaje. */
	private String mensaje;

	/** The texto legales. */
	private String textoLegales;

	/**
	 * Instantiates a new transferencia.
	 */
	public Transferencia() {
		super(OperacionesRSAEnum.TRANSFERENCIA);

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
		return this.cuentaOrigen.getTipoCuentaSinUnificar();
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
		return concepto.getDescripcion().substring(0, MAX_CONCEPTO).toUpperCase(Locale.getDefault())
				+ getInformacionAdicional();
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
	 * Gets the identificacion beneficiario.
	 *
	 * @return the identificacionBeneficiario
	 */
	public String getIdentificacionBeneficiario() {
		return identificacionBeneficiario;
	}

	/**
	 * Sets the identificacion beneficiario.
	 *
	 * @param identificacionBeneficiario
	 *            the identificacionBeneficiario to set
	 */
	public void setIdentificacionBeneficiario(String identificacionBeneficiario) {
		this.identificacionBeneficiario = identificacionBeneficiario;
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
		if (TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(this.cuentaOrigen.getTipoCuentaEnum())) {
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
	 * Gets the periodica.
	 *
	 * @return the periodica
	 */
	public String getPeriodica() {
		return periodica;
	}

	/**
	 * Sets the periodica.
	 *
	 * @param periodica
	 *            the periodica to set
	 */
	public void setPeriodica(String periodica) {
		this.periodica = periodica;
	}

	/**
	 * Gets the cantidad dias.
	 *
	 * @return the cantidadDias
	 */
	public short getCantidadDias() {
		return cantidadDias;
	}

	/**
	 * Sets the cantidad dias.
	 *
	 * @param cantidadDias
	 *            the cantidadDias to set
	 */
	public void setCantidadDias(short cantidadDias) {
		this.cantidadDias = cantidadDias;
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
		if (cuentaDestinoBanelco == null || "".equalsIgnoreCase(cuentaDestinoBanelco)) {
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
		if (bancoDestino == null || "".equalsIgnoreCase(bancoDestino)) {
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
	 * Gets the antiguedad.
	 *
	 * @return the antiguedad
	 */
	public int getAntiguedad() {
		return antiguedad;
	}

	/**
	 * Sets the antiguedad.
	 *
	 * @param antiguedad
	 *            the new antiguedad
	 */
	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transferencia [CBU=");
		builder.append(cbu);
		builder.append(", cuentaOrigen=");
		builder.append(cuentaOrigen);
		builder.append(", tipoTransferencia=");
		builder.append(TIPO_TRANSFERENCIA_DEFAULT);
		builder.append(", importe=");
		builder.append(importe);
		builder.append(", moneda=");
		builder.append(moneda);
		builder.append(", informacionDiscrecional=");
		builder.append(informacionDiscrecional);
		builder.append(", identificacionBeneficiario=");
		builder.append(identificacionBeneficiario);
		builder.append(", marcaLimite=");
		builder.append(marcaLimite);
		builder.append(", plazoAcreditacion=");
		builder.append(plazoAcreditacion);
		builder.append(", periodica=");
		builder.append(periodica);
		builder.append(", cantidadDias=");
		builder.append(cantidadDias);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", titular=");
		builder.append(titular);
		builder.append(", cuit=");
		builder.append(cuit);
		appendExtra(builder);
		return builder.toString();
	}

	/**
	 * Append extra.
	 *
	 * @param builder
	 *            the builder
	 * @last_updated: B039542 - ignacio_valek@itrsa.com.ar - 14/09/2016
	 */
	private void appendExtra(StringBuilder builder) {
		builder.append(", cuentaDestinoBanelco=");
		builder.append(cuentaDestinoBanelco);
		builder.append(", tipoDeCuentaToBanelco=");
		builder.append(tipoDeCuentaToBanelco);
		builder.append(", tipoDeCuentaFromBanelco=");
		builder.append(tipoDeCuentaFromBanelco);
		builder.append(", email=");
		builder.append(email);
		builder.append(", comentario=");
		builder.append(comentario);
		builder.append(", conceptoAdicional=");
		builder.append(conceptoAdicional);
		builder.append(", descripcionAdicional=");
		builder.append(descripcionAdicional);
		builder.append(", concepto=");
		builder.append(concepto);
		builder.append(", informacionAdicional=");
		builder.append(informacionAdicional);
		builder.append(", fiid=");
		builder.append(fiid);
		builder.append(", user=");
		builder.append(user);
		builder.append(", bancoReceptor=");
		builder.append(bancoReceptor);
		builder.append(", codigoConcepto=");
		builder.append(codigoConcepto);
		builder.append(", bancoDestino=");
		builder.append(bancoDestino);
		builder.append(", agenda=");
		builder.append(agenda);
		builder.append("]");
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

		Transferencia other = (Transferencia) obj;
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
	 * Checks if is hacer challenge.
	 *
	 * @return true, if is hacer challenge
	 */
	public boolean isHacerChallenge() {
		return hacerChallenge;
	}

	/**
	 * Sets the hacer challenge.
	 *
	 * @param hacerChallenge
	 *            the new hacer challenge
	 */
	public void setHacerChallenge(boolean hacerChallenge) {
		this.hacerChallenge = hacerChallenge;
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
	 * Gets the tarjeta coordenada.
	 *
	 * @return the tarjetaCoordenada
	 */
	@Deprecated
	public TarjetaCoordenada getTarjetaCoordenada() {
		return tarjetaCoordenada;
	}

	/**
	 * Sets the tarjeta coordenada.
	 *
	 * @param tarjetaCoordenada
	 *            the tarjetaCoordenada to set
	 */
	@Deprecated
	public void setTarjetaCoordenada(TarjetaCoordenada tarjetaCoordenada) {
		this.tarjetaCoordenada = tarjetaCoordenada;
	}

	/**
	 * Este metodo retorna la moneda disponible para transferir.
	 *
	 * @param tienePesos
	 *            the tiene pesos
	 * @param tieneDolares
	 *            the tiene dolares
	 * @return the string
	 * @throws BusinessException
	 *             the business exception
	 */
	public List<String> getMonedasValidasParaTransferir(boolean tienePesos, boolean tieneDolares)
			throws BusinessException {
		boolean destinoPesos = false;
		boolean destinoDolares = false;
		if (!this.haciaOtroBanco) {
			LOGGER.info("La transferencia es hacia el mismo banco, RIO/RIO");
			if (this.tipoCuentaDestino == null) {
				throw new BusinessException("Las cuenta destino es nula o vacia");
			}
			// resuelvo monedas destino posibles
			switch (tipoCuentaDestino) {
			case CAJA_AHORRO_DOLARES:
			case CUENTA_CORRIENTE_DOLARES:
				destinoDolares = true;
				break;
			case CAJA_AHORRO_PESOS:
			case CUENTA_CORRIENTE_PESOS:
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
			// hacia otros bancos, no puedo determinar la moneda destino
			destinoPesos = true;
			destinoDolares = true;
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
	 * Gets the tipo desafio.
	 *
	 * @return the tipo desafio
	 */
	@Deprecated
	public TipoDesafioEnum getTipoDesafio() {
		return tipoDesafio;
	}

	/**
	 * Sets the tipo desafio.
	 *
	 * @param tipoDesafio
	 *            the new tipo desafio
	 */
	@Deprecated
	public void setTipoDesafio(TipoDesafioEnum tipoDesafio) {
		this.tipoDesafio = tipoDesafio;
	}

	/**
	 * Gets the metodo desafio.
	 *
	 * @return the metodo desafio
	 */
	public AutentificacionDTO getMetodoDesafio() {
		return metodoDesafio;
	}

	/**
	 * Sets the metodo desafio.
	 *
	 * @param metodoAutentificacion
	 *            the new metodo desafio
	 */
	public void setMetodoDesafio(AutentificacionDTO metodoAutentificacion) {
		this.metodoDesafio = metodoAutentificacion;
	}

	/**
	 * Gets the texto legales.
	 *
	 * @return the texto legales
	 */
	public String getTextoLegales() {
		return textoLegales;
	}

	/**
	 * Sets the texto legales.
	 *
	 * @param textoLegales
	 *            the new texto legales
	 */
	public void setTextoLegales(String textoLegales) {
		this.textoLegales = textoLegales;
	}
}

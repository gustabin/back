/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class TransferenciaAgendada.
 */
public class TransferenciaAgendada extends Entity {

	/** The Constant serialVersionUID. */
	private static long serialVersionUID = 1L;

	/** The rio rio. */
	private boolean rioRio;
	/** The canal tipo. */
	private String canalTipo;

	/** The fecha agendamiento. */
	private String fechaAgendamiento;

	/** The fecha base recurrencia. */
	private String fechaBaseRecurrencia;

	/** The fecha ejecucion. */
	private String fechaEjecucion;

	/** The frec recurrencia. */
	private String frecRecurrencia;

	/** The id transaccion. */
	private String idTransaccion;

	/** The max recurrencia. */
	private String maxRecurrencia;

	/** The nombre. */
	private String nombre;

	/** The nro recurrencia. */
	private String nroRecurrencia;

	/** The subcanal tipo. */
	private String subcanalTipo;

	/** The tipo agendamiento. */
	private String tipoAgendamiento;

	/** The tipo recurrencia. */
	private String tipoRecurrencia;

	/** The version. */
	private String version;

	/** Datos Adicionales, Datos Mens. Avisos */
	/** The concepto. */
	protected String concepto;

	/** The Titular credito. */
	protected String TitularCredito;

	/** The descripcion adicional 3. */
	protected String descripcionAdicional3;

	/** The descripcion adicional 2. */
	protected String descripcionAdicional2;

	/** The descripcion adicional 1. */
	protected String descripcionAdicional1;

	/** The mail cliente reply. */
	protected String mailClienteReply;

	/** The concepto adicional 3. */
	protected String conceptoAdicional3;

	/** The concepto adicional 2. */
	protected String conceptoAdicional2;

	/** The info adicional. */
	protected String infoAdicional;

	/** The concepto adicional 1. */
	protected String conceptoAdicional1;

	/** The anotaciones personales. */
	protected String anotacionesPersonales;

	/** The comentario. */
	protected String comentario;

	/** The mail credito. */
	protected String mailCredito;

	/** The titular debito. */
	protected String titularDebito;

	/** The codigo credito. */
	protected String codigoCredito;

	/** The importe. */
	protected float importe;

	/** The subcodigo credito. */
	protected String subcodigoCredito;

	/** The comprobante credito. */
	protected String comprobanteCredito;

	/** The codigo debito. */
	protected String codigoDebito;

	/** The limite sobregiro. */
	protected String limiteSobregiro;

	/** The subcodigo debito. */
	protected String subcodigoDebito;

	/** Registro Otro Banco. */
	private String informacDiscrecional;

	/** The cbu. */
	private String cbu;

	/** The caracteristica cta credito. */
	private String caracteristicaCtaCredito;

	/** The moneda transferencia. */
	private String monedaTransferencia;

	/** The long cta des bane. */
	private String longCtaDesBane;

	/** The cuit 3. */
	private String cuit3;

	/** The cuit 2. */
	private String cuit2;

	/** The cuit 1. */
	private String cuit1;

	/** The marca gravado. */
	private String marcaGravado;

	/** The indicador funcion. */
	private String indicadorFuncion;

	/** The fiid. */
	private String fiid;

	/** The titular. */
	private String titular;

	/** The tpo cta from bane. */
	private String tpoCtaFromBane;

	/** The cta bane. */
	private String ctaBane;

	/** The user. */
	private String user;

	/** The plazo acreditacion. */
	private String plazoAcreditacion;

	/** The digito cta credito. */
	private String digitoCtaCredito;

	/** The marca limite. */
	private String marcaLimite;

	/** The referencia univoca. */
	private String referenciaUnivoca;

	/** The identific beneficiario. */
	private String identificBeneficiario;

	/** The firmante cta debito. */
	private String firmanteCtaDebito;

	/** The cod concepto. */
	private String codConcepto;

	/** The banco receptor. */
	private String bancoReceptor;

	/** The i P maquina. */
	private String iPMaquina;

	/** The periodica. */
	private String periodica;

	/** The cantidad dias. */
	private String cantidadDias;

	/** The entidad cta credito. */
	private String entidadCtaCredito;

	/** The banco destino. */
	private String bancoDestino;

	/** The importe transferencia. */
	private String importeTransferencia;

	/** The tipo transferencia. */
	private String tipoTransferencia;

	/** The tpo cta to bane. */
	private String tpoCtaToBane;

	/** The desc concepto. */
	private String descConcepto;

	/** Registro Riorio. */

	protected String nroComprobante;

	/** The cotizacion transferencia. */
	protected String cotizacionTransferencia;

	/** The usuario tipo. */
	protected String usuarioTipo;

	/** The indicador limite max. */
	protected String indicadorLimiteMax;

	/** The logueo agenda historica. */
	protected String logueoAgendaHistorica;

	/** The nro cta debito. */
	protected String nroCtaDebito;

	/** The importe debito. */
	protected String importeDebito;

	/** The id sesion cnt. */
	protected String idSesionCnt;

	/** The ind auten. */
	protected String indAuten;

	/** The subcanal id. */
	protected String subcanalId;

	/** The usuario atrib. */
	protected String usuarioAtrib;

	/** The fecha nac. */
	protected String fechaNac;

	/** The indicador afectar disponible. */
	protected String indicadorAfectarDisponible;

	/** The tipo persona. */
	protected String tipoPersona;

	/** The canal id. */
	protected String canalId;

	/** The suc cta credito. */
	protected String sucCtaCredito;

	/** The importe credito. */
	protected String importeCredito;

	/** The nombre cta credito. */
	protected String nombreCtaCredito;

	/** The eco datos entrada. */
	protected String ecoDatosEntrada;

	/** The nro cta credito. */
	protected String nroCtaCredito;

	/** The suc cta debito. */
	protected String sucCtaDebito;

	/** The cuenta propia. */
	protected String cuentaPropia;

	/** The codigo concepto. */
	protected String codigoConcepto;

	/** The tipo cta debito. */
	protected String tipoCtaDebito;

	/** The ind transf diferida. */
	protected String indTransfDiferida;

	/** The tipo cta credito. */
	protected String tipoCtaCredito;

	/** The descripcion concepto. */
	protected String descripcionConcepto;

	/** The tipo id. */
	protected String tipoId;

	/** The canal version. */
	protected String canalVersion;

	/** The id cliente. */
	protected String idCliente;

	/** The version XML. */
	protected String versionXML;

	/** anterior *. */

	/** The id def. */
	private String idDef;

	/** The tx servicio. */
	private String txServicio;

	/** The tx version. */
	private String txVersion;

	/** The nup. */
	private String nup;

	/** The cta orig tipo. */
	private String ctaOrigTipo;

	/** The cta orig suc. */
	private String ctaOrigSuc;

	/** The cta orig num. */
	private String ctaOrigNum;

	/** The cta dest tipo. */
	private String ctaDestTipo;

	/** The cta dest suc. */
	private String ctaDestSuc;

	/** The cta dest num. */
	private String ctaDestNum;

	/** The cta orig tipo mig. */
	private String ctaOrigTipoMig;

	/** The cta orig suc mig. */
	private String ctaOrigSucMig;

	/** The cta orig num mig. */
	private String ctaOrigNumMig;

	/** The cta dest tipo mig. */
	private String ctaDestTipoMig;

	/** The cta dest suc mig. */
	private String ctaDestSucMig;

	/** The cta dest num mig. */
	private String ctaDestNumMig;

	/** The tpo doc. */
	private String tpoDoc;

	/** The nro doc. */
	private String nroDoc;

	/** The canal. */
	private String canal;

	/** The subcanal. */
	private String subcanal;

	/** The pedido transferencia agendada. */
	private PedidoTransferenciaAgendada pedidoTransferenciaAgendada;

	/** The id datos rec. */
	private String idDatosRec;

	/** The fecha base rec. */
	private String fechaBaseRec;

	/** The frecuencia rec. */
	private String frecuenciaRec;

	/** The tipo rec. */
	private String tipoRec;

	/** The maxima rec. */
	private String maximaRec;

	/** The maxima FV. */
	private String maximaFV;

	/** The accion FV. */
	private String accionFV;

	/** The dias aviso previo. */
	private String diasAvisoPrevio;

	/** The id evento. */
	private String idEvento;

	/** The fecha base. */
	private String fechaBase;

	/** The recurrencia. */
	private String recurrencia;

	/** The reintentos. */
	private String reintentos;

	/** The cant FV. */
	private String cantFV;

	/** The estado. */
	private String estado;

	/** The usuario. */
	private String usuario;

	/** The sistema. */
	private String sistema;

	/** The accion. */
	private String accion;

	/** The fecha. */
	private String fecha;

	/**
	 * ---------------------------------------------------------------.
	 *
	 * @return the id def
	 */

	/**
	 * Gets the id def.
	 *
	 * @return the id def
	 */
	public String getIdDef() {
		return idDef;
	}

	/**
	 * Sets the id def.
	 *
	 * @param idDef
	 *            the new id def
	 */
	public void setIdDef(String idDef) {
		this.idDef = idDef;
	}

	/**
	 * Gets the tx servicio.
	 *
	 * @return the tx servicio
	 */
	public String getTxServicio() {
		return txServicio;
	}

	/**
	 * Sets the tx servicio.
	 *
	 * @param txServicio
	 *            the new tx servicio
	 */
	public void setTxServicio(String txServicio) {
		this.txServicio = txServicio;
	}

	/**
	 * Gets the tx version.
	 *
	 * @return the tx version
	 */
	public String getTxVersion() {
		return txVersion;
	}

	/**
	 * Sets the tx version.
	 *
	 * @param txVersion
	 *            the new tx version
	 */
	public void setTxVersion(String txVersion) {
		this.txVersion = txVersion;
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
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the cta orig tipo.
	 *
	 * @return the cta orig tipo
	 */
	public String getCtaOrigTipo() {
		return ctaOrigTipo;
	}

	/**
	 * Sets the cta orig tipo.
	 *
	 * @param ctaOrigTipo
	 *            the new cta orig tipo
	 */
	public void setCtaOrigTipo(String ctaOrigTipo) {
		this.ctaOrigTipo = ctaOrigTipo;
	}

	/**
	 * Gets the cta orig suc.
	 *
	 * @return the cta orig suc
	 */
	public String getCtaOrigSuc() {
		return ctaOrigSuc;
	}

	/**
	 * Sets the cta orig suc.
	 *
	 * @param ctaOrigSuc
	 *            the new cta orig suc
	 */
	public void setCtaOrigSuc(String ctaOrigSuc) {
		this.ctaOrigSuc = ctaOrigSuc;
	}

	/**
	 * Gets the cta orig num.
	 *
	 * @return the cta orig num
	 */
	public String getCtaOrigNum() {
		return ctaOrigNum;
	}

	/**
	 * Sets the cta orig num.
	 *
	 * @param ctaOrigNum
	 *            the new cta orig num
	 */
	public void setCtaOrigNum(String ctaOrigNum) {
		this.ctaOrigNum = ctaOrigNum;
	}

	/**
	 * Gets the cta dest tipo.
	 *
	 * @return the cta dest tipo
	 */
	public String getCtaDestTipo() {
		return ctaDestTipo;
	}

	/**
	 * Sets the cta dest tipo.
	 *
	 * @param ctaDestTipo
	 *            the new cta dest tipo
	 */
	public void setCtaDestTipo(String ctaDestTipo) {
		this.ctaDestTipo = ctaDestTipo;
	}

	/**
	 * Gets the cta dest suc.
	 *
	 * @return the cta dest suc
	 */
	public String getCtaDestSuc() {
		return ctaDestSuc;
	}

	/**
	 * Sets the cta dest suc.
	 *
	 * @param ctaDestSuc
	 *            the new cta dest suc
	 */
	public void setCtaDestSuc(String ctaDestSuc) {
		this.ctaDestSuc = ctaDestSuc;
	}

	/**
	 * Gets the cta dest num.
	 *
	 * @return the cta dest num
	 */
	public String getCtaDestNum() {
		return ctaDestNum;
	}

	/**
	 * Sets the cta dest num.
	 *
	 * @param ctaDestNum
	 *            the new cta dest num
	 */
	public void setCtaDestNum(String ctaDestNum) {
		this.ctaDestNum = ctaDestNum;
	}

	/**
	 * Gets the cta orig tipo mig.
	 *
	 * @return the cta orig tipo mig
	 */
	public String getCtaOrigTipoMig() {
		return ctaOrigTipoMig;
	}

	/**
	 * Sets the cta orig tipo mig.
	 *
	 * @param ctaOrigTipoMig
	 *            the new cta orig tipo mig
	 */
	public void setCtaOrigTipoMig(String ctaOrigTipoMig) {
		this.ctaOrigTipoMig = ctaOrigTipoMig;
	}

	/**
	 * Gets the cta orig suc mig.
	 *
	 * @return the cta orig suc mig
	 */
	public String getCtaOrigSucMig() {
		return ctaOrigSucMig;
	}

	/**
	 * Sets the cta orig suc mig.
	 *
	 * @param ctaOrigSucMig
	 *            the new cta orig suc mig
	 */
	public void setCtaOrigSucMig(String ctaOrigSucMig) {
		this.ctaOrigSucMig = ctaOrigSucMig;
	}

	/**
	 * Gets the cta orig num mig.
	 *
	 * @return the cta orig num mig
	 */
	public String getCtaOrigNumMig() {
		return ctaOrigNumMig;
	}

	/**
	 * Sets the cta orig num mig.
	 *
	 * @param ctaOrigNumMig
	 *            the new cta orig num mig
	 */
	public void setCtaOrigNumMig(String ctaOrigNumMig) {
		this.ctaOrigNumMig = ctaOrigNumMig;
	}

	/**
	 * Gets the cta dest tipo mig.
	 *
	 * @return the cta dest tipo mig
	 */
	public String getCtaDestTipoMig() {
		return ctaDestTipoMig;
	}

	/**
	 * Sets the cta dest tipo mig.
	 *
	 * @param ctaDestTipoMig
	 *            the new cta dest tipo mig
	 */
	public void setCtaDestTipoMig(String ctaDestTipoMig) {
		this.ctaDestTipoMig = ctaDestTipoMig;
	}

	/**
	 * Gets the cta dest suc mig.
	 *
	 * @return the cta dest suc mig
	 */
	public String getCtaDestSucMig() {
		return ctaDestSucMig;
	}

	/**
	 * Sets the cta dest suc mig.
	 *
	 * @param ctaDestSucMig
	 *            the new cta dest suc mig
	 */
	public void setCtaDestSucMig(String ctaDestSucMig) {
		this.ctaDestSucMig = ctaDestSucMig;
	}

	/**
	 * Gets the cta dest num mig.
	 *
	 * @return the cta dest num mig
	 */
	public String getCtaDestNumMig() {
		return ctaDestNumMig;
	}

	/**
	 * Sets the cta dest num mig.
	 *
	 * @param ctaDestNumMig
	 *            the new cta dest num mig
	 */
	public void setCtaDestNumMig(String ctaDestNumMig) {
		this.ctaDestNumMig = ctaDestNumMig;
	}

	/**
	 * Gets the tpo doc.
	 *
	 * @return the tpo doc
	 */
	public String getTpoDoc() {
		return tpoDoc;
	}

	/**
	 * Sets the tpo doc.
	 *
	 * @param tpoDoc
	 *            the new tpo doc
	 */
	public void setTpoDoc(String tpoDoc) {
		this.tpoDoc = tpoDoc;
	}

	/**
	 * Gets the nro doc.
	 *
	 * @return the nro doc
	 */
	public String getNroDoc() {
		return nroDoc;
	}

	/**
	 * Sets the nro doc.
	 *
	 * @param nroDoc
	 *            the new nro doc
	 */
	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the new canal
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the subcanal.
	 *
	 * @return the subcanal
	 */
	public String getSubcanal() {
		return subcanal;
	}

	/**
	 * Sets the subcanal.
	 *
	 * @param subcanal
	 *            the new subcanal
	 */
	public void setSubcanal(String subcanal) {
		this.subcanal = subcanal;
	}

	/**
	 * Gets the id datos rec.
	 *
	 * @return the id datos rec
	 */
	public String getIdDatosRec() {
		return idDatosRec;
	}

	/**
	 * Sets the id datos rec.
	 *
	 * @param idDatosRec
	 *            the new id datos rec
	 */
	public void setIdDatosRec(String idDatosRec) {
		this.idDatosRec = idDatosRec;
	}

	/**
	 * Gets the fecha base rec.
	 *
	 * @return the fecha base rec
	 */
	public String getFechaBaseRec() {
		return fechaBaseRec;
	}

	/**
	 * Sets the fecha base rec.
	 *
	 * @param fechaBaseRec
	 *            the new fecha base rec
	 */
	public void setFechaBaseRec(String fechaBaseRec) {
		this.fechaBaseRec = fechaBaseRec;
	}

	/**
	 * Gets the frecuencia rec.
	 *
	 * @return the frecuencia rec
	 */
	public String getFrecuenciaRec() {
		return frecuenciaRec;
	}

	/**
	 * Sets the frecuencia rec.
	 *
	 * @param frecuenciaRec
	 *            the new frecuencia rec
	 */
	public void setFrecuenciaRec(String frecuenciaRec) {
		this.frecuenciaRec = frecuenciaRec;
	}

	/**
	 * Gets the tipo rec.
	 *
	 * @return the tipo rec
	 */
	public String getTipoRec() {
		return tipoRec;
	}

	/**
	 * Sets the tipo rec.
	 *
	 * @param tipoRec
	 *            the new tipo rec
	 */
	public void setTipoRec(String tipoRec) {
		this.tipoRec = tipoRec;
	}

	/**
	 * Gets the maxima rec.
	 *
	 * @return the maxima rec
	 */
	public String getMaximaRec() {
		return maximaRec;
	}

	/**
	 * Sets the maxima rec.
	 *
	 * @param maximaRec
	 *            the new maxima rec
	 */
	public void setMaximaRec(String maximaRec) {
		this.maximaRec = maximaRec;
	}

	/**
	 * Gets the maxima FV.
	 *
	 * @return the maxima FV
	 */
	public String getMaximaFV() {
		return maximaFV;
	}

	/**
	 * Sets the maxima FV.
	 *
	 * @param maximaFV
	 *            the new maxima FV
	 */
	public void setMaximaFV(String maximaFV) {
		this.maximaFV = maximaFV;
	}

	/**
	 * Gets the accion FV.
	 *
	 * @return the accion FV
	 */
	public String getAccionFV() {
		return accionFV;
	}

	/**
	 * Sets the accion FV.
	 *
	 * @param accionFV
	 *            the new accion FV
	 */
	public void setAccionFV(String accionFV) {
		this.accionFV = accionFV;
	}

	/**
	 * Gets the dias aviso previo.
	 *
	 * @return the dias aviso previo
	 */
	public String getDiasAvisoPrevio() {
		return diasAvisoPrevio;
	}

	/**
	 * Sets the dias aviso previo.
	 *
	 * @param diasAvisoPrevio
	 *            the new dias aviso previo
	 */
	public void setDiasAvisoPrevio(String diasAvisoPrevio) {
		this.diasAvisoPrevio = diasAvisoPrevio;
	}

	/**
	 * Gets the id evento.
	 *
	 * @return the id evento
	 */
	public String getIdEvento() {
		return idEvento;
	}

	/**
	 * Sets the id evento.
	 *
	 * @param idEvento
	 *            the new id evento
	 */
	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	/**
	 * Gets the fecha base.
	 *
	 * @return the fecha base
	 */
	public String getFechaBase() {
		return fechaBase;
	}

	/**
	 * Sets the fecha base.
	 *
	 * @param fechaBase
	 *            the new fecha base
	 */
	public void setFechaBase(String fechaBase) {
		this.fechaBase = fechaBase;
	}

	/**
	 * Gets the recurrencia.
	 *
	 * @return the recurrencia
	 */
	public String getRecurrencia() {
		return recurrencia;
	}

	/**
	 * Sets the recurrencia.
	 *
	 * @param recurrencia
	 *            the new recurrencia
	 */
	public void setRecurrencia(String recurrencia) {
		this.recurrencia = recurrencia;
	}

	/**
	 * Gets the reintentos.
	 *
	 * @return the reintentos
	 */
	public String getReintentos() {
		return reintentos;
	}

	/**
	 * Sets the reintentos.
	 *
	 * @param reintentos
	 *            the new reintentos
	 */
	public void setReintentos(String reintentos) {
		this.reintentos = reintentos;
	}

	/**
	 * Gets the cant FV.
	 *
	 * @return the cant FV
	 */
	public String getCantFV() {
		return cantFV;
	}

	/**
	 * Sets the cant FV.
	 *
	 * @param cantFV
	 *            the new cant FV
	 */
	public void setCantFV(String cantFV) {
		this.cantFV = cantFV;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario
	 *            the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the sistema.
	 *
	 * @return the sistema
	 */
	public String getSistema() {
		return sistema;
	}

	/**
	 * Sets the sistema.
	 *
	 * @param sistema
	 *            the new sistema
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	/**
	 * Gets the accion.
	 *
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Sets the accion.
	 *
	 * @param accion
	 *            the new accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
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
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the pedido transferencia agendada.
	 *
	 * @return the pedido transferencia agendada
	 */
	public PedidoTransferenciaAgendada getPedidoTransferenciaAgendada() {
		return pedidoTransferenciaAgendada;
	}

	/**
	 * Sets the pedido transferencia agendada.
	 *
	 * @param pedidoTransferenciaAgendada
	 *            the new pedido transferencia agendada
	 */
	public void setPedidoTransferenciaAgendada(PedidoTransferenciaAgendada pedidoTransferenciaAgendada) {
		this.pedidoTransferenciaAgendada = pedidoTransferenciaAgendada;
	}

	/**
	 * Checks if is rio rio.
	 *
	 * @return true, if is rio rio
	 */
	public boolean isRioRio() {
		return rioRio;
	}

	/**
	 * Sets the rio rio.
	 *
	 * @param rioRio
	 *            the new rio rio
	 */
	public void setRioRio(boolean rioRio) {
		this.rioRio = rioRio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.entities.Entity#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(this.canalTipo).append(this.fechaAgendamiento).append(this.fechaBaseRecurrencia)
				.append(this.fechaEjecucion).append(this.frecRecurrencia).append(this.idTransaccion)
				.append(this.maxRecurrencia).append(this.nombre).append(this.nroRecurrencia).append(this.subcanalTipo)
				.append(this.tipoAgendamiento).append(this.tipoRecurrencia).append(version);

		return hcb.toHashCode();
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
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TransferenciaAgendada other = (TransferenciaAgendada) obj;

		return new EqualsBuilder().append(canalTipo, other.canalTipo).append(fechaAgendamiento, other.fechaAgendamiento)
				.append(fechaBaseRecurrencia, other.fechaBaseRecurrencia).append(fechaEjecucion, other.fechaEjecucion)
				.append(frecRecurrencia, other.frecRecurrencia).append(idTransaccion, other.idTransaccion)
				.append(maxRecurrencia, other.maxRecurrencia).append(nombre, other.nombre).isEquals();

	}

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
	 * Gets the fecha agendamiento.
	 *
	 * @return the fechaAgendamiento
	 */
	public String getFechaAgendamiento() {
		return fechaAgendamiento;
	}

	/**
	 * Sets the fecha agendamiento.
	 *
	 * @param fechaAgendamiento
	 *            the fechaAgendamiento to set
	 */
	public void setFechaAgendamiento(String fechaAgendamiento) {
		this.fechaAgendamiento = fechaAgendamiento;
	}

	/**
	 * Gets the fecha base recurrencia.
	 *
	 * @return the fechaBaseRecurrencia
	 */
	public String getFechaBaseRecurrencia() {
		return fechaBaseRecurrencia;
	}

	/**
	 * Sets the fecha base recurrencia.
	 *
	 * @param fechaBaseRecurrencia
	 *            the fechaBaseRecurrencia to set
	 */
	public void setFechaBaseRecurrencia(String fechaBaseRecurrencia) {
		this.fechaBaseRecurrencia = fechaBaseRecurrencia;
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
	 * Gets the frec recurrencia.
	 *
	 * @return the frecRecurrencia
	 */
	public String getFrecRecurrencia() {
		return frecRecurrencia;
	}

	/**
	 * Sets the frec recurrencia.
	 *
	 * @param frecRecurrencia
	 *            the frecRecurrencia to set
	 */
	public void setFrecRecurrencia(String frecRecurrencia) {
		this.frecRecurrencia = frecRecurrencia;
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
	 * Gets the max recurrencia.
	 *
	 * @return the maxRecurrencia
	 */
	public String getMaxRecurrencia() {
		return maxRecurrencia;
	}

	/**
	 * Sets the max recurrencia.
	 *
	 * @param maxRecurrencia
	 *            the maxRecurrencia to set
	 */
	public void setMaxRecurrencia(String maxRecurrencia) {
		this.maxRecurrencia = maxRecurrencia;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the nro recurrencia.
	 *
	 * @return the nroRecurrencia
	 */
	public String getNroRecurrencia() {
		return nroRecurrencia;
	}

	/**
	 * Sets the nro recurrencia.
	 *
	 * @param nroRecurrencia
	 *            the nroRecurrencia to set
	 */
	public void setNroRecurrencia(String nroRecurrencia) {
		this.nroRecurrencia = nroRecurrencia;
	}

	/**
	 * Gets the subcanal tipo.
	 *
	 * @return the subcanalTipo
	 */
	public String getSubcanalTipo() {
		return subcanalTipo;
	}

	/**
	 * Sets the subcanal tipo.
	 *
	 * @param subcanalTipo
	 *            the subcanalTipo to set
	 */
	public void setSubcanalTipo(String subcanalTipo) {
		this.subcanalTipo = subcanalTipo;
	}

	/**
	 * Gets the tipo agendamiento.
	 *
	 * @return the tipoAgendamiento
	 */
	public String getTipoAgendamiento() {
		return tipoAgendamiento;
	}

	/**
	 * Sets the tipo agendamiento.
	 *
	 * @param tipoAgendamiento
	 *            the tipoAgendamiento to set
	 */
	public void setTipoAgendamiento(String tipoAgendamiento) {
		this.tipoAgendamiento = tipoAgendamiento;
	}

	/**
	 * Gets the tipo recurrencia.
	 *
	 * @return the tipoRecurrencia
	 */
	public String getTipoRecurrencia() {
		return tipoRecurrencia;
	}

	/**
	 * Sets the tipo recurrencia.
	 *
	 * @param tipoRecurrencia
	 *            the tipoRecurrencia to set
	 */
	public void setTipoRecurrencia(String tipoRecurrencia) {
		this.tipoRecurrencia = tipoRecurrencia;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Sets the serialversionuid.
	 *
	 * @param serialversionuid
	 *            the serialversionuid to set
	 */
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
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
	 * Gets the titular credito.
	 *
	 * @return the titularCredito
	 */
	public String getTitularCredito() {
		return TitularCredito;
	}

	/**
	 * Sets the titular credito.
	 *
	 * @param titularCredito
	 *            the titularCredito to set
	 */
	public void setTitularCredito(String titularCredito) {
		TitularCredito = titularCredito;
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
	 * Gets the descripcion adicional 1.
	 *
	 * @return the descripcionAdicional1
	 */
	public String getDescripcionAdicional1() {
		return descripcionAdicional1;
	}

	/**
	 * Sets the descripcion adicional 1.
	 *
	 * @param descripcionAdicional1
	 *            the descripcionAdicional1 to set
	 */
	public void setDescripcionAdicional1(String descripcionAdicional1) {
		this.descripcionAdicional1 = descripcionAdicional1;
	}

	/**
	 * Gets the mail cliente reply.
	 *
	 * @return the mailClienteReply
	 */
	public String getMailClienteReply() {
		return mailClienteReply;
	}

	/**
	 * Sets the mail cliente reply.
	 *
	 * @param mailClienteReply
	 *            the mailClienteReply to set
	 */
	public void setMailClienteReply(String mailClienteReply) {
		this.mailClienteReply = mailClienteReply;
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
	 * Gets the info adicional.
	 *
	 * @return the infoAdicional
	 */
	public String getInfoAdicional() {
		return infoAdicional;
	}

	/**
	 * Sets the info adicional.
	 *
	 * @param infoAdicional
	 *            the infoAdicional to set
	 */
	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

	/**
	 * Gets the concepto adicional 1.
	 *
	 * @return the conceptoAdicional1
	 */
	public String getConceptoAdicional1() {
		return conceptoAdicional1;
	}

	/**
	 * Sets the concepto adicional 1.
	 *
	 * @param conceptoAdicional1
	 *            the conceptoAdicional1 to set
	 */
	public void setConceptoAdicional1(String conceptoAdicional1) {
		this.conceptoAdicional1 = conceptoAdicional1;
	}

	/**
	 * Gets the anotaciones personales.
	 *
	 * @return the anotacionesPersonales
	 */
	public String getAnotacionesPersonales() {
		return anotacionesPersonales;
	}

	/**
	 * Sets the anotaciones personales.
	 *
	 * @param anotacionesPersonales
	 *            the anotacionesPersonales to set
	 */
	public void setAnotacionesPersonales(String anotacionesPersonales) {
		this.anotacionesPersonales = anotacionesPersonales;
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
	 * Gets the mail credito.
	 *
	 * @return the mailCredito
	 */
	public String getMailCredito() {
		return mailCredito;
	}

	/**
	 * Sets the mail credito.
	 *
	 * @param mailCredito
	 *            the mailCredito to set
	 */
	public void setMailCredito(String mailCredito) {
		this.mailCredito = mailCredito;
	}

	/**
	 * Gets the titular debito.
	 *
	 * @return the titularDebito
	 */
	public String getTitularDebito() {
		return titularDebito;
	}

	/**
	 * Sets the titular debito.
	 *
	 * @param titularDebito
	 *            the titularDebito to set
	 */
	public void setTitularDebito(String titularDebito) {
		this.titularDebito = titularDebito;
	}

	/**
	 * Gets the codigo credito.
	 *
	 * @return the codigoCredito
	 */
	public String getCodigoCredito() {
		return codigoCredito;
	}

	/**
	 * Sets the codigo credito.
	 *
	 * @param codigoCredito
	 *            the codigoCredito to set
	 */
	public void setCodigoCredito(String codigoCredito) {
		this.codigoCredito = codigoCredito;
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
	 * Gets the logueo agenda historica.
	 *
	 * @return the logueoAgendaHistorica
	 */
	public String getLogueoAgendaHistorica() {
		return logueoAgendaHistorica;
	}

	/**
	 * Sets the logueo agenda historica.
	 *
	 * @param logueoAgendaHistorica
	 *            the logueoAgendaHistorica to set
	 */
	public void setLogueoAgendaHistorica(String logueoAgendaHistorica) {
		this.logueoAgendaHistorica = logueoAgendaHistorica;
	}

	/**
	 * Gets the nro cta debito.
	 *
	 * @return the nroCtaDebito
	 */
	public String getNroCtaDebito() {
		return nroCtaDebito;
	}

	/**
	 * Sets the nro cta debito.
	 *
	 * @param nroCtaDebito
	 *            the nroCtaDebito to set
	 */
	public void setNroCtaDebito(String nroCtaDebito) {
		this.nroCtaDebito = nroCtaDebito;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public float getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(float importe) {
		this.importe = importe;
	}

	/**
	 * Gets the subcodigo credito.
	 *
	 * @return the subcodigoCredito
	 */
	public String getSubcodigoCredito() {
		return subcodigoCredito;
	}

	/**
	 * Sets the subcodigo credito.
	 *
	 * @param subcodigoCredito
	 *            the subcodigoCredito to set
	 */
	public void setSubcodigoCredito(String subcodigoCredito) {
		this.subcodigoCredito = subcodigoCredito;
	}

	/**
	 * Gets the id sesion cnt.
	 *
	 * @return the idSesionCnt
	 */
	public String getIdSesionCnt() {
		return idSesionCnt;
	}

	/**
	 * Sets the id sesion cnt.
	 *
	 * @param idSesionCnt
	 *            the idSesionCnt to set
	 */
	public void setIdSesionCnt(String idSesionCnt) {
		this.idSesionCnt = idSesionCnt;
	}

	/**
	 * Gets the ind auten.
	 *
	 * @return the indAuten
	 */
	public String getIndAuten() {
		return indAuten;
	}

	/**
	 * Sets the ind auten.
	 *
	 * @param indAuten
	 *            the indAuten to set
	 */
	public void setIndAuten(String indAuten) {
		this.indAuten = indAuten;
	}

	/**
	 * Gets the subcanal id.
	 *
	 * @return the subcanalId
	 */
	public String getSubcanalId() {
		return subcanalId;
	}

	/**
	 * Sets the subcanal id.
	 *
	 * @param subcanalId
	 *            the subcanalId to set
	 */
	public void setSubcanalId(String subcanalId) {
		this.subcanalId = subcanalId;
	}

	/**
	 * Gets the usuario atrib.
	 *
	 * @return the usuarioAtrib
	 */
	public String getUsuarioAtrib() {
		return usuarioAtrib;
	}

	/**
	 * Sets the usuario atrib.
	 *
	 * @param usuarioAtrib
	 *            the usuarioAtrib to set
	 */
	public void setUsuarioAtrib(String usuarioAtrib) {
		this.usuarioAtrib = usuarioAtrib;
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
	 * Gets the tipo persona.
	 *
	 * @return the tipoPersona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}

	/**
	 * Sets the tipo persona.
	 *
	 * @param tipoPersona
	 *            the tipoPersona to set
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
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
	 * Gets the comprobante credito.
	 *
	 * @return the comprobanteCredito
	 */
	public String getComprobanteCredito() {
		return comprobanteCredito;
	}

	/**
	 * Sets the comprobante credito.
	 *
	 * @param comprobanteCredito
	 *            the comprobanteCredito to set
	 */
	public void setComprobanteCredito(String comprobanteCredito) {
		this.comprobanteCredito = comprobanteCredito;
	}

	/**
	 * Gets the suc cta credito.
	 *
	 * @return the sucCtaCredito
	 */
	public String getSucCtaCredito() {
		return sucCtaCredito;
	}

	/**
	 * Sets the suc cta credito.
	 *
	 * @param sucCtaCredito
	 *            the sucCtaCredito to set
	 */
	public void setSucCtaCredito(String sucCtaCredito) {
		this.sucCtaCredito = sucCtaCredito;
	}

	/**
	 * Gets the eco datos entrada.
	 *
	 * @return the ecoDatosEntrada
	 */
	public String getEcoDatosEntrada() {
		return ecoDatosEntrada;
	}

	/**
	 * Sets the eco datos entrada.
	 *
	 * @param ecoDatosEntrada
	 *            the ecoDatosEntrada to set
	 */
	public void setEcoDatosEntrada(String ecoDatosEntrada) {
		this.ecoDatosEntrada = ecoDatosEntrada;
	}

	/**
	 * Gets the nro cta credito.
	 *
	 * @return the nroCtaCredito
	 */
	public String getNroCtaCredito() {
		return nroCtaCredito;
	}

	/**
	 * Sets the nro cta credito.
	 *
	 * @param nroCtaCredito
	 *            the nroCtaCredito to set
	 */
	public void setNroCtaCredito(String nroCtaCredito) {
		this.nroCtaCredito = nroCtaCredito;
	}

	/**
	 * Gets the suc cta debito.
	 *
	 * @return the sucCtaDebito
	 */
	public String getSucCtaDebito() {
		return sucCtaDebito;
	}

	/**
	 * Sets the suc cta debito.
	 *
	 * @param sucCtaDebito
	 *            the sucCtaDebito to set
	 */
	public void setSucCtaDebito(String sucCtaDebito) {
		this.sucCtaDebito = sucCtaDebito;
	}

	/**
	 * Gets the tipo cta debito.
	 *
	 * @return the tipoCtaDebito
	 */
	public String getTipoCtaDebito() {
		return tipoCtaDebito;
	}

	/**
	 * Sets the tipo cta debito.
	 *
	 * @param tipoCtaDebito
	 *            the tipoCtaDebito to set
	 */
	public void setTipoCtaDebito(String tipoCtaDebito) {
		this.tipoCtaDebito = tipoCtaDebito;
	}

	/**
	 * Gets the tipo cta credito.
	 *
	 * @return the tipoCtaCredito
	 */
	public String getTipoCtaCredito() {
		return tipoCtaCredito;
	}

	/**
	 * Sets the tipo cta credito.
	 *
	 * @param tipoCtaCredito
	 *            the tipoCtaCredito to set
	 */
	public void setTipoCtaCredito(String tipoCtaCredito) {
		this.tipoCtaCredito = tipoCtaCredito;
	}

	/**
	 * Gets the tipo id.
	 *
	 * @return the tipoId
	 */
	public String getTipoId() {
		return tipoId;
	}

	/**
	 * Sets the tipo id.
	 *
	 * @param tipoId
	 *            the tipoId to set
	 */
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	/**
	 * Gets the canal version.
	 *
	 * @return the canalVersion
	 */
	public String getCanalVersion() {
		return canalVersion;
	}

	/**
	 * Sets the canal version.
	 *
	 * @param canalVersion
	 *            the canalVersion to set
	 */
	public void setCanalVersion(String canalVersion) {
		this.canalVersion = canalVersion;
	}

	/**
	 * Gets the id cliente.
	 *
	 * @return the idCliente
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * Sets the id cliente.
	 *
	 * @param idCliente
	 *            the idCliente to set
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * Gets the codigo debito.
	 *
	 * @return the codigoDebito
	 */
	public String getCodigoDebito() {
		return codigoDebito;
	}

	/**
	 * Sets the codigo debito.
	 *
	 * @param codigoDebito
	 *            the codigoDebito to set
	 */
	public void setCodigoDebito(String codigoDebito) {
		this.codigoDebito = codigoDebito;
	}

	/**
	 * Gets the limite sobregiro.
	 *
	 * @return the limiteSobregiro
	 */
	public String getLimiteSobregiro() {
		return limiteSobregiro;
	}

	/**
	 * Sets the limite sobregiro.
	 *
	 * @param limiteSobregiro
	 *            the limiteSobregiro to set
	 */
	public void setLimiteSobregiro(String limiteSobregiro) {
		this.limiteSobregiro = limiteSobregiro;
	}

	/**
	 * Gets the version XML.
	 *
	 * @return the versionXML
	 */
	public String getVersionXML() {
		return versionXML;
	}

	/**
	 * Sets the version XML.
	 *
	 * @param versionXML
	 *            the versionXML to set
	 */
	public void setVersionXML(String versionXML) {
		this.versionXML = versionXML;
	}

	/**
	 * Gets the subcodigo debito.
	 *
	 * @return the subcodigoDebito
	 */
	public String getSubcodigoDebito() {
		return subcodigoDebito;
	}

	/**
	 * Sets the subcodigo debito.
	 *
	 * @param subcodigoDebito
	 *            the subcodigoDebito to set
	 */
	public void setSubcodigoDebito(String subcodigoDebito) {
		this.subcodigoDebito = subcodigoDebito;
	}

	/**
	 * Gets the serial version UID.
	 *
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * Sets the serial version UID.
	 *
	 * @param serialVersionUID
	 *            the serialVersionUID to set
	 */
	public static void setSerialVersionUID(long serialVersionUID) {
		TransferenciaAgendada.serialVersionUID = serialVersionUID;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nroComprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the nroComprobante to set
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the cotizacion transferencia.
	 *
	 * @return the cotizacionTransferencia
	 */
	public String getCotizacionTransferencia() {
		return cotizacionTransferencia;
	}

	/**
	 * Sets the cotizacion transferencia.
	 *
	 * @param cotizacionTransferencia
	 *            the cotizacionTransferencia to set
	 */
	public void setCotizacionTransferencia(String cotizacionTransferencia) {
		this.cotizacionTransferencia = cotizacionTransferencia;
	}

	/**
	 * Gets the indicador limite max.
	 *
	 * @return the indicadorLimiteMax
	 */
	public String getIndicadorLimiteMax() {
		return indicadorLimiteMax;
	}

	/**
	 * Sets the indicador limite max.
	 *
	 * @param indicadorLimiteMax
	 *            the indicadorLimiteMax to set
	 */
	public void setIndicadorLimiteMax(String indicadorLimiteMax) {
		this.indicadorLimiteMax = indicadorLimiteMax;
	}

	/**
	 * Gets the importe debito.
	 *
	 * @return the importeDebito
	 */
	public String getImporteDebito() {
		return importeDebito;
	}

	/**
	 * Sets the importe debito.
	 *
	 * @param importeDebito
	 *            the importeDebito to set
	 */
	public void setImporteDebito(String importeDebito) {
		this.importeDebito = importeDebito;
	}

	/**
	 * Gets the indicador afectar disponible.
	 *
	 * @return the indicadorAfectarDisponible
	 */
	public String getIndicadorAfectarDisponible() {
		return indicadorAfectarDisponible;
	}

	/**
	 * Sets the indicador afectar disponible.
	 *
	 * @param indicadorAfectarDisponible
	 *            the indicadorAfectarDisponible to set
	 */
	public void setIndicadorAfectarDisponible(String indicadorAfectarDisponible) {
		this.indicadorAfectarDisponible = indicadorAfectarDisponible;
	}

	/**
	 * Gets the importe credito.
	 *
	 * @return the importeCredito
	 */
	public String getImporteCredito() {
		return importeCredito;
	}

	/**
	 * Sets the importe credito.
	 *
	 * @param importeCredito
	 *            the importeCredito to set
	 */
	public void setImporteCredito(String importeCredito) {
		this.importeCredito = importeCredito;
	}

	/**
	 * Gets the nombre cta credito.
	 *
	 * @return the nombreCtaCredito
	 */
	public String getNombreCtaCredito() {
		return nombreCtaCredito;
	}

	/**
	 * Sets the nombre cta credito.
	 *
	 * @param nombreCtaCredito
	 *            the nombreCtaCredito to set
	 */
	public void setNombreCtaCredito(String nombreCtaCredito) {
		this.nombreCtaCredito = nombreCtaCredito;
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
	 * Gets the ind transf diferida.
	 *
	 * @return the indTransfDiferida
	 */
	public String getIndTransfDiferida() {
		return indTransfDiferida;
	}

	/**
	 * Sets the ind transf diferida.
	 *
	 * @param indTransfDiferida
	 *            the indTransfDiferida to set
	 */
	public void setIndTransfDiferida(String indTransfDiferida) {
		this.indTransfDiferida = indTransfDiferida;
	}

	/**
	 * Gets the descripcion concepto.
	 *
	 * @return the descripcionConcepto
	 */
	public String getDescripcionConcepto() {
		return descripcionConcepto;
	}

	/**
	 * Sets the descripcion concepto.
	 *
	 * @param descripcionConcepto
	 *            the descripcionConcepto to set
	 */
	public void setDescripcionConcepto(String descripcionConcepto) {
		this.descripcionConcepto = descripcionConcepto;
	}

	/**
	 * Gets the informac discrecional.
	 *
	 * @return the informacDiscrecional
	 */
	public String getInformacDiscrecional() {
		return informacDiscrecional;
	}

	/**
	 * Sets the informac discrecional.
	 *
	 * @param informacDiscrecional
	 *            the informacDiscrecional to set
	 */
	public void setInformacDiscrecional(String informacDiscrecional) {
		this.informacDiscrecional = informacDiscrecional;
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
	 * Gets the caracteristica cta credito.
	 *
	 * @return the caracteristicaCtaCredito
	 */
	public String getCaracteristicaCtaCredito() {
		return caracteristicaCtaCredito;
	}

	/**
	 * Sets the caracteristica cta credito.
	 *
	 * @param caracteristicaCtaCredito
	 *            the caracteristicaCtaCredito to set
	 */
	public void setCaracteristicaCtaCredito(String caracteristicaCtaCredito) {
		this.caracteristicaCtaCredito = caracteristicaCtaCredito;
	}

	/**
	 * Gets the moneda transferencia.
	 *
	 * @return the monedaTransferencia
	 */
	public String getMonedaTransferencia() {
		return monedaTransferencia;
	}

	/**
	 * Sets the moneda transferencia.
	 *
	 * @param monedaTransferencia
	 *            the monedaTransferencia to set
	 */
	public void setMonedaTransferencia(String monedaTransferencia) {
		this.monedaTransferencia = monedaTransferencia;
	}

	/**
	 * Gets the long cta des bane.
	 *
	 * @return the longCtaDesBane
	 */
	public String getLongCtaDesBane() {
		return longCtaDesBane;
	}

	/**
	 * Sets the long cta des bane.
	 *
	 * @param longCtaDesBane
	 *            the longCtaDesBane to set
	 */
	public void setLongCtaDesBane(String longCtaDesBane) {
		this.longCtaDesBane = longCtaDesBane;
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
	 * Gets the cuit 1.
	 *
	 * @return the cuit1
	 */
	public String getCuit1() {
		return cuit1;
	}

	/**
	 * Sets the cuit 1.
	 *
	 * @param cuit1
	 *            the cuit1 to set
	 */
	public void setCuit1(String cuit1) {
		this.cuit1 = cuit1;
	}

	/**
	 * Gets the marca gravado.
	 *
	 * @return the marcaGravado
	 */
	public String getMarcaGravado() {
		return marcaGravado;
	}

	/**
	 * Sets the marca gravado.
	 *
	 * @param marcaGravado
	 *            the marcaGravado to set
	 */
	public void setMarcaGravado(String marcaGravado) {
		this.marcaGravado = marcaGravado;
	}

	/**
	 * Gets the indicador funcion.
	 *
	 * @return the indicadorFuncion
	 */
	public String getIndicadorFuncion() {
		return indicadorFuncion;
	}

	/**
	 * Sets the indicador funcion.
	 *
	 * @param indicadorFuncion
	 *            the indicadorFuncion to set
	 */
	public void setIndicadorFuncion(String indicadorFuncion) {
		this.indicadorFuncion = indicadorFuncion;
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
	 * Gets the tpo cta from bane.
	 *
	 * @return the tpoCtaFromBane
	 */
	public String getTpoCtaFromBane() {
		return tpoCtaFromBane;
	}

	/**
	 * Sets the tpo cta from bane.
	 *
	 * @param tpoCtaFromBane
	 *            the tpoCtaFromBane to set
	 */
	public void setTpoCtaFromBane(String tpoCtaFromBane) {
		this.tpoCtaFromBane = tpoCtaFromBane;
	}

	/**
	 * Gets the cta bane.
	 *
	 * @return the ctaBane
	 */
	public String getCtaBane() {
		return ctaBane;
	}

	/**
	 * Sets the cta bane.
	 *
	 * @param ctaBane
	 *            the ctaBane to set
	 */
	public void setCtaBane(String ctaBane) {
		this.ctaBane = ctaBane;
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
	 * Gets the digito cta credito.
	 *
	 * @return the digitoCtaCredito
	 */
	public String getDigitoCtaCredito() {
		return digitoCtaCredito;
	}

	/**
	 * Sets the digito cta credito.
	 *
	 * @param digitoCtaCredito
	 *            the digitoCtaCredito to set
	 */
	public void setDigitoCtaCredito(String digitoCtaCredito) {
		this.digitoCtaCredito = digitoCtaCredito;
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
	 * Gets the referencia univoca.
	 *
	 * @return the referenciaUnivoca
	 */
	public String getReferenciaUnivoca() {
		return referenciaUnivoca;
	}

	/**
	 * Sets the referencia univoca.
	 *
	 * @param referenciaUnivoca
	 *            the referenciaUnivoca to set
	 */
	public void setReferenciaUnivoca(String referenciaUnivoca) {
		this.referenciaUnivoca = referenciaUnivoca;
	}

	/**
	 * Gets the identific beneficiario.
	 *
	 * @return the identificBeneficiario
	 */
	public String getIdentificBeneficiario() {
		return identificBeneficiario;
	}

	/**
	 * Sets the identific beneficiario.
	 *
	 * @param identificBeneficiario
	 *            the identificBeneficiario to set
	 */
	public void setIdentificBeneficiario(String identificBeneficiario) {
		this.identificBeneficiario = identificBeneficiario;
	}

	/**
	 * Gets the firmante cta debito.
	 *
	 * @return the firmanteCtaDebito
	 */
	public String getFirmanteCtaDebito() {
		return firmanteCtaDebito;
	}

	/**
	 * Sets the firmante cta debito.
	 *
	 * @param firmanteCtaDebito
	 *            the firmanteCtaDebito to set
	 */
	public void setFirmanteCtaDebito(String firmanteCtaDebito) {
		this.firmanteCtaDebito = firmanteCtaDebito;
	}

	/**
	 * Gets the cod concepto.
	 *
	 * @return the codConcepto
	 */
	public String getCodConcepto() {
		return codConcepto;
	}

	/**
	 * Sets the cod concepto.
	 *
	 * @param codConcepto
	 *            the codConcepto to set
	 */
	public void setCodConcepto(String codConcepto) {
		this.codConcepto = codConcepto;
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
	 * Gets the i P maquina.
	 *
	 * @return the iPMaquina
	 */
	public String getiPMaquina() {
		return iPMaquina;
	}

	/**
	 * Sets the i P maquina.
	 *
	 * @param iPMaquina
	 *            the iPMaquina to set
	 */
	public void setiPMaquina(String iPMaquina) {
		this.iPMaquina = iPMaquina;
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
	public String getCantidadDias() {
		return cantidadDias;
	}

	/**
	 * Sets the cantidad dias.
	 *
	 * @param cantidadDias
	 *            the cantidadDias to set
	 */
	public void setCantidadDias(String cantidadDias) {
		this.cantidadDias = cantidadDias;
	}

	/**
	 * Gets the entidad cta credito.
	 *
	 * @return the entidadCtaCredito
	 */
	public String getEntidadCtaCredito() {
		return entidadCtaCredito;
	}

	/**
	 * Sets the entidad cta credito.
	 *
	 * @param entidadCtaCredito
	 *            the entidadCtaCredito to set
	 */
	public void setEntidadCtaCredito(String entidadCtaCredito) {
		this.entidadCtaCredito = entidadCtaCredito;
	}

	/**
	 * Gets the banco destino.
	 *
	 * @return the bancoDestino
	 */
	public String getBancoDestino() {
		return bancoDestino;
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
	 * Gets the importe transferencia.
	 *
	 * @return the importeTransferencia
	 */
	public String getImporteTransferencia() {
		return importeTransferencia;
	}

	/**
	 * Sets the importe transferencia.
	 *
	 * @param importeTransferencia
	 *            the importeTransferencia to set
	 */
	public void setImporteTransferencia(String importeTransferencia) {
		this.importeTransferencia = importeTransferencia;
	}

	/**
	 * Gets the tipo transferencia.
	 *
	 * @return the tipoTransferencia
	 */
	public String getTipoTransferencia() {
		return tipoTransferencia;
	}

	/**
	 * Sets the tipo transferencia.
	 *
	 * @param tipoTransferencia
	 *            the tipoTransferencia to set
	 */
	public void setTipoTransferencia(String tipoTransferencia) {
		this.tipoTransferencia = tipoTransferencia;
	}

	/**
	 * Gets the tpo cta to bane.
	 *
	 * @return the tpoCtaToBane
	 */
	public String getTpoCtaToBane() {
		return tpoCtaToBane;
	}

	/**
	 * Sets the tpo cta to bane.
	 *
	 * @param tpoCtaToBane
	 *            the tpoCtaToBane to set
	 */
	public void setTpoCtaToBane(String tpoCtaToBane) {
		this.tpoCtaToBane = tpoCtaToBane;
	}

	/**
	 * Gets the desc concepto.
	 *
	 * @return the descConcepto
	 */
	public String getDescConcepto() {
		return descConcepto;
	}

	/**
	 * Sets the desc concepto.
	 *
	 * @param descConcepto
	 *            the descConcepto to set
	 */
	public void setDescConcepto(String descConcepto) {
		this.descConcepto = descConcepto;
	}

}

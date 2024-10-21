/*
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class CNSAgendaDatosOtrosBancosTransferenciaResponse.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CNSAgendaDatosOtrosBancosTransferenciaResponse extends AbstractCNSAgendaDatosTransferenciaResponse {

	/** The informac discrecional. */
	@XmlElement(name = "InformacDiscrecional")
	private String informacDiscrecional;

	/** The caracteristicas credito. */
	@XmlElement(name = "CaracteristicaCtaCredito")
	private String caracteristicasCredito;

	/** The moneda transferencia. */
	@XmlElement(name = "MonedaTransferencia")
	private String monedaTransferencia;

	/** The usuario tipo. */
	@XmlElement(name = "UsuarioTipo")
	private String usuarioTipo;

	/** The marca gravado. */
	@XmlElement(name = "MarcaGravado")
	private String marcaGravado;

	/** The logueo agenda historica. */
	@XmlElement(name = "LogueoAgendaHistorica")
	private String logueoAgendaHistorica;

	/** The Indicador funcion. */
	@XmlElement(name = "IndicadorFuncion")
	private String IndicadorFuncion;

	/** The id sesion cnt. */
	@XmlElement(name = "IdSesionCnt")
	private String idSesionCnt;

	/** The ind auten. */
	@XmlElement(name = "IndAuten")
	private String indAuten;

	/** The subcanal id. */
	@XmlElement(name = "SubcanalId")
	private String subcanalId;

	/** The plazo acreditacion. */
	@XmlElement(name = "PlazoAcreditacion")
	private String plazoAcreditacion;

	/** The digito cta credito. */
	@XmlElement(name = "DigitoCtaCredito")
	private String digitoCtaCredito;

	/** The usuario atrib. */
	@XmlElement(name = "UsuarioAtrib")
	private String usuarioAtrib;

	/** The marca limite. */
	@XmlElement(name = "MarcaLimite")
	private String marcaLimite;

	/** The referencia univoca. */
	@XmlElement(name = "ReferenciaUnivoca")
	private String referenciaUnivoca;

	/** The identific beneficiario. */
	@XmlElement(name = "IdentificBeneficiario")
	private String identificBeneficiario;

	/** The firmante cta debito. */
	@XmlElement(name = "FirmanteCtaDebito")
	private String firmanteCtaDebito;

	/** The canal id. */
	@XmlElement(name = "CanalId")
	private String canalId;

	/** The periodica. */
	@XmlElement(name = "Periodica")
	private String periodica;

	/** The eco datos entrada. */
	@XmlElement(name = "EcoDatosEntrada")
	private String ecoDatosEntrada;

	/** The cantidad dias. */
	@XmlElement(name = "CantidadDias")
	private String cantidadDias;

	/** The entidad cta credito. */
	@XmlElement(name = "EntidadCtaCredito")
	private String entidadCtaCredito;

	/** The importe transferencia. */
	@XmlElement(name = "ImporteTransferencia")
	private String importeTransferencia;

	/** The canal version. */
	@XmlElement(name = "CanalVersion")
	private String canalVersion;

	/** The tipo transferencia. */
	@XmlElement(name = "TipoTransferencia")
	private String tipoTransferencia;

	/** The version XML. */
	@XmlElement(name = "VersionXML")
	private String versionXML;

	/** The ip maquina. */
	@XmlElement(name = "IPMaquina")
	private String ipMaquina;

	/** The cuit 1. */
	@XmlElement(name = "Cuit1")
	private String cuit1;

	/** The long cta des bane. */
	@XmlElement(name = "LongCtaDesBane")
	private String longCtaDesBane;

	/** The cta bane. */
	@XmlElement(name = "CtaBane")
	private String ctaBane;

	/** The tpo cta to bane. */
	@XmlElement(name = "TpoCtaToBane")
	private String tpoCtaToBane;

	/** The tpo cta from bane. */
	@XmlElement(name = "TpoCtaFromBane")
	private String tpoCtaFromBane;

	/** The mail credito. */
	@XmlElement(name = "MailCredito")
	private String mailCredito;

	/** The cbu. */
	@XmlElement(name = "CBU")
	private String cbu;

	/** The fiid. */
	@XmlElement(name = "FIID")
	private String fiid;

	/** The user. */
	@XmlElement(name = "user")
	private String user;

	/** The banco receptor. */
	@XmlElement(name = "BancoReceptor")
	private String bancoReceptor;

	/** The banco destino. */
	@XmlElement(name = "BancoDestino")
	private String bancoDestino;

	/** The agenda. */
	@XmlElement(name = "Agenda")
	private String agenda;

	/**
	 * Gets the informac discrecional.
	 *
	 * @return the informac discrecional
	 */
	public String getInformacDiscrecional() {
		return informacDiscrecional;
	}

	/**
	 * Sets the informac discrecional.
	 *
	 * @param informacDiscrecional
	 *            the new informac discrecional
	 */
	public void setInformacDiscrecional(String informacDiscrecional) {
		this.informacDiscrecional = informacDiscrecional;
	}

	/**
	 * Gets the caracteristicas credito.
	 *
	 * @return the caracteristicas credito
	 */
	public String getCaracteristicasCredito() {
		return caracteristicasCredito;
	}

	/**
	 * Sets the caracteristicas credito.
	 *
	 * @param caracteristicasCredito
	 *            the new caracteristicas credito
	 */
	public void setCaracteristicasCredito(String caracteristicasCredito) {
		this.caracteristicasCredito = caracteristicasCredito;
	}

	/**
	 * Gets the moneda transferencia.
	 *
	 * @return the moneda transferencia
	 */
	public String getMonedaTransferencia() {
		return monedaTransferencia;
	}

	/**
	 * Sets the moneda transferencia.
	 *
	 * @param monedaTransferencia
	 *            the new moneda transferencia
	 */
	public void setMonedaTransferencia(String monedaTransferencia) {
		this.monedaTransferencia = monedaTransferencia;
	}

	/**
	 * Gets the usuario tipo.
	 *
	 * @return the usuario tipo
	 */
	public String getUsuarioTipo() {
		return usuarioTipo;
	}

	/**
	 * Sets the usuario tipo.
	 *
	 * @param usuarioTipo
	 *            the new usuario tipo
	 */
	public void setUsuarioTipo(String usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	/**
	 * Gets the marca gravado.
	 *
	 * @return the marca gravado
	 */
	public String getMarcaGravado() {
		return marcaGravado;
	}

	/**
	 * Sets the marca gravado.
	 *
	 * @param marcaGravado
	 *            the new marca gravado
	 */
	public void setMarcaGravado(String marcaGravado) {
		this.marcaGravado = marcaGravado;
	}

	/**
	 * Gets the logueo agenda historica.
	 *
	 * @return the logueo agenda historica
	 */
	public String getLogueoAgendaHistorica() {
		return logueoAgendaHistorica;
	}

	/**
	 * Sets the logueo agenda historica.
	 *
	 * @param logueoAgendaHistorica
	 *            the new logueo agenda historica
	 */
	public void setLogueoAgendaHistorica(String logueoAgendaHistorica) {
		this.logueoAgendaHistorica = logueoAgendaHistorica;
	}

	/**
	 * Gets the indicador funcion.
	 *
	 * @return the indicador funcion
	 */
	public String getIndicadorFuncion() {
		return IndicadorFuncion;
	}

	/**
	 * Sets the indicador funcion.
	 *
	 * @param indicadorFuncion
	 *            the new indicador funcion
	 */
	public void setIndicadorFuncion(String indicadorFuncion) {
		IndicadorFuncion = indicadorFuncion;
	}

	/**
	 * Gets the id sesion cnt.
	 *
	 * @return the id sesion cnt
	 */
	public String getIdSesionCnt() {
		return idSesionCnt;
	}

	/**
	 * Sets the id sesion cnt.
	 *
	 * @param idSesionCnt
	 *            the new id sesion cnt
	 */
	public void setIdSesionCnt(String idSesionCnt) {
		this.idSesionCnt = idSesionCnt;
	}

	/**
	 * Gets the ind auten.
	 *
	 * @return the ind auten
	 */
	public String getIndAuten() {
		return indAuten;
	}

	/**
	 * Sets the ind auten.
	 *
	 * @param indAuten
	 *            the new ind auten
	 */
	public void setIndAuten(String indAuten) {
		this.indAuten = indAuten;
	}

	/**
	 * Gets the subcanal id.
	 *
	 * @return the subcanal id
	 */
	public String getSubcanalId() {
		return subcanalId;
	}

	/**
	 * Sets the subcanal id.
	 *
	 * @param subcanalId
	 *            the new subcanal id
	 */
	public void setSubcanalId(String subcanalId) {
		this.subcanalId = subcanalId;
	}

	/**
	 * Gets the plazo acreditacion.
	 *
	 * @return the plazo acreditacion
	 */
	public String getPlazoAcreditacion() {
		return plazoAcreditacion;
	}

	/**
	 * Sets the plazo acreditacion.
	 *
	 * @param plazoAcreditacion
	 *            the new plazo acreditacion
	 */
	public void setPlazoAcreditacion(String plazoAcreditacion) {
		this.plazoAcreditacion = plazoAcreditacion;
	}

	/**
	 * Gets the digito cta credito.
	 *
	 * @return the digito cta credito
	 */
	public String getDigitoCtaCredito() {
		return digitoCtaCredito;
	}

	/**
	 * Sets the digito cta credito.
	 *
	 * @param digitoCtaCredito
	 *            the new digito cta credito
	 */
	public void setDigitoCtaCredito(String digitoCtaCredito) {
		this.digitoCtaCredito = digitoCtaCredito;
	}

	/**
	 * Gets the usuario atrib.
	 *
	 * @return the usuario atrib
	 */
	public String getUsuarioAtrib() {
		return usuarioAtrib;
	}

	/**
	 * Sets the usuario atrib.
	 *
	 * @param usuarioAtrib
	 *            the new usuario atrib
	 */
	public void setUsuarioAtrib(String usuarioAtrib) {
		this.usuarioAtrib = usuarioAtrib;
	}

	/**
	 * Gets the marca limite.
	 *
	 * @return the marca limite
	 */
	public String getMarcaLimite() {
		return marcaLimite;
	}

	/**
	 * Sets the marca limite.
	 *
	 * @param marcaLimite
	 *            the new marca limite
	 */
	public void setMarcaLimite(String marcaLimite) {
		this.marcaLimite = marcaLimite;
	}

	/**
	 * Gets the referencia univoca.
	 *
	 * @return the referencia univoca
	 */
	public String getReferenciaUnivoca() {
		return referenciaUnivoca;
	}

	/**
	 * Sets the referencia univoca.
	 *
	 * @param referenciaUnivoca
	 *            the new referencia univoca
	 */
	public void setReferenciaUnivoca(String referenciaUnivoca) {
		this.referenciaUnivoca = referenciaUnivoca;
	}

	/**
	 * Gets the identific beneficiario.
	 *
	 * @return the identific beneficiario
	 */
	public String getIdentificBeneficiario() {
		return identificBeneficiario;
	}

	/**
	 * Sets the identific beneficiario.
	 *
	 * @param identificBeneficiario
	 *            the new identific beneficiario
	 */
	public void setIdentificBeneficiario(String identificBeneficiario) {
		this.identificBeneficiario = identificBeneficiario;
	}

	/**
	 * Gets the firmante cta debito.
	 *
	 * @return the firmante cta debito
	 */
	public String getFirmanteCtaDebito() {
		return firmanteCtaDebito;
	}

	/**
	 * Sets the firmante cta debito.
	 *
	 * @param firmanteCtaDebito
	 *            the new firmante cta debito
	 */
	public void setFirmanteCtaDebito(String firmanteCtaDebito) {
		this.firmanteCtaDebito = firmanteCtaDebito;
	}

	/**
	 * Gets the canal id.
	 *
	 * @return the canal id
	 */
	public String getCanalId() {
		return canalId;
	}

	/**
	 * Sets the canal id.
	 *
	 * @param canalId
	 *            the new canal id
	 */
	public void setCanalId(String canalId) {
		this.canalId = canalId;
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
	 *            the new periodica
	 */
	public void setPeriodica(String periodica) {
		this.periodica = periodica;
	}

	/**
	 * Gets the eco datos entrada.
	 *
	 * @return the eco datos entrada
	 */
	public String getEcoDatosEntrada() {
		return ecoDatosEntrada;
	}

	/**
	 * Sets the eco datos entrada.
	 *
	 * @param ecoDatosEntrada
	 *            the new eco datos entrada
	 */
	public void setEcoDatosEntrada(String ecoDatosEntrada) {
		this.ecoDatosEntrada = ecoDatosEntrada;
	}

	/**
	 * Gets the cantidad dias.
	 *
	 * @return the cantidad dias
	 */
	public String getCantidadDias() {
		return cantidadDias;
	}

	/**
	 * Sets the cantidad dias.
	 *
	 * @param cantidadDias
	 *            the new cantidad dias
	 */
	public void setCantidadDias(String cantidadDias) {
		this.cantidadDias = cantidadDias;
	}

	/**
	 * Gets the entidad cta credito.
	 *
	 * @return the entidad cta credito
	 */
	public String getEntidadCtaCredito() {
		return entidadCtaCredito;
	}

	/**
	 * Sets the entidad cta credito.
	 *
	 * @param entidadCtaCredito
	 *            the new entidad cta credito
	 */
	public void setEntidadCtaCredito(String entidadCtaCredito) {
		this.entidadCtaCredito = entidadCtaCredito;
	}

	/**
	 * Gets the importe transferencia.
	 *
	 * @return the importe transferencia
	 */
	public String getImporteTransferencia() {
		return importeTransferencia;
	}

	/**
	 * Sets the importe transferencia.
	 *
	 * @param importeTransferencia
	 *            the new importe transferencia
	 */
	public void setImporteTransferencia(String importeTransferencia) {
		this.importeTransferencia = importeTransferencia;
	}

	/**
	 * Gets the canal version.
	 *
	 * @return the canal version
	 */
	public String getCanalVersion() {
		return canalVersion;
	}

	/**
	 * Sets the canal version.
	 *
	 * @param canalVersion
	 *            the new canal version
	 */
	public void setCanalVersion(String canalVersion) {
		this.canalVersion = canalVersion;
	}

	/**
	 * Gets the tipo transferencia.
	 *
	 * @return the tipo transferencia
	 */
	public String getTipoTransferencia() {
		return tipoTransferencia;
	}

	/**
	 * Sets the tipo transferencia.
	 *
	 * @param tipoTransferencia
	 *            the new tipo transferencia
	 */
	public void setTipoTransferencia(String tipoTransferencia) {
		this.tipoTransferencia = tipoTransferencia;
	}

	/**
	 * Gets the version XML.
	 *
	 * @return the version XML
	 */
	public String getVersionXML() {
		return versionXML;
	}

	/**
	 * Sets the version XML.
	 *
	 * @param versionXML
	 *            the new version XML
	 */
	public void setVersionXML(String versionXML) {
		this.versionXML = versionXML;
	}

	/**
	 * Gets the ip maquina.
	 *
	 * @return the ip maquina
	 */
	public String getIpMaquina() {
		return ipMaquina;
	}

	/**
	 * Sets the ip maquina.
	 *
	 * @param ipMaquina
	 *            the new ip maquina
	 */
	public void setIpMaquina(String ipMaquina) {
		this.ipMaquina = ipMaquina;
	}

	/**
	 * Gets the cuit 1.
	 *
	 * @return the cuit 1
	 */
	public String getCuit1() {
		return cuit1;
	}

	/**
	 * Sets the cuit 1.
	 *
	 * @param cuit1
	 *            the new cuit 1
	 */
	public void setCuit1(String cuit1) {
		this.cuit1 = cuit1;
	}

	/**
	 * Gets the long cta des bane.
	 *
	 * @return the long cta des bane
	 */
	public String getLongCtaDesBane() {
		return longCtaDesBane;
	}

	/**
	 * Sets the long cta des bane.
	 *
	 * @param longCtaDesBane
	 *            the new long cta des bane
	 */
	public void setLongCtaDesBane(String longCtaDesBane) {
		this.longCtaDesBane = longCtaDesBane;
	}

	/**
	 * Gets the cta bane.
	 *
	 * @return the cta bane
	 */
	public String getCtaBane() {
		return ctaBane;
	}

	/**
	 * Sets the cta bane.
	 *
	 * @param ctaBane
	 *            the new cta bane
	 */
	public void setCtaBane(String ctaBane) {
		this.ctaBane = ctaBane;
	}

	/**
	 * Gets the tpo cta to bane.
	 *
	 * @return the tpo cta to bane
	 */
	public String getTpoCtaToBane() {
		return tpoCtaToBane;
	}

	/**
	 * Sets the tpo cta to bane.
	 *
	 * @param tpoCtaToBane
	 *            the new tpo cta to bane
	 */
	public void setTpoCtaToBane(String tpoCtaToBane) {
		this.tpoCtaToBane = tpoCtaToBane;
	}

	/**
	 * Gets the tpo cta from bane.
	 *
	 * @return the tpo cta from bane
	 */
	public String getTpoCtaFromBane() {
		return tpoCtaFromBane;
	}

	/**
	 * Sets the tpo cta from bane.
	 *
	 * @param tpoCtaFromBane
	 *            the new tpo cta from bane
	 */
	public void setTpoCtaFromBane(String tpoCtaFromBane) {
		this.tpoCtaFromBane = tpoCtaFromBane;
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
	 *            the new fiid
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
	 *            the new user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets the banco receptor.
	 *
	 * @return the banco receptor
	 */
	public String getBancoReceptor() {
		return bancoReceptor;
	}

	/**
	 * Sets the banco receptor.
	 *
	 * @param bancoReceptor
	 *            the new banco receptor
	 */
	public void setBancoReceptor(String bancoReceptor) {
		this.bancoReceptor = bancoReceptor;
	}

	/**
	 * Gets the banco destino.
	 *
	 * @return the banco destino
	 */
	public String getBancoDestino() {
		return bancoDestino;
	}

	/**
	 * Sets the banco destino.
	 *
	 * @param bancoDestino
	 *            the new banco destino
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
	 *            the new agenda
	 */
	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	/**
	 * Gets the mail credito.
	 *
	 * @return the mail credito
	 */
	public String getMailCredito() {
		return mailCredito;
	}

	/**
	 * Sets the mail credito.
	 *
	 * @param mailCredito
	 *            the new mail credito
	 */
	public void setMailCredito(String mailCredito) {
		this.mailCredito = mailCredito;
	}

}

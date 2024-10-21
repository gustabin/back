/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class CNSAgendaRegResponse.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CNSAgendaRegResponse {

	/** The id def. */
	@XmlElement(name = "IdDef")
	protected String idDef;

	/** The tx servicio. */
	@XmlElement(name = "TxServicio")
	protected String txServicio;

	/** The tx version. */
	@XmlElement(name = "TxVersion")
	protected String txVersion;

	/** The nup. */
	@XmlElement(name = "Nup")
	protected String nup;

	/** The cta orig tipo. */
	@XmlElement(name = "CtaOrigTipo")
	protected String ctaOrigTipo;

	/** The cta orig suc. */
	@XmlElement(name = "CtaOrigSuc")
	protected String ctaOrigSuc;

	/** The cta orig num. */
	@XmlElement(name = "CtaOrigNum")
	protected String ctaOrigNum;

	/** The cta dest tipo. */
	@XmlElement(name = "CtaDestTipo")
	protected String ctaDestTipo;

	/** The cta dest suc. */
	@XmlElement(name = "CtaDestSuc")
	protected String ctaDestSuc;

	/** The cta dest num. */
	@XmlElement(name = "CtaDestNum")
	protected String ctaDestNum;

	/** The cta orig tipo mig. */
	@XmlElement(name = "CtaOrigTipoMig")
	protected String ctaOrigTipoMig;

	/** The cta orig suc mig. */
	@XmlElement(name = "CtaOrigSucMig")
	protected String ctaOrigSucMig;

	/** The cta orig num mig. */
	@XmlElement(name = "CtaOrigNumMig")
	protected String ctaOrigNumMig;

	/** The cta dest tipo mig. */
	@XmlElement(name = "CtaDestTipoMig")
	protected String ctaDestTipoMig;

	/** The cta dest suc mig. */
	@XmlElement(name = "CtaDestSucMig")
	protected String ctaDestSucMig;

	/** The cta dest num mig. */
	@XmlElement(name = "CtaDestNumMig")
	protected String ctaDestNumMig;

	/** The tpo doc. */
	@XmlElement(name = "TpoDoc")
	protected String tpoDoc;

	/** The nro doc. */
	@XmlElement(name = "NroDoc")
	protected String nroDoc;

	/** The canal. */
	@XmlElement(name = "Canal")
	protected String canal;

	/** The subcanal. */
	@XmlElement(name = "Subcanal")
	protected String subcanal;

	/** The xml. */
	@XmlElement(name = "Xml")
	protected String xml;

	/** The id datos rec. */
	@XmlElement(name = "IdDatosRec")
	protected String idDatosRec;

	/** The fecha base rec. */
	@XmlElement(name = "FechaBaseRec")
	protected String fechaBaseRec;

	/** The frecuencia rec. */
	@XmlElement(name = "FrecuenciaRec")
	protected String frecuenciaRec;

	/** The tipo rec. */
	@XmlElement(name = "TipoRec")
	protected String tipoRec;

	/** The maxima rec. */
	@XmlElement(name = "MaximaRec")
	protected String maximaRec;

	/** The maxima FV. */
	@XmlElement(name = "MaximaFV")
	protected String maximaFV;

	/** The accion FV. */
	@XmlElement(name = "AccionFV")
	protected String accionFV;

	/** The dias aviso previo. */
	@XmlElement(name = "DiasAvisoPrevio")
	protected String diasAvisoPrevio;

	/** The id evento. */
	@XmlElement(name = "IdEvento")
	protected String idEvento;

	/** The fecha ejecucion. */
	@XmlElement(name = "FechaEjecucion")
	protected String fechaEjecucion;

	/** The fecha base. */
	@XmlElement(name = "FechaBase")
	protected String fechaBase;

	/** The recurrencia. */
	@XmlElement(name = "Recurrencia")
	protected String recurrencia;

	/** The reintentos. */
	@XmlElement(name = "Reintentos")
	protected String reintentos;

	/** The cant FV. */
	@XmlElement(name = "CantFV")
	protected String cantFV;

	/** The tipo agendamiento. */
	@XmlElement(name = "TipoAgendamiento")
	protected String tipoAgendamiento;

	/** The estado. */
	@XmlElement(name = "Estado")
	protected String estado;

	/** The usuario. */
	@XmlElement(name = "Usuario")
	protected String usuario;

	/** The sistema. */
	@XmlElement(name = "Sistema")
	protected String sistema;

	/** The accion. */
	@XmlElement(name = "Accion")
	protected String accion;

	/** The fecha. */
	@XmlElement(name = "Fecha")
	protected String fecha;

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
	 * Gets the tx servicio.
	 *
	 * @return the txServicio
	 */
	public String getTxServicio() {
		return txServicio;
	}

	/**
	 * Sets the tx servicio.
	 *
	 * @param txServicio
	 *            the txServicio to set
	 */
	public void setTxServicio(String txServicio) {
		this.txServicio = txServicio;
	}

	/**
	 * Gets the tx version.
	 *
	 * @return the txVersion
	 */
	public String getTxVersion() {
		return txVersion;
	}

	/**
	 * Sets the tx version.
	 *
	 * @param txVersion
	 *            the txVersion to set
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
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the cta orig tipo.
	 *
	 * @return the ctaOrigTipo
	 */
	public String getCtaOrigTipo() {
		return ctaOrigTipo;
	}

	/**
	 * Sets the cta orig tipo.
	 *
	 * @param ctaOrigTipo
	 *            the ctaOrigTipo to set
	 */
	public void setCtaOrigTipo(String ctaOrigTipo) {
		this.ctaOrigTipo = ctaOrigTipo;
	}

	/**
	 * Gets the cta orig suc.
	 *
	 * @return the ctaOrigSuc
	 */
	public String getCtaOrigSuc() {
		return ctaOrigSuc;
	}

	/**
	 * Sets the cta orig suc.
	 *
	 * @param ctaOrigSuc
	 *            the ctaOrigSuc to set
	 */
	public void setCtaOrigSuc(String ctaOrigSuc) {
		this.ctaOrigSuc = ctaOrigSuc;
	}

	/**
	 * Gets the cta orig num.
	 *
	 * @return the ctaOrigNum
	 */
	public String getCtaOrigNum() {
		return ctaOrigNum;
	}

	/**
	 * Sets the cta orig num.
	 *
	 * @param ctaOrigNum
	 *            the ctaOrigNum to set
	 */
	public void setCtaOrigNum(String ctaOrigNum) {
		this.ctaOrigNum = ctaOrigNum;
	}

	/**
	 * Gets the cta dest tipo.
	 *
	 * @return the ctaDestTipo
	 */
	public String getCtaDestTipo() {
		return ctaDestTipo;
	}

	/**
	 * Sets the cta dest tipo.
	 *
	 * @param ctaDestTipo
	 *            the ctaDestTipo to set
	 */
	public void setCtaDestTipo(String ctaDestTipo) {
		this.ctaDestTipo = ctaDestTipo;
	}

	/**
	 * Gets the cta dest suc.
	 *
	 * @return the ctaDestSuc
	 */
	public String getCtaDestSuc() {
		return ctaDestSuc;
	}

	/**
	 * Sets the cta dest suc.
	 *
	 * @param ctaDestSuc
	 *            the ctaDestSuc to set
	 */
	public void setCtaDestSuc(String ctaDestSuc) {
		this.ctaDestSuc = ctaDestSuc;
	}

	/**
	 * Gets the cta dest num.
	 *
	 * @return the ctaDestNum
	 */
	public String getCtaDestNum() {
		return ctaDestNum;
	}

	/**
	 * Sets the cta dest num.
	 *
	 * @param ctaDestNum
	 *            the ctaDestNum to set
	 */
	public void setCtaDestNum(String ctaDestNum) {
		this.ctaDestNum = ctaDestNum;
	}

	/**
	 * Gets the cta orig tipo mig.
	 *
	 * @return the ctaOrigTipoMig
	 */
	public String getCtaOrigTipoMig() {
		return ctaOrigTipoMig;
	}

	/**
	 * Sets the cta orig tipo mig.
	 *
	 * @param ctaOrigTipoMig
	 *            the ctaOrigTipoMig to set
	 */
	public void setCtaOrigTipoMig(String ctaOrigTipoMig) {
		this.ctaOrigTipoMig = ctaOrigTipoMig;
	}

	/**
	 * Gets the cta orig suc mig.
	 *
	 * @return the ctaOrigSucMig
	 */
	public String getCtaOrigSucMig() {
		return ctaOrigSucMig;
	}

	/**
	 * Sets the cta orig suc mig.
	 *
	 * @param ctaOrigSucMig
	 *            the ctaOrigSucMig to set
	 */
	public void setCtaOrigSucMig(String ctaOrigSucMig) {
		this.ctaOrigSucMig = ctaOrigSucMig;
	}

	/**
	 * Gets the cta orig num mig.
	 *
	 * @return the ctaOrigNumMig
	 */
	public String getCtaOrigNumMig() {
		return ctaOrigNumMig;
	}

	/**
	 * Sets the cta orig num mig.
	 *
	 * @param ctaOrigNumMig
	 *            the ctaOrigNumMig to set
	 */
	public void setCtaOrigNumMig(String ctaOrigNumMig) {
		this.ctaOrigNumMig = ctaOrigNumMig;
	}

	/**
	 * Gets the cta dest tipo mig.
	 *
	 * @return the ctaDestTipoMig
	 */
	public String getCtaDestTipoMig() {
		return ctaDestTipoMig;
	}

	/**
	 * Sets the cta dest tipo mig.
	 *
	 * @param ctaDestTipoMig
	 *            the ctaDestTipoMig to set
	 */
	public void setCtaDestTipoMig(String ctaDestTipoMig) {
		this.ctaDestTipoMig = ctaDestTipoMig;
	}

	/**
	 * Gets the cta dest suc mig.
	 *
	 * @return the ctaDestSucMig
	 */
	public String getCtaDestSucMig() {
		return ctaDestSucMig;
	}

	/**
	 * Sets the cta dest suc mig.
	 *
	 * @param ctaDestSucMig
	 *            the ctaDestSucMig to set
	 */
	public void setCtaDestSucMig(String ctaDestSucMig) {
		this.ctaDestSucMig = ctaDestSucMig;
	}

	/**
	 * Gets the cta dest num mig.
	 *
	 * @return the ctaDestNumMig
	 */
	public String getCtaDestNumMig() {
		return ctaDestNumMig;
	}

	/**
	 * Sets the cta dest num mig.
	 *
	 * @param ctaDestNumMig
	 *            the ctaDestNumMig to set
	 */
	public void setCtaDestNumMig(String ctaDestNumMig) {
		this.ctaDestNumMig = ctaDestNumMig;
	}

	/**
	 * Gets the tpo doc.
	 *
	 * @return the tpoDoc
	 */
	public String getTpoDoc() {
		return tpoDoc;
	}

	/**
	 * Sets the tpo doc.
	 *
	 * @param tpoDoc
	 *            the tpoDoc to set
	 */
	public void setTpoDoc(String tpoDoc) {
		this.tpoDoc = tpoDoc;
	}

	/**
	 * Gets the nro doc.
	 *
	 * @return the nroDoc
	 */
	public String getNroDoc() {
		return nroDoc;
	}

	/**
	 * Sets the nro doc.
	 *
	 * @param nroDoc
	 *            the nroDoc to set
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
	 *            the canal to set
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
	 *            the subcanal to set
	 */
	public void setSubcanal(String subcanal) {
		this.subcanal = subcanal;
	}

	/**
	 * Gets the xml.
	 *
	 * @return the xml
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * Sets the xml.
	 *
	 * @param xml
	 *            the xml to set
	 */
	public void setXml(String xml) {
		this.xml = xml;
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
	 * Gets the fecha base rec.
	 *
	 * @return the fechaBaseRec
	 */
	public String getFechaBaseRec() {
		return fechaBaseRec;
	}

	/**
	 * Sets the fecha base rec.
	 *
	 * @param fechaBaseRec
	 *            the fechaBaseRec to set
	 */
	public void setFechaBaseRec(String fechaBaseRec) {
		this.fechaBaseRec = fechaBaseRec;
	}

	/**
	 * Gets the frecuencia rec.
	 *
	 * @return the frecuenciaRec
	 */
	public String getFrecuenciaRec() {
		return frecuenciaRec;
	}

	/**
	 * Sets the frecuencia rec.
	 *
	 * @param frecuenciaRec
	 *            the frecuenciaRec to set
	 */
	public void setFrecuenciaRec(String frecuenciaRec) {
		this.frecuenciaRec = frecuenciaRec;
	}

	/**
	 * Gets the tipo rec.
	 *
	 * @return the tipoRec
	 */
	public String getTipoRec() {
		return tipoRec;
	}

	/**
	 * Sets the tipo rec.
	 *
	 * @param tipoRec
	 *            the tipoRec to set
	 */
	public void setTipoRec(String tipoRec) {
		this.tipoRec = tipoRec;
	}

	/**
	 * Gets the maxima rec.
	 *
	 * @return the maximaRec
	 */
	public String getMaximaRec() {
		return maximaRec;
	}

	/**
	 * Sets the maxima rec.
	 *
	 * @param maximaRec
	 *            the maximaRec to set
	 */
	public void setMaximaRec(String maximaRec) {
		this.maximaRec = maximaRec;
	}

	/**
	 * Gets the maxima FV.
	 *
	 * @return the maximaFV
	 */
	public String getMaximaFV() {
		return maximaFV;
	}

	/**
	 * Sets the maxima FV.
	 *
	 * @param maximaFV
	 *            the maximaFV to set
	 */
	public void setMaximaFV(String maximaFV) {
		this.maximaFV = maximaFV;
	}

	/**
	 * Gets the accion FV.
	 *
	 * @return the accionFV
	 */
	public String getAccionFV() {
		return accionFV;
	}

	/**
	 * Sets the accion FV.
	 *
	 * @param accionFV
	 *            the accionFV to set
	 */
	public void setAccionFV(String accionFV) {
		this.accionFV = accionFV;
	}

	/**
	 * Gets the dias aviso previo.
	 *
	 * @return the diasAvisoPrevio
	 */
	public String getDiasAvisoPrevio() {
		return diasAvisoPrevio;
	}

	/**
	 * Sets the dias aviso previo.
	 *
	 * @param diasAvisoPrevio
	 *            the diasAvisoPrevio to set
	 */
	public void setDiasAvisoPrevio(String diasAvisoPrevio) {
		this.diasAvisoPrevio = diasAvisoPrevio;
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
	 * Gets the fecha base.
	 *
	 * @return the fechaBase
	 */
	public String getFechaBase() {
		return fechaBase;
	}

	/**
	 * Sets the fecha base.
	 *
	 * @param fechaBase
	 *            the fechaBase to set
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
	 *            the recurrencia to set
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
	 *            the reintentos to set
	 */
	public void setReintentos(String reintentos) {
		this.reintentos = reintentos;
	}

	/**
	 * Gets the cant FV.
	 *
	 * @return the cantFV
	 */
	public String getCantFV() {
		return cantFV;
	}

	/**
	 * Sets the cant FV.
	 *
	 * @param cantFV
	 *            the cantFV to set
	 */
	public void setCantFV(String cantFV) {
		this.cantFV = cantFV;
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
	 *            the estado to set
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
	 *            the usuario to set
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
	 *            the sistema to set
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
	 *            the accion to set
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
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}

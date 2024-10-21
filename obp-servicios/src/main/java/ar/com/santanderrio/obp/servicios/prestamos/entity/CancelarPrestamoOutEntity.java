package ar.com.santanderrio.obp.servicios.prestamos.entity;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

@Record
public class CancelarPrestamoOutEntity {

	public static final String DELIMITER = "õ";

	@Field
	private String headerTrama;

	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;
	
	@Field
	private String ccc;

	@Field
	private String divisa;

	@Field
	private String oficinaOper;

	@Field
	private String entidadOper;

	@Field
	private String feValor;

	@Field
	private String enTit;

	@Field
	private String ofiTit;

	@Field
	private String codEvento;

	@Field
	private String nio;

	@Field
	private String feOper;

	@Field
	private String codFaseProm;

	@Field
	private String secuenProp;

	@Field
	private String impConce;

	@Field
	private String disponible;

	@Field
	private String salReal;

	@Field
	private String impCuota;

	@Field
	private String feVencim;

	@Field
	private String importeCargo;

	@Field
	private String codDivisaPago;

	@Field
	private String indFormCargo;

	@Field
	private String ccaCargo;

	@Field
	private String docCargo;

	@Field
	private String importeAbono;

	@Field
	private String codDivisaAbono;

	@Field
	private String indFormAbono;

	@Field
	private String ccaAbono;

	@Field
	private String docAbono;

	@Field
	private String peTitDoc;

	@Field
	private String peNumDoc;

	@Field
	private String pePriApe;

	@Field
	private String peSegApe;

	@Field
	private String peNomPer;

	@Field
	private String peNomCal;

	@Field
	private String peNumBlo;

	@Field
	private String peNomLoc;

	@Field
	private String peNomCom;

	@Field
	private String peCodPos;

	@Field
	private String pePreTel;

	@Field
	private String peNumTel;

	@Field
	private String tasa;

	@Field
	private String tea;

	@Field
	private String respoIva;

	@Field
	private String impCap;

	@Field
	private String impInt;

	@Field
	private String iva1;

	@Field
	private String iva2;

	@Field
	private String impDeuFinanc;

	@Field
	private String ingresosBrutos;

	@Field
	private String restoImpuestos;

	@Field
	private String totComision;

	@Field
	private String totGastos;

	@Field
	private String totSeguros;

	@Field
	private String total;

	@Field
	private String saldoPrev;

	@Field
	private String impAjusSal;

	@Field
	private String factorIndex;

	@Field
	private String impAjusCap;

	@Field
	private String coeficiIndex;

	@Field
	private String cft;

	@Field
	private String cftSimp;

	@Field
	private String capInt;

	@Field
	private String cuentaPrestamo;

	@Field
	private String unidadExposicion;

	@Field
	private String cotCambioUniExpo;

	@Field
	private String fechaCotizacion;

	@Field
	private String importeCapital;

	@Field
	private String importeIntereses;

	@Field
	private String importeTotal;

	@Field
	private String saldoPrevio;

	@Field
	private String ajusteSaldo;

	@Field
	private String saldoPrevioAjustado;

	private String nroComprobante;

	private String fecha;
	
	private String mensajeErrorServicio;

	private Boolean tieneError = Boolean.FALSE;
	
	
	public String getHeaderTrama() {
		return headerTrama;
	}

	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	public String getCcc() {
		return ccc;
	}

	public void setCcc(String ccc) {
		this.ccc = ccc;
	}

	public String getDivisa() {
		return divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	public String getOficinaOper() {
		return oficinaOper;
	}

	public void setOficinaOper(String oficinaOper) {
		this.oficinaOper = oficinaOper;
	}

	public String getEntidadOper() {
		return entidadOper;
	}

	public void setEntidadOper(String entidadOper) {
		this.entidadOper = entidadOper;
	}

	public String getFeValor() {
		return feValor;
	}

	public void setFeValor(String feValor) {
		this.feValor = feValor;
	}

	public String getEnTit() {
		return enTit;
	}

	public void setEnTit(String enTit) {
		this.enTit = enTit;
	}

	public String getOfiTit() {
		return ofiTit;
	}

	public void setOfiTit(String ofiTit) {
		this.ofiTit = ofiTit;
	}

	public String getCodEvento() {
		return codEvento;
	}

	public void setCodEvento(String codEvento) {
		this.codEvento = codEvento;
	}

	public String getNio() {
		return nio;
	}

	public void setNio(String nio) {
		this.nio = nio;
	}

	public String getFeOper() {
		return feOper;
	}

	public void setFeOper(String feOper) {
		this.feOper = feOper;
	}

	public String getCodFaseProm() {
		return codFaseProm;
	}

	public void setCodFaseProm(String codFaseProm) {
		this.codFaseProm = codFaseProm;
	}

	public String getSecuenProp() {
		return secuenProp;
	}

	public void setSecuenProp(String secuenProp) {
		this.secuenProp = secuenProp;
	}

	public String getImpConce() {
		return impConce;
	}

	public void setImpConce(String impConce) {
		this.impConce = impConce;
	}

	public String getDisponible() {
		return disponible;
	}

	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}

	public String getSalReal() {
		return salReal;
	}

	public void setSalReal(String salReal) {
		this.salReal = salReal;
	}

	public String getImpCuota() {
		return impCuota;
	}

	public void setImpCuota(String impCuota) {
		this.impCuota = impCuota;
	}

	public String getFeVencim() {
		return feVencim;
	}

	public void setFeVencim(String feVencim) {
		this.feVencim = feVencim;
	}

	public String getImporteCargo() {
		return importeCargo;
	}

	public void setImporteCargo(String importeCargo) {
		this.importeCargo = importeCargo;
	}

	public String getCodDivisaPago() {
		return codDivisaPago;
	}

	public void setCodDivisaPago(String codDivisaPago) {
		this.codDivisaPago = codDivisaPago;
	}

	public String getIndFormCargo() {
		return indFormCargo;
	}

	public void setIndFormCargo(String indFormCargo) {
		this.indFormCargo = indFormCargo;
	}

	public String getCcaCargo() {
		return ccaCargo;
	}

	public void setCcaCargo(String ccaCargo) {
		this.ccaCargo = ccaCargo;
	}

	public String getDocCargo() {
		return docCargo;
	}

	public void setDocCargo(String docCargo) {
		this.docCargo = docCargo;
	}

	public String getImporteAbono() {
		return importeAbono;
	}

	public void setImporteAbono(String importeAbono) {
		this.importeAbono = importeAbono;
	}

	public String getCodDivisaAbono() {
		return codDivisaAbono;
	}

	public void setCodDivisaAbono(String codDivisaAbono) {
		this.codDivisaAbono = codDivisaAbono;
	}

	public String getIndFormAbono() {
		return indFormAbono;
	}

	public void setIndFormAbono(String indFormAbono) {
		this.indFormAbono = indFormAbono;
	}

	public String getCcaAbono() {
		return ccaAbono;
	}

	public void setCcaAbono(String ccaAbono) {
		this.ccaAbono = ccaAbono;
	}

	public String getDocAbono() {
		return docAbono;
	}

	public void setDocAbono(String docAbono) {
		this.docAbono = docAbono;
	}

	public String getPeTitDoc() {
		return peTitDoc;
	}

	public void setPeTitDoc(String peTitDoc) {
		this.peTitDoc = peTitDoc;
	}

	public String getPeNumDoc() {
		return peNumDoc;
	}

	public void setPeNumDoc(String peNumDoc) {
		this.peNumDoc = peNumDoc;
	}

	public String getPePriApe() {
		return pePriApe;
	}

	public void setPePriApe(String pePriApe) {
		this.pePriApe = pePriApe;
	}

	public String getPeSegApe() {
		return peSegApe;
	}

	public void setPeSegApe(String peSegApe) {
		this.peSegApe = peSegApe;
	}

	public String getPeNomPer() {
		return peNomPer;
	}

	public void setPeNomPer(String peNomPer) {
		this.peNomPer = peNomPer;
	}

	public String getPeNomCal() {
		return peNomCal;
	}

	public void setPeNomCal(String peNomCal) {
		this.peNomCal = peNomCal;
	}

	public String getPeNumBlo() {
		return peNumBlo;
	}

	public void setPeNumBlo(String peNumBlo) {
		this.peNumBlo = peNumBlo;
	}

	public String getPeNomLoc() {
		return peNomLoc;
	}

	public void setPeNomLoc(String peNomLoc) {
		this.peNomLoc = peNomLoc;
	}

	public String getPeNomCom() {
		return peNomCom;
	}

	public void setPeNomCom(String peNomCom) {
		this.peNomCom = peNomCom;
	}

	public String getPeCodPos() {
		return peCodPos;
	}

	public void setPeCodPos(String peCodPos) {
		this.peCodPos = peCodPos;
	}

	public String getPePreTel() {
		return pePreTel;
	}

	public void setPePreTel(String pePreTel) {
		this.pePreTel = pePreTel;
	}

	public String getPeNumTel() {
		return peNumTel;
	}

	public void setPeNumTel(String peNumTel) {
		this.peNumTel = peNumTel;
	}

	public String getTasa() {
		return tasa;
	}

	public void setTasa(String tasa) {
		this.tasa = tasa;
	}

	public String getTea() {
		return tea;
	}

	public void setTea(String tea) {
		this.tea = tea;
	}

	public String getRespoIva() {
		return respoIva;
	}

	public void setRespoIva(String respoIva) {
		this.respoIva = respoIva;
	}

	public String getImpCap() {
		return impCap;
	}

	public void setImpCap(String impCap) {
		this.impCap = impCap;
	}

	public String getImpInt() {
		return impInt;
	}

	public void setImpInt(String impInt) {
		this.impInt = impInt;
	}

	public String getIva1() {
		return iva1;
	}

	public void setIva1(String iva1) {
		this.iva1 = iva1;
	}

	public String getIva2() {
		return iva2;
	}

	public void setIva2(String iva2) {
		this.iva2 = iva2;
	}

	public String getImpDeuFinanc() {
		return impDeuFinanc;
	}

	public void setImpDeuFinanc(String impDeuFinanc) {
		this.impDeuFinanc = impDeuFinanc;
	}

	public String getIngresosBrutos() {
		return ingresosBrutos;
	}

	public void setIngresosBrutos(String ingresosBrutos) {
		this.ingresosBrutos = ingresosBrutos;
	}

	public String getRestoImpuestos() {
		return restoImpuestos;
	}

	public void setRestoImpuestos(String restoImpuestos) {
		this.restoImpuestos = restoImpuestos;
	}

	public String getTotComision() {
		return totComision;
	}

	public void setTotComision(String totComision) {
		this.totComision = totComision;
	}

	public String getTotGastos() {
		return totGastos;
	}

	public void setTotGastos(String totGastos) {
		this.totGastos = totGastos;
	}

	public String getTotSeguros() {
		return totSeguros;
	}

	public void setTotSeguros(String totSeguros) {
		this.totSeguros = totSeguros;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getSaldoPrev() {
		return saldoPrev;
	}

	public void setSaldoPrev(String saldoPrev) {
		this.saldoPrev = saldoPrev;
	}

	public String getImpAjusSal() {
		return impAjusSal;
	}

	public void setImpAjusSal(String impAjusSal) {
		this.impAjusSal = impAjusSal;
	}

	public String getFactorIndex() {
		return factorIndex;
	}

	public void setFactorIndex(String factorIndex) {
		this.factorIndex = factorIndex;
	}

	public String getImpAjusCap() {
		return impAjusCap;
	}

	public void setImpAjusCap(String impAjusCap) {
		this.impAjusCap = impAjusCap;
	}

	public String getCoeficiIndex() {
		return coeficiIndex;
	}

	public void setCoeficiIndex(String coeficiIndex) {
		this.coeficiIndex = coeficiIndex;
	}

	public String getCft() {
		return cft;
	}

	public void setCft(String cft) {
		this.cft = cft;
	}

	public String getCftSimp() {
		return cftSimp;
	}

	public void setCftSimp(String cftSimp) {
		this.cftSimp = cftSimp;
	}

	public String getCapInt() {
		return capInt;
	}

	public void setCapInt(String capInt) {
		this.capInt = capInt;
	}

	public String getCuentaPrestamo() {
		return cuentaPrestamo;
	}

	public void setCuentaPrestamo(String cuentaPrestamo) {
		this.cuentaPrestamo = cuentaPrestamo;
	}

	public String getUnidadExposicion() {
		return unidadExposicion;
	}

	public void setUnidadExposicion(String unidadExposicion) {
		this.unidadExposicion = unidadExposicion;
	}

	public String getCotCambioUniExpo() {
		return cotCambioUniExpo;
	}

	public void setCotCambioUniExpo(String cotCambioUniExpo) {
		this.cotCambioUniExpo = cotCambioUniExpo;
	}

	public String getFechaCotizacion() {
		return fechaCotizacion;
	}

	public void setFechaCotizacion(String fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}

	public String getImporteCapital() {
		return importeCapital;
	}

	public void setImporteCapital(String importeCapital) {
		this.importeCapital = importeCapital;
	}

	public String getImporteIntereses() {
		return importeIntereses;
	}

	public void setImporteIntereses(String importeIntereses) {
		this.importeIntereses = importeIntereses;
	}

	public String getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getSaldoPrevio() {
		return saldoPrevio;
	}

	public void setSaldoPrevio(String saldoPrevio) {
		this.saldoPrevio = saldoPrevio;
	}

	public String getAjusteSaldo() {
		return ajusteSaldo;
	}

	public void setAjusteSaldo(String ajusteSaldo) {
		this.ajusteSaldo = ajusteSaldo;
	}

	public String getSaldoPrevioAjustado() {
		return saldoPrevioAjustado;
	}

	public void setSaldoPrevioAjustado(String saldoPrevioAjustado) {
		this.saldoPrevioAjustado = saldoPrevioAjustado;
	}

	public String getNroComprobante() {
		return nroComprobante;
	}

	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMensajeErrorServicio() {
		return mensajeErrorServicio;
	}

	public void setMensajeErrorServicio(String mensajeErrorServicio) {
		this.mensajeErrorServicio = mensajeErrorServicio;
	}

	public Boolean getTieneError() {
		return tieneError;
	}

	public void setTieneError(Boolean tieneError) {
		this.tieneError = tieneError;
	}
}

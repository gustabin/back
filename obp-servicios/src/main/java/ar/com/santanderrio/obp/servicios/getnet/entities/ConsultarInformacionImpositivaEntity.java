package ar.com.santanderrio.obp.servicios.getnet.entities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.beanio.annotation.Field;

public class ConsultarInformacionImpositivaEntity {

	/** numeroCliente. */
	@Field
	private String numeroCliente;
	
	/** secAnteCbioSitImp. */
	@Field
	private String secAnteCbioSitImp;
	
	/** fInicioSitImpositiva. */
	@Field
	private String fInicioSitImpositiva;
	
	/** fFinSitImpositiva. */
	@Field
	private String fFinSitImpositiva;
	
	/** agDePercepIngBrutos. */
	@Field
	private String agDePercepIngBrutos;
	
	/** respAnteIVA. */
	@Field
	private String respAnteIVA;
	
	/** constTipoRespAFIP. */
	@Field
	private String constTipoRespAFIP;
	
	/** certifExclPerc3337. */
	@Field
	private String certifExclPerc3337;
	
	/** fecInicioExclusion. */
	@Field
	private String fecInicioExclusion;
	
	/** fecVtoExclusion. */
	@Field
	private String fecVtoExclusion;
	
	/** respAnteGcias. */
	@Field
	private String respAnteGcias;
	
	/** certifNoRetencion. */
	@Field
	private String certifNoRetencion;
	
	/** pjeNoRetGcias */
	@Field
	private String pjeNoRetGcias;
	
	/** fecInicioCert. */
	@Field
	private String fecInicioCert;
	
	/** fecVtoCert. */
	@Field
	private String fecVtoCert;
	
	/** certifExencion. */
	@Field 
	private String certifExencion;
	
	/** ajusteInflacion. */
	@Field
	private String ajusteInflacion;
	
	/** respAnteIngBrutos. */
	@Field
	private String respAnteIngBrutos;
	
	/** numIngBrutos. */
	@Field
	private String numIngBrutos;
	
	/** marcaEmpleador. */
	@Field
	private String marcaEmpleador;
	
	/** marcaImpEndeudamiento. */
	@Field
	private String marcaImpEndeudamiento;
	
	/** marcaVigilancia. */
	@Field
	private String marcaVigilancia;
	
	/** marcaAutonomo. */
	@Field
	private String marcaAutonomo;
	
	/** ultPagoPrev. */
	@Field
	private String ultPagoPrev;
	
	/** marcaRetenedor. */
	@Field
	private String marcaRetenedor;
	
	/** marcaAutoretenedor. */
	@Field
	private String marcaAutoretenedor;
	
	/** marcaPactoFiscal. */
	@Field
	private String marcaPactoFiscal;
	
	/** infPactoFiscal. */
	@Field
	private String infPactoFiscal;
	
	/** marcaAgenteRet. */
	@Field
	private String marcaAgenteRet;
	
	/** marcaCertAgenteRet. */
	@Field
	private String marcaCertAgenteRet;
	
	/** pjeExclRet. */
	@Field
	private String pjeExclRet;
	
	/** numDNRP. */
	@Field
	private String numDNRP;
	
	/** marcaFutImp1. */
	@Field
	private String marcaFutImp1;
	
	/** marcaFutImp2. */
	@Field
	private String marcaFutImp2;
	
	/** marcaFutImp3. */
	@Field
	private String marcaFutImp3;
	
	/** marcaFutImp4. */
	@Field
	private String marcaFutImp4;
	
	/** marcaFutImp5. */
	@Field
	private String marcaFutImp5;
	
	/** timestamp. */
	@Field
	private String timestamp;

	public String getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public String getSecAnteCbioSitImp() {
		return secAnteCbioSitImp;
	}

	public void setSecAnteCbioSitImp(String secAnteCbioSitImp) {
		this.secAnteCbioSitImp = secAnteCbioSitImp;
	}

	public String getfInicioSitImpositiva() {
		return fInicioSitImpositiva;
	}

	public void setfInicioSitImpositiva(String fInicioSitImpositiva) {
		this.fInicioSitImpositiva = fInicioSitImpositiva;
	}

	public String getfFinSitImpositiva() {
		return fFinSitImpositiva;
	}

	public void setfFinSitImpositiva(String fFinSitImpositiva) {
		this.fFinSitImpositiva = fFinSitImpositiva;
	}

	public String getAgDePercepIngBrutos() {
		return agDePercepIngBrutos;
	}

	public void setAgDePercepIngBrutos(String agDePercepIngBrutos) {
		this.agDePercepIngBrutos = agDePercepIngBrutos;
	}

	public String getRespAnteIVA() {
		return respAnteIVA;
	}

	public void setRespAnteIVA(String respAnteIVA) {
		this.respAnteIVA = respAnteIVA;
	}

	public String getConstTipoRespAFIP() {
		return constTipoRespAFIP;
	}

	public void setConstTipoRespAFIP(String constTipoRespAFIP) {
		this.constTipoRespAFIP = constTipoRespAFIP;
	}

	public String getCertifExclPerc3337() {
		return certifExclPerc3337;
	}

	public void setCertifExclPerc3337(String certifExclPerc3337) {
		this.certifExclPerc3337 = certifExclPerc3337;
	}

	public String getFecInicioExclusion() {
		return fecInicioExclusion;
	}

	public void setFecInicioExclusion(String fecInicioExclusion) {
		this.fecInicioExclusion = fecInicioExclusion;
	}

	public String getFecVtoExclusion() {
		return fecVtoExclusion;
	}

	public void setFecVtoExclusion(String fecVtoExclusion) {
		this.fecVtoExclusion = fecVtoExclusion;
	}

	public String getRespAnteGcias() {
		return respAnteGcias;
	}

	public void setRespAnteGcias(String respAnteGcias) {
		this.respAnteGcias = respAnteGcias;
	}

	public String getCertifNoRetencion() {
		return certifNoRetencion;
	}

	public void setCertifNoRetencion(String certifNoRetencion) {
		this.certifNoRetencion = certifNoRetencion;
	}

	public String getPjeNoRetGcias() {
		return pjeNoRetGcias;
	}

	public void setPjeNoRetGcias(String pjeNoRetGcias) {
		this.pjeNoRetGcias = pjeNoRetGcias;
	}

	public String getFecInicioCert() {
		return fecInicioCert;
	}

	public void setFecInicioCert(String fecInicioCert) {
		this.fecInicioCert = fecInicioCert;
	}

	public String getFecVtoCert() {
		return fecVtoCert;
	}

	public void setFecVtoCert(String fecVtoCert) {
		this.fecVtoCert = fecVtoCert;
	}

	public String getCertifExencion() {
		return certifExencion;
	}

	public void setCertifExencion(String certifExencion) {
		this.certifExencion = certifExencion;
	}

	public String getAjusteInflacion() {
		return ajusteInflacion;
	}

	public void setAjusteInflacion(String ajusteInflacion) {
		this.ajusteInflacion = ajusteInflacion;
	}

	public String getRespAnteIngBrutos() {
		return respAnteIngBrutos;
	}

	public void setRespAnteIngBrutos(String respAnteIngBrutos) {
		this.respAnteIngBrutos = respAnteIngBrutos;
	}

	public String getNumIngBrutos() {
		return numIngBrutos;
	}

	public void setNumIngBrutos(String numIngBrutos) {
		this.numIngBrutos = numIngBrutos;
	}

	public String getMarcaEmpleador() {
		return marcaEmpleador;
	}

	public void setMarcaEmpleador(String marcaEmpleador) {
		this.marcaEmpleador = marcaEmpleador;
	}

	public String getMarcaImpEndeudamiento() {
		return marcaImpEndeudamiento;
	}

	public void setMarcaImpEndeudamiento(String marcaImpEndeudamiento) {
		this.marcaImpEndeudamiento = marcaImpEndeudamiento;
	}

	public String getMarcaVigilancia() {
		return marcaVigilancia;
	}

	public void setMarcaVigilancia(String marcaVigilancia) {
		this.marcaVigilancia = marcaVigilancia;
	}

	public String getMarcaAutonomo() {
		return marcaAutonomo;
	}

	public void setMarcaAutonomo(String marcaAutonomo) {
		this.marcaAutonomo = marcaAutonomo;
	}

	public String getUltPagoPrev() {
		return ultPagoPrev;
	}

	public void setUltPagoPrev(String ultPagoPrev) {
		this.ultPagoPrev = ultPagoPrev;
	}

	public String getMarcaRetenedor() {
		return marcaRetenedor;
	}

	public void setMarcaRetenedor(String marcaRetenedor) {
		this.marcaRetenedor = marcaRetenedor;
	}

	public String getMarcaAutoretenedor() {
		return marcaAutoretenedor;
	}

	public void setMarcaAutoretenedor(String marcaAutoretenedor) {
		this.marcaAutoretenedor = marcaAutoretenedor;
	}

	public String getMarcaPactoFiscal() {
		return marcaPactoFiscal;
	}

	public void setMarcaPactoFiscal(String marcaPactoFiscal) {
		this.marcaPactoFiscal = marcaPactoFiscal;
	}

	public String getInfPactoFiscal() {
		return infPactoFiscal;
	}

	public void setInfPactoFiscal(String infPactoFiscal) {
		this.infPactoFiscal = infPactoFiscal;
	}

	public String getMarcaAgenteRet() {
		return marcaAgenteRet;
	}

	public void setMarcaAgenteRet(String marcaAgenteRet) {
		this.marcaAgenteRet = marcaAgenteRet;
	}

	public String getMarcaCertAgenteRet() {
		return marcaCertAgenteRet;
	}

	public void setMarcaCertAgenteRet(String marcaCertAgenteRet) {
		this.marcaCertAgenteRet = marcaCertAgenteRet;
	}

	public String getPjeExclRet() {
		return pjeExclRet;
	}

	public void setPjeExclRet(String pjeExclRet) {
		this.pjeExclRet = pjeExclRet;
	}

	public String getNumDNRP() {
		return numDNRP;
	}

	public void setNumDNRP(String numDNRP) {
		this.numDNRP = numDNRP;
	}

	public String getMarcaFutImp1() {
		return marcaFutImp1;
	}

	public void setMarcaFutImp1(String marcaFutImp1) {
		this.marcaFutImp1 = marcaFutImp1;
	}

	public String getMarcaFutImp2() {
		return marcaFutImp2;
	}

	public void setMarcaFutImp2(String marcaFutImp2) {
		this.marcaFutImp2 = marcaFutImp2;
	}

	public String getMarcaFutImp3() {
		return marcaFutImp3;
	}

	public void setMarcaFutImp3(String marcaFutImp3) {
		this.marcaFutImp3 = marcaFutImp3;
	}

	public String getMarcaFutImp4() {
		return marcaFutImp4;
	}

	public void setMarcaFutImp4(String marcaFutImp4) {
		this.marcaFutImp4 = marcaFutImp4;
	}

	public String getMarcaFutImp5() {
		return marcaFutImp5;
	}

	public void setMarcaFutImp5(String marcaFutImp5) {
		this.marcaFutImp5 = marcaFutImp5;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("numeroCliente", numeroCliente);
		builder.append("secAnteCbioSitImp", secAnteCbioSitImp);
		builder.append("fInicioSitImpositiva", fInicioSitImpositiva);
		builder.append("fFinSitImpositiva", fFinSitImpositiva);
		builder.append("agDePercepIngBrutos", agDePercepIngBrutos);
		builder.append("respAnteIVA", respAnteIVA);
		builder.append("constTipoRespAFIP", constTipoRespAFIP);
		builder.append("certifExclPerc3337", certifExclPerc3337);
		builder.append("fecInicioExclusion", fecInicioExclusion);
		builder.append("fecVtoExclusion", fecVtoExclusion);
		builder.append("respAnteGcias", respAnteGcias);
		builder.append("certifNoRetencion", certifNoRetencion);
		builder.append("pjeNoRetGcias", pjeNoRetGcias);
		builder.append("fecInicioCert", fecInicioCert);
		builder.append("fecVtoCert", fecVtoCert);
		builder.append("certifExencion", certifExencion);
		builder.append("ajusteInflacion", ajusteInflacion);
		builder.append("respAnteIngBrutos", respAnteIngBrutos);
		builder.append("numIngBrutos", numIngBrutos);
		builder.append("marcaEmpleador", marcaEmpleador);
		builder.append("marcaImpEndeudamiento", marcaImpEndeudamiento);
		builder.append("marcaVigilancia", marcaVigilancia);
		builder.append("marcaAutonomo", marcaAutonomo);
		builder.append("ultPagoPrev", ultPagoPrev);
		builder.append("marcaRetenedor", marcaRetenedor);
		builder.append("marcaAutoretenedor", marcaAutoretenedor);
		builder.append("marcaPactoFiscal", marcaPactoFiscal);
		builder.append("infPactoFiscal", infPactoFiscal);
		builder.append("marcaAgenteRet", marcaAgenteRet);
		builder.append("marcaCertAgenteRet", marcaCertAgenteRet);
		builder.append("pjeExclRet", pjeExclRet);
		builder.append("numDNRP", numDNRP);
		builder.append("marcaFutImp1", marcaFutImp1);
		builder.append("marcaFutImp2", marcaFutImp2);
		builder.append("marcaFutImp3", marcaFutImp3);
		builder.append("marcaFutImp4", marcaFutImp4);
		builder.append("marcaFutImp5", marcaFutImp5);
		builder.append("timestamp", timestamp);
		return builder.toString();
	}
	

}

package ar.com.santanderrio.obp.servicios.echeq.entities;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.generated.webservices.echeq.Cheques;
import ar.com.santanderrio.obp.generated.webservices.echeq.Cuenta;
import ar.com.santanderrio.obp.generated.webservices.echeq.IMF;

public class RetrieveMarketInfrastructureInOutEntity {

	private String trkJsessionid;
	private javax.xml.ws.Holder<java.lang.String> code;
	private javax.xml.ws.Holder<java.lang.String> message;
	private javax.xml.ws.Holder<ar.com.santanderrio.obp.generated.webservices.echeq.CCEStat> stat;
	private javax.xml.ws.Holder<String> intchequeId;
	private javax.xml.ws.Holder<String> codVisualizacion;
	private javax.xml.ws.Holder<String> beneficiarioDocumentoTipo;
	private javax.xml.ws.Holder<String> beneficiarioDocumento;
	private javax.xml.ws.Holder<String> beneficiarioRazonSocial;
	private javax.xml.ws.Holder<Double> montoTotalEmitido;
	private javax.xml.ws.Holder<Double> montoTotalRecib_pendRecepcion;
	private javax.xml.ws.Holder<Double> montoTotalRecibido;
	private javax.xml.ws.Holder<Double> montoTotalCustodia;
	private javax.xml.ws.Holder<Double> montoTotalEmitPendRecepcion;
	private javax.xml.ws.Holder<Double> totalCheques;
	private javax.xml.ws.Holder<Cheques> cheques;
	private javax.xml.ws.Holder<List<Cuenta>> cuenta;
	private javax.xml.ws.Holder<List<IMF>> infraestructurasMercado;
	private EstadoRespuesta estadoRespuesta;

	public RetrieveMarketInfrastructureInOutEntity() {
	}

	public RetrieveMarketInfrastructureInOutEntity(String trkJsessionid) {
		this.trkJsessionid = trkJsessionid;
	}

	public String getTrkJsessionid() {
		return trkJsessionid;
	}

	public void setTrkJsessionid(String trkJsessionid) {
		this.trkJsessionid = trkJsessionid;
	}

	public javax.xml.ws.Holder<java.lang.String> getCode() {
		return code;
	}

	public void setCode(javax.xml.ws.Holder<java.lang.String> code) {
		this.code = code;
	}

	public javax.xml.ws.Holder<java.lang.String> getMessage() {
		return message;
	}

	public void setMessage(javax.xml.ws.Holder<java.lang.String> message) {
		this.message = message;
	}

	public javax.xml.ws.Holder<ar.com.santanderrio.obp.generated.webservices.echeq.CCEStat> getStat() {
		return stat;
	}

	public void setStat(javax.xml.ws.Holder<ar.com.santanderrio.obp.generated.webservices.echeq.CCEStat> stat) {
		this.stat = stat;
	}

	public javax.xml.ws.Holder<String> getIntchequeId() {
		return intchequeId;
	}

	public void setIntchequeId(javax.xml.ws.Holder<String> intchequeId) {
		this.intchequeId = intchequeId;
	}

	public javax.xml.ws.Holder<String> getCodVisualizacion() {
		return codVisualizacion;
	}

	public void setCodVisualizacion(javax.xml.ws.Holder<String> codVisualizacion) {
		this.codVisualizacion = codVisualizacion;
	}

	public javax.xml.ws.Holder<String> getBeneficiarioDocumentoTipo() {
		return beneficiarioDocumentoTipo;
	}

	public void setBeneficiarioDocumentoTipo(javax.xml.ws.Holder<String> beneficiarioDocumentoTipo) {
		this.beneficiarioDocumentoTipo = beneficiarioDocumentoTipo;
	}

	public javax.xml.ws.Holder<String> getBeneficiarioDocumento() {
		return beneficiarioDocumento;
	}

	public void setBeneficiarioDocumento(javax.xml.ws.Holder<String> beneficiarioDocumento) {
		this.beneficiarioDocumento = beneficiarioDocumento;
	}

	public javax.xml.ws.Holder<String> getBeneficiarioRazonSocial() {
		return beneficiarioRazonSocial;
	}

	public void setBeneficiarioRazonSocial(javax.xml.ws.Holder<String> beneficiarioRazonSocial) {
		this.beneficiarioRazonSocial = beneficiarioRazonSocial;
	}

	public javax.xml.ws.Holder<Double> getMontoTotalEmitido() {
		return montoTotalEmitido;
	}

	public void setMontoTotalEmitido(javax.xml.ws.Holder<Double> montoTotalEmitido) {
		this.montoTotalEmitido = montoTotalEmitido;
	}

	public javax.xml.ws.Holder<Double> getMontoTotalRecib_pendRecepcion() {
		return montoTotalRecib_pendRecepcion;
	}

	public void setMontoTotalRecib_pendRecepcion(javax.xml.ws.Holder<Double> montoTotalRecib_pendRecepcion) {
		this.montoTotalRecib_pendRecepcion = montoTotalRecib_pendRecepcion;
	}

	public javax.xml.ws.Holder<Double> getMontoTotalRecibido() {
		return montoTotalRecibido;
	}

	public void setMontoTotalRecibido(javax.xml.ws.Holder<Double> montoTotalRecibido) {
		this.montoTotalRecibido = montoTotalRecibido;
	}

	public javax.xml.ws.Holder<Double> getMontoTotalCustodia() {
		return montoTotalCustodia;
	}

	public void setMontoTotalCustodia(javax.xml.ws.Holder<Double> montoTotalCustodia) {
		this.montoTotalCustodia = montoTotalCustodia;
	}

	public javax.xml.ws.Holder<Double> getMontoTotalEmitPendRecepcion() {
		return montoTotalEmitPendRecepcion;
	}

	public void setMontoTotalEmitPendRecepcion(javax.xml.ws.Holder<Double> montoTotalEmitPendRecepcion) {
		this.montoTotalEmitPendRecepcion = montoTotalEmitPendRecepcion;
	}

	public javax.xml.ws.Holder<Double> getTotalCheques() {
		return totalCheques;
	}

	public void setTotalCheques(javax.xml.ws.Holder<Double> totalCheques) {
		this.totalCheques = totalCheques;
	}

	public javax.xml.ws.Holder<Cheques> getCheques() {
		return cheques;
	}

	public void setCheques(javax.xml.ws.Holder<Cheques> cheques) {
		this.cheques = cheques;
	}

	public javax.xml.ws.Holder<List<Cuenta>> getCuenta() {
		return cuenta;
	}

	public void setCuenta(javax.xml.ws.Holder<List<Cuenta>> cuenta) {
		this.cuenta = cuenta;
	}

	public javax.xml.ws.Holder<List<IMF>> getInfraestructurasMercado() {
		return infraestructurasMercado;
	}

	public void setInfraestructurasMercado(javax.xml.ws.Holder<List<IMF>> infraestructurasMercado) {
		this.infraestructurasMercado = infraestructurasMercado;
	}

	public EstadoRespuesta getEstadoRespuesta() {
		return estadoRespuesta;
	}

	public void setEstadoRespuesta(EstadoRespuesta estadoRespuesta) {
		this.estadoRespuesta = estadoRespuesta;
	}

}

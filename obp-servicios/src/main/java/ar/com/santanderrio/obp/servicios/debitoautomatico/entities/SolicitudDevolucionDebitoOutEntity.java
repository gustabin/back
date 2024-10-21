package ar.com.santanderrio.obp.servicios.debitoautomatico.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

@Record
public class SolicitudDevolucionDebitoOutEntity {

	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoResultadoExtendido;
	
	@Field
	private String fecOri;
	
	@Field
	private String importe;
	
	@Field
	private String cdTipo;
	
	@Field
	private String cdSucursal;
	
	@Field
	private String cdNumero;
	
	@Field
	private String digVerif;
	
	@Field
	private String titular;
	
	@Field
	private String ieoEntidad;
	
	@Field
	private String ieoSucursal;
	
	@Field
	private String firmante;
	
	@Field
	private String traceOrig;
	
	@Field
	private String importeDebacre;
	
	@Field
	private String cotizacion;
	
	@Field
	private String senalCruce;
	
	@Field
	private String resto;
	
	public String getHeaderTrama() {
		return headerTrama;
	}

	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	public String getCodigoResultadoExtendido() {
		return codigoResultadoExtendido;
	}

	public void setCodigoResultadoExtendido(String codigoRetornoExtendido) {
		this.codigoResultadoExtendido = codigoRetornoExtendido;
	}

	public String getFecOri() {
		return fecOri;
	}

	public void setFecOri(String fecOri) {
		this.fecOri = fecOri;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getCdTipo() {
		return cdTipo;
	}

	public void setCdTipo(String cdTipo) {
		this.cdTipo = cdTipo;
	}

	public String getCdSucursal() {
		return cdSucursal;
	}

	public void setCdSucursal(String cdSucursal) {
		this.cdSucursal = cdSucursal;
	}

	public String getCdNumero() {
		return cdNumero;
	}

	public void setCdNumero(String cdNumero) {
		this.cdNumero = cdNumero;
	}

	public String getDigVerif() {
		return digVerif;
	}

	public void setDigVerif(String digVerif) {
		this.digVerif = digVerif;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getIeoEntidad() {
		return ieoEntidad;
	}

	public void setIeoEntidad(String ieoEntidad) {
		this.ieoEntidad = ieoEntidad;
	}

	public String getIeoSucursal() {
		return ieoSucursal;
	}

	public void setIeoSucursal(String ieoSucursal) {
		this.ieoSucursal = ieoSucursal;
	}

	public String getFirmante() {
		return firmante;
	}

	public void setFirmante(String firmante) {
		this.firmante = firmante;
	}

	public String getTraceOrig() {
		return traceOrig;
	}

	public void setTraceOrig(String traceOrig) {
		this.traceOrig = traceOrig;
	}

	public String getImporteDebacre() {
		return importeDebacre;
	}

	public void setImporteDebacre(String importeDebacre) {
		this.importeDebacre = importeDebacre;
	}

	public String getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	public String getSenalCruce() {
		return senalCruce;
	}

	public void setSenalCruce(String senalCruce) {
		this.senalCruce = senalCruce;
	}

	public String getResto() {
		return resto;
	}

	public void setResto(String resto) {
		this.resto = resto;
	}

}

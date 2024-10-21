package ar.com.santanderrio.obp.servicios.compraventa.dto;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.view.EstadisticaView;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

public class VentaUSDDTO extends RsaDTO implements EstadisticaView {

	private static final long serialVersionUID = -8377954828783454472L;

	public VentaUSDDTO() {
		super(OperacionesRSAEnum.VENTA_USD);
	}

	@Override
	public Estadistica cargarEstadistica() {
		// TODO Auto-generated method stub
		return null;
	}

	/** The cbu. */
	private String cbu;

	/** The titular. */
	private String titular;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The nro cuenta destino. */
	private String nroCuentaDestino = "";

	/** The importe. */
	private String importe;
	
	/** The importe USD. */
	private String importeUSD;

	/** The moneda. */
	private String moneda;

	/** The fechaEjecucion. */
	private String fechaEjecucion;

	/** The fecha acreditacion. */
	private String fechaAcreditacion;

	/** The cuenta propia. */
	/*private boolean cuentaPropia;*/

	/** The is rio rio. */
	/*private Boolean isRioRio;*/

	/** The error banelco. */
	private boolean errorBanelco = false;

	/** The celular my A. */
	private boolean celularMyA = false;

	/** The isInmediata. */
	/*@JsonIgnore
	private boolean isInmediata = false;*/

	/** The saldoCuentaOrigen. */
	@JsonIgnore
	private String saldoCuentaOrigen;

	/** The tipoCuentaEnum. */
	@JsonIgnore
	private TipoCuenta tipoCuentaEnum;

	/** The monedaAltair. */
	@JsonIgnore
	private String monedaAltair;
	
	/** The fecha creacion de Destinatario en la agenda.*/
    private String fechaCreacionDestinatario = null;
    
    private Integer cantDiasUltimoCambioClave;
	
	private Integer cantDiasUltimoCambioToken;
	
	private boolean acumdiariousd = false;
	
	private String montoAcumuladoUSD;

	public String getFechaCreacionDestinatario() {
		return fechaCreacionDestinatario;
	}

	public void setFechaCreacionDestinatario(String fechaCreacionDestinatario) {
		this.fechaCreacionDestinatario = fechaCreacionDestinatario;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getNroCuentaDestino() {
		return nroCuentaDestino;
	}

	public void setNroCuentaDestino(String nroCuentaDestino) {
		this.nroCuentaDestino = nroCuentaDestino;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getImporteUSD() {
		return importeUSD;
	}

	public void setImporteUSD(String importeUSD) {
		this.importeUSD = importeUSD;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getFechaEjecucion() {
		return fechaEjecucion;
	}

	public void setFechaEjecucion(String fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public String getFechaAcreditacion() {
		return fechaAcreditacion;
	}

	public void setFechaAcreditacion(String fechaAcreditacion) {
		this.fechaAcreditacion = fechaAcreditacion;
	}

	/*public boolean isCuentaPropia() {
		return cuentaPropia;
	}

	public void setCuentaPropia(boolean cuentaPropia) {
		this.cuentaPropia = cuentaPropia;
	}

	public Boolean getIsRioRio() {
		return isRioRio;
	}

	public void setIsRioRio(Boolean isRioRio) {
		this.isRioRio = isRioRio;
	}*/

	public boolean isErrorBanelco() {
		return errorBanelco;
	}

	public void setErrorBanelco(boolean errorBanelco) {
		this.errorBanelco = errorBanelco;
	}

	public boolean isCelularMyA() {
		return celularMyA;
	}

	public void setCelularMyA(boolean celularMyA) {
		this.celularMyA = celularMyA;
	}

	/*public boolean isInmediata() {
		return isInmediata;
	}

	public void setInmediata(boolean isInmediata) {
		this.isInmediata = isInmediata;
	}*/

	public String getSaldoCuentaOrigen() {
		return saldoCuentaOrigen;
	}

	public void setSaldoCuentaOrigen(String saldoCuentaOrigen) {
		this.saldoCuentaOrigen = saldoCuentaOrigen;
	}

	public TipoCuenta getTipoCuentaEnum() {
		return tipoCuentaEnum;
	}

	public void setTipoCuentaEnum(TipoCuenta tipoCuentaEnum) {
		this.tipoCuentaEnum = tipoCuentaEnum;
	}

	public String getMonedaAltair() {
		return monedaAltair;
	}

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

	public boolean isAcumdiariousd() {
		return acumdiariousd;
	}

	public void setAcumdiariousd(boolean acumdiariousd) {
		this.acumdiariousd = acumdiariousd;
	}

	public String getMontoAcumuladoUSD() {
		return montoAcumuladoUSD;
	}

	public void setMontoAcumuladoUSD(String montoAcumuladoUSD) {
		this.montoAcumuladoUSD = montoAcumuladoUSD;
	}

}
